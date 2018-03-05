package com.ddsh.goods.service.dao;

import com.ddsh.goods.service.api.data.GoodsInfoRespData;
import com.ddsh.goods.service.api.model.GoodsInfoCriteria;
import java.util.List;

public interface GoodsInfoCustomizeMapper {
 
    List<GoodsInfoRespData> selectByExample(GoodsInfoCriteria example);

    GoodsInfoRespData selectByPrimaryKey(String id);
 
}