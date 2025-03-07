// Array original
let plantillaBetis = ["13 Adrián", "2 Bellerín", "5 Bartra", "23 Sabaly", "4 Johnny Cardoso", 
                      "16 Altimira", "20 Lo Celso", "22 Isco", "7 Antony", "9 Chumy Ávila", "19 Cucho"];

// Elementos del DOM
const ordenarBtn = document.getElementById('ordenarBtn');
const resultadoDiv = document.getElementById('resultado');

// Función para ordenar alfabéticamente por nombre
function ordenarAlfabeticamente(plantilla) {
  return plantilla.sort((a, b) => {
    // Extraer los nombres (todo lo que viene después del espacio)
    const nombreA = a.substring(a.indexOf(" ") + 1).toLowerCase();
    const nombreB = b.substring(b.indexOf(" ") + 1).toLowerCase();
    
    // Comparar los nombres
    if (nombreA < nombreB) return -1;
    if (nombreA > nombreB) return 1;
    return 0;
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
  const plantillaOrdenada = ordenarAlfabeticamente([...plantillaBetis]);
  mostrarPlantilla(plantillaOrdenada);
});

// Mostrar la plantilla original al cargar
document.addEventListener('DOMContentLoaded', () => {
  mostrarPlantilla(plantillaBetis);
}); 