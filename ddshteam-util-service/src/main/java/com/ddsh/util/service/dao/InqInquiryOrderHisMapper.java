package com.ddsh.util.service.dao;

import com.ddsh.util.service.api.model.InqInquiryOrderHis;
import com.ddsh.util.service.api.model.InqInquiryOrderHisCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface InqInquiryOrderHisMapper {
    /**
     *  根据指定的条件获取数据库记录数,inq_inquiry_order_his
     *
     * @param example
     */
    long countByExample(InqInquiryOrderHisCriteria example);

    /**
     *  根据指定的条件删除数据库符合条件的记录,inq_inquiry_order_his
     *
     * @param example
     */
    int deleteByExample(InqInquiryOrderHisCriteria example);

    /**
     *  根据主键删除数据库的记录,inq_inquiry_order_his
     *
     * @param id
     */
    int deleteByPrimaryKey(String id);

    /**
     *  新写入数据库记录,inq_inquiry_order_his
     *
     * @param record
     */
    int insert(InqInquiryOrderHis record);

    /**
     *  动态字段,写入数据库记录,inq_inquiry_order_his
     *
     * @param record
     */
    int insertSelective(InqInquiryOrderHis record);

    /**
     *  根据指定的条件查询符合条件的数据库记录,inq_inquiry_order_his
     *
     * @param example
     */
    List<InqInquiryOrderHis> selectByExample(InqInquiryOrderHisCriteria example);

    /**
     *  根据指定主键获取一条数据库记录,inq_inquiry_order_his
     *
     * @param id
     */
    InqInquiryOrderHis selectByPrimaryKey(String id);

    /**
     *  动态根据指定的条件来更新符合条件的数据库记录,inq_inquiry_order_his
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") InqInquiryOrderHis record, @Param("example") InqInquiryOrderHisCriteria example);

    /**
     *  根据指定的条件来更新符合条件的数据库记录,inq_inquiry_order_his
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") InqInquiryOrderHis record, @Param("example") InqInquiryOrderHisCriteria example);

    /**
     *  动态字段,根据主键来更新符合条件的数据库记录,inq_inquiry_order_his
     *
     * @param record
     */
    int updateByPrimaryKeySelective(InqInquiryOrderHis record);

    /**
     *  根据主键来更新符合条件的数据库记录,inq_inquiry_order_his
     *
     * @param record
     */
    int updateByPrimaryKey(InqInquiryOrderHis record);

    int insertBatchSelective(List<InqInquiryOrderHis> records);

    int updateBatchByPrimaryKeySelective(List<InqInquiryOrderHis> records);
}