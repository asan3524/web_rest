package com.ddshteam.web.system.service.api.data;

import java.io.Serializable;

@SuppressWarnings("serial")
public class RoleListReq implements Serializable {

	String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
