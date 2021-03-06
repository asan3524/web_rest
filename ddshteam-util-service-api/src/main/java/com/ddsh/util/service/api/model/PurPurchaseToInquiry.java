package com.ddsh.util.service.api.model;

import java.io.Serializable;
import java.util.List;

public class PurPurchaseToInquiry implements Serializable {
    /**
     * 主键ID
     * 表字段 : pur_purchase_to_inquiry.id
     */
    private String id;

    /**
     * 采购单ID
     * 表字段 : pur_purchase_to_inquiry.purchase_id
     */
    private String purchaseId;

    /**
     * 询价单ID，采购时选择的询价单必须是未失效、已完成、审核意见为采购的询价单
     * 表字段 : pur_purchase_to_inquiry.inquiry_id
     */
    private String inquiryId;

    /**
     * 询价单编码
     * 表字段 : pur_purchase_to_inquiry.code
     */
    private String code;

    /**
     * 询价单名称
     * 表字段 : pur_purchase_to_inquiry.name
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table pur_purchase_to_inquiry
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    private List<PurPurchaseOrderGoods> purPurchaseOrderGoodss;

    /**
     * 获取 主键ID 字段:pur_purchase_to_inquiry.id
     *
     * @return pur_purchase_to_inquiry.id, 主键ID
     */
    public String getId() {
        return id;
    }

    /**
     * 设置 主键ID 字段:pur_purchase_to_inquiry.id
     *
     * @param id the value for pur_purchase_to_inquiry.id, 主键ID
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 获取 采购单ID 字段:pur_purchase_to_inquiry.purchase_id
     *
     * @return pur_purchase_to_inquiry.purchase_id, 采购单ID
     */
    public String getPurchaseId() {
        return purchaseId;
    }

    /**
     * 设置 采购单ID 字段:pur_purchase_to_inquiry.purchase_id
     *
     * @param purchaseId the value for pur_purchase_to_inquiry.purchase_id, 采购单ID
     */
    public void setPurchaseId(String purchaseId) {
        this.purchaseId = purchaseId == null ? null : purchaseId.trim();
    }

    /**
     * 获取 询价单ID，采购时选择的询价单必须是未失效、已完成、审核意见为采购的询价单 字段:pur_purchase_to_inquiry.inquiry_id
     *
     * @return pur_purchase_to_inquiry.inquiry_id, 询价单ID，采购时选择的询价单必须是未失效、已完成、审核意见为采购的询价单
     */
    public String getInquiryId() {
        return inquiryId;
    }

    /**
     * 设置 询价单ID，采购时选择的询价单必须是未失效、已完成、审核意见为采购的询价单 字段:pur_purchase_to_inquiry.inquiry_id
     *
     * @param inquiryId the value for pur_purchase_to_inquiry.inquiry_id, 询价单ID，采购时选择的询价单必须是未失效、已完成、审核意见为采购的询价单
     */
    public void setInquiryId(String inquiryId) {
        this.inquiryId = inquiryId == null ? null : inquiryId.trim();
    }

    /**
     * 获取 询价单编码 字段:pur_purchase_to_inquiry.code
     *
     * @return pur_purchase_to_inquiry.code, 询价单编码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置 询价单编码 字段:pur_purchase_to_inquiry.code
     *
     * @param code the value for pur_purchase_to_inquiry.code, 询价单编码
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 获取 询价单名称 字段:pur_purchase_to_inquiry.name
     *
     * @return pur_purchase_to_inquiry.name, 询价单名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置 询价单名称 字段:pur_purchase_to_inquiry.name
     *
     * @param name the value for pur_purchase_to_inquiry.name, 询价单名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * ,pur_purchase_to_inquiry
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", purchaseId=").append(purchaseId);
        sb.append(", inquiryId=").append(inquiryId);
        sb.append(", code=").append(code);
        sb.append(", name=").append(name);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public List<PurPurchaseOrderGoods> getPurPurchaseOrderGoodss() {
        return purPurchaseOrderGoodss;
    }

    public void setPurPurchaseOrderGoodss(List<PurPurchaseOrderGoods> purPurchaseOrderGoodss) {
        this.purPurchaseOrderGoodss=purPurchaseOrderGoodss;
    }
}