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

function jugarLoteria () {

    // Creamos una "constante" cuyo valor es "[]" indicando, por lo tanto, que es un array.
    const arrayNumerosAleatorios = [];

    // Creamos la variable de valor cambiante por cada aleatorio generado.
    let numeroAleatorio;

    // Bucle que genera "49" veces un aleatorio y lo "pushea" dentro del array.
    for (let posicion = 0; posicion < 50; posicion++) {
        
        // Llamamos la función, generamos el aleatorio y lo guardamos.
        numeroAleatorio = generarAleatorios();

        // Pusheamos ese "aleatorio" dentro del array.
        arrayNumerosAleatorios.push(numeroAleatorio);
    }
}

/**
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

/**
 * Método / Función que desordena el contendio de cualquier array utilizando el
 * algoritmo de desordenamiento de Fisher-Yates que requiere una sol pasada por
 * todo el array con la misma probabilida de desorden para todos los números
 * o valores del array, funciona con dos indices, los cuales son, en honor al
 * nombre del algoritmo, que son "F" (posición en el array) y "Y" que es donde
 * se intercambiara el valor que se encuentre en "F".
 * @param {*} arrayNumerosAleatorios 
 */
function desordenarArray (arrayNumerosAleatorios) {

}