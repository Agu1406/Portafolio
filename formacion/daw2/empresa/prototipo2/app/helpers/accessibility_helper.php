<?php
/**
 * Helper de Accesibilidad
 * Funciones útiles para mejorar la accesibilidad del sitio
 */

/**
 * Genera un atributo aria-label
 * @param string $text Texto del label
 * @return string
 */
function ariaLabel($text) {
    return 'aria-label="' . htmlspecialchars($text) . '"';
}

/**
 * Genera un atributo aria-describedby
 * @param string $id ID del elemento que describe
 * @return string
 */
function ariaDescribedby($id) {
    return 'aria-describedby="' . $id . '"';
}

/**
 * Genera un atributo role
 * @param string $role Rol del elemento
 * @return string
 */
function role($role) {
    return 'role="' . $role . '"';
}

/**
 * Genera un atributo tabindex
 * @param int $index Índice de tabulación
 * @return string
 */
function tabindex($index) {
    return 'tabindex="' . $index . '"';
}

/**
 * Genera un botón accesible para leer texto en voz alta
 * @param string $targetId ID del elemento a leer
 * @param string $buttonText Texto del botón
 * @return string HTML del botón
 */
function textToSpeechButton($targetId, $buttonText = 'Escuchar') {
    $id = 'tts-' . uniqid();
    
    return '<button type="button" id="' . $id . '" class="btn btn-sm btn-outline-primary tts-button mb-2" ' . 
           'data-target="' . $targetId . '" ' . 
           ariaLabel("Leer en voz alta") . ' ' . 
           role("button") . '>
           <i class="fas fa-volume-up me-1"></i> ' . $buttonText . '
           </button>';
}

/**
 * Genera un control para cambiar el tamaño del texto
 * @return string HTML del control
 */
function fontSizeControls() {
    return '<div class="font-size-controls d-inline-block me-2" ' . role("group") . ' aria-label="Controles de tamaño de texto">
                <span class="me-2 text-muted small">Tamaño de texto:</span>
                <button type="button" id="decreaseFontBtn" class="btn btn-sm btn-outline-secondary" ' . ariaLabel("Disminuir tamaño de texto") . '>
                    <i class="fas fa-minus"></i>
                </button>
                <button type="button" id="increaseFontBtn" class="btn btn-sm btn-outline-secondary" ' . ariaLabel("Aumentar tamaño de texto") . '>
                    <i class="fas fa-plus"></i>
                </button>
            </div>';
}

/**
 * Genera un control para cambiar el contraste
 * @return string HTML del control
 */
function contrastToggle() {
    return '<div class="contrast-control d-inline-block">
                <button type="button" id="contrastBtn" class="btn btn-sm btn-outline-secondary" ' . ariaLabel("Cambiar contraste") . '>
                    <i class="fas fa-adjust me-1"></i> Activar alto contraste
                </button>
            </div>';
}

/**
 * Genera un conjunto completo de controles de accesibilidad
 * @return string HTML de los controles
 */
function accessibilityControls() {
    return '<div class="accessibility-controls" ' . role("toolbar") . ' aria-label="Controles de accesibilidad">
                <div class="d-flex align-items-center justify-content-end">
                    ' . fontSizeControls() . '
                    ' . contrastToggle() . '
                </div>
            </div>';
} 