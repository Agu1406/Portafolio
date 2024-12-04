// Función para obtener la hora del servidor de forma síncrona.
function obtenerHoraSincrona() {
    // Crear una nueva solicitud XMLHttpRequest.
    var solicitud = new XMLHttpRequest();

    // Configurar la solicitud para obtener los datos de forma síncrona.
    solicitud.open("GET", "hora_servidor.php", false);

    try {
        // Enviar la solicitud al servidor.
        solicitud.send();

        // Verificar si la respuesta fue exitosa.
        if (solicitud.status === 200) {
            // Actualizar el contenido del div con la hora recibida del servidor.
            document.getElementById("hora").innerHTML = solicitud.responseText;
        } else {
            // Si hubo un error, mostrar un mensaje en el div.
            document.getElementById("hora").innerHTML = "Error al obtener la hora";
        }
    } catch (e) {
        // Manejar errores en caso de que no se pueda completar la solicitud.
        document.getElementById("hora").innerHTML = "Error al realizar la solicitud.";
    }
}

// Llamar a la función cada 5 segundos (puedes descomentar si quieres usarlo con `setInterval`).
// setInterval(obtenerHoraSincrona, 5000);

// También puedes ejecutarla directamente.
obtenerHoraSincrona();