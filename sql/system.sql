-- ----------------------------
-- Table structure for `sys_user_info`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_info`;
CREATE TABLE `sys_user_info` (
  `id` varchar(64) NOT NULL COMMENT '主键ID',
  `account` varchar(128) NOT NULL COMMENT '账号',
  `password` varchar(64) NOT NULL COMMENT '用户登录密码',
  `name` varchar(128) NOT NULL COMMENT '姓名',
  `sex` int(11) DEFAULT NULL COMMENT '性别：1男0女',
  `email` varchar(128) DEFAULT NULL COMMENT '用户邮箱地址',
  `fax` varchar(32) DEFAULT NULL COMMENT '传真号',
  `phone` varchar(32) DEFAULT NULL COMMENT '座机号',
  `mobile` varchar(32) DEFAULT NULL COMMENT '手机号',
  `dep_id` varchar(64) DEFAULT NULL COMMENT '所属部门ID，当前系统无部门时，此列无意义为空',
  `is_builtin` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否是内置账户，1是0不是，内置账户页面不可见而且有不进行权限过滤',
  `remark` varchar(256) DEFAULT NULL COMMENT '备注',
  `order_num` int(11) NOT NULL DEFAULT 1000 COMMENT '序号',
  `status` int(11) NOT NULL DEFAULT 1 COMMENT '状态：1有效0失效，理论上用户不应该删除，删除应置为失效',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `account_unique` (`account`) USING HASH
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息表';

-- ----------------------------
-- Records of sys_user_info
-- root pwd bitlife_root_888T3
-- admin pwd admin
-- ----------------------------
INSERT INTO `sys_user_info` VALUES ('0', 'root', 's5+xTlu4HTuXWkcJS8vXzq==', '内置账户', null, '', '', '', '', null, '1', '内置账户用户不可见', '0', '1', '2018-02-01 19:22:55');
INSERT INTO `sys_user_info` VALUES ('5226830917534711', 'admin', 'ismLkxFxF14d9uEosEa6MM==', '管理员', null, '', '', '', '', '1', '0', '内置管理员不可删除', '1', '1', '2018-02-01 19:22:55');
INSERT INTO `sys_user_info` VALUES ('5240629861712896', 'xiaolin', 'UqH3ouCWw1K+vKbxY7+ip7==', 'xiaolin', '1', '', null, '13028153703', '13028153703', '5235164010087424', '0', '', '0', '1', '2018-03-21 21:17:53');
INSERT INTO `sys_user_info` VALUES ('5240641183384576', 'xiaolin2_1521685476835', 'UqH3ouCWw1K+vKbxY7+ip7==', 'xiaolin2', '1', '', null, '', '', '1', '0', '', '0', '0', '2018-03-21 21:23:39');
INSERT INTO `sys_user_info` VALUES ('5240677402936320', 'xiaolin2_1521686997902', 'UqH3ouCWw1K+vKbxY7+ip7==', 'lixiaolin', '1', '', null, '13028153703', '13028153703', '1', '0', '', '0', '0', '2018-03-21 21:42:04');

-- ----------------------------
-- Table structure for `sys_dep_info` 可选
-- ----------------------------
DROP TABLE IF EXISTS `sys_dep_info`;
CREATE TABLE `sys_dep_info` (
  `id` varchar(64) NOT NULL COMMENT '主键ID',
  `name` varchar(128) NOT NULL COMMENT '部门名称',
  `parent_id` varchar(64) DEFAULT NULL COMMENT '上级部门ID',
  `type_id` varchar(64) DEFAULT NULL COMMENT '部门分类，当前系统无部门分类时，此列无意义为空',
  `remark` varchar(256) DEFAULT NULL COMMENT '备注',
  `order_num` int(11) NOT NULL DEFAULT 1000 COMMENT '序号',
  `status` int(11) NOT NULL DEFAULT 1 COMMENT '状态：1有效0失效',
  `path` varchar(1024) DEFAULT NULL COMMENT '层级路径',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门信息表';

-- ----------------------------
-- Records of sys_dep_info
-- ----------------------------
INSERT INTO `sys_dep_info` VALUES ('1', '集团公司', null, null, '总公司', '0', '1', '1', '2018-02-01 19:22:55');

-- ----------------------------
-- Table structure for `sys_deptype_info` 可选
-- ----------------------------
DROP TABLE IF EXISTS `sys_deptype_info`;
CREATE TABLE `sys_deptype_info` (
  `id` varchar(64) NOT NULL COMMENT '主键ID',
  `code` varchar(64) NOT NULL COMMENT '分类编码',
  `name` varchar(128) NOT NULL COMMENT '分类名称',
  `remark` varchar(256) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `code_unique` (`code`) USING HASH
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门分类信息表';

-- ----------------------------
-- Records of sys_deptype_info
-- ----------------------------
INSERT INTO `sys_deptype_info` VALUES ('1', 'SCHOOL', '学校', '学校');
INSERT INTO `sys_deptype_info` VALUES ('2', 'DEPT', '直属部门', '直属部门');
INSERT INTO `sys_deptype_info` VALUES ('3', 'CHILD', '子公司', '子公司');

-- ----------------------------
-- Table structure for `sys_menu_info`
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu_info`;
CREATE TABLE `sys_menu_info` (
  `id` varchar(64) NOT NULL COMMENT '主键ID',
  `name` varchar(128) NOT NULL COMMENT '菜单名称',
  `parent_id` varchar(32) DEFAULT NULL COMMENT '父节点ID',
  `type` int(11) NOT NULL COMMENT '菜单类型，必填目前有目录1、页面2、功能点3（按钮）',
  `url` varchar(128) DEFAULT NULL COMMENT '访问地址',
  `perms` varchar(256) DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(256) DEFAULT NULL COMMENT '图标',
  `order_num` int(11) NOT NULL DEFAULT 1000 COMMENT '序号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单信息表';

-- ----------------------------
-- Records of sys_menu_info
-- ----------------------------
INSERT INTO `sys_menu_info` VALUES ('5219191946478591', '系统管理', '', '1', '/system', '', 'android-settings', '5');
INSERT INTO `sys_menu_info` VALUES ('5219191946478592', '物资管理', null, '1', '/goods', '', 'briefcase', '0');
INSERT INTO `sys_menu_info` VALUES ('5219194605470720', '用户管理', '5219191946478591', '2', '/system/user', '', 'android-person', '1');
INSERT INTO `sys_menu_info` VALUES ('5219195567703040', '角色管理', '5219191946478591', '2', '/system/role', '', 'android-people', '2');
INSERT INTO `sys_menu_info` VALUES ('5219197081027584', '部门管理', '5219191946478591', '2', '/system/dept', '', 'cube', '0');
INSERT INTO `sys_menu_info` VALUES ('5219199034688512', '物资管理', '5219191946478592', '2', '/goods/manage', '', 'briefcase', '0');
INSERT INTO `sys_menu_info` VALUES ('5219200373818368', '分类管理', '5219191946478592', '2', '/goods/category', '', 'folder', '0');
INSERT INTO `sys_menu_info` VALUES ('5219201469776896', '品牌管理', '5219191946478592', '2', '/goods/brands', '', 'star', '0');
INSERT INTO `sys_menu_info` VALUES ('5227617678525440', '菜单管理', '5219191946478591', '2', '/system/menu', '', 'navicon-round', '3');
INSERT INTO `sys_menu_info` VALUES ('5227619801564160', '菜单树', '5227617678525440', '3', '', 'system:menu:tree', 'android-settings', '1');
INSERT INTO `sys_menu_info` VALUES ('5227623190397952', '详情', '5227617678525440', '3', '', 'system:menu:info', 'android-settings', '0');
INSERT INTO `sys_menu_info` VALUES ('5227624319583232', '新增', '5227617678525440', '3', '', 'system:menu:save', 'android-settings', '0');
INSERT INTO `sys_menu_info` VALUES ('5227625014887424', '修改', '5227617678525440', '3', '', 'system:menu:update', 'android-settings', '0');
INSERT INTO `sys_menu_info` VALUES ('5227625606972416', '删除', '5227617678525440', '3', '', 'system:menu:delete', 'android-settings', '0');
INSERT INTO `sys_menu_info` VALUES ('5227627115774976', '状态树', '5219195567703040', '3', '', 'system:menu:tree2status', 'android-settings', '0');
INSERT INTO `sys_menu_info` VALUES ('5227647843730432', '用户菜单', null, '3', '', 'system:menu:tree2user', 'android-settings', '6');
INSERT INTO `sys_menu_info` VALUES ('5230700403786752', '详情', '5219194605470720', '3', '', 'system:user:info', 'android-settings', '0');
INSERT INTO `sys_menu_info` VALUES ('5230700889375744', '新增', '5219194605470720', '3', '', 'system:user:save', 'android-settings', '0');
INSERT INTO `sys_menu_info` VALUES ('5230701290685440', '修改', '5219194605470720', '3', '', 'system:user:update', 'android-settings', '0');
INSERT INTO `sys_menu_info` VALUES ('5230701685507072', '删除', '5219194605470720', '3', '', 'system:user:delete', 'android-settings', '0');
INSERT INTO `sys_menu_info` VALUES ('5230702176142336', '批量删除', '5219194605470720', '3', '', 'system:user:deletes', 'android-settings', '0');
INSERT INTO `sys_menu_info` VALUES ('5230703144698880', '列表', '5219194605470720', '3', '', 'system:user:list', 'android-settings', '0');
INSERT INTO `sys_menu_info` VALUES ('5230704077505536', '修改密码', '5219194605470720', '3', '', 'system:user:changepwd', 'android-settings', '0');
INSERT INTO `sys_menu_info` VALUES ('5230704464299008', '重置密码', '5219194605470720', '3', '', 'system:user:pwd', 'android-settings', '0');
INSERT INTO `sys_menu_info` VALUES ('5230705194337280', '详情', '5219195567703040', '3', '', 'system:role:info', 'android-settings', '0');
INSERT INTO `sys_menu_info` VALUES ('5230705603642368', '新增', '5219195567703040', '3', '', 'system:role:save', 'android-settings', '0');
INSERT INTO `sys_menu_info` VALUES ('5230705872208896', '修改', '5219195567703040', '3', '', 'system:role:update', 'android-settings', '0');
INSERT INTO `sys_menu_info` VALUES ('5230706540381184', '删除', '5219195567703040', '3', '', 'system:role:delete', 'android-settings', '0');
INSERT INTO `sys_menu_info` VALUES ('5230707520865280', '批量删除', '5219195567703040', '3', '', 'system:role:deletes', 'android-settings', '0');
INSERT INTO `sys_menu_info` VALUES ('5230707978863616', '列表', '5219195567703040', '3', '', 'system:role:list', 'android-settings', '0');
INSERT INTO `sys_menu_info` VALUES ('5230708402750464', '功能权限', '5219195567703040', '3', '', 'system:role:role2menu', 'android-settings', '0');
INSERT INTO `sys_menu_info` VALUES ('5230709271462912', '详情', '5219197081027584', '3', '', 'system:dept:info', 'android-settings', '0');
INSERT INTO `sys_menu_info` VALUES ('5230711477109760', '新增', '5219197081027584', '3', '', 'system:dept:save', 'android-settings', '0');
INSERT INTO `sys_menu_info` VALUES ('5230712077583360', '修改', '5219197081027584', '3', '', 'system:dept:update', 'android-settings', '0');
INSERT INTO `sys_menu_info` VALUES ('5230713089983488', '删除', '5219197081027584', '3', '', 'system:dept:delete', 'android-settings', '0');
INSERT INTO `sys_menu_info` VALUES ('5230713872548864', '列表', '5219197081027584', '3', '', 'system:dept:list', 'android-settings', '0');
INSERT INTO `sys_menu_info` VALUES ('5230714925286400', '用户部门树', '5219197081027584', '3', '', 'system:dept:tree2user', 'android-settings', '0');
INSERT INTO `sys_menu_info` VALUES ('5230715329446912', '部门树', '5219197081027584', '3', '', 'system:dept:tree', 'android-settings', '0');
INSERT INTO `sys_menu_info` VALUES ('5230910162568192', '详情', '5219199034688512', '3', '', 'goods:goodsinfo:info', 'android-settings', '0');
INSERT INTO `sys_menu_info` VALUES ('5230911395431424', '列表', '5219199034688512', '3', '', 'goods:goodsinfo:list', 'android-settings', '0');
INSERT INTO `sys_menu_info` VALUES ('5230912259195904', '新增', '5219199034688512', '3', '', 'goods:goodsinfo:save', 'android-settings', '0');
INSERT INTO `sys_menu_info` VALUES ('5230913193575424', '更新', '5219199034688512', '3', '', 'goods:goodsinfo:update', 'android-settings', '0');
INSERT INTO `sys_menu_info` VALUES ('5230914520548352', '批量删除', '5219199034688512', '3', '', 'goods:goodsinfo:deletes', 'android-settings', '0');
INSERT INTO `sys_menu_info` VALUES ('5230915341747200', '删除', '5219199034688512', '3', '', 'goods:goodsinfo:delete', 'android-settings', '0');
INSERT INTO `sys_menu_info` VALUES ('5230917841486848', '新增', '5219200373818368', '3', '', 'goods:goodstype:save', 'android-settings', '0');
INSERT INTO `sys_menu_info` VALUES ('5230918327403520', '更新', '5219200373818368', '3', '', 'goods:goodstype:update', 'android-settings', '0');
INSERT INTO `sys_menu_info` VALUES ('5230919166821376', '删除', '5219200373818368', '3', '', 'goods:goodstype:delete', 'android-settings', '0');
INSERT INTO `sys_menu_info` VALUES ('5230919554860032', '批量删除', '5219200373818368', '3', '', 'goods:goodstype:deletes', 'android-settings', '0');
INSERT INTO `sys_menu_info` VALUES ('5230920055424000', '详情', '5219200373818368', '3', '', 'goods:goodstype:info', 'android-settings', '0');
INSERT INTO `sys_menu_info` VALUES ('5230920517911552', '类型树', '5219200373818368', '3', '', 'goods:goodstype:tree', 'android-settings', '0');
INSERT INTO `sys_menu_info` VALUES ('5230921096889344', '新增', '5219201469776896', '3', '', 'goods:goodsbrand:save', 'android-settings', '0');
INSERT INTO `sys_menu_info` VALUES ('5230921473262592', '更新', '5219201469776896', '3', '', 'goods:goodsbrand:update', 'android-settings', '0');
INSERT INTO `sys_menu_info` VALUES ('5230921924183040', '删除', '5219201469776896', '3', '', 'goods:goodsbrand:delete', 'android-settings', '0');
INSERT INTO `sys_menu_info` VALUES ('5230922338796544', '批量删除', '5219201469776896', '3', '', 'goods:goodsbrand:deletes', 'android-settings', '0');
INSERT INTO `sys_menu_info` VALUES ('5230922999792640', '详情', '5219201469776896', '3', '', 'goods:goodsbrand:info', 'android-settings', '0');
INSERT INTO `sys_menu_info` VALUES ('5230923472569344', '品牌树', '5219201469776896', '3', '', 'goods:goodsbrand:tree', 'android-settings', '0');
INSERT INTO `sys_menu_info` VALUES ('5232968841233408', '常量获取', '5219199034688512', '3', '', 'goods:constant:list', 'android-settings', '0');
INSERT INTO `sys_menu_info` VALUES ('5235178711188480', '部门类型列表', '5219197081027584', '3', '', 'system:depttype:list', 'android-settings', '0');
INSERT INTO `sys_menu_info` VALUES ('5238247571817472', '日志管理', '5219191946478591', '2', '/system/optlogs', '', 'clipboard', '4');
INSERT INTO `sys_menu_info` VALUES ('5239189594047488', '日志列表', '5238247571817472', '3', '', 'system:oplog:list', 'android-settings', '0');

-- ----------------------------
-- Table structure for `sys_role_info`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_info`;
CREATE TABLE `sys_role_info` (
  `id` varchar(64) NOT NULL COMMENT '主键ID',
  `name` varchar(128) NOT NULL COMMENT '角色名称',
  `remark` varchar(256) DEFAULT NULL COMMENT '备注',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色信息表，删除角色需要删除所有关联表';

-- ----------------------------
-- Records of sys_role_info
-- ----------------------------
INSERT INTO `sys_role_info` VALUES ('5225158686966781', 'admin', '系统管理', '2018-02-01 19:22:55');
INSERT INTO `sys_role_info` VALUES ('5240825946506240', 'root', 'root', '2018-03-21 22:57:37');

-- ----------------------------
-- Table structure for `sys_role_to_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_to_menu`;
CREATE TABLE `sys_role_to_menu` (
  `id` varchar(64) NOT NULL COMMENT '主键ID',
  `role_id` varchar(64) NOT NULL COMMENT '角色ID',
  `menu_id` varchar(64) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色菜单关联表';

-- ----------------------------
-- Records of sys_role_to_menu
-- ----------------------------
INSERT INTO `sys_role_to_menu` VALUES ('01dd0c192d1111e8ad3500163e024576', '5225158686966781', '5219191946478592');
INSERT INTO `sys_role_to_menu` VALUES ('01dd0de82d1111e8ad3500163e024576', '5225158686966781', '5219200373818368');
INSERT INTO `sys_role_to_menu` VALUES ('01dd0e4e2d1111e8ad3500163e024576', '5225158686966781', '5230920517911552');
INSERT INTO `sys_role_to_menu` VALUES ('01dd0e8e2d1111e8ad3500163e024576', '5225158686966781', '5230918327403520');
INSERT INTO `sys_role_to_menu` VALUES ('01dd0ec92d1111e8ad3500163e024576', '5225158686966781', '5230919166821376');
INSERT INTO `sys_role_to_menu` VALUES ('01dd0f112d1111e8ad3500163e024576', '5225158686966781', '5230919554860032');
INSERT INTO `sys_role_to_menu` VALUES ('01dd0f482d1111e8ad3500163e024576', '5225158686966781', '5230920055424000');
INSERT INTO `sys_role_to_menu` VALUES ('01dd0f7d2d1111e8ad3500163e024576', '5225158686966781', '5230917841486848');
INSERT INTO `sys_role_to_menu` VALUES ('01dd0fb12d1111e8ad3500163e024576', '5225158686966781', '5219201469776896');
INSERT INTO `sys_role_to_menu` VALUES ('01dd0fea2d1111e8ad3500163e024576', '5225158686966781', '5230922338796544');
INSERT INTO `sys_role_to_menu` VALUES ('01dd101d2d1111e8ad3500163e024576', '5225158686966781', '5230922999792640');
INSERT INTO `sys_role_to_menu` VALUES ('01dd104f2d1111e8ad3500163e024576', '5225158686966781', '5230921096889344');
INSERT INTO `sys_role_to_menu` VALUES ('01dd107f2d1111e8ad3500163e024576', '5225158686966781', '5230923472569344');
INSERT INTO `sys_role_to_menu` VALUES ('01dd10b12d1111e8ad3500163e024576', '5225158686966781', '5230921473262592');
INSERT INTO `sys_role_to_menu` VALUES ('01dd10ea2d1111e8ad3500163e024576', '5225158686966781', '5230921924183040');
INSERT INTO `sys_role_to_menu` VALUES ('01dd111c2d1111e8ad3500163e024576', '5225158686966781', '5219199034688512');
INSERT INTO `sys_role_to_menu` VALUES ('01dd114c2d1111e8ad3500163e024576', '5225158686966781', '5230915341747200');
INSERT INTO `sys_role_to_menu` VALUES ('01dd117e2d1111e8ad3500163e024576', '5225158686966781', '5230910162568192');
INSERT INTO `sys_role_to_menu` VALUES ('01dd11b32d1111e8ad3500163e024576', '5225158686966781', '5230913193575424');
INSERT INTO `sys_role_to_menu` VALUES ('01dd11e62d1111e8ad3500163e024576', '5225158686966781', '5230912259195904');
INSERT INTO `sys_role_to_menu` VALUES ('01dd12182d1111e8ad3500163e024576', '5225158686966781', '5230914520548352');
INSERT INTO `sys_role_to_menu` VALUES ('01dd124c2d1111e8ad3500163e024576', '5225158686966781', '5232968841233408');
INSERT INTO `sys_role_to_menu` VALUES ('01dd127e2d1111e8ad3500163e024576', '5225158686966781', '5230911395431424');
INSERT INTO `sys_role_to_menu` VALUES ('01dd12b02d1111e8ad3500163e024576', '5225158686966781', '5238247571817472');
INSERT INTO `sys_role_to_menu` VALUES ('01dd12e12d1111e8ad3500163e024576', '5225158686966781', '5239189594047488');
INSERT INTO `sys_role_to_menu` VALUES ('01dd13142d1111e8ad3500163e024576', '5225158686966781', '5219195567703040');
INSERT INTO `sys_role_to_menu` VALUES ('01dd13492d1111e8ad3500163e024576', '5225158686966781', '5230707520865280');
INSERT INTO `sys_role_to_menu` VALUES ('01dd137b2d1111e8ad3500163e024576', '5225158686966781', '5230705603642368');
INSERT INTO `sys_role_to_menu` VALUES ('01dd13ad2d1111e8ad3500163e024576', '5225158686966781', '5230707978863616');
INSERT INTO `sys_role_to_menu` VALUES ('01dd13e22d1111e8ad3500163e024576', '5225158686966781', '5227627115774976');
INSERT INTO `sys_role_to_menu` VALUES ('01dd14132d1111e8ad3500163e024576', '5225158686966781', '5230705872208896');
INSERT INTO `sys_role_to_menu` VALUES ('01dd14442d1111e8ad3500163e024576', '5225158686966781', '5230708402750464');
INSERT INTO `sys_role_to_menu` VALUES ('01dd147b2d1111e8ad3500163e024576', '5225158686966781', '5230706540381184');
INSERT INTO `sys_role_to_menu` VALUES ('01dd14ae2d1111e8ad3500163e024576', '5225158686966781', '5230705194337280');
INSERT INTO `sys_role_to_menu` VALUES ('01dd14df2d1111e8ad3500163e024576', '5225158686966781', '5219197081027584');
INSERT INTO `sys_role_to_menu` VALUES ('01dd150e2d1111e8ad3500163e024576', '5225158686966781', '5235178711188480');
INSERT INTO `sys_role_to_menu` VALUES ('01dd15412d1111e8ad3500163e024576', '5225158686966781', '5230713089983488');
INSERT INTO `sys_role_to_menu` VALUES ('01dd15732d1111e8ad3500163e024576', '5225158686966781', '5230713872548864');
INSERT INTO `sys_role_to_menu` VALUES ('01dd15a32d1111e8ad3500163e024576', '5225158686966781', '5230714925286400');
INSERT INTO `sys_role_to_menu` VALUES ('01dd15d32d1111e8ad3500163e024576', '5225158686966781', '5230712077583360');
INSERT INTO `sys_role_to_menu` VALUES ('01dd16062d1111e8ad3500163e024576', '5225158686966781', '5230709271462912');
INSERT INTO `sys_role_to_menu` VALUES ('01dd163b2d1111e8ad3500163e024576', '5225158686966781', '5230711477109760');
INSERT INTO `sys_role_to_menu` VALUES ('01dd166d2d1111e8ad3500163e024576', '5225158686966781', '5230715329446912');
INSERT INTO `sys_role_to_menu` VALUES ('01dd16a42d1111e8ad3500163e024576', '5225158686966781', '5219194605470720');
INSERT INTO `sys_role_to_menu` VALUES ('01dd16dc2d1111e8ad3500163e024576', '5225158686966781', '5230704077505536');
INSERT INTO `sys_role_to_menu` VALUES ('01dd170d2d1111e8ad3500163e024576', '5225158686966781', '5230701290685440');
INSERT INTO `sys_role_to_menu` VALUES ('01dd173e2d1111e8ad3500163e024576', '5225158686966781', '5230704464299008');
INSERT INTO `sys_role_to_menu` VALUES ('01dd176f2d1111e8ad3500163e024576', '5225158686966781', '5230703144698880');
INSERT INTO `sys_role_to_menu` VALUES ('01dd17a12d1111e8ad3500163e024576', '5225158686966781', '5230702176142336');
INSERT INTO `sys_role_to_menu` VALUES ('01dd17d42d1111e8ad3500163e024576', '5225158686966781', '5230700403786752');
INSERT INTO `sys_role_to_menu` VALUES ('01dd18562d1111e8ad3500163e024576', '5225158686966781', '5230700889375744');
INSERT INTO `sys_role_to_menu` VALUES ('01dd188f2d1111e8ad3500163e024576', '5225158686966781', '5230701685507072');
INSERT INTO `sys_role_to_menu` VALUES ('01dd18c42d1111e8ad3500163e024576', '5225158686966781', '5227647843730432');
INSERT INTO `sys_role_to_menu` VALUES ('01dd18fa2d1111e8ad3500163e024576', '5225158686966781', '5219191946478591');
INSERT INTO `sys_role_to_menu` VALUES ('28aa298b2d8511e8ad3500163e024576', '5240825946506240', '5219191946478592');
INSERT INTO `sys_role_to_menu` VALUES ('28aa2a822d8511e8ad3500163e024576', '5240825946506240', '5219199034688512');
INSERT INTO `sys_role_to_menu` VALUES ('28aa2ad52d8511e8ad3500163e024576', '5240825946506240', '5230910162568192');
INSERT INTO `sys_role_to_menu` VALUES ('28aa2b0c2d8511e8ad3500163e024576', '5240825946506240', '5230911395431424');
INSERT INTO `sys_role_to_menu` VALUES ('28aa2b452d8511e8ad3500163e024576', '5240825946506240', '5230912259195904');
INSERT INTO `sys_role_to_menu` VALUES ('28aa2b7a2d8511e8ad3500163e024576', '5240825946506240', '5230913193575424');
INSERT INTO `sys_role_to_menu` VALUES ('28aa2bb92d8511e8ad3500163e024576', '5240825946506240', '5230914520548352');
INSERT INTO `sys_role_to_menu` VALUES ('28aa2be62d8511e8ad3500163e024576', '5240825946506240', '5230915341747200');
INSERT INTO `sys_role_to_menu` VALUES ('28aa2ca42d8511e8ad3500163e024576', '5240825946506240', '5232968841233408');
INSERT INTO `sys_role_to_menu` VALUES ('28aa2ce92d8511e8ad3500163e024576', '5240825946506240', '5219200373818368');
INSERT INTO `sys_role_to_menu` VALUES ('28aa2d1e2d8511e8ad3500163e024576', '5240825946506240', '5230917841486848');
INSERT INTO `sys_role_to_menu` VALUES ('28aa2d4f2d8511e8ad3500163e024576', '5240825946506240', '5230918327403520');
INSERT INTO `sys_role_to_menu` VALUES ('28aa2d802d8511e8ad3500163e024576', '5240825946506240', '5230919166821376');
INSERT INTO `sys_role_to_menu` VALUES ('28aa2dae2d8511e8ad3500163e024576', '5240825946506240', '5230919554860032');
INSERT INTO `sys_role_to_menu` VALUES ('28aa2ddf2d8511e8ad3500163e024576', '5240825946506240', '5230920055424000');
INSERT INTO `sys_role_to_menu` VALUES ('28aa2e112d8511e8ad3500163e024576', '5240825946506240', '5230920517911552');
INSERT INTO `sys_role_to_menu` VALUES ('28aa2e3b2d8511e8ad3500163e024576', '5240825946506240', '5219201469776896');
INSERT INTO `sys_role_to_menu` VALUES ('28aa2e6c2d8511e8ad3500163e024576', '5240825946506240', '5230921096889344');
INSERT INTO `sys_role_to_menu` VALUES ('28aa2e982d8511e8ad3500163e024576', '5240825946506240', '5230921473262592');
INSERT INTO `sys_role_to_menu` VALUES ('28aa2ec52d8511e8ad3500163e024576', '5240825946506240', '5230921924183040');
INSERT INTO `sys_role_to_menu` VALUES ('28aa2eef2d8511e8ad3500163e024576', '5240825946506240', '5230922338796544');
INSERT INTO `sys_role_to_menu` VALUES ('28aa2f1c2d8511e8ad3500163e024576', '5240825946506240', '5230922999792640');
INSERT INTO `sys_role_to_menu` VALUES ('28aa2f462d8511e8ad3500163e024576', '5240825946506240', '5230923472569344');

-- ----------------------------
-- Table structure for `sys_role_to_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_to_user`;
CREATE TABLE `sys_role_to_user` (
  `id` varchar(64) NOT NULL COMMENT '主键ID',
  `role_id` varchar(64) NOT NULL COMMENT '角色ID',
  `user_id` varchar(64) NOT NULL COMMENT '用户ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色用户关联表';

-- ----------------------------
-- Records of sys_role_to_user
-- ----------------------------
INSERT INTO `sys_role_to_user` VALUES ('1', '5225158686966781', '5226830917534711');
INSERT INTO `sys_role_to_user` VALUES ('5c23664c2d7b11e8ad3500163e024576', '5225158686966781', '5240629861712896');


-- ----------------------------
-- Table structure for `sys_op_logs`
-- ----------------------------
DROP TABLE IF EXISTS `sys_op_logs`;
CREATE TABLE `sys_op_logs` (
  `id` varchar(64) NOT NULL COMMENT '主键ID',
  `account` varchar(128) NOT NULL COMMENT '账号',
  `name` varchar(128) NOT NULL COMMENT '姓名',
  `ip` varchar(32) NOT NULL COMMENT '请求者IP',
  `uri` varchar(128) DEFAULT NULL COMMENT 'uri',
  `excute_time` int(11) NOT NULL DEFAULT 1000 COMMENT '执行时间',
  `resp` varchar(512) DEFAULT NULL COMMENT '响应',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='操作日志表';

-- ----------------------------
-- Function structure for `dept_check_parent`
-- 查询当前部门及其子部门所属的用户语句为：select * from  sys_user_info where dept_check_parent('当前部门ID', dep_id) or dep_id='当前部门ID';
-- ----------------------------
DROP FUNCTION IF EXISTS `dept_check_parent`;
DELIMITER ;;
CREATE FUNCTION `dept_check_parent`(`parent` varchar(64), `self` varchar(64)) RETURNS tinyint(1)
BEGIN
DECLARE temp varchar(64) default '';
DECLARE temp_self varchar(64) default '';
DECLARE c int default 0;

IF
  (self IS NULL OR parent IS NULL OR self = paren) THEN return 0;
  END IF;
  
SET temp_self = self;

SELECT count(*) INTO c FROM sys_dep_info WHERE id = temp_self;
IF
  c = 0 THEN return 0;
  END IF;
  
WHILE temp IS NOT NULL DO 

SELECT parent_id INTO temp FROM sys_dep_info WHERE id = temp_self;

IF
  (temp IS NULL) THEN return 0;
  END IF;
  
IF
  (temp IS NOT NULL AND temp = parent) THEN return 1;
  END IF;

SET temp_self = temp;

END WHILE;

RETURN 0;
END
;;
DELIMITER ;

-- ----------------------------
-- Function structure for `delete_dept_childrens`
-- ----------------------------
DROP PROCEDURE  IF EXISTS `delete_dept_childrens`;
DELIMITER ;;
CREATE PROCEDURE  `delete_dept_childrens`(`self` varchar(64))
BEGIN
DECLARE temp varchar(64) default '';
DECLARE STOP INT DEFAULT 0; 
  
DECLARE _Cur CURSOR FOR SELECT id FROM sys_dep_info WHERE parent_id = self;
DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' SET STOP = 1;
SET @@max_sp_recursion_depth = 100; 

OPEN _Cur; 
	FETCH _Cur INTO temp;  
	WHILE STOP <> 1 DO
		IF
		  (temp IS NOT NULL) THEN 
				call delete_dept_childrens(temp);
				DELETE FROM sys_dep_info WHERE id = temp;
		  END IF;
		FETCH _Cur INTO temp;
	END WHILE;
CLOSE _Cur;

DELETE FROM sys_dep_info WHERE id = self;

END
;;
DELIMITER ;

-- ----------------------------
-- Function structure for `delete_menu_childrens`
-- ----------------------------
DROP PROCEDURE  IF EXISTS `delete_menu_childrens`;
DELIMITER ;;
CREATE PROCEDURE  `delete_menu_childrens`(`self` varchar(64))
BEGIN
DECLARE temp varchar(64) default '';
DECLARE STOP INT DEFAULT 0; 
  
DECLARE _Cur CURSOR FOR SELECT id FROM sys_menu_info WHERE parent_id = self;
DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' SET STOP = 1;
SET @@max_sp_recursion_depth = 100; 

OPEN _Cur; 
	FETCH _Cur INTO temp;  
	WHILE STOP <> 1 DO
		IF
		  (temp IS NOT NULL) THEN 
				call delete_menu_childrens(temp);
				DELETE FROM sys_menu_info WHERE id = temp;
                DELETE FROM sys_role_to_menu WHERE menu_id = temp;
		  END IF;
		FETCH _Cur INTO temp;
	END WHILE;
CLOSE _Cur;

DELETE FROM sys_role_to_menu WHERE menu_id = self;
DELETE FROM sys_menu_info WHERE id = self;

END
;;
DELIMITER ;

