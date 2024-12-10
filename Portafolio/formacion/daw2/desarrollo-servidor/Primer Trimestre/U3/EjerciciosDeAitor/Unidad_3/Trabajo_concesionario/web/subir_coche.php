<?php

require_once "../config/Singleton_db_sesion.php";

define("RUTA_XML", "../config/xml/configuracion_db.xml");

try {
    if ($_SERVER["REQUEST_METHOD"] == "POST") {
        $conexion = Connection_db::get_conexion(RUTA_XML);
    
        $preparada = $conexion->prepare("   INSERT into t_coches(modelo, fabricante, foto, año)
                                            VALUES (:modelo, :fabricante, :foto, :anyo)");
        $preparada->bindParam(":modelo", $_POST["modelo"]);
        $preparada->bindParam(":fabricante", $_POST["fabricante"]);
        $preparada->bindParam(":foto", $_POST["foto"]);
        $preparada->bindParam(":anyo", $_POST["anyo"]);
    
        $preparada->execute();
        echo "EXITOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO";
    }
} catch (PDOException $exc) {
    echo "[!] ERROR<br>";
    echo $exc->getMessage();
}
