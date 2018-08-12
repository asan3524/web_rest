package com.ddsh.util.service.dao;

import com.ddsh.util.service.api.model.PurPurchaseOrderGoods;
import com.ddsh.util.service.api.model.PurPurchaseOrderGoodsCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PurPurchaseOrderGoodsMapper {
    /**
     *  根据指定的条件获取数据库记录数,pur_purchase_order_goods
     *
     * @param example
     */
    long countByExample(PurPurchaseOrderGoodsCriteria example);

    /**
     *  根据指定的条件删除数据库符合条件的记录,pur_purchase_order_goods
     *
     * @param example
     */
    int deleteByExample(PurPurchaseOrderGoodsCriteria example);

    /**
     *  根据主键删除数据库的记录,pur_purchase_order_goods
     *
     * @param id
     */
    int deleteByPrimaryKey(String id);

    /**
     *  新写入数据库记录,pur_purchase_order_goods
     *
     * @param record
     */
    int insert(PurPurchaseOrderGoods record);

    /**
     *  动态字段,写入数据库记录,pur_purchase_order_goods
     *
     * @param record
     */
    int insertSelective(PurPurchaseOrderGoods record);

    /**
     *  根据指定的条件查询符合条件的数据库记录,pur_purchase_order_goods
     *
     * @param example
     */
    List<PurPurchaseOrderGoods> selectByExample(PurPurchaseOrderGoodsCriteria example);

    /**
     *  根据指定主键获取一条数据库记录,pur_purchase_order_goods
     *
     * @param id
     */
    PurPurchaseOrderGoods selectByPrimaryKey(String id);

    /**
     *  动态根据指定的条件来更新符合条件的数据库记录,pur_purchase_order_goods
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PurPurchaseOrderGoods record, @Param("example") PurPurchaseOrderGoodsCriteria example);

    /**
     *  根据指定的条件来更新符合条件的数据库记录,pur_purchase_order_goods
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PurPurchaseOrderGoods record, @Param("example") PurPurchaseOrderGoodsCriteria example);

    /**
     *  动态字段,根据主键来更新符合条件的数据库记录,pur_purchase_order_goods
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PurPurchaseOrderGoods record);

    /**
     *  根据主键来更新符合条件的数据库记录,pur_purchase_order_goods
     *
     * @param record
     */
    int updateByPrimaryKey(PurPurchaseOrderGoods record);

    int insertBatchSelective(List<PurPurchaseOrderGoods> records);

    int updateBatchByPrimaryKeySelective(List<PurPurchaseOrderGoods> records);
}