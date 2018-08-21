package com.ddsh.pdf.service.api;

/**
 * pdf文件导出
 * @ClassName: IPDFService
 * @author arpgate
 * @date 2018年8月9日 下午10:20:40
 * @version v1.0.0
 * 
 */
public interface IPDFService {
	
 
	/**
	 * 导出pdf
	 * @Title: exportPDf
	 * @param inpath
	 * @param outpath
	 * @return void
	 * @see 
	 * @throws
	 * @author arpgate
	 */
	void exportWordToPDf(String orderid,String inpath,String outpath);

}
