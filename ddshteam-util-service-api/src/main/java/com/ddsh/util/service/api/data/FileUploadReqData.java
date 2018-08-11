package com.ddsh.util.service.api.data;

import java.io.Serializable;

@SuppressWarnings("serial")
public class FileUploadReqData implements Serializable {

	String bussnessObjId;
	
	String bussnessObjSubId;
	
	String type;
	
	String fileName;
	
	String fileSize;
	
	String fileType;
	
	String attType;
	
	String attSubType;
	
 
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getBussnessObjId() {
		return bussnessObjId;
	}
	public void setBussnessObjId(String bussnessObjId) {
		this.bussnessObjId = bussnessObjId;
	}
	public String getFileSize() {
		return fileSize;
	}
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public String getAttType() {
		return attType;
	}
	public void setAttType(String attType) {
		this.attType = attType;
	}
	public String getAttSubType() {
		return attSubType;
	}
	public void setAttSubType(String attSubType) {
		this.attSubType = attSubType;
	}
	public String getBussnessObjSubId() {
		return bussnessObjSubId;
	}
	public void setBussnessObjSubId(String bussnessObjSubId) {
		this.bussnessObjSubId = bussnessObjSubId;
	}
 
}
