package com.ddshteam.web.controller.system;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.ddshteam.web.core.util.IdUtil;
import com.ddshteam.web.dto.system.MenuReq;
import com.ddshteam.web.system.service.api.SysMenuService;
import com.ddshteam.web.system.service.api.constant.SystemContants;
import com.ddshteam.web.system.service.api.data.Tree;
import com.ddshteam.web.system.service.api.model.SysMenuInfo;
import com.ddshteam.web.system.service.api.model.SysUserInfo;

@Api(value = "/system/menu", description = "菜单接口")
@RestController
@RequestMapping(value = "/system/menu")
public class SysMenuController extends BaseController {

	private final static Logger logger = LoggerFactory.getLogger(SysMenuController.class);

	@Reference(version = "1.0.0")
	private SysMenuService sysMenuService;

	@ApiOperation(value = "菜单树", notes = "用于菜单管理树展示")
	@GetMapping(value = { "/tree" })
	@RequiresPermissions(SystemContants.Permission.PERMISSION_MENU_TREE)
	public Object getMenuTree(HttpServletRequest request, HttpServletResponse response) {
		logger.debug("SysMenuController.getMenuTree()");

		Subject subject = SecurityUtils.getSubject();
		SysUserInfo user = (SysUserInfo) subject.getPrincipals().getPrimaryPrincipal();

		if (StringUtils.isEmpty(user)) {
			return getResponse(HttpCode.UNAUTHORIZED, false);
		}

		List<Tree> list = null;
		if (user.getIsBuiltin()) {
			list = sysMenuService.getAllMenuTree();
		} else {
			list = sysMenuService.getMenuTreeByUser(user.getId());
		}
		return getResponse(list);
	}

	@ApiOperation(value = "用户带勾选状态的菜单树(包含功能点)", notes = "用于新增角色时查看已勾选的状态树（所有状态未勾选）")
	@GetMapping(value = { "/tree2status" })
	@RequiresPermissions(SystemContants.Permission.PERMISSION_MENU_TREE2STATUS)
	public Object getMenuWithStatusTree(HttpServletRequest request, HttpServletResponse response) {
		logger.debug("SysMenuController.getMenuWithStatusTree()");

		Subject subject = SecurityUtils.getSubject();
		SysUserInfo user = (SysUserInfo) subject.getPrincipals().getPrimaryPrincipal();

		if (StringUtils.isEmpty(user)) {
			return getResponse(HttpCode.UNAUTHORIZED, false);
		}

		List<Tree> list = null;
		if (user.getIsBuiltin()) {
			list = sysMenuService.getMenuTreeByRole(null);
		} else {
			list = sysMenuService.getMenuTreeByRole(user.getId(), null);
		}
		return getResponse(list);
	}

	@ApiOperation(value = "用户带勾选状态的菜单树(包含功能点)", notes = "用于更新角色时查看已勾选的状态树,返回指定role的勾选状态")
	@GetMapping(value = { "/tree2status/{roleId}" })
	@RequiresPermissions(SystemContants.Permission.PERMISSION_MENU_TREE2STATUS)
	public Object getMenuWithStatusTree(@PathVariable String roleId, HttpServletRequest request,
			HttpServletResponse response) {
		logger.debug("SysMenuController.getMenuWithStatusTree()");

		if (StringUtils.isEmpty(roleId)) {
			return getResponse(HttpCode.BAD_REQUEST, false, "roleId为空");
		}

		Subject subject = SecurityUtils.getSubject();
		SysUserInfo user = (SysUserInfo) subject.getPrincipals().getPrimaryPrincipal();

		if (StringUtils.isEmpty(user)) {
			return getResponse(HttpCode.UNAUTHORIZED, false);
		}

		List<Tree> list = null;
		if (user.getIsBuiltin()) {
			list = sysMenuService.getMenuTreeByRole(roleId);
		} else {
			list = sysMenuService.getMenuTreeByRole(user.getId(), roleId);
		}
		return getResponse(list);
	}

