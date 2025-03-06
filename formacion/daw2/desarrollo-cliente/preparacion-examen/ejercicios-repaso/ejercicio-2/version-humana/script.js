/**
 * No utilizar variables del tipo var (var = numero), están obsoletas.
 */

/* Pedir un número entre 0-20 y al pulsar un botón se
* inicie la cuenta atrás, y si le das a otro botón este para la
* cuenta atrás y escribe en otro div que has parado el timer.
*/

// Rescatamos elementos ya existente en el HTML a través de su ID.
let main = document.getElementById("main");
let play = document.getElementById("play");
let stop = document.getElementById("stop");
// Creamos elementos inexistentes en el HTML desde JavaScript
let cuentaAtras = document.createElement("div");
let mensajeStop = document.createElement("div");
// Inyectamos los elementos nuevos en el HTML usando JavaScript.
main.appendChild(cuentaAtras);
main.appendChild(mensajeStop);

// Creamos el intervalo inicialmente vacio.
let intervalo;

// Solicitu un número por pantalla usando prompt (lo parseo a int).
let contador = parseInt(prompt("Ingresa un número del 1 al 20"));

// Función ejecutada en intervalos de 1 segundo que cuenta atrás.
function actualizarCuentaAtras() {
    // Va "pintando" cada segundo el número del conteo actual.
    cuentaAtras.innerHTML = `<p>Quedan ${contador} segundos.</p>`;
    // Le resta "1" al contador para actualizar su valor.
    contador--;
    // Si llegamos al segundo "0" paramos el intervalo.
    if (contador < 0) {
        // Limpiamos el intervalo.
        clearInterval(intervalo);
    }
}

// Si hacemos click en el botón de play ejecuta la función en intervalos de 1 segundo.
play.addEventListener("click", () => {
    intervalo = setInterval(actualizarCuentaAtras, 1000);
    mensajeStop.innerHTML = "";
});

// Si hacemos click en el botón de stop pone el mensaje de "stop" en su contenedor y para el intervalo.
stop.addEventListener("click", () => {
    mensajeStop.innerHTML = "¡Has parado la cuenta regresiva";
    clearInterval(intervalo);
});
