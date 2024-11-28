 // Roberto, si lees esto, apruebame por favor.

/**
 * Para mayor claridad del código he creado funciones que verifican por mi si los números
 * son divisibles o no, podría haber usado funciones flecha pero como no las domino bien
 * es que ni si quiera me arriesgue.
 */

// Solicitamos con un prompt ambos números, el inicial y el final.
let numeroInicial = Number(prompt("Introduce el número inicial"));
let numeroFinal = Number(prompt("Introduce el numero final"));

// Llamamos a la función de fizzBuzz que returna un array con estadisticas.
let arrayConEstadisticas = fizzBuzzTipico(numeroInicial, numeroFinal);

// Imprimimos las estadisticas del array en forma de objeto en console.log
console.log(arrayConEstadisticas);

// Ahora si, con más calma, imprimos sus valores, uno por uno.
console.log("Hay un total de: " + arrayConEstadisticas.cantidadBuzz + " Buzz.");
console.log("Hay un total de: " + arrayConEstadisticas.cantidadFizz + " Fizz.");
console.log("Hay un total de: " + arrayConEstadisticas.cantidadFizzBuzz + " FizzBuzz.");
console.log("Hay un total de: " + arrayConEstadisticas.cantidadNormales + " Normales.")


// Función que verifica si un número es Fizz (divisible por 3)
function esFizz(numero) {
    return numero % 3 === 0;
}

// Función que verifica si un número es Buzz (divisible por 5)
function esBuzz(numero) {
    return numero % 5 === 0;
}

// Función que verifica si un número es Extra (divisible por 6)
function esExtra(numero) {
    return numero % 6 === 0;
}

// Función principal FizzBuzz que procesa el rango de números entre el inicial y el final.
function fizzBuzzTipico(numeroInicial, numeroFinal) {
    // Array asociativo que me permitira almacenar las estadísticas
    let estadisticasAgustinianas = {
        cantidadExtra: 0,
        cantidadFizz: 0,
        cantidadBuzz: 0,
        cantidadFizzBuzz: 0,
        cantidadNormales: 0
    };

    // Recorremos el rango de números
    for (let posicion = numeroInicial; posicion <= numeroFinal; posicion++) {
        // Guardamos en dos buleanos los resultados de mis métodos que verifican "Fizz" o "Buzz".
        let esDivisiblePor3 = esFizz(posicion);
        let esDivisiblePor5 = esBuzz(posicion);
        let esDivisiblePorExtra = esExtra(posicion);

        /**
         * Este sistema de if-else usando mis métodos funciona de forma tal
         * que si es divisible entre ambos, "3" y "5" imprime "FizzBuzz" y
         * actualiza el contador de estadisticas de FizzBuzz, pero si solo
         * son divisibles entre "3" o "5" imprime sus respectivos valores
         * y actualiza sus estadisticas, por descarte, si no es ninguna
         * de las anteriores, el else, por su puesto, imprime el número
         * a secas y actualiza esa estadistica.
         */
        if (esDivisiblePor3 && esDivisiblePor5) {
            console.log("FizzBuzz");
            // Actualizo las estadisticas del array asociativo.
            estadisticasAgustinianas.cantidadFizzBuzz++;
        } else if (esDivisiblePor3) {
            console.log("Fizz");
            // Actualizo las estadisticas del array asociativo.
            estadisticasAgustinianas.cantidadFizz++;
        } else if (esDivisiblePor5) {
            console.log("Buzz");
            // Actualizo las estadisticas del array asociativo.
            estadisticasAgustinianas.cantidadBuzz++;
        } else if (esDivisiblePorExtra) {
            console.log("Extra");
            // Actualizo las estadisticad del extra (parte 2 del examen)
            estadisticasAgustinianas.cantidadExtra++;
        } else {
            console.log(posicion);
            // Actualizo las estadisticas del array asociativo.
            estadisticasAgustinianas.cantidadNormales++;
        }
    }

    // Returno el array con las estadisticas actualizadas.
    return estadisticasAgustinianas;
}
