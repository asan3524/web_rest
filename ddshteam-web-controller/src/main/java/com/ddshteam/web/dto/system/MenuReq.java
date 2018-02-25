package com.ddshteam.web.dto.system;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class MenuReq {
	@NotBlank
	private String name;    
	private String parentId;//(not must)父级菜单
	@NotNull
	private Integer type;       //菜单类型，必填目前有：1.目录、2.页面、3.功能点（按钮）
	private String url;     //(not must)前端路由(只有type=2时才有效)
	private String perms;   //(not must)功能权限(只有type=3时才有效)
	private String icon;
	@NotNull
	private int orderNum;
	
	public MenuReq() {
		super();
	}
	public MenuReq(String name, String parentId, int type, String url, String perms, String icon, int orderNum) {
		super();
		this.name = name;
		this.parentId = parentId;
		this.type = type;
		this.url = url;
		this.perms = perms;
		this.icon = icon;
		this.orderNum = orderNum;
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
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
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
}
