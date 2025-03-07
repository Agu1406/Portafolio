/**
 * No utilizar variables del tipo var (var = numero), están obsoletas.
 */

// Constructor para crear pitos.
function Pitos(grosor, largo, color, peludo) {

    this.grosor = grosor;
    this.largo = largo;
    this.color = color;
    this.peludo = peludo;

    this.mear = function () {
        console.log("mear");
    }

    this.correrse = function () {
        console.log("correrse");
    }
}
// Contrusctor para crear personas.
function Persona(nombre) {
    this.nombre = nombre;
}

let pitoAgustin = new Pitos(3, 8, "blanco", true);
let Agustin = new Persona("Agustín");

console.log (pitoAgustin instanceof Pitos); // True.
console.log (pitoAgustin instanceof Persona); // False.