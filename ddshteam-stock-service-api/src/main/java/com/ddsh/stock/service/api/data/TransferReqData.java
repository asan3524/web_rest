package com.ddsh.stock.service.api.data;

import java.util.List;

public class TransferReqData {

	private String outstoreId;

	private String instoreId;

	private String remark;

	private List<TransferGoodsReqData> goods;

	public String getOutstoreId() {
		return outstoreId;
	}

	public void setOutstoreId(String outstoreId) {
		this.outstoreId = outstoreId;
	}

	public String getInstoreId() {
		return instoreId;
	}

	public void setInstoreId(String instoreId) {
		this.instoreId = instoreId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<TransferGoodsReqData> getGoods() {
		return goods;
	}

	public void setGoods(List<TransferGoodsReqData> goods) {
		this.goods = goods;
	}
}
