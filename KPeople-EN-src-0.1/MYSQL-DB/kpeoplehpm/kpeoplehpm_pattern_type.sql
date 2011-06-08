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
-- Table structure for table `pattern_type`
--

DROP TABLE IF EXISTS `pattern_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pattern_type` (
  `ID_PATTERN_TYPE` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) NOT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `IS_ACTIVE` bit(1) DEFAULT NULL,
  `SHOW_IN_LIST` bit(1) DEFAULT NULL,
  `VERSION` varchar(255) DEFAULT NULL,
  `RELATED_FORM` varchar(255) DEFAULT NULL,
  `PATTERN_TYPE_CODE` varchar(255) DEFAULT NULL,
  `ORDERING` int(11) DEFAULT '0',
  `WAITING_ACTIVITY` bit(1) DEFAULT NULL,
  `IS_DELETED` bit(1) DEFAULT NULL,
  `DELETED_BY` int(11) DEFAULT NULL,
  `DELETED_DATE` timestamp NULL DEFAULT NULL,
  `FIRST_ACTION_PERFORMER` int(11) DEFAULT NULL,
  `FIRST_ACTION_DATE` timestamp NULL DEFAULT NULL,
  `LAST_ACTION_PERFORMER` int(11) DEFAULT NULL,
  `LAST_ACTION_DATE` timestamp NULL DEFAULT NULL,
  `ACTIVITI_PROCESS_DEFINITION_ID` varchar(255) NOT NULL,
  `HPM_PATTERN_TYPE_ID` varchar(255) NOT NULL,
  PRIMARY KEY (`ID_PATTERN_TYPE`)
) ENGINE=MyISAM AUTO_INCREMENT=1002 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pattern_type`
--

LOCK TABLES `pattern_type` WRITE;
/*!40000 ALTER TABLE `pattern_type` DISABLE KEYS */;
INSERT INTO `pattern_type` VALUES (2,'Delega','pattern che non si deve vedere nella lista','','\0','1',NULL,'DELEGA',3,'\0','\0',0,NULL,1,'2011-03-02 09:47:51',NULL,NULL,'patterninvisibile','patterninvisibile'),(5,'Sollecito','pattern di esempio 3','','\0','1',NULL,'SOLLECITO',4,'\0','\0',0,NULL,1,'2011-03-03 23:00:00',NULL,NULL,'patternesempio3','patternesempio3'),(4,'Esclation','pattern di esempio 2','','\0','1',NULL,'ESCALATION',5,'\0','\0',0,NULL,1,'2011-03-03 23:00:00',NULL,NULL,'patternesempio2','patternesempio2'),(3,'Pianifica riunione','pattern di esempio 1','','\0','1',NULL,'RIUNIONE',6,'\0','\0',0,NULL,1,'2011-03-04 09:47:51',NULL,NULL,'patternesempio1','patternesempio1'),(6,'Richiedi Autorizzazione','pattern semplice che invia una mail','','\0','1',NULL,'RICHIEDI',7,'\0','\0',0,NULL,1,'2011-03-04 09:47:51',NULL,NULL,'patternMailerFlow1:1:268','patternMailerFlow1:1:268'),(7,'Richiedi contributo','Pattern per la richiesta contributo','','','1',NULL,'PATRICCONTR',2,'','\0',0,NULL,1,'2011-03-18 09:47:51',NULL,NULL,'patternRichiestaContributo:1:116','patternRichiestaContributo:1:116');
/*!40000 ALTER TABLE `pattern_type` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2011-06-08 18:01:40
