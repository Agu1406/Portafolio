<?php
session_start();

// Destruir todas las variables de sesión
session_unset();

// Destruir la sesión
session_destroy();

// Borrar la cookie de usuario si existe
if (isset($_COOKIE[$_SESSION['usuario']])) {
    // Para eliminar una cookie, la definimos con una fecha de expiración en el pasado
    setcookie($_SESSION['usuario'], "", time() - 86400, "/");
}

// Redirigir de vuelta al formulario de login
header("Location: formulario_de_inicio_de_sesion.html");

exit();