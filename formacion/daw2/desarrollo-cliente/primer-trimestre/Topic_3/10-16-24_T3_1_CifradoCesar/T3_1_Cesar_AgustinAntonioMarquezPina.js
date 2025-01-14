// Función que cifra un texto con el cifrado César desplazando 3 posiciones
function cifrarTexto() {
    const texto = document.getElementById("texto").value; // Obtenemos el texto del input
    const desplazamiento = document.getElementById("desplazamiento").value; // Número de posiciones a desplazar
    let textoNormalizado = ""; // Variable para almacenar el texto normalizado
    let textoCifrado = ""; // Variable para almacenar el resultado cifrado
    const abecedarioMinuscula = "abcdefghijklmnopqrstuvwxyz"; // 26 letras (sin ñ)
    const abecedarioMayuscula = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; // 26 letras (sin ñ)

    // Normalizamos el texto (elimina tildes y acentos)
    textoNormalizado = texto.normalize("NFD").replace(/[\u0300-\u036f]/g, "");

    // Iteramos sobre cada letra del texto normalizado
    for (const letra of textoNormalizado) {
        // Si es una letra mayúscula
        if (/[A-Z]/.test(letra)) {
            const nuevaLetra = desplazarLetra(letra, abecedarioMayuscula, desplazamiento);
            textoCifrado += nuevaLetra; // Añade la letra cifrada al resultado
        } 
        // Si es una letra minúscula
        else if (/[a-z]/.test(letra)) {
            const nuevaLetra = desplazarLetra(letra, abecedarioMinuscula, desplazamiento);
            textoCifrado += nuevaLetra; // Añade la letra cifrada al resultado
        } 
        // Si no es una letra (espacio, signo, etc.), la añade tal cual
        else {
            textoCifrado += letra;
        }
    }

    // Mostrar el texto cifrado en la página usando innerHTML
    document.getElementById("resultado").innerHTML = `
        <p><strong>Texto cifrado:</strong> ${textoCifrado}</p>
        <p><strong>Texto original:</strong> ${texto}</p>
    `;
}

// Función que desplaza una letra dentro del abecedario dado
function desplazarLetra(letra, abecedario, desplazamiento) {
    const posicionActual = abecedario.indexOf(letra); // Encuentra la posición actual de la letra
    const nuevaPosicion = (posicionActual + desplazamiento) % abecedario.length; // Desplaza y usa módulo para evitar desbordes
    
    return abecedario[nuevaPosicion]; // Retorna la nueva letra desplazada
}
