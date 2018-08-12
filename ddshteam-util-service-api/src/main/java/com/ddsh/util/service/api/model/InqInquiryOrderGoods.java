package com.ddsh.util.service.api.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class InqInquiryOrderGoods implements Serializable {
    /**
     * 主键ID
     * 表字段 : inq_inquiry_order_goods.id
     */
    private String id;

    /**
     * 询价单ID
     * 表字段 : inq_inquiry_order_goods.inquiry_id
     */
    private String inquiryId;

    /**
     * 物资ID
     * 表字段 : inq_inquiry_order_goods.goods_id
     */
    private String goodsId;

    /**
     * 物资名称
     * 表字段 : inq_inquiry_order_goods.name
     */
    private String name;

    /**
     * 编码
     * 表字段 : inq_inquiry_order_goods.code
     */
    private String code;

    /**
     * 类型
     * 表字段 : inq_inquiry_order_goods.type
     */
    private String type;

    /**
     * 品牌名称
     * 表字段 : inq_inquiry_order_goods.brand
     */
    private String brand;

    /**
     * 颜色
     * 表字段 : inq_inquiry_order_goods.color
     */
    private String color;

    /**
     * 单位
     * 表字段 : inq_inquiry_order_goods.unit
     */
    private String unit;

    /**
     * 规格说明
     * 表字段 : inq_inquiry_order_goods.norm
     */
    private String norm;

    /**
     * 备注
     * 表字段 : inq_inquiry_order_goods.remark
     */
    private String remark;

    /**
     * 询价物资使用情况，0新购，1更换
     * 表字段 : inq_inquiry_order_goods.isreplace
     */
    private Integer isreplace;

    /**
     * 询价单价
     * 表字段 : inq_inquiry_order_goods.unit_price
     */
    private BigDecimal unitPrice;

    /**
     * 询价数量
     * 表字段 : inq_inquiry_order_goods.quantity
     */
    private Integer quantity;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table inq_inquiry_order_goods
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * 获取 主键ID 字段:inq_inquiry_order_goods.id
     *
     * @return inq_inquiry_order_goods.id, 主键ID
     */
    public String getId() {
        return id;
    }

    /**
     * 设置 主键ID 字段:inq_inquiry_order_goods.id
     *
     * @param id the value for inq_inquiry_order_goods.id, 主键ID
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 获取 询价单ID 字段:inq_inquiry_order_goods.inquiry_id
     *
     * @return inq_inquiry_order_goods.inquiry_id, 询价单ID
     */
    public String getInquiryId() {
        return inquiryId;
    }

    /**
     * 设置 询价单ID 字段:inq_inquiry_order_goods.inquiry_id
     *
     * @param inquiryId the value for inq_inquiry_order_goods.inquiry_id, 询价单ID
     */
    public void setInquiryId(String inquiryId) {
        this.inquiryId = inquiryId == null ? null : inquiryId.trim();
    }

    /**
     * 获取 物资ID 字段:inq_inquiry_order_goods.goods_id
     *
     * @return inq_inquiry_order_goods.goods_id, 物资ID
     */
    public String getGoodsId() {
        return goodsId;
    }

    /**
     * 设置 物资ID 字段:inq_inquiry_order_goods.goods_id
     *
     * @param goodsId the value for inq_inquiry_order_goods.goods_id, 物资ID
     */
    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId == null ? null : goodsId.trim();
    }

    /**
     * 获取 物资名称 字段:inq_inquiry_order_goods.name
     *
     * @return inq_inquiry_order_goods.name, 物资名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置 物资名称 字段:inq_inquiry_order_goods.name
     *
     * @param name the value for inq_inquiry_order_goods.name, 物资名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取 编码 字段:inq_inquiry_order_goods.code
     *
     * @return inq_inquiry_order_goods.code, 编码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置 编码 字段:inq_inquiry_order_goods.code
     *
     * @param code the value for inq_inquiry_order_goods.code, 编码
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 获取 类型 字段:inq_inquiry_order_goods.type
     *
     * @return inq_inquiry_order_goods.type, 类型
     */
    public String getType() {
        return type;
    }

    /**
     * 设置 类型 字段:inq_inquiry_order_goods.type
     *
     * @param type the value for inq_inquiry_order_goods.type, 类型
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * 获取 品牌名称 字段:inq_inquiry_order_goods.brand
     *
     * @return inq_inquiry_order_goods.brand, 品牌名称
     */
    public String getBrand() {
        return brand;
    }

    /**
     * 设置 品牌名称 字段:inq_inquiry_order_goods.brand
     *
     * @param brand the value for inq_inquiry_order_goods.brand, 品牌名称
     */
    public void setBrand(String brand) {
        this.brand = brand == null ? null : brand.trim();
    }

    /**
     * 获取 颜色 字段:inq_inquiry_order_goods.color
     *
     * @return inq_inquiry_order_goods.color, 颜色
     */
    public String getColor() {
        return color;
    }

    /**
     * 设置 颜色 字段:inq_inquiry_order_goods.color
     *
     * @param color the value for inq_inquiry_order_goods.color, 颜色
     */
    public void setColor(String color) {
        this.color = color == null ? null : color.trim();
    }

    /**
     * 获取 单位 字段:inq_inquiry_order_goods.unit
     *
     * @return inq_inquiry_order_goods.unit, 单位
     */
    public String getUnit() {
        return unit;
    }

    /**
     * 设置 单位 字段:inq_inquiry_order_goods.unit
     *
     * @param unit the value for inq_inquiry_order_goods.unit, 单位
     */
    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    /**
     * 获取 规格说明 字段:inq_inquiry_order_goods.norm
     *
     * @return inq_inquiry_order_goods.norm, 规格说明
     */
    public String getNorm() {
        return norm;
    }

    /**
     * 设置 规格说明 字段:inq_inquiry_order_goods.norm
     *
     * @param norm the value for inq_inquiry_order_goods.norm, 规格说明
     */
    public void setNorm(String norm) {
        this.norm = norm == null ? null : norm.trim();
    }

    /**
     * 获取 备注 字段:inq_inquiry_order_goods.remark
     *
     * @return inq_inquiry_order_goods.remark, 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 备注 字段:inq_inquiry_order_goods.remark
     *
     * @param remark the value for inq_inquiry_order_goods.remark, 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 获取 询价物资使用情况，0新购，1更换 字段:inq_inquiry_order_goods.isreplace
     *
     * @return inq_inquiry_order_goods.isreplace, 询价物资使用情况，0新购，1更换
     */
    public Integer getIsreplace() {
        return isreplace;
    }

    /**
     * 设置 询价物资使用情况，0新购，1更换 字段:inq_inquiry_order_goods.isreplace
     *
     * @param isreplace the value for inq_inquiry_order_goods.isreplace, 询价物资使用情况，0新购，1更换
     */
    public void setIsreplace(Integer isreplace) {
        this.isreplace = isreplace;
    }

    /**
     * 获取 询价单价 字段:inq_inquiry_order_goods.unit_price
     *
     * @return inq_inquiry_order_goods.unit_price, 询价单价
     */
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    /**
     * 设置 询价单价 字段:inq_inquiry_order_goods.unit_price
     *
     * @param unitPrice the value for inq_inquiry_order_goods.unit_price, 询价单价
     */
    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    /**
     * 获取 询价数量 字段:inq_inquiry_order_goods.quantity
     *
     * @return inq_inquiry_order_goods.quantity, 询价数量
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * 设置 询价数量 字段:inq_inquiry_order_goods.quantity
     *
     * @param quantity the value for inq_inquiry_order_goods.quantity, 询价数量
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * ,inq_inquiry_order_goods
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", inquiryId=").append(inquiryId);
        sb.append(", goodsId=").append(goodsId);
        sb.append(", name=").append(name);
        sb.append(", code=").append(code);
        sb.append(", type=").append(type);
        sb.append(", brand=").append(brand);
        sb.append(", color=").append(color);
        sb.append(", unit=").append(unit);
        sb.append(", norm=").append(norm);
        sb.append(", remark=").append(remark);
        sb.append(", isreplace=").append(isreplace);
        sb.append(", unitPrice=").append(unitPrice);
        sb.append(", quantity=").append(quantity);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}