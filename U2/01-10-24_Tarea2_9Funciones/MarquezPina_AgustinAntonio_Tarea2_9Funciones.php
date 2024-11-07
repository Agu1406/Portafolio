<?php
/*
Objetivo:

- Desarrollar un script PHP que incluya una función para filtrar un array de números.
- La función debe recibir un array de números y un valor límite.
- La función devuelve un nuevo array que contiene solo los números menores que el valor límite.

*/

/**
 * Función que filtra los números menores que el valor límite
 * @param array $numeros Array de números a filtrar
 * @param float|int $limite Valor límite
 * @return array Nuevo array con los números menores que el límite
 */
function filtrarMenoresQue($numeros, $limite) {
    // Usamos la función array_filter para obtener los números menores que el límite
    return array_filter($numeros, function($numero) use ($limite) {
        return $numero < $limite;
    });
}

// Probar la función con un array de números y un límite
$numeros = [10, 25, 5, 30, 15, 2, 50, 3];
$limite = 15;

$resultado = filtrarMenoresQue($numeros, $limite);

// Imprimir el resultado
echo "Números menores que $limite: ";
print_r($resultado);
?>
