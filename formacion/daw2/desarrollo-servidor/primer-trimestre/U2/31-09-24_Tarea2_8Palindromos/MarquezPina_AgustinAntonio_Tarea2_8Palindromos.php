<?php
/*
Objetivo:

- Desarrollar un script en PHP que incluya una función para comprobar si un string es un palíndromo.

Funcionalidad:

- La función debe recibir un string y comprobar si dicho string es un palíndromo (es decir, si se lee igual de izquierda a derecha que de derecha a izquierda, ignorando mayúsculas y espacios).

Requisitos:

- Utilizar funciones de strings: https://www.php.net/manual/es/ref.strings.php
*/

/**
 * Función que comprueba si un string es un palíndromo
 * @param string $cadena
 * @return boolean
 */
function esPalindromo($cadena) {
    // Convertir la cadena a minúsculas y eliminar los espacios
    $cadena = strtolower(str_replace(' ', '', $cadena));

    // Comprobar si la cadena es igual a su versión invertida
    return $cadena === strrev($cadena);
}

// Probar la función con algunos ejemplos
$palabras = ["Anita lava la tina", "Hola", "A man a plan a canal Panama", "Radar"];

foreach ($palabras as $palabra) {
    if (esPalindromo($palabra)) {
        echo "'$palabra' es un palíndromo.<br>";
    } else {
        echo "'$palabra' no es un palíndromo.<br>";
    }
}

?>
