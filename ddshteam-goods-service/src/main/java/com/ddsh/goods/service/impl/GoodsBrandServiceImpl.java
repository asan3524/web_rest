package com.ddsh.goods.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.ddsh.goods.service.api.IGoodsBrandService;
import com.ddsh.goods.service.api.model.GoodsBrandInfo;
import com.ddsh.goods.service.api.model.GoodsBrandInfoCriteria;
import com.ddsh.goods.service.api.model.GoodsBrandInfoCriteria.Criteria;
import com.ddsh.goods.service.dao.GoodsBrandInfoMapper;
import com.ddshteam.web.system.service.api.data.Tree;

@Service(version = "1.0.0")
@Transactional(rollbackFor = Exception.class)
public class GoodsBrandServiceImpl implements IGoodsBrandService {

	@Autowired
	private GoodsBrandInfoMapper goodsBrandInfoDao;
	
	@Override
	public List<Tree> getTree() {
		return null;
	}

	@Override
	public GoodsBrandInfo get(String id) {
		return goodsBrandInfoDao.selectByPrimaryKey(id);
	}

	@Override
	public boolean save(GoodsBrandInfo Info) {
		int result=goodsBrandInfoDao.insert(Info);
		return result>0;
	}

	@Override
	public boolean update(GoodsBrandInfo Info) {
		int result=goodsBrandInfoDao.updateByPrimaryKey(Info);
		return result>0;
	}

	@Override
	public boolean delete(String id) {
		int result=goodsBrandInfoDao.deleteByPrimaryKey(id);
		return result>0;
	}

	@Override
	public boolean delete(String... id) {
		GoodsBrandInfoCriteria goodsBrandInfoCriteria=new GoodsBrandInfoCriteria();
		Criteria criteria= goodsBrandInfoCriteria.createCriteria();
		criteria.andIdIn(Arrays.asList(id));
		int result=goodsBrandInfoDao.deleteByExample(goodsBrandInfoCriteria);
		return result>0;
	}

}
