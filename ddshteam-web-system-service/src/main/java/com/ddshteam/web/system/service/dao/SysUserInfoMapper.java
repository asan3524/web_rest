package com.ddshteam.web.system.service.dao;

import com.ddshteam.web.system.service.api.model.SysUserInfo;
import com.ddshteam.web.system.service.api.model.SysUserInfoCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysUserInfoMapper {
    long countByExample(SysUserInfoCriteria example);

    int deleteByExample(SysUserInfoCriteria example);

    int deleteByPrimaryKey(String id);

    int insert(SysUserInfo record);

    int insertSelective(SysUserInfo record);

    List<SysUserInfo> selectByExample(SysUserInfoCriteria example);

    SysUserInfo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SysUserInfo record, @Param("example") SysUserInfoCriteria example);

    int updateByExample(@Param("record") SysUserInfo record, @Param("example") SysUserInfoCriteria example);

    int updateByPrimaryKeySelective(SysUserInfo record);

    int updateByPrimaryKey(SysUserInfo record);
}