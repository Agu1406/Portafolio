/**
 
No utilizar variables del tipo var (var = numero), están obsoletas.*/

// Función asíncrona que simula cargar datos de un servidor
async function cargarDatos() {
    console.log("🔄 Iniciando carga de datos...");

    // Creamos una promesa que se resuelve después de 2 segundos
    const datos = await new Promise((resolver) => {
        console.log("📡 Conectando con el servidor...");

        setTimeout(() => {
            // Simulamos que recibimos datos del servidor
            const datosRecibidos = {
                nombre: "Juan",
                edad: 25,
                ciudad: "Madrid"
            };

            console.log("✅ Datos recibidos del servidor");

            resolver(datosRecibidos);
        }, 2000); // 2 segundos
    });

    // Esto se ejecuta solo cuando la promesa se resuelve
    console.log("📋 Procesando datos...");
    console.log(Nombre: ${ datos.nombre }, Edad: ${ datos.edad }, Ciudad: ${ datos.ciudad });

    return datos; // Devolvemos los datos
}

// Llamamos a la función asíncrona
console.log("▶️ Programa iniciado");

cargarDatos()
    .then(resultado => {
        console.log("🎉 Todo completado correctamente");
        console.log("Resultado final:", resultado);
    })
    .catch(error => {
        console.log("❌ Ocurrió un error:", error);
    });

console.log("⏳ Esperando a que los datos se carguen...");