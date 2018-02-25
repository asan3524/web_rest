package com.ddshteam.web.dto.system;

import java.util.List;

import javax.validation.constraints.NotNull;

public class UserReq extends UserReqBase {
	
	@NotNull
	List<String> roleIds;

	public List<String> getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(List<String> roleIds) {
		this.roleIds = roleIds;
	}
}
