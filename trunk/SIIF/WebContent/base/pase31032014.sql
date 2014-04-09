delete from x071vm20_siif.rol_item
where item_fk=24;

delete from x071vm20_siif.itemmenu
where id=24;

INSERT INTO `x071vm20_siif`.`aforonuevo` (`id`, `monto`, `tipoDeAforo`) VALUES ('1', '120', 'BASICO');
INSERT INTO `x071vm20_siif`.`aforonuevo` (`id`, `porcentaje`, `tipoDeAforo`) VALUES ('2', '25', 'MAT_CAIDO_O_TRAT_SILVIC_INCOMPL');
INSERT INTO `x071vm20_siif`.`aforonuevo` (`id`, `porcentaje`, `tipoDeAforo`) VALUES ('3', '15', 'PM_SILVOPASTORIL');
INSERT INTO `x071vm20_siif`.`aforonuevo` (`id`, `porcentaje`, `tipoDeAforo`) VALUES ('4', '10', 'USO_EN_EL_LUGAR');
INSERT INTO `x071vm20_siif`.`aforonuevo` (`id`, `porcentaje`, `tipoDeAforo`) VALUES ('5', '15', 'USO_COMERCIAL');
INSERT INTO `x071vm20_siif`.`aforonuevo` (`id`, `porcentaje`, `tipoDeAforo`) VALUES ('6', '0', 'ESTRUCTURA_IRREGULAR');
INSERT INTO `x071vm20_siif`.`aforonuevo` (`id`, `porcentaje`, `tipoDeAforo`) VALUES ('7', '100', 'CLASIFICACION_DIAMETROS');


UPDATE `x071vm20_siif`.`itemmenu` SET `url`='/recuperarAforoNuevo.do?metodo=cargarModificacionAforos' WHERE `id`='25';

update x071vm20_siif.tipoproducto
set habilitado = 0
where id= 25;

update x071vm20_siif.tipoproducto
set habilitado = 0
where id= 26;

ALTER TABLE `x071vm20_siif`.`guiaforestal` 
ADD COLUMN `tipoDeAforo` VARCHAR(255) NULL;

ALTER TABLE `x071vm20_siif`.`guiaforestal` 
ADD COLUMN `compensacionCaminos` DOUBLE;

ALTER TABLE `x071vm20_siif`.`guiaforestal` 
ADD COLUMN `f931Afip` BIT(1) NULL AFTER `compensacionCaminos`;

ALTER TABLE `x071vm20_siif`.`guiaforestal` 
ADD COLUMN `compensacionFiscalizacion` DOUBLE;

/*Menu de Caminos de 2do Orden*/
INSERT INTO `x071vm20_siif`.`itemmenu` (`id`, `item`, `orden`, `url`) VALUES ('123', 'Camino 2do Orden', '4', NULL);
UPDATE `x071vm20_siif`.`itemmenu` SET `orden`='9' WHERE `id`='6';
UPDATE `x071vm20_siif`.`itemmenu` SET `orden`='7' WHERE `id`='12';
UPDATE `x071vm20_siif`.`itemmenu` SET `orden`='8' WHERE `id`='16';
UPDATE `x071vm20_siif`.`itemmenu` SET `orden`='5' WHERE `id`='34';
UPDATE `x071vm20_siif`.`itemmenu` SET `orden`='6' WHERE `id`='46';
INSERT INTO `x071vm20_siif`.`itemmenu` (`id`, `item`, `url`, `item_fk`) VALUES ('124', 'Alta Camino 2do Orden', '/camino.do?metodo=inicializarAltaCamino', '123');
INSERT INTO `x071vm20_siif`.`itemmenu` (`id`, `item`, `url`, `item_fk`) VALUES ('125', 'Consulta Camino 2do Orden', '/camino.do?metodo=consultaSaldo', '123');

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

SET SQL_SAFE_UPDATES=0;
UPDATE `x071vm20_siif`.`guiaforestal` SET `compensacionCaminos`=0;

SET SQL_SAFE_UPDATES=0;
UPDATE `x071vm20_siif`.`guiaforestal` SET `f931Afip`=0;

SET SQL_SAFE_UPDATES=0;
UPDATE `x071vm20_siif`.`guiaforestal` SET `compensacionFiscalizacion`=0;