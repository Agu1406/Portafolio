<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Web para mostrar la base de datos</title>
    <link rel="stylesheet" href="../../estilos/estilos.css">
</head>
<!-- Como necesitamos los datos ANTES de cargar el HTML, nuestro script debe estár antes que
 el resto del código HTML. -->
<?php 
/**
 * Este comando se utiliza para que nuestro script importe funciones
 * y métodos de otro archivo PHP, en nuestro caso, "conexion_bd.php"
 * es el script con el que leemos XML e instanciamos PDO.
 */
include_once "../2º - Configuracion/conexion_bd.php";

// Verificar si la conexión fue exitosa
if ($conexionBD) {
    // Nada
} else {
    echo "No se pudo establecer la conexión a la base de datos.";
    exit;  // Salir del script si la conexión falla (no ejecuta nada más)
}

// Escribimos el "código" SQL que utilizaremos como consulta para la base de datos
$sqlCoches = "SELECT c.MODELO, c.AÑO, c.FOTO, f.NOMBRE as fabricante FROM COCHES c INNER JOIN FABRICANTES f ON c.ID_FABRICANTE = f.ID";

// Preparamos la consulta en una variable llamada "consultaCoches".
$consultaCoches = $conexionBD->prepare($sqlCoches);

// Ejecutamos la consulta utilizando el método "execute()" en la base de datos.
$consultaCoches->execute();

// Metemos en "coches" todos los coches obtenidos en "consultaCoches" en forma de un array asociativo.
$coches = $consultaCoches->fetchAll(PDO::FETCH_ASSOC);

?>
<body>
    <div class="contenedor-principal">
        <h1>¡Web para mostrar los datos!</h1>
        <br>
        <div class="mostrar-productos">
            <?php
            // Si coches tiene resultados, se ejecuta el if
            if ($coches) {
                // Iterar sobre los resultados de la consulta y mostrarlos en el HTML
                foreach ($coches as $posicion) {
                    echo '<div class="mostrar-productos__producto">';
                    
                    // Mostrar el modelo y año del coche
                    echo '<h3>' . htmlspecialchars($posicion["MODELO"]) . ' (' . htmlspecialchars($posicion["AÑO"]) . ')</h3>';
                    
                    // Mostrar el fabricante
                    echo '<p>Fabricante: ' . htmlspecialchars($posicion["fabricante"]) . '</p>';
                    
                    // Si hay una foto, mostrarla (ten en cuenta que FOTO está en formato binario)
                    if ($posicion["FOTO"]) {
                        // Convertir la imagen binaria a base64 para mostrarla
                        $fotoCoche = base64_encode($posicion["FOTO"]);
                        echo '<img src="data:image/jpeg;base64,' . $fotoCoche . '" alt="Imagen del coche" />';
                    } else {
                        echo '<p>No hay foto disponible.</p>';
                    }

                    echo '</div>';
                }
            } else {
                echo "No se encontraron coches.";
            }
            ?>
        </div>
    </div>
</body>
</html>