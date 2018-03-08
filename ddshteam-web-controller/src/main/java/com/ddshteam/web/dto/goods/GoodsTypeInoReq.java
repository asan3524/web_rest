package com.ddshteam.web.dto.goods;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class GoodsTypeInoReq {
	@NotBlank
	String name;
	
	String remark;
	@NotNull
	String parentid;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getParentid() {
		return parentid;
	}
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
