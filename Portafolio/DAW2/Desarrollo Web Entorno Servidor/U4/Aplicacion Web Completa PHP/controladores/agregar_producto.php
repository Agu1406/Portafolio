<?php

// Importamos ambos, la conexión-instancia a la base de datos y el CRUD de productos.
include_once __DIR__ . "/../modelos/conexion_bd.php";
include_once __DIR__ . "../modelos/crud_productos.php";

// Al haber importado el script, tenemos acceso a sus métodos y funciones, los usamos.
try {
    // Obtener la instancia de la clase ConexionBaseDeDatos
    $baseDeDatos = ConexionBaseDeDatos::obtenerInstancia();
    // Obtener la conexión de la base de datos desde la instancia.
    $conexion = $baseDeDatos->obtenerConexion();
} catch (Exception $e) {
    // Manejo de errores más específico
    die("Error: No se ha podido conectar a la base de datos, " . $e->getMessage());
}

 // Si el formulario enviado es "POST" ejecutamos el if, si no el "else" envia error.
if ($_SERVER["REQUEST_METHOD" == "POST"]) {

} else {
    echo "Error al intentar subir un producto.";
}


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
        $stmt = $conexionBD -> prepare($sql);

        // Asociamos a los atributos de la consulta sus valores del formulario.
        $stmt -> bindParam(':modelo', $modelo);
        $stmt -> bindParam(':fabricante', $fabricante);
        $stmt -> bindParam(':foto', $fotoCoche);
        $stmt -> bindParam(':ano', $ano);

        // Ejecutamos la ocnsulta en la base de datos.
        $stmt -> execute();

        // Enviamos un mensaje de exito si la consulta se ejecuta correctamente.
        echo "Coche subido con éxito.";
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