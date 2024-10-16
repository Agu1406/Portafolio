/*
En el código de ejemplo está documentado todo el código, sus errores perceptibles a primera vista
y aquí, por lo tanto, se hara el código corregido con una mejor estructura.

¿Diferencias entre "Const", "Var" y "Let"?

Una variable "Const" una vez le es asignado un valor, este no puede cambiar, además, si intentamos
usar variable "Const" sin inicializar nos llevaremos un "ReferenceErro"

Una variable "Var" solo sera utilizable dentro de la misma función o bloque donde se declare, su
valor se puede modificar y ser reasignado, a diferencia de "Const" y "let", una variable "var"
si se intenta utilizar sin ser inicializada entra un valor parecido a 'nulo' el cual es un
"Undefined" pero no explotara el código.

Una variable "let" puede cambiar de valor o ser reasignado como una variable "var" pero es como
una "Const", si se intenta llamar sin haberse inicilizado nos lanzara una excepción de
"ReferenceError".
*/

// En el contexto de este ejercicio "A" siempre sera un seís, su valor no cambiara, usaremos "Const", igual pasa con "B"
const A = 6; B = 6;

/* Diferencia si que podra cambiar de valor, pero como no se espera usar sin inicializarse uso "let" en lugar de "var"
¿Por que su valor es "NaN"? "NaN" significa "Not Number", esto se usa para poder controlar mejor, por ejemplo, usando
if-else si un valor es un número valido o no.*/
let diferencia = NaN;

// Se inician ambas variables como "var" porque sus resultados pueden cambiar y si no se definen pueden ser "undifined" (false).
var suma = 1, resta = 1; logrado = false;

/**
 * Esta función verifica si diferencia es un número diferente de "0", si lo es, entonces
 * es un "true" y se ejecuta el if interno, si es "0" entonces "false" y el if no se
 * ejecuta, en cualquiera de los dos casos,se ejectura el "console log", como la variable
 * es "var" existe en toda la función, incluso si el "if" no se ejecuta y en caso de un
 * "false" el console log, por la naturaleza de una variable "var" imprimiria "undefined",
 * como ambas variables, "A" y "B" son 6, la diferencia es la resta, por lo tanto un "0"
 * y por lo tanto un "false" por lo que "logrado" nunca se vuelve "true" y el console log
 * devuelve / imprime un "undefined".
 */
function conseguido () {
    if (diferencia) {
        logrado = true;
    }

    console.log(logrado);
} 

/**
 * Si "A" es un cualquier número diferente de "0" se ejectura el "if", en cualquier otro caso, no.
 */
if (A) {
    console.log('Los valores a operar (sumar y restar) son ' + A + ' y ' + B + '.');
}

/**
 * Como el valor original de "suma" es un "1" y por lo tanto es diferente de "0" se considera "true",
 * por lo que el "if" se ejecuta.
 */
if (suma) {

    console.log('Ejecutando la suma...');
    
    console.log('La suma es: ' + (A + B));

}

/**
 * Como el valor original de "resta" es un "1" y por lo tanto es diferente de "0" se considera "true",
 * por lo que el "if" se ejecuta.
 */
if (resta) {

    console.log('Ejecutando la resta...');
    
    console.log('La suma es: ' + (A - B));

    // "Diferencia" es el "resto" que queda de la resta de "A" y "B"
    diferencia = A - B;

}

// Llamamos a la función de "conseguido" al principio del código.
conseguido ();

// Usando ternarios imprimimos el estado actual del booleano "logrado" de dicha función
logrado ? console.log("¡Hurra!") : console.log("¡Me cago en to'");

