<?php
// Inicio una sesión
session_start();

// Verificar si el usuario no ha iniciado sesión
if (!isset($_SESSION['usuario'])) {
    // Si no ha iniciado sesión, redirigir al login
    header("Location: ../vistas/formulario_login.php");
    exit();
}

// Verificar si el usuario no es administrador
if ($_SESSION['rol'] !== "1") {
    // Si no es admin, redirigir al catálogo
    header("Location: ../vistas/catalogo.php"); 
    exit();
}

// Importar el modelo de usuario
include_once "../modelos/crud_usuarios.php";

// Verificar si se recibió el usuario a eliminar
if (isset($_POST['usuario'])) {
    $correo = trim($_POST['usuario']);
    
    try {
        // Obtener instancia de la base de datos
        $db = ConexionBaseDeDatos::obtenerInstancia()->obtenerConexion();
        
        // Preparar y ejecutar la consulta para eliminar el usuario
        $sql = $db->prepare("DELETE FROM Credenciales WHERE correo = :correo");
        $sql->bindParam(':correo', $correo, PDO::PARAM_STR);
        
        if ($sql->execute()) {
            // Si se eliminó correctamente, redirigir con mensaje de éxito
            header("Location: ../vistas/formulario_borrar_usuario.php?success=1");
        } else {
            // Si hubo error, redirigir con mensaje de error
            header("Location: ../vistas/formulario_borrar_usuario.php?error=1");
        }
    } catch (PDOException $e) {
        // En caso de error en la base de datos
        header("Location: ../vistas/formulario_borrar_usuario.php?error=2");
    }
} else {
    // Si no se recibió el usuario, redirigir con error
    header("Location: ../vistas/formulario_borrar_usuario.php?error=3");
}