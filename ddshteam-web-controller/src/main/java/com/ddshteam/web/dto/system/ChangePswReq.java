package com.ddshteam.web.dto.system;

import org.hibernate.validator.constraints.NotBlank;

public class ChangePswReq {
	@NotBlank
	private String userId;
	@NotBlank
	private String newPassword;
	@NotBlank
	private String oldPassword;
	
	public ChangePswReq() {
		super();
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	
}
