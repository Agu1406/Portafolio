<?php
// Importamos la conexión de la base de datos.
include_once "conexion_bd.php";

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

class controlSesiones {
    // Método para verificar las credenciales del usuario
    public static function crearSesion ($usuario, $contrasena) {
        // Obtenemos la conexión a la base de datos
        $conexion = ConexionBaseDeDatos::obtenerInstancia()-> obtenerConexion();

        // Preparamos la consulta SQL en la conexión a la base de datos.
        $sql = $conexion -> prepare("SELECT * FROM Credenciales WHERE correo = :usuario LIMIT 1");

        // Con bindParam remplazamos los valores de ":usuario" con los recibidos en el argumento del método.
        $sql -> bindParam(':usuario', $usuario, PDO::PARAM_STR);

        // Ejecutamos la consulta
        $sql -> execute();

        // "Jalamos" del "sql" todos los usuarios que hayan coincidido con la consulta.
        $resultado = $sql -> fetch(PDO::FETCH_ASSOC);

        // Verificamos si se encontró un usuario y se compara la contraseña hasheada.
        if ($resultado && password_verify($contrasena, $resultado['contrasena'])) {
            return $resultado; // Devuelve los datos del usuario si la contraseña coincide
        } else {
            return false; // Retorna false si las credenciales no son válidas
        }
    }
}