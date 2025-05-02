/**
 * Guardo los botones del HTML/CSS obteniendo los mismos con
 * sus ID's desde el DOM (Documento HTML).
 */
let tapete = document.getElementById("tapete");
let btnPausar = document.getElementById("pausar");
let btnRenaudar = document.getElementById("reanudar");

/**
 * El botón de pausar tiene un eventListener, cuando el usuario hace click
 * en el lo primero que hace es "pausar" la animación del tapete, luego,
 * usando un bucle foreach también pausa la animación de las bolas, del
 * taco y del mensaje (en caso de que se este animando en ese momento).
 */
btnPausar.addEventListener("click", function () {
  // Modifica los estilos del tapete para "pausar" la animación.
  tapete.style.animationPlayState = "paused";
  // Guardo todos los elementos dentro del tapete que tienen animaciones.
  let elementosAnimados = tapete.querySelectorAll(".taco, .bola, .mensaje");
  // Bucle que recorre todos los elementos (taco, bola, mensaje) y los pausa
  elementosAnimados.forEach((elemento) => {
    // Modifica los estilos de "X" elemento para pausar su animación.
    elemento.style.animationPlayState = "paused";
  });
});

/**
 * El botón de renaudar es una copia del botón anterior, hace exactamente
 * lo mismo pero en lugar de "pausar" (paused) las animaciones, las pone
 * en "marcha" (running).
 */
btnRenaudar.addEventListener("click", function () {
  // Modifica los estilos del tapete para "pausar" la animación.
  tapete.style.animationPlayState = "running";
  // Guardo todos los elementos dentro del tapete que tienen animaciones.
  let elementosAnimados = tapete.querySelectorAll(".taco, .bola, .mensaje");
  // Bucle que recorre todos los elementos (taco, bola, mensaje) y los pausa
  elementosAnimados.forEach((elemento) => {
    // Modifica los estilos de "X" elemento para pausar su animación.
    elemento.style.animationPlayState = "running";
  });
});
