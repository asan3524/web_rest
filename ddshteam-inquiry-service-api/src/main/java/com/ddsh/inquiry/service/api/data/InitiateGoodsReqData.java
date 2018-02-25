package com.ddsh.inquiry.service.api.data;

/**
 * 询价申请发起时物资信息
 * @ClassName: InitiateGoodsReqData
 * @author lishibang
 * @date 2018年2月15日 下午2:06:20
 * @version v1.0.0
 * 
 */
public class InitiateGoodsReqData {
	/**
	 * 物资ID
	 * @Fields goodsId
	 */
	private String goodsId;
	/**
	 * 物资名称
	 * @Fields goodsId
	 */
	private String name;
	/**
	 * 物资编码
	 * @Fields goodsId
	 */
	private String code;
	/**
	 * 物资品牌
	 * @Fields goodsId
	 */
	private String brand;
	/**
	 * 物资类型
	 * @Fields type
	 */
	private String type;
	/**
	 * 物资颜色
	 * @Fields goodsId
	 */
	private String colour;

	private String unit;
	/**
	 * 备注
	 * @Fields remark
	 */
	private String remark;
	/**
	 * 数量
	 * @Fields quantity
	 */
	private int quantity;

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

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

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
