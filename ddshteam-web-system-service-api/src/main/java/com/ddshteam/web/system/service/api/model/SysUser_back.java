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
@ToString
public class SysUser_back implements Serializable{
	
	private String id;
	private String account;
	private String password;
	private String name;
	private String email;      //(not must)
	private String fax;        //(not must)
	private String phone;      //(not must)
	private String mobile;     //(not must)
	private String depId;      //(not must)所属部门ID，当前系统无部门时，此列无意义为空
	private boolean isBuiltin; //是否是内置账户，1是0不是，内置账户页面不可见而且有不进行权限过滤
	private int orderNum;
	private int status;        //状态：1有效0失效，理论上用户不应该删除，删除应置为失效
	private Date createTime;
	
	private SysDept dept;      //关联部门
	private List<SysRole> roles;//用户的角色
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getDepId() {
		return depId;
	}
	public void setDepId(String depId) {
		this.depId = depId;
	}
	public boolean isBuiltin() {
		return isBuiltin;
	}
	public void setBuiltin(boolean isBuiltin) {
		this.isBuiltin = isBuiltin;
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
	
	public SysDept getDept() {
		return dept;
	}
	public void setDept(SysDept dept) {
		this.dept = dept;
	}
	
	public List<SysRole> getRoles() {
		return roles;
	}
	public void setRoles(List<SysRole> roles) {
		this.roles = roles;
	}
	public SysUser_back() {
		super();
	}
	public SysUser_back(String id, String account, String password, String name, String email, String fax, String phone,
			String mobile, String depId, boolean isBuiltin, int orderNum, int status, Date createTime, SysDept dept,
			List<SysRole> roles) {
		super();
		this.id = id;
		this.account = account;
		this.password = password;
		this.name = name;
		this.email = email;
		this.fax = fax;
		this.phone = phone;
		this.mobile = mobile;
		this.depId = depId;
		this.isBuiltin = isBuiltin;
		this.orderNum = orderNum;
		this.status = status;
		this.createTime = createTime;
		this.dept = dept;
		this.roles = roles;
	}
	
	
}