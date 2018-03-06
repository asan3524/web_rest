package com.ddsh.inquiry.service.api;

import java.util.List;

import com.ddsh.inquiry.service.api.data.CommunicateGoodsReqData;
import com.ddsh.inquiry.service.api.data.InitiateOrderReqData;
import com.ddsh.inquiry.service.api.data.InquiryGoodsReqData;
import com.ddsh.inquiry.service.api.data.OrderInfoRespData;
import com.ddsh.inquiry.service.api.data.OrderListReqData;
import com.ddsh.inquiry.service.api.data.OrderListRespData;
import com.github.pagehelper.PageInfo;

/**
 * 询价申请服务
 * @ClassName: InquiryService
 * @author lishibang
 * @date 2018年2月13日 上午9:22:38
 * @version v1.0.0
 * 
 */
public interface IInquiryService {
	/**
	 * 根据ID获取询价申请详情
	 * @Title: get
	 * @param id
	 * @return OrderInfoRespData
	 * @author lishibang
	 */
	public OrderInfoRespData get(String id);

	/**
	 * 条件检索询价单列表
	 * @Title: list
	 * @param reqData
	 * @return PageInfo<OrderListRespData>
	 * @author lishibang
	 */
	public PageInfo<OrderListRespData> list(int pageNum, int pageSize, OrderListReqData reqData);

	/**
	 * 询价申请发起，从发起时间起，一个月内均为有效，超过一个月询价无效
	 * @Title: initiate
	 * @param userId 申请者ID
	 * @param inquiryOrder 询价单对象
	 * @return boolean
	 * @author lishibang
	 */
	public boolean initiate(String userId, InitiateOrderReqData inquiryOrder);

	/**
	 * 详情沟通
	 * 前提：step=1待沟通 and status=1有效 and life_status=1有效
	 * 执行事务：step=2、communicateuser_id=userId、communicate_time=now
	 * 			CommunicateGoodsReqData.id 为空时（其他属性必填）增加物资列表inq_inquiry_order_good.goodsId/type/norm/quantity/name/code/brand/colour/remark 9项必填
	 * 			CommunicateGoodsReqData.id 不为空时更新inq_inquiry_order_goods.goodsId/type/norm/quantity 4项必填
	 * @Title: communicate
	 * @param userId 详情沟通者ID
	 * @param orderId 询价单ID
	 * @param goods 询价物资清单
	 * @return boolean
	 * @author lishibang
	 */
	public boolean communicate(String userId, String orderId, List<CommunicateGoodsReqData> goods);

	/**
	 * 询价
	 * 前提：step=2待询价 and status=1有效 and life_status=1有效
	 * 执行事务：step=3、inquiryuser_id=userId、inquiry_time=now、更新inq_inquiry_order_goods.remark及unit_price
	 * @Title: inquiry
	 * @param userId 询价者ID
	 * @param orderId 询价单ID
	 * @param goods 询价物资清单
	 * @return boolean
	 * @author lishibang
	 */
	public boolean inquiry(String userId, String orderId, List<InquiryGoodsReqData> goods);

	/**
	 * 现场评估
	 * 前提：step=3待评估 and status=1有效 and life_status=1有效
	 * 执行事务：step=4、evaluateuser_id=userId、evaluate_result=evaluate、evaluate_time=now、evaluate_remark=remark
	 * @Title: evaluate
	 * @param userId 评估者ID
	 * @param orderId 询价单ID
	 * @param evaluate 评估结论
	 * @param remark 评估说明
	 * @return boolean
	 * @author lishibang
	 */
	public boolean evaluate(String userId, String orderId, int evaluate, String remark);

	/**
	 * 审核
	 * 前提：step=4待审核 and status=1有效 and life_status=1有效
	 * 执行事务：step=5、audituser_id=userId、audit_result=audit、audit_time=now、audit_remark=remark
	 * @Title: audit
	 * @param userId 审核者ID
	 * @param orderId 询价申请单ID
	 * @param audit 审核结论 1通过 0不通过
	 * @param remark 审核说明
	 * @return boolean
	 * @author lishibang
	 */
	public boolean audit(String userId, String orderId, int audit, String remark);

	/**
	 * 决策
	 * 前提：step=5待决策 and status=1有效 and life_status=1有效
	 * 执行事务：step=6、decisionuser_id=userId、decision_result=decision、decision_time=now
	 * @Title: decision
	 * @param userId 决策者ID
	 * @param orderId 询价申请单ID
	 * @param decision 决策值 1 采购 0不采购
	 * @return boolean
	 * @author lishibang
	 */
	public boolean decision(String userId, String orderId, int decision);

	/**
	 * 删除询价单（status置为0）
	 * @Title: delete
	 * @param userId
	 * @param orderId
	 * @return boolean
	 * @author lishibang
	 */
	public boolean delete(String userId, String orderId);
}
