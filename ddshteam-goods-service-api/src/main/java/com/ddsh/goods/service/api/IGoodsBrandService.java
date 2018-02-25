package com.ddsh.goods.service.api;

import java.util.List;

import com.ddsh.goods.service.api.model.GoodsInfo;
import com.ddshteam.web.system.service.api.data.Tree;

public interface IGoodsBrandService {

	public List<Tree> getTree();

	public GoodsInfo get(String id);

	public boolean save(GoodsInfo Info);

	public boolean update(GoodsInfo Info);

	/**
	 * 根据ID删除品牌
	 * @Title: delete
	 * @param id
	 * @return boolean
	 * @author lishibang
	 */
	public boolean delete(String id);

	public boolean delete(String... id);
}
