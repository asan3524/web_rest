package com.ddsh.util.service.api.data;

import java.io.Serializable;

@SuppressWarnings("serial")
public class FileInfo implements Serializable {
	
	String userid;
	
	String username;
	
	String filename;
	
	String path;
	
	Long fileSize;
	
	String fileType;
	
	String tableName;
	
	String type;
	
	Integer status;
	
	String bussnessObjId;

	String name;
	
	String remark;
	
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}


	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
 
	public String getBussnessObjId() {
		return bussnessObjId;
	}

	public void setBussnessObjId(String bussnessObjId) {
		this.bussnessObjId = bussnessObjId;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	

}
