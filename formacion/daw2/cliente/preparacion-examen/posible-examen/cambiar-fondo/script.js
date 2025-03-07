// Array de la plantilla
let plantillaBetis = ["13 Adrián", "2 Bellerín", "5 Bartra", "23 Sabaly", "4 Johnny Cardoso", 
                      "16 Altimira", "20 Lo Celso", "22 Isco", "7 Antony", "9 Chumy Ávila", "19 Cucho"];

// Elementos del DOM
const activarFondoBtn = document.getElementById('activarFondo');
const desactivarFondoBtn = document.getElementById('desactivarFondo');
const listaJugadores = document.getElementById('lista-jugadores');

// Función para mostrar la lista de jugadores
function mostrarJugadores() {
  listaJugadores.innerHTML = '';
  
  plantillaBetis.forEach(jugador => {
    const jugadorElement = document.createElement('div');
    jugadorElement.className = 'jugador';
    jugadorElement.textContent = jugador;
    listaJugadores.appendChild(jugadorElement);
  });
}

// Función para activar el fondo del Betis
function activarFondoBetis() {
  document.body.classList.add('fondo-betis');
  
  // Guardar preferencia en localStorage
  localStorage.setItem('fondoBetis', 'activo');
}

// Función para desactivar el fondo del Betis
function desactivarFondoBetis() {
  document.body.classList.remove('fondo-betis');
  
  // Guardar preferencia en localStorage
  localStorage.setItem('fondoBetis', 'inactivo');
}

// Eventos para los botones
activarFondoBtn.addEventListener('click', activarFondoBetis);
desactivarFondoBtn.addEventListener('click', desactivarFondoBetis);

// Inicializar
document.addEventListener('DOMContentLoaded', () => {
  // Mostrar jugadores
  mostrarJugadores();
  
  // Comprobar si hay una preferencia guardada
  const fondoGuardado = localStorage.getItem('fondoBetis');
  if (fondoGuardado === 'activo') {
    activarFondoBetis();
  }
}); 