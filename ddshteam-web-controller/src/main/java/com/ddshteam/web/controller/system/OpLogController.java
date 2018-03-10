package com.ddshteam.web.controller.system;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ddshteam.web.core.base.BaseController;
import com.ddshteam.web.dto.system.OpLogReq;
import com.ddshteam.web.shrio.Constant;
import com.ddshteam.web.system.service.api.SysOpLogService;
import com.ddshteam.web.system.service.api.data.OpLogListReqData;
import com.ddshteam.web.system.service.api.model.SysOpLogs;
import com.github.pagehelper.PageInfo;

@Api(value = "/system/oplog", description = "操作日志接口")
@RestController
@RequestMapping(value = "/system/oplog")
public class OpLogController extends BaseController {

	private final static Logger logger = LoggerFactory.getLogger(OpLogController.class);

	@Reference(version = "1.0.0")
	private SysOpLogService sysOpLogService;

	@ApiOperation(value = "日志列表", notes = "可指定参数: name(用户名称), ip(访问IP), 时间范围等")
	@PostMapping(value = { "/list" })
	@RequiresPermissions(Constant.PERMISSION_OPLOG_LIST)
	public Object getOpLogList(@Valid @RequestBody OpLogReq opLogReq,
			@PageableDefault(page = 1, size = 10, sort = "create_time,desc") Pageable pageable, BindingResult errors) {

		logger.debug("OpLogController.getOpLogList()");

		OpLogListReqData reqData = new OpLogListReqData();
		reqData.setName(opLogReq.getName());
		reqData.setIp(opLogReq.getIp());
		reqData.setCreateTime_s(opLogReq.getCreateTime_s());
		reqData.setCreateTime_e(opLogReq.getCreateTime_e());

		PageInfo<SysOpLogs> pi = sysOpLogService
				.getOpLogList(pageable.getPageNumber(), pageable.getPageSize(), reqData);
		return getResponse(pi);
	}
}
