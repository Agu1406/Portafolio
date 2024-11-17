<?php 
class ConexionBaseDeDatos
{
    private static $instance = null;
    private $connection;

    // Constructor privado para evitar que se pueda instanciar fuera de la clase
    private function __construct()
    {
        // Cargar los datos de conexión desde el XML
        $rutaDelXML = __DIR__ . "/../configuracion/datos_de_conexion.xml";

        // Cargar los datos de validación del XML desde el XSD
        $rutaDelXSD = __DIR__ . "/../configuracion/datos_de_validacion.xsd";

        // Verifica si el archivo de validación ("XSD") existe, si no, lanza una excepción.
        if (!file_exists($rutaDelXSD)) {
            // Excepcion que se lanza si el XSD no eixste o la ruta es incorrecta.
            throw new Exception("Error: El archivo XSD no existe.");

        } else 
        // Si el XSD existe, procede a verificar si también existe el XML o no.
        if (!file_exists($rutaDelXML)) {
            // Excepción que se lanza si el "if" detecta que el XML no existe.
            throw new Exception("Error: El archivo XML no existe.");
        } else {

            // Creamos una nueva variable DOMDocument para cargar el XML
            $configuracionBD = new DOMDocument();

            // Cargamos el XML en esa variable.
            $configuracionBD -> load($rutaDelXML);

            // Validamos los datos del XML utilizando DOMDocument y el XSD
            if (!$configuracionBD -> schemaValidate($rutaDelXSD)) {
                // Si el XML no se puede validar con el XSD lanzo una excepcion.
                throw new Exception("El archivo XML no es valido segun el esquema XSD.");
            }
            
            // Utilizamos la función "leerDatosXML" que extrae los datos del XML en forma de array.
            $datosDeConexion = $this->leerDatosXML($configuracionBD);

            // Utilizamos la función "conectarBD" que utiliza los datos del array.
            $this->connection = $this->conectarBD($datosDeConexion);

        }
    }

    // Método para conectar a la base de datos
    private function conectarBD($arrayDatosDeConexion)
    {
        try {
            $dsn = "{$arrayDatosDeConexion['tipo']}:{$arrayDatosDeConexion['nombre']};{$arrayDatosDeConexion['host']}";
            $usuario = $arrayDatosDeConexion['usuario'];
            $contrasena = $arrayDatosDeConexion['contrasena'];

            // Instanciamos PDO
            $conexion = new PDO($dsn, $usuario, $contrasena);
            
            // Le decimos a la conexión que en caso de errores los lance como excepciones.
            $conexion->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

            // Returna la conexión exitosa a la base de datos.
            return $conexion;

        } catch (PDOException $e) {
            // Excepción que se lanza si ocurre algún error en la conexión a la base de datos.
            throw new Exception("Error en la conexión: " . $e->getMessage());
        }
    }

    // Método para leer los datos del archivo XML
    private function leerDatosXML($configuracionBD)
    {
        // Creamos un array asociativo del cual extraemos uno por uno los valores del XML cargado.
        $datosDeConexion = [
            'tipo' => $configuracionBD->xpath("//Tipo")[0]->__toString(),
            'nombre' => "dbname=" . $configuracionBD->xpath("//Nombre")[0]->__toString(),
            'host' => "host=" . $configuracionBD->xpath("//Host")[0]->__toString(),
            'usuario' => $configuracionBD->xpath("//Usuario")[0]->__toString(),
            'contrasena' => $configuracionBD->xpath("//Contrasena")[0]->__toString()
        ];

        // Returnamos ese mismo Array una vez creado.
        return $datosDeConexion;
    }


    // Método para obtener la instancia única de la clase
    public static function obtenerInstancia()
    {
        if (self::$instance === null) {
            self::$instance = new self();
        }
        return self::$instance;
    }

    // Prevenimos la clonación de la clase
    private function __clone() {}
    private function __wakeup() {}

    // Método para obtener la conexión
    public function obtenerConexion()
    {
        return $this->connection;
    }
}