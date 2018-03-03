package com.ddsh.goods.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.ddsh.goods.service.api.IGoodsTypeService;
import com.ddsh.goods.service.api.constant.GoodsContants;
import com.ddsh.goods.service.api.model.GoodsTypeInfo;
import com.ddsh.goods.service.api.model.GoodsTypeInfoCriteria;
import com.ddsh.goods.service.api.model.GoodsTypeInfoCriteria.Criteria;
import com.ddsh.goods.service.dao.GoodsTypeInfoMapper;
import com.ddsh.goods.service.dao.GoodsTypeInfoTreeMapper;
import com.ddsh.goods.service.util.GoodsCoder;
import com.ddshteam.web.system.service.api.data.Tree;

@Service(version = "1.0.0")
@Transactional(rollbackFor = Exception.class)
public class GoodsTypeServiceImpl implements IGoodsTypeService {

	@Autowired
	private GoodsTypeInfoMapper goodsTypeInfoDao;
	
	@Autowired
	private GoodsTypeInfoTreeMapper goodsTypeInfoTreeDao;
	
	@Override
	public List<Tree> getTypeTree() {
		return null;
	}

	@Override
	public GoodsTypeInfo getType(String id) {
		return goodsTypeInfoDao.selectByPrimaryKey(id);
	}

	@Override
	public boolean save(GoodsTypeInfo typeInfo) {
		typeInfo.setId(UUID.randomUUID().toString());
		typeInfo.setCreateTime(new Date());
		typeInfo.setStatus(GoodsContants.GoodsTypeStatus.EFFECT);
		typeInfo.setCode(GoodsCoder.getGoodsCode(typeInfo.getName(),typeInfo.getParentId()));
		int result =goodsTypeInfoDao.insert(typeInfo);
 		return result>0;
	}

	@Override
	public boolean update(GoodsTypeInfo typeInfo) {
		GoodsTypeInfo oldtype=goodsTypeInfoDao.selectByPrimaryKey(typeInfo.getId());
		typeInfo.setCode(oldtype.getCode());
		typeInfo.setStatus(oldtype.getStatus());
		typeInfo.setCreateTime(oldtype.getCreateTime());
		int result =goodsTypeInfoDao.updateByPrimaryKey(typeInfo);
		return result>0;
	}

	@Override
	public boolean delete(String id) {
		int result =goodsTypeInfoDao.deleteByPrimaryKey(id);
		return result>0;
	}

	@Override
	public boolean delete(List id) {
		GoodsTypeInfoCriteria goodsTypeInfoCriteria=new GoodsTypeInfoCriteria();
		Criteria criteria=goodsTypeInfoCriteria.createCriteria();
		criteria.andIdIn(id);
		int result =goodsTypeInfoDao.deleteByExample(goodsTypeInfoCriteria);
		return result>0;
	}

	@Override
	public List<GoodsTypeInfo> getSubType(String id) {
		GoodsTypeInfoCriteria goodsTypeInfoCriteria=new GoodsTypeInfoCriteria();
		Criteria criteria=goodsTypeInfoCriteria.createCriteria();
		if(id==null)
		{
			criteria.andParentIdIsNull();
		}
		else
		{
			criteria.andParentIdEqualTo(id);
		}
		List<GoodsTypeInfo> goodsTypeInfos=goodsTypeInfoDao.selectByExample(goodsTypeInfoCriteria);
		return goodsTypeInfos;
	}

}
