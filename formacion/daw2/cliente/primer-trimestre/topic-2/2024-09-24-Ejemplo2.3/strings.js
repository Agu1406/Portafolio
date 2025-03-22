// Ejemplos de strings (cadenas de texto) en JavaScript
console.log("=== STRINGS EN JAVASCRIPT ===");

// Diferentes formas de crear strings
let comillasSimples = 'Hola mundo';
let comillasDobles = "Hola mundo";
let comillasInvertidas = `Hola mundo`;

console.log("Con comillas simples:", comillasSimples);
console.log("Con comillas dobles:", comillasDobles);
console.log("Con comillas invertidas:", comillasInvertidas);

// Concatenación de strings
let nombre = "Juan";
let apellido = "García";
console.log("\nConcatenación:");
console.log("Usando + :", nombre + " " + apellido);
console.log("Usando template literals:", `${nombre} ${apellido}`);

// Métodos útiles de strings
let texto = "JavaScript es genial";
console.log("\nMétodos útiles:");
console.log("Longitud:", texto.length);
console.log("En mayúsculas:", texto.toUpperCase());
console.log("En minúsculas:", texto.toLowerCase());
console.log("Primera posición de 'a':", texto.indexOf('a'));
console.log("Substring (posición 0 a 4):", texto.substring(0, 4));
console.log("Reemplazar palabra:", texto.replace("genial", "increíble"));

// Caracteres especiales
console.log("\nCaracteres especiales:");
console.log("Salto de línea:\nNueva línea");
console.log("Tabulación:\tTabulado");
console.log("Barra invertida: \\");

// Dividir string en array
let frase = "Hola,qué,tal,estás";
console.log("\nDividir string:");
console.log("Split por coma:", frase.split(",")); 