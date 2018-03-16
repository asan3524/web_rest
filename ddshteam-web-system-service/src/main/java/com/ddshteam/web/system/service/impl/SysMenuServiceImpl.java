package com.ddshteam.web.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.ddshteam.web.system.service.api.SysMenuService;
import com.ddshteam.web.system.service.api.data.Tree;
import com.ddshteam.web.system.service.api.model.SysMenuInfo;
import com.ddshteam.web.system.service.api.model.SysMenuInfoCriteria;
import com.ddshteam.web.system.service.dao.SysMenuInfoCustomizeMapper;
import com.ddshteam.web.system.service.dao.SysMenuInfoMapper;
import com.ddshteam.web.system.service.dao.SysRoleToMenuMapper;
import com.ddshteam.web.system.service.util.MenuTreeBuilder;
import com.google.common.base.Splitter;
import com.mysql.cj.core.util.StringUtils;

@Service(version = "1.0.0")
@Transactional(rollbackFor = Exception.class)
public class SysMenuServiceImpl implements SysMenuService {

	@Autowired
	private SysMenuInfoMapper sysMenuInfoDao;

	@Autowired
	private SysRoleToMenuMapper sysRoleToMenuDao;

	@Autowired
	private SysMenuInfoCustomizeMapper sysMenuInfoCustomizeDao;

	@Override
	public List<Tree> getAllMenuTree() {
		SysMenuInfoCriteria criteria = new SysMenuInfoCriteria();
		criteria.setOrderByClause("order_num asc");
		List<SysMenuInfo> list = sysMenuInfoDao.selectByExample(criteria);
		List<Tree> trees = MenuTreeBuilder.build(list);
		return trees;
	}

	@Override
	public List<Tree> getMenuTree() {
		SysMenuInfoCriteria criteria = new SysMenuInfoCriteria();
		criteria.createCriteria().andTypeNotEqualTo(3);
		criteria.setOrderByClause("order_num asc");
		List<SysMenuInfo> list = sysMenuInfoDao.selectByExample(criteria);
		List<Tree> trees = MenuTreeBuilder.build(list);
		return trees;
	}

	@Override
	public List<Tree> getMenuTreeByRole(String roleId) {
		if (StringUtils.isNullOrEmpty(roleId)) {
			return MenuTreeBuilder.convert(sysMenuInfoCustomizeDao.getMenuTree());
		} else {
			return MenuTreeBuilder.convert(sysMenuInfoCustomizeDao.getMenuTreeByRole(roleId));
		}
	}

	@Override
	public List<Tree> getMenuTreeByUser(String userId) {
		// TODO Auto-generated method stub
		List<SysMenuInfo> list = sysMenuInfoCustomizeDao.getMenusByUser(userId);
		List<Tree> trees = MenuTreeBuilder.build(list);
		return trees;
	}

	@Override
	public List<String> getPermissionByUser(String userId) {
		// TODO Auto-generated method stub
		List<String> list = sysMenuInfoCustomizeDao.getPermissionByUser(userId);
		List<String> result = new ArrayList<String>();
		for (String perm : list) {
			if (!StringUtils.isNullOrEmpty(perm) && perm.contains(",")) {
				List<String> temp = Splitter.on(",").trimResults().splitToList(perm);
				result.addAll(temp);
			} else {
				result.add(perm);
			}
		}
		return result;
	}

	@Override
	public List<Tree> getMenuTreeByRole(String userId, String roleId) {
		// TODO Auto-generated method stub
		if (StringUtils.isNullOrEmpty(roleId)) {
			return MenuTreeBuilder.convert(sysMenuInfoCustomizeDao.getMenuTreeByUser(userId));
		} else {
			return MenuTreeBuilder.convert(sysMenuInfoCustomizeDao.getMenuTreeByUserRole(userId, roleId));
		}
	}

	@Override
	public SysMenuInfo getMenuById(String menuId) {
		// TODO Auto-generated method stub
		return sysMenuInfoDao.selectByPrimaryKey(menuId);
	}

	@Override
	public boolean saveMenu(SysMenuInfo sysMenuInfo) {
		// TODO Auto-generated method stub
		int result = sysMenuInfoDao.insert(sysMenuInfo);
		return result > 0;
	}

	@Override
	public boolean updateMenu(SysMenuInfo sysMenuInfo) {
		// TODO Auto-generated method stub
		int result = sysMenuInfoDao.updateByPrimaryKey(sysMenuInfo);
		return result > 0;
	}

	@Override
	@Transactional
	public boolean deleteMenuById(String menuId) {
		// TODO Auto-generated method stub
		int result = sysMenuInfoCustomizeDao.deleteByPrimaryKey(menuId);
		return result > 0;
	}
}
