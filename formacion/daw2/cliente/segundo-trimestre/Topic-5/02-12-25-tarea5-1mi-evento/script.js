/**
 * No usar la variable "var", está en deshuso y no es profesional, usar
 * let o const en su lugar.
 */

/**
 * Función que muestra un mensaje de alerta cuando se presiona una tecla.
 * Esta función se utiliza en el primer input con el evento "onkeydown".
 */
function mostrarMensaje() {
    alert("Se ha presionado una tecla - manera 1");
}

/**
 * Agrega un event listener al input con id "campo".
 * Este listener muestra un mensaje de alerta cuando se presiona una tecla.
 */
document.getElementById("campo").addEventListener("keydown", () => {
    alert("Se ha presionado una tecla - manera 2");
});

/**
 * Asigna una función al evento "onkeydown" del input con id "campo2".
 * Esta función muestra un mensaje de alerta cuando se presiona una tecla.
 */
document.getElementById("campo2").onkeydown = () => {
    alert("Se ha presionado una tecla - manera 3");
}