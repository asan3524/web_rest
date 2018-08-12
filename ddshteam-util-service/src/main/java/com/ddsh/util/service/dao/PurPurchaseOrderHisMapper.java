package com.ddsh.util.service.dao;

import com.ddsh.util.service.api.model.PurPurchaseOrderHis;
import com.ddsh.util.service.api.model.PurPurchaseOrderHisCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PurPurchaseOrderHisMapper {
    /**
     *  根据指定的条件获取数据库记录数,pur_purchase_order_his
     *
     * @param example
     */
    long countByExample(PurPurchaseOrderHisCriteria example);

    /**
     *  根据指定的条件删除数据库符合条件的记录,pur_purchase_order_his
     *
     * @param example
     */
    int deleteByExample(PurPurchaseOrderHisCriteria example);

    /**
     *  根据主键删除数据库的记录,pur_purchase_order_his
     *
     * @param id
     */
    int deleteByPrimaryKey(String id);

    /**
     *  新写入数据库记录,pur_purchase_order_his
     *
     * @param record
     */
    int insert(PurPurchaseOrderHis record);

    /**
     *  动态字段,写入数据库记录,pur_purchase_order_his
     *
     * @param record
     */
    int insertSelective(PurPurchaseOrderHis record);

    /**
     *  根据指定的条件查询符合条件的数据库记录,pur_purchase_order_his
     *
     * @param example
     */
    List<PurPurchaseOrderHis> selectByExample(PurPurchaseOrderHisCriteria example);

    /**
     *  根据指定主键获取一条数据库记录,pur_purchase_order_his
     *
     * @param id
     */
    PurPurchaseOrderHis selectByPrimaryKey(String id);

    /**
     *  动态根据指定的条件来更新符合条件的数据库记录,pur_purchase_order_his
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PurPurchaseOrderHis record, @Param("example") PurPurchaseOrderHisCriteria example);

    /**
     *  根据指定的条件来更新符合条件的数据库记录,pur_purchase_order_his
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PurPurchaseOrderHis record, @Param("example") PurPurchaseOrderHisCriteria example);

    /**
     *  动态字段,根据主键来更新符合条件的数据库记录,pur_purchase_order_his
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PurPurchaseOrderHis record);

    /**
     *  根据主键来更新符合条件的数据库记录,pur_purchase_order_his
     *
     * @param record
     */
    int updateByPrimaryKey(PurPurchaseOrderHis record);

    int insertBatchSelective(List<PurPurchaseOrderHis> records);

    int updateBatchByPrimaryKeySelective(List<PurPurchaseOrderHis> records);
}