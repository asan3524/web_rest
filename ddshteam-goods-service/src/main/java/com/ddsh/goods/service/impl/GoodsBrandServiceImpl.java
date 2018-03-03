package com.ddsh.goods.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.ddsh.goods.service.api.IGoodsBrandService;
import com.ddsh.goods.service.api.model.GoodsBrandInfo;
import com.ddsh.goods.service.api.model.GoodsBrandInfoCriteria;
import com.ddsh.goods.service.api.model.GoodsBrandInfoCriteria.Criteria;
import com.ddsh.goods.service.dao.GoodsBrandInfoMapper;
import com.ddsh.goods.service.dao.GoodsBrandInfoTreeMapper;
import com.ddsh.goods.service.util.GoodsCoder;
import com.ddshteam.web.system.service.api.data.Tree;

@Service(version = "1.0.0")
@Transactional(rollbackFor = Exception.class)
public class GoodsBrandServiceImpl implements IGoodsBrandService {

	@Autowired
	private GoodsBrandInfoMapper goodsBrandInfoDao;
	@Autowired
	private GoodsBrandInfoTreeMapper goodsBrandInfoTreeDao;
	
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
		Info.setId(UUID.randomUUID().toString());
		Info.setCreateTime(new Date());
		Info.setCode(GoodsCoder.getGoodsCode(Info.getName(),Info.getParentId()));
		int result=goodsBrandInfoDao.insert(Info);
		return result>0;
	}

	@Override
	public boolean update(GoodsBrandInfo Info) {
		GoodsBrandInfo oldInfo=goodsBrandInfoDao.selectByPrimaryKey(Info.getId());
		Info.setCreateTime(oldInfo.getCreateTime());
		Info.setCode(oldInfo.getCode());
		int result=goodsBrandInfoDao.updateByPrimaryKey(Info);
		return result>0;
	}

	@Override
	public boolean delete(String id) {
		int result=goodsBrandInfoDao.deleteByPrimaryKey(id);
		return result>0;
	}

	@Override
	public boolean delete(List id) {
		GoodsBrandInfoCriteria goodsBrandInfoCriteria=new GoodsBrandInfoCriteria();
		Criteria criteria= goodsBrandInfoCriteria.createCriteria();
		criteria.andIdIn(id);
		int result=goodsBrandInfoDao.deleteByExample(goodsBrandInfoCriteria);
		return result>0;
	}

	@Override
	public List<Tree> getSubBrand(String id) {
//		GoodsBrandInfoCriteria goodsBrandInfoCriteria=new GoodsBrandInfoCriteria();
//		Criteria criteria= goodsBrandInfoCriteria.createCriteria();
//		
//		if(id==null)
//		{
//			criteria.andParentIdIsNull();
//		}
//		else
//		{
//			criteria.andParentIdEqualTo(id);
//		}
//		List<GoodsBrandInfo> goodsBrandInfos=goodsBrandInfoDao.selectByExample(goodsBrandInfoCriteria);
//		goodsBrandInfoDao.
		List<Tree> trees = goodsBrandInfoTreeDao.selectByPrimaryKey(id);
		return trees;
	}

}
