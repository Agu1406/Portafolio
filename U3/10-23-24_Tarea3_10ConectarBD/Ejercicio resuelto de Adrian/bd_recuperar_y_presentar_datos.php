<?php
$datos_conexion ='mysql:dbname=empresa;host=127.0.0.1';
$administrador ='root';
$pw ='';

try {
$bd = new PDO($datos_conexion, $administrador, $pw);
 echo "<p>Se ha realizado la conexión con ".$datos_conexion.
 "<br> Administrador= ".$administrador." | pw= ".$pw."</p>";

$sql = 'SELECT nombre, clave, rol FROM usuarios';

//Todos los usuarios de la base de datos
$usuarios = $bd->query($sql);

foreach ($usuarios as $usr) {
    print "Nombre: ".$usr['nombre']." | ";
    print " Clave: ".$usr['clave']." | ";
    print " Rol: ".$usr['rol']."<br>";
}

//Filtra las claves de usuarios con 0
$preparada = $bd->prepare("select nombre,clave,rol from usuarios where rol =:rol");
$preparada->execute(array(':rol'=> 0));

echo "<br>Los usuarios de rol 0: <br>";

foreach ($preparada as $usr) {
    print "Nombre: ".$usr['nombre']." | ";
    print " Clave: ".$usr['clave']." | ";
    print " Rol: ".$usr['rol']."<br>";
}

//Para probar mas, por mi cuenta añado este filtro para clave 2222
$clave = $bd->prepare("select nombre,clave,rol from usuarios where clave =:clave");
$clave->execute(array(':clave'=> 2222));

echo "<br>EXTRA Los usuarios de clave 2222: <br>";

foreach ($clave as $usr) {
    print "Nombre: ".$usr['nombre']." | ";
    print " Clave: ".$usr['clave']." | ";
    print " Rol: ".$usr['rol']."<br>";
}

}catch (PDOException $e) {
 echo 'Error al intentar conectar con la base de datos: <br>'
.$e->getMessage();
}

?>