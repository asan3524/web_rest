package com.ddshteam.web.system.service.dao;

import com.ddshteam.web.system.service.api.model.SysDeptypeInfo;
import com.ddshteam.web.system.service.api.model.SysDeptypeInfoCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysDeptypeInfoMapper {
    long countByExample(SysDeptypeInfoCriteria example);

    int deleteByExample(SysDeptypeInfoCriteria example);

    int deleteByPrimaryKey(String id);

    int insert(SysDeptypeInfo record);

    int insertSelective(SysDeptypeInfo record);

    List<SysDeptypeInfo> selectByExample(SysDeptypeInfoCriteria example);

    SysDeptypeInfo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SysDeptypeInfo record, @Param("example") SysDeptypeInfoCriteria example);

    int updateByExample(@Param("record") SysDeptypeInfo record, @Param("example") SysDeptypeInfoCriteria example);

    int updateByPrimaryKeySelective(SysDeptypeInfo record);

    int updateByPrimaryKey(SysDeptypeInfo record);
}