package com.ddshteam.web.system.service.api.constant;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ddshteam.web.system.service.api.annotation.ConstantDescription;

/**
 *  系统管理常量
 * @ClassName: SystemContants
 * @author arpgate
 * @date 2018年3月5日 下午5:41:02
 * @version v1.0.0
 * 
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class SystemContants {
	public static final String PERMISSION = "permission";

	public static final Map<String, Map<String, String>> CONSTANT_DESC = new HashMap<String, Map<String, String>>(40);

	public static final Map<String, String> CONSTANT_TYPE_DESC = new HashMap<String, String>(20);

	public static final Map<String, List<String>> CONSTANT_TYPE_LIST = new HashMap<String, List<String>>();

	static {
		try {
			for (Class cls : SystemContants.class.getClasses()) {
				ConstantDescription cds = (ConstantDescription) cls.getAnnotation(ConstantDescription.class);
				if (cds != null) {
					if (PERMISSION.equals(cds.desc())) {

						List<String> ps = new ArrayList<String>();
						for (Field fd : cls.getDeclaredFields()) {
							if (fd != null) {
								ps.add(fd.get(cls).toString());
							}
						}

						CONSTANT_TYPE_LIST.put(PERMISSION, ps);
						continue;
					}

					CONSTANT_TYPE_DESC.put(cls.getSimpleName(), cds.desc());
					Map<String, String> valueDesc = new HashMap<String, String>(10);
					CONSTANT_DESC.put(cls.getSimpleName(), valueDesc);

					for (Field fd : cls.getDeclaredFields()) {
						ConstantDescription cdf = fd.getAnnotation(ConstantDescription.class);
						if (cdf != null) {
							valueDesc.put(fd.get(cls).toString(), cdf.desc());

						}
					}

				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@ConstantDescription(desc = "部门是否有效")
	public static final class SysDeptStatus {
		@ConstantDescription(desc = "有效")
		public static final int EFFECT = 1;

		@ConstantDescription(desc = "失效")
		public static final int LOSE_EFFECT = 0;

	}

	@ConstantDescription(desc = "菜单资源类型")
	public static final class SysMenuTypes {
		@ConstantDescription(desc = "菜单目录")
		public static final int MENU_ITEM = 1;

		@ConstantDescription(desc = "菜单页面")
		public static final int MENU_PAGE = 2;

		@ConstantDescription(desc = "菜单功能(按钮)")
		public static final int MENU_FEATURE = 3;

	}

	@ConstantDescription(desc = "系统用户是否有效")
	public static final class SysUserStatus {
		@ConstantDescription(desc = "有效")
		public static final int EFFECT = 1;

		@ConstantDescription(desc = "失效")
		public static final int LOSE_EFFECT = 0;

	}

	@ConstantDescription(desc = "系统用户是否有效")
	public static final class SysUserSex {
		@ConstantDescription(desc = "男人")
		public static final int MAN = 1;

		@ConstantDescription(desc = "女人")
		public static final int WOMAN = 0;

	}

	@ConstantDescription(desc = "系统用户是否内置账户,内置账户页面不可见")
	public static final class SysUserIsBuiltin {
		@ConstantDescription(desc = "内置账户")
		public static final boolean BUILTIN = true;

		@ConstantDescription(desc = "非内置账户")
		public static final boolean NOT_BUILTIN = false;

		@ConstantDescription(desc = "账户默认密码")
		public static final String DEFAULT_PWD = "123456";

	}

	@ConstantDescription(desc = SystemContants.PERMISSION)
	public static final class Permission {
		/**
		 *  登录
		 */
		public static final String PERMISSION_LOGIN = "system:login";
		/**
		 *  登出
		 */
		public static final String PERMISSION_LOGOUT = "system:logout";

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

		/**
		 * 部门
		 */
		public static final String PERMISSION_DEPT_TREE = "system:dept:tree";
		public static final String PERMISSION_DEPT_TREE2USER = "system:dept:tree2user";
		public static final String PERMISSION_DEPT_LIST = "system:dept:list";
		public static final String PERMISSION_DEPT_INFO = "system:dept:info";
		public static final String PERMISSION_DEPT_SAVE = "system:dept:save";
		public static final String PERMISSION_DEPT_UPDATE = "system:dept:update";
		public static final String PERMISSION_DEPT_DELETE = "system:dept:delete";

		/**
		 * 部门类型
		 */
		public static final String PERMISSION_DEPTTYPE_LIST = "system:depttype:list";
		public static final String PERMISSION_DEPTTYPE_INFO = "system:depttype:info";
		public static final String PERMISSION_DEPTTYPE_SAVE = "system:depttype:save";
		public static final String PERMISSION_DEPTTYPE_UPDATE = "system:depttype:update";
		public static final String PERMISSION_DEPTTYPE_DELETE = "system:depttype:delete";

		/**
		 * 角色
		 */
		public static final String PERMISSION_ROLE_ROLE2MENU = "system:role:role2menu";
		public static final String PERMISSION_ROLE_LIST = "system:role:list";
		public static final String PERMISSION_ROLE_INFO = "system:role:info";
		public static final String PERMISSION_ROLE_SAVE = "system:role:save";
		public static final String PERMISSION_ROLE_UPDATE = "system:role:update";
		public static final String PERMISSION_ROLE_DELETE = "system:role:delete";
		public static final String PERMISSION_ROLE_DELETES = "system:role:deletes";

		/**
		 * 用户
		 */
		public static final String PERMISSION_USER_CHANGEPWD = "system:user:changepwd";
		public static final String PERMISSION_USER_PWD = "system:user:pwd";
		public static final String PERMISSION_USER_LIST = "system:user:list";
		public static final String PERMISSION_USER_INFO = "system:user:info";
		public static final String PERMISSION_USER_SAVE = "system:user:save";
		public static final String PERMISSION_USER_UPDATE = "system:user:update";
		public static final String PERMISSION_USER_DELETE = "system:user:delete";
		public static final String PERMISSION_USER_DELETES = "system:user:deletes";
	}
}
