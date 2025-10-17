<?php
/**
 * Controlador para la gestión de pedidos
 * 
 * Este controlador maneja las operaciones relacionadas con pedidos:
 * listar pedidos del usuario, ver detalles de un pedido, etc.
 */
class ControladorPedidos {
    
    /**
     * Muestra el historial de pedidos del usuario
     */
    public function mostrar() {
        // Verificar si el usuario está logueado
        if (!isset($_SESSION['usuario'])) {
            header('Location: ' . $GLOBALS['configuracion']['rutaBase'] . '/publico/login');
            exit;
        }
        
        // Variable para el header
        $usuarioLogueado = true;
        
        $usuarioId = $_SESSION['usuario']['id'];
        $pedidos = [];
        
        // Obtener los pedidos del usuario
        if (isset($GLOBALS['bd']) && $GLOBALS['bd']) {
            try {
                $pedidos = $GLOBALS['bd']->obtenerPedidosUsuario($usuarioId);
                
                // Para cada pedido, obtener sus detalles
                foreach ($pedidos as &$pedido) {
                    $pedido['detalles'] = $GLOBALS['bd']->obtenerDetallesPedido($pedido['id']);
                }
            } catch (Exception $e) {
                $_SESSION['mensaje'] = [
                    'tipo' => 'error',
                    'texto' => 'Error al obtener los pedidos: ' . $e->getMessage()
                ];
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
        
        // Título de la página
        $tituloPagina = 'Mis Pedidos - NaturalShop';
        
        // Cargar la vista de pedidos
        include RUTA_APLICACION . '/vistas/pedidos.php';
    }
    
    public function detalle($id) {
        // Verificar si el usuario está logueado
        if (!isset($_SESSION['usuario'])) {
            header('Location: ' . $GLOBALS['configuracion']['rutaBase'] . '/publico/login');
            exit;
        }
        
        // Variable para el header
        $usuarioLogueado = true;
        
        $usuarioId = $_SESSION['usuario']['id'];
        $pedido = null;
        $detalles = [];
        
        // Obtener los detalles del pedido
        if (isset($GLOBALS['bd']) && $GLOBALS['bd']) {
            try {
                $pedidos = $GLOBALS['bd']->obtenerPedidosUsuario($usuarioId);
                foreach ($pedidos as $p) {
                    if ($p['id'] == $id) {
                        $pedido = $p;
                        $detalles = $GLOBALS['bd']->obtenerDetallesPedido($id);
                        break;
                    }
                }
                
                if (!$pedido) {
                    throw new Exception('Pedido no encontrado o no tienes permiso para verlo.');
                }
            } catch (Exception $e) {
                $_SESSION['mensaje'] = [
                    'tipo' => 'error',
                    'texto' => 'Error al obtener el pedido: ' . $e->getMessage()
                ];
                header('Location: ' . $GLOBALS['configuracion']['rutaBase'] . '/publico/pedidos');
                exit;
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
        include RUTA_APLICACION . '/vistas/pedido-detalle.php';
    }
} 