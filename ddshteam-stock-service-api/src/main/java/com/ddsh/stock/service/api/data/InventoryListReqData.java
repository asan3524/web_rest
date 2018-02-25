package com.ddsh.stock.service.api.data;

import java.util.Date;

public class InventoryListReqData {

	private String code;
	private String storeId;
	private Date makeendTime_s;
	private Date makeendTime_e;
	private int makeStatus;

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

	public Date getMakeendTime_s() {
		return makeendTime_s;
	}

	public void setMakeendTime_s(Date makeendTime_s) {
		this.makeendTime_s = makeendTime_s;
	}

	public Date getMakeendTime_e() {
		return makeendTime_e;
	}

	public void setMakeendTime_e(Date makeendTime_e) {
		this.makeendTime_e = makeendTime_e;
	}

	public int getMakeStatus() {
		return makeStatus;
	}

	public void setMakeStatus(int makeStatus) {
		this.makeStatus = makeStatus;
	}
}
