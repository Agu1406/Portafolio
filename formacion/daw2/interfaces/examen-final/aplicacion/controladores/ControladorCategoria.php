<?php
/**
 * Controlador para la gestión de categorías
 * 
 * Este controlador maneja la visualización de categorías
 * y la redirección a productos filtrados por categoría.
 */
class ControladorCategoria {
    
    /**
     * Muestra el listado de todas las categorías
     */
    public function listar() {
        // Título de la página
        $tituloPagina = 'Categorías - NaturalShop';
        
        // Obtener categorías si la base de datos está activada
        $categorias = [];
        
        if (isset($GLOBALS['bd'])) {
            $categorias = $GLOBALS['bd']->obtenerCategorias();
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
                ],
                [
                    'id' => 4,
                    'nombre' => 'Aromaterapia',
                    'descripcion' => 'Aceites esenciales y difusores',
                    'imagen' => 'imagenes/categorias/aromaterapia.jpg'
                ],
                [
                    'id' => 5,
                    'nombre' => 'Herbolario',
                    'descripcion' => 'Plantas medicinales y tés',
                    'imagen' => 'imagenes/categorias/herbolario.jpg'
                ]
            ];
        }
        
        // Verificar si el usuario está logueado
        $usuarioLogueado = isset($_SESSION['usuario']);
        
        // Cargar la vista de categorías
        include RUTA_APLICACION . '/vistas/categorias.php';
    }
    
    /**
     * Redirige a la página de productos filtrados por categoría
     * 
     * @param int $id ID de la categoría
     */
    public function mostrarProductos($id) {
        // Validar que el ID sea un número
        if (!$id || !is_numeric($id)) {
            // Redirigir a la lista de categorías si no hay ID válido
            header('Location: ' . $GLOBALS['configuracion']['rutaBase'] . '/categorias');
            exit;
        }
        
        // Redirigir a la página de productos con el filtro de categoría
        header('Location: ' . $GLOBALS['configuracion']['rutaBase'] . '/productos?categoria=' . $id);
        exit;
    }
} 