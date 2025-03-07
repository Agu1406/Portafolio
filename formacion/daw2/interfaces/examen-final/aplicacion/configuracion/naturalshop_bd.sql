/*
* SCRIPT SQL PARA LA BASE DE DATOS DE NATURALSHOP
* 
* Versión: 1.0
* Fecha de creación: 07/03/2024
* Última modificación: 07/03/2024
* Motor de base de datos: MySQL 8.0
* Codificación: UTF-8
* 
* Este script crea la estructura de la base de datos para la tienda online NaturalShop,
* especializada en productos de parafarmacia, cosméticos y productos naturales.
*/

-- Configuración inicial
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- Eliminar la base de datos si existe para asegurar una creación limpia
DROP DATABASE IF EXISTS `naturalshop`;

-- Crear la base de datos con soporte completo para UTF-8
CREATE DATABASE `naturalshop` 
    DEFAULT CHARACTER SET utf8mb4 
    DEFAULT COLLATE utf8mb4_unicode_ci;

-- Seleccionar la base de datos
USE `naturalshop`;

-- Tabla de usuarios
CREATE TABLE `usuarios` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'Identificador único del usuario',
    `email` VARCHAR(100) NOT NULL COMMENT 'Correo electrónico (usado para login)',
    `password` VARCHAR(255) NOT NULL COMMENT 'Contraseña hasheada',
    `nombre` VARCHAR(100) NOT NULL COMMENT 'Nombre completo del usuario',
    `telefono` VARCHAR(15) NULL COMMENT 'Número de teléfono',
    `direccion` VARCHAR(255) NOT NULL COMMENT 'Dirección de envío',
    `codigo_postal` VARCHAR(10) NOT NULL COMMENT 'Código postal',
    `ciudad` VARCHAR(100) NOT NULL COMMENT 'Ciudad',
    `provincia` VARCHAR(100) NOT NULL COMMENT 'Provincia o estado',
    `fecha_registro` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Fecha de registro',
    `ultima_sesion` DATETIME NULL COMMENT 'Fecha de última sesión',
    `activo` TINYINT(1) NOT NULL DEFAULT 1 COMMENT 'Estado del usuario (1=activo, 0=inactivo)',
    `rol` ENUM('cliente', 'admin') NOT NULL DEFAULT 'cliente' COMMENT 'Rol del usuario',
    PRIMARY KEY (`id`),
    UNIQUE INDEX `email_UNIQUE` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci
COMMENT='Almacena la información de los usuarios registrados';

-- Tabla de categorías
CREATE TABLE `categorias` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'Identificador único de la categoría',
    `nombre` VARCHAR(100) NOT NULL COMMENT 'Nombre de la categoría',
    `descripcion` TEXT NULL COMMENT 'Descripción de la categoría',
    `imagen` VARCHAR(255) NULL COMMENT 'Ruta a la imagen de la categoría',
    `slug` VARCHAR(100) NOT NULL COMMENT 'Versión URL-friendly del nombre',
    `activa` TINYINT(1) NOT NULL DEFAULT 1 COMMENT 'Estado de la categoría (1=activa, 0=inactiva)',
    `orden` INT NOT NULL DEFAULT 0 COMMENT 'Orden de visualización',
    `categoria_padre_id` INT UNSIGNED NULL COMMENT 'ID de la categoría padre (para subcategorías)',
    `fecha_creacion` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Fecha de creación',
    `fecha_actualizacion` DATETIME NULL ON UPDATE CURRENT_TIMESTAMP COMMENT 'Fecha de última actualización',
    PRIMARY KEY (`id`),
    UNIQUE INDEX `slug_UNIQUE` (`slug`),
    INDEX `fk_categorias_categorias_idx` (`categoria_padre_id`),
    CONSTRAINT `fk_categorias_categorias` FOREIGN KEY (`categoria_padre_id`)
        REFERENCES `categorias` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci
COMMENT='Almacena las categorías de productos';

