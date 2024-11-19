<?php
/**
 * Esta clase es la que se encarga de, a peticion del controlador de sesiones
 * de verificar si un usuario ha iniciado sesión o no, en caso de no haber
 * iniciado sesión y estar en paginas privadas, le redige al login, este
 * mismo modelo también puede controlar el cerrar sesión de los usuarios.
 */
class controlSesiones {
    // Método para verificar las credenciales del usuario
    public static function crearSesion ($usuario, $contrasena) {
        // Obtenemos la conexión a la base de datos
        $conexion = ConexionBaseDeDatos::obtenerInstancia()->obtenerConexion();

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