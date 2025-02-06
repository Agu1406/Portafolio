@echo off
REM Habilita la expansión retardada de variables
setlocal enabledelayedexpansion

REM Definimos el rango y el incremento directamente en el script.
set "inicio=140"
set "final=150"
set "incremento=1"

REM Creamos nuestras variables, la primera es el prefijo de los archivos
REM la segunda es la extensión y la tercera es el número a partir del
REM cual el bucle crea los arhivo (prefijo + número + extensión).
set "prefijo=MPAA_Ejercicio"
set "extension=.html"

REM Definimos el contenido que se escribirá en cada archivo HTML
set "contenido=<!DOCTYPE html>^
<html lang='es'>^
<head>^
    <meta charset='UTF-8'>^
    <meta name='viewport' content='width=device-width, initial-scale=1.0'>^
    <title>Nombre de la actividad</title>^
    <link rel='icon' type='image/x-icon' href='https://iesventura.es/_ies_datos/LOGO_new.jpg'>^
    <link rel='stylesheet' href='estilos.css'>^
</head>^
<body>^
    <div class='contenedor'>^
        <header>^
            <p>Desarrollo Web Entorno Cliente.</p>^
        </header>^
        <main>^
            <h1>Titulo de la actividad</h1>^
            <p>Descripción de la actividad.</p>^
        </main>^
        <footer>^
            <p>&copy; 2024 <a href='https://github.com/agu1406' target='_blank'>Github Agu1406</a>. Todos los derechos reservados.</p>^
        </footer>^
    </div>^
    <script src='script.js'></script>^
</body>^
</html>"

REM Usamos un bucle "for" que cree un documento con prefijo y extensión y escribe contenido.
for /L %%i in (!inicio!,!incremento!,!final!) do (
    echo !contenido! > "!prefijo!%%i!extension!"
)

echo Archivos creados correctamente.
pause