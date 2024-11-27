<?php
// Iniciar la sesión
session_start(); 

// Verificar si el usuario no ha iniciado sesión
if (!isset($_SESSION['usuario'])) {
    // Si no ha iniciado sesión, redirigir al login
    header("Location: formulario_login.php");
    exit(); // Asegurarse de salir del script
}

// Incluir el controlador del carrito para leer el contenido del carrito.
include_once __DIR__ . "/../controladores/control_carrito.php";

// Obtener los productos del carrito en forma de Array asociativo.
$productosPedido = carritoCRUD::leerProductoCarrito($codigoCarrito);


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
        <div class="seccion">
            <!-- Parte izquierda con un mensaje introductorio -->
            <div class="izquierda">
                <h1>Confirma tú pedido</h1>
                <p>Ya casi has terminado, ¡ánimo! Solo falta confirmar los
                    detalles de tu pedido y estarás listo. El departamento
                    de pedidos se comunicará contigo en los siguientes 2
                    días naturales.
                </p>
            </div>

            <!-- Parte derecha con el formulario de pedido -->
            <div class="derecha">
                <!-- Formulario para crear un nuevo pedido -->
                <h2>Detalles de tú pedido:</h2>
                <?php if (!empty($productosPedido)): ?>
                    <table class="tabla-carrito">
                        <thead>
                            <tr>
                                <th>Nombre</th>
                                <th>Cantidad</th>
                                <th>Precio</th>
                                <th>Total</th>
                            </tr>
                        </thead>
                        <tbody>
                            <?php
                            $totalPedido = 0;
                            foreach ($productosPedido as $producto): 
                                $codigoProducto = $producto['codigo_producto'];
                                $cantidadProducto = $producto['cantidad_producto'];

                                $nombreProducto = $producto["nombre_producto"];
                                $precioProducto = $producto["precio_producto"];
                                
                                $totalProducto = $precioProducto * $cantidadProducto;
                                $totalPedido += $totalProducto;
                            ?>
                                <tr>
                                    <td><?php echo htmlspecialchars($nombreProducto); ?></td>
                                    <td><?php echo htmlspecialchars($cantidadProducto); ?></td>
                                    <td><?php echo number_format($precioProducto, 2, ',', '.'); ?>€</td>
                                    <td><?php echo number_format($totalProducto, 2, ',', '.'); ?>€</td>
                                </tr>
                            <?php endforeach; ?>
                        </tbody>
                    </table>
                    <div class="total-pedido">
                        <h3>Total del pedido: <?php echo number_format($totalPedido, 2, ',', '.'); ?>€</h3>
                    </div>
                <?php else: ?>
                    <p>No hay productos en el pedido.</p>
                <?php endif; ?>

                <!-- Formulario para elegir el método de pago -->
                <h2>Confirma tú pedido</h2>
                <form class="login-form" method="POST" action="../controladores/control_pedido.php">
                    <label for="metodoPago">Elige tú metodo de pago:</label>
                    <select name="metodoPago" id="metodoPago">
                        <option value="tarjeta">Tarjeta</option>
                        <option value="transferencia">Transferencia</option>
                        <option value="efectivo">Efectivo</option>
                    </select>
                    <br>
                    <input type="submit" value="Confirmar pedido">
                </form>
            </div>
        </div>
    </div>
    </main>
    <?php include_once "footer.php"; ?>
</body>
</html>