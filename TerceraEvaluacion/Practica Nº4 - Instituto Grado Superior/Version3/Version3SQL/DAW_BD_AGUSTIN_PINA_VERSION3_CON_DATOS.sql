/*
* CREACIÓN DEL SQL: AGUSTÍN. A MARQUEZ PIÑA
* INSERTAR DATOS EN LAS TABLAS: AGUSTÍN. A MARQUEZ PIÑA
* ESTABLECER LAS FOREIGN KEYS (FK Y PFK): AGUSTÍN. A MARQUEZ PIÑA
* ESTABLECER LAS PRIMARY KEYS (PK Y/O AK): AGUSTÍN. A MARQUEZ PIÑA
*
* CREACIÓN DEL SQL: 15/04/2024
* ULTIMA MODIFICACIÓN: 30/04/2024
* TIPO DE BASE DE DATOS: MySQL 5.0
* MODELO DE BASE DE DATOS: MySQL 5.0
*/
-- Elimina la base de datos si ya existe para evitar conflictos de nombres y 
-- asegurar una creación limpia
DROP DATABASE IF EXISTS DAW_BD_AGUSTIN_PINA_VERSION3;
-- Crea la base de datos con el nombre específico proporcionado
CREATE DATABASE DAW_BD_AGUSTIN_PINA;
-- Selecciona la base de datos recién creada para comenzar a operar sobre ella
USE DAW_BD_AGUSTIN_PINA;

-- Creación de la tabla CURSOS
CREATE TABLE `CURSOS`(
  `CODIGO_CURSO` Char(4) NOT NULL, -- Almacena el curso actual "DAW1" o "DAW2"
  `NOMBRE_CURSO` Varchar(45) -- "Puede llevar el nombre del curso completo.
);

-- Define el código de curso como la primary key de la tabla.
ALTER TABLE `CURSOS` ADD PRIMARY KEY (`CODIGO_CURSO`);

-- Creación de la tabla MODULOS
CREATE TABLE `MODULOS`(
  `CODIGO_MODULO` Char(4) NOT NULL,
  `NOMBRE_MODULO` Varchar(20) NOT NULL,
  `HORAS_TOTALES` Int NOT NULL,
  `HORAS_SEMANALES` Int
);

-- Define el código de modulo como la primary key de la tabla.
ALTER TABLE `MODULOS` ADD PRIMARY KEY (`CODIGO_MODULO`);

-- Creación de la tabla ALUMNOS
CREATE TABLE `ALUMNOS`(
  `NIA_ALUMNO` Char(9) NOT NULL,
  `DNI_ALUMNO` Char(9) NOT NULL,
  `GENERO_ALUMNO` Char(1) NOT NULL,
  `FECHA_NACIMIENTO` Date NOT NULL,
  `NOMBRE_ALUMNO` Varchar(20) NOT NULL,
  `APELLIDO1_ALUMNO` Char(20) NOT NULL,
  `APELLIDO2_ALUMNO` Char(20) NOT NULL,
  `DIRECCION_ALUMNO` Varchar(255) NOT NULL
);

-- Define el NIA de los alumnos como la primary key de la tabla.
ALTER TABLE `ALUMNOS` ADD PRIMARY KEY (`NIA_ALUMNO`);

-- Define el DNI de los alumnos como la alternative key de la tabla.
ALTER TABLE `ALUMNOS` ADD UNIQUE `DNI_ALUMNO` (`DNI_ALUMNO`);

-- Creación de la tabla ESPECIALIDADES
CREATE TABLE `ESPECIALIDADES`(
  `ESPECIALIDAD` Varchar(5) NOT NULL,
  `NOMBRE_ESPECIALIDAD` Varchar(60) NOT NULL,
  `DESCRIPCION_ESPECIALIDAD` Varchar(150) NOT NULL
);
ALTER TABLE `ESPECIALIDADES` ADD PRIMARY KEY (`ESPECIALIDAD`);

-- Creación de la tabla PROFESORES
CREATE TABLE `PROFESORES`(
  `CODIGO_PROFESOR` Char(5) NOT NULL,
  `NOMBRE_PROFESOR` Varchar(20) NOT NULL,
  `APELLIDO_PROFESOR` Varchar(20) NOT NULL,
  `ESPECIALIDAD` Varchar(5) NOT NULL
);

ALTER TABLE `PROFESORES` ADD PRIMARY KEY (`CODIGO_PROFESOR`);
ALTER TABLE `PROFESORES` ADD CONSTRAINT `FK_ESPECIALIDAD` FOREIGN KEY (`ESPECIALIDAD`) 
REFERENCES `ESPECIALIDADES` (`ESPECIALIDAD`) ON DELETE CASCADE ON UPDATE CASCADE;

-- Creación de la tabla MATRICULAS_MODULOS
CREATE TABLE `MATRICULAS_MODULOS`(
  `CODIGO_MATRICULA` Char(5) NOT NULL,
  `NIA_ALUMNO` Char(9) NOT NULL,
  `CODIGO_MODULO` Char(4) NOT NULL,
  `CODIGO_PROFESOR` Char(5) NOT NULL,
  `FECHA_MATRICULA` Date NOT NULL,
  `CONVOCATORIAS_RESTANTES` Int NOT NULL,
  `FALTAS_ALUMNO` Int NOT NULL
);

-- Define las PFK de la entidad "Matricula Modulos".
ALTER TABLE `MATRICULAS_MODULOS` ADD PRIMARY KEY (`CODIGO_MODULO`, `NIA_ALUMNO`, `CODIGO_PROFESOR`, `CODIGO_MATRICULA`);

-- Define la PFK código de modulo como foreign key en la tabla.
ALTER TABLE `MATRICULAS_MODULOS` ADD CONSTRAINT `APARECE EN` FOREIGN KEY (`CODIGO_MODULO`) 
REFERENCES `MODULOS` (`CODIGO_MODULO`) ON DELETE CASCADE ON UPDATE CASCADE;

-- Define la PFK NIA de alumno como foreign key en la tabla.
ALTER TABLE `MATRICULAS_MODULOS` ADD CONSTRAINT `CURSA` FOREIGN KEY (`NIA_ALUMNO`) 
REFERENCES `ALUMNOS` (`NIA_ALUMNO`) ON DELETE CASCADE ON UPDATE CASCADE;

-- Define la PFK código de profesor
ALTER TABLE `MATRICULAS_MODULOS` ADD CONSTRAINT `IMPARTE` FOREIGN KEY (`CODIGO_PROFESOR`) 
REFERENCES `PROFESORES` (`CODIGO_PROFESOR`) ON DELETE CASCADE ON UPDATE CASCADE;

-- Creación de la tabla CATALOGO_MODULOS
CREATE TABLE `CATALOGO_MODULOS`(
  `CODIGO_CURSO` Char(4) NOT NULL,
  `CODIGO_MODULO` Char(4) NOT NULL
);

-- Define las primary keys de catalogo de modulos, siendo estas código de curso y 
-- código de modulo.
ALTER TABLE `CATALOGO_MODULOS` ADD PRIMARY KEY (`CODIGO_CURSO`, `CODIGO_MODULO`);

-- Define la foreign key 1 de catalogo de modulos, siendo esta código de curso.
ALTER TABLE `CATALOGO_MODULOS` ADD CONSTRAINT `DEFINE` FOREIGN KEY (`CODIGO_CURSO`) 
REFERENCES `CURSOS` (`CODIGO_CURSO`) ON DELETE CASCADE ON UPDATE CASCADE;

-- Define la foreign key 1 de catalogo de modulos, siendo esta código de modulo.
ALTER TABLE `CATALOGO_MODULOS` ADD CONSTRAINT `ESTA EN` FOREIGN KEY (`CODIGO_MODULO`) 
REFERENCES `MODULOS` (`CODIGO_MODULO`) ON DELETE CASCADE ON UPDATE CASCADE;

