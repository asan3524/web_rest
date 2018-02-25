package com.ddshteam.web.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.ddshteam.web.system.service.api.SysMenuService;
import com.ddshteam.web.system.service.api.data.Tree;
import com.ddshteam.web.system.service.api.model.SysMenu;
import com.ddshteam.web.system.service.dao.SysMenuDao;
import com.ddshteam.web.system.service.util.MenuTreeBuilder;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.mysql.cj.core.util.StringUtils;

@Service(version = "1.0.0")
@Transactional(rollbackFor = Exception.class)
public class SysMenuServiceImpl implements SysMenuService{

	@Autowired
	private SysMenuDao sysMenuDao;

	@Override
	public PageInfo<SysMenu> getMenuList(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<SysMenu> list = sysMenuDao.getMenuList();
		PageInfo<SysMenu> pageInfo = new PageInfo<SysMenu>(list, 10);
		return pageInfo;
	}

	@Override
	public List<Tree> getMenuTree() {
		//{id,name,url,iconClass,children}
		List<SysMenu> list = sysMenuDao.getMenu$NoFuncList();
		List<Tree> trees = MenuTreeBuilder.build(list);
		return trees;
	}
	
	@Override
	public List<Tree> getAllMenuTree() {
		//{id,name,url,iconClass,children}
		List<SysMenu> list = sysMenuDao.getMenuList();
		List<Tree> trees = MenuTreeBuilder.build(list);
		return trees;
	}

	@Override
	public List<Tree> getMenuTreeByUser(String userId) {
		
//		SysUser user = sysUserDao.getUserById(userId);
//		
//		List<String> ids = Lists.transform(user.getRoles(), new Function<SysRole, String>() {
//			@Override
//			public String apply(SysRole r) {
//				return r.getId();
//			}
//		});
//		
//		ids = new ArrayList<String>(new HashSet<String>(ids));
//		
//		List<String> menuIds = sysRoleDao.getMenuIdByRole(ids.toArray(new String[ids.size()]));
//		
//		List<SysMenu> menuList = sysMenuDao.getMenuByIds(menuIds.toArray(new String[menuIds.size()]));
//		
//		List<SysMenu> result = (List<SysMenu>) Collections2.filter(menuList, x -> !x.getType().equals("3"));
		
		//TODO 过滤type=3， 合并SQL
		List<SysMenu> result = sysMenuDao.getMenus$NoFuncByUser(userId);
		List<Tree> trees = MenuTreeBuilder.build(result);
		return trees;
	}
	

	@Override
	public List<String> getPermissionByUser(String userId) {
		List<String> list = sysMenuDao.getPermissionByUser(userId);
		List<String> result = Lists.newArrayList();
		for (String perm : list) {
			if(!StringUtils.isNullOrEmpty(perm) && perm.contains(",")) {
				List<String> temp = Splitter.on(",").trimResults().splitToList(perm);
				result.addAll(temp);
			}else{
				result.add(perm);
			}
		}
		return result;
	}

	@Override
	public List<Tree> getMenuTreeByRole(String roleId) {
		// TODO 暂时不需要
		return null;
	}

	@Override
	public SysMenu getMenuById(String menuId) {
		return sysMenuDao.getMenuById(menuId);
	}

	@Override
	public boolean saveMenu(SysMenu sysMenu) {
		int result = sysMenuDao.saveMenu(sysMenu);
		return result > 0;
	}

	@Override
	public boolean updateMenu(SysMenu sysMenu) {
		int result = sysMenuDao.updateMenu(sysMenu);
		return result > 0;
	}

	@Override
	public boolean deleteMenuById(String menuId) {
		int result = sysMenuDao.deleteMenuById(menuId);
		return result > 0;
	}

	@Override
	public List<SysMenu> getMenuByIds(String... menuIds) {
		return sysMenuDao.getMenuByIds(menuIds);
	}

}
