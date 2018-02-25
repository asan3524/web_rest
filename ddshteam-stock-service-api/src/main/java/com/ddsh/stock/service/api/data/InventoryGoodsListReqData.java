package com.ddsh.stock.service.api.data;

public class InventoryGoodsListReqData {

	/**
	 * 必填
	 * @Fields inventoryId
	 */
	private String inventoryId;
	private String name;
	private String code;
	private String brand;
	private String type;
	private Integer makeStatus;

	public String getInventoryId() {
		return inventoryId;
	}

	public void setInventoryId(String inventoryId) {
		this.inventoryId = inventoryId;
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

	public Integer getMakeStatus() {
		return makeStatus;
	}

	public void setMakeStatus(Integer makeStatus) {
		this.makeStatus = makeStatus;
	}
}
