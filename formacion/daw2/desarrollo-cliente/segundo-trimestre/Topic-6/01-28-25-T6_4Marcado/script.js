/**
 * No usar la variable "var", está en deshuso y no es profesional, usar
 * let o const en su lugar.
 */

/**
 * Crea una página con una gran cantidad de texto.
 *
 * Solicita al usuario una palabra a buscar.
 * Realiza scroll hasta la primera ocurrencia de esa palabra (la palabra aparecerá remarcada).
 * Consejo: Aunque no es estándar, puede ser útil el método find() de la interfaz Window de JS.
 *
 * Cuando lo consigas, haz que:
 * A los tres segundos de encontrarla, salta un aviso indicando que se calculará el número de veces que aparece el texto en la página.
 * Cuando el usuario acepte, la página mostrará el número de ocurrencias del texto.
 * Se muestra el texto buscado y su cantidad de ocurrencias al principio de la página (y se traslada allí).
 */

let palabra = prompt("Escribe la palabra");

if (palabra) {
    window.find(palabra);
}

setTimeout (() => {
    if(confirm(`Se calculará el N.º de veces que aparece la palabra ${palabra} en el texto`)) {
        let contador = 0;
        let texto = document.body.textContent;
        let posicion = texto.indexOf(palabra);

        while (posicion != -1) {
            contador++;
            posicion = texto.indexOf(palabra, posicion + 1);
        }

        let parrafo1 = document.createElement("p");
        let contenido1 = document.createTextNode(`La palabra ${palabra} aparece ${contador} veces.`);

        parrafo1.appendChild(contenido1);

        document.body.insertBefore(parrafo1, document.body.firstChild);
    }
}, 3000)