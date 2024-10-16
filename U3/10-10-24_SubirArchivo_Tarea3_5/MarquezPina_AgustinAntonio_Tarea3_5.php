<?php 

// Si el método de envío del formulario es "POST", ejecuta el "if"
if ($_SERVER['REQUEST_METHOD'] == "POST") {

    /**
     * Verificamos que se haya subido un archivo y que no haya errores.
     * - "isset()" verifica si existe la entrada "subirArchivo".
     * - "error === 0" indica que no hubo errores durante la subida.
     */
    if (isset($_FILES["subirArchivo"]) && $_FILES["subirArchivo"]["error"] === 0) {

        // Guardamos los datos del archivo subido en variables
        $archivo = $_FILES["subirArchivo"];
        $nombre = $archivo["name"];
        $tipo = $archivo["type"];
        $tamano = $archivo["size"];
        $rutaTemporal = $archivo["tmp_name"];

        // Definimos la carpeta donde se almacenarán los archivos
        $carpetaRuta = "uploads/";

        // Creamos la carpeta si no existe
        if (!is_dir($carpetaRuta)) {
            mkdir($carpetaRuta, 0755, true);
        }

        // Definimos la ruta de destino del archivo
        $rutaDestino = $carpetaRuta . $nombre;

        // Intentamos mover el archivo desde la ruta temporal al destino
        if (move_uploaded_file($rutaTemporal, $rutaDestino)) {
            echo "<p>¡Archivo subido con éxito!</p>";
            echo "<p>Nombre del archivo: $nombre</p>";
            echo "<p>Tamaño: " . round($tamano / 1024, 2) . " KB</p>";
            echo "<p>Tipo: $tipo</p>";
        } else {
            echo "<p>Error: No se pudo mover el archivo al destino.</p>";
        }

    } else {
        echo "<p>Error: No se ha subido ningún archivo o hubo un problema.</p>";
    }
}
?>
