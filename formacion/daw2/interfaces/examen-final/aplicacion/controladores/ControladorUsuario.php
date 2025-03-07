<?php
/**
 * Controlador para la gestión de usuarios
 * 
 * Este controlador maneja las operaciones relacionadas con usuarios:
 * login, registro, cierre de sesión, etc.
 */
class ControladorUsuario {
    
    /**
     * Muestra el formulario de login
     */
    public function mostrarLogin() {
        // Si el usuario ya está logueado, redirigir al inicio
        if (isset($_SESSION['usuario'])) {
            header('Location: ' . $GLOBALS['configuracion']['rutaBase'] . '/');
            exit;
        }
        
        // Título de la página
        $tituloPagina = 'Iniciar Sesión - NaturalShop';
        
        // Mensaje de error (si existe)
        $error = isset($_GET['error']) ? $_GET['error'] : null;
        
        // Obtener categorías para el menú
        $categorias = [];
        if (isset($GLOBALS['bd'])) {
            $categorias = $GLOBALS['bd']->obtenerCategorias();
        } else {
            // Categorías de ejemplo
            $categorias = [
                ['id' => 1, 'nombre' => 'Parafarmacia'],
                ['id' => 2, 'nombre' => 'Cosméticos Naturales'],
                ['id' => 3, 'nombre' => 'Suplementos Alimenticios']
            ];
        }
        
        // Cargar la vista de login
        include RUTA_APLICACION . '/vistas/login.php';
    }
    
    /**
     * Procesa el formulario de login
     */
    public function login() {
        // Si el usuario ya está logueado, redirigir al inicio
        if (isset($_SESSION['usuario'])) {
            header('Location: ' . $GLOBALS['configuracion']['rutaBase'] . '/');
            exit;
        }
        
        // Verificar que se han enviado los datos del formulario
        if (isset($_POST['email']) && isset($_POST['password'])) {
            $email = trim($_POST['email']);
            $password = trim($_POST['password']);
            
            // Validar que los campos no estén vacíos
            if (empty($email) || empty($password)) {
                // Redirigir con mensaje de error
                header('Location: ' . $GLOBALS['configuracion']['rutaBase'] . '/login?error=campos_vacios');
                exit;
            }
            
            // Verificar credenciales
            $usuario = null;
            
            if (isset($GLOBALS['bd'])) {
                // Verificar credenciales en la base de datos
                $usuario = $GLOBALS['bd']->verificarCredenciales($email, $password);
            } else {
                // Simulación de verificación para desarrollo
                if ($email === 'usuario@ejemplo.com' && $password === 'password123') {
                    $usuario = [
                        'id' => 1,
                        'nombre' => 'Usuario Demo',
                        'email' => $email,
                        'rol' => 'cliente'
                    ];
                } elseif ($email === 'admin@naturalshop.com' && $password === 'admin123') {
                    $usuario = [
                        'id' => 2,
                        'nombre' => 'Administrador',
                        'email' => $email,
                        'rol' => 'admin'
                    ];
                }
            }
            
            if ($usuario) {
                // Login exitoso, guardar datos en sesión
                $_SESSION['usuario'] = $usuario;
                
                // Redirigir según el rol
                if ($usuario['rol'] === 'admin') {
                    header('Location: ' . $GLOBALS['configuracion']['rutaBase'] . '/admin');
                } else {
                    // Redirigir a la página anterior si existe, o al inicio
                    $redirect = isset($_SESSION['redirect_after_login']) ? $_SESSION['redirect_after_login'] : $GLOBALS['configuracion']['rutaBase'] . '/';
                    unset($_SESSION['redirect_after_login']);
                    header('Location: ' . $redirect);
                }
                exit;
            } else {
                // Login fallido
                header('Location: ' . $GLOBALS['configuracion']['rutaBase'] . '/login?error=credenciales_invalidas');
                exit;
            }
        } else {
            // No se enviaron los datos necesarios
            header('Location: ' . $GLOBALS['configuracion']['rutaBase'] . '/login?error=datos_incompletos');
            exit;
        }
    }
    
    /**
     * Cierra la sesión del usuario
     */
    public function logout() {
        // Destruir la sesión
        session_destroy();
        
        // Redirigir al inicio
        header('Location: ' . $GLOBALS['configuracion']['rutaBase'] . '/');
        exit;
    }
    
    /**
     * Muestra el formulario de registro
     */
    public function mostrarRegistro() {
        // Si el usuario ya está logueado, redirigir al inicio
        if (isset($_SESSION['usuario'])) {
            header('Location: ' . $GLOBALS['configuracion']['rutaBase'] . '/');
            exit;
        }
        
        // Título de la página
        $tituloPagina = 'Registro - NaturalShop';
        
        // Mensaje de error (si existe)
        $errores = isset($_SESSION['errores_registro']) ? $_SESSION['errores_registro'] : [];
        unset($_SESSION['errores_registro']);
        
        // Datos del formulario (para mantenerlos si hay errores)
        $datos = isset($_SESSION['datos_registro']) ? $_SESSION['datos_registro'] : [];
        unset($_SESSION['datos_registro']);
        
        // Obtener categorías para el menú
        $categorias = [];
        if (isset($GLOBALS['bd'])) {
            $categorias = $GLOBALS['bd']->obtenerCategorias();
        } else {
            // Categorías de ejemplo
            $categorias = [
                ['id' => 1, 'nombre' => 'Parafarmacia'],
                ['id' => 2, 'nombre' => 'Cosméticos Naturales'],
                ['id' => 3, 'nombre' => 'Suplementos Alimenticios']
            ];
        }
        
        // Cargar la vista de registro
        include RUTA_APLICACION . '/vistas/registro.php';
    }
    
