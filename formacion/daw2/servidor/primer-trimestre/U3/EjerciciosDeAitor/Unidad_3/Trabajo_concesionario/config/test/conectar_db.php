<?php

require_once "../Singleton_db_sesion.php";

define("RUTA_XML", "../xml/configuracion_db.xml"); //completar

$db = Connection_db::get_conexion(RUTA_XML);

$reults = $db->query("SELECT * from t_fabricante");

foreach ($reults as $campo) {
    echo "<p>[+] -> " . $campo[0] . "</p>";
    echo "<p>[+] -> " . $campo[1] . "</p>";
}