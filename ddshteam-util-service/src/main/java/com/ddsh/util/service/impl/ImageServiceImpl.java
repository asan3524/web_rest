package com.ddsh.util.service.impl;

import java.io.File;

import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.ddsh.util.service.api.IImageService;
import com.ddsh.util.service.api.constant.UtilContants;
import com.ddsh.util.service.api.data.GoodsPrintLablelReqData;
import com.ddsh.util.service.util.labelprinter.JaxbXmlUtil;
import com.ddsh.util.service.util.labelprinter.LabelUtil;
import com.ddsh.util.service.util.labelprinter.weiwen.LabelData;

@Service(version = "1.0.0")
@Transactional(noRollbackFor=RuntimeException.class)
public class ImageServiceImpl implements IImageService {

	@Override
	public String getPrintLablel(GoodsPrintLablelReqData reqData) {
	 String path="";

   	 JaxbXmlUtil jaxbXmlUtil=new JaxbXmlUtil();
   	 String  value=jaxbXmlUtil.readFileForXml(UtilContants.Sysset.LABLE_PATH+UtilContants.Sysset.LABLE_MODEL_NAME);
   	 try {
			LabelData labelData=jaxbXmlUtil.convertToJavaBean(value, LabelData.class);
			File file = new File(UtilContants.Sysset.LABLE_PATH+labelData.getGoodsid()+".jpg");  
			if(file.exists())
			{
				return file.getPath();
			}
			labelData.setGoodsid(reqData.getCode());
			labelData.parseReqDataToLabelData(reqData);
			LabelUtil lu=new LabelUtil(labelData);
			path=lu.drawLabel();
		} catch (Exception e) {
			e.printStackTrace();
		}
   	 
   	 return path;
	}

}
