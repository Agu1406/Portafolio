<?php

$conexion = "mysql:dbname=empresa;host=127.0.0.1";
$user = "root";
$password = "";

try {
    //conexión
    $bd = new PDO($conexion,$user,$password);
    echo "[+] Conexión exitosa<br><br>";

    //query
    $perfil = array(0, 1);
    $consulta = $bd -> prepare("SELECT nombre, clave, rol FROM usuarios WHERE rol = :rol"); //:rol se sustituirá al referenciar con el método bind param
    $consulta -> bindParam(":rol", $perfil[1]);
    $consulta -> execute();

    //resultados
    echo "Numero de filas: " . $consulta -> rowCount() . "<br>";
    foreach ($consulta as $campos) {
        echo "Nombre: " . $campos["nombre"] . "<br>";
        echo "Clave: " . $campos["clave"] . "<br>";
        echo "Rol: " . $campos["rol"] . "<br>";
    }
} catch (PDOException $exc) {
    echo "[!] Error en la conexión<br>";
    echo $exc -> getMessage();
}



