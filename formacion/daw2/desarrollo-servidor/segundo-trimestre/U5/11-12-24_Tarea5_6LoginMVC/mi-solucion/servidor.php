<?php
/**
 * Aquí en el PHP (continuación del JavaScript) sabemos que hemos recibido
 * desde el Js un JSON con los datos del login y que han sido enviados
 * utlizando el método POST, para ello usamos el "file_get_contents"
 */
$datosJson = file_get_contents("php://input");

/**
 * Para trabajar mejor cogemos el JSON y lo convertirmos en un array
 * asociativo, para ellos usamos el "json_decode" y como segundo
 * atributo "true" indicando que conviertas los datos en un array
 * asociativo, si fuese "false" no sería un array asociativo.
 */
$datos = json_decode($datosJson, true);

// Sabiendo que es un array asociativo, extraemos el usuario y la contraseña:
$usuario = $datos["usuario"];
$contrasena = $datos["contrasena"];

/**
 * Como es ficiticio, con tal y que ambos datos estén llenos y
 * no vacios damos por valido el login, si falta alguno damos
 * un "false".
 */
if ($usuario && $contrasena) {
    echo "¡Login exitoso! El usuario es " . $usuario;
} else {
    echo "¡Error! Datos incompletos, intentalo de nuevo";
}