package com.ddsh.purchase.service.api.data;

import java.util.List;

public class InitiateOrderReqData {
	/**
	 * 采购单标题
	 * @Fields name
	 */
	private String name;
	/**
	 * 采购单备注说明
	 * @Fields inquiryRemark
	 */
	private String purchaseRemark;
	/**
	 * 采购询价清单
	 * @Fields inquirys
	 */
	private List<InitiateInquiryReqData> inquirys;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPurchaseRemark() {
		return purchaseRemark;
	}

	public void setPurchaseRemark(String purchaseRemark) {
		this.purchaseRemark = purchaseRemark;
	}

	public List<InitiateInquiryReqData> getInquirys() {
		return inquirys;
	}

	public void setInquirys(List<InitiateInquiryReqData> inquirys) {
		this.inquirys = inquirys;
	}
}
