<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="./styles/login.css">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>

<body>
    <h1>LOGIN</h1>
    <form action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]);?>" method="POST">
        <label for="user">Usuario</label><br>
        <input type="text" name="user" id="user"><br><br>
        <label for="pswd">Contraseña</label><br>
        <input type="password" name="pswd" id="pwsd"><br><br>
        <input type="submit" value="Enviar">
    </form>
</body>
</html>

<?php

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    if (estaMiUsuario($_POST["user"], $_POST["pswd"], "credenciales.csv")) {
        session_start();
        $_SESSION["user"] = $_POST["user"];
        header("Location: sesiones1_login.php");
    } else {
        echo "Usuario o contraseña incorrectos";
    }
}



/**
 * @param string $user usuario a comprobar
 * @param string $password contraseña a comprobar
 * @param string $rutaFichero CSV con campos usuario y contraseña separado en ; 
 * @throws \Exception si no puede leer el fichero
 * @return bool devuelve true si ha encontrado alguna coincidencia en el fichero, false si no
 */
function estaMiUsuario (string $user, string $password, string $rutaFichero) :bool {
    if (is_readable($rutaFichero)) {
        $file = fopen($rutaFichero, "r");
        $credenciales = [2]; //array que almacena usuario y contraseña
        while (!feof($file)) {
            $credenciales = explode(";",trim(fgets($file))); //separa cada linea del csv en usuario y contraseña eliminando el salto de linea y espacios en los extremos
            if ($credenciales[0] == $user && $credenciales[1] == $password) //comprueba si las credenciales coinciden
                return true;
        }
        return false;
    } else {
        throw new Exception("No se ha podido leer el fichero", 1);
    }
}