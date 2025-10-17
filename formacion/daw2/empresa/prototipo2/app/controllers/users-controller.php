<?php
/**
 * Controlador Users
 * Maneja el registro, login y perfil de usuarios
 */
class UsersController extends Controller {
    /**
     * Constructor
     */
    public function __construct() {
        // Cargar modelo de usuario
        $this->userModel = $this->model('User');
    }

    /**
     * Registro de usuario
     */
    public function register() {
        // Verificar si el usuario ya está logueado
        if($this->isLoggedIn()) {
            $this->redirect('');
        }

        // Procesar formulario si se envió
        if($_SERVER['REQUEST_METHOD'] == 'POST') {
            // Sanitizar datos POST
            $_POST = filter_input_array(INPUT_POST, FILTER_SANITIZE_STRING);

            // Datos del formulario
            $data = [
                'title' => 'Registro',
                'name' => trim($_POST['name']),
                'email' => trim($_POST['email']),
                'password' => trim($_POST['password']),
                'confirm_password' => trim($_POST['confirm_password']),
                'name_err' => '',
                'email_err' => '',
                'password_err' => '',
                'confirm_password_err' => ''
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
            } elseif($this->userModel->findUserByEmail($data['email'])) {
                $data['email_err'] = 'Email ya registrado';
            }

            // Validar contraseña
            if(empty($data['password'])) {
                $data['password_err'] = 'Por favor ingrese una contraseña';
            } elseif(strlen($data['password']) < 6) {
                $data['password_err'] = 'La contraseña debe tener al menos 6 caracteres';
            }

            // Validar confirmación de contraseña
            if(empty($data['confirm_password'])) {
                $data['confirm_password_err'] = 'Por favor confirme la contraseña';
            } elseif($data['password'] != $data['confirm_password']) {
                $data['confirm_password_err'] = 'Las contraseñas no coinciden';
            }

            // Si no hay errores, registrar usuario
            if(empty($data['name_err']) && empty($data['email_err']) && 
               empty($data['password_err']) && empty($data['confirm_password_err'])) {
                
                // Hash de la contraseña
                $data['password'] = password_hash($data['password'], PASSWORD_DEFAULT);

                // Registrar usuario
                if($this->userModel->register($data)) {
                    // Mensaje flash
                    setFlash('register_success', '¡Registro exitoso! Ya puede iniciar sesión.');
                    
                    // Redireccionar a login
                    $this->redirect('users/login');
                } else {
                    die('Algo salió mal');
                }
            } else {
                // Cargar vista con errores
                $this->view('users/register', $data);
            }
        } else {
            // Datos para la vista
            $data = [
                'title' => 'Registro',
                'name' => '',
                'email' => '',
                'password' => '',
                'confirm_password' => '',
                'name_err' => '',
                'email_err' => '',
                'password_err' => '',
                'confirm_password_err' => ''
            ];

            // Cargar vista
            $this->view('users/register', $data);
        }
    }

    /**
     * Login de usuario
     */
    public function login() {
        // Verificar si el usuario ya está logueado
        if($this->isLoggedIn()) {
            $this->redirect('');
        }

        // Procesar formulario si se envió
        if($_SERVER['REQUEST_METHOD'] == 'POST') {
            // Sanitizar datos POST
            $_POST = filter_input_array(INPUT_POST, FILTER_SANITIZE_STRING);

            // Datos del formulario
            $data = [
                'title' => 'Iniciar Sesión',
                'email' => trim($_POST['email']),
                'password' => trim($_POST['password']),
                'email_err' => '',
                'password_err' => ''
            ];

            // Validar email
            if(empty($data['email'])) {
                $data['email_err'] = 'Por favor ingrese su email';
            } elseif(!filter_var($data['email'], FILTER_VALIDATE_EMAIL)) {
                $data['email_err'] = 'Email inválido';
            } elseif(!$this->userModel->findUserByEmail($data['email'])) {
                $data['email_err'] = 'Usuario no encontrado';
            }

            // Validar contraseña
            if(empty($data['password'])) {
                $data['password_err'] = 'Por favor ingrese su contraseña';
            }

            // Si no hay errores, iniciar sesión
            if(empty($data['email_err']) && empty($data['password_err'])) {
                // Verificar login
                $loggedInUser = $this->userModel->login($data['email'], $data['password']);

                if($loggedInUser) {
                    // Crear sesión
                    $this->createUserSession($loggedInUser);
                } else {
                    $data['password_err'] = 'Contraseña incorrecta';
                    $this->view('users/login', $data);
                }
            } else {
                // Cargar vista con errores
                $this->view('users/login', $data);
            }
        } else {
            // Datos para la vista
            $data = [
                'title' => 'Iniciar Sesión',
                'email' => '',
                'password' => '',
                'email_err' => '',
                'password_err' => ''
            ];

            // Cargar vista
            $this->view('users/login', $data);
        }
    }

