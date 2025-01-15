let contenedor = document.getElementById('ejemplo1');
let parrafo = document.getElementById('parrafo1');
let boton = document.getElementById('boton1');

// Fase de burbuja (por defecto)
contenedor.addEventListener('click', () => {
    console.log("Click en contenedor.");
});
parrafo.addEventListener('click', () => {
    console.log("Click en párrafo.")
});
boton.addEventListener('click', () => {
    console.log('Por fase de burbuja:');
    console.log("Click en botón.")
});


// Fase de captura
contenedor.addEventListener('click', () => {
    console.log('Por fase de captura:')
    console.log("Click en contenedor.");
}, true);
parrafo.addEventListener('click', () => {
    console.log("Click en párrafo.")
}, true);
boton.addEventListener('click', () => {
    console.log("Click en botón.")
}, true);

