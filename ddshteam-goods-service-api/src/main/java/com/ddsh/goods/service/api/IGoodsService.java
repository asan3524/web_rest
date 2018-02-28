package com.ddsh.goods.service.api;

import com.ddsh.goods.service.api.data.GoodsInfoReqData;
import com.ddsh.goods.service.api.model.GoodsInfo;
import com.github.pagehelper.PageInfo;

/**
 * 物质管理
 * @ClassName: IGoodsService
 * @author arpgate
 * @date 2018年2月27日 下午7:20:44
 * @version v1.0.0
 * 
 */
public interface IGoodsService {

	/**
	 * 条件检索物资列表
	 * @Title: list
	 * @param pageNum
	 * @param pageSize
	 * @param reqData 条件检索对象
	 * @return PageInfo<GoodsInfo>
	 * @author lishibang
	 */
	public PageInfo<GoodsInfo> list(int pageNum, int pageSize, GoodsInfoReqData reqData);

	public GoodsInfo get(String id);

	public boolean save(GoodsInfo goodsInfo);

	public boolean update(GoodsInfo goodsInfo);

	/**
	 * 根据ID删除物资
	 * @Title: deleteGoods
	 * @param goodsId
	 * @return boolean
	 * @author lishibang
	 */
	public boolean delete(String goodsId);

	public boolean delete(String... goodsId);
}
