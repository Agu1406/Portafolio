/**
 * Realiza un fichero que contenga un array de tamaño 40 con números naturales
 * comprendidos entre 1 y 50.
 * 
 * 1º) Crea una función llamada pares que reciba como parámetro el array.
 * 
 * 2º) La función deberá recorrer un array dado y devolver la cantidad de pares que hay en él.
 * 
 * 3º) Crea una función llamada impares que reciba parámetro el array.
 * 
 * 4º) La función hará lo mismo que la anterior, pero con la cantidad de impares.
 * 
 * 5º) Crea una estructura que indique el número de pares y el número de impares de un array,
 * utilizando las funciones anteriores.
 * 
 * 6º) Imprime la estructura en la página.
 * 
 * 7º) Mejora el procedimiento anterior, con una única función.
 */

// Usando la función, genero 40 números aleatorios entre "0" y "50" y los guardo en el array.
var arrayConNumeros = generarAleatorios();

// Usando la función de pares, guardo los números pares del array en otro array.
var arrayConPares = numerosPares(arrayConNumeros);

// Usando la función de impares, guardo los números impares del array en otro array.
var arrayConImpares = numerosImpares(arrayConNumeros);


function generarAleatorios () {
    // Variable que, luego, contendra, los números generados.
    var arrayConNumeros = [];

    // Bucle que, 40 veces consecutivas, genera un número entre  "1" y "50"
    for (let posicion = 0; posicion < 40; posicion++) {
        
        // Generamos un número "aleatorio" entre "0" y "50".
        const numeroAelatorio = Math.floor(Math.random() * 50) + 1;

        // Pusheamos ese número dentro del array.
        arrayConNumeros.push(numeroAelatorio);
    }

    // Fuera del bucle, ya generados los 40 números, hacemos el return
    return arrayConNumeros;

}

function numerosPares (arrayConNumeros) {
    var arrayConPares = [];

    // Bucle "for" que recorre una por una todas las posiciones de un array.
    for (let posicion = 0; posicion < arrayConNumeros.length; posicion++) {
        // Guardamos en una variable el "X" número que este en la "X" posición del array.
        const numeroEnPosicion = arrayConNumeros[posicion];

        // Si el resto de dividir el número entre "2" es "0", es par.
        if (numeroEnPosicion % 2 == 0) {
            // Pusheamos dentro del array los pares
            arrayConPares.push(numeroEnPosicion);
        }
        
    }

    // Returnamos el array que solo tiene números pares
    return arrayConPares;
}

function numerosImpares (arrayConNumeros) {
    var arrayConImpares = [];

    // Bucle "for" que recorre una por una todas las posiciones de un array.
    for (let posicion = 0; posicion < arrayConNumeros.length; posicion++) {
        // Guardamos en una variable el "X" número que este en la "X" posición del array.
        const numeroEnPosicion = arrayConNumeros[posicion];

        // Si el resto de dividir el número entre "2", no es "0", es impar.
        if (numeroEnPosicion % 2 !== 0) {
            // Pusheamos dentro del array los pares
            arrayConImpares.push(numeroEnPosicion);
        }
        
    }

    // Returnamos el array que solo tiene números pares
    return arrayConImpares;
}

// Función para imprimir los resultados en el HTML
function imprimirListas() {
    const contenedorDeListas = document.getElementById("mostrar-dos-listas");

    // Crear un elemento de lista para los números pares
    const listaPares = document.createElement("div");
    // A ese elemento "div" le agrega la clase que definimos para los estilos
    listaPares.classList.add("mostrar-dos-listas__lista");
    // Dentro del div con "innerHTML" agregamos un H2 y el contenido del array en forma de lista "ul"
    listaPares.innerHTML = `<h2>Números Pares:</h2><ul>${arrayConPares.map(num => `<li>${num}</li>`).join('')}</ul>`;
    // al contenedor agregamos esta lista recien creada.
    contenedorDeListas.appendChild(listaPares);

    // Crear un elemento de lista para los números impares
    const listaImpares = document.createElement("div");
    // A ese elemento "div" le agrega la clase que definimos para los estilos
    listaImpares.classList.add("mostrar-dos-listas__lista");
    // Dentro del div con "innerHTML" agregamos un H2 y el contenido del array en forma de lista "ul"
    listaImpares.innerHTML = `<h2>Números Impares:</h2><ul>${arrayConImpares.map(num => `<li>${num}</li>`).join('')}</ul>`;
    // al contenedor agregamos esta lista recien creada.
    contenedorDeListas.appendChild(listaImpares);
}

// Llamamos a la función para imprimir las listas cuando la página se haya cargado
window.onload = imprimirListas;