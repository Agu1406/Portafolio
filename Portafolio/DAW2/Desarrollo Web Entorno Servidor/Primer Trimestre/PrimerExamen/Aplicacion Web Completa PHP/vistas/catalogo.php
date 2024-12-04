<?php
// Iniciar la sesión
session_start(); 

// Verificar si el usuario no ha iniciado sesión
if (!isset($_SESSION['usuario'])) {
    // Si no ha iniciado sesión, redirigir al login
    header("Location: formulario_login.php");
    exit(); // Asegurarse de salir del script
}

if (!isset($_SESSION['codigo_cliente'])) {
    die("Error: No se encontró el código del cliente en la sesión.");
}

// Incluir el archivo que contiene la clase productosCRUD para cargar los productos
include_once __DIR__ . "/../modelos/crud_productos.php";

// Obtener los productos
$productos = productoCRUD::leerProducto();

// Filtrar productos con stock > 0, es decir, que tengan stock.
$productos = array_filter($productos, function($producto) {
    return $producto['stock'] > 0;
});

// Verificar si se ha recibido la acción de añadir al carrito
if (isset($_POST['accion']) && $_POST['accion'] == 'añadir') {
    // Obtener los datos del producto y la cantidad
    $codigo_producto = $_POST['codigo_producto'];
    $cantidad = $_POST['cantidad'];

    // Verificar si la cantidad es al menos 1
    if ($cantidad >= 1) {
        // Aquí se podría agregar el producto al carrito (almacenado en sesión o base de datos)
        if (!isset($_SESSION['carrito'])) {
            $_SESSION['carrito'] = []; // Inicializar el carrito si no existe
        }

        // Verificar si el producto ya está en el carrito
        if (isset($_SESSION['carrito'][$codigo_producto])) {
            $_SESSION['carrito'][$codigo_producto]['cantidad_producto'] += $cantidad; // Aumentar la cantidad
        } else {
            // Si el producto no está en el carrito, añadirlo
            $_SESSION['carrito'][$codigo_producto] = [
                'codigo_producto' => $codigo_producto,
                'cantidad_producto' => $cantidad,
            ];
        }
    }
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
                            <img src="<?php echo htmlspecialchars($producto['imagen']); ?>" alt="<?php echo htmlspecialchars($producto['nombre_producto']); ?>">
                            <h3><?php echo htmlspecialchars($producto['nombre_producto']); ?></h3>
                            <p><?php echo htmlspecialchars($producto['descripcion_producto']); ?></p>
                            <p>Precio: $<?php echo number_format($producto['precio_producto'], 2, ',', '.'); ?></p>
                            
                            <!-- Formulario para añadir producto al carrito -->
                            <form method="POST" action="../controladores/control_carrito.php">
                                <!-- Este "hidden" es el que le dice a "control_carrito.php" que acción ejecutar en el switch-case. -->
                                <input type="hidden" name="accion" value="agregar">

                                <!-- Esto inserta de forma automatica el código del producto que quiero añadir -->
                                <input type="hidden" name="codigo_producto" value="<?php echo htmlspecialchars($producto['codigo_producto']); ?>">
                                
                                <!-- Aquí introduzco la cantidad del producto que quiero pedir. -->
                                <label for="cantidad_<?php echo $producto['codigo_producto']; ?>">Cantidad:</label>
                                <br>
                                <input type="number" name="cantidad" id="cantidad_<?php echo $producto['codigo_producto']; ?>" value="1" min="1">
                                <button type="submit">Añadir al carrito</button>
                            </form>
                        </div>
                    <?php endforeach; ?>
                </div>
            <?php endif; ?>
        </div>
    </main>

    <?php include_once "footer.php"; ?>
</body>
</html>
