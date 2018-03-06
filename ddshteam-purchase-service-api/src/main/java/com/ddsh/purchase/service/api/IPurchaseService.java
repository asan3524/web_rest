package com.ddsh.purchase.service.api;

import java.util.List;

import com.ddsh.purchase.service.api.data.InitiateOrderReqData;
import com.ddsh.purchase.service.api.data.OrderInfoRespData;
import com.ddsh.purchase.service.api.data.OrderListReqData;
import com.ddsh.purchase.service.api.data.OrderListRespData;
import com.ddsh.purchase.service.api.data.PurchaseGoodsReqData;
import com.github.pagehelper.PageInfo;

public interface IPurchaseService {
	/**
	 * 根据ID获取询价申请详情
	 * @Title: get
	 * @param id
	 * @return OrderInfoRespData
	 * @author lishibang
	 */
	public OrderInfoRespData get(String id);

	/**
	 * 条件检索采购单列表
	 * @Title: list
	 * @param reqData
	 * @return PageInfo<OrderListRespData>
	 * @author lishibang
	 */
	public PageInfo<OrderListRespData> list(int pageNum, int pageSize, OrderListReqData reqData);

	/**
	 * 采购申请发起
	 * @Title: initiate
	 * @param userId 申请者ID
	 * @param purchaseOrder 采购单对象
	 * @return boolean
	 * @author lishibang
	 */
	public boolean initiate(String userId, InitiateOrderReqData purchaseOrder);

	/**
	 * 预算审核
	 * 前提：step=1待审核 and status=1有效
	 * 执行事务：
	 * 			有预算step=2、audituser_id=userId、audit_result=audit、audit_time=now采购单继续
	 * 			无预算step=7、audituser_id=userId、audit_result=audit、audit_time=now采购单终止
	 * @Title: audit
	 * @param userId 预算审核者ID
	 * @param orderId 采购申请单ID
	 * @param audit 预算审核结论 1有预算 0无预算
	 * @return boolean
	 * @author lishibang
	 */
	public boolean audit(String userId, String orderId, int audit);

	/**
	 * 采购审批
	 * 前提：step=2待审批 and status=1有效
	 * 执行事务：
	 * 			审批通过step=3、decisionuser_id=userId、decision_result=decision、decision_time=now采购单继续
	 * 			审批不通过step=8、decisionuser_id=userId、decision_result=decision、decision_time=now采购单终止
	 * @Title: decision
	 * @param userId 采购审批ID
	 * @param orderId 采购申请单ID
	 * @param decision 决策值 1 采购 0不采购
	 * @return boolean
	 * @author lishibang
	 */
	public boolean decision(String userId, String orderId, int decision);

	/**
	 * 采购执行
	 * @Title: purchase
	 * @param userId 采购者ID
	 * @param orderId 采购单ID
	 * @param reqData 采购时物资采购信息列表
	 * @param storeIds 采购时需要根据物资执行入库库房
	 * @return boolean
	 * @author lishibang
	 */
	public boolean purchase(String userId, String orderId, List<PurchaseGoodsReqData> reqData, String... storeIds);

	/**
	 * 删除采购单（status置为0）
	 * @Title: delete
	 * @param userId
	 * @param orderId
	 * @return boolean
	 * @author lishibang
	 */
	public boolean delete(String userId, String orderId);
}
