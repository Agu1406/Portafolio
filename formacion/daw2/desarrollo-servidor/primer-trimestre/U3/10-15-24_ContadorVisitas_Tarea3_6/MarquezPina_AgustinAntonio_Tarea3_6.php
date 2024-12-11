<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<?php
/**
 * Primero, entendamos como funciona el método / función "setcookie" que se utiliza para
 * definir, crear y modificar "cookies", el método se escribe de la siguiente forma y
 * contiene los siguientes atributos:
 * 
 * setcookie(
 * string $name, // El nombre de la cookie. 
 * string $value, // El valor, un "string" o un número, por ejemplo.
 * int $expire = 0, // La expiración de la cookie (en segundos) 
 * string $path = "", // La ruta del sitio web donde es valida, por ejemplo si el valor es "/" es valida en todo el sitio web.
 * string $domain = "", // El dominio donde es valida la cookie, ejemplo "www.google.com"
 * boolean $secure = false, // Si la cookie solo es valida en HTTPS (true) o también en HTTP (false)
 * boolean $httponly = false // Si la cookie puede ser accedida o usada desde JavaScript (true) o no (false)
 * );
 */

$nombreDeLaCookie = "contadorDeVisitas"; // Creamos una variable para darle un nombre a nuestra cookie.
$contador = 0; // De momento en "0" porque aún nadie visita el sitio web.
$caducidad = time() + 3600 * 24; // time() significa "fecha y hora actual" y le sumamos 3600 segundos  (una hora en segundos) por "24", por o que son "24" horas a partir del momento en el que se crea, eso dura nuestra cookie.
$rutaValida = "/"; // De esta forma es valida para todo nuestro siio web.
$dominioValido = ""; // Vacio, para que sea valido todo el "localhost" del XAMP.
$seguridadHTTP = false; // False, así vale para ambos, HTTPS y HTTP.
$accesibleDesdeJS = false; // True, así no puede ser vista ni usada desde JavaScript.

// Verificamos con un "ifsset" si la Cookie ya existe previamente, si existia, incrementamos su contador, si no, la creamos.
if(isset($_COOKIE[$nombreDeLaCookie])) { 
    
    // Si la cookie ya existe, incrementamos el "contador de visitas" en uno.
    $contador = $_COOKIE[$nombreDeLaCookie] + 1;

} else {

    // Si no exite la variable "contador" vale "1" porque es la primera visita.
    $contador = 1;

}

// Establecemos todos los valores de la "cookie" utilizando el método "setcookie"
setcookie(
    $nombreDeLaCookie, // Lo creamos al principio, esta cookie se llama "contadorDeVisitas"
    $contador, // Dependiendo del if, si es la primera vez que se crea, "1", si no el valor anterior de la cookie + 1
    $caducidad, // Anteriomente dijimos que sería de 24 horas.
    $rutaValida, // Dijimos "/" para que valga para todo el "sitio web".
    $dominioValido, // En blanco, es decir "" para que sirva en LocalHost de XAMP
    $seguridadHTTP, // Lo deje en false por si, por XAMP, no va el HTTPS, valga en HTTP también.
    $accesibleDesdeJS // Lo deje en false porque es buena practica de seguridad inhabilitar su uso en JavaScript.
 );

// Mensaje que imprimi el HTML dependiendo de si es la primera visita o no.
if ($contador === 1) {
    $mensaje = "¡Bienvenido! Esta es tu primera visita.";
} else {
    $mensaje = "Has visitado esta página un total de $contador veces.";
}

/**
 * Si queremos borrar una cookie es tan facil como utilizar el propio "setcookie" y en la caducidad
 * por ejemplo, poner un tiempo que ya paso, antes dijimos "time () + 3600 * 24", es decir, 24 horas
 * desde el momento actual y exacto de crearla, pero si en lugar usamos "time () - 3600 * 24" es
 * 24 horas antes del tiempo actual, es decir, hace "24" horas que caduco, eso la destruye.
 * 
 * setcookie (
 * $nombreDeLaCookie, // Lo creamos al principio, esta cookie se llama "contadorDeVisitas"
 * $contador, // Dependiendo del if, si es la primera vez que se crea, "1", si no el valor anterior de la cookie + 1
 * $caducidad = time() - 3600 * 24
 * );
 * 
 * listo, tan facil como eso.
 */
?>
<body>
    <h1>Contador de Visitas con Cookies en PHP</h1>
    <br>
    <p><?php echo $mensaje; ?></p>
</body>
</html>