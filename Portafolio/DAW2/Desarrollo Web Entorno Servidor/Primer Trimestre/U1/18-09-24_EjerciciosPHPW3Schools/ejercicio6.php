<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <h1>Tipos de variables y data</h1>
    <p>Imaginemos que, por cualquier motivo que sea, queremos saber el valor y el tipo de variable de
        un Script, para ello exisaten métodos que permiten conocerlo, uno de ellos es el var_dump("variable")
        el cual nos devuelve primero, el tipo de variable y entre parentesis su valor, a continuación un ejemplo:
    </p>
    <br>
    <?php 
    // Creamos las variables
    $variableInt = 14;
    $variableFloat = 0.6;
    $variableBoolean = true;  // El valor booleano debe ser true o false
    $variableString = "Agustín";

    // Usamos var_dump() directamente para cada variable
    echo "<strong>Variable int:</strong> ";
    var_dump($variableInt);
    echo "<br>";

    echo "<strong>Variable float:</strong> ";
    var_dump($variableFloat);
    echo "<br>";

    echo "<strong>Variable boolean:</strong> ";
    var_dump($variableBoolean);
    echo "<br>";

    echo "<strong>Variable string:</strong> ";
    var_dump($variableString);
    echo "<br>";
    ?>
</body>
</html>