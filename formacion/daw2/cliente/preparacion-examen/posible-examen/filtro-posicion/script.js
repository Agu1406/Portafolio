// Array de la plantilla con posiciones añadidas
let plantillaBetis = [
  { dorsal: "13", nombre: "Adrián", posicion: "Portero" },
  { dorsal: "2", nombre: "Bellerín", posicion: "Defensa" },
  { dorsal: "5", nombre: "Bartra", posicion: "Defensa" },
  { dorsal: "23", nombre: "Sabaly", posicion: "Defensa" },
  { dorsal: "4", nombre: "Johnny Cardoso", posicion: "Centrocampista" },
  { dorsal: "16", nombre: "Altimira", posicion: "Centrocampista" },
  { dorsal: "20", nombre: "Lo Celso", posicion: "Centrocampista" },
  { dorsal: "22", nombre: "Isco", posicion: "Centrocampista" },
  { dorsal: "7", nombre: "Antony", posicion: "Delantero" },
  { dorsal: "9", nombre: "Chumy Ávila", posicion: "Delantero" },
  { dorsal: "19", nombre: "Cucho", posicion: "Delantero" }
];

// Elementos del DOM
const listaJugadores = document.getElementById('lista-jugadores');
const botones = document.querySelectorAll('button');

// Función para mostrar jugadores
function mostrarJugadores(jugadores) {
  listaJugadores.innerHTML = '';
  
  jugadores.forEach(jugador => {
    const tarjeta = document.createElement('div');
    tarjeta.className = 'tarjeta-jugador';
    
    tarjeta.innerHTML = `
      <div class="dorsal">${jugador.dorsal}</div>
      <div class="nombre">${jugador.nombre}</div>
      <div class="posicion">${jugador.posicion}</div>
    `;
    
    listaJugadores.appendChild(tarjeta);
  });
}

// Añadir eventos a los botones
botones.forEach(boton => {
  boton.addEventListener('click', () => {
    // Quitar clase activo de todos los botones
    botones.forEach(b => b.classList.remove('activo'));
    
    // Añadir clase activo al botón pulsado
    boton.classList.add('activo');
    
    let jugadoresFiltrados;
    
    // Filtrar según el botón pulsado
    switch(boton.id) {
      case 'defensas':
        jugadoresFiltrados = plantillaBetis.filter(j => j.posicion === 'Defensa' || j.posicion === 'Portero');
        break;
      case 'centrocampistas':
        jugadoresFiltrados = plantillaBetis.filter(j => j.posicion === 'Centrocampista');
        break;
      case 'delanteros':
        jugadoresFiltrados = plantillaBetis.filter(j => j.posicion === 'Delantero');
        break;
      default:
        jugadoresFiltrados = plantillaBetis;
    }
    
    mostrarJugadores(jugadoresFiltrados);
  });
});

// Mostrar todos los jugadores al cargar
document.addEventListener('DOMContentLoaded', () => {
  mostrarJugadores(plantillaBetis);
}); 