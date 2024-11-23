<?php
// Iniciar la sesión
session_start(); 

// Verificar si el usuario no ha iniciado sesión
if (!isset($_SESSION['usuario'])) {
    // Si no ha iniciado sesión, redirigir al login
    header("Location: formulario_login.php");
    exit(); // Asegurarse de salir del script
}

// Importamos ambos, la conexión-instancia a la base de datos y el CRUD de productos.
include_once __DIR__ . "/../modelos/conexion_bd.php";
include_once __DIR__ . "/../modelos/crud_carrito.php";

try {
    // Obtenemos el código del cliente desde la sesión
    $codigoCliente = $_SESSION['codigo_cliente'];

    // Obtenemos el código del carrito del cliente
    $conexion = ConexionBaseDeDatos::obtenerInstancia()-> obtenerConexion();

    // Preramos la consulta que seleccionara el carrito asociado al cliente que actualmente haya iniciado sesión.
    $stmt = $conexion->prepare("SELECT codigo_carrito FROM Carrito WHERE cliente_codigo_cliente = :codigo_cliente");
    
    // Con bindParam asignamos al valor de la consulta el código del cliente en la sesión.
    $stmt->bindParam(':codigo_cliente', $codigoCliente, PDO::PARAM_INT);
    
    // Ejecutamos la consulta.
    $stmt->execute();

    // Creamos el carrito en una variable con un array asociativo con todos los valores del carrito.
    $carrito = $stmt->fetch(PDO::FETCH_ASSOC);

    // En el hipotetico caso de que el usuario no tenga el carrito existente, hace un "die" y da un error.
    if (!$carrito) {
        die("Error: No se encontró un carrito asociado al usuario.");
    }

    // Extramos del array asociativo el codigo del carrito.
    $codigoCarrito = $carrito['codigo_carrito'];

    // Además agregamos a la sesión el codigo de carrito del usuario
    $_SESSION['codigo_carrito'] = $carrito['codigo_carrito'];

    // Procesar la acción solicitada desde las vistas "catalogo" o "carrito" con los botones.
    if ($_SERVER['REQUEST_METHOD'] === 'POST' && isset($_POST['accion'])) {
        $accion = $_POST['accion'];

        // Dependiendo de la acción que sea llamada al controlado ejecuta un caso u otro.
        switch ($accion) {
            // Permite agregar productos al carrito y returna un mensaje de exito o de error.
            case 'agregar':
                $codigoProducto = $_POST['codigo_producto'];
                $cantidad = $_POST['cantidad'];

                $mensaje = carritoCRUD::agregarProductoCarrito($codigoCarrito, $codigoProducto, $cantidad);
                
                echo $mensaje;

                header("Location: ../vistas/carrito.php");
                
                break;

            // Permite agregar productos al carrito y returna un mensaje de exito o de error.
            case 'leer':
                // Obtener los productos del carrito
                $productos = carritoCRUD::leerProductoCarrito($codigoCarrito);
            
                // Verificar si la respuesta es un array de productos
                if (is_array($productos)) {
                    // Incluir la vista y pasar los productos como parte de los datos
                    include_once __DIR__ . '/../vistas/carrito.php'; // Incluimos la vista
                } else {
                    // En caso de error, mostrar un mensaje de error
                    echo $productos; // Mensaje de error en caso de que no se encuentren productos
                }
                break;

            // Permite agregar productos al carrito y returna un mensaje de exito o de error.
            case 'actualizar':

                $codigoProducto = $_POST['codigo_producto'];

                $nuevaCantidad = $_POST['cantidad_producto'];

                $mensaje = carritoCRUD::actualizarProductoCarrito($codigoCarrito, $codigoProducto, $nuevaCantidad);
                echo $mensaje;
                break;
            
            // Permite agregar productos al carrito y returna un mensaje de exito o de error.
            case 'borrar':

                $codigoProducto = $_POST['codigo_producto'];

                $mensaje = carritoCRUD::borrarProductoCarrito($codigoCarrito, $codigoProducto);
                echo $mensaje;
                break;

            // Default si la acción de llamada no es valida o no existe.
            default:
                echo "Error: Acción no válida.";
        }
    } else {
        echo "Error: Método o acción no válida.";
    }
} catch (Exception $e) {
    echo "Error: Ocurrió un problema: " . $e->getMessage();
}