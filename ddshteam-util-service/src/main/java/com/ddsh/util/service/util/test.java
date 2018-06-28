package com.ddsh.util.service.util;

import com.ddsh.util.service.api.constant.UtilContants;
import com.ddsh.util.service.api.data.GoodsPrintLablelReqData;
import com.ddsh.util.service.util.labelprinter.JaxbXmlUtil;
import com.ddsh.util.service.util.labelprinter.LabelUtil;
import com.ddsh.util.service.util.labelprinter.weiwen.LabelData;

public class test {

	public static void main(String[] args) {
		GoodsPrintLablelReqData reqData=new GoodsPrintLablelReqData();
		reqData.setName("戴尔笔记本");
		reqData.setBrand("戴尔");
		reqData.setCode("M12105809940000000000");
		reqData.setColor("银色");
		reqData.setInsotretime("2018-05-31");
		reqData.setNorm("大的");
		reqData.setType("笔记本电脑");
	   	 JaxbXmlUtil jaxbXmlUtil=new JaxbXmlUtil();
	   	 String  value=jaxbXmlUtil.readFileForXml(UtilContants.Sysset.LABLE_PATH+UtilContants.Sysset.LABLE_MODEL_NAME);
	   	 try {
				LabelData labelData=jaxbXmlUtil.convertToJavaBean(value, LabelData.class);
				labelData.setGoodsid(reqData.getCode());
				labelData.parseReqDataToLabelData(reqData);
				LabelUtil lu=new LabelUtil(labelData);
				lu.drawLabel();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

}
