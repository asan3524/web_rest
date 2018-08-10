package com.ddsh.util.service.util;

import com.ddsh.util.service.api.constant.UtilContants;

/**
 * 获取操作系统类型
 * @ClassName: OSUtil
 * @author arpgate
 * @date 2018年8月10日 下午3:30:01
 * @version v1.0.0
 * 
 */
public class OSUtil {
	
	public static int getSystemType()
	{
		int os=UtilContants.OSType.LINUX;
		
		String osname=System.getProperty("os.name").toLowerCase();
		
		if(osname.contains("windows"))
		{
			os=UtilContants.OSType.WINDOWS;
		}
		else if(osname.contains("linux"))
		{
			os=UtilContants.OSType.LINUX;

		}
		
		return os;
	}

}
