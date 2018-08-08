package com.ddsh.util.service.api.data.word.bean;

import com.ddsh.util.service.api.constant.UtilContants;
import com.ddsh.util.service.api.data.word.IWordExportMapper;

/**
 *  询价单mapper
 * @ClassName: InquiryWordMapper
 * @author arpgate
 * @date 2018年7月28日 下午6:04:09
 * @version v1.0.0
 * 
 */
@SuppressWarnings("serial")
public class InquiryWordMapper extends IWordExportMapper{
	/**
	 * 使用单位,如:嘉祥锦江校区
	 * 
	 */
	public final static String I_D_1 ="I_D_1"; 
	/**
	 * 询价单标题，如：联想ThinkPad笔记本询价单
	 * 
	 */
	public final static String I_D_2 ="I_D_2"; 
	/**
	 * 询价申请,状态,申请通过为:【√】,未通过为:【X】,未执行状态为:【】
	 * 
	 */
	public final static String I_D_3 ="I_D_3";  
	/**
	 * 详情沟通,状态,申请通过为:【√】,未通过为:【X】,未执行状态为:【】
	 * 
	 */
	public final static String I_D_4 ="I_D_4";  
	/**
	 * 物资询价,状态,申请通过为:【√】,未通过为:【X】,未执行状态为:【】
	 * 
	 */
	public final static String I_D_5 ="I_D_5";  
	/**
	 * 现场评估,状态,申请通过为:【√】,未通过为:【X】,未执行状态为:【】
	 * 
	 */
	public final static String I_D_6 ="I_D_6";  
	/**
	 * 报告审核,状态,申请通过为:【√】,未通过为:【X】,未执行状态为:【】
	 * 
	 */
	public final static String I_D_7 ="I_D_7";  
	/**
	 * 采购决策,状态,申请通过为:【√】,未通过为:【X】,未执行状态为:【】
	 * 
	 */
	public final static String I_D_8 ="I_D_8"; 
	/**
	 * 询价申请详单，表格
	 * 
	 */ 
	public final static String I_D_9 ="I_D_9";  
	/**
	 * 询价说明
	 * 
	 */
	public final static String I_D_10="I_D_10"; 
	/**
	 * 申请者
	 * 
	 */
	public final static String I_D_11="I_D_11"; 
	/**
	 * 申请时间
	 * 
	 */
	public final static String I_D_12="I_D_12"; 
	/**
	 * 沟通结果
	 * 
	 */
	public final static String I_D_13="I_D_13"; 
	/**
	 * 详情沟通者
	 * 
	 */
	public final static String I_D_14="I_D_14"; 
	/**
	 * 详情沟通时间
	 * 
	 */
	public final static String I_D_15="I_D_15"; 
	/**
	 * 物资询价详情列表
	 * 
	 */
	public final static String I_D_16="I_D_16"; 
	/**
	 * 补充说明
	 * 
	 */
	public final static String I_D_17="I_D_17"; 
	/**
	 * 询价者
	 * 
	 */
	public final static String I_D_18="I_D_18"; 
	/**
	 * 
	 * 询价时间
	 */
	public final static String I_D_19="I_D_19"; 
	/**
	 * 评估结论
	 * 
	 */
	public final static String I_D_20="I_D_20"; 
	/**
	 * 
	 * 评估者
	 */
	public final static String I_D_21="I_D_21"; 
	/**
	 * 评估时间
	 * 
	 */
	public final static String I_D_22="I_D_22"; 
	/**
	 * 审核结论
	 * 
	 */
	public final static String I_D_23="I_D_23"; 
	/**
	 * 审核者
	 * 
	 */
	public final static String I_D_24="I_D_24"; 
	/**
	 * 审核时间
	 * 
	 */
	public final static String I_D_25="I_D_25"; 
	/**
	 * 
	 * 决策结论
	 */
	public final static String I_D_26="I_D_26"; 
	/**
	 * 决策 者
	 * 
	 */
	public final static String I_D_27="I_D_27"; 
	/**
	 * 决策时间
	 * 
	 */
	public final static String I_D_28="I_D_28"; 
	/**
	 * 附件 
	 * 
	 */
	public final static String I_D_29="I_D_29";
	
