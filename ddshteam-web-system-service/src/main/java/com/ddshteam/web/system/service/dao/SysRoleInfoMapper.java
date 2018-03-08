package com.ddshteam.web.system.service.dao;

import com.ddshteam.web.system.service.api.model.SysRoleInfo;
import com.ddshteam.web.system.service.api.model.SysRoleInfoCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysRoleInfoMapper {
    long countByExample(SysRoleInfoCriteria example);

    int deleteByExample(SysRoleInfoCriteria example);

    int deleteByPrimaryKey(String id);

    int insert(SysRoleInfo record);

    int insertSelective(SysRoleInfo record);

    List<SysRoleInfo> selectByExample(SysRoleInfoCriteria example);

    SysRoleInfo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SysRoleInfo record, @Param("example") SysRoleInfoCriteria example);

    int updateByExample(@Param("record") SysRoleInfo record, @Param("example") SysRoleInfoCriteria example);

    int updateByPrimaryKeySelective(SysRoleInfo record);

    int updateByPrimaryKey(SysRoleInfo record);
}