<?php

require_once "../config/Singleton_db_sesion.php";

define("RUTA_XML", "../config/xml/configuracion_db.xml");

$db = Connection_db::get_conexion(RUTA_XML);

$t_coches = $db->query("SELECT * from t_coches");
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="styles/style.css">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <?php
    foreach ($t_coches as $coche) { //muestra los atributos de los coches
        print "id - " . $coche[0] . "<br>";
        print "modelo - " . $coche[1] . "<br>";
        $nombre_fabricante = $db->query("SELECT nombre_fabricante FROM t_fabricante WHERE id_fabricante = " . $coche[2]);
        print "fabricante - " . $nombre_fabricante->fetch(PDO::FETCH_ASSOC)["nombre_fabricante"] . "<br>";
        print "año - " . $coche[4] . "<br>";
        echo '<img src="data:image/jpeg;base64,' . base64_encode($coche[3]) . '" alt="foto coche" height="300px" width="300px"><br>';
        echo "<br>";
    }
    ?>
</body>
</html>