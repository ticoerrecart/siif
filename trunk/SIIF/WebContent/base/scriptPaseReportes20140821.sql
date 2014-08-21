SET SQL_SAFE_UPDATES=0;
DELETE from `x071vm20_siif`.`reporte` ;
insert into `x071vm20_siif`.`reporte` SELECT * FROM `x071vm20_test_siif`.`reporte`;

