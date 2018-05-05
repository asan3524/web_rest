package com.ddshteam.web.system.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.ddshteam.web.system.service.api.SysDeptService;
import com.ddshteam.web.system.service.api.constant.SystemContants;
import com.ddshteam.web.system.service.api.data.DeptInfoResp;
import com.ddshteam.web.system.service.api.data.Tree;
import com.ddshteam.web.system.service.api.model.SysDepInfo;
import com.ddshteam.web.system.service.api.model.SysDepInfoCriteria;
import com.ddshteam.web.system.service.api.model.SysDepInfoCriteria.Criteria;
import com.ddshteam.web.system.service.api.model.SysDeptypeInfo;
import com.ddshteam.web.system.service.api.model.SysDeptypeInfoCriteria;
import com.ddshteam.web.system.service.api.model.SysUserInfo;
import com.ddshteam.web.system.service.api.model.SysUserInfoCriteria;
import com.ddshteam.web.system.service.dao.SysDepInfoMapper;
import com.ddshteam.web.system.service.dao.SysDeptCustomizeMapper;
import com.ddshteam.web.system.service.dao.SysDeptypeInfoMapper;
import com.ddshteam.web.system.service.dao.SysUserInfoCustomizeMapper;
import com.ddshteam.web.system.service.dao.SysUserInfoMapper;
import com.ddshteam.web.system.service.util.Coder;
import com.ddshteam.web.system.service.util.DeptTreeBuilder;
import com.google.common.collect.Lists;
import com.mysql.cj.core.util.StringUtils;

@Service(version = "1.0.0")
@Transactional(rollbackFor = Exception.class)
public class SysDeptServiceImpl implements SysDeptService {

	@Autowired
	private SysDepInfoMapper sysDepInfoDao;

	@Autowired
	private SysDeptCustomizeMapper sysDeptCustomizeDao;

	@Autowired
	private SysUserInfoMapper SysUserInfoDao;

	@Autowired
	private SysUserInfoCustomizeMapper SysUserInfoCustomizeDao;

	@Autowired
	private SysDeptypeInfoMapper sysDeptypeInfoDao;

	@Override
	public List<Tree> getDeptTree(boolean lazy) {
		SysDepInfoCriteria sysDepInfoCriteria = new SysDepInfoCriteria();
		Criteria criteria = sysDepInfoCriteria.createCriteria();
		criteria.andStatusEqualTo(SystemContants.SysDeptStatus.EFFECT);
		List<SysDepInfo> list = sysDepInfoDao.selectByExample(sysDepInfoCriteria);
		// sysDeptCustomizeDao.getDeptList(); older
		List<Tree> trees = DeptTreeBuilder.build(list, null);
		return trees;
	}

	@Override
	public List<Tree> getDeptTree(String userId, boolean lazy) {
		List<Tree> trees = Lists.newArrayList();

		SysUserInfo user = SysUserInfoDao.selectByPrimaryKey(userId);
		// SysUserInfoCustomizeDao.getUserById(userId);
		String deptId = user.getDepId();
		if (StringUtils.isNullOrEmpty(deptId)) {
			return trees;
		}

		SysDepInfoCriteria sysDepInfoCriteria = new SysDepInfoCriteria();
		Criteria criteria = sysDepInfoCriteria.createCriteria();
		criteria.andStatusEqualTo(SystemContants.SysDeptStatus.EFFECT);
		List<SysDepInfo> list = sysDepInfoDao.selectByExample(sysDepInfoCriteria);
		// sysDeptCustomizeDao.getDeptList();
		trees = DeptTreeBuilder.build(list, deptId);
		return trees;
	}

	@Override
	public List<Tree> getChildrenDeptList(String deptId,String userdepid) {
		/*
		 * SysDepInfoCriteria sysDepInfoCriteria=new SysDepInfoCriteria();
		 * Criteria criteria=sysDepInfoCriteria.createCriteria();
		 * criteria.andStatusEqualTo(SystemContants.SysDeptStatus.EFFECT);
		 * criteria.andParentIdEqualTo(deptId); List<SysDepInfo> list =
		 * sysDepInfoDao.selectByExample(sysDepInfoCriteria); List<Tree> trees =
		 * DeptTreeBuilder.childrenBuild(list); return trees;
		 */
		// sysDeptCustomizeDao.getChildrenDeptList(deptId);
		
		if(deptId==null)
		{
			return sysDeptCustomizeDao.selectTreeByUserDefault(userdepid);
		}
		else
		{
			return sysDeptCustomizeDao.selectTreeByPrimaryKey(deptId);
		}
		
	}

	@Override
	public List<DeptInfoResp> getSysDeptDetailList() {
		SysDepInfoCriteria sysDepInfoCriteria = new SysDepInfoCriteria();
		sysDepInfoCriteria.setOrderByClause(" sdi.create_time DESC");
		Criteria criteria = sysDepInfoCriteria.createCriteria();
		criteria.andStatusEqualTo(SystemContants.SysDeptStatus.EFFECT);
		return sysDeptCustomizeDao.selectByExample(sysDepInfoCriteria);
		// sysDeptCustomizeDao.getDeptList();
	}

