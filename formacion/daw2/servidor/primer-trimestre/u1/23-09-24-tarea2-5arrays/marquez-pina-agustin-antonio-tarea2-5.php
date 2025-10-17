<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tarea 2.5 de Arrays</title>
</head>

<body>
    <!--
        Desarrollar un Script PHP que rellene dos arrays con números aleatorios
        entre "0" y "50" con 25 elementos C/U.

        El scrip presentara por consola los siguientes resultados:

        1. Los valores de un array que existan o estén duplicados en el otro array.
        2. La suma de todos los números de un array y del otro.
        3. Los dos arrays ordenados de forma ascendente.
        4. Un nuevo array que combine los dos inciiales.

        Para lo anterior intenta utilizar al maximo posible las funciones existente
        de los arrays que podrás encontrar en https://www.php.net/manual/es/ref.array.php
    -->
    <?php

    // Creamos nuestras variables al principio del código
    $miPrimerArray = [];
    $miSegundoArray = [];
    $arraysFusionados = [];

    $miPrimerArray = rellenadorDeArrays();
    $miSegundoArray = rellenadorDeArrays();

    // Método que rellena un Array con 25 números aleatorios.
    function rellenadorDeArrays()
    {
        // Array interno que sera creado y rellenado.
        $nuevoArray = [];
        // número que se generara en cada iteración del bucle.
        $numeroAleatorio = 0;

        // Bucle que en cada iteración genera un número aleatorio y lo inserta en el Array
        for ($posicion = 0; $posicion <= 24; $posicion++) {

            // Genera un número aleatorio entre "0" y "100"
            $numeroAleatorio = rand(0, 100);

            // Llena el Array en esa posición actual con el número generado aleatoriamente.
            $nuevoArray[$posicion] = $numeroAleatorio;
        }

        // Una vez finalizado el bucle devuelve el Array relleno llamado desde el método
        return $nuevoArray;
    }


    // Step 1: Find and show duplicate values
    $valoresDuplicados = array_intersect($miPrimerArray, $miSegundoArray);
    echo "<h3>1. Valores duplicados:</h3>";
    print_r($valoresDuplicados);

    // ---
    // Step 2: Calculate and show the sum of each array
    $sumaPrimerArray = array_sum($miPrimerArray);
    $sumaSegundoArray = array_sum($miSegundoArray);
    echo "<h3>2. Suma de los Arrays:</h3>";
    echo "Suma del primer array: " . $sumaPrimerArray . "<br>";
    echo "Suma del segundo array: " . $sumaSegundoArray . "<br>";

    // ---
    // Step 3: Sort both arrays in ascending order
    sort($miPrimerArray);
    sort($miSegundoArray);
    echo "<h3>3. Arrays ordenados:</h3>";
    echo "Primer array ordenado:<br>";
    print_r($miPrimerArray);
    echo "Segundo array ordenado:<br>";
    print_r($miSegundoArray);

    // ---
    // Step 4: Combine the two arrays
    $arraysFusionados = array_merge($miPrimerArray, $miSegundoArray);
    echo "<h3>4. Arrays fusionados:</h3>";
    print_r($arraysFusionados);
    ?>
</body>

</html>