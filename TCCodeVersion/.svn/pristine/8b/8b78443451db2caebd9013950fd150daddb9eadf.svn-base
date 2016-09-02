# ************************************************************
# Sequel Pro SQL dump
# Version 3408
#
# http://www.sequelpro.com/
# http://code.google.com/p/sequel-pro/
#
# Host: 121.40.80.249 (MySQL 5.7.9)
# Database: tccv
# Generation Time: 2016-06-08 01:27:05 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table app_version
# ------------------------------------------------------------

DROP TABLE IF EXISTS `app_version`;

CREATE TABLE `app_version` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `app_name` varchar(64) DEFAULT NULL COMMENT '项目名',
  `app_key` varchar(64) DEFAULT NULL COMMENT '项目标识',
  `app_url` varchar(128) DEFAULT NULL COMMENT '项目下载地址',
  `app_version` varchar(32) DEFAULT NULL COMMENT '项目版本号',
  `is_delete` int(1) NOT NULL DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `is_forced` int(1) NOT NULL DEFAULT '0',
  `is_released` int(1) NOT NULL DEFAULT '0',
  `display_version` varchar(16) DEFAULT NULL COMMENT '展现的版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='项目版本';

LOCK TABLES `app_version` WRITE;
/*!40000 ALTER TABLE `app_version` DISABLE KEYS */;

INSERT INTO `app_version` (`id`, `app_name`, `app_key`, `app_url`, `app_version`, `is_delete`, `create_time`, `update_time`, `is_forced`, `is_released`, `display_version`)
VALUES
	(1,'tcf','3bfde406-18bc-4942-b102-1ee1bab4212d','/appfile/2016-05-03/36848711-be35-49c4-972b-6b48e605f03f.zip','1',0,'2016-05-03 09:46:09','2016-05-03 09:46:09',0,0,NULL),
	(2,'tcf','3bfde406-18bc-4942-b102-1ee1bab4212d','/appfile/2016-05-03/2ecd3864-cd38-4e1c-b22a-ea0b4d291c7b.zip','2',0,'2016-05-03 10:07:05','2016-05-03 10:07:05',0,0,NULL),
	(3,'TCPaySJ','79799266-82c6-4ef7-8677-bf6696587545','/appfile/2016-05-04/b75a2cb5-01e2-427a-ac11-c3dec5bcb7f6.zip','2',0,'2016-05-04 10:16:56','2016-05-04 10:16:56',0,0,NULL),
	(8,'TCPayDLS','ab44237f-a214-4636-b0ea-0d98ef3b4acf','/appfile/2016-05-19/26c689fe-6041-498f-b4fd-debae5ca174d.zip','1.0',0,'2016-05-19 10:42:23','2016-05-19 10:42:23',0,0,NULL),
	(9,'TCPayDLS','ab44237f-a214-4636-b0ea-0d98ef3b4acf','/appfile/2016-05-19/084dd0a0-f37e-4f38-aad9-6f816d5ba3fa.zip','1.0',0,'2016-05-19 10:49:08','2016-05-19 10:49:08',0,0,NULL),
	(10,'TCPayDLS','ab44237f-a214-4636-b0ea-0d98ef3b4acf','/appfile/2016-05-19/1449f8d8-a7d5-4617-926e-0ea596ee7b14.zip','\"1\"',0,'2016-05-19 11:00:26','2016-05-19 11:00:26',0,0,NULL),
	(11,'TCPayDLS','ab44237f-a214-4636-b0ea-0d98ef3b4acf','/appfile/2016-05-19/7b691863-31ee-47ce-ab10-6df942d98bf6.zip','\"1\"',0,'2016-05-19 11:19:47','2016-05-19 11:19:47',0,0,NULL),
	(12,'TCPayDLS','ab44237f-a214-4636-b0ea-0d98ef3b4acf','/appfile/2016-05-19/a35c246c-b13f-4b53-969d-eb78e620bbe1.zip','0',0,'2016-05-19 11:23:33','2016-05-19 11:23:33',0,0,NULL),
	(13,'TCPayDLS','ab44237f-a214-4636-b0ea-0d98ef3b4acf','/appfile/2016-05-19/8f87588d-37de-47e0-a4a1-569d452031b3.zip','\"1\"',0,'2016-05-19 11:24:34','2016-05-19 11:24:34',0,0,NULL),
	(14,'TCPayDLS','ab44237f-a214-4636-b0ea-0d98ef3b4acf','/appfile/2016-05-19/625474b7-3eed-459f-9f1b-3e4c8e639c09.zip','\"1\"',0,'2016-05-19 11:28:18','2016-05-19 11:28:18',0,0,NULL),
	(15,'TCPayDLS','ab44237f-a214-4636-b0ea-0d98ef3b4acf','/appfile/2016-05-19/7c2e3b95-5203-49ff-a03b-081f1476a8a2.zip','\"1\"',0,'2016-05-19 11:30:32','2016-05-19 11:30:32',0,0,NULL),
	(16,'TCPayDLS','ab44237f-a214-4636-b0ea-0d98ef3b4acf','/appfile/2016-05-19/552e9fd1-4905-458a-b577-16b69579610c.zip','1',0,'2016-05-19 11:34:43','2016-05-19 11:34:43',0,0,NULL),
	(17,'TCFreeUser','0e484a2f-cbe0-4425-a86d-f94c3eb9dfc5','/appfile/2016-06-02/d436d87a-ea0b-4b67-8dcf-fddad483f775.zip','1',0,'2016-06-02 14:10:45','2016-06-02 14:10:45',0,0,NULL),
	(18,'TCFreeUser','0e484a2f-cbe0-4425-a86d-f94c3eb9dfc5','/appfile/2016-06-02/a47ae958-3941-40e2-901d-2a73732b0c23.zip','1',0,'2016-06-02 14:13:05','2016-06-02 14:13:05',0,0,NULL),
	(19,'TCFreeUser','0e484a2f-cbe0-4425-a86d-f94c3eb9dfc5','/appfile/2016-06-02/9efdc439-9d0f-4d08-bab6-d9e5fede3a97.zip','1',0,'2016-06-02 14:16:22','2016-06-02 14:16:22',0,0,NULL);

/*!40000 ALTER TABLE `app_version` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table file_version
# ------------------------------------------------------------

DROP TABLE IF EXISTS `file_version`;

CREATE TABLE `file_version` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `app_name` varchar(64) DEFAULT NULL COMMENT '项目名',
  `app_key` varchar(64) DEFAULT NULL COMMENT '项目标识',
  `app_url` varchar(128) DEFAULT NULL COMMENT '项目下载地址',
  `app_version` varchar(32) DEFAULT NULL COMMENT '项目版本号',
  `is_delete` int(1) NOT NULL DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `is_forced` int(1) NOT NULL DEFAULT '0',
  `is_released` int(1) NOT NULL DEFAULT '0',
  `display_version` varchar(16) DEFAULT NULL COMMENT '展现的版本号',
  `description` varchar(128) DEFAULT NULL COMMENT '新版本描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='项目版本';




/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
