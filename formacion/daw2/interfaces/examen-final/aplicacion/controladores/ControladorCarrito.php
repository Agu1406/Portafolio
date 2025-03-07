<?php
/**
 * Controlador para la gestión del carrito de compras
 * 
 * Este controlador maneja todas las operaciones relacionadas con el carrito:
 * mostrar, agregar productos, actualizar cantidades, eliminar productos, etc.
 */
class ControladorCarrito {
    
    /**
     * Muestra el contenido del carrito
     */
    public function mostrar() {
        // Título de la página
        $tituloPagina = 'Carrito de Compras - NaturalShop';
        
        // Inicializar el carrito si no existe
        if (!isset($_SESSION['carrito'])) {
            $_SESSION['carrito'] = [];
        }
        
        // Obtener productos del carrito
        $productosCarrito = [];
        $total = 0;
        
        if (isset($GLOBALS['bd']) && !empty($_SESSION['carrito'])) {
            // Obtener detalles de productos desde la base de datos
            foreach ($_SESSION['carrito'] as $idProducto => $cantidad) {
                $producto = $GLOBALS['bd']->obtenerProductoPorId($idProducto);
                if ($producto) {
                    $producto['cantidad'] = $cantidad;
                    $producto['subtotal'] = $producto['precio'] * $cantidad;
                    $productosCarrito[] = $producto;
                    $total += $producto['subtotal'];
                }
            }
        } else {
            // Datos de ejemplo si no hay conexión a la base de datos
            if (!empty($_SESSION['carrito'])) {
                $productosEjemplo = [
                    1 => [
                        'id' => 1,
                        'nombre' => 'Crema Hidratante Natural',
                        'descripcion_corta' => 'Crema hidratante con ingredientes 100% naturales',
                        'precio' => 19.99,
                        'imagen_principal' => 'imagenes/productos/crema-hidratante.jpg'
                    ],
                    2 => [
                        'id' => 2,
                        'nombre' => 'Vitamina C 1000mg',
                        'descripcion_corta' => 'Suplemento de Vitamina C para reforzar el sistema inmunológico',
                        'precio' => 12.50,
                        'imagen_principal' => 'imagenes/productos/vitamina-c.jpg'
                    ],
                    3 => [
                        'id' => 3,
                        'nombre' => 'Gel de Aloe Vera',
                        'descripcion_corta' => 'Gel calmante de Aloe Vera para pieles sensibles',
                        'precio' => 9.95,
                        'imagen_principal' => 'imagenes/productos/aloe-vera.jpg'
                    ],
                    4 => [
                        'id' => 4,
                        'nombre' => 'Aceite Esencial de Lavanda',
                        'descripcion_corta' => 'Aceite esencial 100% puro para aromaterapia',
                        'precio' => 15.75,
                        'imagen_principal' => 'imagenes/productos/aceite-lavanda.jpg'
                    ]
                ];
                
                foreach ($_SESSION['carrito'] as $idProducto => $cantidad) {
                    if (isset($productosEjemplo[$idProducto])) {
                        $producto = $productosEjemplo[$idProducto];
                        $producto['cantidad'] = $cantidad;
                        $producto['subtotal'] = $producto['precio'] * $cantidad;
                        $productosCarrito[] = $producto;
                        $total += $producto['subtotal'];
                    }
                }
            }
        }
        
        // Obtener categorías para el menú
        $categorias = [];
        if (isset($GLOBALS['bd'])) {
            $categorias = $GLOBALS['bd']->obtenerCategorias();
        } else {
            // Categorías de ejemplo
            $categorias = [
                ['id' => 1, 'nombre' => 'Parafarmacia'],
                ['id' => 2, 'nombre' => 'Cosméticos Naturales'],
                ['id' => 3, 'nombre' => 'Suplementos Alimenticios']
            ];
        }
        
        // Verificar si el usuario está logueado
        $usuarioLogueado = isset($_SESSION['usuario']);
        
        // Cargar la vista del carrito
        include RUTA_APLICACION . '/vistas/carrito.php';
    }
    
