/**
 * No utilizar variables del tipo var (var = numero), están obsoletas.
 */


let main = document.getElementById("main");
let lista = document.getElementById("lista");
// NodeList
let elementosLista = document.getElementsByTagName("li");

// Primer paso - Convierto los elementos en un Array.
let elementosListaArray = Array.from(elementosLista);

// Segundo paso - Organizo alfabeticamente los elementos de la lista.
elementosListaArray.sort((a, b) => a.textContent.localeCompare(b.textContent));

// Tercer paso - borro la lista.
lista.innerHTML = "";

// Cuarto paso - Inserto los elementos organizado nuevamente en la lista.
for (let elemento of elementosListaArray) {

    lista.appendChild(elemento);
}

let plantillaBetis = ["13 Adrián", "2 Bellerín", "5 Bartra", "23 Sabaly", "4 Johnny Cardoso", 
    "16 Altimira", "20 Lo Celso", "22 Isco", "7 Antony", "9 Chumy Ávila", "19 Cucho"];

// Ordenar y mostrar en un solo paso
lista.innerHTML = plantillaBetis
    .sort((a, b) => parseInt(a) - parseInt(b))
    .map(jugador => `<li>${jugador}</li>`)
    .join('');

lista.innerHTML = plantillaBetis.sort((a, b) => {
      // Extraer los números de dorsal
      const dorsalA = parseInt(a.split(" ")[0]);
      const dorsalB = parseInt(b.split(" ")[0]);
      
      // Comparar los dorsales
      return dorsalA - dorsalB;
    });

// Tercer paso - borro la lista.
lista.innerHTML = "";

// Ordenar por nombre
lista.innerHTML = plantillaBetis
        .sort((a, b) => {
            let nombreA = a.split(' ').slice(1).join(' ');
            let nombreB = b.split(' ').slice(1).join(' ');
            return nombreA.localeCompare(nombreB);
        })
        .map(jugador => `<li>${jugador}</li>`)
        .join('');