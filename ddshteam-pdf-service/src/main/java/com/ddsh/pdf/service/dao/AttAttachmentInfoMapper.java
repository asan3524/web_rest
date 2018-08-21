package com.ddsh.pdf.service.dao;

import com.ddsh.pdf.service.api.model.AttAttachmentInfo;
import com.ddsh.pdf.service.api.model.AttAttachmentInfoCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AttAttachmentInfoMapper {
    /**
     *  根据指定的条件获取数据库记录数,att_attachment_info
     *
     * @param example
     */
    long countByExample(AttAttachmentInfoCriteria example);

    /**
     *  根据指定的条件删除数据库符合条件的记录,att_attachment_info
     *
     * @param example
     */
    int deleteByExample(AttAttachmentInfoCriteria example);

    /**
     *  根据主键删除数据库的记录,att_attachment_info
     *
     * @param id
     */
    int deleteByPrimaryKey(String id);

    /**
     *  新写入数据库记录,att_attachment_info
     *
     * @param record
     */
    int insert(AttAttachmentInfo record);

    /**
     *  动态字段,写入数据库记录,att_attachment_info
     *
     * @param record
     */
    int insertSelective(AttAttachmentInfo record);

    /**
     *  根据指定的条件查询符合条件的数据库记录,att_attachment_info
     *
     * @param example
     */
    List<AttAttachmentInfo> selectByExample(AttAttachmentInfoCriteria example);

    /**
     *  根据指定主键获取一条数据库记录,att_attachment_info
     *
     * @param id
     */
    AttAttachmentInfo selectByPrimaryKey(String id);

    /**
     *  动态根据指定的条件来更新符合条件的数据库记录,att_attachment_info
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") AttAttachmentInfo record, @Param("example") AttAttachmentInfoCriteria example);

    /**
     *  根据指定的条件来更新符合条件的数据库记录,att_attachment_info
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") AttAttachmentInfo record, @Param("example") AttAttachmentInfoCriteria example);

    /**
     *  动态字段,根据主键来更新符合条件的数据库记录,att_attachment_info
     *
     * @param record
     */
    int updateByPrimaryKeySelective(AttAttachmentInfo record);

    /**
     *  根据主键来更新符合条件的数据库记录,att_attachment_info
     *
     * @param record
     */
    int updateByPrimaryKey(AttAttachmentInfo record);

    int insertBatchSelective(List<AttAttachmentInfo> records);

    int updateBatchByPrimaryKeySelective(List<AttAttachmentInfo> records);
}