package com.ddshteam.web.controller.goods;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ddsh.goods.service.api.IGoodsBrandService;
import com.ddsh.goods.service.api.IGoodsService;
import com.ddsh.goods.service.api.IGoodsTypeService;
import com.ddsh.goods.service.api.model.GoodsBrandInfo;
import com.ddsh.goods.service.api.model.GoodsTypeInfo;
import com.ddshteam.web.core.base.BaseController;
import com.ddshteam.web.core.support.HttpCode;
import com.ddshteam.web.dto.goods.GoodsTypeInoReq;
import com.ddshteam.web.system.service.api.data.Tree;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 物资类型服务
 * @ClassName: GoodsTypeController
 * @author arpgate
 * @date 2018年3月1日 上午10:14:43
 * @version v1.0.0
 * 
 */
@Api(value = "/goods", description = "物资类型接口-完成后端测试")
@RestController
@RequestMapping(value = "/goods")
public class GoodsTypeController extends BaseController {

	private final static Logger logger = LoggerFactory.getLogger(GoodsTypeController.class);

	@Reference(version = "1.0.0")
	private IGoodsTypeService goodsTypeService;
	
	@Reference(version = "1.0.0")
	private IGoodsBrandService goodsBrandService;
	
	@Reference(version = "1.0.0")
	private IGoodsService goodsService;
	
	@ApiOperation(value = "增加物资类型", notes = "增加物资类型")
	@PostMapping(value = { "/type/save" })
	public Object addGoodsType( @Valid @RequestBody GoodsTypeInoReq goodsTypeInoReq, BindingResult errors) {
		
		if (errors.hasErrors()) {
			String msg = errors.getAllErrors().get(0).getDefaultMessage();
			logger.error(msg);
			return getResponse(HttpCode.BAD_REQUEST, false, msg);
		}
		
		GoodsTypeInfo goodsTypeInfo=new GoodsTypeInfo();
		goodsTypeInfo.setName(goodsTypeInoReq.getName());
		goodsTypeInfo.setParentId(goodsTypeInoReq.getParentid());
		goodsTypeInfo.setRemark(goodsTypeInoReq.getRemark());
        boolean status=goodsTypeService.save(goodsTypeInfo);
        return getResponse(status);
	}
	
	@ApiOperation(value = "更新物资类型", notes = "根据物资类型id更新物资类型信息")
	@PutMapping(value = { "/type/update" })
	public Object updateGoodsType( @Valid @RequestBody GoodsTypeInoReq goodsTypeInoReq, String goodstypeid,BindingResult errors) {
		
		if (errors.hasErrors()) {
			String msg = errors.getAllErrors().get(0).getDefaultMessage();
			logger.error(msg);
			return getResponse(HttpCode.BAD_REQUEST, false, msg);
		}
		
		if(StringUtils.isEmpty(goodstypeid)) {
			logger.error("goodstypeid is null.");
			return getResponse(HttpCode.BAD_REQUEST, false);
		}
		
		GoodsTypeInfo goodsTypeInfo=new GoodsTypeInfo();
		goodsTypeInfo.setName(goodsTypeInoReq.getName());
		goodsTypeInfo.setParentId(goodsTypeInoReq.getParentid());
		goodsTypeInfo.setRemark(goodsTypeInoReq.getRemark());
		goodsTypeInfo.setId(goodstypeid);
		
        boolean status=goodsTypeService.update(goodsTypeInfo);
        return getResponse(status);
	}
	
	@ApiOperation(value = "删除物资类型-单条", notes = "根据物资类型id删除物资类型信息")
	@DeleteMapping(value = { "/type/delete/{goodstypeid}" })
	public Object deleteGoodsTypebyId(@PathVariable("goodstypeid")  String goodstypeid) {
		
		if(StringUtils.isEmpty(goodstypeid)) {
			logger.error("goodstypeid is null.");
			return getResponse(HttpCode.BAD_REQUEST, false);
		}
		
		boolean status=goodsTypeService.delete(goodstypeid);
        return getResponse(status);
	}
	
