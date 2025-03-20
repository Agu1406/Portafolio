/**
 * Script principal para Manos a la Olla
 * Inicializa componentes y funcionalidades generales
 */

document.addEventListener('DOMContentLoaded', function() {
    // Inicializar componentes de Bootstrap
    initBootstrapComponents();
    
    // Inicializar validación de formularios
    initFormValidation();
    
    // Inicializar funcionalidades específicas
    initRecipeFormats();
    initNotifications();
    initImagePreviews();
    
    // Inicializar tooltips y popovers
    initTooltips();
});

/**
 * Inicializa componentes de Bootstrap
 */
function initBootstrapComponents() {
    // Inicializar todos los tooltips
    const tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'));
    tooltipTriggerList.map(function (tooltipTriggerEl) {
        return new bootstrap.Tooltip(tooltipTriggerEl);
    });
    
    // Inicializar todos los popovers
    const popoverTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="popover"]'));
    popoverTriggerList.map(function (popoverTriggerEl) {
        return new bootstrap.Popover(popoverTriggerEl);
    });
    
    // Inicializar todos los toasts
    const toastElList = [].slice.call(document.querySelectorAll('.toast'));
    toastElList.map(function (toastEl) {
        return new bootstrap.Toast(toastEl);
    });
}

/**
 * Inicializa la validación de formularios
 */
function initFormValidation() {
    // Obtener todos los formularios con la clase needs-validation
    const forms = document.querySelectorAll('.needs-validation');
    
    // Iterar sobre ellos y prevenir el envío si no son válidos
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

/**
 * Inicializa el formateo de recetas (ingredientes e instrucciones)
 */
function initRecipeFormats() {
    // Formatear ingredientes
    const ingredientsContainers = document.querySelectorAll('.recipe-ingredients');
    ingredientsContainers.forEach(container => {
        const rawText = container.textContent.trim();
        if (rawText && window.accessibilityTools) {
            container.innerHTML = window.accessibilityTools.formatIngredientsList(rawText);
        }
    });
    
    // Formatear instrucciones
    const instructionsContainers = document.querySelectorAll('.recipe-instructions');
    instructionsContainers.forEach(container => {
        const rawText = container.textContent.trim();
        if (rawText && window.accessibilityTools) {
            container.innerHTML = window.accessibilityTools.formatInstructionsList(rawText);
        }
    });
}

/**
 * Inicializa las notificaciones
 */
function initNotifications() {
    // Mostrar toasts de notificación
    const toasts = document.querySelectorAll('.toast');
    toasts.forEach(toast => {
        const bsToast = new bootstrap.Toast(toast);
        bsToast.show();
    });
    
    // Marcar notificaciones como leídas al hacer clic
    const notificationLinks = document.querySelectorAll('.notification-link');
    notificationLinks.forEach(link => {
        link.addEventListener('click', function(e) {
            const notificationId = this.getAttribute('data-notification-id');
            if (notificationId) {
                markNotificationAsRead(notificationId);
            }
        });
    });
}

/**
 * Marca una notificación como leída
 * @param {string} id - ID de la notificación
 */
function markNotificationAsRead(id) {
    // Enviar solicitud AJAX para marcar como leída
    fetch(`/notifications/mark-read/${id}`, {
        method: 'POST',
        headers: {
            'X-Requested-With': 'XMLHttpRequest'
        }
    })
    .then(response => response.json())
    .then(data => {
        if (data.success) {
            // Actualizar contador de notificaciones
            const counter = document.querySelector('.notification-counter');
            if (counter) {
                const count = parseInt(counter.textContent) - 1;
                counter.textContent = count > 0 ? count : '';
                if (count <= 0) {
                    counter.style.display = 'none';
                }
            }
            
            // Ocultar la notificación en la lista
            const notificationItem = document.querySelector(`.notification-item[data-notification-id="${id}"]`);
            if (notificationItem) {
                notificationItem.classList.add('read');
            }
        }
    })
    .catch(error => {
        console.error('Error al marcar notificación como leída:', error);
    });
}

/**
 * Inicializa las previsualizaciones de imágenes
 */
function initImagePreviews() {
    const imageInputs = document.querySelectorAll('.image-upload');
    
    imageInputs.forEach(input => {
        input.addEventListener('change', function() {
            const previewContainer = document.querySelector(this.getAttribute('data-preview'));
            
            if (previewContainer && this.files && this.files[0]) {
                const reader = new FileReader();
                
                reader.onload = function(e) {
                    previewContainer.src = e.target.result;
                    previewContainer.style.display = 'block';
                };
                
                reader.readAsDataURL(this.files[0]);
            }
        });
    });
}

/**
 * Inicializa tooltips personalizados
 */
function initTooltips() {
    // Inicializar tooltips de Bootstrap
    const tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'));
    tooltipTriggerList.map(function (tooltipTriggerEl) {
        return new bootstrap.Tooltip(tooltipTriggerEl);
    });
}

/**
 * Confirma una acción con un modal
 * @param {string} message - Mensaje de confirmación
 * @param {Function} callback - Función a ejecutar si se confirma
 */
function confirmAction(message, callback) {
    const confirmModal = document.getElementById('confirmModal');
    
    if (confirmModal) {
        const modalBody = confirmModal.querySelector('.modal-body');
        modalBody.textContent = message;
        
        const confirmButton = confirmModal.querySelector('.btn-confirm');
        
        // Eliminar listeners anteriores
        const newConfirmButton = confirmButton.cloneNode(true);
        confirmButton.parentNode.replaceChild(newConfirmButton, confirmButton);
        
        // Agregar nuevo listener
        newConfirmButton.addEventListener('click', function() {
            const modal = bootstrap.Modal.getInstance(confirmModal);
            modal.hide();
            callback();
        });
        
        // Mostrar modal
        const modal = new bootstrap.Modal(confirmModal);
        modal.show();
    } else {
        // Fallback si no hay modal
        if (confirm(message)) {
            callback();
        }
    }
}

// Exponer funciones para uso global
window.appTools = {
    confirmAction
}; 