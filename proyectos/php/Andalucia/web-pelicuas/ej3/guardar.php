<?php
require_once "../funciones/cargadatos.php";
require_once "../funciones/guardardatos.php";

/**
 * Creamos un Arraylist vacio que podra almacenar uno o varios
 * errores en caso de que ocurran.
 */
$errores = [];
$datosCorrectos= [];

// Verificamos que exista cualquier dato del array $_POST  con isset

/*Si titulo, dirección o argumento existen y no son vacias
devolvemos la misma cadena sin espacios en blanco
En caso contrario le añadimos una cadena vacia.*/
$titulo = isset($_POST["título"]) ? trim($_POST["título"]) : "";
$direccion = isset($_POST["dirección"]) ? trim($_POST["dirección"]) : "";
$argumento = isset($_POST["argumento"]) ? trim($_POST["argumento"]) : "";

//Si isset es false $genero es nulo
$genero = isset($_POST["género"]) ? $_POST["género"] : null;

/*Verificamos que año y duración existe. 
En caso contrario asignamos un valor vacío*/
$año = isset($_POST["año"]) ? $_POST["año"] : null;
$duracion = isset($_POST["duración"]) ? $_POST["duración"] : null;

/*Validamos que título, dirección o argumento no sean vacíos. */ 
if (empty($titulo)) { 
    $errores[] = "¡Error! El título no puede estar vacío.";
}else{
    $datosCorrectos["título"] = $titulo;
}

if (empty($direccion)) {
    $errores[] = "¡Error! La dirección no puede estar vacía.";
}else{
    $datosCorrectos["dirección"] = $direccion;
}

if (empty($argumento)) {
    $errores[] = "¡Error! El argumento no puede ser nulo ni vacío.";
}else{
    $datosCorrectos["argumento"] = $argumento;
}

/**
 * Si el genero es nulo o no es uno de los generos validos existentes
 * en cargadatos.php salta un error.
 */
if ($genero === null || !in_array($genero, GENEROS)) {
    $errores[] = "¡Error! El género no es válido.";
}else{
    $datosCorrectos["género"] = $genero;
}

// Obtenemos el año actual utilizando la función date("Y") y lo parseamos a int.
$añoActual = (int) date("Y");

/**
 * Verificamos que el año sea válido, no puede ser nulo, no puede ser previo a
 * 1960 ni posterior al año actual y tiene que ser un número entero
 */
if ($año === "" || $año === null) {
    $errores[] = "¡Error! El año no puede estar vacío.";
} elseif (!is_numeric($año)) {
    $errores[] = "¡Error! El año tiene que ser un número.";
} else {
    $año = (int)$año;
    if ($año < 1960 || $año > $añoActual) {
        $errores[] = "¡Error! El año debe estar entre 1960 y el año actual ($añoActual).";
    }else {
        $datosCorrectos["año"] = $año;
    }
}

/**
 * Verificamos que la duración sea válida, no puede ser nula ni fuera de rango.
 */
if ($duracion === "" || $duracion === null)  {
    $errores[] = "¡Error! El duración no puede estar vacío.";
} elseif (!is_numeric($duracion)) {
    $errores[] = "¡Error! La duración tiene que ser un número.";
} else {
    $duracion = (int)$duracion;
    if ($duracion < 1 || $duracion > 500) {
        $errores[] = "¡Error! La duración debe estar entre 0 500.";
    }else {
        $datosCorrectos["duración"] = $duracion;
    }
}

/*
* Comprobamos la duplicidad de los datos
* Si no hay errores cargamos el archivo datos.csv
*/
if(empty($errores)){

    $existeDuplicado = false;
    $rutaArchivoCSV = "../datos.csv";
    list($filas_correctas, $filas_incorrectas, $peliculasOriginales) = cargarCSV($rutaArchivoCSV);

        foreach ($peliculasOriginales as $pelicula) {
            /*
             * Verificamos con un if si ya existe una pelicula
             * con el mismo titulo, dirección y en año, en el
             * caso de que exista agregamos al Array de errores
             * el mensaje de error correspondiente. 
             */
            if ($pelicula['título'] === $titulo &&
            $pelicula['dirección'] === $direccion && 
            (int)$pelicula['año'] === $año) {
                // Si encuentra la pelicula, duplicado = true;
                $existeDuplicado = true;
            }
    }
    
    /**
     * Luego de recorrer todas las peliculas con el foreach anterior
     * si se encontro algún duplicado se agrega el mensaje de error
     * al Array de errores, si no, no pasa nada.
     */
    if ($existeDuplicado) {
        $errores[] = "¡Error! Ya existe una pelicula igual, intentelo de nuevo";
    } else {

        /**
         * Creamos un Array de clave valor (asociativo) solo
         * si hemos verificado que la pelicula no tiene 
         * errores y no existe previamente para poder
         * guardarla.
         */
        $nuevaPelicula = [[
            "título" => $titulo,
            "género" => $genero,
            "año" => $año,
            "dirección" => $direccion,
            "duración" => $duracion,
            "argumento" => $argumento
        ]];

        // $ejemplo = [[...datos una pelicula...]]
        
        /**
         * Llamamos a la función "guardarCSV" que esta creada en el archivo
         * "guardardatos.php", ella recibe dos argumentos, un Array con una
         * o varias peliculas y la ruta del archivo CSV donde tiene que
         * guardar las peliculas.
         */
        $nerrores = guardarCSV($rutaArchivoCSV, $nuevaPelicula);
        
        /**
         * La función devuelve un número entero con la cantidad de errores
         * encontrados durante el proceso de guardar la pelicula, si devuelve
         * "0" todo ha ido, si devuelve algo diferente a "0" entonces algo ha
         * salido mal.
         */
        if ($nerrores > 0) {
            $errores[] = "¡Error! No se pudo guardar la pelicula, intentelo de nuevo.";
        }

    }
}


// Conteo del total de errores ocurridos.
$nerrores = count($errores);

if (!empty($errores)) {
    require "index.php";
} else {
?>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Resultado guardar película</title>
</head>
<body> 
    <h1>Autor/a: RAFAEL MORONES BURGOS - Ejercicio 3 - Tarea 1</h1>

    
    <?php 
    // Si no hay errores mostramos el mensaje de "pelicula guardada".
    if (empty($errores)): ?>
        <h2>La película se ha guardado con <?=$nerrores?> errores</h2>
    <?php endif; ?>

    <a href="index.php">Volver al formulario</a> | 
    <a href="../ej2/index.php">Ir al listado de películas</a> | 
    <a href="../index.php">Ir a la página principal</a>

</body>
</html>
<?php
}
