function muestraHermanos(){
    let mediano = document.getElementsByTagName("li")[1]; //al indicarle un tag, en nuestra lista hay tres elementos, 
                                                          //los cuales es como si se almacenasen en un array, le indicamos [1] para decirle la posicion que tiene que seleccionar
    console.log(mediano.textContent);
    console.log(mediano.previousElementSibling.textContent);
    console.log(mediano.nextElementSibling.textContent);
}