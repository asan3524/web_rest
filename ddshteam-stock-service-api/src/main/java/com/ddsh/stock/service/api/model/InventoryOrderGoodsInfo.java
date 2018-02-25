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
public class InventoryOrderGoodsInfo implements Serializable {

	private String id;
	private String inventoryId;
	private String stockId;

	private Date outstoreTime;
	private String usedepId;

	private int goodsStatus;
	private int makeStatus;

	private String remark;

	private int lastQuantity;
	private String makeuserId;
	private Date makeTime;
	private int stockQuantity;
	private int quantity;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInventoryId() {
		return inventoryId;
	}

	public void setInventoryId(String inventoryId) {
		this.inventoryId = inventoryId;
	}

	public String getStockId() {
		return stockId;
	}

	public void setStockId(String stockId) {
		this.stockId = stockId;
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

	public int getGoodsStatus() {
		return goodsStatus;
	}

	public void setGoodsStatus(int goodsStatus) {
		this.goodsStatus = goodsStatus;
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

	public int getLastQuantity() {
		return lastQuantity;
	}

	public void setLastQuantity(int lastQuantity) {
		this.lastQuantity = lastQuantity;
	}

	public String getMakeuserId() {
		return makeuserId;
	}

	public void setMakeuserId(String makeuserId) {
		this.makeuserId = makeuserId;
	}

	public Date getMakeTime() {
		return makeTime;
	}

	public void setMakeTime(Date makeTime) {
		this.makeTime = makeTime;
	}

	public int getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}