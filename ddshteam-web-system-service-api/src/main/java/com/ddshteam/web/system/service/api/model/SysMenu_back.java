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
public class SysMenu_back implements Serializable{
	
	private String id;      
	private String name;    
	private String parentId;//(not must)父级菜单
	private int type;    //菜单类型，必填目前有：1.目录、2.页面、3.功能点（按钮）
	private String url;     //(not must)前端路由(只有type=2时才有效)
	private String perms;   //(not must)功能权限(只有type=3时才有效)
	private String icon;
	private int orderNum;
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
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPerms() {
		return perms;
	}
	public void setPerms(String perms) {
		this.perms = perms;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public int getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public SysMenu_back() {
		super();
	}
	public SysMenu_back(String id, String name, String parentId, int type, String url, String perms, String icon,
			int orderNum, Date createTime) {
		super();
		this.id = id;
		this.name = name;
		this.parentId = parentId;
		this.type = type;
		this.url = url;
		this.perms = perms;
		this.icon = icon;
		this.orderNum = orderNum;
		this.createTime = createTime;
	}
	
	
}

