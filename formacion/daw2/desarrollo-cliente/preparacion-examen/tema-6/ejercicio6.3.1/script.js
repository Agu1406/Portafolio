/**
 * No utilizar variables del tipo var (var = numero), están obsoletas.
 */

function Pitos (grosor, largo, color, peludo) {
    
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

let pitoAgustin = new Pito (3, 8, "blanco", true);
let pitoIsmael = new Pito (5, 15, "blanco", false);