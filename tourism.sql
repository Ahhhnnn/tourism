/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : localhost:3306
 Source Schema         : tourism

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 22/04/2019 22:01:24
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `role_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
  `comments` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '管理员', '管理员', '2018-12-19 23:11:29', '2018-12-19 23:11:29');
INSERT INTO `sys_role` VALUES (2, '普通用户', '普通用户', '2018-12-19 23:12:09', '2018-12-19 23:12:09');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '账号',
  `password` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `nick_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '昵称',
  `avatar` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
  `sex` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '男' COMMENT '性别',
  `phone` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `email` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `email_verified` int(1) NULL DEFAULT 0 COMMENT '邮箱是否验证，0未验证，1已验证',
  `true_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '真实姓名',
  `id_card` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '身份证号',
  `birthday` date NULL DEFAULT NULL COMMENT '出生日期',
  `department_id` int(11) NULL DEFAULT NULL COMMENT '部门id',
  `state` int(1) NOT NULL DEFAULT 0 COMMENT '状态，0正常，1冻结',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `user_account`(`username`) USING BTREE,
  INDEX `FK_sys_user`(`true_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', 'e10adc3949ba59abbe56e057f20f883e', '管理员', NULL, '男', '12345678901', NULL, 0, NULL, NULL, NULL, NULL, 0, '2018-12-19 23:30:05', '2019-04-16 15:50:42');
INSERT INTO `sys_user` VALUES (2, 'demo', 'e10adc3949ba59abbe56e057f20f883e', 'Demo01', NULL, '男', '12345678901', NULL, 0, NULL, NULL, NULL, NULL, 0, '2018-12-19 23:31:25', '2019-01-04 17:24:32');
INSERT INTO `sys_user` VALUES (3, 'hening', '25f9e794323b453885f5181f1b624d0b', '何宁', NULL, '男', '13699448253', NULL, 0, NULL, NULL, NULL, NULL, 0, '2019-01-23 14:00:54', '2019-04-15 23:11:32');
INSERT INTO `sys_user` VALUES (4, 'test', 'e10adc3949ba59abbe56e057f20f883e', '测试', NULL, '男', '15828401997', NULL, 0, NULL, NULL, NULL, NULL, 0, '2019-01-24 11:26:36', '2019-02-13 13:03:57');
INSERT INTO `sys_user` VALUES (6, 'ttt', 'e10adc3949ba59abbe56e057f20f883e', 'ttt', NULL, '男', '13699448253', NULL, 0, NULL, NULL, NULL, NULL, 0, '2019-01-28 09:02:10', '2019-01-28 09:02:10');
INSERT INTO `sys_user` VALUES (7, 'gggg', 'e10adc3949ba59abbe56e057f20f883e', 'gggg', NULL, '男', '13699448253', NULL, 0, NULL, NULL, NULL, NULL, 0, '2019-01-28 09:02:22', '2019-01-28 09:02:22');
INSERT INTO `sys_user` VALUES (8, 'ddd', 'e10adc3949ba59abbe56e057f20f883e', 'ddd', NULL, '男', '13699448253', NULL, 0, NULL, NULL, NULL, NULL, 0, '2019-01-28 09:02:30', '2019-01-28 09:02:30');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_sys_user_role`(`user_id`) USING BTREE,
  INDEX `FK_sys_user_role_role`(`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户角色' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (4, 3, 1, '2019-01-23 14:00:54');
