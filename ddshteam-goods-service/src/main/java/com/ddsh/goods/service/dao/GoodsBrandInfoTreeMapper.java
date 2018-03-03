package com.ddsh.goods.service.dao;

import com.ddshteam.web.system.service.api.data.Tree;

import java.util.List;

public interface GoodsBrandInfoTreeMapper {
	
    List<Tree> selectByPrimaryKey(String id);
 }