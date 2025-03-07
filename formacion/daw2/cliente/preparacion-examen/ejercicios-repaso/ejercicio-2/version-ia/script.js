/**
 * No utilizar variables del tipo var (var = numero), están obsoletas.
 */

/* Pedir un número entre 0-20 y al pulsar un botón se
* inicie la cuenta atrás, y si le das a otro botón este para la
* cuenta atrás y escribe en otro div que has parado el timer.
*/

// Obtenemos los elementos del DOM
const main = document.getElementById("main");
const botonPlay = document.getElementById("play");
const botonStop = document.getElementById("stop");

// Creamos los elementos para mostrar la cuenta atrás y el mensaje
const divCuentaAtras = document.createElement("div");
const divMensaje = document.createElement("div");

// Añadimos los elementos al DOM
main.appendChild(divCuentaAtras);
main.appendChild(divMensaje);

// Variable para el intervalo
let intervalo;
// Variable para almacenar el número inicial
let numero;

// Función para iniciar la cuenta atrás
function iniciarCuentaAtras() {
    // Pedimos un número entre 0 y 20
    numero = parseInt(prompt("Introduce un número entre 0 y 20"));
    
    // Mostramos el número inicial
    divCuentaAtras.textContent = numero;
    
    // Limpiamos el mensaje anterior
    divMensaje.textContent = "";
    
    // Iniciamos el intervalo
    intervalo = setInterval(function() {
        // Reducimos el número
        numero--;
        
        // Actualizamos el texto
        divCuentaAtras.textContent = numero;
        
        // Si llegamos a 0, detenemos el intervalo
        if (numero <= 0) {
            clearInterval(intervalo);
        }
    }, 1000);
}

// Función para detener la cuenta atrás
function detenerCuentaAtras() {
    // Detenemos el intervalo
    clearInterval(intervalo);
    
    // Mostramos el mensaje
    divMensaje.textContent = "Has parado el timer";
}

// Asignamos los eventos a los botones
botonPlay.addEventListener("click", iniciarCuentaAtras);
botonStop.addEventListener("click", detenerCuentaAtras);
