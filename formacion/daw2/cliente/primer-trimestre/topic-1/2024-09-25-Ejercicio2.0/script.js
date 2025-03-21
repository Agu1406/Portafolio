/*
Imagina que queremos usar JavaScript para calcular la altura de un árbol años
después de plantarlo. Lo compramos como un retoño de 0,8 m de alto y el vivero
predice que el árbol crecerá 20 cm por año.

Esta es una situación en la que podemos usar las funciones lineales y = mx + b.
Por ejemplo, si el usuario quiere saber qué altura tendrá el árbol dentro de 10
años, le diríamos que la altura prevista es de 2,80 m.

Crea un código para resolver ese cálculo, teniendo en cuenta que:

- Se usará prompt para preguntarle al usuario la cantidad de años en el futuro
que desea conocer.
- Se usará alert para decirles la altura prevista.
*/

// Creamos una variable llamada "tiempo" donde, mediante un prompt se nos den "años" para calcúlar.
let tiempo = prompt("Dime la cantidad de años para predecir el crecimiento del arbol para entonces.");

// Como el input recibido no es un número si no un string lo casteamos a número decimal.
tiempo = parseFloat(tiempo);

// Creamos la variable "resultado" que almacena el resultado del cálculo.
let resultado = 0.8 + (tiempo * 0.20);

// Imprimos el prognostico de crecimiento usando un alert.
alert("El prognostico es que el árbol en " + tiempo + " tendrá una altúra de: " + resultado + " metros.");
