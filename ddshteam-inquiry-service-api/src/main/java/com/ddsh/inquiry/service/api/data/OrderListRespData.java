package com.ddsh.inquiry.service.api.data;

import java.util.Date;
import java.util.List;

/**
 * 询价单列表对象
 * @ClassName: OrderListRespData
 * @author lishibang
 * @date 2018年2月15日 下午2:22:46
 * @version v1.0.0
 * 
 */
public class OrderListRespData {
	private String id;
	private String code;
	private String name;
	private int step;
	private int lifeStatus;
	private int status;
	private Date initiateTime;
	private Date updateTime;
	private List<GoodsListRespData> goods;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	public int getLifeStatus() {
		return lifeStatus;
	}

	public void setLifeStatus(int lifeStatus) {
		this.lifeStatus = lifeStatus;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getInitiateTime() {
		return initiateTime;
	}

	public void setInitiateTime(Date initiateTime) {
		this.initiateTime = initiateTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public List<GoodsListRespData> getGoods() {
		return goods;
	}

	public void setGoods(List<GoodsListRespData> goods) {
		this.goods = goods;
	}
}
