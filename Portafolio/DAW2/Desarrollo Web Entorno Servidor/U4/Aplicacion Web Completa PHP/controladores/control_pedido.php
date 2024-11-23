<?php
// Iniciar la sesión
session_start(); 

// Verificar si el usuario no ha iniciado sesión
if (!isset($_SESSION['usuario'])) {
    // Si no ha iniciado sesión, redirigir al login
    header("Location: formulario_login.php");
    exit(); // Asegurarse de salir del script
}

// Importamos la clase del CRUD de pedidos y carritos.
include_once __DIR__ . "/../modelos/crud_pedidos.php";
include_once __DIR__ . "/../modelos/crud_carrito.php";

// Obtenemos el código del carrito del usuario que hace el pedido.
$codigoCarrito = $_SESSION['codigo_carrito'];

// Crear un nuevo pedido
if ($_SERVER['REQUEST_METHOD'] === 'POST') {

    /**
     * Desde el INSERT se pone la fecha con VALUE (NOW) y el código de pedido
     * se genera por si solo y es autoincrementativo, por eso aquí no se les
     * ve.
     */
    $codigoCliente = $_SESSION['codigo_cliente']; // Suponiendo que el código del cliente está en la sesión
    $metodoPago = $_POST['metodoPago'];
    $estadoPedido = 'Pendiente';
    $productosPedido = carritoCRUD::leerProductoCarrito($codigoCarrito); 

    foreach ($productosPedido as $producto) {
        $detalles[] = [
            'cantidad' => $producto['cantidad_producto'],
            'codigoProducto' => $producto['codigo_producto']
        ];
    }

    // Llamamos al método para crear el pedido
    $codigoPedido = pedidosCRUD::crearPedido($codigoCliente, $metodoPago, $estadoPedido, $detalles);

    // Guardamos en $_SESSION el código del pedido como variable de uso global
    $_SESSION["codigoPedido"] = $codigoPedido;

    // Enviamos al location con el mensaje de confirmación del pedido.
    header("Location: ../vistas/confirmacion.php");
}