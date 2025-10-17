<?php
/**
 * Clase para manejar la conexión a la base de datos
 * 
 * Esta clase utiliza la configuración cargada desde XML para
 * establecer una conexión a la base de datos MySQL usando PDO.
 */
class ConexionBD {
    /**
     * Instancia única de la conexión (patrón Singleton)
     * @var PDO
     */
    private static $instancia = null;
    
    /**
     * Configuración de la base de datos
     * @var array
     */
    private static $configuracion = null;
    
    /**
     * Constructor privado para evitar la creación de instancias directamente
     */
    private function __construct() {
        // Constructor privado para implementar el patrón Singleton
    }
    
    /**
     * Establece la configuración de la base de datos
     * 
     * @param array $config Configuración de la base de datos
     */
    public static function setConfiguracion($config) {
        self::$configuracion = $config;
    }
    
    /**
     * Obtiene una instancia de la conexión a la base de datos
     * 
     * @return PDO Instancia de PDO para la conexión a la base de datos
     * @throws Exception Si la base de datos no está activada o hay un error de conexión
     */
    public static function obtenerInstancia() {
        // Si no se ha establecido la configuración, lanzar excepción
        if (self::$configuracion === null) {
            throw new Exception('La configuración de la base de datos no ha sido establecida');
        }
        
        // Si la base de datos no está activada, lanzar excepción
        if (!self::$configuracion['activada']) {
            throw new Exception('La base de datos no está activada en la configuración');
        }
        
        // Si no existe una instancia, crearla
        if (self::$instancia === null) {
            try {
                // Construir el DSN según el tipo de base de datos
                $dsn = self::$configuracion['tipo'] . ':host=' . self::$configuracion['servidor'] . 
                       ';port=' . self::$configuracion['puerto'] . 
                       ';dbname=' . self::$configuracion['nombre'] . 
                       ';charset=' . self::$configuracion['charset'];
                
                // Opciones de PDO para manejar errores y preparar sentencias
                $opciones = [
                    PDO::ATTR_ERRMODE => PDO::ERRMODE_EXCEPTION,
                    PDO::ATTR_DEFAULT_FETCH_MODE => PDO::FETCH_ASSOC,
                    PDO::ATTR_EMULATE_PREPARES => false
                ];
                
                // Crear la instancia de PDO
                self::$instancia = new PDO(
                    $dsn, 
                    self::$configuracion['usuario'], 
                    self::$configuracion['clave'], 
                    $opciones
                );
            } catch (PDOException $e) {
                // Capturar y relanzar la excepción con un mensaje más amigable
                throw new Exception('Error al conectar con la base de datos: ' . $e->getMessage());
            }
        }
        
        return self::$instancia;
    }
    
    /**
     * Cierra la conexión a la base de datos
     */
    public static function cerrarConexion() {
        self::$instancia = null;
    }
} 