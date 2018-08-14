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
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.apache.xmlbeans.XmlCursor;

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
					//System.out.println(text);
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
						StringBuilder sb = null;
						if (cells != null && cells.size() > 0) {
							for (XWPFTableCell cell : cells) {
								String text = cell.getText();
								boolean isSetText = false;

								if (text != null) {
									if (sb == null) {
										sb = new StringBuilder();
									}
									sb.append(text);
								}
								String key = param.getKey();
								if (sb.indexOf(key) != -1) {
									isSetText = true;
									Object value = param.getValue();
									if (value instanceof String) {// 文本替换
										sb=new StringBuilder(sb.toString().replace(key, value.toString()));
										 //text= text.replace(key, value.toString());
									}
								}
							
								if (isSetText) {
									cell.removeParagraph(0);
									cell.setText(sb.toString());
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
			for(int pi=cell.getParagraphs().size()-1;pi>=0;pi--)
			{
				cell.removeParagraph(pi);
			}
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
		
		if(input!=null)
		{
			input.close();
		}
		
		if(output!=null)
		{
			output.close();
		}
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
				//String rows_key = (String) mapper.getFlowMapper().get(entry.getKey());
				List<List<String>> rows = (List<List<String>>) mapper.getValue().get(entry.getKey());
				XWPFTable table=getTableByKey(doc,entry.getKey());
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
		
		if(mapper.getDeletes()!=null&&mapper.getDeletes().size()>0)
		{
			for(String key:mapper.getDeletes())
			{
				removeTableByKey(doc, key);
			}
		}
		doc.write(fileExp);
		
		if(ocpModel!=null)
		{
			ocpModel.close();
		}
		
		File mfile=new File(modelpath);
		if(mfile.exists())
		{
			mfile.delete();
		}
	}

	
	
	public static void removeTableByKey(XWPFDocument document, String key) {
		for (XWPFTable table : document.getTables()) {
			for(XWPFTableRow row:table.getRows())
			{
				for(XWPFTableCell cell:row.getTableCells())
				{
					for(XWPFTable ctable:cell.getTables())
					{
						if (ctable.getText().contains(key)) {
							for(int rowIndex=ctable.getRows().size()-1;rowIndex>=0;rowIndex--)
							{
								ctable.removeRow(rowIndex);
							}
							row.setHeight(0);
							cell.setText("");
							return;
						}
					}
				}
			}
		}
	}
	
	public static XWPFTable getTableByKey(XWPFDocument document, String key) {
		XWPFTable xtable = null;
		for (XWPFTable table : document.getTables()) {
			if (table.getText().contains(key)) {
				xtable = table;
				break;
			}
			
			for(XWPFTableRow row:table.getRows())
			{
				for(XWPFTableCell cell:row.getTableCells())
				{
					for(XWPFTable ctable:cell.getTables())
					{
						if (ctable.getText().contains(key)) {
							xtable = ctable;
							break;
						}
					}
					
				}
			}
		}
		return xtable;
	}

 
}
