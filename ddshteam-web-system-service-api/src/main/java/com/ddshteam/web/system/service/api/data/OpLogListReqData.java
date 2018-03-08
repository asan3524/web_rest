package com.ddshteam.web.system.service.api.data;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class OpLogListReqData implements Serializable {
	private String name;
	private String ip;
	/**
	 * 申请时间
	 * @Fields createTime_s
	 */
	private Date createTime_s;
	private Date createTime_e;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getCreateTime_s() {
		return createTime_s;
	}

	public void setCreateTime_s(Date createTime_s) {
		this.createTime_s = createTime_s;
	}

	public Date getCreateTime_e() {
		return createTime_e;
	}

	public void setCreateTime_e(Date createTime_e) {
		this.createTime_e = createTime_e;
	}
}
