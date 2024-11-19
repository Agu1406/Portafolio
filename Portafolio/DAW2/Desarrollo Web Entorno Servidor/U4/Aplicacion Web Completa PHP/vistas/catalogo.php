<?php
// Iniciar la sesión
session_start(); 

// Verificar si el usuario no ha iniciado sesión
if (!isset($_SESSION['usuario'])) {
    // Si no ha iniciado sesión, redirigir al login
    header("Location: login.php");
    exit(); // Asegurarse de salir del script
}

// Incluir el archivo que contiene la clase productosCRUD para cargar los productos
include_once __DIR__ . "/../modelos/crud_productos.php";

// Obtener los productos
$productos = productoCRUD::leerProducto();
?>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Catálogo</title>
    <link rel="stylesheet" href="../archivos/estilos.css?v=<?php echo time(); ?>">
</head>
<body>
    <?php include_once "header.php"; ?>

    <main>
        <div class="contenedor-principal">
            <h1>Catálogo de productos</h1>
            <?php if (count($productos) == 0): ?>
                <p>No hay productos disponibles en este momento.</p>
            <?php else: ?>
                <div class="productos-grid">
                    <?php foreach ($productos as $producto): ?>
                        <div class="producto">
                            <img src="<?php echo $producto['imagen']; ?>" alt="<?php echo $producto['nombre_producto']; ?>">
                            <h3><?php echo $producto['nombre_producto']; ?></h3>
                            <p><?php echo $producto['descripcion_producto']; ?></p>
                            <p>Precio: $<?php echo $producto['precio_producto']; ?></p>                        </div>
                    <?php endforeach; ?>
                </div>
            <?php endif; ?>
        </div>
    </main>

    <?php include_once "footer.php"; ?>
</body>
</html>
