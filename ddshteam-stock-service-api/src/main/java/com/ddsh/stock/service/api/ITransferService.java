package com.ddsh.stock.service.api;

import com.ddsh.stock.service.api.data.TransferListReqData;
import com.ddsh.stock.service.api.data.TransferListRespData;
import com.ddsh.stock.service.api.data.TransferReqData;
import com.github.pagehelper.PageInfo;

public interface ITransferService {

	/**
	 * 检索当前用户可见的出库仓、入库仓之间的调拨单
	 * @Title: list
	 * @param pageNum
	 * @param pageSize
	 * @param userId
	 * @param reqData
	 * @return PageInfo<TransferListRespData>
	 * @author lishibang
	 */
	public PageInfo<TransferListRespData> list(int pageNum, int pageSize, String userId, TransferListReqData reqData);

	/**
	 * 调拨出库，操作事务：
	 * 1.生成并插入调拨单
	 * 2.更新出库仓库存
	 * @Title: transferout
	 * @param userId
	 * @param reqData
	 * @return boolean
	 * @author lishibang
	 */
	public boolean transferout(String userId, TransferReqData reqData);

	/**
	 * 调拨入库，操作事务：
	 * 1.更新调拨单
	 * 2.更新入库仓库存
	 * @Title: transferin
	 * @param userId
	 * @param transferId
	 * @return boolean
	 * @author lishibang
	 */
	public boolean transferin(String userId, String transferId);
}
