/**
 * Ejemplo de promesa con setTimeout para niños
 * Simula pedir una pizza y jugar mientras esperas
 */

// Elementos del DOM
const botonPedirPizza = document.getElementById('pedir-pizza');
const mensajeElement = document.getElementById('mensaje');
const pizzaElement = document.getElementById('pizza');
const jugueteElement = document.getElementById('juguete');

// Función que crea una promesa para pedir una pizza
function pedirPizza() {
    // Mostrar mensaje de espera
    mensajeElement.textContent = "¡Pizza pedida! Llegará en 5 segundos...";
    mensajeElement.className = "mensaje espera";
    pizzaElement.style.display = "none";
    
    // Crear y devolver una promesa
    return new Promise((resolve, reject) => {
        // La pizza llegará después de 5 segundos
        setTimeout(() => {
            // Hay un 90% de probabilidad de que la pizza llegue bien
            const pizzaOk = Math.random() < 0.9;
            
            if (pizzaOk) {
                // Si todo va bien, la pizza llega (resuelve la promesa)
                resolve("¡La pizza ha llegado! ¡A comer!");
            } else {
                // Si algo sale mal (rechaza la promesa)
                reject("Oh no, se equivocaron de dirección");
            }
        }, 5000); // 5 segundos
    });
}

// Función para jugar mientras esperamos
function jugar() {
    jugueteElement.style.display = "block";
    
    // Mensaje de que estamos jugando
    setTimeout(() => {
        mensajeElement.textContent = "Jugando mientras esperamos la pizza...";
    }, 1000);
    
    // Otro mensaje después de un rato
    setTimeout(() => {
        mensajeElement.textContent = "¡Qué divertido es jugar! La pizza llegará pronto...";
    }, 3000);
}

// Evento para el botón de pedir pizza
botonPedirPizza.addEventListener('click', () => {
    // Desactivar el botón para evitar múltiples pedidos
    botonPedirPizza.disabled = true;
    
    // Llamamos a la función que devuelve la promesa
    pedirPizza()
        .then((mensaje) => {
            // Esto se ejecuta cuando la pizza llega
            mensajeElement.textContent = mensaje;
            mensajeElement.className = "mensaje exito";
            pizzaElement.style.display = "block";
            jugueteElement.style.display = "none";
            console.log("¡Pizza deliciosa!");
            
            // Reactivar el botón después de recibir la pizza
            botonPedirPizza.disabled = false;
        })
        .catch((error) => {
            // Esto se ejecuta si hay algún problema con la pizza
            mensajeElement.textContent = error;
            mensajeElement.className = "mensaje error";
            jugueteElement.style.display = "none";
            console.log("Problema con la pizza :(");
            
            // Reactivar el botón después del error
            botonPedirPizza.disabled = false;
        });
    
    // Mientras esperamos la pizza, podemos jugar
    jugar();
});

// Mensaje inicial
mensajeElement.textContent = "¿Tienes hambre? ¡Pide una pizza!";
mensajeElement.className = "mensaje"; 