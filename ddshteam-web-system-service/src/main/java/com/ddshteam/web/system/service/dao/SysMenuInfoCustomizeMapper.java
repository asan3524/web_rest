package com.ddshteam.web.system.service.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ddshteam.web.system.service.api.data.Tree;
import com.ddshteam.web.system.service.api.model.SysMenuInfo;

public interface SysMenuInfoCustomizeMapper {

	List<SysMenuInfo> getMenusByUser(String userId);

	List<String> getPermissionByUser(String userId);

	List<Tree> getMenuTreeByUserRole(@Param("userId") String userId, @Param("roleId") String roleId);

	List<Tree> getMenuTreeByRole(String roleId);

	List<Tree> getMenuTreeByUser(String userId);

	List<Tree> getMenuTree();

	int deleteByPrimaryKey(String id);
}