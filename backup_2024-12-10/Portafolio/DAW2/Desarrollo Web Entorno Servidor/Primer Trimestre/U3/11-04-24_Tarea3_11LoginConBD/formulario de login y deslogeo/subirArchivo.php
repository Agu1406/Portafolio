<?php
// Directorio donde se subirán los archivos
$directorioDeSubida = "D:\\2DAW-AGUSTIN\\PortafolioAcademicoPHP\\ArchivosSubidos\\";

// Verificamos que existe el array global $_FILES y tenga contenido, en caso positivo, se ejecuta el if
if (isset($_FILES["subirArchivo"])) {
    // El nombre del fichero sera el mismo con el que haya sido subido desde el formulario con el ID "subirArchivo".
    $nombreDelFichero = basename($_FILES["subirArchivo"]["name"]);
    // la ruta final sera el directorio + el nombre del archivo.
    $rutaFinal = $directorioDeSubida . $nombreDelFichero;

    // Asegurarse de que el directorio existe
    if (!is_dir($directorioDeSubida)) {
        // Si no existe, creamos el directorio.
        mkdir($directorioDeSubida, 0777, true);
    }

    // Mover el archivo subido a la ruta final
    if (move_uploaded_file($_FILES["subirArchivo"]["tmp_name"], $rutaFinal)) {
        echo "El archivo ha sido subido exitosamente.";
    } else {
        echo "Hubo un error al subir el archivo.";
    }
} else {
    echo "No se ha enviado ningún archivo.";
}

// Redirigir al formulario de subida
header("Location: formularioSubirArchivo.php");

// Detener ejecución después de la redirección
exit(); 