-- Tabla de calificaciones: Almacena las calificaciones de los alumnos por módulo, 
-- profesor y matrícula.
CREATE TABLE `CALIFICACIONES`
(
  `CODIGO_MODULO` Char(4) NOT NULL,
  `NIA_ALUMNO` Char(9) NOT NULL,
  `CODIGO_PROFESOR` Char(5) NOT NULL,
  `CODIGO_MATRICULA` Char(5) NOT NULL,
  `PRIMERA_EVALUACION` Int NOT NULL,
  `SEGUNDA_EVALUACION` Int NOT NULL,
  `ORDINARIA` Int NOT NULL,
  `EXTRAORDINARIA` Int
);

-- Define la primary key de la tabla de calificaciones, es compuesta, son 4 PFK.
ALTER TABLE `CALIFICACIONES` ADD PRIMARY KEY (`CODIGO_MODULO`, `NIA_ALUMNO`, `CODIGO_PROFESOR`, `CODIGO_MATRICULA`);

-- Relación que asegura que cada calificación se asocie con una matrícula válida.
ALTER TABLE `CALIFICACIONES` ADD CONSTRAINT `CALIFICA` 
FOREIGN KEY (`CODIGO_MODULO`, `NIA_ALUMNO`, `CODIGO_PROFESOR`, `CODIGO_MATRICULA`) 
REFERENCES `MATRICULAS_MODULOS` (`CODIGO_MODULO`, `NIA_ALUMNO`, `CODIGO_PROFESOR`, `CODIGO_MATRICULA`) 
ON DELETE CASCADE ON UPDATE CASCADE;

/*
* CREACIÓN DE LOS INSERT: AGUSTÍN. A MARQUEZ PIÑA
*
* CREACIÓN DEL SQL: 15/04/2024
* ULTIMA MODIFICACIÓN: 30/04/2024
* TIPO DE BASE DE DATOS: MySQL 5.0
* MODELO DE BASE DE DATOS: MySQL 5.0
*
* Los datos se insertaran en las tablas en el mismo orden en el que
* fueron creadas, ese orden es el siguiente:
*
* 1º Cursos.
* 2º Módulos.
* 3º Alumnos.
* 4º Especialidades.
* 5º Profesores.
* 6º Matricula Módulos.
* 7º Catalogo Módulos.
* 8º Calificaciones.
*
* Siendo de mayor a menor las mayores las menos dependientes y las
* menores las más dependientes de otras.
*/

/*
* La tabla de Cursos posee los siguientes atributos: CODIGO_CURSO, NOMBRE_CURSO
*/
INSERT INTO CURSOS (CODIGO_CURSO, NOMBRE_CURSO)
VALUES 
('DAW1', 'Desarrollo de Aplicaciones Web - Primer año'),
('DAW2', 'Desarrollo de Aplicaciones Web - Segundo año');

/*
* La tabla de Módulos posee los siguientes atributos: CODIGO_MODULO, NOMBRE_MODULO, HORAS_TOTALES, HORAS_SEMANALES
*/
INSERT INTO MODULOS (CODIGO_MODULO, NOMBRE_MODULO, HORAS_TOTALES, HORAS_SEMANALES)
VALUES 
('0484', 'Bases de datos', 205, 6),
('0487', 'Entornos de Desarrollo', 90, 3),
('0617', 'Formación Orientativa Laboral', 90, 3),
('0373', 'Lenguaje de Marcas', 140, 4),
('0485', 'Programación', 270, 8),
('0483', 'Sistemas Informaticos', 215, 6),
('0612', 'Desarrollo Web en Entorno Cliente', 115, 6),
('0613', 'Desarrollo Web en Entorno Servidor', 180, 9),
('0614', 'Despliegue de Aplicaciones Web', 85, 4),
('0615', 'Diseño de Interfaces Web', 115, 6),
('0618', 'Empresa e iniciativa emprendedora', 65, 3),
('CM14', 'Ingles tecnico para grado superior', 40, 2),
('PDAW', 'Proyecto de Desarrollo de Aplicaciones Web', 30, NULL),
('MFCT', 'Formación en Centros de Trabajo', 370, NULL);

/* 
* La tabla de Alumnos posee los siguientes atributos: NIA_ALUMNO,
* DNI_ALUMNO, GENERO_ALUMNO, FECHA_NACIMIENTO, NOMBRE_ALUMNO,
* APELLIDO1_ALUMNO, APELLIDO2_ALUMNO, DIRECCION_ALUMNO
*/
INSERT INTO ALUMNOS (NIA_ALUMNO, DNI_ALUMNO, GENERO_ALUMNO, FECHA_NACIMIENTO, NOMBRE_ALUMNO, APELLIDO1_ALUMNO, APELLIDO2_ALUMNO, DIRECCION_ALUMNO)
VALUES
('123456789A', '12345678A', 'M', '1997-07-10', 'Adrian', 'Yin', 'Brown', 'Avenida Sukasa 69, 28123'),
('987654321B', '87654321B', 'M', '1997-11-27', 'Adrian', 'Yang', 'Sugar', 'Calle Kasadeadri 0, 28321'),
('246813579C', '98765432C', 'M', '1996-09-22', 'Agustín', 'Papi', 'Piña', 'Carretera Motomami 14, 28231'),
('135792468D', '23456789D', '?', '1995-12-04', 'Aitor', 'Tilla', 'Consebolla', 'Callejon Sucio 1, 28976'),
('864207913E', '34567890E', 'M', '1995-08-15', 'Alberto', 'Paquetote', 'Chulito', 'Autovia Lokura 8, 28765'),
('579314682F', '45678901F', 'M', '1996-06-28', 'Andrew', 'Bellaco', 'Guiño', 'Bulevar Delosbellos 12, 28345'),
('314682975G', '56789012G', 'M', '1995-06-09', 'Carlos', 'Aesthetic', 'Vintage', 'Calle Peatonal Devadvoys 3, 28567'),
('753190246H', '67890123H', 'M', '1995-04-03', 'Carlos', 'Codillo', 'Tierno', 'Camino Delavida 29, 28432'),
('902468135I', '78901234I', 'F', '1995-12-18', 'Cristina', 'Berijot', 'Gerl', 'Corredera Latina 36, 28584'),
('468135792J', '89012345J', 'M', '1995-07-19', 'Eduardo', 'Dickson', 'Mamasanga', 'Paseo Delamanouwu 68, 28564'),
('246897531K', '90123456K', 'M', '1996-04-25', 'Guido', 'Asado', 'Welldone', 'Tunel Carpiano 112, 28564'),
('975318624L', '01234567L', 'M', '1995-11-15', 'Ismael', 'McFlurry', 'Elado', 'Parque Perrosuelto 2, 28534'),
('318624970M', '21098765M', 'F', '1996-01-17', 'Laura', 'Jitana', 'Malagueña', 'Puente Delosbesos 4, 28573'),
('624970318N', '32109876N', '?', '1995-07-28', 'Marta', 'Hairof', 'Failla', 'Costanilla Pseudobooleana 12, 28546'),
('970318624O', '43210987O', 'M', '1996-09-05', 'Max', 'Fornait', 'Valorante', 'Autovia Mariocar 64, 28064'),
('318624970P', '54321098P', 'M', '1996-05-10', 'Miguel', 'Don', 'Watch', 'Via Verde Vivemuylejos 74, 28999'),
('624970318Q', '65432109Q', 'F', '1995-07-03', 'Paula', 'Purple', 'Nails', 'Urbanización Wasupjoimi 88, 28765'),
('970318624R', '76543210R', 'M', '1995-11-09', 'Quique', 'Portuano', 'Milano', 'Paseo Maritimo Aguademadri 45, 28357'),
('318624970S', '87654321S', 'M', '1995-05-01', 'Roberto', 'Stolisnaya', 'Bocka', 'Avenida Cargoesrunrun 22, 28954'),
('624970318T', '98765432T', 'M', '1995-12-23', 'Rodrigo', 'Esquibidi', 'Toilet', 'Calle Peatonal Tefetelol 56, 28472'),
('970318624U', '09876543U', 'F', '1995-12-07', 'Tati', 'Mileidi', 'Mailof', 'Callejon Delasmamis 36, 28479'),
('318624970V', '10987654V', '?', '1996-02-18', 'Victor', 'Pelotade', 'Basket', 'Parque Fuboivasquec 67, 28528');

