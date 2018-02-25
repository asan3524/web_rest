package com.ddsh.purchase.service.api.model;

import java.io.Serializable;
import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@SuppressWarnings("serial")
@Builder
@Data
@ToString
public class PurchaseInquiryInfo implements Serializable {
	private String id;
	/**
	 * 询价单ID
	 * @Fields inquiryId
	 */
	private String inquiryId;
	/**
	 * 询价单名称
	 * @Fields name
	 */
	private String name;
	/**
	 * 询价单编码
	 * @Fields code
	 */
	private String code;

	private List<PurchaseOrderGoodsInfo> goods;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public List<PurchaseOrderGoodsInfo> getGoods() {
		return goods;
	}

	public void setGoods(List<PurchaseOrderGoodsInfo> goods) {
		this.goods = goods;
	}
}
