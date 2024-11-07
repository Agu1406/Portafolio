<?php
$directorio_subida = "archivos_subidos/"; //ruta del directorio al que almacenar
$nombre_fichero = basename($_FILES['archivo']['name']); //nombre del fichero al subirse
$nombre_final = $directorio_subida . $nombre_fichero; //ruta del fichero una vez subido

if (move_uploaded_file($_FILES["archivo"]["tmp_name"], $nombre_final)) {  //mueve el archivo si devuelve true
    header("Location: nube.php");//manda de vuelta a la página de subida
} else {
    $error = true;
    include 'nube.php';
}
?>