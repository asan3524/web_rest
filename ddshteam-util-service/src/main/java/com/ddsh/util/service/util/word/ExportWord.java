package com.ddsh.util.service.util.word;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRow;

import com.ddsh.util.service.api.constant.UtilContants;
import com.ddsh.util.service.api.data.word.IWordExportMapper;

public class ExportWord {

	public static XWPFDocument generateWordText(Entry<String, Object> param, XWPFDocument doc) {
		try {
			if (param != null) {
				List<XWPFParagraph> paragraphList = doc.getParagraphs();
				processParagraphs(paragraphList, param, doc);
				processTables(doc.getTables(), param, doc);
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
	public static void processParagraphs(List<XWPFParagraph> paragraphList, Entry<String, Object> param,
			XWPFDocument doc) {
		if (paragraphList != null && paragraphList.size() > 0) {
			for (XWPFParagraph paragraph : paragraphList) {
				List<XWPFRun> runs = paragraph.getRuns();
				StringBuilder sb = null;

				for (int runindex = 0; runindex < runs.size(); runindex++) {
					XWPFRun run = runs.get(runindex);
					String text = run.getText(0);

					if (text != null) {
						if (sb == null) {
							sb = new StringBuilder();
						}
						sb.append(text);
					}
					System.out.println(text);
					if (sb != null && runindex == runs.size() - 1) {
						text = sb.toString();
						boolean isSetText = false;
						run.setText(text, 0);
						String key = param.getKey();
						if (text.indexOf(key) != -1) {
							isSetText = true;
							Object value = param.getValue();
							if (value instanceof String) {// 文本替换
								text = text.replace(key, value.toString());
							}
						}
						if (isSetText) {
							run.setText(text, 0);
						}
					} else {
						run.setText("", 0);
					}
				}
			}
		}
	}

	public static void processTables(List<XWPFTable> xWPFTableList,Entry<String, Object> param, XWPFDocument doc) {
		if (xWPFTableList != null && xWPFTableList.size() > 0) {
			for (XWPFTable xwpftable : xWPFTableList) {

				List<XWPFTableRow> rows = xwpftable.getRows();
				if (rows != null && rows.size() > 0) {
					for (XWPFTableRow row : xwpftable.getRows()) {

						List<XWPFTableCell> cells = row.getTableCells();

						if (cells != null && cells.size() > 0) {
							for (XWPFTableCell cell : cells) {
								String text = cell.getText();
								boolean isSetText = false;

								System.out.println("text----->:"+text);
								System.out.println("key----->:"+param.getKey());
								String key = param.getKey();
								if (text.indexOf(key) != -1) {
									isSetText = true;
									Object value = param.getValue();
									if (value instanceof String) {// 文本替换
										text = text.replace(key, value.toString());
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

	public static void insertImage(String key, List<String> param,XWPFDocument doc) {
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
								
								for(String path:param)
								{
									String ext[]=path.split("\\.");
									run.addPicture(new FileInputStream(path),
											UtilContants.WordImageType.cache.get(ext[ext.length-1]), path,
											Units.toEMU(200), Units.toEMU(200)); 
								}
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

	public static  Boolean addRows(XWPFTable table, List<List<String>> values) {

		XWPFTableRow header=table.getRow(1);
		for(XWPFTableCell cell: header.getTableCells())
		{
			cell.removeParagraph(0);
		}
    	for(int ci=1;ci<values.size();ci++)
		{
			table.createRow();
		}
		
		for (int row_index = 0; row_index < values.size(); row_index++) {
			XWPFTableRow row=null;
			row=table.getRow(row_index+1);

			List<String> value = values.get(row_index);
			for (int col_index = 0; col_index < value.size(); col_index++) {
				row.getCell(col_index).setText(value.get(col_index));
			}
		}
		return true;
	}

	@SuppressWarnings({ "unchecked", "static-access" })
	public static void generalMedia(IWordExportMapper mapper) throws IOException {
		XWPFDocument doc = null;
		if (mapper.getValue() == null || mapper.getValue().size() < 1) {
			return;
		}
		File file = new File(mapper.getModelpath());
		if (!file.exists()) {
			return;
		}
		FileChannel input = new FileInputStream(mapper.getModelpath()).getChannel();
		String modelpath = file.getParent() + File.separator + System.currentTimeMillis() + file.getName();
		FileChannel output = new FileOutputStream(modelpath).getChannel();
		output.transferFrom(input, 0, input.size());

		OPCPackage ocpModel = POIXMLDocument.openPackage(modelpath);
		FileOutputStream fileExp = new FileOutputStream(mapper.getExportpath());
		doc = new XWPFDocument(ocpModel);

		for (Entry<String, Object> entry : mapper.getValue().entrySet()) {
			if (!mapper.getTypeMapper().containsKey(entry.getKey())) {
				continue;
			}
			switch (mapper.getTypeMapper().get(entry.getKey())) {
			case UtilContants.WordMediaType.MEDIA_IMAGE:
				List<String> images = (List<String>) entry.getValue();
				if(images!=null&&images.size()>0)
				{
					insertImage(entry.getKey(), images, doc);
				}
				break;
			case UtilContants.WordMediaType.MEDIA_TABLE:
				String rows_key = (String) mapper.getFlowMapper().get(entry.getKey());
				List<List<String>> rows = (List<List<String>>) mapper.getValue().get(rows_key);
				XWPFTable table=getTableByKey(doc,rows_key);
				if(table!=null&&rows!=null&&rows.size()>0)
				{
					addRows(table, rows);
				}
				
				
				break;

			case UtilContants.WordMediaType.MEDIA_TEXT:
				generateWordText(entry, doc);
				break;
			default:
				break;
			}

		}
		doc.write(fileExp);
	}

	public static XWPFTable getTableByKey(XWPFDocument document, String key) {
		XWPFTable xtable = null;
		for (XWPFTable table : document.getTables()) {
			if (table.getText().contains(key)) {
				xtable = table;
				break;
			}
		}
		return xtable;
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

		// XWPFDocument doc = ExportWord.generateWordText(param,
		// "D:\\project_file\\资料\\采购库存\\model\\example\\inquiry_model_compomnet_exp.docx");
		// FileOutputStream fopts = new
		// FileOutputStream("D:\\project_file\\tt\\"+UUID.randomUUID().toString()+".docx");
		// doc.write(fopts);
		OPCPackage pack = POIXMLDocument
				.openPackage("D:\\project_file\\资料\\采购库存\\model\\example\\inquiry_model_compomnet_exp.docx");
		XWPFDocument doc = new XWPFDocument(pack);
		for (XWPFTable table : doc.getTables()) {
			if (table.getText().contains("INQUIRY_INQUIRY_DETAILS")) {
				System.out.println(table.getText());
			}
		}

		File file = new File("D:\\project_file\\资料\\采购库存\\model\\example\\123.txt");
		System.out.println(file.getParent());
		System.out.println(file.getName());
		System.out.println(File.separator);

		FileChannel input = new FileInputStream("D:\\project_file\\资料\\采购库存\\model\\example\\123.txt").getChannel();
		FileChannel output = new FileOutputStream("D:\\project_file\\资料\\采购库存\\model\\example\\123456789.txt")
				.getChannel();
		output.transferFrom(input, 0, input.size());

		/*
		 * ExportWord.insertTab("${table}", doc); // /----------创建表
		 * 
		 * ExportWord.insertImage("${image}", doc); // /----------创建图
		 * 
		 * // ------替换多余的标志位----//
		 * 
		 * param = new HashMap<String, Object>();
		 * 
		 * param.put("${test}", "下一个段落");
		 * 
		 * param.put("${table}", "");
		 * 
		 * param.put("${image}", "");
		 * 
		 * ExportWord.processParagraphs(doc.getParagraphs(), param, doc);
		 * FileOutputStream fopts = new
		 * FileOutputStream("D:\\project_file\\tt\\template-2.docx");
		 * doc.write(fopts);
		 */

	}
}
