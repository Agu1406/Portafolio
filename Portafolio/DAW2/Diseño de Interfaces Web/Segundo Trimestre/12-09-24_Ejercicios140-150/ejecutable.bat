@echo off
REM Habilita la expansión retardada de variables
setlocal enabledelayedexpansion

REM Creamos nuestras variables, la primera es el prefijo de los archivos
REM la segunda es la extensión y la tercera es el número a partir del
REM cual el bucle crea los arhivo (prefijo + número + extensión).
set "prefijo=MarquezPina_AgustinAntonio_Ejercicio"
set "extension=.html"

REM Indicamos el rango (desde donde hasta donde crear los documentos)
REM y también el incremento (en mi caso de "1") del bucle.
set "inicio=140"
set "final=150"
set "incremento=1"

REM Usamos un bucle "for" que cree un documento con prefijo y extensión.
for /L %%i in (!inicio!,!incremento!,!final!) do (
    echo. > !prefijo!%%i!extension!
)

echo Archivos creados correctamente.
pause
