<?php
/**
 * Clase para gestionar las imágenes de los productos
 * 
 * Esta clase proporciona métodos para subir, guardar y recuperar imágenes de productos
 */
class GestorImagenes {
    
    /**
     * Directorio base donde se almacenarán las imágenes
     */
    private $directorioBase;
    
    /**
     * Constructor
     */
    public function __construct() {
        $this->directorioBase = RUTA_BASE . '/publico/imagenes/productos/';
        
        // Asegurarse de que el directorio existe
        if (!file_exists($this->directorioBase)) {
            mkdir($this->directorioBase, 0755, true);
        }
    }
    
    /**
     * Sube una imagen y devuelve la ruta relativa donde se guardó
     * 
     * @param array $archivo Array con la información del archivo ($_FILES['imagen'])
     * @return string|false Ruta relativa de la imagen o false si hubo un error
     */
    public function subirImagen($archivo) {
        // Verificar que el archivo se haya subido correctamente
        if ($archivo['error'] !== UPLOAD_ERR_OK) {
            return false;
        }
        
        // Obtener información del archivo
        $nombreOriginal = $archivo['name'];
        $extension = pathinfo($nombreOriginal, PATHINFO_EXTENSION);
        $rutaTemporal = $archivo['tmp_name'];
        
        // Generar un nombre único para evitar colisiones
        $nombreUnico = uniqid('producto_') . '.' . $extension;
        $rutaDestino = $this->directorioBase . $nombreUnico;
        
        // Mover el archivo a la ubicación final
        if (move_uploaded_file($rutaTemporal, $rutaDestino)) {
            // Devolver la ruta relativa para guardar en la base de datos
            return 'imagenes/productos/' . $nombreUnico;
        }
        
        return false;
    }
    
    /**
     * Elimina una imagen del servidor
     * 
     * @param string $rutaRelativa Ruta relativa de la imagen
     * @return bool True si se eliminó correctamente, false en caso contrario
     */
    public function eliminarImagen($rutaRelativa) {
        $rutaCompleta = RUTA_BASE . '/publico/' . $rutaRelativa;
        
        if (file_exists($rutaCompleta)) {
            return unlink($rutaCompleta);
        }
        
        return false;
    }
    
    /**
     * Obtiene la URL completa de una imagen
     * 
     * @param string $rutaRelativa Ruta relativa de la imagen
     * @return string URL completa de la imagen
     */
    public function obtenerUrlImagen($rutaRelativa) {
        if (empty($rutaRelativa)) {
            // Devolver una imagen por defecto si no hay ruta
            return $GLOBALS['configuracion']['rutaPublica'] . '/imagenes/productos/default.jpg';
        }
        
        return $GLOBALS['configuracion']['rutaPublica'] . '/' . $rutaRelativa;
    }
    
    /**
     * Crea una imagen por defecto si no existe
     */
    public function crearImagenPorDefecto() {
        $rutaDefecto = $this->directorioBase . 'default.jpg';
        
        if (!file_exists($rutaDefecto)) {
            // Crear una imagen por defecto simple
            $imagen = imagecreatetruecolor(300, 300);
            $colorFondo = imagecolorallocate($imagen, 240, 240, 240);
            $colorTexto = imagecolorallocate($imagen, 100, 100, 100);
            
            imagefill($imagen, 0, 0, $colorFondo);
            imagestring($imagen, 5, 100, 140, 'Sin imagen', $colorTexto);
            
            imagejpeg($imagen, $rutaDefecto, 90);
            imagedestroy($imagen);
        }
    }
    
    /**
     * Obtiene las imágenes adicionales de un producto
     * 
     * @param int $idProducto ID del producto
     * @return array Array con las rutas de las imágenes adicionales
     */
    public function obtenerImagenesProducto($idProducto) {
        $imagenesProducto = [];
        
        // Directorio donde se almacenan las imágenes
        $directorioImagenes = RUTA_APLICACION . '/../publico/imagenes/productos';
        
        // Verificar si el directorio existe
        if (!is_dir($directorioImagenes)) {
            return $imagenesProducto;
        }
        
        // Buscar archivos que contengan el ID del producto
        $archivos = scandir($directorioImagenes);
        
        if ($archivos) {
            foreach ($archivos as $archivo) {
                // Ignorar directorios y la imagen principal
                if ($archivo == '.' || $archivo == '..' || $archivo == 'default.jpg') {
                    continue;
                }
                
                // Verificar si el archivo pertenece al producto (por ejemplo: producto_1_1.jpg, producto_1_2.jpg)
                if (strpos($archivo, 'producto_' . $idProducto . '_') !== false) {
                    $imagenesProducto[] = [
                        'id' => count($imagenesProducto) + 1,
                        'ruta' => 'imagenes/productos/' . $archivo
                    ];
                }
            }
        }
        
        // Si no hay imágenes, crear algunas de ejemplo
        if (empty($imagenesProducto)) {
            // Crear imágenes de ejemplo (archivos de texto)
            for ($i = 1; $i <= 3; $i++) {
                $nombreArchivo = 'producto_' . $idProducto . '_' . $i . '.txt';
                $rutaArchivo = $directorioImagenes . '/' . $nombreArchivo;
                
                // Verificar si el archivo ya existe
                if (!file_exists($rutaArchivo)) {
                    // Crear un archivo de texto como placeholder
                    file_put_contents($rutaArchivo, "Este es un archivo de texto que simula una imagen para el producto $idProducto (imagen adicional $i)");
                }
                
                $imagenesProducto[] = [
                    'id' => $i,
                    'ruta' => 'imagenes/productos/' . $nombreArchivo
                ];
            }
        }
        
        return $imagenesProducto;
    }
    
    /**
     * Crea imágenes de ejemplo para un producto
     * 
     * @param int $idProducto ID del producto
     * @return void
     */
    public function crearImagenesEjemplo($idProducto) {
        // Directorio donde se almacenan las imágenes
        $directorioImagenes = RUTA_APLICACION . '/../publico/imagenes/productos';
        
        // Verificar si el directorio existe, si no, crearlo
        if (!is_dir($directorioImagenes)) {
            mkdir($directorioImagenes, 0777, true);
        }
        
        // Crear imagen principal
        $nombreArchivoPrincipal = 'producto_' . $idProducto . '.txt';
        $rutaArchivoPrincipal = $directorioImagenes . '/' . $nombreArchivoPrincipal;
        
        // Verificar si el archivo ya existe
        if (!file_exists($rutaArchivoPrincipal)) {
            // Crear un archivo de texto como placeholder
            file_put_contents($rutaArchivoPrincipal, "Este es un archivo de texto que simula una imagen para el producto $idProducto (imagen principal)");
        }
        
        // Crear imágenes adicionales
        for ($i = 1; $i <= 3; $i++) {
            $nombreArchivo = 'producto_' . $idProducto . '_' . $i . '.txt';
            $rutaArchivo = $directorioImagenes . '/' . $nombreArchivo;
            
            // Verificar si el archivo ya existe
            if (!file_exists($rutaArchivo)) {
                // Crear un archivo de texto como placeholder
                file_put_contents($rutaArchivo, "Este es un archivo de texto que simula una imagen para el producto $idProducto (imagen adicional $i)");
            }
        }
    }
} 