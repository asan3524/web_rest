package com.ddshteam.web.system.service.dao;

import com.ddshteam.web.system.service.api.model.SysUserInfo;
import com.ddshteam.web.system.service.api.model.SysUserInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysUserInfoMapper {
    long countByExample(SysUserInfoExample example);

    int deleteByExample(SysUserInfoExample example);

    int deleteByPrimaryKey(String id);

    int insert(SysUserInfo record);

    int insertSelective(SysUserInfo record);

    List<SysUserInfo> selectByExample(SysUserInfoExample example);

    SysUserInfo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SysUserInfo record, @Param("example") SysUserInfoExample example);

    int updateByExample(@Param("record") SysUserInfo record, @Param("example") SysUserInfoExample example);

    int updateByPrimaryKeySelective(SysUserInfo record);

    int updateByPrimaryKey(SysUserInfo record);
}