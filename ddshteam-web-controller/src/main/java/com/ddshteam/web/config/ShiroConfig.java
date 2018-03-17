package com.ddshteam.web.config;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.ddshteam.web.shrio.AuthRealm;
import com.ddshteam.web.shrio.ShiroSessionListener;
import com.google.common.collect.Lists;

@Configuration
@Component
public class ShiroConfig {

	private static Long sessionTimeOut = 1800000L;

	private static Long sessionInterval = 30000L;

	@Autowired
	private AuthRealm authRealm;

	@Bean
	public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

		shiroFilterFactoryBean.setSecurityManager(securityManager);

		// filter
		Map<String, String> map = new LinkedHashMap<String, String>();

		// login
		map.put("/logout", "anon");
		map.put("/login", "anon");

		// swagger
		map.put("/swagger-ui.html", "anon");
		map.put("/webjars/**", "anon");
		map.put("/swagger-resources/**", "anon");
		map.put("/v2/api-docs", "anon");

		// static resource
		map.put("/static/**", "anon");

		// all,开发模式下不验证权限
		map.put("/**", "authc");

		shiroFilterFactoryBean.setLoginUrl("/login");
		shiroFilterFactoryBean.setUnauthorizedUrl("/unauthorized");

		shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
		return shiroFilterFactoryBean;
	}

	@Bean(name = "sessionRedisDao")
	public EnterpriseCacheSessionDAO sessionRedisDao() {
		EnterpriseCacheSessionDAO sessionRedisDao = new EnterpriseCacheSessionDAO();
		return sessionRedisDao;
	}

	@Bean(name = "sessionListener")
	public SessionListener sessionListener() {
		return new ShiroSessionListener();
	}

	@Bean(name = "sessionManager")
	public SessionManager sessionManager(@Qualifier("sessionRedisDao") EnterpriseCacheSessionDAO sessionRedisDao,
			@Qualifier("sessionListener") SessionListener sessionListener) {
		DefaultWebSessionManager manager = new DefaultWebSessionManager();
		manager.setSessionValidationSchedulerEnabled(true);
		manager.setGlobalSessionTimeout(sessionTimeOut);
		manager.setSessionValidationInterval(sessionInterval);
		manager.setSessionDAO(sessionRedisDao);

		List<SessionListener> listeners = Lists.newArrayList();
		listeners.add(sessionListener);
		manager.setSessionListeners(listeners);
		return manager;
	}

	// @Bean(name = "cacheManager")
	// public MemoryConstrainedCacheManager cacheManager() {
	// MemoryConstrainedCacheManager cacheManager = new
	// MemoryConstrainedCacheManager();
	// return cacheManager;
	// }

	@Bean(name = "securityManager")
	public SecurityManager securityManager(@Qualifier("authRealm") AuthRealm authRealm,
			@Qualifier("cookieRememberMeManager") CookieRememberMeManager cookieRememberMeManager,
			@Qualifier("sessionManager") SessionManager sessionManager) {
		DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
		manager.setRealm(authRealm);
		manager.setSessionManager(sessionManager);
		manager.setRememberMeManager(cookieRememberMeManager);
		return manager;
	}

	// @Bean(name = "authRealm")
	// public AuthRealm authRealm() {
	// AuthRealm authRealm = new AuthRealm();
	// return authRealm;
	// }

	@Bean
	public SimpleCookie rememberMeCookie() {
		// checkbox 的name = rememberMe
		SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
		// cookie生效时间30天
		simpleCookie.setMaxAge(259200);
		return simpleCookie;
	}

	// cookie管理器
	@Bean(name = "cookieRememberMeManager")
	public CookieRememberMeManager rememberMeManager() {
		// System.out.println("rememberMeManager()");
		CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
		cookieRememberMeManager.setCipherKey(Base64.decode("'6ZmI6I2j5Y+R5aSn5ZOlAA=='"));
		cookieRememberMeManager.setCookie(rememberMeCookie());
		return cookieRememberMeManager;
	}

	// 密码匹配凭证管理器
	/*
	 * @Bean(name = "hashedCredentialsMatcher") public HashedCredentialsMatcher
	 * hashedCredentialsMatcher() { HashedCredentialsMatcher
	 * hashedCredentialsMatcher = new HashedCredentialsMatcher();
	 * hashedCredentialsMatcher.setHashAlgorithmName("MD5");
	 * hashedCredentialsMatcher.setHashIterations(2); return
	 * hashedCredentialsMatcher; }
	 */

	@Bean
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	// AOP方法级权限检查
	@Bean
	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
		creator.setProxyTargetClass(true);
		return creator;
	}

	/**
	 *  权限Bean处理器
	 *  使用: @RequiresPermissions("userInfo:test")
	 * @param manager
	 * @return
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
			@Qualifier("securityManager") SecurityManager manager) {
		AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
		advisor.setSecurityManager(manager);
		return advisor;
	}
}
