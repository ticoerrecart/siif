CREATE DATABASE  IF NOT EXISTS `siif` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `siif`;
-- MySQL dump 10.13  Distrib 5.1.40, for Win32 (ia32)
--
-- Host: localhost    Database: siif
-- ------------------------------------------------------
-- Server version	5.1.50-community

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
-- Table structure for table `entidad`
--

DROP TABLE IF EXISTS `entidad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `entidad` (
  `tipoEntidad` varchar(31) NOT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `direccion` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) NOT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  `localidad_fk` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK456F1C9295362EF` (`localidad_fk`),
  CONSTRAINT `FK456F1C9295362EF` FOREIGN KEY (`localidad_fk`) REFERENCES `localidad` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entidad`
--

LOCK TABLES `entidad` WRITE;
/*!40000 ALTER TABLE `entidad` DISABLE KEYS */;
INSERT INTO `entidad` VALUES ('RN',1,'Almada 99','rn@rn.gov.ar','Recursos Naturales Rio Grande','2131454',1),('OBR',2,'Ruta 3 km5600','info@bronzovich.com','Bronzovich Hnos SA','(02258)-458792',1),('PPF',3,'Canales nro 45','hsegovia@gmail.com','Hector Segovia','(02258)-457412',2),('OBR',4,'Corrales 258','info@asrgrp.com','AserGroup SA','22145987',1),('RN',5,'Perez 564','rntolhuin@gmail.com','Recursos Naturales Tolhuin','(0248) 456-7894',2),('PPF',6,'Rios 2345','randrada@gmail.com','Roberto Andrada','(0258)456-9874',3),('RN',7,'Rios 2345','rnushuaia@gmail.com','Recursos Naturales Ushuaia','(0258)456-9874',3);
/*!40000 ALTER TABLE `entidad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subimporte`
--

DROP TABLE IF EXISTS `subimporte`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `subimporte` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cantidadMts` double NOT NULL,
  `cantidadUnidades` int(11) NOT NULL,
  `especie` varchar(255) DEFAULT NULL,
  `estado` varchar(255) DEFAULT NULL,
  `importe` double NOT NULL,
  `valorAforos` double NOT NULL,
  `tipoProducto_fk` bigint(20) DEFAULT NULL,
  `guiaForestal_fk` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK914F2F20C88B2F9` (`tipoProducto_fk`),
  KEY `FK914F2F206F29D8B9` (`guiaForestal_fk`),
  CONSTRAINT `FK914F2F206F29D8B9` FOREIGN KEY (`guiaForestal_fk`) REFERENCES `guiaforestal` (`id`),
  CONSTRAINT `FK914F2F20C88B2F9` FOREIGN KEY (`tipoProducto_fk`) REFERENCES `tipoproducto` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subimporte`
--

