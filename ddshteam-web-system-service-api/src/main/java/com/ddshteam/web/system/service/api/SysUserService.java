package com.ddshteam.web.system.service.api;

import java.util.List;

import com.ddshteam.web.system.service.api.model.SysUser;
import com.github.pagehelper.PageInfo;

public interface SysUserService {

	/**
	 * 获取用户分页列表
	 * <p>需关联部门,角色,隐藏内建用户(TODO 权限不拦截)</p>
	 * @Title: getUserList
	 * @param pageNum 当前页索引
	 * @param pageSize 分页大小
	 * @param name 用户名模糊搜索（可选null）
	 * @param depId 用户所属部门（下拉可选null）
	 * @return PageInfo<SysUser>
	 * @author lishibang
	 */
	public PageInfo<SysUser> getUserList(int pageNum, int pageSize, String name, String depId);

	/**
	 * 根据账号(用户名)获取用户
	 * <p>需关联部门,角色</p>
	 * @param account
	 * @return
	 */
	public SysUser getUserByAccount(String account);

	/**
	 * 根据用户id获取用户
	 * <p>需关联部门,角色</p>
	 * @param userId
	 * @return
	 */
	public SysUser getUserById(String userId);

	/**
	 * 添加用户，只添加用户信息不指定角色时使用
	 * @param sysUser
	 * @return
	 */
	public boolean saveUser(SysUser sysUser);

	/**
	 * 批量导入用户
	 * @param sysUsers
	 * @return
	 */
	public boolean saveUser(List<SysUser> sysUsers);

	/**
	 * 添加用户
	 * @param sysUser
	 * @param roleIds 角色id数组(必填)
	 * @return
	 */
	public boolean saveUser(SysUser sysUser, String... roleIds);

	/**
	 * 修改用户，只修改用户信息时使用
	 * @param sysUser
	 * @return
	 */
	public boolean updateUser(SysUser sysUser);

	/**
	 * 修改用户
	 * <p>仅限修改详情,包含「部门/角色」,但不包含密码</p>
	 * @param sysUser
	 * @param roleIds 角色id数组(必填)
	 * @return
	 */
	public boolean updateUser(SysUser sysUser, String... roleIds);

	/**
	 * 更新用户密码
	 * @param userId      用户id
	 * @param newPassword 用户新密码(需加密)
	 * @return
	 */
	public boolean updatePassword(String userId, String newPassword);

	/**
	 * 删除用户，内置用户不能被删除，考虑在上层做判定当前登录用户不能删除自己，admin账户不能被删除
	 * <p>用户置为失效（无法登陆），但是删除user2role中间表</p>
	 * @param userId 用户id
	 * @return
	 */
	public boolean deleteUser(String userId);

	/**
	 * 为指定用户设置角色列表
	 * @Title: setUserRole
	 * @param userId
	 * @param roleIds 为空时表示不为用户设置任何角色(业务上必填)
	 * @return boolean
	 * @author lishibang
	 */
	public boolean setUserRole(String userId, String... roleIds);
}
