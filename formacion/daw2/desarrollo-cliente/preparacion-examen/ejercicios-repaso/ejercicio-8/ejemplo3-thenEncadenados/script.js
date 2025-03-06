/**
 * No utilizar variables del tipo var (var = numero), están obsoletas.
 */

// Creamos una promesa simple: hornear una pizza
console.log("🍕 Ponemos la pizza en el horno");

let pizzaEnElHorno = new Promise(function(pizzaLista, pizzaQuemada) {
    // La pizza tarda 3 segundos en hornearse
    console.log("⏲️ La pizza se está horneando...");
    
    setTimeout(function() {
        // 50% de las veces, la pizza sale bien
        let pizzaSaleBien = Math.random() * 0.5 ? true : false;
        
        if (pizzaSaleBien) {
            pizzaLista("¡La pizza está lista! 🍕");
        } else {
            pizzaQuemada("¡Oh no! La pizza se quemó 🔥");
        }
    }, 3000); // 3 segundos
});

// Qué hacer cuando la pizza esté lista
pizzaEnElHorno
    .then(function(mensaje) {
        console.log(mensaje);
        return "Ahora vamos a comer la pizza";
    })
    .then(function(siguientePaso) {
        console.log(siguientePaso);
        console.log("¡Mmm, qué rica! 😋");
    })
    .catch(function(problema) {
        console.log(problema);
        console.log("Tendremos que pedir otra pizza 📱");
    });


console.log("Mientras la pizza se hornea, podemos preparar la mesa 🍽️");