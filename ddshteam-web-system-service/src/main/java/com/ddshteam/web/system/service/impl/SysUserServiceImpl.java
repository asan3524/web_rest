package com.ddshteam.web.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.ddshteam.web.system.service.api.SysUserService;
import com.ddshteam.web.system.service.api.model.SysUser;
import com.ddshteam.web.system.service.dao.SysUserDao;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service(version = "1.0.0")
@Transactional(rollbackFor = Exception.class)
public class SysUserServiceImpl implements SysUserService {

	@Autowired
	private SysUserDao sysUserDao;

	@Override
	public PageInfo<SysUser> getUserList(int pageNum, int pageSize, String name, String depId) {
		PageHelper.startPage(pageNum, pageSize);
		List<SysUser> list = sysUserDao.getUserList(name, depId);
		PageInfo<SysUser> pageInfo = new PageInfo<SysUser>(list, 10);
		return pageInfo;
	}

	@Override
	public SysUser getUserByAccount(String account) {
		SysUser sysUser = sysUserDao.getUserByAccount(account);
		return sysUser;
	}

	@Override
	public SysUser getUserById(String userId) {
		SysUser sysUser = sysUserDao.getUserById(userId);
		return sysUser;
	}

	@Override
	public boolean saveUser(SysUser sysUser, String... roleIds) {
		int result = sysUserDao.saveUser(sysUser, roleIds);
		return result > 0;
	}

	@Override
	public boolean updateUser(SysUser sysUser, String... roleIds) {
		int result = sysUserDao.updateUser(sysUser, roleIds);
		return result > 0;
	}

	@Override
	public boolean updatePassword(String userId, String newPassword) {
		int result = sysUserDao.updatePassword(userId, newPassword);
		return result > 0;
	}

	@Override
	public boolean deleteUser(String userId) {
		int result = sysUserDao.deleteUser(userId);
		return result > 0;
	}

	@Override
	public boolean saveUser(SysUser sysUser) {
		int result = sysUserDao.saveUserNoRole(sysUser);
		return result > 0;
	}

	@Override
	public boolean saveUser(List<SysUser> sysUsers) {
		// TODO
		return false;
	}

	@Override
	public boolean updateUser(SysUser sysUser) {
		int result = sysUserDao.updateUserNoRole(sysUser);
		return result > 0;
	}

	@Override
	public boolean setUserRole(String userId, String... roleIds) {
		int result = sysUserDao.setUserRole(userId, roleIds);
		return result > 0;
	}

}
