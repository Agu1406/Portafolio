<?php

$datos = "mysql:dbname=empresa;host=127.0.0.1";
$admin = "root";
$pw = "";

try {
    $db = new PDO($datos, $admin, $pw);
    echo "<p>Conexión realizada</p>";
    
    //actualizamos
    echo "<p> Insertar un nuevo usuario con mi nombre</p>";
    $update = "UPDATE Usuarios SET Rol = 0 WHERE Nombre = 'ana'";
    $result = $db->query($update);
    if ($result) {
        echo "<p>Update realizaado con éxito</p>";
    }
} catch (PDOException $exc) {
    echo "<p>[!] Ha habido un error</p>";
    echo $exc->getMessage();
}