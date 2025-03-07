<?php
/**
 * Controlador para la página de inicio
 * 
 * Este controlador maneja la visualización de la página principal
 * de la tienda, mostrando productos destacados y categorías.
 */
class ControladorInicio {
    
    /**
     * Método principal que muestra la página de inicio
     */
    public function index() {
        // Título de la página
        $tituloPagina = 'NaturalShop - Tu tienda de parafarmacia, cosméticos y productos naturales';
        
        // Obtener categorías y productos destacados si la base de datos está activada
        $categorias = [];
        $productosDestacados = [];
        
        if (isset($GLOBALS['bd'])) {
            $categorias = $GLOBALS['bd']->obtenerCategorias();
            $productosDestacados = $GLOBALS['bd']->obtenerProductosDestacados(4);
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
            
            $productosDestacados = [
                [
                    'id' => 1,
                    'nombre' => 'Crema Hidratante Natural',
                    'descripcion_corta' => 'Crema hidratante con ingredientes 100% naturales',
                    'precio' => 19.99,
                    'imagen_principal' => 'imagenes/productos/crema-hidratante.jpg',
                    'categoria_nombre' => 'Cosméticos Naturales'
                ],
                [
                    'id' => 2,
                    'nombre' => 'Vitamina C 1000mg',
                    'descripcion_corta' => 'Suplemento de Vitamina C para reforzar el sistema inmunológico',
                    'precio' => 12.50,
                    'imagen_principal' => 'imagenes/productos/vitamina-c.jpg',
                    'categoria_nombre' => 'Suplementos Alimenticios'
                ],
                [
                    'id' => 3,
                    'nombre' => 'Gel de Aloe Vera',
                    'descripcion_corta' => 'Gel calmante de Aloe Vera para pieles sensibles',
                    'precio' => 9.95,
                    'imagen_principal' => 'imagenes/productos/aloe-vera.jpg',
                    'categoria_nombre' => 'Parafarmacia'
                ],
                [
                    'id' => 4,
                    'nombre' => 'Aceite Esencial de Lavanda',
                    'descripcion_corta' => 'Aceite esencial 100% puro para aromaterapia',
                    'precio' => 15.75,
                    'imagen_principal' => 'imagenes/productos/aceite-lavanda.jpg',
                    'categoria_nombre' => 'Aromaterapia'
                ]
            ];
        }
        
        // Verificar si el usuario está logueado
        $usuarioLogueado = isset($_SESSION['usuario']);
        
        // Cargar la vista de inicio
        include RUTA_APLICACION . '/vistas/inicio.php';
    }
} 