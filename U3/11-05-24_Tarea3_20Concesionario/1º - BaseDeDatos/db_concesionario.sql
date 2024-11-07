/*
* CREACIÓN DEL SQL: AGUSTÍN. A MARQUEZ PIÑA
* INSERTAR DATOS EN LAS TABLAS: AGUSTÍN. A MARQUEZ PIÑA
* ESTABLECER LAS FOREIGN KEYS (FK Y PFK): AGUSTÍN. A MARQUEZ PIÑA
* ESTABLECER LAS PRIMARY KEYS (PK Y/O AK): AGUSTÍN. A MARQUEZ PIÑA
*
* CREACIÓN DEL SQL: 05/11/2024
* ULTIMA MODIFICACIÓN: 05/11/2024
* TIPO DE BASE DE DATOS: MySQL 5.0
* MODELO DE BASE DE DATOS: MySQL 5.0
*/

-- Elimina la base de datos si ya existe para evitar conflictos de nombres y 
-- asegurar una creación limpia
DROP DATABASE IF EXISTS DB_CONCESIONARIO;

-- Crea la base de datos con el nombre específico proporcionado
CREATE DATABASE DB_CONCESIONARIO;

-- Selecciona la base de datos recién creada para comenzar a operar sobre ella
USE DB_CONCESIONARIO;

-- Creación de la tabla FABRICANTES
CREATE TABLE `FABRICANTES` (
    `ID` CHAR(4) NOT NULL,      -- ID único de cada fabricante en la BD.
    `NOMBRE` VARCHAR(35)        -- Nombre del fabricante al que se refiere.
);

-- Definimos la primary key de la tabla "FABRICANTES"
ALTER TABLE `FABRICANTES` ADD PRIMARY KEY (`ID`);

-- Creación de la tabla COCHES
CREATE TABLE `COCHES` (
    `ID` CHAR(4) NOT NULL,        -- ID único de cada coche en la BD.
    `MODELO` VARCHAR(35),         -- Modelo del coche al que se refiere.
    `ID_FABRICANTE` CHAR(4),      -- Clave foránea que conecta con la tabla FABRICANTES.
    `FOTO` MEDIUMBLOB,            -- Foto del coche almacenada en formato binario.
    `AÑO` INT                     -- Año de fabricación del coche.
);

-- Definimos la primary key de la tabla "COCHES"
ALTER TABLE `COCHES` ADD PRIMARY KEY (`ID`);

-- Definimos la foreign key en la tabla "COCHES" que conecta con "FABRICANTES"
ALTER TABLE `COCHES` ADD CONSTRAINT `FK_FABRICANTE` FOREIGN KEY (`ID_FABRICANTE`)
REFERENCES `FABRICANTES`(`ID`) ON DELETE CASCADE ON UPDATE CASCADE;
