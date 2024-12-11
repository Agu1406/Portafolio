let persona = {
  nombre: {
      pila: 'Jane',
      apellido: 'Doe'
  },
  edad: 30,
  genero: 'femenino',
  intereses: ['programación', 'teatro'],
  bio: function () {
    document.write(this.nombre.pila + ' tiene ' + this.edad + ' años. Le gusta ' + this.intereses[0] + ' y ' + this.intereses[1] + '.');
  },
  saludo: function() {
    alert('Hola, soy '+ this.nombre.pila + '. ');
  }
};
// Acceso a propiedades
persona.nombre.pila
persona.edad
persona.intereses[1]
persona.bio()
persona.saludo()

