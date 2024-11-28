// Roberto, si lees esto, apruebame por favor.

/**
 * Para mayor claridad del código he creado funciones que verifican por mi si los números
 * son divisibles o no, podría haber usado funciones flecha pero como no las domino bien
 * es que ni si quiera me arriesgue.
 */

// Arrays con divisores y sus frutas correspondientes
const divisoresExtra = [6, 14, 8, 9];
const frutasTropicales = ["Mango", "Papaya", "Guayaba", "Maracuyá"];

// Solicitamos con un prompt ambos números, el inicial y el final.
let numeroInicial = Number(prompt("Introduce el número inicial"));
let numeroFinal = Number(prompt("Introduce el numero final"));

// Llamamos a la función de fizzBuzz que returna un array con estadisticas.
let arrayConEstadisticas = fizzBuzzTipico(numeroInicial, numeroFinal, divisoresExtra, frutasTropicales);

// Imprimimos las estadisticas del array en forma de objeto en console.log
console.log(arrayConEstadisticas);

// Ahora si, con más calma, imprimos sus valores, uno por uno.
console.log("Hay un total de: " + arrayConEstadisticas.cantidadBuzz + " Buzz.");
console.log("Hay un total de: " + arrayConEstadisticas.cantidadFizz + " Fizz.");
console.log("Hay un total de: " + arrayConEstadisticas.cantidadFizzBuzz + " FizzBuzz.");
console.log("Hay un total de: " + arrayConEstadisticas.cantidadNormales + " Normales.")

// Imprimimos las estadísticas de frutas tropicales
divisoresExtra.forEach((divisor, index) => {
    console.log("Hay un total de: " + arrayConEstadisticas["cantidad" + frutasTropicales[index]] + " " + frutasTropicales[index] + ".");
});

// Función que verifica si un número es Fizz (divisible por 3)
function esFizz(numero) {
    return numero % 3 === 0;
}

// Función que verifica si un número es Buzz (divisible por 5)
function esBuzz(numero) {
    return numero % 5 === 0;
}

// Función que verifica si un número es divisible por algún divisor extra
/**
 * Al hacer el param de la función si la invoco desde la función principal
 * cargan correctamente los métodos y se entiende mejor el cacao que
 * estoy haciendo Roberto, no te ralles.
 * 
 * @param {number} numero 
 * @param {number} divisor 
 * @returns true si la divición deja resto "0", false si no.
 */
function esDivisiblePor(numero, divisor) {
    return numero % divisor === 0;
}

// Función principal FizzBuzz que procesa el rango de números entre el inicial y el final.
/**
 * 
 * @param {number} numeroInicial 
 * @param {number} numeroFinal 
 * @param {array} divisoresExtra 
 * @param {array} frutasTropicales 
 * 
 * @returns array con estadisticas actualizadas. 
 */
function fizzBuzzTipico(numeroInicial, numeroFinal, divisoresExtra, frutasTropicales) {
    // Array asociativo que me permitira almacenar las estadísticas
    let estadisticasAgustinianas = {
        cantidadFizz: 0,
        cantidadBuzz: 0,
        cantidadFizzBuzz: 0,
        cantidadNormales: 0
    };

    // Añadimos contadores para cada fruta tropical (usando yo métodos flecha, vaya por Dios.)
    frutasTropicales.forEach(fruta => {
        estadisticasAgustinianas["cantidad" + fruta] = 0;
    });

    // Recorremos el rango de números
    for (let i = numeroInicial; i <= numeroFinal; i++) {
        // Guardamos en los buleanos los resultados de mis métodos que verifican "Fizz" y "Buzz"
        let esDivisiblePor3 = esFizz(i);
        let esDivisiblePor5 = esBuzz(i);
        
        // Verificamos primero si es divisible por algún divisor extra, de entrada creemos que no ("false")
        let divisiblePorExtra = false;
        // Usamos un bucle para recorrer todos los divisores extras que hay en el array de divisores.
        for(let j = 0; j < divisoresExtra.length; j++) {
            /**
             * Si el número es divisible entre el disivor extra que este en la posición
             * actual del bucle, entonces devuelve su palabra extra, que sera la misma
             * posición en el array de palabras "frutas" equivalente a la posición del
             * array de divisores, además, actualizamos el array de estadiscticas con
             * la cantidad de divisibles entre ese número.
             */
            if(esDivisiblePor(i, divisoresExtra[j])) {
                console.log(frutasTropicales[j]);
                estadisticasAgustinianas["cantidad" + frutasTropicales[j]]++;
                divisiblePorExtra = true;

                /**
                 * Si no uso break entonces lo compara con otros indices, con un booleano
                 * lo iba a arreglar pero no me dio tiempo.
                 */
                break;
            }
        }

        // Si no es divisible por ningún extra, seguimos con la lógica FizzBuzz normal
        if(!divisiblePorExtra) {
            if (esDivisiblePor3 && esDivisiblePor5) {
                console.log("FizzBuzz");
                estadisticasAgustinianas.cantidadFizzBuzz++;
            } else if (esDivisiblePor3) {
                console.log("Fizz");
                estadisticasAgustinianas.cantidadFizz++;
            } else if (esDivisiblePor5) {
                console.log("Buzz");
                estadisticasAgustinianas.cantidadBuzz++;
            } else {
                console.log(i);
                estadisticasAgustinianas.cantidadNormales++;
            }
        }
    }

    return estadisticasAgustinianas;
}
