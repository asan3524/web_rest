-- ----------------------------
-- Table structure for `inq_inquiry_order`
-- ----------------------------
DROP TABLE IF EXISTS `inq_inquiry_order`;
CREATE TABLE `inq_inquiry_order` (
  `id` varchar(64) NOT NULL COMMENT '主键ID',
  `code` varchar(64) NOT NULL COMMENT '编码',
  `name` varchar(128) NOT NULL COMMENT '名称',
  `initiateuser_id` varchar(64) DEFAULT NULL COMMENT '询价发起人',
  `communicateuser_id` varchar(64) DEFAULT NULL COMMENT '详情沟通人',
  `inquiryuser_id` varchar(64) DEFAULT NULL COMMENT '询价人',
  `evaluateuser_id` varchar(64) DEFAULT NULL COMMENT '现场评估人',
  `audituser_id` varchar(64) DEFAULT NULL COMMENT '评估审核人',
  `decisionuser_id` varchar(64) DEFAULT NULL COMMENT '决策人',
  `initiate_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '询价发起时间',
  `communicate_time` timestamp DEFAULT NULL COMMENT '详情沟通时间',
  `inquiry_time` timestamp DEFAULT NULL COMMENT '询价时间',
  `evaluate_time` timestamp DEFAULT NULL COMMENT '现场评估时间',
  `audit_time` timestamp DEFAULT NULL COMMENT '评估审核时间',
  `decision_time` timestamp DEFAULT NULL COMMENT '决策时间',
  `inquiry_price` decimal(13,2) NOT NULL DEFAULT 0.00 COMMENT '询价总价',
  `initiate_remark` varchar(512) DEFAULT NULL COMMENT '询价说明，询价发起时填写',
  `communicate_remark` varchar(512) DEFAULT NULL COMMENT '询价补充说明，详情沟通时填写',
  `evaluate_remark` varchar(512) DEFAULT NULL COMMENT '评估说明，现场评估后填写',
  `audit_remark` varchar(512) DEFAULT NULL COMMENT '审核说明，评估审核时填写',
  `step` int(11) NOT NULL DEFAULT 1 COMMENT '询价单所处节点：1待沟通2待询价3待评估4待审核5待决策6已完成',
  `evaluate_result` int(11) NOT NULL DEFAULT 0 COMMENT '现场评估结论：1通过0不通过',
  `audit_result` int(11) NOT NULL DEFAULT 0 COMMENT '评估审核结论：1通过0不通过',
  `decision_result` int(11) NOT NULL DEFAULT 0 COMMENT '采购决策结论：1采购0不采购',
  `life_status` int(11) NOT NULL DEFAULT 1 COMMENT '生命周期：1有效0失效, 从发起时间起一个月内的询价单置为失效，询价单不可再改变状态。采购、不采购均为已完成',
  `status` int(11) NOT NULL DEFAULT 1 COMMENT '状态：1有效0删除, 被删除的询价单置为已删除',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `code_unique` (`code`) USING HASH
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='询价申请单';

-- ----------------------------
-- Records of inq_inquiry_order
-- ----------------------------
INSERT INTO `inq_inquiry_order` VALUES ('1', 'INQCK001201802010001', '格力空调询价', '1', '1', '1', '1', '1', '1', '2018-02-01 19:22:55', '2018-02-01 19:22:55', '2018-02-01 19:22:55', '2018-02-01 19:22:55', '2018-02-01 19:22:55', '2018-02-01 19:22:55', 4129.30, '询价说明', '补充说明', '评估说明', '审核说明', 6, 1, 1, 1, 1, 1, '2018-02-01 19:22:55');

-- ----------------------------
-- Table structure for `inq_inquiry_order_goods`
-- ----------------------------
DROP TABLE IF EXISTS `inq_inquiry_order_goods`;
CREATE TABLE `inq_inquiry_order_goods` (
  `id` varchar(64) NOT NULL COMMENT '主键ID',
  `inquiry_id` varchar(64) NOT NULL COMMENT '询价单ID',
  `goods_id` varchar(64) NOT NULL COMMENT '物资ID',
  `name` varchar(256) NOT NULL COMMENT '物资名称',
  `code` varchar(64) NOT NULL COMMENT '编码',
  `type` varchar(128) DEFAULT NULL COMMENT '类型',
  `brand` varchar(128) DEFAULT NULL COMMENT '品牌名称',
  `color` varchar(32) NOT NULL COMMENT '颜色',
  `unit` varchar(32) NOT NULL COMMENT '单位',
  `norm` varchar(512) DEFAULT NULL COMMENT '规格说明',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `replace` int(11) NOT NULL DEFAULT 0 COMMENT '询价物资使用情况，0新购，1更换',
  `unit_price` decimal(11,2) NOT NULL DEFAULT 0.00 COMMENT '询价单价',
  `quantity` int(11) NOT NULL DEFAULT 0 COMMENT '询价数量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='询价单物资清单';

-- ----------------------------
-- Records of inq_inquiry_order_goods
-- ----------------------------
INSERT INTO `inq_inquiry_order_goods` VALUES ('1', '1', '1', '格力空调3P', '001', '空调', '格力', '白色', '台', '', '', 1, 412.93, 10);