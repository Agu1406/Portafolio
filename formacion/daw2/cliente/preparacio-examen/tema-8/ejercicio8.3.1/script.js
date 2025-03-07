/**
 * No utilizar variables del tipo var (var = numero), están obsoletas.
 */

let parrafos = document.getElementsByTagName("p");

function imprimirContenido (parrafo) {
    console.log(parrafo.target.textContent);
}

for (let parrafo of parrafos) {
    parrafo.addEventListener("click", imprimirContenido);
}
