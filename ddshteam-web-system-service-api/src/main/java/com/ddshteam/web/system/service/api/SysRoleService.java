package com.ddshteam.web.system.service.api;

import java.util.List;

import com.ddshteam.web.system.service.api.data.Tree;
import com.ddshteam.web.system.service.api.model.SysRoleInfo;
import com.github.pagehelper.PageInfo;

public interface SysRoleService {

	/**
	 * 获取角色分页列表
	 * @param pageNum  当前页索引
	 * @param pageSize 分页大小
	 * @return
	 */
	public PageInfo<SysRoleInfo> getRoleList(int pageNum, int pageSize);

	/**
	 * 根据id获取角色详情
	 * @param roleId 角色id
	 * @return
	 */
	public SysRoleInfo getRoleById(String roleId);

	/**
	 * 添加角色
	 * @param sysRole
	 * @return
	 */
	public boolean saveRole(SysRoleInfo sysRole,String ...menuIds);

	/**
	 * 更新角色
	 * @param sysRole
	 * @return
	 */
	public boolean updateRole(SysRoleInfo sysRole,String ...menuIds);

	/**
	 * 删除角色
	 * <p>删除user2role,role2menu中间表</p>
	 * @param roleId 角色id，admin角色不能被删除
	 * @return
	 */
	public boolean deleteRole(String roleId);

	
	/**
	 * 批量删除角色
	 * <p>删除user2role,role2menu中间表</p>
	 * @param roleId 角色id，admin角色不能被删除
	 * @return
	 */
	public boolean deleteRoles(List<String> roles);
	
	/**
	 * 获取指定用户的可选（带可选标记）角色树
	 * {id,name,disabled,isLeaf,checkStatus=1/0}
	 * @Title: getRoleTreeByUser
	 * @param userId 
	 * @return List<Tree>
	 * @author lishibang
	 */
	public List<Tree> getRoleTreeByUser(String userId);

	/**
	 * 为角色设置菜单权限
	 * @Title: setRoleMenu
	 * @param roleId
	 * @param menuIds 为空时表示不为角色设置任何菜单
	 * @return boolean
	 * @author lishibang
	 */
	public boolean setRoleMenu(String roleId, String... menuIds);
	
	/**
	 * 获取角色的菜单权限id数组
	 * @Title: getMenuIdByRole
	 * @param roleIds
	 * @return List<String>
	 * @author duyu
	 */
	public List<String> getMenuIdByRole(String... roleIds);
}
