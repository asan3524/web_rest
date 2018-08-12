package com.ddsh.util.service.dao;

import com.ddsh.util.service.api.model.InqInquiryOrder;
import com.ddsh.util.service.api.model.InqInquiryOrderCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface InqInquiryOrderMapper {
    /**
     *  根据指定的条件获取数据库记录数,inq_inquiry_order
     *
     * @param example
     */
    long countByExample(InqInquiryOrderCriteria example);

    /**
     *  根据指定的条件删除数据库符合条件的记录,inq_inquiry_order
     *
     * @param example
     */
    int deleteByExample(InqInquiryOrderCriteria example);

    /**
     *  根据主键删除数据库的记录,inq_inquiry_order
     *
     * @param id
     */
    int deleteByPrimaryKey(String id);

    /**
     *  新写入数据库记录,inq_inquiry_order
     *
     * @param record
     */
    int insert(InqInquiryOrder record);

    /**
     *  动态字段,写入数据库记录,inq_inquiry_order
     *
     * @param record
     */
    int insertSelective(InqInquiryOrder record);

    /**
     *  根据指定的条件查询符合条件的数据库记录,inq_inquiry_order
     *
     * @param example
     */
    List<InqInquiryOrder> selectByExample(InqInquiryOrderCriteria example);

    /**
     *  根据指定主键获取一条数据库记录,inq_inquiry_order
     *
     * @param id
     */
    InqInquiryOrder selectByPrimaryKey(String id);

    /**
     *  动态根据指定的条件来更新符合条件的数据库记录,inq_inquiry_order
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") InqInquiryOrder record, @Param("example") InqInquiryOrderCriteria example);

    /**
     *  根据指定的条件来更新符合条件的数据库记录,inq_inquiry_order
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") InqInquiryOrder record, @Param("example") InqInquiryOrderCriteria example);

    /**
     *  动态字段,根据主键来更新符合条件的数据库记录,inq_inquiry_order
     *
     * @param record
     */
    int updateByPrimaryKeySelective(InqInquiryOrder record);

    /**
     *  根据主键来更新符合条件的数据库记录,inq_inquiry_order
     *
     * @param record
     */
    int updateByPrimaryKey(InqInquiryOrder record);

    int insertBatchSelective(List<InqInquiryOrder> records);

    int updateBatchByPrimaryKeySelective(List<InqInquiryOrder> records);
}