    /**
     * Agrega un producto al carrito
     * 
     * @param int $productoId ID del producto a agregar
     * @param int $cantidad Cantidad del producto (por defecto 1)
     */
    public function agregar($productoId, $cantidad = 1) {
        // Validar parámetros
        if (!$productoId || !is_numeric($productoId) || $cantidad < 1) {
            header('Location: ' . $GLOBALS['configuracion']['rutaBase'] . '/carrito');
            exit;
        }
        
        // Convertir a enteros
        $productoId = (int)$productoId;
        $cantidad = (int)$cantidad;
        
        // Verificar si el producto existe
        $productoExiste = false;
        
        if (isset($GLOBALS['bd'])) {
            $producto = $GLOBALS['bd']->obtenerProductoPorId($productoId);
            $productoExiste = ($producto !== null);
        } else {
            // Simulación para desarrollo
            $productoExiste = in_array($productoId, [1, 2, 3, 4]);
        }
        
        if (!$productoExiste) {
            header('Location: ' . $GLOBALS['configuracion']['rutaBase'] . '/carrito');
            exit;
        }
        
        // Inicializar el carrito si no existe
        if (!isset($_SESSION['carrito'])) {
            $_SESSION['carrito'] = [];
        }
        
        // Agregar o actualizar el producto en el carrito
        if (isset($_SESSION['carrito'][$productoId])) {
            // Si ya existe, sumar la cantidad
            $_SESSION['carrito'][$productoId] += $cantidad;
        } else {
            // Si no existe, agregarlo
            $_SESSION['carrito'][$productoId] = $cantidad;
        }
        
        // Redirigir al carrito
        header('Location: ' . $GLOBALS['configuracion']['rutaBase'] . '/carrito');
        exit;
    }
    
    /**
     * Actualiza la cantidad de un producto en el carrito
     * 
     * @param int $productoId ID del producto a actualizar
     * @param int $cantidad Nueva cantidad del producto
     */
    public function actualizar($productoId, $cantidad) {
        // Validar parámetros
        if (!$productoId || !is_numeric($productoId) || !is_numeric($cantidad)) {
            header('Location: ' . $GLOBALS['configuracion']['rutaBase'] . '/carrito');
            exit;
        }
        
        // Convertir a enteros
        $productoId = (int)$productoId;
        $cantidad = (int)$cantidad;
        
        // Verificar si el carrito existe
        if (!isset($_SESSION['carrito'])) {
            header('Location: ' . $GLOBALS['configuracion']['rutaBase'] . '/carrito');
            exit;
        }
        
        // Actualizar la cantidad o eliminar si es 0
        if ($cantidad > 0) {
            $_SESSION['carrito'][$productoId] = $cantidad;
        } else {
            $this->eliminar($productoId);
            return;
        }
        
        // Redirigir al carrito
        header('Location: ' . $GLOBALS['configuracion']['rutaBase'] . '/carrito');
        exit;
    }
    
    /**
     * Elimina un producto del carrito
     * 
     * @param int $productoId ID del producto a eliminar
     */
    public function eliminar($productoId) {
        // Validar parámetros
        if (!$productoId || !is_numeric($productoId)) {
            header('Location: ' . $GLOBALS['configuracion']['rutaBase'] . '/carrito');
            exit;
        }
        
        // Convertir a entero
        $productoId = (int)$productoId;
        
        // Verificar si el carrito existe
        if (!isset($_SESSION['carrito'])) {
            header('Location: ' . $GLOBALS['configuracion']['rutaBase'] . '/carrito');
            exit;
        }
        
        // Eliminar el producto del carrito
        if (isset($_SESSION['carrito'][$productoId])) {
            unset($_SESSION['carrito'][$productoId]);
        }
        
        // Redirigir al carrito
        header('Location: ' . $GLOBALS['configuracion']['rutaBase'] . '/carrito');
        exit;
    }
    
    /**
     * Vacía completamente el carrito
     */
    public function vaciar() {
        // Vaciar el carrito
        $_SESSION['carrito'] = [];
        
        // Redirigir al carrito
        header('Location: ' . $GLOBALS['configuracion']['rutaBase'] . '/carrito');
        exit;
    }
} 