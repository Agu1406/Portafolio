/**
 * No utilizar variables del tipo var (var = numero), están obsoletas.
 */

console.log("¡Comienza la carrera de coches de juguete!");

// Coche rojo: promesa que se resolverá cuando el coche llegue a la meta
let cocheRojo = new Promise(function(llegar, chocar) {

    console.log("🚗 El coche ROJO ha salido");
    
    // El coche rojo tarda 3 segundos en llegar a la meta
    setTimeout(function() {
        
        let rojo = true;
        
        if (rojo) {
            llegar("🏁 El coche ROJO ha llegado a la meta en 3 segundos");
        } else {
            chocar("💥 ¡Oh no! El coche ROJO se ha chocado");
        }
    }, 3000); // 3 segundos
});

// Coche azul: promesa que se resolverá cuando el coche llegue a la meta
let cocheAzul = new Promise(function(llegar, chocar) {
    console.log("🚙 El coche AZUL ha salido");
    
    // El coche azul tarda 5 segundos en llegar a la meta
    setTimeout(function() {

        let azul = true;
        
        if (azul) {
            llegar("🏁 El coche AZUL ha llegado a la meta en 5 segundos");
        } else {
            chocar("💥 ¡Oh no! El coche AZUL se ha chocado");
        }
    }, 5000); // 5 segundos
});

// Qué hacer cuando el coche rojo termine su carrera
cocheRojo.then(function(mensaje) {
    console.log(mensaje);
}).catch(function(problema) {
    console.log(problema);
});

// Qué hacer cuando el coche azul termine su carrera
cocheAzul.then(function(mensaje) {
    console.log(mensaje);
}).catch(function(problema) {
    console.log(problema);
});

// Qué hacer cuando AMBOS coches hayan terminado (no importa si llegaron o chocaron)
Promise.all([cocheRojo, cocheAzul])
    .then(function() {
        console.log("🎯 ¡La carrera ha terminado! Ambos coches han completado su recorrido");
    })
    .catch(function() {
        console.log("🚨 La carrera ha terminado, pero al menos un coche ha tenido problemas");
    });

// Qué hacer cuando el PRIMER coche termine (sea cual sea)
Promise.race([cocheRojo, cocheAzul])
    .then(function(primerMensaje) {
        console.log("🥇 ¡Tenemos un ganador!");
        console.log(primerMensaje);
    })
    .catch(function(primerProblema) {
        console.log("⚠️ El primer coche ha tenido un problema:");
        console.log(primerProblema);
    });

console.log("Mientras los coches corren, puedes seguir jugando con otros juguetes");