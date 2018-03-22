package com.ddshteam.web.system.service.dao;

import com.ddshteam.web.system.service.api.model.SysDepInfo;
import com.ddshteam.web.system.service.api.model.SysDepInfoCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysDepInfoMapper {
    /**
     *  根据指定的条件获取数据库记录数,sys_dep_info
     *
     * @param example
     */
    long countByExample(SysDepInfoCriteria example);

    /**
     *  根据指定的条件删除数据库符合条件的记录,sys_dep_info
     *
     * @param example
     */
    int deleteByExample(SysDepInfoCriteria example);

    /**
     *  根据主键删除数据库的记录,sys_dep_info
     *
     * @param id
     */
    int deleteByPrimaryKey(String id);

    /**
     *  新写入数据库记录,sys_dep_info
     *
     * @param record
     */
    int insert(SysDepInfo record);

    /**
     *  动态字段,写入数据库记录,sys_dep_info
     *
     * @param record
     */
    int insertSelective(SysDepInfo record);

    /**
     *  根据指定的条件查询符合条件的数据库记录,sys_dep_info
     *
     * @param example
     */
    List<SysDepInfo> selectByExample(SysDepInfoCriteria example);

    /**
     *  根据指定主键获取一条数据库记录,sys_dep_info
     *
     * @param id
     */
    SysDepInfo selectByPrimaryKey(String id);

    /**
     *  动态根据指定的条件来更新符合条件的数据库记录,sys_dep_info
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") SysDepInfo record, @Param("example") SysDepInfoCriteria example);

    /**
     *  根据指定的条件来更新符合条件的数据库记录,sys_dep_info
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") SysDepInfo record, @Param("example") SysDepInfoCriteria example);

    /**
     *  动态字段,根据主键来更新符合条件的数据库记录,sys_dep_info
     *
     * @param record
     */
    int updateByPrimaryKeySelective(SysDepInfo record);

    /**
     *  根据主键来更新符合条件的数据库记录,sys_dep_info
     *
     * @param record
     */
    int updateByPrimaryKey(SysDepInfo record);

    int insertBatchSelective(List<SysDepInfo> records);

    int updateBatchByPrimaryKeySelective(List<SysDepInfo> records);
}