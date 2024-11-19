<?php
// Iniciar sesión
session_start();

// Verificar si el usuario no ha iniciado sesión
if (!isset($_SESSION['usuario'])) {
    // Si no ha iniciado sesión, redirigir al login
    header("Location: formulario_login.php");

    // Sale del script luego de redirigir.
    exit();
}
?>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Agregar Categoría</title>
    <link rel="stylesheet" href="../archivos/estilos.css">
</head>
<body>
    <?php include_once "header.php"; ?>
    <main>
    <div class="contenedor-principal">
        <h1>Bienvenido a tumercado.com</h1>
        <div class="seccion">
            <!-- Div de la izquierda con H1 y párrafo -->
            <div class="izquierda">
                <h1>¡Añade una nueva categoría!</h1>
                <p>&emsp; Rellena el formulario para añadir una nueva categoría a tu tienda.</p>
            </div>
            <!-- Div de la derecha con el formulario -->
            <div class="derecha">
                <form class="login-form" action="../controladores/agregar_categoria.php" method="post">
                    <label for="nombre_categoria">Nombre de la Categoría</label>
                    <input type="text" name="nombre_categoria" id="nombre_categoria" placeholder="Ingresa el nombre de la categoría" required>
                    
                    <input type="submit" value="Añadir Categoría">
                </form>
            </div>
        </div>
    </div>
    </main>
    <?php include_once "footer.php"; ?>
</body>
</html>