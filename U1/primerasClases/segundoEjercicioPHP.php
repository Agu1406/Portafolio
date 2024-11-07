<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <?php
        // Creamos dos variables, "A" y "B" y definimos sus valores.
        $a = 10;
        $b = 5;

        // Operadores aritméticos, realizamos operaciones y mostramos su resultado en pantalla

        echo "El resultado de " . $a . " + " . $b . " = " . ($a + $b) . "<br>"; // Suma
        echo "El resultado de " . $a . " - " . $b . " = " . ($a - $b) . "<br>"; // Resta
        echo "El resultado de " . $a . " * " . $b . " = " . ($a * $b) . "<br>"; // Multiplicación
        echo "El resultado de " . $a . " / " . $b . " = " . ($a / $b) . "<br><br>"; // División

        /* Operadores de comparación ¿Son iguales? ¿Diferentes? ¿Es mayor que?
        en PHP los "true" se imprimen en un String como un "1" y los "false" por otra parte no se
        imprimen, por lo que para evitar que no se imprima nada, es decir, un mensaje vacio podemos
        utilizar la misma función que aprendimos en Java de personalizar las respuesta a un "true"
        o "false" para ello encerramos en un parentesis "( )" la operación de comparación indicando
        en cada respuesta si es un "true" o un "false", en mi caso "Verdadero" o "Falso". */
        echo "¿" . $a . " es igual a " . $b . "? " . (($a == $b) ? "Verdadero" : "Falso") . "<br>"; // Igualdad
        echo "¿" . $a . " es diferente de " . $b . "? " . (($a != $b) ? "Verdadero" : "Falso") . "<br>"; // Desigualdad
        echo "¿" . $a . " es mayor que " . $b . "? " . (($a > $b) ? "Verdadero" : "Falso") . "<br><br>"; // Mayor que

        // Operadores lógicos
        echo "¿" . $a . " es mayor que 5 y " . $b . " es menor que 10? " . (($a > 5 && $b < 10) ? "Verdadero" : "Falso") . "<br>"; // Y lógico
        echo "¿" . $a . " es mayor que 5 o " . $b . " es mayor que 10? " . (($a > 5 || $b > 10) ? "Verdadero" : "Falso") . "<br>"; // O lógico
        echo "¿No es " . $a . "? " . ((!$a) ? "Verdadero" : "Falso") . "<br><br>"; // Negación
    ?>
</body>
</html>
