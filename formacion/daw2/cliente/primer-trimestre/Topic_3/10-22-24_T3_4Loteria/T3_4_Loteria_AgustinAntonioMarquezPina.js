/**
 * 
 * Crear una aplicación web que muestre 10 combinaciones para jugar a la lotería primitiva.
 * 
 * Las combinaciones son seis números del 1 al 49, pero hay que tener en cuenta que no se pueden 
 * repetir los números. Tener esto en cuenta porque no valdría con generar seis números 
 * aleatorios del 1 al 49.
 * 
 * Crearemos un array con 49 elementos con cada uno de los números del 1 al 49.
 * 
 * Después moveremos los elementos de forma aleatoria en el array. Lo haremos intercambiando 50 
 * veces (o el número que indiquemos) los números del array de forma aleatorio.
 * 
 * Finalmente, mostraremos los seis primeros números del array cada vez que revolvamos éste.
 */

function jugarLoteria() {
    // Crear el array que contiene los números del 1 al 49.
    let arrayNumerosAleatorios = [];

    // Llenar el array con números del 1 al 49.
    for (let i = 1; i <= 49; i++) {
        arrayNumerosAleatorios.push(i);
    }

    // Selecciona la div en la que se mostrarán las combinaciones y la vacía.
    const mostrarCombinaciones = document.querySelector('.mostrarCombinaciones');
    mostrarCombinaciones.innerHTML = '<ul>';

    // Generar y mostrar 10 combinaciones de 6 números cada una.
    for (let i = 0; i < 10; i++) {
        // Desordenar el array en cada iteración.
        desordenarArray(arrayNumerosAleatorios);

        // Tomar los primeros 6 números del array desordenado.
        const combinacion = arrayNumerosAleatorios.slice(0, 6);
        
        // Crear un elemento de lista con la combinación actual y añadirlo a la div.
        const combinacionElement = document.createElement('li');
        combinacionElement.textContent = `Combinación ${i + 1}: ${combinacion.join(", ")}`;
        mostrarCombinaciones.appendChild(combinacionElement);
    }

    mostrarCombinaciones.innerHTML += '</ul>';
}

/**
 * Método / Función que desordena el contendio de cualquier array utilizando el
 * algoritmo de desordenamiento de Fisher-Yates que requiere una sol pasada por
 * todo el array con la misma probabilida de desorden para todos los números
 * o valores del array, funciona con dos indices, los cuales son, en honor al
 * nombre del algoritmo, que son "posicion" y "posicionAleatoria" que es donde
 * se intercambiara el valor que se encuentre en "posicion".
 * @param {*} arrayNumerosAleatorios 
 */
function desordenarArray (arrayNumerosAleatorios) {

    /**
     * La posición sera el array.lenght -1, ¿Por que "-1"? Porque si hay 50 elementos en el Array, 
     * el indice de la ultima posición es "49", no "50", luego, internamente, la posición aleatoria
     * siempre está limitada a ser una posición entre "0" y la posición actual del bucle, porque
     * si ya un número fue asignado más alla de dicha posición, esa posición es final y no lo
     * volvemos a mezclar.
     */
    for (let posicion = arrayNumerosAleatorios.length -1; posicion > 0; posicion--) {
        // Elegimos una posición aleatoria dentro del array entre "0" y la "posicion" actual del bucle "+1" para incluir la posición también.
        const posicionAleatoria = Math.floor(Math.random() * (posicion + 1));

        // Utulizamos destructuración en JavaScript para intercambiar los valores de posición con los de posición aleatoria.
        [arrayNumerosAleatorios[posicion], arrayNumerosAleatorios[posicionAleatoria]] = [arrayNumerosAleatorios[posicionAleatoria], arrayNumerosAleatorios[posicion]];
    }
}

/**
 * @deprecated En deshuso porque erroneamente generaba 49 números aleatorios del "0" al "49" pero ya que
 * los números no se pueden repetir dentro del array, realmente solo tenia que llenar el array con
 * todos los números del "0" al "49" y luego desordenarlos.
 * 
 * Función / Método que genera números aleatorios, según
 * nuestra formula del "0" al "49" ambos incluidos.
 * */
function generarAleatorios () {

    // Número minimo (incluido) para la generación de pseudo-aleatorios.
    const min = 0;
    // Número maximo (incluido) para la generación de pseudo-aleatorios.
    const max = 49;
    
    /**
     * Usamos "Math.floor" porque los números generados son decimales y redondeamos
     * estos números al número entero inferior más cercano, ejem, 0.433 es "0".
     * 
     * Usamos "Math.random" que genera "aleatorios" entre "0" y "1" decimales, ejemplo,
     * puede ser "0.423432" o "0.9868".
     * 
     * Ya que queremos elegir nosotros el rango, multiplicamos ese rango entre "0" y "1"
     * por el número más pequeño que deseamos generar y el más grande, desplazados "1"
     * para llegar siempre al maximo.
     * 
     * ¿Como se entiende esto? nuestro número maximo posible es "49" y el más pequeño es
     * "0", entonces los números seran generados como maximo a "50", que podría ser, por
     * los decimales, ejemplo, el "49.98868", que con "floor", seria el "49".
     */
    let numeroAleatorio = Math.floor(Math.random() * (max - min + 1)) + min;
    
    // Dejamos constancia en la consola de cada número aleatorio generado.
    console.log(numeroAleatorio);

    // Devolvemos el "aleatorio" generado cada que llamamos la función.
    return numeroAleatorio;
}