/*
* La tabla de Especialidades posee los siguientes atributos: ESPECIALIDAD, NOMBRE_ESPECIALIDAD, DESCRIPCION_ESPECIALIDAD
*/
INSERT INTO ESPECIALIDADES (ESPECIALIDAD, NOMBRE_ESPECIALIDAD, DESCRIPCION_ESPECIALIDAD)
VALUES 
('INF', 'Informatica', 'Agrupa todos los módulos de Bases de Datos, Entornos de Desarrollo, Programación, Lenguaje de Marcas y Sistemas Informaticos'),
('FOL', 'Formacón Orientativa Laboral', 'Módulo especifico de Formación Orientativa Laboral'),
('ING', 'Ingles', 'Módulo especifico de la Comunidad de Madrid, Inglés tecnico para Grados Superiores'),
('EIE', 'Empresa e Iniciatvia Emprendedora', 'Módulo especifico de Empresa e Iniciativa Emprendedora');

/*
* La tabla de Profesores posee los siguientes atributos: CODIGO_PROFESOR, NOMBRE_PROFESOR, APELLIDO_PROFESOR, ESPECIALIDAD
*/
INSERT INTO PROFESORES (CODIGO_PROFESOR, NOMBRE_PROFESOR, APELLIDO_PROFESOR, ESPECIALIDAD)
VALUES 
('PR001', 'Alfonso', 'Datouss', 'INF'),
('PR002', 'Mercedes', 'Entornes', 'INF'),
('PR003', 'Jaime', 'Essepciones', 'INF'),
('PR004', 'Julia', 'Ekisemele', 'INF'),
('PR005', 'Manuel', 'Rekursos', 'FOL'),
('PR006', 'Emilio', 'Makina', 'INF');

/*
* Insertamos las matrículas de cada alumno en la tabla MATRICULAS_MODULOS, 
* excluyendo a los que no toman ciertos módulos y ajustando los módulos que solo toman algunos.
*/

-- Alumno: Adrian Yin Brown (Excluido de FOL)
INSERT INTO MATRICULAS_MODULOS 
  (CODIGO_MATRICULA, NIA_ALUMNO, CODIGO_MODULO, CODIGO_PROFESOR, FECHA_MATRICULA, CONVOCATORIAS_RESTANTES, FALTAS_ALUMNO)
VALUES 
  ('MT001', '123456789A', '0484', 'PR001', '2023-09-01', 2, 1), -- Bases de datos
  ('MT002', '123456789A', '0487', 'PR002', '2023-09-02', 2, 3), -- Entornos de Desarrollo
  ('MT003', '123456789A', '0373', 'PR004', '2023-09-03', 2, 7), -- Lenguaje de Marcas
  ('MT004', '123456789A', '0485', 'PR005', '2023-09-04', 2, 9), -- Programación
  ('MT005', '123456789A', '0483', 'PR006', '2023-09-05', 2, 11); -- Sistemas Informáticos

-- Alumno: Adrian Yang Sugar (Excluido de FOL)
INSERT INTO MATRICULAS_MODULOS 
  (CODIGO_MATRICULA, NIA_ALUMNO, CODIGO_MODULO, CODIGO_PROFESOR, FECHA_MATRICULA, CONVOCATORIAS_RESTANTES, FALTAS_ALUMNO)
VALUES 
  ('MT006', '987654321B', '0484', 'PR001', '2023-09-06', 2, 1), -- Bases de datos
  ('MT007', '987654321B', '0487', 'PR002', '2023-09-07', 2, 3), -- Entornos de Desarrollo
  ('MT008', '987654321B', '0373', 'PR004', '2023-09-08', 2, 7), -- Lenguaje de Marcas
  ('MT009', '987654321B', '0485', 'PR005', '2023-09-09', 2, 9), -- Programación
  ('MT010', '987654321B', '0483', 'PR006', '2023-09-10', 2, 11); -- Sistemas Informáticos

-- Alumno: Agustín Papi Piña (Cursa todos los Módulos)
INSERT INTO MATRICULAS_MODULOS 
  (CODIGO_MATRICULA, NIA_ALUMNO, CODIGO_MODULO, CODIGO_PROFESOR, FECHA_MATRICULA, CONVOCATORIAS_RESTANTES, FALTAS_ALUMNO)
VALUES 
  ('MT011', '246813579C', '0484', 'PR001', '2023-09-11', 2, 0), -- Bases de datos
  ('MT012', '246813579C', '0487', 'PR002', '2023-09-12', 2, 0), -- Entornos de Desarrollo
  ('MT013', '246813579C', '0373', 'PR004', '2023-09-13', 2, 0), -- Lenguaje de Marcas
  ('MT014', '246813579C', '0485', 'PR005', '2023-09-14', 2, 0), -- Programación
  ('MT015', '246813579C', '0483', 'PR006', '2023-09-15', 2, 0),
  ('MT016', '246813579C', '0486', 'PR007', '2023-09-16', 2, 0); -- Formación Orientativa Laboral

-- Alumno: Aitor Tilla Consebolla (Excluido de FOL)
INSERT INTO MATRICULAS_MODULOS 
  (CODIGO_MATRICULA, NIA_ALUMNO, CODIGO_MODULO, CODIGO_PROFESOR, FECHA_MATRICULA, CONVOCATORIAS_RESTANTES, FALTAS_ALUMNO)
VALUES 
  ('MT021', '135792468D', '0484', 'PR001', '2023-09-17', 2, 0), -- Bases de datos
  ('MT022', '135792468D', '0487', 'PR002', '2023-09-18', 2, 0), -- Entornos de Desarrollo
  ('MT023', '135792468D', '0373', 'PR004', '2023-09-19', 2, 0), -- Lenguaje de Marcas
  ('MT024', '135792468D', '0485', 'PR005', '2023-09-20', 2, 0), -- Programación
  ('MT025', '135792468D', '0483', 'PR006', '2023-09-21', 2, 0); -- Sistemas Informáticos

-- Alumno: Alberto Paquetote Chulito (Excluido de FOL)
INSERT INTO MATRICULAS_MODULOS 
  (CODIGO_MATRICULA, NIA_ALUMNO, CODIGO_MODULO, CODIGO_PROFESOR, FECHA_MATRICULA, CONVOCATORIAS_RESTANTES, FALTAS_ALUMNO)
VALUES 
  ('MT026', '864207913E', '0484', 'PR001', '2023-09-22', 2, 0), -- Bases de datos
  ('MT027', '864207913E', '0485', 'PR005', '2023-09-23', 2, 0); -- Programación

-- Alumno: Andrew Bellaco Guiño (Cursa todos los Módulos)
INSERT INTO MATRICULAS_MODULOS 
  (CODIGO_MATRICULA, NIA_ALUMNO, CODIGO_MODULO, CODIGO_PROFESOR, FECHA_MATRICULA, CONVOCATORIAS_RESTANTES, FALTAS_ALUMNO)
VALUES 
  ('MT031', '579314682F', '0484', 'PR001', '2023-09-24', 2, 0), -- Bases de datos
  ('MT032', '579314682F', '0487', 'PR002', '2023-09-25', 2, 0), -- Entornos de Desarrollo
  ('MT033', '579314682F', '0617', 'PR003', '2023-09-26', 2, 0), -- Formación Orientativa Laboral
  ('MT034', '579314682F', '0373', 'PR004', '2023-09-27', 2, 0), -- Lenguaje de Marcas
  ('MT035', '579314682F', '0485', 'PR005', '2023-09-28', 2, 0), -- Programación
  ('MT036', '579314682F', '0483', 'PR006', '2023-09-29', 2, 0); -- Sistemas Informáticos

-- Alumno: Carlos Aesthetic Vintage (Excluido de FOL)
INSERT INTO MATRICULAS_MODULOS 
  (CODIGO_MATRICULA, NIA_ALUMNO, CODIGO_MODULO, CODIGO_PROFESOR, FECHA_MATRICULA, CONVOCATORIAS_RESTANTES, FALTAS_ALUMNO)
