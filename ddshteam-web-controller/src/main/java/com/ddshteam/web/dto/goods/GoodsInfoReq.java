package com.ddshteam.web.dto.goods;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 物资信息DTO
 * @ClassName: GoodsInfoReq
 * @author arpgate
 * @date 2018年3月1日 下午10:36:11
 * @version v1.0.0
 * 
 */
public class GoodsInfoReq {
	
	@NotBlank
    private String name;
	@NotBlank
    private String typeId;
	@NotBlank
    private String brandId;
	@NotBlank
    private String color;
	@NotBlank
    private String unit;
	@NotBlank
    private String norm;
	@NotBlank
    private String remark;
	@NotNull
    private Integer orderNum;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getBrandId() {
		return brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getNorm() {
		return norm;
	}

	public void setNorm(String norm) {
		this.norm = norm;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
    
}