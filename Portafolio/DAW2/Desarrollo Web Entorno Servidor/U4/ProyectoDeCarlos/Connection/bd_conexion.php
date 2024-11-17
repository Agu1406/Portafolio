<?php
function dataBaseConnection(){

//Rutas relativas a los archivos __DIR__ devuelve la ruta absoluta
$xmlPath = __DIR__ . '/bd_conexion.xml';
$xsdPath = __DIR__ . '/bd_config.xsd';

// Validar existencia de archivos
if (!file_exists($xmlPath)) {
    die("No se encontró el archivo XML en la ruta: " . realpath($xmlPath));
}

if (!file_exists($xsdPath)) {
    die("No se encontró el archivo XSD en la ruta: " . realpath($xsdPath));
}

//Guardamos y cargamos el archivo xml
$xml = simplexml_load_file($xmlPath);

//Almacenamos la ruta del esquema XSD
$xsd = $xsdPath;

//Creamos el objeto DOM
$dom = new DOMDocument;
$dom->loadXML($xml->asXML());

//Validamos con el XSD
if($dom->schemaValidate($xsd)){

//Almacenamos los datos de conexión
$nombreBD = (string)$xml->nombreBD;
$host = (string)$xml->host;
$administrador = (string)$xml->administrador;
$pw = (string)$xml->pw;

try{
    //Conexion con la BD
    $cadena_conexion = "mysql:host=$host;dbname=$nombreBD";
    
    //Creamos objeto de conexión
    return new PDO($cadena_conexion, $administrador, $pw);

}catch(PDOException $e){
    echo "Conexión fallida " .$e->getMessage();
}
}else{
    echo "Archivo XML no válido";
    return false;
}
}