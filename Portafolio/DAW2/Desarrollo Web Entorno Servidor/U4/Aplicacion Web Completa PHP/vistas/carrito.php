<?php
// Iniciar la sesión
session_start(); 

// Verificar si el usuario no ha iniciado sesión
if (!isset($_SESSION['usuario'])) {
    // Si no ha iniciado sesión, redirigir al login
    header("Location: formulario_login.php");
    exit(); // Asegurarse de salir del script
}
?>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Catálogo</title>
    <link rel="stylesheet" href="../archivos/estilos.css?v=<?php echo time(); ?>">
</head>
<?php include_once "header.php"; ?>
<main>
<body>
    <div class="contenedor-principal">
        <h1>Tu Carrito de Compras</h1>
        <!-- Si existe el array productos y no está vacio pinta toda la tabla y sus valores -->
        <?php if (isset($productos) && count($productos) > 0): ?>
        <table class="tabla-carrito">
            <thead>
                <tr>
                    <th>Nombre</th>
                    <th>Descripción</th>
                    <th>Precio</th>
                    <th>Cantidad</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <!-- Logica que carga con un foreach cada uno de los productos en el array asociativo y sus valores -->
                <?php 

                // Variable que se actualizara al precio total de todos los productos.
                $total = 0;

                // Recorre todos los productos uno por uno en el array asociativo y va actualizando el total del carrito.
                foreach ($productos as $producto): 
                    $total += $producto['precio'] * $producto['cantidad_producto']; // Calculamos el total de la línea
                ?>

                <!-- Pinta el nombre de "X" producto, su descripción y su precio. -->
                <tr>
                    <td><?php echo htmlspecialchars($producto['nombre_producto']); ?></td>
                    <td><?php echo htmlspecialchars($producto['descripcion']); ?></td>
                    <td><?php echo number_format($producto['precio'], 2, ',', '.'); ?>€</td>
                    <td>
                        <input type="number" value="<?php echo $producto['cantidad_producto']; ?>" min="1">
                    </td>
                    <td>
                        <form method="POST" action="carrito.php">
                            <input type="hidden" name="accion" value="borrar">
                            <input type="hidden" name="codigo_producto" value="<?php echo htmlspecialchars($producto['codigo_producto']); ?>">
                            <button type="submit">Borrar</button>
                        </form>
                    </td>
                </tr>
                <?php endforeach; ?>
            </tbody>
        </table>
        <!-- Este es el DIV donde se pinta el total del carrito con decimales y todo. -->
        <div class="total-pedido">
            <h3>Total: <?php echo number_format($total, 2, ',', '.'); ?>€</h3>
        </div>
        <!-- En el caso de que el array este vacio, solo se pinta el "<p>Carrito vacío</p>" -->
        <?php else: ?>
        <p>Carrito vacío</p>
        <?php endif; ?>
    </div>
</body>
</main>
<?php include_once "footer.php"; ?>
</html>