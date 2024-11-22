<?php
// Iniciar la sesión
session_start(); 

// Verificar si el usuario no ha iniciado sesión
if (!isset($_SESSION['usuario'])) {
    // Si no ha iniciado sesión, redirigir al login
    header("Location: formulario_login.php");
    exit(); // Asegurarse de salir del script
}

// Incluir el controlador del carrito
include_once __DIR__ . "/../controladores/control_carrito.php";

// Obtener los productos del carrito
$productosCarrito = carritoCRUD::leerProductoCarrito($codigoCarrito);

if (!is_array($productosCarrito)) {
    echo "Error leyendo los productos del carrito, el valor devuelto no es un array";
    die;
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
        <!-- Si existe el array productos y no está vacío, pinta toda la tabla y sus valores -->
        <?php if (isset($productosCarrito) && count($productosCarrito) > 0): ?>
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
                <!-- Lógica que carga con un foreach cada uno de los productos en el array asociativo y sus valores -->
                <?php 
                // Variable que se actualizará al precio total de todos los productos
                $total = 0;

                // Recorre todos los productos uno por uno en el array asociativo y va actualizando el total del carrito
                foreach ($productosCarrito as $producto): 
                    $total += $producto['precio_producto'] * $producto['cantidad_producto']; // Calculamos el total de la línea
                ?>
                <tr>
                    <td><?php echo htmlspecialchars($producto['nombre_producto']); ?></td>
                    <td><?php echo htmlspecialchars($producto['descripcion_producto']); ?></td>
                    <td><?php echo number_format($producto['precio_producto'], 2, ',', '.'); ?>€</td>
                    <td>
                        <!-- Formulario para actualizar la cantidad de un producto en el carrito -->
                        <form method="POST" action="carrito.php" style="display: inline-block; margin-right: 10px;">
                            <input type="number" name="cantidad_producto" value="<?php echo $producto['cantidad_producto']; ?>" min="1">
                            <input type="hidden" name="accion" value="actualizar">
                            <input type="hidden" name="codigo_producto" value="<?php echo htmlspecialchars($producto['codigo_producto']); ?>">
                            <button type="submit">Actualizar</button>
                        </form>
                    </td>
                    <td>
                        <!-- Formulario para borrar el producto -->
                        <form method="POST" action="carrito.php" style="display: inline-block;">
                            <input type="hidden" name="accion" value="borrar">
                            <input type="hidden" name="codigo_producto" value="<?php echo htmlspecialchars($producto['codigo_producto']); ?>">
                            <button type="submit">Borrar</button>
                        </form>
                    </td>
                </tr>
                <?php endforeach; ?>
            </tbody>
        </table>

        <!-- Este es el DIV donde se pinta el total del carrito con decimales y todo -->
        <div class="total-pedido">
            <h3>Total: <?php echo number_format($total, 2, ',', '.'); ?>€</h3>
        </div>
        <div class="medio">
        <!-- Formulario para hacer el pedido -->
            <form class="login-form" method="POST" action="pedido.php">
                <!-- Pasar cada producto y su cantidad en un campo oculto -->
                <?php foreach ($productosCarrito as $producto): ?>
                    <input type="hidden" name="productos[<?php echo htmlspecialchars($producto['codigo_producto']); ?>][codigo]" value="<?php echo htmlspecialchars($producto['codigo_producto']); ?>">
                    <input type="hidden" name="productos[<?php echo htmlspecialchars($producto['codigo_producto']); ?>][cantidad]" value="<?php echo $producto['cantidad_producto']; ?>">
                <?php endforeach; ?>
                <button type="submit" class="btn-pedido">Hacer pedido</button>
            </form>
        </div>
        <!-- En el caso de que el array esté vacío, solo se pinta el "<p>Carrito vacío</p>" -->
        <?php else: ?>
        <p>Carrito vacío, ¿Por qué no añades algunos productos? :P</p>
        <?php endif; ?>
    </div>
</body>
</main>
<?php include_once "footer.php"; ?>
</html>
