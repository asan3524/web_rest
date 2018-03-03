package com.ddshteam.web.system.service.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ddshteam.web.system.service.api.model.SysRole;

@Repository
//@Mapper
public interface SysRoleDao_back {
	
	public List<SysRole> getRoleList();
	
	public SysRole getRoleById(@Param("roleId") String roleId);
	
	public Integer saveRole(SysRole sysRole);
	
	public Integer updateRole(SysRole sysRole);
	
	public Integer deleteRole(@Param("roleId") String roleId);
	
	public Integer setRoleMenu(@Param("roleId") String roleId, @Param("menuIds") String... menuIds);

	public List<String> getMenuIdByRole(@Param("roleIds") String... roleIds);
}
