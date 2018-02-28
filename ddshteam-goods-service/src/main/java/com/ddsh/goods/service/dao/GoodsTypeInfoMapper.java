package com.ddsh.goods.service.dao;

import com.ddsh.goods.service.api.model.GoodsTypeInfo;
import com.ddsh.goods.service.api.model.GoodsTypeInfoPojo;
import java.util.List;

public interface GoodsTypeInfoMapper {
    long countByExample(GoodsTypeInfoPojo example);

    int deleteByPrimaryKey(String id);

    int insert(GoodsTypeInfo record);

    int insertSelective(GoodsTypeInfo record);

    List<GoodsTypeInfo> selectByExample(GoodsTypeInfoPojo example);

    GoodsTypeInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GoodsTypeInfo record);

    int updateByPrimaryKey(GoodsTypeInfo record);
}