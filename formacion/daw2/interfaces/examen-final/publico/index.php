<?php

/**
 * Punto de entrada principal de la aplicación
 * 
 * Este archivo actúa como controlador frontal (front controller) y maneja
 * todas las solicitudes entrantes, dirigiéndolas al controlador adecuado.
 */

// Iniciar sesión para manejar datos de usuario y carrito
session_start();

/**
 * Establece las rutas base para toda la aplicación, cuando se ejecuta en
 * un servidor local este usa las rutas relativas para acceder a los archivos   
 * de la aplicación.
 */
define('RUTA_BASE', dirname(__DIR__));
define('RUTA_APLICACION', RUTA_BASE . '/aplicacion');
define('RUTA_PUBLICO', RUTA_BASE . '/publico');

// Incluir archivos de configuración
$configuracion = require_once RUTA_APLICACION . '/configuracion/configuracion.php';

// Inicializar la conexión a la base de datos si está activada
if ($configuracion['baseDatos']['activada']) {
    require_once RUTA_APLICACION . '/modelos/ConexionBD.php';
    require_once RUTA_APLICACION . '/modelos/BaseDatos.php';
    
    ConexionBD::setConfiguracion($configuracion['baseDatos']);
    
    try {
        // Crear instancia de BaseDatos
        $bd = new BaseDatos();
        
        // Verificar si la base de datos está configurada
        if (!$bd->verificarBaseDatos()) {
            // Ejecutar el script SQL para crear la estructura
            if (!$bd->ejecutarScriptSQL()) {
                // Si falla, mostrar mensaje de error
                echo 'Error: No se pudo configurar la base de datos. Por favor, contacte al administrador.';
                exit;
            }
        }
        
        // Guardar la instancia de BaseDatos en una variable global para usarla en los controladores
        $GLOBALS['bd'] = $bd;
    } catch (Exception $e) {
        // En producción, registrar el error y mostrar un mensaje genérico
        // error_log('Error de conexión a la base de datos: ' . $e->getMessage());
        echo 'Error: No se pudo conectar a la base de datos. Por favor, contacte al administrador.';
        exit;
    }
}

/**
 * Carga un controlador dinámicamente, verificando si existe el archivo
 * y requiriendo su contenido.
 * 
 * @param string $nombreControlador Nombre del controlador a cargar
 * @return bool True si el controlador se cargó correctamente, false en caso contrario
 */
function cargarControlador($nombreControlador)
{
    $archivoControlador = RUTA_APLICACION . '/controladores/' . $nombreControlador . '.php';
    if (file_exists($archivoControlador)) {
        require_once $archivoControlador;
        return true;
    }
    return false;
}

/**
 * Obtiene la URI solicitada y procesa la ruta base para obtener la ruta relativa
 * de la aplicación, ejemplo: http://localhost/publico/productos, la ruta base es   
 * http://localhost/publico y la ruta relativa es /productos.
 */
$peticion = $_SERVER['REQUEST_URI'];
$rutaBase = $configuracion['rutaBase'];

/**
 * La función de esta variable es eliminar la ruta base de la URI para obtener
 * ejemplo: http://localhost/publico/productos, la ruta base es http://localhost/publico
 * y la ruta relativa es /productos, entonces la variable $uri contendrá /productos
 * ya que habra eliminado http://localhost/publico de la URI.
 */
$uri = str_replace($rutaBase, '', $peticion);

/**
 * Este if se ejecuta si la URI contiene parámetros GET, por ejemplo:   
 * http://localhost/publico/productos?id=1, la variable $uri contendrá /productos
 * y el parámetro GET será ?id=1, entonces se elimina el parámetro GET de la URI
 * para que la ruta sea /productos.
 */
if (($posicion = strpos($uri, '?')) !== false) {
    $uri = substr($uri, 0, $posicion);
}

/**
 * Sistema de switch case para enrutar las solicitudes a los controladores
 * si por ejemplo la ruta es /productos, se enrutara a la ruta productos    
 * de la aplicación, así controlamos que controlador y método se debe ejecutar  
 * según la ruta ingresada por el usuario.
 */
