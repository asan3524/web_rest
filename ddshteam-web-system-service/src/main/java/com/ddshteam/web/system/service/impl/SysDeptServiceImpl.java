package com.ddshteam.web.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.ddshteam.web.system.service.api.SysDeptService;
import com.ddshteam.web.system.service.api.data.Tree;
import com.ddshteam.web.system.service.api.model.SysDept;
import com.ddshteam.web.system.service.api.model.SysUser;
import com.ddshteam.web.system.service.dao.SysDeptDao;
import com.ddshteam.web.system.service.dao.SysUserDao;
import com.ddshteam.web.system.service.util.DeptTreeBuilder;
import com.google.common.collect.Lists;
import com.mysql.cj.core.util.StringUtils;

@Service(version = "1.0.0")
@Transactional(rollbackFor = Exception.class)
public class SysDeptServiceImpl implements SysDeptService{

	@Autowired
	private SysDeptDao sysDeptDao;
	
	@Autowired
	private SysUserDao sysUserDao;

	@Override
	public List<Tree> getDeptTree(boolean lazy) {
		List<SysDept> list = sysDeptDao.getDeptList();
		List<Tree> trees = DeptTreeBuilder.build(list, null);
		return trees;
	}

	@Override
	public List<Tree> getDeptTree(String userId, boolean lazy) {
		List<Tree> trees = Lists.newArrayList();
		
		SysUser user = sysUserDao.getUserById(userId);
		String deptId = user.getDepId();
		if(StringUtils.isNullOrEmpty(deptId)){
			return trees;
		}
		List<SysDept> list = sysDeptDao.getDeptList();
		trees = DeptTreeBuilder.build(list, deptId);
		return trees;
	}
	
	@Override
	public List<Tree> getChildrenDeptList(String deptId) {
		List<SysDept> list = sysDeptDao.getChildrenDeptList(deptId);
		List<Tree> trees = DeptTreeBuilder.childrenBuild(list);
		return trees;
	}


	@Deprecated
	@Override
	public List<SysDept> getSysDeptDetailList() {
		return sysDeptDao.getDeptList();
	}

	@Override
	public SysDept getSysDeptById(String deptId) {
		return sysDeptDao.getSysDeptById(deptId);
	}

	@Override
	public boolean saveDept(SysDept sysDept) {
		int result = sysDeptDao.saveDept(sysDept);
		return result > 0;
	}

	@Override
	public boolean saveDept(List<SysDept> sysDepts) {
		int result = sysDeptDao.saveDeptList(sysDepts);
		return result > 0;
	}

	@Override
	public boolean updateDept(SysDept sysDept) {
		int result = sysDeptDao.updateDept(sysDept);
		return result > 0;
	}

	@Override
	public boolean deleteDept(String deptId) {
		
		int result = sysDeptDao.deleteDept(deptId);
		return result > 0;
	}

	@Override
	public boolean isDeptHasUser(String deptId) {
		int result = sysDeptDao.isDeptHasUser(deptId);
		return result > 0;
	}

}
