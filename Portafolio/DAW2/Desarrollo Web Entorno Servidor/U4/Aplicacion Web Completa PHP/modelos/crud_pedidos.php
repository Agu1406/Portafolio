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

class pedidosCRUD {
    // Crear un nuevo pedido junto con sus detalles
    public static function crearPedido($codigoCliente, $metodoPago, $estadoPedido, $detalles) {
        global $conexion;
        try {
            $conexion->beginTransaction(); // Iniciamos la transacción
            
            // Inserción en la tabla Pedido
            $sqlPedido = "INSERT INTO Pedido (fecha_pedido, estado_pedido, metodo_pago, cliente_codigo_cliente) 
                          VALUES (NOW(), :estadoPedido, :metodoPago, :codigoCliente)";
                          
            $stmtPedido = $conexion->prepare($sqlPedido);
            $stmtPedido->bindParam(":estadoPedido", $estadoPedido);
            $stmtPedido->bindParam(":metodoPago", $metodoPago);
            $stmtPedido->bindParam(":codigoCliente", $codigoCliente);
            $stmtPedido->execute();

            // Obtener el ID del pedido recién creado
            $codigoPedido = $conexion->lastInsertId();

            // Inserción de detalles en la tabla Detalle_Pedido
            $sqlDetalle = "INSERT INTO Detalle_Pedido (cantidad_producto, Pedido_codigo_pedido, Producto_codigo_producto) 
                           VALUES (:cantidad, :codigoPedido, :codigoProducto)";
            $stmtDetalle = $conexion->prepare($sqlDetalle);

            foreach ($detalles as $detalle) {
                $stmtDetalle->bindParam(":cantidad", $detalle['cantidad']);
                $stmtDetalle->bindParam(":codigoPedido", $codigoPedido);
                $stmtDetalle->bindParam(":codigoProducto", $detalle['codigoProducto']);
                $stmtDetalle->execute();
            }

            $conexion->commit(); // Confirmamos la transacción
            return $codigoPedido; // Devolvemos el ID del pedido creado
        } catch (Exception $e) {
            $conexion->rollBack(); // Revertimos la transacción en caso de error
            die("Error al crear el pedido: " . $e->getMessage());
        }
    }

    // Obtener detalles de un pedido específico
    public static function detallesPedido($codigoPedido) {
        global $conexion;
        try {
            // Consultar la información del pedido
            $sqlPedido = "SELECT * FROM Pedido WHERE codigo_pedido = :codigoPedido";
            $stmtPedido = $conexion->prepare($sqlPedido);
            $stmtPedido->bindParam(":codigoPedido", $codigoPedido);
            $stmtPedido->execute();
            $pedido = $stmtPedido->fetch(PDO::FETCH_ASSOC);

            if (!$pedido) {
                return null; // Si no se encuentra el pedido
            }

            // Consultar los detalles del pedido
            $sqlDetalles = "SELECT dp.cantidad_producto, p.nombre_producto, p.precio_producto 
                            FROM Detalle_Pedido dp 
                            JOIN Producto p ON dp.Producto_codigo_producto = p.codigo_producto 
                            WHERE dp.Pedido_codigo_pedido = :codigoPedido";
            $stmtDetalles = $conexion->prepare($sqlDetalles);
            $stmtDetalles->bindParam(":codigoPedido", $codigoPedido);
            $stmtDetalles->execute();
            $detalles = $stmtDetalles->fetchAll(PDO::FETCH_ASSOC);

            return ["pedido" => $pedido, "detalles" => $detalles];
        } catch (Exception $e) {
            die("Error al obtener los detalles del pedido: " . $e->getMessage());
        }
    }
    
    // Actualizar únicamente el estado de un pedido
    public static function actualizarPedido($codigoPedido, $estadoPedido) {
        global $conexion;
        try {
            $sql = "UPDATE Pedido 
                    SET estado_pedido = :estadoPedido 
                    WHERE codigo_pedido = :codigoPedido";
            $stmt = $conexion->prepare($sql);
            $stmt->bindParam(":estadoPedido", $estadoPedido);
            $stmt->bindParam(":codigoPedido", $codigoPedido);
            $stmt->execute();

            return $stmt->rowCount() > 0; // Devuelve true si se actualizó alguna fila
        } catch (Exception $e) {
            die("Error al actualizar el estado del pedido: " . $e->getMessage());
        }
    }

    // Eliminar un pedido y sus detalles
    public static function borrarPedido($codigoPedido) {
        global $conexion;
        try {
            $sql = "DELETE FROM Pedido WHERE codigo_pedido = :codigoPedido";
            $stmt = $conexion->prepare($sql);
            $stmt->bindParam(":codigoPedido", $codigoPedido);
            $stmt->execute();

            return $stmt->rowCount() > 0; // Devuelve true si se eliminó alguna fila
        } catch (Exception $e) {
            die("Error al eliminar el pedido: " . $e->getMessage());
        }
    }
}