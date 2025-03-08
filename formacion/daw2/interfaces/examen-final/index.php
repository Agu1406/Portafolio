<?php
/**
 * Archivo de redirección principal
 * 
 * Este archivo simplemente redirige a la carpeta pública
 * sin depender de reglas de .htaccess
 */

// Redirigir a la carpeta pública
header('Location: publico/index.php');
exit; 