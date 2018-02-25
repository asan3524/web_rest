package com.ddsh.stock.service.api.data;

public class InstoreGoodsReqData {

	private String purchaseGoodsId;

	private int quantity;

	public String getPurchaseGoodsId() {
		return purchaseGoodsId;
	}

	public void setPurchaseGoodsId(String purchaseGoodsId) {
		this.purchaseGoodsId = purchaseGoodsId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
