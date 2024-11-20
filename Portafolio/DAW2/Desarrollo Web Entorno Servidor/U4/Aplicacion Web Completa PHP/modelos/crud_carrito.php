<?php
// Importamos la conexión de la base de datos.
include_once "conexion_bd.php";

// Al haber importado el script, tenemos acceso a sus métodos y funciones, los usamos.
try {
    // Obtener la instancia de la clase ConexionBaseDeDatos
    $baseDeDatos = ConexionBaseDeDatos::obtenerInstancia();
    // Obtener la conexión de la base de datos desde la instancia.
    $conexion = $baseDeDatos->obtenerConexion();
} catch (Exception $e) {
    // Manejo de errores más específico
    die("Error: No se ha podido conectar a la base de datos, " . $e->getMessage());
}

class carritoCRUD {
  // Método para agregar un producto al carrito
  public function agregarProductoCarrito($codigoCarrito, $codigoProducto, $cantidad) {
    try {
        // Obtenemos la conexión a la base de datos
        $conexion = ConexionBaseDeDatos::obtenerInstancia()->obtenerConexion();
        
        $sql = $conexion -> prepare(
            "INSERT INTO Detalle_Carrito (cantidad_producto, Carrito_codigo_carrito, Producto_codigo_producto) 
             VALUES (:cantidad, :codigo_carrito, :codigo_producto)"
        );
        $sql->bindParam(':cantidad', $cantidad, PDO::PARAM_INT);
        $sql->bindParam(':codigo_carrito', $codigoCarrito, PDO::PARAM_INT);
        $sql->bindParam(':codigo_producto', $codigoProducto, PDO::PARAM_INT);
        $sql->execute();

        return "Producto agregado al carrito correctamente.";

    } catch (Exception $e) {

        return "Error al agregar el producto al carrito: " . $e->getMessage();
    }
}

// Método para leer los productos del carrito de un usuario
public function leerProductoCarrito($codigoCarrito) {
    try {
        // Obtenemos la conexión a la base de datos
        $conexion = ConexionBaseDeDatos::obtenerInstancia()->obtenerConexion();
        
        $sql = $conexion -> prepare(
            "SELECT p.nombre_producto, dc.cantidad_producto, p.precio 
             FROM Detalle_Carrito dc
             INNER JOIN Producto p ON dc.Producto_codigo_producto = p.codigo_producto
             WHERE dc.Carrito_codigo_carrito = :codigo_carrito"
        );

        $sql->bindParam(':codigo_carrito', $codigoCarrito, PDO::PARAM_INT);

        $sql->execute();

        return $sql->fetchAll(PDO::FETCH_ASSOC); // Devuelve un array asociativo con los productos
    } catch (Exception $e) {

        return "Error al leer los productos del carrito: " . $e->getMessage();
    }
}

// Método para actualizar la cantidad de un producto en el carrito
public function actualizarProductoCarrito($codigoCarrito, $codigoProducto, $nuevaCantidad) {
    try {
        // Obtenemos la conexión a la base de datos
        $conexion = ConexionBaseDeDatos::obtenerInstancia()->obtenerConexion();
        
        $sql = $conexion -> prepare(
            "UPDATE Detalle_Carrito 
             SET cantidad_producto = :nueva_cantidad 
             WHERE Carrito_codigo_carrito = :codigo_carrito 
             AND Producto_codigo_producto = :codigo_producto"
        );

        $sql->bindParam(':nueva_cantidad', $nuevaCantidad, PDO::PARAM_INT);
        $sql->bindParam(':codigo_carrito', $codigoCarrito, PDO::PARAM_INT);
        $sql->bindParam(':codigo_producto', $codigoProducto, PDO::PARAM_INT);

        $sql->execute();

        return "Cantidad actualizada correctamente.";

    } catch (Exception $e) {

        return "Error al actualizar el producto en el carrito: " . $e->getMessage();
    }
}

// Método para eliminar un producto del carrito
public function borrarProductoCarrito($codigoCarrito, $codigoProducto) {
    try {
        // Obtenemos la conexión a la base de datos
        $conexion = ConexionBaseDeDatos::obtenerInstancia()->obtenerConexion();
        
        $sql = $conexion -> prepare(
            "DELETE FROM Detalle_Carrito 
             WHERE Carrito_codigo_carrito = :codigo_carrito 
             AND Producto_codigo_producto = :codigo_producto"
        );

        $sql->bindParam(':codigo_carrito', $codigoCarrito, PDO::PARAM_INT);
        $sql->bindParam(':codigo_producto', $codigoProducto, PDO::PARAM_INT);

        $sql->execute();

        return "Producto eliminado del carrito correctamente.";
        
    } catch (Exception $e) {

        return "Error al eliminar el producto del carrito: " . $e->getMessage();
    }
}
}