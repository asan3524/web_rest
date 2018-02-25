package com.ddsh.stock.service.api.data;

import java.util.Date;

public class InventoryGoodsListRespData {

	private String id;

	private String inventoryId;
	private String goodsId;
	private String name;
	private String code;
	private String brand;
	private String type;
	private String colour;
	private String unit;
	private String norm;
	private int stockQuantity;

	private Date outstoreTime;
	private String usedepId;
	private String usedepName;
	private int goodsStatus;
	private int makeStatus;
	private String remark;

	private String makeuserId;
	private String makeuserName;
	private Date makeTime;

	private int lastQuantity;
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

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getNorm() {
		return norm;
	}

	public void setNorm(String norm) {
		this.norm = norm;
	}

	public int getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
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

	public String getUsedepName() {
		return usedepName;
	}

	public void setUsedepName(String usedepName) {
		this.usedepName = usedepName;
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

	public String getMakeuserId() {
		return makeuserId;
	}

	public void setMakeuserId(String makeuserId) {
		this.makeuserId = makeuserId;
	}

	public String getMakeuserName() {
		return makeuserName;
	}

	public void setMakeuserName(String makeuserName) {
		this.makeuserName = makeuserName;
	}

	public Date getMakeTime() {
		return makeTime;
	}

	public void setMakeTime(Date makeTime) {
		this.makeTime = makeTime;
	}

	public int getLastQuantity() {
		return lastQuantity;
	}

	public void setLastQuantity(int lastQuantity) {
		this.lastQuantity = lastQuantity;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
