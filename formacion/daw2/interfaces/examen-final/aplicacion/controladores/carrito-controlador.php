<?php

class CarritoControlador {
    private $baseDatos;

    public function __construct() {
        // Usar la instancia global de BaseDatos
        $this->baseDatos = $GLOBALS['bd'];
        
        // Inicializar el carrito si no existe
        if (!isset($_SESSION['carrito'])) {
            $_SESSION['carrito'] = [
                'productos' => [],
                'total' => 0,
                'cantidad' => 0
            ];
        }
    }

    /**
     * Muestra el contenido del carrito
     */
    public function mostrar() {
        $carrito = $_SESSION['carrito'];
        
        // Obtener información detallada de los productos en el carrito
        $productosDetalle = [];
        $total = 0;
        
        foreach ($carrito['productos'] as $productoId => $item) {
            $producto = $this->baseDatos->obtenerProductoPorId($productoId);
            
            if ($producto) {
                $subtotal = $producto['precio'] * $item['cantidad'];
                $total += $subtotal;
                
                // Determinar el nombre de la imagen basado en el nombre del producto
                $nombreImagen = 'default.jpg';
                switch(strtolower($producto['nombre'])) {
                    case 'aceite esencial de lavanda':
                        $nombreImagen = 'aceite-lavanda.jpg';
                        break;
                    case 'vitamina c 1000mg':
                        $nombreImagen = 'vitamina-c.jpg';
                        break;
                    case 'crema hidratante natural':
                        $nombreImagen = 'crema-hidratante.jpg';
                        break;
                    case 'gel de aloe vera':
                        $nombreImagen = 'aloe-vera.jpg';
                        break;
                }
                $rutaImagen = 'imagenes/productos/' . $nombreImagen;
                
                $productosDetalle[] = [
                    'id' => $productoId,
                    'nombre' => $producto['nombre'],
                    'precio' => $producto['precio'],
                    'imagen' => $rutaImagen,
                    'cantidad' => $item['cantidad'],
                    'subtotal' => $subtotal
                ];
            }
        }
        
        // Actualizar el total en la sesión
        $_SESSION['carrito']['total'] = $total;
        
        // Cargar vista
        require_once RUTA_APLICACION . '/vistas/carrito.php';
    }
    
    /**
     * Agrega un producto al carrito
     * 
     * @param int|null $productoId ID del producto a agregar
     * @param int|null $cantidad Cantidad a agregar
     */
    public function agregar($productoId = null, $cantidad = null) {
        // Verificar si se recibieron los datos necesarios
        if ($productoId === null) {
            $productoId = isset($_POST['idProducto']) ? (int)$_POST['idProducto'] : 0;
        }
        
        if ($cantidad === null) {
            $cantidad = isset($_POST['cantidad']) ? (int)$_POST['cantidad'] : 1;
        }
        
        if ($productoId <= 0) {
            $this->redirigirConMensaje('Error: Datos incompletos', 'error');
            return;
        }
        
        // Validar cantidad
        if ($cantidad <= 0) {
            $cantidad = 1;
        }
        
        // Verificar si el producto existe
        $producto = $this->baseDatos->obtenerProductoPorId($productoId);
        
        if (!$producto) {
            $this->redirigirConMensaje('Error: El producto no existe', 'error');
            return;
        }
        
        // Verificar stock (si existe el campo en la base de datos)
        if (isset($producto['stock']) && $producto['stock'] < $cantidad) {
            $this->redirigirConMensaje('Error: No hay suficiente stock disponible', 'error');
            return;
        }
        
        // Agregar o actualizar el producto en el carrito
        if (isset($_SESSION['carrito']['productos'][$productoId])) {
            // El producto ya está en el carrito, actualizar cantidad
            $_SESSION['carrito']['productos'][$productoId]['cantidad'] += $cantidad;
        } else {
            // Nuevo producto en el carrito
            $_SESSION['carrito']['productos'][$productoId] = [
                'cantidad' => $cantidad
            ];
        }
        
        // Actualizar cantidad total de productos
        $cantidadTotal = 0;
        foreach ($_SESSION['carrito']['productos'] as $item) {
            $cantidadTotal += $item['cantidad'];
        }
        $_SESSION['carrito']['cantidad'] = $cantidadTotal;
        
        // Redirigir al carrito con mensaje de éxito
        $this->redirigirConMensaje('Producto añadido al carrito correctamente', 'success');
    }
    
