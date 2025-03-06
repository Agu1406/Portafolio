@echo off
setlocal enabledelayedexpansion

REM Definimos el rango y el incremento directamente en el script.
set "inicio=154"
set "final=170"
set "incremento=1"

REM Creamos nuestras variables
set "prefijo=MPAA_Ejercicio"
set "extension=.html"
set "plantilla=index.html"

REM Verificamos si el archivo formato.html existe
if not exist "%plantilla%" (
    echo Error: No se encontró el archivo "%plantilla%".
    pause
    exit /b
)

REM Bucle para crear y copiar contenido
for /L %%i in (%inicio%,%incremento%,%final%) do (
    set "archivo=!prefijo!%%i!extension!"
    
    REM Crear archivo vacío
    copy NUL "!archivo!" >nul

    REM Copiar el contenido de formato.html
    type "%plantilla%" > "!archivo!"
)

echo Archivos creados correctamente.
pause