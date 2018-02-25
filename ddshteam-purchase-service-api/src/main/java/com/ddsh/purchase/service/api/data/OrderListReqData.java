package com.ddsh.purchase.service.api.data;

import java.util.Date;

/**
 * 采购单检索条件对象
 * @ClassName: OrderListReqData
 * @author lishibang
 * @date 2018年2月18日 上午9:32:07
 * @version v1.0.0
 * 
 */
public class OrderListReqData {
	/**
	 * 采购单编码
	 * @Fields code
	 */
	private String code;
	/**
	 * 采购单名称
	 * @Fields name
	 */
	private String name;
	/**
	 * 物资名称
	 * @Fields goodsName
	 */
	private String goodsName;
	private Integer step;
	private Integer status;
	/**
	 * 申请时间
	 * @Fields initiateTime_s
	 */
	private Date initiateTime_s;
	private Date initiateTime_e;
	/**
	 * 更新时间
	 * @Fields updateTime_s
	 */
	private Date updateTime_s;
	private Date updateTime_e;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public Integer getStep() {
		return step;
	}

	public void setStep(Integer step) {
		this.step = step;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getInitiateTime_s() {
		return initiateTime_s;
	}

	public void setInitiateTime_s(Date initiateTime_s) {
		this.initiateTime_s = initiateTime_s;
	}

	public Date getInitiateTime_e() {
		return initiateTime_e;
	}

	public void setInitiateTime_e(Date initiateTime_e) {
		this.initiateTime_e = initiateTime_e;
	}

	public Date getUpdateTime_s() {
		return updateTime_s;
	}

	public void setUpdateTime_s(Date updateTime_s) {
		this.updateTime_s = updateTime_s;
	}

	public Date getUpdateTime_e() {
		return updateTime_e;
	}

	public void setUpdateTime_e(Date updateTime_e) {
		this.updateTime_e = updateTime_e;
	}
}
