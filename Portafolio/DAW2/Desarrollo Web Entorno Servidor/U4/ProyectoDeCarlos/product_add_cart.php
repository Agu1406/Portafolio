<?php
session_start();
include './SQL/addToCart.php'; // Archivo con la función addToCart

// Verificar si el ID del producto está presente
if (isset($_POST['idProducto'])) {
    $idProducto = intval($_POST['idProducto']); // Convertir a entero para mayor seguridad

   //Llamamos a la función para obtener los datos de conexión.
   $bd = dataBaseConnection();
   
   //Asignamos el ID de la Empresa a una variable
   $idSesion = $_SESSION['idEmpresa'];

   $sql = "INSERT INTO ShoppingCart (`Productos_ID_Productos`, `Empresa_ID_Empresa`, `Cantidad`)
   VALUES (:idProducto, :idEmpresa, :cantidad)";    
   $stmt = $bd->prepare($sql);
   $resultado = $stmt->execute([
       ':idProducto' => $idProducto, 
       ':idEmpresa' => $idSesion, 
       ':cantidad' => '1']);

    if ($resultado) {
        // Redirigir a la página de la tienda con un mensaje de éxito
        header("Location: shop.php?mensaje=Producto añadido al carrito");
        exit();
    } else {
        // Redirigir con un mensaje de error
        header("Location: shop.php?mensaje=Error al añadir el producto al carrito");
        exit();
    }
} else {
    // Si no se envió un ID de producto, redirigir a la tienda
    header("Location: shop.php?mensaje=No se seleccionó ningún producto");
    exit();
}
?>
