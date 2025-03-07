-- -----------------------------------------------------
-- LIMPIA ANTES DE VOLVER A CREAR LA BD
-- -----------------------------------------------------
drop schema if exists `bdiesinformatica`;
create schema if not exists `bdiesinformatica` default character set utf8 ;
show warnings;

-- -----------------------------------------------------
-- BORRA EL USUARIO Y SUS PRIVILEGIOS ANTES DE CREARLO
-- -----------------------------------------------------
use bdiesinformatica;
drop user if exists 'usuariodaw2'@'127.0.0.1';
create user 'usuariodaw2'@'127.0.0.1' identified by 'Ventura2024';
grant all on bdiesinformatica.* to 'usuariodaw2'@'127.0.0.1';
show grants for 'usuariodaw2'@'127.0.0.1';