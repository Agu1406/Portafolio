/**
 * No utilizar variables del tipo var (var = numero), están obsoletas.
 */

// Botón que ordene alfabéticamente una lista pasados 3s.
const lista = document.querySelector("ul");
const boton = document.getElementById("ordenar");

function ordenarLista() {
    // Espera 3 segundos y ordena
    setTimeout(() => {
        // Convierte a array, ordena y reinserta
        const elementos = Array.from(lista.querySelectorAll("li"));
        elementos.sort((a, b) => a.textContent.localeCompare(b.textContent));
        
        // Vacía y rellena la lista
        lista.innerHTML = "";
        elementos.forEach(el => lista.appendChild(el));
    }, 3000);
}

boton.addEventListener("click", ordenarLista);