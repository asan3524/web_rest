package com.ddsh.goods.service.dao;

import com.ddsh.goods.service.api.model.GoodsTypeInfo;
import com.ddsh.goods.service.api.model.GoodsTypeInfoCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GoodsTypeInfoMapper {
    long countByExample(GoodsTypeInfoCriteria example);

    int deleteByExample(GoodsTypeInfoCriteria example);

    int deleteByPrimaryKey(String id);

    int insert(GoodsTypeInfo record);

    int insertSelective(GoodsTypeInfo record);

    List<GoodsTypeInfo> selectByExample(GoodsTypeInfoCriteria example);

    GoodsTypeInfo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") GoodsTypeInfo record, @Param("example") GoodsTypeInfoCriteria example);

    int updateByExample(@Param("record") GoodsTypeInfo record, @Param("example") GoodsTypeInfoCriteria example);

    int updateByPrimaryKeySelective(GoodsTypeInfo record);

    int updateByPrimaryKey(GoodsTypeInfo record);
}