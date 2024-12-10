<?php

$infodb = "mysql:dbname=empresa;host=127.0.0.1";
$user = "root";
$pass = "";

try {
    $bd = new PDO($infodb, $user, $pass);
    $insert = "INSERT into usuarios(Nombre, Clave, Rol) VALUES ('Aitor', '93', '1')";

    $bd->beginTransaction(); //abro transacción

    $result = $bd->query($insert);
    $result = $bd->query($insert); //insert doble para forzar el false 

    //BUSCAR ERROR 23000 MYSQL COMO CONTROLAR CON PDOEXCEPTION

    if(!$result) { //si hay error en la query
        echo "<p>[!] Ha habido un error en el insert</p>";
        echo "<p>[+] Haciendo rollback</p>";
        $bd->rollBack();
        echo "<p>[+] Transacción anulada, rollback completado</p>";
    } else {
        $bd->commit();
    }

} catch (PDOException $exc) { //error en la conexión
    echo "<p>[!] Ha habido un error</p>";
    echo $exc->getMessage();
}


