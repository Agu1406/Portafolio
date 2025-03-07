/**
 * No utilizar variables del tipo var (var = numero), están obsoletas.
 */

// 4. Un botón que borre los párrafos pares y otro botón que devuelva los párrafos.

let parrafos = document.getElementsByTagName("p");


function ocultarParrafosPares() {

    mostrarTodosParrafos();
    /*
    Posiciones =    [0][1][2][3][4][5][6][7][8]
    Parrafo =       [1][2][3][4][5][6][7][8][9]
    */
    for (let parrafo = 1; parrafo < parrafos.length; parrafo += 2) {
        parrafos[parrafo].hidden = true;
    }
}

function mostrarTodosParrafos() {
    for (let parrafo of parrafos) {
        parrafo.hidden = false;
    }
}

function ocultarParrafosImpares() {
    // Hago todos los parrafos visibles.
    mostrarTodosParrafos();
    // Oculto todos los parrafos impares.
    for (let parrafo = 0; parrafo < parrafos.length; parrafo +=2) {
        parrafos[parrafo].hidden = true;
    }
}

let main = document.getElementById("main");
let mostrarTodos = document.getElementById("mostrarTodos");
let mostrarPares = document.getElementById("mostrarPares");
let mostrarImpares = document.getElementById("mostrarImpares");

mostrarTodos.addEventListener("click", mostrarTodosParrafos);
mostrarPares.addEventListener("click", ocultarParrafosImpares);
mostrarImpares.addEventListener("click", ocultarParrafosPares);

