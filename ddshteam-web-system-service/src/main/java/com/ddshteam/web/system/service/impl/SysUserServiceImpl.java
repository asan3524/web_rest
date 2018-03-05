package com.ddshteam.web.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.ddshteam.web.system.service.api.SysUserService;
import com.ddshteam.web.system.service.api.model.SysUserInfo;
import com.ddshteam.web.system.service.api.model.SysUserInfoCriteria;
import com.ddshteam.web.system.service.api.model.SysUserInfoCriteria.Criteria;
import com.ddshteam.web.system.service.dao.SysUserInfoCustomizeMapper;
import com.ddshteam.web.system.service.dao.SysUserInfoMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service(version = "1.0.0")
@Transactional(rollbackFor = Exception.class)
public class SysUserServiceImpl implements SysUserService {

	@Autowired
	private SysUserInfoMapper SysUserInfoInfoDao;
	
	@Autowired
	private SysUserInfoCustomizeMapper SysUserInfoInfoCustomizeDao;

	@Override
	public PageInfo<SysUserInfo> getUserList(int pageNum, int pageSize, String name, String depId) {
		PageHelper.startPage(pageNum, pageSize);
		SysUserInfoCriteria sysUserInfoCriteria=new SysUserInfoCriteria();
		Criteria criteria=sysUserInfoCriteria.createCriteria();
		criteria.andNameEqualTo(name);
		criteria.andDepIdEqualTo(depId);
		List<SysUserInfo> sysUserInfos=SysUserInfoInfoDao.selectByExample(sysUserInfoCriteria);
		PageInfo<SysUserInfo> pageInfo = new PageInfo<SysUserInfo>(sysUserInfos, 10);
		return pageInfo;
	}

	@Override
	public SysUserInfo getUserByAccount(String account) {
		
		SysUserInfoCriteria sysUserInfoCriteria=new SysUserInfoCriteria();
		Criteria criteria=sysUserInfoCriteria.createCriteria();
		criteria.andAccountEqualTo(account);
		criteria.andStatusEqualTo(1);
		List<SysUserInfo> sysUserInfos=SysUserInfoInfoDao.selectByExample(sysUserInfoCriteria);
		return sysUserInfos.size()>0?sysUserInfos.get(0):null;
	}

	@Override
	public SysUserInfo getUserById(String userId) {
		SysUserInfo SysUserInfo = SysUserInfoInfoDao.selectByPrimaryKey(userId);
		return SysUserInfo;
	}

	@Override
	public boolean saveUser(SysUserInfo SysUserInfo, String... roleIds) {
		int result = SysUserInfoInfoCustomizeDao.saveUser(SysUserInfo, roleIds);
		return result > 0;
	}

	@Override
	public boolean updateUser(SysUserInfo SysUserInfo, String... roleIds) {
		int result = SysUserInfoInfoCustomizeDao.updateUser(SysUserInfo, roleIds);
		return result > 0;
	}

	@Override
	public boolean updatePassword(String userId, String newPassword) {
		SysUserInfo record=new SysUserInfo();
		record.setId(userId);
		record.setPassword(newPassword);
		int result = SysUserInfoInfoDao.updateByPrimaryKeySelective(record);
		return result > 0;
	}

	@Override
	public boolean deleteUser(String userId) {
		int result =SysUserInfoInfoDao.deleteByPrimaryKey(userId);
		return result > 0;
	}

	@Override
	public boolean saveUser(SysUserInfo SysUserInfo) {
		int result = SysUserInfoInfoDao.insert(SysUserInfo);
		return result > 0;
	}

	@Override
	public boolean saveUser(List<SysUserInfo> SysUserInfos) {
		// TODO
		return false;
	}

	@Override
	public boolean updateUser(SysUserInfo SysUserInfo) {
		int result = SysUserInfoInfoDao.updateByPrimaryKeySelective(SysUserInfo);
		return result > 0;
	}

	@Override
	public boolean setUserRole(String userId, String... roleIds) {
		int result = SysUserInfoInfoCustomizeDao.setUserRole(userId, roleIds);
		return result > 0;
	}

}