INSERT INTO `sys_user_role` VALUES (5, 4, 1, '2019-01-24 11:26:36');
INSERT INTO `sys_user_role` VALUES (6, 4, 1, '2019-01-24 11:27:27');
INSERT INTO `sys_user_role` VALUES (7, 5, 1, '2019-01-28 08:41:17');
INSERT INTO `sys_user_role` VALUES (8, 6, 1, '2019-01-28 09:02:10');
INSERT INTO `sys_user_role` VALUES (9, 7, 1, '2019-01-28 09:02:22');
INSERT INTO `sys_user_role` VALUES (10, 8, 2, '2019-01-28 09:02:30');
INSERT INTO `sys_user_role` VALUES (11, 9, 1, '2019-01-28 09:02:39');
INSERT INTO `sys_user_role` VALUES (12, 10, 1, '2019-01-28 09:02:49');
INSERT INTO `sys_user_role` VALUES (13, 11, 1, '2019-01-28 09:02:58');
INSERT INTO `sys_user_role` VALUES (14, 12, 1, '2019-02-12 12:29:12');
INSERT INTO `sys_user_role` VALUES (15, 1, 1, '2019-02-13 12:39:07');
INSERT INTO `sys_user_role` VALUES (16, 1, 1, '2019-02-13 12:40:48');
INSERT INTO `sys_user_role` VALUES (17, 4, 1, '2019-02-13 13:03:57');
INSERT INTO `sys_user_role` VALUES (18, 2, 2, '2019-04-02 16:18:55');
INSERT INTO `sys_user_role` VALUES (19, 2, 1, '2019-04-02 16:18:55');
INSERT INTO `sys_user_role` VALUES (20, 2, 2, '2019-04-02 16:19:17');
INSERT INTO `sys_user_role` VALUES (21, 2, 1, '2019-04-02 16:19:17');

-- ----------------------------
-- Table structure for tr_car
-- ----------------------------
DROP TABLE IF EXISTS `tr_car`;
CREATE TABLE `tr_car`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `car_picture` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '车辆图片',
  `car_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '车辆名称',
  `car_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '车辆类型',
  `price` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '租车价格',
  `people_num` int(11) NULL DEFAULT NULL COMMENT '载人数',
  `car_sum` int(11) NULL DEFAULT NULL COMMENT '汽车总数',
  `car_num` int(11) NULL DEFAULT NULL COMMENT '剩余数量',
  `car_out` int(11) NULL DEFAULT 0 COMMENT '已租数量',
  `statu` int(1) NOT NULL DEFAULT 0 COMMENT '状态',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tr_car
-- ----------------------------
INSERT INTO `tr_car` VALUES (1, 'bingcar.jpg', '丰田汉兰达/SUV/2.7自动', '大巴', '180', 48, 20, 20, 0, 0, '2019-04-16 21:56:40', '2019-04-22 14:55:05');
INSERT INTO `tr_car` VALUES (2, 'benchi.jpg', '奔驰S350', '小轿车', '580', 4, 20, 20, 0, 0, '2019-04-16 23:09:15', '2019-04-22 14:57:10');

