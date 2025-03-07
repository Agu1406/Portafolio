/**
 * No utilizar variables del tipo var (var = numero), están obsoletas.
 */

async function prepararDesayunoSinAwait() {
    console.log("🍳 ¡Vamos a preparar el desayuno SIN await!");
    
    // Preparar el pan tostado (tarda 2 segundos)
    console.log("🍞 Poniendo el pan en la tostadora...");
    let promesaPan = new Promise(resolver => {
        setTimeout(() => {
            console.log("🔔 ¡Ding! El pan está tostado");
            resolver("🍞 Pan tostado crujiente");
        }, 2000);
    });
    // Sin await, esto se ejecuta inmediatamente, ¡antes de que el pan esté listo!
    console.log("❓ ¿Tenemos pan? " + promesaPan);
    
    // Preparar los huevos (tarda 3 segundos)
    console.log("🥚 Cocinando los huevos...");
    let promesaHuevos = new Promise(resolver => {
        setTimeout(() => {
            console.log("🔔 ¡Los huevos están listos!");
            resolver("🍳 Huevos revueltos");
        }, 3000);
    });
    // Sin await, esto se ejecuta inmediatamente, ¡antes de que los huevos estén listos!
    console.log("❓ ¿Tenemos huevos? " + promesaHuevos);
    
    // Sin await, intentamos servir el desayuno antes de que esté listo
    return `¿Desayuno listo? Tenemos: ${promesaPan} y ${promesaHuevos}`;
}

prepararDesayunoSinAwait();