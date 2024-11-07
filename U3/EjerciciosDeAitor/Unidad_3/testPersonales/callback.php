<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <?php
    //crea un vector
    $strings = ["Marta", "Aitor", "Carlos", "Vuelve", "te echo de menos"];
    //usa strlen dentro de la función map sobre el array de strings
    $lengths = array_map(
        function ($item) {
            return (strlen($item));
        }, $strings);

        echo "longitudes -> ";
        print_r($lengths);
    ?>
</body>
</html>