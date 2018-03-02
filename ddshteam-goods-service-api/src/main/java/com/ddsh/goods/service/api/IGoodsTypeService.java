package com.ddsh.goods.service.api;

import java.util.List;

import com.ddsh.goods.service.api.model.GoodsTypeInfo;
import com.ddshteam.web.system.service.api.data.Tree;

/**
 * 物质分类服务
 * @ClassName: IGoodsTypeService
 * @author arpgate
 * @date 2018年2月28日 上午10:55:11
 * @version v1.0.0
 * 
 */
public interface IGoodsTypeService {

	/**
	 *  获取物质分类树
	 * @Title: getTypeTree
	 * @return List<Tree>
	 * @see 
	 * @throws
	 * @author arpgate
	 */
	public List<Tree> getTypeTree();

	/**
	 * 根据物质分类id获取物质分类详情
	 * @Title: getType
	 * @param id
	 * @return GoodsTypeInfo
	 * @see 
	 * @throws
	 * @author arpgate
	 */
	public GoodsTypeInfo getType(String id);
	
	/**
	 *  根据物质分类id获取物资下级分类详情
	 * @Title: getSubType
	 * @param id
	 * @return List<GoodsTypeInfo>
	 * @see 
	 * @throws
	 * @author arpgate
	 */
	public List<GoodsTypeInfo> getSubType(String id);

	/**
	 * 保存物资分类详情
	 * @Title: save
	 * @param typeInfo
	 * @return boolean
	 * @see 
	 * @throws
	 * @author arpgate
	 */
	public boolean save(GoodsTypeInfo typeInfo);

	/**
	 * 更新物资分类详情
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
	 * 根据IDs删除类型
	 * @Title: delete
	 * @param id
	 * @return boolean
	 * @see 
	 * @throws
	 * @author arpgate
	 */
	public boolean delete(List id);
}
