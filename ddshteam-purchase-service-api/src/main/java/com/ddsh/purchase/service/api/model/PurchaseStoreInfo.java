package com.ddsh.purchase.service.api.model;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@SuppressWarnings("serial")
@Builder
@Data
@ToString
public class PurchaseStoreInfo implements Serializable {
	private String id;
	private String purchaseId;
	private String storeId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(String purchaseId) {
		this.purchaseId = purchaseId;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
}
