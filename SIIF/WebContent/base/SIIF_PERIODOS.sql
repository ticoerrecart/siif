
-- Table structure for table `periodo`
--
DROP TABLE IF EXISTS `siif`.`periodo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `siif`.`periodo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `periodo` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `periodos`
--

LOCK TABLES `siif`.`periodo` WRITE;
/*!40000 ALTER TABLE `siif`.`periodo` DISABLE KEYS */;
INSERT INTO `siif`.`periodo` VALUES (1,'2010-2011'),(2,'2011-2012'),(3,'2012-2013');
/*!40000 ALTER TABLE `siif`.`periodo` ENABLE KEYS */;
UNLOCK TABLES;



INSERT INTO `siif`.`itemmenu`
(`id`, `item`, `orden`, `url`, `item_fk`) VALUES
( 55, "Período" , null, null, 12);

INSERT INTO `siif`.`itemmenu`
(`id`, `item`, `orden`, `url`, `item_fk`) VALUES
( 56, "Alta de Período" , null, "/jsp.do?page=.altaPeriodo&metodo=altaPeriodo", 55);

INSERT INTO `siif`.`itemmenu`
(`id`, `item`, `orden`, `url`, `item_fk`) VALUES
( 57, "Modificación de Período" , null, "/cargarPeriodosAModificar.do?metodo=cargarPeriodosAModificar", 55);

INSERT INTO `siif`.`rol_item`
(`rol_fk`, `item_fk`)
VALUES 
( 2 , 55);

INSERT INTO `siif`.`rol_item`
(`rol_fk`, `item_fk`)
VALUES 
( 2 , 56);

INSERT INTO `siif`.`rol_item`
(`rol_fk`, `item_fk`)
VALUES 
( 2 , 57);
