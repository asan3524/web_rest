package com.ddshteam.web.shrio;

import java.util.ArrayList;
import java.util.List;

import com.ddshteam.web.system.service.api.constant.SystemContants;

/**
 * 权限标注常量
 * @ClassName: Constant
 * @author lishibang
 * @date 2018年3月6日 下午2:54:07
 * @version v1.0.0
 */
public class Constant {

	private static List<String> permissions = new ArrayList<String>();

	static {
		permissions.addAll(SystemContants.CONSTANT_TYPE_LIST.get(SystemContants.PERMISSION));
	}

	/**
	 * root内置用户权限初始化到常量池，默认有所有权限
	 * @Title: rootPermission
	 * @return List<String>
	 * @author lishibang
	 */
	public static List<String> rootPermission() {
		return permissions;
	}
}
