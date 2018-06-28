package com.ddsh.util.service.api;

import com.ddsh.util.service.api.data.GoodsPrintLablelReqData;

/**
 * 物质服务
 * @ClassName: IGoodsService
 * @author arpgate
 * @date 2018年2月28日 上午10:55:06
 * @version v1.0.0
 * 
 */
public interface IImageService {
	
	/**
	 * 根据物资信息输出打印图片标签
	 * @Title: get
	 * @param reqData
	 * @return String
	 * @see 
	 * @throws
	 * @author arpgate
	 */
	public String getPrintLablel(GoodsPrintLablelReqData reqData);
 
}
