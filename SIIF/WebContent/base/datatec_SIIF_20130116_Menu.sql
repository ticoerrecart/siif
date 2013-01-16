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
) ENGINE=InnoDB AUTO_INCREMENT=96 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `itemmenu`
--

LOCK TABLES `itemmenu` WRITE;
/*!40000 ALTER TABLE `itemmenu` DISABLE KEYS */;
INSERT INTO `itemmenu` VALUES (1,'Fiscalización',1,'',NULL),(2,'Alta de Fiscalización de Productos Forestales',NULL,'/fiscalizacion.do?metodo=cargarAltaFiscalizacion',1),(3,'Modificación de Fiscalización de Productos Forestales',NULL,'/fiscalizacion.do?metodo=recuperarTiposDeEntidadParaFiscalizacionesAModificar',1),(4,'Anulación de Fiscalización de Productos Forestales',NULL,'/fiscalizacion.do?metodo=recuperarTiposDeEntidadParaFiscalizacionesAAnular',1),(6,'Salir',7,'',NULL),(7,'Salir de la Aplicacion',NULL,'/login.do?metodo=logout',6),(8,'Guía Forestal Básica',2,'',NULL),(9,'Alta de Guía Forestal Básica',NULL,'/guiaForestal.do?metodo=recuperarTiposDeEntidadParaAltaGFB',8),(10,'Modificación de Guía Forestal Básica',NULL,'/guiaForestal.do?metodo=recuperarTiposDeEntidadParaModificacionGFB',8),(12,'Datos del Sistema',5,'',NULL),(13,'Tipo de Producto Forestal',NULL,' ',12),(14,'Alta Tipo de Producto Forestal',NULL,'/jsp.do?page=.altaTipoProductoForestal',13),(15,'Modificacion Tipo de Producto Forestal',NULL,'/recuperarTipoProductoForestal.do?metodo=cargarModificacionTipoProductoForestal',13),(16,'Usuarios',6,'',NULL),(17,'Alta de Usuario',NULL,'/usuario.do?metodo=cargarAltaUsuario',16),(18,'Modificación de Usuario',NULL,'/cargarUsuariosAModificar.do?metodo=cargarUsuariosAModificar',16),(19,'Baja de Usuario',NULL,'',16),(20,'Entidad',NULL,'',12),(21,'Alta de Entidad',NULL,'/entidad.do?metodo=cargarAltaEntidad',20),(22,'Modificación de Entidad',NULL,'/cargarEntidadesAModificar.do?metodo=cargarEntidadesAModificar',20),(23,'Aforo',NULL,'',12),(24,'Alta de Aforo',NULL,'/recuperarAforo.do?metodo=cargarAltaAforo',23),(25,'Modificación de Aforo',NULL,'/recuperarAforo.do?metodo=cargarModificacionAforos',23),(26,'Plan de Manejo Forestal',NULL,'',12),(27,'Alta Plan de Manejo Forestal',NULL,'/ubicacion.do?metodo=recuperarUbicacionesParaAlta',26),(28,'Modificación Plan de Manejo Forestal',NULL,'/ubicacion.do?metodo=recuperarProductoresParaModificacionPMF',26),(29,'Boletas de Deposito',NULL,'',8),(30,'Registrar Pago Boleta de Deposito',NULL,'/guiaForestal.do?metodo=recuperarProductoresParaBoletasDeposito&forward=cargarGuiaForestalPagoBoletaDeposito&forwardBuscarNroGuia=cargarGuiaForestalPagoBoletaDepositoPorNroGuia',29),(31,'Reemplazar Boleta de Deposito',NULL,'/guiaForestal.do?metodo=recuperarProductoresParaBoletasDeposito&forward=cargarGuiaForestalReemBoletaDeposito&forwardBuscarNroGuia=cargarGuiaForestalReemBoletaDepositoPorNroGuia',29),(32,'Vales de Transporte',NULL,'',8),(33,'Devolución Vale de Transporte',NULL,'/guiaForestal.do?metodo=recuperarProductoresParaValeTransporte&forward=cargarGuiaForestalDevolucionValeTransporte&forwardBuscarNroGuia=cargarGuiaForestalDevolucionValeTransportePorNroGuia',32),(34,'Consultas',3,'',NULL),(35,'Fiscalizacion',NULL,'',34),(36,'Fiscalizacion de Productos Forestales con Guia',NULL,'/consultasFiscalizacion.do?metodo=cargarTiposDeEntidadConsultaFiscalizacion&forward=recuperarFiscalizacionesConGuiaForestal',35),(37,'Guia Forestal',NULL,'',34),(38,'Guías Forestales Vigentes',NULL,'/consultasPorProductor.do?metodo=cargarConsultaPorProductores&forward=recuperarGuiasForestalesVigentes',37),(39,'Guías Forestales No Vigentes',NULL,'/consultasPorProductor.do?metodo=cargarConsultaPorProductores&forward=recuperarGuiasForestalesNoVigentes',37),(40,'Localidad',NULL,NULL,12),(41,'Alta de Localidad',NULL,'/jsp.do?page=.altaLocalidad&metodo=altaLocalidad',40),(42,'Modificación de Localidad',NULL,'/cargarLocalidadesAModificar.do?metodo=cargarLocalidadesAModificar',40),(43,'Rol',NULL,NULL,12),(44,'Alta de Rol',NULL,'/recuperarRol.do?metodo=cargarAltaRol',43),(45,'Modificación de Rol',NULL,'/recuperarRol.do?metodo=cargarModificacionRol',43),(46,'Reportes',4,'',NULL),(49,'Deudas de Aforo',NULL,'/consultasPorProductor.do?metodo=cargarConsultaPorProductores&forward=recuperarGuiasForestalesConDeudasAforo',37),(50,'Deudas Vales de Transporte',NULL,'/consultasPorProductor.do?metodo=cargarConsultaPorProductores&forward=recuperarGuiasForestalesConDeudasValeTransporte',37),(51,'Fiscalizacion de Productos Forestales sin Guia',NULL,'/consultasFiscalizacion.do?metodo=cargarTiposDeEntidadConsultaFiscalizacion&forward=recuperarFiscalizacionesSinGuiaForestal',35),(53,'Reemplazar Vale de Transporte',NULL,'/guiaForestal.do?metodo=recuperarProductoresParaValeTransporte&forward=cargarGuiaForestalReemplazarValeTransporte&forwardBuscarNroGuia=cargarGuiaForestalReemplazarValeTransportePorNroGuia',32),(55,'Asociar Fiscalización a Guía',NULL,'/jsp.do?page=.recuperarGuiaAsociarFiscalizacion',8),(56,'Período',NULL,NULL,12),(57,'Alta de Período',NULL,'/jsp.do?page=.altaPeriodo&metodo=altaPeriodo',56),(58,'Modificación de Período',NULL,'/cargarPeriodosAModificar.do?metodo=cargarPeriodosAModificar',56),(59,'Por Productor',NULL,NULL,46),(60,'Volumen Fiscalizado',NULL,NULL,59),(61,'Por Productos',NULL,'/reportesPorProductor.do?metodo=cargarReportePorProductorGeneral&paramForward=generarReporteVolumenFiscalizadoPorProductos',60),(62,'Total',NULL,'/reportesPorProductor.do?metodo=cargarReporteVolumenFiscalizadoTotal',60),(63,'Volumen de GFB',NULL,NULL,59),(64,'Montos Pagos',NULL,'/reportesPorProductor.do?metodo=cargarReportePorProductorGeneral&paramForward=generarReporteVolumenGFBMontosPagos',63),(65,'Montos Adeudados',NULL,'/reportesPorProductor.do?metodo=cargarReportePorProductorGeneral&paramForward=generarReporteVolumenGFBMontosAdeudados',63),(66,'Listado de Boletas de Deposito',NULL,NULL,59),(67,'Pagas',NULL,'/reportesPorProductor.do?metodo=cargarReportePorProductorGeneral&paramForward=generarReporteListaBoletasPagas',66),(68,'Impagas',NULL,'/reportesPorProductor.do?metodo=cargarReportePorProductorGeneral&paramForward=generarReporteListaBoletasImpagas',66),(69,'Listado de Vales de Transporte',NULL,NULL,59),(70,'Devueltos',NULL,'/reportesPorProductor.do?metodo=cargarReportePorProductorGeneral&paramForward=generarReporteListaValesDevueltos',69),(71,'En Uso',NULL,'/reportesPorProductor.do?metodo=cargarReportePorProductorGeneral&paramForward=generarReporteListaValesEnUso',69),(72,'Volumen por Ubicacion',NULL,'/reportesPorProductor.do?metodo=cargarReportePorProductorVolumenPorUbicacion&paramForward=generarReporteVolumenPorUbicacion',59),(73,'Volumen por Plan de Manejo',NULL,NULL,72),(74,'Volumen por Tranzon',NULL,NULL,72),(75,'Volumen por Marcacion',NULL,NULL,72),(76,'Por Producto',NULL,NULL,46),(77,'Volumen Por Año Forestal',NULL,'/jsp.do?page=.reportePorProductoVolumenPorAnioForestal',76),(78,'Por Productor',NULL,NULL,76),(79,'Entre Fechas',NULL,'/reportesPorProducto.do?metodo=cargarReporteVolumenPorProductorEntreFechas',78),(80,'Por Ubicación',NULL,'/reportesPorProducto.do?metodo=cargarReporteVolumenPorProductoPorProductorPorUbicacion',78),(81,'En Tranzon',NULL,NULL,78),(82,'En Marcacion',NULL,NULL,78),(83,'Recaudacion',NULL,NULL,46),(84,'Por Productor',NULL,NULL,83),(85,'Entre Fechas',NULL,'/reportesRecaudacion.do?metodo=cargarReporteRecaudacionPorProductorEntreFechas',84),(86,'Por Año Forestal',NULL,'/reportesRecaudacion.do?metodo=cargarReporteRecaudacionPorProductorPorAnioForestal',84),(87,'Por Ubicacion',NULL,'/reportesRecaudacion.do?metodo=cargarReporteRecaudacionPorProductorPorUbicacion',84),(88,'Total de Productores',NULL,NULL,83),(89,'Entre Fechas',NULL,'/jsp.do?page=.reporteRecaudacionTotalProductoresEntreFechas',88),(90,'Año Forestal',NULL,NULL,83),(91,'Por Productor',NULL,'/reportesRecaudacion.do?metodo=cargarReporteRecaudacionPorAnioForestalPorProductor',90),(92,'Total de Productores',NULL,'/jsp.do?page=.reporteRecaudacionPorAnioForestalTotalProductores',90),(93,'Total',NULL,'/reportesPorProductor.do?metodo=cargarReportePorProductorGeneral&paramForward=generarReporteListaBoletasTotales',66),(94,'Total',NULL,'/reportesPorProductor.do?metodo=cargarReportePorProductorGeneral&paramForward=generarReporteListaValesTotales',69),(95,'Desasociar Fiscalización a Guía',NULL,'/jsp.do?page=.recuperarGuiaDesasociarFiscalizacion',8);
/*!40000 ALTER TABLE `itemmenu` ENABLE KEYS */;
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
INSERT INTO `rol_item` VALUES (8,1),(8,2),(8,4),(8,6),(8,7),(8,8),(8,9),(8,10),(8,16),(8,18),(8,29),(8,30),(8,31),(8,32),(8,33),(8,34),(8,35),(8,36),(8,37),(8,38),(8,39),(8,46),(8,49),(8,50),(8,51),(8,53),(8,55),(9,1),(9,2),(9,6),(9,7),(9,8),(9,9),(9,16),(9,18),(9,29),(9,30),(9,32),(9,33),(9,34),(9,35),(9,36),(9,37),(9,38),(9,39),(9,46),(9,49),(9,50),(9,51),(9,55),(2,1),(2,2),(2,3),(2,4),(2,6),(2,7),(2,8),(2,9),(2,10),(2,12),(2,13),(2,14),(2,15),(2,16),(2,17),(2,18),(2,19),(2,20),(2,21),(2,22),(2,23),(2,24),(2,25),(2,26),(2,27),(2,28),(2,29),(2,30),(2,31),(2,32),(2,33),(2,34),(2,35),(2,36),(2,37),(2,38),(2,39),(2,40),(2,41),(2,42),(2,46),(2,49),(2,50),(2,51),(2,53),(2,55),(2,56),(2,57),(2,58),(2,59),(2,60),(2,61),(2,62),(2,63),(2,64),(2,65),(2,66),(2,67),(2,68),(2,69),(2,70),(2,71),(2,72),(2,76),(2,77),(2,78),(2,79),(2,80),(2,83),(2,84),(2,85),(2,86),(2,87),(2,88),(2,89),(2,90),(2,91),(2,92),(2,93),(2,94),(2,95);
/*!40000 ALTER TABLE `rol_item` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-01-16 10:09:41
