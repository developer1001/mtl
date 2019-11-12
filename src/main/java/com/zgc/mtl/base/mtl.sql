/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50728
Source Host           : localhost:3306
Source Database       : mtl

Target Server Type    : MYSQL
Target Server Version : 50728
File Encoding         : 65001

Date: 2019-11-12 14:15:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for base_sex
-- ----------------------------
DROP TABLE IF EXISTS `base_sex`;
CREATE TABLE `base_sex` (
  `sex_id` varchar(1) NOT NULL COMMENT '主键',
  `sex` varchar(2) NOT NULL COMMENT '性别',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_user` varchar(20) NOT NULL COMMENT '创建人',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `update_user` varchar(20) NOT NULL COMMENT '更新人',
  PRIMARY KEY (`sex_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='性别基表';

-- ----------------------------
-- Records of base_sex
-- ----------------------------
INSERT INTO `base_sex` VALUES ('0', '男', '2019-11-02 00:00:00', 'admin', '2019-11-02 11:24:55', 'admin');
INSERT INTO `base_sex` VALUES ('1', '女', '2019-11-02 00:00:00', 'admin', '2019-11-02 11:25:17', 'admin');
INSERT INTO `base_sex` VALUES ('2', '未知', '2019-11-02 00:00:00', 'admin', '2019-11-02 11:44:14', 'admin');

-- ----------------------------
-- Table structure for qrtz_blob_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_blob_triggers`;
CREATE TABLE `qrtz_blob_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(190) NOT NULL,
  `TRIGGER_GROUP` varchar(190) NOT NULL,
  `BLOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `SCHED_NAME` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_blob_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_calendars
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_calendars`;
CREATE TABLE `qrtz_calendars` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `CALENDAR_NAME` varchar(190) NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_calendars
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE `qrtz_cron_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(190) NOT NULL,
  `TRIGGER_GROUP` varchar(190) NOT NULL,
  `CRON_EXPRESSION` varchar(120) NOT NULL,
  `TIME_ZONE_ID` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_cron_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE `qrtz_fired_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `ENTRY_ID` varchar(95) NOT NULL,
  `TRIGGER_NAME` varchar(190) NOT NULL,
  `TRIGGER_GROUP` varchar(190) NOT NULL,
  `INSTANCE_NAME` varchar(190) NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) NOT NULL,
  `JOB_NAME` varchar(190) DEFAULT NULL,
  `JOB_GROUP` varchar(190) DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`ENTRY_ID`),
  KEY `IDX_QRTZ_FT_TRIG_INST_NAME` (`SCHED_NAME`,`INSTANCE_NAME`),
  KEY `IDX_QRTZ_FT_INST_JOB_REQ_RCVRY` (`SCHED_NAME`,`INSTANCE_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_QRTZ_FT_J_G` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_FT_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_FT_T_G` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_FT_TG` (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_fired_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_job_details
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE `qrtz_job_details` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `JOB_NAME` varchar(190) NOT NULL,
  `JOB_GROUP` varchar(190) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) NOT NULL,
  `IS_DURABLE` varchar(1) NOT NULL,
  `IS_NONCONCURRENT` varchar(1) NOT NULL,
  `IS_UPDATE_DATA` varchar(1) NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) NOT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_J_REQ_RECOVERY` (`SCHED_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_QRTZ_J_GRP` (`SCHED_NAME`,`JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_job_details
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_locks
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE `qrtz_locks` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `LOCK_NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_locks
-- ----------------------------
INSERT INTO `qrtz_locks` VALUES ('clusteredScheduler', 'STATE_ACCESS');
INSERT INTO `qrtz_locks` VALUES ('clusteredScheduler', 'TRIGGER_ACCESS');

-- ----------------------------
-- Table structure for qrtz_paused_trigger_grps
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE `qrtz_paused_trigger_grps` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_GROUP` varchar(190) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_paused_trigger_grps
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE `qrtz_scheduler_state` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `INSTANCE_NAME` varchar(190) NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_scheduler_state
-- ----------------------------
INSERT INTO `qrtz_scheduler_state` VALUES ('clusteredScheduler', 'DESKTOP-T4A4EQD1572673916740', '1572676627652', '10000');

-- ----------------------------
-- Table structure for qrtz_simple_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simple_triggers`;
CREATE TABLE `qrtz_simple_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(190) NOT NULL,
  `TRIGGER_GROUP` varchar(190) NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_simple_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_simprop_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
CREATE TABLE `qrtz_simprop_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(190) NOT NULL,
  `TRIGGER_GROUP` varchar(190) NOT NULL,
  `STR_PROP_1` varchar(512) DEFAULT NULL,
  `STR_PROP_2` varchar(512) DEFAULT NULL,
  `STR_PROP_3` varchar(512) DEFAULT NULL,
  `INT_PROP_1` int(11) DEFAULT NULL,
  `INT_PROP_2` int(11) DEFAULT NULL,
  `LONG_PROP_1` bigint(20) DEFAULT NULL,
  `LONG_PROP_2` bigint(20) DEFAULT NULL,
  `DEC_PROP_1` decimal(13,4) DEFAULT NULL,
  `DEC_PROP_2` decimal(13,4) DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_simprop_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_triggers`;
