/*
MySQL Data Transfer
Source Host: localhost
Source Database: newchats
Target Host: localhost
Target Database: newchats
Date: 2016/5/17 9:18:43
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for tr_fmwk_menu_function
-- ----------------------------
DROP TABLE IF EXISTS `tr_fmwk_menu_function`;
CREATE TABLE `tr_fmwk_menu_function` (
  `ID` int(255) NOT NULL AUTO_INCREMENT,
  `menu_id` int(255) NOT NULL,
  `function_id` int(255) NOT NULL,
  `CREATE_BY` int(255) NOT NULL,
  `CREATE_DATE` datetime NOT NULL,
  `LAST_UPDATE_BY` int(255) DEFAULT NULL,
  `LAST_UPDATE_DATE` datetime DEFAULT NULL,
  `DELETE_FLAG` int(255) DEFAULT '0',
  PRIMARY KEY (`ID`),
  KEY `ID` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tr_fmwk_role_funcgrp
-- ----------------------------
DROP TABLE IF EXISTS `tr_fmwk_role_funcgrp`;
CREATE TABLE `tr_fmwk_role_funcgrp` (
  `ID` int(255) NOT NULL AUTO_INCREMENT,
  `role_id` int(255) NOT NULL,
  `function_group_id` int(255) NOT NULL,
  `CREATE_BY` int(255) NOT NULL,
  `CREATE_DATE` datetime NOT NULL,
  `LAST_UPDATE_BY` int(255) DEFAULT NULL,
  `LAST_UPDATE_DATE` datetime DEFAULT NULL,
  `DELETE_FLAG` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `ID` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tr_fmwk_role_function
-- ----------------------------
DROP TABLE IF EXISTS `tr_fmwk_role_function`;
CREATE TABLE `tr_fmwk_role_function` (
  `ID` int(255) NOT NULL AUTO_INCREMENT,
  `role_id` int(255) NOT NULL,
  `function_id` int(255) NOT NULL,
  `CREATE_DATE` datetime NOT NULL,
  `CREATE_BY` int(255) NOT NULL,
  `LAST_UPDATE_DATE` datetime DEFAULT NULL,
  `LAST_UPDATE_BY` int(255) DEFAULT NULL,
  `DELETE_FLAG` int(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `ID` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=400 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tr_fmwk_user_role
-- ----------------------------
DROP TABLE IF EXISTS `tr_fmwk_user_role`;
CREATE TABLE `tr_fmwk_user_role` (
  `ID` int(255) NOT NULL AUTO_INCREMENT,
  `user_id` int(255) DEFAULT NULL,
  `role_id` int(255) DEFAULT NULL,
  `CREATE_BY` int(255) DEFAULT NULL,
  `CREATE_DATE` datetime DEFAULT NULL,
  `LAST_UPDATE_BY` int(255) DEFAULT NULL,
  `LAST_UPDATE_DATE` datetime DEFAULT NULL,
  `DELETE_FLAG` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `ID` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tr_fmwk_usrgrp_role
-- ----------------------------
DROP TABLE IF EXISTS `tr_fmwk_usrgrp_role`;
CREATE TABLE `tr_fmwk_usrgrp_role` (
  `ID` int(255) NOT NULL AUTO_INCREMENT,
  `user_group_id` int(255) NOT NULL,
  `role_id` int(255) NOT NULL,
  `CREATE_BY` int(255) NOT NULL,
  `CREATE_DATE` datetime NOT NULL,
  `LAST_UPDATE_BY` int(255) DEFAULT NULL,
  `LAST_UPDATE_DATE` datetime DEFAULT NULL,
  `DELETE_FLAG` int(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `ID` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tr_group_room
-- ----------------------------
DROP TABLE IF EXISTS `tr_group_room`;
CREATE TABLE `tr_group_room` (
  `room_id` int(11) DEFAULT NULL,
  `group_id` int(11) DEFAULT NULL,
  `ID` int(11) DEFAULT NULL,
  `CREATE_DATE` datetime DEFAULT NULL,
  `CREATE_BY` varchar(255) DEFAULT NULL,
  `LAST_UPDATE_DATE` datetime DEFAULT NULL,
  `LAST_UPDATE_BY` varchar(255) DEFAULT NULL,
  `DELETE_FLAG` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ts_fmwk_func_group
-- ----------------------------
DROP TABLE IF EXISTS `ts_fmwk_func_group`;
CREATE TABLE `ts_fmwk_func_group` (
  `ID` int(255) NOT NULL AUTO_INCREMENT,
  `function_group_code` varchar(255) NOT NULL,
  `function_group_name` varchar(255) NOT NULL,
  `function_group_desc` varchar(255) NOT NULL,
  `CREATE_BY` int(255) NOT NULL,
  `CREATE_DATE` datetime NOT NULL,
  `LAST_UPDATE_BY` int(255) DEFAULT NULL,
  `LAST_UPDATE_DATE` datetime DEFAULT NULL,
  `DELETE_FLAG` int(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `ID` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ts_fmwk_function
-- ----------------------------
DROP TABLE IF EXISTS `ts_fmwk_function`;
CREATE TABLE `ts_fmwk_function` (
  `ID` int(255) NOT NULL AUTO_INCREMENT,
  `function_code` varchar(255) NOT NULL,
  `function_name` varchar(255) NOT NULL,
  `function_desc` varchar(255) NOT NULL,
  `CREATE_BY` int(255) NOT NULL,
  `CREATE_DATE` datetime NOT NULL,
  `LAST_UPDATE_BY` int(255) DEFAULT NULL,
  `LAST_UPDATE_DATE` datetime DEFAULT NULL,
  `function_group_id` int(255) DEFAULT NULL,
  `DELETE_FLAG` int(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `ID` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ts_fmwk_menu
-- ----------------------------
DROP TABLE IF EXISTS `ts_fmwk_menu`;
CREATE TABLE `ts_fmwk_menu` (
  `ID` int(255) NOT NULL AUTO_INCREMENT,
  `is_leaf` int(255) DEFAULT NULL,
  `menu_code` varchar(255) NOT NULL,
  `menu_level` int(255) NOT NULL,
  `menu_name` varchar(255) NOT NULL,
  `parent_menu_code` varchar(255) NOT NULL,
  `url` varchar(255) NOT NULL,
  `CREATE_BY` int(255) NOT NULL,
  `CREATE_DATE` datetime NOT NULL,
  `LAST_UPDATE_BY` int(255) DEFAULT NULL,
  `LAST_UPDATE_DATE` datetime DEFAULT NULL,
  `DELETE_FLAG` int(255) DEFAULT '0',
  PRIMARY KEY (`ID`),
  KEY `ID` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ts_fmwk_role
-- ----------------------------
DROP TABLE IF EXISTS `ts_fmwk_role`;
CREATE TABLE `ts_fmwk_role` (
  `ID` int(255) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) DEFAULT NULL,
  `role_desc` varchar(255) DEFAULT NULL,
  `role_code` varchar(255) DEFAULT NULL,
  `ROLE_GROUP` int(11) NOT NULL,
  `CREATE_BY` int(255) NOT NULL,
  `CREATE_DATE` datetime DEFAULT NULL,
  `LAST_UPDATE_BY` int(255) DEFAULT NULL,
  `LAST_UPDATE_DATE` datetime DEFAULT NULL,
  `DELETE_FLAG` int(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `ID` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ts_fmwk_user
-- ----------------------------
DROP TABLE IF EXISTS `ts_fmwk_user`;
CREATE TABLE `ts_fmwk_user` (
  `ID` bigint(255) NOT NULL AUTO_INCREMENT,
  `user_code` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `status_code` varchar(11) NOT NULL DEFAULT '2',
  `user_group_id` varchar(11) NOT NULL DEFAULT '1',
  `user_type` varchar(11) NOT NULL,
  `CREATE_DATE` datetime NOT NULL,
  `BUSSINESS_EMAIL` varchar(255) DEFAULT NULL,
  `employer_owner_id` varchar(255) DEFAULT '1',
  `CREATE_BY` int(11) NOT NULL,
  `LAST_UPDATE_BY` int(11) DEFAULT NULL,
  `LAST_UPDATE_DATE` datetime DEFAULT NULL,
  `DELETE_FLAG` int(255) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `ID` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ts_fmwk_user_group
-- ----------------------------
DROP TABLE IF EXISTS `ts_fmwk_user_group`;
CREATE TABLE `ts_fmwk_user_group` (
  `DELETE_FLAG` int(255) DEFAULT NULL,
  `ID` int(255) NOT NULL AUTO_INCREMENT,
  `user_group_code` varchar(255) NOT NULL,
  `user_group_name` varchar(255) NOT NULL,
  `CREATE_BY` int(255) NOT NULL,
  `CREATE_DATE` datetime NOT NULL,
  `LAST_UPDATE_BY` int(255) DEFAULT NULL,
  `LAST_UPDATE_DATE` datetime DEFAULT NULL,
  `parent_group_id` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `ID` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for xd_chats
-- ----------------------------
DROP TABLE IF EXISTS `xd_chats`;
CREATE TABLE `xd_chats` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` text NOT NULL COMMENT '内容',
  `userName` varchar(255) NOT NULL COMMENT '发言人昵称',
  `userId` bigint(20) NOT NULL COMMENT '发言人id',
  `date` datetime NOT NULL COMMENT '发言时间',
  `toUser` varchar(255) DEFAULT NULL COMMENT '接收方名称',
  `valid` int(11) DEFAULT '0' COMMENT '是否审核 0没审核 1 已审核',
  `isRobot` int(1) DEFAULT '0' COMMENT '发言是否是机器人0不是 1是',
  `roomNo` varchar(255) DEFAULT NULL COMMENT '房间号',
  `validUser` varchar(255) DEFAULT NULL COMMENT '审核人',
  `faceImg` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT '0' COMMENT '发言类型 0群聊，1私聊，2置顶，3滚动',
  `DELETE_FLAG` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `userid` (`userId`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for xd_customer
-- ----------------------------
DROP TABLE IF EXISTS `xd_customer`;
CREATE TABLE `xd_customer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `DELETE_FLAG` int(11) DEFAULT NULL COMMENT '0 没删 1删掉',
  `CREATE_BY` bigint(20) DEFAULT NULL,
  `CREATE_DATE` datetime DEFAULT NULL,
  `LAST_UPDATE_BY` bigint(20) DEFAULT NULL,
  `LAST_UPDATE_DATE` datetime DEFAULT NULL,
  `userName` varchar(255) NOT NULL COMMENT '用户名',
  `nickName` varchar(255) NOT NULL COMMENT '昵称',
  `pwd` varchar(255) NOT NULL COMMENT '密码',
  `mobile` varchar(12) DEFAULT NULL COMMENT '手机',
  `qq` varchar(255) DEFAULT NULL,
  `ip` varchar(255) DEFAULT NULL,
  `lastLoginTime` datetime DEFAULT NULL COMMENT '最后登陆时间',
  `userId` bigint(20) DEFAULT NULL COMMENT '所属业务员',
  `faceImg` varchar(255) DEFAULT NULL COMMENT '头像',
  `level` bigint(255) DEFAULT '0' COMMENT '等级',
  `status` varchar(255) DEFAULT 'normal' COMMENT 'normal/block',
  `mail` varchar(255) DEFAULT NULL COMMENT '邮件地址',
  `realName` varchar(255) DEFAULT NULL COMMENT '真名',
  `referer` varchar(512) DEFAULT NULL COMMENT '入口',
  `linksource` varchar(512) DEFAULT NULL COMMENT '来源',
  `roomNo` varchar(255) DEFAULT '1' COMMENT '可登陆房间号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`userName`),
  KEY `mobile` (`mobile`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for xd_customer_type
-- ----------------------------
DROP TABLE IF EXISTS `xd_customer_type`;
CREATE TABLE `xd_customer_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `DELETE_FLAG` int(11) DEFAULT NULL,
  `CREATE_BY` bigint(20) DEFAULT NULL,
  `CREATE_DATE` datetime DEFAULT NULL,
  `LAST_UPDATE_BY` bigint(20) DEFAULT NULL,
  `LAST_UPDATE_DATE` datetime DEFAULT NULL,
  `customer_id` bigint(11) DEFAULT NULL COMMENT '对用用户id',
  `chat_time` bigint(20) DEFAULT NULL COMMENT '聊天间隔单位秒',
  `video_time` bigint(20) DEFAULT NULL COMMENT '总视频时间 单位分钟',
  `used_video_time` bigint(20) DEFAULT NULL COMMENT '已用视频时间 单位分钟',
  `is_chat` int(11) DEFAULT '0' COMMENT '是否禁言 0未禁言，1已禁言',
  `is_scrol` int(11) DEFAULT '0' COMMENT '是否滚动，0不可滚动，1可滚动',
  `is_top` int(11) DEFAULT '0' COMMENT '是否置顶 0不可，1可',
  `is_font` int(11) DEFAULT '0' COMMENT '是否字体 0 不可，1可',
  `is_valid` int(11) DEFAULT '0' COMMENT '是否审核 0 不可，1 可',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for xd_kcb
-- ----------------------------
DROP TABLE IF EXISTS `xd_kcb`;
CREATE TABLE `xd_kcb` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `time` varchar(255) DEFAULT NULL,
  `lesson_mon` varchar(255) DEFAULT NULL,
  `teacher_mon` varchar(255) DEFAULT NULL,
  `lesson_tue` varchar(255) DEFAULT NULL,
  `teacher_tue` varchar(255) DEFAULT NULL,
  `lesson_wed` varchar(255) DEFAULT NULL,
  `teacher_wed` varchar(255) DEFAULT NULL,
  `lesson_thu` varchar(255) DEFAULT NULL,
  `teacher_thu` varchar(255) DEFAULT NULL,
  `lesson_fri` varchar(255) DEFAULT NULL,
  `teacher_fri` varchar(255) DEFAULT NULL,
  `roomId` int(11) DEFAULT NULL,
  `CREATE_DATE` datetime DEFAULT NULL,
  `LAST_UPDATE_DATE` datetime DEFAULT NULL,
  `LAST_UPDATE_BY` int(255) DEFAULT NULL,
  `CREATE_BY` int(255) DEFAULT NULL,
  `DELETE_FLAG` int(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `ID` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Table structure for xd_robot
-- ----------------------------
DROP TABLE IF EXISTS `xd_robot`;
CREATE TABLE `xd_robot` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `DELETE_FLAG` int(11) DEFAULT NULL,
  `CREATE_BY` bigint(20) DEFAULT NULL,
  `CREATE_DATE` datetime DEFAULT NULL,
  `LAST_UPDATE_BY` bigint(20) DEFAULT NULL,
  `LAST_UPDATE_DATE` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL COMMENT '昵称',
  `userId` varchar(255) DEFAULT NULL COMMENT '对应用户',
  `faceImg` varchar(255) DEFAULT NULL COMMENT '头像',
  `level` varchar(255) DEFAULT NULL COMMENT '等级',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for xd_room
-- ----------------------------
DROP TABLE IF EXISTS `xd_room`;
CREATE TABLE `xd_room` (
  `ID` int(11) NOT NULL,
  `roomCode` varchar(255) NOT NULL,
  `roomName` varchar(255) NOT NULL,
  `DELETE_FLAG` varchar(255) DEFAULT NULL,
  `CREATE_DATE` datetime DEFAULT NULL,
  `CREATE_BY` varchar(255) DEFAULT NULL,
  `LAST_UPDATE_DATE` datetime DEFAULT NULL,
  `LAST_UPDATE_BY` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for xd_upload
-- ----------------------------
DROP TABLE IF EXISTS `xd_upload`;
CREATE TABLE `xd_upload` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATE_DATE` datetime DEFAULT NULL,
  `CREATE_BY` bigint(20) DEFAULT NULL,
  `LAST_UPDATE_DATE` datetime DEFAULT NULL,
  `LAST_UPDATE_BY` bigint(20) DEFAULT NULL,
  `DELETE_FLAG` int(11) DEFAULT NULL,
  `src` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `tr_fmwk_menu_function` VALUES ('1', '4', '1', '1', '2013-11-06 08:58:27', '0', '2013-11-06 08:58:27', '0');
INSERT INTO `tr_fmwk_menu_function` VALUES ('2', '5', '2', '1', '2013-11-06 08:59:20', '0', '2013-11-06 08:59:20', '0');
INSERT INTO `tr_fmwk_menu_function` VALUES ('3', '6', '3', '1', '2013-11-06 08:59:48', '0', '2013-11-06 08:59:48', '0');
INSERT INTO `tr_fmwk_menu_function` VALUES ('4', '7', '4', '1', '2013-11-06 09:00:17', '0', '2013-11-06 09:00:17', '0');
INSERT INTO `tr_fmwk_menu_function` VALUES ('12', '18', '12', '1', '2013-11-20 14:20:43', '1', '2013-11-20 14:20:43', '0');
INSERT INTO `tr_fmwk_menu_function` VALUES ('20', '26', '20', '1', '2015-02-14 20:59:46', '0', '2015-02-14 20:59:46', '0');
INSERT INTO `tr_fmwk_menu_function` VALUES ('21', '27', '21', '1', '2015-02-14 21:01:03', '0', '2015-02-14 21:01:03', '0');
INSERT INTO `tr_fmwk_menu_function` VALUES ('22', '28', '22', '1', '2015-03-16 16:12:47', '0', '2015-03-16 16:12:47', '0');
INSERT INTO `tr_fmwk_menu_function` VALUES ('23', '29', '23', '1', '2015-03-17 10:03:08', '0', '2015-03-17 10:03:08', '0');
INSERT INTO `tr_fmwk_menu_function` VALUES ('24', '30', '24', '1', '2015-03-19 16:54:09', '0', '2015-03-19 16:54:09', '0');
INSERT INTO `tr_fmwk_menu_function` VALUES ('25', '31', '25', '1', '2015-03-19 16:55:13', '0', '2015-03-19 16:55:13', '0');
INSERT INTO `tr_fmwk_menu_function` VALUES ('26', '32', '26', '1', '2015-03-19 16:55:57', '0', '2015-03-19 16:55:57', '0');
INSERT INTO `tr_fmwk_menu_function` VALUES ('27', '33', '27', '1', '2015-03-19 16:56:31', '0', '2015-03-19 16:56:31', '0');
INSERT INTO `tr_fmwk_menu_function` VALUES ('28', '34', '28', '1', '2015-03-19 16:59:45', '0', '2015-03-19 16:59:45', '0');
INSERT INTO `tr_fmwk_menu_function` VALUES ('29', '35', '29', '1', '2015-04-13 11:25:26', '0', '2015-04-13 11:25:26', '0');
INSERT INTO `tr_fmwk_menu_function` VALUES ('30', '36', '30', '1', '2015-05-04 17:14:22', '0', '2015-05-04 17:14:22', '0');
INSERT INTO `tr_fmwk_menu_function` VALUES ('31', '37', '31', '1', '2015-06-01 10:33:41', '0', '2015-06-01 10:33:41', '0');
INSERT INTO `tr_fmwk_menu_function` VALUES ('32', '38', '32', '1', '2015-07-02 16:02:11', '0', '2015-07-02 16:02:11', '0');
INSERT INTO `tr_fmwk_menu_function` VALUES ('33', '39', '33', '1', '2015-11-11 09:29:54', '0', '2015-11-11 09:29:54', '0');
INSERT INTO `tr_fmwk_role_function` VALUES ('312', '3', '1', '2015-05-07 09:34:23', '1', '2015-05-07 09:34:23', '1', '0');
INSERT INTO `tr_fmwk_role_function` VALUES ('313', '3', '20', '2015-05-07 09:34:23', '1', '2015-05-07 09:34:23', '1', '0');
INSERT INTO `tr_fmwk_role_function` VALUES ('314', '3', '21', '2015-05-07 09:34:23', '1', '2015-05-07 09:34:23', '1', '0');
INSERT INTO `tr_fmwk_role_function` VALUES ('315', '3', '29', '2015-05-07 09:34:23', '1', '2015-05-07 09:34:23', '1', '0');
INSERT INTO `tr_fmwk_role_function` VALUES ('361', '1', '1', '2015-11-11 09:30:09', '1', '2015-11-11 09:30:09', '1', '0');
INSERT INTO `tr_fmwk_role_function` VALUES ('362', '1', '2', '2015-11-11 09:30:09', '1', '2015-11-11 09:30:09', '1', '0');
INSERT INTO `tr_fmwk_role_function` VALUES ('363', '1', '3', '2015-11-11 09:30:09', '1', '2015-11-11 09:30:09', '1', '0');
INSERT INTO `tr_fmwk_role_function` VALUES ('364', '1', '4', '2015-11-11 09:30:09', '1', '2015-11-11 09:30:09', '1', '0');
INSERT INTO `tr_fmwk_role_function` VALUES ('365', '1', '20', '2015-11-11 09:30:09', '1', '2015-11-11 09:30:09', '1', '0');
INSERT INTO `tr_fmwk_role_function` VALUES ('366', '1', '21', '2015-11-11 09:30:09', '1', '2015-11-11 09:30:09', '1', '0');
INSERT INTO `tr_fmwk_role_function` VALUES ('367', '1', '22', '2015-11-11 09:30:09', '1', '2015-11-11 09:30:09', '1', '0');
INSERT INTO `tr_fmwk_role_function` VALUES ('368', '1', '23', '2015-11-11 09:30:09', '1', '2015-11-11 09:30:09', '1', '0');
INSERT INTO `tr_fmwk_role_function` VALUES ('369', '1', '24', '2015-11-11 09:30:09', '1', '2015-11-11 09:30:09', '1', '0');
INSERT INTO `tr_fmwk_role_function` VALUES ('370', '1', '25', '2015-11-11 09:30:09', '1', '2015-11-11 09:30:09', '1', '0');
INSERT INTO `tr_fmwk_role_function` VALUES ('371', '1', '26', '2015-11-11 09:30:09', '1', '2015-11-11 09:30:09', '1', '0');
INSERT INTO `tr_fmwk_role_function` VALUES ('372', '1', '27', '2015-11-11 09:30:09', '1', '2015-11-11 09:30:09', '1', '0');
INSERT INTO `tr_fmwk_role_function` VALUES ('373', '1', '28', '2015-11-11 09:30:09', '1', '2015-11-11 09:30:09', '1', '0');
INSERT INTO `tr_fmwk_role_function` VALUES ('374', '1', '29', '2015-11-11 09:30:09', '1', '2015-11-11 09:30:09', '1', '0');
INSERT INTO `tr_fmwk_role_function` VALUES ('375', '1', '30', '2015-11-11 09:30:09', '1', '2015-11-11 09:30:09', '1', '0');
INSERT INTO `tr_fmwk_role_function` VALUES ('376', '1', '31', '2015-11-11 09:30:09', '1', '2015-11-11 09:30:09', '1', '0');
INSERT INTO `tr_fmwk_role_function` VALUES ('377', '1', '32', '2015-11-11 09:30:09', '1', '2015-11-11 09:30:09', '1', '0');
INSERT INTO `tr_fmwk_role_function` VALUES ('378', '1', '33', '2015-11-11 09:30:09', '1', '2015-11-11 09:30:09', '1', '0');
INSERT INTO `tr_fmwk_role_function` VALUES ('392', '2', '1', '2016-05-12 16:39:15', '1', '2016-05-12 16:39:15', '1', '0');
INSERT INTO `tr_fmwk_role_function` VALUES ('393', '2', '20', '2016-05-12 16:39:16', '1', '2016-05-12 16:39:16', '1', '0');
INSERT INTO `tr_fmwk_role_function` VALUES ('394', '2', '21', '2016-05-12 16:39:16', '1', '2016-05-12 16:39:16', '1', '0');
INSERT INTO `tr_fmwk_role_function` VALUES ('395', '2', '22', '2016-05-12 16:39:16', '1', '2016-05-12 16:39:16', '1', '0');
INSERT INTO `tr_fmwk_role_function` VALUES ('396', '2', '23', '2016-05-12 16:39:16', '1', '2016-05-12 16:39:16', '1', '0');
INSERT INTO `tr_fmwk_role_function` VALUES ('397', '2', '24', '2016-05-12 16:39:16', '1', '2016-05-12 16:39:16', '1', '0');
INSERT INTO `tr_fmwk_role_function` VALUES ('398', '2', '29', '2016-05-12 16:39:16', '1', '2016-05-12 16:39:16', '1', '0');
INSERT INTO `tr_fmwk_role_function` VALUES ('399', '2', '33', '2016-05-12 16:39:16', '1', '2016-05-12 16:39:16', '1', '0');
INSERT INTO `tr_fmwk_user_role` VALUES ('99', '1', '1', '1', '2015-06-04 10:52:09', '1', '2015-06-04 10:52:09', '0');
INSERT INTO `tr_fmwk_user_role` VALUES ('100', '2', '3', '1', '2016-05-13 15:35:56', '1', '2016-05-13 15:35:56', '0');
INSERT INTO `tr_group_room` VALUES ('1', '1', '1', '2016-05-13 12:41:34', '1', '2016-05-13 12:41:36', '1', '0');
INSERT INTO `tr_group_room` VALUES ('2', '1', '2', '2016-05-13 12:42:36', '1', '2016-05-13 12:42:38', '1', '0');
INSERT INTO `tr_group_room` VALUES ('3', '1', '3', '2016-05-13 12:42:48', '1', '2016-05-13 12:42:50', '1', '0');
INSERT INTO `tr_group_room` VALUES ('1', '2', '4', '2016-05-13 12:42:58', '1', '2016-05-13 12:43:00', '1', '0');
INSERT INTO `tr_group_room` VALUES ('2', '2', '5', '2016-05-13 12:43:07', '1', '2016-05-13 12:43:09', '1', '0');
INSERT INTO `tr_group_room` VALUES ('1', '3', '6', '2016-05-13 12:43:17', '1', '2016-05-13 12:43:19', '1', '0');
INSERT INTO `tr_group_room` VALUES ('2', '4', '7', '2016-05-13 12:45:12', '1', '2016-05-13 12:45:13', '1', '0');
INSERT INTO `tr_group_room` VALUES ('3', '5', '8', '2016-05-13 12:45:25', '1', '2016-05-13 12:45:29', '1', '0');
INSERT INTO `tr_group_room` VALUES ('4', '6', '9', '2016-05-13 12:45:41', '1', '2016-05-13 12:45:43', '1', '0');
INSERT INTO `ts_fmwk_func_group` VALUES ('1', '1', '1', '1', '1', '2013-11-06 08:43:38', '1', '2013-11-06 08:43:41', '0');
INSERT INTO `ts_fmwk_function` VALUES ('1', 'M01', 'MainTain', 'menu', '1', '2013-11-06 08:58:27', '0', '2013-11-06 08:58:27', '1', '0');
INSERT INTO `ts_fmwk_function` VALUES ('2', 'M01_01', 'Menu', 'menu', '1', '2013-11-06 08:59:20', '0', '2013-11-06 08:59:20', '1', '0');
INSERT INTO `ts_fmwk_function` VALUES ('3', 'M01_02', 'Role', 'menu', '1', '2013-11-06 08:59:48', '0', '2013-11-06 08:59:48', '1', '0');
INSERT INTO `ts_fmwk_function` VALUES ('4', 'M01_03', 'User', 'menu', '1', '2013-11-06 09:00:17', '0', '2013-11-06 09:00:17', '1', '0');
INSERT INTO `ts_fmwk_function` VALUES ('12', 'M05', '修改密码', 'menu', '1', '2013-11-20 14:20:43', '1', '2013-11-20 14:20:43', '1', '0');
INSERT INTO `ts_fmwk_function` VALUES ('20', 'M02', '客户管理', 'menu', '1', '2015-02-14 20:59:46', '0', '2015-02-14 20:59:46', '1', '0');
INSERT INTO `ts_fmwk_function` VALUES ('21', 'M02_01', '客户管理', 'menu', '1', '2015-02-14 21:01:03', '0', '2015-02-14 21:01:03', '1', '0');
INSERT INTO `ts_fmwk_function` VALUES ('22', 'M02_02', '管理员管理', 'menu', '1', '2015-03-16 16:12:47', '0', '2015-03-16 16:12:47', '1', '0');
INSERT INTO `ts_fmwk_function` VALUES ('23', 'M02_03', '机器人管理', 'menu', '1', '2015-03-17 10:03:08', '0', '2015-03-17 10:03:08', '1', '0');
INSERT INTO `ts_fmwk_function` VALUES ('24', 'M03', '功能模块', 'menu', '1', '2015-03-19 16:54:09', '0', '2015-03-19 16:54:09', '1', '0');
INSERT INTO `ts_fmwk_function` VALUES ('25', 'M03_01', '在线答疑', 'menu', '1', '2015-03-19 16:55:13', '0', '2015-03-19 16:55:13', '1', '0');
INSERT INTO `ts_fmwk_function` VALUES ('26', 'M03_02', '交易提示', 'menu', '1', '2015-03-19 16:55:57', '0', '2015-03-19 16:55:57', '1', '0');
INSERT INTO `ts_fmwk_function` VALUES ('27', 'M03_03', '市场评论', 'menu', '1', '2015-03-19 16:56:31', '0', '2015-03-19 16:56:31', '1', '0');
INSERT INTO `ts_fmwk_function` VALUES ('28', 'M03_04', '喊单提示', 'menu', '1', '2015-03-19 16:59:45', '0', '2015-03-19 16:59:45', '1', '0');
INSERT INTO `ts_fmwk_function` VALUES ('29', 'M01_04', '修改米吗', 'menu', '1', '2015-04-13 11:25:26', '0', '2015-04-13 11:25:26', '1', '0');
INSERT INTO `ts_fmwk_function` VALUES ('30', 'M03_05', '历史视频', 'menu', '1', '2015-05-04 17:14:22', '0', '2015-05-04 17:14:22', '1', '0');
INSERT INTO `ts_fmwk_function` VALUES ('31', 'M01_05', '修改直播视频', 'menu', '1', '2015-06-01 10:33:40', '0', '2015-06-01 10:33:40', '1', '0');
INSERT INTO `ts_fmwk_function` VALUES ('32', 'M03_06', '共享上传', 'menu', '1', '2015-07-02 16:02:11', '0', '2015-07-02 16:02:11', '1', '0');
INSERT INTO `ts_fmwk_function` VALUES ('33', 'M03_07', '上传课程表', 'menu', '1', '2015-11-11 09:29:54', '0', '2015-11-11 09:29:54', '1', '0');
INSERT INTO `ts_fmwk_menu` VALUES ('4', '0', 'M01', '1', '系统操作', '0', '#', '1', '2013-11-06 08:58:27', '1', '2015-11-06 15:36:05', '0');
INSERT INTO `ts_fmwk_menu` VALUES ('5', '1', 'M01_01', '2', '系统菜单管理', 'M01', '/maintain/menu/init', '1', '2013-11-06 08:59:20', '0', '2013-11-06 08:59:20', '0');
INSERT INTO `ts_fmwk_menu` VALUES ('6', '1', 'M01_02', '2', '系统角色管理', 'M01', '/maintain/role/init', '1', '2013-11-06 08:59:48', '0', '2013-11-06 08:59:48', '0');
INSERT INTO `ts_fmwk_menu` VALUES ('7', '1', 'M01_03', '2', '系统用户管理', 'M01', '/maintain/user/init', '1', '2013-11-06 09:00:17', '0', '2013-11-06 09:00:17', '0');
INSERT INTO `ts_fmwk_menu` VALUES ('26', '1', 'M02', '1', '信息管理', '0', '#', '1', '2015-02-14 20:59:45', '1', '2015-02-14 21:01:16', '0');
INSERT INTO `ts_fmwk_menu` VALUES ('27', '1', 'M02_01', '2', '客户管理', 'M02', '/mainTain/customerinfo/init', '1', '2015-02-14 21:01:03', '1', '2015-02-14 21:05:05', '0');
INSERT INTO `ts_fmwk_menu` VALUES ('29', '1', 'M02_03', '2', '机器人管理', 'M02', '/mainTain/robot/init', '1', '2015-03-17 10:03:08', '0', '2015-03-17 10:03:08', '0');
INSERT INTO `ts_fmwk_menu` VALUES ('30', '0', 'M03', '1', '功能模块', '0', '#', '1', '2015-03-19 16:54:09', '1', '2015-03-19 16:54:26', '0');
INSERT INTO `ts_fmwk_menu` VALUES ('35', '1', 'M01_04', '1', '修改密码', 'M01', '/maintain/user/initpassword', '1', '2015-04-13 11:25:26', '0', '2015-04-13 11:25:26', '0');
INSERT INTO `ts_fmwk_menu` VALUES ('38', '1', 'M03_06', '1', '分配QQ', 'M03', '/mainTain/upload/init', '1', '2015-07-02 16:02:11', '1', '2015-10-28 17:26:14', '0');
INSERT INTO `ts_fmwk_menu` VALUES ('39', '1', 'M03_07', '1', '上传课程表', 'M03', '/mainTain/kcb/init', '1', '2015-11-11 09:29:54', '0', '2015-11-11 09:29:54', '0');
INSERT INTO `ts_fmwk_role` VALUES ('1', 'ADMIN', '超级管理员', 'R001', '1', '1', '2015-11-11 09:30:09', '1', '2015-11-11 09:30:09', '0');
INSERT INTO `ts_fmwk_role` VALUES ('2', 'ROOM_ADMIN', '房间管理员', 'R002', '1', '1', '2016-05-12 16:39:14', '1', '2016-05-12 16:39:14', '0');
INSERT INTO `ts_fmwk_role` VALUES ('3', 'CUSTOMER', '业务员', 'R003', '1', '1', '2015-05-07 09:34:23', '1', '2015-05-07 09:34:23', '0');
INSERT INTO `ts_fmwk_user` VALUES ('1', 'admin', 'BBE5892D81B3FCE96B5B938AD314CE', 'admin', '2', '1', '5490', '2015-06-04 10:52:08', '123@123.com', '0', '0', '1', '2016-03-13 16:07:03', '0', '0');
INSERT INTO `ts_fmwk_user` VALUES ('2', '1', 'E1ADC3949BA59ABBE56E057F2F883E', '1', '2', '3', '0', '2016-05-13 15:35:55', null, '1', '1', '0', '2016-05-13 15:35:55', '0', null);
INSERT INTO `ts_fmwk_user_group` VALUES ('0', '1', '1', '超级管理员组', '1', '2013-11-13 14:02:34', '1', '2013-11-13 14:02:38', '0');
INSERT INTO `ts_fmwk_user_group` VALUES ('0', '2', '2', '房间1房间2管理员', '1', '2013-11-06 11:44:24', '1', '2013-11-06 11:44:29', '1');
INSERT INTO `ts_fmwk_user_group` VALUES ('0', '3', '3', '房间1业务员', '1', '2013-11-06 12:22:43', '1', '2013-11-06 12:22:47', '2');
INSERT INTO `ts_fmwk_user_group` VALUES ('0', '4', '4', '房间2业务员', '1', '2013-11-06 12:23:34', '1', '2013-11-06 12:23:38', '2');
INSERT INTO `ts_fmwk_user_group` VALUES ('0', '5', '5', '房间3业务员', '1', '2013-11-06 12:23:57', '1', '2013-11-06 12:23:52', '6');
INSERT INTO `ts_fmwk_user_group` VALUES ('0', '6', '6', '房间3管理员', '1', '2014-12-31 12:36:33', '1', '2014-12-31 12:36:37', '1');
INSERT INTO `xd_customer` VALUES ('1', '0', '1', '2016-05-13 15:02:40', '1', '2016-05-13 17:29:07', '房1', '房1', '123', null, null, null, null, '1', null, '5', 'block', null, null, null, null, '1');
INSERT INTO `xd_customer` VALUES ('2', '0', '1', '2016-05-13 15:02:51', '1', '2016-05-13 15:02:53', '房2', '房2', '123', null, null, null, null, '1', null, '2', 'normal', null, null, null, null, '2');
INSERT INTO `xd_customer` VALUES ('3', '0', '1', '2016-05-13 15:03:14', '1', '2016-05-13 15:03:16', '房3', '房3', '123', null, null, null, null, '2', null, '0', 'normal', null, null, null, null, '1');
INSERT INTO `xd_kcb` VALUES ('1', '9:30-11:00', '股海风云', '伯懿老师', '股海风云', '伯懿老师', '股海风云', '伯懿老师', '股海风云', '伯懿老师', '股海风云', '伯懿老师', null, null, null, null, null, null);
INSERT INTO `xd_kcb` VALUES ('2', '13:00-15:00', '温故知新', '亮剑老师', '温故知新', '亮剑老师', '温故知新', '亮剑老师', '温故知新', '亮剑老师', '温故知新', '亮剑老师', null, null, null, null, null, null);
INSERT INTO `xd_kcb` VALUES ('3', '15:00-17:00', '短线神手', '伯懿老师', '短线神手', '伯懿老师', '短线神手', '伯懿老师', '短线神手', '伯懿老师', '短线神手', '伯懿老师', null, null, null, null, null, null);
INSERT INTO `xd_kcb` VALUES ('4', '17:30-19:30', '趋势为王', '清风老师', '趋势为王', '清风老师', '趋势为王', '清风老师', '趋势为王', '清风老师', '趋势为王', '清风老师', null, null, null, null, null, null);
INSERT INTO `xd_kcb` VALUES ('5', '19:30-21:30', '巅峰时刻', '捕手老师', '巅峰时刻', '捕手老师', '巅峰时刻', '捕手老师', '巅峰时刻', '捕手老师', '捕手老师', '捕手老师', null, null, null, null, null, null);
INSERT INTO `xd_kcb` VALUES ('6', '21:30-24:00', '测试', '测试老师', '测试', '测试老师', '测试', '测试老师', '测试', '测试老师', '测试', '测试老师', null, null, null, null, null, null);
INSERT INTO `xd_robot` VALUES ('1', '0', '1', '2016-05-16 20:25:14', '1', '2016-05-16 20:25:14', '无敌机器人', null, '07.png', '0');
INSERT INTO `xd_room` VALUES ('1', '1', '1房', '0', '2016-05-13 12:46:41', '1', '2016-05-13 12:46:43', '1');
INSERT INTO `xd_room` VALUES ('2', '2', '2房', '0', '2016-05-13 12:46:56', '1', '2016-05-13 12:46:58', '1');
INSERT INTO `xd_room` VALUES ('3', '3', '3房', '0', '2016-05-13 12:47:08', '1', '2016-05-13 12:47:11', '1');
