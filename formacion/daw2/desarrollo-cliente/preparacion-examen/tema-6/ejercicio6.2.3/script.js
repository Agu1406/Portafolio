/**
 * No utilizar variables del tipo var (var = numero), están obsoletas.
 */

/**
 * Constructor que nos permite crear varios objetos del tipo
 * persona.
 * @param {String} nombre de la persona.
 */
function Persona (nombre) {
    this.nombre = nombre;

    this.saludar = function () {
        console.log("Hola, mi nombre es " + this.nombre);
    }
}

// Creamos un objeto persona nuevo llamado "Agustín"
let persona1 = new Persona("Agustín");
// Creamos un objeto persona nuevo llamado "Marta"
let persona2 = new Persona("Marta");

/*
Aunque nosotros no lo vemos en segundo plano la palabra "this"
permite al constructor identificar que objeto lo esta utilizando
(persona1 o persona2) para así añadir a ese objeto en especifico
su nombre y crearle la función "saludar", por ejemplo, cuando yo
escribo:

let persona1 = new Persona("Agustín");

El constructor lo ve así:

function Persona (nombre) {
    persona1.nombre = nombre;

    persona1.saludar = function () {
        console.log("Hola, mi nombre es " + persona1.nombre);
    }
}
*/
