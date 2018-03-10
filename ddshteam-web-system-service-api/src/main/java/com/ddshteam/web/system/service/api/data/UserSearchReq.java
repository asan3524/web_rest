package com.ddshteam.web.system.service.api.data;

import java.io.Serializable;

@SuppressWarnings("serial")
public class UserSearchReq implements Serializable{
	
	String username;
	
	String[] depids;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String[] getDepids() {
		return depids;
	}

	public void setDepids(String[] depids) {
		this.depids = depids;
	}

}
