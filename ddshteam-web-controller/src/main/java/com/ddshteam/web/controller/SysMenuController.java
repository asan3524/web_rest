package com.ddshteam.web.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ddshteam.web.core.base.BaseController;
import com.ddshteam.web.core.support.HttpCode;
import com.ddshteam.web.dto.system.MenuReq;
import com.ddshteam.web.system.service.api.SysMenuService;
import com.ddshteam.web.system.service.api.SysRoleService;
import com.ddshteam.web.system.service.api.SysUserService;
import com.ddshteam.web.system.service.api.data.Tree;
import com.ddshteam.web.system.service.api.model.SysMenu;
import com.ddshteam.web.system.service.api.model.SysRole;
import com.ddshteam.web.system.service.api.model.SysUser;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Function;
import com.google.common.collect.Lists;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "/menu", description = "菜单接口")
@RestController
@RequestMapping(value = "/menu")
public class SysMenuController extends BaseController {
	
	private final static Logger logger = LoggerFactory.getLogger(SysMenuController.class);
	
	@Reference(version = "1.0.0")
	private SysMenuService sysMenuService;
	
	@Reference(version = "1.0.0")
	private SysRoleService sysRoleService;
	
	@Reference(version = "1.0.0")
	private SysUserService sysUserervice;
	
	@ApiOperation(value = "菜单列表", notes = "")
	@GetMapping(value = { "/list" })
	public Object getMenuList(HttpServletRequest request, HttpServletResponse response,
			@PageableDefault(page = 1, size = 10, sort = "createTime,asc") Pageable pageable) {
		logger.debug("SysMenuController.getMenuList()");

		PageInfo<SysMenu> pi = sysMenuService
				.getMenuList(pageable.getPageNumber(), pageable.getPageSize());
		return getResponse(pi);
	}
	
	@ApiOperation(value = "菜单树(不含功能点)", notes = "用于添加菜单时选择父级菜单弹窗")
	@GetMapping(value = { "/tree" })
	public Object getMenuTree(HttpServletRequest request, HttpServletResponse response) {
		logger.debug("SysMenuController.getMenuTree()");

		List<Tree> list = sysMenuService.getMenuTree();
		return getResponse(list);
	}
	
	@ApiOperation(value = "菜单树(包含功能点)", notes = "用于添加角色时需要勾选菜单权限")
	@GetMapping(value = { "/tree2all" })
	public Object getAllMenuTree(HttpServletRequest request, HttpServletResponse response) {
		logger.debug("SysMenuController.getAllMenuTree()");

		List<Tree> list = sysMenuService.getAllMenuTree();
		return getResponse(list);
	}
	
	@ApiOperation(value = "用户带勾选状态的菜单树(包含功能点)", notes = "用于更新角色时查看已勾选的状态树")
	@GetMapping(value = { "/tree2status/{userId}" })
	public Object getAllMenuWithStatusTree(@PathVariable String userId, HttpServletRequest request, HttpServletResponse response) {
		logger.debug("SysMenuController.getAllMenuWithStatusTree()");

		if(StringUtils.isEmpty(userId)) {
			logger.error("userId is null.");
			return getResponse(HttpCode.BAD_REQUEST, false);
		}
		
		List<Tree> list = sysMenuService.getAllMenuTree();
		
		//TODO 此处需待优化效率
		
		SysUser user = sysUserervice.getUserById(userId);
		
		List<String> ids = Lists.transform(user.getRoles(), new Function<SysRole, String>() {
			@Override
			public String apply(SysRole r) {
				return r.getId();
			}
		});
	
		ids = new ArrayList<String>(new HashSet<String>(ids));
		
		List<String> menuIds = sysRoleService.getMenuIdByRole(ids.toArray(new String[ids.size()]));
		
		for (String menuId : menuIds) {
			for (Tree tree : list) {
				if(menuId.equals(tree.getId())) {
					tree.setCheckStatus(1);
					break;
				}else {
					if(null != tree.getChildren() && !tree.getChildren().isEmpty()){
						setTreeCheckStatus(tree.getChildren(), menuId);
					}
				}
			}
		}
		
		return getResponse(list);
	}
	
