package com.ddsh.stock.service.api.data;

import java.util.Date;

public class InstoreListReqData {

	private String code;
	private Date instoreTime_s;
	private Date instoreTime_e;
	private String storeId;
	private String inquiryCode;
	private String goodsCode;
	private String goodsName;
	private String brandId;
	private String typeId;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getInstoreTime_s() {
		return instoreTime_s;
	}

	public void setInstoreTime_s(Date instoreTime_s) {
		this.instoreTime_s = instoreTime_s;
	}

	public Date getInstoreTime_e() {
		return instoreTime_e;
	}

	public void setInstoreTime_e(Date instoreTime_e) {
		this.instoreTime_e = instoreTime_e;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getInquiryCode() {
		return inquiryCode;
	}

	public void setInquiryCode(String inquiryCode) {
		this.inquiryCode = inquiryCode;
	}

	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getBrandId() {
		return brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
}
