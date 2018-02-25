package com.ddsh.purchase.service.api.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@SuppressWarnings("serial")
@Builder
@Data
@ToString
public class PurchaseOrderInfo implements Serializable {
	private String id;
	private String code;
	private String name;
	private int step;
	private int status;
	private Double inquiryPrice;
	private Double purchasePrice;

	private Date initiateTime;
	private String initiateUserId;
	private String initiateRemark;

	private Date auditTime;
	private String auditUserId;

	private Date decisionTime;
	private String decisionUserId;

	private Date purchaseTime;
	private String purchaseUserId;

	private Date updateTime;
	private List<PurchaseInquiryInfo> inquirys;

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

	public String getInitiateUserId() {
		return initiateUserId;
	}

	public void setInitiateUserId(String initiateUserId) {
		this.initiateUserId = initiateUserId;
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

	public String getAuditUserId() {
		return auditUserId;
	}

	public void setAuditUserId(String auditUserId) {
		this.auditUserId = auditUserId;
	}

	public Date getDecisionTime() {
		return decisionTime;
	}

	public void setDecisionTime(Date decisionTime) {
		this.decisionTime = decisionTime;
	}

	public String getDecisionUserId() {
		return decisionUserId;
	}

	public void setDecisionUserId(String decisionUserId) {
		this.decisionUserId = decisionUserId;
	}

	public Date getPurchaseTime() {
		return purchaseTime;
	}

	public void setPurchaseTime(Date purchaseTime) {
		this.purchaseTime = purchaseTime;
	}

	public String getPurchaseUserId() {
		return purchaseUserId;
	}

	public void setPurchaseUserId(String purchaseUserId) {
		this.purchaseUserId = purchaseUserId;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public List<PurchaseInquiryInfo> getInquirys() {
		return inquirys;
	}

	public void setInquirys(List<PurchaseInquiryInfo> inquirys) {
		this.inquirys = inquirys;
	}
}
