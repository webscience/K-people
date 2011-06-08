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
-- Table structure for table `activity_type`
--

DROP TABLE IF EXISTS `activity_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activity_type` (
  `ID_ACTIVITY_TYPE` int(11) NOT NULL,
  `NAME` varchar(255) NOT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `RELATED_FORM` varchar(255) DEFAULT NULL,
  `IS_DELETED` bit(1) DEFAULT NULL,
  `DELETED_BY` int(11) DEFAULT NULL,
  `DELETED_DATE` timestamp NULL DEFAULT NULL,
  `FIRST_ACTION_PERFORMER` int(11) DEFAULT NULL,
  `FIRST_ACTION_DATE` timestamp NULL DEFAULT NULL,
  `LAST_ACTION_PERFORMER` int(11) DEFAULT NULL,
  `LAST_ACTION_DATE` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`ID_ACTIVITY_TYPE`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity_type`
--

LOCK TABLES `activity_type` WRITE;
/*!40000 ALTER TABLE `activity_type` DISABLE KEYS */;
INSERT INTO `activity_type` VALUES (1,'defaultactivity','attività di default','defaultform','\0',NULL,NULL,NULL,NULL,NULL,NULL),(4,'richiestacontributo_contribute','Invio contributo','it/webscience/kpeople/activiti/pattern/richiestaContributo/richiestacontributo_contribute.form','\0',NULL,NULL,NULL,NULL,NULL,NULL),(3,'richiestacontributo_accept','Contributo richiesto','it/webscience/kpeople/activiti/pattern/richiestaContributo/richiestacontributo_accept.form','\0',NULL,NULL,NULL,NULL,NULL,NULL),(2,'richiestacontributo_request','Richiesta contributo','it/webscience/kpeople/activiti/pattern/richiestaContributo/richiestacontributo_request.form','\0',NULL,NULL,NULL,NULL,NULL,NULL),(7,'richiestacontributo1_view','Visualizza contributo','it/webscience/kpeople/activiti/examples/richiestaContributo/2/notifycontribute.form','\0',NULL,NULL,NULL,NULL,NULL,NULL),(6,'richiestacontributo1_approve','Approva contributo','it/webscience/kpeople/activiti/examples/richiestaContributo/2/approvecontribute.form','\0',NULL,NULL,NULL,NULL,NULL,NULL),(5,'richiestacontributo1_contribute','Invio contributo','it/webscience/kpeople/activiti/examples/richiestaContributo/2/contribute.form','\0',NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `activity_type` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2011-06-08 18:14:38