LOCK TABLES `subimporte` WRITE;
/*!40000 ALTER TABLE `subimporte` DISABLE KEYS */;
INSERT INTO `subimporte` VALUES (1,100,55,'Lenga','Verde',6500,65,1,1),(2,1000,110,'Lenga','Seco',100000,100,4,2),(3,2000,110,'Lenga','Seco',200000,100,4,3),(4,1000,110,'Lenga','Seco',15000,15,3,5),(5,1500,100,'Lenga','Seco',75000,50,2,7),(6,1000,100,'Lenga','Verde',65000,65,1,8),(7,400,10,'Lenga','Seco',28000,70,2,9);
/*!40000 ALTER TABLE `subimporte` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `boletadeposito`
--

DROP TABLE IF EXISTS `boletadeposito`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `boletadeposito` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `area` varchar(255) DEFAULT NULL,
  `concepto` varchar(255) DEFAULT NULL,
  `efectivoCheque` varchar(255) DEFAULT NULL,
  `fechaPago` datetime DEFAULT NULL,
  `fechaVencimiento` datetime NOT NULL,
  `monto` double NOT NULL,
  `numero` int(11) NOT NULL,
  `guiaForestal_fk` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKBE758A446F29D8B9` (`guiaForestal_fk`),
  CONSTRAINT `FKBE758A446F29D8B9` FOREIGN KEY (`guiaForestal_fk`) REFERENCES `guiaforestal` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `boletadeposito`
--

LOCK TABLES `boletadeposito` WRITE;
/*!40000 ALTER TABLE `boletadeposito` DISABLE KEYS */;
INSERT INTO `boletadeposito` VALUES (1,'Direccion General de Bosques','Aforo tipo 3','','2011-02-25 00:00:00','2011-03-25 00:00:00',30000,1,2),(2,'Direccion General de Bosques','Aforo tipo 3','','2011-06-17 10:20:48','2011-06-11 00:00:00',60000,2,2),(3,'Direccion General de Bosques','Aforo tipo 3','','2011-06-17 12:12:37','2011-07-11 00:00:00',30000,3,2),(4,'Direccion General de Bosques','Aforo tipo 3','','2011-06-17 10:17:49','2011-07-15 00:00:00',60000,1,3),(5,'Direccion General de Bosques','Aforo tipo 3','','2011-06-17 10:19:48','2011-08-15 00:00:00',60000,2,3),(6,'Direccion General de Bosques','Aforo tipo 3','','2011-07-20 12:07:40','2011-09-15 00:00:00',60000,3,3),(8,'Direccion General de Bosques','Aforo tipo 3','',NULL,'2011-11-16 00:00:00',60000,6,3),(9,'Direccion General de Bosques','Aforo tipo 3','',NULL,'2011-09-07 00:00:00',3900,1,1),(10,'Direccion General de Bosques','Aforo tipo 3','',NULL,'2011-10-18 00:00:00',3900,2,1),(15,'Direccion General de Bosques','Aforo tipo 3','','2012-08-31 15:37:05','2012-02-05 00:00:00',6000,1,5),(16,'Direccion General de Bosques','Aforo tipo 1','',NULL,'2012-03-05 00:00:00',6000,2,5),(17,'Direccion General de Bosques','Aforo tipo 3','',NULL,'2012-04-05 00:00:00',6000,3,5),(18,'asd','asd','',NULL,'2012-12-19 00:00:00',90000,222,7),(19,'asd','asd','',NULL,'2012-11-08 00:00:00',39000,234,8),(20,'sa','qwe','',NULL,'2013-03-10 00:00:00',39000,235,8),(21,'asd','asd','','2012-09-07 11:49:10','2012-11-01 00:00:00',11200,111,9),(22,'qw','qwe','',NULL,'2013-01-01 00:00:00',11200,222,9),(23,'kjh','kjh','',NULL,'2013-03-15 00:00:00',11200,333,9);
/*!40000 ALTER TABLE `boletadeposito` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rol`
--

DROP TABLE IF EXISTS `rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rol` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `rol` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rol`
--

LOCK TABLES `rol` WRITE;
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
INSERT INTO `rol` VALUES (1,'Usuario Interno'),(2,'Administrador'),(3,'UsuarioPrueba1'),(4,'UsuarioPrueba2'),(5,'UsuarioPrueba3'),(6,'UsuarioPrueba4'),(7,'UsuarioPrueba5');
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rodal`
--

DROP TABLE IF EXISTS `rodal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rodal` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `marcacion_fk` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4B78012DCD6FBEF` (`marcacion_fk`),
  CONSTRAINT `FK4B78012DCD6FBEF` FOREIGN KEY (`marcacion_fk`) REFERENCES `marcacion` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rodal`
--

LOCK TABLES `rodal` WRITE;
/*!40000 ALTER TABLE `rodal` DISABLE KEYS */;
INSERT INTO `rodal` VALUES (1,'6',1),(2,'I',2),(3,'II',2),(4,'987',3),(5,'789',1),(6,'rodal1',4);
/*!40000 ALTER TABLE `rodal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fiscalizacion`
--

DROP TABLE IF EXISTS `fiscalizacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fiscalizacion` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cantidadMts` double NOT NULL,
  `cantidadUnidades` int(11) NOT NULL,
  `fecha` datetime DEFAULT NULL,
  `periodoForestal` varchar(255) DEFAULT NULL,
  `tamanioMuestra` int(11) NOT NULL,
  `tipoProducto_fk` bigint(20) DEFAULT NULL,
  `entidad_fk` bigint(20) DEFAULT NULL,
  `guiaForestal_fk` bigint(20) DEFAULT NULL,
  `rodal_fk` bigint(20) DEFAULT NULL,
  `usuario_fk` bigint(20) DEFAULT NULL,
  `oficina_fk` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK43E3AE77C88B2F9` (`tipoProducto_fk`),
  KEY `FK43E3AE77799681AF` (`entidad_fk`),
  KEY `FK43E3AE776F29D8B9` (`guiaForestal_fk`),
  KEY `FK43E3AE77702A2FCF` (`rodal_fk`),
  KEY `FK43E3AE77D3833F8F` (`usuario_fk`),
  KEY `FK43E3AE77B77D334D` (`oficina_fk`),
  CONSTRAINT `FK43E3AE776F29D8B9` FOREIGN KEY (`guiaForestal_fk`) REFERENCES `guiaforestal` (`id`),
  CONSTRAINT `FK43E3AE77702A2FCF` FOREIGN KEY (`rodal_fk`) REFERENCES `rodal` (`id`),
  CONSTRAINT `FK43E3AE77799681AF` FOREIGN KEY (`entidad_fk`) REFERENCES `entidad` (`id`),
  CONSTRAINT `FK43E3AE77B77D334D` FOREIGN KEY (`oficina_fk`) REFERENCES `entidad` (`id`),
  CONSTRAINT `FK43E3AE77C88B2F9` FOREIGN KEY (`tipoProducto_fk`) REFERENCES `tipoproducto` (`id`),
  CONSTRAINT `FK43E3AE77D3833F8F` FOREIGN KEY (`usuario_fk`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fiscalizacion`
--

LOCK TABLES `fiscalizacion` WRITE;
/*!40000 ALTER TABLE `fiscalizacion` DISABLE KEYS */;
INSERT INTO `fiscalizacion` VALUES (1,100,50,'2010-12-08 00:00:00','2010-2011',3,1,3,1,2,3,1),(3,2000,110,'2011-02-17 00:00:00','2010-2011',5,4,2,3,1,3,1),(4,1000,110,'2011-02-21 00:00:00','2010-2011',2,4,2,2,1,3,1),(5,1000,105,'2011-02-20 00:00:00','2010-2011',1,1,3,NULL,2,3,1),(6,1000,110,'2011-02-08 00:00:00','2010-2011',2,3,3,5,2,3,1),(7,200,200,'2012-07-25 00:00:00','2012-2013',1,2,3,9,3,5,1),(10,200,200,'2012-07-26 00:00:00','2012-2013',1,2,2,NULL,1,5,5),(11,200,200,'2012-07-27 00:00:00','2012-2013',1,2,3,NULL,3,5,5),(12,100,100,'2012-07-30 00:00:00','2012-2013',1,4,3,NULL,3,5,5),(13,100,100,'2012-07-30 00:00:00','2012-2013',1,2,3,9,3,5,5),(14,100,100,'2012-08-03 00:00:00','2012-2013',1,2,4,7,6,3,1),(15,100,100,'2012-08-10 00:00:00','2012-2013',2,4,4,NULL,6,1,1),(16,100,100,'2012-08-14 00:00:00','2012-2013',2,4,2,NULL,1,3,5),(17,32.01,10,'2012-08-30 00:00:00','2012-2013',2,2,4,NULL,6,2,1);
/*!40000 ALTER TABLE `fiscalizacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `localidad`
--

DROP TABLE IF EXISTS `localidad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `localidad` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `localidad`
--

LOCK TABLES `localidad` WRITE;
/*!40000 ALTER TABLE `localidad` DISABLE KEYS */;
INSERT INTO `localidad` VALUES (1,'Rio Grande'),(2,'Tolhuin'),(3,'Ushuaia');
/*!40000 ALTER TABLE `localidad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pmf`
--

DROP TABLE IF EXISTS `pmf`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pmf` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `expediente` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `entidad_fk` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK135E9799681AF` (`entidad_fk`),
  CONSTRAINT `FK135E9799681AF` FOREIGN KEY (`entidad_fk`) REFERENCES `entidad` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pmf`
--

LOCK TABLES `pmf` WRITE;
/*!40000 ALTER TABLE `pmf` DISABLE KEYS */;
INSERT INTO `pmf` VALUES (1,'5555','PMF1',2),(2,'555/555','PMF2',3),(5,'987','PMF1',4);
/*!40000 ALTER TABLE `pmf` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `itemmenu`
--

DROP TABLE IF EXISTS `itemmenu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `itemmenu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `item` varchar(255) DEFAULT NULL,
  `orden` int(11) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `item_fk` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4A06A7322591D4F8` (`item_fk`),
  CONSTRAINT `FK4A06A7322591D4F8` FOREIGN KEY (`item_fk`) REFERENCES `itemmenu` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `itemmenu`
--

LOCK TABLES `itemmenu` WRITE;
/*!40000 ALTER TABLE `itemmenu` DISABLE KEYS */;
INSERT INTO `itemmenu` VALUES (1,'Fiscalización',1,'',NULL),(2,'Alta de Fiscalización de Productos Forestales',NULL,'/fiscalizacion.do?metodo=cargarAltaFiscalizacion',1),(3,'Modificación de Fiscalización de Productos Forestales',NULL,'/fiscalizacion.do?metodo=recuperarTiposDeEntidadParaFiscalizacionesAModificar',1),(4, 'Anulaci�n de Fiscalizaci�n de Productos Forestales', NULL, '/fiscalizacion.do?metodo=recuperarTiposDeEntidadParaFiscalizacionesAAnular', 1),(6,'Salir',7,'',NULL),(7,'Salir de la Aplicacion',NULL,'/login.do?metodo=logout',6),(8,'Guía Forestal Básica',2,'',NULL),(9,'Alta de Guía Forestal Básica',NULL,'/guiaForestal.do?metodo=recuperarTiposDeEntidadParaAltaGFB',8),(10,'Modificación de Guía Forestal Básica',NULL,'/guiaForestal.do?metodo=recuperarLocalidadesParaModificacionGFB',8),(12,'Datos del Sistema',5,'',NULL),(13,'Tipo de Producto Forestal',NULL,' ',12),(14,'Alta Tipo de Producto Forestal',NULL,'/jsp.do?page=.altaTipoProductoForestal',13),(15,'Modificacion Tipo de Producto Forestal',NULL,'/recuperarTipoProductoForestal.do?metodo=cargarModificacionTipoProductoForestal',13),(16,'Usuarios',6,'',NULL),(17,'Alta de Usuario',NULL,'/usuario.do?metodo=cargarAltaUsuario',16),(18,'Modificación de Usuario',NULL,'/cargarUsuariosAModificar.do?metodo=cargarUsuariosAModificar',16),(19,'Baja de Usuario',NULL,'',16),(20,'Entidad',NULL,'',12),(21,'Alta de Entidad',NULL,'/entidad.do?metodo=cargarAltaEntidad',20),(22,'Modificación de Entidad',NULL,'/cargarEntidadesAModificar.do?metodo=cargarEntidadesAModificar',20),(23,'Aforo',NULL,'',12),(24,'Alta de Aforo',NULL,'/recuperarAforo.do?metodo=cargarAltaAforo',23),(25,'Modificación de Aforo',NULL,'/recuperarAforo.do?metodo=cargarModificacionAforos',23),(26,'Plan de Manejo Forestal',NULL,'',12),(27,'Alta Plan de Manejo Forestal',NULL,'/ubicacion.do?metodo=recuperarUbicacionesParaAlta',26),(28,'Modificación Plan de Manejo Forestal',NULL,'/ubicacion.do?metodo=recuperarUbicacionesParaModificacion',26),(29,'Boletas de Deposito',NULL,'',8),(30,'Registrar Pago Boleta de Deposito',NULL,'/guiaForestal.do?metodo=recuperarProductoresParaBoletasDeposito&forward=cargarGuiaForestalPagoBoletaDeposito&forwardBuscarNroGuia=cargarGuiaForestalPagoBoletaDepositoPorNroGuia',29),(31,'Reemplazar Boleta de Deposito',NULL,'/guiaForestal.do?metodo=recuperarProductoresParaBoletasDeposito&forward=cargarGuiaForestalReemBoletaDeposito&forwardBuscarNroGuia=cargarGuiaForestalReemBoletaDepositoPorNroGuia',29),(32,'Vales de Transporte',NULL,'',8),(33,'Devolución Vale de Transporte',NULL,'/guiaForestal.do?metodo=recuperarProductoresParaValeTransporte&forward=cargarGuiaForestalDevolucionValeTransporte&forwardBuscarNroGuia=cargarGuiaForestalDevolucionValeTransportePorNroGuia',32),(34,'Consultas',3,'',NULL),(35,'Fiscalizacion',NULL,'',34),(36,'Fiscalizacion de Productos Forestales con Guia',NULL,'/consultasFiscalizacion.do?metodo=cargarTiposDeEntidadConsultaFiscalizacion&forward=recuperarFiscalizacionesConGuiaForestal',35),(37,'Guia Forestal',NULL,'',34),(38,'Guías Forestales Vigentes',NULL,'/consultasPorProductor.do?metodo=cargarConsultaPorProductores&forward=recuperarGuiasForestalesVigentes',37),(39,'Guías Forestales No Vigentes',NULL,'/consultasPorProductor.do?metodo=cargarConsultaPorProductores&forward=recuperarGuiasForestalesNoVigentes',37),(40,'Localidad',NULL,NULL,12),(41,'Alta de Localidad',NULL,'/jsp.do?page=.altaLocalidad&metodo=altaLocalidad',40),(42,'Modificación de Localidad',NULL,'/cargarLocalidadesAModificar.do?metodo=cargarLocalidadesAModificar',40),(43,'Rol',NULL,NULL,12),(44,'Alta de Rol',NULL,'/recuperarRol.do?metodo=cargarAltaRol',43),(45,'Modificación de Rol',NULL,'/recuperarRol.do?metodo=cargarModificacionRol',43),(46,'Reportes',4,'',NULL),(47,'Fiscalizaciones',NULL,'',46),(48,'Volumen Fiscalizado Por Producto Forestal entre Fechas',NULL,'/jsp.do?page=.reporteVolumenFiscalizadoProdForestalEntreFechas&paramForward=generarReporteVolumenFiscalizadoPorProductoForestalEntreFecha',47),(49,'Deudas de Aforo',NULL,'/consultasPorProductor.do?metodo=cargarConsultaPorProductores&forward=recuperarGuiasForestalesConDeudasAforo',37),(50,'Deudas Vales de Transporte',NULL,'/consultasPorProductor.do?metodo=cargarConsultaPorProductores&forward=recuperarGuiasForestalesConDeudasValeTransporte',37),(51,'Fiscalizacion de Productos Forestales sin Guia',NULL,'/consultasFiscalizacion.do?metodo=cargarTiposDeEntidadConsultaFiscalizacion&forward=recuperarFiscalizacionesSinGuiaForestal',35),(52,'Volumen Fiscalizado Por Productor entre Fechas',NULL,'/reporte.do?metodo=cargarReporteVolumenFiscalizadoEntreFechas&paramForward=generarReporteVolumenFiscalizadoPorProductorEntreFechas',47),(53,'Reemplazar Vale de Transporte',NULL,'/guiaForestal.do?metodo=recuperarProductoresParaValeTransporte&forward=cargarGuiaForestalReemplazarValeTransporte&forwardBuscarNroGuia=cargarGuiaForestalReemplazarValeTransportePorNroGuia',32),(54,'prueba Menu',NULL,' ',52);
/*!40000 ALTER TABLE `itemmenu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `aforo`
--

DROP TABLE IF EXISTS `aforo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `aforo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `estado` varchar(255) DEFAULT NULL,
  `tipoProductor` varchar(255) DEFAULT NULL,
  `valorAforo` double NOT NULL,
  `tipoProducto_fk` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3C40487C88B2F9` (`tipoProducto_fk`),
  CONSTRAINT `FK3C40487C88B2F9` FOREIGN KEY (`tipoProducto_fk`) REFERENCES `tipoproducto` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aforo`
--

LOCK TABLES `aforo` WRITE;
/*!40000 ALTER TABLE `aforo` DISABLE KEYS */;
INSERT INTO `aforo` VALUES (1,'Seco','PPF',75,3),(2,'Seco','OBR',100,4),(3,'Verde','PPF',150,3),(4,'Verde','OBR',105,4),(5,'Seco','PPF',55,1),(6,'Verde','PPF',65,1),(7,'Seco','OBR',200,1),(8,'Seco','OBR',50,2),(9,'Seco','PPF',70,2);
/*!40000 ALTER TABLE `aforo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipoproducto`
--

DROP TABLE IF EXISTS `tipoproducto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipoproducto` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipoproducto`
--

LOCK TABLES `tipoproducto` WRITE;
/*!40000 ALTER TABLE `tipoproducto` DISABLE KEYS */;
INSERT INTO `tipoproducto` VALUES (1,'Rollizos'),(2,'Fustes'),(3,'Leña'),(4,'Postes'),(5,'Trineos');
/*!40000 ALTER TABLE `tipoproducto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `muestra`
--

DROP TABLE IF EXISTS `muestra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `muestra` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `diametro1` double NOT NULL,
  `diametro2` double NOT NULL,
  `largo` double NOT NULL,
  `fiscalizacion_fk` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKB6AC216D19787EAF` (`fiscalizacion_fk`),
  CONSTRAINT `FKB6AC216D19787EAF` FOREIGN KEY (`fiscalizacion_fk`) REFERENCES `fiscalizacion` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `muestra`
--

LOCK TABLES `muestra` WRITE;
/*!40000 ALTER TABLE `muestra` DISABLE KEYS */;
INSERT INTO `muestra` VALUES (1,1,1,6,1),(2,1,2,10,1),(3,1,1,9,1),(9,1,1,15,3),(10,1,1,10,3),(11,0.9,1,11,3),(17,1,1,1,4),(19,1,1,1,5),(22,1,1,1,6),(23,2,2,2,6),(25,4,4,4,3),(26,1,1,0.9,4),(27,1,1.2,9,3),(28,1,1,1,7),(29,1,1,1,10),(30,1,1.5,1,11),(31,1,1,1,12),(32,1,1,1,13),(33,1,1,1,14),(34,1,1,1,15),(35,1.1,0.9,1.2,15),(36,1,1,1,16),(37,1.1,0.9,1.2,16),(38,0.85,0.84,7,17),(39,0.7,0.75,6,17);
/*!40000 ALTER TABLE `muestra` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rol_item`
--

DROP TABLE IF EXISTS `rol_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rol_item` (
  `rol_fk` bigint(20) NOT NULL,
  `item_fk` bigint(20) NOT NULL,
  KEY `FKEFD143832591D4F8` (`item_fk`),
  KEY `FKEFD143837B33DD6F` (`rol_fk`),
  CONSTRAINT `FKEFD143832591D4F8` FOREIGN KEY (`item_fk`) REFERENCES `itemmenu` (`id`),
  CONSTRAINT `FKEFD143837B33DD6F` FOREIGN KEY (`rol_fk`) REFERENCES `rol` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rol_item`
--

LOCK TABLES `rol_item` WRITE;
/*!40000 ALTER TABLE `rol_item` DISABLE KEYS */;
INSERT INTO `rol_item` VALUES (4,2),(4,1),(4,9),(4,8),(4,18),(4,16),(4,7),(4,6),(6,6),(6,7),(6,16),(6,18),(6,34),(6,35),(6,36),(1,1),(1,2),(1,3),(1,6),(1,7),(1,8),(1,9),(1,10),(1,16),(1,18),(1,29),(1,30),(1,31),(1,32),(1,33),(1,34),(1,35),(1,36),(1,37),(1,38),(1,39),(3,6),(3,7),(3,34),(3,35),(3,36),(3,37),(3,38),(3,39),(7,1),(7,2),(7,6),(7,7),(7,34),(7,37),(7,46),(7,47),(7,48),(7,50),(5,1),(5,2),(5,3),(5,6),(5,7),(2,1),(2,2),(2,3),(2,6),(2,7),(2,8),(2,9),(2,10),(2,12),(2,13),(2,14),(2,15),(2,16),(2,17),(2,18),(2,19),(2,20),(2,21),(2,22),(2,23),(2,24),(2,25),(2,26),(2,27),(2,28),(2,29),(2,30),(2,31),(2,32),(2,33),(2,34),(2,35),(2,36),(2,37),(2,38),(2,39),(2,40),(2,41),(2,42),(2,43),(2,44),(2,45),(2,46),(2,47),(2,48),(2,49),(2,50),(2,51),(2,52),(2,53),(2,54);
/*!40000 ALTER TABLE `rol_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombreUsuario` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `rol_fk` bigint(20) DEFAULT NULL,
  `entidad_fk` bigint(20) DEFAULT NULL,
  `habilitado` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5B4D8B0E799681AF` (`entidad_fk`),
  KEY `FK5B4D8B0E7B33DD6F` (`rol_fk`),
  CONSTRAINT `FK5B4D8B0E799681AF` FOREIGN KEY (`entidad_fk`) REFERENCES `entidad` (`id`),
  CONSTRAINT `FK5B4D8B0E7B33DD6F` FOREIGN KEY (`rol_fk`) REFERENCES `rol` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'a','a',1,1,''),(2,'b','b',5,1,''),(3,'c','c',2,1,''),(4,'d','d',3,1,''),(5,'e','e',1,5,''),(6,'f','f',7,7,'');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `marcacion`
--

DROP TABLE IF EXISTS `marcacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `marcacion` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `disposicion` int(11) DEFAULT NULL,
  `tranzon_fk` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK31E30CE1B35ADB8F` (`tranzon_fk`),
  CONSTRAINT `FK31E30CE1B35ADB8F` FOREIGN KEY (`tranzon_fk`) REFERENCES `tranzon` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `marcacion`
--

LOCK TABLES `marcacion` WRITE;
/*!40000 ALTER TABLE `marcacion` DISABLE KEYS */;
INSERT INTO `marcacion` VALUES (1,9879,1),(2,98748,2),(3,457898,2),(4,654,4);
/*!40000 ALTER TABLE `marcacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `valetransporte`
--

DROP TABLE IF EXISTS `valetransporte`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `valetransporte` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cantidadMts` double NOT NULL,
  `destino` varchar(255) DEFAULT NULL,
  `dominio` varchar(255) DEFAULT NULL,
  `especie` varchar(255) DEFAULT NULL,
  `fechaVencimiento` datetime NOT NULL,
  `marca` varchar(255) DEFAULT NULL,
  `nroPiezas` int(11) NOT NULL,
  `numero` int(11) NOT NULL,
  `origen` varchar(255) DEFAULT NULL,
  `producto` varchar(255) DEFAULT NULL,
  `vehiculo` varchar(255) DEFAULT NULL,
  `guiaForestal_fk` bigint(20) DEFAULT NULL,
  `fechaDevolucion` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4DC147006F29D8B9` (`guiaForestal_fk`),
  CONSTRAINT `FK4DC147006F29D8B9` FOREIGN KEY (`guiaForestal_fk`) REFERENCES `guiaforestal` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `valetransporte`
--

LOCK TABLES `valetransporte` WRITE;
/*!40000 ALTER TABLE `valetransporte` DISABLE KEYS */;
INSERT INTO `valetransporte` VALUES (1,0,'Lago Escondido','DFR456','Lenga','2011-03-29 00:00:00','Volvo',55,1,'Malvinera Sur','Postes','Camion',2,'2011-08-15 15:16:17'),(2,0,'Lago Escondido','DFR456','Lenga','2011-06-10 00:00:00','Volvo',55,2,'Malvinera Sur','Postes','Camion',2,NULL),(3,1000,'Lago Escondido','DFR456','Lenga','2011-07-20 00:00:00','Volvo',50,1,'Malvinera Sur','Postes','Camion',3,'2012-08-31 15:38:03'),(4,500,'Lago Escondido','DFR456','Lenga','2011-08-20 00:00:00','Volvo',25,2,'Malvinera Sur','Postes','Camion',3,NULL),(5,500,'Lago Escondido','DFR456','Lenga','2011-09-20 00:00:00','Volvo',25,3,'Malvinera Sur','Postes','Camion',3,'2011-09-20 00:00:00'),(6,1000,'Lago Escondido','ERT897','Lenga','2012-04-17 00:00:00','Mercedez Benz',110,1,'Malvinera Sur','Leña','Camion',5,NULL),(7,654,'asd','asd654','qwe','2012-11-14 00:00:00','asd',654,789,'asd','qwe','asd',7,NULL),(8,654,'qwe','asd654','qwe','2012-12-12 00:00:00','qwe',654,654,'asd','qwe','qwqwe',8,NULL),(9,654,'asd','asd654','qwe','2013-02-06 00:00:00','sefew',654,555,'asd','qwe','asd',9,NULL);
/*!40000 ALTER TABLE `valetransporte` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `guiaforestal`
--

DROP TABLE IF EXISTS `guiaforestal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `guiaforestal` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `distanciaAforoMovil` int(11) NOT NULL,
  `fecha` datetime DEFAULT NULL,
  `fechaVencimiento` datetime DEFAULT NULL,
  `importeTotal` double NOT NULL,
  `inspFiscalizacion` double NOT NULL,
  `localidad` varchar(255) DEFAULT NULL,
  `nroGuia` int(11) NOT NULL,
  `observaciones` varchar(255) DEFAULT NULL,
  `usuario_fk` bigint(20) DEFAULT NULL,
  `periodoForestal` varchar(255) DEFAULT NULL,
  `entidad_fk` bigint(20) DEFAULT NULL,
  `rodal_fk` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKAE8BBDCED3833F8F` (`usuario_fk`),
  KEY `FKAE8BBDCE799681AF` (`entidad_fk`),
  KEY `FKAE8BBDCE702A2FCF` (`rodal_fk`),
  CONSTRAINT `FKAE8BBDCE702A2FCF` FOREIGN KEY (`rodal_fk`) REFERENCES `rodal` (`id`),
  CONSTRAINT `FKAE8BBDCE799681AF` FOREIGN KEY (`entidad_fk`) REFERENCES `entidad` (`id`),
  CONSTRAINT `FKAE8BBDCED3833F8F` FOREIGN KEY (`usuario_fk`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `guiaforestal`
--

LOCK TABLES `guiaforestal` WRITE;
/*!40000 ALTER TABLE `guiaforestal` DISABLE KEYS */;
INSERT INTO `guiaforestal` VALUES (1,0,'2010-12-08 00:00:00','2011-01-02 00:00:00',7800,1300,'Rio Grande',21010,'',3,'2010-2011',3,2),(2,5,'2011-03-11 00:00:00','2011-10-06 00:00:00',120000,20000,'Rio Grande',51298,'sdfsdflaskdjslakdjsalkdjsaldkjasldkjaslkdjsaldkjsalkdjsalkdjaslkdjaslkdjalskdjslakdjaslkdjaslkdjas\r\nsdfsdfsd\r\nsdfsdfsd\r\nsdfsdf\r\nsdfsdf',3,'2011-2012',2,1),(3,0,'2011-06-15 00:00:00','2011-10-21 00:00:00',240000,40000,'Rio Grande',45897,'',3,'2012-2013',2,1),(5,0,'2012-01-13 00:00:00','2012-04-17 00:00:00',18000,3000,'Rio Grande',8978255,'qwe\r\nqwe\r\nqwe\r\nqwe\r\nqwe\r\nqwe',3,'2012-2013',3,2),(7,10,'2012-08-31 00:00:00','2012-12-31 00:00:00',90000,15000,'Rio Grande',555555,'asd\r\nasd\r\nasd',3,'2012-2013',4,6),(8,10,'2012-09-07 00:00:00','2013-04-01 00:00:00',78000,13000,'Rio Grande',888888,'asd\r\nasd\r\nasd',3,'2012-2013',3,6),(9,20,'2012-09-08 00:00:00','2013-03-25 00:00:00',33600,5600,'Rio Grande',999999,'',3,'2012-2013',3,3);
/*!40000 ALTER TABLE `guiaforestal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tranzon`
--

DROP TABLE IF EXISTS `tranzon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tranzon` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `disposicion` int(11) DEFAULT NULL,
  `numero` int(11) DEFAULT NULL,
  `pmf_fk` bigint(20) DEFAULT NULL,
  `numeroDisposicion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK239EA8AE77AB372F` (`pmf_fk`),
  CONSTRAINT `FK239EA8AE77AB372F` FOREIGN KEY (`pmf_fk`) REFERENCES `pmf` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tranzon`
--

LOCK TABLES `tranzon` WRITE;
/*!40000 ALTER TABLE `tranzon` DISABLE KEYS */;
INSERT INTO `tranzon` VALUES (1,555,555,1,NULL),(2,1225,798,2,NULL),(4,1234,957,5,NULL),(5,65479,1235,2,NULL);
/*!40000 ALTER TABLE `tranzon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ubicacion`
--

DROP TABLE IF EXISTS `ubicacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ubicacion` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dispocicion` varchar(255) DEFAULT NULL,
  `expediente` varchar(255) DEFAULT NULL,
  `pmf` varchar(255) DEFAULT NULL,
  `rodal` varchar(255) DEFAULT NULL,
  `tranzon` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ubicacion`
--

LOCK TABLES `ubicacion` WRITE;
/*!40000 ALTER TABLE `ubicacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `ubicacion` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2012-09-11 16:37:58
