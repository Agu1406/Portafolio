﻿/*
Created: 15/04/2024
Modified: 16/04/2024
Model: MySQL 5.0
Database: MySQL 5.0
*/

-- Create tables section -------------------------------------------------

-- Table CURSOS

CREATE TABLE `CURSOS`
(
  `CODIGO_CURSO` Char(4) NOT NULL DEFAULT DAWX,
  `NOMBRE_CURSO` Varchar(45)
)
;

ALTER TABLE `CURSOS` ADD PRIMARY KEY (`CODIGO_CURSO`)
;

-- Table MODULOS

CREATE TABLE `MODULOS`
(
  `CODIGO_MODULO` Char(4) NOT NULL,
  `NOMBRE_MODULO` Varchar(20) NOT NULL,
  `HORAS_TOTALES` Int NOT NULL,
  `HORAS_SEMANALES` Int
)
;

ALTER TABLE `MODULOS` ADD PRIMARY KEY (`CODIGO_MODULO`)
;

-- Table ALUMNOS

CREATE TABLE `ALUMNOS`
(
  `NIA_ALUMNO` Char(9) NOT NULL,
  `DNI_ALUMNO` Char(9) NOT NULL,
  `GENERO_ALUMNO` Char(1) NOT NULL,
  `FECHA_NACIMIENTO` Date NOT NULL,
  `NOMBRE_ALUMNO` Varchar(20) NOT NULL,
  `APELLIDO1_ALUMNO` Char(20) NOT NULL,
  `APELLIDO2_ALUMNO` Char(20) NOT NULL,
  `DIRECCION_ALUMNO` Varchar(255) NOT NULL
)
;

ALTER TABLE `ALUMNOS` ADD PRIMARY KEY (`NIA_ALUMNO`)
;

ALTER TABLE `ALUMNOS` ADD UNIQUE `DNI_ALUMNO` (`DNI_ALUMNO`)
;

-- Table PROFESORES

CREATE TABLE `PROFESORES`
(
  `CODIGO_PROFESOR` Char(5) NOT NULL,
  `NOMBRE_PROFESOR` Varchar(20) NOT NULL,
  `APELLIDO_PROFESOR` Varchar(20) NOT NULL,
  `ESPECIALIDAD` Varchar(5) NOT NULL
)
;

ALTER TABLE `PROFESORES` ADD PRIMARY KEY (`CODIGO_PROFESOR`,`ESPECIALIDAD`)
;

-- Table ESPECIALIDADES

CREATE TABLE `ESPECIALIDADES`
(
  `ESPECIALIDAD` Varchar(5) NOT NULL,
  `NOMBRE_ESPECIALIDAD` Varchar(60) NOT NULL,
  `DESCRIPCION_ESPECIALIDAD` Varchar(150) NOT NULL
)
;

ALTER TABLE `ESPECIALIDADES` ADD PRIMARY KEY (`ESPECIALIDAD`)
;

-- Table MAATRICULAS_MODULOS

CREATE TABLE `MAATRICULAS_MODULOS`
(
  `CODIGO_MATRICULA` Char(5) NOT NULL,
  `NIA_ALUMNO` Char(9) NOT NULL,
  `CODIGO_MODULO` Char(4) NOT NULL,
  `CODIGO_PROFESOR` Char(5) NOT NULL,
  `FECHA_MATRICULA` Date NOT NULL,
  `CONVOCATORIAS_RESTANTES` Int NOT NULL,
  `ESPECIALIDAD` Varchar(5) NOT NULL
)
;

ALTER TABLE `MAATRICULAS_MODULOS` ADD PRIMARY KEY (`CODIGO_MODULO`,`NIA_ALUMNO`,`CODIGO_PROFESOR`,`CODIGO_MATRICULA`,`ESPECIALIDAD`)
;

-- Table CATALOGO_MODULOS

CREATE TABLE `CATALOGO_MODULOS`
(
  `CODIGO_CURSO` Char(4) NOT NULL,
  `CODIGO_MODULO` Char(4) NOT NULL
)
;

ALTER TABLE `CATALOGO_MODULOS` ADD PRIMARY KEY (`CODIGO_CURSO`,`CODIGO_MODULO`)
;

-- Table CALIFICACIONES

CREATE TABLE `CALIFICACIONES`
(
  `CODIGO_MODULO` Char(4) NOT NULL,
  `NIA_ALUMNO` Char(9) NOT NULL,
  `CODIGO_PROFESOR` Char(5) NOT NULL,
  `CODIGO_MATRICULA` Char(5) NOT NULL,
  `ESPECIALIDAD` Varchar(5) NOT NULL,
  `PRIMERA_EVALUACION` Int NOT NULL,
  `SEGUNDA_EVALUACION` Int NOT NULL,
  `ORDINARIA` Int NOT NULL,
  `EXTRAORDINARIA` Int
)
;

ALTER TABLE `CALIFICACIONES` ADD PRIMARY KEY (`CODIGO_MODULO`,`NIA_ALUMNO`,`CODIGO_PROFESOR`,`CODIGO_MATRICULA`,`ESPECIALIDAD`)
;

-- Create foreign keys (relationships) section -------------------------------------------------

ALTER TABLE `MAATRICULAS_MODULOS` ADD CONSTRAINT `APARECE EN` FOREIGN KEY (`CODIGO_MODULO`) REFERENCES `MODULOS` (`CODIGO_MODULO`) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE `MAATRICULAS_MODULOS` ADD CONSTRAINT `CURSA` FOREIGN KEY (`NIA_ALUMNO`) REFERENCES `ALUMNOS` (`NIA_ALUMNO`) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE `MAATRICULAS_MODULOS` ADD CONSTRAINT `IMPARTE` FOREIGN KEY (`CODIGO_PROFESOR`, `ESPECIALIDAD`) REFERENCES `PROFESORES` (`CODIGO_PROFESOR`, `ESPECIALIDAD`) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE `PROFESORES` ADD CONSTRAINT `TIENE` FOREIGN KEY (`ESPECIALIDAD`) REFERENCES `ESPECIALIDADES` (`ESPECIALIDAD`) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE `CATALOGO_MODULOS` ADD CONSTRAINT `DEFINE` FOREIGN KEY (`CODIGO_CURSO`) REFERENCES `CURSOS` (`CODIGO_CURSO`) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE `CATALOGO_MODULOS` ADD CONSTRAINT `ESTA EN` FOREIGN KEY (`CODIGO_MODULO`) REFERENCES `MODULOS` (`CODIGO_MODULO`) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE `CALIFICACIONES` ADD CONSTRAINT `CALIFICA` FOREIGN KEY (`CODIGO_MODULO`, `NIA_ALUMNO`, `CODIGO_PROFESOR`, `CODIGO_MATRICULA`, `ESPECIALIDAD`) REFERENCES `MAATRICULAS_MODULOS` (`CODIGO_MODULO`, `NIA_ALUMNO`, `CODIGO_PROFESOR`, `CODIGO_MATRICULA`, `ESPECIALIDAD`) ON DELETE RESTRICT ON UPDATE RESTRICT
;

