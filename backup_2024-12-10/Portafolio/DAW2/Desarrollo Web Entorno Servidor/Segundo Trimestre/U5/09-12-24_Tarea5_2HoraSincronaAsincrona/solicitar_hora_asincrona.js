// Función para obtener la hora del servidor de forma asíncrona.
function obtenerHoraAsincrona() {
    // Crear una nueva solicitud XMLHttpRequest.
    var solicitud = new XMLHttpRequest();

    // Configurar la solicitud para obtener los datos de forma asíncrona.
    solicitud.open("GET", "hora_servidor.php", true);

    // Manejar los cambios de estado de la solicitud.
    solicitud.onreadystatechange = function () {
        // Verificar si la solicitud está completa (estado 4) y si fue exitosa (código 200).
        if (solicitud.readyState === 4 && solicitud.status === 200) {
            // Actualizar el contenido del div con la hora recibida del servidor.
            document.getElementById("hora").innerHTML = solicitud.responseText;
        } else if (solicitud.readyState === 4) {
            // Si hubo un error, mostrar un mensaje en el div.
            document.getElementById("hora").innerHTML = "Error al obtener la hora";
        }
    };

    // Enviar la solicitud al servidor.
    solicitud.send();
}

// Actualizar la hora del servidor cada 2 segundos.
setInterval(obtenerHoraAsincrona, 2000);