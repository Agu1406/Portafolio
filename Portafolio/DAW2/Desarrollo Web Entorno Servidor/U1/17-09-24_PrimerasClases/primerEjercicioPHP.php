<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <!-- Para escribir código PHP debemos utilizar siempre las etiquetas "<?php ?>"
    y dentro de ellas el código utilizado. -->
    <?php 
        // Declaración de variables y sus valores.
        $nombre = "Juan";
        $edad = 30;
        $esEstudiante = true;
        // Presentar el contenido de estas variables en HTML utilizando saltos de linea
        echo "Mi nombre es: " . $nombre . "<br>";
        echo "Tengo " . $edad . " años.<br>";
        echo "¿Soy estudiante? " . $esEstudiante;
    ?>
</body>
</html>