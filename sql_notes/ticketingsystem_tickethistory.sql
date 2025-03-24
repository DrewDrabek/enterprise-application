CREATE DATABASE  IF NOT EXISTS `TicketingSystem` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `TicketingSystem`;
-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: localhost    Database: TicketingSystem
-- ------------------------------------------------------
-- Server version	8.0.41

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
-- Table structure for table `TicketHistory`
--

DROP TABLE IF EXISTS `TicketHistory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `TicketHistory` (
  `HistoryID` int NOT NULL AUTO_INCREMENT,
  `TicketID` int NOT NULL,
  `ChangedBy` int NOT NULL,
  `OldStatus` enum('Open','In Progress','Resolved','Closed') DEFAULT NULL,
  `NewStatus` enum('Open','In Progress','Resolved','Closed') DEFAULT NULL,
  `OldAssignedTo` int DEFAULT NULL,
  `NewAssignedTo` int DEFAULT NULL,
  `ChangedAt` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`HistoryID`),
  KEY `TicketID` (`TicketID`),
  KEY `ChangedBy` (`ChangedBy`),
  KEY `OldAssignedTo` (`OldAssignedTo`),
  KEY `NewAssignedTo` (`NewAssignedTo`),
  CONSTRAINT `TicketHistory_ibfk_1` FOREIGN KEY (`TicketID`) REFERENCES `tickets` (`TicketID`),
  CONSTRAINT `TicketHistory_ibfk_2` FOREIGN KEY (`ChangedBy`) REFERENCES `users` (`UserID`),
  CONSTRAINT `TicketHistory_ibfk_3` FOREIGN KEY (`OldAssignedTo`) REFERENCES `users` (`UserID`),
  CONSTRAINT `TicketHistory_ibfk_4` FOREIGN KEY (`NewAssignedTo`) REFERENCES `users` (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TicketHistory`
--

LOCK TABLES `TicketHistory` WRITE;
/*!40000 ALTER TABLE `TicketHistory` DISABLE KEYS */;
/*!40000 ALTER TABLE `TicketHistory` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-03-18 17:58:09
