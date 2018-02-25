package com.ddsh.inquiry.service.api.model;

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
public class InquiryOrderInfo implements Serializable {
	private String id;
	private String code;
	private String name;

	private double inquiryPrice;

	private Date initiateTime;
	private String initiateUserId;
	private String initiateRemark;

	private Date communicateTime;
	private String communicateUserId;
	private String communicateRemark;

	private Date inquiryTime;
	private String inquiryUserId;

	private Date evaluateTime;
	private String evaluateUserId;
	private int evaluateResult;
	private String evaluateRemark;

	private Date auditTime;
	private String auditUserId;
	private int auditResult;
	private String auditRemark;

	private Date decisionTime;
	private int decisionUserId;
	private int decisionResult;

	private int step;
	private int lifeStatus;
	private int status;

	private Date updateTime;
	private List<InquiryOrderGoodsInfo> goods;

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

	public double getInquiryPrice() {
		return inquiryPrice;
	}

	public void setInquiryPrice(double inquiryPrice) {
		this.inquiryPrice = inquiryPrice;
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

	public Date getCommunicateTime() {
		return communicateTime;
	}

	public void setCommunicateTime(Date communicateTime) {
		this.communicateTime = communicateTime;
	}

	public String getCommunicateUserId() {
		return communicateUserId;
	}

	public void setCommunicateUserId(String communicateUserId) {
		this.communicateUserId = communicateUserId;
	}

	public String getCommunicateRemark() {
		return communicateRemark;
	}

	public void setCommunicateRemark(String communicateRemark) {
		this.communicateRemark = communicateRemark;
	}

	public Date getInquiryTime() {
		return inquiryTime;
	}

	public void setInquiryTime(Date inquiryTime) {
		this.inquiryTime = inquiryTime;
	}

	public String getInquiryUserId() {
		return inquiryUserId;
	}

	public void setInquiryUserId(String inquiryUserId) {
		this.inquiryUserId = inquiryUserId;
	}

	public Date getEvaluateTime() {
		return evaluateTime;
	}

	public void setEvaluateTime(Date evaluateTime) {
		this.evaluateTime = evaluateTime;
	}

	public String getEvaluateUserId() {
		return evaluateUserId;
	}

	public void setEvaluateUserId(String evaluateUserId) {
		this.evaluateUserId = evaluateUserId;
	}

	public int getEvaluateResult() {
		return evaluateResult;
	}

	public void setEvaluateResult(int evaluateResult) {
		this.evaluateResult = evaluateResult;
	}

	public String getEvaluateRemark() {
		return evaluateRemark;
	}

	public void setEvaluateRemark(String evaluateRemark) {
		this.evaluateRemark = evaluateRemark;
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

	public int getAuditResult() {
		return auditResult;
	}

	public void setAuditResult(int auditResult) {
		this.auditResult = auditResult;
	}

	public String getAuditRemark() {
		return auditRemark;
	}

	public void setAuditRemark(String auditRemark) {
		this.auditRemark = auditRemark;
	}

	public Date getDecisionTime() {
		return decisionTime;
	}

	public void setDecisionTime(Date decisionTime) {
		this.decisionTime = decisionTime;
	}

	public int getDecisionUserId() {
		return decisionUserId;
	}

	public void setDecisionUserId(int decisionUserId) {
		this.decisionUserId = decisionUserId;
	}

	public int getDecisionResult() {
		return decisionResult;
	}

	public void setDecisionResult(int decisionResult) {
		this.decisionResult = decisionResult;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	public int getLifeStatus() {
		return lifeStatus;
	}

	public void setLifeStatus(int lifeStatus) {
		this.lifeStatus = lifeStatus;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public List<InquiryOrderGoodsInfo> getGoods() {
		return goods;
	}

	public void setGoods(List<InquiryOrderGoodsInfo> goods) {
		this.goods = goods;
	}
}