-- ----------------------------
-- Table structure for tr_car_order
-- ----------------------------
DROP TABLE IF EXISTS `tr_car_order`;
CREATE TABLE `tr_car_order`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '车辆订单id',
  `team_id` int(11) NULL DEFAULT NULL COMMENT '关联的团队id',
  `team_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `guide_id` int(11) NULL DEFAULT NULL COMMENT '下单导游id',
  `guide_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '下单导游名称',
  `car_id` int(11) NULL DEFAULT NULL,
  `car_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '车辆名称',
  `car_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型',
  `car_num` int(255) NULL DEFAULT NULL,
  `start_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '出发日期',
  `end_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '归回日期',
  `price` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '总价格',
  `statu` int(1) NOT NULL DEFAULT 0,
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tr_guide
-- ----------------------------
DROP TABLE IF EXISTS `tr_guide`;
CREATE TABLE `tr_guide`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `acount` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账号',
  `guide_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '导游名称',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `age` int(3) NULL DEFAULT NULL COMMENT '年龄',
  `sex` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `salary` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '薪水',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '住址',
  `statu` int(1) NULL DEFAULT 0 COMMENT '状态',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tr_guide
-- ----------------------------
INSERT INTO `tr_guide` VALUES (1, 'guide1', '导游1test', '123456', 25, '男', '13666666666', '4000', '成都市新都区新都大道8号', 0, '2019-04-16 23:27:10', '2019-04-17 13:12:18');
INSERT INTO `tr_guide` VALUES (2, 'guide2', '导游test2', NULL, 29, '男', '13880869412', '5000', '成都市新都区', 0, '2019-04-17 13:08:57', '2019-04-17 13:12:18');

-- ----------------------------
-- Table structure for tr_hotal
-- ----------------------------
DROP TABLE IF EXISTS `tr_hotal`;
CREATE TABLE `tr_hotal`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `hotal_picture` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `hotal_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '酒店名称',
  `grade` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '酒店等级',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '酒店地址',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '酒店电话',
  `introduce` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '酒店介绍',
  `room1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '房间类型1',
  `room1_num` int(11) NULL DEFAULT NULL COMMENT '数量',
  `room1_price` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '价格',
  `room2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '房间类型2',
  `room2_num` int(11) NULL DEFAULT NULL COMMENT '数量',
  `room2_price` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '价格',
  `room3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '房间类型3',
  `room3_num` int(11) NULL DEFAULT NULL COMMENT '数量',
  `room3_price` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '价格',
  `room4` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '房间类型4',
  `room4_num` int(11) NULL DEFAULT NULL COMMENT '数量',
  `room4_price` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '价格',
  `min_price` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `statu` int(1) NULL DEFAULT NULL COMMENT '状态',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tr_hotal
-- ----------------------------
INSERT INTO `tr_hotal` VALUES (1, 'binfen.jpg', '缤纷时代酒店', '3', '缤纷时代广场', '13699448253', '测试酒店', '标准间', 10, '120', '大床房', 15, '110', '三人间', 10, '180', '四人间', 5, '200', '110', 0, '2019-04-22 19:45:16', '2019-04-22 19:45:16');

-- ----------------------------
-- Table structure for tr_hotal_order
-- ----------------------------
DROP TABLE IF EXISTS `tr_hotal_order`;
CREATE TABLE `tr_hotal_order`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '酒店订单id',
  `team_id` int(11) NULL DEFAULT NULL COMMENT '关联的团队id',
  `team_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `guide_id` int(11) NULL DEFAULT NULL COMMENT '下单导游id',
  `guide_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '导游名称',
  `hotal_id` int(11) NULL DEFAULT NULL COMMENT '酒店id',
  `hotal_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '酒店名称',
  `room_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '房间名称',
  `room_num` int(11) NULL DEFAULT NULL COMMENT '订购数量',
  `start_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '入住时间',
  `end_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '离开时间',
  `price` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '总价格',
  `statu` int(1) NOT NULL DEFAULT 0 COMMENT '状态',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tr_order
-- ----------------------------
DROP TABLE IF EXISTS `tr_order`;
CREATE TABLE `tr_order`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `order_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单名称',
  `person_ids` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '游客ids',
  `person_names` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '游客名称',
  `guide_id` int(11) NULL DEFAULT NULL COMMENT '导游id',
  `guide_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '导游名称',
  `scenic_id` int(11) NULL DEFAULT NULL COMMENT '景点id',
  `scenic_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '景点名称',
  `out_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '出发时间',
  `statu` int(1) NOT NULL DEFAULT 0 COMMENT '订单状态',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tr_person
-- ----------------------------
DROP TABLE IF EXISTS `tr_person`;
CREATE TABLE `tr_person`  (
  `person_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `person_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `sex` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `birthday` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '生日',
  `statu` int(1) NULL DEFAULT 0 COMMENT '状态',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '住址',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`person_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tr_person
-- ----------------------------
INSERT INTO `tr_person` VALUES (1, '何宁', '男', '13699448253', '1017909292@qq.com', '1997-05-25', 0, '西南石油大学', '2019-04-15 23:20:08', '2019-04-17 13:12:22');

-- ----------------------------
-- Table structure for tr_scenic
-- ----------------------------
DROP TABLE IF EXISTS `tr_scenic`;
CREATE TABLE `tr_scenic`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `picture` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片路径',
  `price` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '价格',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型',
  `route` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '旅游路线',
  `introduce` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '景点介绍',
  `place` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地点',
  `statu` int(1) NOT NULL DEFAULT 0 COMMENT '状态',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tr_scenic
