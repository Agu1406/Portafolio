<?php
/**
 * Controlador para la administración del sitio
 * 
 * Este controlador maneja las funciones administrativas como
 * la gestión de productos, categorías, imágenes, etc.
 */
class ControladorAdmin {
    
    /**
     * Muestra el formulario para subir imágenes
     */
    public function mostrarFormularioImagen() {
        // Verificar si el usuario está autenticado como administrador
        // (Esta parte se implementaría con un sistema de autenticación)
        
        // Título de la página
        $tituloPagina = 'Subir Imagen - Panel de Administración';
        
        // Cargar el modelo de productos para obtener la lista
        if (isset($GLOBALS['bd'])) {
            $productos = $GLOBALS['bd']->obtenerProductos();
        } else {
            // Datos de ejemplo si no hay conexión a la base de datos
            $productos = [
                [
                    'id' => 1,
                    'nombre' => 'Crema Hidratante Natural'
                ],
                [
                    'id' => 2,
                    'nombre' => 'Vitamina C 1000mg'
                ],
                [
                    'id' => 3,
                    'nombre' => 'Gel de Aloe Vera'
                ],
                [
                    'id' => 4,
                    'nombre' => 'Aceite Esencial de Lavanda'
                ]
            ];
        }
        
        // Incluir la vista
        include RUTA_APLICACION . '/vistas/admin/subir_imagen.php';
    }
    
    /**
     * Procesa la subida de una imagen
     */
    public function subirImagen() {
        // Verificar si el usuario está autenticado como administrador
        // (Esta parte se implementaría con un sistema de autenticación)
        
        // Verificar si se ha enviado el formulario
        if ($_SERVER['REQUEST_METHOD'] !== 'POST') {
            header('Location: ' . $GLOBALS['configuracion']['rutaBase'] . '/publico/admin/subir-imagen');
            exit;
        }
        
        // Verificar si se ha seleccionado un producto
        if (!isset($_POST['producto_id']) || empty($_POST['producto_id'])) {
            $mensaje = [
                'tipo' => 'danger',
                'texto' => 'Debes seleccionar un producto.'
            ];
            
            // Volver a mostrar el formulario con el mensaje de error
            $this->mostrarFormularioImagen();
            return;
        }
        
        // Verificar si se ha subido una imagen
        if (!isset($_FILES['imagen']) || $_FILES['imagen']['error'] !== UPLOAD_ERR_OK) {
            $mensaje = [
                'tipo' => 'danger',
                'texto' => 'Error al subir la imagen. Por favor, inténtalo de nuevo.'
            ];
            
            // Volver a mostrar el formulario con el mensaje de error
            $this->mostrarFormularioImagen();
            return;
        }
        
        // Obtener datos del formulario
        $productoId = (int)$_POST['producto_id'];
        $esPrincipal = isset($_POST['es_principal']) && $_POST['es_principal'] == '1';
        
        // Cargar el gestor de imágenes
        require_once RUTA_APLICACION . '/modelos/GestorImagenes.php';
        $gestorImagenes = new GestorImagenes();
        
        // Subir la imagen
        $rutaImagen = $gestorImagenes->subirImagen($_FILES['imagen']);
        
        if ($rutaImagen === false) {
            $mensaje = [
                'tipo' => 'danger',
                'texto' => 'Error al guardar la imagen. Por favor, inténtalo de nuevo.'
            ];
            
            // Volver a mostrar el formulario con el mensaje de error
            $this->mostrarFormularioImagen();
            return;
        }
        
        // Guardar la información en la base de datos
        if (isset($GLOBALS['bd'])) {
            if ($esPrincipal) {
                // Si es la imagen principal, actualizar el producto
                $GLOBALS['bd']->actualizarImagenPrincipal($productoId, $rutaImagen);
            } else {
                // Si no es la imagen principal, añadirla a la tabla de imágenes
                $GLOBALS['bd']->agregarImagenProducto($productoId, $rutaImagen);
            }
        }
        
        // Mensaje de éxito
        $mensaje = [
            'tipo' => 'success',
            'texto' => 'Imagen subida correctamente.'
        ];
        
        // Volver a mostrar el formulario con el mensaje de éxito
        $this->mostrarFormularioImagen();
    }

    public function mostrar() {
        // Verificar si el usuario está logueado y es admin
        if (!isset($_SESSION['usuario']) || $_SESSION['usuario']['rol'] !== 'admin') {
            header('Location: ' . $GLOBALS['configuracion']['rutaBase'] . '/publico/login');
            exit;
        }

        $tituloPagina = 'Panel de Administración';
        $usuarioLogueado = true;

        // Incluir la vista del panel de administración
        include RUTA_APLICACION . '/vistas/admin/panel.php';
    }

