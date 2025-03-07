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

    //si todo va bien
    $bd->commit();

} catch (PDOException $exc) { //error en la conexión o en la inserción
    if ($bd->inTransaction()) { //inserción
        echo "<p>[!] No se pudo realizar la inserción<p>";
        $bd->rollBack();
        echo "<p>[+] Transacción anulada, rollback finalizado<p>";
    }
    echo "<p>[!] Mostrando ensaje de error " . $exc->getMessage() . "<p>";
}


