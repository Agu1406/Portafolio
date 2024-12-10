<?php
// Iniciar la sesión
session_start();

// Verificar si hay un código de pedido en la sesión
if (!isset($_SESSION['codigoPedido'])) {
    // Si no hay código de pedido, redirigir al carrito o al inicio
    header("Location: index.php");
    exit();
}

// Obtener el código de pedido de la sesión
$codigoPedido = $_SESSION['codigoPedido'];
?>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Confirmación de Pedido</title>
    <link rel="stylesheet" href="../archivos/estilos.css?v=<?php echo time(); ?>">
</head>
<body>
    <?php include_once "header.php"; ?>
    <main>
    <div class="contenedor-principal">
        <h1>¡Gracias por tu pedido!</h1>

        <div class="message">
            <p>Tu pedido ha sido recibido correctamente y está siendo procesado. 
                El código de tu pedido es: <?php echo $codigoPedido; ?></p>
            <p>Te hemos enviado un correo de confirmación con los detalles de tu compra.</p>
        </div>

        <a href="catalogo.php" class="back-button">Volver al inicio</a>
    </div>
    </main>
    <?php include_once "footer.php"; ?>
</body>
</html>