/**
 * No utilizar variables del tipo var (var = numero), están obsoletas.
 */

let parrafos = document.getElementsByTagName("p");

for (let parrafo of parrafos) {
    parrafo.addEventListener("click", () => {alert(parrafo.textContent)});
}

let nuevoParrafo = document.createElement("p");

nuevoParrafo.textContent = "Soy el párrafo 5";

let main = document.getElementById("main");

main.appendChild(nuevoParrafo);