/**
 * No usar la variable "var", está en desuso y no es profesional, usar
 * let o const en su lugar.
 */

// Guardamos todos los párrafos existentes en un array dinámico.
let todosLosParrafos = Array.from(document.querySelectorAll('p'));

// Eliminamos todos los parrafos del body usando un foreach con función flecha.
todosLosParrafos.forEach(parrafo => parrafo.remove());

// Variable que almacenará el párrafo visible.
let parrafoVisible;

// Función que muestra los párrafos uno a uno y borra el anterior.
let intervalo = setInterval(() => {
    // Si ya existe un párrafo visible, lo borra para poder mostrar otro.
    if (parrafoVisible) {
        parrafoVisible.remove();
    }

    // Verificamos si quedan párrafos para mostrar.
    if (todosLosParrafos.length > 0) {
        // Extraemos y clonamos el primer párrafo de la lista.
        parrafoVisible = todosLosParrafos.shift().cloneNode(true);
        // Lo agregamos al body para que sea mostrado.
        document.body.appendChild(parrafoVisible);
    } else {
        // Si ya mostramos todos los párrafos, detenemos el intervalo.
        clearInterval(intervalo);
    }
// Ejecuta cada iteración del intervalo en 1 segundo (1000 ms).
}, 1000);

