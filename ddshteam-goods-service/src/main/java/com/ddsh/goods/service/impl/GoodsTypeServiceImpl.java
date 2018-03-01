package com.ddsh.goods.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.ddsh.goods.service.api.IGoodsTypeService;
import com.ddsh.goods.service.api.model.GoodsTypeInfo;
import com.ddsh.goods.service.api.model.GoodsTypeInfoCriteria;
import com.ddsh.goods.service.api.model.GoodsTypeInfoCriteria.Criteria;
import com.ddsh.goods.service.dao.GoodsTypeInfoMapper;
import com.ddshteam.web.system.service.api.data.Tree;

@Service(version = "1.0.0")
@Transactional(rollbackFor = Exception.class)
public class GoodsTypeServiceImpl implements IGoodsTypeService {

	@Autowired
	private GoodsTypeInfoMapper GoodsTypeInfoDao;
	
	@Override
	public List<Tree> getTypeTree() {
		return null;
	}

	@Override
	public GoodsTypeInfo getType(String id) {
		return GoodsTypeInfoDao.selectByPrimaryKey(id);
	}

	@Override
	public boolean save(GoodsTypeInfo typeInfo) {
		int result =GoodsTypeInfoDao.insert(typeInfo);
 		return result>0;
	}

	@Override
	public boolean update(GoodsTypeInfo typeInfo) {
		int result =GoodsTypeInfoDao.updateByPrimaryKey(typeInfo);
		return result>0;
	}

	@Override
	public boolean delete(String id) {
		int result =GoodsTypeInfoDao.deleteByPrimaryKey(id);
		return result>0;
	}

	@Override
	public boolean delete(String... id) {
		GoodsTypeInfoCriteria goodsTypeInfoCriteria=new GoodsTypeInfoCriteria();
		Criteria criteria=goodsTypeInfoCriteria.createCriteria();
		criteria.andIdIn(Arrays.asList(id));
		int result =GoodsTypeInfoDao.deleteByExample(goodsTypeInfoCriteria);
		return result>0;
	}

}