-- ----------------------------
INSERT INTO `tr_scenic` VALUES (1, '北京一日游(test)', 'qingcheng.png', '300', 'recommend', '故宫-天坛-长城', '测试景点介绍！！！', '北京', 0, '2019-04-15 15:10:03', '2019-04-18 23:21:19');
INSERT INTO `tr_scenic` VALUES (2, '成都三日游', 'qingcheng.png', '500', 'domestic', NULL, NULL, '成都', 0, '2019-04-15 16:39:24', '2019-04-16 11:50:34');
INSERT INTO `tr_scenic` VALUES (3, '欧洲七日游', 'qingcheng.png', '2000', 'abroad', NULL, NULL, '欧洲', 0, '2019-04-15 16:39:54', '2019-04-16 11:50:35');
INSERT INTO `tr_scenic` VALUES (4, '黄龙溪一日游', 'qingcheng.png', '100', 'recommend', NULL, NULL, '成都', 0, '2019-04-15 20:20:57', '2019-04-16 11:50:37');
INSERT INTO `tr_scenic` VALUES (5, '新都一日游test', 'qingcheng.png', '120', 'domestic', NULL, NULL, '新都', 0, '2019-04-16 13:04:37', '2019-04-16 13:04:37');
INSERT INTO `tr_scenic` VALUES (6, '云南丽江三日游test', 'qingcheng.png', '580', 'recommend', NULL, NULL, '云南', 0, '2019-04-16 13:05:27', '2019-04-16 13:05:27');
INSERT INTO `tr_scenic` VALUES (7, '美国七日游', 'qingcheng.png', '5800', 'abroad', NULL, NULL, '美国', 0, '2019-04-16 13:05:58', '2019-04-16 13:05:58');
INSERT INTO `tr_scenic` VALUES (8, '重庆三日游', NULL, '480', 'domestic', 'xxx-xxx-xxx', '重庆旅游介绍', '重庆', 0, '2019-04-18 23:24:15', '2019-04-18 23:24:15');
INSERT INTO `tr_scenic` VALUES (10, '测试', NULL, '180', 'recommend', '测试', '测试', '测试', 0, '2019-04-18 23:26:13', '2019-04-18 23:26:13');
INSERT INTO `tr_scenic` VALUES (11, '测试2', NULL, '800', 'recommend', '测试', '测试', '测试2', 0, '2019-04-18 23:26:28', '2019-04-18 23:26:28');

-- ----------------------------
-- Table structure for tr_scenic_order
-- ----------------------------
DROP TABLE IF EXISTS `tr_scenic_order`;
CREATE TABLE `tr_scenic_order`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '景点订单id',
  `scenic_id` int(11) NULL DEFAULT NULL COMMENT '景点id',
  `scenic_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `team_id` int(11) NULL DEFAULT NULL COMMENT '关联的团队id',
  `team_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `guide_id` int(11) NULL DEFAULT NULL,
  `guide_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `start_time` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '出发日期',
  `end_time` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '归回日期',
  `price` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '总价',
  `statu` int(1) NOT NULL DEFAULT 0 COMMENT '状态',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tr_scenic_order
-- ----------------------------
INSERT INTO `tr_scenic_order` VALUES (1, 1, '北京一日游(test)', 2, '夕阳团', 1, '导游1test', '2019-04-22', '2019-04-24', '300.0', 0, '2019-04-21 23:50:40', '2019-04-21 23:50:40');

-- ----------------------------
-- Table structure for tr_team
-- ----------------------------
DROP TABLE IF EXISTS `tr_team`;
CREATE TABLE `tr_team`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `team_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '团队名称',
  `guide_id` int(11) NULL DEFAULT NULL,
  `guide_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `person_ids` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `person_names` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `statu` int(1) NOT NULL DEFAULT 0,
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tr_team
-- ----------------------------
INSERT INTO `tr_team` VALUES (2, '夕阳团', 1, '导游1test', '1', '何宁 ', 0, '2019-04-18 17:24:37', '2019-04-19 11:12:46');

SET FOREIGN_KEY_CHECKS = 1;
