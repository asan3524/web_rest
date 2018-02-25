package com.ddsh.stock.service.api;

import com.ddsh.stock.service.api.data.StockListReqData;
import com.ddsh.stock.service.api.data.StockListRespData;
import com.github.pagehelper.PageInfo;

public interface IStockService {

	/**
	 * 获取当前用户可见的仓库库存情况
	 * @Title: list
	 * @param pageNum
	 * @param pageSize
	 * @param userId
	 * @param reqData
	 * @return PageInfo<StockListRespData>
	 * @author lishibang
	 */
	public PageInfo<StockListRespData> list(int pageNum, int pageSize, String userId, StockListReqData reqData);

	public boolean setThreshold(String id, int upper, int lower);
}
