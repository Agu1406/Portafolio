/**
 * No usar la variable "var", está en deshuso y no es profesional, usar
 * let o const en su lugar.
 */

// Guardo en una variable el objeto "formulario" extradio desde el cliente usando su ID.
let formulario = document.getElementById("formulario");

/**
 * Para evitar que se envie vacio, es decir, por "default" agregamos un
 * event listenerg que cuando detecte que se pinche el botón submit
 * evite que se envie el por defecto.
 */
formulario.addEventListener("submit", function(event) {
    event.preventDefault();
})

/**
 * Dentro del objeto "formulario" existen otros dos objetos que son
 * "usuario" y "contrasena" identificados por sus ID's, entonces de
 * ellos extramos sus valores.
 */
const usuario = formulario.usuario.value;
const contrasena = formulario.contrasena.value;

// Creamos un objeto JSON donde guardamos los datos extraidos
const datos = JSON.stringify(
    /***
     * Esto lo vimos en cliente, podemos crear objetos dentro
     * de otros objetos (anidación de objetos), usando llaves
     * cremos los objetos "usuario" y "contrasena" con sus
     * respectivos valores que extraimos antés del formulario.
     */
    {
        usuario: usuario,
        contrasena: contrasena
    }
    // Fuera de las llaves cerramos los argumentos "()" del JSON.
);

// Convertimos este objeto en un JSON.
const datosJSON = new JSON.stringify(datos);


// Instanciamos una nueva conexión con XMLHTTPREQUEST();
const conexion = new XMLHttpRequest();

/**
 * Abrimos una nueva conexión con el servidor (PHP), dentro
 * de las variables de apertura de la conexión indicamos
 * que el método de envio de datos es POST, también a que
 * servidor (PHP) estamos llamado y si la conexión es
 * asincrona (TRUE) o sincrona (FALSE).
 */
conexion.open("POST", "../servidor.php", true);