-- Tabla de productos
CREATE TABLE `productos` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'Identificador único del producto',
    `codigo` VARCHAR(50) NULL COMMENT 'Código SKU del producto',
    `nombre` VARCHAR(255) NOT NULL COMMENT 'Nombre del producto',
    `descripcion` TEXT NULL COMMENT 'Descripción detallada del producto',
    `descripcion_corta` VARCHAR(255) NULL COMMENT 'Descripción breve del producto',
    `precio` DECIMAL(10,2) NOT NULL COMMENT 'Precio del producto',
    `precio_oferta` DECIMAL(10,2) NULL COMMENT 'Precio de oferta (si aplica)',
    `stock` INT NOT NULL DEFAULT 0 COMMENT 'Cantidad disponible',
    `imagen_principal` VARCHAR(255) NULL COMMENT 'Ruta a la imagen principal',
    `slug` VARCHAR(255) NOT NULL COMMENT 'Versión URL-friendly del nombre',
    `destacado` TINYINT(1) NOT NULL DEFAULT 0 COMMENT 'Producto destacado (1=sí, 0=no)',
    `nuevo` TINYINT(1) NOT NULL DEFAULT 0 COMMENT 'Producto nuevo (1=sí, 0=no)',
    `activo` TINYINT(1) NOT NULL DEFAULT 1 COMMENT 'Estado del producto (1=activo, 0=inactivo)',
    `categoria_id` INT UNSIGNED NOT NULL COMMENT 'ID de la categoría a la que pertenece',
    `fecha_creacion` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Fecha de creación',
    `fecha_actualizacion` DATETIME NULL ON UPDATE CURRENT_TIMESTAMP COMMENT 'Fecha de última actualización',
    PRIMARY KEY (`id`),
    UNIQUE INDEX `slug_UNIQUE` (`slug`),
    INDEX `fk_productos_categorias_idx` (`categoria_id`),
    CONSTRAINT `fk_productos_categorias` FOREIGN KEY (`categoria_id`)
        REFERENCES `categorias` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci
COMMENT='Almacena la información de los productos';

-- Tabla de imágenes de productos (para múltiples imágenes por producto)
CREATE TABLE `imagenes_producto` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'Identificador único de la imagen',
    `producto_id` INT UNSIGNED NOT NULL COMMENT 'ID del producto al que pertenece',
    `ruta` VARCHAR(255) NOT NULL COMMENT 'Ruta a la imagen',
    `orden` INT NOT NULL DEFAULT 0 COMMENT 'Orden de visualización',
    `fecha_creacion` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Fecha de creación',
    PRIMARY KEY (`id`),
    INDEX `fk_imagenes_productos_idx` (`producto_id`),
    CONSTRAINT `fk_imagenes_productos` FOREIGN KEY (`producto_id`)
        REFERENCES `productos` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci
COMMENT='Almacena las imágenes adicionales de los productos';

-- Tabla de carritos
CREATE TABLE `carritos` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'Identificador único del carrito',
    `usuario_id` INT UNSIGNED NULL COMMENT 'ID del usuario (NULL para carritos de invitados)',
    `token` VARCHAR(100) NULL COMMENT 'Token para carritos de invitados',
    `fecha_creacion` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Fecha de creación',
    `fecha_actualizacion` DATETIME NULL ON UPDATE CURRENT_TIMESTAMP COMMENT 'Fecha de última actualización',
    PRIMARY KEY (`id`),
    INDEX `fk_carritos_usuarios_idx` (`usuario_id`),
    CONSTRAINT `fk_carritos_usuarios` FOREIGN KEY (`usuario_id`)
        REFERENCES `usuarios` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci
COMMENT='Almacena los carritos de compra';

-- Tabla de detalles de carrito
CREATE TABLE `detalles_carrito` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'Identificador único del detalle',
    `carrito_id` INT UNSIGNED NOT NULL COMMENT 'ID del carrito',
    `producto_id` INT UNSIGNED NOT NULL COMMENT 'ID del producto',
    `cantidad` INT NOT NULL DEFAULT 1 COMMENT 'Cantidad del producto',
    `precio_unitario` DECIMAL(10,2) NOT NULL COMMENT 'Precio unitario al momento de añadir',
    `fecha_creacion` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Fecha de creación',
    `fecha_actualizacion` DATETIME NULL ON UPDATE CURRENT_TIMESTAMP COMMENT 'Fecha de última actualización',
    PRIMARY KEY (`id`),
    UNIQUE INDEX `carrito_producto_UNIQUE` (`carrito_id`, `producto_id`),
    INDEX `fk_detalles_carrito_productos_idx` (`producto_id`),
    CONSTRAINT `fk_detalles_carrito_carritos` FOREIGN KEY (`carrito_id`)
        REFERENCES `carritos` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `fk_detalles_carrito_productos` FOREIGN KEY (`producto_id`)
        REFERENCES `productos` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci
