package com.ddshteam.web.system.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.ddshteam.web.core.util.IdUtil;
import com.ddshteam.web.system.service.api.SysOpLogAsyncService;
import com.ddshteam.web.system.service.api.model.SysOpLogs;
import com.ddshteam.web.system.service.dao.SysOpLogsMapper;

@Service(version = "1.0.0")
@Transactional(rollbackFor = Exception.class)
public class SysOpLogAsyncServiceImpl implements SysOpLogAsyncService {

	@Autowired
	private SysOpLogsMapper sysOpLogsDao;

	@Override
	public boolean write(SysOpLogs sysOpLogs) {
		// TODO Auto-generated method stub
		sysOpLogs.setId(IdUtil.generateId().toString());
		int result = sysOpLogsDao.insert(sysOpLogs);
		return result > 0;
	}
}
