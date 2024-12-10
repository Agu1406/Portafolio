<?php
$datos_conexion = 'mysql:dbname=empresa;host=127.0.0.1';
$administrador = 'root';
$pw = '';
try {
    $bd = new PDO($datos_conexion, $administrador, $pw);
    echo "<p>Se ha realizado la conexión con ".$datos_conexion.
        "<br> Administrador= ".$administrador." | pw= ".$pw."</p>";
} catch (PDOException $e) {
    echo "Error al intentar conectar con la base de datos: <br>" . $e->getMessage();
}

//query

$consulta = "SELECT nombre, clave, rol FROM usuarios";

$usuarios = $bd -> query($consulta);

foreach ($usuarios as $usr) {
    print "Nombre : ".$usr['nombre']." | ";
    print " Clave : ".$usr['clave']. " | ";
    print " Rol : ".$usr['rol']."<br>";
}