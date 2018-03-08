package com.ddshteam.web.system.service.dao;

import com.ddshteam.web.system.service.api.model.SysOpLogs;
import com.ddshteam.web.system.service.api.model.SysOpLogsCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysOpLogsMapper {
    long countByExample(SysOpLogsCriteria example);

    int deleteByExample(SysOpLogsCriteria example);

    int deleteByPrimaryKey(String id);

    int insert(SysOpLogs record);

    int insertSelective(SysOpLogs record);

    List<SysOpLogs> selectByExample(SysOpLogsCriteria example);

    SysOpLogs selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SysOpLogs record, @Param("example") SysOpLogsCriteria example);

    int updateByExample(@Param("record") SysOpLogs record, @Param("example") SysOpLogsCriteria example);

    int updateByPrimaryKeySelective(SysOpLogs record);

    int updateByPrimaryKey(SysOpLogs record);
}