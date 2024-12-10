let punto = {
    x: 15,
    y: 3,
    devolverCoord: function(){
        return ("punto1: " + `(${this.x}, ${this.y})`);
    }
};
console.log(punto.devolverCoord()); //(15, 3)



let punto2 = new Object();
punto2.x = 12;
punto2.y = punto2.x * 2;
punto2.devolverCoord = function() {
    return ("punto2: " + `(${punto2.x}, ${punto2.y})`);
};
console.log(punto2.devolverCoord()); //(12, 24)



let punto3 = {
    x: prompt('3. Introduce la coordenada x.', '2'),
    y: prompt('3. Introduce la coordenada y.', '1'),
    mostrarCoord: function(){
        console.log("punto3: " + `(${this.x}, ${this.y})`);//Aquí hacemos una prueba con this, que es igual que punto3.
    }
};
punto3.mostrarCoord(); //(?, ?)



let punto4 = {
    mostrarCoord: function(){
        console.log("punto4: " + `(${punto4.x}, ${punto4.y})`);
    }, 
    x: 15,
    y: 3
};
punto4.mostrarCoord();



let punto5 = {
    x: prompt('5. Introduce la coordenada x.', '2'),
    y: this.x * 2,
    mostrarCoord: function(){
        console.log("punto5: " + `(${punto5.x}, ${punto5.y})`);
    }
}
punto5.mostrarCoord();



let libro = {
    titulo: "Mi libro",
    "nSerie": "37G2",
    autores: ["Autor 1", "Autor 2"],
    editorial: {
        nombre: "Mi editorial",
        pais: "España"
    }
}
console.log(libro.titulo);
console.log(libro["nSerie"]);
console.log(libro.autores[1]);
console.log(libro.editorial.nombre);