VALUES 
  ('MT031', '314682975G', '0484', 'PR001', '2023-10-01', 2, 0), -- Bases de datos
  ('MT032', '314682975G', '0485', 'PR005', '2023-10-02', 2, 0), -- Programación
  ('MT033', '314682975G', '0483', 'PR006', '2023-10-03', 2, 0), -- Sistemas Informáticos
  ('MT035', '314682975G', '0373', 'PR004', '2023-10-04', 2, 0); -- Lenguaje de Marcas

-- Alumno: Carlos Codillo Tierno (Excluido de FOL)
INSERT INTO MATRICULAS_MODULOS 
  (CODIGO_MATRICULA, NIA_ALUMNO, CODIGO_MODULO, CODIGO_PROFESOR, FECHA_MATRICULA, CONVOCATORIAS_RESTANTES, FALTAS_ALUMNO)
VALUES 
  ('MT036', '753190246H', '0484', 'PR001', '2023-10-05', 2, 0), -- Bases de datos
  ('MT037', '753190246H', '0485', 'PR005', '2023-10-06', 2, 0), -- Programación
  ('MT038', '753190246H', '0483', 'PR006', '2023-10-07', 2, 0), -- Sistemas Informáticos
  ('MT040', '753190246H', '0373', 'PR004', '2023-10-08', 2, 0); -- Lenguaje de Marcas

-- Alumno: Cristina Berijot Gerl (Solo tiene dos módulos)
INSERT INTO MATRICULAS_MODULOS 
  (CODIGO_MATRICULA, NIA_ALUMNO, CODIGO_MODULO, CODIGO_PROFESOR, FECHA_MATRICULA, CONVOCATORIAS_RESTANTES, FALTAS_ALUMNO)
VALUES 
  ('MT041', '902468135I', '0485', 'PR005', '2023-10-09', 2, 0), -- Programación
  ('MT042', '902468135I', '0483', 'PR006', '2023-10-10', 2, 0); -- Sistemas Informáticos

-- Alumno: Eduardo Dickson Mamasanga (Cursa todos los Módulos)
INSERT INTO MATRICULAS_MODULOS 
  (CODIGO_MATRICULA, NIA_ALUMNO, CODIGO_MODULO, CODIGO_PROFESOR, FECHA_MATRICULA, CONVOCATORIAS_RESTANTES, FALTAS_ALUMNO)
VALUES 
  ('MT011', '468135792J', '0482', 'PR004', '2023-10-11', 2, 21), -- Lenguaje de Marcas
  ('MT012', '468135792J', '0483', 'PR006', '2023-10-12', 2, 23), -- Sistemas Informáticos
  ('MT013', '468135792J', '0484', 'PR001', '2023-10-13', 2, 25), -- Bases de Datos
  ('MT014', '468135792J', '0485', 'PR005', '2023-10-14', 2, 27), -- Programación
  ('MT015', '468135792J', '0486', 'PR007', '2023-10-15', 2, 29), -- Entornos de Desarrollo
  ('MT016', '468135792J', '0617', 'PR003', '2023-10-16', 2, 31); -- Formación Orientativa Laboral

-- Alumno: Guido Asado Welldone (Cursa todos los Módulos)
INSERT INTO MATRICULAS_MODULOS 
  (CODIGO_MATRICULA, NIA_ALUMNO, CODIGO_MODULO, CODIGO_PROFESOR, FECHA_MATRICULA, CONVOCATORIAS_RESTANTES, FALTAS_ALUMNO)
VALUES 
  ('MT017', '246897531K', '0481', 'PR008', '2023-10-17', 2, 21), -- Sistemas Informáticos
  ('MT018', '246897531K', '0482', 'PR004', '2023-10-18', 2, 23), -- Lenguaje de Marcas
  ('MT019', '246897531K', '0483', 'PR006', '2023-10-19', 2, 25), -- Bases de Datos
  ('MT020', '246897531K', '0484', 'PR001', '2023-10-20', 2, 27), -- Programación
  ('MT021', '246897531K', '0485', 'PR005', '2023-10-21', 2, 29), -- Entornos de Desarrollo
  ('MT022', '246897531K', '0617', 'PR003', '2023-10-22', 2, 31); -- Formación Orientativa Laboral

-- Alumno: Ismael McFlurry Elado (Cursa todos los Módulos)
INSERT INTO MATRICULAS_MODULOS 
  (CODIGO_MATRICULA, NIA_ALUMNO, CODIGO_MODULO, CODIGO_PROFESOR, FECHA_MATRICULA, CONVOCATORIAS_RESTANTES, FALTAS_ALUMNO)
VALUES 
  ('MT023', '975318624L', '0481', 'PR008', '2023-10-23', 2, 21), -- Sistemas Informáticos
  ('MT024', '975318624L', '0482', 'PR004', '2023-10-24', 2, 23), -- Lenguaje de Marcas
  ('MT025', '975318624L', '0483', 'PR006', '2023-10-25', 2, 25), -- Bases de Datos
  ('MT026', '975318624L', '0484', 'PR001', '2023-10-26', 2, 27), -- Programación
  ('MT027', '975318624L', '0485', 'PR005', '2023-10-27', 2, 29), -- Entornos de Desarrollo
  ('MT028', '975318624L', '0617', 'PR003', '2023-10-28', 2, 31); -- Formación Orientativa Laboral

-- Alumno: Laura Jitana Malagueña (Cursa todos los Módulos)
INSERT INTO MATRICULAS_MODULOS 
  (CODIGO_MATRICULA, NIA_ALUMNO, CODIGO_MODULO, CODIGO_PROFESOR, FECHA_MATRICULA, CONVOCATORIAS_RESTANTES, FALTAS_ALUMNO)
VALUES 
  ('MT029', '318624970M', '0481', 'PR008', '2023-10-29', 2, 21), -- Sistemas Informáticos
  ('MT030', '318624970M', '0482', 'PR004', '2023-10-30', 2, 23), -- Lenguaje de Marcas
  ('MT031', '318624970M', '0483', 'PR006', '2023-10-31', 2, 25), -- Bases de Datos
  ('MT032', '318624970M', '0484', 'PR001', '2023-11-01', 2, 27), -- Programación
  ('MT033', '318624970M', '0485', 'PR005', '2023-11-02', 2, 29), -- Entornos de Desarrollo
  ('MT034', '318624970M', '0617', 'PR003', '2023-11-03', 2, 31); -- Formación Orientativa Laboral

-- Alumno: Marta Hairof Failla (Cursa todos los Módulos)
INSERT INTO MATRICULAS_MODULOS 
  (CODIGO_MATRICULA, NIA_ALUMNO, CODIGO_MODULO, CODIGO_PROFESOR, FECHA_MATRICULA, CONVOCATORIAS_RESTANTES, FALTAS_ALUMNO)
VALUES 
  ('MT035', '624970318N', '0481', 'PR008', '2023-11-04', 2, 21), -- Sistemas Informáticos
  ('MT036', '624970318N', '0482', 'PR004', '2023-11-05', 2, 23), -- Lenguaje de Marcas
  ('MT037', '624970318N', '0483', 'PR006', '2023-11-06', 2, 25), -- Bases de Datos
  ('MT038', '624970318N', '0484', 'PR001', '2023-11-07', 2, 27), -- Programación
  ('MT039', '624970318N', '0485', 'PR005', '2023-11-08', 2, 29), -- Entornos de Desarrollo
  ('MT040', '624970318N', '0617', 'PR003', '2023-11-09', 2, 31); -- Formación Orientativa Laboral

-- Alumno: Max Fornait Valorante (Excluido de FOL)
INSERT INTO MATRICULAS_MODULOS 
  (CODIGO_MATRICULA, NIA_ALUMNO, CODIGO_MODULO, CODIGO_PROFESOR, FECHA_MATRICULA, CONVOCATORIAS_RESTANTES, FALTAS_ALUMNO)
VALUES 
  ('MT041', '970318624O', '0481', 'PR008', '2023-11-10', 2, 21), -- Sistemas Informáticos
  ('MT042', '970318624O', '0482', 'PR004', '2023-11-11', 2, 23), -- Lenguaje de Marcas
  ('MT043', '970318624O', '0483', 'PR006', '2023-11-12', 2, 25), -- Bases de Datos
  ('MT044', '970318624O', '0484', 'PR001', '2023-11-13', 2, 27), -- Programación
  ('MT045', '970318624O', '0485', 'PR005', '2023-11-14', 2, 29); -- Entornos de Desarrollo

