package com.ddshteam.web.controller.util;

import java.util.Map;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ddshteam.web.core.base.BaseController;
import com.ddshteam.web.core.support.HttpCode;
import com.ddshteam.web.system.service.api.constant.SystemContants;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "/util", description = "系统工具服务-完成后端测试")
@RestController
@RequestMapping(value = "/util")
public class UtilController extends BaseController{

	private final static Logger logger = LoggerFactory.getLogger(UtilController.class);

	@ApiOperation(value = "获取常量类型", notes = "获取常量类型")
	@PostMapping(value = { "/constanttype" })
	public Object getConstantTypeByName( )
	{
		logger.debug("UtilController.getConstantTypeByName()");
 
		return getResponse(SystemContants.CONSTANT_TYPE_DESC);

	}
	
	@ApiOperation(value = "获取常量类型", notes = "根据常量类型名称获取常量类型详情")
	@PostMapping(value = { "/constant/{typename}" })
	public Object getConstantByType(@PathVariable("typename") String typename)
	{
		logger.debug("UtilController.getConstantByType()");

		if(StringUtils.isEmpty(typename)) {
			logger.error("typename is null.");
			return getResponse(HttpCode.BAD_REQUEST, false);
		}
		Map<String, String> constant=SystemContants.CONSTANT_DESC.get(typename);
		return getResponse(constant);
	}
}
