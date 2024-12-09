// Array con todas las páginas del sitio
const paginas = [
    { url: '../../index.html', nombre: 'Inicio' },
    { url: 'Empresa Iniciativa Emprendedora/recetas.html', nombre: 'Recetas' },
    { url: 'Empresa Iniciativa Emprendedora/mi_cocina.html', nombre: 'Mi Cocina' },
    { url: 'Empresa Iniciativa Emprendedora/perfil.html', nombre: 'Mi Perfil' },
    { url: 'Empresa Iniciativa Emprendedora/sobre_nosotros.html', nombre: 'Sobre Nosotros' },
    { url: 'Empresa Iniciativa Emprendedora/contacto.html', nombre: 'Contacto' }
];

function generarMenu() {
    const paginaActual = window.location.pathname.split('/').pop() || 'index.html';
    return `
        <nav>
            ${paginas.map(pagina => `
                <a href="${pagina.url}" 
                   class="${paginaActual === pagina.url.split('/').pop() ? 'activo' : ''}">
                   ${pagina.nombre}
                </a>
            `).join('')}
        </nav>
    `;
}

function cargarComponente(url, elemento) {
    fetch(url)
        .then(response => response.text())
        .then(data => {
            document.querySelector(elemento).innerHTML = data;
            if (elemento === 'header' || elemento === 'footer') {
                const menuContainer = document.querySelector(
                    elemento === 'header' ? '#menu-principal' : '#menu-footer'
                );
                if (menuContainer) {
                    menuContainer.innerHTML = generarMenu();
                }
            }
        })
        .catch(error => console.error('Error al cargar el componente:', error));
}

document.addEventListener('DOMContentLoaded', function() {
    cargarComponente('Empresa Iniciativa Emprendedora/componentes/header.html', 'header');
    cargarComponente('Empresa Iniciativa Emprendedora/componentes/footer.html', 'footer');
}); 