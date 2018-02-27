-- ----------------------------
-- Table structure for `pur_purchase_order`
-- ----------------------------
DROP TABLE IF EXISTS `pur_purchase_order`;
CREATE TABLE `pur_purchase_order` (
  `id` varchar(64) NOT NULL COMMENT '主键ID',
  `code` varchar(64) NOT NULL COMMENT '编码',
  `name` varchar(128) NOT NULL COMMENT '名称',
  `initiateuser_id` varchar(64) DEFAULT NULL COMMENT '采购发起人',
  `audituser_id` varchar(64) DEFAULT NULL COMMENT '预算审核人',
  `decisionuser_id` varchar(64) DEFAULT NULL COMMENT '采购审批人',
  `purchaseuser_id` varchar(64) DEFAULT NULL COMMENT '采购人',
  `initiate_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '采购发起时间',
  `audit_time` timestamp DEFAULT NULL COMMENT '预算审核时间',
  `decision_time` timestamp DEFAULT NULL COMMENT '采购审批时间',
  `purchase_time` timestamp DEFAULT NULL COMMENT '采购时间',
  `inquiry_price` decimal(13,2) NOT NULL DEFAULT 0.00 COMMENT '询价总价',
  `purchase_price` decimal(13,2) NOT NULL DEFAULT 0.00 COMMENT '采购总价，采购总价必须小于询价总价',
  `initiate_remark` varchar(256) DEFAULT NULL COMMENT '采购说明，采购发起时填写',
  `step` int(11) NOT NULL DEFAULT 1 COMMENT '采购单所处节点：1待审核2待审批3待采购4待入库5入库中6已完成7无预算8未批准,6/7/8均为终止状态不可再改变',
  `audit_result` int(11) NOT NULL DEFAULT 0 COMMENT '预算审核结论：1有预算0无预算',
  `decision_result` int(11) NOT NULL DEFAULT 0 COMMENT '采购审批结论：1批准0未批准',
  `status` int(11) NOT NULL DEFAULT 1 COMMENT '状态：1有效0删除, 被删除的采购单置为已删除',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `code_unique` (`code`) USING HASH
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='询价申请单';

-- ----------------------------
-- Records of pur_purchase_order
-- ----------------------------
INSERT INTO `pur_purchase_order` VALUES ('1', 'PURCK001201802010001', '格力空调采购', '1', '1', '1', '1', '2018-02-01 19:22:55', '2018-02-01 19:22:55', '2018-02-01 19:22:55', '2018-02-01 19:22:55', 4129.30, 4000, '采购申请说明', 8, 1, 1, 1, '2018-02-01 19:22:55');

-- ----------------------------
-- Table structure for `pur_purchase_to_store`
-- ----------------------------
DROP TABLE IF EXISTS `pur_purchase_to_store`;
CREATE TABLE `pur_purchase_to_store` (
  `id` varchar(64) NOT NULL COMMENT '主键ID',
  `purchase_id` varchar(64) NOT NULL COMMENT '采购单ID',
  `store_id` varchar(64) NOT NULL COMMENT '仓库ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='当前采购指定的入库仓库';

-- ----------------------------
-- Records of pur_purchase_to_store
-- ----------------------------
INSERT INTO `pur_purchase_to_store` VALUES ('1', '1', '1');
INSERT INTO `pur_purchase_to_store` VALUES ('2', '1', '2');

-- ----------------------------
-- Table structure for `pur_purchase_to_inquiry`
-- ----------------------------
DROP TABLE IF EXISTS `pur_purchase_to_inquiry`;
CREATE TABLE `pur_purchase_to_inquiry` (
  `id` varchar(64) NOT NULL COMMENT '主键ID',
  `purchase_id` varchar(64) NOT NULL COMMENT '采购单ID',
  `inquiry_id` varchar(64) NOT NULL COMMENT '询价单ID，采购时选择的询价单必须是未失效、已完成、审核意见为采购的询价单',
  `code` varchar(64) NOT NULL COMMENT '询价单编码',
  `name` varchar(128) NOT NULL COMMENT '询价单名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='当前采购关联的询价单列表';

-- ----------------------------
-- Records of pur_purchase_to_inquiry
-- ----------------------------
INSERT INTO `pur_purchase_to_inquiry` VALUES ('1', '1', '1', 'INQCK001201802010001', '格力空调询价');

-- ----------------------------
-- Table structure for `pur_purchase_order_goods`
-- ----------------------------
DROP TABLE IF EXISTS `pur_purchase_order_goods`;
CREATE TABLE `pur_purchase_order_goods` (
  `id` varchar(64) NOT NULL COMMENT '主键ID',
  `purchase_inquiry_id` varchar(64) NOT NULL COMMENT '采购单与询价单关联ID',
  `goods_id` varchar(64) NOT NULL COMMENT '物资ID',
  `name` varchar(256) NOT NULL COMMENT '物资名称',
  `code` varchar(64) NOT NULL COMMENT '编码',
  `type` varchar(128) DEFAULT NULL COMMENT '类型',
  `brand` varchar(128) DEFAULT NULL COMMENT '品牌名称',
  `colour` varchar(32) NOT NULL COMMENT '颜色',
  `unit` varchar(32) NOT NULL COMMENT '单位',
  `norm` varchar(512) DEFAULT NULL COMMENT '规格说明',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `unit_price` decimal(11,2) NOT NULL DEFAULT 0.00 COMMENT '询价单价',
  `price` decimal(11,2) NOT NULL DEFAULT 0.00 COMMENT '实际采购单价',
  `quantity` int(11) NOT NULL DEFAULT 0 COMMENT '询价数量',
  `instore_num` int(11) NOT NULL DEFAULT 0 COMMENT '已入库数量',
  `supplier` varchar(128) DEFAULT NULL COMMENT '供货商',
  `arrival_time` timestamp DEFAULT NULL COMMENT '预计到货时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='采购单物资清单';

-- ----------------------------
-- Records of pur_purchase_order_goods
-- ----------------------------
INSERT INTO `pur_purchase_order_goods` VALUES ('1', '1', '1', '格力空调3P', '001', '空调', '格力', '白色', '台', '', '', 412.93, 400, 20, 20, '京东', '2018-02-01 19:22:55');
INSERT INTO `pur_purchase_order_goods` VALUES ('2', '1', '2', '格力空调1P', '002', '空调', '格力', '白色', '台', '', '', 412.93, 400, 30, 30, '京东', '2018-02-01 19:22:55');
INSERT INTO `pur_purchase_order_goods` VALUES ('3', '1', '3', '格力空调大1P', '003', '空调', '格力', '白色', '台', '', '', 412.93, 400, 30, 30, '京东', '2018-02-01 19:22:55');
