/**
 * No utilizar variables del tipo var (var = numero), están obsoletas.
 */

let capa = document.querySelector("div");

let parrafo = document.querySelectorAll("p")[2];

let boton = document.querySelector("button");


capa.addEventListener("click", () => { console.log("Soy el div") }, true);

parrafo.addEventListener("click", () => { console.log("Soy el parrafo") }, true);

boton.addEventListener("click", () => { console.log("Soy el botón") }, true);

// boton.addEventListener("click", () => { console.log("Soy el botón") }), false)