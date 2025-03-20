<?php
/**
 * Archivo Bootstrap
 * Carga las configuraciones y clases principales
 */

// Cargar configuración
require_once 'config/config.php';

// Cargar helpers
require_once 'helpers/url_helper.php';
require_once 'helpers/session_helper.php';
require_once 'helpers/accessibility_helper.php';

// Autoload de clases principales
spl_autoload_register(function($className) {
    // Lista de directorios donde buscar clases
    $directories = [
        'config/',
        'controllers/',
        'models/'
    ];
    
    // Recorrer directorios
    foreach($directories as $directory) {
        $file = __DIR__ . '/' . $directory . $className . '.php';
        
        // Si existe el archivo, cargarlo
        if(file_exists($file)) {
            require_once $file;
            return;
        }
    }
}); 