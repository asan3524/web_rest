package com.ddsh.pdf.service.constant;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ddsh.pdf.service.annotation.ConstantDescription;


/**
 * 常量
 * @ClassName: GoodsContants
 * @author arpgate
 * @date 2018年3月2日 下午11:54:32
 * @version v1.0.0
 * 
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class PDFContants {

	public static final String PERMISSION = "permission";

	public static final Map<String, Map<String, String>> CONSTANT_DESC = new HashMap<String, Map<String, String>>(40);

	public static final Map<String, String> CONSTANT_TYPE_DESC = new HashMap<String, String>(20);

	public static final Map<String, List<String>> CONSTANT_TYPE_LIST = new HashMap<String, List<String>>();
	static {
		try {
			for (Class cls : PDFContants.class.getClasses()) {

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

 
	@ConstantDescription(desc = "系统配置")
	public static final class Sysset {
/*		@ConstantDescription(desc = "物资标签模型存放路径")
		public static final String LABLE_PATH = "D:\\project_file\\temp\\";
		
		@ConstantDescription(desc = "物资标签存放路径")
		public static final String LABLE_PRINTER_PPATH = "D:\\project_file\\temp\\";*/
		
		
        @ConstantDescription(desc = "询价模板")
        public static final String INQUIRY_MODEL = "/inquiry_model_compomnet.docx";
        
        @ConstantDescription(desc = "采购模板")
        public static final String PURCHASE_MODEL = "/purchase_model_compomnet.docx";
        
        @ConstantDescription(desc = "物资标签模型存放路径")
        public static final String MODEL_PATH = "/model/";
        @ConstantDescription(desc = "字段样式")
        public static final String FONTS_PATH = "/FONTS/";
		@ConstantDescription(desc = "模型文件名称")
		public static final String LABLE_MODEL_NAME = "P30-60.xml";
		
		@ConstantDescription(desc = "上传文件根路径")
		public static final String BASE_W_ROOT= "D:\\";
		
		@ConstantDescription(desc = "上传文件根路径")
		public static final String BASE_L_ROOT= "/root/data/";

		@ConstantDescription(desc = "上传文件根路径")
        public static final String UPLOAD_ROOT_PATH = "/upload/";
		
	}
	
	@ConstantDescription(desc = "操作系统类型")
	public static final class OSType
	{
		@ConstantDescription(desc = "windows")
		public static final int WINDOWS=1;
		
		@ConstantDescription(desc = "linux")
		public static final int LINUX=2;
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
	@ConstantDescription(desc = "word图片类型")
	public static final class WordImageType
	{
		public static final Map<String, Integer> cache=new HashMap<String, Integer>();
		static
		{
			cache.put("emf",2);
			cache.put("EMF",2);
			cache.put("WMF",3);
			cache.put("wmf",3);
			cache.put("PICT",4);
			cache.put("pict",4);
			cache.put("jpeg",5);
			cache.put("JPEG",5);
			cache.put("jpg",5);
			cache.put("JPG",5);
			cache.put("PNG",6);
			cache.put("png",6);
			cache.put("DIB",7);
			cache.put("dib",7);
			cache.put("GIF",8);
			cache.put("gif",8);
			cache.put("TIFF",9);
			cache.put("tiff",9);
			cache.put("EPS",10);
			cache.put("eps",10);
			cache.put("BMP",11);
			cache.put("bmp",11);
			cache.put("WPG",12);
			cache.put("wpg",12);
		}
	}
	
	
	@ConstantDescription(desc = "word资源类型")
	public static final class WordMediaType
	{
		@ConstantDescription(desc = "图片")
		public static final int MEDIA_IMAGE = 1;
		@ConstantDescription(desc = "文字")
		public static final int MEDIA_TEXT = 2;
		@ConstantDescription(desc = "表格")
		public static final int MEDIA_TABLE = 3;
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
	

	@ConstantDescription(desc = PDFContants.PERMISSION)
	public static final class Permission { }
}
