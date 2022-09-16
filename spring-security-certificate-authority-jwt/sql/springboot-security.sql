-- --------------------------------------------------------
-- 主机:                           192.168.237.128
-- 服务器版本:                        5.7.39 - MySQL Community Server (GPL)
-- 服务器操作系统:                      Linux
-- HeidiSQL 版本:                  12.1.0.6537
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- 导出 springboot-security 的数据库结构
CREATE DATABASE IF NOT EXISTS `springboot-security` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;
USE `springboot-security`;

-- 导出  表 springboot-security.sys_role 结构
CREATE TABLE IF NOT EXISTS `sys_role` (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `code` varchar(90) DEFAULT NULL COMMENT '角色编码',
  `name` varchar(90) DEFAULT NULL COMMENT '角色名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=655 DEFAULT CHARSET=utf8 COMMENT='角色信息';

-- 正在导出表  springboot-security.sys_role 的数据：~2 rows (大约)
INSERT INTO `sys_role` (`id`, `code`, `name`) VALUES
	(1, 'ROLE_ADMIN', '超级管理员'),
	(2, 'ROLE_USER', '普通用户');

-- 导出  表 springboot-security.sys_user 结构
CREATE TABLE IF NOT EXISTS `sys_user` (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `user_name` varchar(90) DEFAULT NULL COMMENT '用户名',
  `password` varchar(90) DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=655 DEFAULT CHARSET=utf8 COMMENT='用户信息';

-- 正在导出表  springboot-security.sys_user 的数据：~2 rows (大约)
INSERT INTO `sys_user` (`id`, `user_name`, `password`) VALUES
	(1, 'admin', '$2a$10$qQSZvn5ZVR5jI5gdShVNQ.U1n7WV0t7DN2gHRD/BeJ6CrAw.nQesK'),
	(2, 'user', '$2a$10$qQSZvn5ZVR5jI5gdShVNQ.U1n7WV0t7DN2gHRD/BeJ6CrAw.nQesK');

-- 导出  表 springboot-security.sys_user_role 结构
CREATE TABLE IF NOT EXISTS `sys_user_role` (
  `user_id` int(32) NOT NULL COMMENT '用户id',
  `role_id` int(32) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`user_id`,`role_id`) USING BTREE,
  KEY `fk_role_id` (`role_id`) USING BTREE,
  CONSTRAINT `fk_role_id` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色关联信息表';

-- 正在导出表  springboot-security.sys_user_role 的数据：~3 rows (大约)
INSERT INTO `sys_user_role` (`user_id`, `role_id`) VALUES
	(1, 1),
	(1, 2),
	(2, 2);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
