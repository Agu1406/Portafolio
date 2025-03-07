/**
 * Ejemplo de promesa simple para niños
 * Simula pedir un helado a mamá
 */

// Elementos del DOM
const botonPedirHelado = document.getElementById('pedir-helado');
const mensajeElement = document.getElementById('mensaje');
const heladoElement = document.getElementById('helado');

// Función que crea una promesa para pedir un helado
function pedirHelado() {
    return new Promise((resolve, reject) => {
        // Mamá decide después de 2 segundos
        setTimeout(() => {
            // 70% de probabilidad de que mamá diga que sí
            if (Math.random() < 0.7) {
                resolve("¡Sí! Aquí tienes tu helado");
            } else {
                reject("Lo siento, no hay helado hoy");
            }
        }, 2000);
    });
}

// Evento para el botón
botonPedirHelado.addEventListener('click', () => {
    mensajeElement.textContent = "Esperando a que mamá decida...";
    mensajeElement.className = "mensaje";
    heladoElement.style.display = "none";
    
    pedirHelado()
        .then((mensaje) => {
            mensajeElement.textContent = mensaje;
            mensajeElement.className = "mensaje exito";
            heladoElement.style.display = "block";
        })
        .catch((error) => {
            mensajeElement.textContent = error;
            mensajeElement.className = "mensaje error";
        });
});

// Mensaje inicial
mensajeElement.textContent = "¿Quieres un helado? ¡Pídeselo a mamá!";