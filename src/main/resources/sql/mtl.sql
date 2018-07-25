/*
Navicat MySQL Data Transfer

Source Server         : zs
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : mtl

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-07-25 14:02:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t-person
-- ----------------------------
DROP TABLE IF EXISTS `t-person`;
CREATE TABLE `t-person` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `age` int(3) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t-person
-- ----------------------------
INSERT INTO `t-person` VALUES ('1', '一号', '25');
INSERT INTO `t-person` VALUES ('2', '二号', '26');
