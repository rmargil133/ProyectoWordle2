-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: mydb
-- ------------------------------------------------------
-- Server version	8.0.31

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
-- Table structure for table `equipo`
--

DROP TABLE IF EXISTS `equipo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `equipo` (
  `idequipo` int NOT NULL AUTO_INCREMENT,
  `puntos` varchar(45) NOT NULL,
  `logo` varchar(45) DEFAULT NULL,
  `nombre` varchar(45) NOT NULL,
  PRIMARY KEY (`idequipo`),
  UNIQUE KEY `idEquipo_UNIQUE` (`idequipo`),
  UNIQUE KEY `Nombre_UNIQUE` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipo`
--

LOCK TABLES `equipo` WRITE;
/*!40000 ALTER TABLE `equipo` DISABLE KEYS */;
INSERT INTO `equipo` VALUES (1,'560','si','rangers'),(2,'652','si','realmadrid');
/*!40000 ALTER TABLE `equipo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `juega_partida_a`
--

DROP TABLE IF EXISTS `juega_partida_a`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `juega_partida_a` (
  `idpartida` int NOT NULL AUTO_INCREMENT,
  `jugador_idjugador` int NOT NULL,
  `juego_idjuego` int NOT NULL,
  `palabra_jugada` varchar(45) NOT NULL,
  `numero_intentos` int NOT NULL,
  `puntos` int NOT NULL,
  `datetime` datetime NOT NULL,
  PRIMARY KEY (`idpartida`,`jugador_idjugador`,`juego_idjuego`,`datetime`),
  KEY `fk_Jugador_has_Juego_Juego1_idx` (`juego_idjuego`),
  KEY `fk_Jugador_has_Juego_Jugador_idx` (`jugador_idjugador`),
  CONSTRAINT `fk_Jugador_has_Juego_Juego1` FOREIGN KEY (`juego_idjuego`) REFERENCES `juego` (`idjuego`),
  CONSTRAINT `fk_Jugador_has_Juego_Jugador` FOREIGN KEY (`jugador_idjugador`) REFERENCES `jugador` (`idjugador`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `juega_partida_a`
--

LOCK TABLES `juega_partida_a` WRITE;
/*!40000 ALTER TABLE `juega_partida_a` DISABLE KEYS */;
INSERT INTO `juega_partida_a` VALUES (1,1,1,'cocodrilo',5,10,'2023-05-25 10:30:00'),(4,5,1,'supermercado',15,20,'2023-06-14 14:44:49'),(5,5,1,'paco',15,100,'2023-06-14 14:45:49'),(6,4,1,'champions',15,200,'2023-06-14 14:51:47'),(7,4,1,'futbol',15,200,'2023-06-14 14:55:37'),(9,2,1,'zanahoria',10,25,'2023-06-16 20:57:21'),(10,2,1,'melocoton',10,25,'2023-06-16 20:59:06');
/*!40000 ALTER TABLE `juega_partida_a` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `actualizar_puntos_equipo` AFTER INSERT ON `juega_partida_a` FOR EACH ROW BEGIN
    DECLARE equipo_id INT;
    DECLARE puntos_antiguos INT;
    DECLARE puntos_nuevos INT;

    -- Obtener el ID del equipo al que pertenece el jugador de la partida
    SET equipo_id = (SELECT equipo_idequipo FROM Jugador WHERE idjugador = NEW.jugador_idjugador);

    -- Obtener los puntos antiguos del equipo
    SET puntos_antiguos = (SELECT puntos FROM Equipo WHERE idequipo = equipo_id);

    -- Calcular los nuevos puntos sumando los puntos de la nueva partida
    SET puntos_nuevos = puntos_antiguos + NEW.puntos;

    -- Actualizar los puntos del equipo
    UPDATE Equipo SET puntos = puntos_nuevos WHERE idequipo = equipo_id;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `actualizar_puntos_jugador` AFTER INSERT ON `juega_partida_a` FOR EACH ROW BEGIN
    UPDATE jugador
    SET puntos = puntos + NEW.puntos
    WHERE idjugador = NEW.jugador_idjugador;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `juego`
--

DROP TABLE IF EXISTS `juego`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `juego` (
  `idjuego` int NOT NULL AUTO_INCREMENT,
  `dificultad_juego` varchar(45) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `instrucciones` varchar(45) NOT NULL,
  `intentos_max` int NOT NULL,
  PRIMARY KEY (`idjuego`),
  UNIQUE KEY `idJuego_UNIQUE` (`idjuego`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `juego`
--

LOCK TABLES `juego` WRITE;
/*!40000 ALTER TABLE `juego` DISABLE KEYS */;
INSERT INTO `juego` VALUES (1,'media','wordle','SI',2);
/*!40000 ALTER TABLE `juego` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jugador`
--

DROP TABLE IF EXISTS `jugador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jugador` (
  `idjugador` int NOT NULL AUTO_INCREMENT,
  `equipo_idequipo` int NOT NULL,
  `admin` varchar(45) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `avatar` varchar(45) DEFAULT NULL,
  `puntos` varchar(45) NOT NULL,
  PRIMARY KEY (`idjugador`),
  UNIQUE KEY `idJugador_UNIQUE` (`idjugador`),
  KEY `fk_Jugador_Equipo1_idx` (`equipo_idequipo`),
  CONSTRAINT `fk_Jugador_Equipo1` FOREIGN KEY (`equipo_idequipo`) REFERENCES `equipo` (`idequipo`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3 COMMENT='	';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jugador`
--

LOCK TABLES `jugador` WRITE;
/*!40000 ALTER TABLE `jugador` DISABLE KEYS */;
INSERT INTO `jugador` VALUES (1,1,'0','pepe','si','260'),(2,1,'0','ruben','si','300'),(3,2,'0','camavinga','si','52'),(4,2,'0','vinijr','si','250'),(5,2,'0','valverde','si','50'),(8,1,'0','martin','si','260'),(10,1,'0','miguel','si','110');
/*!40000 ALTER TABLE `jugador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'mydb'
--

--
-- Dumping routines for database 'mydb'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-06-16 21:18:07
