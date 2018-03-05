package com.ddshteam.web.system.service.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ddshteam.web.system.service.api.model.SysRoleInfo;

public interface SysRoleInfoCustomizeMapper {
	
	public List<SysRoleInfo> getRoleList();
	
	public SysRoleInfo getRoleById(@Param("roleId") String roleId);
	
	public Integer saveRole(SysRoleInfo sysRole);
	
	public Integer updateRole(SysRoleInfo sysRole);
	
	public Integer deleteRole(@Param("roleId") String roleId);
	
	public Integer setRoleMenu(@Param("roleId") String roleId, @Param("menuIds") String... menuIds);

	public List<String> getMenuIdByRole(@Param("roleIds") String... roleIds);
}
