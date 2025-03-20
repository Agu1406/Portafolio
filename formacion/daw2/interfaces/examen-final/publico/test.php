<?php
// Archivo de prueba para verificar el enrutamiento

// Incluir la configuración
define('RUTA_BASE', dirname(__DIR__));
define('RUTA_APLICACION', RUTA_BASE . '/aplicacion');
$configuracion = require_once RUTA_APLICACION . '/configuracion/configuracion.php';

echo "<h1>Prueba de enrutamiento</h1>";
echo "<p>Si puedes ver este mensaje, el enrutamiento funciona correctamente.</p>";

echo "<h2>Información de la solicitud</h2>";
echo "<pre>";
echo "REQUEST_URI: " . $_SERVER['REQUEST_URI'] . "\n";
echo "SCRIPT_NAME: " . $_SERVER['SCRIPT_NAME'] . "\n";
echo "PHP_SELF: " . $_SERVER['PHP_SELF'] . "\n";
echo "DOCUMENT_ROOT: " . $_SERVER['DOCUMENT_ROOT'] . "\n";
echo "</pre>";

echo "<h2>Configuración</h2>";
echo "<pre>";
echo "rutaBase: " . $configuracion['rutaBase'] . "\n";
echo "rutaPublica: " . $configuracion['rutaPublica'] . "\n";
echo "</pre>";

echo "<h2>Enlaces de prueba</h2>";
echo "<ul>";
echo "<li><a href='" . $configuracion['rutaBase'] . "/'>Inicio</a></li>";
echo "<li><a href='" . $configuracion['rutaBase'] . "/productos'>Productos</a></li>";
echo "<li><a href='" . $configuracion['rutaBase'] . "/producto/detalle?id=1'>Detalle de producto</a></li>";
echo "<li><a href='" . $configuracion['rutaBase'] . "/carrito'>Carrito</a></li>";
echo "</ul>";
?> 