<?php

include_once "conexion_bd.php";

if (isset($_POST['subirFabricante'])) {
    $nombreFabricante = $_POST['nombre_fabricante'];

    // Insertar el fabricante en la base de datos
    $sql = "INSERT INTO FABRICANTES (NOMBRE) VALUES (:nombre)";
    $stmt = $conexion->prepare($sql);
    $stmt->bindParam(':nombre', $nombreFabricante);
    $stmt->execute();

    echo "Fabricante subido con éxito.";
}

if (isset($_POST['subir_coche'])) {
    $modelo = $_POST['modelo'];
    $idFabricante = $_POST['id_fabricante'];
    $fotoCoche = subirFoto('foto');
    $anio = $_POST['anio'];

    if ($fotoCoche !== null) {
        // Insertar el coche en la base de datos
        $sql = "INSERT INTO COCHES (MODELO, ID_FABRICANTE, FOTO, AÑO) VALUES (:modelo, :id_fabricante, :foto, :anio)";
        $stmt = $conexion->prepare($sql);
        $stmt->bindParam(':modelo', $modelo);
        $stmt->bindParam(':id_fabricante', $idFabricante);
        $stmt->bindParam(':foto', $fotoCoche);
        $stmt->bindParam(':anio', $anio);
        $stmt->execute();

        echo "Coche subido con éxito.";
    } else {
        echo "Error al subir la foto del coche.";
    }
}


function subirFoto($campoArchivo) {
    if ($_FILES[$campoArchivo]['error'] == 0) {
        $foto = file_get_contents($_FILES[$campoArchivo]['tmp_name']);
        return $foto;
    } else {
        return null;
    }
}