package com.ddshteam.web.system.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.ddshteam.web.system.service.api.SysRoleService;
import com.ddshteam.web.system.service.api.data.Tree;
import com.ddshteam.web.system.service.api.model.SysRoleInfo;
import com.ddshteam.web.system.service.api.model.SysRoleInfoCriteria;
import com.ddshteam.web.system.service.api.model.SysRoleInfoCriteria.Criteria;
import com.ddshteam.web.system.service.api.model.SysRoleToMenuCriteria;
import com.ddshteam.web.system.service.api.model.SysRoleToUserCriteria;
import com.ddshteam.web.system.service.dao.SysRoleInfoCustomizeMapper;
import com.ddshteam.web.system.service.dao.SysRoleInfoMapper;
import com.ddshteam.web.system.service.dao.SysRoleToMenuMapper;
import com.ddshteam.web.system.service.dao.SysRoleToUserMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service(version = "1.0.0")
@Transactional(rollbackFor = Exception.class)
public class SysRoleServiceImpl implements SysRoleService {

	@Autowired
	private SysRoleInfoMapper sysRoleInfoDao;
	@Autowired
	private SysRoleInfoCustomizeMapper sysRoleInfoCustomizeDao;
	@Autowired
	private SysRoleToMenuMapper sysRoleToMenuDao;
	@Autowired
	private SysRoleToUserMapper sysRoleToUserDao;

	@Override
	public PageInfo<SysRoleInfo> getRoleList(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		SysRoleInfoCriteria sysRoleInfoCriteria = new SysRoleInfoCriteria();
		sysRoleInfoCriteria.setOrderByClause(" create_time desc");
		Criteria criteria = sysRoleInfoCriteria.createCriteria();
		criteria.andIdIsNotNull();
		List<SysRoleInfo> list = sysRoleInfoDao.selectByExample(sysRoleInfoCriteria);
		PageInfo<SysRoleInfo> pageInfo = new PageInfo<SysRoleInfo>(list, pageSize);
		return pageInfo;
	}

	@Override
	public SysRoleInfo getRoleById(String roleId) {
		return sysRoleInfoDao.selectByPrimaryKey(roleId);
	}

	@Override
	public boolean saveRole(SysRoleInfo sysRole) {
		sysRole.setCreateTime(new Date());
		int result = sysRoleInfoDao.insert(sysRole);
		return result > 0;
	}

	@Override
	public boolean updateRole(SysRoleInfo sysRole) {
		int result = sysRoleInfoDao.updateByPrimaryKeySelective(sysRole);
		return result > 0;
	}

	@Override
	public boolean deleteRole(String roleId) {
		SysRoleToUserCriteria sysRoleToUserCriteria = new SysRoleToUserCriteria();
		com.ddshteam.web.system.service.api.model.SysRoleToUserCriteria.Criteria criteria = sysRoleToUserCriteria
				.createCriteria();
		criteria.andRoleIdEqualTo(roleId);
		int result = sysRoleToUserDao.deleteByExample(sysRoleToUserCriteria);

		SysRoleToMenuCriteria sysRoleToMenuCriteria = new SysRoleToMenuCriteria();
		com.ddshteam.web.system.service.api.model.SysRoleToMenuCriteria.Criteria mcriteria = sysRoleToMenuCriteria
				.createCriteria();
		mcriteria.andRoleIdEqualTo(roleId);
		result = sysRoleToMenuDao.deleteByExample(sysRoleToMenuCriteria);

		result = sysRoleInfoDao.deleteByPrimaryKey(roleId);

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

	@Override
	public boolean deleteRoles(List<String> roles) {

		SysRoleToUserCriteria sysRoleToUserCriteria = new SysRoleToUserCriteria();
		com.ddshteam.web.system.service.api.model.SysRoleToUserCriteria.Criteria criteria = sysRoleToUserCriteria
				.createCriteria();
		criteria.andRoleIdIn(roles);
		int result = sysRoleToUserDao.deleteByExample(sysRoleToUserCriteria);

		SysRoleToMenuCriteria sysRoleToMenuCriteria = new SysRoleToMenuCriteria();
		com.ddshteam.web.system.service.api.model.SysRoleToMenuCriteria.Criteria mcriteria = sysRoleToMenuCriteria
				.createCriteria();
		mcriteria.andRoleIdIn(roles);
		result = sysRoleToMenuDao.deleteByExample(sysRoleToMenuCriteria);

		SysRoleInfoCriteria  sysRoleInfoCriteria=new SysRoleInfoCriteria();
		com.ddshteam.web.system.service.api.model.SysRoleInfoCriteria.Criteria roleCriteria=sysRoleInfoCriteria.createCriteria();
		roleCriteria.andIdIn(roles);
		result = sysRoleInfoDao.deleteByExample(sysRoleInfoCriteria);

		return result > 0;
	}

}
