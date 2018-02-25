package com.ddsh.purchase.service.api.data;

import java.util.Date;

/**
 * 采购时物资对象
 * @ClassName: PurchaseGoodsReqData
 * @author lishibang
 * @date 2018年2月18日 上午10:11:52
 * @version v1.0.0
 * 
 */
public class PurchaseGoodsReqData {
	private String id;
	private String remark;
	private String norm;
	/**
	 * 实际采购价格
	 * @Fields price
	 */
	private double price;
	/**
	 * 供应商选填
	 * @Fields supplier
	 */
	private String supplier;
	/**
	 * 预计到货时间选填
	 * @Fields arrivalTime
	 */
	private Date arrivalTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getNorm() {
		return norm;
	}

	public void setNorm(String norm) {
		this.norm = norm;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public Date getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(Date arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
}
