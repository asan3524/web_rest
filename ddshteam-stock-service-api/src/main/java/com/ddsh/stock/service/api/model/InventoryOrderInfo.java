package com.ddsh.stock.service.api.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@SuppressWarnings("serial")
@Builder
@Data
@ToString
public class InventoryOrderInfo implements Serializable {

	private String id;
	private String code;
	private String storeId;
	private String makeuserId;
	private Date makestartTime;
	private Date makeendTime;

	private Date lastmakeTime;

	private int makeStatus;

	private String remark;
	private int status;
	private Date updatTime;

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

	public String getMakeuserId() {
		return makeuserId;
	}

	public void setMakeuserId(String makeuserId) {
		this.makeuserId = makeuserId;
	}

	public Date getMakestartTime() {
		return makestartTime;
	}

	public void setMakestartTime(Date makestartTime) {
		this.makestartTime = makestartTime;
	}

	public Date getMakeendTime() {
		return makeendTime;
	}

	public void setMakeendTime(Date makeendTime) {
		this.makeendTime = makeendTime;
	}

	public Date getLastmakeTime() {
		return lastmakeTime;
	}

	public void setLastmakeTime(Date lastmakeTime) {
		this.lastmakeTime = lastmakeTime;
	}

	public int getMakeStatus() {
		return makeStatus;
	}

	public void setMakeStatus(int makeStatus) {
		this.makeStatus = makeStatus;
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

	public Date getUpdatTime() {
		return updatTime;
	}

	public void setUpdatTime(Date updatTime) {
		this.updatTime = updatTime;
	}
}