COMMENT='Almacena los productos en cada carrito';

-- Tabla de pedidos
CREATE TABLE `pedidos` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'Identificador único del pedido',
    `codigo` VARCHAR(20) NOT NULL COMMENT 'Código único del pedido',
    `usuario_id` INT UNSIGNED NOT NULL COMMENT 'ID del usuario que realizó el pedido',
    `estado` ENUM('pendiente', 'pagado', 'enviado', 'entregado', 'cancelado') NOT NULL DEFAULT 'pendiente' COMMENT 'Estado del pedido',
    `metodo_pago` ENUM('tarjeta', 'paypal', 'transferencia', 'contrareembolso') NOT NULL COMMENT 'Método de pago utilizado',
    `referencia_pago` VARCHAR(255) NULL COMMENT 'Referencia del pago (si aplica)',
    `subtotal` DECIMAL(10,2) NOT NULL COMMENT 'Subtotal del pedido',
    `impuestos` DECIMAL(10,2) NOT NULL COMMENT 'Impuestos aplicados',
    `gastos_envio` DECIMAL(10,2) NOT NULL COMMENT 'Gastos de envío',
    `descuento` DECIMAL(10,2) NOT NULL DEFAULT 0.00 COMMENT 'Descuento aplicado',
    `total` DECIMAL(10,2) NOT NULL COMMENT 'Total del pedido',
    `nombre_envio` VARCHAR(100) NOT NULL COMMENT 'Nombre para el envío',
    `direccion_envio` VARCHAR(255) NOT NULL COMMENT 'Dirección de envío',
    `codigo_postal_envio` VARCHAR(10) NOT NULL COMMENT 'Código postal de envío',
    `ciudad_envio` VARCHAR(100) NOT NULL COMMENT 'Ciudad de envío',
    `provincia_envio` VARCHAR(100) NOT NULL COMMENT 'Provincia de envío',
    `telefono_envio` VARCHAR(15) NULL COMMENT 'Teléfono de contacto para el envío',
    `notas` TEXT NULL COMMENT 'Notas adicionales del pedido',
    `fecha_pedido` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Fecha de creación del pedido',
    `fecha_pago` DATETIME NULL COMMENT 'Fecha de pago',
    `fecha_envio` DATETIME NULL COMMENT 'Fecha de envío',
    `fecha_entrega` DATETIME NULL COMMENT 'Fecha de entrega',
    `fecha_actualizacion` DATETIME NULL ON UPDATE CURRENT_TIMESTAMP COMMENT 'Fecha de última actualización',
    PRIMARY KEY (`id`),
    UNIQUE INDEX `codigo_UNIQUE` (`codigo`),
    INDEX `fk_pedidos_usuarios_idx` (`usuario_id`),
    CONSTRAINT `fk_pedidos_usuarios` FOREIGN KEY (`usuario_id`)
        REFERENCES `usuarios` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci
COMMENT='Almacena los pedidos realizados';

-- Tabla de detalles de pedido
CREATE TABLE `detalles_pedido` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'Identificador único del detalle',
    `pedido_id` INT UNSIGNED NOT NULL COMMENT 'ID del pedido',
    `producto_id` INT UNSIGNED NOT NULL COMMENT 'ID del producto',
    `nombre_producto` VARCHAR(255) NOT NULL COMMENT 'Nombre del producto (snapshot)',
    `precio_unitario` DECIMAL(10,2) NOT NULL COMMENT 'Precio unitario al momento de la compra',
    `cantidad` INT NOT NULL COMMENT 'Cantidad comprada',
    `subtotal` DECIMAL(10,2) NOT NULL COMMENT 'Subtotal de la línea',
    PRIMARY KEY (`id`),
    INDEX `fk_detalles_pedido_pedidos_idx` (`pedido_id`),
    INDEX `fk_detalles_pedido_productos_idx` (`producto_id`),
    CONSTRAINT `fk_detalles_pedido_pedidos` FOREIGN KEY (`pedido_id`)
        REFERENCES `pedidos` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `fk_detalles_pedido_productos` FOREIGN KEY (`producto_id`)
        REFERENCES `productos` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci
COMMENT='Almacena los productos de cada pedido';

