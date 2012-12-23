CREATE DATABASE  IF NOT EXISTS `x071vm20_siif` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `x071vm20_siif`;
-- MySQL dump 10.13  Distrib 5.1.40, for Win32 (ia32)
--
-- Host: localhost    Database: x071vm20_siif
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
-- Table structure for table `Entidad`
--

DROP TABLE IF EXISTS `Entidad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Entidad` (
  `tipoEntidad` varchar(31) NOT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `direccion` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) NOT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  `localidad_fk` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK456F1C9295362EF` (`localidad_fk`),
  CONSTRAINT `FK456F1C9295362EF` FOREIGN KEY (`localidad_fk`) REFERENCES `Localidad` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Entidad`
--

LOCK TABLES `Entidad` WRITE;
/*!40000 ALTER TABLE `Entidad` DISABLE KEYS */;
INSERT INTO `Entidad` VALUES ('RN',1,'Almada 99','rn@rn.gov.ar','Recursos Naturales Rio Grande','2131454',1),('OBR',2,'Ruta 3 km5600','info@bronzovich.com','Bronzovich Hnos SA','(02258)-458792',1),('PPF',3,'Canales nro 45','hsegovia@gmail.com','Hector Segovia','(02258)-457412',2),('OBR',4,'Corrales 258','info@asrgrp.com','AserGroup SA','22145987',1),('RN',5,'Perez 564','rntolhuin@gmail.com','Recursos Naturales Tolhuin','(0248) 456-7894',2),('PPF',6,'Rios 2345','randrada@gmail.com','Roberto Andrada','(0258)456-9874',3),('RN',7,'Rios 2345','rnushuaia@gmail.com','Recursos Naturales Ushuaia','(0258)456-9874',3);
/*!40000 ALTER TABLE `Entidad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SubImporte`
--

DROP TABLE IF EXISTS `SubImporte`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SubImporte` (
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
  CONSTRAINT `FK914F2F206F29D8B9` FOREIGN KEY (`guiaForestal_fk`) REFERENCES `GuiaForestal` (`id`),
  CONSTRAINT `FK914F2F20C88B2F9` FOREIGN KEY (`tipoProducto_fk`) REFERENCES `TipoProducto` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SubImporte`
--

LOCK TABLES `SubImporte` WRITE;
/*!40000 ALTER TABLE `SubImporte` DISABLE KEYS */;
INSERT INTO `SubImporte` VALUES (10,1370.35,0,'Lenga','Verde',89072.75,65,1,11),(11,550,0,'Lenga','Verde',19250,35,2,11),(12,741.92,0,'Lenga','Verde',25967.2,35,2,12),(13,430.03,0,'Lenga','Verde',45153.15,105,4,12),(14,525,0,'Lenga','Verde',18375,35,3,12),(15,600,0,'Lenga','Verde',21000,35,2,13),(16,808.61,0,'Lenga','Verde',52559.65,65,1,14),(17,636.05,0,'Lenga','Verde',25442,40,2,14),(18,250,0,'Lenga','Verde',37500,150,3,14),(19,310.75,0,'Lenga','Verde',10876.25,35,2,15),(20,220,0,'Lenga','Verde',23100,105,4,15),(21,325,0,'Lenga','Verde',11375,35,3,16);
/*!40000 ALTER TABLE `SubImporte` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BoletaDeposito`
--

DROP TABLE IF EXISTS `BoletaDeposito`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `BoletaDeposito` (
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
  CONSTRAINT `FKBE758A446F29D8B9` FOREIGN KEY (`guiaForestal_fk`) REFERENCES `GuiaForestal` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BoletaDeposito`
--

LOCK TABLES `BoletaDeposito` WRITE;
/*!40000 ALTER TABLE `BoletaDeposito` DISABLE KEYS */;
INSERT INTO `BoletaDeposito` VALUES (26,'asd','asd','','2012-10-02 11:25:50','2012-11-30 00:00:00',43329.1,111,11),(27,'qw','qwe','','2012-10-10 10:17:46','2012-12-30 00:00:00',43329.1,112,11),(28,'asd','asd','',NULL,'2013-01-30 00:00:00',43329.1,113,11),(29,'asd','asd','','2012-10-10 11:56:21','2012-11-20 00:00:00',26848.605,211,12),(30,'asd','qwe','',NULL,'2012-12-20 00:00:00',26848.605,212,12),(31,'asd','asd','',NULL,'2013-01-20 00:00:00',26848.605,213,12),(32,'asd','asd','',NULL,'2013-02-20 00:00:00',26848.605,214,12),(33,'ad','asd','',NULL,'2012-11-10 00:00:00',8400,111,13),(34,'asd','asd','',NULL,'2012-12-10 00:00:00',8400,112,13),(35,'asd','asd','',NULL,'2013-01-10 00:00:00',8400,113,13),(36,'asd','asd','','2012-10-11 11:09:34','2012-11-10 00:00:00',46200.66,101,14),(37,'asd','asd','',NULL,'2012-12-10 00:00:00',46200.66,102,14),(38,'asd','asd','',NULL,'2013-01-10 00:00:00',46200.66,103,14),(39,'asd','asd','','2012-12-11 10:22:23','2012-12-11 00:00:00',20385.75,1,15),(40,'d','as','',NULL,'2013-02-08 00:00:00',20385.75,2,15),(41,'asd','asd','',NULL,'2012-11-10 00:00:00',6825,56,16),(42,'asd','asd','',NULL,'2012-12-05 00:00:00',6825,57,16);
/*!40000 ALTER TABLE `BoletaDeposito` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Rol`
--

DROP TABLE IF EXISTS `Rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Rol` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `rol` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Rol`
--

LOCK TABLES `Rol` WRITE;
/*!40000 ALTER TABLE `Rol` DISABLE KEYS */;
INSERT INTO `Rol` VALUES (2,'Administrador'),(8,'Agente Tipo 1 '),(9,'Agente Tipo 2');
/*!40000 ALTER TABLE `Rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Periodo`
--

DROP TABLE IF EXISTS `Periodo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Periodo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `periodo` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Periodo`
--

LOCK TABLES `Periodo` WRITE;
/*!40000 ALTER TABLE `Periodo` DISABLE KEYS */;
INSERT INTO `Periodo` VALUES (1,'2010-2011'),(2,'2011-2012'),(3,'2012-2013');
/*!40000 ALTER TABLE `Periodo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Rodal`
--

DROP TABLE IF EXISTS `Rodal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Rodal` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `marcacion_fk` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4B78012DCD6FBEF` (`marcacion_fk`),
  CONSTRAINT `FK4B78012DCD6FBEF` FOREIGN KEY (`marcacion_fk`) REFERENCES `Marcacion` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Rodal`
--

LOCK TABLES `Rodal` WRITE;
/*!40000 ALTER TABLE `Rodal` DISABLE KEYS */;
INSERT INTO `Rodal` VALUES (1,'6',1),(2,'I',2),(3,'II',2),(4,'988',3),(5,'7899',1),(6,'Rodal1',4),(7,'r54',5),(8,'I89',6);
/*!40000 ALTER TABLE `Rodal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Fiscalizacion`
--

DROP TABLE IF EXISTS `Fiscalizacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Fiscalizacion` (
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
  CONSTRAINT `FK43E3AE776F29D8B9` FOREIGN KEY (`guiaForestal_fk`) REFERENCES `GuiaForestal` (`id`),
  CONSTRAINT `FK43E3AE77702A2FCF` FOREIGN KEY (`rodal_fk`) REFERENCES `Rodal` (`id`),
  CONSTRAINT `FK43E3AE77799681AF` FOREIGN KEY (`entidad_fk`) REFERENCES `Entidad` (`id`),
  CONSTRAINT `FK43E3AE77B77D334D` FOREIGN KEY (`oficina_fk`) REFERENCES `Entidad` (`id`),
  CONSTRAINT `FK43E3AE77C88B2F9` FOREIGN KEY (`tipoProducto_fk`) REFERENCES `TipoProducto` (`id`),
  CONSTRAINT `FK43E3AE77D3833F8F` FOREIGN KEY (`usuario_fk`) REFERENCES `Usuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Fiscalizacion`
--

LOCK TABLES `Fiscalizacion` WRITE;
/*!40000 ALTER TABLE `Fiscalizacion` DISABLE KEYS */;
INSERT INTO `Fiscalizacion` VALUES (22,387.72,50,'2012-09-15 00:00:00','2012-2013',4,2,4,NULL,7,3,1),(23,715.62,45,'2012-08-10 00:00:00','2011-2012',3,1,4,11,6,3,1),(24,477.72,50,'2012-09-10 00:00:00','2012-2013',2,5,4,NULL,7,3,1),(25,623.5,100,'2012-09-02 00:00:00','2012-2013',2,2,4,NULL,6,3,1),(26,654.73,75,'2012-08-22 00:00:00','2012-2013',2,1,4,11,6,3,1),(31,350,0,'2012-09-10 00:00:00','2012-2013',0,3,3,NULL,3,5,5),(33,1755.84,150,'2012-08-08 00:00:00','2012-2013',2,1,2,NULL,1,5,5),(34,741.92,80,'2012-09-20 00:00:00','2012-2013',1,2,2,12,1,5,5),(35,430.03,60,'2012-06-05 00:00:00','2012-2013',3,4,2,12,1,5,5),(36,407.15,45,'2012-08-07 00:00:00','2012-2013',1,1,3,NULL,2,3,5),(40,401.46,65,'2012-09-12 00:00:00','2012-2013',2,1,3,14,2,5,5),(41,636.05,55,'2012-07-11 00:00:00','2012-2013',2,2,3,14,2,5,5),(42,542.87,60,'2012-09-26 00:00:00','2012-2013',1,4,3,NULL,3,5,5),(43,143.14,30,'2012-08-20 00:00:00','2012-2013',2,4,3,NULL,4,5,1),(44,325,0,'2012-07-16 00:00:00','2012-2013',0,3,2,16,5,5,1),(45,481.9,45,'2012-08-30 00:00:00','2012-2013',2,4,2,NULL,5,5,7),(46,310.75,60,'2012-09-23 00:00:00','2012-2013',1,2,4,15,6,5,5),(47,654.09,65,'2012-08-13 00:00:00','2012-2013',1,4,2,NULL,1,5,1);
/*!40000 ALTER TABLE `Fiscalizacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PMF`
--

DROP TABLE IF EXISTS `PMF`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PMF` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `expediente` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `entidad_fk` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK135E9799681AF` (`entidad_fk`),
  CONSTRAINT `FK135E9799681AF` FOREIGN KEY (`entidad_fk`) REFERENCES `Entidad` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PMF`
--

LOCK TABLES `PMF` WRITE;
/*!40000 ALTER TABLE `PMF` DISABLE KEYS */;
INSERT INTO `PMF` VALUES (1,'555','PMF1',2),(2,'555/555','PMF2',3),(5,'987','PMF1',4);
/*!40000 ALTER TABLE `PMF` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ItemMenu`
--

DROP TABLE IF EXISTS `ItemMenu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ItemMenu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `item` varchar(255) DEFAULT NULL,
  `orden` int(11) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `item_fk` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4A06A7322591D4F8` (`item_fk`),
  CONSTRAINT `FK4A06A7322591D4F8` FOREIGN KEY (`item_fk`) REFERENCES `ItemMenu` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=95 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ItemMenu`
--

LOCK TABLES `ItemMenu` WRITE;
/*!40000 ALTER TABLE `ItemMenu` DISABLE KEYS */;
INSERT INTO `ItemMenu` VALUES (1,'Fiscalización',1,'',NULL),(2,'Alta de Fiscalización de Productos Forestales',NULL,'/Fiscalizacion.do?metodo=cargarAltaFiscalizacion',1),(3,'Modificación de Fiscalización de Productos Forestales',NULL,'/Fiscalizacion.do?metodo=recuperarTiposDeEntidadParaFiscalizacionesAModificar',1),(4,'Anulación de Fiscalización de Productos Forestales',NULL,'/Fiscalizacion.do?metodo=recuperarTiposDeEntidadParaFiscalizacionesAAnular',1),(6,'Salir',7,'',NULL),(7,'Salir de la Aplicacion',NULL,'/login.do?metodo=logout',6),(8,'Guía Forestal Básica',2,'',NULL),(9,'Alta de Guía Forestal Básica',NULL,'/guiaForestal.do?metodo=recuperarTiposDeEntidadParaAltaGFB',8),(10,'Modificación de Guía Forestal Básica',NULL,'/guiaForestal.do?metodo=recuperarTiposDeEntidadParaModificacionGFB',8),(12,'Datos del Sistema',5,'',NULL),(13,'Tipo de Producto Forestal',NULL,' ',12),(14,'Alta Tipo de Producto Forestal',NULL,'/jsp.do?page=.altaTipoProductoForestal',13),(15,'Modificacion Tipo de Producto Forestal',NULL,'/recuperarTipoProductoForestal.do?metodo=cargarModificacionTipoProductoForestal',13),(16,'Usuarios',6,'',NULL),(17,'Alta de Usuario',NULL,'/Usuario.do?metodo=cargarAltaUsuario',16),(18,'Modificación de Usuario',NULL,'/cargarUsuariosAModificar.do?metodo=cargarUsuariosAModificar',16),(19,'Baja de Usuario',NULL,'',16),(20,'Entidad',NULL,'',12),(21,'Alta de Entidad',NULL,'/Entidad.do?metodo=cargarAltaEntidad',20),(22,'Modificación de Entidad',NULL,'/cargarEntidadesAModificar.do?metodo=cargarEntidadesAModificar',20),(23,'Aforo',NULL,'',12),(24,'Alta de Aforo',NULL,'/recuperarAforo.do?metodo=cargarAltaAforo',23),(25,'Modificación de Aforo',NULL,'/recuperarAforo.do?metodo=cargarModificacionAforos',23),(26,'Plan de Manejo Forestal',NULL,'',12),(27,'Alta Plan de Manejo Forestal',NULL,'/ubicacion.do?metodo=recuperarUbicacionesParaAlta',26),(28,'Modificación Plan de Manejo Forestal',NULL,'/ubicacion.do?metodo=recuperarProductoresParaModificacionPMF',26),(29,'Boletas de Deposito',NULL,'',8),(30,'Registrar Pago Boleta de Deposito',NULL,'/guiaForestal.do?metodo=recuperarProductoresParaBoletasDeposito&forward=cargarGuiaForestalPagoBoletaDeposito&forwardBuscarNroGuia=cargarGuiaForestalPagoBoletaDepositoPorNroGuia',29),(31,'Reemplazar Boleta de Deposito',NULL,'/guiaForestal.do?metodo=recuperarProductoresParaBoletasDeposito&forward=cargarGuiaForestalReemBoletaDeposito&forwardBuscarNroGuia=cargarGuiaForestalReemBoletaDepositoPorNroGuia',29),(32,'Vales de Transporte',NULL,'',8),(33,'Devolución Vale de Transporte',NULL,'/guiaForestal.do?metodo=recuperarProductoresParaValeTransporte&forward=cargarGuiaForestalDevolucionValeTransporte&forwardBuscarNroGuia=cargarGuiaForestalDevolucionValeTransportePorNroGuia',32),(34,'Consultas',3,'',NULL),(35,'Fiscalizacion',NULL,'',34),(36,'Fiscalizacion de Productos Forestales con Guia',NULL,'/consultasFiscalizacion.do?metodo=cargarTiposDeEntidadConsultaFiscalizacion&forward=recuperarFiscalizacionesConGuiaForestal',35),(37,'Guia Forestal',NULL,'',34),(38,'Guías Forestales Vigentes',NULL,'/consultasPorProductor.do?metodo=cargarConsultaPorProductores&forward=recuperarGuiasForestalesVigentes',37),(39,'Guías Forestales No Vigentes',NULL,'/consultasPorProductor.do?metodo=cargarConsultaPorProductores&forward=recuperarGuiasForestalesNoVigentes',37),(40,'Localidad',NULL,NULL,12),(41,'Alta de Localidad',NULL,'/jsp.do?page=.altaLocalidad&metodo=altaLocalidad',40),(42,'Modificación de Localidad',NULL,'/cargarLocalidadesAModificar.do?metodo=cargarLocalidadesAModificar',40),(43,'Rol',NULL,NULL,12),(44,'Alta de Rol',NULL,'/recuperarRol.do?metodo=cargarAltaRol',43),(45,'Modificación de Rol',NULL,'/recuperarRol.do?metodo=cargarModificacionRol',43),(46,'Reportes',4,'',NULL),(47,'Fiscalizaciones',NULL,'',46),(48,'Volumen Fiscalizado Por Producto Forestal entre Fechas',NULL,'/jsp.do?page=.reporteVolumenFiscalizadoProdForestalEntreFechas&paramForward=generarReporteVolumenFiscalizadoPorProductoForestalEntreFecha',47),(49,'Deudas de Aforo',NULL,'/consultasPorProductor.do?metodo=cargarConsultaPorProductores&forward=recuperarGuiasForestalesConDeudasAforo',37),(50,'Deudas Vales de Transporte',NULL,'/consultasPorProductor.do?metodo=cargarConsultaPorProductores&forward=recuperarGuiasForestalesConDeudasValeTransporte',37),(51,'Fiscalizacion de Productos Forestales sin Guia',NULL,'/consultasFiscalizacion.do?metodo=cargarTiposDeEntidadConsultaFiscalizacion&forward=recuperarFiscalizacionesSinGuiaForestal',35),(52,'Volumen Fiscalizado Por Productor entre Fechas',NULL,'/reporte.do?metodo=cargarReporteVolumenFiscalizadoEntreFechas&paramForward=generarReporteVolumenFiscalizadoPorProductorEntreFechas',47),(53,'Reemplazar Vale de Transporte',NULL,'/guiaForestal.do?metodo=recuperarProductoresParaValeTransporte&forward=cargarGuiaForestalReemplazarValeTransporte&forwardBuscarNroGuia=cargarGuiaForestalReemplazarValeTransportePorNroGuia',32),(54,'prueba Menu',NULL,' ',52),(55,'Asociar Fiscalización a Guía',NULL,'/jsp.do?page=.recuperarGuiaAsociarFiscalizacion',8),(56,'Período',NULL,NULL,12),(57,'Alta de Período',NULL,'/jsp.do?page=.altaPeriodo&metodo=altaPeriodo',56),(58,'Modificación de Período',NULL,'/cargarPeriodosAModificar.do?metodo=cargarPeriodosAModificar',56),(59,'Por Productor',NULL,NULL,46),(60,'Volumen Fiscalizado',NULL,NULL,59),(61,'Por Productos',NULL,'/reportesPorProductor.do?metodo=cargarReportePorProductorGeneral&paramForward=generarReporteVolumenFiscalizadoPorProductos',60),(62,'Total',NULL,'/reportesPorProductor.do?metodo=cargarReporteVolumenFiscalizadoTotal',60),(63,'Volumen de GFB',NULL,NULL,59),(64,'Montos Pagos',NULL,'/reportesPorProductor.do?metodo=cargarReportePorProductorGeneral&paramForward=generarReporteVolumenGFBMontosPagos',63),(65,'Montos Adeudados',NULL,'/reportesPorProductor.do?metodo=cargarReportePorProductorGeneral&paramForward=generarReporteVolumenGFBMontosAdeudados',63),(66,'Listado de Boletas de Deposito',NULL,NULL,59),(67,'Pagas',NULL,'/reportesPorProductor.do?metodo=cargarReportePorProductorGeneral&paramForward=generarReporteListaBoletasPagas',66),(68,'Impagas',NULL,'/reportesPorProductor.do?metodo=cargarReportePorProductorGeneral&paramForward=generarReporteListaBoletasImpagas',66),(69,'Listado de Vales de Transporte',NULL,NULL,59),(70,'Devueltos',NULL,'/reportesPorProductor.do?metodo=cargarReportePorProductorGeneral&paramForward=generarReporteListaValesDevueltos',69),(71,'En Uso',NULL,'/reportesPorProductor.do?metodo=cargarReportePorProductorGeneral&paramForward=generarReporteListaValesEnUso',69),(72,'Volumen por Ubicacion',NULL,'/reportesPorProductor.do?metodo=cargarReportePorProductorVolumenPorUbicacion&paramForward=generarReporteVolumenPorUbicacion',59),(73,'Volumen por Plan de Manejo',NULL,NULL,72),(74,'Volumen por Tranzon',NULL,NULL,72),(75,'Volumen por Marcacion',NULL,NULL,72),(76,'Por Producto',NULL,NULL,46),(77,'Volumen Por Año Forestal',NULL,'/jsp.do?page=.reportePorProductoVolumenPorAnioForestal',76),(78,'Por Productor',NULL,NULL,76),(79,'Entre Fechas',NULL,'/reportesPorProducto.do?metodo=cargarReporteVolumenPorProductorEntreFechas',78),(80,'Por Ubicación',NULL,'/reportesPorProducto.do?metodo=cargarReporteVolumenPorProductoPorProductorPorUbicacion',78),(81,'En Tranzon',NULL,NULL,78),(82,'En Marcacion',NULL,NULL,78),(83,'Recaudacion',NULL,NULL,46),(84,'Por Productor',NULL,NULL,83),(85,'Entre Fechas',NULL,'/reportesRecaudacion.do?metodo=cargarReporteRecaudacionPorProductorEntreFechas',84),(86,'Por Año Forestal',NULL,'/reportesRecaudacion.do?metodo=cargarReporteRecaudacionPorProductorPorAnioForestal',84),(87,'Por Ubicacion',NULL,'/reportesRecaudacion.do?metodo=cargarReporteRecaudacionPorProductorPorUbicacion',84),(88,'Total de Productoresl',NULL,NULL,83),(89,'Entre Fechas',NULL,'/jsp.do?page=.reporteRecaudacionTotalProductoresEntreFechas',88),(90,'Año Forestal',NULL,NULL,83),(91,'Por Productor',NULL,'/reportesRecaudacion.do?metodo=cargarReporteRecaudacionPorAnioForestalPorProductor',90),(92,'Total de Productores',NULL,'/jsp.do?page=.reporteRecaudacionPorAnioForestalTotalProductores',90),(93,'Total',NULL,'/reportesPorProductor.do?metodo=cargarReportePorProductorGeneral&paramForward=generarReporteListaBoletasTotales',66),(94,'Total',NULL,'/reportesPorProductor.do?metodo=cargarReportePorProductorGeneral&paramForward=generarReporteListaValesTotales',69);
/*!40000 ALTER TABLE `ItemMenu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Aforo`
--

DROP TABLE IF EXISTS `Aforo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Aforo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `estado` varchar(255) DEFAULT NULL,
  `tipoProductor` varchar(255) DEFAULT NULL,
  `valorAforo` double NOT NULL,
  `tipoProducto_fk` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3C40487C88B2F9` (`tipoProducto_fk`),
  CONSTRAINT `FK3C40487C88B2F9` FOREIGN KEY (`tipoProducto_fk`) REFERENCES `TipoProducto` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Aforo`
--

LOCK TABLES `Aforo` WRITE;
/*!40000 ALTER TABLE `Aforo` DISABLE KEYS */;
INSERT INTO `Aforo` VALUES (3,'Verde','PPF',150,3),(4,'Verde','OBR',105,4),(6,'Verde','PPF',65,1),(10,'Verde','OBR',35,2),(11,'Verde','PPF',40,2),(12,'Verde','PPF',20,4),(13,'Verde','OBR',65,1),(14,'Verde','OBR',35,3);
/*!40000 ALTER TABLE `Aforo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TipoProducto`
--

DROP TABLE IF EXISTS `TipoProducto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TipoProducto` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TipoProducto`
--

LOCK TABLES `TipoProducto` WRITE;
/*!40000 ALTER TABLE `TipoProducto` DISABLE KEYS */;
INSERT INTO `TipoProducto` VALUES (1,'Rollizos'),(2,'Fustes'),(3,'Leña'),(4,'Postes'),(5,'Trineos');
/*!40000 ALTER TABLE `TipoProducto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Muestra`
--

DROP TABLE IF EXISTS `Muestra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Muestra` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `diametro1` double NOT NULL,
  `diametro2` double NOT NULL,
  `largo` double NOT NULL,
  `fiscalizacion_fk` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKB6AC216D19787EAF` (`fiscalizacion_fk`),
  CONSTRAINT `FKB6AC216D19787EAF` FOREIGN KEY (`fiscalizacion_fk`) REFERENCES `Fiscalizacion` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=117 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Muestra`
--

LOCK TABLES `Muestra` WRITE;
/*!40000 ALTER TABLE `Muestra` DISABLE KEYS */;
INSERT INTO `Muestra` VALUES (71,1.5,1.3,8,22),(72,1.1,0.9,7,22),(73,1,1,7.5,22),(74,1.2,1.1,7,22),(75,1.8,1.8,9,23),(76,1.2,1.2,7,23),(77,1.6,1.6,8.4,23),(78,1.2,1.2,8,24),(79,1.5,1.2,7,24),(80,0.9,1,8.2,25),(81,1.1,1.1,7,25),(82,0.9,0.9,8,26),(83,1.5,1.5,7,26),(93,1.2,1.2,8.2,33),(94,1.5,1.5,8,33),(95,1.2,1.2,8.2,34),(96,1.25,1.25,7.3,35),(97,1.1,1.1,7,35),(98,1,1,7.5,35),(101,1.2,1.2,8,36),(106,1.2,1.2,8.2,40),(107,0.7,0.7,8,40),(108,1.1,1.2,9.1,41),(109,1.5,1.45,8,41),(110,1.2,1.2,8,42),(111,0.9,0.9,7,43),(112,0.9,0.9,8,43),(113,1.2,1.2,8,45),(114,1.5,1.5,7,45),(115,0.9,1,7.3,46),(116,1.25,1.25,8.2,47);
/*!40000 ALTER TABLE `Muestra` ENABLE KEYS */;
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
  CONSTRAINT `FKEFD143832591D4F8` FOREIGN KEY (`item_fk`) REFERENCES `ItemMenu` (`id`),
  CONSTRAINT `FKEFD143837B33DD6F` FOREIGN KEY (`rol_fk`) REFERENCES `Rol` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rol_item`
--

LOCK TABLES `rol_item` WRITE;
/*!40000 ALTER TABLE `rol_item` DISABLE KEYS */;
INSERT INTO `rol_item` VALUES (9,1),(9,2),(9,6),(9,7),(9,8),(9,9),(9,16),(9,18),(9,29),(9,30),(9,32),(9,33),(9,34),(9,35),(9,36),(9,37),(9,38),(9,39),(9,46),(9,47),(9,48),(9,49),(9,50),(9,51),(9,52),(9,54),(9,55),(8,1),(8,2),(8,4),(8,6),(8,7),(8,8),(8,9),(8,10),(8,16),(8,18),(8,29),(8,30),(8,31),(8,32),(8,33),(8,34),(8,35),(8,36),(8,37),(8,38),(8,39),(8,46),(8,47),(8,48),(8,49),(8,50),(8,51),(8,52),(8,53),(8,54),(8,55),(2,1),(2,2),(2,3),(2,4),(2,6),(2,7),(2,8),(2,9),(2,10),(2,12),(2,13),(2,14),(2,15),(2,16),(2,17),(2,18),(2,19),(2,20),(2,21),(2,22),(2,23),(2,24),(2,25),(2,26),(2,27),(2,28),(2,29),(2,30),(2,31),(2,32),(2,33),(2,34),(2,35),(2,36),(2,37),(2,38),(2,39),(2,40),(2,41),(2,42),(2,46),(2,47),(2,48),(2,49),(2,50),(2,51),(2,52),(2,53),(2,54),(2,55),(2,56),(2,57),(2,58),(2,59),(2,60),(2,61),(2,62),(2,63),(2,64),(2,65),(2,66),(2,67),(2,68),(2,69),(2,70),(2,71),(2,72),(2,76),(2,77),(2,78),(2,79),(2,80),(2,83),(2,84),(2,85),(2,86),(2,87),(2,88),(2,89),(2,90),(2,91),(2,92),(2,93),(2,94);
/*!40000 ALTER TABLE `rol_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Usuario`
--

DROP TABLE IF EXISTS `Usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Usuario` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombreUsuario` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `rol_fk` bigint(20) DEFAULT NULL,
  `entidad_fk` bigint(20) DEFAULT NULL,
  `habilitado` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5B4D8B0E799681AF` (`entidad_fk`),
  KEY `FK5B4D8B0E7B33DD6F` (`rol_fk`),
  CONSTRAINT `FK5B4D8B0E799681AF` FOREIGN KEY (`entidad_fk`) REFERENCES `Entidad` (`id`),
  CONSTRAINT `FK5B4D8B0E7B33DD6F` FOREIGN KEY (`rol_fk`) REFERENCES `Rol` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Usuario`
