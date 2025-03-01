// CREACIÓN DE OBJETOS LITERALES
// Objeto literal de Agustín.

// Objeto literal de Alberto.
let coche = {
    marca: "Audi",
    modelo: "A4"
}

// Objeto literal de Aitor.
let gato = {
    nombre: "paco",
    genero: "macho"
}

// Objeto literal de Agustín
let vesoenelano = {
    // Atributos simples con valores simples.
    nombre: "Agustin",
    apellido: "Piña",
    nacionalidad: "Español",
    edad: 24,
    ahorros: "0,99€",
    tieneNovio: true,
    // Atributo Array con "Strings" dentro.
    hobbies: [
        "cocinar",
        "follar",
        "cantar",
        "comer"
    ],
    // Función normal que el objeto puede utilizar.
    follar: function () {
        console.log("🍆🍑🥵 ¡Puta! Que rico");
    },
    // Función flecha que el objeto puede utilizar.
    pagarCafe: () => {
        console.log("Agustin paga cabrón 🍆🍑🥵");
    },
    /**
     * Función callback que se ejecuta primero y una
     * vez termina de ejecutarse, llama a otra función y la
     * ejecuta, en nuestro ejemplo, nuestro objeto debe comer
     * primero antes de poder cagar.
     */

    // Función para comer
    comer: function () {
        console.log("🫓🥘🍌💧");
    },
    // Función para cagar que llama a comer primero
    cagar: function () {
        this.comer(); // Llama a la función comer del objeto
        console.log("💩🚽🧻🥵");
    }
}

// Llamada a la función cagar, que ahora incluye comer
vesoenelano.cagar();


// CREACIÓN DE OBJETOS CON CONSTRUCTORES



// Objeto con constructor de Agustín.
function Libro(ISBN, nombre, autor, fecha) {

    this.ISBN = ISBN;
    this.nombre = nombre;
    this.autor = autor;
    this.fecha = fecha;
    // Todos los libros por defecto no han sido leídos.
    this.leido = false;

    // Función normal que permite ver el ISBN y la fecha de publicación de un libro.
    this.verISBNFecha = function () {
        console.log("ISBN: " + this.ISBN + " - Fecha: " + this.fecha);
    }

    // Función callback que primero ejecuta la función "verISBNFecha" antes de sí misma.
    this.verNombreAutor = function () {
        this.verISBNFecha();
        console.log("Nombre: " + this.nombre + " - Autor: " + this.autor);
    }

    // Función callback que recibe otra función, la ejecuta primero y luego se ejecuta a sí misma.
    this.destruirLibro = function (funcionLeer) {
        // Verificamos si se proporcionó una función
        funcionLeer(this);
        // Verificamos que el libro fue leido antes de destruirlo.
        console.log("¿El libro fue leido? Respuesta: " + this.leido);
        
        // Borra todos los atributos y funciones del libro.
        delete this.ISBN;
        delete this.autor;
        delete this.fecha;
        delete this.leido;
        delete this.nombre;
        delete this.verISBNFecha;
        delete this.verNombreAutor;
        delete this.destruirLibro;
    }
}

// Creamos dos objetos de ejemplo con el constructor.
let quijote = new Libro(1406, "Quijote", "Miguel de Cervantes", "1860");
let biblia = new Libro(1809, "Biblia", "Apóstoles de Jesús", "200");

/**
 * Función que recibe como argumento un libro y cambia su atributo
 * "leido" de "false" a "true".
 * @param {Libro} libro
 */
function leer(libro) {
    libro.leido = true;
}

const funci = (libro) => {
    libro.leido = true;
    console.log("Soy una funcion");
}

const otra = function (libro) {
    libro.leido = true;
    console.log("Soy una funcion no flecha");

}

/**
 * Función que imprime todas las propiedades de un objeto incluidas
 * las funciones.
 * @param {Libro} objeto
 */
function imprimirAtributosObjeto(objeto) {
    for (let propiedad in objeto) {
        console.log("Nombre: " + propiedad + " - Valor: " + objeto[propiedad]);
    }
}


// Imprimimos todas las propiedades del objeto.
imprimirAtributosObjeto(quijote);

// Llamada a destruirLibro pasando la función leer
quijote.destruirLibro(otra);

// Intentamos imprimir todas las propiedades otra vez.
imprimirAtributosObjeto(quijote);

// Preguntamos ¿Quijote es un objeto del tipo "Libro"?
console.log(quijote instanceof Libro); // True

// Herencia del constructor de libros.
function Revista (ISBN, nombre, autor, fecha, precio) {
    // Hereda todo de su padre, incluidas las funciones que empiezan por "this".
    Libro.call(this, ISBN, nombre, autor, fecha);
    // La única cosa nueva que tiene es "precio".
    this.precio = precio


}

// Instanciamos un nuevo objeto del tipo "Revista".
let playboy = new Revista (1234, "Playboy", "Playboy", "2024", 9.99);

console.log(playboy);

// Llamamos a destruirLibro pasando la función leer
playboy.destruirLibro(leer);

console.log(playboy);

// CREACIÓN DE OBJETOS CON CLASES

// Objeto con clase de Agustín.

// Objeto con clase de Alberto.
class Coche {
    constructor(marca, modelo) {
        this.marca = marca;
        this.modelo = modelo;

        this.mostrarInfoCoche = function () {
            return `Coche: ${this.marca} ${this.modelo}`;
        };
    }
}
// Objeto con clase de Aitor.
class Animal {
    constructor(nombre, genero) {
        this.nombre = nombre;
        this.genero = genero;
    }

    saludarGenerico = () => {
        console.log("me llamo", this.nombre, "soy un", this.genero);
    }
}
// Instanciamos un objeto del tipo "Animal" usando su constructor.
let leon = new Animal("Guido", "Varón");

// Accedemos a sus propiedades usando el nombre del objeto.
console.log("Nombre del animal: " + leon.nombre + " - Género: " + leon.genero);

// Herencia de un objeto usando la clase "Animal" de Aitor.
class Gato extends Animal {
    // Hereda el constructor pero le agrega algo propio, los "maullidos".
    constructor(nombre, genero, maullidosDiarios) {
        // No define el "this.nombre" y el "this.genero" porque los hereda.
        super(nombre, genero);
        // Sí define el "this.maullidosDiarios" porque es algo nuevo.
        this.maullidosDiarios = maullidosDiarios;
        // Los gatos por defecto no han sido acariciados.
        this.acariciado = false;
    }

    saludar() {
        console.log("Soy un gato, me llamo " + this.nombre, "soy un " + this.genero);
    }
    
    maulla(acariciar) {
        // Función que recibe un gato para ser acariciado.
        acariciar(this);
        // Una vez acariciado imprime una monada por consola.
        console.log("😽😽😽 ¿Ha sido acariciado? " + this.acariciado);
    }
}
/**
 * Función que acaricia a un gato.
 * @param {Gato} gato
 */
function acariciar (gato) {
    // Modifica a "true" si el gato ha sido acariciado.
    gato.acariciado = true;
    // Imprime una monada por consola.
    console.log("😺😸😹");
}

// Instanciamos un nuevo objeto de la clase Gato.
let aitor = new Gato ("Aitor", "No binario", 10);

// Usamos su función "saludar" para que salude.
aitor.saludar();

// Usamos su función "callback" para que, después de ser acariciado, maulle.
aitor.maulla(acariciar);
