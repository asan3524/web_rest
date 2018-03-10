package com.ddshteam.web.controller.system;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ddshteam.web.core.base.BaseController;
import com.ddshteam.web.core.support.HttpCode;
import com.ddshteam.web.core.util.IdUtil;
import com.ddshteam.web.core.util.SecurityUtil;
import com.ddshteam.web.dto.system.ChangePswReq;
import com.ddshteam.web.dto.system.UserReq;
import com.ddshteam.web.dto.system.UserReqBase;
import com.ddshteam.web.system.service.api.SysUserService;
import com.ddshteam.web.system.service.api.constant.SystemContants;
import com.ddshteam.web.system.service.api.data.SysUserInfoResp;
import com.ddshteam.web.system.service.api.model.SysUserInfo;
import com.github.pagehelper.PageInfo;

@Api(value = "/system/user", description = "用户接口-完成后端测试")
@RestController
@RequestMapping(value = "/system/user")
public class SysUserController extends BaseController {

	private final static Logger logger = LoggerFactory.getLogger(SysUserController.class);

	@Reference(version = "1.0.0")
	private SysUserService sysUserService;

	@ApiOperation(value = "用户列表", notes = "可指定参数: name(用户姓名), depId(指定部门ID)")
	@GetMapping(value = { "/list" })
	public Object getUserList(HttpServletRequest request, HttpServletResponse response,
			@PageableDefault(page = 1, size = 10, sort = "createTime,asc") Pageable pageable) {
		logger.debug("SysUserController.getUserList()");

		Map<String, String> paremeters = loadParemeter(request);

		String name = paremeters.get("name");
		String depId = paremeters.get("depId");

		PageInfo<SysUserInfoResp> pi = sysUserService.getUserList(pageable.getPageNumber(), pageable.getPageSize(),
				name, depId);
		return getResponse(pi);
	}

	// @ApiOperation(value = "根据账号(用户名)获取用户", notes = "")
	// @GetMapping(value = { "/account/{account}" })
	// public Object getUserByAccount(@PathVariable String account) {
	// logger.debug("SysUserController.getUserByAccount()");
	//
	// if(StringUtils.isEmpty(account)) {
	// logger.error("account is null.");
	// return getResponse(HttpCode.BAD_REQUEST, false);
	// }
	//
	// SysUserInfoResp user = sysUserService.getUserByAccount(account);
	// user.setPassword(null);
	// return getResponse(user);
	// }

	@ApiOperation(value = "根据用户id获取用户", notes = "")
	@GetMapping(value = { "/id/{id}" })
	public Object getUserById(@PathVariable String id) {
		logger.debug("SysUserController.getUserById()");

		if (StringUtils.isEmpty(id)) {
			logger.error("id is null.");
			return getResponse(HttpCode.BAD_REQUEST, false);
		}

		SysUserInfoResp user = sysUserService.getUserById(id);
		user.setPassword(null);
		return getResponse(user);
	}

	@ApiOperation(value = "添加用户(不指定角色)", notes = "添加时不指定角色")
	@PostMapping(value = { "/save" })
	public Object saveUserNoRole(@Valid @RequestBody UserReqBase userReqBase, BindingResult errors) {
		logger.debug("SysUserController.saveUserNoRole()");

		if (errors.hasErrors()) {
			String msg = errors.getAllErrors().get(0).getDefaultMessage();
			logger.error(msg);
			return getResponse(HttpCode.BAD_REQUEST, false, msg);
		}

		SysUserInfo user = new SysUserInfo();
		user.setId(IdUtil.generateId().toString());
		user.setAccount(userReqBase.getAccount());
		if (userReqBase.getPassword() == null || userReqBase.getPassword().trim().equals("")) {
			user.setPassword(SystemContants.SysUserIsBuiltin.DEFAULT_PWD);
		} else {
			user.setPassword(SecurityUtil.encryptMd5(userReqBase.getPassword()));
		}

		user.setName(userReqBase.getName());
		user.setEmail(userReqBase.getEmail());
		user.setFax(userReqBase.getFax());
		user.setMobile(userReqBase.getMobile());
		user.setPhone(userReqBase.getPhone());
		user.setDepId(userReqBase.getDepId());
		user.setIsBuiltin(SystemContants.SysUserIsBuiltin.NOT_BUILTIN);
		user.setOrderNum(userReqBase.getOrderNum());
		user.setStatus(SystemContants.SysUserStatus.EFFECT);
		user.setCreateTime(new Date());
		user.setRemark(userReqBase.getRemark());
		user.setSex(userReqBase.getSex());

		boolean result = sysUserService.saveUser(user);
		if (result) {
			return getResponse(user);
		} else
			return getResponse(HttpCode.INTERNAL_SERVER_ERROR, result, "新增用户失败");
	}

