package com.ddsh.stock.service.api;

import com.ddsh.stock.service.api.data.OutstoreListReqData;
import com.ddsh.stock.service.api.data.OutstoreListRespData;
import com.ddsh.stock.service.api.data.OutstoreReqData;
import com.github.pagehelper.PageInfo;

public interface IOutstoreService {

	/**
	 * 获取当前用户可见的出库清单
	 * @Title: list
	 * @param pageNum
	 * @param pageSize
	 * @param userId
	 * @param reqData
	 * @return PageInfo<OutstoreListRespData>
	 * @see 
	 * @throws
	 * @author lishibang
	 */
	public PageInfo<OutstoreListRespData> list(int pageNum, int pageSize, String userId, OutstoreListReqData reqData);

	/**
	 * 出库（领用）记录，出库的仓库必须是当前用户有权限操作的仓库
	 * @Title: outstore
	 * @param userId
	 * @param reqData
	 * @return boolean
	 * @author lishibang
	 */
	public boolean outstore(String userId, OutstoreReqData reqData);

	/**
	 * 允许出库员重新出库（删除之前自己的出库单）
	 * @Title: delete
	 * @param userId
	 * @param outstoreId
	 * @return boolean
	 * @author lishibang
	 */
	public boolean delete(String userId, String outstoreId);
}