-- Alumno: Miguel Don Watch (Excluido de FOL)
INSERT INTO MATRICULAS_MODULOS 
  (CODIGO_MATRICULA, NIA_ALUMNO, CODIGO_MODULO, CODIGO_PROFESOR, FECHA_MATRICULA, CONVOCATORIAS_RESTANTES, FALTAS_ALUMNO)
VALUES 
  ('MT046', '318624970P', '0481', 'PR008', '2023-11-15', 2, 31), -- Sistemas Informáticos
  ('MT047', '318624970P', '0482', 'PR004', '2023-11-16', 2, 33), -- Lenguaje de Marcas
  ('MT048', '318624970P', '0483', 'PR006', '2023-11-17', 2, 35), -- Bases de Datos
  ('MT049', '318624970P', '0484', 'PR001', '2023-11-18', 2, 37), -- Programación
  ('MT050', '318624970P', '0485', 'PR005', '2023-11-19', 2, 39); -- Entornos de Desarrollo

-- Alumno: Paula Purple Nails (Cursa todos los Módulos)
INSERT INTO MATRICULAS_MODULOS 
  (CODIGO_MATRICULA, NIA_ALUMNO, CODIGO_MODULO, CODIGO_PROFESOR, FECHA_MATRICULA, CONVOCATORIAS_RESTANTES, FALTAS_ALUMNO)
VALUES 
  ('MT056', '624970318Q', '0481', 'PR008', '2023-11-20', 2, 51), -- Sistemas Informáticos
  ('MT057', '624970318Q', '0482', 'PR004', '2023-11-21', 2, 53), -- Lenguaje de Marcas
  ('MT058', '624970318Q', '0483', 'PR006', '2023-11-22', 2, 55), -- Bases de Datos
  ('MT059', '624970318Q', '0484', 'PR001', '2023-11-23', 2, 57), -- Programación
  ('MT060', '624970318Q', '0485', 'PR005', '2023-11-24', 2, 59), -- Entornos de Desarrollo
  ('MT061', '624970318Q', '0617', 'PR003', '2023-11-25', 2, 61); -- Formación Orientativa Laboral

-- Alumno: Quique Portuano Milano (Excluido de FOL)
INSERT INTO MATRICULAS_MODULOS 
  (CODIGO_MATRICULA, NIA_ALUMNO, CODIGO_MODULO, CODIGO_PROFESOR, FECHA_MATRICULA, CONVOCATORIAS_RESTANTES, FALTAS_ALUMNO)
VALUES 
  ('MT051', '970318624R', '0481', 'PR008', '2023-11-26', 2, 41), -- Sistemas Informáticos
  ('MT052', '970318624R', '0482', 'PR004', '2023-11-27', 2, 43), -- Lenguaje de Marcas
  ('MT053', '970318624R', '0483', 'PR006', '2023-11-28', 2, 45), -- Bases de Datos
  ('MT054', '970318624R', '0484', 'PR001', '2023-11-29', 2, 47), -- Programación
  ('MT055', '970318624R', '0485', 'PR005', '2023-11-30', 2, 49); -- Entornos de Desarrollo

-- Alumno: Roberto Stolisnaya (Excluido de FOL)
INSERT INTO MATRICULAS_MODULOS 
  (CODIGO_MATRICULA, NIA_ALUMNO, CODIGO_MODULO, CODIGO_PROFESOR, FECHA_MATRICULA, CONVOCATORIAS_RESTANTES, FALTAS_ALUMNO)
VALUES 
  ('MT025', '87654321S', '0484', 'PR001', '2023-12-01', 2, 0), -- Bases de Datos
  ('MT026', '87654321S', '0487', 'PR002', '2023-12-02', 2, 0), -- Entornos de Desarrollo
  ('MT027', '87654321S', '0373', 'PR004', '2023-12-03', 2, 0), -- Lenguaje de Marcas
  ('MT028', '87654321S', '0485', 'PR005', '2023-12-04', 2, 0), -- Programación
  ('MT029', '87654321S', '0483', 'PR006', '2023-12-05', 2, 0); -- Sistemas Informáticos

-- Alumno: Rodrigo Esquibidi (Excluido de FOL)
INSERT INTO MATRICULAS_MODULOS 
  (CODIGO_MATRICULA, NIA_ALUMNO, CODIGO_MODULO, CODIGO_PROFESOR, FECHA_MATRICULA, CONVOCATORIAS_RESTANTES, FALTAS_ALUMNO)
VALUES 
  ('MT031', '98765432T', '0484', 'PR001', '2023-12-06', 2, 0), -- Bases de Datos
  ('MT032', '98765432T', '0487', 'PR002', '2023-12-07', 2, 0), -- Entornos de Desarrollo
  ('MT034', '98765432T', '0373', 'PR004', '2023-12-08', 2, 0), -- Lenguaje de Marcas
  ('MT035', '98765432T', '0485', 'PR005', '2023-12-09', 2, 0), -- Programación
  ('MT036', '98765432T', '0483', 'PR006', '2023-12-10', 2, 0); -- Sistemas Informáticos

-- Alumno: Tati Mileidi (Excluido de FOL)
INSERT INTO MATRICULAS_MODULOS 
  (CODIGO_MATRICULA, NIA_ALUMNO, CODIGO_MODULO, CODIGO_PROFESOR, FECHA_MATRICULA, CONVOCATORIAS_RESTANTES, FALTAS_ALUMNO)
VALUES 
  ('MT037', '09876543U', '0484', 'PR001', '2023-12-11', 2, 0), -- Bases de Datos
  ('MT038', '09876543U', '0487', 'PR002', '2023-12-12', 2, 0), -- Entornos de Desarrollo
  ('MT040', '09876543U', '0373', 'PR004', '2023-12-13', 2, 0), -- Lenguaje de Marcas
  ('MT041', '09876543U', '0485', 'PR005', '2023-12-14', 2, 0), -- Programación
  ('MT042', '09876543U', '0483', 'PR006', '2023-12-15', 2, 0); -- Sistemas Informáticos

-- Alumno: Victor Pelotade (Excluido de FOL)
INSERT INTO MATRICULAS_MODULOS 
  (CODIGO_MATRICULA, NIA_ALUMNO, CODIGO_MODULO, CODIGO_PROFESOR, FECHA_MATRICULA, CONVOCATORIAS_RESTANTES, FALTAS_ALUMNO)
VALUES 
  ('MT023', '10987654V', '0484', 'PR001', '2023-12-16', 2, 0), -- Bases de Datos
  ('MT023', '10987654V', '0487', 'PR002', '2023-12-17', 2, 0), -- Entornos de Desarrollo
  ('MT023', '10987654V', '0373', 'PR004', '2023-12-18', 2, 0), -- Lenguaje de Marcas
  ('MT023', '10987654V', '0485', 'PR005', '2023-12-19', 2, 0), -- Programación
  ('MT023', '10987654V', '0483', 'PR006', '2023-12-20', 2, 0); -- Sistemas Informáticos

-- Asignación de módulos al primer año (DAW1)
INSERT INTO CATALOGO_MODULOS (CODIGO_CURSO, CODIGO_MODULO)
VALUES 
  ('DAW1', '0484'), -- Bases de datos
  ('DAW1', '0487'), -- Entornos de Desarrollo
  ('DAW1', '0617'), -- Formación Orientativa Laboral
  ('DAW1', '0373'), -- Lenguaje de Marcas
  ('DAW1', '0485'), -- Programación
  ('DAW1', '0483'); -- Sistemas Informáticos

-- Asignación de módulos al segundo año (DAW2)
INSERT INTO CATALOGO_MODULOS (CODIGO_CURSO, CODIGO_MODULO)
VALUES 
  ('DAW2', '0612'), -- Desarrollo Web en Entorno Cliente
  ('DAW2', '0613'), -- Desarrollo Web en Entorno Servidor
  ('DAW2', '0614'), -- Despliegue de Aplicaciones Web
  ('DAW2', '0615'), -- Diseño de Interfaces Web
  ('DAW2', '0618'), -- Empresa e iniciativa emprendedora
  ('DAW2', 'CM14'), -- Ingles tecnico para grado superior
  ('DAW2', 'PDAW'), -- Proyecto de Desarrollo de Aplicaciones Web
  ('DAW2', 'MFCT'); -- Formación en Centros de Trabajo

