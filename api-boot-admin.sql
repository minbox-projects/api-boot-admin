-- MySQL dump 10.13  Distrib 8.0.18, for osx10.15 (x86_64)
--
-- Host: 127.0.0.1    Database: api-boot-admin
-- ------------------------------------------------------
-- Server version	8.0.18

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- 创建数据库
create database api-boot-admin default character set utf8mb4 collate utf8mb4_unicode_ci;

-- 使用api-boot-admin数据库
use api-boot-admin;
--
-- Table structure for table `clientdetails`
--

DROP TABLE IF EXISTS `clientdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clientdetails` (
  `appId` varchar(128) NOT NULL,
  `resourceIds` varchar(256) DEFAULT NULL,
  `appSecret` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `grantTypes` varchar(256) DEFAULT NULL,
  `redirectUrl` varchar(256) DEFAULT NULL,
  `authorities` varchar(256) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additionalInformation` varchar(4096) DEFAULT NULL,
  `autoApproveScopes` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`appId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientdetails`
--

LOCK TABLES `clientdetails` WRITE;
/*!40000 ALTER TABLE `clientdetails` DISABLE KEYS */;
/*!40000 ALTER TABLE `clientdetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `logging_global_logs`
--

DROP TABLE IF EXISTS `logging_global_logs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `logging_global_logs` (
  `lgl_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '日志主键',
  `lgl_request_log_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '请求日志编号，关联logging_request_logs主键',
  `lgl_level` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '日志等级',
  `lgl_content` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '日志内容',
  `lgl_caller_class` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '日志输出的类名',
  `lgl_caller_method` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '日志输出的方法名称',
  `lgl_caller_code_line_number` int(11) DEFAULT NULL COMMENT '日志输出的代码行号',
  `lgl_exception_stack` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT 'error等级的日志异常堆栈信息',
  `lgl_create_time` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '日志发生时间',
  PRIMARY KEY (`lgl_id`),
  KEY `logging_global_logs_logging_request_logs_lrl_id_fk` (`lgl_request_log_id`),
  CONSTRAINT `logging_global_logs_logging_request_logs_lrl_id_fk` FOREIGN KEY (`lgl_request_log_id`) REFERENCES `logging_request_logs` (`lrl_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='全局日志信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `logging_global_logs`
--

LOCK TABLES `logging_global_logs` WRITE;
/*!40000 ALTER TABLE `logging_global_logs` DISABLE KEYS */;
/*!40000 ALTER TABLE `logging_global_logs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `logging_request_logs`
--

DROP TABLE IF EXISTS `logging_request_logs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `logging_request_logs` (
  `lrl_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键，UUID',
  `lrl_service_detail_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '服务详情编号，关联logging_service_details主键',
  `lrl_trace_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '链路ID',
  `lrl_parent_span_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '上级跨度ID',
  `lrl_span_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '跨度ID',
  `lrl_start_time` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '请求开始时间',
  `lrl_end_time` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '请求结束时间',
  `lrl_http_status` int(11) DEFAULT NULL COMMENT '请求响应状态码',
  `lrl_request_body` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '请求主体内容',
  `lrl_request_headers` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '请求头信息',
  `lrl_request_ip` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '发起请求客户端的IP地址',
  `lrl_request_method` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '请求方式',
  `lrl_request_uri` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '请求路径',
  `lrl_response_body` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '响应内容',
  `lrl_response_headers` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '响应头信息',
  `lrl_time_consuming` int(11) DEFAULT NULL COMMENT '请求耗时',
  `lrl_create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '日志保存时间',
  `lrl_request_params` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
  `lrl_exception_stack` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
  PRIMARY KEY (`lrl_id`),
  KEY `logging_request_logs_LRL_SERVICE_DETAIL_ID_index` (`lrl_service_detail_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='请求日志信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `logging_request_logs`
--

LOCK TABLES `logging_request_logs` WRITE;
/*!40000 ALTER TABLE `logging_request_logs` DISABLE KEYS */;
/*!40000 ALTER TABLE `logging_request_logs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `logging_service_details`
--

DROP TABLE IF EXISTS `logging_service_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `logging_service_details` (
  `lsd_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `lsd_service_id` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '上报服务的ID，对应spring.application.name配置值',
  `lsd_service_ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '上报服务的IP地址',
  `lsd_service_port` int(11) DEFAULT NULL COMMENT '上报服务的端口号',
  `lsd_last_report_time` timestamp NULL DEFAULT NULL COMMENT '最后一次上报时间，每次上报更新',
  `lsd_create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '首次上报时创建时间',
  PRIMARY KEY (`lsd_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='上报日志的客户端服务详情';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `logging_service_details`
--

LOCK TABLES `logging_service_details` WRITE;
/*!40000 ALTER TABLE `logging_service_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `logging_service_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oauth_access_token`
--

DROP TABLE IF EXISTS `oauth_access_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `oauth_access_token` (
  `token_id` varchar(256) DEFAULT NULL,
  `token` blob,
  `authentication_id` varchar(128) NOT NULL,
  `user_name` varchar(256) DEFAULT NULL,
  `client_id` varchar(256) DEFAULT NULL,
  `authentication` blob,
  `refresh_token` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`authentication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oauth_access_token`
--

LOCK TABLES `oauth_access_token` WRITE;
/*!40000 ALTER TABLE `oauth_access_token` DISABLE KEYS */;
/*!40000 ALTER TABLE `oauth_access_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oauth_approvals`
--

DROP TABLE IF EXISTS `oauth_approvals`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `oauth_approvals` (
  `userId` varchar(256) DEFAULT NULL,
  `clientId` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `expiresAt` datetime DEFAULT NULL,
  `lastModifiedAt` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oauth_approvals`
--

LOCK TABLES `oauth_approvals` WRITE;
/*!40000 ALTER TABLE `oauth_approvals` DISABLE KEYS */;
/*!40000 ALTER TABLE `oauth_approvals` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oauth_client_details`
--

DROP TABLE IF EXISTS `oauth_client_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `oauth_client_details` (
  `client_id` varchar(128) NOT NULL,
  `resource_ids` varchar(256) DEFAULT NULL,
  `client_secret` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `authorized_grant_types` varchar(256) DEFAULT NULL,
  `web_server_redirect_uri` varchar(256) DEFAULT NULL,
  `authorities` varchar(256) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additional_information` varchar(4096) DEFAULT NULL,
  `autoapprove` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oauth_client_details`
--

LOCK TABLES `oauth_client_details` WRITE;
/*!40000 ALTER TABLE `oauth_client_details` DISABLE KEYS */;
INSERT INTO `oauth_client_details` VALUES ('api-boot-admin','api','$2a$10$lpVCA8VFwzzbl12tqJfO6O459I0UsXjOTMbaBwnxVqXczg9K4nJa6','read,write','password,refresh_token',NULL,NULL,7200,2592000,NULL,NULL);
/*!40000 ALTER TABLE `oauth_client_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oauth_client_token`
--

DROP TABLE IF EXISTS `oauth_client_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `oauth_client_token` (
  `token_id` varchar(256) DEFAULT NULL,
  `token` blob,
  `authentication_id` varchar(128) NOT NULL,
  `user_name` varchar(256) DEFAULT NULL,
  `client_id` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`authentication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oauth_client_token`
--

LOCK TABLES `oauth_client_token` WRITE;
/*!40000 ALTER TABLE `oauth_client_token` DISABLE KEYS */;
/*!40000 ALTER TABLE `oauth_client_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oauth_code`
--

DROP TABLE IF EXISTS `oauth_code`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `oauth_code` (
  `code` varchar(256) DEFAULT NULL,
  `authentication` blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oauth_code`
--

LOCK TABLES `oauth_code` WRITE;
/*!40000 ALTER TABLE `oauth_code` DISABLE KEYS */;
/*!40000 ALTER TABLE `oauth_code` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oauth_refresh_token`
--

DROP TABLE IF EXISTS `oauth_refresh_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `oauth_refresh_token` (
  `token_id` varchar(256) DEFAULT NULL,
  `token` blob,
  `authentication` blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oauth_refresh_token`
--

LOCK TABLES `oauth_refresh_token` WRITE;
/*!40000 ALTER TABLE `oauth_refresh_token` DISABLE KEYS */;
/*!40000 ALTER TABLE `oauth_refresh_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `system_user`
--

DROP TABLE IF EXISTS `system_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `system_user` (
  `su_user_id` varchar(36) COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户编号',
  `su_avatar` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '头像路径',
  `su_username` varchar(30) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户名',
  `su_password` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '密码密文',
  `su_email` varchar(30) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '邮箱地址',
  `su_age` int(11) DEFAULT NULL COMMENT '年龄',
  `su_address` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '地址',
  `su_create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '用户创建时间，默认当前时间',
  `su_status` int(11) DEFAULT '1' COMMENT '用户状态，1：正常，0：冻结，-1：已删除',
  `su_mark` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`su_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='系统用户信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `system_user`
--

LOCK TABLES `system_user` WRITE;
/*!40000 ALTER TABLE `system_user` DISABLE KEYS */;
INSERT INTO `system_user` VALUES ('24af3f79-1dbb-42af-a239-572f4baf83a5','/images/avatar.jpeg','yuqiyu','$2a$10$DtiYlg/67VqOJMhFcUjnK.glaY/t302P599W/kB/fgr1c41t1hfVe','yuqiyu@vip.qq.com',NULL,'山东济南','2020-01-07 02:01:33',1,NULL),('7d074374-2d0a-11ea-a821-44de8f3045ca','/images/avatar.jpeg','hengboy','$2a$10$lpVCA8VFwzzbl12tqJfO6O459I0UsXjOTMbaBwnxVqXczg9K4nJa6','jnyuqy@gmail.com',25,'山东济南','2020-01-02 02:49:25',1,'密码为：123456');
/*!40000 ALTER TABLE `system_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-01-07 11:00:50
