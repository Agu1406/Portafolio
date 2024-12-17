/**
 * No usar la variable "var", está en deshuso y no es profesional, usar
 * let o const en su lugar.
 */

/**
 * Clase Carta
 * Representa una carta de una baraja española con dos propiedades:
 * 
 * palo: un número del 1 al 4 que representa el palo de la carta:
 *       1 - Oros
 *       2 - Espadas
 *       3 - Bastos
 *       4 - Copas
 * 
 * valor: un número del 1 al 10 que representa el valor de la carta:
 *        1 - As
 *        8 - Sota
 *        9 - Caballo
 *        10 - Rey
 * 
 * Métodos:
 * 
 * darValor(): Recibe un número de palo y un valor y los asigna a la carta.
 *             Si los datos son incorrectos, no realiza ninguna acción.
 * 
 * toString(): Devuelve un texto que describe la carta en formato de lectura
 *             (por ejemplo: "As de Oros").
 */

/**
 * Recordamos el ejercicio de cartas del año pasado, lo primero es crear 4 variables
 * globables que controlen la cantidad de cartas instanciadas / creadas de cada
 * tipo para que nunca sean más de 10.
 */
const limiteOro = 0;
const limiteEspadas = 0;
const limiteBastos = 0;
const limiteCopas = 0;
/**
 * Luego, para controlar que palo de cartas estamos creando usamos un array
 * el cual podemos manejar por posiciones (0, 1, 2 y 3) para que una misma
 * función ejecute un bucle cuatro veces creando 10 cartas e intercambiado
 * entre un palo y otro al crearlas, haciendo así 10 de cada una.
 */
const palo = ["Oro", "Espada", "Basto", "Copa"];

/**
 * Función / constructor que permite instanciar objetos del tipo "Carta"
 * aunque los "palos" son String dentro de un array al crearlos el
 * constructor diferencia una de otro usando sus posiciones en el array
 * por eso recibe números (number) y no un String.
 * 
 * @param {number} palo (Oros, Espadas, Bastos o Copas)
 * @param {number} valor (un número entero del 1 al 10)
 */
function Carta (palo, valor) {
    // Verifica si el número que recibe el constrcutor es valido
    if (valor < 1 || valor > 10) {
        console.log("¡Error! El número de carta no es valido.");
    }
    // Verifica si el palo recibido es correcto o no
    if (palo < 0 || palo > 3) {
        console.log("¡Error! El palo recibido no es valido.");
    }

    // Verifica si ya existen 10 cartas de ese palo, si existen, para y da error
    VerificarLimiteCarta(palo);
    
}

/**
 * Esta función recibe un número (palo) y usando un switchcase
 * verifica las variable globales de limites para verificar si
 * ya existen 10 cartas de ese tipo.
 * 
 * @param {number} palo 
 */
function VerificarLimiteCarta(palo) {
    switch (palo) {
        // El caso uno es la posición "0" del palo "Oro".
        case 1:
            if (limiteOro >= 10) {
                console.log("Limite de cartas Oro alcanzado.");
            }
        break;
        case 2:
            if (limiteEspadas >= 10) {
                console.log("Limite de cartas Espada alcanzado.");
            }
        break;
        case 3:
        break;
        case 4:
        break;
        default:
        break;
    }
}

