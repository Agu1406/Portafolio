// Roberto, si lees esto, apruebame por favor.

/**
 * Si no le indico a la función que es el parametro que recibe,
 * la lista desplegable de métodos no aparece cuando pongo "."
 * al final, de por ejemplo, el array "numeros", esto me lo
 * enseño Aitor, gracias Aitor <3.
 * 
 * Recibe entre "1" y "n" números, tantos como quiera el usuario.
 * @param {number[]} numeros
 */
(function calcularMediaArmonica(...numeros) {
    // Si el array o los arguemtos llegan vacios, avisa por consola del error.
    if (numeros.length === 0) {
        console.log("No se han proporcionado números");
        // Hace un return vacio para que no se ejecute el resto del código.
        return; // Esto me lo enseño K3 (Adrían);
    }

    // Creamos un Set con los números para verificar si contiene un 0 o un NaN (Not a Number).
    const numerosSet = new Set(numeros);
    // Si el Set contiene un 0 o un NaN, avisa por consola del error y hace un return vacio.
    if (numerosSet.has(0) || numeros.some(num => isNaN(num))) {
        console.log("Por favor, introduce solo números válidos distintos de 0");
        // Hace un return vacio para que no se ejecute el resto del código.
        return // Esto me lo enseño K3 (Adrían);
    }

    // Inicializa la variable para la suma de los números del array.
    let sumaRecíprocos = 0;
    
    // Recorre el array sumando los números y sus valores dividos fraccionados (vaya lio).
    for (let numero of numeros) {
        // Suma el recíproco (1/2) a la suma total
        sumaRecíprocos += 1 / numero;
    }
    
    // Esta es la fórmula correcta: Media Armonica = length / (n(1/2) + n(1/4) + n(1/6)) si uso de ejemplo 2, 4 y 6.
    const mediaArmonica = numeros.length / sumaRecíprocos;
    
    // Si no es con comillas "``" no funciona, no se porqué.
    console.log(`La media armónica de los números ${numeros.toString()} es: ${mediaArmonica}`);
})
// Probamos la función con los números dados por Roberto a ver si funcionan (sería 3,27272727)
(2, 4, 6);
