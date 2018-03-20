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
import com.ddshteam.web.dto.system.DeptReq;
import com.ddshteam.web.system.service.api.SysDeptService;
import com.ddshteam.web.system.service.api.constant.SystemContants;
import com.ddshteam.web.system.service.api.data.DeptInfoResp;
import com.ddshteam.web.system.service.api.data.Tree;
import com.ddshteam.web.system.service.api.model.SysDepInfo;
import com.ddshteam.web.system.service.api.model.SysDeptypeInfo;
import com.ddshteam.web.system.service.api.model.SysUserInfo;

@Api(value = "/system/dept", description = "部门接口-完成后端测试")
@RestController
@RequestMapping(value = "/system/dept")
public class SysDeptController extends BaseController {

	private final static Logger logger = LoggerFactory.getLogger(SysDeptController.class);

	@Reference(version = "1.0.0")
	private SysDeptService sysDeptService;

	/*
	 * @ApiOperation(value = "部门列表", notes = "")
	 * 
	 * @GetMapping(value = { "/list" })
	 * 
	 * @RequiresPermissions(SystemContants.Permission.PERMISSION_DEPT_LIST)
	 * public Object getDeptList(HttpServletRequest request, HttpServletResponse
	 * response) { logger.debug("SysDeptController.getDeptList()");
	 * 
	 * @SuppressWarnings("deprecation") List<DeptInfoResp> list =
	 * sysDeptService.getSysDeptDetailList(); return getResponse(list); }
	 */

	/*
	 * @ApiOperation(value = "部门树(全部)", notes = "")
	 * 
	 * @GetMapping(value = { "/tree2all" }) public Object
	 * getDeptTree(HttpServletRequest request, HttpServletResponse response) {
	 * logger.debug("SysDeptController.getDeptTree()");
	 * 
	 * List<Tree> trees = sysDeptService.getDeptTree(false); return
	 * getResponse(trees); }
	 */

	@ApiOperation(value = "部门树(当前用户)", notes = "当前登录用户的部门及子部门")
	@GetMapping(value = { "/tree2user" })
	@RequiresPermissions(SystemContants.Permission.PERMISSION_DEPT_TREE2USER)
	public Object getDeptTreeByUser() {
		logger.debug("SysDeptController.getDeptTreeByUser()");

		Subject subject = SecurityUtils.getSubject();
		SysUserInfo user = (SysUserInfo) subject.getPrincipals().getPrimaryPrincipal();

		if (StringUtils.isEmpty(user)) {
			return getResponse(HttpCode.UNAUTHORIZED, false);
		}

		List<Tree> trees = null;
		if (user.getIsBuiltin()) {
			trees = sysDeptService.getDeptTree(false);
		} else {
			trees = sysDeptService.getDeptTree(user.getId(), false);
		}
		return getResponse(trees);
	}

	@ApiOperation(value = "获取直接子部门", notes = "")
	@GetMapping(value = { "/tree/{deptId}"})
	@RequiresPermissions(SystemContants.Permission.PERMISSION_DEPT_TREE)
	public Object getChildrenDept(@PathVariable("deptId") String deptId, HttpServletRequest request,
			HttpServletResponse response) {
		logger.debug("SysDeptController.getChildrenDept()");

		if (StringUtils.isEmpty(deptId)||deptId.equalsIgnoreCase("null")) {
			deptId=null;
		}
		
		Subject subject = SecurityUtils.getSubject();
		SysUserInfo user = (SysUserInfo) subject.getPrincipals().getPrimaryPrincipal();

		List<Tree> trees = sysDeptService.getChildrenDeptList(deptId,user.getDepId());
		return getResponse(trees);
	}
	

	@ApiOperation(value = "获取部门详情", notes = "")
	@GetMapping(value = { "/id/{deptId}" })
	@RequiresPermissions(SystemContants.Permission.PERMISSION_DEPT_INFO)
	public Object getSysDeptById(@PathVariable("deptId") String deptId, HttpServletRequest request,
			HttpServletResponse response) {
		logger.debug("SysDeptController.getSysDeptById()");

		if (StringUtils.isEmpty(deptId)) {
			logger.error("deptId is null.");
			return getResponse(HttpCode.BAD_REQUEST, false);
		}

		DeptInfoResp dept = sysDeptService.getSysDeptById(deptId);
		return getResponse(dept);
	}

	@ApiOperation(value = "添加部门", notes = "")
	@PostMapping(value = { "/save" })
	@RequiresPermissions(SystemContants.Permission.PERMISSION_DEPT_SAVE)
	public Object saveDept(@Valid @RequestBody DeptReq deptReq, BindingResult errors) {
		logger.debug("SysDeptController.saveDept()");

		if (errors.hasErrors()) {
			String msg = errors.getAllErrors().get(0).getDefaultMessage();
			logger.error(msg);
			return getResponse(HttpCode.BAD_REQUEST, false, msg);
		}

		SysDepInfo dept = new SysDepInfo();

		dept.setId(IdUtil.generateId().toString());
		dept.setName(deptReq.getName());
		dept.setParentId(deptReq.getParentId());
		dept.setTypeId(deptReq.getTypeId());
		dept.setRemark(deptReq.getRemark());
		dept.setOrderNum(deptReq.getOrderNum());
		boolean result = sysDeptService.saveDept(dept);
		if (result) {
			return getResponse(result);
		} else
			return getResponse(HttpCode.INTERNAL_SERVER_ERROR, result, "新增部门失败");
	}

