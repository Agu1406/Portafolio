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

// Imprime todas las propiedades de un objeto.
for (let propiedad in coordenadas) {
    console.log(`Coordenada ${propiedad}: ${coordenadas[propiedad]}`)
}

// Borramos la coordenada "Z" del objeto coordenada.
delete coordenadas.z;

// Intentamos imprimirlo en consola, da "undefined" porque no existe.
console.log(coordenadas.z);
