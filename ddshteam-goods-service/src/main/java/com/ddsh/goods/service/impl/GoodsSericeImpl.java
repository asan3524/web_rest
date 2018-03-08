package com.ddsh.goods.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.ddsh.goods.service.api.IGoodsService;
import com.ddsh.goods.service.api.constant.GoodsContants;
import com.ddsh.goods.service.api.data.GoodsInfoRespData;
import com.ddsh.goods.service.api.data.GoodsInfoSearchReqData;
import com.ddsh.goods.service.api.model.GoodsInfo;
import com.ddsh.goods.service.api.model.GoodsInfoCriteria;
import com.ddsh.goods.service.api.model.GoodsInfoCriteria.Criteria;
import com.ddsh.goods.service.dao.GoodsInfoCustomizeMapper;
import com.ddsh.goods.service.dao.GoodsInfoMapper;
import com.ddsh.goods.service.util.GoodsCoder;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service(version = "1.0.0")
@Transactional(rollbackFor = Exception.class)
public class GoodsSericeImpl  implements IGoodsService{

	@Autowired
	private GoodsInfoMapper goodsInfoDao;
	@Autowired
	private GoodsInfoCustomizeMapper goodsInfoCustomizeDao;
	
	@Override
	public PageInfo<GoodsInfoRespData> list(int pageNum, int pageSize, GoodsInfoSearchReqData searchReqData) {
		PageHelper.startPage(pageNum, pageSize);
		GoodsInfoCriteria goodsInfoCriteria=new GoodsInfoCriteria();
		goodsInfoCriteria.setOrderByClause(" order_num desc,create_time desc");
		Criteria criteria=goodsInfoCriteria.createCriteria();
		criteria.andCustomizeIdIsNotNull();
		if(searchReqData.getCode()!=null&&!searchReqData.getCode().equals(""))
		{
			criteria.andCustomizeCodeLike("%"+searchReqData.getCode()+"%");
		}
		
		if(searchReqData.getName()!=null&&!searchReqData.getName().equals(""))
		{
			criteria.andCustomizeNameLike("%"+searchReqData.getName()+"%");

		}
		
		if(searchReqData.getTypeIds()!=null&&!searchReqData.getTypeIds().isEmpty())
		{
			criteria.andCustomizeTypeIdIn(searchReqData.getTypeIds());
		}
		
		if(searchReqData.getBrandIds()!=null&&!searchReqData.getBrandIds().isEmpty())
		{
			criteria.andCustomizeBrandIdIn(searchReqData.getBrandIds());
		}
		PageInfo<GoodsInfoRespData> pageinfo=new PageInfo<GoodsInfoRespData>(goodsInfoCustomizeDao.selectByExample(goodsInfoCriteria));
		return pageinfo ;
	}

	@Override
	public GoodsInfoRespData get(String id) {
		return goodsInfoCustomizeDao.selectByPrimaryKey(id);
	}

	@Override
	public boolean save(GoodsInfo goodsInfo) {
		String code=GoodsCoder.getGoodsCode(goodsInfo.getTypeId(),goodsInfo.getBrandId(),goodsInfo.getColor(),goodsInfo.getName());
		goodsInfo.setCode(code);
		goodsInfo.setCreateTime(new Date());
		goodsInfo.setStatus(GoodsContants.GoodsStatus.EFFECT);
		int result = goodsInfoDao.insert(goodsInfo);
		return result>0;
	}

	@Override
	public boolean update(GoodsInfo goodsInfo) {
		int result = goodsInfoDao.updateByPrimaryKeySelective(goodsInfo);
		return result>0;
	}

	@Override
	public boolean delete(String goodsId) {
		int result = goodsInfoDao.deleteByPrimaryKey(goodsId);
		return result>0;
	}

	@Override
	public boolean delete(List<String> goodsId) {
		GoodsInfoCriteria goodsInfoCriteria=new GoodsInfoCriteria();
		Criteria criteria=goodsInfoCriteria.createCriteria();
		criteria.andIdIn(goodsId);
		int result = goodsInfoDao.deleteByExample(goodsInfoCriteria);
		return result>0;
	}

}
