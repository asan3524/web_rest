package com.ddsh.util.service.util.excel;

import java.util.HashMap;
import java.util.Map;

import com.ddsh.util.service.api.constant.UtilContants;

/**
 * Excel映射配置文件
 * @ClassName: ExcelConfig
 * @author arpgate
 * @date 2018年5月3日 下午9:49:10
 * @version v1.0.0
 * 
 */
public class ExcelConfig {
	
	private static Map<String, Class> ExcelMapper=new HashMap<String, Class>();
	
	static
	{
		ExcelMapper.put(UtilContants.ExcelType.CHOOSE_CLASS_SET_APP, null);
		ExcelMapper.put(UtilContants.ExcelType.COMMON_CLASS_SET_APP, null);
		ExcelMapper.put(UtilContants.ExcelType.PERSONAL_MANY_SET_APP, null);
		ExcelMapper.put(UtilContants.ExcelType.TAIN_SET_APP, null);
		ExcelMapper.put(UtilContants.ExcelType.TEACHER_INFO_APP, null);
	}
	
	public static Class getExcelModel(String excelType)
	{
		return ExcelMapper.get(excelType);
	}

}
