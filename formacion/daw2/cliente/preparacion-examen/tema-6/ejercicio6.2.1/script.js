/**
 * No utilizar variables del tipo var (var = numero), están obsoletas.
 */

let persona = {
    // Creamos una propiedad llamada "nombre" que guarda un dato del tipo "String".
    nombre: "Agustín",
    // Creamos una propiedad llamada "edad" que guarda un dato del tipo "number".
    edad: 24,
    // Creamos una propiedad llamada "humano" que guarda un dato del tipo "boolean".
    humano: true,
    // Creamos una función que permite al objeto presentar accediendo a sus propiedades.
    saludar: function () {
        console.log("¡Hola! mi nombre es " + this.nombre + ", tengo " + this.edad + " años.");
    }
}

// Accedemos individualmente a las propiedades/atributos de un objeto.
console.log("El nombre de la persona es: " + persona.nombre);
console.log("¿Es esta persona un humano? La respuesta es: " + persona.humano);

// Accedemos a los métodos/funciones de un objeto.
persona.saludar();