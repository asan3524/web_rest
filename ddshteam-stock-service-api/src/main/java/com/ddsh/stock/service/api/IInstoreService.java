package com.ddsh.stock.service.api;

import com.ddsh.stock.service.api.data.InstoreListReqData;
import com.ddsh.stock.service.api.data.InstoreListRespData;
import com.ddsh.stock.service.api.data.InstoreReqData;
import com.github.pagehelper.PageInfo;

public interface IInstoreService {

	/**
	 * 获取当前用户可见仓库的入库清单
	 * @Title: list
	 * @param pageNum
	 * @param pageSize
	 * @param userId
	 * @param reqData
	 * @return PageInfo<InstoreListRespData>
	 * @author lishibang
	 */
	public PageInfo<InstoreListRespData> list(int pageNum, int pageSize, String userId, InstoreListReqData reqData);
	
	/**
	 * 入库，入库的仓库必须是当前用户有权限操作的仓库，事务操作：
	 * 1.生成并插入库单
	 * 2.生成并插入库物资清单
	 * 3.更新采购单状态：
	 * 					如果对应采购单已全部入库 step=6 已完成
	 * 					如果对应采购单部分入库 step=5 入库中
	 * 4.更换采购单对应的物资已入库数量
	 * 5.更新库存
	 * @Title: instore
	 * @param userId
	 * @param reqData
	 * @return boolean
	 * @see 
	 * @throws
	 * @author lishibang
	 */
	public boolean instore(String userId, InstoreReqData reqData);
	
	/**
	 * 撤销自己的入库操作
	 * 前提：
	 * 1.入库操作对应的采购单状态为已完成（ step=6 ）时，必须在24小时之内撤销，否则不能撤销
	 * 2.入库操作对应的采购单状态为入库中（ step=5 ）时，不限时间
	 * @Title: undo
	 * @param userId
	 * @param instoreId
	 * @return boolean
	 * @author lishibang
	 */
	public boolean undo(String userId, String instoreId);
}
