<?php

class ControladorCheckout {
    
    /**
     * Muestra la página de checkout
     */
    public function mostrar() {
        // Inicializar el carrito si no existe
        if (!isset($_SESSION['carrito'])) {
            $_SESSION['carrito'] = [];
        }
        
        // Establecer variable para el menú
        $usuarioLogueado = isset($_SESSION['usuario']);
        
        // Obtener productos del carrito
        $productosCarrito = $_SESSION['carrito'];
        $productosDetalle = [];
        $total = 0;
        
        // Si hay productos en el carrito, obtener sus detalles
        if (!empty($productosCarrito)) {
            // Si hay conexión a la base de datos, obtener detalles de los productos
            if (isset($GLOBALS['bd']) && $GLOBALS['bd']) {
                foreach ($productosCarrito as $idProducto => $cantidad) {
                    $producto = $GLOBALS['bd']->obtenerProductoPorId($idProducto);
                    if ($producto) {
                        $subtotal = $producto['precio'] * $cantidad;
                        $total += $subtotal;
                        
                        $productosDetalle[] = [
                            'id' => $producto['id'],
                            'nombre' => $producto['nombre'],
                            'precio' => $producto['precio'],
                            'imagen' => isset($producto['imagen_principal']) ? $producto['imagen_principal'] : 'imagenes/productos/default.jpg',
                            'cantidad' => $cantidad,
                            'subtotal' => $subtotal
                        ];
                    }
                }
            } else {
                // Usar datos de ejemplo si no hay conexión a la base de datos
                $productosEjemplo = [
                    1 => [
                        'id' => 1, 
                        'nombre' => 'Crema Hidratante Natural', 
                        'precio' => 19.99, 
                        'imagen' => 'imagenes/productos/crema-hidratante.jpg'
                    ],
                    2 => [
                        'id' => 2, 
                        'nombre' => 'Vitamina C 1000mg', 
                        'precio' => 12.50, 
                        'imagen' => 'imagenes/productos/vitamina-c.jpg'
                    ],
                    3 => [
                        'id' => 3, 
                        'nombre' => 'Gel de Aloe Vera', 
                        'precio' => 9.95, 
                        'imagen' => 'imagenes/productos/aloe-vera.jpg'
                    ],
                    4 => [
                        'id' => 4, 
                        'nombre' => 'Aceite Esencial de Lavanda', 
                        'precio' => 15.75, 
                        'imagen' => 'imagenes/productos/aceite-lavanda.jpg'
                    ]
                ];
                
                foreach ($productosCarrito as $idProducto => $cantidad) {
                    if (isset($productosEjemplo[$idProducto])) {
                        $producto = $productosEjemplo[$idProducto];
                        $subtotal = $producto['precio'] * $cantidad;
                        $total += $subtotal;
                        
                        $productosDetalle[] = [
                            'id' => $producto['id'],
                            'nombre' => $producto['nombre'],
                            'precio' => $producto['precio'],
                            'imagen' => isset($producto['imagen_principal']) ? $producto['imagen_principal'] : 'imagenes/productos/default.jpg',
                            'cantidad' => $cantidad,
                            'subtotal' => $subtotal
                        ];
                    }
                }
            }
        }
        
        // Obtener categorías para el menú
        $categorias = [];
        if (isset($GLOBALS['bd']) && $GLOBALS['bd']) {
            $categorias = $GLOBALS['bd']->obtenerCategorias();
        } else {
            // Categorías de ejemplo
            $categorias = [
                ['id' => 1, 'nombre' => 'Parafarmacia', 'descripcion' => 'Productos de parafarmacia'],
                ['id' => 2, 'nombre' => 'Cosméticos Naturales', 'descripcion' => 'Cosméticos con ingredientes naturales'],
                ['id' => 3, 'nombre' => 'Suplementos Alimenticios', 'descripcion' => 'Vitaminas y suplementos']
            ];
        }
        
        // Incluir la vista
        include RUTA_APLICACION . '/vistas/checkout.php';
    }
    
