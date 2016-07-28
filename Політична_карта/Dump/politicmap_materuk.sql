-- MySQL dump 10.13  Distrib 5.6.24, for Win64 (x86_64)
--
-- Host: localhost    Database: politicmap
-- ------------------------------------------------------
-- Server version	5.6.27-log

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
-- Table structure for table `materuk`
--

DROP TABLE IF EXISTS `materuk`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `materuk` (
  `materukId` int(11) NOT NULL AUTO_INCREMENT,
  `nazva` char(50) NOT NULL,
  `ploshcha` decimal(8,2) DEFAULT NULL,
  `k_naselennja` int(11) DEFAULT NULL,
  `k_krain` int(11) DEFAULT NULL,
  `opus` char(100) DEFAULT NULL,
  PRIMARY KEY (`materukId`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `materuk`
--

LOCK TABLES `materuk` WRITE;
/*!40000 ALTER TABLE `materuk` DISABLE KEYS */;
INSERT INTO `materuk` VALUES (1,'Євразія',53.90,4325,89,'Євразія — омивається водами Тихого, Атлантичного, Індійського та Північного Льодовитого океанів.'),(2,'Африка',30.30,920,54,'Африка найбільший з трьох материків південної групи'),(3,'Північна Амерка',24.25,528,44,'Береги континенту сильно розчленовані на півночі, північному заході й північному сході'),(4,'Південна Америка',18.28,382,18,'До складу Південної Америки також входять різні острови'),(5,'Антрактида',13.97,0,0,'Антарктида — найвищий континент Землі, середня висота поверхні континенту над рівнем моря'),(6,'Австралія',7.68,320,1,'Теринами, Австралія межує з Азією через Арафурське та Тиморське моря.'),(7,'Азія',43.42,3600,52,'Від Північної Америки відокремлена Беринґовою протокою'),(8,'Європа',10.52,792,37,'З Азією Європа утворює континент Євразію, займає 8% поверхні суходолу');
/*!40000 ALTER TABLE `materuk` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-04-09 23:01:06
