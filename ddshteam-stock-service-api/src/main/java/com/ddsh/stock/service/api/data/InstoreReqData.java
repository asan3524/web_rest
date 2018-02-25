package com.ddsh.stock.service.api.data;

import java.util.List;

public class InstoreReqData {

	private String storeId;

	private String purchaseId;

	private List<InstoreGoodsReqData> goods;

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(String purchaseId) {
		this.purchaseId = purchaseId;
	}

	public List<InstoreGoodsReqData> getGoods() {
		return goods;
	}

	public void setGoods(List<InstoreGoodsReqData> goods) {
		this.goods = goods;
	}
}
