package com.ddsh.goods.service.util;


/**
 * 物资编码编码器,后续待完善
 * @ClassName: GoodsCoder
 * @author arpgate
 * @date 2018年3月2日 上午9:39:14
 * @version v1.0.0
 * 
 */
public class GoodsCoder {
	
	private final static String BEGIN_MAKER="M"; 
	private final static int CODE_LENGTH=20;
	public static String getGoodsCode(String ...factors)
	{
		if(factors.length<1)
		{
			return null;
		}
		int factory=factors.length;
	 
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
