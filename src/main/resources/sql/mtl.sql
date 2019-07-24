/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50522
Source Host           : localhost:3306
Source Database       : mtl

Target Server Type    : MYSQL
Target Server Version : 50522
File Encoding         : 65001

Date: 2019-07-24 15:56:36
*/

SET FOREIGN_KEY_CHECKS=0;

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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '管理员', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '1');
INSERT INTO `sys_user` VALUES ('2', '超级管理员', 'superadmin', 'e10adc3949ba59abbe56e057f20f883e', '1');
INSERT INTO `sys_user` VALUES ('5', '企业1', 'qiye1', 'e10adc3949ba59abbe56e057f20f883e', '1');
INSERT INTO `sys_user` VALUES ('7', '测试改', 'ceshi11', 'e10adc3949ba59abbe56e057f20f883e', '0');

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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='健康指数表';

-- ----------------------------
-- Records of t_healthy
-- ----------------------------
INSERT INTO `t_healthy` VALUES ('3', '3.000', '4', '1', '2019-07-20 11:20:45');
INSERT INTO `t_healthy` VALUES ('4', '2.200', '4', '1', '2019-07-24 11:20:45');
INSERT INTO `t_healthy` VALUES ('5', '6.000', '4', '1', '2019-07-22 11:20:45');
INSERT INTO `t_healthy` VALUES ('6', '3.000', '4', '5', '2019-07-24 11:20:45');
INSERT INTO `t_healthy` VALUES ('7', '3.000', '4', '5', '2019-07-24 11:20:45');
INSERT INTO `t_healthy` VALUES ('8', '3.000', '4', '5', '2019-07-24 11:20:45');

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