-- Tabla de contactos (para mensajes de contacto)
CREATE TABLE `contactos` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'Identificador único del mensaje',
    `nombre` VARCHAR(100) NOT NULL COMMENT 'Nombre de la persona',
    `email` VARCHAR(100) NOT NULL COMMENT 'Correo electrónico',
    `telefono` VARCHAR(15) NULL COMMENT 'Número de teléfono',
    `asunto` VARCHAR(255) NOT NULL COMMENT 'Asunto del mensaje',
    `mensaje` TEXT NOT NULL COMMENT 'Contenido del mensaje',
    `leido` TINYINT(1) NOT NULL DEFAULT 0 COMMENT 'Estado de lectura (1=leído, 0=no leído)',
    `fecha_creacion` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Fecha de envío',
    `fecha_lectura` DATETIME NULL COMMENT 'Fecha de lectura',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci
COMMENT='Almacena los mensajes de contacto';

-- Restablecer la verificación de claves foráneas
SET FOREIGN_KEY_CHECKS = 1;

-- Insertar categorías iniciales
INSERT INTO `categorias` (`nombre`, `descripcion`, `slug`, `activa`, `orden`) VALUES 
('Parafarmacia', 'Productos para el cuidado de la salud', 'parafarmacia', 1, 1),
('Cosméticos Naturales', 'Belleza con ingredientes naturales', 'cosmeticos-naturales', 1, 2),
('Suplementos Alimenticios', 'Complementos para una vida saludable', 'suplementos-alimenticios', 1, 3),
('Aromaterapia', 'Aceites esenciales y difusores', 'aromaterapia', 1, 4),
('Herbolario', 'Plantas medicinales y tés', 'herbolario', 1, 5);

-- Insertar usuario administrador
INSERT INTO `usuarios` (`email`, `password`, `nombre`, `telefono`, `direccion`, `codigo_postal`, `ciudad`, `provincia`, `rol`) VALUES 
('admin@naturalshop.com', '$2y$10$YourHashedPasswordHere', 'Administrador', '600123456', 'Calle Administración 1', '28001', 'Madrid', 'Madrid', 'admin');

-- Insertar usuario de prueba
INSERT INTO `usuarios` (`email`, `password`, `nombre`, `telefono`, `direccion`, `codigo_postal`, `ciudad`, `provincia`) VALUES 
('usuario@ejemplo.com', '$2y$10$YourHashedPasswordHere', 'Usuario Demo', '600654321', 'Calle Ejemplo 123', '08001', 'Barcelona', 'Barcelona');

-- Insertar productos de ejemplo
INSERT INTO `productos` (`codigo`, `nombre`, `descripcion_corta`, `descripcion`, `precio`, `stock`, `slug`, `destacado`, `nuevo`, `activo`, `categoria_id`) VALUES 
('CR001', 'Crema Hidratante Natural', 'Crema hidratante con ingredientes 100% naturales', 'Nuestra crema hidratante está formulada con ingredientes naturales como aloe vera, aceite de jojoba y manteca de karité. Hidrata profundamente la piel sin dejar sensación grasa. Ideal para todo tipo de pieles, incluso las más sensibles.', 19.99, 15, 'crema-hidratante-natural', 1, 0, 1, 2),
('VIT001', 'Vitamina C 1000mg', 'Suplemento de Vitamina C para reforzar el sistema inmunológico', 'Suplemento de Vitamina C de alta absorción que ayuda a fortalecer el sistema inmunológico, combatir el estrés oxidativo y promover la producción de colágeno. Cada tableta contiene 1000mg de Vitamina C pura.', 12.50, 30, 'vitamina-c-1000mg', 1, 0, 1, 3),
('ALO001', 'Gel de Aloe Vera', 'Gel calmante de Aloe Vera para pieles sensibles', 'Gel puro de Aloe Vera con propiedades calmantes e hidratantes. Ideal para después del sol, irritaciones leves o como base hidratante diaria. Contiene 99% de Aloe Vera orgánico sin parabenos ni colorantes artificiales.', 9.95, 20, 'gel-aloe-vera', 1, 0, 1, 1),
('LAV001', 'Aceite Esencial de Lavanda', 'Aceite esencial 100% puro para aromaterapia', 'Aceite esencial de lavanda 100% puro y natural, ideal para aromaterapia, masajes relajantes o para añadir a difusores. Ayuda a reducir el estrés, mejorar el sueño y calmar la piel irritada.', 15.75, 25, 'aceite-esencial-lavanda', 1, 1, 1, 4); 