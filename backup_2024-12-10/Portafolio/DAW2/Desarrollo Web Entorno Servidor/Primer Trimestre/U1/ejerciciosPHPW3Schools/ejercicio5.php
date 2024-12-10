<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ejercicio 5 de PHP</title>
</head>
<body>
    <h1>Diferencias entre "Echo" y "Print"</h1>
    <p>La diferencia es que con "Echo" puedes imprimir varias cadenas o variables mientras que print puede, como maximo
        imprimir una sola variable, a continuación vemos un ejemplo de ellos:
    </p>
    <br>
    <?php 
    // Creamos dos variables globales / locales que usamos sin método / función.
    $texto1 = "Hola bebé, ya que contigo no sirve la labia <br>";
    $texto2 = "Y te crees muy sabia, pero ya vas a ver, te lo digo mujer, ea <br>";
    
    // Con "echo", separando las cadeanes o variables con comas puedo imprimir tantas como quiera.
    echo $texto1, $texto2;

    // Con "print" lo maximo que puedo hacer es imprimir una sola de ellas.
    print($texto1);
    ?>
</body>
</html>