<?php

// Si el método de envío del formulario es POST, se ejecuta la validación
if ($_SERVER["REQUEST_METHOD"] == "POST") {

    // Usuario y contraseña predefinidos (evitar en producción)
    $usuarioValido = "agu1406";
    $contrasenaValida = "1234";

    // Capturamos los valores ingresados en el formulario
    $usuario = $_POST["usuario"];
    $contrasena = $_POST["contrasena"];

    // Validación de usuario y contraseña
    if ($usuario == $usuarioValido && $contrasena == $contrasenaValida) {
        
        // Iniciar sesión
        session_start();
        $_SESSION["usuario"] = $usuario; // Guardamos el nombre de usuario en la sesión

        // Crear la cookie para el usuario
        crearCookie($usuario);

        // Redirigimos al usuario a la página de bienvenida
        header("Location: Sesiones1_principal.php");

    } else {
        // Si los datos no son válidos, mostramos un mensaje de error
        $error = true;
    }
}

// Si se ha generado un error en la validación, mostrar mensaje
if (isset($error)) {
    echo "<br><p>¡Error! Verifica tu usuario o contraseña e inténtalo de nuevo</p>";
}

/**
 * Función para crear o actualizar una cookie del usuario
 */
function crearCookie($usuario) {
    // Definir el nombre de la cookie y su duración
    $nombreDeLaCookie = $usuario; // El nombre de la cookie es el nombre del usuario
    $valorDeLaCookie = "Sesión activa para " . $usuario;
    $tiempoExpiracion = time() + (86400); // 24 días de duración

    // Si ya existe la cookie, la actualizamos
    if (isset($_COOKIE[$nombreDeLaCookie])) {
        // Actualizamos el valor de la cookie (ejemplo: para contar visitas o algo similar)
        $valorDeLaCookie = $_COOKIE[$nombreDeLaCookie] . " Visitó de nuevo";
    }

    // Crear o actualizar la cookie con setcookie()
    setcookie($nombreDeLaCookie, $valorDeLaCookie, $tiempoExpiracion, "/");
}