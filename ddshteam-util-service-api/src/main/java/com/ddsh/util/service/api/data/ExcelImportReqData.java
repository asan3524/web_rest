package com.ddsh.util.service.api.data;

import java.io.Serializable;

import com.ddsh.util.service.api.constant.UtilContants;

public class ExcelImportReqData implements Serializable{
	
	/**
	 * 文件id
	 * @Fields fileid
	 */
	String fileid;
	
	/**
	 * 文件业务类型
	 * @see UtilContants.ExcelType
	 * @Fields exceltype
	 */
	String exceltype;
	/**
	 * 起始Sheet位置
	 * @Fields startSheet
	 */
	Integer startSheet;
	/**
	 * 数据起始行
	 * @Fields startHeader
	 */
	Integer startHeader;
	/**
	 * 数据起始列
	 * @Fields startLeft
	 */
	Integer startLeft;
	/**
	 * 头行号
	 * @Fields header
	 */
	Integer header;
	public String getExceltype() {
		return exceltype;
	}
	public void setExceltype(String exceltype) {
		this.exceltype = exceltype;
	}
	public Integer getStartSheet() {
		return startSheet;
	}
	public void setStartSheet(Integer startSheet) {
		this.startSheet = startSheet;
	}
	public Integer getStartHeader() {
		return startHeader;
	}
	public void setStartHeader(Integer startHeader) {
		this.startHeader = startHeader;
	}
	public Integer getStartLeft() {
		return startLeft;
	}
	public void setStartLeft(Integer startLeft) {
		this.startLeft = startLeft;
	}
	public Integer getHeader() {
		return header;
	}
	public void setHeader(Integer header) {
		this.header = header;
	}
	public String getFileid() {
		return fileid;
	}
	public void setFileid(String fileid) {
		this.fileid = fileid;
	}
	
}
