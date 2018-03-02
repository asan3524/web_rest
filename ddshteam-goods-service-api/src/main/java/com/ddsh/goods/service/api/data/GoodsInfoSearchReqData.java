package com.ddsh.goods.service.api.data;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class GoodsInfoSearchReqData implements Serializable{
	private String name;
	private String code;
	private List<String> brandIds;
	private List<String> typeIds;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public List<String> getBrandIds() {
		return brandIds;
	}
	public void setBrandIds(List<String> brandIds) {
		this.brandIds = brandIds;
	}
	public List<String> getTypeIds() {
		return typeIds;
	}
	public void setTypeIds(List<String> typeIds) {
		this.typeIds = typeIds;
	}
	
	

	 
}
