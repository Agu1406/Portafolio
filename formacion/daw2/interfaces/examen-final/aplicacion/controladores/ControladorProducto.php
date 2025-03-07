<?php
/**
 * Controlador para la gestión de productos
 * 
 * Este controlador maneja la visualización de productos,
 * tanto el listado como los detalles de cada producto.
 */
class ControladorProducto {
    
    /**
     * Muestra el listado de productos
     * Opcionalmente filtra por categoría si se proporciona el parámetro
     */
    public function listar() {
        // Título de la página
        $tituloPagina = 'Productos - NaturalShop';
        
        // Obtener el filtro de categoría si existe
        $categoriaId = isset($_GET['categoria']) ? (int)$_GET['categoria'] : null;
        $categoriaActual = null;
        
        // Obtener productos y categorías si la base de datos está activada
        $productos = [];
        $categorias = [];
        
        if (isset($GLOBALS['bd'])) {
            $categorias = $GLOBALS['bd']->obtenerCategorias();
            
            // Si hay filtro de categoría, obtener productos de esa categoría
            if ($categoriaId) {
                $productos = $GLOBALS['bd']->obtenerProductosPorCategoria($categoriaId);
                
                // Buscar el nombre de la categoría actual
                foreach ($categorias as $categoria) {
                    if ($categoria['id'] == $categoriaId) {
                        $categoriaActual = $categoria;
                        break;
                    }
                }
            } else {
                // Si no hay filtro, obtener todos los productos
                $productos = $GLOBALS['bd']->obtenerProductos();
            }
        } else {
            // Datos de ejemplo si no hay conexión a la base de datos
            $categorias = [
                [
                    'id' => 1,
                    'nombre' => 'Parafarmacia',
                    'descripcion' => 'Productos para el cuidado de la salud',
                    'imagen' => 'imagenes/categorias/parafarmacia.jpg'
                ],
                [
                    'id' => 2,
                    'nombre' => 'Cosméticos Naturales',
                    'descripcion' => 'Belleza con ingredientes naturales',
                    'imagen' => 'imagenes/categorias/cosmeticos.jpg'
                ],
                [
                    'id' => 3,
                    'nombre' => 'Suplementos Alimenticios',
                    'descripcion' => 'Complementos para una vida saludable',
                    'imagen' => 'imagenes/categorias/suplementos.jpg'
                ]
            ];
            
            // Productos de ejemplo
            $todosProductos = [
                [
                    'id' => 1,
                    'nombre' => 'Crema Hidratante Natural',
                    'descripcion_corta' => 'Crema hidratante con ingredientes 100% naturales',
                    'precio' => 19.99,
                    'imagen_principal' => 'imagenes/productos/crema-hidratante.jpg',
                    'categoria_id' => 2,
                    'categoria_nombre' => 'Cosméticos Naturales'
                ],
                [
                    'id' => 2,
                    'nombre' => 'Vitamina C 1000mg',
                    'descripcion_corta' => 'Suplemento de Vitamina C para reforzar el sistema inmunológico',
                    'precio' => 12.50,
                    'imagen_principal' => 'imagenes/productos/vitamina-c.jpg',
                    'categoria_id' => 3,
                    'categoria_nombre' => 'Suplementos Alimenticios'
                ],
                [
                    'id' => 3,
                    'nombre' => 'Gel de Aloe Vera',
                    'descripcion_corta' => 'Gel calmante de Aloe Vera para pieles sensibles',
                    'precio' => 9.95,
                    'imagen_principal' => 'imagenes/productos/aloe-vera.jpg',
                    'categoria_id' => 1,
                    'categoria_nombre' => 'Parafarmacia'
                ],
                [
                    'id' => 4,
                    'nombre' => 'Aceite Esencial de Lavanda',
                    'descripcion_corta' => 'Aceite esencial 100% puro para aromaterapia',
                    'precio' => 15.75,
                    'imagen_principal' => 'imagenes/productos/aceite-lavanda.jpg',
                    'categoria_id' => 4,
                    'categoria_nombre' => 'Aromaterapia'
                ]
            ];
            
            // Filtrar productos por categoría si se especifica
            if ($categoriaId) {
                $productos = array_filter($todosProductos, function($producto) use ($categoriaId) {
                    return $producto['categoria_id'] == $categoriaId;
                });
                
                // Buscar el nombre de la categoría actual
                foreach ($categorias as $categoria) {
                    if ($categoria['id'] == $categoriaId) {
                        $categoriaActual = $categoria;
                        break;
                    }
                }
            } else {
                $productos = $todosProductos;
            }
        }
        
        // Verificar si el usuario está logueado
        $usuarioLogueado = isset($_SESSION['usuario']);
        
        // Cargar la vista de productos
        include RUTA_APLICACION . '/vistas/productos.php';
    }
    
