<?php

/**
 * Cuando utilizamos la clase PDO para instanciar una conexión a
 * una base de datos utilizando PHP el primero dato que recibe
 * el constructor de la clase un String que contiene en su
 * información el tipo de base de datos, el nombre de la misma,
 * el host o dirección IP y (en caso de haber modificado el
 * que esta por default), el puerto.
 * 
 * En mi caso en particular, Carlos Hernandez, el año pasado
 * tuvo que cambiar el puerto por defecto, por lo que, en mi
 * caso, para realizar la conexión tuve que indicar el puerto.
 */
$datosDeConexion = "mysql:dbname=empresa;host=127.0.0.1:3309";

// El siguiente dato que debemos dar es el usuario con el que haremos la conexión
$usuario = "root";

// Por ultimo, la contraseña de dicho usuario, en nuestro caso, vacia.
$contrasena = "";

// Una vez con todos los datos correctos intentamos la conexión con un try-catch
try {
    // Creamos una nueva instancia de la clase PDO
    $baseDeDatos = new PDO($datosDeConexion, $usuario, $contrasena);

    // Si todo sale bien, imprimimos un mensaje de exito en la conexión.
    echo "<p>¡Felicidades! La conexión ha salido correctamente</p>";

} catch (PDOException $e) {

    // En caso de cualquier tipo de error / excepción imprimimos un mensaje
    echo "<p>¡Error! No ha sido posible realizar la conexión, la información es: " . $e -> getMessage() . "</p>";
}