    /**
     * Actualiza la cantidad de un producto en el carrito
     * 
     * @param int|null $productoId ID del producto a actualizar
     * @param int|null $cantidad Nueva cantidad
     */
    public function actualizar($productoId = null, $cantidad = null) {
        // Verificar si se recibieron los datos necesarios
        if ($productoId === null) {
            $productoId = isset($_POST['idProducto']) ? (int)$_POST['idProducto'] : 0;
        }
        
        if ($cantidad === null) {
            $cantidad = isset($_POST['cantidad']) ? (int)$_POST['cantidad'] : 0;
        }
        
        if ($productoId <= 0) {
            $this->redirigirConMensaje('Error: Datos incompletos', 'error');
            return;
        }
        
        // Validar cantidad
        if ($cantidad <= 0) {
            // Si la cantidad es 0 o negativa, eliminar el producto
            $this->eliminar($productoId);
            return;
        }
        
        // Verificar si el producto existe en el carrito
        if (!isset($_SESSION['carrito']['productos'][$productoId])) {
            $this->redirigirConMensaje('Error: El producto no está en el carrito', 'error');
            return;
        }
        
        // Verificar stock (si existe el campo en la base de datos)
        $producto = $this->baseDatos->obtenerProductoPorId($productoId);
        if (isset($producto['stock']) && $producto['stock'] < $cantidad) {
            $this->redirigirConMensaje('Error: No hay suficiente stock disponible', 'error');
            return;
        }
        
        // Actualizar cantidad
        $_SESSION['carrito']['productos'][$productoId]['cantidad'] = $cantidad;
        
        // Actualizar cantidad total de productos
        $cantidadTotal = 0;
        foreach ($_SESSION['carrito']['productos'] as $item) {
            $cantidadTotal += $item['cantidad'];
        }
        $_SESSION['carrito']['cantidad'] = $cantidadTotal;
        
        // Redirigir al carrito con mensaje de éxito
        $this->redirigirConMensaje('Carrito actualizado correctamente', 'success');
    }
    
    /**
     * Elimina un producto del carrito
     * 
     * @param int|null $productoId ID del producto a eliminar
     */
    public function eliminar($productoId = null) {
        // Verificar si se recibió el ID del producto
        if ($productoId === null) {
            $productoId = isset($_POST['idProducto']) ? (int)$_POST['idProducto'] : 
                         (isset($_GET['id']) ? (int)$_GET['id'] : 0);
        }
        
        if ($productoId <= 0) {
            $this->redirigirConMensaje('Error: Datos incompletos', 'error');
            return;
        }
        
        // Verificar si el producto existe en el carrito
        if (!isset($_SESSION['carrito']['productos'][$productoId])) {
            $this->redirigirConMensaje('Error: El producto no está en el carrito', 'error');
            return;
        }
        
        // Eliminar el producto del carrito
        unset($_SESSION['carrito']['productos'][$productoId]);
        
        // Actualizar cantidad total de productos
        $cantidadTotal = 0;
        foreach ($_SESSION['carrito']['productos'] as $item) {
            $cantidadTotal += $item['cantidad'];
        }
        $_SESSION['carrito']['cantidad'] = $cantidadTotal;
        
        // Redirigir al carrito con mensaje de éxito
        $this->redirigirConMensaje('Producto eliminado del carrito correctamente', 'success');
    }
    
    /**
     * Vacía el carrito
     */
    public function vaciar() {
        // Reiniciar el carrito
        $_SESSION['carrito'] = [
            'productos' => [],
            'total' => 0,
            'cantidad' => 0
        ];
        
        // Redirigir al carrito con mensaje de éxito
        $this->redirigirConMensaje('Carrito vaciado correctamente', 'success');
    }
    
    /**
     * Redirige a una página con un mensaje
     * 
     * @param string $mensaje Mensaje a mostrar
     * @param string $tipo Tipo de mensaje (success, error, warning, info)
     * @param string $ruta Ruta a la que redirigir (por defecto, al carrito)
     */
    private function redirigirConMensaje($mensaje, $tipo = 'info', $ruta = '/carrito') {
        $_SESSION['mensaje'] = [
            'texto' => $mensaje,
            'tipo' => $tipo
        ];
        
        header('Location: ' . $GLOBALS['configuracion']['rutaBase'] . $ruta);
        exit;
    }
} 