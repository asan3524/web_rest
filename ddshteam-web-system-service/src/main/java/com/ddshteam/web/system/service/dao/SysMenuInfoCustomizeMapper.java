package com.ddshteam.web.system.service.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ddshteam.web.system.service.api.data.Tree;
import com.ddshteam.web.system.service.api.model.SysMenuInfo;

public interface SysMenuInfoCustomizeMapper {

	/**
	 * 获取用户的导航菜单
	 * @Title: getMenusByUser
	 * @param userId
	 * @return List<SysMenuInfo>
	 * @author lishibang
	 */
	List<SysMenuInfo> getMenusByUser(String userId);

	/**
	 * 获取所有菜单，给内置账户使用
	 * @Title: getMenuTree
	 * @return List<Tree>
	 * @author lishibang
	 */
	List<Tree> getMenuTree();
	
	/**
	 * 获取用户权限清单
	 * @Title: getPermissionByUser
	 * @param userId
	 * @return List<String>
	 * @author lishibang
	 */
	List<String> getPermissionByUser(String userId);

	/**
	 * 指定角色且指定账户的菜单权限
	 * @Title: getMenuTreeByUserRole
	 * @param userId
	 * @param roleId
	 * @return List<Tree>
	 * @author lishibang
	 */
	List<Tree> getMenuTreeByUserRole(@Param("userId") String userId, @Param("roleId") String roleId);

	/**
	 * 指定角色菜单权限，给内置账户使用
	 * @Title: getMenuTreeByRole
	 * @param roleId
	 * @return List<Tree>
	 * @author lishibang
	 */
	List<Tree> getMenuTreeByRole(String roleId);

	/**
	 * 获取指定用户所有菜单，用于新增角色
	 * @Title: getMenuTreeByUser
	 * @param userId
	 * @return List<Tree>
	 * @author lishibang
	 */
	List<Tree> getMenuTreeByUser(String userId);

	/**
	 * 关联删除菜单
	 * @Title: deleteByPrimaryKey
	 * @param id
	 * @return int
	 * @author lishibang
	 */
	int deleteByPrimaryKey(String id);
}