    /**
     * Crear sesión de usuario
     * @param object $user Usuario
     */
    public function createUserSession($user) {
        $_SESSION['user_id'] = $user->id;
        $_SESSION['user_name'] = $user->name;
        $_SESSION['user_email'] = $user->email;
        $this->redirect('profile');
    }

    /**
     * Cerrar sesión
     */
    public function logout() {
        unset($_SESSION['user_id']);
        unset($_SESSION['user_name']);
        unset($_SESSION['user_email']);
        session_destroy();
        $this->redirect('users/login');
    }

    /**
     * Perfil de usuario
     */
    public function profile() {
        // Verificar si el usuario está logueado
        if(!$this->isLoggedIn()) {
            $this->redirect('users/login');
        }

        // Obtener información del usuario
        $user = $this->userModel->getUserById($_SESSION['user_id']);
        
        // Obtener recetas del usuario
        $recipes = $this->model('Recipe')->getRecipesByUser($_SESSION['user_id']);

        // Datos para la vista
        $data = [
            'title' => 'Mi Perfil',
            'user' => $user,
            'recipes' => $recipes
        ];

        // Cargar vista
        $this->view('users/profile', $data);
    }

    /**
     * Editar perfil
     */
    public function edit() {
        // Verificar si el usuario está logueado
        if(!$this->isLoggedIn()) {
            $this->redirect('users/login');
        }

        // Procesar formulario si se envió
        if($_SERVER['REQUEST_METHOD'] == 'POST') {
            // Sanitizar datos POST
            $_POST = filter_input_array(INPUT_POST, FILTER_SANITIZE_STRING);

            // Datos del formulario
            $data = [
                'title' => 'Editar Perfil',
                'id' => $_SESSION['user_id'],
                'name' => trim($_POST['name']),
                'email' => trim($_POST['email']),
                'bio' => trim($_POST['bio']),
                'name_err' => '',
                'email_err' => '',
                'bio_err' => ''
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
            } elseif($this->userModel->findUserByEmailExceptId($data['email'], $data['id'])) {
                $data['email_err'] = 'Email ya registrado por otro usuario';
            }

            // Si no hay errores, actualizar perfil
            if(empty($data['name_err']) && empty($data['email_err']) && empty($data['bio_err'])) {
                // Actualizar usuario
                if($this->userModel->updateUser($data)) {
                    // Actualizar sesión
                    $_SESSION['user_name'] = $data['name'];
                    $_SESSION['user_email'] = $data['email'];
                    
                    // Mensaje flash
                    setFlash('profile_success', 'Perfil actualizado correctamente');
                    
                    // Redireccionar a perfil
                    $this->redirect('users/profile');
                } else {
                    die('Algo salió mal');
                }
            } else {
                // Cargar vista con errores
                $this->view('users/edit', $data);
            }
        } else {
            // Obtener información del usuario
            $user = $this->userModel->getUserById($_SESSION['user_id']);

            // Datos para la vista
            $data = [
                'title' => 'Editar Perfil',
                'id' => $user->id,
                'name' => $user->name,
                'email' => $user->email,
                'bio' => $user->bio ?? '',
                'name_err' => '',
                'email_err' => '',
                'bio_err' => ''
            ];

            // Cargar vista
            $this->view('users/edit', $data);
        }
    }
} 