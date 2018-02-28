package com.ddsh.goods.service.dao;

import com.ddsh.goods.service.api.model.GoodsBrandInfo;
import com.ddsh.goods.service.api.model.GoodsBrandInfoPojo;
import java.util.List;

public interface GoodsBrandInfoMapper {
    long countByExample(GoodsBrandInfoPojo example);

    int deleteByPrimaryKey(String id);

    int insert(GoodsBrandInfo record);

    int insertSelective(GoodsBrandInfo record);

    List<GoodsBrandInfo> selectByExample(GoodsBrandInfoPojo example);

    GoodsBrandInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GoodsBrandInfo record);

    int updateByPrimaryKey(GoodsBrandInfo record);
}