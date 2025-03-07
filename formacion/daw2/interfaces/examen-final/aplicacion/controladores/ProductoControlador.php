<?php

class ProductoControlador {
    private $baseDatos;

    public function __construct() {
        // Usar la instancia global de BaseDatos
        $this->baseDatos = $GLOBALS['bd'];
    }

    /**
     * Muestra el listado de productos
     * Permite filtrar por categoría, precio y ordenar por diferentes criterios
     */
    public function listar() {
        // Obtener parámetros de filtrado
        $categoriaId = isset($_GET['categoria']) ? (int)$_GET['categoria'] : null;
        
        // Obtener productos según filtros
        if ($categoriaId) {
            $productos = $this->baseDatos->obtenerProductosPorCategoria($categoriaId);
            $categoriaActual = $this->obtenerCategoriaPorId($categoriaId);
        } else {
            $productos = $this->baseDatos->obtenerProductos();
            $categoriaActual = null;
        }
        
        // Obtener todas las categorías para el menú lateral
        $categorias = $this->baseDatos->obtenerCategorias();
        
        // Cargar vista
        require_once RUTA_APLICACION . '/vistas/productos.php';
    }
    
    /**
     * Muestra el detalle de un producto específico
     */
    public function detalle($id = null) {
        // Obtener ID del producto si no se pasó como parámetro
        if ($id === null) {
            $id = isset($_GET['id']) ? (int)$_GET['id'] : 0;
        }
        
        if ($id <= 0) {
            // Redirigir a la lista de productos si no hay ID válido
            header('Location: ' . $GLOBALS['configuracion']['rutaBase'] . '/productos');
            exit;
        }
        
        // Obtener información del producto
        $producto = $this->baseDatos->obtenerProductoPorId($id);
        
        if (!$producto) {
            // El producto no existe, pero mostraremos la vista con un mensaje de error
            require_once RUTA_APLICACION . '/vistas/producto_detalle.php';
            return;
        }
        
        // Obtener productos relacionados (de la misma categoría)
        $productosRelacionados = $this->baseDatos->obtenerProductosRelacionados($producto['categoria_id'], $id, 3);
        
        // Cargar vista
        require_once RUTA_APLICACION . '/vistas/producto_detalle.php';
    }
    
    /**
     * Busca productos según término de búsqueda
     */
    public function buscar() {
        $termino = isset($_GET['q']) ? $_GET['q'] : '';
        
        if (empty($termino)) {
            header('Location: ' . $GLOBALS['configuracion']['rutaBase'] . '/productos');
            exit;
        }
        
        // Redirigir a la lista de productos con el parámetro de búsqueda
        header('Location: ' . $GLOBALS['configuracion']['rutaBase'] . '/productos?busqueda=' . urlencode($termino));
        exit;
    }
    
    /**
     * Obtiene una categoría por su ID
     * 
     * @param int $id ID de la categoría
     * @return array|null Datos de la categoría o null si no existe
     */
    private function obtenerCategoriaPorId($id) {
        $categorias = $this->baseDatos->obtenerCategorias();
        
        foreach ($categorias as $categoria) {
            if ($categoria['id'] == $id) {
                return $categoria;
            }
        }
        
        return null;
    }
} 