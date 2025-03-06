/**
 * No utilizar variables del tipo var (var = numero), están obsoletas.
 */

/*
* 3 botones, cada uno asociado a una promesa distinta, cada promesa se resuelve
* en un tiempo distinto y al haber pulsado los 3 botones, escriba en un div
* que se han pulsado los tres.
*/

// Creamos una promesa de helado
let promesaDeHelado = new Promise(function(cumplir, rechazar) {
    // Imaginemos que esta variable indica si hay helado en casa
    let hayHeladoEnCasa = true;
    
    if (hayHeladoEnCasa) {
        // Si hay helado, la promesa se cumple
        cumplir("¡Aquí tienes tu helado de chocolate!"); // Esto es "mensaje"
    } else {
        // Si no hay helado, la promesa se rechaza
        rechazar("Lo siento, no hay helado en casa"); // Esto es "error"
    }
});


// Qué hacer cuando recibas el helado
promesaDeHelado.then(function(mensaje) {
    console.log(mensaje);  // Muestra: "¡Aquí tienes tu helado de chocolate!"
    console.log("¡Mmm, qué rico!");
}).catch(function(error) {
    console.log(error);    // Si no hay helado, mostraría: "Lo siento, no hay helado en casa"
    console.log("Estoy triste :(");
});