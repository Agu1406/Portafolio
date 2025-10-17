// Array de publicaciones
window.publicaciones = [
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
];

// Función para cargar componentes HTML
function cargarComponente(url, elemento) {
    return new Promise((resolve, reject) => {
        const xhr = new XMLHttpRequest();
        xhr.open('GET', `./componentes/${url}`, true);
        
        xhr.onload = function() {
            if (xhr.status >= 200 && xhr.status < 300) {
                const elementoDestino = document.querySelector(elemento);
                if (elementoDestino) {
                    elementoDestino.innerHTML = xhr.responseText;
                    resolve();
                } else {
                    console.error(`No se encontró el elemento ${elemento}`);
                    reject(new Error(`No se encontró el elemento ${elemento}`));
                }
            } else {
                reject(new Error(`Error HTTP: ${xhr.status}`));
            }
        };

        xhr.onerror = function() {
            const elementoDestino = document.querySelector(elemento);
            const error = new Error('Error al cargar el componente');
            console.error(error);
            if (elementoDestino) {
                elementoDestino.innerHTML = `<div class="error">Error al cargar el componente. Por favor, asegúrate de acceder a través de un servidor web (http://localhost/...).</div>`;
            }
            reject(error);
        };

        xhr.send();
    });
}

// Función para mostrar las publicaciones (hacerla global)
window.mostrarPublicaciones = function() {
    const contenedor = document.getElementById('contenedorPublicaciones');
    if (!contenedor) return;

    contenedor.innerHTML = '';
    window.publicaciones.forEach(pub => {
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
        contenedor.innerHTML += publicacionHTML;
    });
};

// Función para manejar "Me gusta" (hacerla global)
window.meGusta = function(usuario) {
    alert(`Te gusta la publicación de ${usuario}`);
};

// Función para manejar comentarios (hacerla global)
window.comentar = function(usuario) {
    const comentario = prompt(`Escribe tu comentario para ${usuario}:`);
    if (comentario) {
        alert(`Comentario enviado a ${usuario}: ${comentario}`);
    }
};

// Inicialización cuando el DOM está listo
document.addEventListener('DOMContentLoaded', async () => {
    try {
        // Primero cargar los componentes
        await cargarComponente('header.html', 'header');
        await cargarComponente('footer.html', 'footer');

        // Luego inicializar las funcionalidades
        window.mostrarPublicaciones();

        // Configurar el formulario de publicación
        const btnAgregarPost = document.getElementById('btnAgregarPost');
        if (btnAgregarPost) {
            btnAgregarPost.addEventListener('click', function(e) {
                e.preventDefault();
                const textarea = document.querySelector('.publicacion__entrada');
                const imagenInput = document.getElementById('imagenReceta');
                const contenido = textarea?.value;
                const imagenFile = imagenInput?.files[0];

                if (contenido && imagenFile) {
                    const reader = new FileReader();
                    reader.onload = function(event) {
                        const nuevaPublicacion = {
                            usuario: 'Usuario',
                            fecha: new Date().toLocaleString(),
                            contenido: contenido,
                            imagen: event.target.result
                        };
                        window.publicaciones.unshift(nuevaPublicacion);
                        textarea.value = '';
                        imagenInput.value = '';
                        window.mostrarPublicaciones();
                    };
                    reader.readAsDataURL(imagenFile);
                } else {
                    alert('Por favor, escribe algo y selecciona una imagen para compartir.');
                }
            });
        }
    } catch (error) {
        console.error('Error durante la inicialización:', error);
    }
}); 