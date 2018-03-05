package com.ddshteam.web.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.ddshteam.web.system.service.api.SysRoleService;
import com.ddshteam.web.system.service.api.data.Tree;
import com.ddshteam.web.system.service.api.model.SysRoleInfo;
import com.ddshteam.web.system.service.api.model.SysRoleInfoCriteria;
import com.ddshteam.web.system.service.api.model.SysRoleInfoCriteria.Criteria;
import com.ddshteam.web.system.service.dao.SysRoleInfoCustomizeMapper;
import com.ddshteam.web.system.service.dao.SysRoleInfoMapper;
import com.ddshteam.web.system.service.dao.SysRoleToMenuMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service(version = "1.0.0")
@Transactional(rollbackFor = Exception.class)
public class SysRoleServiceImpl implements SysRoleService{
	
	@Autowired
	private SysRoleInfoMapper sysRoleInfoDao;
	@Autowired
	private SysRoleInfoCustomizeMapper sysRoleInfoCustomizeDao;
	@Autowired
	private SysRoleToMenuMapper SysRoleToMenuDao;
	@Override
	public PageInfo<SysRoleInfo> getRoleList(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		SysRoleInfoCriteria sysRoleInfoCriteria=new SysRoleInfoCriteria(); 
		Criteria criteria=sysRoleInfoCriteria.createCriteria();
		criteria.andIdIsNotNull();
		List<SysRoleInfo> list = sysRoleInfoDao.selectByExample(sysRoleInfoCriteria);
		PageInfo<SysRoleInfo> pageInfo = new PageInfo<SysRoleInfo>(list, 10);
		return pageInfo;
	}

	@Override
	public SysRoleInfo getRoleById(String roleId) {
		return sysRoleInfoDao.selectByPrimaryKey(roleId);
	}

	@Override
	public boolean saveRole(SysRoleInfo sysRole) {
		int result = sysRoleInfoDao.updateByPrimaryKey(sysRole);
		return result > 0;
	}

	@Override
	public boolean updateRole(SysRoleInfo sysRole) {
		int result = sysRoleInfoDao.updateByPrimaryKeySelective(sysRole);
		return result > 0;
	}

	@Override
	public boolean deleteRole(String roleId) {
		int result = sysRoleInfoDao.deleteByPrimaryKey(roleId);
		return result > 0;
	}

	@Override
	public List<Tree> getRoleTreeByUser(String userId) {
		return null;
	}

	@Override
	public boolean setRoleMenu(String roleId, String... menuIds) {
		int result = sysRoleInfoCustomizeDao.setRoleMenu(roleId, menuIds);
		return result > 0;
	}

	@Override
	public List<String> getMenuIdByRole(String... roleIds) {
 		return sysRoleInfoCustomizeDao.getMenuIdByRole(roleIds);
		 
	}
	
}
