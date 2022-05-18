CREATE DATABASE  IF NOT EXISTS `service_center` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `service_center`;
-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: localhost    Database: service_center
-- ------------------------------------------------------
-- Server version	8.0.27

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `addresses`
--

DROP TABLE IF EXISTS `addresses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `addresses` (
  `address_id` bigint NOT NULL AUTO_INCREMENT,
  `country` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `postcode` int DEFAULT NULL,
  `state` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `region` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `city` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `street` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `house_number` int NOT NULL,
  `apartment_number` int DEFAULT NULL,
  PRIMARY KEY (`address_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `addresses`
--

LOCK TABLES `addresses` WRITE;
/*!40000 ALTER TABLE `addresses` DISABLE KEYS */;
INSERT INTO `addresses` VALUES (1,'Россия',101000,'','','Минск','ул.Лесная',11,258),(2,'Беларусь',220006,'','','Минск','пр.Джержинского ',112,43),(4,'Россия',101000,NULL,NULL,'Москва','ул.Измайловская',125,12),(5,'Беларусь',684654,'Минская',NULL,'Минск','пр. Победителей',12,47),(6,'Беларусь',NULL,NULL,NULL,'Гомель','Набережная',846,NULL),(7,'Беларусь',275275,'Минская','Осиповичский','Осиповичи','ул. Красная',2,1),(8,'Беларусь',NULL,NULL,NULL,'Минск','Цветочная',11,NULL),(9,'',213800,'Могилевская','','Бобруйск','проспект Строителей',24,0),(10,'Беларусь',NULL,NULL,NULL,'Минск','Цветочная',11,NULL),(11,NULL,213800,'Могилевская',NULL,'Бобруйск','проспект Строителей',24,NULL),(12,NULL,213800,'Могилевская',NULL,'Бобруйск','проспект Строителей',24,NULL),(13,'Беларусь',165153,'Могилевская','Могилевский','Могилев','ул. Центральная',1,15),(14,'Беларусь',NULL,NULL,NULL,'Минск','Андреевская',12,1),(17,NULL,0,NULL,NULL,'Минск','Монтажников',34,0),(18,'Беларусь',210555,'Главная область','Неизвестный','Университетск','Ученая',24,45),(19,NULL,NULL,NULL,NULL,'Минск','ул. Семенова',24,45),(20,NULL,NULL,NULL,NULL,'Менеджерск','Менеджерская',11,NULL),(21,NULL,NULL,NULL,NULL,'Минск','ул. Правды',52,54),(22,NULL,NULL,NULL,NULL,'Москва','Набережная',45,NULL);
/*!40000 ALTER TABLE `addresses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clients`
--

DROP TABLE IF EXISTS `clients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clients` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `discount` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id_idx` (`user_id`),
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clients`
--

LOCK TABLES `clients` WRITE;
/*!40000 ALTER TABLE `clients` DISABLE KEYS */;
INSERT INTO `clients` VALUES (1,2,5),(3,4,0),(4,6,0),(6,9,0),(7,10,NULL),(8,11,0),(9,12,NULL),(10,13,NULL),(11,14,NULL),(12,15,NULL),(13,18,NULL);
/*!40000 ALTER TABLE `clients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `codes`
--

DROP TABLE IF EXISTS `codes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `codes` (
  `email` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `code` varchar(5) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `codes`
--

LOCK TABLES `codes` WRITE;
/*!40000 ALTER TABLE `codes` DISABLE KEYS */;
INSERT INTO `codes` VALUES ('dyushka80@gmail.com','79301'),('petrov@mail.ru','64898');
/*!40000 ALTER TABLE `codes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `companies`
--

DROP TABLE IF EXISTS `companies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `companies` (
  `company_id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(15) CHARACTER SET utf16 COLLATE utf16_general_ci NOT NULL,
  `is_service_contract` tinyint(1) NOT NULL,
  PRIMARY KEY (`company_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `companies`
--

LOCK TABLES `companies` WRITE;
/*!40000 ALTER TABLE `companies` DISABLE KEYS */;
INSERT INTO `companies` VALUES (1,'Samsung',1),(2,'Panasonic',1),(3,'LG',1),(4,'Philips',0),(5,'Tefal',1),(6,'Rowenta',1),(7,'Moulinex',0),(8,'Apple',0),(9,'Asus',0),(10,'Aser',0),(11,'Xiaomi',0),(12,'',0),(13,'Dell',0),(14,'',0),(15,'Indesit',0),(16,'Kyocera',0);
/*!40000 ALTER TABLE `companies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `devices`
--

DROP TABLE IF EXISTS `devices`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `devices` (
  `device_id` bigint NOT NULL AUTO_INCREMENT,
  `device_name` varchar(25) NOT NULL,
  PRIMARY KEY (`device_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf16;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `devices`
--

LOCK TABLES `devices` WRITE;
/*!40000 ALTER TABLE `devices` DISABLE KEYS */;
INSERT INTO `devices` VALUES (1,'Смартфон'),(2,'Ноутбук'),(3,'Телевизор'),(4,'Холодильник'),(5,'СВЧ-печь'),(6,'Пылесос'),(7,'Кухонный комбайн'),(8,'Монитор'),(9,'Робот пылесос'),(10,'Музыкальный центр');
/*!40000 ALTER TABLE `devices` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employees`
--

DROP TABLE IF EXISTS `employees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employees` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `login` varchar(30) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id_idx` (`user_id`),
  CONSTRAINT `employee_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employees`
--

LOCK TABLES `employees` WRITE;
/*!40000 ALTER TABLE `employees` DISABLE KEYS */;
INSERT INTO `employees` VALUES (1,1,'admin','a9bed6e9ea4acbe1187eee5f3a16ad44:7e6c619ccb30b46bb7bc6905e54626f7fe35638b27d12b0297adafa54c67c30f'),(2,5,'manager','d6c999e5e719a9d3ab710195d44bd638:e84bab60b358fa39e01641a8a300e9963f0bca9a4f77d06d4295d26469e546b0'),(3,7,'engineer','797f4249cb7969933bcecc43acec2d47:fc7501d2c382ea26da91757a1a90b069824dfde10828fa1d7ecffa8c48f346d9'),(5,17,'manager1','82714a09cf6696ea7357ed5a41890b2f:b453c84ec8e94765590c9f167d079f4c6517b865141e4502ed793b26d59e5adb'),(6,19,'engineer1','230d2c0aa69667f86c50eed68b45c302:020a3e44b6f186899740d1ee6d65a0883975ec457f8d68a22d49d33ee369cc47'),(7,20,'manager2','dc9321868bcd5ee64b5fbeeb63452309:256feb19b526775e920e072aee26d3486469d5010e150a5ed9c9f00db67a5cd1'),(8,21,'engineer2','0b1a01c75431f0ac73517e1880b3067e:4e9bb0eae2272167a501bebdaae1df3f5c2620b53e8c4e320f6e07197b8daa74'),(9,22,'manager3','d75fff6660b756ce03628f240a9a6f4d:b78915a82b66a1b1fe51fce72f7f8a17e5052da924f18fead9244c41d11a1b75'),(10,23,'engineer3','332052afcdf8052d349331574376310d:fff1fc2ff563442169d5f142e8b1e4ceefd3ef1da35a25e9224d4e35f6ef01c7'),(11,24,'manager4',NULL),(12,25,'manager5',NULL),(13,26,'engineer4',NULL);
/*!40000 ALTER TABLE `employees` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `order_id` bigint NOT NULL AUTO_INCREMENT,
  `order_number` varchar(10) CHARACTER SET utf16 COLLATE utf16_general_ci NOT NULL,
  `creation_date` datetime NOT NULL,
  `client` bigint NOT NULL,
  `accepted_employee` bigint NOT NULL,
  `device` bigint NOT NULL,
  `company` bigint DEFAULT NULL,
  `model` varchar(15) CHARACTER SET utf16 COLLATE utf16_general_ci DEFAULT NULL,
  `serial_number` varchar(20) CHARACTER SET utf16 COLLATE utf16_general_ci DEFAULT NULL,
  `completed_employee` bigint DEFAULT NULL,
  `completion_date` datetime DEFAULT NULL,
  `issue_date` datetime DEFAULT NULL,
  `work_description` varchar(45) DEFAULT NULL,
  `work_price` bigint DEFAULT NULL,
  `order_status` enum('ACCEPTED','IN_PROGRESS','CLOSED','ISSUED') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `note` text,
  PRIMARY KEY (`order_id`),
  KEY `device_idx` (`device`),
  KEY `company_idx` (`company`),
  KEY `client_idx` (`client`),
  KEY `accepted_employee_idx` (`accepted_employee`),
  KEY `completed_employee_idx` (`completed_employee`),
  KEY `work_cost_idx` (`work_price`),
  CONSTRAINT `accepted_employee` FOREIGN KEY (`accepted_employee`) REFERENCES `employees` (`user_id`),
  CONSTRAINT `client` FOREIGN KEY (`client`) REFERENCES `clients` (`user_id`),
  CONSTRAINT `company` FOREIGN KEY (`company`) REFERENCES `companies` (`company_id`),
  CONSTRAINT `completed_employee` FOREIGN KEY (`completed_employee`) REFERENCES `employees` (`user_id`),
  CONSTRAINT `device` FOREIGN KEY (`device`) REFERENCES `devices` (`device_id`),
  CONSTRAINT `work_cost` FOREIGN KEY (`work_price`) REFERENCES `prices` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (2,'P1','2021-12-27 22:57:16',2,1,1,1,'Galaxy S8+','BN651J65HHH25',1,'2021-12-27 22:57:16','2021-12-27 22:57:16','замена дисплея',1,'ISSUED',''),(3,'S1','2021-12-27 22:59:50',4,5,2,9,'AM458+','654684351',7,'2022-02-08 22:24:00','2022-02-18 15:35:14','work description',2,'ISSUED',NULL),(4,'P2','2021-12-27 22:59:50',6,7,5,3,'MS2045','D5165D368',7,'2022-02-13 23:11:00',NULL,'test without spare parts',5,'CLOSED',NULL),(5,'P3','2022-02-05 18:25:10',4,1,3,3,'42LF841651S1','AS46565AJHSBJHA',1,'2022-02-15 11:49:00',NULL,'test with spare part',3,'CLOSED',NULL),(6,'P4','2022-02-05 18:50:22',9,1,2,11,'GD654K','JJHGH6465BBhj',1,'2022-02-15 11:59:00',NULL,'замена экрана',2,'CLOSED','test full filling'),(7,'S2','2022-02-05 19:38:09',10,1,6,12,NULL,NULL,1,'2022-02-15 12:03:00',NULL,'working',6,'CLOSED','test min filling'),(8,'S3','2022-02-06 12:53:43',11,1,2,3,'S15KJN564','65468435135',7,'2022-02-02 16:57:00',NULL,'xfgnd ffd yjy j',2,'CLOSED',NULL),(9,'P5','2022-02-06 13:03:30',12,1,6,1,'VC-6015','1531351351',1,'2022-02-15 12:32:00','2022-02-15 12:33:00','ремонт мотора',6,'ISSUED',NULL),(10,'P6','2022-02-06 13:08:02',13,1,1,8,'IPHONE 12','65465435',7,NULL,NULL,NULL,NULL,'IN_PROGRESS',NULL),(13,'S4','2022-02-06 13:32:53',13,1,2,1,'21FF156465','131319319315',NULL,NULL,NULL,NULL,NULL,'ACCEPTED',NULL),(14,'P9','2022-02-06 13:33:28',13,1,1,14,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'ACCEPTED',NULL),(15,'S5','2022-02-06 14:22:23',13,1,4,15,'RL35KJG','456456456',NULL,NULL,NULL,NULL,NULL,'ACCEPTED',NULL),(16,'S6','2022-02-17 21:46:12',13,1,2,3,'FYT17JHG','ADADAD654654',NULL,NULL,NULL,NULL,NULL,'ACCEPTED',NULL),(17,'P10','2022-02-22 15:27:12',15,1,1,2,'WWW34','654684651KHGJH',NULL,NULL,NULL,NULL,NULL,'ACCEPTED','проверка адреса'),(18,'P10','2022-02-26 00:03:06',18,1,3,2,'YTRYTR8798','-56345345352',NULL,NULL,NULL,NULL,NULL,'ACCEPTED',NULL);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders_spare_parts`
--

DROP TABLE IF EXISTS `orders_spare_parts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders_spare_parts` (
  `order_id` bigint NOT NULL,
  `spare_part_id` bigint NOT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  KEY `order_id_idx` (`order_id`),
  KEY `spare_part_id_idx` (`spare_part_id`),
  CONSTRAINT `order_id` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`) ON DELETE CASCADE,
  CONSTRAINT `spare_part_id` FOREIGN KEY (`spare_part_id`) REFERENCES `spare_parts` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders_spare_parts`
--

LOCK TABLES `orders_spare_parts` WRITE;
/*!40000 ALTER TABLE `orders_spare_parts` DISABLE KEYS */;
INSERT INTO `orders_spare_parts` VALUES (2,1,3),(2,2,4),(2,6,5),(7,1,8),(6,2,9),(5,6,11),(5,2,12);
/*!40000 ALTER TABLE `orders_spare_parts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phone_numbers`
--

DROP TABLE IF EXISTS `phone_numbers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `phone_numbers` (
  `phone_number_id` bigint NOT NULL AUTO_INCREMENT,
  `phone_number` varchar(15) NOT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`phone_number_id`),
  KEY `user_id_idx` (`user_id`),
  CONSTRAINT `phone_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf16;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phone_numbers`
--

LOCK TABLES `phone_numbers` WRITE;
/*!40000 ALTER TABLE `phone_numbers` DISABLE KEYS */;
INSERT INTO `phone_numbers` VALUES (7,'+375171111121',2),(8,'+375446546546',2),(13,'+375291111221',2),(14,'+375296715130',1),(15,'+375269584774',5),(20,'+375654654544',10),(22,'+375275454545',12),(23,'+375296715130',13),(24,'+375151353515',14),(26,'+372954545455',4),(30,'+374532135138',6),(31,'+375846513515',6),(32,'+375123843551',6),(35,'+375484651351',15),(36,'+375654684651',15),(37,'+375296715131',1),(38,'+375296715132',1),(39,'+375655354651',18),(46,'+375000000000',17),(47,'+375111111111',17),(48,'+375654651231',19),(49,'+384684351351',19),(50,'+876451320321',19),(51,'+375898989898',20),(52,'+375111155555',22),(53,'+375899999999',21),(54,'+745123213213',23);
/*!40000 ALTER TABLE `phone_numbers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prices`
--

DROP TABLE IF EXISTS `prices`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prices` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `device_id` bigint NOT NULL,
  `repair_level` enum('MAINTENANCE','REPAIR_LEVEL_1','REPAIR_LEVEL_2','REPAIR_LEVEL_3','DIAGNOSTIC','TECHNICAL_CONCLUSION') COLLATE utf8_unicode_ci NOT NULL,
  `repair_cost` decimal(5,2) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `device_id_idx` (`device_id`),
  CONSTRAINT `device_id` FOREIGN KEY (`device_id`) REFERENCES `devices` (`device_id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prices`
--

LOCK TABLES `prices` WRITE;
/*!40000 ALTER TABLE `prices` DISABLE KEYS */;
INSERT INTO `prices` VALUES (1,1,'REPAIR_LEVEL_1',120.00),(2,2,'REPAIR_LEVEL_1',150.00),(3,3,'REPAIR_LEVEL_1',80.00),(4,4,'REPAIR_LEVEL_1',60.00),(5,5,'REPAIR_LEVEL_1',30.00),(6,6,'REPAIR_LEVEL_1',20.00),(7,7,'REPAIR_LEVEL_1',15.00),(8,8,'REPAIR_LEVEL_1',50.00),(18,1,'MAINTENANCE',80.00),(19,1,'REPAIR_LEVEL_2',150.00),(20,1,'REPAIR_LEVEL_3',180.00),(21,1,'DIAGNOSTIC',50.00),(22,1,'TECHNICAL_CONCLUSION',20.00),(23,2,'DIAGNOSTIC',60.00),(24,2,'MAINTENANCE',110.00),(25,2,'REPAIR_LEVEL_2',200.00),(26,2,'REPAIR_LEVEL_3',0.00),(27,2,'TECHNICAL_CONCLUSION',0.00),(28,3,'DIAGNOSTIC',10.00),(29,3,'MAINTENANCE',40.00),(30,3,'REPAIR_LEVEL_2',120.00),(31,3,'REPAIR_LEVEL_3',200.00),(32,3,'TECHNICAL_CONCLUSION',50.00),(33,4,'DIAGNOSTIC',10.00),(34,4,'MAINTENANCE',50.00),(35,4,'REPAIR_LEVEL_2',70.00),(36,4,'REPAIR_LEVEL_3',80.00),(37,4,'TECHNICAL_CONCLUSION',5.00);
/*!40000 ALTER TABLE `prices` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `spare_parts`
--

DROP TABLE IF EXISTS `spare_parts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `spare_parts` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `part_number` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `description` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `cost` decimal(5,2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spare_parts`
--

LOCK TABLES `spare_parts` WRITE;
/*!40000 ALTER TABLE `spare_parts` DISABLE KEYS */;
INSERT INTO `spare_parts` VALUES (1,'BN96-654682A','Основная плата','Main board FG52B, ver.2.0',100.00),(2,'BN12-513255A','Дисплей','OLED Dilplay 1600*1400 size 6.4\'',60.00),(5,'BN31-654822A','Микрофон','Motorola G4, xiaomi 2S, 4C, 4S, 4i, Note 4, 5, 6 S Plus',10.00),(6,'BN09-651351S','Шлейф','10 pin SMD socket',15.00),(7,'MS-0625874','Мотор',NULL,140.00),(8,NULL,'Микропереключатель',NULL,14.50),(9,'FS1154879','Шлейф','FFP 15 pin 1.5 l-250mm',2.30),(10,NULL,'Защелка Midea',NULL,2.00),(11,NULL,'testPart',NULL,2.00),(12,'AF52-6546511A','Мотор','220V 1800W',120.00),(13,'test','test part','test description',50.00);
/*!40000 ALTER TABLE `spare_parts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `first_name` varchar(20) CHARACTER SET utf16 COLLATE utf16_general_ci NOT NULL,
  `second_name` varchar(40) CHARACTER SET utf16 COLLATE utf16_general_ci NOT NULL,
  `patronymic` varchar(40) CHARACTER SET utf16 COLLATE utf16_general_ci DEFAULT NULL,
  `address` bigint DEFAULT NULL,
  `email` varchar(40) CHARACTER SET utf16 COLLATE utf16_general_ci DEFAULT NULL,
  `user_role` enum('GUEST','CLIENT','ADMIN','MANAGER','ENGINEER') DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `address_idx` (`address`),
  CONSTRAINT `address` FOREIGN KEY (`address`) REFERENCES `addresses` (`address_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Иван','Иванов','Иванович',1,'ivanov@mail.ru','ADMIN'),(2,'Вадим','Петров','Вадимович',2,'dyushka80@gmail.com','CLIENT'),(4,'Николай','Зайцев','Петрович',1,'web@web.by','CLIENT'),(5,'Семен','Кондратьев','Семенович',4,'semen@mail.ru','MANAGER'),(6,'Курочкин','Вадим','Андреевич',5,'clientmail@yandex.ru','CLIENT'),(7,'Надежда','Волкова','Александровна',5,'vn@mail.ru','ENGINEER'),(8,'Курочкин','Валерий','Сергеевич',6,'clientmail@yandex.ru',NULL),(9,'Петровский','Сергей','',7,'asasas@post.com',NULL),(10,'Шишкин','Иван',NULL,8,NULL,NULL),(11,'Корнеев','Василий','',9,'rrrrrr@post.ru',NULL),(12,'Петровский','Сергей',NULL,10,'asasas@post.com',NULL),(13,'Кузнецов','Николай',NULL,11,'fgfgfgf@gmail.com',NULL),(14,'Корнеев','Василий',NULL,12,'rrrrrr@post.ru',NULL),(15,'Андреев','Андрей','Андреевич',13,'newClient@mail.com',NULL),(17,'Иван','Петровский','',17,'','MANAGER'),(18,'Андреев','Андрей',NULL,14,'testmail@testmail.by',NULL),(19,'Инженер','Инженеров','Инженерович',18,'eng@mail.com','ENGINEER'),(20,'Сергей','Иванов','',19,'','MANAGER'),(21,'Владимир','Петров','',21,'','ENGINEER'),(22,'Менеджер','Менеджеров','',20,'','MANAGER'),(23,'Андрей','Николаев','',22,'','ENGINEER'),(24,'','',NULL,NULL,NULL,'MANAGER'),(25,'','',NULL,NULL,NULL,'MANAGER'),(26,'','',NULL,NULL,NULL,'ENGINEER');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-06 19:45:35