	@ApiOperation(value = "添加用户(指定角色)", notes = "添加时指定角色")
	@PostMapping(value = { "/save/v2" })
	public Object saveUser$Role(@Valid @RequestBody UserReq userReq, BindingResult errors) {
		logger.debug("SysUserController.saveUser$Role()");

		if (errors.hasErrors()) {
			String msg = errors.getAllErrors().get(0).getDefaultMessage();
			logger.error(msg);
			return getResponse(HttpCode.BAD_REQUEST, false, msg);
		}

		SysUserInfo user = new SysUserInfo();
		user.setId(IdUtil.generateId().toString());
		user.setAccount(userReq.getAccount());
		user.setPassword(SecurityUtil.encryptMd5(userReq.getPassword()));
		user.setName(userReq.getName());
		user.setEmail(userReq.getEmail());
		user.setFax(userReq.getFax());
		user.setMobile(userReq.getMobile());
		user.setPhone(userReq.getPhone());
		user.setDepId(userReq.getDepId());
		user.setIsBuiltin(SystemContants.SysUserIsBuiltin.NOT_BUILTIN);
		user.setOrderNum(userReq.getOrderNum());
		user.setStatus(SystemContants.SysUserStatus.EFFECT);
		user.setCreateTime(new Date());
		user.setRemark(userReq.getRemark());
		user.setSex(userReq.getSex());

		List<String> roleIds = userReq.getRoleIds();
		boolean result = sysUserService.saveUser(user, roleIds.toArray(new String[roleIds.size()]));
		if (result) {
			return getResponse(result);
		} else
			return getResponse(HttpCode.INTERNAL_SERVER_ERROR, result, "新增用户失败");
	}

	@ApiOperation(value = "更新用户(不更新角色)", notes = "更新用户时不更新角色")
	@PutMapping(value = { "/update/{id}" })
	public Object updateUser(@Valid @RequestBody UserReqBase userReqBase, @PathVariable String id,
			BindingResult errors) {
		logger.debug("UserController.updateUser()");

		if (errors.hasErrors()) {
			String msg = errors.getAllErrors().get(0).getDefaultMessage();
			logger.error(msg);
			return getResponse(HttpCode.BAD_REQUEST, false, msg);
		}

		if (StringUtils.isEmpty(id)) {
			logger.error("userid is null.");
			return getResponse(HttpCode.BAD_REQUEST, false);
		}

		// if(userReq.getUsername().equals(Constant.ADMIN)) {
		// return getResponse(HttpCode.CONFLICT, false, "admin用户为系统保留账号");
		// }

		SysUserInfo user = new SysUserInfo();
		user.setId(id);
		// user.setName(userReqBase.getName());
		user.setEmail(userReqBase.getEmail());
		user.setFax(userReqBase.getFax());
		user.setMobile(userReqBase.getMobile());
		user.setPhone(userReqBase.getPhone());
		user.setDepId(userReqBase.getDepId());
		user.setIsBuiltin(SystemContants.SysUserIsBuiltin.NOT_BUILTIN);
		user.setOrderNum(userReqBase.getOrderNum());
		user.setRemark(userReqBase.getRemark());
		user.setSex(userReqBase.getSex());

		boolean result = sysUserService.updateUser(user);
		if (result) {
			return getResponse(result);
		} else
			return getResponse(HttpCode.INTERNAL_SERVER_ERROR, result, "修改用户失败");
	}

