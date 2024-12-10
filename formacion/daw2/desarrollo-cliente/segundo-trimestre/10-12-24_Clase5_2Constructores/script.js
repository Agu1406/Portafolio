// LITERAL
let coche = {
    marca: 'Tesla',
    modelo: 'S',
    color: 'rojo',
    datos: function() {
        console.log(`[Literal]\nEl coche es de la marca ${coche.marca} y del color ${coche.color}`);
    }
};
coche.datos();

// FUNCIÓN QUE CREA UN OBJETO Y LO RELLENA
function crearCoche(marcaCoche, modeloCoche, colorCoche) {
    let coche = {};
    coche.marca = marcaCoche;
    coche.modelo = modeloCoche;
    coche.color = colorCoche;
    coche.datos = function() {
        console.log(`[Por función]\nEl coche es de la marca ${coche.marca} y del color ${coche.color}`);
    }
    return coche;
}
let objCoche1 = crearCoche('Tesla', 'S', 'rojo');
let objCoche2 = crearCoche('Toyota', 'Yaris', 'azul');
objCoche1.datos();
objCoche2.datos();

// CONSTRUCTOR
function Coche(marcaCoche, modeloCoche, colorCoche) {
  this.marca = marcaCoche;
  this.modelo = modeloCoche;
  this.color = colorCoche;
  this.datos = function() {
    console.log(`[Por constructor]\nEl coche es de la marca ${this.marca} y del color ${this.color}`);
  };
}
let coche1 = new Coche('Tesla', 'S', 'rojo');
let coche2 = new Coche('Toyota', 'Yaris', 'azul');
coche1.datos();
coche2.datos();