	private void setTreeCheckStatus(List<Tree> trees, String menuId) {
		for (Tree tree : trees) {
			if(menuId.equals(tree.getId())) {
				tree.setCheckStatus(1);
				break;
			}else {
				if(null != tree.getChildren() && !tree.getChildren().isEmpty()){
					setTreeCheckStatus(tree.getChildren(), menuId);
				}
			}
		}
	}
	
	@ApiOperation(value = "用户左侧菜单树", notes = "当前用户的左侧菜单(type!=3)")
	@GetMapping(value = { "/tree2user/{userId}" })
	public Object getMenuTreeByUser(@PathVariable String userId, HttpServletRequest request, HttpServletResponse response) {
		logger.debug("SysMenuController.getMenuTreeByUser()");

		//TODO 用户id从session中获取
		if(StringUtils.isEmpty(userId)) {
			logger.error("userId is null.");
			return getResponse(HttpCode.BAD_REQUEST, false);
		}
		
		List<Tree> list = sysMenuService.getMenuTreeByUser(userId);
		return getResponse(list);
	}
	
	@ApiOperation(value = "获取菜单详情", notes = "")
	@GetMapping(value = { "/id/{menuId}" })
	public Object getMenuById(@PathVariable String menuId, HttpServletRequest request, HttpServletResponse response) {
		logger.debug("SysMenuController.getMenuById()");

		if(StringUtils.isEmpty(menuId)) {
			logger.error("menuId is null.");
			return getResponse(HttpCode.BAD_REQUEST, false);
		}
		
		SysMenu menu = sysMenuService.getMenuById(menuId);
		return getResponse(menu);
	}
	
	@ApiOperation(value = "添加菜单", notes = "")
	@PostMapping(value = { "/save" })
	public Object saveMenu(@Valid @RequestBody MenuReq menuReq, BindingResult errors) {
		logger.debug("SysMenuController.saveMenu()");

		if (errors.hasErrors()) {
			String msg = errors.getAllErrors().get(0).getDefaultMessage();
			logger.error(msg);
			return getResponse(HttpCode.BAD_REQUEST, false, msg);
		}
		
		SysMenu menu = new SysMenu();
		
		menu.setName(menuReq.getName());
		menu.setParentId(menuReq.getParentId());
		menu.setType(menuReq.getType());
		menu.setUrl(menuReq.getUrl());
		menu.setPerms(menuReq.getPerms());
		menu.setIcon(menuReq.getIcon());
		menu.setOrderNum(menuReq.getOrderNum());
		
		boolean result = sysMenuService.saveMenu(menu);
		if(result) {
			return getResponse(result);	
		}else return getResponse(HttpCode.INTERNAL_SERVER_ERROR, result, "新增菜单失败");
	}
	
	@ApiOperation(value = "更新菜单", notes = "")
	@PutMapping(value = { "/update/{id}" })
	public Object updateMenu(@Valid @RequestBody MenuReq menuReq, @PathVariable String id, BindingResult errors) {
		logger.debug("SysMenuController.updateMenu()");

		if (errors.hasErrors()) {
			String msg = errors.getAllErrors().get(0).getDefaultMessage();
			logger.error(msg);
			return getResponse(HttpCode.BAD_REQUEST, false, msg);
		}
		
		SysMenu menu = new SysMenu();
		menu.setId(id);
		menu.setName(menuReq.getName());
		menu.setParentId(menuReq.getParentId());
		menu.setType(menuReq.getType());
		menu.setUrl(menuReq.getUrl());
		menu.setPerms(menuReq.getPerms());
		menu.setIcon(menuReq.getIcon());
		menu.setOrderNum(menuReq.getOrderNum());
		
		boolean result = sysMenuService.updateMenu(menu);
		if(result) {
			return getResponse(result);	
		}else return getResponse(HttpCode.INTERNAL_SERVER_ERROR, result, "更新菜单失败");
	}
	
	@ApiOperation(value = "删除菜单", notes = "")
	@DeleteMapping(value = { "/delete/{id}" })
	public Object deleteMenu(@PathVariable String id) {
		logger.debug("SysMenuController.deleteMenu()");

		if(StringUtils.isEmpty(id)) {
			logger.error("menuid is null.");
			return getResponse(HttpCode.BAD_REQUEST, false);
		}
		
		boolean result = sysMenuService.deleteMenuById(id);
		if(result) {
			return getResponse(result);	
		}else return getResponse(HttpCode.INTERNAL_SERVER_ERROR, result, "删除菜单失败");
	}
}
