/**
 * No usar la variable "var", está en deshuso y no es profesional, usar
 * let o const en su lugar.
 */

/**
 * Crea una página que muestre un contador de tiempo desde 5 segundos,
 * en el último segundo indique «Nos vamos...» y, después, redirija
 * a la página de Google.
 */

// Creamos una variable que controla el número de iteraciones.
let iteraciones = 4;
// Guardamos el objeto div que contendrá el conteo regresivo.
let contenedor = document.getElementById("redireccion");

// Creamos un intervalo (funciona como un bucle) que se ejecutará cada 1000 milisegundos (1 segundo).
const intervalo = setInterval(() => {
    // Si aún quedan iteraciones por hacer... se ejecuta el if.
    if (iteraciones >= 1) {
        // Actualiza el contenido del div con el número de iteraciones restantes.
        contenedor.innerHTML = `Redirigiendo en ${iteraciones} segundos...`;
    } else if (iteraciones === 1) {
        // Actualiza el contenido del div con el mensaje de despedida.
        contenedor.innerHTML = "Nos vamos...";
    } else {
        // Finalmente, redirige a la página de Google.
        window.location.href = 'https://www.google.com';
    }
    // Decrementa el número de iteraciones cada 1000 milisegundos (1 segundo).
    iteraciones--;

// 1000 milisegundos (1 segundo), define cuanto dura cada iteración.
}, 1000);
