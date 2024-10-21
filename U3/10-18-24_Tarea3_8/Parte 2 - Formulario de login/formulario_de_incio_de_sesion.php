<?php

/**
 * En base a las tareas anteriores deberiamos ser capaces de desarrollar un inicio
 * de sesión coherente, que valide los datos introducidos y dependiendo de una cosa
 * u otra permita cerrar sesión o entrar al sitio web una vez validados los datos.
 */

 // Si el método de envio del formulario es POST, se ejecuta el "if", si no, no.
if ($_SERVER["REQUEST_METHOD"]== "POST") {
    // Obtenemos del "POST" el usuario y la contraseña ingresados, si son validos, el if se ejecuta, si no, error.
    if($_POST["usuario"] == "agu1406" and $_POST["contrasena"] == "1234") {
        // Si los datos son validos, lo primero que hacemos es crear una sesión, por algo se llama "inicio de sesión"
        session_start();
        // Si los datos son validos, redirigimos al sitio web de bienvenida
        header ("Location: bienvenido.html");
    } 
    // Si los datos son invalidos, creamos una variable llamada "error" con valor "true".
    else {
        $error = true;
    }
}