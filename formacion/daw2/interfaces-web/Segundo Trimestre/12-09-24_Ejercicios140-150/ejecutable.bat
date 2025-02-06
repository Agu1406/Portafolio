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
set "contenido=<!DOCTYPE html>\n<html lang='es'>\n<head>\n    <meta charset='UTF-8'>\n    <meta name='viewport' content='width=device-width, initial-scale=1.0'>\n    <title>Nombre de la actividad</title>\n    <link rel='icon' type='image/x-icon' href='https://iesventura.es/_ies_datos/LOGO_new.jpg'>\n    <link rel='stylesheet' href='estilos.css'>\n</head>\n<body>\n\n</body>\n</html>"

REM Usamos un bucle "for" que cree un documento con prefijo y extensión y escribe contenido.
for /L %%i in (!inicio!,!incremento!,!final!) do (
    echo.!contenido! > "!prefijo!%%i!extension!"
)

echo Archivos creados correctamente.
pause