	@ApiOperation(value = "用户左侧菜单树", notes = "当前用户的左侧菜单(type!=3)")
	@GetMapping(value = { "/tree2user" })
	@RequiresPermissions(SystemContants.Permission.PERMISSION_MENU_TREE2USER)
	public Object getMenuTreeByUser(HttpServletRequest request, HttpServletResponse response) {
		logger.debug("SysMenuController.getMenuTreeByUser()");

		Subject subject = SecurityUtils.getSubject();
		SysUserInfo user = (SysUserInfo) subject.getPrincipals().getPrimaryPrincipal();

		if (StringUtils.isEmpty(user)) {
			return getResponse(HttpCode.UNAUTHORIZED, false);
		}

		List<Tree> list = null;
		if (user.getIsBuiltin()) {
			list = sysMenuService.getMenuTree();
		} else {
			list = sysMenuService.getMenuTreeByUser(user.getId());
		}
		return getResponse(list);
	}

	@ApiOperation(value = "获取菜单详情", notes = "")
	@GetMapping(value = { "/id/{menuId}" })
	@RequiresPermissions(SystemContants.Permission.PERMISSION_MENU_INFO)
	public Object getMenuById(@PathVariable String menuId, HttpServletRequest request, HttpServletResponse response) {
		logger.debug("SysMenuController.getMenuById()");

		if (StringUtils.isEmpty(menuId)) {
			logger.error("menuId is null.");
			return getResponse(HttpCode.BAD_REQUEST, false);
		}

		SysMenuInfo menu = sysMenuService.getMenuById(menuId);
		return getResponse(menu);
	}

	@ApiOperation(value = "添加菜单", notes = "")
	@PostMapping(value = { "/save" })
	@RequiresPermissions(SystemContants.Permission.PERMISSION_MENU_SAVE)
	public Object saveMenu(@Valid @RequestBody MenuReq menuReq, BindingResult errors) {
		logger.debug("SysMenuController.saveMenu()");

		if (errors.hasErrors()) {
			String msg = errors.getAllErrors().get(0).getDefaultMessage();
			logger.error(msg);
			return getResponse(HttpCode.BAD_REQUEST, false, msg);
		}

		SysMenuInfo menu = new SysMenuInfo();

		menu.setId(IdUtil.generateId().toString());
		menu.setName(menuReq.getName());
		menu.setParentId(menuReq.getParentId());
		menu.setType(menuReq.getType());
		menu.setUrl(menuReq.getUrl());
		menu.setPerms(menuReq.getPerms());
		menu.setIcon(menuReq.getIcon());
		menu.setOrderNum(menuReq.getOrderNum());

		boolean result = sysMenuService.saveMenu(menu);
		if (result) {
			return getResponse(result);
		} else
			return getResponse(HttpCode.INTERNAL_SERVER_ERROR, result, "新增菜单失败");
	}

	@ApiOperation(value = "更新菜单", notes = "")
	@PutMapping(value = { "/update/{id}" })
	@RequiresPermissions(SystemContants.Permission.PERMISSION_MENU_UPDATE)
	public Object updateMenu(@Valid @RequestBody MenuReq menuReq, @PathVariable String id, BindingResult errors) {
		logger.debug("SysMenuController.updateMenu()");

		if (errors.hasErrors()) {
			String msg = errors.getAllErrors().get(0).getDefaultMessage();
			logger.error(msg);
			return getResponse(HttpCode.BAD_REQUEST, false, msg);
		}

		SysMenuInfo menu = new SysMenuInfo();
		menu.setId(id);
		menu.setName(menuReq.getName());
		menu.setParentId(menuReq.getParentId());
		menu.setType(menuReq.getType());
		menu.setUrl(menuReq.getUrl());
		menu.setPerms(menuReq.getPerms());
		menu.setIcon(menuReq.getIcon());
		menu.setOrderNum(menuReq.getOrderNum());

		boolean result = sysMenuService.updateMenu(menu);
		if (result) {
			return getResponse(result);
		} else
			return getResponse(HttpCode.INTERNAL_SERVER_ERROR, result, "更新菜单失败");
	}

	@ApiOperation(value = "删除菜单", notes = "")
	@DeleteMapping(value = { "/delete/{id}" })
	@RequiresPermissions(SystemContants.Permission.PERMISSION_MENU_DELETE)
	public Object deleteMenu(@PathVariable String id) {
		logger.debug("SysMenuController.deleteMenu()");

		if (StringUtils.isEmpty(id)) {
			logger.error("menuid is null.");
			return getResponse(HttpCode.BAD_REQUEST, false);
		}

		boolean result = sysMenuService.deleteMenuById(id);
		if (result) {
			return getResponse(result);
		} else
			return getResponse(HttpCode.INTERNAL_SERVER_ERROR, result, "删除菜单失败");
	}
}
