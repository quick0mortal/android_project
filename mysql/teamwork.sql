/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50711
Source Host           : localhost:3306
Source Database       : teamwork

Target Server Type    : MYSQL
Target Server Version : 50711
File Encoding         : 65001

Date: 2016-11-16 14:00:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `table_from_message_to`
-- ----------------------------
DROP TABLE IF EXISTS `table_from_message_to`;
CREATE TABLE `table_from_message_to` (
  `msgId` int(11) NOT NULL AUTO_INCREMENT,
  `form` text,
  `content` text,
  `to` text,
  PRIMARY KEY (`msgId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of table_from_message_to
-- ----------------------------
INSERT INTO `table_from_message_to` VALUES ('1', 'person1', '抓紧时间做项目', 'person2');
INSERT INTO `table_from_message_to` VALUES ('2', 'person2', '好的', 'person1');
INSERT INTO `table_from_message_to` VALUES ('3', 'person4', '明天开会讨论', 'person5');
INSERT INTO `table_from_message_to` VALUES ('4', 'teacher', '抓紧时间，记得交项目进程报告', 'person1');

-- ----------------------------
-- Table structure for `table_informations`
-- ----------------------------
DROP TABLE IF EXISTS `table_informations`;
CREATE TABLE `table_informations` (
  `infoId` int(11) NOT NULL AUTO_INCREMENT,
  `title` text,
  `informationMessage` text,
  PRIMARY KEY (`infoId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of table_informations
-- ----------------------------
INSERT INTO `table_informations` VALUES ('1', '大创中期结题', '11月16日下午4点前提交结题材料到江安二基础教学楼B402');
INSERT INTO `table_informations` VALUES ('2', '大创十月份报账', '10月22日下午4点前将报账材料交到二基楼B402');
INSERT INTO `table_informations` VALUES ('3', '大创中期检查', '6月17日下午4点前提交到江安二基础教学楼B402');

-- ----------------------------
-- Table structure for `table_project_info`
-- ----------------------------
DROP TABLE IF EXISTS `table_project_info`;
CREATE TABLE `table_project_info` (
  `teamId` int(11) NOT NULL AUTO_INCREMENT,
  `projectName` text,
  `name1` text,
  `name2` text,
  `name3` text,
  `name4` text,
  `comment` text,
  PRIMARY KEY (`teamId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of table_project_info
-- ----------------------------
INSERT INTO `table_project_info` VALUES ('1', 'project1', 'person1', 'person2', 'person3', 'teacher', '继续加油');
INSERT INTO `table_project_info` VALUES ('2', 'project2', 'person4', 'person5', 'person6', 'teacher', '请尽快开始');

-- ----------------------------
-- Table structure for `table_requirement_infos`
-- ----------------------------
DROP TABLE IF EXISTS `table_requirement_infos`;
CREATE TABLE `table_requirement_infos` (
  `messageId` int(11) NOT NULL AUTO_INCREMENT,
  `username` text,
  `requirementMessage` text,
  PRIMARY KEY (`messageId`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of table_requirement_infos
-- ----------------------------
INSERT INTO `table_requirement_infos` VALUES ('1', 'qwer', '需要一名软件学院的同学组队');
INSERT INTO `table_requirement_infos` VALUES ('2', 'person1', '想要组队一起报大创项目');
INSERT INTO `table_requirement_infos` VALUES ('14', 'person2', '我也要组队');
INSERT INTO `table_requirement_infos` VALUES ('15', 'person4', '我要组一个队伍');

-- ----------------------------
-- Table structure for `table_task_info`
-- ----------------------------
DROP TABLE IF EXISTS `table_task_info`;
CREATE TABLE `table_task_info` (
  `taskId` int(11) NOT NULL AUTO_INCREMENT,
  `projectName` text,
  `personName` text,
  `taskContent` text,
  `deadline` text,
  `isOK` text,
  PRIMARY KEY (`taskId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of table_task_info
-- ----------------------------
INSERT INTO `table_task_info` VALUES ('1', 'project1', 'person1', '界面设计', '2016/7/9', '1');
INSERT INTO `table_task_info` VALUES ('2', 'project1', 'person1', '写文档', '2016/11/1', null);
INSERT INTO `table_task_info` VALUES ('3', 'project1', 'person2', '搭建服务器', '2016/9/10', '1');
INSERT INTO `table_task_info` VALUES ('4', 'project1', 'person2', '建立数据库', '2016/10/10', '1');
INSERT INTO `table_task_info` VALUES ('5', 'project1', 'person3', '客户端设计', '2016/8/10', null);
INSERT INTO `table_task_info` VALUES ('6', 'project1', 'person3', '客户端代码编写', '2016/9/15', null);

-- ----------------------------
-- Table structure for `table_user_password`
-- ----------------------------
DROP TABLE IF EXISTS `table_user_password`;
CREATE TABLE `table_user_password` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `userAccount` text,
  `userPassword` text,
  `identity` text,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of table_user_password
-- ----------------------------
INSERT INTO `table_user_password` VALUES ('1', 'person1', 'q', null);
INSERT INTO `table_user_password` VALUES ('2', 'person2', 'w', null);
INSERT INTO `table_user_password` VALUES ('3', 'person3', 'e', null);
INSERT INTO `table_user_password` VALUES ('4', 'person4', 'r', null);
INSERT INTO `table_user_password` VALUES ('5', 'person5', 't', null);
INSERT INTO `table_user_password` VALUES ('6', 'person6', 'y', null);
INSERT INTO `table_user_password` VALUES ('9', 'teacher', 'a', 'teacher');
