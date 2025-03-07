/**
 * No utilizar variables del tipo var (var = numero), están obsoletas.
 */

// Primera forma - objeto literal en variable.
let persona1 = {
    nombre: "Agustín"
}
// Segunda forma - objeto literal con Object.
let persona2 = new Object();
persona2.nombre = "Agustín";

// Tercera forma - objeto con función constructora.
function Persona3 (nombre) {
    this.nombre = nombre;
}
let persona3 = new Persona3("Agustín");


// Cuarta forma - objetos creados con clases.
class Persona {
    // Constructor de objetos.
    constructor (nombre) {
        this.nombre = nombre;
    }
    // Función de los objetos.
    saludar() {
        console.log("¡Hola! Mi nombre es " + this.nombre);
    }
}

let persona4 = new Persona ("Agustín");

// Herencia con clases de JavaScript
class Programador extends Persona {
    // Constructor de la clase Programador.
    constructor (nombre, sueldo) {
        super(nombre);
        this.sueldo = sueldo;
    }

    cobrar() {
        console.log("¡He cobrado!");
    }
}

let programador = new Programador("Juan", 2000);

programador.saludar();
programador.cobrar();