switch ($uri) {
    // Se ejecuta si la ruta es / o /index.php
    case '/':
    case '':
        // Carga el controlador de inicio
        cargarControlador('ControladorInicio');
        // Crea una instancia del controlador de inicio
        $controlador = new ControladorInicio();
        // Ejecuta el método index del controlador de inicio
        $controlador->index();
        break;

    // Se ejecuta si la ruta es /productos
    case '/productos':
        // Carga el controlador de productos
        cargarControlador('ControladorProducto');
        // Crea una instancia del controlador de productos
        $controlador = new ControladorProducto();
        // Ejecuta el método listar del controlador de productos
        $controlador->listar();
        break;

    // Detalle de un producto específico    
    case '/producto/detalle':
        // Carga el controlador de productos
        cargarControlador('ControladorProducto');
        // Crea una instancia del controlador de productos
        $controlador = new ControladorProducto();
        // Ejecuta el método detalle del controlador de productos
        $controlador->detalle($_GET['id'] ?? null);
        break;

    // Listado de categorías    
    case '/categorias':
        // Carga el controlador de categorías
        cargarControlador('ControladorCategoria');
        // Crea una instancia del controlador de categorías
        $controlador = new ControladorCategoria();
        // Ejecuta el método listar del controlador de categorías
        $controlador->listar();
        break;

    // Productos de una categoría específica    
    case '/categoria/productos':
        // Carga el controlador de categorías
        cargarControlador('ControladorCategoria');
        // Crea una instancia del controlador de categorías
        $controlador = new ControladorCategoria();
        // Ejecuta el método mostrarProductos del controlador de categorías
        $controlador->mostrarProductos($_GET['id'] ?? null);
        break;

    // Carrito de compras    
    case '/carrito':
        // Carga el controlador de carrito
        cargarControlador('ControladorCarrito');
        // Crea una instancia del controlador de carrito
        $controlador = new ControladorCarrito();
        // Ejecuta el método mostrar del controlador de carrito
        $controlador->mostrar();
        break;

    // Agregar producto al carrito    
    case '/carrito/agregar':
        // Carga el controlador de carrito
        cargarControlador('ControladorCarrito');
        // Crea una instancia del controlador de carrito
        $controlador = new ControladorCarrito();
        // Ejecuta el método agregar del controlador de carrito
        $controlador->agregar($_POST['idProducto'] ?? null, $_POST['cantidad'] ?? 1);
        break;

    // Actualizar cantidad de un producto en el carrito    
    case '/carrito/actualizar':
        // Carga el controlador de carrito
        cargarControlador('ControladorCarrito');
        // Crea una instancia del controlador de carrito
        $controlador = new ControladorCarrito();
        // Ejecuta el método actualizar del controlador de carrito
        $controlador->actualizar($_POST['idProducto'] ?? null, $_POST['cantidad'] ?? 1);
        break;

    // Eliminar producto del carrito    
    case '/carrito/eliminar':
        // Carga el controlador de carrito
        cargarControlador('ControladorCarrito');
        // Crea una instancia del controlador de carrito
        $controlador = new ControladorCarrito();
        // Ejecuta el método eliminar del controlador de carrito
        $controlador->eliminar($_GET['idProducto'] ?? null);
        break;

    // Vaciar carrito    
    case '/carrito/vaciar':
        // Carga el controlador de carrito
        cargarControlador('ControladorCarrito');
        // Crea una instancia del controlador de carrito
        $controlador = new ControladorCarrito();
        // Ejecuta el método vaciar del controlador de carrito
        $controlador->vaciar();
        break;

    // Formulario de login    
    case '/login':
        // Carga el controlador de usuario
        cargarControlador('ControladorUsuario');
        // Crea una instancia del controlador de usuario
        $controlador = new ControladorUsuario();
        // Ejecuta el método mostrarLogin del controlador de usuario
        $controlador->mostrarLogin();
        break;

    // Procesar login    
    case '/login/auth':
        // Carga el controlador de usuario
        cargarControlador('ControladorUsuario');
        // Crea una instancia del controlador de usuario
        $controlador = new ControladorUsuario();
        // Ejecuta el método login del controlador de usuario
        $controlador->login();
        break;

    // Cerrar sesión    
    case '/logout':
        // Carga el controlador de usuario
        cargarControlador('ControladorUsuario');
        // Crea una instancia del controlador de usuario
        $controlador = new ControladorUsuario();
        // Ejecuta el método logout del controlador de usuario
        $controlador->logout();
        break;

    // Formulario de registro    
    case '/registro':
        // Carga el controlador de usuario
        cargarControlador('ControladorUsuario');
        // Crea una instancia del controlador de usuario
        $controlador = new ControladorUsuario();
        // Ejecuta el método mostrarRegistro del controlador de usuario
        $controlador->mostrarRegistro();
        break;

    // Procesar registro    
    case '/registro/crear':
        // Carga el controlador de usuario
        cargarControlador('ControladorUsuario');
        // Crea una instancia del controlador de usuario
        $controlador = new ControladorUsuario();
        // Ejecuta el método registrar del controlador de usuario
        $controlador->registrar();
        break;
    // Se ejecuta si la ruta no existe y se muestra la página de error 404    
    default:
        // Muestra un mensaje de error 404  
        header('HTTP/1.0 404 Not Found');
        // Incluye la vista de error 404
        include RUTA_APLICACION . '/vistas/404.php';
        break;
}
