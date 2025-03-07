// Array de la plantilla
let plantillaBetis = ["13 Adrián", "2 Bellerín", "5 Bartra", "23 Sabaly", "4 Johnny Cardoso", 
                      "16 Altimira", "20 Lo Celso", "22 Isco", "7 Antony", "9 Chumy Ávila", "19 Cucho"];

// Elementos del DOM
const mostrarBtn = document.getElementById('mostrarBtn');
const contenedor = document.getElementById('contenedor');

// Función para mostrar jugadores con retraso
function mostrarJugadoresConRetraso() {
  // Deshabilitar el botón mientras se muestra
  mostrarBtn.disabled = true;
  
  // Limpiar contenedor
  contenedor.innerHTML = '';
  
  // Mostrar cada jugador con retraso
  plantillaBetis.forEach((jugador, index) => {
    setTimeout(() => {
      // Crear elemento para el jugador
      const jugadorElement = document.createElement('div');
      jugadorElement.className = 'jugador';
      jugadorElement.textContent = jugador;
      contenedor.appendChild(jugadorElement);
      
      // Aplicar la clase visible después de un pequeño retraso para activar la transición
      setTimeout(() => {
        jugadorElement.classList.add('visible');
      }, 50);
      
      // Habilitar el botón cuando se muestre el último jugador
      if (index === plantillaBetis.length - 1) {
        mostrarBtn.disabled = false;
      }
    }, index * 1000); // 1 segundo de retraso entre cada jugador
  });
}

// Evento de clic para el botón
mostrarBtn.addEventListener('click', mostrarJugadoresConRetraso); 