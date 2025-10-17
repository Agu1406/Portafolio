<?php

/**
 * HTTP es un protocolo sin estado por lo que las
 * peticiones de un cliente al servidor son
 * independientes; es decir, no tienen ninguna relación
 * entre sí. Entonces, para poderlas asociar se usan las
 * sesiones.
 * 
 * • Cuando se inicia una sesión, el servidor le envía al
 * usuario un identificador de sesión. Y en cada petición
 * siguiente, el usuario envía al servidor ese mismo
 * identificador. Así el servidor se sabe que se trata del
 * mismo usuario.
 * 
 * • Para controlar la sesión el servidor deja una cookie
 * con el identificador de sesión en el cliente. Esta
 * cookie se elimina cuando se cierra la sesión.
 * 
 * • Si borramos manualmente las cookies del navegador
 * se cierran las sesiones abiertas. Se puede configurar
 * el servidor para controlar las sesiones sin cookies
 * pero no es lo habitual.
 * 
 * Para crear una sesión se usa la función session_start()
 * 
 * • Si no hay una sesión activa, la crea.
 * 
 * • Cuando ya existe la sesión, el script que llama a esta 
 * función session_start() se une a ella.
 */

 // Iniciamos una sesión cada vez que se ejecute este archivo ".php"
 session_start();

// Si la variable "contador" no existe dentro de la sesión la creamos con un valor igual a "0"
if (!isset($_SESSION['contador'])){
    $_SESSION['contador']=0;
} 
// En cambio, si la variable ya existe, se incrementa en "1" su estado anterior guardado.
else {
    $_SESSION['contador']++;
}

echo "¡Hola! Has iniciado sesión aquí un total de " . $_SESSION['contador'] . " veces.";

echo "<br><a href='sesiones_uso_basico2.php'>Siguiente archivo PHP</a>";