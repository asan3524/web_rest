package com.ddshteam.web.system.service.api;

import com.ddshteam.web.system.service.api.model.SysOpLogs;

public interface SysOpLogAsyncService {

	public boolean write(SysOpLogs sysOpLogs);
}
