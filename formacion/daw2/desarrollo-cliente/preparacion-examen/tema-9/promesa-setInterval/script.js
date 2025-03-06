/**
 * Ejemplo de promesa con setInterval para niños
 * Simula hornear galletas y ver cómo se van cocinando
 */

// Elementos del DOM
const botonHornearGalletas = document.getElementById('hornear-galletas');
const mensajeElement = document.getElementById('mensaje');
const temperaturaElement = document.getElementById('temperatura');
const galletasContainer = document.getElementById('galletas-container');

// Función que crea una promesa para hornear galletas
function hornearGalletas() {
    // Mostrar mensaje de inicio
    mensajeElement.textContent = "Calentando el horno...";
    mensajeElement.className = "mensaje espera";
    galletasContainer.innerHTML = "";
    
    // Variables para controlar el proceso
    let temperatura = 0;
    let intervalId;
    let galletasHorneadas = 0;
    
    // Crear y devolver una promesa
    return new Promise((resolve, reject) => {
        // Usamos setInterval para actualizar la temperatura cada 0.5 segundos
        intervalId = setInterval(() => {
            // Aumentamos la temperatura
            temperatura += 10;
            
            // Actualizamos la barra de temperatura
            temperaturaElement.style.width = `${temperatura}%`;
            
            // Actualizamos el mensaje según la temperatura
            if (temperatura < 50) {
                mensajeElement.textContent = `El horno se está calentando... ${temperatura}°C`;
            } else if (temperatura < 100) {
                mensajeElement.textContent = `¡El horno está casi listo! ${temperatura}°C`;
            } else {
                // Cuando llegamos a 100%, el horno está listo
                mensajeElement.textContent = `¡El horno está listo! Horneando galletas...`;
                
                // Añadimos una galleta cada segundo cuando el horno está listo
                if (temperatura >= 100 && temperatura % 20 === 0) {
                    const galleta = document.createElement('div');
                    galleta.className = 'galleta';
                    galleta.textContent = '🍪';
                    galletasContainer.appendChild(galleta);
                    galletasHorneadas++;
                }
            }
            
            // Si la temperatura sube demasiado, las galletas se queman
            if (temperatura > 200) {
                clearInterval(intervalId);
                reject("¡Oh no! Las galletas se quemaron porque el horno estaba demasiado caliente.");
            }
            
            // Cuando tenemos 5 galletas, terminamos
            if (galletasHorneadas >= 5) {
                clearInterval(intervalId);
                resolve("¡Las galletas están listas! Hemos horneado 5 deliciosas galletas.");
            }
        }, 500); // Actualizar cada 0.5 segundos
    });
}

// Evento para el botón de hornear galletas
botonHornearGalletas.addEventListener('click', () => {
    // Desactivar el botón mientras se hornean las galletas
    botonHornearGalletas.disabled = true;
    
    // Llamamos a la función que devuelve la promesa
    hornearGalletas()
        .then((mensaje) => {
            // Esto se ejecuta cuando las galletas están listas
            mensajeElement.textContent = mensaje;
            mensajeElement.className = "mensaje exito";
            console.log("¡Galletas deliciosas!");
            
            // Reactivar el botón
            botonHornearGalletas.disabled = false;
        })
        .catch((error) => {
            // Esto se ejecuta si las galletas se queman
            mensajeElement.textContent = error;
            mensajeElement.className = "mensaje error";
            console.log("Galletas quemadas :(");
            
            // Limpiar las galletas quemadas
            galletasContainer.innerHTML = "🖤🖤🖤 Galletas quemadas 🖤🖤🖤";
            
            // Reactivar el botón
            botonHornearGalletas.disabled = false;
        });
});

// Mensaje inicial
mensajeElement.textContent = "¿Quieres hornear galletas? ¡Pulsa el botón!";
mensajeElement.className = "mensaje"; 