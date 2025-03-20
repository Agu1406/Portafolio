document.addEventListener('DOMContentLoaded', async function() {
    try {
        // Obtener los elementos header y footer
        const headerElement = document.querySelector('header');
        const footerElement = document.querySelector('footer');

        if (!headerElement || !footerElement) {
            throw new Error('No se encontraron los elementos header o footer');
        }

        // Cargar header
        const headerResponse = await fetch('./componentes/header.html');
        if (!headerResponse.ok) throw new Error('Error al cargar el header');
        const headerContent = await headerResponse.text();
        headerElement.innerHTML = headerContent;

        // Cargar footer
        const footerResponse = await fetch('./componentes/footer.html');
        if (!footerResponse.ok) throw new Error('Error al cargar el footer');
        const footerContent = await footerResponse.text();
        footerElement.innerHTML = footerContent;

        // Marcar el enlace activo en la navegación
        const currentPage = window.location.pathname.split('/').pop().replace('.html', '');
        const navLinks = document.querySelectorAll('.navegacion__enlace');
        
        navLinks.forEach(link => {
            const linkPage = link.getAttribute('href').replace('.html', '').replace('./', '');
            if (currentPage === linkPage || 
                (currentPage === '' && linkPage === 'index') ||
                (currentPage === 'index' && linkPage === 'index')) {
                link.classList.add('navegacion__enlace--activo');
            }
        });

        console.log('Componentes cargados correctamente');
    } catch (error) {
        console.error('Error al cargar los componentes:', error);
    }
}); 