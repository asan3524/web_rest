package com.ddshteam.web.dto.system;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 系统登陆DTO
 * @ClassName: LogInReq
 * @author arpgate
 * @date 2018年2月28日 上午12:07:51
 * @version v1.0.0
 * 
 */
public class LogInReq {
	/**
	 * 登陆名称
	 * @Fields logName
	 */
	@NotBlank
	String logName;
	/**
	 * 登陆密码 密文
	 * @Fields passWd
	 */
	@NotBlank
	String passWd;
	/**
	 * 标记，预留，目前任意
	 * @Fields label
	 */
	@NotNull
	String label;
	public String getLogName() {
		return logName;
	}
	public void setLogName(String logName) {
		this.logName = logName;
	}
	public String getPassWd() {
		return passWd;
	}
	public void setPassWd(String passWd) {
		this.passWd = passWd;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}	
	
	

}
