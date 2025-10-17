/**
 * No usar la variable "var", está en deshuso y no es profesional, usar
 * let o const en su lugar.
 */

// Límites globales de creación de cartas.
let limiteOro = 0;
let limiteEspadas = 0;
let limiteBastos = 0;
let limiteCopas = 0;
// Array que tiene los tipos de palos posibles.
const palo = ["Oro", "Espada", "Basto", "Copa"];

/**
 * Función para crear objetos tipo "Carta".
 * 
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
 */
function Carta(palo, valor) {
    // Verifica si el valor y el palo son válidos
    if (valor < 1 || valor > 10) {
        console.log("¡Error! El número de carta no es valido.");
        return;
    }
    if (palo < 1 || palo > 4) {
        console.log("¡Error! El palo recibido no es valido.");
        return;
    }

    // Verifica si ya existen 10 cartas de ese palo, si existen, para y da error
    VerificarLimiteCarta(palo);

    // Define el valor de la carta (As, Sota, Caballo, Rey o número)
    this.valor = definirValor(valor);
    // Uso del array `palo` para definir el nombre del palo
    this.palo = definirPalo(palo);
}

// Método que define el valor de una carta (As, Sota, Caballo, Rey o número).
function definirValor(valor) {
    switch (valor) {
        case 1:
            return "As";
        case 8:
            return "Sota";
        case 9:
            return "Caballo";
        case 10:
            return "Rey";
        default:
            return valor;
    }
}

/**
 * Función que recibe el palo desde el constructor como un número pero reemplaza el número por su valor en string.
 * 
 * @param {number} palo 
 * @returns el palo (string)
 */
function definirPalo(palo) {
    return ["Oro", "Espada", "Basto", "Copa"][palo - 1];
}

/**
 * Función que recibe un número (palo) y usando un switch-case verifica las variables globales de límites para verificar si ya existen 10 cartas de ese tipo.
 * 
 * @param {number} palo 
 */
function VerificarLimiteCarta(palo) {
    switch (palo) {
        case 1:
            if (limiteOro >= 10) {
                console.log("Límite de cartas Oro alcanzado.");
                return;
            }
            limiteOro++;
            break;
        case 2:
            if (limiteEspadas >= 10) {
                console.log("Límite de cartas Espada alcanzado.");
                return;
            }
            limiteEspadas++;
            break;
        case 3:
            if (limiteBastos >= 10) {
                console.log("Límite de cartas Basto alcanzado.");
                return;
            }
            limiteBastos++;
            break;
        case 4:
            if (limiteCopas >= 10) {
                console.log("Límite de cartas Copa alcanzado.");
                return;
            }
            limiteCopas++;
            break;
        default:
            console.log("¡Error! El palo recibido no es correcto.");
            return;
    }
}

/**
 * Método que permite convertir una carta a string.
 * @returns String.
 */
Carta.prototype.toString = function() {
    return `Carta ${this.valor} de ${this.palo}`;
};

/**
 * Función que permite mezclar las cartas de un array.
 * @param {Array} baraja - El array que contiene las cartas.
 */
function MezclarCartas(baraja) {
    let indiceCartas = baraja.length;
    while (indiceCartas !== 0) {
        const randomIndex = Math.floor(Math.random() * indiceCartas);
        [baraja[indiceCartas - 1], baraja[randomIndex]] = [baraja[randomIndex], baraja[indiceCartas - 1]];
        indiceCartas--;
    }
}

/**
 * Función / constructor que permite instanciar objetos del tipo "Carta".
 */
function crearCartas() {
    const baraja = [];
    for (let palo = 1; palo <= 4; palo++) {
        for (let valor = 1; valor <= 10; valor++) {
            const nuevaCarta = new Carta(palo, valor);
            baraja.push(nuevaCarta);
        }
    }
    return baraja;
}

// Creamos una baraja.
const baraja = crearCartas();
// Mostramos las cartas.
console.log("CARTAS ORDENADAS: ");
baraja.forEach(carta => console.log(carta.toString()));
// Mezclamos las cartas.
MezclarCartas(baraja);
// Mostramos las cartas mezcladas.
console.log("CARTAS DESORDENADAS ");
baraja.forEach(carta => console.log(carta.toString()));
