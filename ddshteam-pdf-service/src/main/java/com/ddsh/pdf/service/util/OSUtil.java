package com.ddsh.pdf.service.util;

import com.ddsh.pdf.service.constant.PDFContants;

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
		int os=PDFContants.OSType.LINUX;
		
		String osname=System.getProperty("os.name").toLowerCase();
		
		if(osname.contains("windows"))
		{
			os=PDFContants.OSType.WINDOWS;
		}
		else if(osname.contains("linux"))
		{
			os=PDFContants.OSType.LINUX;

		}
		
		return os;
	}
	
    
    
    public static String getBasePath() {
        if(OSUtil.getSystemType()==PDFContants.OSType.LINUX)
        {
            return PDFContants.Sysset.BASE_L_ROOT;
        }
        else
        {
            return PDFContants.Sysset.BASE_W_ROOT;
        }
    }


}
