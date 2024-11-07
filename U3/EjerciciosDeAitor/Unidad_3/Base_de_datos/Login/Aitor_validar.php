<?php
$err = false;
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    if (validarCredenciales($_POST['user'], $_POST['pass']))
        header("Location: nube.html");
    else {
        $err = true;
        include 'Aitor_Login.php';
    }
}

function validarCredenciales (string $usuario, string $contraseña) :bool {
    try {
        $bd = abrirConexion();
    } catch (PDOException $exc) {
        echo "[!] Error en la conexión<br>";
        echo $exc -> getMessage();
    }
    
    $consulta = $bd->query("SELECT user, password 
                            FROM credenciales 
                            WHERE user = \"" . $usuario . "\" && password = \"" . $contraseña . "\"");

    return($consulta->rowCount() == 1); //si se encuentra una fila es que hay un registro con estras credenciales
}

function abrirConexion():PDO {
    $conexion = "mysql:dbname=usuario_contraseña_texto_claro;host=127.0.0.1";
    $user = "root";
    $password = "";

    return(new PDO($conexion,$user,$password));
}