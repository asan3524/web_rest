package com.ddshteam.web.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.ddshteam.web.system.service.api.SysRoleService;
import com.ddshteam.web.system.service.api.data.Tree;
import com.ddshteam.web.system.service.api.model.SysRole;
import com.ddshteam.web.system.service.dao.SysRoleDao;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service(version = "1.0.0")
@Transactional(rollbackFor = Exception.class)
public class SysRoleServiceImpl implements SysRoleService{
	
	@Autowired
	private SysRoleDao sysRoleDao;

	@Override
	public PageInfo<SysRole> getRoleList(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<SysRole> list = sysRoleDao.getRoleList();
		PageInfo<SysRole> pageInfo = new PageInfo<SysRole>(list, 10);
		return pageInfo;
	}

	@Override
	public SysRole getRoleById(String roleId) {
		return sysRoleDao.getRoleById(roleId);
	}

	@Override
	public boolean saveRole(SysRole sysRole) {
		int result = sysRoleDao.saveRole(sysRole);
		return result > 0;
	}

	@Override
	public boolean updateRole(SysRole sysRole) {
		int result = sysRoleDao.updateRole(sysRole);
		return result > 0;
	}

	@Override
	public boolean deleteRole(String roleId) {
		int result = sysRoleDao.deleteRole(roleId);
		return result > 0;
	}

	@Override
	public List<Tree> getRoleTreeByUser(String userId) {
		// TODO 角色目前无树结构
		return null;
	}

	@Override
	public boolean setRoleMenu(String roleId, String... menuIds) {
		int result = sysRoleDao.setRoleMenu(roleId, menuIds);
		return result > 0;
	}

	@Override
	public List<String> getMenuIdByRole(String... roleIds) {
		return sysRoleDao.getMenuIdByRole(roleIds);
	}
	
}
