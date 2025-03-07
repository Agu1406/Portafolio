// Array de la plantilla
let plantillaBetis = ["13 Adrián", "2 Bellerín", "5 Bartra", "23 Sabaly", "4 Johnny Cardoso", 
                      "16 Altimira", "20 Lo Celso", "22 Isco", "7 Antony", "9 Chumy Ávila", "19 Cucho"];

// Variables globales
let indiceActual = 0;
let temporizador;
let contadorSegundos = 3;
let pausado = true;

// Elementos del DOM
const dorsalElement = document.getElementById('dorsal');
const nombreElement = document.getElementById('nombre');
const contadorElement = document.getElementById('contador');
const iniciarBtn = document.getElementById('iniciar');
const pausarBtn = document.getElementById('pausar');
const reiniciarBtn = document.getElementById('reiniciar');

// Función para mostrar el jugador actual
function mostrarJugador(indice) {
  if (indice < plantillaBetis.length) {
    const jugador = plantillaBetis[indice];
    const partes = jugador.split(' ');
    const dorsal = partes[0];
    const nombre = partes.slice(1).join(' ');
    
    dorsalElement.textContent = dorsal;
    nombreElement.textContent = nombre;
  } else {
    // Si llegamos al final, reiniciamos
    indiceActual = 0;
    mostrarJugador(indiceActual);
  }
}

// Función para actualizar el contador
function actualizarContador() {
  contadorElement.textContent = contadorSegundos;
  
  if (contadorSegundos === 0) {
    // Pasar al siguiente jugador
    indiceActual++;
    mostrarJugador(indiceActual);
    contadorSegundos = 3; // Reiniciar contador
  } else {
    contadorSegundos--;
  }
}

// Iniciar el temporizador
iniciarBtn.addEventListener('click', () => {
  if (pausado) {
    pausado = false;
    temporizador = setInterval(actualizarContador, 1000);
    iniciarBtn.disabled = true;
    pausarBtn.disabled = false;
  }
});

// Pausar el temporizador
pausarBtn.addEventListener('click', () => {
  clearInterval(temporizador);
  pausado = true;
  iniciarBtn.disabled = false;
  pausarBtn.disabled = true;
});

// Reiniciar el temporizador
reiniciarBtn.addEventListener('click', () => {
  clearInterval(temporizador);
  pausado = true;
  indiceActual = 0;
  contadorSegundos = 3;
  mostrarJugador(indiceActual);
  contadorElement.textContent = contadorSegundos;
  iniciarBtn.disabled = false;
  pausarBtn.disabled = true;
});

// Inicializar
document.addEventListener('DOMContentLoaded', () => {
  mostrarJugador(indiceActual);
  pausarBtn.disabled = true;
}); 