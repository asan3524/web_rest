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
public class OutstoreInfo implements Serializable {

	private String id;
	private String code;
	private String storeId;
	private String outuserId;
	private Date outstoreTime;
	private String usedepId;
	private String useuserId;
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

	public String getOutuserId() {
		return outuserId;
	}

	public void setOutuserId(String outuserId) {
		this.outuserId = outuserId;
	}

	public Date getOutstoreTime() {
		return outstoreTime;
	}

	public void setOutstoreTime(Date outstoreTime) {
		this.outstoreTime = outstoreTime;
	}

	public String getUsedepId() {
		return usedepId;
	}

	public void setUsedepId(String usedepId) {
		this.usedepId = usedepId;
	}

	public String getUseuserId() {
		return useuserId;
	}

	public void setUseuserId(String useuserId) {
		this.useuserId = useuserId;
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