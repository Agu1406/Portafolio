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
    `ID` INT NOT NULL,      -- ID único de cada fabricante en la BD.
    `NOMBRE` VARCHAR(35) NOT NULL        -- Nombre del fabricante al que se refiere.
);

-- Definimos la primary key de la tabla "FABRICANTES"
ALTER TABLE `FABRICANTES` ADD PRIMARY KEY (`ID`);

-- Decimos que el "ID" sea un valor auto incrementable.
ALTER TABLE `FABRICANTES` MODIFY COLUMN `ID` INT AUTO_INCREMENT;

-- Creación de la tabla COCHES
CREATE TABLE `COCHES` (
    `ID` INT NOT NULL,        -- ID único de cada coche en la BD.
    `MODELO` VARCHAR(35) NOT NULL,         -- Modelo del coche al que se refiere.
    `ID_FABRICANTE` INT,      -- Clave foránea que conecta con la tabla FABRICANTES.
    `FOTO` MEDIUMBLOB,            -- Foto del coche almacenada en formato binario.
    `AÑO` INT                     -- Año de fabricación del coche.
);

-- Definimos la primary key de la tabla "COCHES"
ALTER TABLE `COCHES` ADD PRIMARY KEY (`ID`);

-- Definimos la foreign key en la tabla "COCHES" que conecta con "FABRICANTES"
ALTER TABLE `COCHES` ADD CONSTRAINT `FK_FABRICANTE` FOREIGN KEY (`ID_FABRICANTE`)
REFERENCES `FABRICANTES`(`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

-- Decimos que el "ID" de COCHES sea un valor auto incrementable.
ALTER TABLE `COCHES` MODIFY COLUMN `ID` INT AUTO_INCREMENT;