    public function productos() {
        // Verificar si el usuario está logueado y es admin
        if (!isset($_SESSION['usuario']) || $_SESSION['usuario']['rol'] !== 'admin') {
            header('Location: ' . $GLOBALS['configuracion']['rutaBase'] . '/publico/login');
            exit;
        }

        $tituloPagina = 'Gestión de Productos';
        $usuarioLogueado = true;

        // Obtener los productos de la base de datos
        $db = new BaseDatos();
        $productos = $db->query("SELECT p.*, c.nombre as categoria_nombre 
                               FROM productos p 
                               LEFT JOIN categorias c ON p.categoria_id = c.id 
                               ORDER BY p.id DESC");

        // Incluir la vista de gestión de productos
        include RUTA_APLICACION . '/vistas/admin/productos.php';
    }

    public function agregarProducto() {
        // Verificar si el usuario está logueado y es admin
        if (!isset($_SESSION['usuario']) || $_SESSION['usuario']['rol'] !== 'admin') {
            header('Location: ' . $GLOBALS['configuracion']['rutaBase'] . '/publico/login');
            exit;
        }

        $tituloPagina = 'Agregar Producto';
        $usuarioLogueado = true;

        // Obtener las categorías para el formulario
        $db = new BaseDatos();
        $categorias = $db->query("SELECT * FROM categorias ORDER BY nombre");

        // Incluir la vista del formulario de producto
        include RUTA_APLICACION . '/vistas/admin/agregar-producto.php';
    }

    public function borrarProducto() {
        // Verificar si el usuario está logueado y es admin
        if (!isset($_SESSION['usuario']) || $_SESSION['usuario']['rol'] !== 'admin') {
            header('Location: ' . $GLOBALS['configuracion']['rutaBase'] . '/publico/login');
            exit;
        }

        if (isset($_POST['id'])) {
            $db = new BaseDatos();
            $id = $_POST['id'];
            
            // Borrar el producto
            $db->query("DELETE FROM productos WHERE id = ?", [$id]);
            
            // Redirigir a la lista de productos
            header('Location: ' . $GLOBALS['configuracion']['rutaBase'] . '/publico/admin/productos?mensaje=producto_eliminado');
            exit;
        }
    }

    public function pedidos() {
        // Verificar si el usuario está logueado y es admin
        if (!isset($_SESSION['usuario']) || $_SESSION['usuario']['rol'] !== 'admin') {
            header('Location: ' . $GLOBALS['configuracion']['rutaBase'] . '/publico/login');
            exit;
        }

        $tituloPagina = 'Gestión de Pedidos';
        $usuarioLogueado = true;

        // Obtener los pedidos de la base de datos
        $db = new BaseDatos();
        $pedidos = $db->query("
            SELECT p.*, u.nombre as nombre_usuario, u.email as email_usuario,
                   (SELECT COUNT(*) FROM detalles_pedido dp WHERE dp.pedido_id = p.id) as total_productos
            FROM pedidos p
            LEFT JOIN usuarios u ON p.usuario_id = u.id
            ORDER BY p.fecha_pedido DESC
        ");

        // Incluir la vista de gestión de pedidos
        include RUTA_APLICACION . '/vistas/admin/pedidos.php';
    }

    public function editarProducto() {
        // Verificar si el usuario está logueado y es admin
        if (!isset($_SESSION['usuario']) || $_SESSION['usuario']['rol'] !== 'admin') {
            header('Location: ' . $GLOBALS['configuracion']['rutaBase'] . '/publico/login');
            exit;
        }

        $tituloPagina = 'Editar Producto';
        $usuarioLogueado = true;

        // Obtener el ID del producto
        $id = $_GET['id'] ?? null;
        if (!$id) {
            header('Location: ' . $GLOBALS['configuracion']['rutaBase'] . '/publico/admin/productos');
            exit;
        }

        // Obtener el producto y las categorías
        $db = new BaseDatos();
        $producto = $db->query("SELECT * FROM productos WHERE id = ?", [$id])[0] ?? null;
        $categorias = $db->query("SELECT * FROM categorias ORDER BY nombre");

        if (!$producto) {
            header('Location: ' . $GLOBALS['configuracion']['rutaBase'] . '/publico/admin/productos');
            exit;
        }

        // Incluir la vista del formulario de edición
        include RUTA_APLICACION . '/vistas/admin/editar-producto.php';
    }
} 