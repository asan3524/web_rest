package com.ddsh.util.service.api.data.word;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public abstract class IWordExportMapper  implements Serializable {
	
	/**
	 * 标识类型
	 * @Fields TypeMapper
	 */
	protected static Map<String, Integer> TypeMapper;
	/**
	 * 标识流
	 * @Fields FlowMapper
	 */
	protected static Map<String, Object> FlowMapper;
	/**
	 * 导出数据
	 * @Fields value
	 */
	protected Map<String,Object> value;
	/**
	 * 模型路径
	 * @Fields modelpath
	 */
	protected String modelpath;
	/**
	 * 输出文件路径
	 * @Fields exportpath
	 */
	protected String exportpath;
	
	protected List<String> deletes=null;
	
	protected List<String> clear=null;


	public static Map<String, Integer> getTypeMapper() {
		if(TypeMapper==null)
		{
			TypeMapper=new HashMap<String, Integer>();
		}
		return TypeMapper;
	}

	public static void setTypeMapper(Map<String, Integer> typeMapper) {
		IWordExportMapper.TypeMapper = typeMapper;
	}
	public static Map<String, Object> getFlowMapper() {
		if(FlowMapper==null)
		{
			FlowMapper=new HashMap<String, Object>();
		}
		return FlowMapper;
	}

	public static void setFlowMapper(Map<String, Object> flowMapper) {
		FlowMapper = flowMapper;
	}

	public Map<String, Object> getValue() {
		if(value==null)
		{
			value=new HashMap<String, Object>();
		}
		return value;
	}

	public void setValue(Map<String, Object> value) {
		this.value = value;
	}

	public String getModelpath() {
		return modelpath;
	}

	public void setModelpath(String modelpath) {
		this.modelpath = modelpath;
	}

	public String getExportpath() {
		return exportpath;
	}

	public void setExportpath(String exportpath) {
		this.exportpath = exportpath;
	}

	public List<String> getDeletes() {
		if(deletes==null)
		{
			deletes=new ArrayList<String>();
		}
		return deletes;
	}

	public void setDeletes(List<String> deletes) {
		this.deletes = deletes;
	}

	public List<String> getClear() {
		if(clear==null)
		{
			clear=new ArrayList<String>();
		}
		return clear;
	}

	public void setClear(List<String> clear) {
		this.clear = clear;
	}
	
	
	
	
}
