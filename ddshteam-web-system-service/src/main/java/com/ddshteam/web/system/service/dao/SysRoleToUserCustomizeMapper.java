package com.ddshteam.web.system.service.dao;

import org.apache.ibatis.annotations.Param;

public interface SysRoleToUserCustomizeMapper {

	int insertRoleToUsers(@Param("userid") String userid,@Param("roleids") String...roleid);
}
