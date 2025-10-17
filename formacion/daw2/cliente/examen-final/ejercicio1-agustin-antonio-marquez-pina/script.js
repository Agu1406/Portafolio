// Array proporcionado por el profesor
let plantillaBetis =
    // Saltos de lina para que no quede tan ancho, nada raro.
    ["13 Adrián", "2 Bellerín", "5 Bartra",
        "23 Sabaly", "4 Johnny Cardoso", "16 Altimira",
        "20 Lo Celso", "22 Isco", "7 Antony", "9 Chumy Ávila", "19 Cucho",];


// SECCIÓN DEL CÓDIGO ENCARGADA DE LOS EVENTOS.

// Rescatamos el botón del HTML para crearle luego un evento.
let botonOriginal = document.getElementById("mostrarOriginal");
let botonOrdenado = document.getElementById("mostrarOrdenado");
let botonDemorado = document.getElementById("mostrarDemorado");

// Creamos un eventListener del tipo click.
botonOrdenado.addEventListener("click", mostrarPlantillaOrdenada);
botonOriginal.addEventListener("click", mostrarPlantillaOriginal);
botonDemorado.addEventListener("click", mostrarPlantillaDemorada);

// SECCIÓN DEL CÓDIGO QUE SE ENCARGA DE ORDENAR EL ARRAY

// Rescatamos del HTML el "div" donde meterte la lista.
let div = document.getElementById("jugadores");






function mostrarPlantillaOrdenada() {

    div.innerHTML = "";

    // Creamos un nuevo elemento DOM tipo "ul" (es una lista).
    let lista = document.createElement("ul");

    /**
     * Usamos innerHTML para inyectar código dentro de la lista
     * el código filtrado sera el array "plantillaBetis" ordenado
     * para eso usamos un sort que compara A con B (todos los elementos)
     * del Arraylist, sabemos que el nombre del jugador esta separado
     * por un espacio en blanco, ejemplo: "1 Agustiín", con Split
     * separo los nombres de lo9s números y lo que realmente comparo para
     * ordenarlos (sort) son los números/dorsal de los jugadores.
     */
    let plantillaBetisOrdenada = plantillaBetis.sort((a, b) => {
        // Extraer los números de dorsal
        const dorsalA = parseInt(a.split(" ")[0]);
        const dorsalB = parseInt(b.split(" ")[0]);

        // Comparar los dorsales
        return dorsalA - dorsalB;
    });

    // Bucle que mete uno por uno los jugadores dentro de la lista.
    for (let jugador in plantillaBetisOrdenada) {
        // En cada vuelta creamos un elemento del tipo "li"
        let elemento = document.createElement("li");
        // Metemos dentro del elemento un jugador.
        elemento.innerHTML = `<li>${plantillaBetisOrdenada[jugador]}</li>`;
        // Hacemos apendChild a la lista (ul)
        lista.appendChild(elemento);
    }

    // Inyectamos la lista (ul) dentro de main.
    div.appendChild(lista);
}

function mostrarPlantillaOriginal() {
    div.innerHTML = "";

    // Creamos un nuevo elemento DOM tipo "ul" (es una lista).
    let lista2 = document.createElement("ul");

    // Bucle que mete uno por uno los jugadores dentro de la lista.
    for (let jugador in plantillaBetis) {
        // En cada vuelta creamos un elemento del tipo "li"
        let elemento = document.createElement("li");
        // Metemos dentro del elemento un jugador.
        elemento.innerHTML = `<li>${plantillaBetis[jugador]}</li>`;
        // Hacemos apendChild a la lista (ul)
        lista2.appendChild(elemento);
    }
    // Inyectamos la lista (ul) dentro de main.
    div.appendChild(lista2);
}

// Función para mostrar jugadores con retraso
function mostrarPlantillaDemorada() {
    // Limpio el div para borrar cualquier contenido anterior.
    div.innerHTML = '';

    // Creo una plantilla del betis ordenada recilando código.
    let plantillaBetisOrdenada = plantillaBetis.sort((a, b) => {
        // Extraer los números de dorsal
        const dorsalA = parseInt(a.split(" ")[0]);
        const dorsalB = parseInt(b.split(" ")[0]);

        // Comparar los dorsales
        return dorsalA - dorsalB;
    });

    plantillaBetisOrdenada.forEach((jugador, demora) => {
        setTimeout(() => {
            // Crear elemento para el jugador
            let elemento = document.createElement("li");

            // No se como filtrar pares/impares rapidamente.
            elemento.setAttribute("style", "color: white;");
            elemento.textContent = jugador;
            div.appendChild(elemento);

        }, demora * 1000); // 1 segundo de retraso entre cada jugador
    });
}