	@ApiOperation(value = "更新用户(需更新角色时)", notes = "更新时更新角色")
	@PutMapping(value = { "/update/v2/{id}" })
	public Object updateUser$Role(@Valid @RequestBody UserReq userReq, @PathVariable String id, BindingResult errors) {
		logger.debug("UserController.updateUser$Role()");

		if (errors.hasErrors()) {
			String msg = errors.getAllErrors().get(0).getDefaultMessage();
			logger.error(msg);
			return getResponse(HttpCode.BAD_REQUEST, false, msg);
		}

		if (StringUtils.isEmpty(id)) {
			logger.error("userid is null.");
			return getResponse(HttpCode.BAD_REQUEST, false);
		}

		// if(userReq.getUsername().equals(Constant.ADMIN)) {
		// return getResponse(HttpCode.CONFLICT, false, "admin用户为系统保留账号");
		// }

		SysUserInfo user = new SysUserInfo();
		user.setId(id);
		// user.setName(UserReqBase.getName());
		user.setEmail(userReq.getEmail());
		user.setFax(userReq.getFax());
		user.setMobile(userReq.getMobile());
		user.setPhone(userReq.getPhone());
		user.setDepId(userReq.getDepId());
		user.setIsBuiltin(SystemContants.SysUserIsBuiltin.NOT_BUILTIN);
		user.setOrderNum(userReq.getOrderNum());
		user.setRemark(userReq.getRemark());
		user.setSex(userReq.getSex());

		List<String> roleIds = userReq.getRoleIds();

		boolean result = sysUserService.updateUser(user, roleIds.toArray(new String[roleIds.size()]));
		if (result) {
			return getResponse(result);
		} else
			return getResponse(HttpCode.INTERNAL_SERVER_ERROR, result, "修改用户失败");
	}

	@ApiOperation(value = "重置用户密码", notes = "重置用户密码")
	@PutMapping(value = { "/update/pwd" })
	public Object updatePassword(@Valid @RequestBody ChangePswReq changePswReq, BindingResult errors) {
		logger.debug("UserController.updatePassword()");

		if (errors.hasErrors()) {
			String msg = errors.getAllErrors().get(0).getDefaultMessage();
			logger.error(msg);
			return getResponse(HttpCode.BAD_REQUEST, false, msg);
		}

		String userId = changePswReq.getUserId();
		boolean result = sysUserService.updatePassword(userId, SecurityUtil.encryptMd5(changePswReq.getNewPassword()));
		if (result) {
			return getResponse(result);
		} else
			return getResponse(HttpCode.INTERNAL_SERVER_ERROR, result, "更新用户密码失败");

	}

	@ApiOperation(value = "修改用户密码", notes = "修改用户密码,修改密码必需传入新的密码")
	@PutMapping(value = { "/update/changepwd" })
	public Object chagePassword(@Valid @RequestBody ChangePswReq changePswReq, BindingResult errors) {
		logger.debug("UserController.chagePassword()");

		if (errors.hasErrors()) {
			String msg = errors.getAllErrors().get(0).getDefaultMessage();
			logger.error(msg);
			return getResponse(HttpCode.BAD_REQUEST, false, msg);
		}

		// TODO 此处的userId应由session提供
		String userId = changePswReq.getUserId();
		SysUserInfo u = sysUserService.getUserById(userId);
		if (!u.getPassword().equals(SecurityUtil.encryptMd5(changePswReq.getOldPassword()))) {
			logger.error("password is not correct.");
			return getResponse(HttpCode.BAD_REQUEST, false, "旧密码不正确");
		}

		boolean result = sysUserService.updatePassword(userId, SecurityUtil.encryptMd5(changePswReq.getNewPassword()));
		if (result) {
			return getResponse(result);
		} else
			return getResponse(HttpCode.INTERNAL_SERVER_ERROR, result, "更新用户密码失败");

	}

