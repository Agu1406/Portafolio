    
    /*

    La primera vez ejectuado, como no existe ninguna constante / variable llamada "myAge"
    el script falla, ocasionando un error el cual es:

    Uncaught ReferenceError: Cannot access 'myAge' before initialization
    at AgustinAntonioMarquezPina_Tarea2_2.js:1:17

    Es una "excepción" basicamente.

    console.log(myAge);
    */

    let myAge = 30

    console.log(myAge);

    // Ejecutado la segunda vez imprime por consola, efectivamente, el número entero 30.