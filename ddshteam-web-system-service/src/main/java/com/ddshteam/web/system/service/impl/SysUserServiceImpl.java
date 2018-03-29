package com.ddshteam.web.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.ddshteam.web.system.service.api.SysUserService;
import com.ddshteam.web.system.service.api.constant.SystemContants;
import com.ddshteam.web.system.service.api.data.SysUserInfoResp;
import com.ddshteam.web.system.service.api.model.SysRoleToUserCriteria;
import com.ddshteam.web.system.service.api.model.SysUserInfo;
import com.ddshteam.web.system.service.api.model.SysUserInfoCriteria;
import com.ddshteam.web.system.service.api.model.SysUserInfoCriteria.Criteria;
import com.ddshteam.web.system.service.dao.SysRoleToUserCustomizeMapper;
import com.ddshteam.web.system.service.dao.SysRoleToUserMapper;
import com.ddshteam.web.system.service.dao.SysUserInfoCustomizeMapper;
import com.ddshteam.web.system.service.dao.SysUserInfoMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service(version = "1.0.0")
@Transactional(rollbackFor = Exception.class)
public class SysUserServiceImpl implements SysUserService {

	@Autowired
	private SysUserInfoMapper sysUserInfoInfoDao;

	@Autowired
	private SysUserInfoCustomizeMapper sysUserInfoInfoCustomizeDao;

	@Autowired
	private SysRoleToUserMapper sysRoleToUserDao;

	@Autowired
	private SysRoleToUserCustomizeMapper sysRoleToUserCustomizeDao;

	@Override
	public PageInfo<SysUserInfoResp> getUserList(int pageNum, int pageSize, String name, String[] depIds) {
		PageHelper.startPage(pageNum, pageSize);
		/*
		 * SysUserInfoCriteria sysUserInfoCriteria=new SysUserInfoCriteria();
		 * sysUserInfoCriteria.
		 * setOrderByClause(" order_num desc,create_time desc"); Criteria
		 * criteria=sysUserInfoCriteria.createCriteria();
		 * 
		 * if(name!=null&&!name.equals("")) { criteria.andNameEqualTo(name); }
		 * if(depId!=null&&!depId.equals("")) { criteria.andDepIdEqualTo(depId);
		 * }
		 * 
		 * criteria.andStatusEqualTo(SystemContants.SysUserStatus.EFFECT);
		 * criteria.andIsBuiltinEqualTo(SystemContants.SysUserIsBuiltin.
		 * NOT_BUILTIN); List<SysUserInfo>
		 * sysUserInfos=SysUserInfoInfoDao.selectByExample(sysUserInfoCriteria);
		 */

		List<SysUserInfoResp> sysUserInfos = sysUserInfoInfoCustomizeDao.getUserList(name, depIds);

		PageInfo<SysUserInfoResp> pageInfo = new PageInfo<SysUserInfoResp>(sysUserInfos, pageSize);
		return pageInfo;
	}

	@Override
	public SysUserInfo getUserByAccount(String account) {
		SysUserInfoCriteria sysUserInfoCriteria = new SysUserInfoCriteria();
		Criteria criteria = sysUserInfoCriteria.createCriteria();
		criteria.andAccountEqualTo(account);
		criteria.andStatusEqualTo(SystemContants.SysUserStatus.EFFECT);
		List<SysUserInfo> sysUserInfos = sysUserInfoInfoDao.selectByExample(sysUserInfoCriteria);
		return sysUserInfos.size() > 0 ? sysUserInfos.get(0) : null;
		// return SysUserInfoInfoCustomizeDao.getUserByAccount(account);
	}

	@Override
	public SysUserInfoResp getUserById(String userId) {
		/*
		 * SysUserInfoResp SysUserInfo =
		 * SysUserInfoInfoDao.selectByPrimaryKey(userId); return SysUserInfo;
		 */
		return sysUserInfoInfoCustomizeDao.getUserById(userId);

	}

	@Override
	public boolean saveUser(SysUserInfo SysUserInfo, String... roleIds) {
		int result = sysUserInfoInfoDao.insert(SysUserInfo);
		result = sysRoleToUserCustomizeDao.insertRoleToUsers(SysUserInfo.getId(), roleIds);
		// saveUser(SysUserInfo,SysUserInfo.getId(), roleIds);
		return result > 0;
	}

