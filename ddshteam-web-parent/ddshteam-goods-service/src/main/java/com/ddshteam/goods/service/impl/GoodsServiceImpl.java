package com.ddshteam.goods.service.impl;

import com.ddsh.goods.service.api.IGoodsService;
import com.ddsh.goods.service.api.data.GoodsInfoReqData;
import com.ddsh.goods.service.api.model.GoodsInfo;
import com.github.pagehelper.PageInfo;

public class GoodsServiceImpl implements IGoodsService {

	@Override
	public PageInfo<GoodsInfo> list(int pageNum, int pageSize, GoodsInfoReqData reqData) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GoodsInfo get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean save(GoodsInfo goodsInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(GoodsInfo goodsInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(String goodsId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(String... goodsId) {
		// TODO Auto-generated method stub
		return false;
	}

}
