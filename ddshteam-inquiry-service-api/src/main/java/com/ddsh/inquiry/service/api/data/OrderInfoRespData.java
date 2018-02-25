package com.ddsh.inquiry.service.api.data;

import java.util.Date;
import java.util.List;

/**
 * 询价单详情对象
 * @ClassName: OrderInfoRespData
 * @author lishibang
 * @date 2018年2月15日 下午2:25:05
 * @version v1.0.0
 * 
 */
public class OrderInfoRespData {
	private String id;
	private String code;
	private String name;

	private double inquiryPrice;

	private Date initiateTime;
	private String initiateUserName;
	private String initiateRemark;

	private Date communicateTime;
	private String communicateUserName;
	private String communicateRemark;

	private Date inquiryTime;
	private String inquiryUserName;

	private Date evaluateTime;
	private String evaluateUserName;
	private int evaluateResult;
	private String evaluateRemark;

	private Date auditTime;
	private String auditUserName;
	private int auditResult;
	private String auditRemark;

	private Date decisionTime;
	private int decisionUserName;
	private int decisionResult;

	private int step;
	private int lifeStatus;
	private int status;

	private Date updateTime;
	private List<GoodsInfoRespData> goods;

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

	public Date getCommunicateTime() {
		return communicateTime;
	}

	public void setCommunicateTime(Date communicateTime) {
		this.communicateTime = communicateTime;
	}

	public String getCommunicateUserName() {
		return communicateUserName;
	}

	public void setCommunicateUserName(String communicateUserName) {
		this.communicateUserName = communicateUserName;
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

	public String getInquiryUserName() {
		return inquiryUserName;
	}

	public void setInquiryUserName(String inquiryUserName) {
		this.inquiryUserName = inquiryUserName;
	}

	public Date getEvaluateTime() {
		return evaluateTime;
	}

	public void setEvaluateTime(Date evaluateTime) {
		this.evaluateTime = evaluateTime;
	}

	public String getEvaluateUserName() {
		return evaluateUserName;
	}

	public void setEvaluateUserName(String evaluateUserName) {
		this.evaluateUserName = evaluateUserName;
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

	public String getAuditUserName() {
		return auditUserName;
	}

	public void setAuditUserName(String auditUserName) {
		this.auditUserName = auditUserName;
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

	public int getDecisionUserName() {
		return decisionUserName;
	}

	public void setDecisionUserName(int decisionUserName) {
		this.decisionUserName = decisionUserName;
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

	public List<GoodsInfoRespData> getGoods() {
		return goods;
	}

	public void setGoods(List<GoodsInfoRespData> goods) {
		this.goods = goods;
	}
}
