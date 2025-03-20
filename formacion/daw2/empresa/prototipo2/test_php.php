<?php
// Este es un archivo PHP extremadamente simple para verificar si PHP está funcionando

// Mostrar todos los errores
ini_set('display_errors', 1);
ini_set('display_startup_errors', 1);
error_reporting(E_ALL);

// Información básica
echo "<h1>Prueba básica de PHP</h1>";
echo "<p>PHP está funcionando correctamente.</p>";
echo "<p>Versión de PHP: " . phpversion() . "</p>";

// Información del servidor
echo "<h2>Información del servidor</h2>";
echo "<ul>";
echo "<li>Sistema operativo: " . PHP_OS . "</li>";
echo "<li>Servidor web: " . $_SERVER['SERVER_SOFTWARE'] . "</li>";
echo "<li>Directorio raíz: " . $_SERVER['DOCUMENT_ROOT'] . "</li>";
echo "</ul>";

// No incluir nada más, ni configuración ni conexión a base de datos
echo "<p><a href='test.html'>Volver a la página de prueba</a></p>"; 