<?php
include_once __DIR__ . "/../modelos/crud_carrito.php";
include_once __DIR__ . "/../modelos/crud_productos.php";

$carrito = CarritoCRUD::obtenerCarrito();
$productos_en_carrito = [];
foreach ($carrito as $articulo) {
    $producto = productoCRUD::leerProductoPorId($articulo['id_producto']);
    $producto['cantidad'] = $articulo['cantidad'];
    $productos_en_carrito[] = $producto;
}
?>

<table>
    <thead>
        <tr>
            <th>Producto</th>
            <th>Precio</th>
            <th>Cantidad</th>
            <th>Total</th>
            <th>Acciones</th>
        </tr>
    </thead>
    <tbody>
        <?php foreach ($productos_en_carrito as $producto): ?>
            <tr>
                <td><?php echo $producto['nombre_producto']; ?></td>
                <td>$<?php echo $producto['precio_producto']; ?></td>
                <td><?php echo $producto['cantidad']; ?></td>
                <td>$<?php echo $producto['precio_producto'] * $producto['cantidad']; ?></td>
                <td>
                    <form method="post" action="../controladores/controlar_carrito.php">
                        <input type="hidden" name="accion" value="eliminar">
                        <input type="hidden" name="id_producto" value="<?php echo $producto['id_producto']; ?>">
                        <button type="submit">Eliminar</button>
                    </form>
                </td>
            </tr>
        <?php endforeach; ?>
    </tbody>
</table>

<form method="post" action="../controladores/controlar_carrito.php">
    <input type="hidden" name="accion" value="vaciar">
    <button type="submit">Vaciar carrito</button>
</form>