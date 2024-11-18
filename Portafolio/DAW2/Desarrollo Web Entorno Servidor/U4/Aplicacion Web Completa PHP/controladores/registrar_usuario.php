<?php
// Incluir el archivo de conexión
include __DIR__ . "/../modelos/conexion_bd.php";

// Al haber importado el script, tenemos acceso a sus métodos y funciones, los usamos.
try {
    // Obtener la instancia de la clase ConexionBaseDeDatos
    $baseDeDatos = ConexionBaseDeDatos::obtenerInstancia();

    // Obtener la conexión de la base de datos desde la instancia.
    $conexion = $baseDeDatos->obtenerConexion();
    
} catch (Exception $e) {
    // En caso de error de conexión imprime un echo al respecto.
    echo "Error: No se ha podido conectar a la base de datos, " . $e->getMessage();
    exit();
}

// Extrae del POST ambos, el usuario y la contraseña.
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

// Preparamos la consulta en la conexión a la base de datos.
$stmt = $conexion->prepare($sql);

// con bindParam remplazamos los valores ":usuario" y ":contrasena" por los recibidos desde el formulario.
$stmt->bindParam(':usuario', $usuario, PDO::PARAM_STR);
$stmt->bindParam(':contrasena', $contrasena_hash, PDO::PARAM_STR);

// Ejecutar la consulta
if ($stmt->execute()) {
    echo "Usuario registrado correctamente.";
} else {
    echo "Error al registrar el usuario: " . $stmt->errorInfo()[2];
    header("Location registro.php?error=1");
}