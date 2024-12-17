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
        pila: "Agustín",
        apellido: "Piña"
    },

    // Creamos el atributo "edad" dentro de "persona"
    edad: 24,
    // También creamos el atributo genero y un valor.
    genero : "masculino",
    // También creamos (en un array) los intereses.
    intereses : ["programación", "cocina"],

    /**
     * Podemos crear métodos / funciones dentro de los
     * objetos, aquí haremos dos, uno llamado "saludo"
     * que imprime en un "alert" el nombre de la persona.
     * 
     * Y otro que se llamara "bio" que tendra la edad
     * y el genero (datos biologicos).
     */
    
    // Función que imprime en un alert un saludo de persona.
    saludo: function () {
        alert("¡Hola! Mi nombre es: " + this.nombre.pila + " y mi apellido es: " + this.apellido);
    },
    // Función que imprime los datos biologicos de la persona.
    bio: function () {
        alert(this.nombre.pila + " tiene " + this.edad + " años y su genero es: " + this.genero);
    }
};

/**
 * Ahora que ya existen el objetos y sus atributos, podemos
 * acceder a ellos y manipularlos.
 */

/**
 * Dentro de "persona" existe un objeto llamado "nombre",
 * en JavaScript se permite crear facilmente objetos dentro
 * de objetos, se llama "anidación" de objetos, accedemos
 * al objeto "persona", dentro del cual está el objeto
 * "nombre" y dentro de nombre acceder a "nombre" o
 * "apellido" según nos interese.
 */
console.log("Nombre: " + persona.nombre.pila);
console.log("Apellido: " + persona.nombre.apellido);

/**
 * Podemos acceder al array de intereses y usando posiciones
 * podemos imprimir uno u otro.
 */
console.log(persona.intereses[0]);
console.log(persona.intereses[1]);

/**
 * También podemos imprimir sus atributos que son a secas
 * solo eso, atributos y valores, como la edad o genero.
 */
console.log(persona.genero);
console.log(persona.edad);

/**
 * Probamos los métodos creados del objeto persona.
 */
persona.bio();
persona.saludo();