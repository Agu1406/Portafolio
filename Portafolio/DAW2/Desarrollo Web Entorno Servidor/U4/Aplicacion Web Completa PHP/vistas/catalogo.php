<?php
// Iniciar la sesión
session_start(); 

// Verificar si el usuario no ha iniciado sesión
if (!isset($_SESSION['usuario'])) {
    // Si no ha iniciado sesión, redirigir al login
    header("Location: login.php");
    exit(); // Asegurarse de salir del script
}
?>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Catálogo</title>
    <link rel="stylesheet" href="estilos.css">
</head>
<body>
    <div class="contenedor-principal">
        <h1>Catálogo de productos</h1>
        <p>Bienvenido, <?php echo $_SESSION['usuario']; ?>.</p>
        <!-- El resto de tu contenido de la página -->
    </div>
</body>
</html>
