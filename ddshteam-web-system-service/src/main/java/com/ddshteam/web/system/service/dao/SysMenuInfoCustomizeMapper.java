package com.ddshteam.web.system.service.dao;

import java.util.List;

import com.ddshteam.web.system.service.api.model.SysMenuInfo;

public interface SysMenuInfoCustomizeMapper {

	List<SysMenuInfo> getMenusByUser(String userId);

	List<String> getPermissionByUser(String userId);

	List<SysMenuInfo> getMenuByRole(String roleId);
}