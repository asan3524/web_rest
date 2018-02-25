package com.ddshteam.web.core.interceptor;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.net.util.Base64;
import org.springframework.web.util.WebUtils;

import com.alibaba.fastjson.JSON;
import com.ddshteam.web.core.support.HttpCode;
import com.ddshteam.web.core.support.security.coder.MDCoder;
import com.ddshteam.web.core.util.InstanceUtil;


/**
 * 签名验证拦截器
 * 
 */
public class SignInterceptor extends BaseInterceptor {

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Map<String, Object> params = WebUtils.getParametersStartingWith(request, null);//WebUtil.getParameterMap(request);
		String[] keys = params.keySet().toArray(new String[] {});
		Arrays.sort(keys);
		StringBuilder sb = new StringBuilder();
		for (String key : keys) {
			if (!"sign".equals(key) && !"token".equals(key) && !"dataFile".equals(key)) {
				if (sb.length() == 0) {
					sb = sb.append(key).append("=").append(params.get(key));
				} else {
					sb = sb.append("&").append(key).append("=").append(params.get(key));
				}
			}
		}
		String kv = null;
		//前端需要计算body的base64编码,并发送前100字符
		if (sb.length() > 100) {
			kv = sb.substring(0, 100);
		} else {
			kv = sb.toString();
		}
		String sign = Base64.encodeBase64String(MDCoder.encodeMD5(kv.getBytes("UTF-8")));
		if (!sign.equals(params.get("sign"))) {
			response.setContentType("application/json;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setStatus(200);
			
			logger.warn("签名认证失败，请求接口：{}，请求IP：{}，请求参数：{}",
                    request.getRequestURI(), getIpAddress(request), JSON.toJSONString(request.getParameterMap()));
			
			Map<String, Object> modelMap = InstanceUtil.newLinkedHashMap();
			modelMap.put("httpCode", HttpCode.FORBIDDEN.value());
			modelMap.put("msg", HttpCode.FORBIDDEN.msg());
			modelMap.put("timestamp", System.currentTimeMillis());
			PrintWriter out = response.getWriter();
			out.println(JSON.toJSONString(modelMap));
			out.flush();
			out.close();
			return false;
		}
		return super.preHandle(request, response, handler);
	}
	
	private String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        // 如果是多级代理，那么取第一个ip为客户端ip
        if (ip != null && ip.indexOf(",") != -1) {
            ip = ip.substring(0, ip.indexOf(",")).trim();
        }

        return ip;
    }
	
}
