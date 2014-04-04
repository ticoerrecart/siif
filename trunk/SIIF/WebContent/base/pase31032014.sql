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
ADD COLUMN `f931Afip` BIT(1) NULL AFTER `compensacionCaminos`;