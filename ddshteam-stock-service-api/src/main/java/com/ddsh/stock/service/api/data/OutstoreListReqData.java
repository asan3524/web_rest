package com.ddsh.stock.service.api.data;

import java.util.Date;

public class OutstoreListReqData {

	private String code;
	private Date outstoreTime_s;
	private Date outstoreTime_e;
	private String storeId;
	private String goodsCode;
	private String goodsName;
	private String brandId;
	private String typeId;
	private String useuserName;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getOutstoreTime_s() {
		return outstoreTime_s;
	}

	public void setOutstoreTime_s(Date outstoreTime_s) {
		this.outstoreTime_s = outstoreTime_s;
	}

	public Date getOutstoreTime_e() {
		return outstoreTime_e;
	}

	public void setOutstoreTime_e(Date outstoreTime_e) {
		this.outstoreTime_e = outstoreTime_e;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
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

	public String getUseuserName() {
		return useuserName;
	}

	public void setUseuserName(String useuserName) {
		this.useuserName = useuserName;
	}
}
