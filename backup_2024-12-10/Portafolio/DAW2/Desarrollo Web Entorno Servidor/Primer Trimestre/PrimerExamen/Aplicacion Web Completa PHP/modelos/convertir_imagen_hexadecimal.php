<?php 
/**
 * Este PHP no se usa en toda la aplicación web, es un PHP que he diseñado especificamente
 * para convertir imagienes a binarios y convertir esos binarios en hexadecimales para
 * facilitarme introducir producto de demostración manualmente en el ".sql" en la base
 * de datos, así que no te asustes si ves que este script no se usa en ningún lugar del
 * diseño MVC.
 */
?>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Agregar Producto</title>
    <link rel="stylesheet" href="../archivos/estilos.css">
</head>
<body>
    <?php include_once "../vistas/header.php"; ?>
    <main>
    <div class="contenedor-principal">
        <h1>Bienvenido a tumercado.com</h1>
        <div class="seccion">
            <div class="izquierda">
                <h1>¡Añade un nuevo producto a tu tienda!</h1>
                <p>&emsp; Rellena los datos del producto para incluirlo en nuestro catálogo de productos.</p>
            </div>
            <div class="derecha">
                <!-- La "action" es nula porque se ejecuta el mismo PHP en el que está creado el formulario -->
                <form class="login-form" action="" method="post" enctype="multipart/form-data">

                    <label for="imagen">Imagen del Producto</label>
                    <input type="file" name="imagen" id="imagen" accept="image/*" required>
                    <br>
                    
                    <input type="submit" value="Añadir Producto">
                </form>
            </div>
        </div>
    </div>
    </main>
    <?php include_once "../vistas/footer.php"; ?>
    <?php

    // Lo primero, verificar que el método de envio sea POST.
    if ($_SERVER["REQUEST_METHOD"] === "POST") {
        // Verificamos si el archivo fue cargado correctamente y su código de error es "0" (significa todo bien)
        if (isset($_FILES["imagen"]) && $_FILES["imagen"]["error"] == 0) {
            // Superado el if, guardamos en una variable la ruta temporal de la imagén que está en "$_FILES".
            $rutaImagen = $_FILES["imagen"]["tmp_name"];

            // Ahora que ya tenemos la ruta de la imagén, procedemos a convertirla en binario.
            $imagenEnBinario = file_get_contents($rutaImagen);

            // Convertimos ese binario en hexadecimal, que se puede leer en los ".sql" con la letra "X" al inicio.
            $imagenEnHexadecimal = bin2hex($imagenEnBinario);

            //Imprimimos por pantalla el código hexadecimal de la imagén convertida.
            echo "<h2 style='text-align: center'>Resultado de convertir la imagén</h2>";
            echo "<br><br>";
            echo "<textarea style='width: 100%; height: 200px;' readonly>$imagenEnHexadecimal</textarea>";

        } else {
            // En caso de que la imagén no exista en "$_FILES" o su código de error sea diferente a "0".
            echo "Hubo un error al subir la imagen, por favor, intentalo de nuevo.";
        }
    } 
    ?>
</body>
</html>