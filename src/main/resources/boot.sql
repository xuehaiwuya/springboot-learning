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

-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `uid` int(11) NOT NULL AUTO_INCREMENT COMMENT  '主键id',
  `name` varchar(50) NOT NULL COMMENT               '用户名',
  `pwd` varchar(100) DEFAULT NULL COMMENT            '密码',
  `phone` varchar(20) NOT NULL COMMENT              '手机',
  `photo` varchar(100) DEFAULT NULL COMMENT         '头像',
  `email` varchar(50) DEFAULT NULL COMMENT          '邮箱',
  `status` int(4) DEFAULT '1' COMMENT               '用户状态(0:禁止登陆，1:允许登陆，2:审核中)',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL COMMENT           '角色名称',
  `cname` varchar(64) DEFAULT NULL COMMENT          '角色中文名称',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL COMMENT                '用户id',
  `rid` int(11) DEFAULT NULL COMMENT                '角色id',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `uid` (`uid`),
  KEY `rid` (`rid`),
  CONSTRAINT `user_role_on_menu` FOREIGN KEY (`uid`) REFERENCES `tb_user` (`uid`),
  CONSTRAINT `user_role_on_role` FOREIGN KEY (`rid`) REFERENCES `tb_role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_menu
-- ----------------------------
DROP TABLE IF EXISTS `tb_menu`;
CREATE TABLE `tb_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(64) DEFAULT NULL COMMENT            '请求路径规则',
  `path` varchar(64) DEFAULT NULL COMMENT           '路由path',
  `component` varchar(64) DEFAULT NULL COMMENT      '组件名称',
  `name` varchar(64) DEFAULT NULL COMMENT           '组件名',
  `icon` varchar(64) DEFAULT NULL COMMENT           '菜单图标',
  `keep_alive` int(4) DEFAULT NULL COMMENT          '菜单切换时是否保活',
  `require_auth` int(4) DEFAULT NULL COMMENT        '是否登陆后才能访问',
  `parent_id` int(11) DEFAULT NULL COMMENT          '父菜单id',
  `status` int(4) DEFAULT '1' COMMENT               '是否可用(1:可用，0:不可用)',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `parent_id` (`parent_id`),
  CONSTRAINT `menu_to_menu` FOREIGN KEY (`parent_id`) REFERENCES `tb_menu` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for menu_role
-- ----------------------------
DROP TABLE IF EXISTS `menu_role`;
CREATE TABLE `menu_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mid` int(11) DEFAULT NULL COMMENT                '菜单id',
  `rid` int(11) DEFAULT NULL COMMENT                '角色id',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `mid` (`mid`),
  KEY `rid` (`rid`),
  CONSTRAINT `menu_role_on_menu` FOREIGN KEY (`mid`) REFERENCES `tb_menu` (`id`),
  CONSTRAINT `menu_role_on_role` FOREIGN KEY (`rid`) REFERENCES `tb_role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_role
-- ----------------------------
INSERT INTO `tb_role` VALUES ('1',  'ROLE_manager',     '部门经理');
INSERT INTO `tb_role` VALUES ('2',  'ROLE_personnel',   '人事专员');
INSERT INTO `tb_role` VALUES ('3',  'ROLE_recruiter',   '招聘主管');
INSERT INTO `tb_role` VALUES ('4',  'ROLE_train',       '培训主管');
INSERT INTO `tb_role` VALUES ('5',  'ROLE_performance', '薪酬绩效主管');
INSERT INTO `tb_role` VALUES ('6',  'ROLE_admin',       '系统管理员');
INSERT INTO `tb_role` VALUES ('7',  'ROLE_test2',       '测试角色2');
INSERT INTO `tb_role` VALUES ('8',  'ROLE_test1',       '测试角色1');

-- ----------------------------
-- Records of menu_role
-- ----------------------------
INSERT INTO `menu_role` VALUES ('1', '7',  '3');
INSERT INTO `menu_role` VALUES ('2', '7',  '6');
INSERT INTO `menu_role` VALUES ('3', '9',  '6');
INSERT INTO `menu_role` VALUES ('4', '10', '6');
INSERT INTO `menu_role` VALUES ('5', '11', '6');

-- ----------------------------
-- Records of tb_menu
-- ----------------------------
INSERT INTO `tb_menu` VALUES ('1',  '/',                     null,         null,       '所有',       null,                   null, null, null, '1','2019-05-05 10:10:10','2019-05-05 10:10:10');
INSERT INTO `tb_menu` VALUES ('2',  '/',                     '/home',      'Home',     '员工资料',   'fa fa-user-circle-o',  null, '1',  '1',  '1');
INSERT INTO `tb_menu` VALUES ('3',  '/',                     '/home',      'Home',     '人事管理',   'fa fa-address-card-o', null, '1',  '1',  '1');
INSERT INTO `tb_menu` VALUES ('4',  '/',                     '/home',      'Home',     '薪资管理',   'fa fa-money',          null, '1',  '1',  '1');
INSERT INTO `tb_menu` VALUES ('5',  '/',                     '/home',      'Home',     '统计管理',   'fa fa-bar-chart',      null, '1',  '1',  '1');
INSERT INTO `tb_menu` VALUES ('6',  '/',                     '/home',      'Home',     '系统管理',   'fa fa-windows',        null, '1',  '1',  '1');
INSERT INTO `tb_menu` VALUES ('7',  '/employee/basic/**',    '/emp/basic', 'EmpBasic', '基本资料',   null,                   null, '1',  '2',  '1');
INSERT INTO `tb_menu` VALUES ('8',  '/employee/advanced/**', '/emp/adv',   'EmpAdv',   '高级资料',   null,                   null, '1',  '2',  '0');
INSERT INTO `tb_menu` VALUES ('9',  '/personnel/emp/**',     '/per/emp',   'PerEmp',   '员工资料',   null,                   null, '1',  '3',  '0');
INSERT INTO `tb_menu` VALUES ('10', '/personnel/ec/**',      '/per/ec',    'PerEc',    '员工奖惩',   null,                   null, '1',  '3',  '1');
INSERT INTO `tb_menu` VALUES ('11', '/personnel/train/**',   '/per/train', 'PerTrain', '员工培训',   null,                   null, '1',  '3',  '1');
