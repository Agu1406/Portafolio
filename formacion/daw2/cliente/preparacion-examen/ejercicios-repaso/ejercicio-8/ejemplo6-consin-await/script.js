/**
 * No utilizar variables del tipo var (var = numero), están obsoletas.
 */

// Función que retorna una promesa y espera el tiempo indicado
function esperar(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
}

// Función que muestra el nombre del navegador después de 3 segundos
async function mostrarNavegador() {
    console.log("Esperando 3 segundos para mostrar el navegador...");
    await esperar(3000);
    document.write(`Estás usando: ${navigator.userAgent} <br>`);
    console.log("Nombre del navegador mostrado.");
}

// Función que pregunta si abrir Google o ir a DuckDuckGo después de 5 segundos
async function preguntarDestino() {
    console.log("Esperando 5 segundos antes de preguntar...");
    await esperar(5000);

    let respuesta = confirm("¿Abrir Google en una nueva pestaña?");

    if (respuesta) {
        window.open("https://www.google.es/", "_blank"); // Abre en una nueva pestaña
        console.log("Se abrió Google en una nueva pestaña.");
    } else {
        window.location.href = "https://duckduckgo.com/"; // Redirige en la misma pestaña
        console.log("Se redirige a DuckDuckGo.");
    }
}

// Función principal que ejecuta ambas funciones en orden
async function iniciarProceso() {
    console.log("Iniciando proceso...");
    await mostrarNavegador();
    await preguntarDestino();
}

// Ejecutar la función principal
iniciarProceso();