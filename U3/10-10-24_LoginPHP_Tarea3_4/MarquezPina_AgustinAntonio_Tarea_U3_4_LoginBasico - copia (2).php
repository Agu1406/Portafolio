<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- <meta http-equiv="refresh" content="60"> <!-- recarga la pagina cada 5 segundos -->
    <title>Document</title>
</head>
<style>
    body {
        display: flex; /* Utilizamos "flex" para centrar todo */
        justify-content: center; /* Centramos todo horizontalmente */
        align-items: center; /* Centramos todo verticalmente */
        margin: 0px; /* Establece el margen en todos los lados en "0" */
        padding: 0px; /* Establece la separación entre elementos en "0" */
        background-color: azure; /* Color "Azure" porque está*/
        text-align: center; /* Centramos todo el texto */
    }
    form {
        max-width: 600px; /* Tamaño maximo posible del formulario */
        margin: 0 auto; /* Margen a todos lados del formulario */
        padding: 20px; /* Separación interna entre el borde y los elementos internos*/
        border: 1px solid #ccc; /* Borde externo solido de 1 pixel del formulario */
        border-radius: 8px; /* Redondeamos un poco el borde por añadido estetico */
        background-color: #f9f9f9; /* El color de fondo del formulario es un gris claro */
    }
    
    fieldset {
        margin-bottom: 15px;
        border: none;
    }
    
    label {
        display: block;
        margin-bottom: 5px;
    }
    
    input[type="submit"], input[type="reset"], input[type="button"] {
        margin-right: 10px;
        margin-top: 10px;
        border-radius: 8px; /* Redondeamos un poco el borde por añadido estetico */
    }
</style>
<body>
    <div class="contenedor">
<!-- Explicación de los atributos del formulario:
    
        A) ¿A que se refiere con htmlspecialchars? A un mecanismo de seguridad
    anti inyección de código, toma caracteres especiales de HTML como los
    "<" o ">" y los convierte en texto literal.

    B) el PHP_SELF es para que se reconozca así mismo como un PHP interno y se
    ejecute.
    
    -->
    <form action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]); ?>" method="POST">
    
        <label for="tituloFormulario" class="tituloFormulario">Formulario Basico en PHP</label>
        <br>
        <label for="usuario">Nombre de usuario: </label>
        <!-- Explicación del value del input
    
        Es un "if" donde "Si el valor de usuario está guardado o ha sido previamente
        usado permite "rellenar automaticamente" el input con ese valor, nada más.-->
        <input type="text" name="usuario" value="<?php if(isset($usuario)) echo $usuario; ?>">
        <br>

        <!-- No hay mucho que decir, etiqueta e input para una contraseña -->
        <label for="contrasenya">Contraseña: </label>
        <input type="password" name="contrasenya">
        <br>

        <!-- Botón para enviar el formulario -->
        <input type="submit" value="Iniciar Sesión">

        <!-- Botón para resetear el formulario -->
        <input type="reset" value="Reiniciar formulario">

    </form>
    <div id="error">

    </div>
    </div>
    
    <?php
        // Si el formulario fue enviado correctamente (con el método POST) se ejecuta el IF, si no, no.
        if($_SERVER ['REQUEST_METHOD'] == "POST") {
            // Extrae del POST el usuario y contraseña y verifica si son correctos
            if($_POST["usuario"] == "Agu1406" and $_POST["contrasenya"] == "1234") {
                // Si los datos son correctos le redireaccionamos a otro sitio HTML
                header("Location: bienvenido.html");
            } 
            // Si no son correcto, el "else" activa un "error" como "true".
            else {
                // La variable error solo es creada y activada si se llega aquí.
                $error = true;
            }
        }
    
        /* "isset" verifica que la variable ha sido creada y tiene
        un valor diferente de "nulo", fuera del parentesis, si existe
        y es "true" se ejecuta el if, si es "false" no se ejecuta. */
        if (isset($error)) {
            // Mensaje de error y aviso por pantalla incrustado en HTML
            echo "<br><br><p>¡Error! Verifica el usuario y la contraseña";
        }
    ?>
</body>
</html>