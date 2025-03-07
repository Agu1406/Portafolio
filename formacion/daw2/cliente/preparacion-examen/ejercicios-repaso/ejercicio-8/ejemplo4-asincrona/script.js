/**
 * No utilizar variables del tipo var (var = numero), están obsoletas.
 */

async function prepararDesayunoConAwait() {
    console.log("🍳 ¡Vamos a preparar el desayuno CON await!");
    
    // Preparar el pan tostado (tarda 2 segundos)
    console.log("🍞 Poniendo el pan en la tostadora...");
    let panTostado = await new Promise(resolver => {
        setTimeout(() => {
            console.log("🔔 ¡Ding! El pan está tostado");
            resolver("🍞 Pan tostado crujiente");
        }, 2000);
    });

    console.log("✅ Tenemos: " + panTostado);
    
    // Preparar los huevos (tarda 3 segundos)
    console.log("🥚 Cocinando los huevos...");
    let huevos = await new Promise(resolver => {
        setTimeout(() => {
            console.log("🔔 ¡Los huevos están listos!");
            resolver("🍳 Huevos revueltos");
        }, 3000);
    });
    console.log("✅ Tenemos: " + huevos);
    
    // Servir el desayuno con ambas cosas
    return `¡Desayuno listo! Tenemos: ${panTostado} y ${huevos}`;
}

prepararDesayunoConAwait();