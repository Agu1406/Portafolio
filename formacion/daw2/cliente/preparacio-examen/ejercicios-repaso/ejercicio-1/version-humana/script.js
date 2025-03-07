/**
 * No utilizar variables del tipo var (var = numero), están obsoletas.
 */

// 1. Un botón que inicie un timer de 5s y que al pulsar otro botón pare el timer.

// Guardamos todos los botones existentes en el HTML en la variable.
let botones = document.getElementsByTagName("button");

// Creamos la variable donde guardar el intervalo.
let intervalo;

// Función que crea/destruye el intervalo dependiendo del botón que presiona.
function controlIntervalo (boton) {
    // Si es "true" inicia el timer, si es "false" lo para.
    if (boton) {
        // Creamos un intervalo de 5 segundos que ejecuta una función "flecha".
        intervalo = setInterval(() => console.log("Hola"), 1000);
    } else {
        // Destruimos el intervalo.
        clearInterval(intervalo);
    }
}

// Si presionamos el botón de play (botón 0) envia "true" a la función "intervalo".
botones[0].addEventListener("click", () => controlIntervalo(true));
// Si presionamos el botón de play (botón 1) envia "false" a la función "intervalo".
botones[1].addEventListener("click", () => controlIntervalo(false));