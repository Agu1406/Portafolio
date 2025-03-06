// Obtenemos los botones por ID
const btnIniciar = document.getElementById("btnIniciar");
const btnDetener = document.getElementById("btnDetener");

// Variable para guardar el intervalo
let intervalo;

// Función para iniciar el timer
function iniciarTimer() {
    intervalo = setInterval(() => {
        console.log("Timer en curso");
    }, 1000);
}


// Asignamos los eventos a los botones
btnIniciar.addEventListener("click", iniciarTimer);
btnDetener.addEventListener("click", () => clearInterval(intervalo));