-- Calificaciones de: Adrian Yin Brown (5 módulos, excluido de FOL)
INSERT INTO CALIFICACIONES (CODIGO_MODULO, NIA_ALUMNO, CODIGO_PROFESOR, CODIGO_MATRICULA, PRIMERA_EVALUACION, SEGUNDA_EVALUACION, ORDINARIA, EXTRAORDINARIA)
VALUES 
  ('0484', '123456789A', 'PR001', 'MT001', 7, 5, 9, -1), -- Calificaciones de Bases de Datos
  ('0487', '123456789A', 'PR002', 'MT002', 6, 4, 8, -1), -- Calificaciones de Entornos de Desarrollo
  ('0373', '123456789A', 'PR004', 'MT003', 8, 6, 10, -1), -- Calificaciones de Lenguaje de Marcas
  ('0485', '123456789A', 'PR005', 'MT004', 9, 5, 7, -1), -- Calificaciones de Programación
  ('0483', '123456789A', 'PR006', 'MT005', 4, 2, 6, -1); -- Calificaciones de Sistemas Informáticos

-- Calificaciones de: Adrian Yang Sugar (5 módulos, excluido de FOL)
INSERT INTO CALIFICACIONES (CODIGO_MODULO, NIA_ALUMNO, CODIGO_PROFESOR, CODIGO_MATRICULA, PRIMERA_EVALUACION, SEGUNDA_EVALUACION, ORDINARIA, EXTRAORDINARIA)
VALUES 
  ('0484', '987654321B', 'PR001', 'MT006', 6, 7, 8, -1), -- Calificaciones de Bases de Datos
  ('0487', '987654321B', 'PR002', 'MT007', 5, 5, 9, -1), -- Calificaciones de Entornos de Desarrollo
  ('0373', '987654321B', 'PR004', 'MT008', 2, 3, 4, -1), -- Calificaciones de Lenguaje de Marcas
  ('0485', '987654321B', 'PR005', 'MT009', 7, 6, 8, -1), -- Calificaciones de Programación
  ('0483', '987654321B', 'PR006', 'MT010', 9, 10, 10, -1); -- Calificaciones de Sistemas Informáticos

-- Calificaciones de: Agustín Papi Piña (6 módulos)
INSERT INTO CALIFICACIONES (CODIGO_MODULO, NIA_ALUMNO, CODIGO_PROFESOR, CODIGO_MATRICULA, PRIMERA_EVALUACION, SEGUNDA_EVALUACION, ORDINARIA, EXTRAORDINARIA)
VALUES 
  ('0484', '246813579C', 'PR001', 'MT011', 8, 7, 6, -1), -- Calificaciones de Bases de Datos
  ('0487', '246813579C', 'PR002', 'MT012', 5, 4, 3, -1), -- Calificaciones de Entornos de Desarrollo
  ('0617', '246813579C', 'PR007', 'MT016', 2, 3, 4, -1), -- Calificaciones de Formación Orientativa Laboral
  ('0373', '246813579C', 'PR004', 'MT013', 6, 5, 4, -1), -- Calificaciones de Lenguaje de Marcas
  ('0485', '246813579C', 'PR005', 'MT014', 7, 8, 9, -1), -- Calificaciones de Programación
  ('0483', '246813579C', 'PR006', 'MT015', 10, 9, 8, -1); -- Calificaciones de Sistemas Informáticos

-- Calificaciones de: Aitor Tilla Consebolla (5 módulos, excluido de FOL)
INSERT INTO CALIFICACIONES (CODIGO_MODULO, NIA_ALUMNO, CODIGO_PROFESOR, CODIGO_MATRICULA, PRIMERA_EVALUACION, SEGUNDA_EVALUACION, ORDINARIA, EXTRAORDINARIA)
VALUES 
  ('0484', '135792468D', 'PR001', 'MT021', 4, 6, 5, -1), -- Calificaciones de Bases de Datos
  ('0487', '135792468D', 'PR002', 'MT022', 5, 8, 7, -1), -- Calificaciones de Entornos de Desarrollo
  ('0373', '135792468D', 'PR004', 'MT023', 6, 7, 8, -1), -- Calificaciones de Lenguaje de Marcas
  ('0485', '135792468D', 'PR005', 'MT024', 3, 4, 5, -1), -- Calificaciones de Programación
  ('0483', '135792468D', 'PR006', 'MT025', 9, 8, 7, -1); -- Calificaciones de Sistemas Informáticos

-- Calificaciones de: Alberto Paquetote Chulito (2 módulos)
INSERT INTO CALIFICACIONES (CODIGO_MODULO, NIA_ALUMNO, CODIGO_PROFESOR, CODIGO_MATRICULA, PRIMERA_EVALUACION, SEGUNDA_EVALUACION, ORDINARIA, EXTRAORDINARIA)
VALUES 
  ('0484', '864207913E', 'PR001', 'MT026', 8, 7, 6, -1), -- Calificaciones de Bases de Datos
  ('0485', '864207913E', 'PR005', 'MT027', 5, 4, 3, -1); -- Calificaciones de Programación

-- Calificaciones de: Andrew Bellaco Guiño (6 módulos)
INSERT INTO CALIFICACIONES (CODIGO_MODULO, NIA_ALUMNO, CODIGO_PROFESOR, CODIGO_MATRICULA, PRIMERA_EVALUACION, SEGUNDA_EVALUACION, ORDINARIA, EXTRAORDINARIA)
VALUES 
  ('0484', '579314682F', 'PR001', 'MT031', 6, 5, 4, -1), -- Calificaciones de Bases de Datos
  ('0487', '579314682F', 'PR002', 'MT032', 7, 6, 5, -1), -- Calificaciones de Entornos de Desarrollo
  ('0617', '579314682F', 'PR003', 'MT033', 8, 7, 6, -1), -- Calificaciones de Formación Orientativa Laboral
  ('0373', '579314682F', 'PR004', 'MT034', 5, 4, 3, -1), -- Calificaciones de Lenguaje de Marcas
  ('0485', '579314682F', 'PR005', 'MT035', 10, 9, 8, -1), -- Calificaciones de Programación
  ('0483', '579314682F', 'PR006', 'MT036', 2, 3, 4, -1); -- Calificaciones de Sistemas Informáticos

-- Calificaciones de: Carlos Aesthetic Vintage (5 módulos, excluido de FOL)
INSERT INTO CALIFICACIONES (CODIGO_MODULO, NIA_ALUMNO, CODIGO_PROFESOR, CODIGO_MATRICULA, PRIMERA_EVALUACION, SEGUNDA_EVALUACION, ORDINARIA, EXTRAORDINARIA)
VALUES 
  ('0484', '314682975G', 'PR001', 'MT031', 5, 7, 6, -1), -- Calificaciones de Bases de Datos
  ('0487', '314682975G', 'PR002', 'MT032', 8, 9, 10, -1), -- Calificaciones de Entornos de Desarrollo
  ('0373', '314682975G', 'PR004', 'MT035', 4, 5, 3, -1), -- Calificaciones de Lenguaje de Marcas
  ('0485', '314682975G', 'PR005', 'MT033', 6, 7, 5, -1), -- Calificaciones de Programación
  ('0483', '314682975G', 'PR006', 'MT034', 9, 8, 10, -1); -- Calificaciones de Sistemas Informáticos

