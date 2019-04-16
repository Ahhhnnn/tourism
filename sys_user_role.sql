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

 Date: 15/04/2019 12:55:19
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

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
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户角色' ROW_FORMAT = Dynamic;

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
INSERT INTO `sys_user_role` VALUES (22, 2, 3, '2019-04-02 16:19:17');

SET FOREIGN_KEY_CHECKS = 1;
