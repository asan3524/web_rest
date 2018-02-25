package com.ddshteam.web.system.service.api.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;


@SuppressWarnings("serial")
@Builder
@Data
//@NoArgsConstructor
//@AllArgsConstructor
@ToString
public class SysDept implements Serializable{
	
	private String id;
	private String name;
	private String parentId;//(not must)上级部门ID
	private String typeId;  //(not must)部门分类，当前系统无部门分类时，此列无意义为空
	private String remark;  //(not must)
	private int orderNum;
	private int status;     //状态：1有效0失效
	private Date createTime;
	
	private List<SysUser> userList; //部门所属人员列表
	
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
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public List<SysUser> getUserList() {
		return userList;
	}
	public void setUserList(List<SysUser> userList) {
		this.userList = userList;
	}
	public SysDept() {
		super();
	}
	public SysDept(String id, String name, String parentId, String typeId, String remark, int orderNum, int status,
			Date createTime, List<SysUser> userList) {
		super();
		this.id = id;
		this.name = name;
		this.parentId = parentId;
		this.typeId = typeId;
		this.remark = remark;
		this.orderNum = orderNum;
		this.status = status;
		this.createTime = createTime;
		this.userList = userList;
	}
	
}