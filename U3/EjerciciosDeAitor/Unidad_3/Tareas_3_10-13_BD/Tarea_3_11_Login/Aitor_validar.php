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

function validarCredenciales (string $usuario, string $contrasenya) :bool {
    try {
        $bd = abrirConexion();
    } catch (PDOException $exc) {
        echo "[!] Error en la conexión<br>";
        echo $exc -> getMessage();
        return false;
    }
                /*
                //usando query

                $consulta = $bd->query("SELECT user, password 
                                        FROM credenciales 
                                        WHERE user = \"" . $usuario . "\" && password = \"" . $contrasenya . "\"");
                */
    try {
        $consulta = $bd->prepare("SELECT user, password
                            FROM credenciales
                            WHERE user = :user && password = :pswd");

        //parámetros
        $consulta->bindParam(":user", $usuario, PDO::PARAM_STR);
        $consulta->bindParam(":pswd", $contrasenya, PDO::PARAM_STR);

        //ejecución
        $consulta->execute();
    } catch (PDOException $exc) {
        echo "[!] Error al realizar la consulta";
        echo $exc -> getMessage();
        return false;
    }

    return($consulta->rowCount() == 1); //si se encuentra una sola fila es que hay un registro con estas credenciales
}

function abrirConexion() :PDO { //estoy haciendo una conexión a una bd propia, NO a EMPRESA
    $conexion = "mysql:dbname=usuario_contraseña_texto_claro;host=127.0.0.1";
    $user = "root";
    $password = "";

    return(new PDO($conexion,$user,$password));
}