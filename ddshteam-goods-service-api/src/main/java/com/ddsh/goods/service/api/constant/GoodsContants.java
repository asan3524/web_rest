package com.ddsh.goods.service.api.constant;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.ddsh.goods.service.api.annotation.ConstantDescription;

/**
 * 物资常量
 * @ClassName: GoodsContants
 * @author arpgate
 * @date 2018年3月2日 下午11:54:32
 * @version v1.0.0
 * 
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class GoodsContants {

	 public static final Map<String,Map<String,String>> CONSTANT_DESC = new HashMap<String, Map<String, String>>(40);
	 
	 public static final Map<String,String> CONSTANT_TYPE_DESC = new HashMap<String, String>(20);
	 
	 static
	 {
		 try {
			 for (Class cls : GoodsContants.class.getClasses()) {
				 ConstantDescription cds = (ConstantDescription) cls.getAnnotation(ConstantDescription.class);
				 if(cds!=null)
				 {
					 CONSTANT_TYPE_DESC.put(cls.getSimpleName(), cds.desc());
					 Map<String, String> valueDesc = new HashMap<String, String>(10);
					 CONSTANT_DESC.put(cls.getSimpleName(), valueDesc);

					 for (Field fd : cls.getDeclaredFields()) {
						 ConstantDescription cdf = fd.getAnnotation(ConstantDescription.class);
						 if(cdf!=null)
						 {
							 valueDesc.put(fd.get(cls).toString(), cdf.desc());

						 }
					 }
				 }
			
				 
			 }
		 }catch (Exception e) {
			 e.printStackTrace();
		}
	 }
 
	@ConstantDescription(desc="物资是否有效")
	public static final class GoodsStatus
	{
		@ConstantDescription(desc="有效")
		public static final String EFFECT="1";
		
		@ConstantDescription(desc="失效")
		public static final String LOSE_EFFECT="0";

	}
	
	@ConstantDescription(desc="物资类型是否有效")
	public static final class GoodsTypeStatus
	{
		@ConstantDescription(desc="有效")
		public static final String EFFECT="1";
		
		@ConstantDescription(desc="失效")
		public static final String LOSE_EFFECT="0";

	}
	
	@ConstantDescription(desc="物资品牌类型是否有效")
	public static final class GoodsBrandStatus
	{
		@ConstantDescription(desc="有效")
		public static final String EFFECT="1";
		
		@ConstantDescription(desc="失效")
		public static final String LOSE_EFFECT="0";

	}
}
