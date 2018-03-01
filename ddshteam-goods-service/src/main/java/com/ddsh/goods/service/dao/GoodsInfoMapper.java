package com.ddsh.goods.service.dao;

import com.ddsh.goods.service.api.model.GoodsInfo;
import com.ddsh.goods.service.api.model.GoodsInfoCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GoodsInfoMapper {
    long countByExample(GoodsInfoCriteria example);

    int deleteByExample(GoodsInfoCriteria example);

    int deleteByPrimaryKey(String id);

    int insert(GoodsInfo record);

    int insertSelective(GoodsInfo record);

    List<GoodsInfo> selectByExample(GoodsInfoCriteria example);

    GoodsInfo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") GoodsInfo record, @Param("example") GoodsInfoCriteria example);

    int updateByExample(@Param("record") GoodsInfo record, @Param("example") GoodsInfoCriteria example);

    int updateByPrimaryKeySelective(GoodsInfo record);

    int updateByPrimaryKey(GoodsInfo record);
}