<?php

$datos = "mysql:dbname=empresa;host=127.0.0.1";
$admin = "root";
$pw = "";

try {
    $db = new PDO($datos, $admin, $pw);
    echo "<p>Conexión realizada</p>";
    
    //nuevo usaurio
    echo "<p> Insertar un nuevo usuario con mi nombre</p>";
    $insert = "INSERT into usuarios(Nombre, Clave, Rol) VALUES ('Aitor', '93', '1')";
    $result = $db->query($insert);
    if ($result) {
        echo "<p>Insert realizaado con éxito</p>";
    }
} catch (PDOException $exc) {
    echo "<p>[!] Ha habido un error</p>";
    echo $exc->getMessage();
}