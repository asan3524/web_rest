package com.ddsh.util.service.util.excel;

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
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ddsh.util.service.api.constant.UtilContants;
import com.ddsh.util.service.api.data.excel.IExcelParseData;

/**
 * excel文件解析
 * @ClassName: ExcelParse
 * @author arpgate
 * @date 2018年5月3日 下午4:55:43
 * @version v1.0.0
 * 
 */
public class ExcelParse {

	/**
	 * 这里用一句话描述这个方法的作用
	 * @Title: parse
	 * @param excelType excel 文件类型
	 * @see UtilContants.ExcelType
	 * @param path 文件路径 
	 * @param startSheet 起始sheet
	 * @param startHeader 数据起始行
	 * @param excelversion 文件版本类型
	 * @see UtilContants.ExcelVersion
	 * @param startLeft 数据起始例
	 * @param header 头行号
	 * @return List<T>
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @see 
	 * @throws
	 * @author arpgate
	 */
	public <T extends IExcelParseData> T parse(String excelType, String path, int startSheet, int startHeader,int startLeft,int header) throws InstantiationException, IllegalAccessException {
		if (path.endsWith(UtilContants.ExcelVersion.XLS)||path.endsWith(UtilContants.ExcelVersion.XLS.toLowerCase()))
		{
			return parseXls(excelType, path, startSheet, startHeader,startLeft, header);
		}
		
		if (path.endsWith(UtilContants.ExcelVersion.XLSX)||path.endsWith(UtilContants.ExcelVersion.XLSX.toLowerCase()))
		{
			return parseXlsx(excelType, path, startSheet, startHeader,startLeft, header);
		}
		return null;
	}

	public <T extends IExcelParseData> T parseXls(String excelType, String path, int startSheet, int startHeader, int startleft,int header) throws InstantiationException, IllegalAccessException {
		FileInputStream inputStream = null;
		HSSFWorkbook workBook = null;
		 T parseDatas=(T) UtilContants.ExcelType.EXCEL_CACHE_TYPE.get(excelType).newInstance();
		try {
			inputStream = new FileInputStream(new File(path));
			workBook = new HSSFWorkbook(inputStream);

			Map<String, HSSFSheet> sheets = getSheetByWorkBook(workBook, startSheet, excelType);

			for (String type : sheets.keySet()) {
				HSSFSheet sheet = sheets.get(type);

				int heaerstartcell=startleft;
				HSSFRow headerRow=sheet.getRow(header);
				while(headerRow.getCell(heaerstartcell) != null)
				{
					HSSFCell cell = headerRow.getCell(heaerstartcell);
					heaerstartcell++;
					parseDatas.getHeader().add(cell.getStringCellValue());
				}
				
				HSSFRow row = null;
				int startindex = startHeader;
				while ((row = sheet.getRow(startindex)) != null) {
					int startcell = startleft;
					
					parseDatas.startrow();
					
					while (row.getCell(startcell) != null) {
						HSSFCell cell = row.getCell(startcell);
						CellType ctype = cell.getCellTypeEnum();
						parseDatas.setValue(startcell, parseCellToBean(ctype,cell));
						startcell++;
					}
					startindex++;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(parseDatas!=null)
			{
				parseDatas.endrow();
			}
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
      return parseDatas;
	}

	public <T extends IExcelParseData> T parseXlsx(String excelType, String path, int startSheet, int startHeader, int startleft,int header) throws InstantiationException, IllegalAccessException {
		FileInputStream inputStream = null;
		XSSFWorkbook workBook = null;
		 T parseDatas=(T) UtilContants.ExcelType.EXCEL_CACHE_TYPE.get(excelType).newInstance();
		try {
			inputStream = new FileInputStream(new File(path));
			workBook = new XSSFWorkbook(inputStream);

			Map<String, XSSFSheet> sheets = getSheetByWorkBook(workBook, startSheet, excelType);

			for (String type : sheets.keySet()) {
				XSSFSheet sheet = sheets.get(type);
				
				int heaerstartcell=startleft;
				XSSFRow headerRow=sheet.getRow(header);
				while(headerRow.getCell(heaerstartcell) != null)
				{
					XSSFCell cell = headerRow.getCell(heaerstartcell);
					heaerstartcell++;
					parseDatas.getHeader().add(cell.getStringCellValue());
				}
				
				XSSFRow row = null;
				int startindex = startHeader;
				while ((row = sheet.getRow(startindex)) != null) {
					int startcell = startleft;
					
					parseDatas.startrow();
					
					while (row.getCell(startcell) != null) {
						XSSFCell cell = row.getCell(startcell);
						CellType ctype = cell.getCellTypeEnum();
						parseDatas.setValue(startcell, parseCellToBean(ctype,cell));
						startcell++;
					}
					startindex++;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			
			if(parseDatas!=null)
			{
				parseDatas.endrow();
			}
			
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

		return parseDatas;
	}

	private <T> Map<String, T> getSheetByWorkBook(Workbook workbook, int startSheet, String excelType) {
		Map<String, T> workbooks = new HashMap<String, T>();

		T sheet = null;
		for (int i=startSheet;i<workbook.getNumberOfSheets();i++) {
			if((sheet = (T) workbook.getSheetAt(i) )!= null)
			{
				workbooks.put(excelType, sheet);
			}
		}

		return workbooks;
	}

	private Object parseCellToBean(CellType ctype, Cell cell) {
		Object value = null;
		switch (ctype) {
		case _NONE:
			value = "";
			break;
		case BLANK:
			value = "";
			break;
		case BOOLEAN:
			value = cell.getBooleanCellValue();
			break;
		case ERROR:
			value = cell.getErrorCellValue();
			break;
		case FORMULA:
			value = cell.getCellFormula();
			break;
		case NUMERIC:
			try
			{
				value = Double.valueOf(cell.getNumericCellValue()).intValue();
			}catch (Exception e) {
				value = cell.getNumericCellValue();
			}
			break;
		case STRING:
			value = cell.getStringCellValue();
			break;

		default:
			value = cell.getStringCellValue();
			break;
		}

		return value;
	}
}
