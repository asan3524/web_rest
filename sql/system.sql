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
INSERT INTO `sys_user_info` VALUES ('0', 'root', 's5+xTlu4HTuXWkcJS8vXzq==', '内置账户', null, '', '', '', '', null, 1, '内置账户用户不可见', 0, 1, '2018-02-01 19:22:55');
INSERT INTO `sys_user_info` VALUES ('1', 'admin', 'ismLkxFxF14d9uEosEa6MM==', '管理员', null, '', '', '', '', null, 0, '内置管理员不可删除', 1, 1, '2018-02-01 19:22:55');

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
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门信息表';

-- ----------------------------
-- Records of sys_dep_info
-- ----------------------------
INSERT INTO `sys_dep_info` VALUES ('1', '集团公司', null, null, '总公司', 0, 1, '2018-02-01 19:22:55');
INSERT INTO `sys_dep_info` VALUES ('2', '集团公司2', '1', null, '总公司2', '0', '1', '2018-02-01 19:22:55');
INSERT INTO `sys_dep_info` VALUES ('3', '集团公司3', '2', null, '总公司3', '0', '1', '2018-02-01 19:22:55');

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
INSERT INTO `sys_menu_info` VALUES ('1', '系统管理', null, '1', '/system', null, '', 1);
INSERT INTO `sys_menu_info` VALUES ('2', '菜单管理', '1', '2', '/menu', null, '', 2);
INSERT INTO `sys_menu_info` VALUES ('3', '菜单树', '2', '3', null, 'system:menu:tree', '', 3);
INSERT INTO `sys_menu_info` VALUES ('4', '菜单状态树', '2', '3', null, 'system:menu:tree2status', '', 4);
INSERT INTO `sys_menu_info` VALUES ('5', '用户菜单树', '2', '3', null, 'system:menu:tree2user', '', 5);
INSERT INTO `sys_menu_info` VALUES ('6', '详情', '2', '3', null, 'system:menu:info', '', 6);
INSERT INTO `sys_menu_info` VALUES ('7', '增加', '2', '3', null, 'system:menu:save', '', 7);
INSERT INTO `sys_menu_info` VALUES ('8', '修改', '2', '3', null, 'system:menu:update', '', 8);
INSERT INTO `sys_menu_info` VALUES ('9', '删除', '2', '3', null, 'system:menu:delete', '', 9);

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
INSERT INTO `sys_role_info` VALUES ('1', 'admin', '系统管理', '2018-02-01 19:22:55');

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
INSERT INTO `sys_role_to_menu` VALUES ('1', '1', '1');
INSERT INTO `sys_role_to_menu` VALUES ('2', '1', '2');
INSERT INTO `sys_role_to_menu` VALUES ('3', '1', '3');

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
INSERT INTO `sys_role_to_user` VALUES ('1', '1', '1');

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

