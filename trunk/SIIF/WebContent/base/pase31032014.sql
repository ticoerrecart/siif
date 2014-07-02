delete from `x071vm20_siif`.`rol_item`
where item_fk=24;

delete from `x071vm20_siif`.`ItemMenu`
where id=24;

CREATE TABLE `x071vm20_siif`.`AforoNuevo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `monto` double DEFAULT NULL,
  `porcentaje` int(11) DEFAULT NULL,
  `tipoDeAforo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `x071vm20_siif`.`CaminoConstruido` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `autorizante` varchar(255) DEFAULT NULL,
  `costoDelCamino` double NOT NULL,
  `monto` double NOT NULL,
  `notaReferencia` varchar(255) DEFAULT NULL,
  `usuario_fk` bigint(20) DEFAULT NULL,
  `productor_fk` bigint(20) DEFAULT NULL,
  `guiaForestal_fk` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5D7D70492BED83E6` (`productor_fk`),
  KEY `FK5D7D70496F29D8B9` (`guiaForestal_fk`),
  KEY `FK5D7D7049D3833F8F` (`usuario_fk`),
  CONSTRAINT `FK5D7D7049D3833F8F` FOREIGN KEY (`usuario_fk`) REFERENCES `Usuario` (`id`),
  CONSTRAINT `FK5D7D70492BED83E6` FOREIGN KEY (`productor_fk`) REFERENCES `Entidad` (`id`),
  CONSTRAINT `FK5D7D70496F29D8B9` FOREIGN KEY (`guiaForestal_fk`) REFERENCES `GuiaForestal` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `x071vm20_siif`.`CuentaCorrienteFiscalizacion` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fecha` datetime NOT NULL,
  `monto` double NOT NULL,
  `usuario_fk` bigint(20) DEFAULT NULL,
  `guiaForestal_fk` bigint(20) DEFAULT NULL,
  `fiscalizacion_fk` bigint(20) DEFAULT NULL,
  `productor_fk` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKEC5B99C82BED83E6` (`productor_fk`),
  KEY `FKEC5B99C819787EAF` (`fiscalizacion_fk`),
  KEY `FKEC5B99C86F29D8B9` (`guiaForestal_fk`),
  KEY `FKEC5B99C8D3833F8F` (`usuario_fk`),
  CONSTRAINT `FKEC5B99C8D3833F8F` FOREIGN KEY (`usuario_fk`) REFERENCES `Usuario` (`id`),
  CONSTRAINT `FKEC5B99C819787EAF` FOREIGN KEY (`fiscalizacion_fk`) REFERENCES `Fiscalizacion` (`id`),
  CONSTRAINT `FKEC5B99C82BED83E6` FOREIGN KEY (`productor_fk`) REFERENCES `Entidad` (`id`),
  CONSTRAINT `FKEC5B99C86F29D8B9` FOREIGN KEY (`guiaForestal_fk`) REFERENCES `GuiaForestal` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



INSERT INTO `x071vm20_siif`.`AforoNuevo` (`id`, `monto`, `tipoDeAforo`) VALUES ('1', '120', 'BASICO');
INSERT INTO `x071vm20_siif`.`AforoNuevo` (`id`, `porcentaje`, `tipoDeAforo`) VALUES ('2', '25', 'MAT_CAIDO_O_TRAT_SILVIC_INCOMPL');
INSERT INTO `x071vm20_siif`.`AforoNuevo` (`id`, `porcentaje`, `tipoDeAforo`) VALUES ('3', '15', 'PM_SILVOPASTORIL');
INSERT INTO `x071vm20_siif`.`AforoNuevo` (`id`, `porcentaje`, `tipoDeAforo`) VALUES ('4', '10', 'USO_EN_EL_LUGAR');
INSERT INTO `x071vm20_siif`.`AforoNuevo` (`id`, `porcentaje`, `tipoDeAforo`) VALUES ('5', '15', 'USO_COMERCIAL');
INSERT INTO `x071vm20_siif`.`AforoNuevo` (`id`, `porcentaje`, `tipoDeAforo`) VALUES ('6', '0', 'ESTRUCTURA_IRREGULAR');
INSERT INTO `x071vm20_siif`.`AforoNuevo` (`id`, `porcentaje`, `tipoDeAforo`) VALUES ('7', '100', 'CLASIFICACION_DIAMETROS');


UPDATE `x071vm20_siif`.`ItemMenu` SET `url`='/recuperarAforoNuevo.do?metodo=cargarModificacionAforos' WHERE `id`='25';

update `x071vm20_siif`.`TipoProducto`
set habilitado = 0
where id= 25;

update `x071vm20_siif`.`TipoProducto`
set habilitado = 0
where id= 26;

ALTER TABLE `x071vm20_siif`.`GuiaForestal` 
ADD COLUMN `tipoDeAforo` VARCHAR(255) NULL;

ALTER TABLE `x071vm20_siif`.`GuiaForestal` 
ADD COLUMN `compensacionCaminos` DOUBLE;

ALTER TABLE `x071vm20_siif`.`GuiaForestal` 
ADD COLUMN `f931Afip` BIT(1) NULL AFTER `compensacionCaminos`;

ALTER TABLE `x071vm20_siif`.`GuiaForestal` 
ADD COLUMN `compensacionFiscalizacion` DOUBLE;

/*Menu de Caminos de 2do Orden*/
UPDATE `x071vm20_siif`.`ItemMenu` SET `orden`='10' WHERE `id`='6';
UPDATE `x071vm20_siif`.`ItemMenu` SET `orden`='8' WHERE `id`='12';
UPDATE `x071vm20_siif`.`ItemMenu` SET `orden`='9' WHERE `id`='16';
UPDATE `x071vm20_siif`.`ItemMenu` SET `orden`='6' WHERE `id`='34';
UPDATE `x071vm20_siif`.`ItemMenu` SET `orden`='7' WHERE `id`='46';
INSERT INTO `x071vm20_siif`.`ItemMenu` (`id`, `item`, `orden`, `url`) VALUES ('123', 'Camino 2do Orden', '4', NULL);
INSERT INTO `x071vm20_siif`.`ItemMenu` (`id`, `item`, `url`, `item_fk`) VALUES ('124', 'Alta Camino 2do Orden', '/camino.do?metodo=inicializarAltaCamino', '123');
INSERT INTO `x071vm20_siif`.`ItemMenu` (`id`, `item`, `url`, `item_fk`) VALUES ('125', 'Consulta Camino 2do Orden', '/camino.do?metodo=consultaSaldo', '123');

/*Menu de Compensacion de Fiscalizaciones*/
INSERT INTO `x071vm20_siif`.`ItemMenu` (`id`, `item`, `orden`) VALUES ('126', 'Compensación de Fiscalizaciones', '5');
INSERT INTO `x071vm20_siif`.`ItemMenu` (`id`, `item`, `url`, `item_fk`) VALUES ('127', 'Consulta Saldo', '/guiaForestal.do?metodo=consultaSaldoCCFiscalizacion', '126');

insert into `x071vm20_siif`.`rol_item`
values(1,123);
insert into `x071vm20_siif`.`rol_item`
values(1,124);
insert into `x071vm20_siif`.`rol_item`
values(1,125);
insert into `x071vm20_siif`.`rol_item`
values(2,123);
insert into `x071vm20_siif`.`rol_item`
values(2,124);
insert into `x071vm20_siif`.`rol_item`
values(2,125);
insert into `x071vm20_siif`.`rol_item`
values(2,126);
insert into `x071vm20_siif`.`rol_item`
values(2,127);

SET SQL_SAFE_UPDATES=0;
UPDATE `x071vm20_siif`.`GuiaForestal` SET `compensacionCaminos`=0;

SET SQL_SAFE_UPDATES=0;
UPDATE `x071vm20_siif`.`GuiaForestal` SET `f931Afip`=0;

SET SQL_SAFE_UPDATES=0;
UPDATE `x071vm20_siif`.`GuiaForestal` SET `compensacionFiscalizacion`=0;


ALTER TABLE `x071vm20_siif`.`SubImporte` 
ADD COLUMN `comercializaDentroProvincia` BIT(1) NULL;

update x071vm20_siif.subimporte
set comercializaDentroProvincia = false
where id>0;

update x071vm20_siif.guiaforestal
set tipoDeAforo='BASICO'
where id>1;