<?php 
/* El objetivo de está tarea es escribir una función para calcular factorial de un número, que recibirá 
como argumento. Está función devuelve el factorial pero si el argumento no es válido devuelve -1. */

// Creamos el número cuyo factorial hemos de cálcular
$numero = 7;
function calcularFactorial ($numero) {
    $auxiliar = 1;

    for ($i = $numero; $i > 1; $i--) { 
        
        $auxiliar *=  $i;
    }

    return $auxiliar;
}

$resultado = calcularFactorial($numero);


echo "El factorial del número " . $numero . " es " . $resultado;
?>