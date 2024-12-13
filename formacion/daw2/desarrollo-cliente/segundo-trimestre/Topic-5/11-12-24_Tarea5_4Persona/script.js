/**
 * Objetivos de la practica.
 * 
 * 1.º - Comprueba que funciona el literal del objeto persona.
 * 
 * 2.º - Crea un constructor Persona que permita crear objetos
 * de ese tipo con los mismos atributos del literal.
 */

// Creamos un objeto persona usando "{" "}".
let persona = {
    /***
     * En JavaScript es posible crear objetos dentro de
     * otros objetos, esto se conoce como anidación de
     * objetos, creamos un objeto llamado "nombre" en
     * el objeto "persona" con dos atributos, primer
     * nombre y apellido.
     */
    nombre : {
        nombre: "Agustín",
        apellido: "Piña"
    },

    // Creamos el atributo "edad" dentro de "persona"
    edad: 24,
    genero : "masculino",

    intereses : ["programación", "cocina"]

}