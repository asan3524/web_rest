package com.ddshteam.web.dto.system;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class DeptReq {
	@NotBlank
	private String name;
	private String parentId;//(not must)上级部门ID
	private String typeId;  //(not must)部门分类，当前系统无部门分类时，此列无意义为空
	private String remark;  //(not must)
	@NotNull
	private Integer orderNum;
	@NotNull
	private Integer status; //状态：1有效0失效
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
}
