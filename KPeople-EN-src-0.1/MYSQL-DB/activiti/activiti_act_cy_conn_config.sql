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
-- Table structure for table `act_cy_conn_config`
--

DROP TABLE IF EXISTS `act_cy_conn_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_cy_conn_config` (
  `ID_` varchar(255) COLLATE utf8_bin NOT NULL,
  `PLUGIN_ID_` varchar(255) COLLATE utf8_bin NOT NULL,
  `INSTANCE_NAME_` varchar(255) COLLATE utf8_bin NOT NULL,
  `INSTANCE_ID_` varchar(255) COLLATE utf8_bin NOT NULL,
  `USER_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `GROUP_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `VALUES_` text COLLATE utf8_bin,
  PRIMARY KEY (`ID_`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_cy_conn_config`
--

LOCK TABLES `act_cy_conn_config` WRITE;
/*!40000 ALTER TABLE `act_cy_conn_config` DISABLE KEYS */;
INSERT INTO `act_cy_conn_config` VALUES ('1','org.activiti.cycle.impl.connector.fs.FileSystemConnector','Eclipse Workspace (File System)','Workspace','kermit','','<map><entry><string>basePath</string><string>/Developer/_/activiti-5.2/workspace/activiti-cycle-examples</string></entry></map>'),('2','org.activiti.cycle.impl.connector.signavio.SignavioConnector','Activiti Modeler','Activiti','kermit','','<map> <entry><string>signavioBaseUrl</string><string>http://localhost:8080/activiti-modeler/</string></entry> <entry><string>loginRequired</string><boolean>false</boolean></entry> </map>'),('3','org.activiti.cycle.impl.connector.fs.FileSystemConnector','Eclipse Workspace (File System)','Workspace','fozzie','','<map><entry><string>basePath</string><string>/Developer/_/activiti-5.2/workspace/activiti-cycle-examples</string></entry></map>'),('4','org.activiti.cycle.impl.connector.signavio.SignavioConnector','Activiti Modeler','Activiti','fozzie','','<map> <entry><string>signavioBaseUrl</string><string>http://localhost:8080/activiti-modeler/</string></entry> <entry><string>loginRequired</string><boolean>false</boolean></entry> </map>'),('5','org.activiti.cycle.impl.connector.fs.FileSystemConnector','Eclipse Workspace (File System)','Workspace','gonzo','','<map><entry><string>basePath</string><string>/Developer/_/activiti-5.2/workspace/activiti-cycle-examples</string></entry></map>'),('6','org.activiti.cycle.impl.connector.signavio.SignavioConnector','Activiti Modeler','Activiti','gonzo','','<map> <entry><string>signavioBaseUrl</string><string>http://localhost:8080/activiti-modeler/</string></entry> <entry><string>loginRequired</string><boolean>false</boolean></entry> </map>');
/*!40000 ALTER TABLE `act_cy_conn_config` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2011-06-08 17:37:40
