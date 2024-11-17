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
    <link rel="stylesheet" href="styles/shop.css">
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
                var_dump($_SESSION['idEmpresa']);
            }
            ?>
        </div>
        <div class="logout">
            <a href="logout.php">Cerrar Sesión</a>
        </div>
        <div class="shoppingCart">
            <a href="shoppingCart.php">🛒 Carrito</a>
        </div>
    </div>

    <!-- Productos -->
    <?php
    //
    include './SQL/productList.php';
    //Llamamos a la función para obtener los datos de conexión.
    $productos = productList();
    ?>

    <div class="product-grid">    
    <!-- Bucle para mostrar cada producto -->
    <?php foreach ($productos as $producto) { ?>
    
            <div class="product-list">
                <div class="product-name">
                    <?php echo '<p class="nombre">' .$producto["nombre"]. '</p>' ?>
                </div>
                <div class="product-price">
                    <?php echo '<p class="price">' . number_format($producto["precio"], 2) . '€</p>'; ?>
                </div>
                <div class="add-product-to-cart">
                    <form action="./SQL/addToCart.php" method="POST">
                        <input type="hidden" name="idProducto" value="<?php echo $producto['ID_Productos']; ?>">
                        <button type="submit">Añadir al carrito</button>
                    </form>
                </div>
            </div>
    <?php } ?>
    </div>

    <!-- Footer -->
    <div class="footer">
        <p>&copy; 2024 Grafo. Todos los derechos reservados.</p>
    </div>
        
</body>
</html>
