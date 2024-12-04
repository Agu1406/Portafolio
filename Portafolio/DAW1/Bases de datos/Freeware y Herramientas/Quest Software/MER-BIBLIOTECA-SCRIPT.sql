/*
Created		10/9/2017
Modified		2/22/2022
Project		
Model		
Company		
Author		
Version		
Database		mySQL 5 
*/


drop table IF EXISTS PRESTAMO;
drop table IF EXISTS EJEMPLAR_RECURSO;
drop table IF EXISTS SOCIO;
drop table IF EXISTS RECURSO;
drop table IF EXISTS BIBLIOTECARIO;


Create table BIBLIOTECARIO (
	CODIGO_BIBLIOTECARIO Char(2) NOT NULL COMMENT 'Este campo utiliza una clave cuyo rango es de ''00'' a ''99''',
	NOMBRE_BIBLIOTECARIO Varchar(25) NOT NULL,
	APELLIDO1_BIBLIOTECARIO Varchar(25) NOT NULL,
	APELLIDO2_BIBLIOTECARIO Varchar(25) NOT NULL,
	CORREO_BIBLIOTECARIO Varchar(30) NOT NULL,
 Primary Key (CODIGO_BIBLIOTECARIO)) ENGINE = MyISAM;

Create table RECURSO (
	CODIGO_RECURSO Char(9) NOT NULL COMMENT 'El formato de la clave primaria es el siguiente: RR-000000,
siendo:

RR: un código alfabético para indicar el tipo de recurso

CD: Compact Disc
DV: Digital Video Disc
LB: Libro

0000000: Un código numérico de 6 dígitos

Ejemplos: 
LB-033456
DV-000678',
	TITULO_RECURSO Varchar(50) NOT NULL,
	AUTOR_RECURSO Varchar(50) NOT NULL COMMENT 'Este campo recoge el director de la película, o el grupo/cantante del CD o el autor del libro.',
	TIPO_RECURSO Char(2) NOT NULL COMMENT 'Este campo indica el tipo de recurso:

LB: Libro
CD: Compact Disc
DV: Digital Video Disc',
	GENERO_RECURSO Varchar(20) NOT NULL,
	ISBN_RECURSO Char(20) NOT NULL COMMENT 'El ISBN recoge en el caso de los libros el propio ISBN y en el caso de los CDs y DVDs un código normalizado en la industria musical y audiovisual.',
	UNIQUE (ISBN_RECURSO),
 Primary Key (CODIGO_RECURSO)) ENGINE = MyISAM;

Create table SOCIO (
	CODIGO_SOCIO Char(4) NOT NULL,
	NOMBRE_SOCIO Varchar(25) NOT NULL,
	APELLIDO1_SOCIO Varchar(20) NOT NULL,
	APELLIDO2_SOCIO Varchar(25) NOT NULL,
	CORREO_SOCIO Varchar(50) NOT NULL,
	FECHA_ALTA_SOCIO Date NOT NULL,
	ESTADO_SOCIO Char(1) NOT NULL COMMENT 'A: Activo
B: Baja
S: Suspendido
',
 Primary Key (CODIGO_SOCIO)) ENGINE = MyISAM;

Create table EJEMPLAR_RECURSO (
	CODIGO_RECURSO Char(9) NOT NULL,
	NUMERO_EJEMPLAR Char(2) NOT NULL,
	ESTADO_EJEMPLAR_RECURSO Char(1) NOT NULL COMMENT 'L: Libre
P: Prestado
R: Roto
D: Desaparecido
B: Baja
X: Reservado
',
 Primary Key (CODIGO_RECURSO,NUMERO_EJEMPLAR)) ENGINE = MyISAM;

Create table PRESTAMO (
	NUMERO_PRESTAMO Char(20) NOT NULL,
	CODIGO_RECURSO Char(9) NOT NULL,
	NUMERO_EJEMPLAR Char(2) NOT NULL,
	CODIGO_BIBLIOTECARIO Char(2) NOT NULL,
	CODIGO_SOCIO Char(4) NOT NULL,
	FECHA_INICIO_PRESTAMO Date NOT NULL,
	FECHA_FIN_PRESTAMO Date NOT NULL,
	FECHA_REAL_DEVOLUCION_PRESTAMO Date NOT NULL,
	FECHA_PREVISTA_DEVOLUCION_PRESTAMO Date NOT NULL,
	ESTADO_PRESTAMO Char(20) NOT NULL,
 Primary Key (NUMERO_PRESTAMO)) ENGINE = MyISAM;


Alter table RECURSO add unique Alter_Key2 (ISBN_RECURSO);


Alter table PRESTAMO add Foreign Key (CODIGO_BIBLIOTECARIO) references BIBLIOTECARIO (CODIGO_BIBLIOTECARIO) on delete  restrict on update  restrict;
Alter table EJEMPLAR_RECURSO add Foreign Key (CODIGO_RECURSO) references RECURSO (CODIGO_RECURSO) on delete  restrict on update  restrict;
Alter table PRESTAMO add Foreign Key (CODIGO_SOCIO) references SOCIO (CODIGO_SOCIO) on delete  restrict on update  restrict;
Alter table PRESTAMO add Foreign Key (CODIGO_RECURSO,NUMERO_EJEMPLAR) references EJEMPLAR_RECURSO (CODIGO_RECURSO,NUMERO_EJEMPLAR) on delete  restrict on update  restrict;


/* Users permissions */


