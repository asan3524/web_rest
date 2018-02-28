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
	 * 保存物资类型id
	 * @Title: save
	 * @param Info
	 * @return boolean
	 * @see 
	 * @throws
	 * @author arpgate
	 */
	public boolean save(GoodsBrandInfo Info);

	/**
	 * 更新物资类型
	 * @Title: update
	 * @param Info
	 * @return boolean
	 * @see 
	 * @throws
	 * @author arpgate
	 */
	public boolean update(GoodsBrandInfo Info);

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
	public boolean delete(String... id);
}
