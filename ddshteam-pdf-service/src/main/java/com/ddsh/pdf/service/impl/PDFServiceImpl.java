package com.ddsh.pdf.service.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.ddsh.pdf.service.api.IPDFService;
import com.ddsh.pdf.service.api.model.AttAttachmentInfo;
import com.ddsh.pdf.service.dao.AttAttachmentInfoMapper;
import com.ddsh.pdf.service.util.pdf.ExportPdf;

@Service(version = "1.0.0")
@Transactional(noRollbackFor=RuntimeException.class)
public class PDFServiceImpl implements IPDFService {

	@Autowired
	AttAttachmentInfoMapper attAttachmentInfoDao;
	
	@Override
	public void exportWordToPDf(String orderid,String inpath,String outpath) { 
		AttAttachmentInfo aai=attAttachmentInfoDao.selectByPrimaryKey(orderid+"-"+"pdf");
		if(aai!=null)
		{
			return;
		}
		
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

		
		AttAttachmentInfo attAttachmentInfo=new AttAttachmentInfo();
		attAttachmentInfo.setId(orderid+"-"+"pdf");
		attAttachmentInfo.setObjId(orderid);
		attAttachmentInfo.setObjSubId("pdf");
		attAttachmentInfo.setPath(outpath);
		attAttachmentInfo.setUpdateTime(new Date());
		attAttachmentInfoDao.insert(attAttachmentInfo);
	}

}
