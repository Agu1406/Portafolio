<?php
/**
 * Controlador Home
 * Maneja las páginas principales del sitio
 */
class HomeController extends Controller {
    /**
     * Constructor
     */
    public function __construct() {
        // Cargar modelos si es necesario
    }

    /**
     * Página de inicio
     */
    public function index() {
        // Datos para la vista
        $data = [
            'title' => 'Bienvenido a ' . APP_NAME,
            'description' => 'La red social gastronómica más accesible para compartir y descubrir recetas'
        ];

        // Cargar vista
        $this->view('home/index', $data);
    }

    /**
     * Página Acerca de
     */
    public function about() {
        // Datos para la vista
        $data = [
            'title' => 'Acerca de ' . APP_NAME,
            'description' => 'Conoce más sobre nuestra red social gastronómica accesible'
        ];

        // Cargar vista
        $this->view('home/about', $data);
    }

    /**
     * Página de contacto
     */
    public function contact() {
        // Procesar formulario si se envió
        if($_SERVER['REQUEST_METHOD'] == 'POST') {
            // Sanitizar datos POST
            $_POST = filter_input_array(INPUT_POST, FILTER_SANITIZE_STRING);

            // Datos del formulario
            $data = [
                'title' => 'Contacto',
                'name' => trim($_POST['name']),
                'email' => trim($_POST['email']),
                'message' => trim($_POST['message']),
                'name_err' => '',
                'email_err' => '',
                'message_err' => ''
            ];

            // Validar nombre
            if(empty($data['name'])) {
                $data['name_err'] = 'Por favor ingrese su nombre';
            }

            // Validar email
            if(empty($data['email'])) {
                $data['email_err'] = 'Por favor ingrese su email';
            } elseif(!filter_var($data['email'], FILTER_VALIDATE_EMAIL)) {
                $data['email_err'] = 'Email inválido';
            }

            // Validar mensaje
            if(empty($data['message'])) {
                $data['message_err'] = 'Por favor ingrese un mensaje';
            }

            // Si no hay errores, procesar el formulario
            if(empty($data['name_err']) && empty($data['email_err']) && empty($data['message_err'])) {
                // Aquí iría el código para enviar el email
                
                // Mensaje flash
                setFlash('contact_success', '¡Mensaje enviado correctamente! Nos pondremos en contacto pronto.');
                
                // Redireccionar
                $this->redirect('home/contact');
            } else {
                // Cargar vista con errores
                $this->view('home/contact', $data);
            }
        } else {
            // Datos para la vista
            $data = [
                'title' => 'Contacto',
                'name' => '',
                'email' => '',
                'message' => '',
                'name_err' => '',
                'email_err' => '',
                'message_err' => ''
            ];

            // Cargar vista
            $this->view('home/contact', $data);
        }
    }
} 