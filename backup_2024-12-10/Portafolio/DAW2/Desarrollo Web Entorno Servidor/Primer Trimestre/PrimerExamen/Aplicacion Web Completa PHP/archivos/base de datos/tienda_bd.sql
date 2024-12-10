/*
* CREACIÓN DEL SQL: AGUSTÍN. A MARQUEZ PIÑA
* INSERTAR DATOS EN LAS TABLAS: AGUSTÍN. A MARQUEZ PIÑA
* ESTABLECER LAS FOREIGN KEYS (FK Y PFK): AGUSTÍN. A MARQUEZ PIÑA
* ESTABLECER LAS PRIMARY KEYS (PK Y/O AK): AGUSTÍN. A MARQUEZ PIÑA
*
* CREACIÓN DEL SQL: 17/11/2024
* ULTIMA MODIFICACIÓN: 17/11/2024
* TIPO DE BASE DE DATOS: MySQL 5.0
* MODELO DE BASE DE DATOS: MySQL 5.0
*/

-- Elimina la base de datos si ya existe para evitar conflictos de nombres y 
-- asegurar una creación limpia
DROP DATABASE IF EXISTS `tienda_bd`;

-- Crea la base de datos con el nombre específico proporcionado
CREATE DATABASE `tienda_bd`;

-- Selecciona la base de datos recién creada para comenzar a operar sobre ella
USE `tienda_bd`;

-- Creación de la tabla CLIENTE
CREATE TABLE `Cliente` (
    `codigo_cliente` INT NOT NULL,               -- ID único del cliente.
    `cif_cliente` VARCHAR(20) NOT NULL,          -- CIF del cliente.
    `direccion` VARCHAR(45) NOT NULL,            -- Dirección del cliente.
    `codigo_postal` VARCHAR(5) NOT NULL,         -- Código postal del cliente.
    `telefono` VARCHAR(12) NULL,                 -- Teléfono del cliente (opcional).
    PRIMARY KEY (`codigo_cliente`)               -- Clave primaria para identificar al cliente.
);

-- Hacer que `codigo_cliente` sea auto incrementable
ALTER TABLE `Cliente` MODIFY COLUMN `codigo_cliente` INT AUTO_INCREMENT;

-- Creación de la tabla CREDENCIALES
CREATE TABLE `Credenciales` (
    `correo` VARCHAR(80) NOT NULL,               -- Correo del cliente (clave primaria).
    `contrasena` VARCHAR(255) NOT NULL,           -- Contraseña para acceso.
    `cliente_codigo_cliente` INT NOT NULL,       -- Clave foránea hacia Cliente.
    `rol` CHAR(1) NOT NULL DEFAULT '0',          -- Rol: '0' = Usuario normal, '1' = Administrador
    PRIMARY KEY (`correo`),                      -- Clave primaria del correo.
    CONSTRAINT `fk_Credenciales_Cliente` FOREIGN KEY (`cliente_codigo_cliente`)
    REFERENCES `Cliente`(`codigo_cliente`)      -- Relación con la tabla Cliente.
    ON DELETE CASCADE ON UPDATE CASCADE         -- Acciones en caso de eliminación o actualización.
);

-- Creación de la tabla CARRITO
CREATE TABLE `Carrito` (
    `codigo_carrito` INT NOT NULL,               -- ID único del carrito.
    `fecha_creacion` DATETIME NULL,              -- Fecha de creación del carrito.
    `cliente_codigo_cliente` INT NOT NULL,       -- Clave foránea hacia Cliente.
    PRIMARY KEY (`codigo_carrito`),              -- Clave primaria del carrito.
    CONSTRAINT `fk_Carrito_Cliente` FOREIGN KEY (`cliente_codigo_cliente`)
    REFERENCES `Cliente`(`codigo_cliente`)      -- Relación con la tabla Cliente.
    ON DELETE CASCADE ON UPDATE CASCADE         -- Acciones en caso de eliminación o actualización.
);

