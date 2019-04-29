/*
Navicat MySQL Data Transfer

Source Server         : MySQL-22
Source Server Version : 50722
Source Host           : localhost:3322
Source Database       : db_music

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2018-05-15 13:56:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL,
  `nameZh` varchar(64) DEFAULT NULL COMMENT '角色名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'ROLE_manager', '部门经理');
INSERT INTO `role` VALUES ('2', 'ROLE_personnel', '人事专员');
INSERT INTO `role` VALUES ('3', 'ROLE_recruiter', '招聘主管');
INSERT INTO `role` VALUES ('4', 'ROLE_train', '培训主管');
INSERT INTO `role` VALUES ('5', 'ROLE_performance', '薪酬绩效主管');
INSERT INTO `role` VALUES ('6', 'ROLE_admin', '系统管理员');
INSERT INTO `role` VALUES ('13', 'ROLE_test2', '测试角色2');
INSERT INTO `role` VALUES ('14', 'ROLE_test1', '测试角色1');

-- ----------------------------
-- Table structure for menu_role
-- ----------------------------
DROP TABLE IF EXISTS `menu_role`;
CREATE TABLE `menu_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mid` int(11) DEFAULT NULL,
  `rid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `mid` (`mid`),
  KEY `rid` (`rid`),
  CONSTRAINT `menu_role_ibfk_1` FOREIGN KEY (`mid`) REFERENCES `menu` (`id`),
  CONSTRAINT `menu_role_ibfk_2` FOREIGN KEY (`rid`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=278 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu_role
-- ----------------------------
INSERT INTO `menu_role` VALUES ('161', '7', '3');
INSERT INTO `menu_role` VALUES ('162', '7', '6');
INSERT INTO `menu_role` VALUES ('163', '9', '6');
INSERT INTO `menu_role` VALUES ('164', '10', '6');
INSERT INTO `menu_role` VALUES ('165', '11', '6');



-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(64) DEFAULT NULL,
  `path` varchar(64) DEFAULT NULL,
  `component` varchar(64) DEFAULT NULL,
  `name` varchar(64) DEFAULT NULL,
  `iconCls` varchar(64) DEFAULT NULL,
  `keepAlive` tinyint(1) DEFAULT NULL,
  `requireAuth` tinyint(1) DEFAULT NULL,
  `parentId` int(11) DEFAULT NULL,
  `enabled` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `parentId` (`parentId`),
  CONSTRAINT `menu_ibfk_1` FOREIGN KEY (`parentId`) REFERENCES `menu` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', '/', null, null, '所有', null, null, null, null, '1');
INSERT INTO `menu` VALUES ('2', '/', '/home', 'Home', '员工资料', 'fa fa-user-circle-o', null, '1', '1', '1');
INSERT INTO `menu` VALUES ('3', '/', '/home', 'Home', '人事管理', 'fa fa-address-card-o', null, '1', '1', '1');
INSERT INTO `menu` VALUES ('4', '/', '/home', 'Home', '薪资管理', 'fa fa-money', null, '1', '1', '1');
INSERT INTO `menu` VALUES ('5', '/', '/home', 'Home', '统计管理', 'fa fa-bar-chart', null, '1', '1', '1');
INSERT INTO `menu` VALUES ('6', '/', '/home', 'Home', '系统管理', 'fa fa-windows', null, '1', '1', '1');
INSERT INTO `menu` VALUES ('7', '/employee/basic/**', '/emp/basic', 'EmpBasic', '基本资料', null, null, '1', '2', '1');
INSERT INTO `menu` VALUES ('8', '/employee/advanced/**', '/emp/adv', 'EmpAdv', '高级资料', null, null, '1', '2', '0');
INSERT INTO `menu` VALUES ('9', '/personnel/emp/**', '/per/emp', 'PerEmp', '员工资料', null, null, '1', '3', '0');
INSERT INTO `menu` VALUES ('10', '/personnel/ec/**', '/per/ec', 'PerEc', '员工奖惩', null, null, '1', '3', '1');
INSERT INTO `menu` VALUES ('11', '/personnel/train/**', '/per/train', 'PerTrain', '员工培训', null, null, '1', '3', '1');
