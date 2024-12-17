let publicaciones = [
    {
        usuario: 'María García',
        fecha: 'Hace 2 horas',
        contenido: '¡Hoy preparé la receta de mi abuela! Estas empanadas gallegas quedaron deliciosas. La clave está en el sofrito del relleno. ¿Alguien quiere la receta?',
        imagen: 'https://images.unsplash.com/photo-1628191010210-a59de33e5941'
    },
    {
        usuario: 'Juan Pérez',
        fecha: 'Hace 5 horas',
        contenido: 'Paella dominical con la familia. Una tradición que mantenemos desde hace 30 años. El secreto está en el caldo casero y el arroz bomba.',
        imagen: 'https://images.unsplash.com/photo-1534080564583-6be75777b70a'
    },
    {
        usuario: 'Carmen Martínez',
        fecha: 'Hace 1 día',
        contenido: 'Mi primera vez haciendo pan casero. ¡No puedo creer lo bien que ha salido! Gracias a todos por los consejos en el grupo.',
        imagen: 'https://images.unsplash.com/photo-1549931319-a545dcf3bc73'
    }
]; // Ejemplo de publicaciones

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
    const contenedorPublicaciones = document.getElementById('contenedorPublicaciones'); // Cambiar por el contenedor correcto
    contenedorPublicaciones.innerHTML = ''; // Limpiar el contenedor

    publicaciones.forEach(pub => {
        const publicacionHTML = `
            <div class="publicacion__cabecera">
                <div class="publicacion__usuario">${pub.usuario}</div>
                <div class="publicacion__fecha">${pub.fecha}</div>
            </div>
            <p class="publicacion__contenido">${pub.contenido}</p>
            <img src="${pub.imagen}" alt="Receta" class="publicacion__imagen" />
            <div class="publicacion__acciones">
                <button class="publicacion__boton" onclick="meGusta('${pub.usuario}')">
                    <span>👍</span> Me gusta
                </button>
                <button class="publicacion__boton" onclick="comentar('${pub.usuario}')">
                    <span>💬</span> Comentar
                </button>
            </div>
        `;
        contenedorPublicaciones.innerHTML += publicacionHTML; // Agregar la nueva publicación al contenedor
    });
}

// Función para manejar "Me gusta"
function meGusta(usuario) {
    alert(`Te gusta la publicación de ${usuario}`);
}

// Función para manejar comentarios
function comentar(usuario) {
    const comentario = prompt(`Escribe tu comentario para ${usuario}:`);
    if (comentario) {
        alert(`Comentario enviado a ${usuario}: ${comentario}`);
    }
}

// Llamar a la función para mostrar las publicaciones al cargar la página
document.addEventListener('DOMContentLoaded', mostrarPublicaciones); 