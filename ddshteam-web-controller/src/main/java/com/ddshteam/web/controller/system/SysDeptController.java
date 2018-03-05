package com.ddshteam.web.controller.system;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ddshteam.web.core.base.BaseController;
import com.ddshteam.web.core.support.HttpCode;
import com.ddshteam.web.dto.system.DeptReq;
import com.ddshteam.web.system.service.api.SysDeptService;
import com.ddshteam.web.system.service.api.data.Tree;
import com.ddshteam.web.system.service.api.model.SysDepInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "/dept", description = "部门接口")
@RestController
@RequestMapping(value = "/dept")
public class SysDeptController extends BaseController {
	
	private final static Logger logger = LoggerFactory.getLogger(SysDeptController.class);
	
	@Reference(version = "1.0.0")
	private SysDeptService sysDeptService;
	
	@ApiOperation(value = "部门列表", notes = "")
	@GetMapping(value = { "/list" })
	public Object getDeptList(HttpServletRequest request, HttpServletResponse response) {
		logger.debug("SysDeptController.getDeptList()");

		@SuppressWarnings("deprecation")
		List<SysDepInfo> list = sysDeptService.getSysDeptDetailList();
		return getResponse(list);
	}
	
	@ApiOperation(value = "部门树(全部)", notes = "")
	@GetMapping(value = { "/tree2all" })
	public Object getDeptTree(HttpServletRequest request, HttpServletResponse response) {
		logger.debug("SysDeptController.getDeptTree()");

		List<Tree> trees = sysDeptService.getDeptTree(false);
		return getResponse(trees);
	}
	
	@ApiOperation(value = "部门树(当前用户)", notes = "当前登录用户的部门及子部门")
	@GetMapping(value = { "/tree2user" })
	public Object getDeptTreeByUser(@RequestParam("userId") String userId, HttpServletRequest request, HttpServletResponse response) {
		logger.debug("SysDeptController.getDeptTreeByUser()");

		//TODO userId从session中获取
		
		if(StringUtils.isEmpty(userId)) {
			logger.error("userId is null.");
			return getResponse(HttpCode.BAD_REQUEST, false);
		}
		
		List<Tree> trees = sysDeptService.getDeptTree(userId, false);
		return getResponse(trees);
	}
	
	@ApiOperation(value = "获取直接子部门", notes = "")
	@GetMapping(value = { "/tree/{deptId}" })
	public Object getChildrenDept(@PathVariable("deptId") String deptId, HttpServletRequest request, HttpServletResponse response) {
		logger.debug("SysDeptController.getChildrenDept()");
		
		if(StringUtils.isEmpty(deptId)) {
			logger.error("deptId is null.");
			return getResponse(HttpCode.BAD_REQUEST, false);
		}
		
		List<Tree> trees = sysDeptService.getChildrenDeptList(deptId);
		return getResponse(trees);
	}
	
	@ApiOperation(value = "获取部门详情", notes = "")
	@GetMapping(value = { "/id/{deptId}" })
	public Object getSysDeptById(@PathVariable("deptId") String deptId, HttpServletRequest request, HttpServletResponse response) {
		logger.debug("SysDeptController.getSysDeptById()");
		
		if(StringUtils.isEmpty(deptId)) {
			logger.error("deptId is null.");
			return getResponse(HttpCode.BAD_REQUEST, false);
		}
		
		SysDepInfo dept = sysDeptService.getSysDeptById(deptId);
		return getResponse(dept);
	}
	
	@ApiOperation(value = "添加部门", notes = "")
	@PostMapping(value = { "/save" })
	public Object saveDept(@Valid @RequestBody DeptReq deptReq, BindingResult errors) {
		logger.debug("SysDeptController.saveDept()");
		
		if (errors.hasErrors()) {
			String msg = errors.getAllErrors().get(0).getDefaultMessage();
			logger.error(msg);
			return getResponse(HttpCode.BAD_REQUEST, false, msg);
		}
		
		SysDepInfo dept = new SysDepInfo();
		
		dept.setName(deptReq.getName());
		dept.setParentId(deptReq.getParentId());
		dept.setTypeId(deptReq.getTypeId());
		dept.setRemark(deptReq.getRemark());
		dept.setOrderNum(deptReq.getOrderNum());
		dept.setStatus(deptReq.getStatus());
		
		boolean result = sysDeptService.saveDept(dept);
		if(result) {
			return getResponse(result);	
		}else return getResponse(HttpCode.INTERNAL_SERVER_ERROR, result, "新增部门失败");
	}
	
	@ApiOperation(value = "更新部门", notes = "")
	@PutMapping(value = { "/update/{deptId}" })
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
		if(result) {
			return getResponse(result);	
		}else return getResponse(HttpCode.INTERNAL_SERVER_ERROR, result, "更新部门失败");
	}
	
	@ApiOperation(value = "删除部门", notes = "")
	@DeleteMapping(value = { "/delete/{deptId}" })
	public Object deleteDept(@PathVariable String deptId) {
		logger.debug("SysDeptController.deleteDept()");
		
		if(StringUtils.isEmpty(deptId)) {
			logger.error("deptId is null.");
			return getResponse(HttpCode.BAD_REQUEST, false);
		}
		
		if (sysDeptService.isDeptHasUser(deptId)) {
			logger.info("dept has user.");
			return getResponse(HttpCode.CONFLICT, false);
			
		} else {
			boolean result = sysDeptService.deleteDept(deptId);
			
			if(result) {
				return getResponse(result);	
			}else return getResponse(HttpCode.INTERNAL_SERVER_ERROR, result, "删除部门失败");
		}
		
	}
}
