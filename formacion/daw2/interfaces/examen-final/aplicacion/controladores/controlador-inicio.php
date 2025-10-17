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
        // Cargar el gestor de imágenes
        require_once RUTA_APLICACION . '/modelos/GestorImagenes.php';
        $gestorImagenes = new GestorImagenes();
        
        // Obtener productos destacados
        $productoDestacados = [];
        
        try {
            if (isset($GLOBALS['bd'])) {
                $productoDestacados = $GLOBALS['bd']->obtenerProductosDestacados(8);
            }
        } catch (Exception $e) {
            // Si hay un error, usar datos de ejemplo
            for ($i = 1; $i <= 8; $i++) {
                $productoDestacados[] = [
                    'id' => $i,
                    'nombre' => 'Producto destacado ' . $i,
                    'descripcion_corta' => 'Descripción corta del producto ' . $i,
                    'precio' => 19.99 + $i,
                    'imagen_principal' => 'imagenes/productos/producto_' . $i . '.txt'
                ];
                
                // Crear imágenes de ejemplo
                $gestorImagenes->crearImagenesEjemplo($i);
            }
        }
        
        // Obtener categorías
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
                    'nombre' => 'Categoría ' . $i,
                    'descripcion' => 'Descripción de la categoría ' . $i,
                    'imagen' => 'imagenes/categorias/categoria_' . $i . '.txt'
                ];
                
                // Crear imágenes de ejemplo para categorías
                $this->crearImagenCategoria($i);
            }
        }
        
        // Verificar si el usuario está logueado
        $usuarioLogueado = isset($_SESSION['usuario']);
        
        // Cargar la vista de inicio
        include RUTA_APLICACION . '/vistas/inicio.php';
    }
    
    /**
     * Crea una imagen de ejemplo para una categoría
     * 
     * @param int $idCategoria ID de la categoría
     * @return void
     */
    private function crearImagenCategoria($idCategoria) {
        // Directorio donde se almacenan las imágenes de categorías
        $directorioImagenes = RUTA_APLICACION . '/../publico/imagenes/categorias';
        
        // Verificar si el directorio existe, si no, crearlo
        if (!is_dir($directorioImagenes)) {
            mkdir($directorioImagenes, 0777, true);
        }
        
        // Crear imagen de categoría
        $nombreArchivo = 'categoria_' . $idCategoria . '.txt';
        $rutaArchivo = $directorioImagenes . '/' . $nombreArchivo;
        
        // Verificar si el archivo ya existe
        if (!file_exists($rutaArchivo)) {
            // Crear un archivo de texto como placeholder
            file_put_contents($rutaArchivo, "Este es un archivo de texto que simula una imagen para la categoría $idCategoria");
        }
    }
    
    /**
     * Crea imágenes de ejemplo para los productos
     */
    private function crearImagenesEjemplo() {
        // Directorio donde se almacenarán las imágenes
        $directorioImagenes = RUTA_APLICACION . '/../publico/imagenes/productos';
        
        // Verificar si el directorio existe, si no, crearlo
        if (!is_dir($directorioImagenes)) {
            mkdir($directorioImagenes, 0777, true);
        }
        
        // Verificar si ya existe la imagen por defecto
        $imagenDefault = $directorioImagenes . '/default.txt';
        if (!file_exists($imagenDefault)) {
            // Crear un archivo de texto como placeholder
            file_put_contents($imagenDefault, "Este es un archivo de texto que simula una imagen por defecto");
        }
        
        // Nombres de productos para las imágenes de ejemplo
        $nombresProductos = [
            'crema-hidratante',
            'vitamina-c',
            'aloe-vera',
            'aceite-lavanda',
            'jabon-natural',
            'shampoo-organico',
            'te-verde',
            'aceite-argan'
        ];
        
        // Crear imágenes de ejemplo para cada producto
        foreach ($nombresProductos as $index => $nombre) {
            $idProducto = $index + 1;
            $nombreArchivo = $nombre . '.txt';
            $rutaArchivo = $directorioImagenes . '/' . $nombreArchivo;
            
            // Verificar si el archivo ya existe
            if (!file_exists($rutaArchivo)) {
                // Crear un archivo de texto como placeholder
                file_put_contents($rutaArchivo, "Este es un archivo de texto que simula una imagen para el producto $nombre");
            }
            
            // Crear algunas imágenes adicionales para cada producto
            for ($i = 1; $i <= 2; $i++) {
                $nombreArchivoAdicional = $nombre . '-' . $i . '.txt';
                $rutaArchivoAdicional = $directorioImagenes . '/' . $nombreArchivoAdicional;
                
                // Verificar si el archivo ya existe
                if (!file_exists($rutaArchivoAdicional)) {
                    // Crear un archivo de texto como placeholder
                    file_put_contents($rutaArchivoAdicional, "Este es un archivo de texto que simula una imagen adicional para el producto $nombre");
                }
            }
        }
    }
    
    /**
     * Crea una imagen simple con texto
     * Este método ya no se usa, pero se mantiene por compatibilidad
     */
    private function crearImagenSimple($rutaArchivo, $texto, $colorFondo, $colorTexto) {
        if (!file_exists($rutaArchivo)) {
            // Crear un archivo de texto simple en lugar de una imagen
            $contenido = "Esta es una imagen de ejemplo para " . basename($rutaArchivo) . "\n";
            $contenido .= "En un entorno de producción, aquí habría una imagen real.\n";
            $contenido .= "Por favor, sube una imagen real usando el panel de administración.";
            
            file_put_contents($rutaArchivo, $contenido);
        }
    }
} 