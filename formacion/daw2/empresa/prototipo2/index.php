<?php
/**
 * Archivo de redirección principal
 * 
 * Este archivo simplemente redirige a la carpeta pública
 * sin depender de reglas de .htaccess
 */

// Verificar si el servidor está funcionando correctamente
if (isset($_GET['diagnostico']) && $_GET['diagnostico'] == 'true') {
    echo "<h1>Diagnóstico Básico</h1>";
    echo "<p>El servidor PHP está funcionando correctamente.</p>";
    echo "<p>Intenta acceder a <a href='diagnostico.php'>diagnóstico completo</a> para más información.</p>";
    exit;
}

// Redirigir a la carpeta pública
header('Location: public/index.php');
exit; 