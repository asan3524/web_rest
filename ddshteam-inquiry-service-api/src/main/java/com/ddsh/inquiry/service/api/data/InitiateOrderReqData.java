package com.ddsh.inquiry.service.api.data;

import java.util.List;

/**
 * 询价单对象
 * @ClassName: InitiateOrderReqData
 * @author lishibang
 * @date 2018年2月15日 下午2:05:27
 * @version v1.0.0
 * 
 */
public class InitiateOrderReqData {
	/**
	 * 询价单标题
	 * @Fields name
	 */
	private String name;
	/**
	 * 询价单备注说明
	 * @Fields inquiryRemark
	 */
	private String inquiryRemark;
	/**
	 * 询价物资清单
	 * @Fields goods
	 */
	private List<InitiateGoodsReqData> goods;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInquiryRemark() {
		return inquiryRemark;
	}

	public void setInquiryRemark(String inquiryRemark) {
		this.inquiryRemark = inquiryRemark;
	}

	public List<InitiateGoodsReqData> getGoods() {
		return goods;
	}

	public void setGoods(List<InitiateGoodsReqData> goods) {
		this.goods = goods;
	}
}
