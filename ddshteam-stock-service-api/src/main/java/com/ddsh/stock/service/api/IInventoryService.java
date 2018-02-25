package com.ddsh.stock.service.api;

import com.ddsh.stock.service.api.data.InventoryGoodsListReqData;
import com.ddsh.stock.service.api.data.InventoryGoodsListRespData;
import com.ddsh.stock.service.api.data.InventoryInfoRespData;
import com.ddsh.stock.service.api.data.InventoryListReqData;
import com.ddsh.stock.service.api.data.InventoryListRespData;
import com.github.pagehelper.PageInfo;

public interface IInventoryService {

	/**
	 * 条件检索当前用户可见仓库的盘点单
	 * @Title: list
	 * @param pageNum
	 * @param pageSize
	 * @param userId
	 * @param reqData
	 * @return PageInfo<InventoryListRespData>
	 * @author lishibang
	 */
	public PageInfo<InventoryListRespData> list(int pageNum, int pageSize, String userId, InventoryListReqData reqData);

	/**
	 * 根据ID获取盘点单详情
	 * @Title: get
	 * @param userId
	 * @param inventoryId
	 * @return InventoryInfoRespData
	 * @author lishibang
	 */
	public InventoryInfoRespData get(String userId, String inventoryId);

	/**
	 * 分页条件检索指定盘点单的物资清单，盘点单ID必填
	 * @Title: list
	 * @param pageNum
	 * @param pageSize
	 * @param reqData
	 * @return PageInfo<InventoryGoodsListRespData>
	 * @author lishibang
	 */
	public PageInfo<InventoryGoodsListRespData> list(int pageNum, int pageSize, InventoryGoodsListReqData reqData);

	/**
	 * 指定仓库开始盘点，事务操作：
	 * 1.生成并插入盘点单记录
	 * 2.生成并插入盘点物资清单（考虑使用存储过程，需要查询到上次盘点数）
	 * 3.更新仓库状态
	 * @Title: startInventory
	 * @param userId
	 * @param storeId
	 * @return InventoryInfoRespData
	 * @author lishibang
	 */
	public InventoryInfoRespData startInventory(String userId, String storeId);

	/**
	 * 盘点物资数量，事务操作：
	 * 1.更新盘点物资记录表盘点数
	 * 2.如果盘点数与库存不一致，更新库存
	 * 只有当盘点单处于盘点中状态时，物资盘点数才能修改，否则不能修改
	 * @Title: inventoryGoods
	 * @param userId
	 * @param inventoryGoodsId
	 * @return boolean
	 * @author lishibang
	 */
	public boolean inventoryGoods(String userId, String inventoryGoodsId);

	/**
	 * 只有当盘点单下所有物资都进行盘点（sto_inventory_order_goods.make_status=1）之后，才能制定盘点单结束
	 * @Title: endInventory
	 * @param userId
	 * @param inventoryId
	 * @return boolean
	 * @author lishibang
	 */
	public boolean endInventory(String userId, String inventoryId);
}
