package com.ddshteam.web.controller.system;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.annotations.ApiIgnore;

import com.ddshteam.web.core.base.BaseController;
import com.ddshteam.web.core.support.HttpCode;
import com.ddshteam.web.core.util.IpUtil;
import com.ddshteam.web.core.util.SecurityUtil;
import com.ddshteam.web.dto.system.LoginReqObj;
import com.ddshteam.web.system.service.api.model.SysUserInfo;

@Api(value = "/system/", description = "登陆接口")
@RestController
@RequestMapping(value = "/system/")
public class LoginController extends BaseController {

	private final static Logger logger = LoggerFactory.getLogger(LoginController.class);

	@ApiOperation(value = "登陆")
	@PostMapping(value = { "/login" })
	public Object login(@RequestBody LoginReqObj loginReqObj, BindingResult errors, HttpServletRequest request) {
		logger.debug("LoginController.login()");

		if (errors.hasErrors()) {
			String msg = errors.getAllErrors().get(0).getDefaultMessage();
			logger.error(msg);
			return getResponse(HttpCode.BAD_REQUEST, false, msg);
		}

		Subject subject = SecurityUtils.getSubject();

		subject.logout();
		if (!subject.isAuthenticated()) {
			String clientIp = IpUtil.getIpAddr(request);
			UsernamePasswordToken token = new UsernamePasswordToken(loginReqObj.getUsername(),
					SecurityUtil.encryptMd5(loginReqObj.getPassword()), clientIp);
			token.setRememberMe(true);
			try {
				subject.login(token);

				subject.getSession(true);

			} catch (AuthenticationException e) {
				// e.printStackTrace();
				return getResponse(HttpCode.LOGIN_FAIL, false, e.getMessage());
			}
		}
		SysUserInfo curUser = (SysUserInfo) subject.getPrincipals().getPrimaryPrincipal();
		curUser.setPassword(null);
		return getResponse(HttpCode.OK, curUser, "登陆成功");
	}

	@ApiOperation(value = "登陆提示, 无权限会默认重定向")
	@GetMapping(value = { "/login" })
	@ApiIgnore
	public Object login2Get() {
		logger.debug("LoginController.login2Get()");
		return getResponse(HttpCode.UNAUTHORIZED, "未授权,请先登录");
	}

	@ApiOperation(value = "登出")
	@PostMapping(value = { "/logout" })
	public Object logout() {
		logger.debug("LoginController.logout()");
		SecurityUtils.getSubject().logout();
		return getResponse(HttpCode.OK, true, "成功登出");
	}
}