	@Override
	public DeptInfoResp getSysDeptById(String deptId) {
		return sysDeptCustomizeDao.selectByPrimaryKey(deptId);
		// sysDeptCustomizeDao.getSysDeptById(deptId);
	}

	@Override
	public boolean saveDept(SysDepInfo sysDept) {
		StringBuilder basepath = new StringBuilder();
		if (sysDept.getParentId() != null && !sysDept.getParentId().trim().equals("")) {
			basepath.append(sysDepInfoDao.selectByPrimaryKey(sysDept.getParentId()).getPath());
		}
		
		sysDept.setCreateTime(new Date());
		sysDept.setStatus(SystemContants.SysDeptStatus.EFFECT);
		basepath.append(SystemContants.Symbol.RIGHT_DIAGONAL).append(sysDept.getId());
		sysDept.setPath(basepath.toString());
		
		int result = sysDepInfoDao.insert(sysDept);

		return result > 0;
	}

	@Override
	public boolean saveDept(List<SysDepInfo> sysDepts) {
		
		for(SysDepInfo sysDept:sysDepts)
		{
			StringBuilder basepath = new StringBuilder();
			if (sysDept.getParentId() != null || !sysDept.getParentId().trim().equals("")) {
				basepath.append(sysDepInfoDao.selectByPrimaryKey(sysDept.getParentId()).getPath());
			}
			sysDept.setCreateTime(new Date());
			sysDept.setStatus(SystemContants.SysDeptStatus.EFFECT);
			basepath.append(SystemContants.Symbol.RIGHT_DIAGONAL).append(sysDept.getId());
			sysDept.setPath(basepath.toString());
		}
		
		
		int result = sysDeptCustomizeDao.saveDeptList(sysDepts);
		return result > 0;
	}

	@Override
	public boolean updateDept(SysDepInfo sysDept) {
		int result = sysDepInfoDao.updateByPrimaryKeySelective(sysDept);
		// sysDeptDao.updateDept(sysDept);
		return result > 0;
	}

	@Override
	public boolean deleteDept(String deptId) {
		// sysDepInfoDao.deleteByPrimaryKey(deptId);

		SysUserInfo sysUserInfo = new SysUserInfo();
		sysUserInfo.setDepId("");
		SysUserInfoCriteria sysUserInfoCriteria = new SysUserInfoCriteria();
		com.ddshteam.web.system.service.api.model.SysUserInfoCriteria.Criteria criteria = sysUserInfoCriteria
				.createCriteria();
		criteria.andDepIdEqualTo(deptId);
		SysUserInfoDao.updateByExampleSelective(sysUserInfo, sysUserInfoCriteria);

		sysDeptCustomizeDao.deleteDept(deptId);

		return true;
	}

	@Override
	public boolean isDeptHasUser(String deptId) {
		SysUserInfoCriteria sysUserInfoCriteria = new SysUserInfoCriteria();
		com.ddshteam.web.system.service.api.model.SysUserInfoCriteria.Criteria criteria = sysUserInfoCriteria
				.createCriteria();
		criteria.andDepIdEqualTo(deptId);
		criteria.andStatusEqualTo(SystemContants.SysUserStatus.EFFECT);
		long result = SysUserInfoDao.countByExample(sysUserInfoCriteria);
		// SysUserInfoCustomizeDao.isDeptHasUser(deptId);
		return result > 0;
	}

	@Override
	public boolean saveType(SysDeptypeInfo typeinfo) {
		typeinfo.setCode(Coder.getCode(typeinfo.getName(), typeinfo.getRemark()));
		int result = sysDeptypeInfoDao.insert(typeinfo);
		return result > 0;
	}

	@Override
	public boolean updateType(SysDeptypeInfo typeinfo) {
		typeinfo.setCode(Coder.getCode(typeinfo.getName(), typeinfo.getRemark()));
		int result = sysDeptypeInfoDao.updateByPrimaryKeySelective(typeinfo);
		return result > 0;
	}

	@Override
	public SysDeptypeInfo getTypeinfoById(String depttypeid) {
		return sysDeptypeInfoDao.selectByPrimaryKey(depttypeid);
	}

	@Override
	public boolean deleteTypeByid(String typeid) {
		int result = sysDeptypeInfoDao.deleteByPrimaryKey(typeid);
		return result > 0;
	}

	@Override
	public List<SysDeptypeInfo> ListType() {
		SysDeptypeInfoCriteria sysDeptypeInfoCriteria = new SysDeptypeInfoCriteria();
		com.ddshteam.web.system.service.api.model.SysDeptypeInfoCriteria.Criteria criteria = sysDeptypeInfoCriteria
				.createCriteria();
		criteria.andIdIsNotNull();
		return sysDeptypeInfoDao.selectByExample(sysDeptypeInfoCriteria);
	}

}
