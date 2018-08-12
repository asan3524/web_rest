package com.ddsh.util.service.dao;

import com.ddsh.util.service.api.model.PurPurchaseOrder;
import com.ddsh.util.service.api.model.PurPurchaseOrderCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PurPurchaseOrderMapper {
    /**
     *  根据指定的条件获取数据库记录数,pur_purchase_order
     *
     * @param example
     */
    long countByExample(PurPurchaseOrderCriteria example);

    /**
     *  根据指定的条件删除数据库符合条件的记录,pur_purchase_order
     *
     * @param example
     */
    int deleteByExample(PurPurchaseOrderCriteria example);

    /**
     *  根据主键删除数据库的记录,pur_purchase_order
     *
     * @param id
     */
    int deleteByPrimaryKey(String id);

    /**
     *  新写入数据库记录,pur_purchase_order
     *
     * @param record
     */
    int insert(PurPurchaseOrder record);

    /**
     *  动态字段,写入数据库记录,pur_purchase_order
     *
     * @param record
     */
    int insertSelective(PurPurchaseOrder record);

    /**
     *  根据指定的条件查询符合条件的数据库记录,pur_purchase_order
     *
     * @param example
     */
    List<PurPurchaseOrder> selectByExample(PurPurchaseOrderCriteria example);

    /**
     *  根据指定主键获取一条数据库记录,pur_purchase_order
     *
     * @param id
     */
    PurPurchaseOrder selectByPrimaryKey(String id);

    /**
     *  动态根据指定的条件来更新符合条件的数据库记录,pur_purchase_order
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PurPurchaseOrder record, @Param("example") PurPurchaseOrderCriteria example);

    /**
     *  根据指定的条件来更新符合条件的数据库记录,pur_purchase_order
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PurPurchaseOrder record, @Param("example") PurPurchaseOrderCriteria example);

    /**
     *  动态字段,根据主键来更新符合条件的数据库记录,pur_purchase_order
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PurPurchaseOrder record);

    /**
     *  根据主键来更新符合条件的数据库记录,pur_purchase_order
     *
     * @param record
     */
    int updateByPrimaryKey(PurPurchaseOrder record);

    int insertBatchSelective(List<PurPurchaseOrder> records);

    int updateBatchByPrimaryKeySelective(List<PurPurchaseOrder> records);
}