<?php
/**
 * Configuración principal de la aplicación
 * 
 * Este archivo contiene todas las configuraciones globales necesarias
 * para el funcionamiento de la aplicación, incluyendo la carga
 * de la configuración de la base de datos desde XML.
 */

// Función para cargar y validar la configuración de la base de datos desde XML
function cargarConfiguracionBD() {
    $rutaXML = __DIR__ . '/conexion_bd.xml';
    $rutaXSD = __DIR__ . '/validacion_bd.xsd';
    
    // Verificar que los archivos existen
    if (!file_exists($rutaXML) || !file_exists($rutaXSD)) {
        throw new Exception('Archivos de configuración de base de datos no encontrados');
    }
    
    // Cargar el archivo XML
    $xml = new DOMDocument();
    $xml->load($rutaXML);
    
    // Validar contra el esquema XSD
    if (!$xml->schemaValidate($rutaXSD)) {
        throw new Exception('El archivo de configuración de la base de datos no es válido');
    }
    
    // Convertir XML a array asociativo
    $configXML = simplexml_load_file($rutaXML);
    $configBD = [
        'tipo' => (string)$configXML->Tipo,
        'servidor' => (string)$configXML->Servidor,
        'puerto' => (int)$configXML->Puerto,
        'nombre' => (string)$configXML->Nombre,
        'usuario' => (string)$configXML->Usuario,
        'clave' => (string)$configXML->Clave,
        'charset' => (string)$configXML->Charset,
        'activada' => (bool)$configXML->Activada
    ];
    
    return $configBD;
}

// Intentar cargar la configuración de la base de datos
try {
    $configBD = cargarConfiguracionBD();
} catch (Exception $e) {
    // En caso de error, usar configuración por defecto
    $configBD = [
        'tipo' => 'mysql',
        'servidor' => 'localhost',
        'puerto' => 3306,
        'nombre' => 'naturalshop',
        'usuario' => 'root',
        'clave' => '',
        'charset' => 'utf8mb4',
        'activada' => false
    ];
    
    // En un entorno de producción, podríamos registrar el error
    // error_log('Error al cargar configuración de BD: ' . $e->getMessage());
}

// Configuración completa de la aplicación
return [
    // Información de la aplicación
    'nombreApp' => 'NaturalShop',
    'version' => '1.0.0',
    'descripcion' => 'Tu tienda de parafarmacia, cosméticos y productos naturales',
    
    // Rutas de la aplicación
    'rutaBase' => '/examen-final',
    'rutaPublica' => '/examen-final/publico',
    
    // Configuración de base de datos (cargada desde XML)
    'baseDatos' => $configBD,
    
    // Configuración de sesión
    'sesion' => [
        'duracion' => 7200, // 2 horas
        'ruta' => '/',
        'dominio' => '',
        'segura' => false,
        'soloHttp' => true
    ],
    
    // Configuración de correo (para futuras funcionalidades)
    'correo' => [
        'servidor' => 'smtp.ejemplo.com',
        'puerto' => 587,
        'usuario' => 'info@naturalshop.com',
        'clave' => 'password',
        'seguridad' => 'tls',
        'desde' => 'info@naturalshop.com',
        'nombreDesde' => 'NaturalShop'
    ]
]; 