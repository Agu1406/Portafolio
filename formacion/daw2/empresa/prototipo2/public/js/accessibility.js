/**
 * Script para funcionalidades de accesibilidad
 * Incluye: texto a voz, cambio de tamaño de texto, modo de alto contraste
 */

document.addEventListener('DOMContentLoaded', function() {
    // Variables para el tamaño de fuente
    let currentFontSize = 100; // Porcentaje base
    const minFontSize = 80; // Mínimo 80%
    const maxFontSize = 150; // Máximo 150%
    const stepSize = 10; // Incrementos de 10%

    // Función para ajustar el tamaño de fuente
    function adjustFontSize(increase) {
        if (increase) {
            currentFontSize = Math.min(currentFontSize + stepSize, maxFontSize);
        } else {
            currentFontSize = Math.max(currentFontSize - stepSize, minFontSize);
        }
        document.body.style.fontSize = `${currentFontSize}%`;
        localStorage.setItem('fontSize', currentFontSize);
    }

    // Función para alternar el modo de alto contraste
    function toggleHighContrast() {
        document.body.classList.toggle('high-contrast');
        const isHighContrast = document.body.classList.contains('high-contrast');
        localStorage.setItem('highContrast', isHighContrast);
        
        // Actualizar texto del botón
        const contrastBtn = document.getElementById('contrastBtn');
        if (contrastBtn) {
            contrastBtn.textContent = isHighContrast ? 'Desactivar alto contraste' : 'Activar alto contraste';
        }
    }

    // Restaurar preferencias guardadas
    function restorePreferences() {
        // Restaurar tamaño de fuente
        const savedFontSize = localStorage.getItem('fontSize');
        if (savedFontSize) {
            currentFontSize = parseInt(savedFontSize);
            document.body.style.fontSize = `${currentFontSize}%`;
        }

        // Restaurar modo de contraste
        const savedHighContrast = localStorage.getItem('highContrast');
        if (savedHighContrast === 'true') {
            document.body.classList.add('high-contrast');
            const contrastBtn = document.getElementById('contrastBtn');
            if (contrastBtn) {
                contrastBtn.textContent = 'Desactivar alto contraste';
            }
        }
    }

    // Inicializar texto a voz
    function initTextToSpeech() {
        if ('speechSynthesis' in window) {
            const synth = window.speechSynthesis;
            let isReading = false;
            let currentUtterance = null;

            // Obtener voces en español
            function getSpanishVoice() {
                const voices = synth.getVoices();
                return voices.find(voice => voice.lang.startsWith('es')) || voices[0];
            }

            // Función para detener la lectura
            function stopReading() {
                if (isReading) {
                    synth.cancel();
                    isReading = false;
                    if (currentUtterance) {
                        const button = document.querySelector(`[data-target="${currentUtterance.dataset.target}"]`);
                        if (button) {
                            button.innerHTML = '<i class="fas fa-volume-up me-1"></i> Escuchar';
                            button.classList.remove('btn-danger');
                            button.classList.add('btn-outline-primary');
                        }
                    }
                    currentUtterance = null;
                }
            }

            // Manejar botones de texto a voz
            document.addEventListener('click', function(e) {
                if (e.target.closest('.tts-button')) {
                    const button = e.target.closest('.tts-button');
                    const targetId = button.getAttribute('data-target');
                    const targetElement = document.getElementById(targetId);

                    if (currentUtterance === button) {
                        stopReading();
                        return;
                    }

                    stopReading();

                    if (targetElement) {
                        const text = targetElement.textContent.trim();
                        const utterance = new SpeechSynthesisUtterance(text);
                        utterance.voice = getSpanishVoice();
                        utterance.lang = 'es-ES';
                        utterance.rate = 1;
                        utterance.pitch = 1;

                        utterance.onstart = () => {
                            isReading = true;
                            currentUtterance = button;
                            button.innerHTML = '<i class="fas fa-stop me-1"></i> Detener';
                            button.classList.remove('btn-outline-primary');
                            button.classList.add('btn-danger');
                        };

                        utterance.onend = () => {
                            stopReading();
                        };

                        synth.speak(utterance);
                    }
                }
            });

            // Detener la lectura al navegar
            window.addEventListener('beforeunload', stopReading);
        }
    }

    // Asignar eventos a los botones
    const increaseFontBtn = document.getElementById('increaseFontBtn');
    const decreaseFontBtn = document.getElementById('decreaseFontBtn');
    const contrastBtn = document.getElementById('contrastBtn');

    if (increaseFontBtn) {
        increaseFontBtn.addEventListener('click', () => adjustFontSize(true));
    }
    if (decreaseFontBtn) {
        decreaseFontBtn.addEventListener('click', () => adjustFontSize(false));
    }
    if (contrastBtn) {
        contrastBtn.addEventListener('click', toggleHighContrast);
    }

    // Restaurar preferencias al cargar la página
    restorePreferences();

    // Inicializar texto a voz
    initTextToSpeech();
});

/**
 * Inicializa los controles de tamaño de texto
 */
