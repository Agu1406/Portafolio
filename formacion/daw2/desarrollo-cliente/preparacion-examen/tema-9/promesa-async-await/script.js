/**
 * Ejemplo de async/await para niños
 * Simula preparar un desayuno mágico paso a paso
 */

// Elementos del DOM
const botonPreparar = document.getElementById('preparar-desayuno');
const mensajeElement = document.getElementById('mensaje');
const panElement = document.getElementById('pan');
const huevosElement = document.getElementById('huevos');
const jugoElement = document.getElementById('jugo');
const estadoPanElement = document.getElementById('estado-pan');
const estadoHuevosElement = document.getElementById('estado-huevos');
const estadoJugoElement = document.getElementById('estado-jugo');
const logElement = document.getElementById('log');

// Función para añadir un mensaje al log
function agregarLog(mensaje) {
    const logItem = document.createElement('div');
    logItem.className = 'log-item';
    logItem.textContent = mensaje;
    logElement.appendChild(logItem);
    logElement.scrollTop = logElement.scrollHeight; // Hacer scroll al final
}

// Función para tostar el pan
function tostarPan() {
    return new Promise((resolve, reject) => {
        estadoPanElement.textContent = "Tostando...";
        agregarLog("🍞 Poniendo el pan en la tostadora...");
        
        setTimeout(() => {
            agregarLog("🍞 El pan se está tostando..
        }, 3000);
    });
}

// Función para batir los huevos
function batirHuevos() {
    return new Promise((resolve, reject) => {
        estadoHuevosElement.textContent = "Batiendo...";
        agregarLog("🥚 Batiendo los huevos...");
        
        setTimeout(() => {
            agregarLog("🥚 Los huevos están batidos.");
            resolve();
        }, 2000);
    });
}

// Función para preparar el jugo
function prepararJugo() {
    return new Promise((resolve, reject) => {
        estadoJugoElement.textContent = "Preparando...";
        agregarLog("🧃 Preparando el jugo...");
        
        setTimeout(() => {
            agregarLog("🧃 El jugo está listo.");
            resolve();
        }, 1000);
    });
}

// Función para preparar el desayuno
async function prepararDesayuno() {
    try {
        await tostarPan();
        await batirHuevos();
        await prepararJugo();
        mensajeElement.textContent = "¡Desayuno listo!";
        mensajeElement.className = "mensaje exito";
    } catch (error) {
        mensajeElement.textContent = error;
        mensajeElement.className = "mensaje error";
    }
}

// Evento para el botón de preparar desayuno
botonPreparar.addEventListener('click', prepararDesayuno); 