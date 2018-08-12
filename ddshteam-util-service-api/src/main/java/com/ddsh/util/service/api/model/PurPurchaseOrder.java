package com.ddsh.util.service.api.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class PurPurchaseOrder implements Serializable {
    /**
     * 主键ID
     * 表字段 : pur_purchase_order.id
     */
    private String id;

    /**
     * 编码
     * 表字段 : pur_purchase_order.code
     */
    private String code;

    /**
     * 名称
     * 表字段 : pur_purchase_order.name
     */
    private String name;

    /**
     * 采购发起人
     * 表字段 : pur_purchase_order.initiateuser_id
     */
    private String initiateuserId;

    /**
     * 采购发起人姓名
     * 表字段 : pur_purchase_order.initiateuser_name
     */
    private String initiateuserName;

    /**
     * 预算审核人
     * 表字段 : pur_purchase_order.audituser_id
     */
    private String audituserId;

    /**
     * 预算审核人姓名
     * 表字段 : pur_purchase_order.audituser_name
     */
    private String audituserName;

    /**
     * 采购审批人
     * 表字段 : pur_purchase_order.decisionuser_id
     */
    private String decisionuserId;

    /**
     * 采购审批人姓名
     * 表字段 : pur_purchase_order.decisionuser_name
     */
    private String decisionuserName;

    /**
     * 采购人
     * 表字段 : pur_purchase_order.purchaseuser_id
     */
    private String purchaseuserId;

    /**
     * 采购人姓名
     * 表字段 : pur_purchase_order.purchaseuser_name
     */
    private String purchaseuserName;

    /**
     * 采购发起时间
     * 表字段 : pur_purchase_order.initiate_time
     */
    private Date initiateTime;

    /**
     * 预算审核时间
     * 表字段 : pur_purchase_order.audit_time
     */
    private Date auditTime;

    /**
     * 采购审批时间
     * 表字段 : pur_purchase_order.decision_time
     */
    private Date decisionTime;

    /**
     * 领导可执行总价
     * 表字段 : pur_purchase_order.leader_decision
     */
    private BigDecimal leaderDecision;

    /**
     * 采购时间
     * 表字段 : pur_purchase_order.purchase_time
     */
    private Date purchaseTime;

    /**
     * 询价总价
     * 表字段 : pur_purchase_order.inquiry_price
     */
    private BigDecimal inquiryPrice;

    /**
     * 采购总价，采购总价必须小于询价总价
     * 表字段 : pur_purchase_order.purchase_price
     */
    private BigDecimal purchasePrice;

    /**
     * 采购说明，采购发起时填写
     * 表字段 : pur_purchase_order.initiate_remark
     */
    private String initiateRemark;

    /**
     * 采购单所处节点：1待审核2待审批3待采购4待入库5入库中6已完成7无预算8未批准,6/7/8均为终止状态不可再改变
     * 表字段 : pur_purchase_order.step
     */
    private Integer step;

    /**
     * 预算审核结论：1有预算0无预算
     * 表字段 : pur_purchase_order.audit_result
     */
    private Integer auditResult;

    /**
     * 采购审批结论：1批准0未批准
     * 表字段 : pur_purchase_order.decision_result
     */
    private Integer decisionResult;

    /**
     * 审批意见
     * 表字段 : pur_purchase_order.decision_remark
     */
    private String decisionRemark;

    /**
     * 状态：1有效0删除, 被删除的采购单置为已删除
     * 表字段 : pur_purchase_order.status
     */
    private Integer status;

    /**
     * 更新时间
     * 表字段 : pur_purchase_order.update_time
     */
    private Date updateTime;

    /**
     * 采购执行备注
     * 表字段 : pur_purchase_order.purchase_remark
     */
    private String purchaseRemark;

    /**
     * 其它费用
     * 表字段 : pur_purchase_order.other_price
     */
    private BigDecimal otherPrice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table pur_purchase_order
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    private List<PurPurchaseToInquiry> purPurchaseToInquirys;

    private List<PurPurchaseToStore> purPurchaseToStores;

    /**
     * 获取 主键ID 字段:pur_purchase_order.id
     *
     * @return pur_purchase_order.id, 主键ID
     */
    public String getId() {
        return id;
    }

    /**
     * 设置 主键ID 字段:pur_purchase_order.id
     *
     * @param id the value for pur_purchase_order.id, 主键ID
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 获取 编码 字段:pur_purchase_order.code
     *
     * @return pur_purchase_order.code, 编码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置 编码 字段:pur_purchase_order.code
     *
     * @param code the value for pur_purchase_order.code, 编码
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 获取 名称 字段:pur_purchase_order.name
     *
     * @return pur_purchase_order.name, 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置 名称 字段:pur_purchase_order.name
     *
     * @param name the value for pur_purchase_order.name, 名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取 采购发起人 字段:pur_purchase_order.initiateuser_id
     *
     * @return pur_purchase_order.initiateuser_id, 采购发起人
     */
    public String getInitiateuserId() {
        return initiateuserId;
    }

    /**
     * 设置 采购发起人 字段:pur_purchase_order.initiateuser_id
     *
     * @param initiateuserId the value for pur_purchase_order.initiateuser_id, 采购发起人
     */
    public void setInitiateuserId(String initiateuserId) {
        this.initiateuserId = initiateuserId == null ? null : initiateuserId.trim();
    }

    /**
     * 获取 采购发起人姓名 字段:pur_purchase_order.initiateuser_name
     *
     * @return pur_purchase_order.initiateuser_name, 采购发起人姓名
     */
    public String getInitiateuserName() {
        return initiateuserName;
    }

    /**
     * 设置 采购发起人姓名 字段:pur_purchase_order.initiateuser_name
     *
     * @param initiateuserName the value for pur_purchase_order.initiateuser_name, 采购发起人姓名
     */
    public void setInitiateuserName(String initiateuserName) {
        this.initiateuserName = initiateuserName == null ? null : initiateuserName.trim();
    }

    /**
     * 获取 预算审核人 字段:pur_purchase_order.audituser_id
     *
     * @return pur_purchase_order.audituser_id, 预算审核人
     */
    public String getAudituserId() {
        return audituserId;
    }

    /**
     * 设置 预算审核人 字段:pur_purchase_order.audituser_id
     *
     * @param audituserId the value for pur_purchase_order.audituser_id, 预算审核人
     */
    public void setAudituserId(String audituserId) {
        this.audituserId = audituserId == null ? null : audituserId.trim();
    }

    /**
     * 获取 预算审核人姓名 字段:pur_purchase_order.audituser_name
     *
     * @return pur_purchase_order.audituser_name, 预算审核人姓名
     */
    public String getAudituserName() {
        return audituserName;
    }

    /**
     * 设置 预算审核人姓名 字段:pur_purchase_order.audituser_name
     *
     * @param audituserName the value for pur_purchase_order.audituser_name, 预算审核人姓名
     */
    public void setAudituserName(String audituserName) {
        this.audituserName = audituserName == null ? null : audituserName.trim();
    }

    /**
     * 获取 采购审批人 字段:pur_purchase_order.decisionuser_id
     *
     * @return pur_purchase_order.decisionuser_id, 采购审批人
     */
    public String getDecisionuserId() {
        return decisionuserId;
    }

    /**
     * 设置 采购审批人 字段:pur_purchase_order.decisionuser_id
     *
     * @param decisionuserId the value for pur_purchase_order.decisionuser_id, 采购审批人
     */
    public void setDecisionuserId(String decisionuserId) {
        this.decisionuserId = decisionuserId == null ? null : decisionuserId.trim();
    }

    /**
     * 获取 采购审批人姓名 字段:pur_purchase_order.decisionuser_name
     *
     * @return pur_purchase_order.decisionuser_name, 采购审批人姓名
     */
    public String getDecisionuserName() {
        return decisionuserName;
    }

    /**
     * 设置 采购审批人姓名 字段:pur_purchase_order.decisionuser_name
     *
     * @param decisionuserName the value for pur_purchase_order.decisionuser_name, 采购审批人姓名
     */
    public void setDecisionuserName(String decisionuserName) {
        this.decisionuserName = decisionuserName == null ? null : decisionuserName.trim();
    }

    /**
     * 获取 采购人 字段:pur_purchase_order.purchaseuser_id
     *
     * @return pur_purchase_order.purchaseuser_id, 采购人
     */
    public String getPurchaseuserId() {
        return purchaseuserId;
    }

    /**
     * 设置 采购人 字段:pur_purchase_order.purchaseuser_id
     *
     * @param purchaseuserId the value for pur_purchase_order.purchaseuser_id, 采购人
     */
    public void setPurchaseuserId(String purchaseuserId) {
        this.purchaseuserId = purchaseuserId == null ? null : purchaseuserId.trim();
    }

    /**
     * 获取 采购人姓名 字段:pur_purchase_order.purchaseuser_name
     *
     * @return pur_purchase_order.purchaseuser_name, 采购人姓名
     */
    public String getPurchaseuserName() {
        return purchaseuserName;
    }

    /**
     * 设置 采购人姓名 字段:pur_purchase_order.purchaseuser_name
     *
     * @param purchaseuserName the value for pur_purchase_order.purchaseuser_name, 采购人姓名
     */
    public void setPurchaseuserName(String purchaseuserName) {
        this.purchaseuserName = purchaseuserName == null ? null : purchaseuserName.trim();
    }

    /**
     * 获取 采购发起时间 字段:pur_purchase_order.initiate_time
     *
     * @return pur_purchase_order.initiate_time, 采购发起时间
     */
    public Date getInitiateTime() {
        return initiateTime;
    }

    /**
     * 设置 采购发起时间 字段:pur_purchase_order.initiate_time
     *
     * @param initiateTime the value for pur_purchase_order.initiate_time, 采购发起时间
     */
    public void setInitiateTime(Date initiateTime) {
        this.initiateTime = initiateTime;
    }

    /**
     * 获取 预算审核时间 字段:pur_purchase_order.audit_time
     *
     * @return pur_purchase_order.audit_time, 预算审核时间
     */
    public Date getAuditTime() {
        return auditTime;
    }

    /**
     * 设置 预算审核时间 字段:pur_purchase_order.audit_time
     *
     * @param auditTime the value for pur_purchase_order.audit_time, 预算审核时间
     */
    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    /**
     * 获取 采购审批时间 字段:pur_purchase_order.decision_time
     *
     * @return pur_purchase_order.decision_time, 采购审批时间
     */
    public Date getDecisionTime() {
        return decisionTime;
    }

    /**
     * 设置 采购审批时间 字段:pur_purchase_order.decision_time
     *
     * @param decisionTime the value for pur_purchase_order.decision_time, 采购审批时间
     */
    public void setDecisionTime(Date decisionTime) {
        this.decisionTime = decisionTime;
    }

    /**
     * 获取 领导可执行总价 字段:pur_purchase_order.leader_decision
     *
     * @return pur_purchase_order.leader_decision, 领导可执行总价
     */
    public BigDecimal getLeaderDecision() {
        return leaderDecision;
    }

    /**
     * 设置 领导可执行总价 字段:pur_purchase_order.leader_decision
     *
     * @param leaderDecision the value for pur_purchase_order.leader_decision, 领导可执行总价
     */
    public void setLeaderDecision(BigDecimal leaderDecision) {
        this.leaderDecision = leaderDecision;
    }

    /**
     * 获取 采购时间 字段:pur_purchase_order.purchase_time
     *
     * @return pur_purchase_order.purchase_time, 采购时间
     */
    public Date getPurchaseTime() {
        return purchaseTime;
    }

    /**
     * 设置 采购时间 字段:pur_purchase_order.purchase_time
     *
     * @param purchaseTime the value for pur_purchase_order.purchase_time, 采购时间
     */
    public void setPurchaseTime(Date purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    /**
     * 获取 询价总价 字段:pur_purchase_order.inquiry_price
     *
     * @return pur_purchase_order.inquiry_price, 询价总价
     */
    public BigDecimal getInquiryPrice() {
        return inquiryPrice;
    }

    /**
     * 设置 询价总价 字段:pur_purchase_order.inquiry_price
     *
     * @param inquiryPrice the value for pur_purchase_order.inquiry_price, 询价总价
     */
    public void setInquiryPrice(BigDecimal inquiryPrice) {
        this.inquiryPrice = inquiryPrice;
    }

    /**
     * 获取 采购总价，采购总价必须小于询价总价 字段:pur_purchase_order.purchase_price
     *
     * @return pur_purchase_order.purchase_price, 采购总价，采购总价必须小于询价总价
     */
    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    /**
     * 设置 采购总价，采购总价必须小于询价总价 字段:pur_purchase_order.purchase_price
     *
     * @param purchasePrice the value for pur_purchase_order.purchase_price, 采购总价，采购总价必须小于询价总价
     */
    public void setPurchasePrice(BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    /**
     * 获取 采购说明，采购发起时填写 字段:pur_purchase_order.initiate_remark
     *
     * @return pur_purchase_order.initiate_remark, 采购说明，采购发起时填写
     */
    public String getInitiateRemark() {
        return initiateRemark;
    }

    /**
     * 设置 采购说明，采购发起时填写 字段:pur_purchase_order.initiate_remark
     *
     * @param initiateRemark the value for pur_purchase_order.initiate_remark, 采购说明，采购发起时填写
     */
    public void setInitiateRemark(String initiateRemark) {
        this.initiateRemark = initiateRemark == null ? null : initiateRemark.trim();
    }

    /**
     * 获取 采购单所处节点：1待审核2待审批3待采购4待入库5入库中6已完成7无预算8未批准,6/7/8均为终止状态不可再改变 字段:pur_purchase_order.step
     *
     * @return pur_purchase_order.step, 采购单所处节点：1待审核2待审批3待采购4待入库5入库中6已完成7无预算8未批准,6/7/8均为终止状态不可再改变
     */
    public Integer getStep() {
        return step;
    }

    /**
     * 设置 采购单所处节点：1待审核2待审批3待采购4待入库5入库中6已完成7无预算8未批准,6/7/8均为终止状态不可再改变 字段:pur_purchase_order.step
     *
     * @param step the value for pur_purchase_order.step, 采购单所处节点：1待审核2待审批3待采购4待入库5入库中6已完成7无预算8未批准,6/7/8均为终止状态不可再改变
     */
    public void setStep(Integer step) {
        this.step = step;
    }

    /**
     * 获取 预算审核结论：1有预算0无预算 字段:pur_purchase_order.audit_result
     *
     * @return pur_purchase_order.audit_result, 预算审核结论：1有预算0无预算
     */
    public Integer getAuditResult() {
        return auditResult;
    }

    /**
     * 设置 预算审核结论：1有预算0无预算 字段:pur_purchase_order.audit_result
     *
     * @param auditResult the value for pur_purchase_order.audit_result, 预算审核结论：1有预算0无预算
     */
    public void setAuditResult(Integer auditResult) {
        this.auditResult = auditResult;
    }

    /**
     * 获取 采购审批结论：1批准0未批准 字段:pur_purchase_order.decision_result
     *
     * @return pur_purchase_order.decision_result, 采购审批结论：1批准0未批准
     */
    public Integer getDecisionResult() {
        return decisionResult;
    }

    /**
     * 设置 采购审批结论：1批准0未批准 字段:pur_purchase_order.decision_result
     *
     * @param decisionResult the value for pur_purchase_order.decision_result, 采购审批结论：1批准0未批准
     */
    public void setDecisionResult(Integer decisionResult) {
        this.decisionResult = decisionResult;
    }

    /**
     * 获取 审批意见 字段:pur_purchase_order.decision_remark
     *
     * @return pur_purchase_order.decision_remark, 审批意见
     */
    public String getDecisionRemark() {
        return decisionRemark;
    }

    /**
     * 设置 审批意见 字段:pur_purchase_order.decision_remark
     *
     * @param decisionRemark the value for pur_purchase_order.decision_remark, 审批意见
     */
    public void setDecisionRemark(String decisionRemark) {
        this.decisionRemark = decisionRemark == null ? null : decisionRemark.trim();
    }

    /**
     * 获取 状态：1有效0删除, 被删除的采购单置为已删除 字段:pur_purchase_order.status
     *
     * @return pur_purchase_order.status, 状态：1有效0删除, 被删除的采购单置为已删除
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置 状态：1有效0删除, 被删除的采购单置为已删除 字段:pur_purchase_order.status
     *
     * @param status the value for pur_purchase_order.status, 状态：1有效0删除, 被删除的采购单置为已删除
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取 更新时间 字段:pur_purchase_order.update_time
     *
     * @return pur_purchase_order.update_time, 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置 更新时间 字段:pur_purchase_order.update_time
     *
     * @param updateTime the value for pur_purchase_order.update_time, 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取 采购执行备注 字段:pur_purchase_order.purchase_remark
     *
     * @return pur_purchase_order.purchase_remark, 采购执行备注
     */
    public String getPurchaseRemark() {
        return purchaseRemark;
    }

    /**
     * 设置 采购执行备注 字段:pur_purchase_order.purchase_remark
     *
     * @param purchaseRemark the value for pur_purchase_order.purchase_remark, 采购执行备注
     */
    public void setPurchaseRemark(String purchaseRemark) {
        this.purchaseRemark = purchaseRemark == null ? null : purchaseRemark.trim();
    }

    /**
     * 获取 其它费用 字段:pur_purchase_order.other_price
     *
     * @return pur_purchase_order.other_price, 其它费用
     */
    public BigDecimal getOtherPrice() {
        return otherPrice;
    }

    /**
     * 设置 其它费用 字段:pur_purchase_order.other_price
     *
     * @param otherPrice the value for pur_purchase_order.other_price, 其它费用
     */
    public void setOtherPrice(BigDecimal otherPrice) {
        this.otherPrice = otherPrice;
    }

    /**
     * ,pur_purchase_order
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", code=").append(code);
        sb.append(", name=").append(name);
        sb.append(", initiateuserId=").append(initiateuserId);
        sb.append(", initiateuserName=").append(initiateuserName);
        sb.append(", audituserId=").append(audituserId);
        sb.append(", audituserName=").append(audituserName);
        sb.append(", decisionuserId=").append(decisionuserId);
        sb.append(", decisionuserName=").append(decisionuserName);
        sb.append(", purchaseuserId=").append(purchaseuserId);
        sb.append(", purchaseuserName=").append(purchaseuserName);
        sb.append(", initiateTime=").append(initiateTime);
        sb.append(", auditTime=").append(auditTime);
        sb.append(", decisionTime=").append(decisionTime);
        sb.append(", leaderDecision=").append(leaderDecision);
        sb.append(", purchaseTime=").append(purchaseTime);
        sb.append(", inquiryPrice=").append(inquiryPrice);
        sb.append(", purchasePrice=").append(purchasePrice);
        sb.append(", initiateRemark=").append(initiateRemark);
        sb.append(", step=").append(step);
        sb.append(", auditResult=").append(auditResult);
        sb.append(", decisionResult=").append(decisionResult);
        sb.append(", decisionRemark=").append(decisionRemark);
        sb.append(", status=").append(status);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", purchaseRemark=").append(purchaseRemark);
        sb.append(", otherPrice=").append(otherPrice);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public List<PurPurchaseToInquiry> getPurPurchaseToInquirys() {
        return purPurchaseToInquirys;
    }

    public void setPurPurchaseToInquirys(List<PurPurchaseToInquiry> purPurchaseToInquirys) {
        this.purPurchaseToInquirys=purPurchaseToInquirys;
    }

    public List<PurPurchaseToStore> getPurPurchaseToStores() {
        return purPurchaseToStores;
    }

    public void setPurPurchaseToStores(List<PurPurchaseToStore> purPurchaseToStores) {
        this.purPurchaseToStores=purPurchaseToStores;
    }
}