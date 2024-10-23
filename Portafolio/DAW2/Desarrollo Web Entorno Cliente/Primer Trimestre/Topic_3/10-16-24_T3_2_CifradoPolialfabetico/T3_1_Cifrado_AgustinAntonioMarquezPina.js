// Función que cifra un texto con el cifrado Polialfabético (Vigenère)
function cifrarTexto() {
    const texto = document.getElementById("texto").value; // Obtenemos el texto del input
    const clave = document.getElementById("clave").value; // Obtenemos la clave del input
    let textoNormalizado = ""; // Variable para almacenar el texto normalizado
    let textoCifrado = ""; // Variable para almacenar el resultado cifrado
    const abecedario = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; // Alfabeto sin ñ (para simplicidad)

    // Normalizamos el texto y la clave (elimina tildes y acentos)
    textoNormalizado = texto.normalize("NFD").replace(/[\u0300-\u036f]/g, "").toUpperCase();
    const claveNormalizada = clave.normalize("NFD").replace(/[\u0300-\u036f]/g, "").toUpperCase();

    // Comprobamos que la clave solo contenga letras
    if (!/^[A-Z]+$/.test(claveNormalizada)) {
        alert("La clave solo debe contener letras.");
        return;
    }

    let j = 0; // Índice para recorrer la clave

    // Iteramos sobre cada letra del texto normalizado
    for (const letra of textoNormalizado) {
        // Si es una letra del abecedario
        if (/[A-Z]/.test(letra)) {
            const desplazamiento = abecedario.indexOf(claveNormalizada[j % claveNormalizada.length]); // Desplazamiento según la letra de la clave
            const nuevaLetra = desplazarLetra(letra, abecedario, desplazamiento); // Ciframos la letra
            textoCifrado += nuevaLetra; // Añade la letra cifrada al resultado
            j++; // Avanzamos al siguiente carácter de la clave
        } else {
            // Si no es una letra, la añadimos tal cual
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
