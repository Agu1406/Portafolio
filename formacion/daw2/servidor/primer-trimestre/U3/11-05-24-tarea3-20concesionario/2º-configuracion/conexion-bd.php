<?php

/**
 * El primer paso es crear una variable cuyo valor sera la
 * ruta exacta (absoluta) del archivo XML que nuestro script
 * debe leer para cargar los datos de la conexión, ese
 * archivo está en la misma carpeta que este, por lo tanto:
 */

// $rutaDelXML = "D:/2DAW-AGUSTIN/PortafolioDAW/Portafolio/DAW2/Desarrollo Web Entorno Servidor/U3/11-05-24_Tarea3_20Concesionario/2º - Configuracion/datosDeConexionXML.xml";
$rutaDelXML = "C:/Users/AlumnoMañana.DESKTOP-KK9UC82/Desktop/Repositorios/PortafolioDAW/Portafolio/DAW2/Desarrollo Web Entorno Servidor/U3/11-05-24_Tarea3_20Concesionario/2º - Configuracion/datosDeConexionXML.xml";

/**
 * Si queremos crear nuestras propias excepciones personalizadas en el
 * script es tan facil como crear clases aquí vacias que se extienden
 * (heredan) de la clase nativa "Exception" de PHP, como podrás ver a
 * continuación:
 */

class ExceptionXML extends Exception {
    // Aquí no pongo nada, no hace falta, no nos complicamos la vida.
}

// Encerramos todo el Script dentro de un try-catch que según las excepciones que atrape lance un mensaje u otro.
try {
    // Con un if-else verificamos que existe el archivo, si no existe, lanzamos un mensaje de error.
    if(file_exists($rutaDelXML)) {
        // Si el archivo existe, utilizamos el método "simplexml_load_file" para cargar/leer el archivo.
        $configuracionBD = simplexml_load_file($rutaDelXML);
    } else {
        throw new ExceptionXML ("[1] ¡Error! El archivo XML no existe en la ruta indicada.", 1);
        echo "¡La ruta real es: " . realpath($rutaDelXML) . "!";

    }

    // Llamamos al método que lee el XML y guarda los datos de conexión en un Array.
    $arrayDatosDeConexion = leerDatosXML ($configuracionBD);

    // Llamamos al método que instancia PDO para conectar con la base de datos.
    $conexionBD = conectarBD ($arrayDatosDeConexion);    
    
/**
 * Debemos hacer tantos "catch" como excepciones hayamos hecho que ocurran de forma
 * posible en un Script, por ejemplo, la primera que atrapa es mi excepcion propia
 * que indica que el XML no existe en la ruta indicada.
 */
} catch (ExceptionXML $error) {
    // Imprime un mensaje de error personalizado indicando que el XML no existe en "X" ruta.
    echo "[2] ¡Error! El archivo XML " . $rutaDelXML . " no existe en la ruta indicada.";
} catch (PDOException $error) {
    // Imrpime un mensaje de error si la excepción es ocasionada por la conexión (PDO)
    echo "¡Error estableciento la conexión con la base de datos!";
}



// Separador innecesario que utilizo yo para diferencia la parte "main" de los métodos y funciones.


/**
 * Método del script que utilizando el array asociativo generado con el método
 * "leerDatosXML" instancia un nuevo objeto de la clase PDO generando así una
 * conexión con nuestra base de datos.
 */
function conectarBD($arrayDatosDeConexion) {
    try {
        $dsn = "{$arrayDatosDeConexion['tipo']}:{$arrayDatosDeConexion['nombre']};{$arrayDatosDeConexion['host']}";
        $usuario = $arrayDatosDeConexion['usuario'];
        $contrasena = $arrayDatosDeConexion['contrasena'];

        // Instancia PDO
        $conexion = new PDO($dsn, $usuario, $contrasena);
        
        $conexion->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

        return $conexion;

    } catch (PDOException $e) {
        echo "Error en la conexión: " . $e->getMessage();
        return null;
    }

}


/**
 * Método del script que lee un archivo XML, extrae del mismo uno a uno los datos
 * necesarios para hacer la conexión con la BD de datos y luego los devuelve
 * en forma de una estructura de datos (un Array)
 */
function leerDatosXML ($configuracionBD) {
    /**
     * Como no tengo dos monitores y me gusta dejar todo lo mejor explicado posible, os cuento
     * si el if-else ha sido exitoso, en "configuracionBD" tendremos cargado el XML completo
     * que es este (para no tener que abrirlo cada dos segundos), tenemos que extraer uno
     * por uno los datos del XML cargado para poder conectarnos con nuestra base de datos:
     * 
     * <Configuracion_BD xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
     * xsi:noNamespaceSchemaLocation="configuracion.xsd">
     * <Tipo>mysql</Tipo>
     * <Nombre>db_concesionario</Nombre>
     * <Host>127.0.0.1</Host>
     * <Usuario>root</Usuario>
     * <Contrasena><!-- Vacia, no tiene. --></Contrasena>
     * </Configuracion_BD>
     * 
     * Si miramos tareas anteriores, para conectarnos a una base datos tenemos que
     * darle a la clase PDO un String con el siguiente texto:
     * 
     * $datosDeConexion = "mysql:dbname=empresa;host=127.0.0.1:3309";
     * 
     * Y en nuestro XML tenemos todos los datos, pero nos faltan por ejemplo las
     * palabras "dbname=" o "host=", así que esos datos los agregamos al mismo
     * tiempo que los extraemos del XML, por lo tanto:
     */

    // Creamos un array asociativo donde guardamos todos los valores extraídos del XML
    $datosDeConexion = [
        'tipo' => $configuracionBD->xpath("//Tipo")[0]->__toString(),
        'nombre' => "dbname=" . $configuracionBD->xpath("//Nombre")[0]->__toString(),
        'host' => "host=" . $configuracionBD->xpath("//Host")[0]->__toString(),
        'usuario' => $configuracionBD->xpath("//Usuario")[0]->__toString(),
        'contrasena' => $configuracionBD->xpath("//Contrasena")[0]->__toString()
    ];

    return $datosDeConexion;
}