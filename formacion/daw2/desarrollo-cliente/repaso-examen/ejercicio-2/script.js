/**
 * Recordar que el uso de variables tipo "var" esta deprecated (obsoleto) así que
 * siempre debemos utilizar "let" o "constant".
*/

/**
 * Constructor del tipo "persona" que permite crear obejtos persona, este comentario es JSDOC
 * se genera automaticamente con las etiquetas "param" que permiten "tipear" el tipo de
 * datos que recibe el constructor, por ejemplo, el nombre es un String, la edad un Number,
 * el genero, color de pelo y signo zodiacal son Strings también.
 * @param {String} nombre el nombre de la persona.
 * @param {Number} edad la edad de la persona.
 * @param {String} genero el genero de la persona.
 * @param {String} colorPelo el color de pelo de la persona.
 * @param {String} signoZodiacal el signo zodiacal de la persona.
 */
function Persona(nombre, edad, genero, colorPelo, signoZodiacal) {
    // Usamos "this" para que asigna desde los argumentos los valores al "X" objeto que creemos.
    this.nombre = nombre;
    this.edad = edad;
    this.genero = genero;
    this.colorPelo = colorPelo;
    this.signoZodiacal = signoZodiacal;

    /**
     * Método que permite que una persona salude.
     */
    this.Saludar = function () {
        console.log("¡Hola! jejeje, mi nombre es " + this.nombre + ", es un placer conocerte.");
    }

    /**
     * Método que permite que una persona se despida.
     */
    this.Despedirse = function () {
        console.log("¡Hasta luego! Ha sido un placer para tí, pero tengo que irme.");
    }
}

// Creamos una nueva "persona" llamada "marta" usando el constructor del objeto persona.
let marta = new Persona("Marta", 24, "no binario", "pelirroja", "cancer");

// Utilizando un bucle recorremos todos los atributos y métodos del objeto "marta".
for (let propiedad in marta) {
    // Imprimimos el nombre de la propiedad y su valor usando un console.log.
    console.log(`Propiedad: ${propiedad} - Valor: ${marta[propiedad]}`)
}

// Ahora utilizando un bucle solo vamos a imprimir atributos pero no métodos.
for (let atributo in marta) {
    // Si el atributo es diferente (no es) de una function, el if lo imprime.
    if(typeof marta[atributo] != "function") {
        // Imprimimos solo atributos, nada de métodos/funciones.
        console.log("Atributo: " + atributo + " - Valor: " + marta[atributo]);
    }
}
