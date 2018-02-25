package com.ddsh.purchase.service.api.data;

import java.util.List;

public class InitiateInquiryReqData {
	/**
	 * 询价单ID
	 * @Fields inquiryId
	 */
	private String inquiryId;
	/**
	 * 物资名称
	 * @Fields goodsId
	 */
	private String name;
	/**
	 * 物资编码
	 * @Fields goodsId
	 */
	private String code;

	/**
	 * 采购询价单的物资清单
	 * @Fields goods
	 */
	private List<InitiateGoodsReqData> goods;

	public String getInquiryId() {
		return inquiryId;
	}

	public void setInquiryId(String inquiryId) {
		this.inquiryId = inquiryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<InitiateGoodsReqData> getGoods() {
		return goods;
	}

	public void setGoods(List<InitiateGoodsReqData> goods) {
		this.goods = goods;
	}
}
