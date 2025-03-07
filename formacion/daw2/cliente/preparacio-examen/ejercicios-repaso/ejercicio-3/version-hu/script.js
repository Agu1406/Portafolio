/**
 * No utilizar variables del tipo var (var = numero), están obsoletas.
 */

/*
* (un botón que te marque todas las palabras que de el usuario
* desmarcarlas o marcarlas 3 segundos) acaba de decir esto
* como ejemplo de algo que puede poner
*/

// Creamos elementos inexistentes en el HTML desde JavaScript
let marcar = document.createElement("button");
// <button></button>
let desmarcar = document.createElement("button");
// <button></button>
marcar.innerHTML = "Marcar";
// <button>Marcar</button>
desmarcar.innerHTML = "Desmarcar";
// <button>Desmarcar</button>
let main = document.getElementById("main");
// <main id="main"></main>

// Inyectamos los elementos nuevos en el HTML usando JavaScript.
main.appendChild(marcar);
main.appendChild(desmarcar);
/*
<main id="main">
    <button>Marcar</button>
    <button>Desmarcar</button>
</main>
*/

// Pide a usuario una palabra usando un prompt.
let palabraBuscada = prompt("Dime una palabra");

// Guardamos el parrafo con loremp ipsum resctandolo desde el HTML con su ID.
let parrafo = document.getElementById("parrafo");

// Busca en toda la ventana (del navegador) 
window.find(palabraBuscada);

/*
String = ["agustin guido paula agustin alberto marta agustin"];
String = [""][" Guido Paula "][" Alberto Marta "][""];
*/
let cortar = parrafo.textContent.split(palabraBuscada);
// Crea un nuevo texto (que esta vacio)
let nuevoTexto = "";
// Si el parrafo original cortado tiene más de una posición lo ejecuta.
if (cortar.length > 1) {
    // Va una por una en todas las posiciones del String.
    for (let posicion in cortar) {
        // Si la posición es mayor a "0" le inyecta la palabra buscada.
        if (posicion > 0) {
            nuevoTexto += `<span>${palabraBuscada}</span>`;
        }
        // Sin importar que posición es, le suma el trozo original del parrafo.
        nuevoTexto += cortar[posicion];
    }
    // Luego le agrega este nuevo "texto" al parrafo remplazando su contenido original.
    parrafo.innerHTML = nuevoTexto;
}
