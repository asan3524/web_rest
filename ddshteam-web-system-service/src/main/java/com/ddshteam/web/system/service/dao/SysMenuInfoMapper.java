package com.ddshteam.web.system.service.dao;

import com.ddshteam.web.system.service.api.model.SysMenuInfo;
import com.ddshteam.web.system.service.api.model.SysMenuInfoCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysMenuInfoMapper {
    long countByExample(SysMenuInfoCriteria example);

    int deleteByExample(SysMenuInfoCriteria example);

    int deleteByPrimaryKey(String id);

    int insert(SysMenuInfo record);

    int insertSelective(SysMenuInfo record);

    List<SysMenuInfo> selectByExample(SysMenuInfoCriteria example);

    SysMenuInfo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SysMenuInfo record, @Param("example") SysMenuInfoCriteria example);

    int updateByExample(@Param("record") SysMenuInfo record, @Param("example") SysMenuInfoCriteria example);

    int updateByPrimaryKeySelective(SysMenuInfo record);

    int updateByPrimaryKey(SysMenuInfo record);
}