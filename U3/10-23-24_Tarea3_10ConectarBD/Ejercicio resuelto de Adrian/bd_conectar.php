<?php
// Creamos una variable donde pondremos los datos de conexión de la base datos, en nuestro caso, MySQL, su nombre es "empresa" y el host es la IP local de Xampp
$datos_conexion ='mysql:dbname=empresa;host=127.0.0.1';

// Creamos la variable "administrador" y le damos como valor "root".
$administrador ='root';

// Creamos la variable "contrasena" cuyo valor es vacio ya que no definimos ninguna al crear la base de datos.
$contrasena ='';


// Intentamos crear una instancia de la clase PDO y si no atrapamos las excepciones.
try {
    // Creamos una variable "baseDeDatos" e instanciamos el PDO en ella con los datos de la base de datos.
    $baseDeDatos = new PDO($datos_conexion, $administrador, $contrasena);
    
    // Si ha salido bien, imprimimos por pantalla que ha salido bien y los datos de la conexión.
    echo "<p>Se ha realizado la conexión con " . $datos_conexion . "<br> Administrador= " . $administrador . " | pw= " . $contrasena ."</p>";
}

// En caso de que ocurriese algún error / excepción, lo atrapamos e imprimimos por pantalla que algo ha salido mal.
catch (PDOException $e) {
    // En caso de capturar una excepcion, imprime por pantalla nuestro mensaje personalizado + la excepcion + el mensaje de excepcion.
    echo 'Error al intentar conectar con la base de datos: <br>' . $e -> getMessage();
}