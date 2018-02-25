package com.ddsh.stock.service.api.data;

import java.util.Date;

public class TransferListReqData {

	private String code;

	private String outstoreId;
	private Date outstoreTime_s;
	private Date outstoreTime_e;

	private String instoreId;
	private Date instoreTime_s;
	private Date instoreTime_e;

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

	public String getOutstoreId() {
		return outstoreId;
	}

	public void setOutstoreId(String outstoreId) {
		this.outstoreId = outstoreId;
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

	public String getInstoreId() {
		return instoreId;
	}

	public void setInstoreId(String instoreId) {
		this.instoreId = instoreId;
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
