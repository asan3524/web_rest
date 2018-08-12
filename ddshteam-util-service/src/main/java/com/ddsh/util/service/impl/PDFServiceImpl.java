package com.ddsh.util.service.impl;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.ddsh.util.service.api.IPDFService;
import com.ddsh.util.service.api.constant.UtilContants;
import com.ddsh.util.service.api.data.word.bean.InquiryWordMapper;
import com.ddsh.util.service.api.model.InqInquiryOrder;
import com.ddsh.util.service.api.model.PurPurchaseOrder;
import com.ddsh.util.service.dao.InqInquiryOrderMapper;
import com.ddsh.util.service.dao.PurPurchaseOrderMapper;
import com.ddsh.util.service.util.OSUtil;

@Service(version = "1.0.0")
@Transactional(noRollbackFor=RuntimeException.class)
public class PDFServiceImpl implements IPDFService {

	@Autowired
	InqInquiryOrderMapper inqInquiryOrderDao;
	
	@Autowired
	PurPurchaseOrderMapper purPurchaseOrderDao;
	
	@Override
	public String exportPurchase(String orderId, String userName, String userId) {
		PurPurchaseOrder purPurchaseOrder=purPurchaseOrderDao.selectByPrimaryKey(orderId);
		return null;
	}

	@Override
	public String exportInqury(String orderId, String userName, String userId) {
		String exportname="export";
		InqInquiryOrder inqInquiryOrder=inqInquiryOrderDao.selectByPrimaryKey(orderId);
		if(inqInquiryOrder!=null)
		{
			exportname=inqInquiryOrder.getName();
		}
		else
		{
			return null;
		}
		InquiryWordMapper inquiryWordMapper=new InquiryWordMapper();
		
		inquiryWordMapper.setModelpath(OSUtil.getBasePath()+UtilContants.Sysset.MODEL_PATH+UtilContants.Sysset.INQUIRY_MODEL);
		inquiryWordMapper.setExportpath(OSUtil.getBasePath()+UtilContants.Sysset.UPLOAD_ROOT_PATH+orderId+File.separator+exportname);
		
		inquiryWordMapper.getValue().put(inquiryWordMapper.I_D_1, inqInquiryOrder.getUseDepName());
		inquiryWordMapper.getValue().put(inquiryWordMapper.I_D_2, inqInquiryOrder.getName());
		inquiryWordMapper.getValue().put(inquiryWordMapper.I_D_3, value);
		inquiryWordMapper.getValue().put(inquiryWordMapper.I_D_4, value);
		inquiryWordMapper.getValue().put(inquiryWordMapper.I_D_5, value);
		inquiryWordMapper.getValue().put(inquiryWordMapper.I_D_6, value);
		inquiryWordMapper.getValue().put(inquiryWordMapper.I_D_7, value);
		inquiryWordMapper.getValue().put(inquiryWordMapper.I_D_8, value);
		inquiryWordMapper.getValue().put(inquiryWordMapper.I_D_9, value);
		inquiryWordMapper.getValue().put(inquiryWordMapper.I_D_10, value);
		inquiryWordMapper.getValue().put(inquiryWordMapper.I_D_11, value);
		inquiryWordMapper.getValue().put(inquiryWordMapper.I_D_12, value);
		inquiryWordMapper.getValue().put(inquiryWordMapper.I_D_13, value);
		inquiryWordMapper.getValue().put(inquiryWordMapper.I_D_14, value);
		inquiryWordMapper.getValue().put(inquiryWordMapper.I_D_15, value);
		inquiryWordMapper.getValue().put(inquiryWordMapper.I_D_16, value);
		inquiryWordMapper.getValue().put(inquiryWordMapper.I_D_17, value);
		inquiryWordMapper.getValue().put(inquiryWordMapper.I_D_18, value);
		inquiryWordMapper.getValue().put(inquiryWordMapper.I_D_19, value);
		inquiryWordMapper.getValue().put(inquiryWordMapper.I_D_20, value);
		inquiryWordMapper.getValue().put(inquiryWordMapper.I_D_21, value);
		inquiryWordMapper.getValue().put(inquiryWordMapper.I_D_22, value);
		inquiryWordMapper.getValue().put(inquiryWordMapper.I_D_23, value);
		inquiryWordMapper.getValue().put(inquiryWordMapper.I_D_24, value);
		inquiryWordMapper.getValue().put(inquiryWordMapper.I_D_25, value);
		inquiryWordMapper.getValue().put(inquiryWordMapper.I_D_26, value);
		inquiryWordMapper.getValue().put(inquiryWordMapper.I_D_27, value);
		inquiryWordMapper.getValue().put(inquiryWordMapper.I_D_28, value);
		inquiryWordMapper.getValue().put(inquiryWordMapper.I_D_29, value);


		inquiryWordMapper.getValue().put(inquiryWordMapper.I_I_D_1, value);
		inquiryWordMapper.getValue().put(inquiryWordMapper.I_I_D_2, value);
		inquiryWordMapper.getValue().put(inquiryWordMapper.I_I_D_3, value);
		inquiryWordMapper.getValue().put(inquiryWordMapper.I_I_D_4, value);
		inquiryWordMapper.getValue().put(inquiryWordMapper.I_I_D_5, value);
		inquiryWordMapper.getValue().put(inquiryWordMapper.I_I_D_6, value);
		inquiryWordMapper.getValue().put(inquiryWordMapper.I_I_D_7, value);
		inquiryWordMapper.getValue().put(inquiryWordMapper.I_I_D_8, value);
		inquiryWordMapper.getValue().put(inquiryWordMapper.I_I_D_9, value);
		inquiryWordMapper.getValue().put(inquiryWordMapper.I_I_D_10, value);


		
		
		inquiryWordMapper.getValue().put(inquiryWordMapper.I_A_D_1, value);
		inquiryWordMapper.getValue().put(inquiryWordMapper.I_A_D_2, value);
		inquiryWordMapper.getValue().put(inquiryWordMapper.I_A_D_3, value);
		inquiryWordMapper.getValue().put(inquiryWordMapper.I_A_D_4, value);
		inquiryWordMapper.getValue().put(inquiryWordMapper.I_A_D_5, value);
		inquiryWordMapper.getValue().put(inquiryWordMapper.I_A_D_6, value);
		inquiryWordMapper.getValue().put(inquiryWordMapper.I_A_D_7, value);
		inquiryWordMapper.getValue().put(inquiryWordMapper.I_A_D_8, value);
		 
		
		return null;
	}

}
