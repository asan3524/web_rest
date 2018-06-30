package com.ddsh.util.service.api.data.excel;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * excel parse 接口类
 * @ClassName: IExcelParseData
 * @author arpgate
 * @date 2018年6月30日 下午6:48:19
 * @version v1.0.0
 * 
 */
public interface IExcelParseData extends Serializable {
	void setShhetName(String name);
	List<String> getHeader();
	Map<Integer, LinkedList<Object>> getValue();
	static <T extends IExcelParseData> T getParseObject() {
		try {
			return (T) IExcelParseData.class.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
}
