package com.ddshteam.web.shrio;

import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ddshteam.web.system.service.api.SysMenuService;
import com.ddshteam.web.system.service.api.SysUserService;
import com.ddshteam.web.system.service.api.model.SysUserInfo;

@Component
public class AuthRealm extends AuthorizingRealm {

	private final static Logger logger = LoggerFactory.getLogger(AuthorizingRealm.class);

	@Reference(version = "1.0.0")
	private SysUserService sysUserService;

	@Reference(version = "1.0.0")
	private SysMenuService sysMenuService;

	@Override
	public String getName() {
		return this.getClass().getName();
	}

	@Override
	public boolean supports(AuthenticationToken token) {
		return token instanceof UsernamePasswordToken;
	}

	/**
	 * @see http://blog.csdn.net/lishehe/article/details/45224181 
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SysUserInfo user = (SysUserInfo) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

		if (null == user) {
			return info;
		}

		/**
		 * 内置账户有所有权限
		 */
		if (user.getIsBuiltin()) {
			info.addStringPermissions(Constant.rootPermission());
		} else {
			info.addStringPermissions(sysMenuService.getPermissionByUser(user.getId()));
		}
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// 获取用户输入的token
		UsernamePasswordToken utoken = (UsernamePasswordToken) token;
		String principal = String.valueOf(utoken.getPrincipal()); // principal
		String credential = new String((char[]) utoken.getCredentials()); // credential

		logger.info((String) utoken.getUsername());
		logger.info(new String(utoken.getPassword()));

		SysUserInfo user = sysUserService.getUserByAccount(principal);
		if (null == user) {
			logger.warn("账号或密码不正确");
			throw new UnknownAccountException("账号或密码不正确");
		}

		if (user.getStatus() == 0) {
			logger.warn("账号已被锁定");
			throw new LockedAccountException("账号已被锁定");
		}

		if (!credential.equals(user.getPassword())) {
			logger.warn("密码不正确");
			throw new IncorrectCredentialsException("密码不正确");
		}

		/**
		 * TODO
		 * ExpiredCredentialsException 过期
		 * DisabledAccountException    禁用
		 * ExcessiveAttemptsException  登录错误次数过多
		 */

		logger.warn("PASSWORD IS WRONG: {}", principal, new String(utoken.getPassword()));
		return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
	}

	// clear somebody's cache
	public void clearCachedAuthorizationInfo(String principal) {
		SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
		clearCachedAuthorizationInfo(principals);
	}

	// clear all cache
	public void clearAllCachedAuthorizationInfo() {
		Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
		if (cache != null) {
			for (Object key : cache.keys()) {
				cache.remove(key);
			}
		}
	}
}
