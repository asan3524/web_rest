package com.ddsh.util.service.dao;

import com.ddsh.util.service.api.model.PurPurchaseToStore;
import com.ddsh.util.service.api.model.PurPurchaseToStoreCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PurPurchaseToStoreMapper {
    /**
     *  根据指定的条件获取数据库记录数,pur_purchase_to_store
     *
     * @param example
     */
    long countByExample(PurPurchaseToStoreCriteria example);

    /**
     *  根据指定的条件删除数据库符合条件的记录,pur_purchase_to_store
     *
     * @param example
     */
    int deleteByExample(PurPurchaseToStoreCriteria example);

    /**
     *  根据主键删除数据库的记录,pur_purchase_to_store
     *
     * @param id
     */
    int deleteByPrimaryKey(String id);

    /**
     *  新写入数据库记录,pur_purchase_to_store
     *
     * @param record
     */
    int insert(PurPurchaseToStore record);

    /**
     *  动态字段,写入数据库记录,pur_purchase_to_store
     *
     * @param record
     */
    int insertSelective(PurPurchaseToStore record);

    /**
     *  根据指定的条件查询符合条件的数据库记录,pur_purchase_to_store
     *
     * @param example
     */
    List<PurPurchaseToStore> selectByExample(PurPurchaseToStoreCriteria example);

    /**
     *  根据指定主键获取一条数据库记录,pur_purchase_to_store
     *
     * @param id
     */
    PurPurchaseToStore selectByPrimaryKey(String id);

    /**
     *  动态根据指定的条件来更新符合条件的数据库记录,pur_purchase_to_store
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PurPurchaseToStore record, @Param("example") PurPurchaseToStoreCriteria example);

    /**
     *  根据指定的条件来更新符合条件的数据库记录,pur_purchase_to_store
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PurPurchaseToStore record, @Param("example") PurPurchaseToStoreCriteria example);

    /**
     *  动态字段,根据主键来更新符合条件的数据库记录,pur_purchase_to_store
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PurPurchaseToStore record);

    /**
     *  根据主键来更新符合条件的数据库记录,pur_purchase_to_store
     *
     * @param record
     */
    int updateByPrimaryKey(PurPurchaseToStore record);

    int insertBatchSelective(List<PurPurchaseToStore> records);

    int updateBatchByPrimaryKeySelective(List<PurPurchaseToStore> records);
}