	@Override
	public boolean updateUser(SysUserInfo SysUserInfo, String... roleIds) {
		int result = sysUserInfoInfoDao.updateByPrimaryKeySelective(SysUserInfo);

		if(result>0&&roleIds!=null&&roleIds.length>0)
		{
			SysRoleToUserCriteria sysRoleToUserCriteria = new SysRoleToUserCriteria();
			com.ddshteam.web.system.service.api.model.SysRoleToUserCriteria.Criteria criteria = sysRoleToUserCriteria
					.createCriteria();
			criteria.andUserIdEqualTo(SysUserInfo.getId());
			sysRoleToUserDao.deleteByExample(sysRoleToUserCriteria);

			sysRoleToUserCustomizeDao.insertRoleToUsers(SysUserInfo.getId(), roleIds);
		}

		// SysUserInfoInfoCustomizeDao.updateUser(SysUserInfo, roleIds);
		return result > 0;
	}

	@Override
	public boolean updatePassword(String userId, String newPassword) {
		SysUserInfo record = new SysUserInfo();
		record.setId(userId);
		record.setPassword(newPassword);
		int result = sysUserInfoInfoDao.updateByPrimaryKeySelective(record);
		return result > 0;
	}

	@Override
	public boolean deleteUser(SysUserInfo sysUserInfo) {
		SysRoleToUserCriteria sysRoleToUserCriteria = new SysRoleToUserCriteria();
		com.ddshteam.web.system.service.api.model.SysRoleToUserCriteria.Criteria criteria = sysRoleToUserCriteria
				.createCriteria();
		criteria.andUserIdEqualTo(sysUserInfo.getId());
		long result = sysRoleToUserDao.deleteByExample(sysRoleToUserCriteria);

		sysUserInfo.setStatus(SystemContants.SysUserStatus.LOSE_EFFECT);
		sysUserInfo.setAccount(sysUserInfo.getAccount() + "_" + System.currentTimeMillis());
		if (sysUserInfo.getIsBuiltin() == SystemContants.SysUserIsBuiltin.BUILTIN) {
			return false;
		}
		result = sysUserInfoInfoDao.updateByPrimaryKeySelective(sysUserInfo);

		return result > 0;
	}

	@Override
	public boolean saveUser(SysUserInfo SysUserInfo) {
		int result = sysUserInfoInfoDao.insert(SysUserInfo);
		return result > 0;
	}

	@Override
	public boolean saveUser(List<SysUserInfo> SysUserInfos) {
		// TODO
		return false;
	}

	@Override
	public boolean updateUser(SysUserInfo SysUserInfo) {
		int result = sysUserInfoInfoDao.updateByPrimaryKeySelective(SysUserInfo);
		return result > 0;
	}

	@Override
	public boolean setUserRole(String userId, String... roleIds) {
		SysRoleToUserCriteria sysRoleToUserCriteria = new SysRoleToUserCriteria();
		com.ddshteam.web.system.service.api.model.SysRoleToUserCriteria.Criteria criteria = sysRoleToUserCriteria
				.createCriteria();
		criteria.andUserIdEqualTo(userId);
		int result = sysRoleToUserDao.deleteByExample(sysRoleToUserCriteria);

		sysUserInfoInfoCustomizeDao.setUserRole(userId, roleIds);

		return result > 0;
	}

	@Override
	public boolean getUserExist(String account) {
		SysUserInfoCriteria sysUserInfoCriteria = new SysUserInfoCriteria();
		com.ddshteam.web.system.service.api.model.SysUserInfoCriteria.Criteria criteria = sysUserInfoCriteria
				.createCriteria();
		criteria.andAccountEqualTo(account);
		long result = sysUserInfoInfoDao.countByExample(sysUserInfoCriteria);
		return result > 0;
	}

	@Override
	public boolean deleteUsers(List<SysUserInfo> sysUserInfo) {
		boolean result = false;
		for (SysUserInfo userinfo : sysUserInfo) {
			result = deleteUser(userinfo);
		}
		return result;
	}

	@Override
	public List<SysUserInfo> getUsersByUserid(List<String> userids) {
		SysUserInfoCriteria sysUserInfoCriteria = new SysUserInfoCriteria();
		com.ddshteam.web.system.service.api.model.SysUserInfoCriteria.Criteria criteria = sysUserInfoCriteria
				.createCriteria();
		criteria.andIdIn(userids);
		return sysUserInfoInfoDao.selectByExample(sysUserInfoCriteria);
	}

}
