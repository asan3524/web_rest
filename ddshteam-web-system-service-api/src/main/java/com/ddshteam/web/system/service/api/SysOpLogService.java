package com.ddshteam.web.system.service.api;

import com.ddshteam.web.system.service.api.data.OpLogListReqData;
import com.ddshteam.web.system.service.api.model.SysOpLogs;
import com.github.pagehelper.PageInfo;

public interface SysOpLogService {

	public PageInfo<SysOpLogs> getOpLogList(int pageNum, int pageSize, OpLogListReqData reqData);
}
