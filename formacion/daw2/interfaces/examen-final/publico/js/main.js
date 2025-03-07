/**
 * Funcionalidad JavaScript para NaturalShop
 */
document.addEventListener('DOMContentLoaded', function() {
    // Inicializar tooltips de Bootstrap
    var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'));
    var tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
        return new bootstrap.Tooltip(tooltipTriggerEl);
    });
    
    // Inicializar popovers de Bootstrap
    var popoverTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="popover"]'));
    var popoverList = popoverTriggerList.map(function (popoverTriggerEl) {
        return new bootstrap.Popover(popoverTriggerEl);
    });
    
    // Manejar cambio de cantidad en el carrito
    const cantidadInputs = document.querySelectorAll('.carrito-cantidad');
    if (cantidadInputs.length > 0) {
        cantidadInputs.forEach(input => {
            input.addEventListener('change', function() {
                const form = this.closest('form');
                if (form) {
                    form.submit();
                }
            });
        });
    }
    
    // Manejar miniaturas en detalle de producto
    const miniaturas = document.querySelectorAll('.producto-miniatura');
    const imagenPrincipal = document.querySelector('.producto-imagen-principal img');
    
    if (miniaturas.length > 0 && imagenPrincipal) {
        miniaturas.forEach(miniatura => {
            miniatura.addEventListener('click', function() {
                // Quitar clase active de todas las miniaturas
                miniaturas.forEach(m => m.classList.remove('active'));
                
                // Añadir clase active a la miniatura clickeada
                this.classList.add('active');
                
                // Cambiar la imagen principal
                const nuevaImagen = this.querySelector('img').getAttribute('src');
                imagenPrincipal.setAttribute('src', nuevaImagen);
            });
        });
    }
    
    // Validación de formularios
    const forms = document.querySelectorAll('.needs-validation');
    
    if (forms.length > 0) {
        Array.from(forms).forEach(form => {
            form.addEventListener('submit', event => {
                if (!form.checkValidity()) {
                    event.preventDefault();
                    event.stopPropagation();
                }
                
                form.classList.add('was-validated');
            }, false);
        });
    }
    
    // Contador de caracteres para textarea
    const textareas = document.querySelectorAll('textarea[maxlength]');
    
    if (textareas.length > 0) {
        textareas.forEach(textarea => {
            const maxLength = textarea.getAttribute('maxlength');
            const contador = document.createElement('div');
            contador.className = 'form-text text-muted contador-caracteres';
            contador.innerHTML = `0/${maxLength} caracteres`;
            
            textarea.parentNode.insertBefore(contador, textarea.nextSibling);
            
            textarea.addEventListener('input', function() {
                const caracteresActuales = this.value.length;
                contador.innerHTML = `${caracteresActuales}/${maxLength} caracteres`;
            });
        });
    }
    
    // Animación de elementos al hacer scroll
    const animarElementos = () => {
        const elementos = document.querySelectorAll('.animar-entrada');
        
        elementos.forEach(elemento => {
            const posicion = elemento.getBoundingClientRect().top;
            const alturaVentana = window.innerHeight;
            
            if (posicion < alturaVentana * 0.9) {
                elemento.classList.add('animado');
            }
        });
    };
    
    // Ejecutar animación al cargar la página
    animarElementos();
    
    // Ejecutar animación al hacer scroll
    window.addEventListener('scroll', animarElementos);
}); 