-- Hacer que `codigo_carrito` sea auto incrementable
ALTER TABLE `Carrito` MODIFY COLUMN `codigo_carrito` INT AUTO_INCREMENT;

-- Creación de la tabla PEDIDO
CREATE TABLE `Pedido` (
    `codigo_pedido` INT NOT NULL,                -- ID único del pedido.
    `fecha_pedido` DATETIME NOT NULL,            -- Fecha del pedido.
    `estado_pedido` VARCHAR(20) NOT NULL,        -- Estado del pedido (ej. Pendiente, Enviado).
    `metodo_pago` VARCHAR(15) NOT NULL,          -- Método de pago utilizado.
    `cliente_codigo_cliente` INT NOT NULL,       -- Clave foránea hacia Cliente.
    PRIMARY KEY (`codigo_pedido`),               -- Clave primaria del pedido.
    CONSTRAINT `fk_Pedido_Cliente` FOREIGN KEY (`cliente_codigo_cliente`)
    REFERENCES `cliente`(`codigo_cliente`)      -- Relación con la tabla Cliente.
    ON DELETE CASCADE ON UPDATE CASCADE         -- Acciones en caso de eliminación o actualización.
);

-- Hacer que `codigo_pedido` sea auto incrementable
ALTER TABLE `Pedido` MODIFY COLUMN `codigo_pedido` INT AUTO_INCREMENT;

-- Creación de la tabla CATEGORIA
CREATE TABLE `Categoria` (
    `codigo_categoria` INT NOT NULL,             -- ID único de la categoría.
    `nombre_categoria` VARCHAR(45) NOT NULL,     -- Nombre de la categoría (ej. Electrónica).
    PRIMARY KEY (`codigo_categoria`)             -- Clave primaria de la categoría.
);

-- Hacer que `codigo_categoria` sea auto incrementable
ALTER TABLE `Categoria` MODIFY COLUMN `codigo_categoria` INT AUTO_INCREMENT;

-- Creación de la tabla PRODUCTO
CREATE TABLE `Producto` (
    `codigo_producto` INT NOT NULL,              -- ID único del producto.
    `nombre_producto` VARCHAR(45) NOT NULL,      -- Nombre del producto.
    `descripcion_producto` VARCHAR(45) NULL,     -- Descripción opcional del producto.
    `precio_producto` DECIMAL(10,2) NOT NULL,    -- Precio del producto.
    `stock` INT NOT NULL,
    `imagen` LONGBLOB NOT NULL,                        -- Cantidad disponible del producto.
    `categoria_codigo_categoria` INT NOT NULL,   -- Clave foránea hacia la tabla Categoria.
    PRIMARY KEY (`codigo_producto`),             -- Clave primaria del producto.
    CONSTRAINT `fk_Producto_Categoria` FOREIGN KEY (`Categoria_codigo_categoria`)
    REFERENCES `Categoria`(`codigo_categoria`)  -- Relación con la tabla Categoria.
    ON DELETE CASCADE ON UPDATE CASCADE         -- Acciones en caso de eliminación o actualización.
);

-- Hacer que `codigo_producto` sea auto incrementable
ALTER TABLE `Producto` MODIFY COLUMN `codigo_producto` INT AUTO_INCREMENT;

-- Creación de la tabla DETALLE_CARRITO
CREATE TABLE `Detalle_Carrito` (
    `cantidad_producto` INT NOT NULL,            -- Cantidad de productos en el carrito.
    `Carrito_codigo_carrito` INT NOT NULL,       -- Clave foránea hacia Carrito.
    `Producto_codigo_producto` INT NOT NULL,     -- Clave foránea hacia Producto.
    PRIMARY KEY (`Carrito_codigo_carrito`, `Producto_codigo_producto`), -- Clave primaria compuesta.
    CONSTRAINT `fk_Detalle_Carrito_Carrito` FOREIGN KEY (`Carrito_codigo_carrito`)
    REFERENCES `Carrito`(`codigo_carrito`)      -- Relación con la tabla Carrito.
    ON DELETE CASCADE ON UPDATE CASCADE,         -- Acciones en caso de eliminación o actualización.
    CONSTRAINT `fk_Detalle_Carrito_Producto` FOREIGN KEY (`Producto_codigo_producto`)
    REFERENCES `Producto`(`codigo_producto`)    -- Relación con la tabla Producto.
    ON DELETE CASCADE ON UPDATE CASCADE         -- Acciones en caso de eliminación o actualización.
);

