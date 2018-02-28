package com.ddshteam.web.controller.system;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ddshteam.web.core.base.BaseController;
import com.ddshteam.web.core.support.HttpCode;
import com.ddshteam.web.dto.system.LogInReq;
import com.ddshteam.web.system.service.api.SysUserService;

@Api(value = "/auth", description = "系统权限")
@RestController
@RequestMapping(value = "/auth")
public class SysAuthController extends BaseController {

	private final static Logger logger = LoggerFactory.getLogger(SysAuthController.class);

	@Reference(version = "1.0.0")
	private SysUserService sysUserService;

	@ApiOperation(value = "登陆", notes = "用户登陆系统，用户名,密码密文")
	@GetMapping(value = { "/login" })
	public Object getUserList(HttpServletRequest request,@Valid @RequestBody LogInReq loginreq, BindingResult errors) {
		logger.debug("SysAuthController.login()");
		if (errors.hasErrors()) {
			String msg = errors.getAllErrors().get(0).getDefaultMessage();
			logger.error(msg);
			return getResponse(HttpCode.BAD_REQUEST, false, msg);
		}
		return null;
	}
	
	@ApiOperation(value = "退出系统", notes = "退出系统")
	@GetMapping(value = { "/logout" })
	public Object getUserByAccount(HttpServletRequest request) {
		logger.debug("SysAuthController.logout()");

 

		return null;
	}
	
 
}
