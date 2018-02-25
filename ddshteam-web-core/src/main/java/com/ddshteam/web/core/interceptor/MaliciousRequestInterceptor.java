package com.ddshteam.web.core.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ddshteam.web.core.Constants;
import com.ddshteam.web.core.support.HttpCode;


/**
 * 恶意请求拦截器
 * 
 */
@Component
public class MaliciousRequestInterceptor extends BaseInterceptor {
	private Boolean allRequest = false; // 拦截所有请求,否则拦截相同请求
	
	@Value("${whayer.minRequestIntervalTime}")
	private Long minRequestIntervalTime = 100L; // 允许的最小请求间隔
	
	@Value("${whayer.maxMaliciousTimes}")
	private Integer maxMaliciousTimes = 0; // 允许的最大恶意请求次数

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST,GET,PUT,OPTIONS,DELETE");
		response.setHeader("Access-Control-Allow-Headers",
				"x-requested-with,Access-Control-Allow-Origin,EX-SysAuthToken,EX-JSESSIONID");

		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control","no-cache"); 
		response.setDateHeader("Expires", 0); //-1
		
		String url = request.getServletPath();
		if (url.endsWith("/unauthorized") || url.endsWith("/forbidden")) {
			return super.preHandle(request, response, handler);
		}
		HttpSession session = request.getSession();
		String preRequest = (String) session.getAttribute(Constants.PREREQUEST);
		Long preRequestTime = (Long) session.getAttribute(Constants.PREREQUEST_TIME);
		if (preRequestTime != null && preRequest != null) { // 过滤频繁操作
			if ((url.equals(preRequest) || allRequest)
					&& System.currentTimeMillis() - preRequestTime < minRequestIntervalTime) {
				Integer maliciousRequestTimes = (Integer) session.getAttribute(Constants.MALICIOUS_REQUEST_TIMES);
				if (maliciousRequestTimes == null) {
					maliciousRequestTimes = 1;
				} else {
					maliciousRequestTimes++;
				}
				session.setAttribute(Constants.MALICIOUS_REQUEST_TIMES, maliciousRequestTimes);
				if (maliciousRequestTimes > maxMaliciousTimes) {
					response.setStatus(HttpCode.MULTI_STATUS.value());
					logger.warn("To intercept a malicious request : {}", url);
					return false;
				}
			} else {
				session.setAttribute(Constants.MALICIOUS_REQUEST_TIMES, 0);
			}
		}
		session.setAttribute(Constants.PREREQUEST, url);
		session.setAttribute(Constants.PREREQUEST_TIME, System.currentTimeMillis());
		return super.preHandle(request, response, handler);
	}

	public void setAllRequest(Boolean allRequest) {
		this.allRequest = allRequest;
	}

	public void setMinRequestIntervalTime(Long minRequestIntervalTime) {
		this.minRequestIntervalTime = minRequestIntervalTime;
	}

	public void setMaxMaliciousTimes(Integer maxMaliciousTimes) {
		this.maxMaliciousTimes = maxMaliciousTimes;
	}
}