-- Calificaciones de: Carlos Codillo Tierno (5 módulos, excluido de FOL)
INSERT INTO CALIFICACIONES (CODIGO_MODULO, NIA_ALUMNO, CODIGO_PROFESOR, CODIGO_MATRICULA, PRIMERA_EVALUACION, SEGUNDA_EVALUACION, ORDINARIA, EXTRAORDINARIA)
VALUES 
  ('0484', '753190246H', 'PR001', 'MT036', 7, 6, 5, -1), -- Calificaciones de Bases de Datos
  ('0487', '753190246H', 'PR002', 'MT037', 6, 7, 8, -1), -- Calificaciones de Entornos de Desarrollo
  ('0373', '753190246H', 'PR004', 'MT040', 9, 8, 10, -1), -- Calificaciones de Lenguaje de Marcas
  ('0485', '753190246H', 'PR005', 'MT038', 4, 5, 3, -1), -- Calificaciones de Programación
  ('0483', '753190246H', 'PR006', 'MT039', 8, 9, 7, -1); -- Calificaciones de Sistemas Informáticos

-- Calificaciones de: Cristina Berijot Gerl (2 módulos)
INSERT INTO CALIFICACIONES (CODIGO_MODULO, NIA_ALUMNO, CODIGO_PROFESOR, CODIGO_MATRICULA, PRIMERA_EVALUACION, SEGUNDA_EVALUACION, ORDINARIA, EXTRAORDINARIA)
VALUES 
  ('0485', '902468135I', 'PR005', 'MT041', 8, 7, 9, -1), -- Calificaciones de Programación
  ('0483', '902468135I', 'PR006', 'MT042', 5, 4, 6, -1); -- Calificaciones de Sistemas Informáticos

-- Calificaciones de: Eduardo Dickson Mamasanga (6 módulos)
INSERT INTO CALIFICACIONES (CODIGO_MODULO, NIA_ALUMNO, CODIGO_PROFESOR, CODIGO_MATRICULA, PRIMERA_EVALUACION, SEGUNDA_EVALUACION, ORDINARIA, EXTRAORDINARIA)
VALUES 
  ('0482', '468135792J', 'PR004', 'MT011', 3, 4, 5, -1), -- Calificaciones de Lenguaje de Marcas
  ('0483', '468135792J', 'PR006', 'MT012', 6, 7, 8, -1), -- Calificaciones de Sistemas Informáticos
  ('0484', '468135792J', 'PR001', 'MT013', 9, 8, 10, -1), -- Calificaciones de Bases de Datos
  ('0485', '468135792J', 'PR005', 'MT014', 2, 3, 4, -1), -- Calificaciones de Programación
  ('0486', '468135792J', 'PR007', 'MT015', 5, 6, 7, -1), -- Calificaciones de Entornos de Desarrollo
  ('0617', '468135792J', 'PR003', 'MT016', 8, 9, 7, -1); -- Calificaciones de Formación Orientativa Laboral

-- Calificaciones de: Guido Asado Welldone (6 módulos)
INSERT INTO CALIFICACIONES (CODIGO_MODULO, NIA_ALUMNO, CODIGO_PROFESOR, CODIGO_MATRICULA, PRIMERA_EVALUACION, SEGUNDA_EVALUACION, ORDINARIA, EXTRAORDINARIA)
VALUES 
  ('0481', '246897531K', 'PR008', 'MT017', 7, 6, 5, -1), -- Calificaciones de Sistemas Informáticos
  ('0482', '246897531K', 'PR004', 'MT018', 8, 7, 6, -1), -- Calificaciones de Lenguaje de Marcas
  ('0483', '246897531K', 'PR006', 'MT019', 4, 5, 3, -1), -- Calificaciones de Bases de Datos
  ('0484', '246897531K', 'PR001', 'MT020', 6, 7, 8, -1), -- Calificaciones de Programación
  ('0485', '246897531K', 'PR005', 'MT021', 9, 8, 10, -1), -- Calificaciones de Entornos de Desarrollo
  ('0617', '246897531K', 'PR003', 'MT022', 2, 3, 4, -1); -- Calificaciones de Formación Orientativa Laboral

-- Calificaciones de: Ismael McFlurry Elado (6 módulos)
INSERT INTO CALIFICACIONES (CODIGO_MODULO, NIA_ALUMNO, CODIGO_PROFESOR, CODIGO_MATRICULA, PRIMERA_EVALUACION, SEGUNDA_EVALUACION, ORDINARIA, EXTRAORDINARIA)
VALUES 
  ('0481', '975318624L', 'PR008', 'MT023', 5, 4, 6, -1), -- Calificaciones de Sistemas Informáticos
  ('0482', '975318624L', 'PR004', 'MT024', 7, 8, 9, -1), -- Calificaciones de Lenguaje de Marcas
  ('0483', '975318624L', 'PR006', 'MT025', 3, 2, 4, -1), -- Calificaciones de Bases de Datos
  ('0484', '975318624L', 'PR001', 'MT026', 6, 5, 7, -1), -- Calificaciones de Programación
  ('0485', '975318624L', 'PR005', 'MT027', 8, 9, 10, -1), -- Calificaciones de Entornos de Desarrollo
  ('0617', '975318624L', 'PR003', 'MT028', 4, 3, 5, -1); -- Calificaciones de Formación Orientativa Laboral

-- Calificaciones de: Laura Jitana Malagueña (6 módulos)
INSERT INTO CALIFICACIONES (CODIGO_MODULO, NIA_ALUMNO, CODIGO_PROFESOR, CODIGO_MATRICULA, PRIMERA_EVALUACION, SEGUNDA_EVALUACION, ORDINARIA, EXTRAORDINARIA)
VALUES 
  ('0481', '318624970M', 'PR008', 'MT029', 6, 5, 4, -1), -- Calificaciones de Sistemas Informáticos
  ('0482', '318624970M', 'PR004', 'MT030', 8, 7, 9, -1), -- Calificaciones de Lenguaje de Marcas
  ('0483', '318624970M', 'PR006', 'MT031', 5, 6, 7, -1), -- Calificaciones de Bases de Datos
  ('0484', '318624970M', 'PR001', 'MT032', 9, 8, 10, -1), -- Calificaciones de Programación
  ('0485', '318624970M', 'PR005', 'MT033', 3, 4, 5, -1), -- Calificaciones de Entornos de Desarrollo
  ('0617', '318624970M', 'PR003', 'MT034', 7, 6, 8, -1); -- Calificaciones de Formación Orientativa Laboral

-- Calificaciones de: Marta Hairof Failla (6 módulos)
INSERT INTO CALIFICACIONES (CODIGO_MODULO, NIA_ALUMNO, CODIGO_PROFESOR, CODIGO_MATRICULA, PRIMERA_EVALUACION, SEGUNDA_EVALUACION, ORDINARIA, EXTRAORDINARIA)
VALUES 
  ('0481', '624970318N', 'PR008', 'MT035', 4, 5, 3, -1), -- Calificaciones de Sistemas Informáticos
  ('0482', '624970318N', 'PR004', 'MT036', 6, 5, 7, -1), -- Calificaciones de Lenguaje de Marcas
  ('0483', '624970318N', 'PR006', 'MT037', 8, 7, 9, -1), -- Calificaciones de Bases de Datos
  ('0484', '624970318N', 'PR001', 'MT038', 5, 6, 4, -1), -- Calificaciones de Programación
  ('0485', '624970318N', 'PR005', 'MT039', 7, 8, 6, -1), -- Calificaciones de Entornos de Desarrollo
  ('0617', '624970318N', 'PR003', 'MT040', 9, 10, 8, -1); -- Calificaciones de Formación Orientativa Laboral

-- Calificaciones de: Max Fornait Valorante (5 módulos, Excluido de FOL)
INSERT INTO CALIFICACIONES (CODIGO_MODULO, NIA_ALUMNO, CODIGO_PROFESOR, CODIGO_MATRICULA, PRIMERA_EVALUACION, SEGUNDA_EVALUACION, ORDINARIA, EXTRAORDINARIA)
VALUES 
  ('0481', '970318624O', 'PR008', 'MT041', 5, 6, 7, -1), -- Calificaciones de Sistemas Informáticos
  ('0482', '970318624O', 'PR004', 'MT042', 8, 7, 9, -1), -- Calificaciones de Lenguaje de Marcas
  ('0483', '970318624O', 'PR006', 'MT043', 4, 5, 6, -1), -- Calificaciones de Bases de Datos
  ('0484', '970318624O', 'PR001', 'MT044', 7, 8, 10, -1), -- Calificaciones de Programación
  ('0485', '970318624O', 'PR005', 'MT045', 6, 5, 4, -1); -- Calificaciones de Entornos de Desarrollo

