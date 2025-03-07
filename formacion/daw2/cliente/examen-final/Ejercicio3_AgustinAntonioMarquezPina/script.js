// Elementos del DOM
let mostrarBtn = document.getElementById('mostrarBtn');
let ocultarBtn = document.getElementById('ocultarBtn');
let alternarBtn = document.getElementById('alternarBtn');
let escudo = document.getElementById('escudo');
let info = document.getElementById('info');

// Variable para controlar el estado del escudo
let escudoVisible = false;
let temporizador = null;

// Función para mostrar el escudo
function mostrarEscudo() {
  escudo.classList.add('visible');
  escudoVisible = true;
  info.textContent = 'El escudo está visible';

  // Cancelar cualquier temporizador existente
  if (temporizador) {
    clearTimeout(temporizador);
    temporizador = null;
  }
}

// Función para ocultar el escudo
function ocultarEscudo() {
  escudo.classList.remove('visible');
  escudoVisible = false;
  info.textContent = 'El escudo está oculto';

  // Cancelar cualquier temporizador existente
  if (temporizador) {
    clearTimeout(temporizador);
    temporizador = null;
  }
}

// Función para mostrar el escudo temporalmente (3 segundos)
function mostrarTemporalmente() {
  mostrarEscudo();

  // Configurar temporizador para ocultar después de 3 segundos
  temporizador = setTimeout(() => {
    ocultarEscudo();
    info.textContent = 'El escudo se ocultó automáticamente después de 3 segundos';
  }, 3000);
}

// Función para alternar el estado del escudo
function alternarEscudo() {
  if (escudoVisible) {
    ocultarEscudo();
  } else {
    mostrarTemporalmente();
  }
}

// Eventos para los botones
mostrarBtn.addEventListener('click', mostrarTemporalmente);
ocultarBtn.addEventListener('click', ocultarEscudo);
alternarBtn.addEventListener('click', alternarEscudo);