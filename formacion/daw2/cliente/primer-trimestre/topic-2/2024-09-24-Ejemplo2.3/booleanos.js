// Ejemplos de booleanos en JavaScript
console.log("=== BOOLEANOS EN JAVASCRIPT ===");

// Valores booleanos básicos
let verdadero = true;
let falso = false;
console.log("Valor verdadero:", verdadero);
console.log("Valor falso:", falso);

// Operadores de comparación
console.log("\nOperadores de comparación:");
console.log("5 > 3:", 5 > 3);
console.log("5 < 3:", 5 < 3);
console.log("5 >= 5:", 5 >= 5);
console.log("4 <= 3:", 4 <= 3);
console.log("5 === 5:", 5 === 5);
console.log("5 !== 3:", 5 !== 3);

// Operadores lógicos
console.log("\nOperadores lógicos:");
console.log("AND (true && true):", true && true);
console.log("AND (true && false):", true && false);
console.log("OR (true || false):", true || false);
console.log("OR (false || false):", false || false);
console.log("NOT (!true):", !true);
console.log("NOT (!false):", !false);

// Valores que se evalúan como falso
console.log("\nValores falsy:");
console.log("Boolean(0):", Boolean(0));
console.log("Boolean(''):", Boolean(""));
console.log("Boolean(null):", Boolean(null));
console.log("Boolean(undefined):", Boolean(undefined));
console.log("Boolean(NaN):", Boolean(NaN));

// Valores que se evalúan como verdadero
console.log("\nValores truthy:");
console.log("Boolean(1):", Boolean(1));
console.log("Boolean('hola'):", Boolean("hola"));
console.log("Boolean([]):", Boolean([]));
console.log("Boolean({}):", Boolean({}));
console.log("Boolean(Infinity):", Boolean(Infinity)); 