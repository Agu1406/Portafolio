/**
 * No utilizar variables del tipo var (var = numero), están obsoletas.
 */

/**
 * Ejemplo de promesas para el ejercicio 9.2.3
 * Muestra diferentes formas de trabajar con promesas
 */

// Ejemplo básico de una promesa
const miPromesa = new Promise((resolve, reject) => {
    // Simulamos una operación asíncrona
    setTimeout(() => {
        const exito = Math.random() > 0.2;
        if (exito) {
            resolve("¡Operación completada con éxito!");
        } else {
            reject("¡Operación completada con error!");
        }
    }, 1000);
});