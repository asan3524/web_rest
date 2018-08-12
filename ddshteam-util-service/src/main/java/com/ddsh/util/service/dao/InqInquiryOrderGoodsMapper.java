package com.ddsh.util.service.dao;

import com.ddsh.util.service.api.model.InqInquiryOrderGoods;
import com.ddsh.util.service.api.model.InqInquiryOrderGoodsCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface InqInquiryOrderGoodsMapper {
    /**
     *  根据指定的条件获取数据库记录数,inq_inquiry_order_goods
     *
     * @param example
     */
    long countByExample(InqInquiryOrderGoodsCriteria example);

    /**
     *  根据指定的条件删除数据库符合条件的记录,inq_inquiry_order_goods
     *
     * @param example
     */
    int deleteByExample(InqInquiryOrderGoodsCriteria example);

    /**
     *  根据主键删除数据库的记录,inq_inquiry_order_goods
     *
     * @param id
     */
    int deleteByPrimaryKey(String id);

    /**
     *  新写入数据库记录,inq_inquiry_order_goods
     *
     * @param record
     */
    int insert(InqInquiryOrderGoods record);

    /**
     *  动态字段,写入数据库记录,inq_inquiry_order_goods
     *
     * @param record
     */
    int insertSelective(InqInquiryOrderGoods record);

    /**
     *  根据指定的条件查询符合条件的数据库记录,inq_inquiry_order_goods
     *
     * @param example
     */
    List<InqInquiryOrderGoods> selectByExample(InqInquiryOrderGoodsCriteria example);

    /**
     *  根据指定主键获取一条数据库记录,inq_inquiry_order_goods
     *
     * @param id
     */
    InqInquiryOrderGoods selectByPrimaryKey(String id);

    /**
     *  动态根据指定的条件来更新符合条件的数据库记录,inq_inquiry_order_goods
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") InqInquiryOrderGoods record, @Param("example") InqInquiryOrderGoodsCriteria example);

    /**
     *  根据指定的条件来更新符合条件的数据库记录,inq_inquiry_order_goods
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") InqInquiryOrderGoods record, @Param("example") InqInquiryOrderGoodsCriteria example);

    /**
     *  动态字段,根据主键来更新符合条件的数据库记录,inq_inquiry_order_goods
     *
     * @param record
     */
    int updateByPrimaryKeySelective(InqInquiryOrderGoods record);

    /**
     *  根据主键来更新符合条件的数据库记录,inq_inquiry_order_goods
     *
     * @param record
     */
    int updateByPrimaryKey(InqInquiryOrderGoods record);

    int insertBatchSelective(List<InqInquiryOrderGoods> records);

    int updateBatchByPrimaryKeySelective(List<InqInquiryOrderGoods> records);
}