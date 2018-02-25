package com.ddsh.purchase.service.api.data;

import java.util.Date;
import java.util.List;

/**
 * 采购单详情对象
 * @ClassName: OrderInfoRespData
 * @author lishibang
 * @date 2018年2月18日 上午9:34:18
 * @version v1.0.0
 * 
 */
public class OrderInfoRespData {
	private String id;
	private String code;
	private String name;
	private int step;
	private int status;
	private Double inquiryPrice;
	private Double purchasePrice;

	private Date initiateTime;
	private String initiateUserName;
	private String initiateRemark;

	private Date auditTime;
	private String auditUserName;

	private Date decisionTime;
	private String decisionUserName;

	private Date purchaseTime;
	private String purchaseUserName;

	private Date updateTime;
	private List<InquiryInfoRespData> inquirys;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Double getInquiryPrice() {
		return inquiryPrice;
	}

	public void setInquiryPrice(Double inquiryPrice) {
		this.inquiryPrice = inquiryPrice;
	}

	public Double getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(Double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public Date getInitiateTime() {
		return initiateTime;
	}

	public void setInitiateTime(Date initiateTime) {
		this.initiateTime = initiateTime;
	}

	public String getInitiateUserName() {
		return initiateUserName;
	}

	public void setInitiateUserName(String initiateUserName) {
		this.initiateUserName = initiateUserName;
	}

	public String getInitiateRemark() {
		return initiateRemark;
	}

	public void setInitiateRemark(String initiateRemark) {
		this.initiateRemark = initiateRemark;
	}

	public Date getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	public String getAuditUserName() {
		return auditUserName;
	}

	public void setAuditUserName(String auditUserName) {
		this.auditUserName = auditUserName;
	}

	public Date getDecisionTime() {
		return decisionTime;
	}

	public void setDecisionTime(Date decisionTime) {
		this.decisionTime = decisionTime;
	}

	public String getDecisionUserName() {
		return decisionUserName;
	}

	public void setDecisionUserName(String decisionUserName) {
		this.decisionUserName = decisionUserName;
	}

	public Date getPurchaseTime() {
		return purchaseTime;
	}

	public void setPurchaseTime(Date purchaseTime) {
		this.purchaseTime = purchaseTime;
	}

	public String getPurchaseUserName() {
		return purchaseUserName;
	}

	public void setPurchaseUserName(String purchaseUserName) {
		this.purchaseUserName = purchaseUserName;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public List<InquiryInfoRespData> getInquirys() {
		return inquirys;
	}

	public void setInquirys(List<InquiryInfoRespData> inquirys) {
		this.inquirys = inquirys;
	}
}
