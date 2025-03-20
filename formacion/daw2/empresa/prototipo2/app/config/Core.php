<?php
/**
 * Clase Core
 * Crea la URL y carga el controlador principal
 * Formato URL: /controlador/método/parámetros
 */
class Core {
    protected $currentController = 'Home';
    protected $currentMethod = 'index';
    protected $params = [];

    /**
     * Constructor - Inicializa el enrutamiento
     */
    public function __construct() {
        // Obtener URL
        $url = $this->getUrl();

        // Buscar en controladores por primera parte de la URL
        if(isset($url[0]) && file_exists('../app/controllers/' . ucwords($url[0]) . 'Controller.php')) {
            // Si existe, establecer como controlador
            $this->currentController = ucwords($url[0]) . 'Controller';
            // Eliminar del array
            unset($url[0]);
        } else {
            $this->currentController = 'HomeController';
        }

        // Requerir el controlador
        require_once '../app/controllers/' . $this->currentController . '.php';

        // Instanciar clase del controlador
        $this->currentController = new $this->currentController;

        // Comprobar segunda parte de la URL
        if(isset($url[1])) {
            // Comprobar si el método existe en el controlador
            if(method_exists($this->currentController, $url[1])) {
                $this->currentMethod = $url[1];
                // Eliminar del array
                unset($url[1]);
            }
        }

        // Obtener parámetros
        $this->params = $url ? array_values($url) : [];

        // Llamar a una función de callback con array de parámetros
        call_user_func_array([$this->currentController, $this->currentMethod], $this->params);
    }

    /**
     * Obtiene la URL de la solicitud
     * @return array
     */
    public function getUrl() {
        if(isset($_GET['url'])) {
            // Eliminar el último slash
            $url = rtrim($_GET['url'], '/');
            // Sanitizar URL
            $url = filter_var($url, FILTER_SANITIZE_URL);
            // Dividir URL
            $url = explode('/', $url);
            return $url;
        }
        
        return ['home'];
    }
} 