<?php
// Incluir la clase Correo
include_once __DIR__ . "/../modelos/correo.php";

// Cargar los datos de conexión desde el XML
$rutaDelXML = __DIR__ . "/../configuracion/datos_conexion_correo.xml";

// Cargar los datos de validación del XML desde el XSD  
$rutaDelXSD = __DIR__ . "/../configuracion/datos_validacion_correo.xsd";

// Verifica si el archivo de validación ("XSD") existe, si no, lanza una excepción.
if (!file_exists($rutaDelXSD)) {
    // Excepcion que se lanza si el XSD no existe o la ruta es incorrecta.
    throw new Exception("Error: El archivo XSD no existe.");

} else 
// Si el XSD existe, procede a verificar si también existe el XML o no.
if (!file_exists($rutaDelXML)) {
    // Excepción que se lanza si el "if" detecta que el XML no existe.
    throw new Exception("Error: El archivo XML no existe.");
} else {

    // Creamos una nueva variable DOMDocument para cargar el XML
    $configuracionCorreo = new DOMDocument();

    // Cargamos el XML en esa variable.
    $configuracionCorreo->load($rutaDelXML);

    // Validamos los datos del XML utilizando DOMDocument y el XSD
    if (!$configuracionCorreo->schemaValidate($rutaDelXSD)) {
        // Si el XML no se puede validar con el XSD lanzo una excepcion.
        throw new Exception("El archivo XML no es valido segun el esquema XSD.");
    }

    // Extraer la configuración del correo en forma de un array asociativo.
    $configCorreo = [
        'host' => $configuracionCorreo->getElementsByTagName('host')->item(0)->nodeValue,
        'smtpAuth' => $configuracionCorreo->getElementsByTagName('smtpAuth')->item(0)->nodeValue === 'true',
        'username' => $configuracionCorreo->getElementsByTagName('username')->item(0)->nodeValue,
        'password' => $configuracionCorreo->getElementsByTagName('password')->item(0)->nodeValue,
        'smtpSecure' => $configuracionCorreo->getElementsByTagName('smtpSecure')->item(0)->nodeValue,
        'port' => (int)$configuracionCorreo->getElementsByTagName('port')->item(0)->nodeValue
    ];
}
?>
