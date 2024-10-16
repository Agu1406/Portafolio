<?php
// Ruta del archivo a leer
$archivo = "datos.txt";

// 1. Leer el archivo de golpe (todos sus bits en una operación de lectura) con readfile()
echo "1. Leer todo el archivo de golpe:\n";
readfile($archivo);
echo "\n\n";

// 2. Leer el fichero línea a línea usando fopen(), fgets() y feof()
echo "2. Leer el fichero línea a línea:\n";
$archivoAbierto = fopen($archivo, "r") or die("No se puede abrir el archivo.");
while (!feof($archivoAbierto)) {
    $linea = fgets($archivoAbierto);
    echo $linea;
}
fclose($archivoAbierto);
echo "\n\n";

// 3. Leer el fichero carácter a carácter usando fopen() y fgetc()
echo "3. Leer el fichero carácter a carácter:\n";
$archivoAbierto = fopen($archivo, "r") or die("No se puede abrir el archivo.");
while (!feof($archivoAbierto)) {
    $caracter = fgetc($archivoAbierto);
    echo $caracter;
}
fclose($archivoAbierto);
echo "\n\n";

// 4. Leer el fichero campo a campo separado por “;” usando fopen() y fgets()
echo "4. Leer el fichero campo a campo separado por ';':\n";
$archivoAbierto = fopen($archivo, "r") or die("No se puede abrir el archivo.");
while (!feof($archivoAbierto)) {
    $linea = fgets($archivoAbierto);
    // Separamos los campos de cada línea usando el delimitador ";"
    $campos = explode(";", $linea);
    print_r($campos);  // Mostrar cada campo por separado
}
fclose($archivoAbierto);
?>
