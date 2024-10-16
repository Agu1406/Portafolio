<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Página Multilenguaje</title>
</head>
<body>
    <?php
    // Nombre de la cookie para el idioma
    $nombreCookie = "idiomaSeleccionado";
    
    // Valor por defecto del idioma: Español
    $idioma = "es";

    // Si se ha enviado el formulario, capturamos el idioma seleccionado y lo guardamos en una cookie
    if (isset($_POST['idioma'])) {
        $idioma = $_POST['idioma'];
        
        // Guardamos el idioma en una cookie que caduca en 30 días
        setcookie($nombreCookie, $idioma, time() + (30 * 24 * 60 * 60), "/");
    }

    // Si ya existe la cookie del idioma, la usamos
    if (isset($_COOKIE[$nombreCookie])) {
        $idioma = $_COOKIE[$nombreCookie];
    }

    // Definimos los textos en ambos idiomas
    $textos = [
        'es' => [
            'titulo' => 'Bienvenido a la Página Multilenguaje',
            'saludo' => 'Hola, esta es una página en Español.',
            'seleccion' => 'Selecciona tu idioma:',
            'boton' => 'Guardar Idioma'
        ],
        'en' => [
            'titulo' => 'Welcome to the Multilanguage Page',
            'saludo' => 'Hello, this is a page in English.',
            'seleccion' => 'Select your language:',
            'boton' => 'Save Language'
        ]
    ];
    ?>

    <!-- Mostramos los textos según el idioma seleccionado -->
    <h1><?php echo $textos[$idioma]['titulo']; ?></h1>
    <p><?php echo $textos[$idioma]['saludo']; ?></p>

    <!-- Formulario para seleccionar el idioma -->
    <form method="POST" action="">
        <label for="idioma"><?php echo $textos[$idioma]['seleccion']; ?></label>
        <select name="idioma" id="idioma">
            <option value="es" <?php if ($idioma == 'es') echo 'selected'; ?>>Español</option>
            <option value="en" <?php if ($idioma == 'en') echo 'selected'; ?>>English</option>
        </select>
        <button type="submit"><?php echo $textos[$idioma]['boton']; ?></button>
    </form>
</body>
</html>
