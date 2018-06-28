package com.ddsh.util.service.util;


/**
 * 编码编码器,后续待完善
 * @ClassName: GoodsCoder
 * @author arpgate
 * @date 2018年3月2日 上午9:39:14
 * @version v1.0.0
 * 
 */
public class Coder {
	
	private final static String BEGIN_MAKER="M"; 
	private final static int CODE_LENGTH=20;
	public static String getGoodsCode(String ...factors)
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
		
		code.append(Math.abs(factory));

		while(code.length()<=CODE_LENGTH)
		{
			code.append("0");
		}
		
		return code.toString();
		
	}

}
