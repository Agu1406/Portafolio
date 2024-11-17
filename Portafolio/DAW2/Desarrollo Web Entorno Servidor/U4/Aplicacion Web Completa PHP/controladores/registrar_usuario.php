<?php
// Incluir el archivo de conexión
include "../modelos/conexion_bd.php";

// Al haber importado el script, tenemos acceso a sus métodos y funciones, los usamos.
try {
    // Obtener la instancia de la clase ConexionBaseDeDatos
    $baseDeDatos = ConexionBaseDeDatos::obtenerInstancia();

    // Obtener la conexión de la base de datos desde la instancia.
    $conexion = $baseDeDatos->obtenerConexion();
    
} catch (Exception $e) {
    echo "Error: No se ha podido conectar a la base de datos, " . $e->getMessage();
    exit();
}

// Recoger los datos del formulario
$usuario = $_POST['usuario'];
$contrasena = $_POST['contrasena'];  // La contraseña en texto plano

// Verificar si los campos están vacíos
if (empty($usuario) || empty($contrasena)) {
    echo "Por favor ingrese ambos campos.";
    exit();
}

// Hashear la contraseña
$contrasena_hash = password_hash($contrasena, PASSWORD_DEFAULT);  // Hasheo con el algoritmo predeterminado (bcrypt)

// Insertar el usuario y el hash de la contraseña en la base de datos
$sql = "INSERT INTO Credenciales (correo, Contraseña) VALUES (:usuario, :contrasena)";
$stmt = $conexion->prepare($sql);  // Usamos $conexion en lugar de $conn

// Enlazar los parámetros de forma segura con bindParam
$stmt->bindParam(':usuario', $usuario, PDO::PARAM_STR);
$stmt->bindParam(':contrasena', $contrasena_hash, PDO::PARAM_STR);

// Ejecutar la consulta
if ($stmt->execute()) {
    echo "Usuario registrado correctamente.";
} else {
    echo "Error al registrar el usuario: " . $stmt->errorInfo()[2];
}