	@ApiOperation(value = "更新部门", notes = "")
	@PutMapping(value = { "/update/{deptId}" })
	@RequiresPermissions(SystemContants.Permission.PERMISSION_DEPT_UPDATE)
	public Object updateDept(@Valid @RequestBody DeptReq deptReq, @PathVariable String deptId, BindingResult errors) {
		logger.debug("SysDeptController.updateDept()");

		if (errors.hasErrors()) {
			String msg = errors.getAllErrors().get(0).getDefaultMessage();
			logger.error(msg);
			return getResponse(HttpCode.BAD_REQUEST, false, msg);
		}

		SysDepInfo dept = new SysDepInfo();
		dept.setId(deptId);
		dept.setName(deptReq.getName());
		dept.setParentId(deptReq.getParentId());
		dept.setTypeId(deptReq.getTypeId());
		dept.setRemark(deptReq.getRemark());
		dept.setOrderNum(deptReq.getOrderNum());
		dept.setStatus(deptReq.getStatus());

		boolean result = sysDeptService.updateDept(dept);
		if (result) {
			return getResponse(result);
		} else
			return getResponse(HttpCode.INTERNAL_SERVER_ERROR, result, "更新部门失败");
	}

	@ApiOperation(value = "删除部门", notes = "")
	@DeleteMapping(value = { "/delete/{deptId}" })
	@RequiresPermissions(SystemContants.Permission.PERMISSION_DEPT_DELETE)
	public Object deleteDept(@PathVariable String deptId) {
		logger.debug("SysDeptController.deleteDept()");

		if (StringUtils.isEmpty(deptId)) {
			logger.error("deptId is null.");
			return getResponse(HttpCode.BAD_REQUEST, false);
		}

		if (sysDeptService.isDeptHasUser(deptId)) {
			logger.info("dept has user.");
			return getResponse(HttpCode.CONFLICT, false);

		} else {
			boolean result = sysDeptService.deleteDept(deptId);

			if (result) {
				return getResponse(result);
			} else
				return getResponse(HttpCode.INTERNAL_SERVER_ERROR, result, "删除部门失败");
		}

	}

	@ApiOperation(value = "增加部门类型", notes = "增加部门类型")
	@PostMapping(value = { "/type/save" })
	@RequiresPermissions(SystemContants.Permission.PERMISSION_DEPTTYPE_SAVE)
	public Object addDeptType(@Valid @RequestBody SysDeptypeInfo typeinfo, BindingResult errors) {
		logger.debug("SysDeptController.addDeptType()");

		if (errors.hasErrors()) {
			String msg = errors.getAllErrors().get(0).getDefaultMessage();
			logger.error(msg);
			return getResponse(HttpCode.BAD_REQUEST, false, msg);
		} else {
			typeinfo.setId(IdUtil.generateId().toString());
			boolean result = sysDeptService.saveType(typeinfo);

			if (result) {
				return getResponse(result);
			} else
				return getResponse(HttpCode.INTERNAL_SERVER_ERROR, result, "新增部门类型失败");
		}

	}

	@ApiOperation(value = "增加部门类型", notes = "")
	@PutMapping(value = { "/type/update" })
	@RequiresPermissions(SystemContants.Permission.PERMISSION_DEPTTYPE_UPDATE)
	public Object updateDeptType(@Valid @RequestBody SysDeptypeInfo typeinfo, BindingResult errors) {
		logger.debug("SysDeptController.updateDeptType()");

		if (errors.hasErrors()) {
			String msg = errors.getAllErrors().get(0).getDefaultMessage();
			logger.error(msg);
			return getResponse(HttpCode.BAD_REQUEST, false, msg);
		} else {
			boolean result = sysDeptService.updateType(typeinfo);

			if (result) {
				return getResponse(result);
			} else
				return getResponse(HttpCode.INTERNAL_SERVER_ERROR, result, "更新部门类型失败");
		}

	}

	@ApiOperation(value = "根据部门类型id获取部门详情", notes = "根据部门类型id获取部门详情")
	@GetMapping(value = { "/type/{depttypeid}" })
	@RequiresPermissions(SystemContants.Permission.PERMISSION_DEPTTYPE_INFO)
	public Object getDeptTypeByid(@PathVariable String depttypeid) {
		logger.debug("SysDeptController.getDeptTypeByid()");

		if (StringUtils.isEmpty(depttypeid)) {
			logger.error("deptId is null.");
			return getResponse(HttpCode.BAD_REQUEST, false);
		}
		SysDeptypeInfo typeinfo = sysDeptService.getTypeinfoById(depttypeid);
		return getResponse(typeinfo);

	}

	@ApiOperation(value = "查看部门类型列表", notes = "查看部门类型列表")
	@GetMapping(value = { "/type/list" })
	@RequiresPermissions(SystemContants.Permission.PERMISSION_DEPTTYPE_LIST)
	public Object listDeptType() {
		logger.debug("SysDeptController.listDeptType()");
		return getResponse(sysDeptService.ListType());
	}

	@ApiOperation(value = "删除部门类型", notes = "根据id删除部门类型")
	@DeleteMapping(value = { "/type/delete/{depttypeid}" })
	@RequiresPermissions(SystemContants.Permission.PERMISSION_DEPTTYPE_DELETE)
	public Object deleteTypeinfoByid(@PathVariable String depttypeid) {
		logger.debug("SysDeptController.deleteTypeinfoByid()");
		boolean result = sysDeptService.deleteTypeByid(depttypeid);
		return getResponse(result);
	}
}
