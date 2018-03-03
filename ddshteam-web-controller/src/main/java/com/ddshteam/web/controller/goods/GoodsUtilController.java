package com.ddshteam.web.controller.goods;

import java.util.Map;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ddsh.goods.service.api.constant.GoodsContants;
import com.ddshteam.web.core.base.BaseController;
import com.ddshteam.web.core.support.HttpCode;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "/goods/util", description = "物资工具服务-已测试")
@RestController
@RequestMapping(value = "/goods/util")
public class GoodsUtilController extends BaseController{

	private final static Logger logger = LoggerFactory.getLogger(GoodsUtilController.class);

	@ApiOperation(value = "获取常量类型", notes = "根据常量类型名称获取常量类型详情")
	@PostMapping(value = { "/constanttype/{typename}" })
	public Object getConstantTypeByName(@PathVariable("typename") String typename)
	{
		logger.debug("GoodsUtilController.getConstantTypeByName()");

		if(StringUtils.isEmpty(typename)) {
			logger.error("typename is null.");
			return getResponse(HttpCode.BAD_REQUEST, false);
		}
		String desc=GoodsContants.CONSTANT_TYPE_DESC.get(typename);
		return getResponse(desc);

	}
	
	@ApiOperation(value = "获取常量类型", notes = "根据常量类型名称获取常量类型详情")
	@PostMapping(value = { "/constant/{typename}" })
	public Object getConstantByType(@PathVariable("typename") String typename)
	{
		logger.debug("GoodsUtilController.getConstantByType()");

		if(StringUtils.isEmpty(typename)) {
			logger.error("typename is null.");
			return getResponse(HttpCode.BAD_REQUEST, false);
		}
		Map<String, String> constant=GoodsContants.CONSTANT_DESC.get(typename);
		return getResponse(constant);
	}
}
