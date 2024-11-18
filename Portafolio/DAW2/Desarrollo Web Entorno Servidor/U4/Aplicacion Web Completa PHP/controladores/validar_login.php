<?php
// Importar el modelo de usuario y la conexión a la base de datos
include_once __DIR__ . "/../modelos/crud_usuarios.php";
include_once __DIR__ . "/../modelos/conexion_bd.php";

// Verificar si se han enviado los datos del formulario
if (isset($_POST['usuario']) && isset($_POST['contrasena'])) {
    $usuario = $_POST['usuario'];
    $contrasena = $_POST['contrasena'];

    // Llamar al método del modelo para verificar las credenciales
    $resultado = UsuarioCRUD::verificarCredenciales($usuario, $contrasena);

    if ($resultado) {
        // Iniciar sesión o establecer una cookie si las credenciales son correctas
        session_start();

        // A la sesión le asignamos un atributo/valor con el nombre de usuario.
        $_SESSION['usuario'] = $resultado['nombre_usuario'];

        // Le creamos una cookie al usuario con una duración de 30 días.
        setcookie('usuario', $resultado['nombre_usuario'], time() + (86400 * 30), "/"); // 30 días de duración

        // Redirigir al usuario a la página de inicio o panel
        header("Location: catalogo.php");

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
