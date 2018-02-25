package com.ddsh.stock.service.api.data;

import java.util.Date;
import java.util.List;

public class InstoreListRespData {

	private String id;
	private String code;
	private String storeId;
	private String storeName;
	private String purchaseId;
	private String purchaseCode;
	private String inuserName;
	private Date instoreTime;
	private String remark;
	private int status;
	private List<InstoreGoodsListRespData> goods;

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

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(String purchaseId) {
		this.purchaseId = purchaseId;
	}

	public String getPurchaseCode() {
		return purchaseCode;
	}

	public void setPurchaseCode(String purchaseCode) {
		this.purchaseCode = purchaseCode;
	}

	public String getInuserName() {
		return inuserName;
	}

	public void setInuserName(String inuserName) {
		this.inuserName = inuserName;
	}

	public Date getInstoreTime() {
		return instoreTime;
	}

	public void setInstoreTime(Date instoreTime) {
		this.instoreTime = instoreTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<InstoreGoodsListRespData> getGoods() {
		return goods;
	}

	public void setGoods(List<InstoreGoodsListRespData> goods) {
		this.goods = goods;
	}
}