    /**
     * Procesa el formulario de registro
     */
    public function registrar() {
        // Si el usuario ya está logueado, redirigir al inicio
        if (isset($_SESSION['usuario'])) {
            header('Location: ' . $GLOBALS['configuracion']['rutaBase'] . '/');
            exit;
        }
        
        // Verificar que se han enviado los datos del formulario
        if ($_SERVER['REQUEST_METHOD'] === 'POST') {
            // Recoger datos del formulario
            $nombre = isset($_POST['nombre']) ? trim($_POST['nombre']) : '';
            $email = isset($_POST['email']) ? trim($_POST['email']) : '';
            $password = isset($_POST['password']) ? trim($_POST['password']) : '';
            $confirmarPassword = isset($_POST['confirmar_password']) ? trim($_POST['confirmar_password']) : '';
            $telefono = isset($_POST['telefono']) ? trim($_POST['telefono']) : '';
            $direccion = isset($_POST['direccion']) ? trim($_POST['direccion']) : '';
            $codigoPostal = isset($_POST['codigo_postal']) ? trim($_POST['codigo_postal']) : '';
            $ciudad = isset($_POST['ciudad']) ? trim($_POST['ciudad']) : '';
            $provincia = isset($_POST['provincia']) ? trim($_POST['provincia']) : '';
            
            // Guardar datos para mantenerlos en caso de error
            $_SESSION['datos_registro'] = [
                'nombre' => $nombre,
                'email' => $email,
                'telefono' => $telefono,
                'direccion' => $direccion,
                'codigo_postal' => $codigoPostal,
                'ciudad' => $ciudad,
                'provincia' => $provincia
            ];
            
            // Validar datos
            $errores = [];
            
            if (empty($nombre)) {
                $errores[] = 'El nombre es obligatorio';
            }
            
            if (empty($email)) {
                $errores[] = 'El email es obligatorio';
            } elseif (!filter_var($email, FILTER_VALIDATE_EMAIL)) {
                $errores[] = 'El email no es válido';
            }
            
            if (empty($password)) {
                $errores[] = 'La contraseña es obligatoria';
            } elseif (strlen($password) < 6) {
                $errores[] = 'La contraseña debe tener al menos 6 caracteres';
            }
            
            if ($password !== $confirmarPassword) {
                $errores[] = 'Las contraseñas no coinciden';
            }
            
            if (empty($direccion)) {
                $errores[] = 'La dirección es obligatoria';
            }
            
            if (empty($codigoPostal)) {
                $errores[] = 'El código postal es obligatorio';
            }
            
            if (empty($ciudad)) {
                $errores[] = 'La ciudad es obligatoria';
            }
            
            if (empty($provincia)) {
                $errores[] = 'La provincia es obligatoria';
            }
            
            // Verificar si el email ya existe
            if (isset($GLOBALS['bd']) && empty($errores)) {
                $emailExiste = $GLOBALS['bd']->verificarEmailExiste($email);
                if ($emailExiste) {
                    $errores[] = 'El email ya está registrado';
                }
            }
            
            // Si hay errores, volver al formulario
            if (!empty($errores)) {
                $_SESSION['errores_registro'] = $errores;
                header('Location: ' . $GLOBALS['configuracion']['rutaBase'] . '/registro');
                exit;
            }
            
            // Registrar usuario
            $registroExitoso = false;
            
            if (isset($GLOBALS['bd'])) {
                // Registrar en la base de datos
                $passwordHash = password_hash($password, PASSWORD_DEFAULT);
                $registroExitoso = $GLOBALS['bd']->registrarUsuario($nombre, $email, $passwordHash, $telefono, $direccion, $codigoPostal, $ciudad, $provincia);
            } else {
                // Simulación de registro exitoso para desarrollo
                $registroExitoso = true;
            }
            
            if ($registroExitoso) {
                // Registro exitoso, redirigir al login
                header('Location: ' . $GLOBALS['configuracion']['rutaBase'] . '/login?registro=exitoso');
                exit;
            } else {
                // Error al registrar
                $_SESSION['errores_registro'] = ['Error al registrar el usuario. Por favor, inténtelo de nuevo.'];
                header('Location: ' . $GLOBALS['configuracion']['rutaBase'] . '/registro');
                exit;
            }
        } else {
            // Método no permitido
            header('Location: ' . $GLOBALS['configuracion']['rutaBase'] . '/registro');
            exit;
        }
    }
} 