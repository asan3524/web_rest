package com.ddshteam.web.config;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ddshteam.web.core.util.IdUtil;
import com.ddshteam.web.core.util.IpUtil;
import com.ddshteam.web.system.service.api.SysOpLogAsyncService;
import com.ddshteam.web.system.service.api.model.SysOpLogs;
import com.ddshteam.web.system.service.api.model.SysUserInfo;

@Aspect
@Component
public class HttpLogAspect {

	private ThreadLocal<Long> startTime = new ThreadLocal<Long>();

	@Pointcut("execution(public * com.ddshteam.web.controller..*Controller.*(..))")
	public void log() {
	}

	@Reference(version = "1.0.0", timeout = 5000, async = true)
	private SysOpLogAsyncService sysOpLogAsyncService;

	/**
	 * 记录HTTP请求开始时的日志
	 */
	@Before("log()")
	public void doBefore() {
		startTime.set(System.currentTimeMillis());
		// ServletRequestAttributes attributes = (ServletRequestAttributes)
		// RequestContextHolder.getRequestAttributes();
		//
		// HttpServletRequest request = attributes.getRequest();
		// // URL
		// logger.info("url={}", request.getRequestURI());
		// // method
		// logger.info("method={}", request.getMethod());
		// // ip
		// logger.info("ip={}", IpUtil.getIpAddr(request)/*
		// request.getRemoteAddr() */);
		// // 类方法
		// logger.info("class={} and method name = {}",
		// joinPoint.getSignature().getDeclaringTypeName(), joinPoint
		// .getSignature().getName());
		// // 参数
		// logger.info("参数={}", joinPoint.getArgs());
	}

	/**
	 * 记录HTTP请求结束时的日志
	 */
	// @After("log()")
	// public void doAfter() {
	// // setUser();
	// // ServletRequestAttributes attributes = (ServletRequestAttributes)
	// // RequestContextHolder.getRequestAttributes();
	// // HttpServletRequest request = attributes.getRequest();
	// //
	// // logger.info("method={}", request.getMethod());
	// // logger.info("ip={}", IpUtil.getIpAddr(request));
	// // logger.info("url = {}", request.getRequestURL());
	// // logger.info("user = {}", user.get().toString());
	// // logger.info("class={} and method name = {}",
	// // joinPoint.getSignature().getDeclaringTypeName(), joinPoint
	// // .getSignature().getName());
	// // logger.info("执行时间={}", System.currentTimeMillis() - startTime.get());
	// }

	/**
	 * 获取返回内容
	 * @param object
	 */
	@SuppressWarnings("rawtypes")
	@AfterReturning(returning = "object", pointcut = "log()")
	public void doAfterReturn(Object object) {

		SysUserInfo user = getUser();

		if (null != user) {
			ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
					.getRequestAttributes();
			HttpServletRequest request = attributes.getRequest();
			SysOpLogs sysOpLogs = new SysOpLogs();
			sysOpLogs.setId(IdUtil.generateId().toString());
			sysOpLogs.setAccount(user.getAccount());
			sysOpLogs.setName(user.getName());
			sysOpLogs.setExcuteTime((int) (System.currentTimeMillis() - startTime.get()));
			sysOpLogs.setIp(IpUtil.getIpAddr(request));
			sysOpLogs.setUri(request.getRequestURI());

			if (null != object && object instanceof ResponseEntity) {
				Object obj = ((ResponseEntity) object).getBody();
				if (obj instanceof Map) {
					sysOpLogs.setResp(((Map) obj).get("httpCode").toString());
				}
			}
			
			sysOpLogAsyncService.write(sysOpLogs);
			
			if ("/logout".equals(sysOpLogs.getUri())) {
				SecurityUtils.getSubject().logout();
			}
		}
	}

	private SysUserInfo getUser() {
		Subject subject = null;
		try {
			subject = SecurityUtils.getSubject();
		} catch (Exception e) {
		}
		if (null != subject) {
			Object pc = subject.getPrincipals();
			if (null != pc) {
				Object u = ((PrincipalCollection) pc).getPrimaryPrincipal();
				if (null != u) {
					return (SysUserInfo) u;
				}
			}
		}
		return null;
	}
}
