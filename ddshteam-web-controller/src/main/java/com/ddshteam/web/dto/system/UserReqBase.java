package com.ddshteam.web.dto.system;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

public class UserReqBase {
	@NotBlank
	private String account;
	@NotBlank
	private String password;
	@NotBlank
	private String name;
	@Email
	private String email;      //(not must)
	private String fax;        //(not must)
	private String phone;      //(not must)
	private String mobile;     //(not must)
	private String depId;      //(not must)所属部门ID，当前系统无部门时，此列无意义为空
	@NotNull
	private boolean isBuiltin; //是否是内置账户，1是0不是，内置账户页面不可见而且有不进行权限过滤
	@NotNull
	private int orderNum;
	@NotNull
	private int status;
	
	
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
}
