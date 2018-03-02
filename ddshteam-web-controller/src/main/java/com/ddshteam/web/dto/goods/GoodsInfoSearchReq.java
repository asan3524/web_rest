package com.ddshteam.web.dto.goods;

import java.util.List;


/**
 * 查询物质请求
 * @ClassName: GoodsInfoReq
 * @author arpgate
 * @date 2018年3月1日 下午4:11:30
 * @version v1.0.0
 * 
 */
public class GoodsInfoSearchReq {
	
	/**
	 * 物资编码
	 * @Fields code
	 */
	String code;
	
	/**
	 * 物资名称
	 * @Fields name
	 */
	String name;
	
	/**
	 * 物资类型列表
	 * @Fields typeIds
	 */
	List<String> typeIds;
	
	/**
	 * 物资品牌类型列表
	 * @Fields brandIds
	 */
	List<String> brandIds;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getTypeIds() {
		return typeIds;
	}

	public void setTypeIds(List<String> typeIds) {
		this.typeIds = typeIds;
	}

	public List<String> getBrandIds() {
		return brandIds;
	}

	public void setBrandIds(List<String> brandIds) {
		this.brandIds = brandIds;
	}
	
}
