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

class productoCRUD {
    public static function crearProducto ($nombre_producto, $descripcion_producto, $precio_producto, $stock, $imagen, $categoria_codigo_categoria) {
        // Obtenemos la instancia de la conexión de la base de datos y su conexión.
        $conexion = ConexionBaseDeDatos::obtenerInstancia() -> obtenerConexion();

        /**
         * Para crear un producto lo mejor es iniciar una transacción, eso nos
         * permite en caso de fallos por "X" motivo hacer un rollback y de
         * esa forma deshacer los cambios o fallos.
         */
        $conexion -> beginTransaction();

        /**
         * Con las siguientes instrucciones verificiamos si actualmente
         * ya existe ese mismo producto en la base de datos, si existe
         * avisamos de ello, si no existe, continuamos con el script.
         */

        /**
         * Esta consulta seleccionara de toda la tabla "Producto" todos
         * los productos cuyo nombre de producto sea igual al 
         * proporcionado en los argumentos del método.
         */
        $sql = $conexion -> prepare("SELECT COUNT(*) FROM Producto WHERE nombre_producto = :nombre_producto");

        // Asocia a ":nombre_producto" el proporcionado en los argumentos.
        $sql -> bindParam(":nombre_producto", $nombre_producto, PDO::PARAM_STR);

        // Ejecutamos la consulta
        $sql -> execute();

        // Con "fetchColumn" guardamos los resultados en un array
        $productoExistente = $sql -> fetchColumn();

        // Si el array no esta vacio, es decir, tiene "1" valor o más.
        if ($productoExistente > 0) {
            // Hacemos "rollBack" para deshacer cualquier cambio hecho.
            $conexion -> rollBack();

            return "Ya existe un producto con el mismo nombre.";
        }

        // Si anteriomente no encontramos que ya existia el producto, procedemos a insertarlo en la base de datos
        $sql = $conexion -> prepare(
    "INSERT INTO Producto (nombre_producto, descripcion_producto, precio_producto, stock, imagen, categoria_codigo_categoria)
            VALUES (:nombre_producto, :descripcion_producto, :precio_producto, :stock, :imagen, :categoria_codigo_categoria)"
        );

        // Usando bindParam asignamos valores a los "values" con los datos recibidos como argumentos.
        $sql -> bindParam(":nombre_producto", $nombre_producto, PDO::PARAM_STR);
        $sql -> bindParam(":descripcion_producto", $descripcion_producto, PDO::PARAM_STR);
        $sql -> bindParam(":precio_producto", $precio_producto, PDO::PARAM_STR);
        $sql -> bindParam(":stock", $stock, PDO::PARAM_INT);
        $sql -> bindParam(":imagen", $imagen, PDO::PARAM_LOB);
        $sql -> bindParam(":categoria_codigo_categoria", $categoria_codigo_categoria, PDO::PARAM_INT);

        // Ejecutamos la consulta SQL para realizar los cambios
        $sql -> execute();

        // Hacemos el commit si todo ha ido bien.
        $conexion -> commit();

        return "Producto creado con exito";
    }

    public static function leerProducto() {
        // Obtenemos la instancia de la conexión de la base de datos
        $conexion = ConexionBaseDeDatos::obtenerInstancia()->obtenerConexion();
    
        // Encerramos todo en un try-catch, intentamos leer productos, si no, devolvemos error.
        try {
            // Preparo la consulta para leer la base de datos y extraer los productos con stock > 0
            $sql = $conexion->prepare("SELECT codigo_producto, nombre_producto, descripcion_producto, precio_producto, stock, imagen FROM Producto WHERE stock > 0");
    
            // Ejecutamos la consulta
            $sql->execute();
    
            // Almacenamos todos los productos y sus valores con fetchAll en un array asociativo.
            $productos = $sql->fetchAll(PDO::FETCH_ASSOC);
    
            // Si no hay productos, retornamos un String que avisa de ello.
            if (count($productos) == 0) {
                return "No hay productos disponibles.";
            }
    
            // Convertir las imágenes binarias a base64 antes de devolver los productos
            foreach ($productos as &$producto) {
                // Verificamos si la imagen existe (para evitar problemas si la imagen está vacía)
                if (!empty($producto['imagen'])) {
                    // Convertir la imagen binaria a base64
                    $producto['imagen'] = base64_encode($producto['imagen']);
                    // Formatear la imagen para ser usada en una etiqueta <img> de HTML
                    $producto['imagen'] = 'data:image/jpeg;base64,' . $producto['imagen'];
                }
            }
    
            // Devolvemos el array con los productos extraídos de la base de datos.
            return $productos;
    
        } catch (Exception $e) {
            // En caso de error, lo manejamos y devolvemos un mensaje
            return [];
        }
    }
    

    public static function actualizarProducto () {
        // Obtenemos la instancia de la conexión de la base de datos
        $conexion = ConexionBaseDeDatos::obtenerInstancia() -> obtenerConexion();
    }

    public static function borrarProducto () {
        // Obtenemos la instancia de la conexión de la base de datos
        $conexion = ConexionBaseDeDatos::obtenerInstancia() -> obtenerConexion();
    }
}