/* Ejemplo 1 */
let parrafo1 = document.getElementById('parrafo1');
parrafo1.addEventListener("click", mensaje);

function mensaje(p) {
    alert('¡Has hecho click!');
    parrafo1.removeEventListener("click", mensaje);
}

/* Ejemplo 2 */
let ejemplo2 = document.getElementById('ejemplo2');
let parrafos = ejemplo2.getElementsByTagName('p');
for (let parrafo of parrafos) {
    parrafo.addEventListener('click', () => {
        alert(parrafo.textContent);
    });
}

let p4 = document.createElement('p');
ejemplo2.appendChild(p4).textContent = 'Este párrafo (p4) se ha añadido después. Si haces click, no ocurrirá nada.';

