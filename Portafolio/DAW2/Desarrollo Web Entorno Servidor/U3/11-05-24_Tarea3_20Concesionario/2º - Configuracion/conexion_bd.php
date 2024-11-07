<?php

/**
 * El primer paso es crear una variable cuyo valor sera la
 * ruta exacta (absoluta) del archivo XML que nuestro script
 * debe leer para cargar los datos de la conexión, ese
 * archivo está en la misma carpeta que este, por lo tanto:
 */
$rutaDelXML = "datosDeConexion.xml";

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
        throw new ExceptionXML ("¡Error! El archivo XML no existe en la ruta indicada.", 1);
    }

    // Llamamos al método que lee el XML y guarda los datos de conexión en un Array.
    $arrayDatosDeConexion = leerDatosXML ($configuracionBD);
    
/**
 * Debemos hacer tantos "catch" como excepciones hayamos hecho que ocurran de forma
 * posible en un Script, por ejemplo, la primera que atrapa es mi excepcion propia
 * que indica que el XML no existe en la ruta indicada.
 */
} catch (ExceptionXML $error) {
    // Imprime un mensaje de error personalizado indicando que el XML no existe en "X" ruta.
    echo "¡Error! El archivo XML " . $rutaDelXML . " no existe en la ruta indicada.";
}

/**
 * Dejo el ejerciio hasta aquí, el siguiente paso es crear otra función llamada
 * "conectarBD" el cual, usando el array con los datos de conexión, instancia
 * y se conecte a la BD utilizando la clase PDO
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

    // Obtenemos primero el tipo de base de datos ("mysql")
    $tipoBD = $configuracionBD -> xpath("//Tipo")[0] -> __toString();

    // Ahora, para el nombre primero debemos agregar la palabra "dbname=", por lo tanto
    $nombreBD = "dbname=" . $configuracionBD -> xpath("//Nombre")[0] -> __toString();

    // Para el host, igual, al principio debe ir la palabra "host=", por lo tanto:
    $hostBD = "host=" . $configuracionBD -> xpath("//Host")[0] -> __toString();

    // El siguiente es el usuario (administrador) de la base de datos
    $usuarioBD = $configuracionBD -> xpath("//Usuario")[0] -> __toString();

    // El siguiente es la contraseña de dicho usuario, en mi caso, vacia.
    $contrasenaBD = $configuracionBD -> xpath("//Contrasena")[0] -> __toString();

    // Creamos un array donde guardamos todos los valores extraidos del XML
    $datosDeConexion = [
        $tipo = $tipoBD,
        $nombre = $nombreBD,
        $host = $hostBD,
        $usuario = $usuarioBD,
        $contrasena = $contrasenaBD
    ];

    return $datosDeConexion;
}