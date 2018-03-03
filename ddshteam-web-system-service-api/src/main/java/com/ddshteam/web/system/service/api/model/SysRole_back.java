package com.ddshteam.web.system.service.api.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@SuppressWarnings("serial")
@Builder
@Data
@ToString
public class SysRole_back implements Serializable{
	
	private String id;
	private String name;
	private String remark;  //(not must)
	private Date createTime;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public SysRole_back() {
		super();
	}
	public SysRole_back(String id, String name, String remark, Date createTime) {
		super();
		this.id = id;
		this.name = name;
		this.remark = remark;
		this.createTime = createTime;
	}
	
}
