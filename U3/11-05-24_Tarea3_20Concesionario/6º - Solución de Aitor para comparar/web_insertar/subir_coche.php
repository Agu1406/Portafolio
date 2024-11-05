<?php

require_once "../config/Singleton_db_sesion.php";

define("RUTA_XML", "../config/xml/configuracion_db.xml");

try {
    if ($_SERVER["REQUEST_METHOD"] == "POST") {
        $conexion = Connection_db::get_conexion(RUTA_XML);
    
        $preparada = $conexion->prepare("   INSERT into t_coches(modelo, fabricante, foto, año)
                                            VALUES (:modelo, :fabricante, :foto, :anyo)");
        
        //añade los parámetros
        $preparada->bindParam(":modelo", $_POST["modelo"]);
        $preparada->bindParam(":fabricante", $_POST["fabricante"]);
        $preparada->bindParam(":anyo", $_POST["anyo"]);
        if (isset($_FILES["foto"]["tmp_name"])) {
            $foto_dato = file_get_contents($_FILES["foto"]["tmp_name"]); // Leer contenido binario
            $preparada->bindParam(":foto", $foto_dato, PDO::PARAM_LOB);
        } else {
            echo "[!] No se ha podido subir el archivo, se subirá NULL en su lugar<br>";
            $preparada->bindParam(":foto", "NULL", PDO::PARAM_LOB);
        }

        $preparada->execute();
        echo "[+] EXITOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO";
    }
} catch (PDOException $exc) {
    echo "[!] ERROR<br>";
    echo $exc->getMessage();
}
