package com.ddshteam.web.shrio;

import java.util.ArrayList;
import java.util.List;

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
		permissions.add(Constant.PERMISSION_MENU_TREE);
		permissions.add(Constant.PERMISSION_MENU_TREE2STATUS);
		permissions.add(Constant.PERMISSION_MENU_TREE2USER);
		permissions.add(Constant.PERMISSION_MENU_INFO);
		permissions.add(Constant.PERMISSION_MENU_SAVE);
		permissions.add(Constant.PERMISSION_MENU_UPDATE);
		permissions.add(Constant.PERMISSION_MENU_DELETE);
		permissions.add(Constant.PERMISSION_OPLOG_LIST);
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

	/**
	 *  菜单
	 */
	public static final String PERMISSION_MENU_TREE = "system:menu:tree";
	public static final String PERMISSION_MENU_TREE2STATUS = "system:menu:tree2status";
	public static final String PERMISSION_MENU_TREE2USER = "system:menu:tree2user";
	public static final String PERMISSION_MENU_INFO = "system:menu:info";
	public static final String PERMISSION_MENU_SAVE = "system:menu:save";
	public static final String PERMISSION_MENU_UPDATE = "system:menu:update";
	public static final String PERMISSION_MENU_DELETE = "system:menu:delete";

	/**
	 * 操作日志
	 */
	public static final String PERMISSION_OPLOG_LIST = "system:oplog:list";
}
