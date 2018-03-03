package com.ddshteam.web.controller.goods;


import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ddsh.goods.service.api.IGoodsService;
import com.ddsh.goods.service.api.data.GoodsInfoSearchReqData;
import com.ddsh.goods.service.api.model.GoodsInfo;
import com.ddshteam.web.core.base.BaseController;
import com.ddshteam.web.core.support.HttpCode;
import com.ddshteam.web.dto.goods.GoodsInfoReq;
import com.ddshteam.web.dto.goods.GoodsInfoSearchReq;
import com.github.pagehelper.PageInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 物资信息服务
 * @ClassName: GoodsInfoController
 * @author arpgate
 * @date 2018年3月1日 上午10:15:17
 * @version v1.0.0
 * 
 */
@Api(value = "/goods/goodsinfo", description = "物资类型接口")
@RestController
@RequestMapping(value = "/goods/goodsinfo")
public class GoodsInfoController extends BaseController {
	
	private final static Logger logger = LoggerFactory.getLogger(GoodsInfoController.class);
	
	@Reference(version = "1.0.0")
	private IGoodsService goodsService;
	
	@ApiOperation(value = "查看物资详情", notes = "根据物资id查询物资详情")
	@GetMapping(value = { "/id/{goodsid}" })
	public Object getGoodsInfoByid(@PathVariable("goodsid") String goodsid) {
		logger.debug("GoodsInfoController.getGoodsInfoByid()");

		if(StringUtils.isEmpty(goodsid)) {
			logger.error("goodsid is null.");
			return getResponse(HttpCode.BAD_REQUEST, false);
		}
		GoodsInfo goodsInfo=goodsService.get(goodsid);
		return getResponse(goodsInfo);
	}
	
	@ApiOperation(value = "查看物资列表详情", notes = "根据条件分页查询物资详情")
	@PostMapping(value = { "/getgoodsInfobycause" })
	public Object getGoodsInfoByCause( @Valid @RequestBody GoodsInfoSearchReq goodsInfoReq,@PageableDefault(page = 1, size = 10, sort = "order_num,asc") Pageable pageable, BindingResult errors) {
		logger.debug("GoodsInfoController.getgoodsInfobycause()");

		if (errors.hasErrors()) {
			String msg = errors.getAllErrors().get(0).getDefaultMessage();
			logger.error(msg);
			return getResponse(HttpCode.BAD_REQUEST, false, msg);
		}
		GoodsInfoSearchReqData goodsInfoSearchReqData=new GoodsInfoSearchReqData();
		goodsInfoSearchReqData.setBrandIds(goodsInfoReq.getBrandIds());
		goodsInfoSearchReqData.setTypeIds(goodsInfoReq.getTypeIds());
		goodsInfoSearchReqData.setCode(goodsInfoReq.getCode());
		goodsInfoSearchReqData.setName(goodsInfoReq.getName());
		
		PageInfo<GoodsInfo> pageInfos=goodsService.list(pageable.getPageNumber(), pageable.getPageSize(), goodsInfoSearchReqData);
		return getResponse(pageInfos);
	}
	
	@ApiOperation(value = "增加物资", notes = "根据条件分页查询物资详情")
	@PostMapping(value = { "/addgoods" })
	public Object addGoods( @Valid @RequestBody GoodsInfoReq goodsInforeq, BindingResult errors) {
		logger.debug("GoodsInfoController.addGoods()");

		if (errors.hasErrors()) {
			String msg = errors.getAllErrors().get(0).getDefaultMessage();
			logger.error(msg);
			return getResponse(HttpCode.BAD_REQUEST, false, msg);
		}
		
		GoodsInfo goodsInfo=new GoodsInfo();
		goodsInfo.setBrandId(goodsInforeq.getBrandId());
		goodsInfo.setColour(goodsInforeq.getColour());
		goodsInfo.setName(goodsInforeq.getName());
		goodsInfo.setNorm(goodsInforeq.getNorm());
		goodsInfo.setOrderNum(goodsInforeq.getOrderNum());
		goodsInfo.setRemark(goodsInforeq.getRemark());
		goodsInfo.setTypeId(goodsInforeq.getTypeId());
		goodsInfo.setUnit(goodsInforeq.getUnit());
        boolean status=goodsService.save(goodsInfo);
        return getResponse(status);
	}
	
	@ApiOperation(value = "更新物资", notes = "根据物资id更新物资信息")
	@PostMapping(value = { "/updategoods/{goodsid}" })
	public Object updateGoods( @Valid @RequestBody GoodsInfoReq goodsInforeq, @PathVariable("goodsid") String goodsid,BindingResult errors) {
		logger.debug("GoodsInfoController.updateGoods()");
		
		if (errors.hasErrors()) {
			String msg = errors.getAllErrors().get(0).getDefaultMessage();
			logger.error(msg);
			return getResponse(HttpCode.BAD_REQUEST, false, msg);
		}
		
		if(StringUtils.isEmpty(goodsid)) {
			logger.error("goodsid is null.");
			return getResponse(HttpCode.BAD_REQUEST, false);
		}
		
		GoodsInfo goodsInfo=new GoodsInfo();
		goodsInfo.setBrandId(goodsInforeq.getBrandId());
		goodsInfo.setColour(goodsInforeq.getColour());
		goodsInfo.setId(goodsid);
		goodsInfo.setName(goodsInforeq.getName());
		goodsInfo.setNorm(goodsInforeq.getNorm());
		goodsInfo.setOrderNum(goodsInforeq.getOrderNum());
		goodsInfo.setRemark(goodsInforeq.getRemark());
		goodsInfo.setTypeId(goodsInforeq.getTypeId());
		goodsInfo.setUnit(goodsInforeq.getUnit());
        boolean status=goodsService.update(goodsInfo);
        return getResponse(status);
	}
	
	@ApiOperation(value = "删除物资-单条", notes = "根据物资id删除物资信息")
	@PostMapping(value = { "/id/{goodsid}" })
	public Object deleteGoods(@PathVariable("goodsid") String goodsid) {
		logger.debug("GoodsInfoController.deleteGoods()");

		if(StringUtils.isEmpty(goodsid)) {
			logger.error("goodsid is null.");
			return getResponse(HttpCode.BAD_REQUEST, false);
		}
		
		boolean status=goodsService.delete(goodsid);
        return getResponse(status);
	}
	
	@ApiOperation(value = "删除物资-多条", notes = "根据物资ids删除物资信息")
	@PostMapping(value = { "/ids" })
	public Object deleteGoodss(@Valid @RequestBody List<String> goodsids) {
		logger.debug("GoodsInfoController.deleteGoodss()");

		if(goodsids==null||goodsids.isEmpty()) {
			logger.error("goodsids is null or empty.");
			return getResponse(HttpCode.BAD_REQUEST, false);
		}
		
		boolean status=goodsService.delete(goodsids);
        return getResponse(status);
	}
	
	
}
