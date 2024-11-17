<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="styles/login.css">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Formulario de login</title>
</head>
<body>
    <form action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]); ?>" method="POST">
        <fieldset> 
            <label for="titulo" class="label-titulo">Formulario de Login</label><br><br>

            <!-- Entrada de texto -->
            <label for="usuario">Usuario: </label>
            <input type="text" name="usuario" required><br><br>

            <!-- Entrada de contraseña -->
            <label for="pw">Contraseña: </label>
            <input name="pw" type="password" required><br><br>

            <!--Este script de javascript muestra el error de indicamos en el php en caso de que 
            el usuario o la contraseña sean incorrectos-->
            <script type="text/javascript">
                var error = document.write("<?php echo $error; ?>");
            </script>

            <!-- Entrada de sumbit -->
            <input type="submit" value="Iniciar sesión">
        </fieldset>
    </form>
</body>
</html>
<?php
//
include './Connection/bd_conexion.php';
//Llamamos a la función para obtener los datos de conexión.
$bd = dataBaseConnection();

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    // Obtener usuario y contraseña ingresados
    $usuario = trim($_POST['usuario']);
    $pw = trim($_POST['pw']);

    $pattern = "/\w+@\w+/";
    $idEmpresa = null; //Variable para almcenar el ID

    if(!preg_match($pattern, $usuario)){
        // Preparar consulta SQL para verificar credenciales de usuarios administradores
        $stmt = $bd->prepare("SELECT * FROM usuarios WHERE Usuario = :usuario AND Password = :pw");
    }else{
        // Preparar consulta SQL para verificar credenciales de las empresas cliente
        $stmt = $bd->prepare("SELECT * FROM empresa WHERE Email = :usuario AND Password = :pw");
       
    }

    $stmt->bindParam(':usuario', $usuario);
    $stmt->bindParam(':pw', $pw);
    $stmt->execute();

    if ($stmt->rowCount() > 0) {
        // Credenciales correctas: redirigir a página de bienvenida
        session_start();
        $row = $stmt->fetch(PDO::FETCH_ASSOC); //Obtener el ID del usuario/empresa
        $_SESSION['usuario'] = $_POST['usuario'];
        $_SESSION['idEmpresa'] = $row['ID_Empresa']; //ID del usuario/empresa
       header("Location: shop.php");
        exit();
    } else {
        echo "<p style='color:red;'>Usuario o contraseña incorrectos. Inténtalo de nuevo.</p>";
    }
}