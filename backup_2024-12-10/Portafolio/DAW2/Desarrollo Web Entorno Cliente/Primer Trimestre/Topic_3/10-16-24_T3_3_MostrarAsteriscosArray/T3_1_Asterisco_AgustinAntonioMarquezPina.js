// Función que genera un array con 20 números aleatorios entre 1 y 50 y los muestra con asteriscos
function generarArray() {
    const array = [];
    const resultadoDiv = document.getElementById("resultado");
    resultadoDiv.innerHTML = ""; // Limpia el contenido previo

    // Genera el array con 20 números aleatorios entre 1 y 50
    for (let i = 0; i < 20; i++) {
        const numeroAleatorio = Math.floor(Math.random() * 50) + 1;
        array.push(numeroAleatorio);
    }

    // Recorre el array y muestra el número con tantos asteriscos como su valor
    array.forEach((numero, index) => {
        let asteriscos = "*".repeat(numero); // Genera los asteriscos
        resultadoDiv.innerHTML += `Posición ${index + 1}: ${numero} ${asteriscos}<br>`;
    });
}