CREATE TABLE `qrtz_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(190) NOT NULL,
  `TRIGGER_GROUP` varchar(190) NOT NULL,
  `JOB_NAME` varchar(190) NOT NULL,
  `JOB_GROUP` varchar(190) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) NOT NULL,
  `TRIGGER_TYPE` varchar(8) NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) DEFAULT NULL,
  `CALENDAR_NAME` varchar(190) DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) DEFAULT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_T_J` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_T_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_T_C` (`SCHED_NAME`,`CALENDAR_NAME`),
  KEY `IDX_QRTZ_T_G` (`SCHED_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_T_STATE` (`SCHED_NAME`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_N_STATE` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_N_G_STATE` (`SCHED_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_NEXT_FIRE_TIME` (`SCHED_NAME`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_ST` (`SCHED_NAME`,`TRIGGER_STATE`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE_GRP` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `qrtz_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_name` varchar(40) NOT NULL COMMENT '用户名',
  `login_name` varchar(40) NOT NULL COMMENT '登录名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `is_active` int(1) NOT NULL DEFAULT '0' COMMENT '0未激活，1激活状态',
  PRIMARY KEY (`id`),
  UNIQUE KEY `loginName` (`login_name`) USING BTREE,
  UNIQUE KEY `UQ_loginUser` (`login_name`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '管理员', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '1');
INSERT INTO `sys_user` VALUES ('2', '超级管理员', 'superadmin', 'e10adc3949ba59abbe56e057f20f883e', '1');
INSERT INTO `sys_user` VALUES ('5', '企业1', '问', 'e10adc3949ba59abbe56e057f20f883e', '1');
INSERT INTO `sys_user` VALUES ('7', '测试改', 'ceshi11', 'e10adc3949ba59abbe56e057f20f883e', '0');
INSERT INTO `sys_user` VALUES ('8', '经理', 'manager', 'e10adc3949ba59abbe56e057f20f883e', '1');
INSERT INTO `sys_user` VALUES ('9', '9号员工', 's', '7f6ffaa6bb0b408017b62254211691b5', '1');

-- ----------------------------
-- Table structure for t_customer
-- ----------------------------
DROP TABLE IF EXISTS `t_customer`;
CREATE TABLE `t_customer` (
  `id` varchar(255) NOT NULL,
  `birthday` datetime DEFAULT NULL,
  `name` varchar(64) NOT NULL,
  `phone` varchar(64) DEFAULT NULL,
  `sex` varchar(1) DEFAULT NULL,
  `created_by` varchar(64) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_by` varchar(64) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_customer
-- ----------------------------
INSERT INTO `t_customer` VALUES ('402819856c5fd537016c5fd569d20000', '2019-10-16 14:46:24', '129', '14187824329', 'F', 'admin', '2019-08-05 11:31:57', 'admin', '2019-08-05 11:31:57');
INSERT INTO `t_customer` VALUES ('402819856c5fd537016c5fd56a010001', null, '129', '130690484523203', 'F', 'admin', '2019-08-05 11:31:57', 'admin', '2019-08-05 11:31:57');
INSERT INTO `t_customer` VALUES ('402819856c5fd537016c5fd56a020002', null, '129', '175634800120650', 'F', 'admin', '2019-08-05 11:31:57', 'admin', '2019-08-05 11:31:57');
INSERT INTO `t_customer` VALUES ('402819856c5fd537016c5fd56a020003', null, '129', '192225689472118', 'F', 'admin', '2019-08-05 11:31:57', 'admin', '2019-08-05 11:31:57');
INSERT INTO `t_customer` VALUES ('402819856c5fd537016c5fd56a020004', null, '178', '126681451368906', 'M', 'admin', '2019-08-05 11:31:57', 'admin', '2019-08-05 11:31:57');

-- ----------------------------
-- Table structure for t_healthy
-- ----------------------------
DROP TABLE IF EXISTS `t_healthy`;
CREATE TABLE `t_healthy` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `level` double(10,3) DEFAULT NULL COMMENT '指数',
  `level_type` int(2) DEFAULT NULL COMMENT '1:bg（血糖）; 2:weight; 3:bp（血压）',
  `user_id` varchar(20) DEFAULT NULL COMMENT '用户编号',
  `create_date` datetime DEFAULT NULL COMMENT '创建日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='健康指数表';

-- ----------------------------
-- Records of t_healthy
-- ----------------------------
INSERT INTO `t_healthy` VALUES ('3', '3.000', '4', '1', '2019-07-20 11:20:45');
INSERT INTO `t_healthy` VALUES ('4', '2.200', '4', '1', '2019-07-24 11:20:45');
INSERT INTO `t_healthy` VALUES ('5', '5.000', '4', '1', '2019-07-22 11:20:45');
INSERT INTO `t_healthy` VALUES ('6', '1.000', '4', '5', '2019-07-23 11:20:45');
INSERT INTO `t_healthy` VALUES ('7', '2.500', '4', '5', '2019-10-31 11:20:45');
INSERT INTO `t_healthy` VALUES ('8', '3.300', '4', '5', '2019-07-28 11:20:45');

-- ----------------------------
-- Table structure for t_invest_order
-- ----------------------------
DROP TABLE IF EXISTS `t_invest_order`;
CREATE TABLE `t_invest_order` (
  `order_id` varchar(3) NOT NULL COMMENT '订单编号',
  `user_id` varchar(50) NOT NULL COMMENT 'user_id',
  `prd_id` int(2) NOT NULL COMMENT '产品id',
  `purchase_date` date NOT NULL COMMENT '购买日期',
  `purchase_quota` bigint(10) NOT NULL COMMENT '购买金额（以分为单位）',
  `profit_begin` date NOT NULL COMMENT '收益开始日',
  `expire_date` date DEFAULT NULL COMMENT '到期日',
  `extract_date` date DEFAULT NULL COMMENT '到账日',
  `extract_description` varchar(100) DEFAULT NULL COMMENT '到账描述',
  `extract_mode` varchar(100) DEFAULT NULL COMMENT '到账方式',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_user` varchar(20) NOT NULL COMMENT '创建人',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `update_user` varchar(20) NOT NULL COMMENT '更新人',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='投资订单表';

-- ----------------------------
-- Records of t_invest_order
-- ----------------------------
INSERT INTO `t_invest_order` VALUES ('1', 'e31e1cd693159fc458cb7ca71f4d4af5', '1', '2019-10-14', '100000', '2019-10-15', '2019-11-14', '2019-11-15', '2019年11月15日 24点前', '余额宝或支付宝余额', '2019-11-02 13:20:57', 'admin', '2019-11-02 13:20:46', 'admin');
INSERT INTO `t_invest_order` VALUES ('2', 'e31e1cd693159fc458cb7ca71f4d4af5', '2', '2019-10-21', '1480000', '2019-10-21', '2020-01-31', '2020-02-03', '2020年2月3日 24点前', '余额宝或支付宝余额', '2019-11-02 13:21:07', 'admin', '2019-11-02 13:21:11', 'admin');
INSERT INTO `t_invest_order` VALUES ('3', 'e31e1cd693159fc458cb7ca71f4d4af5', '3', '2019-09-04', '1000000', '2019-09-05', null, null, 'T+2赎回到银行卡', '银行卡', '2019-11-02 13:29:36', 'admin', '2019-11-02 13:29:40', 'admin');
INSERT INTO `t_invest_order` VALUES ('4', 'e31e1cd693159fc458cb7ca71f4d4af5', '4', '2019-11-10', '300000', '2019-11-12', null, null, null, null, '2019-11-12 11:25:44', 'admin', '2019-11-12 11:25:48', 'admin');
INSERT INTO `t_invest_order` VALUES ('5', 'e31e1cd693159fc458cb7ca71f4d4af5', '5', '2019-11-10', '100000', '2019-11-12', null, null, null, null, '2019-11-12 12:37:49', 'admin', '2019-11-12 12:37:52', 'admin');
INSERT INTO `t_invest_order` VALUES ('6', 'e31e1cd693159fc458cb7ca71f4d4af5', '6', '2019-11-11', '200000', '2019-11-12', null, null, null, null, '2019-11-12 12:41:35', 'admin', '2019-11-12 12:41:39', 'admin');

-- ----------------------------
-- Table structure for t_invest_platform
-- ----------------------------
DROP TABLE IF EXISTS `t_invest_platform`;
CREATE TABLE `t_invest_platform` (
  `platform_id` varchar(1) NOT NULL COMMENT '投资平台id',
  `name` varchar(20) NOT NULL COMMENT '投资平台名称',
  `description` varchar(20) DEFAULT NULL COMMENT '平台描述',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_user` varchar(20) NOT NULL COMMENT '创建人',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `update_user` varchar(20) NOT NULL COMMENT '更新人',
  PRIMARY KEY (`platform_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='投资平台表';

-- ----------------------------
-- Records of t_invest_platform
-- ----------------------------
INSERT INTO `t_invest_platform` VALUES ('1', '支付宝', null, '2019-11-02 12:35:54', 'admin', '2019-11-02 12:35:56', 'admin');
INSERT INTO `t_invest_platform` VALUES ('2', '招商银行', null, '2019-11-02 12:36:15', 'admin', '2019-11-02 12:36:17', 'admin');

-- ----------------------------
-- Table structure for t_invest_product
-- ----------------------------
DROP TABLE IF EXISTS `t_invest_product`;
CREATE TABLE `t_invest_product` (
  `prd_id` int(2) NOT NULL AUTO_INCREMENT COMMENT '产品id',
  `name` varchar(20) NOT NULL COMMENT '产品名称',
  `short_name` varchar(10) NOT NULL COMMENT '产品简称',
  `platform_id` int(1) NOT NULL COMMENT '平台id',
  `type` varchar(1) NOT NULL COMMENT '投资类型',
  `min_buy` bigint(10) NOT NULL COMMENT '起购金额（以分为单位）',
  `period` int(5) DEFAULT NULL COMMENT '产品期限',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_user` varchar(20) NOT NULL COMMENT '创建人',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `update_user` varchar(20) NOT NULL COMMENT '更新人',
  PRIMARY KEY (`prd_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='投资产品表';

-- ----------------------------
-- Records of t_invest_product
-- ----------------------------
INSERT INTO `t_invest_product` VALUES ('1', '安邦安增益31天', '安邦31天', '1', '1', '100000', '31', '2019-11-02 12:58:58', 'admin', '2019-11-02 12:59:01', 'admin');
INSERT INTO `t_invest_product` VALUES ('2', '太平久久', '太平久久', '1', '1', '100000', '99', '2019-11-02 12:59:59', 'admin', '2019-11-02 13:00:04', 'admin');
INSERT INTO `t_invest_product` VALUES ('3', '博时现金宝A', '博时现A', '2', '2', '1', null, '2019-11-02 13:00:56', 'admin', '2019-11-02 13:01:00', 'admin');
INSERT INTO `t_invest_product` VALUES ('4', '长江养老月安享', '长江月享', '1', '1', '100000', '30', '2019-11-12 11:22:21', 'admin', '2019-11-12 11:22:25', 'admin');
INSERT INTO `t_invest_product` VALUES ('5', '平安富盈45天', '平安盈45', '1', '1', '100000', '45', '2019-11-12 12:35:50', 'admin', '2019-11-12 12:35:54', 'admin');
INSERT INTO `t_invest_product` VALUES ('6', '平安金通30天', '平安金30', '1', '1', '100000', '30', '2019-11-12 12:40:27', 'admin', '2019-11-12 12:40:31', 'admin');

-- ----------------------------
-- Table structure for t_invest_profit
-- ----------------------------
DROP TABLE IF EXISTS `t_invest_profit`;
CREATE TABLE `t_invest_profit` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_id` varchar(2) NOT NULL COMMENT '订单编号',
  `profit_day` date NOT NULL COMMENT '收益日',
  `profit` bigint(10) NOT NULL COMMENT '收益（以分为单位）',
  `curr_total` bigint(10) NOT NULL COMMENT '当日截止，总金额（以分为单位）',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user` varchar(20) NOT NULL DEFAULT 'admin' COMMENT '创建人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_user` varchar(20) NOT NULL DEFAULT 'admin' COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8 COMMENT='订单收益明细表';

-- ----------------------------
-- Records of t_invest_profit
-- ----------------------------
INSERT INTO `t_invest_profit` VALUES ('1', '3', '2019-09-05', '67', '1000067', '2019-11-02 13:52:31', 'admin', '2019-11-02 13:52:31', 'admin');
INSERT INTO `t_invest_profit` VALUES ('2', '3', '2019-09-06', '67', '1000134', '2019-11-02 13:54:25', 'admin', '2019-11-02 13:54:25', 'admin');
INSERT INTO `t_invest_profit` VALUES ('3', '3', '2019-09-09', '201', '1000335', '2019-11-02 13:54:46', 'admin', '2019-11-02 13:54:46', 'admin');
INSERT INTO `t_invest_profit` VALUES ('4', '3', '2019-09-10', '67', '1000402', '2019-11-02 13:55:02', 'admin', '2019-11-02 13:55:02', 'admin');
INSERT INTO `t_invest_profit` VALUES ('5', '3', '2019-09-11', '67', '1000469', '2019-11-02 13:55:14', 'admin', '2019-11-02 13:55:14', 'admin');
INSERT INTO `t_invest_profit` VALUES ('6', '3', '2019-09-12', '68', '1000537', '2019-11-02 13:55:32', 'admin', '2019-11-02 13:55:32', 'admin');
INSERT INTO `t_invest_profit` VALUES ('7', '3', '2019-09-16', '272', '1000809', '2019-11-02 13:56:58', 'admin', '2019-11-02 13:56:58', 'admin');
INSERT INTO `t_invest_profit` VALUES ('8', '3', '2019-09-17', '68', '1000877', '2019-11-02 13:57:20', 'admin', '2019-11-02 13:57:20', 'admin');
INSERT INTO `t_invest_profit` VALUES ('9', '3', '2019-09-18', '67', '1000944', '2019-11-02 13:57:45', 'admin', '2019-11-02 13:57:45', 'admin');
INSERT INTO `t_invest_profit` VALUES ('10', '3', '2019-09-19', '67', '1001011', '2019-11-02 13:58:09', 'admin', '2019-11-02 13:58:09', 'admin');
INSERT INTO `t_invest_profit` VALUES ('11', '3', '2019-09-20', '66', '1001077', '2019-11-02 13:58:31', 'admin', '2019-11-02 13:58:31', 'admin');
INSERT INTO `t_invest_profit` VALUES ('12', '3', '2019-09-23', '201', '1001278', '2019-11-02 13:58:57', 'admin', '2019-11-02 13:58:57', 'admin');
INSERT INTO `t_invest_profit` VALUES ('13', '3', '2019-09-24', '67', '1001345', '2019-11-02 13:59:28', 'admin', '2019-11-02 13:59:28', 'admin');
INSERT INTO `t_invest_profit` VALUES ('14', '3', '2019-09-25', '67', '1001412', '2019-11-02 13:59:41', 'admin', '2019-11-02 13:59:41', 'admin');
INSERT INTO `t_invest_profit` VALUES ('15', '3', '2019-09-26', '67', '1001479', '2019-11-02 14:00:06', 'admin', '2019-11-02 14:00:06', 'admin');
INSERT INTO `t_invest_profit` VALUES ('16', '3', '2019-09-27', '66', '1001545', '2019-11-02 14:00:23', 'admin', '2019-11-02 14:00:23', 'admin');
INSERT INTO `t_invest_profit` VALUES ('17', '3', '2019-09-30', '198', '1001743', '2019-11-02 14:00:40', 'admin', '2019-11-02 14:00:40', 'admin');
INSERT INTO `t_invest_profit` VALUES ('18', '3', '2019-10-08', '536', '1002279', '2019-11-02 14:01:22', 'admin', '2019-11-02 14:01:22', 'admin');
INSERT INTO `t_invest_profit` VALUES ('19', '3', '2019-10-09', '67', '1002346', '2019-11-02 14:02:10', 'admin', '2019-11-02 14:02:10', 'admin');
INSERT INTO `t_invest_profit` VALUES ('20', '3', '2019-10-10', '66', '1002412', '2019-11-02 14:02:18', 'admin', '2019-11-02 14:02:18', 'admin');
INSERT INTO `t_invest_profit` VALUES ('21', '3', '2019-10-11', '67', '1002479', '2019-11-02 14:02:33', 'admin', '2019-11-02 14:02:33', 'admin');
INSERT INTO `t_invest_profit` VALUES ('22', '3', '2019-10-14', '198', '1002677', '2019-11-02 14:03:11', 'admin', '2019-11-02 14:03:11', 'admin');
INSERT INTO `t_invest_profit` VALUES ('23', '3', '2019-10-15', '66', '1002743', '2019-11-02 14:03:22', 'admin', '2019-11-02 14:03:22', 'admin');
INSERT INTO `t_invest_profit` VALUES ('24', '3', '2019-10-16', '66', '1002809', '2019-11-02 14:03:28', 'admin', '2019-11-02 14:03:28', 'admin');
INSERT INTO `t_invest_profit` VALUES ('25', '3', '2019-10-17', '66', '1002875', '2019-11-02 14:03:33', 'admin', '2019-11-02 14:03:33', 'admin');
INSERT INTO `t_invest_profit` VALUES ('26', '3', '2019-10-18', '66', '1002941', '2019-11-02 14:03:39', 'admin', '2019-11-02 14:03:39', 'admin');
INSERT INTO `t_invest_profit` VALUES ('27', '3', '2019-10-21', '198', '1003139', '2019-11-02 14:03:51', 'admin', '2019-11-02 14:03:51', 'admin');
INSERT INTO `t_invest_profit` VALUES ('28', '3', '2019-10-22', '66', '1003205', '2019-11-02 14:04:01', 'admin', '2019-11-02 14:04:01', 'admin');
INSERT INTO `t_invest_profit` VALUES ('29', '3', '2019-10-23', '71', '1003276', '2019-11-02 14:04:11', 'admin', '2019-11-02 14:04:11', 'admin');
INSERT INTO `t_invest_profit` VALUES ('30', '3', '2019-10-24', '67', '1003343', '2019-11-02 14:04:20', 'admin', '2019-11-02 14:04:20', 'admin');
INSERT INTO `t_invest_profit` VALUES ('31', '3', '2019-10-25', '68', '1003411', '2019-11-02 14:04:32', 'admin', '2019-11-02 14:04:32', 'admin');
INSERT INTO `t_invest_profit` VALUES ('32', '3', '2019-10-28', '201', '1003612', '2019-11-02 14:04:43', 'admin', '2019-11-02 14:04:43', 'admin');
INSERT INTO `t_invest_profit` VALUES ('33', '3', '2019-10-29', '67', '1003679', '2019-11-02 14:04:54', 'admin', '2019-11-02 14:04:54', 'admin');
INSERT INTO `t_invest_profit` VALUES ('34', '3', '2019-10-30', '69', '1003748', '2019-11-02 14:05:04', 'admin', '2019-11-02 14:05:04', 'admin');
INSERT INTO `t_invest_profit` VALUES ('35', '3', '2019-10-31', '68', '1003816', '2019-11-02 14:05:13', 'admin', '2019-11-02 14:05:13', 'admin');
INSERT INTO `t_invest_profit` VALUES ('36', '3', '2019-11-01', '68', '1003884', '2019-11-02 14:05:27', 'admin', '2019-11-02 14:05:27', 'admin');
INSERT INTO `t_invest_profit` VALUES ('37', '2', '2019-10-22', '150', '1480150', '2019-11-02 14:14:35', 'admin', '2019-11-02 14:14:35', 'admin');
INSERT INTO `t_invest_profit` VALUES ('38', '2', '2019-10-23', '149', '1480299', '2019-11-02 14:14:45', 'admin', '2019-11-02 14:14:45', 'admin');
INSERT INTO `t_invest_profit` VALUES ('39', '2', '2019-10-24', '171', '1480470', '2019-11-02 14:14:56', 'admin', '2019-11-02 14:14:56', 'admin');
INSERT INTO `t_invest_profit` VALUES ('40', '2', '2019-10-25', '430', '1480900', '2019-11-02 14:15:08', 'admin', '2019-11-02 14:15:08', 'admin');
INSERT INTO `t_invest_profit` VALUES ('41', '2', '2019-10-28', '175', '1481075', '2019-11-02 14:15:24', 'admin', '2019-11-02 14:15:24', 'admin');
INSERT INTO `t_invest_profit` VALUES ('42', '2', '2019-10-29', '150', '1481225', '2019-11-02 14:15:32', 'admin', '2019-11-02 14:15:32', 'admin');
INSERT INTO `t_invest_profit` VALUES ('43', '2', '2019-10-30', '149', '1481374', '2019-11-02 14:15:44', 'admin', '2019-11-02 14:15:44', 'admin');
INSERT INTO `t_invest_profit` VALUES ('44', '2', '2019-10-31', '152', '1481526', '2019-11-02 14:15:54', 'admin', '2019-11-02 14:15:54', 'admin');
INSERT INTO `t_invest_profit` VALUES ('45', '1', '2019-10-15', '13', '100013', '2019-11-02 14:16:41', 'admin', '2019-11-02 14:16:41', 'admin');
INSERT INTO `t_invest_profit` VALUES ('46', '1', '2019-10-16', '8', '100021', '2019-11-02 14:16:53', 'admin', '2019-11-02 14:16:53', 'admin');
INSERT INTO `t_invest_profit` VALUES ('47', '1', '2019-10-17', '8', '100029', '2019-11-02 14:16:56', 'admin', '2019-11-02 14:16:56', 'admin');
INSERT INTO `t_invest_profit` VALUES ('48', '1', '2019-10-18', '24', '100053', '2019-11-02 14:17:07', 'admin', '2019-11-02 14:17:07', 'admin');
INSERT INTO `t_invest_profit` VALUES ('49', '1', '2019-10-21', '10', '100063', '2019-11-02 14:17:18', 'admin', '2019-11-02 14:17:18', 'admin');
INSERT INTO `t_invest_profit` VALUES ('50', '1', '2019-10-22', '12', '100075', '2019-11-02 14:17:26', 'admin', '2019-11-02 14:17:26', 'admin');
INSERT INTO `t_invest_profit` VALUES ('51', '1', '2019-10-23', '8', '100083', '2019-11-02 14:17:41', 'admin', '2019-11-02 14:17:41', 'admin');
INSERT INTO `t_invest_profit` VALUES ('52', '1', '2019-10-24', '8', '100091', '2019-11-02 14:17:45', 'admin', '2019-11-02 14:17:45', 'admin');
INSERT INTO `t_invest_profit` VALUES ('53', '1', '2019-10-25', '25', '100116', '2019-11-02 14:17:55', 'admin', '2019-11-02 14:17:55', 'admin');
INSERT INTO `t_invest_profit` VALUES ('54', '1', '2019-10-28', '10', '100126', '2019-11-02 14:18:03', 'admin', '2019-11-02 14:18:03', 'admin');
INSERT INTO `t_invest_profit` VALUES ('55', '1', '2019-10-29', '12', '100138', '2019-11-02 14:18:10', 'admin', '2019-11-02 14:18:10', 'admin');
INSERT INTO `t_invest_profit` VALUES ('56', '1', '2019-10-30', '8', '100146', '2019-11-02 14:18:24', 'admin', '2019-11-02 14:18:24', 'admin');
INSERT INTO `t_invest_profit` VALUES ('57', '1', '2019-10-31', '8', '100154', '2019-11-02 14:18:27', 'admin', '2019-11-02 14:18:27', 'admin');
INSERT INTO `t_invest_profit` VALUES ('58', '2', '2019-11-01', '435', '1481961', '2019-11-04 10:19:33', 'admin', '2019-11-04 10:19:33', 'admin');
INSERT INTO `t_invest_profit` VALUES ('59', '1', '2019-11-01', '25', '100179', '2019-11-04 10:20:06', 'admin', '2019-11-04 10:20:06', 'admin');
INSERT INTO `t_invest_profit` VALUES ('60', '1', '2019-11-04', '10', '100189', '2019-11-05 09:15:13', 'admin', '2019-11-05 09:15:13', 'admin');
INSERT INTO `t_invest_profit` VALUES ('61', '2', '2019-11-04', '175', '1482136', '2019-11-05 09:15:24', 'admin', '2019-11-05 09:15:24', 'admin');
INSERT INTO `t_invest_profit` VALUES ('62', '3', '2019-11-04', '207', '1004091', '2019-11-05 09:15:43', 'admin', '2019-11-05 09:15:43', 'admin');
INSERT INTO `t_invest_profit` VALUES ('63', '3', '2019-11-05', '69', '1004160', '2019-11-06 16:15:11', 'admin', '2019-11-06 16:15:11', 'admin');
INSERT INTO `t_invest_profit` VALUES ('64', '2', '2019-11-05', '151', '1482287', '2019-11-06 16:15:55', 'admin', '2019-11-06 16:15:55', 'admin');
INSERT INTO `t_invest_profit` VALUES ('65', '1', '2019-11-05', '11', '100200', '2019-11-06 16:16:04', 'admin', '2019-11-06 16:16:04', 'admin');
INSERT INTO `t_invest_profit` VALUES ('66', '1', '2019-11-06', '9', '100209', '2019-11-09 21:27:59', 'admin', '2019-11-09 21:27:59', 'admin');
INSERT INTO `t_invest_profit` VALUES ('67', '1', '2019-11-07', '9', '100218', '2019-11-09 21:28:06', 'admin', '2019-11-09 21:28:06', 'admin');
INSERT INTO `t_invest_profit` VALUES ('68', '2', '2019-11-06', '151', '1482438', '2019-11-09 21:30:29', 'admin', '2019-11-09 21:30:29', 'admin');
INSERT INTO `t_invest_profit` VALUES ('69', '2', '2019-11-07', '150', '1482588', '2019-11-09 21:30:35', 'admin', '2019-11-09 21:30:35', 'admin');
INSERT INTO `t_invest_profit` VALUES ('70', '3', '2019-11-06', '69', '1004229', '2019-11-09 21:31:37', 'admin', '2019-11-09 21:31:37', 'admin');
INSERT INTO `t_invest_profit` VALUES ('71', '3', '2019-11-07', '55', '1004284', '2019-11-09 21:31:47', 'admin', '2019-11-09 21:31:47', 'admin');
INSERT INTO `t_invest_profit` VALUES ('72', '1', '2019-11-08', '25', '100243', '2019-11-12 12:44:18', 'admin', '2019-11-12 12:44:18', 'admin');
INSERT INTO `t_invest_profit` VALUES ('73', '1', '2019-11-11', '9', '100252', '2019-11-12 12:44:27', 'admin', '2019-11-12 12:44:27', 'admin');
INSERT INTO `t_invest_profit` VALUES ('74', '2', '2019-11-08', '442', '1483030', '2019-11-12 12:45:12', 'admin', '2019-11-12 12:45:12', 'admin');
INSERT INTO `t_invest_profit` VALUES ('75', '2', '2019-11-11', '183', '1483213', '2019-11-12 12:45:25', 'admin', '2019-11-12 12:45:25', 'admin');
INSERT INTO `t_invest_profit` VALUES ('76', '3', '2019-11-08', '69', '1004353', '2019-11-12 12:47:29', 'admin', '2019-11-12 12:47:29', 'admin');
INSERT INTO `t_invest_profit` VALUES ('77', '3', '2019-11-11', '192', '1004545', '2019-11-12 12:47:43', 'admin', '2019-11-12 12:47:43', 'admin');

-- ----------------------------
-- Table structure for t_invest_type
-- ----------------------------
DROP TABLE IF EXISTS `t_invest_type`;
CREATE TABLE `t_invest_type` (
  `type_id` varchar(1) NOT NULL COMMENT '投资分类id',
  `name` varchar(10) NOT NULL COMMENT '投资类型名称',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_user` varchar(20) NOT NULL COMMENT '创建人',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `update_user` varchar(20) NOT NULL COMMENT '更新人',
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='投资分类表';

-- ----------------------------
-- Records of t_invest_type
-- ----------------------------
INSERT INTO `t_invest_type` VALUES ('1', '理财', '2019-11-02 12:39:31', 'admin', '2019-11-02 12:39:36', 'admin');
INSERT INTO `t_invest_type` VALUES ('2', '基金', '2019-11-02 12:39:56', 'admin', '2019-11-02 12:39:59', 'admin');
INSERT INTO `t_invest_type` VALUES ('3', '黄金', '2019-11-02 12:40:17', 'admin', '2019-11-02 12:40:21', 'admin');
INSERT INTO `t_invest_type` VALUES ('4', '股票', '2019-11-02 12:40:46', 'admin', '2019-11-02 12:40:49', 'admin');

-- ----------------------------
-- Table structure for t_invest_user
-- ----------------------------
DROP TABLE IF EXISTS `t_invest_user`;
CREATE TABLE `t_invest_user` (
  `user_id` varchar(50) NOT NULL COMMENT 'user_id',
  `login_name` varchar(20) NOT NULL COMMENT '登录名',
  `nick_name` varchar(20) DEFAULT NULL COMMENT '昵称',
  `salt` varchar(20) DEFAULT NULL COMMENT '盐值',
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `sex` varchar(1) NOT NULL COMMENT '性别',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_user` varchar(20) NOT NULL COMMENT '创建人',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `update_user` varchar(20) NOT NULL COMMENT '更新人',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='投资用户信息表';

-- ----------------------------
-- Records of t_invest_user
-- ----------------------------
INSERT INTO `t_invest_user` VALUES ('e31e1cd693159fc458cb7ca71f4d4af5', 'ypp', '小杨同学', null, null, '0', null, '2019-11-02 12:31:22', 'admin', '2019-11-02 12:31:26', 'admin');

-- ----------------------------
-- Table structure for t_person
-- ----------------------------
DROP TABLE IF EXISTS `t_person`;
CREATE TABLE `t_person` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `age` int(3) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_name_age` (`name`,`age`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_person
-- ----------------------------
INSERT INTO `t_person` VALUES ('1', '一号', '25');
INSERT INTO `t_person` VALUES ('3', '三号', '24');
INSERT INTO `t_person` VALUES ('2', '二号', '23');
INSERT INTO `t_person` VALUES ('4', '四号', '26');

-- ----------------------------
-- Procedure structure for hi
-- ----------------------------
DROP PROCEDURE IF EXISTS `hi`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `hi`()
    NO SQL
SELECT 'hello'
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for proc_getbyname
-- ----------------------------
DROP PROCEDURE IF EXISTS `proc_getbyname`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `proc_getbyname`(userName VARCHAR(20))
    READS SQL DATA
BEGIN	
	SELECT u.* FROM sys_user u WHERE u.user_name LIKE CONCAT('%',userName,'%');
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for proc_while
-- ----------------------------
DROP PROCEDURE IF EXISTS `proc_while`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `proc_while`(IN a INT,b INT)
    NO SQL
BEGIN	
	DECLARE c INT DEFAULT 0;
	WHILE b < 10 DO
		SET a = a + 1;
		SET b = b + a;
		SET c = b * 10;
	END WHILE;
	SELECT a ;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for pr_demo_in
-- ----------------------------
DROP PROCEDURE IF EXISTS `pr_demo_in`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pr_demo_in`(in id int)
    NO SQL
begin
    if (id is not null)then set id = id + 1;
    end if;
    select id as output_id;
end
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for pr_demo_inout
-- ----------------------------
DROP PROCEDURE IF EXISTS `pr_demo_inout`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pr_demo_inout`(inout id int)
    NO SQL
begin
    if (id is not null)then set id = id + 1;
    end if;
    select id as output_id;
end
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for pr_demo_out
-- ----------------------------
DROP PROCEDURE IF EXISTS `pr_demo_out`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pr_demo_out`(out id int)
    NO SQL
begin
    if (id is not null)then set id = id + 1;
    end if;
    select id as output_id;
end
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for sumab
-- ----------------------------
DROP PROCEDURE IF EXISTS `sumab`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sumab`(a int,b int)
    NO SQL
BEGIN
	DECLARE c int;
	if a is null THEN SET a = 0;
	END IF;
	if b is NULL THEN SET b = 0;
	END IF;
	SET c = a + b;
	SELECT c AS total;
END
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `tri_username`;
DELIMITER ;;
CREATE TRIGGER `tri_username` AFTER UPDATE ON `sys_user` FOR EACH ROW select 'something have been changed' into @ee
;;
DELIMITER ;
