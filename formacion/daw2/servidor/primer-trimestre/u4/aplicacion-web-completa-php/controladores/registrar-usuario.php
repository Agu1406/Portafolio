<?php
// Incluir el archivo de conexión y el modelo CRUD
include __DIR__ . "/../modelos/conexion_bd.php";
include __DIR__ . "/../modelos/crud_usuarios.php"; // Asegúrate de incluir el archivo del modelo UsuarioCRUD

// Obtener la conexión a la base de datos
try {
    $baseDeDatos = ConexionBaseDeDatos::obtenerInstancia();
    $conexion = $baseDeDatos->obtenerConexion();
} catch (Exception $e) {
    echo "Error: No se ha podido conectar a la base de datos, " . $e->getMessage();
    exit();
}

// Recoger los datos del formulario
$usuario = $_POST['usuario'];
$contrasena = $_POST['contrasena'];
$cif_cliente = $_POST['cif_cliente'];
$direccion = $_POST['direccion'];
$codigoPostal = $_POST['codigoPostal'];
$telefono = $_POST['telefono'];

// Verificar que no haya campos vacíos
if (empty($usuario) || empty($contrasena) || empty($cif_cliente) || empty($direccion) || empty($codigoPostal) || empty($telefono)) {
    echo "Todos los campos son obligatorios.";
    exit();
}

// Usar el método registrarUsuario de UsuarioCRUD para insertar el usuario
$resultado = UsuarioCRUD::registrarUsuario($usuario, $contrasena, $cif_cliente, $direccion, $codigoPostal, $telefono);

// Comprobar el resultado
if ($resultado === true) {
    echo "Usuario registrado correctamente.";
} else {
    echo $resultado; // Si hay un error, mostrarlo
}