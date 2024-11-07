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
        $sumaPrimerArray;
        $sumaSegundoArray;
        $miPrimerArray = [];
        $miSegundoArray = [];
        $arraysFusionados = [];
        $valoresDuplicados = [];


        // Utilizamos la función que rellena Arrays de números aleatorios
        $miPrimerArray = rellenadorDeArrays();
        $miSegundoArray = rellenadorDeArrays();

        // Imprimimos los dos Arrays tal y como se han generado
        echo "Los dos Arrays generados han quedado de la siguiente forma: <br>";
        imprimirDosArrays($miPrimerArray, $miSegundoArray);

        // Utilizamos el método de Arrays que busca los valores duplicados en dos Arrays y los guarda en otro Array
        $valoresDuplicados = array_intersect($miPrimerArray, $miSegundoArray);

        // Imprimimos los valores duplicados de ambos Arrays
        echo "Los valores duplicados encontrados en ambos Arrays son los siguientes: <br>";
        echo imprimirArray($valoresDuplicados) . "<br>";

        // Utilizamos el método de Arrays que suma todos sus valores y devuelve un valor total (Int o Float)
        $sumaPrimerArray = array_sum($miPrimerArray);
        $sumaSegundoArray = array_sum($miSegundoArray);

        // Imprimimos por pantalla la suma total de ambos arrays por separado.
        echo "La suma total del primer Array es: " . $sumaPrimerArray . "<br>";
        echo "La suma total del segundo Array es: " . $sumaSegundoArray . "<br>";

        // Ordenamos los Arrays de forma ascendente
        sort($miPrimerArray);
        sort($miSegundoArray);

        // Imprimimos los Arrays ordenados de forma ascendente
        echo "Los Arrays ordenados de forma ascendente quedan de la siguiente forma: <br>";
        echo imprimirDosArrays($miPrimerArray, $miSegundoArray);

        // Fusionamos nuestros dos Arrays en uno solo utilizando el método "merge" de la clase Array.
        $arraysFusionados = array_merge($miPrimerArray, $miSegundoArray);

        // Imprimimos el Array creado de la unión / fusión de otros dos Arrays con el método correspondiente
        echo "La fusión de los dos Arrays da lugar al siguiente Array: <br>";
        imprimirArray($arraysFusionados);

        // Método que imprime dos Arrays lado a lado separados por un "|"
        function imprimirDosArrays($miPrimerArray, $miSegundoArray) {
            
            for ($posicion = 0; $posicion < 25; $posicion++) { 

                echo $miPrimerArray[$posicion] . " | " . $miSegundoArray[$posicion] . "<br>";

            }
        }

        // Método que rellena un Array con 25 números aleatorios.
        function rellenadorDeArrays() {
            // Array interno que sera creado y rellenado.
            $nuevoArray = [];
            // número que se generara en cada iteración del bucle.
            $numeroAleatorio = 0;

            // Bucle que en cada iteración genera un número aleatorio y lo inserta en el Array
            for ($posicion = 0; $posicion < 25; $posicion++) {
                
                // Genera un número aleatorio entre "0" y "50"
                $numeroAleatorio = rand(0, 50);

                // Llena el Array en esa posición actual con el número generado aleatoriamente.
                $nuevoArray [$posicion] = $numeroAleatorio;

            }

            // Una vez finalizado el bucle devuelve el Array relleno llamado desde el método
            return $nuevoArray;
        }


        // Método para imprimir un array (usamos Foreach para evitar problemas con los indices)
        function imprimirArray($unArray) {
            /**
             * En los bucles "foreach" en PHP decimos que recorra cada "valor" dentro de un Array
             * indiferentemente del indice, es por ello que creamos una variable interna local
             * llamada "$valor" que ira uno por uno recorriendo el Array y tomando el valor
             * que sea que tenga para su posterior uso, en mi caso, imprimirlo con un
             * echo en cada iteración del bucle.
            */
            foreach ($unArray as $valor) { 
                echo $valor . " ";
            }

            // Una vez impresos todos los valores de "X" Array hago un salto de linea.
            echo "<br>";
        }
    ?>
</body>
</html>