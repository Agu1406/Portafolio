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
        
        // Cargar el gestor de imágenes
        require_once RUTA_APLICACION . '/modelos/GestorImagenes.php';
        $gestorImagenes = new GestorImagenes();
        
        // Obtener el filtro de categoría si existe
        $categoriaId = isset($_GET['categoria']) ? (int)$_GET['categoria'] : null;
        $categoriaActual = null;
        
        // Obtener filtros de precio
        $precioMin = isset($_GET['precio_min']) ? (int)$_GET['precio_min'] : 0;
        $precioMax = isset($_GET['precio_max']) ? (int)$_GET['precio_max'] : 100;
        
        // Obtener orden
        $orden = isset($_GET['orden']) ? $_GET['orden'] : 'nombre_asc';
        
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
            
            // Filtrar por precio
            $productos = array_filter($productos, function($producto) use ($precioMin, $precioMax) {
                return $producto['precio'] >= $precioMin && $producto['precio'] <= $precioMax;
            });
            
            // Ordenar productos
            usort($productos, function($a, $b) use ($orden) {
                switch ($orden) {
                    case 'nombre_asc':
                        return strcmp($a['nombre'], $b['nombre']);
                    case 'nombre_desc':
                        return strcmp($b['nombre'], $a['nombre']);
                    case 'precio_asc':
                        return $a['precio'] <=> $b['precio'];
                    case 'precio_desc':
                        return $b['precio'] <=> $a['precio'];
                    case 'nuevos':
                        // En este caso, asumimos que el ID más alto es el más reciente
                        return $b['id'] <=> $a['id'];
                    default:
                        return strcmp($a['nombre'], $b['nombre']);
                }
            });
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
            
            // Filtrar por precio
            $productos = array_filter($productos, function($producto) use ($precioMin, $precioMax) {
                return $producto['precio'] >= $precioMin && $producto['precio'] <= $precioMax;
            });
            
            // Ordenar productos
            usort($productos, function($a, $b) use ($orden) {
                switch ($orden) {
                    case 'nombre_asc':
                        return strcmp($a['nombre'], $b['nombre']);
                    case 'nombre_desc':
                        return strcmp($b['nombre'], $a['nombre']);
                    case 'precio_asc':
                        return $a['precio'] <=> $b['precio'];
                    case 'precio_desc':
                        return $b['precio'] <=> $a['precio'];
                    case 'nuevos':
                        // En este caso, asumimos que el ID más alto es el más reciente
                        return $b['id'] <=> $a['id'];
                    default:
                        return strcmp($a['nombre'], $b['nombre']);
                }
            });
        }
        
        // Verificar si el usuario está logueado
        $usuarioLogueado = isset($_SESSION['usuario']);
        
        // Cargar la vista de productos
        include RUTA_APLICACION . '/vistas/productos.php';
    }
    
    /**
     * Muestra el detalle de un producto
     */
    public function detalle() {
        // Validar que el ID sea numérico
        $id = isset($_GET['id']) ? $_GET['id'] : null;
        
        if (!is_numeric($id)) {
            // Redirigir a la lista de productos si el ID no es válido
            header('Location: ' . $GLOBALS['configuracion']['rutaPublica'] . '/productos');
            exit;
        }
        
        // Cargar el gestor de imágenes
        require_once RUTA_APLICACION . '/modelos/GestorImagenes.php';
        $gestorImagenes = new GestorImagenes();
        
        // Obtener el producto de la base de datos
        $producto = null;
        $imagenesProducto = [];
        
        try {
            if (isset($GLOBALS['bd'])) {
                $producto = $GLOBALS['bd']->obtenerProducto($id);
                
                // Si el producto no existe, redirigir a la lista de productos
                if (!$producto) {
                    header('Location: ' . $GLOBALS['configuracion']['rutaPublica'] . '/productos');
                    exit;
                }
                
                // Obtener imágenes adicionales del producto
                $imagenesProducto = $gestorImagenes->obtenerImagenesProducto($id);
            }
        } catch (Exception $e) {
            // Si hay un error, usar datos de ejemplo
            $producto = [
                'id' => $id,
                'nombre' => 'Producto de ejemplo ' . $id,
                'descripcion' => 'Esta es una descripción de ejemplo para el producto ' . $id,
                'precio' => 99.99,
                'stock' => 10,
                'categoria_id' => 1,
                'imagen_principal' => 'imagenes/productos/producto_' . $id . '.txt'
            ];
            
            // Crear imágenes de ejemplo
            $gestorImagenes->crearImagenesEjemplo($id);
            
            // Obtener imágenes adicionales del producto
            $imagenesProducto = $gestorImagenes->obtenerImagenesProducto($id);
        }
        
        // Obtener productos relacionados
        $productosRelacionados = [];
        
        try {
            if (isset($GLOBALS['bd'])) {
                $productosRelacionados = $GLOBALS['bd']->obtenerProductosRelacionados($producto['categoria_id'], $id, 4);
            }
        } catch (Exception $e) {
            // Si hay un error, usar datos de ejemplo
            for ($i = 1; $i <= 4; $i++) {
                $productosRelacionados[] = [
                    'id' => $i,
                    'nombre' => 'Producto relacionado ' . $i,
                    'precio' => 49.99 + $i,
                    'imagen_principal' => 'imagenes/productos/default.jpg'
                ];
            }
        }
        
        // Obtener categorías para el menú
        $categorias = [];
        
        try {
            if (isset($GLOBALS['bd'])) {
                $categorias = $GLOBALS['bd']->obtenerCategorias();
            }
        } catch (Exception $e) {
            // Si hay un error, usar datos de ejemplo
            for ($i = 1; $i <= 5; $i++) {
                $categorias[] = [
                    'id' => $i,
                    'nombre' => 'Categoría ' . $i
                ];
            }
        }
        
        // Incluir la vista
        include RUTA_APLICACION . '/vistas/producto_detalle.php';
    }
} 