--

LOCK TABLES `Usuario` WRITE;
/*!40000 ALTER TABLE `Usuario` DISABLE KEYS */;
INSERT INTO `Usuario` VALUES (1,'a','a',9,1,''),(2,'b','b',9,1,''),(3,'c','c',2,1,''),(4,'d','d',8,1,''),(5,'e','e',8,5,''),(6,'f','f',9,7,'');
/*!40000 ALTER TABLE `Usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Marcacion`
--

DROP TABLE IF EXISTS `Marcacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Marcacion` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `disposicion` varchar(255) DEFAULT NULL,
  `tranzon_fk` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK31E30CE1B35ADB8F` (`tranzon_fk`),
  CONSTRAINT `FK31E30CE1B35ADB8F` FOREIGN KEY (`tranzon_fk`) REFERENCES `Tranzon` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Marcacion`
--

LOCK TABLES `Marcacion` WRITE;
/*!40000 ALTER TABLE `Marcacion` DISABLE KEYS */;
INSERT INTO `Marcacion` VALUES (1,'98790',1),(2,'98748LOIU',2),(3,'457898',2),(4,'6544',4),(5,'99999',4),(6,'2222',5);
/*!40000 ALTER TABLE `Marcacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ValeTransporte`
--

DROP TABLE IF EXISTS `ValeTransporte`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ValeTransporte` (
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
  CONSTRAINT `FK4DC147006F29D8B9` FOREIGN KEY (`guiaForestal_fk`) REFERENCES `GuiaForestal` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ValeTransporte`
--

LOCK TABLES `ValeTransporte` WRITE;
/*!40000 ALTER TABLE `ValeTransporte` DISABLE KEYS */;
INSERT INTO `ValeTransporte` VALUES (16,570.98,'asd','qwe','Lenga','2013-01-30 00:00:00','qwe',50,100,'Rodal1','Rollizos','weq',11,'2012-10-03 00:00:00'),(17,799.37,'','','Lenga','2013-01-30 00:00:00','',70,101,'Rodal1','Rollizos','',11,'2012-10-25 00:00:00'),(18,0,'','','','2013-01-30 00:00:00','',0,103,'Rodal1','','',11,NULL),(19,0,NULL,NULL,NULL,'2013-02-28 00:00:00',NULL,0,354,'6',NULL,NULL,12,NULL),(20,0,NULL,NULL,NULL,'2013-02-28 00:00:00',NULL,0,355,'6',NULL,NULL,12,NULL),(21,0,NULL,NULL,NULL,'2013-02-28 00:00:00',NULL,0,356,'6',NULL,NULL,12,NULL),(22,0,NULL,NULL,NULL,'2013-02-28 00:00:00',NULL,0,357,'6',NULL,NULL,12,NULL),(23,0,NULL,NULL,NULL,'2013-02-28 00:00:00',NULL,0,358,'6',NULL,NULL,12,NULL),(24,0,NULL,NULL,NULL,'2013-02-28 00:00:00',NULL,0,359,'6',NULL,NULL,12,NULL),(25,0,NULL,NULL,NULL,'2013-02-28 00:00:00',NULL,0,360,'6',NULL,NULL,12,NULL),(26,0,NULL,NULL,NULL,'2013-02-01 00:00:00',NULL,0,101,'r54',NULL,NULL,13,NULL),(27,0,NULL,NULL,NULL,'2013-02-01 00:00:00',NULL,0,102,'r54',NULL,NULL,13,NULL),(28,0,NULL,NULL,NULL,'2013-02-01 00:00:00',NULL,0,103,'r54',NULL,NULL,13,NULL),(29,0,NULL,NULL,NULL,'2013-01-10 00:00:00',NULL,0,1000,'I',NULL,NULL,14,NULL),(30,0,NULL,NULL,NULL,'2013-01-10 00:00:00',NULL,0,1001,'I',NULL,NULL,14,NULL),(31,0,NULL,NULL,NULL,'2013-01-10 00:00:00',NULL,0,1002,'I',NULL,NULL,14,NULL),(32,0,NULL,NULL,NULL,'2013-01-10 00:00:00',NULL,0,1003,'I',NULL,NULL,14,NULL),(33,0,NULL,NULL,NULL,'2013-02-01 00:00:00',NULL,0,1,'Rodal1',NULL,NULL,15,NULL),(34,0,NULL,NULL,NULL,'2013-02-01 00:00:00',NULL,0,2,'Rodal1',NULL,NULL,15,NULL),(35,0,NULL,NULL,NULL,'2013-02-01 00:00:00',NULL,0,5,'Rodal1',NULL,NULL,15,NULL),(36,0,NULL,NULL,NULL,'2012-12-10 00:00:00',NULL,0,200,'7899',NULL,NULL,16,NULL),(37,0,NULL,NULL,NULL,'2012-12-10 00:00:00',NULL,0,202,'7899',NULL,NULL,16,NULL);
/*!40000 ALTER TABLE `ValeTransporte` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `GuiaForestal`
--

DROP TABLE IF EXISTS `GuiaForestal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `GuiaForestal` (
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
  CONSTRAINT `FKAE8BBDCE702A2FCF` FOREIGN KEY (`rodal_fk`) REFERENCES `Rodal` (`id`),
  CONSTRAINT `FKAE8BBDCE799681AF` FOREIGN KEY (`entidad_fk`) REFERENCES `Entidad` (`id`),
  CONSTRAINT `FKAE8BBDCED3833F8F` FOREIGN KEY (`usuario_fk`) REFERENCES `Usuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `GuiaForestal`
--

LOCK TABLES `GuiaForestal` WRITE;
/*!40000 ALTER TABLE `GuiaForestal` DISABLE KEYS */;
INSERT INTO `GuiaForestal` VALUES (11,0,'2012-10-02 00:00:00','2013-02-02 00:00:00',129987.3,21664.55,'Rio Grande',123,'',3,'2012-2013',4,6),(12,0,'2012-10-02 00:00:00','2013-03-20 00:00:00',107394.42,17899.07,'Rio Grande',5858,'',3,'2012-2013',2,1),(13,0,'2012-10-10 00:00:00','2013-02-15 00:00:00',25200,4200,'Rio Grande',124,'',3,'2012-2013',4,7),(14,0,'2012-10-11 00:00:00','2013-01-15 00:00:00',138601.98,23100.33,'Tolhuin',6060,'',3,'2012-2013',3,2),(15,0,'2012-10-04 00:00:00','2013-02-15 00:00:00',40771.5,6795.25,'Rio Grande',125,'',3,'2012-2013',4,6),(16,0,'2012-10-11 00:00:00','2012-12-10 00:00:00',13650,2275,'Ushuaia',5959,'',3,'2012-2013',2,5);
/*!40000 ALTER TABLE `GuiaForestal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Tranzon`
--

DROP TABLE IF EXISTS `Tranzon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Tranzon` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `disposicion` int(11) DEFAULT NULL,
  `numero` int(11) DEFAULT NULL,
  `pmf_fk` bigint(20) DEFAULT NULL,
  `numeroDisposicion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK239EA8AE77AB372F` (`pmf_fk`),
  CONSTRAINT `FK239EA8AE77AB372F` FOREIGN KEY (`pmf_fk`) REFERENCES `PMF` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Tranzon`
--

LOCK TABLES `Tranzon` WRITE;
/*!40000 ALTER TABLE `Tranzon` DISABLE KEYS */;
INSERT INTO `Tranzon` VALUES (1,555,555,1,NULL),(2,1225,798,2,NULL),(4,1234,957,5,NULL),(5,65479,1235,2,NULL);
/*!40000 ALTER TABLE `Tranzon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Localidad`
--

DROP TABLE IF EXISTS `Localidad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Localidad` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Localidad`
--

LOCK TABLES `Localidad` WRITE;
/*!40000 ALTER TABLE `Localidad` DISABLE KEYS */;
INSERT INTO `Localidad` VALUES (1,'Rio Grande'),(2,'Tolhuin'),(3,'Ushuaia');
/*!40000 ALTER TABLE `Localidad` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2012-12-14 10:38:12
