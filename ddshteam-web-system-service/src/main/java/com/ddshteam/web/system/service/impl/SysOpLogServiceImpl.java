package com.ddshteam.web.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.ddshteam.web.system.service.api.SysOpLogService;
import com.ddshteam.web.system.service.api.data.OpLogListReqData;
import com.ddshteam.web.system.service.api.model.SysOpLogs;
import com.ddshteam.web.system.service.api.model.SysOpLogsCriteria;
import com.ddshteam.web.system.service.api.model.SysOpLogsCriteria.Criteria;
import com.ddshteam.web.system.service.dao.SysOpLogsMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mysql.cj.core.util.StringUtils;

@Service(version = "1.0.0")
@Transactional(rollbackFor = Exception.class)
public class SysOpLogServiceImpl implements SysOpLogService {

	@Autowired
	private SysOpLogsMapper sysOpLogsDao;

	@Override
	public PageInfo<SysOpLogs> getOpLogList(int pageNum, int pageSize, OpLogListReqData reqData) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNum, pageSize);
		SysOpLogsCriteria sysOpLogsCriteria = new SysOpLogsCriteria();
		if (null != reqData) {
			Criteria criteria = sysOpLogsCriteria.createCriteria();
			if (!StringUtils.isNullOrEmpty(reqData.getName())) {
				criteria.andNameLike(reqData.getName());
			}
			if (!StringUtils.isNullOrEmpty(reqData.getIp())) {
				criteria.andIpLike(reqData.getIp());
			}
			if (null != reqData.getCreateTime_s()) {
				if (null != reqData.getCreateTime_e()) {
					criteria.andCreateTimeBetween(reqData.getCreateTime_s(), reqData.getCreateTime_e());
				} else {
					criteria.andCreateTimeGreaterThanOrEqualTo(reqData.getCreateTime_s());
				}
			} else {
				if (null != reqData.getCreateTime_e()) {
					criteria.andCreateTimeLessThanOrEqualTo(reqData.getCreateTime_e());
				}
			}
		}
		sysOpLogsCriteria.setOrderByClause("create_time desc");
		List<SysOpLogs> sysOpLogs = sysOpLogsDao.selectByExample(sysOpLogsCriteria);
		return new PageInfo<SysOpLogs>(sysOpLogs, pageSize);
	}
}
