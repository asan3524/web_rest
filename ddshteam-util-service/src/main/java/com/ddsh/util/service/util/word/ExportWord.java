package com.ddsh.util.service.util.word;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.List;
import java.util.Map.Entry;

import javax.imageio.ImageIO;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFHeader;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlToken;

import com.ddsh.util.service.api.constant.UtilContants;
import com.ddsh.util.service.api.data.word.IWordExportMapper;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTInline;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveSize2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingProps;

public class ExportWord {
	
	 
	public static XWPFDocument generateWordTopText(Entry<String, Object> param, XWPFDocument doc)
	{
        XWPFHeader header = doc.getHeaderList().get(0);
		processParagraphs(header.getParagraphs(), param, doc);
		return doc;
	}

	public static XWPFDocument generateWordText(Entry<String, Object> param, XWPFDocument doc) {
		try {
			if (param != null) {
				List<XWPFParagraph> paragraphList = doc.getParagraphs();
				//processParagraphs(paragraphList, param, doc);
				processTables(doc.getTables(), param, doc);
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		return doc;

	}

 
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
					if (sb != null && runindex == runs.size() - 1) {
						text = sb.toString();
						boolean isSetText = false;
						run.setText(text, 0);
						String key = param.getKey();
						//System.out.println(text);
						if (text.indexOf(key) != -1) {
							isSetText = true;
							Object value = param.getValue();
							if (value instanceof String) {// �ı��滻
								text = text.replace(key, value.toString());
								//System.out.println("key:"+key+"value:"+value);
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
								processParagraphs(cell.getParagraphs(),param,doc);
							}

						}
					}
				}

			}
		}
	}
	
	
	
	private static void removeParagraphs(XWPFTableCell cell) {
		for(int p_index=cell.getParagraphs().size()-1;p_index>=0;p_index--)
		{
			cell.removeParagraph(p_index);
		}
	}

 

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

							XWPFTable tableOne = doc2.insertNewTbl(cursor);// ---����ǹؼ�
						}

					}

				}

			}

		}

	}
  
	public static void generalImage(String key, List<String> param,XWPFDocument doc)
	{
		for(XWPFTable table:doc.getTables())
		{
			for(XWPFTableRow row:table.getRows())
			{
				for(XWPFTableCell cell:row.getTableCells())
				{
				   if(insertImage(key,param,cell.getParagraphs(),table.getWidth()/20))
					{
 						return;
 						
					} 
				}
			}
		}
	}
	
	public static boolean insertImage(String key, List<String> param,List<XWPFParagraph> paragraphList,int width) {
		boolean hasdo=false;
		try {
			if (paragraphList != null && paragraphList.size() > 0) {
				for (XWPFParagraph paragraph : paragraphList) {
					List<XWPFRun> runs = paragraph.getRuns();
					for (XWPFRun run : runs) {
						String text = run.getText(0);
						if (text != null) {
							if (text.indexOf(key) >= 0) {
								run.setText("",0);
								//run.addBreak(BreakType.TEXT_WRAPPING);
								hasdo=true;
								for(String path:param)
								{
									
									  File picture = new File(path);
									  if(!picture.exists())
									  {
										  return true;
									  }
									  
							        BufferedImage sourceImg =ImageIO.read(new FileInputStream(picture)); 
							        int scale=sourceImg.getWidth()/(width-200);
							        if(scale<=0)
							        {
							        	scale=1;
							        }
									String ext[]=path.split("\\.");
									run.addBreak(BreakType.PAGE);
									run.addPicture(new FileInputStream(path),
											UtilContants.WordImageType.cache.get(ext[ext.length-1]), path,
											Units.toEMU(sourceImg.getWidth()/scale), Units.toEMU(sourceImg.getHeight()/scale)); 
								}
								run.addBreak(BreakType.PAGE);
								return true;
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

		return hasdo;
	}
	
	
	public static boolean insertImageFixed(String key, List<String> param,List<XWPFParagraph> paragraphList,int width) {
		boolean hasdo=false;
		try {
			if (paragraphList != null && paragraphList.size() > 0) {
				for (XWPFParagraph paragraph : paragraphList) {
					List<XWPFRun> runs = paragraph.getRuns();
					for (XWPFRun run : runs) {
						String text = run.getText(0);
						if (text != null) {
							if (text.indexOf(key) >= 0) {
								run.setText("",0);
								hasdo=true;
								for(String path:param)
								{
									
									  File picture = new File(path);
									  if(!picture.exists())
									  {
										  return true;
									  }
									  
							        BufferedImage sourceImg =ImageIO.read(new FileInputStream(picture)); 
							        int scale=sourceImg.getWidth()/(width-200);
							        if(scale<=0)
							        {
							        	scale=1;
							        }
									String ext[]=path.split("\\.");
									run.addBreak(BreakType.PAGE);
 									
 									String ind = run.getDocument().addPictureData(new FileInputStream(path), UtilContants.WordImageType.cache.get(ext[ext.length-1]));
 									int id =  run.getDocument().getNextPicNameNumber(UtilContants.WordImageType.cache.get(ext[ext.length-1]));
 									createPicture(ind, id, Units.toEMU(sourceImg.getWidth()/scale), Units.toEMU(sourceImg.getHeight()/scale),paragraph); 
								}
								run.addBreak(BreakType.PAGE);
								return true;
							}
							
						}

					}
					paragraph.setPageBreak(true);
				}
			}

		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();

		}

		return hasdo;
	}
	
	
	
	public static void createPicture(String blipId, int id, int width, int height,XWPFParagraph paragraph) {  
        final int EMU = 9525;  
        width *= EMU;  
        height *= EMU;  
        //String blipId = getAllPictures().get(id).getPackageRelationship().getId();  
        CTInline inline = paragraph.createRun().getCTR().addNewDrawing().addNewInline();  

        String picXml = "" +  
                "<a:graphic xmlns:a=\"http://schemas.openxmlformats.org/drawingml/2006/main\">" +  
                "   <a:graphicData uri=\"http://schemas.openxmlformats.org/drawingml/2006/picture\">" +  
                "      <pic:pic xmlns:pic=\"http://schemas.openxmlformats.org/drawingml/2006/picture\">" +  
                "         <pic:nvPicPr>" +  
                "            <pic:cNvPr id=\"" + id + "\" name=\"Generated\"/>" +  
                "            <pic:cNvPicPr/>" +  
                "         </pic:nvPicPr>" +  
                "         <pic:blipFill>" +  
                "            <a:blip r:embed=\"" + blipId + "\" xmlns:r=\"http://schemas.openxmlformats.org/officeDocument/2006/relationships\"/>" +  
                "            <a:stretch>" +  
                "               <a:fillRect/>" +  
                "            </a:stretch>" +  
                "         </pic:blipFill>" +  
                "         <pic:spPr>" +  
                "            <a:xfrm>" +  
                "               <a:off x=\"0\" y=\"0\"/>" +  
                "               <a:ext cx=\"" + width + "\" cy=\"" + height + "\"/>" +  
                "            </a:xfrm>" +  
                "            <a:prstGeom prst=\"rect\">" +  
                "               <a:avLst/>" +  
                "            </a:prstGeom>  " +  
                "         </pic:spPr>" +  
                "      </pic:pic>" +  
                "   </a:graphicData>" +  
                "</a:graphic>";  

        // CTGraphicalObjectData graphicData =   
        inline.addNewGraphic().addNewGraphicData();  
        XmlToken xmlToken = null;  
        try {  
            xmlToken = XmlToken.Factory.parse(picXml);  
        } catch (XmlException xe) {  
            xe.printStackTrace();  
        }  
        inline.set(xmlToken);  
        inline.setDistT(0);  
        inline.setDistB(0);  
        inline.setDistL(0);  
        inline.setDistR(0);  

        CTPositiveSize2D extent = inline.addNewExtent();  
        extent.setCx(width);  
        extent.setCy(height);  

        CTNonVisualDrawingProps docPr = inline.addNewDocPr();  
        docPr.setId(id);  
        docPr.setName("Picture" + id);  
        docPr.setDescr("Generated");  
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
				if(images!=null)
				{
					generalImage(entry.getKey(), images, doc);
				}
				break;
			case UtilContants.WordMediaType.MEDIA_TABLE:
				List<List<String>> rows = (List<List<String>>) mapper.getValue().get(entry.getKey());
				XWPFTable table=getTableByKey(doc,entry.getKey());
				if(table!=null&&rows!=null&&rows.size()>0)
				{
					addRows(table, rows);
				}
				
				break;

			case UtilContants.WordMediaType.MEDIA_TEXT:
				if(mapper.getFlowMapper().containsKey(entry.getKey())&&((int)mapper.getFlowMapper().get(entry.getKey()))==UtilContants.WordBodyType.TOP)
				{
					generateWordTopText(entry, doc);
				}
				else
				{
					generateWordText(entry, doc);
				}

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
	
	public static void clearValueBykey(XWPFDocument document, String key) {
		for (XWPFTable table : document.getTables()) {
			for(XWPFTableRow row:table.getRows())
			{
				for(XWPFTableCell cell:row.getTableCells())
				{
					if(cell.getText().contains(key))
					   //cell.removeParagraph(0);
					   cell.getParagraphArray(0).getRuns().get(0).setText("", 0);
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
