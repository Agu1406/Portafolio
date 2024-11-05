// Creamos todas las variables que consideramos convenientes para usar en el programa.
let letrasValidasDNI = "TRWAGMYFPDXBNJZSQVHLCKE";

// Función para validar el DNI
function validarDNI(dni) {
    const numerosDelDNI = parseInt(dni.slice(0, 8), 10); // Tomamos los primeros 8 caracteres como números
    const letraDelDNI = dni.slice(-1).toUpperCase(); // Última letra del DNI
    const letraCorrecta = letrasValidasDNI[numerosDelDNI % 23]; // Calculamos la letra correcta
    return letraDelDNI === letraCorrecta; // Retornamos true o false
}

// Función para extraer el servidor del correo
function extraerServidorCorreo(correo) {
    const posicion1 = correo.indexOf("@") + 1; // Posición después de "@"
    const posicion2 = correo.lastIndexOf("."); // Posición del último "."
    return correo.slice(posicion1, posicion2); // Retornamos el servidor
}

// Función para generar el nombre de usuario
function generarNombreUsuario(nombre, apellido) {
    return nombre.charAt(0) + "_" + apellido.slice(-3); // Primera letra del nombre + tres últimas letras del apellido
}

// Agregar evento de envío del formulario
document.querySelector('.contenedorFormulario').addEventListener('submit', function(event) {
    event.preventDefault(); // Prevenir el envío del formulario

    // Extraemos del formulario toda la información utilizando "getElementById".
    const nombre = document.getElementById("nombre").value.trim(); // Quitamos espacios al inicio y al final
    const apellido = document.getElementById("apellido").value.trim();
    const dni = document.getElementById("DNI").value.trim();
    const movil = document.getElementById("movil").value.trim();
    const correo = document.getElementById("correo").value.trim();
    const codPostal = document.getElementById("codPostal").value.trim();

    // Creamos la estructura de datos
    const estructuraDeDatos = {
        nombre: nombre,
        apellido: apellido,
        dni: dni,
        movil: movil,
        correo: correo,
        codPostal: codPostal,
        dniValido: validarDNI(dni), // Validación del DNI
        servidor: extraerServidorCorreo(correo), // Extracción del servidor
        username: generarNombreUsuario(nombre, apellido), // Generación del nombre de usuario
        pais: "🇪🇸" // Bandera de España (puedes cambiar esto a la bandera que desees)
    };

    // Mostramos el resultado
    mostrarResultado(estructuraDeDatos);
});

// Función para mostrar los resultados
function mostrarResultado(datos) {
    const resultadoDiv = document.getElementById("resultado");
    resultadoDiv.innerHTML = `<p>Nombre de usuario: ${datos.username} ${datos.pais}</p>`;
    
    // Comprobamos si la inicial del nombre y los dos primeros caracteres del apellido forman "oso"
    const nombreValido = datos.username.charAt(0).toLowerCase() + datos.apellido.slice(0, 2).toLowerCase();
    if (nombreValido === "oso") {
        alert(`¡Cuidado, hay 1 oso cerca!`); // Mensaje de advertencia
    }
}
