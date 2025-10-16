<?php

require_once "../funciones/cargadatos.php";

?>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Insertar nueva película</title>
</head>
<body>
    <H1>Autor/a: RAFAEL MORONES BURGOS - Ejercicio 3 - Tarea 1 </H1>
    <h1>Formulario para insertar nueva película </h1>
<a href="../ej2/index.php">Ir a listado de películas</a> | <a href="../index.php">Ir a la página principal</a><br><br>
<?php
// Si existen errores se imprimen todos en forma de lista.
if (!empty($errores)): ?>
    <ul>
        <?php foreach ($errores as $error): ?> 
            <li><?=htmlspecialchars($error)?></li>
        <?php endforeach; ?>    
    </ul>
<?php endif; ?>



<!-- La acción (action) del formulario tiene que llamar al archivo PHP que guarda la pelicula -->
<form action="guardar.php" method="post">

    <label>Título:</label>
    <input type="text" name="título" value="<?= htmlentities($datosCorrectos['título'] ?? '') ?>"><br>

    <label>Género:</label>
    <select name="género">
        <option value="">-- Selecciona un género --</option>
        <option value="<?=bin2hex(random_bytes(5))?>">Valor al azar</option>
        <!-- Utilizamos un bucle foreach para mostrar todas las opciones posibles. -->
        <?php foreach (GENEROS as $genero):?>
            <?php
                /*
                 * Verificamos si existe en el Array de datos correctos el género, si existe
                 * entonces el formulario mostrara ese genero "pre-seleccionado" en el
                 * formulario. 
                 * */ 
                $selected = (isset($datosCorrectos["género"]) && $datosCorrectos["género"] === $genero) ? "selected" : "";
            ?>
            <option value="<?=htmlentities($genero)?>" <?=$selected?>><?=htmlentities($genero)?></option>
        <?php endforeach; ?>

    </select>
    
    <br>

    <label>Año:</label>
    <select name="año">
      <option value="">-- Selecciona un año --</option>
      <option value="<?=bin2hex(random_bytes(5))?>">Valor al azar</option>
        <!-- Utilizamos un bucle for para mostrar todos los años desde 1960 hasta actual. -->
        <?php for($contador = 1960; $contador <= (int) date("Y"); $contador++): ?>
            <?php
                /*
                 * Verificamos si existe en el Array de datos correctos el año, si existe
                 * entonces el formulario mostrara ese genero "pre-seleccionado" en el
                 * formulario. 
                 * */ 
                $selected = (isset($datosCorrectos["año"]) && (int)$datosCorrectos["año"] === $contador) ? "selected" : "";
            ?>     
            <option value="<?=$contador?>" <?=$selected?>><?=$contador?></option>
        <?php endfor;?> 

    </select><br>

    <label>Dirección:</label>
    <input type="text" name="dirección" value="<?= htmlentities($datosCorrectos['dirección'] ?? '') ?>"><br>

    <label>Duración:</label>
    <input type="text" name="duración" value="<?= htmlentities($datosCorrectos['duración'] ?? '') ?>"><br>

    <label>Argumento:</label><br>
    <textarea name="argumento"><?= htmlentities($datosCorrectos['argumento'] ?? '') ?></textarea><br>

    <button type="submit" >Guardar</button>
</form>

</body>
</html>