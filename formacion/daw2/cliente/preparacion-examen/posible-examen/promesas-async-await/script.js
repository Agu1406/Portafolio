// Array de la plantilla del Betis
const plantillaBetis = [
    { dorsal: 13, nombre: "Adrián", posicion: "Portero" },
    { dorsal: 2, nombre: "Bellerín", posicion: "Defensa" },
    { dorsal: 5, nombre: "Bartra", posicion: "Defensa" },
    { dorsal: 23, nombre: "Sabaly", posicion: "Defensa" },
    { dorsal: 4, nombre: "Johnny Cardoso", posicion: "Centrocampista" },
    { dorsal: 16, nombre: "Altimira", posicion: "Centrocampista" },
    { dorsal: 20, nombre: "Lo Celso", posicion: "Centrocampista" },
    { dorsal: 22, nombre: "Isco", posicion: "Centrocampista" },
    { dorsal: 7, nombre: "Antony", posicion: "Delantero" },
    { dorsal: 9, nombre: "Chumy Ávila", posicion: "Delantero" },
    { dorsal: 19, nombre: "Cucho", posicion: "Delantero" }
];

// Elementos del DOM
const cargarEscudoBtn = document.getElementById('cargarEscudo');
const cargarJugadoresBtn = document.getElementById('cargarJugadores');
const cargarTodoBtn = document.getElementById('cargarTodo');
const simularErrorBtn = document.getElementById('simularError');
const limpiarBtn = document.getElementById('limpiar');
const escudoImg = document.getElementById('escudo');
const loader = document.getElementById('loader');
const errorMessage = document.getElementById('errorMessage');
const successMessage = document.getElementById('successMessage');
const jugadoresContainer = document.getElementById('jugadoresContainer');
const tabs = document.querySelectorAll('.tab');
const tabContents = document.querySelectorAll('.tab-content');

// Función para mostrar el loader
function mostrarLoader() {
    loader.style.display = 'block';
    errorMessage.style.display = 'none';
    successMessage.style.display = 'none';
}

// Función para ocultar el loader
function ocultarLoader() {
    loader.style.display = 'none';
}

// Función para mostrar un mensaje de error
function mostrarError(mensaje) {
    errorMessage.textContent = mensaje;
    errorMessage.style.display = 'block';
    successMessage.style.display = 'none';
}

// Función para mostrar un mensaje de éxito
function mostrarExito(mensaje) {
    successMessage.textContent = mensaje;
    successMessage.style.display = 'block';
    errorMessage.style.display = 'none';
}

// Función para limpiar la pantalla
function limpiar() {
    jugadoresContainer.innerHTML = '';
    escudoImg.classList.remove('visible');
    errorMessage.style.display = 'none';
    successMessage.style.display = 'none';
}

// Función para esperar un tiempo determinado (útil para simular retrasos)
function esperar(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
}

// Promesa para cargar el escudo
function cargarEscudo() {
    return new Promise((resolve, reject) => {
        // Simulamos una carga con un retraso
        setTimeout(() => {
            // 10% de probabilidad de error (para demostración)
            const exito = Math.random() > 0.1;
            
            if (exito) {
                escudoImg.classList.add('visible');
                resolve('Escudo cargado correctamente');
            } else {
                reject('Error al cargar el escudo');
            }
        }, 1500); // 1.5 segundos de retraso
    });
}

// Promesa para cargar los jugadores
function cargarJugadores() {
    return new Promise((resolve, reject) => {
        // Simulamos una carga con un retraso
        setTimeout(() => {
            // 10% de probabilidad de error (para demostración)
            const exito = Math.random() > 0.1;
            
            if (exito) {
                // Limpiamos el contenedor
                jugadoresContainer.innerHTML = '';
                
                // Añadimos cada jugador con un pequeño retraso entre ellos
                plantillaBetis.forEach((jugador, index) => {
                    setTimeout(() => {
                        const jugadorElement = document.createElement('div');
                        jugadorElement.className = 'jugador-card';
                        
                        const dorsalElement = document.createElement('div');
                        dorsalElement.className = 'dorsal';
                        dorsalElement.textContent = jugador.dorsal;
                        
                        const infoElement = document.createElement('div');
                        infoElement.innerHTML = `<strong>${jugador.nombre}</strong> - ${jugador.posicion}`;
                        
                        jugadorElement.appendChild(dorsalElement);
                        jugadorElement.appendChild(infoElement);
                        jugadoresContainer.appendChild(jugadorElement);
                        
                        // Hacemos visible el jugador con un pequeño retraso para la animación
                        setTimeout(() => {
                            jugadorElement.classList.add('visible');
                        }, 50);
                    }, index * 200); // 200ms entre cada jugador
                });
                
                resolve('Jugadores cargados correctamente');
            } else {
                reject('Error al cargar los jugadores');
            }
        }, 2000); // 2 segundos de retraso
    });
}

// Función para simular un error
function simularError() {
    return new Promise((resolve, reject) => {
        setTimeout(() => {
            reject('Error simulado para demostración');
        }, 1000);
    });
}

// Función para cargar el escudo usando async/await
async function cargarEscudoAsync() {
    try {
        mostrarLoader();
        const resultado = await cargarEscudo();
        ocultarLoader();
        mostrarExito(resultado);
    } catch (error) {
        ocultarLoader();
        mostrarError(error);
    }
}

// Función para cargar jugadores usando async/await
async function cargarJugadoresAsync() {
    try {
        mostrarLoader();
        const resultado = await cargarJugadores();
        ocultarLoader();
        mostrarExito(resultado);
    } catch (error) {
        ocultarLoader();
        mostrarError(error);
    }
}

// Función para cargar todo usando async/await
async function cargarTodoAsync() {
    try {
        mostrarLoader();
        // Ejecutamos ambas promesas en paralelo
        const resultados = await Promise.all([
            cargarEscudo(),
            cargarJugadores()
        ]);
        ocultarLoader();
        mostrarExito('Escudo y jugadores cargados correctamente');
    } catch (error) {
        ocultarLoader();
        mostrarError(error);
    }
}

// Función para simular error usando async/await
async function simularErrorAsync() {
    try {
        mostrarLoader();
        await simularError();
        ocultarLoader();
        mostrarExito('Esto no debería mostrarse');
    } catch (error) {
        ocultarLoader();
        mostrarError(error);
    }
}

// Función para cambiar de pestaña
function cambiarTab(tabId) {
    // Desactivar todas las pestañas
    tabs.forEach(tab => tab.classList.remove('active'));
    tabContents.forEach(content => content.classList.remove('active'));
    
    // Activar la pestaña seleccionada
    document.querySelector(`.tab[data-tab="${tabId}"]`).classList.add('active');
    document.querySelector(`.tab-content[data-tab="${tabId}"]`).classList.add('active');
}

// Event listeners para las pestañas
tabs.forEach(tab => {
    tab.addEventListener('click', () => {
        cambiarTab(tab.getAttribute('data-tab'));
    });
});

// Event listeners para los botones
cargarEscudoBtn.addEventListener('click', cargarEscudoAsync);
cargarJugadoresBtn.addEventListener('click', cargarJugadoresAsync);
cargarTodoBtn.addEventListener('click', cargarTodoAsync);
simularErrorBtn.addEventListener('click', simularErrorAsync);
limpiarBtn.addEventListener('click', limpiar);

// Mensaje inicial
mostrarExito('Haz clic en los botones para cargar los datos usando async/await'); 