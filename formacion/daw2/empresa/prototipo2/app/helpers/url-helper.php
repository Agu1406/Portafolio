<?php
/**
 * Helper de URL
 * Funciones útiles para manejar URLs
 */

/**
 * Redirecciona a una página específica
 * @param string $page Página a redireccionar
 */
function redirect($page) {
    header('location: ' . BASE_URL . '/' . $page);
    exit;
}

/**
 * Genera una URL completa
 * @param string $path Ruta relativa
 * @return string URL completa
 */
function url($path = '') {
    return BASE_URL . '/' . $path;
}

/**
 * Genera una URL para un recurso estático (CSS, JS, imágenes)
 * @param string $path Ruta relativa al recurso
 * @return string URL completa al recurso
 */
function asset($path) {
    return BASE_URL . '/public/' . $path;
}

/**
 * Verifica si la URL actual coincide con la ruta especificada
 * @param string $path Ruta a verificar
 * @return boolean
 */
function isCurrentUrl($path) {
    $currentUrl = $_SERVER['REQUEST_URI'];
    $baseUrl = parse_url(BASE_URL, PHP_URL_PATH) ?: '';
    $currentPath = substr($currentUrl, strlen($baseUrl));
    $currentPath = trim($currentPath, '/');
    
    return $currentPath === trim($path, '/');
} 