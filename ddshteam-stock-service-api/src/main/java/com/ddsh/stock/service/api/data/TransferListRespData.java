package com.ddsh.stock.service.api.data;

import java.util.Date;
import java.util.List;

public class TransferListRespData {

	private String id;
	private String code;

	private String outstoreId;
	private String outstoreName;
	private String outuserName;
	private Date outstoreTime;

	private String instoreId;
	private String instoreName;
	private String inuserName;
	private Date instoreTime;

	private String remark;

	private int orderStatus;
	private int status;
	private List<TransferGoodsListRespData> goods;

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

	public String getOutstoreName() {
		return outstoreName;
	}

	public void setOutstoreName(String outstoreName) {
		this.outstoreName = outstoreName;
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

	public String getInstoreId() {
		return instoreId;
	}

	public void setInstoreId(String instoreId) {
		this.instoreId = instoreId;
	}

	public String getInstoreName() {
		return instoreName;
	}

	public void setInstoreName(String instoreName) {
		this.instoreName = instoreName;
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

	public int getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<TransferGoodsListRespData> getGoods() {
		return goods;
	}

	public void setGoods(List<TransferGoodsListRespData> goods) {
		this.goods = goods;
	}
}
