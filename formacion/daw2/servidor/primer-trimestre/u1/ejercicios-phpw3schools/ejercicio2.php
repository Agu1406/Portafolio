<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ejercicio 2 de PHP</title>
</head>
<body>
    <h2>Segundo ejercicio de PHP</h2>
    <p>En este ejercicio hacemos dos cosas, la primera de ellas es crear una variable en
        PHP, que luego llamamos y mostramos con un "echo" por pantalla y de está misma forma
        usamos uno de los métodos globales para consultar nuestra versión de PHP, a continuación:
    </p>
    <br>
    <?php 
        $texto = "Tú versión de PHP es: ";

        // Imprimimos la variable texto sin salto de linea
        echo "$texto";
        // Imprimimos en la misma linea y despues nuestra versión de PHP
        echo phpversion();
        // Por ultimo, hacemos un salto de linea.
        echo "<br>";
    ?>

    <p>Si todo es correcto deberías haber visto un mensaje que diga "Tú versión de PHP es: "tal versión"
        seguido del salto de linea y este parrafo.
    </p>
</body>
</html>