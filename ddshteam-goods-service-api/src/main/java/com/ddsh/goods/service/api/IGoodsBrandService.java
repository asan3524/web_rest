package com.ddsh.goods.service.api;

import java.util.List;

import com.ddsh.goods.service.api.model.GoodsBrandInfo;
import com.ddshteam.web.system.service.api.data.Tree;

/**
 * 物资类型服务
 * @ClassName: IGoodsBrandService
 * @author arpgate
 * @date 2018年2月28日 上午10:55:00
 * @version v1.0.0
 * 
 */
public interface IGoodsBrandService {

	/**
	 * 物资资型树
	 * @Title: getTree
	 * @return List<Tree>
	 * @see 
	 * @throws
	 * @author arpgate
	 */
	public List<Tree> getTree();

	/**
	 * 根据物资类型 id获取物质类型详情
	 * @Title: get
	 * @param id
	 * @return GoodsInfo
	 * @see 
	 * @throws
	 * @author arpgate
	 */
	public GoodsBrandInfo get(String id);

 
	/**
	 * 根据物质分类id获取物资下级品牌类型详情
	 * @Title: getSubBrand
	 * @param id
	 * @return List<GoodsBrandInfo>
	 * @see 
	 * @throws
	 * @author arpgate
	 */
	public List<Tree> getSubBrand(String id);
	/**
	 * 保存物资类型id
	 * @Title: save
	 * @param info
	 * @return boolean
	 * @see 
	 * @throws
	 * @author arpgate
	 */
	public boolean save(GoodsBrandInfo info);

	/**
	 * 更新物资类型
	 * @Title: update
	 * @param info
	 * @return boolean
	 * @see 
	 * @throws
	 * @author arpgate
	 */
	public boolean update(GoodsBrandInfo info);

	/**
	 * 根据ID删除品牌
	 * @Title: delete
	 * @param id
	 * @return boolean
	 * @author lishibang
	 */
	public boolean delete(String id);

	/**
	 * 根据IDs删除品牌
	 * @Title: delete
	 * @param id
	 * @return boolean
	 * @see 
	 * @throws
	 * @author arpgate
	 */
	public boolean delete(List id);
	
 
	/**
	 * 判断编码是否存在
	 * @Title: nameExist
	 * @param name
	 * @param parentid
	 * @return boolean
	 * @see 
	 * @throws
	 * @author arpgate
	 */
	public boolean nameExist(String name,String parentid);
	
}
