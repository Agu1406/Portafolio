<?php
/**
 * Hemos copiado el código de la tarea 3_4 donde estaban nuestros primeros formularios de login y logout
 * y se han modificado para consultar la base de datos proporcionada por Mercedes llamada "empresa"
 * donde nuestro usuario es "agu1406" y nuestra contraseña es "1406", debemos validar los datos, confirmar
 * el login y redirigir en caso positivo a, por ejemplo, un apartado para subir archivos.
 */

 // Creamos lo primero, una variable global llamada "error", que si se activa, avisa de un fallo.
$error = false;

// Si el método de envio del formulario es POST, procedemos, en otro caso, no se ejecuta el if.
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    // Extraemos del método "POST" el usuario y la contraseña que hemos de validar.
    $posibleUsuario = $_POST["usuario"];
    $posiblePassword = $_POST["contrasena"];

    /**
     * Con un if-else, llamamos al método "validarCredenciales" que intenta, con
     * los datos extraidos del post validarlos, si son correctos, el método
     * devuelve un "true" y se ejecuta el if, en caso contrario se ejecuta
     * el "false" e imprime el mensaje de "error".
     */
    if (validarCredenciales($posibleUsuario, $posiblePassword)) {

        // Si los datos son validos, creamos una sesión para el usuario
        session_start();

        // A esa sesión le añadimos un atributo llamado "usuario" asignado al usuario que hizo login
        $_SESSION["usuario"] = $posibleUsuario;
        
        // Creamos o actualizamos la Cookie con el usuario que inicio sesión.
        crearCookie($posibleUsuario);
        
        // Si los datos son validos, redirigimos el usuario al apartado de subir archivos.
        header("Location: subirArchivos.php");



    } else {
    // Si la validación falla por cualquier motivo, el "error" se establece en "true".
    $error = true;
    }
}

// Si se ha generado un error en la validación, mostrar mensaje
if ($error) {
    echo "<br><p>¡Error! Verifica tu usuario o contraseña e inténtalo de nuevo</p>";
}

function validarCredenciales ($posibleUsuario, $posiblePassword) :bool {
        // Primero, intentamos establecer una conexión exitosa con la base de datos.
        $baseDeDatos = conexionBD();

        /**
         * Mi código está pensando para que, en caso de que la conexión con la
         * base de datos de error devuelva una instancia de la clase PDO cuyo
         * valor sea "null", si eso courre, el método validar datos ni siquiera
         * se molestar en hacer el try-catch, directamente devuelve "false".
         */
        if (!$baseDeDatos) {
            // Devuelve false, no ejecuta el try-catch y adiós.
            return false;
        }
    
    try {


    // Preparamos la consulta que validara ambos, usuario y contraseña.
    $consultaPreparada = $baseDeDatos -> prepare("SELECT nombre, clave 
    FROM usuarios 
    WHERE nombre = :nombre 
    AND clave = :clave");

    /**
     * Tenemos que indicarle a la consulta que valores tendran ":nombre" y
     * ":clave", los cuales son los que hemos extraido del POST del formulario
     * y se deben definir de la siguiente forma:
     */
    $consultaPreparada -> bindParam(":nombre", $posibleUsuario, PDO::PARAM_STR);
    $consultaPreparada -> bindParam(":clave", $posiblePassword, PDO::PARAM_STR);

    // Ahora con la consulta bien preparada, procedemos a ejecutarla:
    $consultaPreparada -> execute();
    
    /**
     * Por ultimo, una vez hecha la consulta, hacemos un rowCount que
     * contara todos los resultados obtenidos de la consulta, si exite
     * "1" significa que los datos son validos, existe ese usuario con
     * esa contraseña, cualquier otro escenario devuelve un "false".
     */
    $datosValidos = ($consultaPreparada -> rowCount() == 1);

    // Devuelve "true" si "1" y en cualquier otro caso devuelve "false".
    return $datosValidos;

    // En caso de que ocurra una excepción durante el intento de validar ocurre el catch
    } catch (PDOException $e) {

        // Aviso por pantalla de un error durante la validación e imrpimo ese error.
        echo "¡Error en la validacion de datos! Error especifico: " . $e -> getMessage();
        
        // Devuelvo "falso" danto por invalido el intento de validar las credenciales.
        return false;
    }
}


function conexionBD() :PDO {
    try {
    // La conexión se realiza utilizando el constructor de la clase PDO
    $datosConexion = "mysql:dbname=empres;host=127.0.0.1:3309";
    $usuarioBD = "root";
    $claveBD = "";

    // Instanciamos un nuevo objeto PDO con esos datos
    $intentoDeConexion = new PDO($datosConexion, $usuarioBD, $claveBD);

    // Devolvemos la conexión exitosa con la base de datos.
    return $intentoDeConexion;

    // En caso de que la conexión de cualquier tipo de error / excepción se atrapa y ocurre el catch.
    } catch (PDOException $e) {
        // Avisamos por pantalla con un mensaje general y el error especifico de conexión.
        echo "¡Error estableciendo la conexión con la base de datos! Error especifico: " . $e -> getMessage();

        // Devuelve una instancia PDO "null", es decir, nula, indicando que no funciona.
        return null;
    }
}

/**
 * Función para crear o actualizar una cookie del usuario
 */
function crearCookie($posibleUsuario) {
    // Definir el nombre de la cookie y su duración
    $nombreDeLaCookie = $posibleUsuario; // El nombre de la cookie es el nombre del usuario
    $valorDeLaCookie = "Sesión activa para " . $posibleUsuario;
    $tiempoExpiracion = time() + (86400); // 24 horas de duración

    // Si ya existe la cookie, la actualizamos
    if (isset($_COOKIE[$nombreDeLaCookie])) {
        // Actualizamos el valor de la cookie (ejemplo: para contar visitas o algo similar)
        $valorDeLaCookie = $_COOKIE[$nombreDeLaCookie] . " Visitó de nuevo";
    }

    // Crear o actualizar la cookie con setcookie()
    setcookie($nombreDeLaCookie, $valorDeLaCookie, $tiempoExpiracion, "/");
}