-- Creación de la tabla DETALLE_PEDIDO
CREATE TABLE `Detalle_Pedido` (
    `cantidad_producto` INT NOT NULL,            -- Cantidad de productos en el pedido.
    `Pedido_codigo_pedido` INT NOT NULL,         -- Clave foránea hacia Pedido.
    `Producto_codigo_producto` INT NOT NULL,     -- Clave foránea hacia Producto.
    PRIMARY KEY (`Pedido_codigo_pedido`, `Producto_codigo_producto`), -- Clave primaria compuesta.
    CONSTRAINT `fk_Detalle_Pedido_Pedido` FOREIGN KEY (`Pedido_codigo_pedido`)
    REFERENCES `Pedido`(`codigo_pedido`)         -- Relación con la tabla Pedido.
    ON DELETE CASCADE ON UPDATE CASCADE,         -- Acciones en caso de eliminación o actualización.
    CONSTRAINT `fk_Detalle_Pedido_Producto` FOREIGN KEY (`Producto_codigo_producto`)
    REFERENCES `Producto`(`codigo_producto`)     -- Relación con la tabla Producto.
    ON DELETE CASCADE ON UPDATE CASCADE          -- Acciones en caso de eliminación o actualización.
);

/* DE AQUÍ PARA ABAJO SIN INSERTS MANUALES QUE USO PARA PROBAR LA BASE DE DATOS */


-- Insertar categorías en la tabla Categoria

-- Categoría 1: Alimentos
INSERT INTO `Categoria` (`codigo_categoria`, `nombre_categoria`)
VALUES (1, 'Alimentos');

-- Categoría 2: Cosméticos
INSERT INTO `Categoria` (`codigo_categoria`, `nombre_categoria`)
VALUES (2, 'Cosmeticos');

-- Categoría 3: Parafarmacia
INSERT INTO `Categoria` (`codigo_categoria`, `nombre_categoria`)
VALUES (3, 'Parafarmacia');

-- Insertar un cliente cliente en la tabla Cliente
INSERT INTO `Cliente` (`codigo_cliente`, `cif_cliente`, `direccion`, `codigo_postal`, `telefono`)
VALUES (1, '12345678A', 'Avenida La Calle 2', '12345', '123456789');

-- Insertar otro cliente en la tabla Cliente
INSERT INTO `Cliente` (`codigo_cliente`, `cif_cliente`, `direccion`, `codigo_postal`, `telefono`)
VALUES (2, '87654321B', 'Calle La Calle 1', '54321', '987654321');

-- Insertar credenciales asociadas al cliente con codigo_cliente = 1 y rol = 1 (administrador)
INSERT INTO `Credenciales` (`correo`, `contrasena`, `cliente_codigo_cliente`, `rol`)
VALUES ('admin@admin.com', '$2y$10$/iSfeyOg7I4ukjBKRZAjTe6tba9419SrKaq/7NEfGEZy3wou1FUhe', 1, '1');

-- Insertar credenciales asociadas al cliente con codigo_cliente = 1 y rol = 0 (usuario normal)
INSERT INTO `Credenciales` (`correo`, `contrasena`, `cliente_codigo_cliente`, `rol`)
VALUES ('ejemplo@ejemplo.com', '$2y$10$/iSfeyOg7I4ukjBKRZAjTe6tba9419SrKaq/7NEfGEZy3wou1FUhe', 2, '0');

-- Insertar un carrito para el cliente con codigo_cliente = 1
INSERT INTO `Carrito` (`fecha_creacion`, `cliente_codigo_cliente`)
VALUES (NOW(), 1);