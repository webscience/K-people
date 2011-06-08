CREATE DATABASE  IF NOT EXISTS `kpeopler` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `kpeopler`;
-- MySQL dump 10.13  Distrib 5.5.9, for Win32 (x86)
--
-- Host: 192.168.0.87    Database: kpeopler
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
-- Table structure for table `user_belongs_process`
--

DROP TABLE IF EXISTS `user_belongs_process`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_belongs_process` (
  `ID_USER` int(11) NOT NULL,
  `ID_PROCESS` char(18) NOT NULL,
  `START_BELONG_DATE` timestamp NULL DEFAULT NULL,
  `IS_DELETED` bit(1) DEFAULT NULL,
  `DELETED_BY` int(11) DEFAULT NULL,
  `DELETED_DATE` timestamp NULL DEFAULT NULL,
  `FIRST_ACTION_PERFORMER` int(11) DEFAULT NULL,
  `FIRST_ACTION_DATE` timestamp NULL DEFAULT NULL,
  `LAST_ACTION_PERFORMER` int(11) DEFAULT NULL,
  `LAST_ACTION_DATE` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`ID_USER`,`ID_PROCESS`),
  KEY `R_12` (`ID_PROCESS`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_belongs_process`
--

LOCK TABLES `user_belongs_process` WRITE;
/*!40000 ALTER TABLE `user_belongs_process` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_belongs_process` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2011-06-08 18:49:53
