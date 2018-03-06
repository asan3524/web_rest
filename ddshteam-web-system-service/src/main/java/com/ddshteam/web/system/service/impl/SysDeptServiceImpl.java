package com.ddshteam.web.system.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.ddshteam.web.system.service.api.SysDeptService;
import com.ddshteam.web.system.service.api.constant.SystemContants;
import com.ddshteam.web.system.service.api.data.Tree;
import com.ddshteam.web.system.service.api.model.SysDepInfo;
import com.ddshteam.web.system.service.api.model.SysDepInfoCriteria;
import com.ddshteam.web.system.service.api.model.SysDepInfoCriteria.Criteria;
import com.ddshteam.web.system.service.api.model.SysUserInfo;
import com.ddshteam.web.system.service.api.model.SysUserInfoCriteria;
import com.ddshteam.web.system.service.dao.SysDepInfoMapper;
import com.ddshteam.web.system.service.dao.SysDeptCustomizeMapper;
import com.ddshteam.web.system.service.dao.SysUserInfoCustomizeMapper;
import com.ddshteam.web.system.service.dao.SysUserInfoMapper;
import com.ddshteam.web.system.service.util.DeptTreeBuilder;
import com.google.common.collect.Lists;
import com.mysql.cj.core.util.StringUtils;

@Service(version = "1.0.0")
@Transactional(rollbackFor = Exception.class)
public class SysDeptServiceImpl implements SysDeptService{

	@Autowired
	private SysDepInfoMapper sysDepInfoDao;
	
	@Autowired
	private SysDeptCustomizeMapper sysDeptCustomizeDao;
	
	@Autowired
	private SysUserInfoMapper SysUserInfoDao;
	
	@Autowired
	private SysUserInfoCustomizeMapper SysUserInfoCustomizeDao;

	@Override
	public List<Tree> getDeptTree(boolean lazy) {
		SysDepInfoCriteria sysDepInfoCriteria=new SysDepInfoCriteria();
		Criteria criteria=sysDepInfoCriteria.createCriteria();
		criteria.andStatusEqualTo(SystemContants.SysDeptStatus.EFFECT);
		List<SysDepInfo> list = sysDepInfoDao.selectByExample(sysDepInfoCriteria);
		//sysDeptCustomizeDao.getDeptList(); older 
		List<Tree> trees = DeptTreeBuilder.build(list, null);
		return trees;
	}

	@Override
	public List<Tree> getDeptTree(String userId, boolean lazy) {
		List<Tree> trees = Lists.newArrayList();
		
		SysUserInfo user =SysUserInfoDao.selectByPrimaryKey(userId);
		//SysUserInfoCustomizeDao.getUserById(userId);
		String deptId = user.getDepId();
		if(StringUtils.isNullOrEmpty(deptId)){
			return trees;
		}
		
		SysDepInfoCriteria sysDepInfoCriteria=new SysDepInfoCriteria();
		Criteria criteria=sysDepInfoCriteria.createCriteria();
		criteria.andStatusEqualTo(SystemContants.SysDeptStatus.EFFECT);
		List<SysDepInfo> list = sysDepInfoDao.selectByExample(sysDepInfoCriteria);
		//sysDeptCustomizeDao.getDeptList();
		trees = DeptTreeBuilder.build(list, deptId);
		return trees;
	}
	
	@Override
	public List<Tree> getChildrenDeptList(String deptId) {
		SysDepInfoCriteria sysDepInfoCriteria=new SysDepInfoCriteria();
		Criteria criteria=sysDepInfoCriteria.createCriteria();
		criteria.andStatusEqualTo(SystemContants.SysDeptStatus.EFFECT);
		criteria.andParentIdEqualTo(deptId);
		List<SysDepInfo> list = sysDepInfoDao.selectByExample(sysDepInfoCriteria);
				//sysDeptCustomizeDao.getChildrenDeptList(deptId);
		List<Tree> trees = DeptTreeBuilder.childrenBuild(list);
		return trees;
	}


	@Deprecated
	@Override
	public List<SysDepInfo> getSysDeptDetailList() {
		SysDepInfoCriteria sysDepInfoCriteria=new SysDepInfoCriteria();
		sysDepInfoCriteria.setOrderByClause(" create_time DESC");
		Criteria criteria=sysDepInfoCriteria.createCriteria();
		criteria.andStatusEqualTo(SystemContants.SysDeptStatus.EFFECT);
		return sysDepInfoDao.selectByExample(sysDepInfoCriteria);
				//sysDeptCustomizeDao.getDeptList();
	}

	@Override
	public SysDepInfo getSysDeptById(String deptId) {
		return sysDepInfoDao.selectByPrimaryKey(deptId);
				//sysDeptCustomizeDao.getSysDeptById(deptId);
	}

	@Override
	public boolean saveDept(SysDepInfo sysDept) {
		sysDept.setId(UUID.randomUUID().toString());
		sysDept.setCreateTime(new Date());
		sysDept.setStatus(SystemContants.SysDeptStatus.EFFECT);
		int result = sysDepInfoDao.insert(sysDept);
				//sysDeptDao.saveDept(sysDept);
		return result > 0;
	}

	@Override
	public boolean saveDept(List<SysDepInfo> sysDepts) {
		int result = sysDeptCustomizeDao.saveDeptList(sysDepts);
		return result > 0;
	}

	@Override
	public boolean updateDept(SysDepInfo sysDept) {
		int result = sysDepInfoDao.updateByPrimaryKeySelective(sysDept);
		   //sysDeptDao.updateDept(sysDept);
		return result > 0;
	}

	@Override
	public boolean deleteDept(String deptId) {
		int result = sysDepInfoDao.deleteByPrimaryKey(deptId);
				//sysDeptDao.deleteDept(deptId);
		return result > 0;
	}

	@Override
	public boolean isDeptHasUser(String deptId) {
		SysUserInfoCriteria sysUserInfoCriteria=new SysUserInfoCriteria();
		com.ddshteam.web.system.service.api.model.SysUserInfoCriteria.Criteria criteria=sysUserInfoCriteria.createCriteria();
		criteria.andDepIdEqualTo(deptId);
		criteria.andStatusEqualTo(SystemContants.SysUserStatus.EFFECT);
		long result = SysUserInfoDao.countByExample(sysUserInfoCriteria);
				//SysUserInfoCustomizeDao.isDeptHasUser(deptId);
		return result > 0;
	}

}
