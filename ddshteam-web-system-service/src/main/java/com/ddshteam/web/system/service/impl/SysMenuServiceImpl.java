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
import com.ddshteam.web.system.service.api.model.SysRoleToMenuCriteria;
import com.ddshteam.web.system.service.dao.SysMenuInfoCustomizeMapper;
import com.ddshteam.web.system.service.dao.SysMenuInfoMapper;
import com.ddshteam.web.system.service.dao.SysRoleToMenuMapper;
import com.ddshteam.web.system.service.util.MenuTreeBuilder;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
	public PageInfo<SysMenuInfo> getMenuList(int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNum, pageSize);
		PageInfo<SysMenuInfo> pageInfo = new PageInfo<SysMenuInfo>(sysMenuInfoDao.selectByExample(null));
		return pageInfo;
	}

	@Override
	public List<Tree> getMenuTree() {
		// TODO Auto-generated method stub
		SysMenuInfoCriteria criteria = new SysMenuInfoCriteria();
		criteria.createCriteria().andTypeNotEqualTo(3);
		List<SysMenuInfo> list = sysMenuInfoDao.selectByExample(criteria);
		List<Tree> trees = MenuTreeBuilder.build(list);
		return trees;
	}

	@Override
	public List<Tree> getAllMenuTree() {
		// TODO Auto-generated method stub
		List<SysMenuInfo> list = sysMenuInfoDao.selectByExample(null);
		List<Tree> trees = MenuTreeBuilder.build(list);
		return trees;
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
	public List<Tree> getMenuTreeByRole(String roleId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SysMenuInfo getMenuById(String menuId) {
		// TODO Auto-generated method stub
		return sysMenuInfoDao.selectByPrimaryKey(menuId);
	}

	@Override
	public List<SysMenuInfo> getMenuByIds(String... menuIds) {
		// TODO Auto-generated method stub
		return null;
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
		int result = sysMenuInfoDao.deleteByPrimaryKey(menuId);

		SysRoleToMenuCriteria criteria = new SysRoleToMenuCriteria();
		criteria.createCriteria().andMenuIdEqualTo(menuId);
		result = sysRoleToMenuDao.deleteByExample(criteria);
		return result > 0;
	}
}
