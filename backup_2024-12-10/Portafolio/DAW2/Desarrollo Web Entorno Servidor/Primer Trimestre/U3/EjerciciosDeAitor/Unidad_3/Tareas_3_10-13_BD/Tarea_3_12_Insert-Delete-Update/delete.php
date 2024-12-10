<?php

$datos = "mysql:dbname=empresa;host=127.0.0.1";
$admin = "root";
$pw = "";

try {
    $db = new PDO($datos, $admin, $pw);
    echo "<p>Conexión realizada</p>";
    //delete
    echo "<p>Borrando usuario</p>";
    $delete = "DELETE from Usuarios WHERE nombre = 'Aitor'";
    $result = $db->query($delete);
    if ($result) {
        echo "[+] El delete se ha ejecutado correctamente";
    } else {
        print_r($db -> errorInfo());
    }
} catch (PDOException $exc) {
    echo "<p>Error en la conexión</p>";
}