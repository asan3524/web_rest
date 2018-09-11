package com.ddsh.util.service.util.excel;


import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ddsh.util.service.api.data.excel.IExcelExportMapper;

/**
 * Excel文件导出
 * @ClassName: ExcelExport
 * @author arpgate
 * @date 2018年5月3日 下午4:55:12
 * @version v1.0.0
 * 
 */
public class ExcelExport {
	
	public static void generalMedia(IExcelExportMapper iExcelExportMapper)
	{
		
		XSSFWorkbook workBook =  new XSSFWorkbook();
		
		XSSFCellStyle titleStyle=workBook.createCellStyle();
		XSSFCellStyle dataStyle=workBook.createCellStyle();

		  XSSFFont titleFont= workBook.createFont();
		  XSSFFont dataFont= workBook.createFont();


		   //标题样式 
		  // 设置单元格边框样式
		  titleStyle.setBorderTop(BorderStyle.THIN);// 上边框 细边线
		  titleStyle.setBorderBottom(BorderStyle.THIN);// 下边框 细边线
		  titleStyle.setBorderLeft(BorderStyle.THIN);// 左边框 细边线
		  titleStyle.setBorderRight(BorderStyle.THIN);// 右边框 细边线
		  // 设置单元格对齐方式
		  titleStyle.setAlignment(HorizontalAlignment.CENTER); // 水平居中
		  titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直居中
		  titleFont.setFontHeightInPoints((short) 15); // 字体高度
		  titleFont.setFontName("黑体"); // 字体样式
		  titleStyle.setFont(titleFont);
		  //标题样式 
		  
		  
		  
		   //数据样式
		  // 设置单元格边框样式
		  dataStyle.setBorderTop(BorderStyle.THIN);// 上边框 细边线
		  dataStyle.setBorderBottom(BorderStyle.THIN);// 下边框 细边线
		  dataStyle.setBorderLeft(BorderStyle.THIN);// 左边框 细边线
		  dataStyle.setBorderRight(BorderStyle.THIN);// 右边框 细边线
		  // 设置单元格对齐方式
		  dataStyle.setAlignment(HorizontalAlignment.CENTER); // 水平居中
		  dataStyle.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直居中
		  dataFont.setFontHeightInPoints((short) 12); // 字体高度
		  dataFont.setFontName("宋体"); // 字体样式
		  dataStyle.setFont(dataFont);
		   //数据样式
		  
		  
		  XSSFSheet sheet=workBook.createSheet("");

		  if(iExcelExportMapper.getHeader()!=null&&iExcelExportMapper.getHeader().size()>0)
		  {		  
			  Map<Integer, XSSFRow> rowMap=new HashMap<Integer, XSSFRow>();
			  
			  for(IExcelExportMapper.Position position:iExcelExportMapper.getHeader().keySet())
			  {
				  
				  if(!rowMap.containsKey(position.getRow()))
				  {
					  rowMap.put(position.getRow(), sheet.createRow(position.getRow()));
				  }
				  
				  
			  }
		  }

		  
	}
	
}
