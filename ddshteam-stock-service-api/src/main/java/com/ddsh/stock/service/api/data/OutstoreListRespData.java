package com.ddsh.stock.service.api.data;

import java.util.Date;
import java.util.List;

public class OutstoreListRespData {

	private String id;
	private String code;
	private String storeId;
	private String storeName;
	private String outuserName;
	private Date outstoreTime;

	private String usedepName;
	private String useuserName;
	private String remark;
	private int status;
	private List<OutstoreGoodsListRespData> goods;

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

	public String getOutuserName() {
		return outuserName;
	}

	public void setOutuserName(String outuserName) {
		this.outuserName = outuserName;
	}

	public Date getOutstoreTime() {
		return outstoreTime;
	}

	public void setOutstoreTime(Date outstoreTime) {
		this.outstoreTime = outstoreTime;
	}

	public String getUsedepName() {
		return usedepName;
	}

	public void setUsedepName(String usedepName) {
		this.usedepName = usedepName;
	}

	public String getUseuserName() {
		return useuserName;
	}

	public void setUseuserName(String useuserName) {
		this.useuserName = useuserName;
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

	public List<OutstoreGoodsListRespData> getGoods() {
		return goods;
	}

	public void setGoods(List<OutstoreGoodsListRespData> goods) {
		this.goods = goods;
	}
}
