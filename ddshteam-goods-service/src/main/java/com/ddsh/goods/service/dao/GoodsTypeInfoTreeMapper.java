package com.ddsh.goods.service.dao;

import com.ddsh.goods.service.api.model.GoodsTypeInfoCriteria;
import com.ddshteam.web.system.service.api.data.Tree;

import java.util.List;

public interface GoodsTypeInfoTreeMapper {
	
    List<Tree> selectByExample(GoodsTypeInfoCriteria example);

    List<Tree> selectByPrimaryKey(String id);
    
}