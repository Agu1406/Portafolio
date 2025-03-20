-- Script para recrear la base de datos Manos a la Olla

-- Eliminar la base de datos si existe
DROP DATABASE IF EXISTS `manosalaolla`;

-- Crear la base de datos
CREATE DATABASE `manosalaolla` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

-- Seleccionar la base de datos
USE `manosalaolla`;

-- Crear tabla de usuarios
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(255) NOT NULL,
  `bio` text DEFAULT NULL,
  `profile_image` varchar(255) DEFAULT 'default.jpg',
  `role` enum('user','admin') NOT NULL DEFAULT 'user',
  `created_at` datetime NOT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Crear tabla de categorías
CREATE TABLE `categories` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `description` text DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Crear tabla de recetas
CREATE TABLE `recipes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `ingredients` text NOT NULL,
  `instructions` text NOT NULL,
  `preparation_time` varchar(50) DEFAULT NULL,
  `cooking_time` varchar(50) DEFAULT NULL,
  `servings` int(11) DEFAULT NULL,
  `difficulty` enum('Fácil','Media','Difícil') NOT NULL DEFAULT 'Media',
  `image` varchar(255) DEFAULT 'default-recipe.jpg',
  `user_id` int(11) NOT NULL,
  `category_id` int(11) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `category_id` (`category_id`),
  CONSTRAINT `recipes_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE,
  CONSTRAINT `recipes_ibfk_2` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Crear tabla de comentarios
CREATE TABLE `comments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `recipe_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `body` text NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `recipe_id` (`recipe_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `comments_ibfk_1` FOREIGN KEY (`recipe_id`) REFERENCES `recipes` (`id`) ON DELETE CASCADE,
  CONSTRAINT `comments_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Crear tabla de favoritos
CREATE TABLE `favorites` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `recipe_id` int(11) NOT NULL,
  `created_at` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_recipe` (`user_id`,`recipe_id`),
  KEY `recipe_id` (`recipe_id`),
  CONSTRAINT `favorites_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE,
  CONSTRAINT `favorites_ibfk_2` FOREIGN KEY (`recipe_id`) REFERENCES `recipes` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Datos iniciales para categorías
INSERT INTO `categories` (`name`, `description`) VALUES
('Entrantes', 'Platos para comenzar una comida'),
('Sopas', 'Caldos y sopas para todos los gustos'),
('Ensaladas', 'Platos frescos y saludables'),
('Carnes', 'Recetas con diferentes tipos de carnes'),
('Pescados', 'Platos con pescados y mariscos'),
('Vegetarianas', 'Recetas sin carne ni pescado'),
('Postres', 'Dulces para terminar una comida'),
('Panes', 'Recetas de panes caseros'),
('Bebidas', 'Bebidas y cócteles'),
('Salsas', 'Acompañamientos para tus platos');

-- Datos de prueba: Usuarios
INSERT INTO `users` (`name`, `email`, `password`, `bio`, `profile_image`, `role`, `created_at`) VALUES
('Admin Principal', 'admin@manosalaolla.com', '$2y$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'Administrador principal de Manos a la Olla', 'https://randomuser.me/api/portraits/men/1.jpg', 'admin', NOW()),
('María García', 'maria@example.com', '$2y$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'Apasionada de la cocina mediterránea', 'https://randomuser.me/api/portraits/women/1.jpg', 'user', NOW());

-- Datos de prueba: Recetas
INSERT INTO `recipes` (`title`, `ingredients`, `instructions`, `preparation_time`, `cooking_time`, `servings`, `difficulty`, `image`, `user_id`, `category_id`, `created_at`) VALUES
('Paella Valenciana', 
'- 400g de arroz bomba\n- 1 pollo troceado\n- 100g de judías verdes\n- 100g de garrofón\n- 1 tomate rallado\n- Azafrán\n- Aceite de oliva\n- Sal\n- Agua\n- 2 dientes de ajo\n- 1 pimiento rojo', 
'1. Sofreír el pollo hasta que esté dorado\n2. Añadir las verduras y sofreír\n3. Agregar el tomate y cocinar\n4. Añadir el arroz y el caldo\n5. Cocinar a fuego medio durante 20 minutos\n6. Dejar reposar 5 minutos antes de servir', 
'30 minutos', '45 minutos', 4, 'Media', 
'https://www.hola.com/horizon/landscape/9e7aa7251e9f-stockfood00456742hirespaellavalencianawithgreenbeansspain.jpg?im=Resize=(960),type=downsize',
2, 1, NOW()),
('Gazpacho Andaluz', 
'- 1kg de tomates maduros\n- 1 pepino\n- 1 pimiento verde\n- 1 diente de ajo\n- Aceite de oliva\n- Vinagre\n- Sal\n- Agua fría', 
'1. Lavar y trocear todas las verduras\n2. Triturar todos los ingredientes\n3. Añadir agua, aceite, vinagre y sal al gusto\n4. Colar si se desea\n5. Refrigerar antes de servir', 
'20 minutos', '0 minutos', 6, 'Fácil', 
'https://www.cnature.es/wp-content/uploads/2020/08/gazpacho-gallego-.jpg',
2, 2, NOW()),
('Tortilla de Patatas', 
'- 6 huevos\n- 4 patatas medianas\n- 1 cebolla\n- Aceite de oliva\n- Sal', 
'1. Pelar y cortar las patatas\n2. Freír a fuego lento\n3. Batir los huevos\n4. Mezclar y cuajar', 
'15 minutos', '30 minutos', 4, 'Media', 
'https://s1.elespanol.com/2024/03/08/sevilla/838427159_240585428_1706x960.jpg',
2, 1, NOW()),
('Crema Catalana', '- 1L leche\n- 8 yemas\n- 200g azúcar\n- Canela\n- Piel de limón\n- Maicena', '1. Hervir la leche con canela y limón\n2. Mezclar yemas y azúcar\n3. Incorporar la leche\n4. Espesar y enfriar\n5. Caramelizar', '20 minutos', '15 minutos', 6, 'Media', 'https://live.staticflickr.com/4588/38506055514_b8ee126f15_b.jpg', 1, 7, NOW()),
('Pan Casero', '- 500g harina\n- 300ml agua\n- 10g levadura\n- 10g sal\n- Aceite de oliva', '1. Mezclar ingredientes\n2. Amasar 10 minutos\n3. Reposar 1 hora\n4. Formar y hornear', '20 minutos', '45 minutos', 8, 'Media', 'https://live.staticflickr.com/65535/49099136716_4c4b2f68c3_b.jpg', 1, 8, NOW());

-- Datos de prueba: Comentarios
INSERT INTO `comments` (`recipe_id`, `user_id`, `body`, `created_at`) VALUES
(1, 1, '¡Excelente receta! La he preparado varias veces y siempre queda perfecta.', NOW()),
(2, 1, 'Refrescante y muy fácil de preparar. Ideal para el verano.', NOW()),
(3, 1, 'La tortilla perfecta, con la cebolla queda mucho más jugosa.', NOW()),
(4, 1, 'El toque de canela le da un sabor especial. ¡Deliciosa!', NOW()),
(5, 1, 'Pan casero como el de toda la vida. ¡Gracias por compartir!', NOW());

-- Datos de prueba: Favoritos
INSERT INTO `favorites` (`user_id`, `recipe_id`, `created_at`) VALUES
(1, 2, NOW()),
(1, 3, NOW()),
(1, 1, NOW()),
(1, 4, NOW()),
(1, 5, NOW()); 