package com.ddshteam.web.system.service.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ddshteam.web.system.service.api.model.SysMenu;

@Repository
//@Mapper
public interface SysMenuDao_back {
	
	public List<SysMenu> getMenuList();
	
	public List<SysMenu> getMenu$NoFuncList();
	
	public SysMenu getMenuById(@Param("menuId") String menuId);
	
	public List<SysMenu> getMenuByIds(@Param("menuIds") String... menuIds);
	
	public Integer saveMenu(SysMenu sysMenu);
	
	public Integer updateMenu(SysMenu sysMenu);
	
	public Integer deleteMenuById(@Param("menuId") String menuId);
	
	/**
	 * 获取用户的按钮权限(type=3)
	 * @Title: getPermissionByUser
	 * @param userId
	 * @return List<String>
	 * @author duyu
	 */
	public List<String> getPermissionByUser(@Param("userId") String userId);
	
	/**
	 * 获取用户的左侧菜单(type!=3)
	 * @Title: getMenus$NoFuncByUser
	 * @param userId
	 * @return List<SysMenu>
	 * @author duyu
	 */
	public List<SysMenu> getMenus$NoFuncByUser(@Param("userId") String userId);
	
}
