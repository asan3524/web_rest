package com.ddshteam.web.system.service.dao;

import com.ddshteam.web.system.service.api.model.SysRoleToUser;
import com.ddshteam.web.system.service.api.model.SysRoleToUserCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysRoleToUserMapper {
    long countByExample(SysRoleToUserCriteria example);

    int deleteByExample(SysRoleToUserCriteria example);

    int deleteByPrimaryKey(String id);

    int insert(SysRoleToUser record);

    int insertSelective(SysRoleToUser record);

    List<SysRoleToUser> selectByExample(SysRoleToUserCriteria example);

    SysRoleToUser selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SysRoleToUser record, @Param("example") SysRoleToUserCriteria example);

    int updateByExample(@Param("record") SysRoleToUser record, @Param("example") SysRoleToUserCriteria example);

    int updateByPrimaryKeySelective(SysRoleToUser record);

    int updateByPrimaryKey(SysRoleToUser record);
}