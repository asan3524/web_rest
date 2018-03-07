package com.ddshteam.web.system.service.api.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class SysOpLogs implements Serializable {
	private String id;

	private String account;

	private String name;

	private String ip;

	private String uri;

	private Integer excuteTime;

	private String resp;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account == null ? null : account.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip == null ? null : ip.trim();
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri == null ? null : uri.trim();
	}

	public Integer getExcuteTime() {
		return excuteTime;
	}

	public void setExcuteTime(Integer excuteTime) {
		this.excuteTime = excuteTime;
	}

	public String getResp() {
		return resp;
	}

	public void setResp(String resp) {
		this.resp = resp == null ? null : resp.trim();
	}
}