	@ApiOperation(value = "删除用户", notes = "内置/admin/自己不能删除(暂不调用)")
	@DeleteMapping(value = { "/delete/{id}" })
	public Object deleteUser(@PathVariable String id) {
		logger.debug("UserController.deleteUser()");

		if (StringUtils.isEmpty(id)) {
			logger.error("userid is null.");
			return getResponse(HttpCode.BAD_REQUEST, false);
		}

		SysUserInfo user = sysUserService.getUserById(id);

		// TODO session 获取登陆用户id,自身不允许删除

		if (user.getAccount().equals("admin") || user.getIsBuiltin()) {
			logger.error("'admin', 'buildin' and itsself can't be deleted.");
			return getResponse(HttpCode.CONFLICT, false, "admin账号,内建账号，自身账号等不能删除");
		}

		boolean result = sysUserService.deleteUser(user);
		if (result) {
			return getResponse(result);
		} else
			return getResponse(HttpCode.INTERNAL_SERVER_ERROR, result, "删除用户失败");

	}
	
	@ApiOperation(value = "批量删除用户", notes = "批量删除用户，内置/admin/自己不能删除(暂不调用)")
	@PostMapping(value = { "/delete/ids" })
	public Object deleteUsers(@RequestBody List<String> userids) {
		logger.debug("UserController.deleteUsers()");

		if (userids==null||userids.isEmpty()) {
			logger.error("userids is null.");
			return getResponse(HttpCode.BAD_REQUEST, false);
		}

		List<SysUserInfo> users = sysUserService.getUsersByUserid(userids);

		// TODO session 获取登陆用户id,自身不允许删除

		for(SysUserInfo user:users)
		{
			if (user.getAccount().equals("admin") || user.getIsBuiltin()) {
				logger.error("'admin', 'buildin' and itsself can't be deleted.");
				return getResponse(HttpCode.CONFLICT, false, "admin账号,内建账号，自身账号等不能删除");
			}

		}
	
		boolean result = sysUserService.deleteUsers(users);
		if (result) {
			return getResponse(result);
		} else
			return getResponse(HttpCode.INTERNAL_SERVER_ERROR, result, "删除用户失败");

	}

	@ApiOperation(value = "更新用户角色")
	@PutMapping(value = { "/update/role/{id}" })
	public Object setUserRole(@PathVariable String id, @RequestParam(value = "roleIds[]") String[] roleIds) {
		logger.debug("UserController.setUserRole()");

		// TODO PUT是否能接收此类型参数(POST可以)

		if (null == roleIds || roleIds.length == 0) {
			logger.error("roleIds is null or empty.");
			return getResponse(HttpCode.BAD_REQUEST, false);
		}

		boolean result = sysUserService.setUserRole(id, roleIds);

		if (result) {
			return getResponse(result);
		} else
			return getResponse(HttpCode.INTERNAL_SERVER_ERROR, result, "更新用户角色失败");

	}

	@ApiOperation(value = "根据账号(用户名)获取判断用户是否存在", notes = "根据账号(用户名)获取判断用户是否存在，用于添加用户或者更新用户")
	@GetMapping(value = { "/account/exist/{account}" })
	public Object userNameExist(@PathVariable String account) {
		logger.debug("UserController.userNameExist()");

		if (StringUtils.isEmpty(account)) {
			logger.error("account is null.");
			return getResponse(HttpCode.BAD_REQUEST, false);
		}

		boolean result = sysUserService.getUserExist(account);
		return getResponse(result);

	}
}
