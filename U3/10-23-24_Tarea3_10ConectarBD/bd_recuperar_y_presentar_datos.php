<?php

/**
 * Como ya vimos anteriormente, para conectar una base de datos
 * se debe de dar al constructor de la clase PDO los
 * datos de conexión, el usuario y la contraseña, sabiendo eso
 * creamos la siguiente variable:
 */
$datosDeConexion = "mysql:dbname=empresa;host=127.0.0.1:3309";

// El administrador (yo) de la base de datos.
$usuario = "root";

// La contraseña asignada a dicho "usuario".
$contrasena = "";

/**
 * Creamos un enorme y masivo try-catch para ejectuar todo
 * nuestro código, donde cualquier excepción o error sea
 * atrapado en el catch.
 */
try {

    // Instanciamos un nuevo objeto de clase PDO con los datos de conexión
    $baseDeDatos = new PDO ($datosDeConexion, $usuario, $contrasena);

    // Avisamos por pantalla que exitosamente se conecto.
    echo "<p>¡Conexión realizada exitosamente!</p><br>";

    // Creamos una consulta para su posterior uso.
    $consulta = "SELECT nombre, clave, rol FROM usuarios";

    /**
     * Si realizamos esa consulta seleccionara todos los
     * usuarios de la tabla "usuarios" y sus campos que
     * son "nombre, clave y rol", por lo tanto, podemos
     * guardarlos en una variable a modo de "array"
    */
    $usuarios = $baseDeDatos -> query($consulta);

    /**
     * 
     * */
    foreach ($usuarios as $espejo) {}
} catch (PDOException $e) {

}