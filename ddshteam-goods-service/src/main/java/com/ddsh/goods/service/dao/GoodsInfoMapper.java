package com.ddsh.goods.service.dao;

import com.ddsh.goods.service.api.model.GoodsInfo;
import com.ddsh.goods.service.api.model.GoodsInfoPojo;
import java.util.List;

public interface GoodsInfoMapper {
    long countByExample(GoodsInfoPojo example);

    int deleteByPrimaryKey(String id);

    int insert(GoodsInfo record);

    int insertSelective(GoodsInfo record);

    List<GoodsInfo> selectByExample(GoodsInfoPojo example);

    GoodsInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GoodsInfo record);

    int updateByPrimaryKey(GoodsInfo record);
}