<?php
/**
 * Helper de Sesión
 * Funciones útiles para manejar sesiones y mensajes flash
 */

/**
 * Establece un mensaje flash
 * @param string $name Nombre del mensaje
 * @param string $message Contenido del mensaje
 * @param string $class Clase CSS (opcional)
 */
function setFlash($name, $message, $class = 'alert alert-success') {
    if(!isset($_SESSION['flash'])) {
        $_SESSION['flash'] = [];
    }
    
    $_SESSION['flash'][$name] = [
        'message' => $message,
        'class' => $class
    ];
}

/**
 * Obtiene un mensaje flash
 * @param string $name Nombre del mensaje
 * @return string HTML del mensaje
 */
function getFlash($name) {
    if(isset($_SESSION['flash'][$name])) {
        $flash = $_SESSION['flash'][$name];
        unset($_SESSION['flash'][$name]);
        
        return '<div class="' . $flash['class'] . ' alert-dismissible fade show" role="alert">
                    ' . $flash['message'] . '
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Cerrar"></button>
                </div>';
    }
    
    return '';
}

/**
 * Verifica si existe un mensaje flash
 * @param string $name Nombre del mensaje
 * @return boolean
 */
function hasFlash($name) {
    return isset($_SESSION['flash'][$name]);
}

/**
 * Establece un valor en la sesión
 * @param string $key Clave
 * @param mixed $value Valor
 */
function setSession($key, $value) {
    $_SESSION[$key] = $value;
}

/**
 * Obtiene un valor de la sesión
 * @param string $key Clave
 * @param mixed $default Valor por defecto si no existe
 * @return mixed
 */
function getSession($key, $default = null) {
    return isset($_SESSION[$key]) ? $_SESSION[$key] : $default;
}

/**
 * Elimina un valor de la sesión
 * @param string $key Clave
 */
function unsetSession($key) {
    if(isset($_SESSION[$key])) {
        unset($_SESSION[$key]);
    }
}

/**
 * Verifica si el usuario ha iniciado sesión
 * @return boolean
 */
function isLoggedIn() {
    return isset($_SESSION['user_id']);
}

/**
 * Obtiene el ID del usuario actual
 * @return int|null
 */
function getCurrentUserId() {
    return getSession('user_id');
}

/**
 * Destruye la sesión actual
 */
function destroySession() {
    session_unset();
    session_destroy();
} 