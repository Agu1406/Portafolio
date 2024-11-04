<?php

$conexion = "mysql:dbname=empresa;host=127.0.0.1";
$user = "root";
$password = "";

try {
    $bd = new PDO($conexion,$user,$password);
    echo "[+] Conexión exitosa";
} catch (PDOException $exc) {
    echo "[!] Error en la conexión<br>";
    echo $exc -> getMessage();
}

$consulta = $bd -> query("SELECT nombre, clave, rol FROM usuarios");

foreach ($consulta as $campos) {
    echo "Nombre: " . $campos["nombre"] . "<br>";
    echo "Clave: " . $campos["clave"] . "<br>";
    echo "Rol: " . $campos["rol"] . "<br>";
}