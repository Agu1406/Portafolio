<?php
/**
 * Archivo de configuración
 * 
 * Define constantes y configuraciones globales para la aplicación
 */

// Rutas del sistema
define('RUTA_BASE', dirname(__DIR__));
define('RUTA_APLICACION', RUTA_BASE . '/aplicacion');
define('RUTA_PUBLICA', RUTA_BASE . '/publico');

// Configuración de la aplicación
$GLOBALS['configuracion'] = [
    'rutaPublica' => '/formacion/daw2/interfaces/examen-final/publico',
    'nombreTienda' => 'NaturalShop',
    'descripcionTienda' => 'Tu tienda de productos naturales',
    'emailContacto' => 'info@naturalshop.com',
    'telefonoContacto' => '900 123 456',
    'direccion' => 'Calle Ejemplo, 123, 28001 Madrid',
    'moneda' => '€',
    'gastoEnvio' => 4.99,
    'envioGratis' => 50.00,
    'iva' => 21
];

// Configuración de la base de datos
$GLOBALS['configBD'] = [
    'host' => 'localhost',
    'usuario' => 'root',
    'password' => '',
    'nombre' => 'naturalshop',
    'charset' => 'utf8mb4'
]; 