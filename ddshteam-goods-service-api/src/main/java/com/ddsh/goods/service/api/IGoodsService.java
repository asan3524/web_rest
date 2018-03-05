package com.ddsh.goods.service.api;

import java.util.List;

import com.ddsh.goods.service.api.data.GoodsInfoReqData;
import com.ddsh.goods.service.api.data.GoodsInfoRespData;
import com.ddsh.goods.service.api.data.GoodsInfoSearchReqData;
import com.ddsh.goods.service.api.model.GoodsInfo;
import com.github.pagehelper.PageInfo;

/**
 * 物质服务
 * @ClassName: IGoodsService
 * @author arpgate
 * @date 2018年2月28日 上午10:55:06
 * @version v1.0.0
 * 
 */
public interface IGoodsService {
 
	/**
	 *  分页条件检索物资列表
	 * @Title: list
	 * @param pageNum
	 * @param pageSize
	 * @param searchReqData 条件检索对象
	 * @return PageInfo<GoodsInfo>
	 * @see 
	 * @throws
	 * @author arpgate
	 */
	public PageInfo<GoodsInfoRespData> list(int pageNum, int pageSize, GoodsInfoSearchReqData searchReqData);

	/**
	 * 根据物质id获取物质详情
	 * @Title: get
	 * @param id
	 * @return GoodsInfo
	 * @see 
	 * @throws
	 * @author arpgate
	 */
	public GoodsInfoRespData get(String id);

	/**
	 * 物质详情保存
	 * @Title: save
	 * @param goodsInfo
	 * @return boolean
	 * @see 
	 * @throws
	 * @author arpgate
	 */
	public boolean save(GoodsInfo goodsInfo);

	/**
	 * 物质详情更新
	 * @Title: update
	 * @param goodsInfo
	 * @return boolean
	 * @see 
	 * @throws
	 * @author arpgate
	 */
	public boolean update(GoodsInfo goodsInfo);

	/**
	 * 根据ID删除物资
	 * @Title: deleteGoods
	 * @param goodsId
	 * @return boolean
	 * @author lishibang
	 */
	public boolean delete(String goodsId);

	/**
	 * 根据IDs删除物资
	 * @Title: delete
	 * @param goodsId
	 * @return boolean
	 * @see 
	 * @throws
	 * @author arpgate
	 */
	public boolean delete(List<String> goodsId);
}
