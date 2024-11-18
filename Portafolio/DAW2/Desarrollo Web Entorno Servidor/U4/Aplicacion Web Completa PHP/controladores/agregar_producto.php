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
    // Si efectivamente es POST extrameos de la variable superglobal los valores del producto
    $nombreProducto = $_POST["nombre_producto"];
    $descripcionProducto = $_POST["descripcion_producto"];
    $precioProducto = $_POST["precio_producto"];
    $stock = $_POST["stock"];
    $categoria = $_POST["categoria"];

    // Aquí es diferente, hay que convertir la imagén en binario para guardarla en la base de datos.
    $imagen = convertirImagen("imagen");

    // "Si" la imagén no es nula, se subio correctamente, procede el if.
    if ($imagen !== null) {
        // Llamamos al método de crear productos del CRUD y le parseamos sus valores
        productoCRUD::crearProducto()
    }

} else {
    echo "Error al intentar subir un producto.";
}

function convertirImagen($imagen) {
    // Primero, verificamos el código de error de la imagen, si es "0" se subio correctamente.
    if ($_FILES[$imagen]["error"] == 0) {
        // Si era "0", usamos file_get_content para convertirla en binario
        $imagenConvertida = file_get_contents($_FILES[$imagen]["tmp_name"]);

        // Ya convertida, la returnamos.
        return $imagenConvertida;
    } else {
        // El "else" se ejecuta si el código de error no era "0".
        return null;
    }
}