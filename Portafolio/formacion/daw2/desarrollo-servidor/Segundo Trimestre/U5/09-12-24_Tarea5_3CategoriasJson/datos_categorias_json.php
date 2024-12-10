<?php
// Categorías default de Mercedes.
$categoria1 = array("codigo" => 1, "nombre" => "Comida");
$categoria2 = array("codigo" => 2, "nombre" => "Bebida");

// Categorías creadas por mi.
$categoria3 = array("codigo" => 3, "nombre" => "Snacks");
$categoria4 = array("codigo" => 4, "nombre" => "Cosmeticos");
$categoria5 = array("codigo" => 5, "nombre" => "Parafarmacia");
$categoria6 = array("codigo" => 6, "nombre" => "Herramientas");
$categoria7 = array("codigo" => 7, "nombre" => "Electronicos");

// Metemos todos los arrays en otro array, creando así un array multidimensional.
$array = array($categoria1, $categoria2, $categoria3, $categoria4, $categoria5, $categoria6, $categoria7);

// Guardamos los datos de ese array en forma de JSON usando encode
$datos = json_encode($array);

// Imprimimos esos datos con echo a ver como lucen.
echo($datos);