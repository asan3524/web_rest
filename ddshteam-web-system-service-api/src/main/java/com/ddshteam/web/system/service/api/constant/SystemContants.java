package com.ddshteam.web.system.service.api.constant;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.ddshteam.web.system.service.api.annotation.ConstantDescription;

/**
 *  系统管理常量
 * @ClassName: SystemContants
 * @author arpgate
 * @date 2018年3月5日 下午5:41:02
 * @version v1.0.0
 * 
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class SystemContants {

	 public static final Map<String,Map<String,String>> CONSTANT_DESC = new HashMap<String, Map<String, String>>(40);
	 
	 public static final Map<String,String> CONSTANT_TYPE_DESC = new HashMap<String, String>(20);
	 
	 static
	 {
		 try {
			 for (Class cls : SystemContants.class.getClasses()) {
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
 
	@ConstantDescription(desc="部门是否有效")
	public static final class SysDeptStatus
	{
		@ConstantDescription(desc="有效")
		public static final int EFFECT=1;
		
		@ConstantDescription(desc="失效")
		public static final int LOSE_EFFECT=0;

	}
	
	@ConstantDescription(desc="菜单资源类型")
	public static final class SysMenuTypes
	{
		@ConstantDescription(desc="菜单目录")
		public static final int MENU_ITEM=1;
		
		@ConstantDescription(desc="菜单页面")
		public static final int MENU_PAGE=2;
		
		@ConstantDescription(desc="菜单功能(按钮)")
		public static final int MENU_FEATURE=3;

	}
	
	@ConstantDescription(desc="系统用户是否有效")
	public static final class SysUserStatus
	{
		@ConstantDescription(desc="有效")
		public static final int EFFECT=1;
		
		@ConstantDescription(desc="失效")
		public static final int LOSE_EFFECT=0;

	}
	
	@ConstantDescription(desc="系统用户是否有效")
	public static final class SysUserSex
	{
		@ConstantDescription(desc="男人")
		public static final int MAN=1;
		
		@ConstantDescription(desc="女人")
		public static final int WOMAN=0;

	}
	
	
	@ConstantDescription(desc="系统用户是否内置账户,内置账户页面不可见")
	public static final class SysUserIsBuiltin
	{
		@ConstantDescription(desc="内置账户")
		public static final boolean BUILTIN=true;
		
		@ConstantDescription(desc="非内置账户")
		public static final boolean NOT_BUILTIN=false;

	}
}
