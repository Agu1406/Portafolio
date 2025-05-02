console.log((1 == true) && (0 == false)); // true
console.log((2 == true) && (0 == false)); // false

let l1 = 9;
let l2 = 10;
console.log((l1 == 9) && (l2 == 10)); // true
console.log((l1 == 9) && (l2 == 9)); // false
console.log((l1 == 10) && (l2 == 9)); // false
console.log((l1 == 10) && (l3 == 9)); // false

console.log((l1 == 9) || (l2 == 10)); // true
console.log((l1 == 9) || (l2 == 9)); // true
console.log((l1 == 10) || (l2 == 9)); // false
//console.log( (l1 == 10) || (l3 == 9)); // ERROR por l3

console.log(!(l1 == 9 && l2 == 10)); // false
console.log(!(l1 == 9 && l2 == 9)); // true

console.log(!(l1 == 9 || l2 == 9)); // false
console.log(!(l1 == 10 || l2 == 9)); // true

let edad = 17;
let conducir = (edad >= 18 && carnet == true);
// Devuelve false:
// aunque 'carnet' no está declarado (y debería),
// no se evalúa la segunda condición porque la primera es falsa