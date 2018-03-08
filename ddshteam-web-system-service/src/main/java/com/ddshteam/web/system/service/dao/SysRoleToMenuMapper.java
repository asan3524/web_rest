package com.ddshteam.web.system.service.dao;

import com.ddshteam.web.system.service.api.model.SysRoleToMenu;
import com.ddshteam.web.system.service.api.model.SysRoleToMenuCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysRoleToMenuMapper {
    long countByExample(SysRoleToMenuCriteria example);

    int deleteByExample(SysRoleToMenuCriteria example);

    int deleteByPrimaryKey(String id);

    int insert(SysRoleToMenu record);

    int insertSelective(SysRoleToMenu record);

    List<SysRoleToMenu> selectByExample(SysRoleToMenuCriteria example);

    SysRoleToMenu selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SysRoleToMenu record, @Param("example") SysRoleToMenuCriteria example);

    int updateByExample(@Param("record") SysRoleToMenu record, @Param("example") SysRoleToMenuCriteria example);

    int updateByPrimaryKeySelective(SysRoleToMenu record);

    int updateByPrimaryKey(SysRoleToMenu record);
}