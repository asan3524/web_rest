package com.ddshteam.web.controller.system;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
import com.ddshteam.web.core.util.IdUtil;
import com.ddshteam.web.dto.system.RoleReq;
import com.ddshteam.web.dto.system.SetRole2MenuReq;
import com.ddshteam.web.system.service.api.SysRoleService;
import com.ddshteam.web.system.service.api.constant.SystemContants;
import com.ddshteam.web.system.service.api.data.RoleListReq;
import com.ddshteam.web.system.service.api.model.SysRoleInfo;
import com.github.pagehelper.PageInfo;

@Api(value = "/system/role", description = "角色接口-完成后端测试")
@RestController
@RequestMapping(value = "/system/role")
public class SysRoleController extends BaseController {

	private final static Logger logger = LoggerFactory.getLogger(SysRoleController.class);

	@Reference(version = "1.0.0")
	private SysRoleService sysRoleService;

	@ApiOperation(value = "角色列表", notes = "")
	@PostMapping(value = { "/list" })
	@RequiresPermissions(logical = Logical.OR, value = {SystemContants.Permission.PERMISSION_MENU_TREE2USER,SystemContants.Permission.PERMISSION_ROLE_LIST})
	public Object getRoleList(@RequestBody RoleListReq req,
			@PageableDefault(page = 1, size = 10, sort = "createTime,asc") Pageable pageable, BindingResult errors) {
		logger.debug("SysRoleController.getRoleList()");
		if (errors.hasErrors()) {
			String msg = errors.getAllErrors().get(0).getDefaultMessage();
			logger.error(msg);
			return getResponse(HttpCode.BAD_REQUEST, false, msg);
		}
		PageInfo<SysRoleInfo> pi = sysRoleService.getRoleList(pageable.getPageNumber(), pageable.getPageSize(),req);
		return getResponse(pi);
	}

	@ApiOperation(value = "获取角色详情", notes = "")
	@GetMapping(value = { "/id/{roleId}" })
	@RequiresPermissions(logical = Logical.OR, value = {SystemContants.Permission.PERMISSION_MENU_TREE2USER,SystemContants.Permission.PERMISSION_ROLE_INFO})
	public Object getRoleById(@PathVariable("roleId") String roleId, HttpServletRequest request, HttpServletResponse response) {
		logger.debug("SysRoleController.getRoleById()");

		if (StringUtils.isEmpty(roleId)) {
			logger.error("roleId is null.");
			return getResponse(HttpCode.BAD_REQUEST, false);
		}

		SysRoleInfo role = sysRoleService.getRoleById(roleId);
		return getResponse(role);
	}

	@ApiOperation(value = "添加角色", notes = "")
	@PostMapping(value = { "/save" })
	@RequiresPermissions(logical = Logical.OR, value = {SystemContants.Permission.PERMISSION_MENU_TREE2USER,SystemContants.Permission.PERMISSION_ROLE_SAVE})
	public Object saveRole(@Valid @RequestBody RoleReq roleReq, BindingResult errors) {
		logger.debug("SysRoleController.saveRole()");

		if (errors.hasErrors()) {
			String msg = errors.getAllErrors().get(0).getDefaultMessage();
			logger.error(msg);
			return getResponse(HttpCode.BAD_REQUEST, false, msg);
		}

		SysRoleInfo role = new SysRoleInfo();
		role.setId(IdUtil.generateId().toString());
		role.setName(roleReq.getName());
		role.setRemark(roleReq.getRemark());

		boolean result = false;

		if (roleReq.getMenuids() != null && !roleReq.getMenuids().isEmpty()) {
			result = sysRoleService.saveRole(role, roleReq.getMenuids().toArray(new String[] {}));
		} else {
			result = sysRoleService.saveRole(role);

		}

		if (result) {
			return getResponse(result);
		} else
			return getResponse(HttpCode.INTERNAL_SERVER_ERROR, result, "新增角色失败");
	}

