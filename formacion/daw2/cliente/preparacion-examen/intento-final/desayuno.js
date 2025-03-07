/**
 * El Desayuno Mágico
 * Un ejemplo de async/await para niños de 5 años
 */

// Función para esperar un tiempo (como cuando esperamos que algo se cocine)
function esperar(segundos) {
    return new Promise(resolve => {
        setTimeout(resolve, segundos * 1000);
    });
}

// Función para tostar el pan
async function tostarPan() {
    console.log("🍞 Poniendo el pan en la tostadora...");
    await esperar(2); // Esperamos 2 segundos
    console.log("🍞 El pan se está calentando...");
    await esperar(2); // Esperamos 2 segundos más
    console.log("🍞 ¡Ding! ¡El pan está tostado! 🍞");
    return "🍞 Pan tostado";
}

// Función para cocinar los huevos
async function cocinarHuevos() {
    console.log("🥚 Rompiendo los huevos en la sartén...");
    await esperar(1); // Esperamos 1 segundo
    console.log("🥚 Los huevos empiezan a cocinarse...");
    await esperar(2); // Esperamos 2 segundos
    console.log("🥚 Dando la vuelta a los huevos...");
    await esperar(2); // Esperamos 2 segundos más
    console.log("🥚 ¡Los huevos están listos! 🍳");
    return "🍳 Huevos cocinados";
}

// Función principal para preparar el desayuno
async function prepararDesayuno() {
    console.log("👨‍🍳 ¡Vamos a preparar el desayuno!");
    
    try {
        // Primero tostamos el pan
        const pan = await tostarPan();
        console.log(`✅ Tenemos: ${pan}`);
        
        // Luego cocinamos los huevos
        const huevos = await cocinarHuevos();
        console.log(`✅ Tenemos: ${huevos}`);
        
        // ¡El desayuno está listo!
        console.log("🎉 ¡El desayuno está listo! 🎉");
        console.log(`🍽️ Desayuno: ${pan} y ${huevos}`);
        
    } catch (error) {
        // Si algo sale mal
        console.log(`❌ Oh no, algo salió mal: ${error}`);
    }
}

// Versión sin async/await (para comparar)
function prepararDesayunoSinAwait() {
    console.log("👨‍🍳 ¡Vamos a preparar el desayuno! (sin await)");
    
    // Primero tostamos el pan
    const panPromesa = tostarPan();
    console.log(`Pan: ${panPromesa}`); // Esto mostrará [object Promise], no el resultado
    
    // Luego cocinamos los huevos
    const huevosPromesa = cocinarHuevos();
    console.log(`Huevos: ${huevosPromesa}`); // Esto mostrará [object Promise], no el resultado
    
    // Intentamos mostrar el desayuno (pero no funcionará correctamente)
    console.log(`🍽️ Desayuno: ${panPromesa} y ${huevosPromesa}`);
}

// Ejecutamos la función para preparar el desayuno
console.log("=== Preparando desayuno con async/await ===");
prepararDesayuno();

// Si quieres ver la diferencia, descomenta esta línea:
// setTimeout(() => {
//     console.log("\n=== Preparando desayuno sin async/await ===");
//     prepararDesayunoSinAwait();
// }, 15000); 