package com.ddshteam.web.system.service.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ddshteam.web.system.service.api.model.SysUser;

@Repository
//@Mapper
public interface SysUserDao {
	
	public List<SysUser> getUserList(@Param("name") String name, @Param("depId") String depId);
	
	public SysUser getUserByAccount(@Param("account") String account);
	
	public SysUser getUserById(@Param("userId") String userId);
	
	/**
	 * <p>注意开启mysql驱动批量执行开关</p>
	 * @param sysUser
	 * @param roleIds 
	 * @return
	 */
	public Integer saveUser(SysUser sysUser, @Param("roleIds") String... roleIds);
	
	public Integer saveUserNoRole(SysUser sysUser);
	
	public Integer updateUser(SysUser sysUser, @Param("roleIds") String... roleIds);
	
	public Integer updateUserNoRole(SysUser sysUser);
	
	public Integer updatePassword(
			@Param("userId") String userId, 
			@Param("newPassword") String newPassword);
	
	public Integer deleteUser(@Param("userId") String userId);
	
	public Integer setUserRole(@Param("userId") String userId, @Param("roleIds") String... roleIds);
}
