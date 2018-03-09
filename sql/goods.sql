-- ----------------------------
-- Table structure for `goods_info`
-- ----------------------------
DROP TABLE IF EXISTS `goods_info`;
CREATE TABLE `goods_info` (
  `id` varchar(64) NOT NULL COMMENT '主键ID',
  `name` varchar(256) NOT NULL COMMENT '物资名称',
  `code` varchar(64) NOT NULL COMMENT '编码',
  `type_id` varchar(64) NOT NULL COMMENT '分类',
  `brand_id` varchar(64) DEFAULT NULL COMMENT '品牌',
  `color` varchar(32) NOT NULL COMMENT '颜色',
  `unit` varchar(32) NOT NULL COMMENT '单位',
  `norm` varchar(512) DEFAULT NULL COMMENT '规格说明',
  `remark` varchar(256) DEFAULT NULL COMMENT '备注',
  `status` int(11) NOT NULL DEFAULT 1 COMMENT '状态：1有效0失效，理论上物资不应该删除，删除应置为失效',
  `order_num` int(11) NOT NULL DEFAULT 1000 COMMENT '序号',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `code_unique` (`code`) USING HASH
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='物资信息表';

-- ----------------------------
-- Records of goods_info
-- ----------------------------
INSERT INTO `goods_info` VALUES ('1', '格力空调3P', '001', '3', '1', '白色', '台', '', '', 1, 1, '2018-02-01 19:22:55');
INSERT INTO `goods_info` VALUES ('2', '格力空调1P', '002', '3', '1', '白色', '台', '', '', 1, 1, '2018-02-01 19:22:55');
INSERT INTO `goods_info` VALUES ('3', '格力空调大1P', '003', '3', '1', '白色', '台', '', '', 1, 1, '2018-02-01 19:22:55');

-- ----------------------------
-- Table structure for `goods_type_info`
-- ----------------------------
DROP TABLE IF EXISTS `goods_type_info`;
CREATE TABLE `goods_type_info` (
  `id` varchar(64) NOT NULL COMMENT '主键ID',
  `code` varchar(64) NOT NULL COMMENT '编码',
  `name` varchar(128) NOT NULL COMMENT '分类名称',
  `parent_id` varchar(64) DEFAULT NULL COMMENT '上级分类ID',
  `remark` varchar(256) DEFAULT NULL COMMENT '备注',
  `status` int(11) NOT NULL DEFAULT 1 COMMENT '状态：1有效0失效',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `code_unique` (`code`) USING HASH
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='物资分类信息表';

-- ----------------------------
-- Records of goods_type_info
-- ----------------------------
INSERT INTO `goods_type_info` VALUES ('1', 'ALL', '所有物资', null, '', 1, '2018-02-01 19:22:55');
INSERT INTO `goods_type_info` VALUES ('2', 'DQ', '电器', '1', '', 1, '2018-02-01 19:22:55');
INSERT INTO `goods_type_info` VALUES ('3', 'KT', '空调', '2', '', 1, '2018-02-01 19:22:55');

-- ----------------------------
-- Table structure for `goods_brand_info`
-- ----------------------------
DROP TABLE IF EXISTS `goods_brand_info`;
CREATE TABLE `goods_brand_info` (
  `id` varchar(64) NOT NULL COMMENT '主键ID',
  `code` varchar(64) NOT NULL COMMENT '编码',
  `name` varchar(128) NOT NULL COMMENT '品牌名称',
  `parent_id` varchar(64) DEFAULT NULL COMMENT '父品牌ID',
  `remark` varchar(256) DEFAULT NULL COMMENT '备注',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `code_unique` (`code`) USING HASH
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='物资分类信息表';

-- ----------------------------
-- Records of goods_brand_info
-- ----------------------------
INSERT INTO `goods_brand_info` VALUES ('1', 'GL', '格力', null, '', '2018-02-01 19:22:55');