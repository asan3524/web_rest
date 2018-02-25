package com.ddshteam.web.dto.system;

import org.hibernate.validator.constraints.NotBlank;

public class RoleReq {
	@NotBlank
	private String name;
	private String remark;  //(not must)
	
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
	
}
