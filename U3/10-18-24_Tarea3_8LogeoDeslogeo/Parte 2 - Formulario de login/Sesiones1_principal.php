<?php
session_start();

// Verificamos si la sesión está activa
if (!isset($_SESSION['usuario'])) {
    // Si no hay sesión activa, redirigimos al formulario de login
    header("Location: form_en_uno_sesion.php");
    exit();
}

// Mensaje de bienvenida al usuario
echo "<h1>¡Bienvenido, " . $_SESSION['usuario'] . "!</h1>";

// Enlace para cerrar sesión
echo '<p><a href="Sesiones1_logout.php">Cerrar sesión</a></p>';