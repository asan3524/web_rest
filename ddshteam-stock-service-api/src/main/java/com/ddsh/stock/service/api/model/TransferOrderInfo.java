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
public class TransferOrderInfo implements Serializable {

	private String id;
	private String code;
	private String outstoreId;
	private String outuserId;
	private Date outstoreTime;

	private String usedepId;
	private String useuserId;

	private String instoreId;
	private String inuserId;
	private Date instoreTime;

	private int orderStatus;

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

	public String getOutstoreId() {
		return outstoreId;
	}

	public void setOutstoreId(String outstoreId) {
		this.outstoreId = outstoreId;
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

	public String getInstoreId() {
		return instoreId;
	}

	public void setInstoreId(String instoreId) {
		this.instoreId = instoreId;
	}

	public String getInuserId() {
		return inuserId;
	}

	public void setInuserId(String inuserId) {
		this.inuserId = inuserId;
	}

	public Date getInstoreTime() {
		return instoreTime;
	}

	public void setInstoreTime(Date instoreTime) {
		this.instoreTime = instoreTime;
	}

	public int getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
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