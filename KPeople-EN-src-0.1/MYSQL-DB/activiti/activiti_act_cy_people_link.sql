CREATE DATABASE  IF NOT EXISTS `activiti` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `activiti`;
-- MySQL dump 10.13  Distrib 5.5.9, for Win32 (x86)
--
-- Host: 192.168.0.87    Database: activiti
-- ------------------------------------------------------
-- Server version	5.1.41-3ubuntu12.10

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `act_cy_people_link`
--

DROP TABLE IF EXISTS `act_cy_people_link`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_cy_people_link` (
  `ID_` varchar(255) NOT NULL,
  `SOURCE_CONNECTOR_ID_` varchar(255) DEFAULT NULL,
  `SOURCE_ARTIFACT_ID_` varchar(550) DEFAULT NULL,
  `SOURCE_REVISION_` bigint(20) DEFAULT NULL,
  `USER_ID_` varchar(255) DEFAULT NULL,
  `GROUP_ID_` varchar(255) DEFAULT NULL,
  `LINK_TYPE_` varchar(255) DEFAULT NULL,
  `COMMENT_` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_cy_people_link`
--

LOCK TABLES `act_cy_people_link` WRITE;
/*!40000 ALTER TABLE `act_cy_people_link` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_cy_people_link` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2011-06-08 17:38:10
