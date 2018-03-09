package com.ddshteam.web.system.service.api.data;


import java.util.List;

import com.ddshteam.web.system.service.api.model.SysDepInfo;
import com.ddshteam.web.system.service.api.model.SysRoleInfo;
import com.ddshteam.web.system.service.api.model.SysUserInfo;

@SuppressWarnings("serial")
public class SysUserInfoResp extends SysUserInfo{
    private SysDepInfo dept;      //关联部门
    private List<SysRoleInfo> roles;//用户的角色
	public SysDepInfo getDept() {
		return dept;
	}
	public void setDept(SysDepInfo dept) {
		this.dept = dept;
	}
	public List<SysRoleInfo> getRoles() {
		return roles;
	}
	public void setRoles(List<SysRoleInfo> roles) {
		this.roles = roles;
	}
    
    
}
