/**
 * No utilizar variables del tipo var (var = numero), están obsoletas.
 */

/*
* (un botón que te marque todas las palabras que de el usuario
* desmarcarlas o marcarlas 3 segundos) acaba de decir esto
* como ejemplo de algo que puede poner
*/

/**
 * Un botón que marque palabras y otro que las desmarque pasados 3s.
 */

// Obtenemos el elemento main y el párrafo
const main = document.getElementById("main");
const parrafo = document.getElementById("parrafo");

// Creamos los botones
const botonMarcar = document.createElement("button");
botonMarcar.textContent = "Marcar palabras";
const botonDesmarcar = document.createElement("button");
botonDesmarcar.textContent = "Desmarcar palabras";

// Añadimos los botones al main
main.insertBefore(botonMarcar, parrafo);
main.insertBefore(botonDesmarcar, parrafo);

// Variable para almacenar el temporizador
let temporizador;

// Función para marcar palabras
function marcarPalabras() {
    // Dividimos el texto en palabras
    const texto = parrafo.textContent;
    const palabras = texto.split(" ");
    
    // Creamos el nuevo contenido con palabras marcadas
    let nuevoContenido = "";
    for (let i = 0; i < palabras.length; i++) {
        nuevoContenido += `<span style="background-color: yellow">${palabras[i]}</span> `;
    }
    
    // Actualizamos el contenido del párrafo
    parrafo.innerHTML = nuevoContenido;
}

// Función para desmarcar palabras después de 3 segundos
function desmarcarPalabras() {
    // Limpiamos cualquier temporizador existente
    clearTimeout(temporizador);
    
    // Creamos un nuevo temporizador
    temporizador = setTimeout(function() {
        // Restauramos el texto original sin marcas
        parrafo.innerHTML = parrafo.textContent;
    }, 3000);
}

// Asignamos los eventos a los botones
botonMarcar.addEventListener("click", marcarPalabras);
botonDesmarcar.addEventListener("click", desmarcarPalabras);
