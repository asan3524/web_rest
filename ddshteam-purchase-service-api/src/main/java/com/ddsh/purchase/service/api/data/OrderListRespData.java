package com.ddsh.purchase.service.api.data;

import java.util.Date;
import java.util.List;

/**
 * 采购单列表对象
 * @ClassName: OrderListRespData
 * @author lishibang
 * @date 2018年2月18日 上午9:34:18
 * @version v1.0.0
 * 
 */
public class OrderListRespData {
	private String id;
	private String code;
	private String name;
	private int step;
	private int status;
	private Double inquiryPrice;
	private Double purchasePrice;
	private Date initiateTime;
	private Date updateTime;
	private List<InquiryListRespData> inquirys;

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

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public List<InquiryListRespData> getInquirys() {
		return inquirys;
	}

	public void setInquirys(List<InquiryListRespData> inquirys) {
		this.inquirys = inquirys;
	}
}
