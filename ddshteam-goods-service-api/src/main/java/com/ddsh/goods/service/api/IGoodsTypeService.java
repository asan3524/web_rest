package com.ddsh.goods.service.api;

import java.util.List;

import com.ddsh.goods.service.api.model.GoodsTypeInfo;
import com.ddshteam.web.system.service.api.data.Tree;

public interface IGoodsTypeService {

	public List<Tree> getTypeTree();

	public GoodsTypeInfo getType(String id);

	public boolean save(GoodsTypeInfo typeInfo);

	public boolean update(GoodsTypeInfo typeInfo);

	/**
	 * 根据ID删除类型
	 * @Title: deleteType
	 * @param id
	 * @return boolean
	 * @author lishibang
	 */
	public boolean delete(String id);

	public boolean delete(String... id);
}
