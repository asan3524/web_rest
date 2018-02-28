package com.ddsh.goods.service.api;

import java.util.List;

import com.ddsh.goods.service.api.model.GoodsTypeInfo;
import com.ddshteam.web.system.service.api.data.Tree;

/**
 * 物质类型
 * @ClassName: IGoodsTypeService
 * @author arpgate
 * @date 2018年2月27日 下午7:21:34
 * @version v1.0.0
 * 
 */
public interface IGoodsTypeService {

	/**
	 * 获取物质树
	 * @Title: getTypeTree
	 * @return List<Tree>
	 * @see 
	 * @throws
	 * @author arpgate
	 */
	public List<Tree> getTypeTree();

	/**
	 * 根据id获取物资类型
	 * @Title: getType
	 * @param id
	 * @return GoodsTypeInfo
	 * @see 
	 * @throws
	 * @author arpgate
	 */
	public GoodsTypeInfo getType(String id);

	/**
	 * 保存物资类型信息
	 * @Title: save
	 * @param typeInfo
	 * @return boolean
	 * @see 
	 * @throws
	 * @author arpgate
	 */
	public boolean save(GoodsTypeInfo typeInfo);

	/**
	 * 更新新物资类型信息
	 * @Title: update
	 * @param typeInfo
	 * @return boolean
	 * @see 
	 * @throws
	 * @author arpgate
	 */
	public boolean update(GoodsTypeInfo typeInfo);

	/**
	 * 根据ID删除类型
	 * @Title: deleteType
	 * @param id
	 * @return boolean
	 * @author lishibang
	 */
	public boolean delete(String id);

	/**
	 * 根据ID删除类型
	 * @Title: delete
	 * @param id
	 * @return boolean
	 * @see 
	 * @throws
	 * @author arpgate
	 */
	public boolean delete(String... id);
}
