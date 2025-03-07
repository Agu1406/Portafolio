/**
 * No utilizar variables del tipo var (var = numero), están obsoletas.
 */

let parrafo = document.querySelectorAll("p")[2];
/**
 * Esta función se ejecuta una sola vez y se
 * autodestruye eliminando el eventListener.
 */
function mensaje () {
    alert("¡Me haz hecho click!");

    parrafo.removeEventListener("click", mensaje);
}

parrafo.addEventListener("click", mensaje);