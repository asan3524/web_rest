-- ----------------------------
-- Table structure for `sto_store_info`
-- ----------------------------
DROP TABLE IF EXISTS `sto_store_info`;
CREATE TABLE `sto_store_info` (
  `id` varchar(64) NOT NULL COMMENT '主键ID',
  `code` varchar(64) NOT NULL COMMENT '编码',
  `name` varchar(128) NOT NULL COMMENT '名称',
  `parent_id` varchar(64) DEFAULT NULL COMMENT '上级仓库ID',
  `contact` varchar(128) NOT NULL COMMENT '联系人',
  `address` varchar(256) NOT NULL COMMENT '仓库地址',
  `postal_code` varchar(32) NOT NULL COMMENT '邮政编码',
  `fax` varchar(32) DEFAULT NULL COMMENT '传真号',
  `phone` varchar(32) DEFAULT NULL COMMENT '座机号',
  `mobile` varchar(32) DEFAULT NULL COMMENT '手机号',
  `dep_id` varchar(64) DEFAULT NULL COMMENT '所属部门ID，当前系统无部门时或仓库无需关联到部门时，此列无意义为空',
  `remark` varchar(256) DEFAULT NULL COMMENT '备注',
  `order_num` int(11) NOT NULL DEFAULT 1000 COMMENT '序号',
  `make_status` int(11) NOT NULL DEFAULT 1000 COMMENT '盘点状态，1默认，0盘点中',
  `status` int(11) NOT NULL DEFAULT 1 COMMENT '状态：1有效0失效，删除应置为失效',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `code_unique` (`code`) USING HASH
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='仓库信息表，仓库下有物资不能删除此仓库';

-- ----------------------------
-- Records of sto_store_info
-- ----------------------------
INSERT INTO `sto_store_info` VALUES ('1', 'ZCK', '主仓库', null, '王某某', '高新区', '111111', '', '', '', null, '', 1, 1, 1, '2018-02-01 19:22:55');
INSERT INTO `sto_store_info` VALUES ('2', 'JJCK', '锦江仓库', '1', '李某某', '锦江区', '111111', '', '', '', null, '', 1, 1, 1, '2018-02-01 19:22:55');

-- ----------------------------
-- Table structure for `sto_store_to_role`
-- ----------------------------
DROP TABLE IF EXISTS `sto_store_to_role`;
CREATE TABLE `sto_store_to_role` (
  `id` varchar(64) NOT NULL COMMENT '主键ID',
  `role_id` varchar(64) NOT NULL COMMENT '角色ID',
  `store_id` varchar(64) NOT NULL COMMENT '仓库ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色仓库关联表，为仓库划分岗位要求，只有有权限的岗位人员才能查看、修改指定仓库的所有信息';

-- ----------------------------
-- Records of sto_store_to_role
-- ----------------------------
INSERT INTO `sto_store_to_role` VALUES ('1', '1', '1');

-- ----------------------------
-- Table structure for `sto_stock_info`
-- ----------------------------
DROP TABLE IF EXISTS `sto_stock_info`;
CREATE TABLE `sto_stock_info` (
  `id` varchar(64) NOT NULL COMMENT '主键ID',
  `store_id` varchar(64) NOT NULL COMMENT '所属仓库ID',
  `goods_id` varchar(64) DEFAULT NULL COMMENT '物资ID',
  `name` varchar(256) NOT NULL COMMENT '物资名称',
  `code` varchar(64) NOT NULL COMMENT '编码',
  `type` varchar(128) DEFAULT NULL COMMENT '类型',
  `brand` varchar(128) DEFAULT NULL COMMENT '品牌名称',
  `color` varchar(32) NOT NULL COMMENT '颜色',
  `unit` varchar(32) NOT NULL COMMENT '单位',
  `norm` varchar(512) DEFAULT NULL COMMENT '规格说明',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `quantity` int(11) NOT NULL DEFAULT 0 COMMENT '库存数量',
  `status` int(11) NOT NULL DEFAULT 1 COMMENT '状态：1有效0失效,此物资完全弃用报损之后，可以将库存记录置为失效，默认情况下不可见,可以考虑加入定时清理计划中',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='库存信息表，将物资信息copy过来，以防物资被修改或删除，库存记录发生变化，此记录只允许修改库存数量这个字段，另一个方案是物资不能修改，删除只是置为失效，那么此处物资名称之类就不需要copy过来';

-- ----------------------------
-- Records of sto_stock_info
-- ----------------------------
-- INSERT INTO `sto_stock_info` VALUES ('1', '1', '1', '格力空调', '001', '空调', '格力', '白色', '台', '', 15, 1, '2018-02-01 19:22:55');
INSERT INTO `sto_stock_info` VALUES ('1', '1', '1', '格力空调3P', '001', '空调', '格力', '白色', '台', '', '', 20, 1, '2018-02-01 19:22:55');
INSERT INTO `sto_stock_info` VALUES ('2', '1', '2', '格力空调1P', '002', '空调', '格力', '白色', '台', '', '', 20, 1, '2018-02-01 19:22:55');
INSERT INTO `sto_stock_info` VALUES ('3', '1', '3', '格力空调大1P', '003', '空调', '格力', '白色', '台', '', '', 20, 1, '2018-02-01 19:22:55');
INSERT INTO `sto_stock_info` VALUES ('4', '2', '2', '格力空调1P', '002', '空调', '格力', '白色', '台', '', '', 10, 1, '2018-02-01 19:22:55');
INSERT INTO `sto_stock_info` VALUES ('5', '2', '3', '格力空调大1P', '003', '空调', '格力', '白色', '台', '', '', 10, 1, '2018-02-01 19:22:55');

-- ----------------------------
-- Table structure for `sto_threshold_info`
-- ----------------------------
DROP TABLE IF EXISTS `sto_threshold_info`;
CREATE TABLE `sto_threshold_info` (
  `id` varchar(64) NOT NULL COMMENT '主键ID',
  `store_id` varchar(64) NOT NULL COMMENT '所属仓库ID',
  `stock_id` varchar(64) DEFAULT NULL COMMENT '库存物资ID，可以为空，未设置阈值的物资遵循所属仓库的阈值',
  `upper` int(11) NOT NULL DEFAULT 100 COMMENT '库存阈值上限值',
  `lower` int(11) NOT NULL DEFAULT 10 COMMENT '库存阈值下限值',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='库存阈值设置表，无阈值则不告警';

-- ----------------------------
-- Records of sto_threshold_info
-- ----------------------------
INSERT INTO `sto_threshold_info` VALUES ('1', '1', '1', 10, 100, '2018-02-01 19:22:55');

-- ----------------------------
-- Table structure for `sto_instore_order`
-- ----------------------------
DROP TABLE IF EXISTS `sto_instore_order`;
CREATE TABLE `sto_instore_order` (
  `id` varchar(64) NOT NULL COMMENT '主键ID',
  `code` varchar(64) NOT NULL COMMENT '编码',
  `store_id` varchar(64) NOT NULL COMMENT '入库仓库ID',
  `purchase_id` varchar(64) DEFAULT NULL COMMENT '入库对应采购单ID',
  `inuser_id` varchar(64) DEFAULT NULL COMMENT '入库人',
  `instore_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '入库时间',
  `remark` varchar(256) DEFAULT NULL COMMENT '备注',
  `status` int(11) NOT NULL DEFAULT 1 COMMENT '状态：1有效0失效, 撤销的入库单置为失效，默认情况下不可见,可以考虑加入定时清理计划中',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `code_unique` (`code`) USING HASH
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='入库单信息表，将物资信息copy过来，以防物资被修改或删除，入库记录发生变化，入库单一旦生成不允许修改，只允许撤销（删除）';

-- ----------------------------
-- Records of sto_instore_order
-- ----------------------------
INSERT INTO `sto_instore_order` VALUES ('1', 'ISZCK001201802010001', '1', '1', '1', '2018-02-01 19:22:55', '', 1, '2018-02-01 19:22:55');
INSERT INTO `sto_instore_order` VALUES ('2', 'ISZCK001201802010002', '2', '1', '1', '2018-02-01 19:22:55', '', 1, '2018-02-01 19:22:55');


-- ----------------------------
-- Table structure for `sto_instore_order_goods`
-- ----------------------------
DROP TABLE IF EXISTS `sto_instore_order_goods`;
CREATE TABLE `sto_instore_order_goods` (
  `id` varchar(64) NOT NULL COMMENT '主键ID',
  `instore_id` varchar(64) NOT NULL COMMENT '入库单ID',
  `purchase_goods_id` varchar(64) NOT NULL COMMENT '采购单物资清单ID',
  `goods_id` varchar(64) NOT NULL COMMENT '物资ID',
  `name` varchar(256) NOT NULL COMMENT '物资名称',
  `code` varchar(64) NOT NULL COMMENT '编码',
  `type` varchar(128) DEFAULT NULL COMMENT '类型',
  `brand` varchar(128) DEFAULT NULL COMMENT '品牌名称',
  `color` varchar(32) NOT NULL COMMENT '颜色',
  `unit` varchar(32) NOT NULL COMMENT '单位',
  `norm` varchar(512) DEFAULT NULL COMMENT '规格说明',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `quantity` int(11) NOT NULL DEFAULT 0 COMMENT '入库数量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='入库单物资清单表，作为入库单表的外键表，存储对应入库单的物资清单';

-- ----------------------------
-- Records of sto_instore_order_goods
-- ----------------------------
INSERT INTO `sto_instore_order_goods` VALUES ('1', '1', '1', '1', '格力空调3P', '001', '空调', '格力', '白色', '台', '', '', 20);
INSERT INTO `sto_instore_order_goods` VALUES ('2', '1', '2', '2', '格力空调1P', '002', '空调', '格力', '白色', '台', '', '', 25);
INSERT INTO `sto_instore_order_goods` VALUES ('3', '1', '3', '3', '格力空调大1P', '003', '空调', '格力', '白色', '台', '', '', 25);
INSERT INTO `sto_instore_order_goods` VALUES ('4', '2', '2', '2', '格力空调1P', '002', '空调', '格力', '白色', '台', '', '', 5);
INSERT INTO `sto_instore_order_goods` VALUES ('5', '2', '4', '3', '格力空调大1P', '003', '空调', '格力', '白色', '台', '', '', 5);

-- ----------------------------
-- Table structure for `sto_outstore_order`
-- ----------------------------
DROP TABLE IF EXISTS `sto_outstore_order`;
CREATE TABLE `sto_outstore_order` (
  `id` varchar(64) NOT NULL COMMENT '主键ID',
  `code` varchar(64) NOT NULL COMMENT '编码',
  `store_id` varchar(64) NOT NULL COMMENT '出库仓库ID',
  `outuser_id` varchar(64) NOT NULL COMMENT '出库人（库管）',
  `outstore_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '出库时间',
  `usedep_id` varchar(64) NOT NULL COMMENT '领用部门ID',
  `useuser_id` varchar(64) NOT NULL COMMENT '领用人ID',
  `remark` varchar(256) DEFAULT NULL COMMENT '备注',
  `status` int(11) NOT NULL DEFAULT 1 COMMENT '状态：1有效0失效, 撤销的入库单置为失效，默认情况下不可见,可以考虑加入定时清理计划中',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `code_unique` (`code`) USING HASH
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='出库单信息表，根据项目要求来确认需不需要更新库存，物资还是记录在库存中。出库单一旦生成不允许修改，只允许撤销（删除）';

-- ----------------------------
-- Records of sto_outstore_order
-- ----------------------------
INSERT INTO `sto_outstore_order` VALUES ('1', 'OSZCK001201802010001', '1', '1', '2018-02-01 19:22:55', '1', '1', '', 1, '2018-02-01 19:22:55');
INSERT INTO `sto_outstore_order` VALUES ('2', 'OSZCK001201802010002', '1', '1', '2018-02-01 19:22:55', '1', '1', '', 1, '2018-02-01 19:22:55');


-- ----------------------------
-- Table structure for `sto_outstore_order_goods`
-- ----------------------------
DROP TABLE IF EXISTS `sto_outstore_order_goods`;
CREATE TABLE `sto_outstore_order_goods` (
  `id` varchar(64) NOT NULL COMMENT '主键ID',
  `outstore_id` varchar(64) NOT NULL COMMENT '出库单ID',
  `stock_id` varchar(64) NOT NULL COMMENT '物资库存ID',
  `goods_id` varchar(64) NOT NULL COMMENT '物资ID',
  `name` varchar(256) NOT NULL COMMENT '物资名称',
  `code` varchar(64) NOT NULL COMMENT '编码',
  `type` varchar(128) DEFAULT NULL COMMENT '类型',
  `brand` varchar(128) DEFAULT NULL COMMENT '品牌名称',
  `color` varchar(32) NOT NULL COMMENT '颜色',
  `unit` varchar(32) NOT NULL COMMENT '单位',
  `norm` varchar(512) DEFAULT NULL COMMENT '规格说明',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `quantity` int(11) NOT NULL DEFAULT 0 COMMENT '出库数量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='出库单物资清单表，作为出库单表的外键表，存储对应出库单的物资清单';

-- ----------------------------
-- Records of sto_outstore_order_goods
-- ----------------------------
INSERT INTO `sto_outstore_order_goods` VALUES ('1', '1', '1', '1', '格力空调3P', '001', '空调', '格力', '白色', '台', '', '', 20);
INSERT INTO `sto_outstore_order_goods` VALUES ('2', '2', '2', '2', '格力空调1P', '002', '空调', '格力', '白色', '台', '', '', 5);
INSERT INTO `sto_outstore_order_goods` VALUES ('3', '1', '3', '3', '格力空调大1P', '003', '空调', '格力', '白色', '台', '', '', 20);

-- ----------------------------
-- Table structure for `sto_transfer_order`
-- ----------------------------
DROP TABLE IF EXISTS `sto_transfer_order`;
CREATE TABLE `sto_transfer_order` (
  `id` varchar(64) NOT NULL COMMENT '主键ID',
  `code` varchar(64) NOT NULL COMMENT '编码',
  `outstore_id` varchar(64) NOT NULL COMMENT '出库仓库ID',
  `outuser_id` varchar(64) NOT NULL COMMENT '出库人（库管）',
  `outstore_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '出库时间',
  `usedep_id` varchar(64) DEFAULT NULL COMMENT '领用部门ID（出库时指定负责调拨运输的部门可以为空）',
  `useuser_id` varchar(64) DEFAULT NULL COMMENT '领用人ID（出库时指定负责调拨运输的人可以为空）',
  `instore_id` varchar(64) NOT NULL COMMENT '入库仓库ID',
  `inuser_id` varchar(64) DEFAULT NULL COMMENT '入库人（库管）',
  `instore_time` timestamp DEFAULT NULL COMMENT '入库时间',
  `remark` varchar(256) DEFAULT NULL COMMENT '备注',
  `order_status` int(11) NOT NULL DEFAULT 1 COMMENT '调拨单状态，1为拨出，2为已到目标库房',
  `status` int(11) NOT NULL DEFAULT 1 COMMENT '状态：1有效0失效, 撤销的入库单置为失效，默认情况下不可见,可以考虑加入定时清理计划中',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `code_unique` (`code`) USING HASH
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='物资调拨单，出库入库仓库不能相同，一旦生成不允许修改，只允许撤销（删除）';

-- ----------------------------
-- Records of sto_transfer_order
-- ----------------------------
INSERT INTO `sto_transfer_order` VALUES ('1', 'TSZCK001201802010001', '1', '1', '2018-02-01 19:22:55', '1', '1', '2', null, null, '', 1,  1, '2018-02-01 19:22:55');
INSERT INTO `sto_transfer_order` VALUES ('2', 'TSZCK001201802010002', '1', '1', '2018-02-01 19:22:55', '1', '1', '2', '1', '2018-02-08 19:22:55', '', 1,  1, '2018-02-08 19:22:55');

-- ----------------------------
-- Table structure for `sto_transfer_order_goods`
-- ----------------------------
DROP TABLE IF EXISTS `sto_transfer_order_goods`;
CREATE TABLE `sto_transfer_order_goods` (
  `id` varchar(64) NOT NULL COMMENT '主键ID',
  `transfer_id` varchar(64) NOT NULL COMMENT '调拨单ID',
  `stock_id` varchar(64) NOT NULL COMMENT '物资库存ID',
  `goods_id` varchar(64) NOT NULL COMMENT '物资ID',
  `name` varchar(256) NOT NULL COMMENT '物资名称',
  `code` varchar(64) NOT NULL COMMENT '编码',
  `type` varchar(128) DEFAULT NULL COMMENT '类型',
  `brand` varchar(128) DEFAULT NULL COMMENT '品牌名称',
  `color` varchar(32) NOT NULL COMMENT '颜色',
  `unit` varchar(32) NOT NULL COMMENT '单位',
  `norm` varchar(512) DEFAULT NULL COMMENT '规格说明',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `quantity` int(11) NOT NULL DEFAULT 0 COMMENT '调拨数量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='调拨单物资清单表，作为调拨单表的外键表，存储对应调拨单的物资清单';

-- ----------------------------
-- Records of sto_transfer_order_goods
-- ----------------------------
INSERT INTO `sto_transfer_order_goods` VALUES ('1', '1', '1', '1', '格力空调3P', '001', '空调', '格力', '白色', '台', '', '', 1);
INSERT INTO `sto_transfer_order_goods` VALUES ('2', '2', '2', '2', '格力空调1P', '002', '空调', '格力', '白色', '台', '', '', 5);
INSERT INTO `sto_transfer_order_goods` VALUES ('3', '2', '3', '3', '格力空调大1P', '003', '空调', '格力', '白色', '台', '', '', 5);


-- ----------------------------
-- Table structure for `sto_inventory_order`
-- ----------------------------
DROP TABLE IF EXISTS `sto_inventory_order`;
CREATE TABLE `sto_inventory_order` (
  `id` varchar(64) NOT NULL COMMENT '主键ID',
  `code` varchar(64) NOT NULL COMMENT '编码',
  `store_id` varchar(64) NOT NULL COMMENT '盘点仓库ID',
  `makeuser_id` varchar(64) NOT NULL COMMENT '盘点人发起人',
  `makestart_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '盘点开始时间',
  `makeend_time` timestamp DEFAULT NULL COMMENT '盘点结束时间',
  `lastmake_time` timestamp DEFAULT NULL COMMENT '本仓库上次盘点时间',
  `make_status` int(11) NOT NULL DEFAULT 0 COMMENT '盘点状态，0盘点中，1盘点完结',
  `remark` varchar(256) DEFAULT NULL COMMENT '备注',
  `status` int(11) NOT NULL DEFAULT 1 COMMENT '状态：1有效0失效, 撤销的入库单置为失效，默认情况下不可见,可以考虑加入定时清理计划中',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `code_unique` (`code`) USING HASH
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='库存盘点单，以库房为单位进行盘点，当前库房正在盘点时，不能重复生成盘点单进入盘点';

-- ----------------------------
-- Records of sto_inventory_order
-- ----------------------------
INSERT INTO `sto_inventory_order` VALUES ('1', 'MSZCK001201802010001', '1', '1', '2018-02-01 19:22:55', null, null, 0, '', 1, '2018-02-01 19:22:55');
INSERT INTO `sto_inventory_order` VALUES ('2', 'MSZCK001201802010002', '2', '1', '2018-02-01 19:22:55', null, '2018-02-01 19:22:55', 1, '', 1, '2018-02-01 19:22:55');

-- ----------------------------
-- Table structure for `sto_inventory_order_goods`
-- ----------------------------
DROP TABLE IF EXISTS `sto_inventory_order_goods`;
CREATE TABLE `sto_inventory_order_goods` (
  `id` varchar(64) NOT NULL COMMENT '主键ID',
  `inventory_id` varchar(64) NOT NULL COMMENT '盘点单ID',
  `stock_id` varchar(64) NOT NULL COMMENT '物资库存ID',
  `outstore_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '查询出库表物资的出库时间作为物资的启用时间',
  `usedep_id` varchar(64) DEFAULT NULL COMMENT '领用部门ID，如果当前物资还在库房中未被领用，则此字段为空，物资状态为1存库',
  `goods_status` int(11) NOT NULL DEFAULT 1 COMMENT '物资状态：1存库，2在用，0报损，复制出库单状态，现在系统没有物资报损，所以物资状态可能会一直显示存库或在用',
  `make_status` int(11) NOT NULL DEFAULT 0 COMMENT '盘点状态，0盘点中，1盘点完成(允许再次修改数量，直到此次盘点单完结)',
  `remark` varchar(256) DEFAULT NULL COMMENT '备注',
  `last_quantity` int(11) NOT NULL DEFAULT 0 COMMENT '上次盘点数量',
  `makeuser_id` varchar(64) NOT NULL COMMENT '盘点人',
  `make_time` timestamp DEFAULT NULL COMMENT '盘点时间',
  `quantity` int(11) NOT NULL DEFAULT 0 COMMENT '盘点数量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='盘点单物资清单表，作为盘点单表的外键表，存储对应盘点单的物资清单';

-- ----------------------------
-- Records of sto_inventory_order_goods
-- ----------------------------
INSERT INTO `sto_inventory_order_goods` VALUES ('1', '1', '1', '2018-02-01 19:22:55', '1', 2, 0, '', 20, '1', '2018-03-01 19:22:55', 20);
INSERT INTO `sto_inventory_order_goods` VALUES ('2', '1', '2', '2018-02-01 19:22:55', '1', 1, 0, '', 20, '1', '2018-03-01 19:22:55', 20);
INSERT INTO `sto_inventory_order_goods` VALUES ('3', '1', '3', '2018-02-01 19:22:55', '1', 2, 0, '', 20, '1', '2018-03-01 19:22:55', 20);
INSERT INTO `sto_inventory_order_goods` VALUES ('4', '2', '4', '2018-02-01 19:22:55', '1', 2, 1, '', 5, '1', '2018-03-01 19:22:55', 5);
INSERT INTO `sto_inventory_order_goods` VALUES ('5', '2', '4', '2018-02-01 19:22:55', '1', 1, 1, '', 5, '1', '2018-03-01 19:22:55', 5);
INSERT INTO `sto_inventory_order_goods` VALUES ('6', '2', '5', '2018-02-01 19:22:55', '1', 2, 1, '', 10, '1', '2018-03-01 19:22:55', 10);