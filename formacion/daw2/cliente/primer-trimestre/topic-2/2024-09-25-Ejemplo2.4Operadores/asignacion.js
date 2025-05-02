console.log('OPERADORES DE ASIGNACIÓN');

let a1 = 1;
console.log('a1 = ' + a1);
a1 = a1 + 1;
console.log('a1 = a1 + 1 \n' + a1); // 2
a1 = a1 - 1;
console.log('a1 = a1 - 1 \n' + a1); // 1

a1++;
console.log('a1++ \n' + a1); // 2
a1--;
console.log('a1-- \n' + a1); // 1

let a2 = 2;
a1 = a1 + a2;
console.log('a1 = a1 + a2 \n' + (a1)); // 3
a1 = a1 - a2;
console.log('a1 = a1 - a2 \n' + a1); // 1

a1 += a2;
console.log('a1 += a2 \n' + a1); // 3
a1 -= a2;
console.log('a1 -= a2 \n' + a1); // 1

let valor = 10, incremento, decremento;
console.log(`valor = ${valor}`);
incremento = valor++;
console.log(`incremento = valor++ \nincremento: ${incremento} \nvalor: ${valor}`); // 10 y 11
/*
incremento = valor;
valor++;
*/
decremento = valor--;
console.log(`decremento = valor-- \ndecremento: ${decremento} \nvalor: ${valor}`); // 11 y 10

incremento = ++valor;
console.log(`incremento = ++valor \nincremento: ${incremento} \nvalor: ${valor}`); // 11 y 11
/*
valor++;
incremento = valor;
*/

incremento += valor;
console.log(`incremento += valor \nincremento: ${incremento} \nvalor: ${valor}`); // 22 y 11
/*
incremento = incremento + valor;
*/

/* OPERADOR CONDICIONAL */
let c = 5;
c -= 2;
console.log(c); // 3
console.log(typeof (c)); // 3

// (condición) ? (acción si true) : (acción si false)
(c++ == 4) ? console.log('Sip') : console.log('Nop'); // Nop
console.log(c);
(++c == 5) ? console.log('Sip') : console.log('Nop'); // Sip
console.log(c);


/* 1. INICIALIZA DOS VARIABLES
   2. SÚMALAS Y ASIGNA EL RESULTADO A LA PRIMERA
   3. INDICA SI ES O NO PAR. */
let num1 = prompt('Escribe un número', '1');
let num2 = prompt('Escribe otro número', '2');
num1 += num2;
(num1 % 2 == 0) ? console.log('Es par.') : console.log('No es par.');
