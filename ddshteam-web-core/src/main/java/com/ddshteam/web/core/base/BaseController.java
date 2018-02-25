package com.ddshteam.web.core.base;

import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.plugins.Page;
import com.ddshteam.web.core.support.DateFormat;
import com.ddshteam.web.core.support.HttpCode;
import com.ddshteam.web.core.util.EscapeHtmlUtil;
import com.ddshteam.web.core.util.InstanceUtil;
import com.ddshteam.web.core.util.WebUtil;


/**
 * 控制器基类
 * 
 */
public abstract class BaseController {
	private final static Logger logger = LoggerFactory.getLogger(BaseController.class);

	/** 获取当前用户Id */
	protected Long getCurrUser() {
		return WebUtil.getCurrentUser();
	}

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new DateFormat(), true));
	}
	
	protected <T> ResponseEntity<Map<String, Object>> getResponse(T data) {
		return getResponse(HttpCode.OK, data);
	}
	
	protected <T> ResponseEntity<Map<String, Object>> getResponse(HttpCode code, T data) {
		return getResponse(code, data, null);
	}
	
	protected <T> ResponseEntity<Map<String, Object>> getResponse(HttpCode code, T data, String msg) {
		Map<String, Object> map = InstanceUtil.newLinkedHashMap();
		
		map.put("data", data);
		map.put("httpCode", code.value());
		map.put("msg", StringUtils.isBlank(msg) ? code.msg() : msg);
		map.put("timestamp", System.currentTimeMillis());
		logger.info("RESPONSE : " + JSON.toJSONString(map));
		return ResponseEntity.ok(map);
	}

	@Deprecated
	protected <T> ResponseEntity<ModelMap> setModelMap(ModelMap modelMap, HttpCode code, T data) {
		Map<String, Object> map = InstanceUtil.newLinkedHashMap();
		map.putAll(modelMap);
		modelMap.clear();
		for (Iterator<String> iterator = map.keySet().iterator(); iterator.hasNext();) {
			String key = iterator.next();
			if (!key.startsWith("org.springframework.validation.BindingResult") && !key.equals("void")) {
				modelMap.put(key, map.get(key));
			}
		}
		if (data != null) {
			if (data instanceof Page) {
				Page<?> page = (Page<?>) data;
				modelMap.put("data", page.getRecords());
				modelMap.put("current", page.getCurrent());
				modelMap.put("size", page.getSize());
				modelMap.put("pages", page.getPages());
				modelMap.put("total", page.getTotal());
			}else if (data instanceof List<?>) {
				modelMap.put("data", data);
			} else {
				modelMap.put("data", data);
			}
		}
		modelMap.put("httpCode", code.value());
		modelMap.put("msg", code.msg());
		modelMap.put("timestamp", System.currentTimeMillis());
		logger.info("RESPONSE : " + JSON.toJSONString(modelMap));
		return ResponseEntity.ok(modelMap);
	}
	
	
	protected Map<String, String> loadParemeter(HttpServletRequest request){
		Map<String, String> paremeters = new HashMap<>();
		Enumeration<String> names = request.getParameterNames();
		String n;
		while(names.hasMoreElements()) {
			n = names.nextElement();
			if(!n.equals("page") && !n.equals("size") && !n.equals("sort")) {
				String value = $(request, n);
				if(null != value)
					paremeters.put(n, value);
			}
		}
		return paremeters;
	}
	
	protected String $(HttpServletRequest request, String key) {
		String value = request.getParameter(key);
		if(StringUtils.isBlank(value) || StringUtils.isEmpty(value)) 
			return null;
		return EscapeHtmlUtil.escapeHtml(value);
	}
	
	protected String[] $$(HttpServletRequest request, String key) {
		String[] value = request.getParameterValues(key);
		if (value == null || value.length == 0)
			return null;
		for (int i = 0; i < value.length; i++) {
			value[i] = EscapeHtmlUtil.escapeHtml(value[i]);
		}
		return value;
	}


	
	/** 异常处理,置于外层 */
	/*@ExceptionHandler(Exception.class)
	public void exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception ex)
			throws Exception {
		logger.error(Constants.Exception_Head, ex);
		ModelMap modelMap = new ModelMap();
		if (ex instanceof BaseException) {
			((BaseException) ex).handler(modelMap);
		} else if (ex instanceof IllegalArgumentException) {
			new IllegalParameterException(ex.getMessage()).handler(modelMap);
		} else if (ex instanceof UnauthorizedException) {
			modelMap.put("httpCode", HttpCode.FORBIDDEN.value().toString());
			modelMap.put("msg", StringUtils.defaultIfBlank(ex.getMessage(), HttpCode.FORBIDDEN.msg()));
		} else {
			modelMap.put("httpCode", HttpCode.INTERNAL_SERVER_ERROR.value().toString());
			String msg = StringUtils.defaultIfBlank(ex.getMessage(), HttpCode.INTERNAL_SERVER_ERROR.msg());
			modelMap.put("msg", msg.length() > 100 ? "系统异常,请稍候再试." : msg);
		}
		response.setContentType("application/json;charset=UTF-8");
		modelMap.put("timestamp", System.currentTimeMillis());
		logger.info("RESPONSE : " + JSON.toJSON(modelMap));
		byte[] bytes = JSON.toJSONBytes(modelMap, SerializerFeature.DisableCircularReferenceDetect);
		response.getOutputStream().write(bytes);
	}*/
}
