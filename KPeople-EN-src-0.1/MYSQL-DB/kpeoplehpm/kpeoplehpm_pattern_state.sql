CREATE DATABASE  IF NOT EXISTS `kpeoplehpm` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `kpeoplehpm`;
-- MySQL dump 10.13  Distrib 5.5.9, for Win32 (x86)
--
-- Host: 192.168.0.87    Database: kpeoplehpm
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
-- Table structure for table `pattern_state`
--

DROP TABLE IF EXISTS `pattern_state`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pattern_state` (
  `ID_PATTERN_STATE` int(11) NOT NULL AUTO_INCREMENT,
  `STATE` varchar(255) DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `ID_PATTERN_TYPE` int(11) NOT NULL,
  `IS_DELETED` bit(1) DEFAULT NULL,
  `DELETED_BY` int(11) DEFAULT NULL,
  `DELETED_DATE` timestamp NULL DEFAULT NULL,
  `FIRST_ACTION_PERFORMER` int(11) DEFAULT NULL,
  `FIRST_ACTION_DATE` timestamp NULL DEFAULT NULL,
  `LAST_ACTION_PERFORMER` int(11) DEFAULT NULL,
  `LAST_ACTION_DATE` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`ID_PATTERN_STATE`)
) ENGINE=MyISAM AUTO_INCREMENT=1000 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pattern_state`
--

LOCK TABLES `pattern_state` WRITE;
/*!40000 ALTER TABLE `pattern_state` DISABLE KEYS */;
INSERT INTO `pattern_state` VALUES (4,'Annullata','Richiesta di contributo annullata',7,'\0',0,NULL,0,'2011-03-06 23:00:00',0,'2011-03-06 23:00:00'),(3,'Accettata','Richiesta di contributo accettata',7,'\0',0,NULL,0,'2011-03-06 23:00:00',0,'2011-03-06 23:00:00'),(2,'Rifiutata','Richiesta di contributo rifiutata',7,'\0',0,NULL,0,'2011-03-06 23:00:00',0,'2011-03-06 23:00:00'),(1,'Inviata','Richiesta di contributo inviata',7,'\0',0,NULL,0,'2011-03-06 23:00:00',0,'2011-03-06 23:00:00'),(5,'Ricevuto','Richiesta di contributo ricevuto',7,'\0',0,NULL,0,'2011-03-06 23:00:00',0,'2011-03-06 23:00:00');
/*!40000 ALTER TABLE `pattern_state` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2011-06-08 18:14:33
