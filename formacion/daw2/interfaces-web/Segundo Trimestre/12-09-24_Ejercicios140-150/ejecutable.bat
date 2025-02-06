@echo off
REM Habilita la expansión retardada de variables
setlocal enabledelayedexpansion

REM Definimos el rango y el incremento directamente en el script.
set "inicio=140"
set "final=150"
set "incremento=1"

REM Creamos nuestras variables
set "prefijo=MPAA_Ejercicio"
set "extension=.html"

REM Escribimos el contenido HTML en un archivo temporal
(
echo ^<!DOCTYPE html^>
echo ^<html lang='es'^>
echo ^<head^>
echo ^    ^<meta charset='UTF-8'^>
echo ^    ^<meta name='viewport' content='width=device-width, initial-scale=1.0'^>
echo ^    ^<title>Nombre de la actividad^</title^>
echo ^    ^<link rel='icon' type='image/x-icon' href='https://iesventura.es/_ies_datos/LOGO_new.jpg'^>
echo ^    ^<link rel='stylesheet' href='estilos.css'^>
echo ^</head^>
echo ^<body^>
echo ^    ^<div class='contenedor'^>
echo ^        ^<header^>
echo ^            ^<p>Desarrollo Web Entorno Cliente.^</p^>
echo ^        </header^>
echo ^        ^<main^>
echo ^            ^<h1>Titulo de la actividad^</h1^>
echo ^            ^<p>Descripción de la actividad.^</p^>
echo ^        </main^>
echo ^        ^<footer^>
echo ^            ^<p>&copy; 2024 ^<a href='https://github.com/agu1406' target='_blank'^>Github Agu1406^</a^>. Todos los derechos reservados.^</p^>
echo ^        </footer^>
echo ^    </div^>
echo ^    ^<script src='script.js'^>^</script^>
echo ^</body^>
echo ^</html^>
) > template.tmp

REM Usamos un bucle para crear los archivos basados en la plantilla
for /L %%i in (!inicio!,!incremento!,!final!) do (
    copy template.tmp "!prefijo!%%i!extension!" >nul
)

REM Eliminamos el archivo temporal
del template.tmp

echo Archivos creados correctamente.
pause