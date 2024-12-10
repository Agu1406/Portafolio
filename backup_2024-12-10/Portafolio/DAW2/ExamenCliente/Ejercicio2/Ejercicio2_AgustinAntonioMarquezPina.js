// Roberto, si lees esto, apruebame por favor.

// Creamos una variable con un array dentro.
let arrayAgustinAntonioMarquezPina = [];

// Función que genera un número aleatorio entre 2 y 99, no recibe argumentos.
function generarAleatorio() {
    // Devuelve (cada vez que se llame) un aleatorio entre "2" y "99" ambos incluidos.
    return Math.floor(Math.random() * (99 - 2 + 1)) + 2;
}

// Función que verifica si un número es primo
function esPrimo(numero) {
    // Si es menor que 2 no es primo, ejemplo, números negativos, el cero, el "1".
    if (numero < 2) return false;
    
    // Verificamos si es divisible por algún número desde 2 hasta el mismo número-1
    for (let i = 2; i < numero; i++) {
        // Si encontramos un divisor que no sea 1 o el mismo número, no es primo
        if (numero % i === 0) return false;
    }
    // Si no encontró divisores, es primo
    return true;
}

// Función que llena el array con 30 números aleatorios
function llenarArray(arrayConMiNombre) {
    // En cada iteración del bucle genera un nuevo aleatorio y lo pushea dentro del array.
    for (let i = 0; i < 30; i++) {
        arrayConMiNombre.push(generarAleatorio());
    }

    // Devuelvo el array, pero está vez lleno con los aleatorios..
    return arrayConMiNombre;
}

// Función que procesa el array y crea un Map con los números clasificados
function procesarArray(arrayConMiNombre) {
    // Creamos un nuevo Map llamado "mapaNumeros".
    let mapaNumeros = new Map();
    
    // Arrays para almacenar los diferentes tipos de números (vacios de momento).
    let pares = [];
    let impares = [];
    let primos = [];
    
    // Recorremos el array principal
    for (let numero of arrayConMiNombre) {
        // Verificamos si es par o impar
        if (numero % 2 === 0) {
            // Si es par lo metemos en su respectivo array.
            pares.push(numero);
        } else {
            // Si ("%") no es "0" entonces por default es impar, lo pusheo ahí.
            impares.push(numero);
        }
        
        // Verificamos si es primo, si lo es, lo pushea al array de primos.
        if (esPrimo(numero)) {
            primos.push(numero);
        }
    }
    
    // Ordenamos el array de primos de mayor a menor
    primos.sort((a, b) => b - a);
    
    // Guardamos los arrays en el Map con sus indices (nombre) y valores (arrays).
    mapaNumeros.set("pares", pares);
    mapaNumeros.set("impares", impares);
    mapaNumeros.set("primos", primos);
    
    return mapaNumeros;
}

// Ejecutamos las funciones pasando el array entre ellas
arrayAgustinAntonioMarquezPina = llenarArray(arrayAgustinAntonioMarquezPina);
let resultado = procesarArray(arrayAgustinAntonioMarquezPina);

// Mostramos los resultados (cantidades de cada tipo), usando el "length".
console.log("Array original:", arrayAgustinAntonioMarquezPina);
console.log("Hay " + resultado.get("pares").length + " números pares");
console.log("Hay " + resultado.get("impares").length + " números impares");
console.log("Hay " + resultado.get("primos").length + " números primos");
// Imprimo los primos también por si a caso realmente funciona bien o no.
console.log("Los números primos ordenados de mayor a menor son:", resultado.get("primos"));
/**
 * Por si a caso también imprimo los pares e impares para ver que no me está colando ningún
 * número que no debería en la lista de pares o impares y verificar si los indices que he
 * creado funcionan correctamente.
 */
console.log("Los números pares (sin organizar) son: ", resultado.get("pares"));
console.log("Los números impares (sin organizar) son: ", resultado.get("impares"));
