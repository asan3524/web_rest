package com.ddsh.pdf.service.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.ddsh.pdf.service.api.IPDFService;
import com.ddsh.pdf.service.util.pdf.ExportPdf;

@Service(version = "1.0.0")
@Transactional(noRollbackFor=RuntimeException.class)
public class PDFServiceImpl implements IPDFService {

	@Override
	public void exportWordToPDf(String inpath,String outpath) { 
		FileInputStream inStream=null;
		FileOutputStream outStream=null;

		try {
			inStream = new FileInputStream(inpath);
			outStream = new FileOutputStream(outpath);
			ExportPdf pdf =new ExportPdf(inStream, outStream, true, false);
			pdf.convert();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(inStream!=null)
			{
				try {
					inStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if(outStream!=null)
			{
				try {
					outStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
