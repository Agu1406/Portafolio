/**
 * Ejemplo de Promise.race para niños
 * Simula una carrera de coches de juguete
 */

// Elementos del DOM
const botonIniciar = document.getElementById('iniciar-carrera');
const mensajeElement = document.getElementById('mensaje');
const cocheRojoElement = document.getElementById('coche-rojo');
const cocheAzulElement = document.getElementById('coche-azul');
const pistaElement = document.getElementById('pista');

// Función para mover un coche
function moverCoche(coche, tiempo, probabilidadChoque) {
    return new Promise((resolve, reject) => {
        let distancia = 0;
        const meta = pistaElement.clientWidth - 50; // Ancho de la pista menos el ancho del coche
        const velocidad = meta / tiempo; // Velocidad para llegar en el tiempo indicado
        
        // Posición inicial
        coche.style.left = '0px';
        
        const intervalId = setInterval(() => {
            // Aumentar la distancia
            distancia += velocidad / 10; // Dividimos por 10 porque actualizamos cada 100ms
            
            // Actualizar la posición del coche
            coche.style.left = `${distancia}px`;
            
            // Comprobar si hay un choque (1% de probabilidad en cada paso)
            if (Math.random() < probabilidadChoque) {
                clearInterval(intervalId);
                coche.classList.add('choque');
                reject(`¡${coche.dataset.nombre} ha chocado!`);
            }
            
            // Comprobar si ha llegado a la meta
            if (distancia >= meta) {
                clearInterval(intervalId);
                coche.classList.add('ganador');
                resolve(`¡${coche.dataset.nombre} ha ganado la carrera!`);
            }
        }, 100); // Actualizar cada 100ms
    });
}

// Evento para el botón de iniciar carrera
botonIniciar.addEventListener('click', () => {
    // Desactivar el botón durante la carrera
    botonIniciar.disabled = true;
    
    // Reiniciar los coches
    cocheRojoElement.style.left = '0px';
    cocheAzulElement.style.left = '0px';
    cocheRojoElement.classList.remove('choque', 'ganador');
    cocheAzulElement.classList.remove('choque', 'ganador');
    
    // Mostrar mensaje de inicio
    mensajeElement.textContent = "¡La carrera ha comenzado!";
    mensajeElement.className = "mensaje espera";
    
    // Crear las promesas para cada coche
    const cocheRojo = moverCoche(cocheRojoElement, 3, 0.005); // 3 segundos, 0.5% de probabilidad de choque en cada paso
    const cocheAzul = moverCoche(cocheAzulElement, 5, 0.003); // 5 segundos, 0.3% de probabilidad de choque en cada paso
    
    // Usar Promise.race para ver qué coche llega primero
    Promise.race([cocheRojo, cocheAzul])
        .then((mensaje) => {
            // Esto se ejecuta cuando un coche gana
            mensajeElement.textContent = mensaje;
            mensajeElement.className = "mensaje exito";
            console.log(mensaje);
            
            // Reactivar el botón después de un tiempo
            setTimeout(() => {
                botonIniciar.disabled = false;
            }, 2000);
        })
        .catch((error) => {
            // Esto se ejecuta si un coche choca
            mensajeElement.textContent = error;
            mensajeElement.className = "mensaje error";
            console.log(error);
            
            // Reactivar el botón después de un tiempo
            setTimeout(() => {
                botonIniciar.disabled = false;
            }, 2000);
        });
    
    // También podemos usar Promise.all para saber cuando ambos coches han terminado
    Promise.all([cocheRojo, cocheAzul])
        .then((mensajes) => {
            console.log("Ambos coches han terminado la carrera:", mensajes);
        })
        .catch((error) => {
            console.log("Al menos un coche no ha terminado:", error);
        });
});

// Mensaje inicial
mensajeElement.textContent = "¡Prepárate para la carrera! Pulsa el botón para empezar.";
mensajeElement.className = "mensaje"; 