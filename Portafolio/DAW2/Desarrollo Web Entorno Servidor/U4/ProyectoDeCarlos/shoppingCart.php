<?php
//Iniciamos la sesión para mantener la sesión del usuario que ha logado
session_start();
if (!isset($_SESSION['usuario'])){
    $_SESSION['usuario']=null;
    header("Location: login.php");
    exit();
}else{
    $mensajeBienvenida = "Bienvenido, ". $_SESSION['usuario'];
}
?>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Grafo - Tienda Online</title>
    <link rel="stylesheet" href="./styles/shop.css">
</head>
<body>

    <!-- Header -->
    <div class="header">
        <div class="logo">
            <h1>Grafo</h1> <!--Imagen + funcion inicio-->
        </div>
        <div class="usuario">
            <?php
            if (isset($mensajeBienvenida)) {
                echo $mensajeBienvenida;
            }
            ?>
        </div>
        <div class="logout">
            <a href="logout.php">Cerrar Sesión</a>
        </div>
        <div class="shoppingCart">
            <a href="#carrito">🛒 Carrito</a>
        </div>
    </div>

<!----------------------------------------------------------------------------------------------->
<?php
include './SQL/getProductsInCart.php';
$productos = obtenerProductosEnCarrito();

if (empty($productos)) {
?>
    <section class="hero is-info">
        <div class="hero-body">
            <div class="container">
                <h1 class="title">
                    Todavía no hay productos
                </h1>
                <h2 class="subtitle">
                    Visita la tienda para agregar productos a tu carrito
                </h2>
                <a href="shop.php" class="button is-warning">Ver tienda</a>
            </div>
        </div>
    </section>
<?php } else { ?>
    <div class="columns">
        <div class="column">
            <h2 class="is-size-2">Mi carrito de compras</h2>
            <table class="table">
                <thead>
                    <tr>
                        <th>Nombre</th>
                        <th>Precio</th>
                        <th>Cantidad</th>
                        <th>Subtotal</th>
                        <th>Quitar</th>
                    </tr>
                </thead>
                <tbody>
                    <?php
                    $total = 0;
                    foreach ($productos as $producto) {
                        $subtotal = $producto['precio'] * $producto['Cantidad'];
                        $total += $subtotal;
                    ?>
                        <tr>
                            <td><?php echo htmlspecialchars($producto['nombre']); ?></td>
                            <td><?php echo number_format($producto['precio'], 2); ?>€</td>
                            <td><?php echo $producto['Cantidad']; ?></td>
                            <td><?php echo number_format($subtotal, 2); ?>€</td>
                            <td>
                            <form action="./SQL/deleteFromCart.php" method="POST">
                                <input type="hidden" name="idProducto" value="<?php echo $producto['ID_Productos']; ?>">
                                <button type="submit">Eliminar</button>
                                <?php echo $producto['ID_Productos']; ?>
                            </form>
                            </td>
                        </tr>
                    <?php } ?>
                </tbody>
                <tfoot>
                    <tr>
                        <td colspan="4" class="is-size-4 has-text-right"><strong>Total</strong></td>
                        <td colspan="2" class="is-size-4">
                            <?php echo number_format($total, 2); ?>€
                        </td>
                    </tr>
                </tfoot>
            </table>
            <a href="terminar_compra.php" class="button is-success is-large">
                <i class="fa fa-check"></i>&nbsp;Terminar compra
            </a>
        </div>
    </div>
<?php } ?>


<!----------------------------------------------------------------------------------------------->


    <!-- Footer -->
    <div class="footer">
        <p>&copy; 2024 Grafo. Todos los derechos reservados.</p>
    </div>
        
</body>
</html>