/*
 Navicat MySQL Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50641
 Source Host           : 127.0.0.1
 Source Database       : vradata

 Target Server Type    : MySQL
 Target Server Version : 50641
 File Encoding         : utf-8

 Date: 09/27/2018 03:41:55 AM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `T_VARTRIVER_ControlEvent`
-- ----------------------------
DROP TABLE IF EXISTS `T_VARTRIVER_ControlEvent`;
CREATE TABLE `T_VARTRIVER_ControlEvent` (
  `id` varchar(42) NOT NULL DEFAULT '',
  `deviceId` varchar(50) DEFAULT '',
  `title` varchar(100) DEFAULT '',
  `fieldName` varchar(50) DEFAULT '',
  `eventType` varchar(2) DEFAULT '',
  `logic` varchar(50) DEFAULT '',
  `lowerLimit` double DEFAULT '0',
  `upperLimit` double DEFAULT '0',
  `executeScript` text,
  `createTime` datetime DEFAULT NULL,
  `userId` varchar(42) DEFAULT '',
  `status` smallint(6) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `T_VARTRIVER_Monitor`
-- ----------------------------
DROP TABLE IF EXISTS `T_VARTRIVER_Monitor`;
CREATE TABLE `T_VARTRIVER_Monitor` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '监视点记录id',
  `deviceId` varchar(50) DEFAULT '' COMMENT '设备id',
  `monitorId` int(11) DEFAULT '0' COMMENT '摄像头上监视点id',
  `monitorName` varchar(100) DEFAULT '' COMMENT '监视点名字',
  `beginTime` varchar(20) DEFAULT '' COMMENT '开始时间',
  `endTime` varchar(20) DEFAULT '' COMMENT '结束时间',
  `rateSecond` int(11) DEFAULT '0' COMMENT '采集时间间隔',
  `cycleDay` int(11) DEFAULT '0' COMMENT '采集周期',
  `imgUrl` varchar(100) DEFAULT '' COMMENT '链接',
  `createTime` varchar(1000) DEFAULT NULL COMMENT '创建时间',
  `success` smallint(6) DEFAULT '0' COMMENT '是否成功 1 成功 2 不成功',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=697 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `alarm_rule`
-- ----------------------------
DROP TABLE IF EXISTS `alarm_rule`;
CREATE TABLE `alarm_rule` (
  `ala_id` int(11) NOT NULL AUTO_INCREMENT,
  `ala_name` varchar(125) DEFAULT NULL,
  `ala_channel` varchar(32) DEFAULT NULL,
  `deviceid` varchar(32) DEFAULT NULL,
  `ala_range` varchar(32) DEFAULT NULL,
  `ala_state` varchar(10) DEFAULT NULL,
  `ala_up` double DEFAULT NULL,
  `ala_low` double DEFAULT NULL,
  `ala_producer` int(11) DEFAULT NULL,
  `ala_supervisor` int(11) DEFAULT NULL,
  `ala_content` varchar(256) DEFAULT NULL,
  `ala_index` varchar(10) DEFAULT NULL,
  `ala_grade` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ala_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `class`
-- ----------------------------
DROP TABLE IF EXISTS `class`;
CREATE TABLE `class` (
  `c_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '分类id',
  `c_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '' COMMENT '分类名字',
  `c_lev` int(10) DEFAULT NULL COMMENT '分类等级',
  `c_rid` int(10) DEFAULT NULL COMMENT '上级分类id',
  `c_state` int(1) DEFAULT '1' COMMENT '状态 1正常 2 不正常',
  `c_posi` int(2) DEFAULT NULL COMMENT '位置',
  `c_photo` varchar(2000) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '' COMMENT '图片',
  `c_type` int(4) DEFAULT '0' COMMENT ' 1 作物 2 农资  3 预警  4 政策  5 即时信息  6 专家 7 首页 8 生产进度',
  PRIMARY KEY (`c_id`)
) ENGINE=InnoDB AUTO_INCREMENT=151 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `control`
-- ----------------------------
DROP TABLE IF EXISTS `control`;
CREATE TABLE `control` (
  `ctrl_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '控制设备id',
  `ctrl_name` varchar(255) DEFAULT NULL COMMENT '控制设备名字',
  `ctrl_nickname` varchar(255) DEFAULT NULL COMMENT '控制设备别名',
  `ctrl_deviceId` varchar(255) DEFAULT NULL COMMENT 's河北id',
  `ctrl_mapingdeviceId` varchar(255) DEFAULT NULL COMMENT '摄像设备id',
  `ctrl_min` varchar(255) DEFAULT NULL COMMENT '映射最小值',
  `ctrl_max` varchar(255) DEFAULT NULL COMMENT '映射最大值',
  `ctrl_channel` varchar(255) DEFAULT NULL COMMENT '传感器分析通道',
  `ctrl_type` int(10) DEFAULT NULL COMMENT '控制类型是卷帘1还是电磁阀2',
  `ctrl_temperature` varchar(255) DEFAULT NULL COMMENT '控温分析通道',
  `ctrl_water` varchar(255) DEFAULT NULL COMMENT '控水分析通道',
  `ctrl_raise_groupId` int(11) DEFAULT NULL COMMENT '上升通道组号',
  `ctrl_raise_switchId` int(10) DEFAULT NULL COMMENT '上升开关号',
  `ctrl_down_groupId` int(10) DEFAULT NULL COMMENT '下降开关组号',
  `ctrl_down_switchId` int(11) DEFAULT NULL COMMENT '下降开关好',
  `ctrl_count` int(10) DEFAULT NULL COMMENT '计量参数',
  `ctrl_picturetype` int(10) DEFAULT NULL COMMENT '状态图名称',
  `ctrl_picturetitle` varchar(255) DEFAULT NULL COMMENT '状态图名称',
  `dev_type` int(255) DEFAULT NULL,
  `state_type` int(255) DEFAULT '0' COMMENT '控制的是哪个开关 1 2 3   0 是全关',
  `s_state` int(255) DEFAULT '2' COMMENT '开关状态2 关 1 开',
  `c_order_less` int(11) DEFAULT '0',
  PRIMARY KEY (`ctrl_id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Table structure for `distribution`
-- ----------------------------
DROP TABLE IF EXISTS `distribution`;
CREATE TABLE `distribution` (
  `d_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '分布情况id',
  `d_type` int(255) DEFAULT NULL COMMENT '分布类型 1 生产分布 2 物种分布',
  `d_state` int(255) DEFAULT NULL COMMENT '分布状态',
  `d_province` varchar(255) DEFAULT NULL COMMENT '省',
  `d_city` varchar(255) DEFAULT NULL COMMENT '市',
  `d_district` varchar(255) DEFAULT NULL COMMENT '县',
  `d_content` text COMMENT '分布内容',
  `d_value` varchar(255) DEFAULT NULL COMMENT '暂时无用',
  `d_index` int(255) DEFAULT NULL COMMENT '没用',
  `d_time` varchar(255) DEFAULT NULL COMMENT '添加时间',
  `d_originalname` varchar(255) DEFAULT NULL,
  `d_filename` varchar(225) DEFAULT NULL,
  `d_procedure` int(11) DEFAULT '0',
  `is_special` int(11) DEFAULT '0',
  PRIMARY KEY (`d_id`)
) ENGINE=InnoDB AUTO_INCREMENT=128 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Table structure for `exp_ans`
-- ----------------------------
DROP TABLE IF EXISTS `exp_ans`;
CREATE TABLE `exp_ans` (
  `exp_ans_id` int(11) NOT NULL AUTO_INCREMENT,
  `exp_ans_title` varchar(255) DEFAULT NULL,
  `exp_ans_content` text,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `tu_id` int(11) DEFAULT NULL,
  `if_delete` int(11) DEFAULT '0',
  `user_question_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`exp_ans_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='专家答疑';

-- ----------------------------
--  Table structure for `exp_client`
-- ----------------------------
DROP TABLE IF EXISTS `exp_client`;
CREATE TABLE `exp_client` (
  `exp_client_id` int(11) NOT NULL AUTO_INCREMENT,
  `exp_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `if_delete` int(11) DEFAULT '0',
  PRIMARY KEY (`exp_client_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `exp_field`
-- ----------------------------
DROP TABLE IF EXISTS `exp_field`;
CREATE TABLE `exp_field` (
  `exp_field_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '专家领域id',
  `c_id` int(11) DEFAULT NULL COMMENT '专家分类id',
  `tu_id` int(11) DEFAULT NULL COMMENT '用户id',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`exp_field_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `exp_pro`
-- ----------------------------
DROP TABLE IF EXISTS `exp_pro`;
CREATE TABLE `exp_pro` (
  `exp_pro_id` int(11) NOT NULL AUTO_INCREMENT,
  `exp_pro_title` varchar(255) DEFAULT NULL,
  `exp_pro_content` text,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `tu_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`exp_pro_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='专家服务项目';

-- ----------------------------
--  Table structure for `homepage`
-- ----------------------------
DROP TABLE IF EXISTS `homepage`;
CREATE TABLE `homepage` (
  `h_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '轮播图id',
  `h_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '轮播图名字',
  `h_url` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '轮播图链接',
  `h_cover` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '轮播图封面',
  `h_type` int(255) DEFAULT NULL COMMENT '轮播图类型',
  `h_state` int(255) DEFAULT NULL COMMENT '轮播图状态 1正常  2 不正常',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`h_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
--  Table structure for `input`
-- ----------------------------
DROP TABLE IF EXISTS `input`;
CREATE TABLE `input` (
  `in_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '投入品id',
  `in_ownername` varchar(255) DEFAULT NULL COMMENT '所有者姓名',
  `in_class1` int(10) DEFAULT NULL COMMENT '一级分类',
  `in_class2` int(10) DEFAULT NULL COMMENT '二级分类',
  `in_mattername` varchar(255) DEFAULT NULL COMMENT '投入品名字',
  `in_total` varchar(255) DEFAULT NULL COMMENT '投入总量',
  `in_pid` int(11) DEFAULT NULL COMMENT '排产id',
  `in_pname` varchar(255) DEFAULT NULL COMMENT '排产计划名字',
  `in_pstandrad` varchar(255) DEFAULT NULL COMMENT '排产标准',
  `in_parea` varchar(255) DEFAULT NULL COMMENT '排产名字',
  `in_time` varchar(255) DEFAULT NULL COMMENT '投入时间',
  `tp_id` int(11) DEFAULT '0' COMMENT '主设备节点id',
  `in_unit` int(11) DEFAULT '1' COMMENT '1亩 2公顷',
  PRIMARY KEY (`in_id`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Table structure for `key_value`
-- ----------------------------
DROP TABLE IF EXISTS `key_value`;
CREATE TABLE `key_value` (
  `redis_key` varchar(255) DEFAULT NULL COMMENT 'rediskey',
  `redis_value` varchar(255) DEFAULT NULL COMMENT 'redis_value'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Table structure for `message`
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `m_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '消息id',
  `m_type` int(10) DEFAULT NULL COMMENT '类型 1 政策 2及时 3 预警 4 首页咨询',
  `m_content` text COMMENT '内容',
  `m_title` varchar(255) DEFAULT NULL COMMENT '标题',
  `m_province` varchar(255) DEFAULT '' COMMENT '省',
  `m_city` varchar(255) DEFAULT '' COMMENT '市',
  `m_district` varchar(255) DEFAULT '' COMMENT '县',
  `m_class` int(10) DEFAULT NULL COMMENT '分类',
  `m_time` varchar(255) DEFAULT '' COMMENT '生成时间',
  `m_authorname` varchar(255) DEFAULT '' COMMENT '作者名',
  `m_cover` varchar(255) DEFAULT '' COMMENT '封面',
  `m_state` int(4) DEFAULT '1' COMMENT ' 1 正常 2 不正常',
  `m_class2` int(10) DEFAULT NULL COMMENT '二级分类',
  PRIMARY KEY (`m_id`)
) ENGINE=InnoDB AUTO_INCREMENT=347 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Table structure for `produce`
-- ----------------------------
DROP TABLE IF EXISTS `produce`;
CREATE TABLE `produce` (
  `p_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '排产计划id',
  `p_name` varchar(255) DEFAULT NULL COMMENT '排产计划名字',
  `p_type` int(10) DEFAULT NULL COMMENT '排产计划类型',
  `p_state` int(10) DEFAULT NULL COMMENT '排产计划状态',
  `p_class1` int(10) DEFAULT NULL COMMENT '排产计划一级分类',
  `p_class2` int(10) DEFAULT NULL COMMENT '排产计划二级分类',
  `p_standrad` varchar(20) DEFAULT NULL COMMENT '排产标准',
  `p_begintime` varchar(255) DEFAULT NULL COMMENT '开始时间',
  `p_endtime` varchar(255) DEFAULT NULL COMMENT '结束时间',
  `p_harvesttime` varchar(255) DEFAULT NULL COMMENT '采收时间',
  `p_harvestarea` varchar(255) DEFAULT '' COMMENT '排产面积',
  `p_ownername` varchar(255) DEFAULT '' COMMENT '所有者姓名',
  `tp_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`p_id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Table structure for `project`
-- ----------------------------
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project` (
  `p_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '专家服务id',
  `p_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '专家服务名字',
  `p_class1` int(255) DEFAULT NULL COMMENT '一级分类id',
  `p_class2` int(255) DEFAULT NULL COMMENT '二级分类id',
  `p_type` int(255) DEFAULT '0' COMMENT '类型',
  `p_check` int(255) DEFAULT '1' COMMENT '1 未审核 2 审核通过 3 审核不过',
  `p_state` int(255) DEFAULT '1' COMMENT ' 1 正常 2 异常',
  `p_charge` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '项目主要负责人',
  `p_chargepic` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '主要负责人照片',
  `p_chargepos` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '主要负责人职务',
  `p_chargeemail` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '主要负责人email',
  `p_videourl` varchar(5000) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '视频连接',
  `p_cover` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '服务封面',
  `p_content` text COLLATE utf8mb4_unicode_ci COMMENT '内容',
  `p_member` text COLLATE utf8mb4_unicode_ci COMMENT '成员',
  `p_uid` int(11) DEFAULT NULL COMMENT '发布人id',
  `p_authorname` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '作者名',
  `p_time` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '时间',
  `p_count` int(255) DEFAULT '0' COMMENT '阅读次数',
  `p_typename` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '类型名字',
  `p_subtitles` text COLLATE utf8mb4_unicode_ci COMMENT '字幕',
  PRIMARY KEY (`p_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
--  Table structure for `relationship`
-- ----------------------------
DROP TABLE IF EXISTS `relationship`;
CREATE TABLE `relationship` (
  `deviceId` varchar(255) NOT NULL,
  `producerid` int(255) DEFAULT NULL,
  `producername` varchar(255) DEFAULT '',
  `superviserid` int(11) DEFAULT NULL,
  `supervisename` varchar(255) DEFAULT '',
  `ownerid` int(11) DEFAULT NULL,
  `ownername` varchar(255) DEFAULT '',
  `professorid` varchar(200) DEFAULT NULL,
  `professorname` varchar(255) DEFAULT '',
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `r_name` varchar(255) DEFAULT NULL COMMENT '身份',
  `r_value` int(10) DEFAULT NULL COMMENT '权限'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Table structure for `s_area`
-- ----------------------------
DROP TABLE IF EXISTS `s_area`;
CREATE TABLE `s_area` (
  `ar_id` varchar(255) NOT NULL COMMENT '区域id',
  `a_name` varchar(255) DEFAULT NULL COMMENT '区域名字'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Table structure for `ss`
-- ----------------------------
DROP TABLE IF EXISTS `ss`;
CREATE TABLE `ss` (
  `ar_id` varchar(255) NOT NULL,
  `a_name` varchar(255) DEFAULT NULL,
  `a_pid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ar_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_appframe_monitor_data`
-- ----------------------------
DROP TABLE IF EXISTS `t_appframe_monitor_data`;
CREATE TABLE `t_appframe_monitor_data` (
  `id` double NOT NULL DEFAULT '0',
  `createDataTime` datetime DEFAULT NULL,
  `devType` varchar(20) DEFAULT '',
  `devId` varchar(100) DEFAULT '',
  `devField` varchar(200) DEFAULT '',
  `devData` varchar(20) DEFAULT '',
  `infoDataTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_appframe_monitor_object`
-- ----------------------------
DROP TABLE IF EXISTS `t_appframe_monitor_object`;
CREATE TABLE `t_appframe_monitor_object` (
  `id` double NOT NULL DEFAULT '0',
  `createDataTime` datetime DEFAULT NULL,
  `devType` varchar(20) DEFAULT '',
  `devId` varchar(100) DEFAULT '',
  `devField` varchar(200) DEFAULT '',
  `infoDataTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_ctrlset`
-- ----------------------------
DROP TABLE IF EXISTS `t_ctrlset`;
CREATE TABLE `t_ctrlset` (
  `id` double NOT NULL DEFAULT '0',
  `enable` varchar(255) NOT NULL DEFAULT '',
  `name` varchar(255) DEFAULT '',
  `str` varchar(255) DEFAULT '',
  `comm` varchar(255) NOT NULL DEFAULT '',
  `memo` varchar(255) NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_monitor`
-- ----------------------------
DROP TABLE IF EXISTS `t_monitor`;
CREATE TABLE `t_monitor` (
  `mo_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '规则id',
  `mo_name` varchar(255) DEFAULT NULL COMMENT '规则名称',
  `mo_deviceId` varchar(255) DEFAULT NULL COMMENT '设备id',
  `mo_time` varchar(255) DEFAULT NULL COMMENT '创建时间',
  `mo_channel` varchar(255) DEFAULT NULL COMMENT '分析的通道',
  `mo_type` int(4) DEFAULT NULL COMMENT '控制类型',
  `mo_state` int(4) DEFAULT NULL COMMENT '控制状态',
  `mo_high` decimal(10,2) DEFAULT NULL COMMENT '上限值',
  `mo_lower` decimal(10,2) DEFAULT NULL COMMENT '下限值',
  `ctrl_id` int(11) DEFAULT NULL COMMENT '控制设备id',
  `order_less` int(11) DEFAULT '1' COMMENT '0关闭1开启',
  `order_more` int(11) DEFAULT '0' COMMENT '0关闭1开启',
  `check_interval` int(11) DEFAULT '5' COMMENT '间隔多长时间检查一次 m',
  `duration` int(11) DEFAULT '120' COMMENT '控制时长s',
  PRIMARY KEY (`mo_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Table structure for `t_point`
-- ----------------------------
DROP TABLE IF EXISTS `t_point`;
CREATE TABLE `t_point` (
  `tp_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '树id',
  `tp_name` varchar(20) DEFAULT '' COMMENT '分类树枝名称',
  `tp_type` int(11) DEFAULT '0' COMMENT '类型 1 站点 2 分类 3 设备',
  `tp_pid` int(11) DEFAULT '0' COMMENT '父级id',
  `tp_state` int(11) DEFAULT '0' COMMENT '状态',
  `tp_order` int(11) DEFAULT '0' COMMENT '顺序',
  `tp_time` varchar(20) DEFAULT NULL COMMENT '时间',
  `ip` varchar(20) DEFAULT '' COMMENT '该设备的ip',
  `port` int(11) DEFAULT '52390' COMMENT '该设备端口',
  `uid` bigint(11) DEFAULT NULL COMMENT '添加设备者id',
  `deviceId` varchar(255) DEFAULT '' COMMENT '设备id',
  `t_role` varchar(255) DEFAULT NULL COMMENT '这个设备的监管者等等的id',
  `x` double(255,7) DEFAULT NULL COMMENT '纬度',
  `y` double(255,7) DEFAULT NULL COMMENT '经度',
  `infoiptime` varchar(255) DEFAULT NULL COMMENT '上报ip的时间',
  `zoom` double(10,2) DEFAULT NULL,
  `tp_index` int(10) DEFAULT NULL,
  PRIMARY KEY (`tp_id`)
) ENGINE=InnoDB AUTO_INCREMENT=134 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Table structure for `t_pointsss`
-- ----------------------------
DROP TABLE IF EXISTS `t_pointsss`;
CREATE TABLE `t_pointsss` (
  `tp_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '树id',
  `tp_name` varchar(20) DEFAULT '' COMMENT '分类树枝名称',
  `tp_type` int(11) DEFAULT '0' COMMENT '类型 1 站点 2 分类 3 设备',
  `tp_pid` int(11) DEFAULT '0' COMMENT '父级id',
  `tp_state` int(11) DEFAULT '0' COMMENT '状态',
  `tp_order` int(11) DEFAULT '0' COMMENT '顺序',
  `tp_time` varchar(20) DEFAULT NULL COMMENT '时间',
  `ip` varchar(20) DEFAULT '' COMMENT '该设备的ip',
  `port` int(11) DEFAULT '52390' COMMENT '该设备端口',
  `uid` bigint(11) DEFAULT NULL COMMENT '添加设备者id',
  `deviceId` varchar(255) DEFAULT '' COMMENT '设备id',
  `t_role` varchar(255) DEFAULT NULL COMMENT '这个设备的监管者等等的id',
  `x` double(255,7) DEFAULT NULL COMMENT '纬度',
  `y` double(255,7) DEFAULT NULL COMMENT '经度',
  `infoiptime` varchar(255) DEFAULT NULL COMMENT '上报ip的时间',
  `zoom` double(10,2) DEFAULT NULL,
  `tp_index` int(10) DEFAULT NULL,
  PRIMARY KEY (`tp_id`)
) ENGINE=InnoDB AUTO_INCREMENT=134 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Table structure for `t_setting`
-- ----------------------------
DROP TABLE IF EXISTS `t_setting`;
CREATE TABLE `t_setting` (
  `ts_deviceId` varchar(255) NOT NULL DEFAULT '0' COMMENT '设备id',
  `ts_var_ip` varchar(255) DEFAULT '' COMMENT '设备ip',
  `ts_var_configstr` varchar(6000) DEFAULT '' COMMENT '设备配置',
  `ts_var_time` varchar(255) DEFAULT '' COMMENT '时间',
  `ts_var_state` int(4) DEFAULT '0' COMMENT '状态',
  `ts_var_port` int(255) DEFAULT '0' COMMENT '端口'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `tu_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `tu_username` varchar(20) DEFAULT NULL COMMENT '用户名',
  `tu_pwd` varchar(20) DEFAULT NULL COMMENT '密码',
  `tu_state` int(6) DEFAULT '1' COMMENT '1 正常 2 不正常',
  `tu_type` int(6) DEFAULT '0' COMMENT '0  1 超级管理员  2 管理员 3 普通用户4监管者用户5生产者用户 6专家用户',
  `tu_phone` varchar(255) DEFAULT NULL COMMENT '电话号',
  `tu_name` varchar(255) DEFAULT NULL COMMENT '姓名',
  `tu_read` int(11) DEFAULT '0' COMMENT '阅读数',
  `tu_email` varchar(125) DEFAULT NULL,
  `tu_sex` varchar(255) DEFAULT '0' COMMENT '0:1:2保密，男，女',
  `tu_job` varchar(32) DEFAULT NULL COMMENT '职务',
  `tu_edu` varchar(12) DEFAULT NULL COMMENT '学位',
  `c_id` int(11) DEFAULT NULL,
  `tu_info` text COMMENT '简介 主要针对专家用户',
  `tu_age` int(11) DEFAULT NULL COMMENT '年龄',
  `tu_logo` varchar(255) DEFAULT NULL COMMENT '头像',
  PRIMARY KEY (`tu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Table structure for `t_varstriver_switch`
-- ----------------------------
DROP TABLE IF EXISTS `t_varstriver_switch`;
CREATE TABLE `t_varstriver_switch` (
  `globalSwId` varchar(100) NOT NULL DEFAULT '',
  `name` varchar(100) DEFAULT '',
  `deviceId` varchar(200) DEFAULT '',
  `switchGroupId` varchar(42) DEFAULT '',
  `switchId` varchar(42) DEFAULT '',
  `smartCtrl` varchar(1) DEFAULT '',
  `confCtrlType` varchar(1) DEFAULT '',
  `switchStatus` varchar(1) DEFAULT '',
  `outputConf` double DEFAULT '0',
  `openDataTime` datetime DEFAULT NULL,
  `openDuration` double DEFAULT '0',
  PRIMARY KEY (`globalSwId`(64))
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_varstriver_switchctrl_immediately_queue`
-- ----------------------------
DROP TABLE IF EXISTS `t_varstriver_switchctrl_immediately_queue`;
CREATE TABLE `t_varstriver_switchctrl_immediately_queue` (
  `id` double NOT NULL DEFAULT '0',
  `deviceId` varchar(200) DEFAULT '',
  `switchGroupId` varchar(42) DEFAULT '',
  `switchId` varchar(42) DEFAULT '',
  `createTime` datetime DEFAULT NULL,
  `cmd` varchar(1) DEFAULT '',
  `cmdExecDataTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_varstriver_switchctrl_log`
-- ----------------------------
DROP TABLE IF EXISTS `t_varstriver_switchctrl_log`;
CREATE TABLE `t_varstriver_switchctrl_log` (
  `id` double NOT NULL DEFAULT '0',
  `name` varchar(100) DEFAULT '',
  `deviceId` varchar(200) DEFAULT '',
  `switchGroupId` varchar(42) DEFAULT '',
  `switchId` varchar(42) DEFAULT '',
  `smartCtrl` varchar(1) DEFAULT '',
  `ctrlType` varchar(1) DEFAULT '',
  `cycleDay` int(11) DEFAULT '0',
  `execTime` varchar(20) DEFAULT '',
  `beginTime` datetime DEFAULT NULL,
  `endTime` datetime DEFAULT NULL,
  `targetDeviceId` varchar(200) DEFAULT '',
  `targetFieldName` varchar(50) DEFAULT '',
  `maxValue` double DEFAULT '0',
  `minValue` double DEFAULT '0',
  `duration` double DEFAULT '0',
  `coefficient` double DEFAULT '0',
  `ruleEnable` varchar(1) DEFAULT '',
  `lastExecDataTime` datetime DEFAULT NULL,
  `realValue` double DEFAULT '0',
  `totalOutput` double DEFAULT '0',
  `confOutPut` double DEFAULT '0',
  `createTime` datetime DEFAULT NULL,
  `SwitchCtrlRuleId` double DEFAULT '0',
  `queueId` double DEFAULT '0',
  `interrupt` varchar(1) DEFAULT '',
  `realDuration` double DEFAULT '0',
  `remainingDuration` double DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_varstriver_switchctrl_monitor`
-- ----------------------------
DROP TABLE IF EXISTS `t_varstriver_switchctrl_monitor`;
CREATE TABLE `t_varstriver_switchctrl_monitor` (
  `id` double NOT NULL DEFAULT '0',
  `deviceId` varchar(200) DEFAULT '',
  `switchGroupId` varchar(42) DEFAULT '',
  `switchId` varchar(42) DEFAULT '',
  `SwitchCtrlRuleId` double DEFAULT '0',
  `lastExecDataTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_varstriver_switchctrl_queue`
-- ----------------------------
DROP TABLE IF EXISTS `t_varstriver_switchctrl_queue`;
CREATE TABLE `t_varstriver_switchctrl_queue` (
  `id` double NOT NULL DEFAULT '0',
  `deviceId` varchar(200) DEFAULT '',
  `switchGroupId` varchar(42) DEFAULT '',
  `switchId` varchar(42) DEFAULT '',
  `createTime` datetime DEFAULT NULL,
  `SwitchCtrlRuleId` double DEFAULT '0',
  `beginDateTime` datetime DEFAULT NULL,
  `endDateTime` datetime DEFAULT NULL,
  `cmdStatus` varchar(1) DEFAULT '',
  `cmdExecDataTime` datetime DEFAULT NULL,
  `cmdValid` varchar(1) DEFAULT '',
  `realValue` double DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_varstriver_switchctrl_rule`
-- ----------------------------
DROP TABLE IF EXISTS `t_varstriver_switchctrl_rule`;
CREATE TABLE `t_varstriver_switchctrl_rule` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '智能控制id',
  `name` varchar(100) DEFAULT '' COMMENT '只能控制名字',
  `deviceId` varchar(200) DEFAULT '' COMMENT '设备id',
  `switchGroupId` varchar(42) DEFAULT '',
  `switchId` varchar(42) DEFAULT '',
  `ctrlType` varchar(1) DEFAULT '',
  `cycleDay` int(11) DEFAULT '0' COMMENT '循环时间(天)',
  `execTime` varchar(20) DEFAULT '' COMMENT '执行时间',
  `beginTime` varchar(255) DEFAULT NULL COMMENT '开始日期',
  `endTime` varchar(255) DEFAULT NULL COMMENT '结束日期',
  `targetDeviceId` varchar(200) DEFAULT '',
  `targetFieldName` varchar(50) DEFAULT '',
  `maxValue` double DEFAULT '0',
  `minValue` double DEFAULT '0',
  `duration` double DEFAULT '0' COMMENT '持续时间',
  `coefficient` double DEFAULT '0',
  `ruleEnable` varchar(1) DEFAULT '' COMMENT '状态1 正常 2 不正常',
  `lastExecDataTime` varchar(255) DEFAULT NULL,
  `type` int(255) DEFAULT NULL COMMENT '类型 1 预约  ',
  `ctrl_id` int(11) DEFAULT NULL COMMENT '控制设备id',
  `execEndTime` varchar(255) DEFAULT NULL COMMENT '执行结束时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_vartriver_10002115`
-- ----------------------------
DROP TABLE IF EXISTS `t_vartriver_10002115`;
CREATE TABLE `t_vartriver_10002115` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `deviceId` varchar(50) DEFAULT '',
  `infoDataTime` datetime DEFAULT NULL,
  `channel1` varchar(20) DEFAULT '',
  `channel2` varchar(20) DEFAULT '',
  `channel3` varchar(20) DEFAULT '',
  `channel4` varchar(20) DEFAULT '',
  `channel5` varchar(20) DEFAULT '',
  `channel6` varchar(20) DEFAULT '',
  `channel7` varchar(20) DEFAULT '',
  `channel8` varchar(20) DEFAULT '',
  `channel9` varchar(20) DEFAULT '',
  `channel10` varchar(20) DEFAULT '',
  `channel11` varchar(20) DEFAULT '',
  `channel12` varchar(20) DEFAULT '',
  `channel13` varchar(20) DEFAULT '',
  `channel14` varchar(20) DEFAULT '',
  `channel15` varchar(20) DEFAULT '',
  `channel16` varchar(20) DEFAULT '',
  `channel17` varchar(20) DEFAULT '',
  `channel18` varchar(20) DEFAULT '',
  `channel19` varchar(20) DEFAULT '',
  `channel20` varchar(20) DEFAULT '',
  `channel21` varchar(20) DEFAULT '',
  `channel22` varchar(20) DEFAULT '',
  `channel23` varchar(20) DEFAULT '',
  `channel24` varchar(20) DEFAULT '',
  `channel25` varchar(20) DEFAULT '',
  `channel26` varchar(20) DEFAULT '',
  `channel27` varchar(20) DEFAULT '',
  `channel28` varchar(20) DEFAULT '',
  `channel29` varchar(20) DEFAULT '',
  `channel30` varchar(20) DEFAULT '',
  `channel31` varchar(20) DEFAULT '',
  `channel32` varchar(20) DEFAULT '',
  `channel33` varchar(20) DEFAULT '',
  `channel34` varchar(20) DEFAULT '',
  `channel35` varchar(20) DEFAULT '',
  `channel36` varchar(20) DEFAULT '',
  `channel37` varchar(20) DEFAULT '',
  `channel38` varchar(20) DEFAULT '',
  `channel39` varchar(20) DEFAULT '',
  `channel40` varchar(20) DEFAULT '',
  `channel41` varchar(20) DEFAULT '',
  `channel42` varchar(20) DEFAULT '',
  `channel43` varchar(20) DEFAULT '',
  `channel44` varchar(20) DEFAULT '',
  `channel45` varchar(20) DEFAULT '',
  `channel46` varchar(20) DEFAULT '',
  `channel47` varchar(20) DEFAULT '',
  `channel48` varchar(20) DEFAULT '',
  `channel49` varchar(20) DEFAULT '',
  `channel50` varchar(20) DEFAULT '',
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `T_VARTRIVER_10002045_index_infoDataTime` (`infoDataTime`)
) ENGINE=InnoDB AUTO_INCREMENT=4079 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_vartriver_10002118`
-- ----------------------------
DROP TABLE IF EXISTS `t_vartriver_10002118`;
CREATE TABLE `t_vartriver_10002118` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `deviceId` varchar(50) DEFAULT '',
  `infoDataTime` datetime DEFAULT NULL,
  `channel1` varchar(20) DEFAULT '',
  `channel2` varchar(20) DEFAULT '',
  `channel3` varchar(20) DEFAULT '',
  `channel4` varchar(20) DEFAULT '',
  `channel5` varchar(20) DEFAULT '',
  `channel6` varchar(20) DEFAULT '',
  `channel7` varchar(20) DEFAULT '',
  `channel8` varchar(20) DEFAULT '',
  `channel9` varchar(20) DEFAULT '',
  `channel10` varchar(20) DEFAULT '',
  `channel11` varchar(20) DEFAULT '',
  `channel12` varchar(20) DEFAULT '',
  `channel13` varchar(20) DEFAULT '',
  `channel14` varchar(20) DEFAULT '',
  `channel15` varchar(20) DEFAULT '',
  `channel16` varchar(20) DEFAULT '',
  `channel17` varchar(20) DEFAULT '',
  `channel18` varchar(20) DEFAULT '',
  `channel19` varchar(20) DEFAULT '',
  `channel20` varchar(20) DEFAULT '',
  `channel21` varchar(20) DEFAULT '',
  `channel22` varchar(20) DEFAULT '',
  `channel23` varchar(20) DEFAULT '',
  `channel24` varchar(20) DEFAULT '',
  `channel25` varchar(20) DEFAULT '',
  `channel26` varchar(20) DEFAULT '',
  `channel27` varchar(20) DEFAULT '',
  `channel28` varchar(20) DEFAULT '',
  `channel29` varchar(20) DEFAULT '',
  `channel30` varchar(20) DEFAULT '',
  `channel31` varchar(20) DEFAULT '',
  `channel32` varchar(20) DEFAULT '',
  `channel33` varchar(20) DEFAULT '',
  `channel34` varchar(20) DEFAULT '',
  `channel35` varchar(20) DEFAULT '',
  `channel36` varchar(20) DEFAULT '',
  `channel37` varchar(20) DEFAULT '',
  `channel38` varchar(20) DEFAULT '',
  `channel39` varchar(20) DEFAULT '',
  `channel40` varchar(20) DEFAULT '',
  `channel41` varchar(20) DEFAULT '',
  `channel42` varchar(20) DEFAULT '',
  `channel43` varchar(20) DEFAULT '',
  `channel44` varchar(20) DEFAULT '',
  `channel45` varchar(20) DEFAULT '',
  `channel46` varchar(20) DEFAULT '',
  `channel47` varchar(20) DEFAULT '',
  `channel48` varchar(20) DEFAULT '',
  `channel49` varchar(20) DEFAULT '',
  `channel50` varchar(20) DEFAULT '',
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `T_VARTRIVER_10002045_index_infoDataTime` (`infoDataTime`)
) ENGINE=InnoDB AUTO_INCREMENT=5075 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_vartriver_10002119`
-- ----------------------------
DROP TABLE IF EXISTS `t_vartriver_10002119`;
CREATE TABLE `t_vartriver_10002119` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `deviceId` varchar(50) DEFAULT '',
  `infoDataTime` datetime DEFAULT NULL,
  `channel1` varchar(20) DEFAULT '',
  `channel2` varchar(20) DEFAULT '',
  `channel3` varchar(20) DEFAULT '',
  `channel4` varchar(20) DEFAULT '',
  `channel5` varchar(20) DEFAULT '',
  `channel6` varchar(20) DEFAULT '',
  `channel7` varchar(20) DEFAULT '',
  `channel8` varchar(20) DEFAULT '',
  `channel9` varchar(20) DEFAULT '',
  `channel10` varchar(20) DEFAULT '',
  `channel11` varchar(20) DEFAULT '',
  `channel12` varchar(20) DEFAULT '',
  `channel13` varchar(20) DEFAULT '',
  `channel14` varchar(20) DEFAULT '',
  `channel15` varchar(20) DEFAULT '',
  `channel16` varchar(20) DEFAULT '',
  `channel17` varchar(20) DEFAULT '',
  `channel18` varchar(20) DEFAULT '',
  `channel19` varchar(20) DEFAULT '',
  `channel20` varchar(20) DEFAULT '',
  `channel21` varchar(20) DEFAULT '',
  `channel22` varchar(20) DEFAULT '',
  `channel23` varchar(20) DEFAULT '',
  `channel24` varchar(20) DEFAULT '',
  `channel25` varchar(20) DEFAULT '',
  `channel26` varchar(20) DEFAULT '',
  `channel27` varchar(20) DEFAULT '',
  `channel28` varchar(20) DEFAULT '',
  `channel29` varchar(20) DEFAULT '',
  `channel30` varchar(20) DEFAULT '',
  `channel31` varchar(20) DEFAULT '',
  `channel32` varchar(20) DEFAULT '',
  `channel33` varchar(20) DEFAULT '',
  `channel34` varchar(20) DEFAULT '',
  `channel35` varchar(20) DEFAULT '',
  `channel36` varchar(20) DEFAULT '',
  `channel37` varchar(20) DEFAULT '',
  `channel38` varchar(20) DEFAULT '',
  `channel39` varchar(20) DEFAULT '',
  `channel40` varchar(20) DEFAULT '',
  `channel41` varchar(20) DEFAULT '',
  `channel42` varchar(20) DEFAULT '',
  `channel43` varchar(20) DEFAULT '',
  `channel44` varchar(20) DEFAULT '',
  `channel45` varchar(20) DEFAULT '',
  `channel46` varchar(20) DEFAULT '',
  `channel47` varchar(20) DEFAULT '',
  `channel48` varchar(20) DEFAULT '',
  `channel49` varchar(20) DEFAULT '',
  `channel50` varchar(20) DEFAULT '',
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `T_VARTRIVER_10002045_index_infoDataTime` (`infoDataTime`)
) ENGINE=InnoDB AUTO_INCREMENT=2673 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_vartriver_10002121`
-- ----------------------------
DROP TABLE IF EXISTS `t_vartriver_10002121`;
CREATE TABLE `t_vartriver_10002121` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `deviceId` varchar(50) DEFAULT '',
  `infoDataTime` datetime DEFAULT NULL,
  `channel1` varchar(20) DEFAULT '',
  `channel2` varchar(20) DEFAULT '',
  `channel3` varchar(20) DEFAULT '',
  `channel4` varchar(20) DEFAULT '',
  `channel5` varchar(20) DEFAULT '',
  `channel6` varchar(20) DEFAULT '',
  `channel7` varchar(20) DEFAULT '',
  `channel8` varchar(20) DEFAULT '',
  `channel9` varchar(20) DEFAULT '',
  `channel10` varchar(20) DEFAULT '',
  `channel11` varchar(20) DEFAULT '',
  `channel12` varchar(20) DEFAULT '',
  `channel13` varchar(20) DEFAULT '',
  `channel14` varchar(20) DEFAULT '',
  `channel15` varchar(20) DEFAULT '',
  `channel16` varchar(20) DEFAULT '',
  `channel17` varchar(20) DEFAULT '',
  `channel18` varchar(20) DEFAULT '',
  `channel19` varchar(20) DEFAULT '',
  `channel20` varchar(20) DEFAULT '',
  `channel21` varchar(20) DEFAULT '',
  `channel22` varchar(20) DEFAULT '',
  `channel23` varchar(20) DEFAULT '',
  `channel24` varchar(20) DEFAULT '',
  `channel25` varchar(20) DEFAULT '',
  `channel26` varchar(20) DEFAULT '',
  `channel27` varchar(20) DEFAULT '',
  `channel28` varchar(20) DEFAULT '',
  `channel29` varchar(20) DEFAULT '',
  `channel30` varchar(20) DEFAULT '',
  `channel31` varchar(20) DEFAULT '',
  `channel32` varchar(20) DEFAULT '',
  `channel33` varchar(20) DEFAULT '',
  `channel34` varchar(20) DEFAULT '',
  `channel35` varchar(20) DEFAULT '',
  `channel36` varchar(20) DEFAULT '',
  `channel37` varchar(20) DEFAULT '',
  `channel38` varchar(20) DEFAULT '',
  `channel39` varchar(20) DEFAULT '',
  `channel40` varchar(20) DEFAULT '',
  `channel41` varchar(20) DEFAULT '',
  `channel42` varchar(20) DEFAULT '',
  `channel43` varchar(20) DEFAULT '',
  `channel44` varchar(20) DEFAULT '',
  `channel45` varchar(20) DEFAULT '',
  `channel46` varchar(20) DEFAULT '',
  `channel47` varchar(20) DEFAULT '',
  `channel48` varchar(20) DEFAULT '',
  `channel49` varchar(20) DEFAULT '',
  `channel50` varchar(20) DEFAULT '',
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `T_VARTRIVER_10002045_index_infoDataTime` (`infoDataTime`)
) ENGINE=InnoDB AUTO_INCREMENT=537 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_vartriver_10002122`
-- ----------------------------
DROP TABLE IF EXISTS `t_vartriver_10002122`;
CREATE TABLE `t_vartriver_10002122` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `deviceId` varchar(50) DEFAULT '',
  `infoDataTime` datetime DEFAULT NULL,
  `channel1` varchar(20) DEFAULT '',
  `channel2` varchar(20) DEFAULT '',
  `channel3` varchar(20) DEFAULT '',
  `channel4` varchar(20) DEFAULT '',
  `channel5` varchar(20) DEFAULT '',
  `channel6` varchar(20) DEFAULT '',
  `channel7` varchar(20) DEFAULT '',
  `channel8` varchar(20) DEFAULT '',
  `channel9` varchar(20) DEFAULT '',
  `channel10` varchar(20) DEFAULT '',
  `channel11` varchar(20) DEFAULT '',
  `channel12` varchar(20) DEFAULT '',
  `channel13` varchar(20) DEFAULT '',
  `channel14` varchar(20) DEFAULT '',
  `channel15` varchar(20) DEFAULT '',
  `channel16` varchar(20) DEFAULT '',
  `channel17` varchar(20) DEFAULT '',
  `channel18` varchar(20) DEFAULT '',
  `channel19` varchar(20) DEFAULT '',
  `channel20` varchar(20) DEFAULT '',
  `channel21` varchar(20) DEFAULT '',
  `channel22` varchar(20) DEFAULT '',
  `channel23` varchar(20) DEFAULT '',
  `channel24` varchar(20) DEFAULT '',
  `channel25` varchar(20) DEFAULT '',
  `channel26` varchar(20) DEFAULT '',
  `channel27` varchar(20) DEFAULT '',
  `channel28` varchar(20) DEFAULT '',
  `channel29` varchar(20) DEFAULT '',
  `channel30` varchar(20) DEFAULT '',
  `channel31` varchar(20) DEFAULT '',
  `channel32` varchar(20) DEFAULT '',
  `channel33` varchar(20) DEFAULT '',
  `channel34` varchar(20) DEFAULT '',
  `channel35` varchar(20) DEFAULT '',
  `channel36` varchar(20) DEFAULT '',
  `channel37` varchar(20) DEFAULT '',
  `channel38` varchar(20) DEFAULT '',
  `channel39` varchar(20) DEFAULT '',
  `channel40` varchar(20) DEFAULT '',
  `channel41` varchar(20) DEFAULT '',
  `channel42` varchar(20) DEFAULT '',
  `channel43` varchar(20) DEFAULT '',
  `channel44` varchar(20) DEFAULT '',
  `channel45` varchar(20) DEFAULT '',
  `channel46` varchar(20) DEFAULT '',
  `channel47` varchar(20) DEFAULT '',
  `channel48` varchar(20) DEFAULT '',
  `channel49` varchar(20) DEFAULT '',
  `channel50` varchar(20) DEFAULT '',
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `T_VARTRIVER_10002045_index_infoDataTime` (`infoDataTime`)
) ENGINE=InnoDB AUTO_INCREMENT=105401 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_vartriver_10002123`
-- ----------------------------
DROP TABLE IF EXISTS `t_vartriver_10002123`;
CREATE TABLE `t_vartriver_10002123` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `deviceId` varchar(50) DEFAULT '',
  `infoDataTime` datetime DEFAULT NULL,
  `channel1` varchar(20) DEFAULT '',
  `channel2` varchar(20) DEFAULT '',
  `channel3` varchar(20) DEFAULT '',
  `channel4` varchar(20) DEFAULT '',
  `channel5` varchar(20) DEFAULT '',
  `channel6` varchar(20) DEFAULT '',
  `channel7` varchar(20) DEFAULT '',
  `channel8` varchar(20) DEFAULT '',
  `channel9` varchar(20) DEFAULT '',
  `channel10` varchar(20) DEFAULT '',
  `channel11` varchar(20) DEFAULT '',
  `channel12` varchar(20) DEFAULT '',
  `channel13` varchar(20) DEFAULT '',
  `channel14` varchar(20) DEFAULT '',
  `channel15` varchar(20) DEFAULT '',
  `channel16` varchar(20) DEFAULT '',
  `channel17` varchar(20) DEFAULT '',
  `channel18` varchar(20) DEFAULT '',
  `channel19` varchar(20) DEFAULT '',
  `channel20` varchar(20) DEFAULT '',
  `channel21` varchar(20) DEFAULT '',
  `channel22` varchar(20) DEFAULT '',
  `channel23` varchar(20) DEFAULT '',
  `channel24` varchar(20) DEFAULT '',
  `channel25` varchar(20) DEFAULT '',
  `channel26` varchar(20) DEFAULT '',
  `channel27` varchar(20) DEFAULT '',
  `channel28` varchar(20) DEFAULT '',
  `channel29` varchar(20) DEFAULT '',
  `channel30` varchar(20) DEFAULT '',
  `channel31` varchar(20) DEFAULT '',
  `channel32` varchar(20) DEFAULT '',
  `channel33` varchar(20) DEFAULT '',
  `channel34` varchar(20) DEFAULT '',
  `channel35` varchar(20) DEFAULT '',
  `channel36` varchar(20) DEFAULT '',
  `channel37` varchar(20) DEFAULT '',
  `channel38` varchar(20) DEFAULT '',
  `channel39` varchar(20) DEFAULT '',
  `channel40` varchar(20) DEFAULT '',
  `channel41` varchar(20) DEFAULT '',
  `channel42` varchar(20) DEFAULT '',
  `channel43` varchar(20) DEFAULT '',
  `channel44` varchar(20) DEFAULT '',
  `channel45` varchar(20) DEFAULT '',
  `channel46` varchar(20) DEFAULT '',
  `channel47` varchar(20) DEFAULT '',
  `channel48` varchar(20) DEFAULT '',
  `channel49` varchar(20) DEFAULT '',
  `channel50` varchar(20) DEFAULT '',
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `T_VARTRIVER_10002045_index_infoDataTime` (`infoDataTime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_vartriver_10002124`
-- ----------------------------
DROP TABLE IF EXISTS `t_vartriver_10002124`;
CREATE TABLE `t_vartriver_10002124` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `deviceId` varchar(50) DEFAULT '',
  `infoDataTime` datetime DEFAULT NULL,
  `channel1` varchar(20) DEFAULT '',
  `channel2` varchar(20) DEFAULT '',
  `channel3` varchar(20) DEFAULT '',
  `channel4` varchar(20) DEFAULT '',
  `channel5` varchar(20) DEFAULT '',
  `channel6` varchar(20) DEFAULT '',
  `channel7` varchar(20) DEFAULT '',
  `channel8` varchar(20) DEFAULT '',
  `channel9` varchar(20) DEFAULT '',
  `channel10` varchar(20) DEFAULT '',
  `channel11` varchar(20) DEFAULT '',
  `channel12` varchar(20) DEFAULT '',
  `channel13` varchar(20) DEFAULT '',
  `channel14` varchar(20) DEFAULT '',
  `channel15` varchar(20) DEFAULT '',
  `channel16` varchar(20) DEFAULT '',
  `channel17` varchar(20) DEFAULT '',
  `channel18` varchar(20) DEFAULT '',
  `channel19` varchar(20) DEFAULT '',
  `channel20` varchar(20) DEFAULT '',
  `channel21` varchar(20) DEFAULT '',
  `channel22` varchar(20) DEFAULT '',
  `channel23` varchar(20) DEFAULT '',
  `channel24` varchar(20) DEFAULT '',
  `channel25` varchar(20) DEFAULT '',
  `channel26` varchar(20) DEFAULT '',
  `channel27` varchar(20) DEFAULT '',
  `channel28` varchar(20) DEFAULT '',
  `channel29` varchar(20) DEFAULT '',
  `channel30` varchar(20) DEFAULT '',
  `channel31` varchar(20) DEFAULT '',
  `channel32` varchar(20) DEFAULT '',
  `channel33` varchar(20) DEFAULT '',
  `channel34` varchar(20) DEFAULT '',
  `channel35` varchar(20) DEFAULT '',
  `channel36` varchar(20) DEFAULT '',
  `channel37` varchar(20) DEFAULT '',
  `channel38` varchar(20) DEFAULT '',
  `channel39` varchar(20) DEFAULT '',
  `channel40` varchar(20) DEFAULT '',
  `channel41` varchar(20) DEFAULT '',
  `channel42` varchar(20) DEFAULT '',
  `channel43` varchar(20) DEFAULT '',
  `channel44` varchar(20) DEFAULT '',
  `channel45` varchar(20) DEFAULT '',
  `channel46` varchar(20) DEFAULT '',
  `channel47` varchar(20) DEFAULT '',
  `channel48` varchar(20) DEFAULT '',
  `channel49` varchar(20) DEFAULT '',
  `channel50` varchar(20) DEFAULT '',
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `T_VARTRIVER_10002045_index_infoDataTime` (`infoDataTime`)
) ENGINE=InnoDB AUTO_INCREMENT=114437 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_vartriver_10002126`
-- ----------------------------
DROP TABLE IF EXISTS `t_vartriver_10002126`;
CREATE TABLE `t_vartriver_10002126` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `deviceId` varchar(50) DEFAULT '',
  `infoDataTime` datetime DEFAULT NULL,
  `channel1` varchar(20) DEFAULT '',
  `channel2` varchar(20) DEFAULT '',
  `channel3` varchar(20) DEFAULT '',
  `channel4` varchar(20) DEFAULT '',
  `channel5` varchar(20) DEFAULT '',
  `channel6` varchar(20) DEFAULT '',
  `channel7` varchar(20) DEFAULT '',
  `channel8` varchar(20) DEFAULT '',
  `channel9` varchar(20) DEFAULT '',
  `channel10` varchar(20) DEFAULT '',
  `channel11` varchar(20) DEFAULT '',
  `channel12` varchar(20) DEFAULT '',
  `channel13` varchar(20) DEFAULT '',
  `channel14` varchar(20) DEFAULT '',
  `channel15` varchar(20) DEFAULT '',
  `channel16` varchar(20) DEFAULT '',
  `channel17` varchar(20) DEFAULT '',
  `channel18` varchar(20) DEFAULT '',
  `channel19` varchar(20) DEFAULT '',
  `channel20` varchar(20) DEFAULT '',
  `channel21` varchar(20) DEFAULT '',
  `channel22` varchar(20) DEFAULT '',
  `channel23` varchar(20) DEFAULT '',
  `channel24` varchar(20) DEFAULT '',
  `channel25` varchar(20) DEFAULT '',
  `channel26` varchar(20) DEFAULT '',
  `channel27` varchar(20) DEFAULT '',
  `channel28` varchar(20) DEFAULT '',
  `channel29` varchar(20) DEFAULT '',
  `channel30` varchar(20) DEFAULT '',
  `channel31` varchar(20) DEFAULT '',
  `channel32` varchar(20) DEFAULT '',
  `channel33` varchar(20) DEFAULT '',
  `channel34` varchar(20) DEFAULT '',
  `channel35` varchar(20) DEFAULT '',
  `channel36` varchar(20) DEFAULT '',
  `channel37` varchar(20) DEFAULT '',
  `channel38` varchar(20) DEFAULT '',
  `channel39` varchar(20) DEFAULT '',
  `channel40` varchar(20) DEFAULT '',
  `channel41` varchar(20) DEFAULT '',
  `channel42` varchar(20) DEFAULT '',
  `channel43` varchar(20) DEFAULT '',
  `channel44` varchar(20) DEFAULT '',
  `channel45` varchar(20) DEFAULT '',
  `channel46` varchar(20) DEFAULT '',
  `channel47` varchar(20) DEFAULT '',
  `channel48` varchar(20) DEFAULT '',
  `channel49` varchar(20) DEFAULT '',
  `channel50` varchar(20) DEFAULT '',
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `T_VARTRIVER_10002045_index_infoDataTime` (`infoDataTime`)
) ENGINE=InnoDB AUTO_INCREMENT=5479 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_vartriver_10002127`
-- ----------------------------
DROP TABLE IF EXISTS `t_vartriver_10002127`;
CREATE TABLE `t_vartriver_10002127` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `deviceId` varchar(50) DEFAULT '',
  `infoDataTime` datetime DEFAULT NULL,
  `channel1` varchar(20) DEFAULT '',
  `channel2` varchar(20) DEFAULT '',
  `channel3` varchar(20) DEFAULT '',
  `channel4` varchar(20) DEFAULT '',
  `channel5` varchar(20) DEFAULT '',
  `channel6` varchar(20) DEFAULT '',
  `channel7` varchar(20) DEFAULT '',
  `channel8` varchar(20) DEFAULT '',
  `channel9` varchar(20) DEFAULT '',
  `channel10` varchar(20) DEFAULT '',
  `channel11` varchar(20) DEFAULT '',
  `channel12` varchar(20) DEFAULT '',
  `channel13` varchar(20) DEFAULT '',
  `channel14` varchar(20) DEFAULT '',
  `channel15` varchar(20) DEFAULT '',
  `channel16` varchar(20) DEFAULT '',
  `channel17` varchar(20) DEFAULT '',
  `channel18` varchar(20) DEFAULT '',
  `channel19` varchar(20) DEFAULT '',
  `channel20` varchar(20) DEFAULT '',
  `channel21` varchar(20) DEFAULT '',
  `channel22` varchar(20) DEFAULT '',
  `channel23` varchar(20) DEFAULT '',
  `channel24` varchar(20) DEFAULT '',
  `channel25` varchar(20) DEFAULT '',
  `channel26` varchar(20) DEFAULT '',
  `channel27` varchar(20) DEFAULT '',
  `channel28` varchar(20) DEFAULT '',
  `channel29` varchar(20) DEFAULT '',
  `channel30` varchar(20) DEFAULT '',
  `channel31` varchar(20) DEFAULT '',
  `channel32` varchar(20) DEFAULT '',
  `channel33` varchar(20) DEFAULT '',
  `channel34` varchar(20) DEFAULT '',
  `channel35` varchar(20) DEFAULT '',
  `channel36` varchar(20) DEFAULT '',
  `channel37` varchar(20) DEFAULT '',
  `channel38` varchar(20) DEFAULT '',
  `channel39` varchar(20) DEFAULT '',
  `channel40` varchar(20) DEFAULT '',
  `channel41` varchar(20) DEFAULT '',
  `channel42` varchar(20) DEFAULT '',
  `channel43` varchar(20) DEFAULT '',
  `channel44` varchar(20) DEFAULT '',
  `channel45` varchar(20) DEFAULT '',
  `channel46` varchar(20) DEFAULT '',
  `channel47` varchar(20) DEFAULT '',
  `channel48` varchar(20) DEFAULT '',
  `channel49` varchar(20) DEFAULT '',
  `channel50` varchar(20) DEFAULT '',
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `T_VARTRIVER_10002045_index_infoDataTime` (`infoDataTime`)
) ENGINE=InnoDB AUTO_INCREMENT=114712 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_vartriver_10002128`
-- ----------------------------
DROP TABLE IF EXISTS `t_vartriver_10002128`;
CREATE TABLE `t_vartriver_10002128` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `deviceId` varchar(50) DEFAULT '',
  `infoDataTime` datetime DEFAULT NULL,
  `channel1` varchar(20) DEFAULT '',
  `channel2` varchar(20) DEFAULT '',
  `channel3` varchar(20) DEFAULT '',
  `channel4` varchar(20) DEFAULT '',
  `channel5` varchar(20) DEFAULT '',
  `channel6` varchar(20) DEFAULT '',
  `channel7` varchar(20) DEFAULT '',
  `channel8` varchar(20) DEFAULT '',
  `channel9` varchar(20) DEFAULT '',
  `channel10` varchar(20) DEFAULT '',
  `channel11` varchar(20) DEFAULT '',
  `channel12` varchar(20) DEFAULT '',
  `channel13` varchar(20) DEFAULT '',
  `channel14` varchar(20) DEFAULT '',
  `channel15` varchar(20) DEFAULT '',
  `channel16` varchar(20) DEFAULT '',
  `channel17` varchar(20) DEFAULT '',
  `channel18` varchar(20) DEFAULT '',
  `channel19` varchar(20) DEFAULT '',
  `channel20` varchar(20) DEFAULT '',
  `channel21` varchar(20) DEFAULT '',
  `channel22` varchar(20) DEFAULT '',
  `channel23` varchar(20) DEFAULT '',
  `channel24` varchar(20) DEFAULT '',
  `channel25` varchar(20) DEFAULT '',
  `channel26` varchar(20) DEFAULT '',
  `channel27` varchar(20) DEFAULT '',
  `channel28` varchar(20) DEFAULT '',
  `channel29` varchar(20) DEFAULT '',
  `channel30` varchar(20) DEFAULT '',
  `channel31` varchar(20) DEFAULT '',
  `channel32` varchar(20) DEFAULT '',
  `channel33` varchar(20) DEFAULT '',
  `channel34` varchar(20) DEFAULT '',
  `channel35` varchar(20) DEFAULT '',
  `channel36` varchar(20) DEFAULT '',
  `channel37` varchar(20) DEFAULT '',
  `channel38` varchar(20) DEFAULT '',
  `channel39` varchar(20) DEFAULT '',
  `channel40` varchar(20) DEFAULT '',
  `channel41` varchar(20) DEFAULT '',
  `channel42` varchar(20) DEFAULT '',
  `channel43` varchar(20) DEFAULT '',
  `channel44` varchar(20) DEFAULT '',
  `channel45` varchar(20) DEFAULT '',
  `channel46` varchar(20) DEFAULT '',
  `channel47` varchar(20) DEFAULT '',
  `channel48` varchar(20) DEFAULT '',
  `channel49` varchar(20) DEFAULT '',
  `channel50` varchar(20) DEFAULT '',
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `T_VARTRIVER_10002045_index_infoDataTime` (`infoDataTime`)
) ENGINE=InnoDB AUTO_INCREMENT=2127 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_vartriver_10002130`
-- ----------------------------
DROP TABLE IF EXISTS `t_vartriver_10002130`;
CREATE TABLE `t_vartriver_10002130` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '记录id',
  `deviceId` varchar(50) DEFAULT '' COMMENT '设备id',
  `infoDataTime` datetime DEFAULT NULL COMMENT '上报时间',
  `channel1` varchar(20) DEFAULT '' COMMENT '上报的channel',
  `channel2` varchar(20) DEFAULT '',
  `channel3` varchar(20) DEFAULT '',
  `channel4` varchar(20) DEFAULT '',
  `channel5` varchar(20) DEFAULT '',
  `channel6` varchar(20) DEFAULT '',
  `channel7` varchar(20) DEFAULT '',
  `channel8` varchar(20) DEFAULT '',
  `channel9` varchar(20) DEFAULT '',
  `channel10` varchar(20) DEFAULT '',
  `channel11` varchar(20) DEFAULT '',
  `channel12` varchar(20) DEFAULT '',
  `channel13` varchar(20) DEFAULT '',
  `channel14` varchar(20) DEFAULT '',
  `channel15` varchar(20) DEFAULT '',
  `channel16` varchar(20) DEFAULT '',
  `channel17` varchar(20) DEFAULT '',
  `channel18` varchar(20) DEFAULT '',
  `channel19` varchar(20) DEFAULT '',
  `channel20` varchar(20) DEFAULT '',
  `channel21` varchar(20) DEFAULT '',
  `channel22` varchar(20) DEFAULT '',
  `channel23` varchar(20) DEFAULT '',
  `channel24` varchar(20) DEFAULT '',
  `channel25` varchar(20) DEFAULT '',
  `channel26` varchar(20) DEFAULT '',
  `channel27` varchar(20) DEFAULT '',
  `channel28` varchar(20) DEFAULT '',
  `channel29` varchar(20) DEFAULT '',
  `channel30` varchar(20) DEFAULT '',
  `channel31` varchar(20) DEFAULT '',
  `channel32` varchar(20) DEFAULT '',
  `channel33` varchar(20) DEFAULT '',
  `channel34` varchar(20) DEFAULT '',
  `channel35` varchar(20) DEFAULT '',
  `channel36` varchar(20) DEFAULT '',
  `channel37` varchar(20) DEFAULT '',
  `channel38` varchar(20) DEFAULT '',
  `channel39` varchar(20) DEFAULT '',
  `channel40` varchar(20) DEFAULT '',
  `channel41` varchar(20) DEFAULT '',
  `channel42` varchar(20) DEFAULT '',
  `channel43` varchar(20) DEFAULT '',
  `channel44` varchar(20) DEFAULT '',
  `channel45` varchar(20) DEFAULT '',
  `channel46` varchar(20) DEFAULT '',
  `channel47` varchar(20) DEFAULT '',
  `channel48` varchar(20) DEFAULT '',
  `channel49` varchar(20) DEFAULT '',
  `channel50` varchar(20) DEFAULT '',
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `T_VARTRIVER_10002045_index_infoDataTime` (`infoDataTime`)
) ENGINE=InnoDB AUTO_INCREMENT=3629 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_vartriver_10002132`
-- ----------------------------
DROP TABLE IF EXISTS `t_vartriver_10002132`;
CREATE TABLE `t_vartriver_10002132` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `deviceId` varchar(50) DEFAULT '',
  `infoDataTime` datetime DEFAULT NULL,
  `channel1` varchar(20) DEFAULT '',
  `channel2` varchar(20) DEFAULT '',
  `channel3` varchar(20) DEFAULT '',
  `channel4` varchar(20) DEFAULT '',
  `channel5` varchar(20) DEFAULT '',
  `channel6` varchar(20) DEFAULT '',
  `channel7` varchar(20) DEFAULT '',
  `channel8` varchar(20) DEFAULT '',
  `channel9` varchar(20) DEFAULT '',
  `channel10` varchar(20) DEFAULT '',
  `channel11` varchar(20) DEFAULT '',
  `channel12` varchar(20) DEFAULT '',
  `channel13` varchar(20) DEFAULT '',
  `channel14` varchar(20) DEFAULT '',
  `channel15` varchar(20) DEFAULT '',
  `channel16` varchar(20) DEFAULT '',
  `channel17` varchar(20) DEFAULT '',
  `channel18` varchar(20) DEFAULT '',
  `channel19` varchar(20) DEFAULT '',
  `channel20` varchar(20) DEFAULT '',
  `channel21` varchar(20) DEFAULT '',
  `channel22` varchar(20) DEFAULT '',
  `channel23` varchar(20) DEFAULT '',
  `channel24` varchar(20) DEFAULT '',
  `channel25` varchar(20) DEFAULT '',
  `channel26` varchar(20) DEFAULT '',
  `channel27` varchar(20) DEFAULT '',
  `channel28` varchar(20) DEFAULT '',
  `channel29` varchar(20) DEFAULT '',
  `channel30` varchar(20) DEFAULT '',
  `channel31` varchar(20) DEFAULT '',
  `channel32` varchar(20) DEFAULT '',
  `channel33` varchar(20) DEFAULT '',
  `channel34` varchar(20) DEFAULT '',
  `channel35` varchar(20) DEFAULT '',
  `channel36` varchar(20) DEFAULT '',
  `channel37` varchar(20) DEFAULT '',
  `channel38` varchar(20) DEFAULT '',
  `channel39` varchar(20) DEFAULT '',
  `channel40` varchar(20) DEFAULT '',
  `channel41` varchar(20) DEFAULT '',
  `channel42` varchar(20) DEFAULT '',
  `channel43` varchar(20) DEFAULT '',
  `channel44` varchar(20) DEFAULT '',
  `channel45` varchar(20) DEFAULT '',
  `channel46` varchar(20) DEFAULT '',
  `channel47` varchar(20) DEFAULT '',
  `channel48` varchar(20) DEFAULT '',
  `channel49` varchar(20) DEFAULT '',
  `channel50` varchar(20) DEFAULT '',
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `T_VARTRIVER_10002045_index_infoDataTime` (`infoDataTime`)
) ENGINE=InnoDB AUTO_INCREMENT=3890 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_vartriver_10002133`
-- ----------------------------
DROP TABLE IF EXISTS `t_vartriver_10002133`;
CREATE TABLE `t_vartriver_10002133` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `deviceId` varchar(50) DEFAULT '',
  `infoDataTime` datetime DEFAULT NULL,
  `channel1` varchar(20) DEFAULT '',
  `channel2` varchar(20) DEFAULT '',
  `channel3` varchar(20) DEFAULT '',
  `channel4` varchar(20) DEFAULT '',
  `channel5` varchar(20) DEFAULT '',
  `channel6` varchar(20) DEFAULT '',
  `channel7` varchar(20) DEFAULT '',
  `channel8` varchar(20) DEFAULT '',
  `channel9` varchar(20) DEFAULT '',
  `channel10` varchar(20) DEFAULT '',
  `channel11` varchar(20) DEFAULT '',
  `channel12` varchar(20) DEFAULT '',
  `channel13` varchar(20) DEFAULT '',
  `channel14` varchar(20) DEFAULT '',
  `channel15` varchar(20) DEFAULT '',
  `channel16` varchar(20) DEFAULT '',
  `channel17` varchar(20) DEFAULT '',
  `channel18` varchar(20) DEFAULT '',
  `channel19` varchar(20) DEFAULT '',
  `channel20` varchar(20) DEFAULT '',
  `channel21` varchar(20) DEFAULT '',
  `channel22` varchar(20) DEFAULT '',
  `channel23` varchar(20) DEFAULT '',
  `channel24` varchar(20) DEFAULT '',
  `channel25` varchar(20) DEFAULT '',
  `channel26` varchar(20) DEFAULT '',
  `channel27` varchar(20) DEFAULT '',
  `channel28` varchar(20) DEFAULT '',
  `channel29` varchar(20) DEFAULT '',
  `channel30` varchar(20) DEFAULT '',
  `channel31` varchar(20) DEFAULT '',
  `channel32` varchar(20) DEFAULT '',
  `channel33` varchar(20) DEFAULT '',
  `channel34` varchar(20) DEFAULT '',
  `channel35` varchar(20) DEFAULT '',
  `channel36` varchar(20) DEFAULT '',
  `channel37` varchar(20) DEFAULT '',
  `channel38` varchar(20) DEFAULT '',
  `channel39` varchar(20) DEFAULT '',
  `channel40` varchar(20) DEFAULT '',
  `channel41` varchar(20) DEFAULT '',
  `channel42` varchar(20) DEFAULT '',
  `channel43` varchar(20) DEFAULT '',
  `channel44` varchar(20) DEFAULT '',
  `channel45` varchar(20) DEFAULT '',
  `channel46` varchar(20) DEFAULT '',
  `channel47` varchar(20) DEFAULT '',
  `channel48` varchar(20) DEFAULT '',
  `channel49` varchar(20) DEFAULT '',
  `channel50` varchar(20) DEFAULT '',
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `T_VARTRIVER_10002045_index_infoDataTime` (`infoDataTime`)
) ENGINE=InnoDB AUTO_INCREMENT=5140 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_vartriver_10002173`
-- ----------------------------
DROP TABLE IF EXISTS `t_vartriver_10002173`;
CREATE TABLE `t_vartriver_10002173` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `deviceId` varchar(50) DEFAULT '',
  `infoDataTime` datetime DEFAULT NULL,
  `channel1` varchar(20) DEFAULT '',
  `channel2` varchar(20) DEFAULT '',
  `channel3` varchar(20) DEFAULT '',
  `channel4` varchar(20) DEFAULT '',
  `channel5` varchar(20) DEFAULT '',
  `channel6` varchar(20) DEFAULT '',
  `channel7` varchar(20) DEFAULT '',
  `channel8` varchar(20) DEFAULT '',
  `channel9` varchar(20) DEFAULT '',
  `channel10` varchar(20) DEFAULT '',
  `channel11` varchar(20) DEFAULT '',
  `channel12` varchar(20) DEFAULT '',
  `channel13` varchar(20) DEFAULT '',
  `channel14` varchar(20) DEFAULT '',
  `channel15` varchar(20) DEFAULT '',
  `channel16` varchar(20) DEFAULT '',
  `channel17` varchar(20) DEFAULT '',
  `channel18` varchar(20) DEFAULT '',
  `channel19` varchar(20) DEFAULT '',
  `channel20` varchar(20) DEFAULT '',
  `channel21` varchar(20) DEFAULT '',
  `channel22` varchar(20) DEFAULT '',
  `channel23` varchar(20) DEFAULT '',
  `channel24` varchar(20) DEFAULT '',
  `channel25` varchar(20) DEFAULT '',
  `channel26` varchar(20) DEFAULT '',
  `channel27` varchar(20) DEFAULT '',
  `channel28` varchar(20) DEFAULT '',
  `channel29` varchar(20) DEFAULT '',
  `channel30` varchar(20) DEFAULT '',
  `channel31` varchar(20) DEFAULT '',
  `channel32` varchar(20) DEFAULT '',
  `channel33` varchar(20) DEFAULT '',
  `channel34` varchar(20) DEFAULT '',
  `channel35` varchar(20) DEFAULT '',
  `channel36` varchar(20) DEFAULT '',
  `channel37` varchar(20) DEFAULT '',
  `channel38` varchar(20) DEFAULT '',
  `channel39` varchar(20) DEFAULT '',
  `channel40` varchar(20) DEFAULT '',
  `channel41` varchar(20) DEFAULT '',
  `channel42` varchar(20) DEFAULT '',
  `channel43` varchar(20) DEFAULT '',
  `channel44` varchar(20) DEFAULT '',
  `channel45` varchar(20) DEFAULT '',
  `channel46` varchar(20) DEFAULT '',
  `channel47` varchar(20) DEFAULT '',
  `channel48` varchar(20) DEFAULT '',
  `channel49` varchar(20) DEFAULT '',
  `channel50` varchar(20) DEFAULT '',
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `T_VARTRIVER_10002045_index_infoDataTime` (`infoDataTime`)
) ENGINE=InnoDB AUTO_INCREMENT=3165 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_vartriver_10002182`
-- ----------------------------
DROP TABLE IF EXISTS `t_vartriver_10002182`;
CREATE TABLE `t_vartriver_10002182` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `deviceId` varchar(50) DEFAULT '',
  `infoDataTime` datetime DEFAULT NULL,
  `channel1` varchar(20) DEFAULT '',
  `channel2` varchar(20) DEFAULT '',
  `channel3` varchar(20) DEFAULT '',
  `channel4` varchar(20) DEFAULT '',
  `channel5` varchar(20) DEFAULT '',
  `channel6` varchar(20) DEFAULT '',
  `channel7` varchar(20) DEFAULT '',
  `channel8` varchar(20) DEFAULT '',
  `channel9` varchar(20) DEFAULT '',
  `channel10` varchar(20) DEFAULT '',
  `channel11` varchar(20) DEFAULT '',
  `channel12` varchar(20) DEFAULT '',
  `channel13` varchar(20) DEFAULT '',
  `channel14` varchar(20) DEFAULT '',
  `channel15` varchar(20) DEFAULT '',
  `channel16` varchar(20) DEFAULT '',
  `channel17` varchar(20) DEFAULT '',
  `channel18` varchar(20) DEFAULT '',
  `channel19` varchar(20) DEFAULT '',
  `channel20` varchar(20) DEFAULT '',
  `channel21` varchar(20) DEFAULT '',
  `channel22` varchar(20) DEFAULT '',
  `channel23` varchar(20) DEFAULT '',
  `channel24` varchar(20) DEFAULT '',
  `channel25` varchar(20) DEFAULT '',
  `channel26` varchar(20) DEFAULT '',
  `channel27` varchar(20) DEFAULT '',
  `channel28` varchar(20) DEFAULT '',
  `channel29` varchar(20) DEFAULT '',
  `channel30` varchar(20) DEFAULT '',
  `channel31` varchar(20) DEFAULT '',
  `channel32` varchar(20) DEFAULT '',
  `channel33` varchar(20) DEFAULT '',
  `channel34` varchar(20) DEFAULT '',
  `channel35` varchar(20) DEFAULT '',
  `channel36` varchar(20) DEFAULT '',
  `channel37` varchar(20) DEFAULT '',
  `channel38` varchar(20) DEFAULT '',
  `channel39` varchar(20) DEFAULT '',
  `channel40` varchar(20) DEFAULT '',
  `channel41` varchar(20) DEFAULT '',
  `channel42` varchar(20) DEFAULT '',
  `channel43` varchar(20) DEFAULT '',
  `channel44` varchar(20) DEFAULT '',
  `channel45` varchar(20) DEFAULT '',
  `channel46` varchar(20) DEFAULT '',
  `channel47` varchar(20) DEFAULT '',
  `channel48` varchar(20) DEFAULT '',
  `channel49` varchar(20) DEFAULT '',
  `channel50` varchar(20) DEFAULT '',
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `T_VARTRIVER_10002045_index_infoDataTime` (`infoDataTime`)
) ENGINE=InnoDB AUTO_INCREMENT=1858 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_vartriver_11111111`
-- ----------------------------
DROP TABLE IF EXISTS `t_vartriver_11111111`;
CREATE TABLE `t_vartriver_11111111` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '记录id',
  `deviceId` varchar(50) DEFAULT '' COMMENT '设备id',
  `infoDataTime` datetime DEFAULT NULL COMMENT '上报时间',
  `channel1` varchar(20) DEFAULT '' COMMENT '上报的channel',
  `channel2` varchar(20) DEFAULT '',
  `channel3` varchar(20) DEFAULT '',
  `channel4` varchar(20) DEFAULT '',
  `channel5` varchar(20) DEFAULT '',
  `channel6` varchar(20) DEFAULT '',
  `channel7` varchar(20) DEFAULT '',
  `channel8` varchar(20) DEFAULT '',
  `channel9` varchar(20) DEFAULT '',
  `channel10` varchar(20) DEFAULT '',
  `channel11` varchar(20) DEFAULT '',
  `channel12` varchar(20) DEFAULT '',
  `channel13` varchar(20) DEFAULT '',
  `channel14` varchar(20) DEFAULT '',
  `channel15` varchar(20) DEFAULT '',
  `channel16` varchar(20) DEFAULT '',
  `channel17` varchar(20) DEFAULT '',
  `channel18` varchar(20) DEFAULT '',
  `channel19` varchar(20) DEFAULT '',
  `channel20` varchar(20) DEFAULT '',
  `channel21` varchar(20) DEFAULT '',
  `channel22` varchar(20) DEFAULT '',
  `channel23` varchar(20) DEFAULT '',
  `channel24` varchar(20) DEFAULT '',
  `channel25` varchar(20) DEFAULT '',
  `channel26` varchar(20) DEFAULT '',
  `channel27` varchar(20) DEFAULT '',
  `channel28` varchar(20) DEFAULT '',
  `channel29` varchar(20) DEFAULT '',
  `channel30` varchar(20) DEFAULT '',
  `channel31` varchar(20) DEFAULT '',
  `channel32` varchar(20) DEFAULT '',
  `channel33` varchar(20) DEFAULT '',
  `channel34` varchar(20) DEFAULT '',
  `channel35` varchar(20) DEFAULT '',
  `channel36` varchar(20) DEFAULT '',
  `channel37` varchar(20) DEFAULT '',
  `channel38` varchar(20) DEFAULT '',
  `channel39` varchar(20) DEFAULT '',
  `channel40` varchar(20) DEFAULT '',
  `channel41` varchar(20) DEFAULT '',
  `channel42` varchar(20) DEFAULT '',
  `channel43` varchar(20) DEFAULT '',
  `channel44` varchar(20) DEFAULT '',
  `channel45` varchar(20) DEFAULT '',
  `channel46` varchar(20) DEFAULT '',
  `channel47` varchar(20) DEFAULT '',
  `channel48` varchar(20) DEFAULT '',
  `channel49` varchar(20) DEFAULT '',
  `channel50` varchar(20) DEFAULT '',
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `T_VARTRIVER_10002045_index_infoDataTime` (`infoDataTime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_vartriver_alarm`
-- ----------------------------
DROP TABLE IF EXISTS `t_vartriver_alarm`;
CREATE TABLE `t_vartriver_alarm` (
  `alarmId` varchar(42) NOT NULL DEFAULT '',
  `alarmName` varchar(100) DEFAULT '',
  `prevRunTime` datetime DEFAULT NULL,
  `alarmStatus` smallint(6) DEFAULT '0',
  `createTime` datetime DEFAULT NULL,
  `userId` varchar(42) DEFAULT '',
  PRIMARY KEY (`alarmId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_vartriver_alarm_emailtemp`
-- ----------------------------
DROP TABLE IF EXISTS `t_vartriver_alarm_emailtemp`;
CREATE TABLE `t_vartriver_alarm_emailtemp` (
  `emailTempId` varchar(42) NOT NULL DEFAULT '',
  `eventId` varchar(42) DEFAULT '',
  `orderBy` varchar(42) DEFAULT '',
  `name` varchar(200) DEFAULT '',
  `content` longtext,
  `userId` varchar(42) DEFAULT '',
  `defaultTemp` smallint(6) DEFAULT '0',
  PRIMARY KEY (`emailTempId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_vartriver_alarm_event`
-- ----------------------------
DROP TABLE IF EXISTS `t_vartriver_alarm_event`;
CREATE TABLE `t_vartriver_alarm_event` (
  `eventId` varchar(42) NOT NULL DEFAULT '',
  `orderBy` varchar(42) DEFAULT '',
  `name` varchar(100) DEFAULT '',
  `levelType` varchar(1) DEFAULT '',
  `ruleType` int(11) DEFAULT '0',
  PRIMARY KEY (`eventId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_vartriver_alarm_limitnum`
-- ----------------------------
DROP TABLE IF EXISTS `t_vartriver_alarm_limitnum`;
CREATE TABLE `t_vartriver_alarm_limitnum` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `target` varchar(50) DEFAULT '',
  `ruleId` varchar(42) DEFAULT '',
  `deviceId` varchar(42) DEFAULT '',
  `num` int(11) DEFAULT '0',
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_vartriver_alarm_notetemp`
-- ----------------------------
DROP TABLE IF EXISTS `t_vartriver_alarm_notetemp`;
CREATE TABLE `t_vartriver_alarm_notetemp` (
  `noteTempId` varchar(42) NOT NULL DEFAULT '',
  `eventId` varchar(42) DEFAULT '',
  `orderBy` varchar(42) DEFAULT '',
  `name` varchar(200) DEFAULT '',
  `content` varchar(200) DEFAULT '',
  `userId` varchar(42) DEFAULT '',
  `remark` varchar(42) DEFAULT '',
  `defaultTemp` smallint(6) DEFAULT '0',
  PRIMARY KEY (`noteTempId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_vartriver_alarm_pagetemp`
-- ----------------------------
DROP TABLE IF EXISTS `t_vartriver_alarm_pagetemp`;
CREATE TABLE `t_vartriver_alarm_pagetemp` (
  `pageTempId` varchar(42) NOT NULL DEFAULT '',
  `eventId` varchar(42) DEFAULT '',
  `orderBy` varchar(42) DEFAULT '',
  `name` varchar(200) DEFAULT '',
  `content` text,
  `userId` varchar(42) DEFAULT '',
  `defaultTemp` smallint(6) DEFAULT '0',
  PRIMARY KEY (`pageTempId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_vartriver_alarm_recipient`
-- ----------------------------
DROP TABLE IF EXISTS `t_vartriver_alarm_recipient`;
CREATE TABLE `t_vartriver_alarm_recipient` (
  `recId` varchar(42) NOT NULL DEFAULT '',
  `alarmId` varchar(42) DEFAULT '',
  `name` varchar(100) DEFAULT '',
  `mobile` varchar(50) DEFAULT '',
  `email` varchar(100) DEFAULT '',
  `status` smallint(6) DEFAULT '0',
  PRIMARY KEY (`recId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_vartriver_alarm_rule`
-- ----------------------------
DROP TABLE IF EXISTS `t_vartriver_alarm_rule`;
CREATE TABLE `t_vartriver_alarm_rule` (
  `ruleId` varchar(42) NOT NULL DEFAULT '',
  `eventId` varchar(42) DEFAULT '',
  `name` varchar(100) DEFAULT '',
  `levelNum` int(11) DEFAULT '0',
  `ruleType` int(11) DEFAULT '0',
  `limitTime` int(11) DEFAULT '0',
  `lowerLimit` double DEFAULT '0',
  `upperLimit` double DEFAULT '0',
  `noteTempId` varchar(42) DEFAULT '',
  `soundTempId` varchar(42) DEFAULT '',
  `voiceTempId` varchar(42) DEFAULT '',
  `emailTempId` varchar(42) DEFAULT '',
  `pageTempId` varchar(42) DEFAULT '',
  PRIMARY KEY (`ruleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_vartriver_alarm_soundtemp`
-- ----------------------------
DROP TABLE IF EXISTS `t_vartriver_alarm_soundtemp`;
CREATE TABLE `t_vartriver_alarm_soundtemp` (
  `soundTempId` varchar(42) NOT NULL DEFAULT '',
  `eventId` varchar(42) DEFAULT '',
  `orderBy` varchar(42) DEFAULT '',
  `name` varchar(200) DEFAULT '',
  `soundFile` varchar(100) DEFAULT '',
  `userId` varchar(42) DEFAULT '',
  `defaultTemp` smallint(6) DEFAULT '0',
  PRIMARY KEY (`soundTempId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_vartriver_alarm_triggercontainer`
-- ----------------------------
DROP TABLE IF EXISTS `t_vartriver_alarm_triggercontainer`;
CREATE TABLE `t_vartriver_alarm_triggercontainer` (
  `id` varchar(60) NOT NULL DEFAULT '',
  `content` longtext,
  `target` varchar(50) DEFAULT '',
  `infoType` int(11) DEFAULT '0',
  `sendNum` int(11) DEFAULT '0',
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_vartriver_alarm_triggercontainer_log`
-- ----------------------------
DROP TABLE IF EXISTS `t_vartriver_alarm_triggercontainer_log`;
CREATE TABLE `t_vartriver_alarm_triggercontainer_log` (
  `id` varchar(60) NOT NULL DEFAULT '',
  `content` longtext,
  `target` varchar(50) DEFAULT '',
  `infoType` int(11) DEFAULT '0',
  `sendNum` int(11) DEFAULT '0',
  `status` smallint(6) DEFAULT '0',
  `sentTime` datetime DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `clearTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_vartriver_alarm_voicetemp`
-- ----------------------------
DROP TABLE IF EXISTS `t_vartriver_alarm_voicetemp`;
CREATE TABLE `t_vartriver_alarm_voicetemp` (
  `voiceTempId` varchar(42) NOT NULL DEFAULT '',
  `eventId` varchar(42) DEFAULT '',
  `orderBy` varchar(42) DEFAULT '',
  `name` varchar(200) DEFAULT '',
  `voiceFile` varchar(100) DEFAULT '',
  `userId` varchar(42) DEFAULT '',
  `defaultTemp` smallint(6) DEFAULT '0',
  PRIMARY KEY (`voiceTempId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_vartriver_alarmfordevice`
-- ----------------------------
DROP TABLE IF EXISTS `t_vartriver_alarmfordevice`;
CREATE TABLE `t_vartriver_alarmfordevice` (
  `id` varchar(42) NOT NULL DEFAULT '',
  `alarmId` varchar(42) DEFAULT '',
  `channelId` varchar(42) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_vartriver_alarmforevent`
-- ----------------------------
DROP TABLE IF EXISTS `t_vartriver_alarmforevent`;
CREATE TABLE `t_vartriver_alarmforevent` (
  `alarmEventId` varchar(42) NOT NULL DEFAULT '',
  `alarmId` varchar(42) DEFAULT '',
  `eventId` varchar(42) DEFAULT '',
  `ruleId` varchar(42) DEFAULT '',
  `cycle` int(11) DEFAULT '0',
  `noteTempId` varchar(42) DEFAULT '',
  `soundTempId` varchar(42) DEFAULT '',
  `voiceTempId` varchar(42) DEFAULT '',
  `emailTempId` varchar(42) DEFAULT '',
  `pageTempId` varchar(42) DEFAULT '',
  PRIMARY KEY (`alarmEventId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_vartriver_collection_error`
-- ----------------------------
DROP TABLE IF EXISTS `t_vartriver_collection_error`;
CREATE TABLE `t_vartriver_collection_error` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `deviceId` varchar(50) DEFAULT '',
  `infoDataTime` datetime DEFAULT NULL,
  `channel1` varchar(20) DEFAULT '',
  `channel2` varchar(20) DEFAULT '',
  `channel3` varchar(20) DEFAULT '',
  `channel4` varchar(20) DEFAULT '',
  `channel5` varchar(20) DEFAULT '',
  `channel6` varchar(20) DEFAULT '',
  `channel7` varchar(20) DEFAULT '',
  `channel8` varchar(20) DEFAULT '',
  `channel9` varchar(20) DEFAULT '',
  `channel10` varchar(20) DEFAULT '',
  `channel11` varchar(20) DEFAULT '',
  `channel12` varchar(20) DEFAULT '',
  `channel13` varchar(20) DEFAULT '',
  `channel14` varchar(20) DEFAULT '',
  `channel15` varchar(20) DEFAULT '',
  `channel16` varchar(20) DEFAULT '',
  `channel17` varchar(20) DEFAULT '',
  `channel18` varchar(20) DEFAULT '',
  `channel19` varchar(20) DEFAULT '',
  `channel20` varchar(20) DEFAULT '',
  `channel21` varchar(20) DEFAULT '',
  `channel22` varchar(20) DEFAULT '',
  `channel23` varchar(20) DEFAULT '',
  `channel24` varchar(20) DEFAULT '',
  `channel25` varchar(20) DEFAULT '',
  `channel26` varchar(20) DEFAULT '',
  `channel27` varchar(20) DEFAULT '',
  `channel28` varchar(20) DEFAULT '',
  `channel29` varchar(20) DEFAULT '',
  `channel30` varchar(20) DEFAULT '',
  `channel31` varchar(20) DEFAULT '',
  `channel32` varchar(20) DEFAULT '',
  `channel33` varchar(20) DEFAULT '',
  `channel34` varchar(20) DEFAULT '',
  `channel35` varchar(20) DEFAULT '',
  `channel36` varchar(20) DEFAULT '',
  `channel37` varchar(20) DEFAULT '',
  `channel38` varchar(20) DEFAULT '',
  `channel39` varchar(20) DEFAULT '',
  `channel40` varchar(20) DEFAULT '',
  `channel41` varchar(20) DEFAULT '',
  `channel42` varchar(20) DEFAULT '',
  `channel43` varchar(20) DEFAULT '',
  `channel44` varchar(20) DEFAULT '',
  `channel45` varchar(20) DEFAULT '',
  `channel46` varchar(20) DEFAULT '',
  `channel47` varchar(20) DEFAULT '',
  `channel48` varchar(20) DEFAULT '',
  `channel49` varchar(20) DEFAULT '',
  `channel50` varchar(20) DEFAULT '',
  `createTime` datetime DEFAULT NULL,
  `daySumCH` varchar(20) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_vartriver_devicestatus`
-- ----------------------------
DROP TABLE IF EXISTS `t_vartriver_devicestatus`;
CREATE TABLE `t_vartriver_devicestatus` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '记录id',
  `deviceId` varchar(50) DEFAULT '' COMMENT '设备id',
  `infoDataTime` datetime DEFAULT NULL COMMENT '上报时间',
  `infoLevel` varchar(20) DEFAULT '' COMMENT '通知等级',
  `infoType` varchar(20) DEFAULT '' COMMENT '通知类型',
  `infoNum` varchar(20) DEFAULT '' COMMENT '通知标志',
  `infoContent` varchar(50) DEFAULT '' COMMENT '通知内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=872 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_vartriver_first`
-- ----------------------------
DROP TABLE IF EXISTS `t_vartriver_first`;
CREATE TABLE `t_vartriver_first` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '记录id',
  `deviceId` varchar(50) DEFAULT '' COMMENT '设备id',
  `infoDataTime` datetime DEFAULT NULL COMMENT '上报时间',
  `channel1` varchar(20) DEFAULT '' COMMENT '上报的channel',
  `channel2` varchar(20) DEFAULT '',
  `channel3` varchar(20) DEFAULT '',
  `channel4` varchar(20) DEFAULT '',
  `channel5` varchar(20) DEFAULT '',
  `channel6` varchar(20) DEFAULT '',
  `channel7` varchar(20) DEFAULT '',
  `channel8` varchar(20) DEFAULT '',
  `channel9` varchar(20) DEFAULT '',
  `channel10` varchar(20) DEFAULT '',
  `channel11` varchar(20) DEFAULT '',
  `channel12` varchar(20) DEFAULT '',
  `channel13` varchar(20) DEFAULT '',
  `channel14` varchar(20) DEFAULT '',
  `channel15` varchar(20) DEFAULT '',
  `channel16` varchar(20) DEFAULT '',
  `channel17` varchar(20) DEFAULT '',
  `channel18` varchar(20) DEFAULT '',
  `channel19` varchar(20) DEFAULT '',
  `channel20` varchar(20) DEFAULT '',
  `channel21` varchar(20) DEFAULT '',
  `channel22` varchar(20) DEFAULT '',
  `channel23` varchar(20) DEFAULT '',
  `channel24` varchar(20) DEFAULT '',
  `channel25` varchar(20) DEFAULT '',
  `channel26` varchar(20) DEFAULT '',
  `channel27` varchar(20) DEFAULT '',
  `channel28` varchar(20) DEFAULT '',
  `channel29` varchar(20) DEFAULT '',
  `channel30` varchar(20) DEFAULT '',
  `channel31` varchar(20) DEFAULT '',
  `channel32` varchar(20) DEFAULT '',
  `channel33` varchar(20) DEFAULT '',
  `channel34` varchar(20) DEFAULT '',
  `channel35` varchar(20) DEFAULT '',
  `channel36` varchar(20) DEFAULT '',
  `channel37` varchar(20) DEFAULT '',
  `channel38` varchar(20) DEFAULT '',
  `channel39` varchar(20) DEFAULT '',
  `channel40` varchar(20) DEFAULT '',
  `channel41` varchar(20) DEFAULT '',
  `channel42` varchar(20) DEFAULT '',
  `channel43` varchar(20) DEFAULT '',
  `channel44` varchar(20) DEFAULT '',
  `channel45` varchar(20) DEFAULT '',
  `channel46` varchar(20) DEFAULT '',
  `channel47` varchar(20) DEFAULT '',
  `channel48` varchar(20) DEFAULT '',
  `channel49` varchar(20) DEFAULT '',
  `channel50` varchar(20) DEFAULT '',
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `T_VARTRIVER_10002045_index_infoDataTime` (`infoDataTime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_vartriver_img`
-- ----------------------------
DROP TABLE IF EXISTS `t_vartriver_img`;
CREATE TABLE `t_vartriver_img` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '上报图片记录id',
  `deviceId` varchar(20) DEFAULT '' COMMENT '设备id',
  `infoDataTime` datetime DEFAULT NULL COMMENT '上报时间',
  `fileName` varchar(100) DEFAULT '' COMMENT '路径名',
  `monitorId` varchar(10) DEFAULT '' COMMENT '监视点id',
  `monitorName` varchar(50) DEFAULT '' COMMENT '监视点名子',
  `smallName` varchar(100) DEFAULT '' COMMENT '小图路径名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=139952 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_vartriver_ipc`
-- ----------------------------
DROP TABLE IF EXISTS `t_vartriver_ipc`;
CREATE TABLE `t_vartriver_ipc` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '摄像头记录id',
  `name` varchar(50) DEFAULT '' COMMENT '摄像头名字',
  `s_nod` varchar(50) DEFAULT '' COMMENT '摄像头结点',
  `deviceId` varchar(50) DEFAULT '' COMMENT '设备id',
  `s_ip` varchar(50) DEFAULT '' COMMENT '摄像头ip',
  `s_port` varchar(50) DEFAULT '' COMMENT '摄像头视频80端口',
  `s_username` varchar(50) DEFAULT '' COMMENT '摄像头用户名',
  `s_password` varchar(50) DEFAULT '' COMMENT '摄像头密码',
  `s_stream` varchar(50) DEFAULT '' COMMENT '摄像头码流类型',
  `s_online` varchar(50) DEFAULT '' COMMENT '是否在线 1 在线  其他不在线',
  `orderNo` int(11) DEFAULT '0' COMMENT '摄像头排序',
  `mapingDeviceId` varchar(50) NOT NULL DEFAULT '' COMMENT '摄像头id',
  `ipcProxyId` varchar(50) DEFAULT '' COMMENT '代理id',
  `stauts` varchar(50) DEFAULT '' COMMENT '摄像头状态',
  `s_power` varchar(50) DEFAULT '' COMMENT '电源是否掉电',
  `ipcCtrlProxyId` varchar(50) DEFAULT '' COMMENT '控制代理id',
  `streamType` varchar(10) DEFAULT '' COMMENT '码流类型',
  `monitorid` int(11) DEFAULT '0',
  `mchannel` int(10) DEFAULT NULL COMMENT '主码流频道类型',
  `mvideoname` varchar(255) DEFAULT NULL COMMENT '名称',
  `mresolution` int(10) DEFAULT NULL COMMENT '主码流分辨率',
  `mvideobitrate` int(10) DEFAULT '0' COMMENT '主码流编码率',
  `mvideoframeRate` int(10) DEFAULT '0' COMMENT '主码流帧率',
  `mencodetype` int(10) DEFAULT '0' COMMENT '主码流编码类型',
  `mencodeefficiency` int(10) DEFAULT '0' COMMENT '主码流视频编码复杂度',
  `schannel` int(4) DEFAULT '-1' COMMENT '字码流',
  `svideoname` int(10) DEFAULT '-1' COMMENT '子码流',
  `sresolution` int(10) DEFAULT '-1' COMMENT '子码流分辨率',
  `svideobitrate` int(10) DEFAULT '-1',
  `sencodetype` int(10) DEFAULT '-1' COMMENT '子码流编码类型',
  `sencodeefficiency` int(10) DEFAULT '-1' COMMENT '子码流编码复杂度',
  `svideoframeRate` int(10) DEFAULT NULL COMMENT '子码流帧率',
  PRIMARY KEY (`id`,`mapingDeviceId`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_vartriver_ipcch`
-- ----------------------------
DROP TABLE IF EXISTS `t_vartriver_ipcch`;
CREATE TABLE `t_vartriver_ipcch` (
  `id` double NOT NULL DEFAULT '0',
  `s_nov` varchar(50) DEFAULT '',
  `deviceId` varchar(50) DEFAULT '',
  `s_nod` varchar(50) DEFAULT '',
  `s_profile` varchar(50) DEFAULT '',
  `s_encoding` varchar(50) DEFAULT '',
  `s_quality` varchar(50) DEFAULT '',
  `s_width` varchar(50) DEFAULT '',
  `s_height` varchar(50) DEFAULT '',
  `s_gov` varchar(50) DEFAULT '',
  `s_fram` varchar(50) DEFAULT '',
  `s_rate` varchar(50) DEFAULT '',
  `s_streaming` varchar(200) DEFAULT '',
  `s_frame` varchar(50) DEFAULT '',
  `s_size` varchar(50) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_vartriver_ipcex`
-- ----------------------------
DROP TABLE IF EXISTS `t_vartriver_ipcex`;
CREATE TABLE `t_vartriver_ipcex` (
  `id` double NOT NULL DEFAULT '0',
  `s_nod` varchar(50) DEFAULT '',
  `name` varchar(50) DEFAULT '',
  `deviceId` varchar(50) DEFAULT '',
  `mapingDeviceId` varchar(50) DEFAULT '',
  `orderNo` int(11) DEFAULT '0',
  `s_ip` varchar(50) DEFAULT '',
  `s_port` varchar(50) DEFAULT '',
  `s_username` varchar(50) DEFAULT '',
  `s_password` varchar(50) DEFAULT '',
  `s_stream` varchar(50) DEFAULT '',
  `s_online` varchar(50) DEFAULT '',
  `s_power` varchar(50) DEFAULT '',
  `ipcProxyId` varchar(50) DEFAULT '',
  `ipcCtrlProxyId` varchar(50) DEFAULT '',
  `s_brightness` varchar(50) DEFAULT '',
  `s_saturation` varchar(50) DEFAULT '',
  `s_contrast` varchar(50) DEFAULT '',
  `s_sharpness` varchar(50) DEFAULT '',
  `s_snaping` varchar(50) DEFAULT '',
  `stauts` varchar(50) DEFAULT '',
  `streamType` varchar(10) DEFAULT '',
  `exUrl` varchar(200) DEFAULT '',
  `ipcType` varchar(20) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_vartriver_ipcproxy`
-- ----------------------------
DROP TABLE IF EXISTS `t_vartriver_ipcproxy`;
CREATE TABLE `t_vartriver_ipcproxy` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '记录id',
  `mapingDeviceId` varchar(255) DEFAULT NULL COMMENT '摄像头id',
  `deviceId` varchar(50) DEFAULT '' COMMENT '设备id',
  `s_host` varchar(50) CHARACTER SET utf8 COLLATE utf8_romanian_ci DEFAULT '' COMMENT '摄像头ip',
  `s_hostport` varchar(50) DEFAULT '' COMMENT '被代理的摄像头的端口',
  `s_proxy` varchar(50) DEFAULT '' COMMENT 'vr1000的端口(这个带代理s_hostport)',
  `s_pwr` varchar(255) DEFAULT NULL COMMENT '电源是否掉电',
  `s_proxyport` varchar(50) DEFAULT '',
  `s_pwrval` varchar(50) DEFAULT '' COMMENT '掉电值',
  `s_timeout` varchar(50) DEFAULT '180' COMMENT '代理持续时间',
  `stauts` varchar(50) DEFAULT '' COMMENT '状态',
  `username` varchar(255) DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `type` int(4) DEFAULT NULL COMMENT '代理类型 1视频代理  2 控制代理',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_vartriver_log`
-- ----------------------------
DROP TABLE IF EXISTS `t_vartriver_log`;
CREATE TABLE `t_vartriver_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `deviceId` varchar(20) DEFAULT '',
  `fileName` varchar(100) DEFAULT '',
  `fileSize` varchar(100) DEFAULT '',
  `filepath` varchar(100) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_vartriver_missile`
-- ----------------------------
DROP TABLE IF EXISTS `t_vartriver_missile`;
CREATE TABLE `t_vartriver_missile` (
  `id` varchar(42) NOT NULL DEFAULT '',
  `terminalId` varchar(50) DEFAULT '',
  `userName` varchar(50) DEFAULT '',
  `password` varchar(50) DEFAULT '',
  `name` varchar(50) DEFAULT '',
  `model` varchar(50) DEFAULT '',
  `deviceId` varchar(50) DEFAULT '',
  `testDateTime` datetime DEFAULT NULL,
  `timeConsuming` int(11) DEFAULT '0',
  `createDate` datetime DEFAULT NULL,
  `testUnit` varchar(50) DEFAULT '',
  `poweronDuration` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_vartriver_missiledetect`
-- ----------------------------
DROP TABLE IF EXISTS `t_vartriver_missiledetect`;
CREATE TABLE `t_vartriver_missiledetect` (
  `id` varchar(42) NOT NULL DEFAULT '',
  `name` varchar(50) DEFAULT '',
  `recoderId` varchar(50) DEFAULT '',
  `technology` varchar(50) DEFAULT '',
  `measuredValue` varchar(50) DEFAULT '',
  `unit` varchar(50) DEFAULT '',
  `meviationValue` varchar(50) DEFAULT '',
  `result` longtext,
  `itemId` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_vartriver_missilefault`
-- ----------------------------
DROP TABLE IF EXISTS `t_vartriver_missilefault`;
CREATE TABLE `t_vartriver_missilefault` (
  `id` varchar(42) NOT NULL DEFAULT '',
  `recoderId` varchar(50) DEFAULT '',
  `result` longtext,
  `itemId` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_vartriver_missileinfo`
-- ----------------------------
DROP TABLE IF EXISTS `t_vartriver_missileinfo`;
CREATE TABLE `t_vartriver_missileinfo` (
  `id` varchar(42) NOT NULL DEFAULT '',
  `terminalId` varchar(50) DEFAULT '',
  `userName` varchar(50) DEFAULT '',
  `password` varchar(50) DEFAULT '',
  `name` varchar(50) DEFAULT '',
  `model` varchar(50) DEFAULT '',
  `deviceId` varchar(50) DEFAULT '',
  `assembleDate` datetime DEFAULT NULL,
  `assembleComp` varchar(50) DEFAULT '',
  `deliverDate` datetime DEFAULT NULL,
  `accumulatedDistance` varchar(50) DEFAULT '',
  `roadDistance` varchar(50) DEFAULT '',
  `railyDistance` varchar(50) DEFAULT '',
  `seaDistance` varchar(50) DEFAULT '',
  `airfreightDistace` varchar(50) DEFAULT '',
  `status` varchar(50) DEFAULT '',
  `stockOut` varchar(50) DEFAULT '',
  `createDate` datetime DEFAULT NULL,
  `isNew` varchar(50) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_vartriver_missileinfostockhistory`
-- ----------------------------
DROP TABLE IF EXISTS `t_vartriver_missileinfostockhistory`;
CREATE TABLE `t_vartriver_missileinfostockhistory` (
  `id` varchar(42) NOT NULL DEFAULT '',
  `terminalId` varchar(50) DEFAULT '',
  `userName` varchar(50) DEFAULT '',
  `password` varchar(50) DEFAULT '',
  `name` varchar(50) DEFAULT '',
  `model` varchar(50) DEFAULT '',
  `deviceId` varchar(50) DEFAULT '',
  `assembleDate` datetime DEFAULT NULL,
  `assembleComp` varchar(50) DEFAULT '',
  `deliverDate` datetime DEFAULT NULL,
  `accumulatedDistance` varchar(50) DEFAULT '',
  `roadDistance` varchar(50) DEFAULT '',
  `railyDistance` varchar(50) DEFAULT '',
  `seaDistance` varchar(50) DEFAULT '',
  `airfreightDistace` varchar(50) DEFAULT '',
  `status` varchar(50) DEFAULT '',
  `stockOut` varchar(50) DEFAULT '',
  `createDate` datetime DEFAULT NULL,
  `isNew` varchar(50) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_vartriver_missilemaintenance`
-- ----------------------------
DROP TABLE IF EXISTS `t_vartriver_missilemaintenance`;
CREATE TABLE `t_vartriver_missilemaintenance` (
  `id` varchar(42) NOT NULL DEFAULT '',
  `recoderId` varchar(50) DEFAULT '',
  `datetime` datetime DEFAULT NULL,
  `description` longtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_vartriver_missilemilestone`
-- ----------------------------
DROP TABLE IF EXISTS `t_vartriver_missilemilestone`;
CREATE TABLE `t_vartriver_missilemilestone` (
  `id` varchar(42) NOT NULL DEFAULT '',
  `recoderId` varchar(50) DEFAULT '',
  `datetime` datetime DEFAULT NULL,
  `description` longtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_vartriver_parsedata`
-- ----------------------------
DROP TABLE IF EXISTS `t_vartriver_parsedata`;
CREATE TABLE `t_vartriver_parsedata` (
  `id` varchar(42) NOT NULL DEFAULT '' COMMENT '规格文件id',
  `channel` int(11) DEFAULT '0' COMMENT '频道',
  `deviceId` varchar(50) DEFAULT '' COMMENT '设备id',
  `name` varchar(50) DEFAULT '' COMMENT '名字',
  `beginPosition` int(11) DEFAULT '0' COMMENT '开始位置',
  `len` int(11) DEFAULT '0' COMMENT '长度',
  `dataType` varchar(10) DEFAULT '' COMMENT '数据类型',
  `decimalFormat` varchar(20) DEFAULT '' COMMENT '小数点样式',
  `fieldName` varchar(50) DEFAULT '' COMMENT '频道名',
  `formula` varchar(200) DEFAULT '' COMMENT '计算公式',
  `unit` varchar(20) DEFAULT '' COMMENT '单位',
  `orderIndex` int(11) NOT NULL DEFAULT '0' COMMENT '列表排序',
  `listDisplay` smallint(6) NOT NULL DEFAULT '0' COMMENT '列表展示',
  `statDisplay` smallint(6) NOT NULL DEFAULT '0' COMMENT '分析展示',
  `lowerLimit` double DEFAULT '0' COMMENT '下限值',
  `upperLimit` double DEFAULT '0' COMMENT '上限值',
  `diffPercent` double DEFAULT '0' COMMENT '偏差',
  `chartID` varchar(50) DEFAULT '' COMMENT '图片id',
  `chartDisplay` smallint(6) DEFAULT '0' COMMENT '图片显示',
  `chartOrderIndex` int(11) DEFAULT '0' COMMENT '图片顺序'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_vartriver_preimgcustomize`
-- ----------------------------
DROP TABLE IF EXISTS `t_vartriver_preimgcustomize`;
CREATE TABLE `t_vartriver_preimgcustomize` (
  `customizeId` varchar(42) NOT NULL DEFAULT '',
  `orderBy` varchar(42) DEFAULT '',
  `customizeName` varchar(100) DEFAULT '',
  `customizeDesception` varchar(100) DEFAULT '',
  `owerUser` varchar(20) DEFAULT '',
  PRIMARY KEY (`customizeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_vartriver_preimgcustomizeimage`
-- ----------------------------
DROP TABLE IF EXISTS `t_vartriver_preimgcustomizeimage`;
CREATE TABLE `t_vartriver_preimgcustomizeimage` (
  `id` int(11) NOT NULL DEFAULT '0',
  `customizeId` varchar(50) DEFAULT '',
  `deviceId` varchar(50) DEFAULT '',
  `infoDataTime` datetime DEFAULT NULL,
  `monitorId` varchar(10) DEFAULT '',
  `monitorName` varchar(50) DEFAULT '',
  `fileName` varchar(100) DEFAULT '',
  `smallName` varchar(100) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_vartriver_spareparts`
-- ----------------------------
DROP TABLE IF EXISTS `t_vartriver_spareparts`;
CREATE TABLE `t_vartriver_spareparts` (
  `id` varchar(42) NOT NULL DEFAULT '',
  `terminalId` varchar(50) DEFAULT '',
  `testUnit` varchar(50) DEFAULT '',
  `userName` varchar(50) DEFAULT '',
  `password` varchar(50) DEFAULT '',
  `name` varchar(50) DEFAULT '',
  `model` varchar(50) DEFAULT '',
  `deviceId` varchar(50) DEFAULT '',
  `poweronDuration` int(11) DEFAULT '0',
  `testDateTime` datetime DEFAULT NULL,
  `timeConsuming` int(11) DEFAULT '0',
  `createDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_vartriver_sparepartsdetect`
-- ----------------------------
DROP TABLE IF EXISTS `t_vartriver_sparepartsdetect`;
CREATE TABLE `t_vartriver_sparepartsdetect` (
  `id` varchar(42) NOT NULL DEFAULT '',
  `name` varchar(50) DEFAULT '',
  `recoderId` varchar(50) DEFAULT '',
  `technology` varchar(50) DEFAULT '',
  `measuredValue` varchar(50) DEFAULT '',
  `unit` varchar(50) DEFAULT '',
  `meviationValue` varchar(50) DEFAULT '',
  `result` longtext,
  `itemId` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_vartriver_sparepartsfault`
-- ----------------------------
DROP TABLE IF EXISTS `t_vartriver_sparepartsfault`;
CREATE TABLE `t_vartriver_sparepartsfault` (
  `id` varchar(42) NOT NULL DEFAULT '',
  `recoderId` varchar(50) DEFAULT '',
  `result` longtext,
  `itemId` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_vartriver_sparepartsinfo`
-- ----------------------------
DROP TABLE IF EXISTS `t_vartriver_sparepartsinfo`;
CREATE TABLE `t_vartriver_sparepartsinfo` (
  `id` varchar(42) NOT NULL DEFAULT '',
  `terminalId` varchar(50) DEFAULT '',
  `userName` varchar(50) DEFAULT '',
  `password` varchar(50) DEFAULT '',
  `name` varchar(50) DEFAULT '',
  `model` varchar(50) DEFAULT '',
  `deviceId` varchar(50) DEFAULT '',
  `assembleDate` datetime DEFAULT NULL,
  `assembleComp` varchar(50) DEFAULT '',
  `deliverDate` datetime DEFAULT NULL,
  `accumulatedDistance` varchar(50) DEFAULT '',
  `roadDistance` varchar(50) DEFAULT '',
  `railyDistance` varchar(50) DEFAULT '',
  `seaDistance` varchar(50) DEFAULT '',
  `airfreightDistace` varchar(50) DEFAULT '',
  `status` varchar(50) DEFAULT '',
  `stockOut` varchar(50) DEFAULT '',
  `createDate` datetime DEFAULT NULL,
  `isNew` varchar(50) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_vartriver_sparepartsinfostockhistory`
-- ----------------------------
DROP TABLE IF EXISTS `t_vartriver_sparepartsinfostockhistory`;
CREATE TABLE `t_vartriver_sparepartsinfostockhistory` (
  `id` varchar(42) NOT NULL DEFAULT '',
  `terminalId` varchar(50) DEFAULT '',
  `userName` varchar(50) DEFAULT '',
  `password` varchar(50) DEFAULT '',
  `name` varchar(50) DEFAULT '',
  `model` varchar(50) DEFAULT '',
  `deviceId` varchar(50) DEFAULT '',
  `assembleDate` datetime DEFAULT NULL,
  `assembleComp` varchar(50) DEFAULT '',
  `deliverDate` datetime DEFAULT NULL,
  `accumulatedDistance` varchar(50) DEFAULT '',
  `roadDistance` varchar(50) DEFAULT '',
  `railyDistance` varchar(50) DEFAULT '',
  `seaDistance` varchar(50) DEFAULT '',
  `airfreightDistace` varchar(50) DEFAULT '',
  `status` varchar(50) DEFAULT '',
  `stockOut` varchar(50) DEFAULT '',
  `createDate` datetime DEFAULT NULL,
  `isNew` varchar(50) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_vartriver_sparepartsmaintenance`
-- ----------------------------
DROP TABLE IF EXISTS `t_vartriver_sparepartsmaintenance`;
CREATE TABLE `t_vartriver_sparepartsmaintenance` (
  `id` varchar(42) NOT NULL DEFAULT '',
  `recoderId` varchar(50) DEFAULT '',
  `datetime` datetime DEFAULT NULL,
  `description` longtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_vartriver_sparepartsmilestone`
-- ----------------------------
DROP TABLE IF EXISTS `t_vartriver_sparepartsmilestone`;
CREATE TABLE `t_vartriver_sparepartsmilestone` (
  `id` varchar(42) NOT NULL DEFAULT '',
  `recoderId` varchar(50) DEFAULT '',
  `datetime` datetime DEFAULT NULL,
  `description` longtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_vartriver_sparepartsreplacement`
-- ----------------------------
DROP TABLE IF EXISTS `t_vartriver_sparepartsreplacement`;
CREATE TABLE `t_vartriver_sparepartsreplacement` (
  `id` varchar(42) NOT NULL DEFAULT '',
  `recoderId` varchar(50) DEFAULT '',
  `datetime` datetime DEFAULT NULL,
  `description` longtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_vartriver_switchconf`
-- ----------------------------
DROP TABLE IF EXISTS `t_vartriver_switchconf`;
CREATE TABLE `t_vartriver_switchconf` (
  `id` varchar(42) NOT NULL DEFAULT '',
  `deviceId` varchar(50) DEFAULT '',
  `groupId` varchar(50) DEFAULT '',
  `switchId` varchar(50) DEFAULT '',
  `ctrlType` varchar(20) DEFAULT '',
  `associatedGroupId` varchar(20) DEFAULT '',
  `associatedSwitchId` varchar(20) DEFAULT '',
  `dateInterval` int(11) DEFAULT '0',
  `levelPulse` varchar(20) DEFAULT '',
  `pulseWidth` int(11) DEFAULT '0',
  `ctrlTime` varchar(20) DEFAULT '',
  `ctrlTimeOnOff` varchar(20) DEFAULT '',
  `createTime` datetime DEFAULT NULL,
  `success` smallint(6) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_vartriver_switchconf_timectrl`
-- ----------------------------
DROP TABLE IF EXISTS `t_vartriver_switchconf_timectrl`;
CREATE TABLE `t_vartriver_switchconf_timectrl` (
  `id` varchar(42) NOT NULL DEFAULT '',
  `deviceId` varchar(50) DEFAULT '',
  `groupId` varchar(50) DEFAULT '',
  `switchId` varchar(50) DEFAULT '',
  `ctrlTime` varchar(20) DEFAULT '',
  `ctrlTimeOnOff` varchar(20) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_vartriver_switchconfin`
-- ----------------------------
DROP TABLE IF EXISTS `t_vartriver_switchconfin`;
CREATE TABLE `t_vartriver_switchconfin` (
  `id` varchar(42) NOT NULL DEFAULT '',
  `deviceId` varchar(50) DEFAULT '',
  `groupId` varchar(50) DEFAULT '',
  `switchId1` varchar(20) DEFAULT '',
  `switchId2` varchar(20) DEFAULT '',
  `switchId3` varchar(20) DEFAULT '',
  `switchId4` varchar(20) DEFAULT '',
  `switchId5` varchar(20) DEFAULT '',
  `switchId6` varchar(20) DEFAULT '',
  `switchId7` varchar(20) DEFAULT '',
  `switchId8` varchar(20) DEFAULT '',
  `createTime` datetime DEFAULT NULL,
  `success` smallint(6) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_vartriver_switchoutctrl`
-- ----------------------------
DROP TABLE IF EXISTS `t_vartriver_switchoutctrl`;
CREATE TABLE `t_vartriver_switchoutctrl` (
  `id` varchar(42) NOT NULL DEFAULT '',
  `deviceId` varchar(50) DEFAULT '',
  `groupId` varchar(50) DEFAULT '',
  `switchId1` varchar(20) DEFAULT '',
  `switchId2` varchar(20) DEFAULT '',
  `switchId3` varchar(20) DEFAULT '',
  `switchId4` varchar(20) DEFAULT '',
  `switchId5` varchar(20) DEFAULT '',
  `switchId6` varchar(20) DEFAULT '',
  `switchId7` varchar(20) DEFAULT '',
  `switchId8` varchar(20) DEFAULT '',
  `createTime` datetime DEFAULT NULL,
  `success` smallint(6) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_vartriver_switchstatus`
-- ----------------------------
DROP TABLE IF EXISTS `t_vartriver_switchstatus`;
CREATE TABLE `t_vartriver_switchstatus` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `deviceId` varchar(50) DEFAULT '',
  `infoDataTime` datetime DEFAULT NULL,
  `groupId` varchar(50) DEFAULT '',
  `switchId1` varchar(20) DEFAULT '',
  `switchId2` varchar(20) DEFAULT '',
  `switchId3` varchar(20) DEFAULT '',
  `switchId4` varchar(20) DEFAULT '',
  `switchId5` varchar(20) DEFAULT '',
  `switchId6` varchar(20) DEFAULT '',
  `switchId7` varchar(20) DEFAULT '',
  `switchId8` varchar(20) DEFAULT '',
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=557 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_vartriver_test`
-- ----------------------------
DROP TABLE IF EXISTS `t_vartriver_test`;
CREATE TABLE `t_vartriver_test` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `deviceId` varchar(50) DEFAULT '',
  `infoDataTime` datetime DEFAULT NULL,
  `channel1` varchar(20) DEFAULT '',
  `channel2` varchar(20) DEFAULT '',
  `channel3` varchar(20) DEFAULT '',
  `channel4` varchar(20) DEFAULT '',
  `channel5` varchar(20) DEFAULT '',
  `channel6` varchar(20) DEFAULT '',
  `channel7` varchar(20) DEFAULT '',
  `channel8` varchar(20) DEFAULT '',
  `channel9` varchar(20) DEFAULT '',
  `channel10` varchar(20) DEFAULT '',
  `channel11` varchar(20) DEFAULT '',
  `channel12` varchar(20) DEFAULT '',
  `channel13` varchar(20) DEFAULT '',
  `channel14` varchar(20) DEFAULT '',
  `channel15` varchar(20) DEFAULT '',
  `channel16` varchar(20) DEFAULT '',
  `channel17` varchar(20) DEFAULT '',
  `channel18` varchar(20) DEFAULT '',
  `channel19` varchar(20) DEFAULT '',
  `channel20` varchar(20) DEFAULT '',
  `channel21` varchar(20) DEFAULT '',
  `channel22` varchar(20) DEFAULT '',
  `channel23` varchar(20) DEFAULT '',
  `channel24` varchar(20) DEFAULT '',
  `channel25` varchar(20) DEFAULT '',
  `channel26` varchar(20) DEFAULT '',
  `channel27` varchar(20) DEFAULT '',
  `channel28` varchar(20) DEFAULT '',
  `channel29` varchar(20) DEFAULT '',
  `channel30` varchar(20) DEFAULT '',
  `channel31` varchar(20) DEFAULT '',
  `channel32` varchar(20) DEFAULT '',
  `channel33` varchar(20) DEFAULT '',
  `channel34` varchar(20) DEFAULT '',
  `channel35` varchar(20) DEFAULT '',
  `channel36` varchar(20) DEFAULT '',
  `channel37` varchar(20) DEFAULT '',
  `channel38` varchar(20) DEFAULT '',
  `channel39` varchar(20) DEFAULT '',
  `channel40` varchar(20) DEFAULT '',
  `channel41` varchar(20) DEFAULT '',
  `channel42` varchar(20) DEFAULT '',
  `channel43` varchar(20) DEFAULT '',
  `channel44` varchar(20) DEFAULT '',
  `channel45` varchar(20) DEFAULT '',
  `channel46` varchar(20) DEFAULT '',
  `channel47` varchar(20) DEFAULT '',
  `channel48` varchar(20) DEFAULT '',
  `channel49` varchar(20) DEFAULT '',
  `channel50` varchar(20) DEFAULT '',
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `T_VARTRIVER_10002045_index_infoDataTime` (`infoDataTime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_vartriver_zigbeedigital`
-- ----------------------------
DROP TABLE IF EXISTS `t_vartriver_zigbeedigital`;
CREATE TABLE `t_vartriver_zigbeedigital` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `deviceId` varchar(50) DEFAULT '',
  `infoDataTime` datetime DEFAULT NULL,
  `channel1` varchar(20) DEFAULT '',
  `channel2` varchar(20) DEFAULT '',
  `channel3` varchar(20) DEFAULT '',
  `channel4` varchar(20) DEFAULT '',
  `channel5` varchar(20) DEFAULT '',
  `channel6` varchar(20) DEFAULT '',
  `channel7` varchar(20) DEFAULT '',
  `channel8` varchar(20) DEFAULT '',
  `channel9` varchar(20) DEFAULT '',
  `channel10` varchar(20) DEFAULT '',
  `channel11` varchar(20) DEFAULT '',
  `channel12` varchar(20) DEFAULT '',
  `channel13` varchar(20) DEFAULT '',
  `channel14` varchar(20) DEFAULT '',
  `channel15` varchar(20) DEFAULT '',
  `channel16` varchar(20) DEFAULT '',
  `channel17` varchar(20) DEFAULT '',
  `channel18` varchar(20) DEFAULT '',
  `channel19` varchar(20) DEFAULT '',
  `channel20` varchar(20) DEFAULT '',
  `channel21` varchar(20) DEFAULT '',
  `channel22` varchar(20) DEFAULT '',
  `channel23` varchar(20) DEFAULT '',
  `channel24` varchar(20) DEFAULT '',
  `channel25` varchar(20) DEFAULT '',
  `channel26` varchar(20) DEFAULT '',
  `channel27` varchar(20) DEFAULT '',
  `channel28` varchar(20) DEFAULT '',
  `channel29` varchar(20) DEFAULT '',
  `channel30` varchar(20) DEFAULT '',
  `channel31` varchar(20) DEFAULT '',
  `channel32` varchar(20) DEFAULT '',
  `channel33` varchar(20) DEFAULT '',
  `channel34` varchar(20) DEFAULT '',
  `channel35` varchar(20) DEFAULT '',
  `channel36` varchar(20) DEFAULT '',
  `channel37` varchar(20) DEFAULT '',
  `channel38` varchar(20) DEFAULT '',
  `channel39` varchar(20) DEFAULT '',
  `channel40` varchar(20) DEFAULT '',
  `channel41` varchar(20) DEFAULT '',
  `channel42` varchar(20) DEFAULT '',
  `channel43` varchar(20) DEFAULT '',
  `channel44` varchar(20) DEFAULT '',
  `channel45` varchar(20) DEFAULT '',
  `channel46` varchar(20) DEFAULT '',
  `channel47` varchar(20) DEFAULT '',
  `channel48` varchar(20) DEFAULT '',
  `channel49` varchar(20) DEFAULT '',
  `channel50` varchar(20) DEFAULT '',
  KEY `Auto_Increment_Key` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12236 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_vastriver_dmaparams`
-- ----------------------------
DROP TABLE IF EXISTS `t_vastriver_dmaparams`;
CREATE TABLE `t_vastriver_dmaparams` (
  `id` varchar(42) NOT NULL DEFAULT '',
  `deviceId` varchar(100) DEFAULT '',
  `name` text,
  `s_id` varchar(42) DEFAULT '',
  `s_addr` varchar(42) DEFAULT '',
  `s_bits` varchar(42) DEFAULT '',
  `s_input` varchar(42) DEFAULT '',
  `s_dformat` varchar(42) DEFAULT '',
  `s_type` varchar(100) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_vastriver_ip`
-- ----------------------------
DROP TABLE IF EXISTS `t_vastriver_ip`;
CREATE TABLE `t_vastriver_ip` (
  `channelId` varchar(42) DEFAULT '',
  `name` varchar(100) DEFAULT '' COMMENT '设备名字(所有者名字)',
  `channelType` varchar(1) DEFAULT '',
  `orderBy` varchar(42) DEFAULT '',
  `IP` varchar(20) DEFAULT '' COMMENT '外网ip',
  `Port` varchar(100) DEFAULT '' COMMENT '外网port',
  `NIP` varchar(100) DEFAULT '' COMMENT '内网IP',
  `NPort` varchar(100) DEFAULT '' COMMENT '内网端口',
  `videoPort` varchar(10) DEFAULT '' COMMENT '视频端口',
  `softWareUpdatePort` varchar(10) DEFAULT '' COMMENT '软件更新端口',
  `DeviceId` varchar(100) NOT NULL DEFAULT '' COMMENT '设备id',
  `Softwareversion` varchar(100) DEFAULT '' COMMENT '软件版本',
  `HardwareVersion` varchar(100) DEFAULT '' COMMENT '硬件版本',
  `orderNo` int(11) DEFAULT '0' COMMENT '排序值',
  `img` varchar(100) DEFAULT '',
  `url` varchar(200) DEFAULT '',
  `alt` varchar(50) DEFAULT '',
  `target` varchar(50) DEFAULT '',
  `tableName` varchar(50) DEFAULT '',
  `proxyIp` varchar(20) DEFAULT '' COMMENT '代理链接IP',
  `proxyPort` varchar(20) DEFAULT '' COMMENT '代理连接端口',
  `useIPConnect` varchar(1) DEFAULT '' COMMENT '链接方式',
  `infoDataTime` datetime DEFAULT NULL COMMENT '上报数据时间',
  `x` double DEFAULT '0' COMMENT '纬度',
  `y` double DEFAULT '0' COMMENT '经度',
  `zoom` int(11) DEFAULT '0' COMMENT '视角',
  `videoPlayPort` varchar(10) DEFAULT '',
  `dataType` varchar(10) DEFAULT '',
  `ctrPort` varchar(10) DEFAULT '',
  `receiveDataTime` datetime DEFAULT NULL,
  `receiveImgTime` datetime DEFAULT NULL,
  `receiveZigBeeTime` datetime DEFAULT NULL,
  `title` varchar(200) DEFAULT '',
  `fieldType` varchar(20) DEFAULT '',
  `functionNum` int(11) DEFAULT '0',
  `smartCtrl` varchar(1) DEFAULT '',
  `benTian` varchar(20) DEFAULT '',
  `yuYang` varchar(20) DEFAULT '',
  `nabtoDevId` varchar(100) DEFAULT '',
  `videoType` varchar(100) DEFAULT '',
  `deviceType` varchar(100) DEFAULT '',
  `videoEnable` varchar(100) DEFAULT '',
  `monitorEnable` varchar(100) DEFAULT '',
  `DeviceModel` varchar(100) DEFAULT '',
  `treeOrgPath` varchar(200) DEFAULT '',
  `orgType` varchar(42) DEFAULT '',
  `depth` varchar(20) DEFAULT '',
  `id` int(11) NOT NULL DEFAULT '0' COMMENT 'id',
  `province` varchar(255) DEFAULT NULL COMMENT '省',
  `city` varchar(255) DEFAULT NULL COMMENT '市',
  `district` varchar(255) DEFAULT NULL COMMENT '县',
  `supervisername` varchar(255) DEFAULT '' COMMENT '监督者姓名',
  `superviserid` int(11) DEFAULT NULL COMMENT '监督者id',
  `producername` varchar(255) DEFAULT '' COMMENT '生产者姓名',
  `producerid` int(11) DEFAULT NULL COMMENT '生产者id',
  `exportorname` varchar(255) DEFAULT '' COMMENT '专家姓名',
  `exportorid` int(255) DEFAULT '0' COMMENT '专家id',
  `groupid` int(11) DEFAULT NULL COMMENT '分组id',
  `siteid` int(11) DEFAULT NULL COMMENT '站点id',
  PRIMARY KEY (`id`,`DeviceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_vastriver_ipssss`
-- ----------------------------
DROP TABLE IF EXISTS `t_vastriver_ipssss`;
CREATE TABLE `t_vastriver_ipssss` (
  `channelId` varchar(42) DEFAULT '',
  `name` varchar(100) DEFAULT '' COMMENT '设备名字(所有者名字)',
  `channelType` varchar(1) DEFAULT '',
  `orderBy` varchar(42) DEFAULT '',
  `IP` varchar(20) DEFAULT '' COMMENT '外网ip',
  `Port` varchar(100) DEFAULT '' COMMENT '外网port',
  `NIP` varchar(100) DEFAULT '' COMMENT '内网IP',
  `NPort` varchar(100) DEFAULT '' COMMENT '内网端口',
  `videoPort` varchar(10) DEFAULT '' COMMENT '视频端口',
  `softWareUpdatePort` varchar(10) DEFAULT '' COMMENT '软件更新端口',
  `DeviceId` varchar(100) DEFAULT '' COMMENT '设备id',
  `Softwareversion` varchar(100) DEFAULT '' COMMENT '软件版本',
  `HardwareVersion` varchar(100) DEFAULT '' COMMENT '硬件版本',
  `orderNo` int(11) DEFAULT '0' COMMENT '排序值',
  `img` varchar(100) DEFAULT '',
  `url` varchar(200) DEFAULT '',
  `alt` varchar(50) DEFAULT '',
  `target` varchar(50) DEFAULT '',
  `tableName` varchar(50) DEFAULT '',
  `proxyIp` varchar(20) DEFAULT '' COMMENT '代理链接IP',
  `proxyPort` varchar(20) DEFAULT '' COMMENT '代理连接端口',
  `useIPConnect` varchar(1) DEFAULT '' COMMENT '链接方式',
  `infoDataTime` datetime DEFAULT NULL COMMENT '上报数据时间',
  `x` double DEFAULT '0' COMMENT '纬度',
  `y` double DEFAULT '0' COMMENT '经度',
  `zoom` int(11) DEFAULT '0' COMMENT '视角',
  `videoPlayPort` varchar(10) DEFAULT '',
  `dataType` varchar(10) DEFAULT '',
  `ctrPort` varchar(10) DEFAULT '',
  `receiveDataTime` datetime DEFAULT NULL,
  `receiveImgTime` datetime DEFAULT NULL,
  `receiveZigBeeTime` datetime DEFAULT NULL,
  `title` varchar(200) DEFAULT '',
  `fieldType` varchar(20) DEFAULT '',
  `functionNum` int(11) DEFAULT '0',
  `smartCtrl` varchar(1) DEFAULT '',
  `benTian` varchar(20) DEFAULT '',
  `yuYang` varchar(20) DEFAULT '',
  `nabtoDevId` varchar(100) DEFAULT '',
  `videoType` varchar(100) DEFAULT '',
  `deviceType` varchar(100) DEFAULT '',
  `videoEnable` varchar(100) DEFAULT '',
  `monitorEnable` varchar(100) DEFAULT '',
  `DeviceModel` varchar(100) DEFAULT '',
  `treeOrgPath` varchar(200) DEFAULT '',
  `orgType` varchar(42) DEFAULT '',
  `depth` varchar(20) DEFAULT '',
  `id` int(11) NOT NULL DEFAULT '0' COMMENT 'id',
  `province` varchar(255) DEFAULT NULL COMMENT '省',
  `city` varchar(255) DEFAULT NULL COMMENT '市',
  `district` varchar(255) DEFAULT NULL COMMENT '县',
  `supervisername` varchar(255) DEFAULT '' COMMENT '监督者姓名',
  `superviserid` int(11) DEFAULT NULL COMMENT '监督者id',
  `producername` varchar(255) DEFAULT '' COMMENT '生产者姓名',
  `producerid` int(11) DEFAULT NULL COMMENT '生产者id',
  `exportorname` varchar(255) DEFAULT '' COMMENT '专家姓名',
  `exportorid` int(255) DEFAULT '0' COMMENT '专家id',
  `groupid` int(11) DEFAULT NULL COMMENT '分组id',
  `siteid` int(11) DEFAULT NULL COMMENT '站点id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_vastriver_monitor_data`
-- ----------------------------
DROP TABLE IF EXISTS `t_vastriver_monitor_data`;
CREATE TABLE `t_vastriver_monitor_data` (
  `id` double NOT NULL AUTO_INCREMENT,
  `createDataTime` datetime DEFAULT NULL,
  `deviceId` varchar(200) DEFAULT '',
  `channelName` varchar(200) DEFAULT '',
  `channelData` varchar(20) DEFAULT '',
  `infoDataTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_vastriver_monitor_objects`
-- ----------------------------
DROP TABLE IF EXISTS `t_vastriver_monitor_objects`;
CREATE TABLE `t_vastriver_monitor_objects` (
  `id` double NOT NULL DEFAULT '0',
  `createDataTime` datetime DEFAULT NULL,
  `deviceId` varchar(200) DEFAULT '',
  `channelName` varchar(200) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_vastriver_motor`
-- ----------------------------
DROP TABLE IF EXISTS `t_vastriver_motor`;
CREATE TABLE `t_vastriver_motor` (
  `id` double NOT NULL DEFAULT '0',
  `deviceId` varchar(100) DEFAULT '',
  `ctrlType` int(11) DEFAULT '0',
  `raiseGroupId` varchar(50) DEFAULT '',
  `raiseSwitchId` varchar(50) DEFAULT '',
  `skinGroupId` varchar(50) DEFAULT '',
  `skinSwitchId` varchar(50) DEFAULT '',
  `direction` int(11) DEFAULT '0',
  `distanceOrDuration` int(11) DEFAULT '0',
  `posSensorCH` int(11) DEFAULT '0',
  `maxValue` varchar(50) DEFAULT '',
  `minValue` varchar(50) DEFAULT '',
  `devType` varchar(50) DEFAULT '',
  `name` varchar(50) DEFAULT '',
  `smartTempCtrl` varchar(50) DEFAULT '',
  `smartWaterCtrl` varchar(50) DEFAULT '',
  `tempCH` varchar(50) DEFAULT '',
  `waterCH` varchar(50) DEFAULT '',
  `tempCHName` varchar(50) DEFAULT '',
  `waterCHName` varchar(50) DEFAULT '',
  `smartCtrlType` varchar(50) DEFAULT '',
  `paramValue` varchar(50) DEFAULT '',
  `videoDeviceId` varchar(50) DEFAULT '',
  `title` varchar(50) DEFAULT '',
  `statusImg` varchar(50) DEFAULT '',
  `statusTitle` varchar(50) DEFAULT '',
  `rs485Addr` varchar(50) DEFAULT '',
  `rs485SwAddr` varchar(50) DEFAULT '',
  `ioStatus` varchar(50) DEFAULT '',
  `ioUDDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_vastriver_motor_appoint_queue`
-- ----------------------------
DROP TABLE IF EXISTS `t_vastriver_motor_appoint_queue`;
CREATE TABLE `t_vastriver_motor_appoint_queue` (
  `id` double NOT NULL DEFAULT '0',
  `motorId` varchar(42) DEFAULT '',
  `createTime` datetime DEFAULT NULL,
  `MotorAppointRuleId` double DEFAULT '0',
  `swStatus` varchar(1) DEFAULT '',
  `beginDateTime` datetime DEFAULT NULL,
  `endDateTime` datetime DEFAULT NULL,
  `realValue` double DEFAULT '0',
  `execBeginTime` datetime DEFAULT NULL,
  `execEndTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_vastriver_motor_appointopentime_log`
-- ----------------------------
DROP TABLE IF EXISTS `t_vastriver_motor_appointopentime_log`;
CREATE TABLE `t_vastriver_motor_appointopentime_log` (
  `id` double NOT NULL DEFAULT '0',
  `realStartTime` datetime DEFAULT NULL,
  `realEndTime` datetime DEFAULT NULL,
  `realDuration` double DEFAULT '0',
  `motorAppointRuleRuleId` varchar(20) DEFAULT '',
  `name` varchar(100) DEFAULT '',
  `motorId` varchar(42) DEFAULT '',
  `smartCtrl` varchar(1) DEFAULT '',
  `ctrlType` varchar(1) DEFAULT '',
  `cycleDay` int(11) DEFAULT '0',
  `execTime` varchar(20) DEFAULT '',
  `beginTime` datetime DEFAULT NULL,
  `endTime` datetime DEFAULT NULL,
  `targetDeviceId` varchar(200) DEFAULT '',
  `targetChannelName` varchar(50) DEFAULT '',
  `maxValue` varchar(50) DEFAULT '',
  `minValue` varchar(50) DEFAULT '',
  `openLevel` varchar(50) DEFAULT '',
  `coefficient` varchar(50) DEFAULT '',
  `realValue` varchar(50) DEFAULT '',
  `totalOutput` double DEFAULT '0',
  `confOutPut` double DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_vastriver_motor_appointrule`
-- ----------------------------
DROP TABLE IF EXISTS `t_vastriver_motor_appointrule`;
CREATE TABLE `t_vastriver_motor_appointrule` (
  `id` double NOT NULL DEFAULT '0',
  `name` varchar(100) DEFAULT '',
  `MotorId` varchar(100) DEFAULT '',
  `leafAgeId` double DEFAULT '0',
  `leafAgeName` varchar(50) DEFAULT '',
  `ctrlType` varchar(1) DEFAULT '',
  `cycleDay` int(11) DEFAULT '0',
  `execTime` varchar(20) DEFAULT '',
  `beginTime` datetime DEFAULT NULL,
  `endTime` datetime DEFAULT NULL,
  `targetDeviceId` varchar(200) DEFAULT '',
  `targetChannelName` varchar(50) DEFAULT '',
  `maxValue` varchar(255) DEFAULT '',
  `minValue` varchar(255) DEFAULT '',
  `openLevel` varchar(255) DEFAULT '',
  `ruleEnable` varchar(1) DEFAULT '',
  `coefficient` varchar(255) DEFAULT '',
  `lastExecDataTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_vastriver_motor_levelrule`
-- ----------------------------
DROP TABLE IF EXISTS `t_vastriver_motor_levelrule`;
CREATE TABLE `t_vastriver_motor_levelrule` (
  `id` double NOT NULL DEFAULT '0',
  `MotorId` varchar(100) DEFAULT '',
  `targetDeviceId` varchar(100) DEFAULT '',
  `targetChannelName` varchar(100) DEFAULT '',
  `maxValue` varchar(50) DEFAULT '',
  `minValue` varchar(50) DEFAULT '',
  `openLevel` varchar(50) DEFAULT '',
  `ruleEnable` varchar(50) DEFAULT '',
  `name` varchar(50) DEFAULT '',
  `leafAgeId` double DEFAULT '0',
  `targetName` varchar(50) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_vastriver_motor_levelrule_view`
-- ----------------------------
DROP TABLE IF EXISTS `t_vastriver_motor_levelrule_view`;
CREATE TABLE `t_vastriver_motor_levelrule_view` (
  `id` double NOT NULL DEFAULT '0',
  `name` varchar(50) DEFAULT '',
  `MotorId` varchar(100) DEFAULT '',
  `leafAgeId` double DEFAULT '0',
  `leafAgeName` double DEFAULT '0',
  `targetDeviceId` varchar(100) DEFAULT '',
  `targetChannelName` varchar(100) DEFAULT '',
  `targetName` varchar(50) DEFAULT '',
  `maxValue` varchar(50) DEFAULT '',
  `minValue` varchar(50) DEFAULT '',
  `openLevel` varchar(50) DEFAULT '',
  `ruleEnable` varchar(50) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_vastriver_motor_waterrule`
-- ----------------------------
DROP TABLE IF EXISTS `t_vastriver_motor_waterrule`;
CREATE TABLE `t_vastriver_motor_waterrule` (
  `id` double NOT NULL DEFAULT '0',
  `name` varchar(50) DEFAULT '',
  `MotorId` varchar(100) DEFAULT '',
  `leafAgeId` double DEFAULT '0',
  `targetDeviceId` varchar(100) DEFAULT '',
  `targetChannelName` varchar(100) DEFAULT '',
  `targetName` varchar(50) DEFAULT '',
  `maxValue` varchar(50) DEFAULT '',
  `minValue` varchar(50) DEFAULT '',
  `openLevel` varchar(50) DEFAULT '',
  `ruleEnable` varchar(50) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `user_question`
-- ----------------------------
DROP TABLE IF EXISTS `user_question`;
CREATE TABLE `user_question` (
  `user_question_id` int(11) NOT NULL AUTO_INCREMENT,
  `q_exp_id` int(11) DEFAULT NULL,
  `q_user_id` int(11) DEFAULT NULL,
  `q_title` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `if_delete` int(11) DEFAULT '0',
  `q_ans` text,
  PRIMARY KEY (`user_question_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
