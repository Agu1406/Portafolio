/**
 * No utilizar variables del tipo var (var = numero), están obsoletas.
 */

// Creamos un objeto literal llamado "coordenadasMinecraft"
let coordenadas = {
    // Estás son las coordenadas de nuestro amigo Juan.
    x: 20,
    y: 3000,
    z: -34
}

/**
 * Quiero imprimir todas las coordenadas usando un bucle
 * porque eso de escribir.
 * 
 * console.log(coordenadasMinecraft.x);
 * console.log(coordenadasMinecraft.y);
 * console.log(coordenadasMinecraft.z);
 * 
 * 
 * */

for (let propiedad in coordenadas) {
    console.log(`Coordenada ${propiedad}: ${coordenadas[propiedad]}`)
}
