package com.ddsh.util.service.api;

/**
 * pdf文件导出
 * @ClassName: IWordService
 * @author arpgate
 * @date 2018年8月9日 下午10:20:40
 * @version v1.0.0
 * 
 */
public interface IPDFService {
	
	
	/**
	 * 导出采购详单
	 * @Title: exportPurchase
	 * @param payMentId
	 * @param userName
	 * @param userId
	 * @return String
	 * @see 
	 * @throws
	 * @author arpgate
	 */
	String exportPurchase(String orderId,String userName,String userId);
	
	/**
	 * 导出询价详单
	 * @Title: exportInqury
	 * @param payMentId
	 * @param userName
	 * @param userId
	 * @return String
	 * @see 
	 * @throws
	 * @author arpgate
	 */
	String exportInqury(String orderId,String userName,String userId);

}
