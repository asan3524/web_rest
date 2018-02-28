package com.ddsh.goods.service.api;

import java.util.List;

import com.ddsh.goods.service.api.model.GoodsInfo;
import com.ddshteam.web.system.service.api.data.Tree;

/**
 * 物质管理
 * @ClassName: IGoodsBrandService
 * @author arpgate
 * @date 2018年2月27日 下午7:15:48
 * @version v1.0.0
 * 
 */
public interface IGoodsBrandService {

	/**
	 * 获取树列表
	 * @Title: getTree
	 * @return List<Tree>
	 * @see 
	 * @throws
	 * @author arpgate
	 */
	public List<Tree> getTree();

	/**
	 *  获取物质详情
	 * @Title: get
	 * @param id
	 * @return GoodsInfo
	 * @see 
	 * @throws
	 * @author arpgate
	 */
	public GoodsInfo get(String id);

	/**
	 *  保存物质详情
	 * @Title: save
	 * @param Info
	 * @return boolean
	 * @see 
	 * @throws
	 * @author arpgate
	 */
	public boolean save(GoodsInfo Info);

	/**
	 * 更新物质详情
	 * @Title: update
	 * @param Info
	 * @return boolean
	 * @see 
	 * @throws
	 * @author arpgate
	 */
	public boolean update(GoodsInfo Info);

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
