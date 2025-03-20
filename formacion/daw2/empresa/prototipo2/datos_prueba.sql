-- Insertar usuarios de prueba
INSERT INTO `users` (`name`, `email`, `password`, `bio`, `profile_image`, `role`, `created_at`) VALUES
('Admin Principal', 'admin@manosalaolla.com', '$2y$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'Administrador principal del sitio. Apasionado por la cocina y la gestión de la comunidad.', 'admin.jpg', 'admin', NOW()),
('María García', 'maria@example.com', '$2y$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'Amante de la cocina tradicional española. Me encanta compartir recetas familiares.', 'maria.jpg', 'user', NOW());

-- Insertar recetas de prueba
INSERT INTO `recipes` (`title`, `ingredients`, `instructions`, `preparation_time`, `cooking_time`, `servings`, `difficulty`, `image`, `user_id`, `category_id`, `created_at`) VALUES
('Paella Valenciana', 
'- 400g de arroz bomba\n- 1 pollo troceado\n- 100g de judías verdes\n- 100g de garrofón\n- 1 tomate rallado\n- Azafrán\n- Aceite de oliva\n- Sal\n- Agua\n- 2 dientes de ajo\n- 1 pimiento rojo', 
'1. Sofreír el pollo hasta que esté dorado\n2. Añadir las verduras y sofreír\n3. Agregar el tomate y cocinar\n4. Añadir el arroz y el caldo\n5. Cocinar a fuego medio durante 20 minutos\n6. Dejar reposar 5 minutos antes de servir', 
'30 minutos', '45 minutos', 4, 'Media', 'paella.jpg', 2, 1, NOW()),

('Gazpacho Andaluz', 
'- 1kg de tomates maduros\n- 1 pepino\n- 1 pimiento verde\n- 1 diente de ajo\n- Aceite de oliva\n- Vinagre\n- Sal\n- Agua fría', 
'1. Lavar y trocear todas las verduras\n2. Triturar todos los ingredientes\n3. Añadir agua, aceite, vinagre y sal al gusto\n4. Colar si se desea\n5. Refrigerar antes de servir', 
'20 minutos', '0 minutos', 6, 'Fácil', 'gazpacho.jpg', 2, 2, NOW()),

('Tortilla de Patatas', 
'- 6 huevos\n- 4 patatas medianas\n- 1 cebolla\n- Aceite de oliva\n- Sal', 
'1. Pelar y cortar las patatas en láminas finas\n2. Picar la cebolla\n3. Freír las patatas y la cebolla a fuego lento\n4. Batir los huevos y mezclar con las patatas\n5. Cuajar la tortilla por ambos lados', 
'15 minutos', '30 minutos', 4, 'Media', 'tortilla.jpg', 2, 1, NOW()),

('Crema Catalana', 
'- 500ml de leche\n- 4 yemas de huevo\n- 100g de azúcar\n- Piel de limón\n- Canela en rama\n- Maicena\n- Azúcar para caramelizar', 
'1. Infusionar la leche con canela y limón\n2. Mezclar yemas con azúcar y maicena\n3. Añadir la leche colada y cocinar hasta espesar\n4. Refrigerar\n5. Caramelizar antes de servir', 
'20 minutos', '15 minutos', 4, 'Media', 'crema-catalana.jpg', 1, 7, NOW()),

('Pan Casero', 
'- 500g de harina de fuerza\n- 300ml de agua\n- 10g de levadura fresca\n- 10g de sal\n- 1 cucharada de aceite', 
'1. Mezclar los ingredientes y amasar\n2. Dejar reposar 1 hora\n3. Dar forma y dejar fermentar\n4. Hornear a 200°C durante 30 minutos', 
'30 minutos', '30 minutos', 1, 'Media', 'pan-casero.jpg', 1, 8, NOW());

-- Insertar comentarios de prueba
INSERT INTO `comments` (`recipe_id`, `user_id`, `body`, `created_at`) VALUES
(1, 2, '¡Excelente receta! La he preparado varias veces y siempre queda perfecta.', NOW()),
(1, 1, 'Gracias por compartir esta receta tradicional. Los ingredientes y las proporciones son perfectos.', NOW()),
(2, 1, 'Refrescante y muy fácil de preparar. Ideal para el verano.', NOW()),
(3, 2, 'La tortilla de patatas es un clásico que nunca falla. Esta receta es perfecta.', NOW()),
(4, 1, 'Me encanta la crema catalana, y esta receta es muy auténtica.', NOW());

-- Insertar algunos favoritos
INSERT INTO `favorites` (`user_id`, `recipe_id`, `created_at`) VALUES
(1, 2, NOW()),
(1, 3, NOW()),
(2, 1, NOW()),
(2, 4, NOW()),
(2, 5, NOW()); 