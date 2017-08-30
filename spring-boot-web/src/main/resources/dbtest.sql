/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50713
Source Host           : localhost:3306
Source Database       : dbtest

Target Server Type    : MYSQL
Target Server Version : 50713
File Encoding         : 65001

Date: 2017-08-30 09:35:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tdepartment`
-- ----------------------------
DROP TABLE IF EXISTS `tdepartment`;
CREATE TABLE `tdepartment` (
  `cid` char(32) NOT NULL,
  `cdepartmentname` varchar(50) DEFAULT NULL COMMENT '部门名称',
  `bdepartmentenable` int(11) DEFAULT '1',
  `tdepartmentaddtime` datetime DEFAULT NULL,
  `tdepartmentupdatetime` datetime DEFAULT NULL,
  `mdepartmentremark` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tdepartment
-- ----------------------------
INSERT INTO `tdepartment` VALUES ('1', 'aa', '1', '2017-08-18 15:34:21', '2017-08-18 15:34:24', 'a');

-- ----------------------------
-- Table structure for `tuser`
-- ----------------------------
DROP TABLE IF EXISTS `tuser`;
CREATE TABLE `tuser` (
  `cid` char(32) NOT NULL,
  `cusername` varchar(50) DEFAULT NULL COMMENT '用户名',
  `iuserage` int(11) DEFAULT NULL COMMENT '年龄',
  `busercommunist` int(11) DEFAULT '0' COMMENT '是否党员',
  `fuserscore` decimal(10,2) DEFAULT NULL COMMENT '分数测试',
  `suersex` varchar(50) DEFAULT NULL COMMENT '性别',
  `ruserintroduce` varchar(1024) DEFAULT NULL COMMENT '自我介绍',
  `uuserphoto` text COMMENT '用户头像',
  `duserbirthday` date DEFAULT NULL COMMENT '生日',
  `buserenable` int(11) DEFAULT '1',
  `tuseraddtime` datetime DEFAULT NULL,
  `tuserupdatetime` datetime DEFAULT NULL,
  `muserremark` varchar(1024) DEFAULT NULL,
  `cuserdepartmentid` char(32) DEFAULT NULL COMMENT '部门id',
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tuser
-- ----------------------------
INSERT INTO `tuser` VALUES ('1', '1收', '0', '0', '0.00', '', '', '', null, '1', null, null, '', null);
INSERT INTO `tuser` VALUES ('12', '1', null, '0', null, null, null, null, null, '1', null, null, null, null);
INSERT INTO `tuser` VALUES ('13', '1', null, '0', null, null, null, null, null, '1', null, null, null, null);
INSERT INTO `tuser` VALUES ('14', '1', null, '0', null, null, null, null, null, '1', null, null, null, null);
INSERT INTO `tuser` VALUES ('15', '12', null, '0', null, null, null, null, null, '1', null, null, null, null);
INSERT INTO `tuser` VALUES ('16', '13', null, '0', null, null, null, null, null, '1', null, null, null, null);
INSERT INTO `tuser` VALUES ('1850f2c05e8c48b9a6e646f71629aed8', '我们', null, '0', null, null, null, null, null, '1', '2017-08-23 16:03:56', null, null, null);
INSERT INTO `tuser` VALUES ('2', '2', '0', '0', '0.00', '', '', '', null, '1', null, null, '', null);
INSERT INTO `tuser` VALUES ('3', '1', null, '0', null, null, null, null, null, '1', null, null, null, null);
INSERT INTO `tuser` VALUES ('4', '1', null, '0', null, null, null, null, null, '1', null, null, null, null);
INSERT INTO `tuser` VALUES ('5b4e75cd715b4cf2bef10c924b24dbd1', '噢噢噢噢', null, '0', null, null, null, null, null, '1', '2017-08-23 17:54:13', '2017-08-23 16:03:59', null, null);
INSERT INTO `tuser` VALUES ('6', '1', null, '0', null, null, null, null, null, '1', null, null, null, null);
INSERT INTO `tuser` VALUES ('7', '1', null, '0', null, null, null, null, null, '1', null, null, null, null);
INSERT INTO `tuser` VALUES ('8', '1', null, '0', null, null, null, null, null, '1', null, null, null, null);
INSERT INTO `tuser` VALUES ('9', '1', null, '0', null, null, null, null, null, '1', null, null, null, null);
INSERT INTO `tuser` VALUES ('a03a969224644ff2af3e9f751abc502b', '6676', null, '0', null, null, null, null, null, '1', '2017-08-23 17:26:32', '2017-08-23 16:03:59', null, null);
INSERT INTO `tuser` VALUES ('f9fda18e6b4c469399de006fae2e9dff', '9999', null, '0', null, null, null, null, null, '1', '2017-08-23 16:04:36', null, null, null);
