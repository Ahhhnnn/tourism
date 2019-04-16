/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : localhost:3306
 Source Schema         : attenddb

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 15/04/2019 12:53:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

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
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', '21232f297a57a5a743894a0e4a801fc3', '管理员', NULL, '男', '12345678901', NULL, 0, NULL, NULL, NULL, NULL, 0, '2018-12-19 23:30:05', '2019-02-13 12:40:48');
INSERT INTO `sys_user` VALUES (2, 'demo', 'e10adc3949ba59abbe56e057f20f883e', 'Demo01', NULL, '男', '12345678901', NULL, 0, NULL, NULL, NULL, NULL, 0, '2018-12-19 23:31:25', '2019-01-04 17:24:32');
INSERT INTO `sys_user` VALUES (3, 'hening', 'e10adc3949ba59abbe56e057f20f883e', '何宁', NULL, '男', '13699448253', NULL, 0, NULL, NULL, NULL, NULL, 0, '2019-01-23 14:00:54', '2019-01-24 11:29:31');
INSERT INTO `sys_user` VALUES (4, 'test', 'e10adc3949ba59abbe56e057f20f883e', '测试', NULL, '男', '15828401997', NULL, 0, NULL, NULL, NULL, NULL, 0, '2019-01-24 11:26:36', '2019-02-13 13:03:57');
INSERT INTO `sys_user` VALUES (6, 'ttt', 'e10adc3949ba59abbe56e057f20f883e', 'ttt', NULL, '男', '13699448253', NULL, 0, NULL, NULL, NULL, NULL, 0, '2019-01-28 09:02:10', '2019-01-28 09:02:10');
INSERT INTO `sys_user` VALUES (7, 'gggg', 'e10adc3949ba59abbe56e057f20f883e', 'gggg', NULL, '男', '13699448253', NULL, 0, NULL, NULL, NULL, NULL, 0, '2019-01-28 09:02:22', '2019-01-28 09:02:22');
INSERT INTO `sys_user` VALUES (8, 'ddd', 'e10adc3949ba59abbe56e057f20f883e', 'ddd', NULL, '男', '13699448253', NULL, 0, NULL, NULL, NULL, NULL, 0, '2019-01-28 09:02:30', '2019-01-28 09:02:30');
INSERT INTO `sys_user` VALUES (9, 'aaa', 'e10adc3949ba59abbe56e057f20f883e', 'sss', NULL, '男', '13699448253', NULL, 0, NULL, NULL, NULL, NULL, 0, '2019-01-28 09:02:39', '2019-01-28 09:02:39');
INSERT INTO `sys_user` VALUES (10, 'tttttt', 'e10adc3949ba59abbe56e057f20f883e', 'yyyy', NULL, '女', '13699448253', NULL, 0, NULL, NULL, NULL, NULL, 0, '2019-01-28 09:02:49', '2019-01-28 09:02:49');
INSERT INTO `sys_user` VALUES (11, 'jjjj', 'e10adc3949ba59abbe56e057f20f883e', 'jjj', NULL, '男', '13699448253', NULL, 0, NULL, NULL, NULL, NULL, 0, '2019-01-28 09:02:58', '2019-01-28 09:02:58');
INSERT INTO `sys_user` VALUES (12, 'hn', 'e10adc3949ba59abbe56e057f20f883e', 'hhhnn', NULL, '女', '13699448253', NULL, 0, NULL, NULL, NULL, NULL, 0, '2019-02-12 12:29:11', '2019-02-12 12:29:11');

SET FOREIGN_KEY_CHECKS = 1;
