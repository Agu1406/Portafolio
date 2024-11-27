<?php
// Inicio una sesión,
session_start();

// Verificar si el usuario no ha iniciado sesión
if (!isset($_SESSION['usuario'])) {
    // Si no ha iniciado sesión, redirigir al login
    header("Location: formulario_login.php");
    exit();
}

// Verificar si el usuario no es administrador
if ($_SESSION['rol'] !== "1") {
    // Si no es admin, redirigir al catálogo
    header("Location: catalogo.php"); 
    exit();
}

// Importar el modelo de usuario
include_once "../modelos/crud_usuarios.php";

// Obtener la lista de usuarios
$db = ConexionBaseDeDatos::obtenerInstancia()->obtenerConexion();
$sql = $db->prepare("SELECT correo FROM Credenciales");
$sql->execute();
$usuarios = $sql->fetchAll(PDO::FETCH_ASSOC);

?>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Borrar Usuario</title>
    <link rel="stylesheet" href="../archivos/estilos.css">
</head>
<body>
    <?php include_once "header.php"; ?>
    <main>
        <div class="contenedor-principal">
            <br><br>
            <h1>Borrar Usuario</h1>
            <div class="seccion">
                <!-- Div de la izquierda con H1 y párrafo -->
                <div class="izquierda">
                    <h1>Panel de Administración</h1>
                    <p>&emsp; Desde aquí puedes eliminar usuarios del sistema.
                        Por favor, selecciona el usuario que deseas eliminar.</p>
                </div>
                <!-- Div de la derecha con el formulario -->
                <div class="derecha">
                    <form class="login-form" action="../controladores/borrar_usuario.php" method="post">
                        <label for="usuario">Seleccionar Usuario:</label>
                        <select name="usuario" id="usuario">
                            <?php foreach($usuarios as $usuario): ?>
                                <option value="<?php echo htmlspecialchars($usuario['correo']); ?>">
                                    <?php echo htmlspecialchars($usuario['correo']); ?>
                                </option>
                            <?php endforeach; ?>
                        </select>
                        <br>
                        <input type="submit" value="Eliminar Usuario">
                    </form>
                </div>
            </div>
        </div>
    </main>
    <?php include_once "footer.php"; ?>
</body>
</html>