    /**
     * Procesa el pedido
     */
    public function procesar() {
        // Verificar si el usuario está logueado
        if (!isset($_SESSION['usuario'])) {
            $_SESSION['mensaje'] = [
                'tipo' => 'error',
                'texto' => 'Debes iniciar sesión para realizar un pedido.'
            ];
            header('Location: ' . $GLOBALS['configuracion']['rutaBase'] . '/publico/login');
            exit;
        }
        
        // Verificar si hay productos en el carrito
        if (!isset($_SESSION['carrito']) || empty($_SESSION['carrito'])) {
            $_SESSION['mensaje'] = [
                'tipo' => 'error',
                'texto' => 'No hay productos en el carrito para realizar el pedido.'
            ];
            header('Location: ' . $GLOBALS['configuracion']['rutaBase'] . '/publico/carrito');
            exit;
        }
        
        // Validar datos del formulario
        if ($_SERVER['REQUEST_METHOD'] === 'POST') {
            $nombre = isset($_POST['nombre']) ? trim($_POST['nombre']) : '';
            $email = isset($_POST['email']) ? trim($_POST['email']) : '';
            $telefono = isset($_POST['telefono']) ? trim($_POST['telefono']) : '';
            $direccion = isset($_POST['direccion']) ? trim($_POST['direccion']) : '';
            $codigoPostal = isset($_POST['codigoPostal']) ? trim($_POST['codigoPostal']) : '';
            $ciudad = isset($_POST['ciudad']) ? trim($_POST['ciudad']) : '';
            $provincia = isset($_POST['provincia']) ? trim($_POST['provincia']) : '';
            $metodoPago = isset($_POST['metodoPago']) ? trim($_POST['metodoPago']) : '';
            $observaciones = isset($_POST['observaciones']) ? trim($_POST['observaciones']) : '';
            
            // Validar campos obligatorios
            if (empty($nombre) || empty($email) || empty($telefono) || empty($direccion) || 
                empty($codigoPostal) || empty($ciudad) || empty($provincia) || empty($metodoPago)) {
                $_SESSION['mensaje'] = [
                    'tipo' => 'error',
                    'texto' => 'Por favor, completa todos los campos obligatorios.'
                ];
                header('Location: ' . $GLOBALS['configuracion']['rutaBase'] . '/publico/checkout');
                exit;
            }
            
            // Validar email
            if (!filter_var($email, FILTER_VALIDATE_EMAIL)) {
                $_SESSION['mensaje'] = [
                    'tipo' => 'error',
                    'texto' => 'Por favor, introduce un email válido.'
                ];
                header('Location: ' . $GLOBALS['configuracion']['rutaBase'] . '/publico/checkout');
                exit;
            }
            
            // Obtener productos del carrito para el pedido
            $productosCarrito = $_SESSION['carrito'];
            $productosDetalle = [];
            $total = 0;
            
            // Obtener detalles de los productos
            if (isset($GLOBALS['bd']) && $GLOBALS['bd']) {
                // Obtener productos de la base de datos
                foreach ($productosCarrito as $idProducto => $cantidad) {
                    $producto = $GLOBALS['bd']->obtenerProductoPorId($idProducto);
                    if ($producto) {
                        $subtotal = $producto['precio'] * $cantidad;
                        $total += $subtotal;
                        
                        $productosDetalle[] = [
                            'id' => $producto['id'],
                            'nombre' => $producto['nombre'],
                            'precio' => $producto['precio'],
                            'imagen' => isset($producto['imagen_principal']) ? $producto['imagen_principal'] : 'imagenes/productos/default.jpg',
                            'cantidad' => $cantidad,
                            'subtotal' => $subtotal
                        ];
                    }
                }
            } else {
                // Usar datos de ejemplo
                $productosEjemplo = [
                    1 => [
                        'id' => 1, 
                        'nombre' => 'Crema Hidratante Natural', 
                        'precio' => 19.99, 
                        'imagen' => 'imagenes/productos/crema-hidratante.jpg'
                    ],
                    2 => [
                        'id' => 2, 
                        'nombre' => 'Vitamina C 1000mg', 
                        'precio' => 12.50, 
                        'imagen' => 'imagenes/productos/vitamina-c.jpg'
                    ],
                    3 => [
                        'id' => 3, 
                        'nombre' => 'Gel de Aloe Vera', 
                        'precio' => 9.95, 
                        'imagen' => 'imagenes/productos/aloe-vera.jpg'
                    ],
                    4 => [
                        'id' => 4, 
                        'nombre' => 'Aceite Esencial de Lavanda', 
                        'precio' => 15.75, 
                        'imagen' => 'imagenes/productos/aceite-lavanda.jpg'
                    ]
                ];
                
                foreach ($productosCarrito as $idProducto => $cantidad) {
                    if (isset($productosEjemplo[$idProducto])) {
                        $producto = $productosEjemplo[$idProducto];
                        $subtotal = $producto['precio'] * $cantidad;
                        $total += $subtotal;
                        
                        $productosDetalle[] = [
                            'id' => $producto['id'],
                            'nombre' => $producto['nombre'],
                            'precio' => $producto['precio'],
                            'imagen' => isset($producto['imagen_principal']) ? $producto['imagen_principal'] : 'imagenes/productos/default.jpg',
                            'cantidad' => $cantidad,
                            'subtotal' => $subtotal
                        ];
                    }
                }
            }
            
            // Aquí se procesaría el pedido en la base de datos
            if (isset($GLOBALS['bd']) && $GLOBALS['bd']) {
                try {
                    // Generar código único para el pedido
                    $codigo = 'PED' . date('YmdHis') . rand(100, 999);
                    
                    // Datos para la tabla pedidos
                    $datosPedido = [
                        'codigo' => $codigo,
                        'usuario_id' => $_SESSION['usuario']['id'],
                        'estado' => 'pendiente',
                        'metodo_pago' => $metodoPago,
                        'subtotal' => $total,
                        'impuestos' => 0, // Por ahora sin impuestos
                        'gastos_envio' => 0, // Envío gratis
                        'descuento' => 0,
                        'total' => $total,
                        'nombre_envio' => $nombre,
                        'direccion_envio' => $direccion,
                        'codigo_postal_envio' => $codigoPostal,
                        'ciudad_envio' => $ciudad,
                        'provincia_envio' => $provincia,
                        'telefono_envio' => $telefono,
                        'notas' => $observaciones
                    ];
                    
                    // Insertar el pedido
                    $pedidoId = $GLOBALS['bd']->insertarPedido($datosPedido);
                    
                    // Insertar los detalles del pedido
                    foreach ($productosDetalle as $producto) {
                        $detallePedido = [
                            'pedido_id' => $pedidoId,
                            'producto_id' => $producto['id'],
                            'cantidad' => $producto['cantidad'],
                            'precio_unitario' => $producto['precio'],
                            'subtotal' => $producto['subtotal']
                        ];
                        $GLOBALS['bd']->insertarDetallePedido($detallePedido);
                    }
                    
                    // Guardar el ID del pedido en la sesión
                    $_SESSION['ultimo_pedido_id'] = $pedidoId;
                    
                } catch (Exception $e) {
                    $_SESSION['mensaje'] = [
                        'tipo' => 'error',
                        'texto' => 'Error al procesar el pedido: ' . $e->getMessage()
                    ];
                    header('Location: ' . $GLOBALS['configuracion']['rutaBase'] . '/publico/checkout');
                    exit;
                }
            }
            
            // Datos del pedido para mostrar en la confirmación
            $datosPedido = [
                'nombre' => $nombre,
                'email' => $email,
                'telefono' => $telefono,
                'direccion' => $direccion,
                'codigoPostal' => $codigoPostal,
                'ciudad' => $ciudad,
                'provincia' => $provincia,
                'metodoPago' => $metodoPago,
                'observaciones' => $observaciones,
                'productos' => $productosDetalle,
                'total' => $total,
                'fecha' => date('Y-m-d H:i:s')
            ];
            
            // Guardar los datos del pedido en la sesión para mostrarlos en la página de confirmación
            $_SESSION['ultimoPedido'] = $datosPedido;
            
            // Vaciar el carrito después de procesar el pedido
            $_SESSION['carrito'] = [];
            
            // Mensaje de éxito
            $_SESSION['mensaje'] = [
                'tipo' => 'success',
                'texto' => '¡Pedido realizado con éxito! Gracias por tu compra.'
            ];
            
            // Redireccionar a la página de confirmación
            header('Location: ' . $GLOBALS['configuracion']['rutaBase'] . '/publico/estatico/confirmacion-compra.php');
            exit;
        } else {
            // Si no es una petición POST, redireccionar al checkout
            header('Location: ' . $GLOBALS['configuracion']['rutaBase'] . '/publico/checkout');
            exit;
        }
    }
} 