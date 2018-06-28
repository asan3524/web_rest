package com.ddsh.util.service.api.constant;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ddsh.util.service.api.annotation.ConstantDescription;

/**
 * 物资常量
 * @ClassName: GoodsContants
 * @author arpgate
 * @date 2018年3月2日 下午11:54:32
 * @version v1.0.0
 * 
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class UtilContants {

	public static final String PERMISSION = "permission";

	public static final Map<String, Map<String, String>> CONSTANT_DESC = new HashMap<String, Map<String, String>>(40);

	public static final Map<String, String> CONSTANT_TYPE_DESC = new HashMap<String, String>(20);

	public static final Map<String, List<String>> CONSTANT_TYPE_LIST = new HashMap<String, List<String>>();
	static {
		try {
			for (Class cls : UtilContants.class.getClasses()) {

				ConstantDescription cds = (ConstantDescription) cls.getAnnotation(ConstantDescription.class);
				if (cds != null) {
					if (PERMISSION.equals(cds.desc())) {
						List<String> ps = new ArrayList<String>();
						for (Field fd : cls.getDeclaredFields()) {
							if (fd != null) {
								ps.add(fd.get(cls).toString());
							}
						}
						CONSTANT_TYPE_LIST.put(PERMISSION, ps);
						continue;
					}

					CONSTANT_TYPE_DESC.put(cls.getSimpleName(), cds.desc());

					Map<String, String> valueDesc = new HashMap<String, String>(10);
					CONSTANT_DESC.put(cls.getSimpleName(), valueDesc);

					for (Field fd : cls.getDeclaredFields()) {
						ConstantDescription cdf = fd.getAnnotation(ConstantDescription.class);
						if (cdf != null) {
							valueDesc.put(fd.get(cls).toString(), cdf.desc());

						}
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@ConstantDescription(desc = "伟文 视图类型")
	public static final class WWViewType {
		@ConstantDescription(desc = "文字")
		public static final int WW_STRING = 0;
		
		@ConstantDescription(desc = "二维码")
		public static final int WW_CODEIMAGE= 1;

		@ConstantDescription(desc = "线")
		public static final int WW_LINE= 4;
		
		@ConstantDescription(desc = "矩形")
		public static final int WW_REC= 5;

	}
	
	
	@ConstantDescription(desc = "系统配置")
	public static final class Sysset {
/*		@ConstantDescription(desc = "物资标签模型存放路径")
		public static final String LABLE_PATH = "D:\\project_file\\temp\\";
		
		@ConstantDescription(desc = "物资标签存放路径")
		public static final String LABLE_PRINTER_PPATH = "D:\\project_file\\temp\\";*/
		
		
		@ConstantDescription(desc = "物资标签模型存放路径")
		public static final String LABLE_PATH = "/root/data/model/";
		
		@ConstantDescription(desc = "物资标签存放路径")
		public static final String LABLE_PRINTER_PPATH = "/root/data/source/";
		
		@ConstantDescription(desc = "模型文件名称")
		public static final String LABLE_MODEL_NAME = "P30-60.xml";
		
		@ConstantDescription(desc = "上传文件根路径")
		public static final String UPLOAD_ROOT_PATH = "/root/data/";

	}
	
	@ConstantDescription(desc = "询价附件信息")
	public static final class AttachmentType
	{
		@ConstantDescription(desc = "询价附件信息")
		public static final String INQ_INQUIRY_ORDER_GOODS="INQ_INQUIRY_ORDER_GOODS";
		
		@ConstantDescription(desc = "采购附件信息")
		public static final String PUR_PURCHASE_ORDER_GOODS="PUR_PURCHASE_ORDER_GOODS";
	}
	
	
	@ConstantDescription(desc = "询价附件信息")
	public static final class INQ_INQUIRY_ORDER_GOODS
	{
		@ConstantDescription(desc = "询价申请物资图片附件")
		public static final String INQUIRY_GOODS_IMAGE="INQUIRY_GOODS_IMAGE";
	}
	
	
	@ConstantDescription(desc = "采购附件信息")
	public static final class PUR_PURCHASE_ORDER_GOODS
	{
		@ConstantDescription(desc = "采购申请物资图片附件")
		public static final String PURCHASE_GOODS_IMAGE="PURCHASE_GOODS_IMAGE";
	}
	
	
	
	@ConstantDescription(desc = "Excel文件类型")
	public static final class ExcelType
	{
		@ConstantDescription(desc = "常规班组班级设置申请表")
		public static final String COMMON_CLASS_SET_APP = "1001";
		
		@ConstantDescription(desc = "选修课班级设置申请表")
		public static final String CHOOSE_CLASS_SET_APP = "2001";
		
		@ConstantDescription(desc = "个性化一对一（多）设置申请表")
		public static final String PERSONAL_MANY_SET_APP = "3001";
		
		@ConstantDescription(desc = "嘉祥训练营设置申请表")
		public static final String TAIN_SET_APP = "4001";
		
		@ConstantDescription(desc = "教师信息表")
		public static final String TEACHER_INFO_APP = "5001";
	}
	
	
	@ConstantDescription(desc = "Excel文件版本类型")
	public static final class ExcelVersion
	{
		@ConstantDescription(desc = "97-2003")
		public static final String XLS = "XLS";
		
		@ConstantDescription(desc = "2007及以后")
		public static final String XLSX = "XLSX";
	}
	
	@ConstantDescription(desc = "Excel导出常量")
	public static final class ExcelExport
	{
		@ConstantDescription(desc = "文件名")
		public static final String FILE_NAME = "FILE_NAME";
		
		@ConstantDescription(desc = "sheet名称")
		public static final String SHEET_NAME = "SHEET_NAME";
		
		@ConstantDescription(desc = "导出扩展名")
		public static final String EXPORT_EXT = ".xls";
		
		@ConstantDescription(desc = "分组数据")
		public static final String GROUP_DATA = "GROUP_DATA";
		
		@ConstantDescription(desc = "表头数据")
		public static final String COLUMNS_DATA = "COLUMNS_DATA";
		
		@ConstantDescription(desc = "返回类型")
		public static final String CONTENT_TYPE= "application/vnd.ms-excel";
		
		@ConstantDescription(desc = "默认导出文件名")
		public static final String EXPORT_DEFAULT_NAME= "export.xls";
		
		@ConstantDescription(desc = "默认sheet名称")
		public static final String SHEET_DEFAULT_NAME= "Sheet";
		
	}
	
	@ConstantDescription(desc = "文件类型")
	public static final class FileMediaType
	{
		@ConstantDescription(desc = "图片")
		public static final int MEDIA_IMAGE = 1;
		@ConstantDescription(desc = "动图")
		public static final int MEDIA_IMAGE_VIDEO = 2;
		@ConstantDescription(desc = "视频")
		public static final int MEDIA_VIDEO = 3;
		@ConstantDescription(desc = "文档")
		public static final int MEDIA_DOCUMENT = 4;
		@ConstantDescription(desc = "应用包")
		public static final int MEDIA_EXE = 5;
		@ConstantDescription(desc = "压缩包")
		public static final int MEDIA_COMP = 6;
	}
	
	@ConstantDescription(desc = "符号")
	public static final class Symbol
	{
		@ConstantDescription(desc = "有效")
		public static final String RIGHT_DIAGONAL = "\\";
		
	}
	
	@ConstantDescription(desc="状态")
	public static final class Status
	{
		@ConstantDescription(desc="有效可用状态")
		public static final int EFFECT=1;
		
		@ConstantDescription(desc="无效删除状态")
		public static final int LOSE_EFFECT=0;

	}

	@ConstantDescription(desc = UtilContants.PERMISSION)
	public static final class Permission { }
}