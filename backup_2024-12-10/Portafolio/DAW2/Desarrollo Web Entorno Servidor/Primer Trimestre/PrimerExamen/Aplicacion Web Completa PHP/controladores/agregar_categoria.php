<?php
// Incluir el archivo de conexión y el modelo CRUD
include __DIR__ . "/../modelos/conexion_bd.php";
include __DIR__ . "/../modelos/crud_categorias.php";

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

// Verifica si el método de envio es POST
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    // Si el método es POST, extrae del "$_POST" el "nombre_categoria"
    $posibleCategoria = $_POST["nombre_categoria"];

    // Llamamos al método CRUD para añadir categoría
    $resultado = categoriaCRUD::crearCategoria($posibleCategoria);

    // Imprime el resultado, sea positivo o no.
    echo $resultado;

} else {
    echo "Error, por favor complete el nombre de la categoría";
}