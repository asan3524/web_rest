package com.ddsh.util.service.dao;

import com.ddsh.util.service.api.model.PurPurchaseToInquiry;
import com.ddsh.util.service.api.model.PurPurchaseToInquiryCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PurPurchaseToInquiryMapper {
    /**
     *  根据指定的条件获取数据库记录数,pur_purchase_to_inquiry
     *
     * @param example
     */
    long countByExample(PurPurchaseToInquiryCriteria example);

    /**
     *  根据指定的条件删除数据库符合条件的记录,pur_purchase_to_inquiry
     *
     * @param example
     */
    int deleteByExample(PurPurchaseToInquiryCriteria example);

    /**
     *  根据主键删除数据库的记录,pur_purchase_to_inquiry
     *
     * @param id
     */
    int deleteByPrimaryKey(String id);

    /**
     *  新写入数据库记录,pur_purchase_to_inquiry
     *
     * @param record
     */
    int insert(PurPurchaseToInquiry record);

    /**
     *  动态字段,写入数据库记录,pur_purchase_to_inquiry
     *
     * @param record
     */
    int insertSelective(PurPurchaseToInquiry record);

    /**
     *  根据指定的条件查询符合条件的数据库记录,pur_purchase_to_inquiry
     *
     * @param example
     */
    List<PurPurchaseToInquiry> selectByExample(PurPurchaseToInquiryCriteria example);

    /**
     *  根据指定主键获取一条数据库记录,pur_purchase_to_inquiry
     *
     * @param id
     */
    PurPurchaseToInquiry selectByPrimaryKey(String id);

    /**
     *  动态根据指定的条件来更新符合条件的数据库记录,pur_purchase_to_inquiry
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PurPurchaseToInquiry record, @Param("example") PurPurchaseToInquiryCriteria example);

    /**
     *  根据指定的条件来更新符合条件的数据库记录,pur_purchase_to_inquiry
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PurPurchaseToInquiry record, @Param("example") PurPurchaseToInquiryCriteria example);

    /**
     *  动态字段,根据主键来更新符合条件的数据库记录,pur_purchase_to_inquiry
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PurPurchaseToInquiry record);

    /**
     *  根据主键来更新符合条件的数据库记录,pur_purchase_to_inquiry
     *
     * @param record
     */
    int updateByPrimaryKey(PurPurchaseToInquiry record);

    int insertBatchSelective(List<PurPurchaseToInquiry> records);

    int updateBatchByPrimaryKeySelective(List<PurPurchaseToInquiry> records);
}