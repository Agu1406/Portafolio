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

// SEGUNDA PARTE, CONTAR LOS ENLACES DEL TERCER PARRAFO

// Guardamos todos los parrafos en una variable let.
let numP=document.getElementsByTagName("p");

// Creamos una variable que guarda el parrafo 3 (posición 2)
let enlaces3P = numP[2].getElementsByTagName("a").length;

// creamos un nuevo parrafo para guardar la respuesta.
let nuevoParrafo2 = document.createElement("p");

// guardamos el total de enlaces del parrafo 3 (posición 2)
nuevoParrafo2.textContent = "Hay " + enlaces3P + " enlaces en el tercer parrafo.";

// Le sumamos al div el nuevo parrafo con la nueva respuesta.
divTotalEnlaces.appendChild(nuevoParrafo2);