<?php
$db_name = 'mysql:dbname=concessionaire;host=127.0.0.1';
$db_user = 'root';
$db_pw = '';

try {
    $db = new PDO($db_name, $db_user, $db_pw);
    $db->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

    // Creamos un nuevo objeto DOMDocument para trabajar con XML
    $dom = new DOMDocument();
    // Cargamos el archivo XML que queremos validar y desde el archivo
    $dom->load('concessionaire.xml');

    // Validamos el XML con el esquema XSD
    if (!$dom->schemaValidate('concessionaire.xsd')) {
        echo "El XML no es válido según el esquema XSD.<br>";
        exit; // Salimos del script si la validación falla
    } else {
        echo "El XML es válido según el esquema XSD.<br>";

        // Cargamos el XML en un objeto SimpleXMLElement para manipularlo
        $xml = simplexml_load_file('concessionaire.xml');

        // Usamos XPath para obtener todos los fabricantes del XML
        $manufacturers = $xml->xpath('//manufacturers/manufacturer');
        // Recorremos cada fabricante encontrado
        foreach ($manufacturers as $manufacturer) {
            // Obtenemos el ID y el nombre del fabricante, convirtiéndolos a los tipos adecuados
            // Convertimos el ID a un entero
            $manufacturerId = (int)$manufacturer->ID; 
            // Convertimos el nombre a una cadena
            $manufacturerName = (string)$manufacturer->Name; 

            // Preparamos la consulta SQL para insertar el fabricante en la base de datos
            // Usamos ON DUPLICATE KEY UPDATE para actualizar el nombre si ya existe el ID
            $sql = "INSERT INTO manufacturer (ID, Name) VALUES (:id, :name) ON DUPLICATE KEY UPDATE Name = :name";
            // Preparamos la sentencia SQL para ejecutar
            $stmt = $db->prepare($sql); 
            // Vinculamos los parámetros a la consulta
            // Asignamos el ID del fabricante
            $stmt->bindParam(':id', $manufacturerId); 
            // Asignamos el nombre del fabricante
            $stmt->bindParam(':name', $manufacturerName); 

            // Ejecutamos la consulta de inserción
            if ($stmt->execute()) {
                echo "Fabricante con ID $manufacturerId insertado exitosamente.<br>";
            } else {
                echo "Error al insertar el fabricante con ID $manufacturerId.<br>";
            }
        }

        // Usamos XPath nuevamente para obtener todos los coches del XML
        $cars = $xml->xpath('//cars/car');
        
        // Recorremos cada coche encontrado en el XML
        foreach ($cars as $car) {
            // Obtenemos los valores de cada coche, asegurándonos de convertirlos al tipo correcto
            // ID del coche, convertido a entero
            $id = (int)$car->ID;
            // Modelo del coche, convertido a cadena
            $model = (string)$car->Model;
            // ID del fabricante, convertido a entero
            $manufacturer = (int)$car->Manufacturer;
            // La imagen del coche (se asume que es base64)
            $image = (string)$car->Image;
            // Año del coche, convertido a cadena
            $year = (string)$car->Year;

            // Preparamos la consulta para insertar el coche en la base de datos
            $sql = "INSERT INTO cars (ID, Model, Manufacturer, Image, Year) VALUES (:id, :model, :manufacturer, :image, :year)";
            // Preparamos la sentencia SQL para ejecutar
            $stmt = $db->prepare($sql); 
            // Vinculamos los parámetros a la consulta
            // Asignamos el ID del coche
            $stmt->bindParam(':id', $id);
            // Asignamos el modelo del coche
            $stmt->bindParam(':model', $model);
            // Asignamos el ID del fabricante
            $stmt->bindParam(':manufacturer', $manufacturer);
            // Asignamos la imagen
            $stmt->bindParam(':image', $image); 
            // Asignamos el año
            $stmt->bindParam(':year', $year); 

            // Ejecutamos la consulta de inserción
            if ($stmt->execute()) {
                echo "Coche con ID $id insertado exitosamente.<br>";
            } else {
                echo "Error al insertar el coche con ID $id.<br>";
            }
        }

    }
} catch (PDOException $e) {
    echo "Error en la conexión: " . $e->getMessage();
}
?>
