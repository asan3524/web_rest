package com.ddshteam.web.system.service.dao;

import com.ddshteam.web.system.service.api.model.SysDepInfo;
import com.ddshteam.web.system.service.api.model.SysDepInfoCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysDepInfoMapper {
    long countByExample(SysDepInfoCriteria example);

    int deleteByExample(SysDepInfoCriteria example);

    int deleteByPrimaryKey(String id);

    int insert(SysDepInfo record);

    int insertSelective(SysDepInfo record);

    List<SysDepInfo> selectByExample(SysDepInfoCriteria example);

    SysDepInfo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SysDepInfo record, @Param("example") SysDepInfoCriteria example);

    int updateByExample(@Param("record") SysDepInfo record, @Param("example") SysDepInfoCriteria example);

    int updateByPrimaryKeySelective(SysDepInfo record);

    int updateByPrimaryKey(SysDepInfo record);
}