-- Calificaciones de: Miguel Don Watch (5 módulos, Excluido de FOL)
INSERT INTO CALIFICACIONES (CODIGO_MODULO, NIA_ALUMNO, CODIGO_PROFESOR, CODIGO_MATRICULA, PRIMERA_EVALUACION, SEGUNDA_EVALUACION, ORDINARIA, EXTRAORDINARIA)
VALUES 
  ('0481', '318624970P', 'PR008', 'MT046', 7, 6, 8, -1), -- Calificaciones de Sistemas Informáticos
  ('0482', '318624970P', 'PR004', 'MT047', 5, 4, 3, -1), -- Calificaciones de Lenguaje de Marcas
  ('0483', '318624970P', 'PR006', 'MT048', 9, 8, 10, -1), -- Calificaciones de Bases de Datos
  ('0484', '318624970P', 'PR001', 'MT049', 2, 3, 4, -1), -- Calificaciones de Programación
  ('0485', '318624970P', 'PR005', 'MT050', 6, 7, 5, -1); -- Calificaciones de Entornos de Desarrollo

-- Calificaciones de: Paula Purple Nails (6 módulos, Cursa todos los Módulos)
INSERT INTO CALIFICACIONES (CODIGO_MODULO, NIA_ALUMNO, CODIGO_PROFESOR, CODIGO_MATRICULA, PRIMERA_EVALUACION, SEGUNDA_EVALUACION, ORDINARIA, EXTRAORDINARIA)
VALUES 
  ('0481', '624970318Q', 'PR008', 'MT056', 8, 9, 10, -1), -- Calificaciones de Sistemas Informáticos
  ('0482', '624970318Q', 'PR004', 'MT057', 3, 4, 5, -1), -- Calificaciones de Lenguaje de Marcas
  ('0483', '624970318Q', 'PR006', 'MT058', 7, 6, 8, -1), -- Calificaciones de Bases de Datos
  ('0484', '624970318Q', 'PR001', 'MT059', 4, 5, 3, -1), -- Calificaciones de Programación
  ('0485', '624970318Q', 'PR005', 'MT060', 6, 7, 9, -1), -- Calificaciones de Entornos de Desarrollo
  ('0617', '624970318Q', 'PR003', 'MT061', 2, 3, 4, -1); -- Calificaciones de Formación Orientativa Laboral

-- Calificaciones de: Quique Portuano Milano (5 módulos, Excluido de FOL)
INSERT INTO CALIFICACIONES (CODIGO_MODULO, NIA_ALUMNO, CODIGO_PROFESOR, CODIGO_MATRICULA, PRIMERA_EVALUACION, SEGUNDA_EVALUACION, ORDINARIA, EXTRAORDINARIA)
VALUES 
  ('0481', '970318624R', 'PR008', 'MT051', 6, 7, 5, -1), -- Calificaciones de Sistemas Informáticos
  ('0482', '970318624R', 'PR004', 'MT052', 8, 9, 10, -1), -- Calificaciones de Lenguaje de Marcas
  ('0483', '970318624R', 'PR006', 'MT053', 4, 3, 2, -1), -- Calificaciones de Bases de Datos
  ('0484', '970318624R', 'PR001', 'MT054', 7, 8, 6, -1), -- Calificaciones de Programación
  ('0485', '970318624R', 'PR005', 'MT055', 5, 4, 3, -1); -- Calificaciones de Entornos de Desarrollo

-- Calificaciones de: Roberto Stolisnaya (5 módulos, Excluido de FOL)
INSERT INTO CALIFICACIONES (CODIGO_MODULO, NIA_ALUMNO, CODIGO_PROFESOR, CODIGO_MATRICULA, PRIMERA_EVALUACION, SEGUNDA_EVALUACION, ORDINARIA, EXTRAORDINARIA)
VALUES 
  ('0484', '87654321S', 'PR001', 'MT025', 6, 5, 4, -1), -- Calificaciones de Bases de Datos
  ('0487', '87654321S', 'PR002', 'MT026', 7, 8, 9, -1), -- Calificaciones de Entornos de Desarrollo
  ('0373', '87654321S', 'PR004', 'MT027', 3, 2, 1, -1), -- Calificaciones de Lenguaje de Marcas
  ('0485', '87654321S', 'PR005', 'MT028', 8, 9, 10, -1), -- Calificaciones de Programación
  ('0483', '87654321S', 'PR006', 'MT029', 5, 4, 3, -1); -- Calificaciones de Sistemas Informáticos

-- Calificaciones de: Rodrigo Esquibidi (6 módulos, Cursa todos los Módulos)
INSERT INTO CALIFICACIONES (CODIGO_MODULO, NIA_ALUMNO, CODIGO_PROFESOR, CODIGO_MATRICULA, PRIMERA_EVALUACION, SEGUNDA_EVALUACION, ORDINARIA, EXTRAORDINARIA)
VALUES 
  ('0484', '98765432T', 'PR001', 'MT031', 5, 6, 7, -1), -- Calificaciones de Bases de Datos
  ('0487', '98765432T', 'PR002', 'MT032', 8, 7, 6, -1), -- Calificaciones de Entornos de Desarrollo
  ('0617', '98765432T', 'PR003', 'MT033', 4, 3, 2, -1), -- Calificaciones de Formación Orientativa Laboral
  ('0373', '98765432T', 'PR004', 'MT034', 9, 8, 7, -1), -- Calificaciones de Lenguaje de Marcas
  ('0485', '98765432T', 'PR005', 'MT035', 6, 5, 4, -1), -- Calificaciones de Programación
  ('0483', '98765432T', 'PR006', 'MT036', 7, 8, 9, -1); -- Calificaciones de Sistemas Informáticos

-- Calificaciones de: Tati Mileidi (6 módulos, Cursa todos los Módulos)
INSERT INTO CALIFICACIONES (CODIGO_MODULO, NIA_ALUMNO, CODIGO_PROFESOR, CODIGO_MATRICULA, PRIMERA_EVALUACION, SEGUNDA_EVALUACION, ORDINARIA, EXTRAORDINARIA)
VALUES 
  ('0484', '09876543U', 'PR001', 'MT037', 8, 9, 10, -1), -- Calificaciones de Bases de Datos
  ('0487', '09876543U', 'PR002', 'MT038', 4, 3, 2, -1), -- Calificaciones de Entornos de Desarrollo
  ('0617', '09876543U', 'PR003', 'MT039', 6, 5, 4, -1), -- Calificaciones de Formación Orientativa Laboral
  ('0373', '09876543U', 'PR004', 'MT040', 7, 8, 9, -1), -- Calificaciones de Lenguaje de Marcas
  ('0485', '09876543U', 'PR005', 'MT041', 3, 2, 1, -1), -- Calificaciones de Programación
  ('0483', '09876543U', 'PR006', 'MT042', 5, 6, 7, -1); -- Calificaciones de Sistemas Informáticos

-- Calificaciones de: Victor Pelotade (6 módulos, Cursa todos los Módulos)
INSERT INTO CALIFICACIONES (CODIGO_MODULO, NIA_ALUMNO, CODIGO_PROFESOR, CODIGO_MATRICULA, PRIMERA_EVALUACION, SEGUNDA_EVALUACION, ORDINARIA, EXTRAORDINARIA)
VALUES 
  ('0484', '10987654V', 'PR001', 'MT023', 5, 4, 3, -1), -- Calificaciones de Bases de Datos
  ('0487', '10987654V', 'PR002', 'MT023', 6, 7, 8, -1), -- Calificaciones de Entornos de Desarrollo
  ('0617', '10987654V', 'PR003', 'MT023', 9, 8, 7, -1), -- Calificaciones de Formación Orientativa Laboral
  ('0373', '10987654V', 'PR004', 'MT023', 2, 3, 4, -1), -- Calificaciones de Lenguaje de Marcas
  ('0485', '10987654V', 'PR005', 'MT023', 6, 5, 4, -1), -- Calificaciones de Programación
  ('0483', '10987654V', 'PR006', 'MT023', 7, 8, 9, -1); -- Calificaciones de Sistemas Informáticos