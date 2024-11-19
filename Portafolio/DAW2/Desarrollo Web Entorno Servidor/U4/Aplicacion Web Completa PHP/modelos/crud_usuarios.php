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

class UsuarioCRUD {
    // Método para registrar un nuevo usuario (CRUD)
public static function registrarUsuario($usuario, $contrasena, $cif, $direccion, $codigoPostal, $telefono) {
    /**
     * Para registrar un nuevo usuario, primero se debe crear un registro en la tabla
     * "Cliente", luego tomar el ID generado y usarlo para registrar los datos de
     * acceso en la tabla "Credenciales".
     */
    
    // Obtenemos la conexión a la base de datos
    $db = ConexionBaseDeDatos::obtenerInstancia()->obtenerConexion();

    try {
        // Iniciamos una transacción para garantizar la atomicidad
        $db -> beginTransaction();

        // Verificamos si el nombre de usuario ya existe en la tabla Credenciales
        $sql = $db->prepare("SELECT COUNT(*) FROM Credenciales WHERE correo = :usuario");
        $sql->bindParam(':usuario', $usuario, PDO::PARAM_STR);
        $sql->execute();
        $usuarioExistente = $sql->fetchColumn();

        if ($usuarioExistente > 0) {
            // Si el usuario ya existe, se cancela la transacción
            $db->rollBack();
            return "El nombre de usuario ya está en uso.";
        }

        // Inserción en la tabla Cliente
        $sql = $db->prepare(
            "INSERT INTO Cliente (cif_cliente, direccion, codigo_postal, telefono) 
             VALUES (:cif, :direccion, :codigo_postal, :telefono)"
        );
        $sql->bindParam(':cif', $cif, PDO::PARAM_STR);
        $sql->bindParam(':direccion', $direccion, PDO::PARAM_STR);
        $sql->bindParam(':codigo_postal', $codigoPostal, PDO::PARAM_STR);
        $sql->bindParam(':telefono', $telefono, PDO::PARAM_STR);
        $sql->execute();

        // Obtener el ID de cliente generado
        $codigoCliente = $db->lastInsertId();

        // Encriptamos la contraseña
        $contrasenaHash = password_hash($contrasena, PASSWORD_BCRYPT);

        // Inserción en la tabla Credenciales
        $sql = $db->prepare(
            "INSERT INTO Credenciales (correo, contrasena, cliente_codigo_cliente) 
             VALUES (:usuario, :contrasena, :codigo_cliente)"
        );
        $sql->bindParam(':usuario', $usuario, PDO::PARAM_STR);
        $sql->bindParam(':contrasena', $contrasenaHash, PDO::PARAM_STR);
        $sql->bindParam(':codigo_cliente', $codigoCliente, PDO::PARAM_INT);
        $sql->execute();

        // Confirmamos la transacción
        $db->commit();
        return true; // Registro exitoso
    } catch (Exception $e) {
        // Si ocurre un error, se revierte la transacción
        $db->rollBack();
        return "Error al registrar el usuario: " . $e->getMessage();
    }
}

    // Método para actualizar la contraseña de un usuario
    public static function actualizarContrasena($usuario, $nuevaContrasena) {
        // Obtenemos la conexión a la base de datos
        $db = ConexionBaseDeDatos::obtenerInstancia()->obtenerConexion();

        // Encriptamos la nueva contraseña
        $contrasenaHash = password_hash($nuevaContrasena, PASSWORD_BCRYPT);

        // Preparamos la consulta de actualización
        $sql = $db->prepare("UPDATE usuarios SET contrasena = :contrasena WHERE nombre_usuario = :usuario");
        $sql->bindParam(':usuario', $usuario, PDO::PARAM_STR);
        $sql->bindParam(':contrasena', $contrasenaHash, PDO::PARAM_STR);

        // Ejecutamos la consulta
        if ($sql->execute()) {
            return true; // Actualización exitosa
        } else {
            return "Error al actualizar la contraseña."; // Error en la actualización
        }
    }

    // Método para eliminar un usuario
    public static function eliminarUsuario($usuario) {
        // Obtenemos la conexión a la base de datos
        $db = ConexionBaseDeDatos::obtenerInstancia()->obtenerConexion();

        // Preparamos la consulta de eliminación
        $sql = $db->prepare("DELETE FROM usuarios WHERE nombre_usuario = :usuario");
        $sql->bindParam(':usuario', $usuario, PDO::PARAM_STR);

        // Ejecutamos la consulta
        if ($sql->execute()) {
            return true; // Eliminación exitosa
        } else {
            return "Error al eliminar el usuario."; // Error en la eliminación
        }
    }
}