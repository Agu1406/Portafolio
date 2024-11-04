<?php

require_once "../config/Singleton_db_sesion.php";

define("RUTA_XML", "../config/xml/configuracion_db.xml");
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    try {
        $db = Connection_db::get_conexion(RUTA_XML);

        $preparada = $db->prepare(" INSERT into t_fabricante(nombre_fabricante)
                                    values(:fabricante)");

        $preparada->bindParam(":fabricante", $_POST["nombre-fabricante"]);
        $preparada->execute();

        echo"[+] EXITOOOOOO";
    } catch (PDOException $exc) {
        echo "[!] Ha ocurrido un error<br>";
        $exc->getMessage();
    }
}