	/**
	 * 询价申请详单-编号
	 * @Fields I_A_D_1
	 */
	public final static String I_A_D_1="I_A_D_1";
	/**
	 * 询价申请详单-名称
	 * @Fields I_A_D_2
	 */
	public final static String I_A_D_2="I_A_D_2";
	/**
	 * 询价申请详单-类型
	 * @Fields I_A_D_3
	 */
	public final static String I_A_D_3="I_A_D_3";
	/**
	 * 询价申请详单-品牌
	 * @Fields I_A_D_4
	 */
	public final static String I_A_D_4="I_A_D_4";
	/**		
	 * 询价申请详单-型号/规格
	 * @Fields I_A_D_5
	 */
	public final static String I_A_D_5="I_A_D_5";
	/**
	 * 询价申请详单-颜色/材质
	 * @Fields I_A_D_6
	 */
	public final static String I_A_D_6="I_A_D_6";
	/**
	 * 询价申请详单-数量
	 * @Fields I_A_D_7
	 */
	public final static String I_A_D_7="I_A_D_7";
	/**
	 * 询价申请详单-	备注
	 * @Fields I_A_D_8
	 */
	public final static String I_A_D_8="I_A_D_8";


	/**
	 * 询价详单-序号	
	 * @Fields I_I_D_1
	 */
	public final static String I_I_D_1="I_I_D_1";
	/**
	 * 询价详单-名称
	 * @Fields I_I_D_2
	 */
	public final static String I_I_D_2="I_I_D_2";
	/**
	 * 询价详单-类型	
	 * @Fields I_I_D_3
	 */
	public final static String I_I_D_3="I_I_D_3";
	/**
	 * 询价详单-品牌	
	 * @Fields I_I_D_4
	 */
	public final static String I_I_D_4="I_I_D_4";
	/**
	 * 询价详单-新购
	 * @Fields I_I_D_5
	 */
	public final static String I_I_D_5="I_I_D_5";
	/**
	 * 询价详单-型号/规格
	 * @Fields I_I_D_6
	 */
	public final static String I_I_D_6="I_I_D_6";
	/**
	 * 询价详单-颜色/材质	
	 * @Fields I_I_D_7
	 */
	public final static String I_I_D_7="I_I_D_7";
	/**
	 * 询价详单-单价
	 * @Fields I_I_D_8
	 */
	public final static String I_I_D_8="I_I_D_8";
	/**
	 * 询价详单-数量
	 * @Fields I_I_D_9
	 */
	public final static String I_I_D_9="I_I_D_9";
	/**
	 * 询价详单-备注
	 * @Fields I_I_D_10
	 */
	public final static String I_I_D_10="I_I_D_10";
	
	/**
	 * 询价申请详单
	 * @Fields INQUIRY_APPLICTION_DETAILS
	 */
	public final static String INQUIRY_APPLICTION_DETAILS="INQUIRY_APPLICTION_DETAILS";
	
	/**
	 * 询价详单
	 * @Fields INQUIRY_INQUIRY_DETAILS
	 */
	public final static String INQUIRY_INQUIRY_DETAILS="INQUIRY_INQUIRY_DETAILS";


