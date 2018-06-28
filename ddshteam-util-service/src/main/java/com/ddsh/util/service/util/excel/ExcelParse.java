package com.ddsh.util.service.util.excel;

import java.awt.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ddsh.util.service.api.constant.UtilContants;

/**
 * excel文件解析
 * @ClassName: ExcelParse
 * @author arpgate
 * @date 2018年5月3日 下午4:55:43
 * @version v1.0.0
 * 
 */
public class ExcelParse {

	public void parse(String excelType, String path, int startSheet, int startHeader, String excelversion) {

		switch (excelversion) {
		case UtilContants.ExcelVersion.XLS:
			parseXls(excelType, path, startSheet, startHeader);
			break;
		case UtilContants.ExcelVersion.XLSX:
			parseXlsx(excelType, path, startSheet, startHeader);
			break;

		}
	}

	public void parseXls(String excelType, String path, int startSheet, int startHeader) {
		FileInputStream inputStream = null;
		HSSFWorkbook workBook = null;

		try {
			inputStream = new FileInputStream(new File(path));
			workBook = new HSSFWorkbook(inputStream);
			
			Map<String, HSSFSheet> sheets=getSheetByWorkBook(workBook,startSheet,excelType);
			
			for(String type:sheets.keySet())
			{
				HSSFSheet sheet = sheets.get(type);
				
				HSSFRow row=null;
				int startindex=startHeader;
				while((row=sheet.getRow(startindex))!=null)
				{
					startindex++;
					HSSFCell cell = row.getCell(2);
					String value = cell.getStringCellValue();
					System.out.println(value);

				}
			}
			
			
		

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (workBook != null) {
				try {
					workBook.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}

	}

	public void parseXlsx(String excelType, String path, int startSheet, int startHeader) {
		FileInputStream inputStream = null;
		XSSFWorkbook workBook = null;
		try {
			inputStream = new FileInputStream(new File(path));
			workBook = new XSSFWorkbook(inputStream);
			
			Map<String, XSSFSheet> sheets=getSheetByWorkBook(workBook,startSheet,excelType);
			
			XSSFSheet sheet = workBook.getSheetAt(0);
			XSSFRow row = sheet.getRow(2);
			XSSFCell cell = row.getCell(2);
			String value = cell.getStringCellValue();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (workBook != null) {
				try {
					workBook.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}

	}
	
	
	private <T> Map<String, T> getSheetByWorkBook(Workbook workbook, int startSheet,String excelType)
	{
		Map<String, T> workbooks=new HashMap<String, T>();
		
		T sheet=null;
		while((sheet=(T) workbook.getSheetAt(startSheet) )!= null)
		{
			workbooks.put(excelType, sheet);
			startSheet++;
		}
		
		return workbooks;
	}
}
