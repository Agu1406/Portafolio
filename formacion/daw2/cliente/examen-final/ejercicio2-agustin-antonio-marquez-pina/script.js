// Obtenemos el botón del DOM
let boton = document.getElementById("intentarFichaje");

// Le agregamos un evento
boton.addEventListener("click", () => {

    // Pide con un prompt el nombre de un jugador para fichar.
    let nombre = prompt("Dime el nombre del jugador a fichar");

    let respuesta = funcionPromesa();

    respuesta
        // Si el fichaje fue exitoso ejecuta el then
        .then(() => {
            // Rescato del DOM el div.
            let div = document.getElementById("respuesta");
            // Imprimo el mensaje de fichaje exitoso.
            div.innerHTML = `El jugador ${nombre} se une al Real Betis Balompié`;
        })
        // Si el fichaje fue descartado ejecuta el catch.
        .catch(() => {
            // Rescato del DOM el div.
            let div = document.getElementById("respuesta");
            // Imprimo el mensaje de fichaje rechazado.
            div.innerHTML = `El jugador ${nombre} rechaza la oferta del Real Betis Balompié`;
        })
        .finally(() => {
            // Rescato del DOM el div.
            let div = document.getElementById("respuesta");

            let final = document.createElement("p");

            final.innerHTML = "Proceso de fichaje finalizado";

            div.appendChild(final);
        });

});

// Función que intenta fichar un jugador
function funcionPromesa() {

    // Creamos una promesa de fichaje OLE OLE BETIS
    let promesa = new Promise((fichado, descartado) => {
        // Simulamos una operación asíncrona
        setTimeout(() => {
            // 31% de probabilidades de ser fichado y 69% de que no.
            const exito = Math.random() > 0.31;
            // Si sale un valor "alto" lo ficha.
            if (exito) {
                fichado("¡Ole ole betis! Has sido fichado copetin");
                // Si sale un valor "bajo" lo descarta.
            } else {
                descartado("Lo siento mucho copetin, estás descartadeo");
            }
            // 4000 milisegundos son 4 segundos.
        }, 4000);
    });

    // la función devuelve una promesa.
    return promesa;
}


