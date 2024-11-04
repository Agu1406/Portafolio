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
     * De la lista de usuarios obtenida de la consulta realizada
     * con un bucle foreach recorre una a una las posiciones del
     * array e imprime de cada una de las instancias de "usarios"
     * los datos que deseamos, en este caso "nombre", "clave" y
     * "rol".
     * */
    foreach ($usuarios as $espejo) {
        print "Nombre del usuario: " . espejo["nombre"] . " | ";
        print "Clave / Contraseña: " . espejo["clave"] . " | ";
        print "Rol del usuario: " . espejo["rol"] . "<br>";
    }

    /**
     * Creamos una consulta pre "preparada" para filtrar a todos los usuarios cuyas
     * roles de usuario sean "0".
     */
    $preparada = $baseDeDatos -> prepare("SELECT nombre, clave, rol FROM usuarios WHERE rol =:rol");
    $preparada -> execute(array(":rol" => 0));

    // Salto de linea, avisamos lo que vamos a imprimir y otro salto de linea.
    echo "<br>Los usuarios con rol \"0\" son los siguientes: <br>";

    /**
     * Con un bucle foreach recorremos el array "preparada" donde hemos ejecutado
     * y guardado en forma de array los resultados de la consulta donde hemos
     * filtrado que se vean los usuarios de rol "0" solamente.
     */
    foreach ($preparada as $usuario) {
        print "Nombre del usuario: " . usuario["nombre"] . " | ";
        print "Clave / Contraseña: " . usuario["clave"] . " | ";
        print "Rol del usuario: " . usuario["rol"] . "<br>";
    }

    /**
     * Creamos una consulta pre "preparada" para filtrar a todos los usuarios cuyas
     * roles de usuario sean "0".
     */
    $preparada2 = $baseDeDatos -> prepare("SELECT nombre, clave, rol FROM usuarios WHERE rol =:rol");
    $preparada2 -> execute(array(":rol" => 2222));

    // Salto de linea, avisamos lo que vamos a imprimir y otro salto de linea.
    echo "<br>Los usuarios con rol \"2222\" son los siguientes: <br>";

    /**
     * Con un bucle foreach recorremos el array "preparada" donde hemos ejecutado
     * y guardado en forma de array los resultados de la consulta donde hemos
     * filtrado que se vean los usuarios de rol "0" solamente.
     */
    foreach ($preparada2 as $usuario) {
        print "Nombre del usuario: " . usuario["nombre"] . " | ";
        print "Clave / Contraseña: " . usuario["clave"] . " | ";
        print "Rol del usuario: " . usuario["rol"] . "<br>";
    }

} catch (PDOException $e) {
    /**
     * De entrada si la conexión con la base de datos no fue exitosa imprimimos
     * un mensaje general personalizado de error y luego imprimimos el mensaje
     * especifico captura por la excepción en la variable "$e".
     */
    echo "¡Error! No se ha podido establecer conexión con la base de datos: <br>" . $e -> getMessage();
}