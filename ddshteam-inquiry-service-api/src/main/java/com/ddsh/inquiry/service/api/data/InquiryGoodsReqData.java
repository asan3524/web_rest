package com.ddsh.inquiry.service.api.data;

/**
 * 询价时物资对象
 * @ClassName: InquiryGoodsReqData
 * @author lishibang
 * @date 2018年2月15日 下午2:32:26
 * @version v1.0.0
 * 
 */
public class InquiryGoodsReqData {
	private String id;
	private String remark;
	private double unitPrice;

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

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
}
