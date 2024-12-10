<?php

// Si está vacío el input de "usuario", imprime el siguiente error.
if (empty($_POST["usuario"])) {
    echo "Error, falta el parámetro usuario";
} else {
    // Si no está vacío, imprime el usuario ingresado.
    echo "Usuario: " . $_POST["usuario"] . "<br>";
}

// Si está vacío el input de "password", imprime el siguiente error.
if (empty($_POST["password"])) {
    echo "Error, falta el parámetro password";
} else {
    // Si no está vacío, imprime la contraseña por pantalla.
    echo "Contraseña: " . $_POST["password"];
}
?>