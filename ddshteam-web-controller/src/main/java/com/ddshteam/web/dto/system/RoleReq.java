package com.ddshteam.web.dto.system;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

public class RoleReq {
	@NotBlank
	private String name;
	private String remark;  //(not must)
	List<String> menuids;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public List<String> getMenuids() {
		return menuids;
	}
	public void setMenuids(List<String> menuids) {
		this.menuids = menuids;
	}
	
}
