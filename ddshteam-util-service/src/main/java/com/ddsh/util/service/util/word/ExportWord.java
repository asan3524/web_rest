package com.ddsh.util.service.util.word;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.Map.Entry;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.Document;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.apache.xmlbeans.XmlCursor;

public class ExportWord {
 
	public static XWPFDocument generateWord(Map<String, Object> param,String template) {
		XWPFDocument doc = null;
		try {
			OPCPackage pack = POIXMLDocument.openPackage(template);
			doc = new XWPFDocument(pack);
			if (param != null && param.size() > 0) {
				List<XWPFParagraph> paragraphList = doc.getParagraphs();
				processParagraphs(paragraphList, param, doc);
				processTables(doc.getTables(),param,doc);
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		return doc;

	}

	/**
	* 处理段落中文本，替换文本中定义的变量；
	* 
	* @param paragraphList
	*            段落列表
	* @param param
	*            需要替换的变量及变量值
	* @param doc
	*            需要替换的DOC
	*/
	public static void processParagraphs(List<XWPFParagraph> paragraphList,	Map<String, Object> param, XWPFDocument doc) {
		if (paragraphList != null && paragraphList.size() > 0) {
			for (XWPFParagraph paragraph : paragraphList) {
				List<XWPFRun> runs = paragraph.getRuns();
				StringBuilder sb=null;
				
				for (int runindex=0;runindex<runs.size();runindex++) {
					XWPFRun run=runs.get(runindex);
					String text = run.getText(0);
					
					if(text!=null)
					{
						if(sb==null)
						{
							sb=new StringBuilder();
						}
						sb.append(text);
					}
					
					if (sb != null&&runindex==runs.size()-1) {
						text=sb.toString();
						boolean isSetText = false;
						run.setText(text, 0);
						for (Entry<String, Object> entry : param.entrySet()) {
							String key = entry.getKey();
							if (text.indexOf(key) != -1) {
								isSetText = true;
								Object value = entry.getValue();
								if (value instanceof String) {// 文本替换
									text = text.replace(key, value.toString());
								}
							}
						}
						if (isSetText) {
							run.setText(text, 0);
						}
					}
					else
					{
						run.setText("", 0);
					}
				}
			}
		}
	}
	
	
	public static void processTables(List<XWPFTable> xWPFTableList,	Map<String, Object> param, XWPFDocument doc) {
		if (xWPFTableList != null && xWPFTableList.size() > 0) {
			for (XWPFTable xwpftable : xWPFTableList) {
				
				List<XWPFTableRow> rows=xwpftable.getRows();
				if(rows!=null&&rows.size()>0)
				{
					for (XWPFTableRow row:xwpftable.getRows()) { 
						
						List<XWPFTableCell> cells=row.getTableCells();
						
						if(cells!=null&&cells.size()>0)
						{
							for(XWPFTableCell cell:cells)
							{
								String text=cell.getText();
								boolean isSetText=false;
								for (Entry<String, Object> entry : param.entrySet()) {
									String key = entry.getKey();
									if (text.indexOf(key) != -1) {
										isSetText = true;
										Object value = entry.getValue();
										if (value instanceof String) {// 文本替换
											text = text.replace(key, value.toString());
										}
									}
								}
								if (isSetText) {
									cell.removeParagraph(0);
									cell.setText(text);
								}
							}
							
						}
					}
				}
			
			}
		}
	}

	/**
	* 在定位的位置插入表格；
	* 
	* @param key
	*            定位的变量值
	* @param doc
	*            需要替换的DOC
	*/

	public static void insertTab(String key, XWPFDocument doc2) {

		List<XWPFParagraph> paragraphList = doc2.getParagraphs();

		if (paragraphList != null && paragraphList.size() > 0) {

			for (XWPFParagraph paragraph : paragraphList) {

				List<XWPFRun> runs = paragraph.getRuns();

				for (XWPFRun run : runs) {

					String text = run.getText(0);

					if (text != null) {

						if (text.indexOf(key) >= 0) {

							XmlCursor cursor = paragraph.getCTP().newCursor();

							XWPFTable tableOne = doc2.insertNewTbl(cursor);// ---这个是关键
							// XWPFTable tableOne =
							// paragraph.getDocument().createTable();
							XWPFTableRow tableOneRowOne = tableOne.getRow(0);
							tableOneRowOne.getCell(0).setText("一行一列");
							XWPFTableCell cell12 = tableOneRowOne.createCell();
							cell12.setText("一行二列");
							// tableOneRowOne.addNewTableCell().setText("第1行第2列");

							// tableOneRowOne.addNewTableCell().setText("第1行第3列");

							// tableOneRowOne.addNewTableCell().setText("第1行第4列");

							XWPFTableRow tableOneRowTwo = tableOne.createRow();
							tableOneRowTwo.getCell(0).setText("第二行第一列");
							tableOneRowTwo.getCell(1).setText("第二行第二列");
							// tableOneRowTwo.getCell(2).setText("第2行第3列");
							XWPFTableRow tableOneRow3 = tableOne.createRow();
							// ---顺序增加行后，忽略第1、2单元格，直接插入3、4
							tableOneRow3.addNewTableCell().setText("第三行第3列");
							tableOneRow3.addNewTableCell().setText("第三行第4列");

						}

					}

				}

			}

		}

	}

	public static void insertImage(String key, XWPFDocument doc) {

		List<XWPFParagraph> paragraphList = doc.getParagraphs();
		try {
			if (paragraphList != null && paragraphList.size() > 0) {
				for (XWPFParagraph paragraph : paragraphList) {
					List<XWPFRun> runs = paragraph.getRuns();
					for (XWPFRun run : runs) {
						String text = run.getText(0);
						if (text != null) {
							if (text.indexOf(key) >= 0) {
								run.addBreak();
								run.addPicture(
										new FileInputStream("E:\\pic\\四姑娘(1)\\100CANON\\IMG_9387.JPG"), Document.PICTURE_TYPE_JPEG, "E:\\\\pic\\\\四姑娘(1)\\\\100CANON\\\\IMG_9387.JPG",
										Units.toEMU(200), Units.toEMU(200)); // 200x200
																				// pixels
								run.addBreak(BreakType.PAGE);
							}
						}

					}

				}

			}

		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();

		}

	}

	/**
	
	* 测试用方法
	
	*/

	public static void main(String[] args) throws Exception {

		Map<String, Object> param = new HashMap<String, Object>();
					
		param.put("${name}", "哈哈哈哈");
		param.put("${age}", "信息管理与信息系统");
		param.put("${sex}", "男");
		param.put("${code}", "大学");
		param.put("${mobile}", "大学");
		param.put("${phone}", "大学");
		param.put("${birth}", "大学");
		param.put("${birthside}", "大学");
		param.put("${home}", "大学");
		param.put("${remark}", "2016-09-21");
		param.put("${personInfo}", "2016-09-21");
		param.put("${test}", new Date().toString());
		
		Map<String, Object> twocode = new HashMap<String, Object>();
		twocode.put("width", 300);

		twocode.put("height", 300);

		twocode.put("type", "png");

		XWPFDocument doc = ExportWord.generateWord(param, "C:\\Users\\arpgate\\Desktop\\template.docx");
		FileOutputStream fopts = new FileOutputStream("D:\\project_file\\tt\\"+UUID.randomUUID().toString()+".docx");
		doc.write(fopts);

/*		ExportWord.insertTab("${table}", doc); // /----------创建表

		ExportWord.insertImage("${image}", doc); // /----------创建图

		// ------替换多余的标志位----//

		param = new HashMap<String, Object>();

		param.put("${test}", "下一个段落");

		param.put("${table}", "");

		param.put("${image}", "");

		ExportWord.processParagraphs(doc.getParagraphs(), param, doc);
		FileOutputStream fopts = new FileOutputStream("D:\\project_file\\tt\\template-2.docx");
		doc.write(fopts);*/

	}
}