    /**
     * Muestra el detalle de un producto específico
     * 
     * @param int $id ID del producto a mostrar
     */
    public function detalle($id) {
        // Validar que el ID sea un número
        if (!$id || !is_numeric($id)) {
            // Redirigir a la lista de productos si no hay ID válido
            header('Location: ' . $GLOBALS['configuracion']['rutaBase'] . '/productos');
            exit;
        }
        
        $producto = null;
        $productosRelacionados = [];
        $categorias = [];
        
        if (isset($GLOBALS['bd'])) {
            // Obtener el producto de la base de datos
            $producto = $GLOBALS['bd']->obtenerProductoPorId($id);
            
            // Si el producto no existe, mostrar página 404
            if (!$producto) {
                header('HTTP/1.0 404 Not Found');
                include RUTA_APLICACION . '/vistas/404.php';
                exit;
            }
            
            // Obtener productos relacionados (de la misma categoría)
            $productosRelacionados = $GLOBALS['bd']->obtenerProductosRelacionados($producto['categoria_id'], $id, 3);
            
            // Obtener categorías para el menú
            $categorias = $GLOBALS['bd']->obtenerCategorias();
        } else {
            // Datos de ejemplo si no hay conexión a la base de datos
            $todosProductos = [
                [
                    'id' => 1,
                    'nombre' => 'Crema Hidratante Natural',
                    'descripcion_corta' => 'Crema hidratante con ingredientes 100% naturales',
                    'descripcion' => 'Nuestra crema hidratante está formulada con ingredientes naturales como aloe vera, aceite de jojoba y manteca de karité. Hidrata profundamente la piel sin dejar sensación grasa. Ideal para todo tipo de pieles, incluso las más sensibles.',
                    'precio' => 19.99,
                    'imagen_principal' => 'imagenes/productos/crema-hidratante.jpg',
                    'categoria_id' => 2,
                    'categoria_nombre' => 'Cosméticos Naturales'
                ],
                [
                    'id' => 2,
                    'nombre' => 'Vitamina C 1000mg',
                    'descripcion_corta' => 'Suplemento de Vitamina C para reforzar el sistema inmunológico',
                    'descripcion' => 'Suplemento de Vitamina C de alta absorción que ayuda a fortalecer el sistema inmunológico, combatir el estrés oxidativo y promover la producción de colágeno. Cada tableta contiene 1000mg de Vitamina C pura.',
                    'precio' => 12.50,
                    'imagen_principal' => 'imagenes/productos/vitamina-c.jpg',
                    'categoria_id' => 3,
                    'categoria_nombre' => 'Suplementos Alimenticios'
                ],
                [
                    'id' => 3,
                    'nombre' => 'Gel de Aloe Vera',
                    'descripcion_corta' => 'Gel calmante de Aloe Vera para pieles sensibles',
                    'descripcion' => 'Gel puro de Aloe Vera con propiedades calmantes e hidratantes. Ideal para después del sol, irritaciones leves o como base hidratante diaria. Contiene 99% de Aloe Vera orgánico sin parabenos ni colorantes artificiales.',
                    'precio' => 9.95,
                    'imagen_principal' => 'imagenes/productos/aloe-vera.jpg',
                    'categoria_id' => 1,
                    'categoria_nombre' => 'Parafarmacia'
                ],
                [
                    'id' => 4,
                    'nombre' => 'Aceite Esencial de Lavanda',
                    'descripcion_corta' => 'Aceite esencial 100% puro para aromaterapia',
                    'descripcion' => 'Aceite esencial de lavanda 100% puro y natural, ideal para aromaterapia, masajes relajantes o para añadir a difusores. Ayuda a reducir el estrés, mejorar el sueño y calmar la piel irritada.',
                    'precio' => 15.75,
                    'imagen_principal' => 'imagenes/productos/aceite-lavanda.jpg',
                    'categoria_id' => 4,
                    'categoria_nombre' => 'Aromaterapia'
                ]
            ];
            
            // Buscar el producto por ID
            foreach ($todosProductos as $p) {
                if ($p['id'] == $id) {
                    $producto = $p;
                    break;
                }
            }
            
            // Si el producto no existe, mostrar página 404
            if (!$producto) {
                header('HTTP/1.0 404 Not Found');
                include RUTA_APLICACION . '/vistas/404.php';
                exit;
            }
            
            // Obtener productos relacionados (de la misma categoría)
            $productosRelacionados = array_filter($todosProductos, function($p) use ($producto, $id) {
                return $p['categoria_id'] == $producto['categoria_id'] && $p['id'] != $id;
            });
            
            // Limitar a 3 productos relacionados
            $productosRelacionados = array_slice($productosRelacionados, 0, 3);
            
            // Categorías de ejemplo para el menú
            $categorias = [
                [
                    'id' => 1,
                    'nombre' => 'Parafarmacia',
                    'descripcion' => 'Productos para el cuidado de la salud',
                    'imagen' => 'imagenes/categorias/parafarmacia.jpg'
                ],
                [
                    'id' => 2,
                    'nombre' => 'Cosméticos Naturales',
                    'descripcion' => 'Belleza con ingredientes naturales',
                    'imagen' => 'imagenes/categorias/cosmeticos.jpg'
                ],
                [
                    'id' => 3,
                    'nombre' => 'Suplementos Alimenticios',
                    'descripcion' => 'Complementos para una vida saludable',
                    'imagen' => 'imagenes/categorias/suplementos.jpg'
                ]
            ];
        }
        
        // Título de la página con el nombre del producto
        $tituloPagina = $producto['nombre'] . ' - NaturalShop';
        
        // Verificar si el usuario está logueado
        $usuarioLogueado = isset($_SESSION['usuario']);
        
        // Cargar la vista de detalle de producto
        include RUTA_APLICACION . '/vistas/producto_detalle.php';
    }
} 