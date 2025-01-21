/**
 * No usar la variable "var", está en deshuso y no es profesional, usar
 * let o const en su lugar.
 */

/**
 * A partir de la página proporcionada y utilizando las
 * funciones DOM, muestra por pantalla la siguiente información:
 *
 * A) Número de enlaces de la página
 * B) Número de enlaces del tercer párrafo
 *
 * RESPUESTA A) Existen dos formas de hacerlo, las cuales son:
 *
 * document.getElementsByTagName("a").length;
 * document.document.querySelectorAll("a").length;
 *
 */

// Creo dos variables para usar ambos métodos para contar enlaces.
let totalEnlaces1 = document.getElementsByTagName("a").length;
let totalEnlaces2 = document.querySelectorAll("a").length;

// Imprimo ambos resultados usando el console log.
console.log("Total enlaces N.º1: " + totalEnlaces1);
console.log("Total enlaces N.º2: " + totalEnlaces2);

// Guardo en una variable el div donde cargo el total de enlaces del sitio web.
let divTotalEnlaces = document.getElementById("totalEnlaces");

// Creamos un nuevo parrafo donde guardaremos el contenido del parrafo.
let nuevoParrafo = document.createElement("p");

// Al parrafo que acabamos de crear le tenemos que dar contenido.
nuevoParrafo.textContent = "Hay " + totalEnlaces1 + " enlaces en total.";

// Por ultimo, le añadimos ese parrafo al contenedor para que se cargue.
divTotalEnlaces.appendChild(nuevoParrafo);

