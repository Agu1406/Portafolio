console.log('OPERADORES RELACIONALES');

console.log(5 > 4); // MAYOR. true
console.log(5 > 5); // MAYOR. false

console.log(4 < 5); // MENOR. true
console.log(5 < 5); // MENOR. false

console.log(5 >= 5); // MAYOR O IGUAL. true
console.log(5 >= 4); // MAYOR O IGUAL. true
console.log(4 >= 5); // MAYOR O IGUAL. false

console.log(5 <= 5); // MENOR O IGUAL. true
console.log(4 <= 5); // MENOR O IGUAL. true
console.log(5 <= 4); // MENOR O IGUAL. false

console.log(5 == 5); // IGUAL. true
console.log(5 == 4); // IGUAL. false
console.log(5 == "5"); // IGUAL. true

console.log(5 != 5); // DESIGUAL. false
console.log(5 != 4); // DESIGUAL. true

console.log("aaaaa" > "b"); // false
console.log("b" > "aaa"); // true
console.log("saco" == "saca"); // false
console.log("saco" > "saca"); // true
console.log("Saco" > "saca"); // false
console.log("o" < "p"); // true
console.log("ñ" < "o"); // false

/* COMPARACIONES ENTRE TIPOS DIFERENTES: */
console.log("2" == 2); // true
console.log("2" != 2); // false

console.log("true" == true); // false
console.log("false" == false); // false

console.log("1" == true); // true
console.log(1 == true); // true
console.log("0" == false); // true
console.log(0 == false); // true
console.log("2" == true); // false
console.log(2 == true); // false

console.log("" == false); // true

console.log("2" === 2); // false
console.log(1 + 1 === 2); // true
console.log("2" !== 2); // true