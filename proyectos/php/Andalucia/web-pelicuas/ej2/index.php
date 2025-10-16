<?php
    /*Usa la variable $este_script para los enlaces a este mismo script
    Ejemplo: <a href="<?=$este_script?>">...</a>
    */
    $este_script = $_SERVER['PHP_SELF'];

    /**
     * Utilizamos "require_once" enre las opciones dadas porque nos permite
     * agregar una sola vez el código necesario para cargar archivos CSV,
     * esta función ya existe y esta en el directorio "funciones".
     */
    require_once "../funciones/cargadatos.php";

    //ruta del archivo
    $rutaArchivoCSV="../datos.csv"; 

    /**
     * Llamo la función del código que acabo de incluir con el "require_once" y le digo
     * la ruta del archivo CSV que quiero que lea/cargue, esta función devuelve 3 datos
     * un número entero que indica la cantidad de filas correctas, otra que indica la
     * cantidad de filas incorrectas y los datos completos del archivo leido.
     */

    /*Uso el método list para desempaquetar el array en variables.*/
    list($filas_correctas, $filas_incorrectas, $datos) = cargarCSV($rutaArchivoCSV);

    /**
     * Crea un Array sin valores duplicados ni repetidos utilizando
     * "array_unique", este array solo tiene los "años" extraidos
     * del array de datos.
     */
    $añosNoRepetidos = array_unique(array_column($datos, "año"));
    sort($añosNoRepetidos);

    require_once "../funciones/filtradatos.php";

    /**
     * Verificamos que efectivamente exista en la URL un año en forma
     * de método GET (visible en la URL).
     */
    if(isset($_GET["añoFiltro"])) {
        // Si existe, extraigo ese año de la variable superglobal GET.
        $añoFiltro = $_GET["añoFiltro"];

        // Verifica si el año que realmente esta disponible en los años filtrables.
        if(in_array($añoFiltro, $añosNoRepetidos)) {

            // Si existe, entonces si filtramos las peliculas.
            filtraDatos($añoFiltro, $datos);
        }
    }

    /*Muestra provisionalmente el contenido del CSV
    echo "<pre>";
    var_dump($filas_correctas);
    var_dump($filas_incorrectas);
    var_dump($datos);
    echo "</pre>";
    */

    /**
    * CABECERAS
     */
?>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tarea 1 - Ejercicio 3</title>
</head>
<body>
<H1>Autor/a: RAFAEL MORONES BURGOS- Ejercicio 2 - Tarea 1 </H1>
<h3>
    Filas correctas: <?=$filas_correctas?> <br>
    Filas incorrectas: <?=$filas_incorrectas?>
</h3>

<a href="<?=$este_script?>"> Resetear </a> | 
<a href="../ej3/index.php"> Ir a formulario para insertar película </a> | 
<a href="../index.php"> Ir a la página principal </a><br><br>
<p>Haz click para filtrar por año:</p>
<?php foreach ($añosNoRepetidos as $año): ?>
    <a href="<?= $este_script. '?añoFiltro=' . urlencode($año) ?>">
        <?= htmlspecialchars($año) ?>
    </a> | 
<?php endforeach; ?>


<?php 
/**
 * Si hay peliculas disponibles para mostrar porque el archivo no estaba vacio se ejecuta
 * todo el código del if.
 */
if(!empty($datos)): ?>
    <table border="1" cellpadding="6" cellspacing="0">
        <thead>
            <tr>
                <?php 
                /**
                 * Creamos un bucle foreach que creará una "columna" por cada cabecera
                 * posible que puedan tener las películas.
                 */
                foreach (CABECERAS as $columna): 
                ?>
                    <th>
                        <?php 
                        /**
                         * Utilizamos la función nativa htmlspecialchars para que
                         * el texto literal no se malinterprete como si fuese código HTML.
                         */
                        ?>
                        <?=htmlspecialchars($columna, ENT_QUOTES, "UTF-8")?>
                    </th>
                <?php endforeach; ?>
            </tr>
        </thead>
        <tbody>
            <?php 
            /**
             * Creamos un bucle foreach que recorre una por una todas las peliculas leidas del
             * archivo CSV.
             */
            foreach ($datos as $fila): ?>
                <tr>
                    <?php 
                    /**
                     * Utilizamos la cabecera como "clave" para leer y mostrar la información de
                     * las peliculas una por una.
                     */
                    foreach (CABECERAS as $columna): ?>
                        <td>
                            <?=htmlspecialchars($fila[$columna] ?? "...", ENT_QUOTES, "UTF-8")?>
                        </td>
                    <?php endforeach; ?>
                </tr>
            <?php endforeach; ?>
        </tbody>
    </table>
<?php 
/**
 * Si no hay peliculas disponibles entonces no se muestra nada.
 */
else: ?>
    <p>No hay peliculas para mostrar.</p>
<?php endif; ?>

</body>
</html>