package com.ddsh.util.service.api.data.excel;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
@SuppressWarnings("serial")
public abstract class IExcelParseData implements Serializable {
	protected SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	protected List<String> header=null;
	protected String sheetName;
	protected Class classType=null;
	protected List<Object> objects=null;
	public abstract Map<String, String> getMapper();
	private Object obj=null;
	public abstract  Class  getClassType();

	public List<String> getHeader() {
		if(header==null)
		{
			header=new ArrayList<String>();
		}
		return header;
	}
	
	public void startrow()
	{
		if(obj!=null)
		{
			this.getObject().add(obj);
		}
		try {
			obj=this.getClassType().newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	public void endrow()
	{
		if(obj!=null)
		{
			this.getObject().add(obj);
		}
		obj=null;
	}

	public void setValue(Integer index,Object value) {
	 if(index==null||value==null)
	 {
		 return;
	 }

		try
		{
			Field field=this.getClassType().getDeclaredField(this.getMapper().get(this.getHeader().get(index)));
			System.out.println("fieldType"+field.getType()+",value"+value.getClass());
			if(field.getType()!=value.getClass())
			{
				if(field.getType()==String.class)
				{
					value=value.toString();
				}
				else if(field.getType()==Integer.class)
				{
					value=Integer.valueOf(value.toString());
				}
				else if(field.getType()==Long.class)
				{
					value=Long.valueOf(value.toString());
				}
				else if(field.getType()==Long.class)
				{
					value=BigDecimal.valueOf(Long.valueOf(value.toString()));
				}
			}
	
		}
		catch (Exception e) {
			return;
		}
		String field=this.getMapper().get(this.getHeader().get(index));
		for(Method method:this.getClassType().getMethods())
		{
			if(method.getName().equalsIgnoreCase("set"+field))
			{
				System.out.println(method.getName()+":"+value);
				try {
					method.invoke(obj, value);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
				break;
			}
		}
	}

	public List<Object> getObject() {
		if(objects==null)
		{
			objects=new ArrayList<Object>();
		}
		return  objects;
	}

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}
	

	
}
