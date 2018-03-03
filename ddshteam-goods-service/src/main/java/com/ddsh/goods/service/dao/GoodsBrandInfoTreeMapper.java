package com.ddsh.goods.service.dao;

import com.ddsh.goods.service.api.model.GoodsBrandInfoCriteria;
import com.ddshteam.web.system.service.api.data.Tree;

import java.util.List;

public interface GoodsBrandInfoTreeMapper {
	
    List<Tree> selectByExample(GoodsBrandInfoCriteria example);

    List<Tree> selectByPrimaryKey(String id);
 }