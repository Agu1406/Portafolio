<?php
/**
 * Archivo de configuración principal
 * Contiene constantes y configuraciones globales
 */

// Configuración de la base de datos
define('DB_HOST', 'localhost');
define('DB_USER', 'root');
define('DB_PASS', '');
define('DB_NAME', 'manosalaolla');

// Configuración de la URL base
define('BASE_URL', 'http://localhost/formacion/daw2/empresa/prototipo2');

// Configuración de la aplicación
define('APP_NAME', 'Manos a la Olla');
define('APP_VERSION', '1.0.0');

// Configuración de rutas
define('APPROOT', dirname(dirname(__FILE__)));
define('PUBLICROOT', dirname(dirname(dirname(__FILE__))) . '/public');

// Configuración de sesión
session_start(); 