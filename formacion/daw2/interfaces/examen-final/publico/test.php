<?php
// Archivo de prueba para verificar que PHP está funcionando correctamente

echo "<h1>Prueba de PHP</h1>";
echo "<p>Si puedes ver este mensaje, PHP está funcionando correctamente.</p>";
echo "<p>Fecha y hora actual: " . date('Y-m-d H:i:s') . "</p>";
echo "<p>Versión de PHP: " . phpversion() . "</p>";

// Mostrar información sobre la ruta
echo "<h2>Información de la ruta</h2>";
echo "<p>REQUEST_URI: " . $_SERVER['REQUEST_URI'] . "</p>";
echo "<p>SCRIPT_NAME: " . $_SERVER['SCRIPT_NAME'] . "</p>";
echo "<p>DOCUMENT_ROOT: " . $_SERVER['DOCUMENT_ROOT'] . "</p>";

// Enlace a la página principal
echo "<p><a href='index.php'>Ir a la página principal</a></p>"; 