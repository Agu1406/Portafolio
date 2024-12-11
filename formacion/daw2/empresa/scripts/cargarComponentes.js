let publicaciones = []; // Array para almacenar publicaciones

function cargarComponente(url, elemento) {
    fetch(url)
        .then(response => {
            if (!response.ok) {
                throw new Error(`Error al cargar ${url}: ${response.statusText}`);
            }
            return response.text();
        })
        .then(data => {
            document.querySelector(elemento).innerHTML = data;
        })
        .catch(error => console.error('Error al cargar el componente:', error));
}

document.addEventListener('DOMContentLoaded', async function() {
    const headerElement = document.querySelector('header');
    const footerElement = document.querySelector('footer');

    if (!headerElement || !footerElement) {
        console.error('No se encontraron los elementos header o footer');
        return; // Salir si no se encuentran los elementos
    }

    cargarComponente('./componentes/header.html', 'header');
    cargarComponente('./componentes/footer.html', 'footer');

    // Agregar evento al botón de añadir post
    const btnAgregarPost = document.getElementById('btnAgregarPost');
    btnAgregarPost.addEventListener('click', function(e) {
        e.preventDefault(); // Prevenir el comportamiento por defecto del formulario
        const textarea = document.querySelector('.publicacion__entrada');
        const contenido = textarea.value;
        const imagenInput = document.getElementById('imagenReceta');
        const imagenFile = imagenInput.files[0]; // Obtener el archivo de imagen

        if (contenido && imagenFile) {
            const reader = new FileReader();
            reader.onload = function(event) {
                // Crear un nuevo post
                const nuevaPublicacion = {
                    usuario: 'Usuario', // Cambiar por el nombre del usuario real
                    fecha: new Date().toLocaleString(),
                    contenido: contenido,
                    imagen: event.target.result // Guardar la imagen en base64
                };
                publicaciones.push(nuevaPublicacion); // Agregar la nueva publicación al array
                textarea.value = ''; // Limpiar el textarea
                imagenInput.value = ''; // Limpiar el input de imagen
                mostrarPublicaciones(); // Mostrar las publicaciones
            };
            reader.readAsDataURL(imagenFile); // Leer la imagen como URL de datos
        } else {
            alert('Por favor, escribe algo y selecciona una imagen para compartir.');
        }
    });
});

// Función para mostrar las publicaciones
function mostrarPublicaciones() {
    const contenedorPublicaciones = document.querySelector('.publicacion'); // Cambiar por el contenedor correcto
    contenedorPublicaciones.innerHTML = ''; // Limpiar el contenedor

    publicaciones.forEach(pub => {
        const publicacionHTML = `
            <div class="publicacion__cabecera">
                <div class="publicacion__usuario">${pub.usuario}</div>
                <div class="publicacion__fecha">${pub.fecha}</div>
            </div>
            <p class="publicacion__contenido">${pub.contenido}</p>
            <img src="${pub.imagen}" alt="Receta" class="publicacion__imagen" /> <!-- Mostrar la imagen -->
        `;
        contenedorPublicaciones.innerHTML += publicacionHTML; // Agregar la nueva publicación al contenedor
    });
} 