	static
	{
		getTypeMapper().put(I_D_1 , UtilContants.WordMediaType.MEDIA_TEXT);
		getTypeMapper().put(I_D_2 , UtilContants.WordMediaType.MEDIA_TEXT);
		getTypeMapper().put(I_D_3 , UtilContants.WordMediaType.MEDIA_TEXT);
		getTypeMapper().put(I_D_4 , UtilContants.WordMediaType.MEDIA_TEXT);
		getTypeMapper().put(I_D_5 , UtilContants.WordMediaType.MEDIA_TEXT);
		getTypeMapper().put(I_D_6 , UtilContants.WordMediaType.MEDIA_TEXT);
		getTypeMapper().put(I_D_7 , UtilContants.WordMediaType.MEDIA_TEXT);
		getTypeMapper().put(I_D_8 , UtilContants.WordMediaType.MEDIA_TEXT);
		getTypeMapper().put(I_D_9 , UtilContants.WordMediaType.MEDIA_TABLE);
		getTypeMapper().put(I_D_10, UtilContants.WordMediaType.MEDIA_TEXT);
		getTypeMapper().put(I_D_11, UtilContants.WordMediaType.MEDIA_TEXT);
		getTypeMapper().put(I_D_12, UtilContants.WordMediaType.MEDIA_TEXT);
		getTypeMapper().put(I_D_13, UtilContants.WordMediaType.MEDIA_TEXT);
		getTypeMapper().put(I_D_14, UtilContants.WordMediaType.MEDIA_TEXT);
		getTypeMapper().put(I_D_15, UtilContants.WordMediaType.MEDIA_TEXT);
		getTypeMapper().put(I_D_16, UtilContants.WordMediaType.MEDIA_TABLE);
		getTypeMapper().put(I_D_17, UtilContants.WordMediaType.MEDIA_TEXT);
		getTypeMapper().put(I_D_18, UtilContants.WordMediaType.MEDIA_TEXT);
		getTypeMapper().put(I_D_19, UtilContants.WordMediaType.MEDIA_TEXT);
		getTypeMapper().put(I_D_20, UtilContants.WordMediaType.MEDIA_TEXT);
		getTypeMapper().put(I_D_21, UtilContants.WordMediaType.MEDIA_TEXT);
		getTypeMapper().put(I_D_22, UtilContants.WordMediaType.MEDIA_TEXT);
		getTypeMapper().put(I_D_23, UtilContants.WordMediaType.MEDIA_TEXT);
		getTypeMapper().put(I_D_24, UtilContants.WordMediaType.MEDIA_TEXT);
		getTypeMapper().put(I_D_25, UtilContants.WordMediaType.MEDIA_TEXT);
		getTypeMapper().put(I_D_26, UtilContants.WordMediaType.MEDIA_TEXT);
		getTypeMapper().put(I_D_27, UtilContants.WordMediaType.MEDIA_TEXT);
		getTypeMapper().put(I_D_28, UtilContants.WordMediaType.MEDIA_TEXT);
		getTypeMapper().put(I_D_29, UtilContants.WordMediaType.MEDIA_IMAGE);

		

		

		//INQUIRY_APPLICTION_DETAILS
		getTypeMapper().put(I_A_D_1, UtilContants.WordMediaType.MEDIA_TEXT);
		getTypeMapper().put(I_A_D_2, UtilContants.WordMediaType.MEDIA_TEXT);
		getTypeMapper().put(I_A_D_3, UtilContants.WordMediaType.MEDIA_TEXT);
		getTypeMapper().put(I_A_D_4, UtilContants.WordMediaType.MEDIA_TEXT);
		getTypeMapper().put(I_A_D_5, UtilContants.WordMediaType.MEDIA_TEXT);
		getTypeMapper().put(I_A_D_6, UtilContants.WordMediaType.MEDIA_TEXT);
		getTypeMapper().put(I_A_D_7, UtilContants.WordMediaType.MEDIA_TEXT);
		getTypeMapper().put(I_A_D_8, UtilContants.WordMediaType.MEDIA_TEXT);
		//INQUIRY_APPLICTION_DETAILS
		
		//INQUIRY_INQUIRY_DETAILS
		getTypeMapper().put(I_I_D_1, UtilContants.WordMediaType.MEDIA_TEXT);
		getTypeMapper().put(I_I_D_2, UtilContants.WordMediaType.MEDIA_TEXT);
		getTypeMapper().put(I_I_D_3, UtilContants.WordMediaType.MEDIA_TEXT);
		getTypeMapper().put(I_I_D_4, UtilContants.WordMediaType.MEDIA_TEXT);
		getTypeMapper().put(I_I_D_5, UtilContants.WordMediaType.MEDIA_TEXT);
		getTypeMapper().put(I_I_D_6, UtilContants.WordMediaType.MEDIA_TEXT);
		getTypeMapper().put(I_I_D_7, UtilContants.WordMediaType.MEDIA_TEXT);
		getTypeMapper().put(I_I_D_8, UtilContants.WordMediaType.MEDIA_TEXT);
		getTypeMapper().put(I_I_D_9, UtilContants.WordMediaType.MEDIA_TEXT);
		getTypeMapper().put(I_I_D_10, UtilContants.WordMediaType.MEDIA_TEXT);
		//INQUIRY_INQUIRY_DETAILS

		getFlowMapper().put(I_D_9, INQUIRY_APPLICTION_DETAILS);
		getFlowMapper().put(I_D_16, INQUIRY_INQUIRY_DETAILS);
	}
}
