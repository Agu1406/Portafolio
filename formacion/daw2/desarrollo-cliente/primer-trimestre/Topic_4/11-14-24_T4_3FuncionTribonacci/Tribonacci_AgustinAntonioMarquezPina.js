/**
 * Basado en la sucesión de Fibonacci del recurso de AV "Ejemplo Recursividad", 
 * tenemos la de Tribonacci. En esta última, cada elemento es la suma de los 
 * tres anteriores: 1, 1, 2, 4, 7, 13, 24, etc, debemos de:
 * 
 * 1º) Resuelve la función de forma recursiva, dada una posición.
 * 2º) Resuelve la función de forma iterativa, dada una posición.
 * 
 */

// Función que calcula hasta esa "X" posición de forma recursiva (se llama así mismo)
function tribonacciRecursivo (consulta) {
    /**
     * En Tribonacci si la posición es "1" o "2" directamente el resultado es "1" y
     * siempre, si la posición es "3" el resultado es dos, por eso, solo hacemos
     * calculos genuinamente de la posición "4" en adelante.
     * */
    if ((consulta === 1) || (consulta === 2)) {
        return 1;
    } else if (consulta === 3) {
        return 2;
    }

    // A partir de "4" o más de forma recursiva se llama así mismo.
    const resultado = tribonacciRecursivo (consulta - 1) + tribonacciRecursivo (consulta - 2) + tribonacciRecursivo (consulta - 3);

    // Devuelve cualquiera el que sea el valor de "resultado".
    return resultado;
}

// Función que calcula hasta esa "X" posición de forma iterativa (bucle)
function tribonacciIterativo (consulta) {
    /**
     * En Tribonacci si la posición es "1" o "2" directamente el resultado es "1" y
     * siempre, si la posición es "3" el resultado es dos, por eso, solo hacemos
     * calculos genuinamente de la posición "4" en adelante.
     * */
    if ((consulta === 1) || (consulta === 2)) {
        return 1;
    } else if (consulta === 3) {
        return 2;
    }

    // Creamos tres variables con los tres valores iniciales necesarios para el primer cálculo.
    var primerNumero = 1, segundoNumero = 1, tercerNumero = 2;

    // Creamos la variable que almacenara el resultado de cada iteración
    var resultado;

    // Bucle "for" que en cada iteración va calculando y desplazandose hasta la posición deseada.
    for (posicion = 4; posicion <= consulta; posicion++) {
        // En cada iteración hacemos el calculo tomando en cuenta los dos números anteriores.
        resultado = primerNumero + segundoNumero + tercerNumero;

        // Luego, el primer número se transforma en el segundo, el segundo en el tercero y el tercero en el resultado
        primerNumero = segundoNumero;
        segundoNumero = tercerNumero;
        tercerNumero = resultado;

        // Así en la siguiente vuelta hara el mismo calculo con valores actualizados.
    }

    return resultado;
}

// Función para imprimir los resultados en el HTML
function imprimirResultado() {
    // Sacamos del input del usuario el número que usaremos para consultar Fibonacci (posición)
    var consulta = parseInt(document.getElementById("numero").value);

    // Creamos las dos variables que guardan 1 resultado por cada método (recursivo e iterativo)
    var primerResultado = tribonacciRecursivo(consulta);
    var segundoResultado = tribonacciIterativo(consulta);

    // Guardamos en una constante el elemento HTML cuyo ID sea "mostrar-dos-listas"
    const contenedorDeListas = document.getElementById("mostrar-dos-listas");

    // Creamos dos elementos "div", uno para cada resultado que vamos a mostrar.
    const resultadoRecursivo = document.createElement("div");
    const resultadoIterativo = document.createElement("div");

    // Le agregamos a los dos elementos el atributo "class" para que funcione la hoja de estilos CSS.
    resultadoRecursivo.classList.add("mostrar-dos-listas__lista");
    resultadoIterativo.classList.add("mostrar-dos-listas__lista");

    // Definimos el contenido que habra / existira dentro de esos divs
    resultadoRecursivo.innerHTML = `<h2>Usando función recursiva</h2> <br> <p>Resultado: ${primerResultado}</p><br>`;
    resultadoIterativo.innerHTML = `<h2>Usando función iterativa</h2> <br> <p>Resultado: ${segundoResultado}</p><br>`;

    /**
     * Ahora ya tenemos los dos divs, con el contenido que es un H2 y un parrafo con el resultado, falta meterlos
     * dentro del div que mostrara los resultados, para eso usamos appendChild uno por uno y los añadimos.
     */
    contenedorDeListas.appendChild(resultadoRecursivo);
    contenedorDeListas.appendChild(resultadoIterativo);

    // Como era invisible el contenedor por el "display: none", cambiamos su atributo "style"
    contenedorDeListas.style.opacity = "100%"; // Cambiamos el display para que se muestre
}

// Llamamos a la función para imprimir las listas cuando la página se haya cargado
window.onload = imprimirResultado;