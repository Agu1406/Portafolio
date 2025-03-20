<?php
/**
 * Controlador Base
 * Carga los modelos y las vistas
 */
class Controller {
    /**
     * Carga un modelo
     * @param string $model Nombre del modelo
     * @return object Instancia del modelo
     */
    public function model($model) {
        // Cargar archivo del modelo
        require_once '../app/models/' . $model . '.php';
        
        // Instanciar modelo
        return new $model();
    }

    /**
     * Carga una vista
     * @param string $view Nombre de la vista
     * @param array $data Datos para la vista (opcional)
     */
    public function view($view, $data = []) {
        // Comprobar si existe el archivo de vista
        if(file_exists('../app/views/' . $view . '.php')) {
            // Extraer los datos para que estén disponibles en la vista
            extract($data);
            
            // Almacenar el contenido de la vista en un buffer
            ob_start();
            require_once '../app/views/' . $view . '.php';
            $viewContent = ob_get_clean();
            
            // Cargar el layout principal que incluirá el contenido de la vista
            require_once '../app/views/layouts/main.php';
        } else {
            // Si la vista no existe
            die('La vista no existe');
        }
    }

    /**
     * Redirecciona a una página
     * @param string $url URL a redireccionar
     */
    public function redirect($url) {
        header('location: ' . BASE_URL . '/' . $url);
    }

    /**
     * Verifica si el usuario ha iniciado sesión
     * @return boolean
     */
    public function isLoggedIn() {
        return isset($_SESSION['user_id']);
    }

    /**
     * Verifica si la solicitud es AJAX
     * @return boolean
     */
    public function isAjax() {
        return !empty($_SERVER['HTTP_X_REQUESTED_WITH']) && 
               strtolower($_SERVER['HTTP_X_REQUESTED_WITH']) == 'xmlhttprequest';
    }

    /**
     * Devuelve una respuesta JSON
     * @param mixed $data Datos a convertir a JSON
     * @param int $status Código de estado HTTP (opcional)
     */
    public function jsonResponse($data, $status = 200) {
        header('Content-Type: application/json');
        http_response_code($status);
        echo json_encode($data);
        exit;
    }
} 