<?php
/*
Objetivos de la actividad:

I.- Desarrollar una aplicación PHP que gestiones excepciones lanzadas por una función:

- Crea una función para calcular y devolver el resultado de dividir dos números.
- Haz que la función anterior lance una excepción y su mensaje correspondiente, cuando el divisor sea cero.
- Crea código para probar lo anterior gestionando las posibles excepciones, en dos casos:
- División por cero, capturando la excepción lanzada.
- Divisor mayor que cero.

II.- Desarrolla lo anterior con la clase DivisionByZeroError https://www.php.net/manual/es/class.divisionbyzeroerror.php

III.- Tarea_2_4B Mejora la tarea 2_3 para que controle si el argumento es negativo, utilizando una excepción.
*/

/**
 * Summary of dividirEntreCeroConIntDiv
 * 
 * Función que utiliza un método / función propio de PHP para dividir dos números
 * enteros, el cual es "intdiv" pasand por agurmentos el dividendo y el divisor.
 * @return void
 */
function dividirEntreCeroConIntDiv () {
    // Bloque try-catch que intenta realizar la operación y atrapa una excepción de división entre cero.
    try {
        echo intdiv(2, 0);
    } catch (DivisionByZeroError $e) {
        echo "Caught DivisionByZeroError!\n";
    }
}

/**
 * Función que divide manualmente dos números enteros el uno con el otro,
 * a diferencia de la primera función no utiliza ningún método o función
 * estandar de PHP y realiza la operación manualmente con el operador "/"
 * @return void
 */
function dividirEntreCero () {
    // Bloque try-catch que intenta realizar la operación y atrapa una excepción de división entre cero.
    try {
        echo (2 / 0);
    } catch (DivisionByZeroError $e) {
        echo "Caught DivisionByZeroError!\n";
    }
}




