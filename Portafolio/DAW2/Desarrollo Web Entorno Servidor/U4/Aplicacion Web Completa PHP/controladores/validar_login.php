<?php
// Importar el modelo de usuario y la conexión a la base de datos
include_once __DIR__ . "/../modelos/crud_sesiones.php";
include_once __DIR__ . "/../modelos/conexion_bd.php";

// Verificar si se han enviado los datos del formulario
if (isset($_POST['usuario']) && isset($_POST['contrasena'])) {
    
    $usuario = trim($_POST['usuario']);
    $contrasena = trim($_POST['contrasena']);

    // Llamar al método del modelo para verificar las credenciales
    $resultado = controlSesiones::crearSesion($usuario, $contrasena);

    if ($resultado) {
        // Iniciar sesión o establecer una cookie si las credenciales son correctas
        session_start();

        // A la sesión le asignamos un atributo/valor con el nombre de usuario.
        $_SESSION['usuario'] = $resultado['correo'];

        // A la sesión tambien le asignamos un atributo/valor con el código cliente del usuario
        $_SESSION['codigo_cliente'] = $resultado['cliente_codigo_cliente'];

        // Prueba para verificar que el nombre de usuario está en la sesión
        var_dump($_SESSION);

        // Le creamos una cookie al usuario con una duración de 30 días.
        setcookie('usuario', $resultado['correo'], time() + (86400 * 30), "/"); // 30 días de duración

        // Redirigir al usuario a la página de inicio o panel
        header("Location: ../vistas/catalogo.php");

        // Hacemos un exit para no gastar recursos y salir del scrip
        exit();
    } else {
        // Mostrar un mensaje de error si las credenciales son incorrectas
        echo "Usuario o contraseña incorrectos. Intente de nuevo.";
        header("Location: login.php?error=1");
    }
} else {
    echo "Por favor, complete todos los campos.";
    header("Location: login.php?error=2");
}
