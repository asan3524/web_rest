package com.ddshteam.web.system.service.util;


 
/**
 * 编码器
 * @ClassName: Coder
 * @author arpgate
 * @date 2018年3月12日 下午10:15:30
 * @version v1.0.0
 * 
 */
public class Coder {
	
	private final static String BEGIN_MAKER="M"; 
	private final static int CODE_LENGTH=20;
	public static String getCode(String ...factors)
	{
		if(factors.length<1)
		{
			return null;
		}
		int factory=factors.length;
		factors[0]=factors[0]+factory;
		StringBuilder code=new StringBuilder(BEGIN_MAKER);
		for(String factor:factors)
		{
			factory=factory|factor.hashCode();
		}
		
		code.append(factory);

		while(code.length()<=CODE_LENGTH)
		{
			code.append("0");
		}
		
		return code.toString();
		
	}

}
