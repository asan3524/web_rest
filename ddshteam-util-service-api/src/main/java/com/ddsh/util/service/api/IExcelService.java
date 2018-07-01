package com.ddsh.util.service.api;

import com.ddsh.util.service.api.data.ExcelImportReqData;
import com.ddsh.util.service.api.data.ExcelImportRespData;
import com.ddsh.util.service.api.data.FileInfo;

public interface IExcelService {
	
	/**
	 * 这里用一句话描述这个方法的作用
	 * @Title: importExcel
	 * @param fileinfo
	 * @param data ExcelImportRespData
	 * @see 
	 * @throws
	 * @author arpgate
	 */
	ExcelImportRespData importExcel(FileInfo fileinfo,ExcelImportReqData data);

}
