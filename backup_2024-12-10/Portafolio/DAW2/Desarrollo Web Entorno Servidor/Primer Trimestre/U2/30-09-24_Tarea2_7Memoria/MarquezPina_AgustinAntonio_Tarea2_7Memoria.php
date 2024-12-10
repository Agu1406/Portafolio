<?php

// Al comienzo del script, informar de la memoria usada inicialmente (m1)
$m1 = memory_get_usage();
echo "Memoria inicial usada: " . $m1 . " bytes<br>";

// Crear un array de, al menos, 20 números
$numeros = range(1, 20); // Crea un array con números del 1 al 20

// Informar de la memoria usada hasta el momento (m2)
$m2 = memory_get_usage();
echo "Memoria usada después de crear el array: " . $m2 . " bytes<br>";

// Calcular el incremento de memoria m2 - m1
$incremento_memoria = $m2 - $m1;
echo "Incremento de memoria: " . $incremento_memoria . " bytes<br>";

// Limpiar la memoria antes de terminar el script
unset($numeros); // Libera el espacio de memoria asignado al array

// Informar de la memoria usada en este momento (m3)
$m3 = memory_get_usage();
echo "Memoria usada después de limpiar el array: " . $m3 . " bytes<br>";

?>
