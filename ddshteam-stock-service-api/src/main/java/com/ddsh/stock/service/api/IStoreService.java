package com.ddsh.stock.service.api;

import java.util.List;

import com.ddsh.stock.service.api.model.StoreInfo;
import com.ddshteam.web.system.service.api.data.Tree;

public interface IStoreService {

	/**
	 * 获取所有仓库树，用于调拨到目标库查询
	 * @Title: getStoreTree
	 * @return List<Tree>
	 * @author lishibang
	 */
	public List<Tree> getStoreTree();
	
	/**
	 * 根据用户获取有权限的仓库树
	 * @Title: getStoreTreeByUser
	 * @param userId
	 * @return List<Tree>
	 * @author lishibang
	 */
	public List<Tree> getStoreTreeByUser(String userId);

	public StoreInfo getType(String id);

	public boolean save(StoreInfo typeInfo);

	public boolean update(StoreInfo typeInfo);

	/**
	 * 根据ID删除类型
	 * @Title: delete
	 * @param id
	 * @return boolean
	 * @author lishibang
	 */
	public boolean delete(String id);

	/**
	 * 设置角色拥有的仓库权限
	 * @Title: setRoleStore
	 * @param roleId
	 * @param storeIds
	 * @return boolean
	 * @author lishibang
	 */
	public boolean setRoleStore(String roleId, String... storeIds);
}
