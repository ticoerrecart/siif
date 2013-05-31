--emi 22/5/2013
ALTER TABLE `x071vm20_siif`.`localizacion` CHANGE COLUMN `tipoTerrenoPMF` `tipoTerreno` VARCHAR(255) NULL DEFAULT NULL  ;


--emi 31/5/2013
INSERT INTO `x071vm20_siif`.`itemmenu` (`id`, `item`, `url`, `item_fk`) 
VALUES ('121', 'Volumen Total Exportadores Entre Fechas', '/jsp.do?page=.reporteCertificadoOrigenTotalExportadoresEntreFechas&metodo=generarReporteCertificadosOrigenTotalExportadoresEntreFechas', '118');

insert into `x071vm20_siif`.`rol_item`
values(2,121)

insert into `x071vm20_siif`.`rol_item`
values(8,121)