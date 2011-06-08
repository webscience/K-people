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
-- Table structure for table `act_ge_bytearray`
--

DROP TABLE IF EXISTS `act_ge_bytearray`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_ge_bytearray` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `REV_` int(11) DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `DEPLOYMENT_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `BYTES_` longblob,
  PRIMARY KEY (`ID_`),
  KEY `ACT_FK_BYTEARR_DEPL` (`DEPLOYMENT_ID_`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_ge_bytearray`
--

LOCK TABLES `act_ge_bytearray` WRITE;
/*!40000 ALTER TABLE `act_ge_bytearray` DISABLE KEYS */;
INSERT INTO `act_ge_bytearray` VALUES ('111',1,'it/webscience/kpeople/activiti/pattern/richiestaContributo/RichiediContibutoFlow.bpmn20.xml','110','<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n\n<definitions id=\"definitions\"\n             targetNamespace=\"http://activiti.org/bpmn20\" \n             xmlns=\"http://www.omg.org/spec/BPMN/20100524/MODEL\"\n             xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n             xmlns:activiti=\"http://activiti.org/bpmn\">\n  \n  <process id=\"patternRichiestaContributo\" name=\"Pattern Richiesta Contributo\">\n  \n    <startEvent \n    	id=\"richiestacontributo_request\" \n    	activiti:formKey=\"it/webscience/kpeople/activiti/pattern/richiestaContributo/richiestacontributo_request.form\" />\n	\n\n    <sequenceFlow \n    	id=\"flow1\" \n    	sourceRef=\"richiestacontributo_request\" \n    	targetRef=\"richiestacontributo_accept\" />\n    \n    \n    <userTask \n    	id=\"richiestacontributo_accept\" \n    	name=\"Accetta richiesta contributo\"\n        activiti:formKey=\"it/webscience/kpeople/activiti/pattern/richiestaContributo/richiestacontributo_accept.form\" >\n      <documentation>\n        Accetta contributo da ${patternRequestor}\n      </documentation> \n      <humanPerformer>\n        <resourceAssignmentExpression>\n          <formalExpression>${patternProvider}</formalExpression>\n        </resourceAssignmentExpression>\n      </humanPerformer>         \n    </userTask>\n\n    <sequenceFlow id=\"flow2\" sourceRef=\"richiestacontributo_accept\" targetRef=\"richiestacontributo_accept_gateway\" />\n    \n    \n    <exclusiveGateway id=\"richiestacontributo_accept_gateway\" name=\"Richiesta contributo accettata?\" />\n    \n	<sequenceFlow \n		id=\"flow4\" \n		sourceRef=\"richiestacontributo_accept_gateway\" \n		targetRef=\"end_notaccepted\">\n	  <conditionExpression xsi:type=\"tFormalExpression\">${!contributeAccepted}</conditionExpression>\n	</sequenceFlow> \n    \n    <endEvent id=\"end_notaccepted\" />\n    \n    <sequenceFlow \n    	id=\"flow5\" \n    	sourceRef=\"richiestacontributo_accept_gateway\" \n    	targetRef=\"richiestacontributo_contribute\">\n	  <conditionExpression xsi:type=\"tFormalExpression\">${contributeAccepted}</conditionExpression>\n	</sequenceFlow> \n    \n    <userTask \n    	id=\"richiestacontributo_contribute\" \n    	name=\"Inserisci contributo\"\n        activiti:formKey=\"it/webscience/kpeople/activiti/pattern/richiestaContributo/richiestacontributo_contribute.form\" >\n      <documentation>\n        Contributo richiesto da ${patternRequestor}\n      </documentation> \n      <humanPerformer>\n        <resourceAssignmentExpression>\n          <formalExpression>${patternProvider}</formalExpression>\n        </resourceAssignmentExpression>\n      </humanPerformer>         \n    </userTask>\n    \n    <sequenceFlow \n    	id=\"flow6\" \n    	sourceRef=\"richiestacontributo_contribute\" \n    	targetRef=\"richiestacontributo_closepattern\" />\n    \n    <serviceTask id=\"richiestacontributo_closepattern\" \n             name=\"Chiusura ricerca contributo\" \n             activiti:class=\"it.webscience.kpeople.pattern.richiestacontributo.task.RichiestaContributoCloseTask\" />\n     \n    <sequenceFlow \n    	id=\"flow7\" \n    	sourceRef=\"richiestacontributo_closepattern\" \n    	targetRef=\"end_approved\" />\n    	        \n    <endEvent id=\"end_approved\" />\n      \n  </process>\n  \n</definitions>\n'),('112',1,'it/webscience/kpeople/activiti/pattern/richiestaContributo/richiestacontributo_contribute.form','110','<h1>Contribuisci</h1>\n<p>\n  ${patternRequestor} ti richiede il contributo:<b>${patternTitle}</b><br/><br/>\n</p>\n<p>\n  <label>\n    Il tuo contributo:<br/>\n    <textarea name=\"patternContent\" value=\"\"></textarea>\n  </label>\n</p>'),('113',1,'it/webscience/kpeople/activiti/pattern/richiestaContributo/richiestacontributo_accept.form','110','<h1>Contributo richiesto</h1>\n<p>\n  ${patternProvider} ha inserito il contributo che hai richiesto:<br/>\n  <b>${patternTitle}</b><br/>\n</p>\n<p>\n  Accettare il contributo?\n  <select name=\"contributeAccepted\">\n    <option value=\"true\">Si</option>\n    <option value=\"false\">No</option>\n   </select>\n   <input type=\"hidden\" name=\"contributeAccepted_type\" value=\"Boolean\" />\n</p>'),('114',1,'it/webscience/kpeople/activiti/pattern/richiestaContributo/richiestacontributo_approve.form','110','<h1>Contributo richiesto</h1>\n<p>\n  ${patternProvider} ha inserito il contributo che hai richiesto:<br/>\n  <b>${patternTitle}</b><br/>\n</p>\n<p>\n  ${patternContent}\n</p>\n<p>\n  Approvare il contributo?\n  <select name=\"contributeApproved\">\n    <option value=\"true\">Si</option>\n    <option value=\"false\">No</option>\n   </select>\n   <input type=\"hidden\" name=\"contributeApproved_type\" value=\"Boolean\" />\n</p>'),('115',1,'it/webscience/kpeople/activiti/pattern/richiestaContributo/richiestacontributo_request.form','110','<h1>Proponi contributo</h1>\n<table>\n  <tr>\n    <td>\n      <label>\n        Pattern provider:<br/>\n        <input type=\"text\" name=\"patternProvider\" value=\"\" />\n        <input type=\"hidden\" name=\"patternProvider_required\" value=\"true\" />\n        <input type=\"hidden\" name=\"patternProvider_type\" value=\"User\" />\n      </label><br/>\n    </td>\n  </tr>\n  <tr>\n    <td>\n      <label>\n        Pattern requestor:<br/>\n        <input type=\"text\" name=\"patternRequestor\" value=\"\" />\n        <input type=\"hidden\" name=\"patternRequestor_required\" value=\"true\" />\n        <input type=\"hidden\" name=\"patternRequestor_type\" value=\"User\" />\n      </label><br/>\n    </td>\n  </tr>\n  <tr>  \n    <td>\n      <label>\n        Titolo:<br/>\n        <input type=\"text\" name=\"patternTitle\" value=\"\"  />\n        <input type=\"hidden\" name=\"patternTitle_required\" value=\"true\" />\n      </label>\n    </td>\n  </tr>\n</table>\n');
/*!40000 ALTER TABLE `act_ge_bytearray` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2011-06-08 17:37:34
