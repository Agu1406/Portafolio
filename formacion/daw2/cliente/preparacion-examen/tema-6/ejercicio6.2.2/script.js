/**
 * No utilizar variables del tipo var (var = numero), están obsoletas.
 */

// Instanciamos un nuevo objeto generico vacio.
let persona = new Object ();
// Creamos la propiedad "nombre" y le damos un valor.
persona.nombre = "Agustín";
// Creamos la propiedad "dni" y le damos un valor.
persona.dni = "12345678R";
// Creamos la función "saludar" y definimos su código.
persona.saludar = function () {
    console.log("¡Hola! soy " + persona.nombre);
}

// Creamos una variable y la convertimos/definimos como objeto
let perro = {
    // Creamos la propiedad "nombre" y le damos un valor.
    nombre: "firulais",
    // Creamos la propiedad "edad" y le damos un valor.
    edad: 2,
    // Creamos la propiedad "vacunado" y le damos un valor.
    vacunado: true,
    // Creamos la función "ladrar" y definimos su código.
    ladrar: function () {
        console.log("Woof woof");
    }
}

// Intentamos probar las funciones del perro y de la persona.
perro.ladrar();
persona.saludar();