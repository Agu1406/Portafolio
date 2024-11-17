<?php
session_start();
include __DIR__ . '/../Connection/bd_conexion.php';

// Verificar si el ID del producto está presente
if (isset($_POST['idProducto'])) {
    $idProducto = intval($_POST['idProducto']); // Convertir a entero para mayor seguridad
    $idSesion = $_SESSION['idEmpresa'];  // Obtener el ID de la empresa de la sesión

    // Llamamos a la función para obtener los datos de conexión
    $bd = dataBaseConnection();

    // Preparar la consulta para eliminar el producto del carrito
    $sql = "DELETE FROM ShoppingCart 
    WHERE Productos_ID_Productos = :idProducto AND Empresa_ID_Empresa = :idEmpresa";
    
    $stmt = $bd->prepare($sql);
    $resultado = $stmt->execute([':idProducto' => $idProducto, ':idEmpresa' => $idSesion]);

    if ($resultado) {
        // Redirigir a la página de la tienda con un mensaje de éxito
        header("Location: ../shoppingCart.php?mensaje=Producto eliminado del carrito");
        exit();
    } else {
        // Redirigir con un mensaje de error
        header("Location: ../shoppingCart.php?mensaje=Error al eliminar el producto del carrito");
        exit();
    }
} else {
    // Si no se envió un ID de producto, redirigir a la tienda
    header("Location: ../shoppingCart.php?mensaje=No se seleccionó ningún producto");
    exit();
}
?>
