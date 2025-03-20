<?php
// Iniciar sesión
session_start();

// Inicializar el carrito si no existe
if (!isset($_SESSION['carrito'])) {
    $_SESSION['carrito'] = [];
}

// Añadir un producto al carrito
$_SESSION['carrito'][1] = 2; // Producto ID 1, cantidad 2

// Mostrar el contenido del carrito
echo "Contenido del carrito:<br>";
print_r($_SESSION['carrito']);

echo "<br><br>Puedes ir a la página de checkout: <a href='publico/checkout'>Ir al checkout</a>";
?> 