package com.ddshteam.web.system.service.api.data;

import com.ddshteam.web.system.service.api.model.SysDepInfo;

@SuppressWarnings("serial")
public class DeptInfoResp extends  SysDepInfo{

	protected String depttypename;

	public String getDepttypename() {
		return depttypename;
	}

	public void setDepttypename(String depttypename) {
		this.depttypename = depttypename;
	}
	
}
