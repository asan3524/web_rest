package com.ddsh.pdf.service.util.pdf;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ResourceBundle;

import org.apache.poi.xwpf.converter.pdf.PdfConverter;
import org.apache.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.lowagie.text.Font;
import com.lowagie.text.pdf.BaseFont;

import fr.opensagres.xdocreport.itext.extension.font.ITextFontRegistry;

import java.awt.Color;



public class ExportPdf extends Export {

	public ExportPdf(InputStream inStream, OutputStream outStream, boolean showMessages,
			boolean closeStreamsWhenComplete) {
		super(inStream, outStream, showMessages, closeStreamsWhenComplete);
	}

	public void convert() throws Exception {
		loading();

		PdfOptions options = PdfOptions.create();
		
		
		XWPFDocument document = new XWPFDocument(inStream);

		// 支持中文字体
		options.fontProvider(new ITextFontRegistry() {
			public Font getFont(String familyName, String encoding, float size, int style, Color color) {
				try {
					
					Resource fileRource = new ClassPathResource("simsun.ttf");
					String path = fileRource.getFile().getAbsolutePath();

					BaseFont bfChinese = BaseFont.createFont(path, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
					Font fontChinese = new Font(bfChinese, size, style, color);
					if (familyName != null)
						fontChinese.setFamily(familyName);
					return fontChinese;
				} catch (Throwable e) {
					e.printStackTrace();
					return ITextFontRegistry.getRegistry().getFont(familyName, encoding, size, style, color);
				}
			}

		});
		processing();
		try
		{
			PdfConverter.getInstance().convert(document, outStream, options);
		}catch (Exception e) {
			e.printStackTrace();
		}
		finished();
	}

}
