package com.ddshteam.web.controller.goods;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ddsh.goods.service.api.IGoodsBrandService;
import com.ddsh.goods.service.api.IGoodsTypeService;

import io.swagger.annotations.Api;

/**
 * 物资类型服务
 * @ClassName: GoodsTypeController
 * @author arpgate
 * @date 2018年3月1日 上午10:14:43
 * @version v1.0.0
 * 
 */
@Api(value = "/goodstype", description = "物资类型接口")
@RestController
@RequestMapping(value = "/goodstype")
public class GoodsTypeController {

	@Reference(version = "1.0.0")
	private IGoodsTypeService goodsTypeService;
	
	@Reference(version = "1.0.0")
	private IGoodsBrandService goodsBrandService;
}
