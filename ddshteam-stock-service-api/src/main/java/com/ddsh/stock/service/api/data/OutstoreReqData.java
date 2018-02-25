package com.ddsh.stock.service.api.data;

import java.util.List;

public class OutstoreReqData {

	private String storeId;

	private String usedepId;
	/**
	 * 可选
	 */
	private String useuserId;

	private String remark;

	private List<OutstoreGoodsReqData> goods;

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<OutstoreGoodsReqData> getGoods() {
		return goods;
	}

	public void setGoods(List<OutstoreGoodsReqData> goods) {
		this.goods = goods;
	}
}
