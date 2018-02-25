package com.ddsh.stock.service.api.data;

public class TransferGoodsReqData {

	private String stockId;

	private int quantity;

	public String getStockId() {
		return stockId;
	}

	public void setStockId(String stockId) {
		this.stockId = stockId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
