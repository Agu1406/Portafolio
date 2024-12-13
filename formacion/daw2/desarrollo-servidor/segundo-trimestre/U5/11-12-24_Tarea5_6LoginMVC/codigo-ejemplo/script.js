document.getElementById('login-form').addEventListener('submit', function(event) {
    event.preventDefault(); // Evita el envío normal del formulario

    // Obtener el formulario y los datos
    const form = document.getElementById('login-form');
    const usuario = form.usuario.value;
    const contrasena = form.contrasena.value;

    // Crear un objeto JSON con los datos
    const datos = JSON.stringify({
        usuario: usuario,
        contrasena: contrasena
    });

    // Crear una nueva instancia de XMLHttpRequest
    const xhr = new XMLHttpRequest();
    xhr.open('POST', '../servidor.php', true); // Configurar la solicitud POST
    xhr.setRequestHeader('Content-Type', 'application/json'); // Establecer el tipo de contenido

    // Manejar el cambio de estado de la solicitud
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4 && xhr.status === 200) {
            // Procesar la respuesta del servidor
            const responseText = xhr.responseText;
            imprimeMensajes(responseText);
        }
    };

    // Enviar la solicitud con los datos
    xhr.send(datos);
});

function imprimeMensajes(data) {
    const mensajeContainer = document.createElement('div');
    mensajeContainer.className = 'mensaje-container';
    
    if (data.includes('Login exitoso')) {
        mensajeContainer.textContent = 'Inicio de sesión exitoso';
        // Aquí puedes redirigir o realizar otra acción
    } else {
        mensajeContainer.textContent = 'Usuario o contraseña incorrectos';
    }

    document.body.appendChild(mensajeContainer); // Agrega el mensaje al cuerpo del HTML
}
