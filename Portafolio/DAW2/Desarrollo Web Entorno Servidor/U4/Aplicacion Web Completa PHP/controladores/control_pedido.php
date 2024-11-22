<?php
/**
 * Este controlador se encarga de recibir un array asociativo desde "carrito.php" con el
 * método POST y de esa forma tener en él todos los productos dentro del carrito de "X"
 * cliente.
 */

 // Arrancamos una sesión aquí,
 session_start();

// Si no existe una sesión el usuario actualmente en el PHP se ejecuta el if.
 if (!isset($_SESSION["usuario"])) {
    // Si no ha iniciado sesión, le llevamos al incio de sesión.
    header ("Location: ../vistas/formulario_login.php");
 }

 // Verificamos que el método de envio desde carrito.php es POST
 if ($_SERVER["REQUEST_METHOD"] == "POST") {
    // Verificamos si existe la variable "productos" y si es un array.
    if ((isset($_POST["productos"]) && is_array($_POST["productos"]))) {
        // Extraemos de POST el array lleno de productos y lo guardamos en una variable
        $productosCarrito = $_POST["productos"];

        
    } else {
        // En caso de que no existe productos o no sea un array.
        echo "No hay productos en el carrito, lo siento.";
    }
 }