	@ApiOperation(value = "删除物资类型-多条", notes = "根据物资类型ids删除物资类型信息")
	@DeleteMapping(value = { "/type/delete/ids" })
	public Object deleteGoodsTypebyIds(@Valid @RequestBody List<String> goodstypeids) {
		
		if(goodstypeids==null||goodstypeids.isEmpty()) {
			logger.error("goodstypeids is null or empty.");
			return getResponse(HttpCode.BAD_REQUEST, false);
		}
		boolean status=goodsTypeService.delete(goodstypeids);
        return getResponse(status);
	}
	
	
	@ApiOperation(value = "获取物资类型", notes = "根据类型id获取物资类型信息")
	@GetMapping(value = { "/type/id/{goodstypeid}" })
	public Object getGoodsType(@PathVariable("goodstypeid") String goodstypeid) {
		
		if(StringUtils.isEmpty(goodstypeid)) {
			logger.error("goodstypeid is null.");
			return getResponse(HttpCode.BAD_REQUEST, false);
		}
		
		GoodsTypeInfo GoodsTypeInfo=goodsTypeService.getType(goodstypeid);
        return getResponse(GoodsTypeInfo);
	}
	
	
	@ApiOperation(value = "获取下级物资类型", notes = "根据类型id获取下级物资类型信息，id为空则获取所有父id为空的物资类型")
	@GetMapping(value = { "/type/tree/{goodstypeid}" })
	public Object getSubGoosType(@PathVariable("goodstypeid") String goodstypeid) {
		
		if(StringUtils.isEmpty(goodstypeid)) {
			logger.error("goodstypeid is null.");
			return getResponse(HttpCode.BAD_REQUEST, false);
		}
		
		List<Tree> GoodsTypeInfos=goodsTypeService.getSubType(goodstypeid);
        return getResponse(GoodsTypeInfos);
	}
	
	
	
	
	@ApiOperation(value = "增加物资品牌类型", notes = "增加物资品牌类型")
	@PostMapping(value = { "/brand/save" })
	public Object addGoodsBrand( @Valid @RequestBody GoodsTypeInoReq goodsTypeInoReq, BindingResult errors) {
		
		if (errors.hasErrors()) {
			String msg = errors.getAllErrors().get(0).getDefaultMessage();
			logger.error(msg);
			return getResponse(HttpCode.BAD_REQUEST, false, msg);
		}
		
		GoodsBrandInfo goodsTypeInfo=new GoodsBrandInfo();
		goodsTypeInfo.setName(goodsTypeInoReq.getName());
		goodsTypeInfo.setParentId(goodsTypeInoReq.getParentid());
		goodsTypeInfo.setRemark(goodsTypeInoReq.getRemark());
        boolean status=goodsBrandService.save(goodsTypeInfo);
        return getResponse(status);
	}
	
	@ApiOperation(value = "更新物资品牌类型", notes = "根据物资品牌类型id更新物资品牌类型信息")
	@PutMapping(value = { "/brand/update" })
	public Object updateGoodsBrand( @Valid @RequestBody GoodsTypeInoReq goodsTypeInoReq, String goodsbrandid,BindingResult errors) {
		
		if (errors.hasErrors()) {
			String msg = errors.getAllErrors().get(0).getDefaultMessage();
			logger.error(msg);
			return getResponse(HttpCode.BAD_REQUEST, false, msg);
		}
		
		if(StringUtils.isEmpty(goodsbrandid)) {
			logger.error("goodstypeid is null.");
			return getResponse(HttpCode.BAD_REQUEST, false);
		}
		
		GoodsBrandInfo goodsTypeInfo=new GoodsBrandInfo();
		goodsTypeInfo.setName(goodsTypeInoReq.getName());
		goodsTypeInfo.setParentId(goodsTypeInoReq.getParentid());
		goodsTypeInfo.setRemark(goodsTypeInoReq.getRemark());
		goodsTypeInfo.setId(goodsbrandid);
		
        boolean status=goodsBrandService.update(goodsTypeInfo);
        return getResponse(status);
	}
	
	@ApiOperation(value = "删除物资品牌类型-单条", notes = "根据物资品牌类型id删除物资品牌类型信息")
	@DeleteMapping(value = { "/brand/delete/{goodsbrandid}" })
	public Object deleteGoodsBrandbyId(@PathVariable("goodsbrandid") String goodsbrandid) {
		
		if(StringUtils.isEmpty(goodsbrandid)) {
			logger.error("goodstypeid is null.");
			return getResponse(HttpCode.BAD_REQUEST, false);
		}
		
		boolean status=goodsBrandService.delete(goodsbrandid);
        return getResponse(status);
	}
	
	@ApiOperation(value = "删除物资品牌类型-多条", notes = "根据物资类型ids删除物资品牌类型信息")
	@DeleteMapping(value = { "/brand/delete/ids" })
	public Object deleteGoodsBrandbyIds(@Valid @RequestBody List<String> goodsbrandids) {
		
		if(goodsbrandids==null||goodsbrandids.isEmpty()) {
			logger.error("goodsbrandids is null or empty.");
			return getResponse(HttpCode.BAD_REQUEST, false);
		}
		boolean status=goodsBrandService.delete(goodsbrandids);
        return getResponse(status);
	}
	
	
	@ApiOperation(value = "获取物资品牌类型", notes = "根据品牌类型id获取物资品牌类型信息")
	@GetMapping(value = { "/brand/id/{goodsbrandid}" })
	public Object getGoodsBrand(@PathVariable("goodsbrandid") String goodsbrandid) {
		
		if(StringUtils.isEmpty(goodsbrandid)) {
			logger.error("goodstypeid is null.");
			return getResponse(HttpCode.BAD_REQUEST, false);
		}
		
		GoodsBrandInfo GoodsTypeInfo=goodsBrandService.get(goodsbrandid);
        return getResponse(GoodsTypeInfo);
	}
	
	
	@ApiOperation(value = "获取下级物资品牌类型", notes = "根据品牌类型id获取下级物资品牌类型信息，id为空则获取所有父id为空的物资品牌类型")
	@GetMapping(value = { "/brand/tree/{goodsbrandid}" })
	public Object getSubGoosBrand(@PathVariable("goodsbrandid") String goodsbrandid) {
		
		if(StringUtils.isEmpty(goodsbrandid)) {
			logger.error("goodstypeid is null.");
			return getResponse(HttpCode.BAD_REQUEST, false);
		}
		
		List<Tree> goodsBrandInfos=goodsBrandService.getSubBrand(goodsbrandid);
        return getResponse(goodsBrandInfos);
	}
	

}
