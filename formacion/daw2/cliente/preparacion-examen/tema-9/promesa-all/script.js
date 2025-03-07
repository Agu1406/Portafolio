// Elementos que vamos a usar
const botonPedirCaramelo = document.getElementById('pedir-caramelo');
const mensajeElement = document.getElementById('mensaje');
const carameloElement = document.getElementById('caramelo');
const pensandoElement = document.getElementById('pensando');

// Función que simula pedir un caramelo a mamá
function pedirCarameloAMama() {
    // Mostrar que mamá está pensando
    pensandoElement.style.display = "block";
    mensajeElement.textContent = "Mamá está pensando...";
    carameloElement.style.display = "none";
    
    // Crear una promesa (como una promesa de mamá)
    return new Promise((resolve, reject) => {
        // Mamá tarda 3 segundos en decidir
        setTimeout(() => {
            // Mamá decide si dar el caramelo (80% de probabilidad)
            const darCaramelo = Math.random() < 0.8;
            
            if (darCaramelo) {
                // Si mamá dice que sí
                resolve("¡Sí! Aquí tienes tu caramelo");
            } else {
                // Si mamá dice que no
                reject("No puedes comer más caramelos hoy");
            }
            
            // Ya no está pensando
            pensandoElement.style.display = "none";
        }, 3000);
    });
}

// Cuando hacemos clic en el botón
botonPedirCaramelo.addEventListener('click', () => {
    // Desactivar el botón mientras esperamos
    botonPedirCaramelo.disabled = true;
    
    // Pedir el caramelo a mamá
    pedirCarameloAMama()
        .then((mensaje) => {
            // Si mamá dijo que sí
            mensajeElement.textContent = mensaje;
            carameloElement.style.display = "block";
            
            // Hacer que el caramelo desaparezca después de un tiempo (como si lo comieras)
            setTimeout(() => {
                carameloElement.style.display = "none";
                mensajeElement.textContent = "¡Mmm, qué rico estaba el caramelo!";
                
                // Reactivar el botón
                botonPedirCaramelo.disabled = false;
            }, 3000);
        })
        .catch((mensaje) => {
            // Si mamá dijo que no
            mensajeElement.textContent = mensaje;
            
            // Reactivar el botón después de un tiempo
            setTimeout(() => {
                botonPedirCaramelo.disabled = false;
                mensajeElement.textContent = "Puedes intentarlo más tarde";
            }, 3000);
        });
});

// Mensaje inicial
mensajeElement.textContent = "¿Quieres un caramelo? ¡Pídeselo a mamá!";