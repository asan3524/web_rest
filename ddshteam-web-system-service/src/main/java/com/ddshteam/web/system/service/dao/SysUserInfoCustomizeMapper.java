package com.ddshteam.web.system.service.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ddshteam.web.system.service.api.model.SysUserInfo;


@Repository
//@Mapper
public interface SysUserInfoCustomizeMapper {
	
	public List<SysUserInfo> getUserList(@Param("name") String name, @Param("depId") String depId);
	
	public SysUserInfo getUserByAccount(@Param("account") String account);
	
	public SysUserInfo getUserById(@Param("userId") String userId);
	
	/**
	 * <p>注意开启mysql驱动批量执行开关</p>
	 * @param sysUser
	 * @param roleIds 
	 * @return
	 */
	public Integer saveUser(SysUserInfo sysUser,@Param("id")String id, @Param("roleIds") String... roleIds);
	
	public Integer saveUserNoRole(SysUserInfo sysUser);
	
	public Integer updateUser(SysUserInfo sysUser, @Param("roleIds") String... roleIds);
	
	public Integer updateUserNoRole(SysUserInfo sysUser);
	
	public Integer updatePassword(
			@Param("userId") String userId, 
			@Param("newPassword") String newPassword);
	
	public Integer deleteUser(@Param("userId") String userId);
	
	public Integer setUserRole(@Param("userId") String userId, @Param("roleIds") String... roleIds);
}
