<?php

$conf = simplexml_load_file("../xml/configuracion_db.xml");

$tipo = $conf->xpath("//DB_type");

foreach($tipo as $valor) {
    echo $valor;
}