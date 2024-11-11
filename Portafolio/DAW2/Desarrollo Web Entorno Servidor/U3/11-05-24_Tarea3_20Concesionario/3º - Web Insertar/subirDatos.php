<?php

/**
 * Este comando se utiliza para que nuestro script importe funciones
 * y métodos de otro archivo PHP, en nuestro caso, "conexion_bd.php"
 * es el script con el que leemos XML e instanciamos PDO.
 */
include_once "conexion_bd.php";

/**
 * En nuestro sitio web de "formulario insertar" tenemos dos formularios,
 * uno de ellos se usa para subir a la base de datos "fabricantes", el otro
 * se usa para subir a la base de datos "coches", ¿Como diferenciar cual es
 * cual? En la etiqueta html hay un boton submit en cada formulario, las
 * cuales son:
 * 
 * <input type="submit" value="¡Subir fabricante!" name="subirFabricante">
 * <input type="submit" value="¡Subir coche!" name="subirCoche">
 * 
 * El PHP puede distinguir una de la otra gracias al atributo "name" que
 * es difernete en los dos botones.
 */

 // Si el formulario enviado es "POST" y es "subirFabricante" se ejecuta este if.
if (isset($_POST['subirFabricante'])) {
    // Extrae del array global "$_POST" los datos enviados por el formulario.
    $nombreFabricante = $_POST['nombre_fabricante'];

    // Creamos la consulta "sql" para insertar este fabricante en la base de datos.
    $sql = "INSERT INTO FABRICANTES (NOMBRE) VALUES (:nombre)";

    // Preparamos la consulta en la instancia de PDO para su posterior uso.
    $consulta = $conexion -> prepare($sql);

    // Asociamos a ":nombre" el valor extraido del formulario.
    $consulta -> bindParam(':nombre', $nombreFabricante);

    // Ejecutamos la consulta en la base de datos.
    $consulta -> execute();

    // Avisamos por pantalla que ha salido bien.
    echo "Fabricante subido con éxito.";
}

// Si el formulario enviado es "POST" y es "subirCoche" se ejecuta este if.
if (isset($_POST['subirCoche'])) {
    // Extra del array goblal "$_POST" los datos enviados en el formulario.
    $modelo = $_POST['modelo'];
    $fabricante = $_POST['fabricante'];
    $ano = $_POST['ano'];

    // Aquí utiizamos el método "subirFoto" del script para preparar la foto.
    $fotoCoche = subirFoto('foto');


    // Si la foto existe, es decir, no es ("!==") null, se ejecuta el if.
    if ($fotoCoche !== null) {
        // Preparamos la consulta para insertar los datos e nla base de datos.
        $sql = "INSERT INTO COCHES (MODELO, ID_FABRICANTE, FOTO, AÑO) VALUES (:modelo, :fabricante, :foto, :ano)";

        // Preparamos la consulta en la instancia de PDO (conexión de base de datos)
        $stmt = $conexion->prepare($sql);

        // Asociamos a los atributos de la consulta sus valores del formulario.
        $stmt->bindParam(':modelo', $modelo);
        $stmt->bindParam(':fabricante', $fabricante);
        $stmt->bindParam(':foto', $fotoCoche);
        $stmt->bindParam(':anio', $anio);

        // Ejecutamos la ocnsulta en la base de datos.
        $stmt->execute();

        // Enviamos un mensaje de exito si la consulta se ejecuta correctamente.
        echo "Coche subido con éxito.";
    } else {
        // Mensaje de error si la ejecución falla.
        echo "Error al subir la foto del coche.";
    }
}


function subirFoto($fotoDelFormulario) {
    // Si el código de error es "0", significa que no hubo errores al subir la foto, se ejecuta el if
    if ($_FILES[$fotoDelFormulario]['error'] == 0) {
        // El valor de "foto" sera la foto del formulario.
        $foto = file_get_contents($_FILES[$fotoDelFormulario]['tmp_name']);
        // Ya convertida, la devuelve.
        return $foto;

    } else {
        // Si el código de error no es "0", devuelve un objeto null.
        return null;
    }
}