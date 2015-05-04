/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50623
 Source Host           : localhost
 Source Database       : infos_gwt

 Target Server Type    : MySQL
 Target Server Version : 50623
 File Encoding         : utf-8

 Date: 05/04/2015 22:22:16 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `blogs`
-- ----------------------------
DROP TABLE IF EXISTS `blogs`;
CREATE TABLE `blogs` (
  `id_` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name_` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '创建人',
  `category_1_` bigint(20) NOT NULL COMMENT '文章一级分类',
  `category_2_` bigint(20) NOT NULL COMMENT '文章二级分类',
  `title_` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '文章标题',
  `content_` varchar(40) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '正文存储文件名',
  `add_time_` datetime NOT NULL COMMENT '创建时间',
  `update_time_` datetime NOT NULL COMMENT '更新时间',
  `is_del_` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `category`
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id_` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '记录主键',
  `name_` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '名称',
  `level_` int(3) NOT NULL COMMENT '分类层级',
  `parent_category_` bigint(20) NOT NULL DEFAULT '0' COMMENT '父分类',
  `is_del_` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  `add_time_` datetime NOT NULL COMMENT '创建时间',
  `update_time_` datetime NOT NULL COMMENT '更新时间',
  `update_user_` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '更新人',
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `links`
-- ----------------------------
DROP TABLE IF EXISTS `links`;
CREATE TABLE `links` (
  `id_` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name_` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '名称',
  `link_` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '链接url',
  `add_time_` datetime NOT NULL COMMENT '创建时间',
  `update_time_` datetime NOT NULL COMMENT '更新时间',
  `is_del_` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标志',
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `members`
-- ----------------------------
DROP TABLE IF EXISTS `members`;
CREATE TABLE `members` (
  `id_` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `real_name_` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '姓名',
  `user_name_` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '账户名',
  `role_group_` tinyint(2) NOT NULL COMMENT '用户角色',
  `is_del_` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标志',
  `add_time_` datetime NOT NULL COMMENT '创建时间',
  `update_time_` datetime NOT NULL COMMENT '更新时间',
  `uid_` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '集团id',
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `slider`
-- ----------------------------
DROP TABLE IF EXISTS `slider`;
CREATE TABLE `slider` (
  `id_` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `idx_` tinyint(3) NOT NULL DEFAULT '0' COMMENT '序号',
  `origin_name_` varchar(150) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '源文件名',
  `dest_name_` varchar(40) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '存储文件名',
  `is_del_` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标志',
  `add_time_` datetime NOT NULL COMMENT '创建时间',
  `update_time_` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