	@ApiOperation(value = "更新角色", notes = "")
	@PutMapping(value = { "/update/{roleId}" })
	@RequiresPermissions(logical = Logical.OR, value = {SystemContants.Permission.PERMISSION_MENU_TREE2USER,SystemContants.Permission.PERMISSION_ROLE_UPDATE})
	public Object updateRole(@Valid @RequestBody RoleReq roleReq, @PathVariable String roleId, BindingResult errors) {
		logger.debug("SysRoleController.updateRole()");

		if (errors.hasErrors()) {
			String msg = errors.getAllErrors().get(0).getDefaultMessage();
			logger.error(msg);
			return getResponse(HttpCode.BAD_REQUEST, false, msg);
		}

		if (StringUtils.isEmpty(roleId)) {
			logger.error("roleId is null.");
			return getResponse(HttpCode.BAD_REQUEST, false);
		}

		SysRoleInfo role = new SysRoleInfo();
		role.setId(roleId);
		role.setName(roleReq.getName());
		role.setRemark(roleReq.getRemark());

		boolean result = false;

		if (roleReq.getMenuids() != null && !roleReq.getMenuids().isEmpty()) {
			result = sysRoleService.updateRole(role, roleReq.getMenuids().toArray(new String[] {}));
		} else {
			result = sysRoleService.updateRole(role);
		}

		if (result) {
			return getResponse(result);
		} else
			return getResponse(HttpCode.INTERNAL_SERVER_ERROR, result, "更新角色失败");
	}

	@ApiOperation(value = "删除角色", notes = "")
	@DeleteMapping(value = { "/delete/{roleId}" })
	@RequiresPermissions(logical = Logical.OR, value = {SystemContants.Permission.PERMISSION_MENU_TREE2USER,SystemContants.Permission.PERMISSION_ROLE_DELETE})
	public Object deleteRole(@PathVariable String roleId) {
		logger.debug("SysRoleController.deleteRole()");

		if (StringUtils.isEmpty(roleId)) {
			logger.error("roleId is null.");
			return getResponse(HttpCode.BAD_REQUEST, false);
		}

		boolean result = sysRoleService.deleteRole(roleId);

		if (result) {
			return getResponse(result);
		} else
			return getResponse(HttpCode.INTERNAL_SERVER_ERROR, result, "删除角色失败");
	}

	@ApiOperation(value = "批量删除角色", notes = "批量删除角色")
	@PostMapping(value = { "/delete/roles" })
	@RequiresPermissions(logical = Logical.OR, value = {SystemContants.Permission.PERMISSION_MENU_TREE2USER,SystemContants.Permission.PERMISSION_ROLE_DELETES})
	public Object deleteRoles(@RequestBody List<String> roles) {
		logger.debug("SysRoleController.deleteRoles()");

		if (roles == null || roles.isEmpty()) {
			logger.error("roles is null.");
			return getResponse(HttpCode.BAD_REQUEST, false);
		}

		boolean result = sysRoleService.deleteRoles(roles);

		if (result) {
			return getResponse(result);
		} else
			return getResponse(HttpCode.INTERNAL_SERVER_ERROR, result, "删除角色失败");
	}

	@ApiOperation(value = "为角色设置菜单权限", notes = "")
	@PostMapping(value = { "/role2menu/{roleId}" })
	@RequiresPermissions(logical = Logical.OR, value = {SystemContants.Permission.PERMISSION_MENU_TREE2USER,SystemContants.Permission.PERMISSION_ROLE_ROLE2MENU})
	public Object setRole2Menu(@Valid @RequestBody SetRole2MenuReq setRole2MenuReq, @PathVariable String roleId,
			BindingResult errors) {
		logger.debug("SysRoleController.setRole2Menu()");

		if (StringUtils.isEmpty(roleId)) {
			logger.error("roleId is null.");
			return getResponse(HttpCode.BAD_REQUEST, false);
		}

		List<String> menuIds = setRole2MenuReq.getMenuIds();

		boolean result = sysRoleService.setRoleMenu(roleId, menuIds.toArray(new String[menuIds.size()]));

		if (result) {
			return getResponse(result);
		} else
			return getResponse(HttpCode.INTERNAL_SERVER_ERROR, result, "角色设置菜单权限失败");
	}
}
