// Array original
let plantillaBetis = ["13 Adrián", "2 Bellerín", "5 Bartra", "23 Sabaly", "4 Johnny Cardoso", 
                      "16 Altimira", "20 Lo Celso", "22 Isco", "7 Antony", "9 Chumy Ávila", "19 Cucho"];

// Elementos del DOM
const ordenarBtn = document.getElementById('ordenarBtn');
const resultadoDiv = document.getElementById('resultado');

// Función para ordenar por dorsal
function ordenarPorDorsal(plantilla) {
  return plantilla.sort((a, b) => {
    // Extraer los números de dorsal
    const dorsalA = parseInt(a.split(" ")[0]);
    const dorsalB = parseInt(b.split(" ")[0]);
    
    // Comparar los dorsales
    return dorsalA - dorsalB;
  });
}

// Función para mostrar la plantilla en el DOM
function mostrarPlantilla(plantilla) {
  resultadoDiv.innerHTML = '';
  
  plantilla.forEach(jugador => {
    const jugadorElement = document.createElement('div');
    jugadorElement.className = 'jugador';
    jugadorElement.textContent = jugador;
    resultadoDiv.appendChild(jugadorElement);
  });
}

// Evento de clic para el botón
ordenarBtn.addEventListener('click', () => {
  const plantillaOrdenada = ordenarPorDorsal([...plantillaBetis]);
  mostrarPlantilla(plantillaOrdenada);
});

// Mostrar la plantilla original al cargar
document.addEventListener('DOMContentLoaded', () => {
  mostrarPlantilla(plantillaBetis);
}); 