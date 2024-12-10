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

?>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inicio de Sesión</title>
    <link rel="stylesheet" href="../archivos/estilos.css">
</head>
<body>
    <?php include_once "header.php"; ?>
    <main>
        <div class="contenedor-principal">
            <br><br>
            <h1>Bienvenido a tumercado.com</h1>
            <div class="seccion">
                <!-- Div de la izquierda con H1 y párrafo -->
                <div class="izquierda">
                    <h1>Los mejores productos al mejor precio</h1>
                    <p>&emsp; ¿Que esperás? Registrate y disfruta de la mejor calidad, 
                        del mejor equipo, de los mejores precios y de los envios
                        más rapidos.</p>
                </div>
                <!-- Div de la derecha con el formulario -->
                <div class="derecha">
                    <form class="login-form" action="../controladores/registrar_usuario.php" method="post">
                        <label for="usuario">Usuario</label>
                        <input type="text" name="usuario" id="usuario" placeholder="Ingresa tu usuario">
                        <label for="contrasena">Contraseña</label>
                        <input type="password" name="contrasena" id="contrasena" placeholder="Ingresa tu contraseña">
                        <label for="cif_cliente">DNI/NIE/CIF</label>
                        <input type="text" name="cif_cliente" id="cif_cliente">
                        <label for="direccion">Dirección</label>
                        <input type="text" name="direccion" id="direccion">
                        <label for="codigoPostal">Código postal:</label>
                        <input type="text" name="codigoPostal" id="codigoPostal">
                        <label for="telefono">telefono</label>
                        <input type="text" name="telefono" id="telefono">
                        <label for="rol">Rol de usuario:</label>
                        <!-- Cambio del ejercicio 7 para permitir elegir el rol al registrar un usuario -->
                        <select name="rol" id="rol">
                            <option value="0">Usuario normal</option>
                            <option value="1">Administrador</option>
                        </select>
                        <br>
                        <input type="submit" value="Registrar nuevo usuario">
                    </form>
                </div>
            </div>
        </div>
    </main>
    <?php include_once "footer.php"; ?>
</body>
</html>
