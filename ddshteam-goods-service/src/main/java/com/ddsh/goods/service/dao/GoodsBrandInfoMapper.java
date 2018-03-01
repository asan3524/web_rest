package com.ddsh.goods.service.dao;

import com.ddsh.goods.service.api.model.GoodsBrandInfo;
import com.ddsh.goods.service.api.model.GoodsBrandInfoCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GoodsBrandInfoMapper {
    long countByExample(GoodsBrandInfoCriteria example);

    int deleteByExample(GoodsBrandInfoCriteria example);

    int deleteByPrimaryKey(String id);

    int insert(GoodsBrandInfo record);

    int insertSelective(GoodsBrandInfo record);

    List<GoodsBrandInfo> selectByExample(GoodsBrandInfoCriteria example);

    GoodsBrandInfo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") GoodsBrandInfo record, @Param("example") GoodsBrandInfoCriteria example);

    int updateByExample(@Param("record") GoodsBrandInfo record, @Param("example") GoodsBrandInfoCriteria example);

    int updateByPrimaryKeySelective(GoodsBrandInfo record);

    int updateByPrimaryKey(GoodsBrandInfo record);
}