function initFontSizeControls() {
    const normalButton = document.getElementById('font-size-normal');
    const largeButton = document.getElementById('font-size-large');
    const largerButton = document.getElementById('font-size-larger');
    const largestButton = document.getElementById('font-size-largest');
    
    if (normalButton && largeButton && largerButton && largestButton) {
        // Restaurar tamaño normal
        normalButton.addEventListener('click', function() {
            document.body.classList.remove('font-size-large', 'font-size-larger', 'font-size-largest');
            saveAccessibilityPreference('fontSize', 'normal');
        });
        
        // Aplicar tamaño grande
        largeButton.addEventListener('click', function() {
            document.body.classList.remove('font-size-larger', 'font-size-largest');
            document.body.classList.add('font-size-large');
            saveAccessibilityPreference('fontSize', 'large');
        });
        
        // Aplicar tamaño más grande
        largerButton.addEventListener('click', function() {
            document.body.classList.remove('font-size-large', 'font-size-largest');
            document.body.classList.add('font-size-larger');
            saveAccessibilityPreference('fontSize', 'larger');
        });
        
        // Aplicar tamaño máximo
        largestButton.addEventListener('click', function() {
            document.body.classList.remove('font-size-large', 'font-size-larger');
            document.body.classList.add('font-size-largest');
            saveAccessibilityPreference('fontSize', 'largest');
        });
        
        // Cargar preferencia guardada
        loadFontSizePreference();
    }
}

/**
 * Inicializa el modo de alto contraste
 */
function initHighContrastMode() {
    const contrastToggle = document.getElementById('contrast-toggle');
    
    if (contrastToggle) {
        contrastToggle.addEventListener('click', function() {
            document.body.classList.toggle('high-contrast');
            
            // Guardar preferencia
            const isHighContrast = document.body.classList.contains('high-contrast');
            saveAccessibilityPreference('highContrast', isHighContrast);
            
            // Actualizar texto del botón
            this.textContent = isHighContrast ? 'Desactivar alto contraste' : 'Activar alto contraste';
        });
        
        // Cargar preferencia guardada
        loadContrastPreference();
    }
}

/**
 * Guarda las preferencias de accesibilidad en localStorage
 * @param {string} key - Clave de la preferencia
 * @param {any} value - Valor de la preferencia
 */
function saveAccessibilityPreference(key, value) {
    localStorage.setItem(`accessibility_${key}`, JSON.stringify(value));
}

/**
 * Carga la preferencia de tamaño de texto
 */
function loadFontSizePreference() {
    const fontSize = JSON.parse(localStorage.getItem('accessibility_fontSize'));
    
    if (fontSize) {
        switch (fontSize) {
            case 'large':
                document.body.classList.add('font-size-large');
                break;
            case 'larger':
                document.body.classList.add('font-size-larger');
                break;
            case 'largest':
                document.body.classList.add('font-size-largest');
                break;
        }
    }
}

/**
 * Carga la preferencia de contraste
 */
function loadContrastPreference() {
    const highContrast = JSON.parse(localStorage.getItem('accessibility_highContrast'));
    const contrastToggle = document.getElementById('contrast-toggle');
    
    if (highContrast) {
        document.body.classList.add('high-contrast');
        if (contrastToggle) {
            contrastToggle.textContent = 'Desactivar alto contraste';
        }
    }
}

/**
 * Inicializa la funcionalidad de impresión
 */
function initPrintFunctionality() {
    const printButtons = document.querySelectorAll('.print-button');
    
    printButtons.forEach(button => {
        button.addEventListener('click', function(e) {
            e.preventDefault();
            window.print();
        });
    });
}

/**
 * Inicializa la funcionalidad de compartir
 */
function initShareFunctionality() {
    const shareButtons = document.querySelectorAll('.share-button');
    
    shareButtons.forEach(button => {
        button.addEventListener('click', function(e) {
            e.preventDefault();
            
            const title = this.getAttribute('data-title') || document.title;
            const text = this.getAttribute('data-text') || '';
            const url = this.getAttribute('data-url') || window.location.href;
            
            if (navigator.share) {
                navigator.share({
                    title: title,
                    text: text,
                    url: url
                })
                .catch(error => {
                    console.error('Error al compartir:', error);
                });
            } else {
                // Fallback para navegadores que no soportan Web Share API
                alert('Comparte este enlace: ' + url);
            }
        });
    });
}

/**
 * Convierte texto plano de ingredientes en una lista HTML
 * @param {string} text - Texto de ingredientes separados por líneas
 * @returns {string} - HTML con lista de ingredientes
 */
function formatIngredientsList(text) {
    if (!text) return '';
    
    const lines = text.split('\n').filter(line => line.trim() !== '');
    let html = '<ul class="list-group list-group-flush">';
    
    lines.forEach(line => {
        html += `<li class="list-group-item">${line.trim()}</li>`;
    });
    
    html += '</ul>';
    return html;
}

/**
 * Convierte texto plano de instrucciones en una lista numerada HTML
 * @param {string} text - Texto de instrucciones separadas por líneas
 * @returns {string} - HTML con lista numerada de instrucciones
 */
function formatInstructionsList(text) {
    if (!text) return '';
    
    const lines = text.split('\n').filter(line => line.trim() !== '');
    let html = '<ol class="list-group list-group-numbered">';
    
    lines.forEach(line => {
        html += `<li class="list-group-item">${line.trim()}</li>`;
    });
    
    html += '</ol>';
    return html;
}

// Exponer funciones para uso global
window.accessibilityTools = {
    formatIngredientsList,
    formatInstructionsList
}; 