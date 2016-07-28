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
-- Table structure for table `krainu`
--

DROP TABLE IF EXISTS `krainu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `krainu` (
  `krainuId` int(11) NOT NULL AUTO_INCREMENT,
  `nazva` char(100) NOT NULL,
  `ystriu` int(11) DEFAULT NULL,
  `k_nasel` int(11) DEFAULT NULL,
  `ploshcha` decimal(6,2) DEFAULT NULL,
  `opus` char(200) NOT NULL,
  `rozvutok` int(11) DEFAULT NULL,
  `vvp` decimal(6,2) DEFAULT NULL,
  `materukkrainuId` int(11) DEFAULT NULL,
  PRIMARY KEY (`krainuId`),
  KEY `FK_MATERUK_KRAINU` (`materukkrainuId`),
  CONSTRAINT `FK_MATERUK_KRAINU` FOREIGN KEY (`materukkrainuId`) REFERENCES `materuk` (`materukId`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `krainu`
--

LOCK TABLES `krainu` WRITE;
/*!40000 ALTER TABLE `krainu` DISABLE KEYS */;
INSERT INTO `krainu` VALUES (1,'Франція',2,65073,543.97,'Держава на заході Європи. Столиця і найбільше місто — Париж',1,2218.00,1),(2,'Німеччина',3,81844,357.02,'Демократична федеративна республіка у центрі Європи.',1,3305.00,1),(3,'Данія',1,5432,43.00,'Данія - скандинавська країна у Північній Європі. Розташована на південний захід від Швеції, і на південь від Норвегї',1,198.50,1),(4,'Фінляндія',2,5457,338.00,'Країна розташована в Північній Європі. Межує зі Швецією (спільний кордон — 586 км), з Норвегією (729 км), з Російською Федерацією (1313 км)',1,179.50,1),(5,'Польща',2,38186,312.60,'Польща -  держава у Центральній Європі, унітарна парламентська республіка, що має в своєму складі 16 воєводств',2,513.90,1),(6,'Італія',2,59464,301.30,'Італія - держава на півдні Європи, в Середземномор\'ї. Займає Апеннінський півострів, Паданську рівнину, південні схили Альп, острови Сицилія, Сардинія тощо',1,2147.00,1),(7,'Китай',2,1368660,9596.00,'Китай - культурний регіон і стародавня цивілізація Східної Азії. Китай належить до найбільш прадавніх цивілізацій, яка увібрала у себе велику кількість держав та культур впродовж 6 тисяч років.',2,9238.00,1),(8,'Індія',2,1210193,3287.00,'Індія - країна в Південній Азії. На північному заході межує з Пакистаном; на півночі — з КНР, Непалом і Бутаном; на сході — з М\'янмою і Бангладеш.',2,4457.00,1);
/*!40000 ALTER TABLE `krainu` ENABLE KEYS */;
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
