// Seleccionamos el elemento del input
const inputTexto = document.getElementById("posiblePalindromo");

// Agregamos un evento para verificar si es un palíndromo cuando el usuario escribe
inputTexto.addEventListener("input", function() {
    const texto = inputTexto.value; // Obtenemos el valor actual del input
    const esPalindromo = verificarPalindromo(texto);

    // Limpiamos cualquier mensaje anterior
    const mensajeExistente = document.getElementById("mensajePalindromo");
    if (mensajeExistente) {
        mensajeExistente.remove();
    }

    // Mostramos el mensaje de resultado
    const mensaje = document.createElement("p");
    mensaje.id = "mensajePalindromo";
    mensaje.textContent = esPalindromo ? "El texto es un palíndromo" : "El texto NO es un palíndromo";
    document.body.appendChild(mensaje);
});

// Función para verificar si una cadena es un palíndromo
function verificarPalindromo(cadena) {
    // Normalizamos la cadena (minúsculas, sin tildes, sin espacios y caracteres especiales)
    const textoNormalizado = cadena
        .toLowerCase()
        .normalize("NFD")
        .replace(/[\u0300-\u036f]/g, "")
        .replace(/[^a-z0-9]/g, "");

    // Verificamos si el texto es igual a su reverso
    const textoInvertido = textoNormalizado.split('').reverse().join('');
    return textoNormalizado === textoInvertido;
}
