/**
 * La media aritmética de dos números es el valor que se obtiene al sumar ambos números 
 * y dividir el resultado entre dos. Es una forma de calcular el promedio de esos dos números.
 * 
 * Fórmula: Media = (Número 1 + Número 2) / 2
 * 
 * Ejemplo:
 * Si los números son 4 y 8, la media sería:
 * Media = (4 + 8) / 2 = 12 / 2 = 6
 * 
 * Este cálculo es útil cuando se quiere obtener un valor representativo o central entre dos valores.
 */
function calcularMedia() {
    // Obtener los valores introducidos por el usuario y convertirlos a int
    const numero1 = parseInt(document.getElementById("numero1").value);
    const numero2 = parseInt(document.getElementById("numero2").value);

    // Verificar si los valores introducidos son números usando "isNaN" que es "is Not a Number".
    if (isNaN(numero1) || isNaN(numero2)) {
        alert("Por favor, introduce dos números válidos.");
        return;
    }

    // Calcular la media aritmética
    const media = (numero1 + numero2) / 2; // En otro ejercicio tendría que calcular en lugar de "2" entre la cantidad de números que haya.

    // Mostrar el resultado en el div correspondiente
    const divResultado = document.getElementById("mostrar-una-lista");
    divResultado.style.opacity = 1;  // Hacer visible el div
    divResultado.innerHTML = `<p>La media aritmética de ${numero1} y ${numero2} es: ${media}</p>`;
}
