package PrimeraEvaluacion.UT3EjerciciosConBucles;
/**
 * <h1 style="text-align: center;">Clase MPAAEjercicio1</h1>
 * <br>
 * <p style="text-align: justify;">
 * Este programa demuestra el uso de diferentes tipos de bucles para mostrar números en secuencias
 * ascendentes y descendentes. Se exploran bucles while, do-while y for para imprimir números
 * del 1 al 20 y del 20 al 1 en la consola. Este ejercicio incluye mostrar los números usando
 * varios tipos de bucles y también hacer que vayan en saltos (de 3, de 10, etc.), utilizando
 * {@code System.out.print} para imprimir en la misma línea y {@code System.out.println} para
 * imprimir en distintas líneas.
 * </p>
 *
 * <h2 style="text-align: center;">Descripción del Ejercicio</h2>
 * <br>
 * <p style="text-align: justify;">
 * 1- Mostrar por pantalla los números del 1 al 20 y del 20 al 1, usando varios tipos de bucles.
 * También haciendo que vayan en saltos (de 3, de 10, etc.). Para mostrarlos puedes usar
 * {@code System.out.print}, para pintar en la misma línea o {@code System.out.println} para
 * pintar en distintas líneas.
 * </p>
 *
 * @version 1.0
 * @since 10/10/2023
 */
public class MPAAEjercicio1 {
    public static void main(String[] args) {
        int numeros = 0;

        // Utilizo un bucle while para mostrar los números del 0 al 20
        while (numeros <= 20) {
            System.out.printf("%d ", numeros);
            numeros++; // Al llegar aquí le suma  "1" a la variable "números".
        }
        System.out.println(" "); // Salto de linea.

        // Utilizo un bucle while para mostrar los números del 20 al 0.
        while (numeros >= 0) {
            System.out.printf("%d ", numeros);
            numeros--; // Al llegar aquí le resta "1" a la variable "números".
        }
        System.out.println(" "); // Salto de linea.


        // Utilizo un bucle do-while para mostrar los números del 0 al 20.
        do {
            System.out.printf("%d ", numeros);
            numeros++; // Al llegar aquí le suma  "1" a la variable "números".
        } while (numeros <= 20);
        System.out.println(" "); // Salto de linea.


        // Utilizo un bucle do-while para mostrar los números del 20 al 0.
        do {
            System.out.printf("%d ", numeros);
            numeros--; // Al llegar aquí le resta "1" a la variable "números".
        } while (numeros >= 0);
        System.out.println(" "); // Salto de linea.


        /* A continuación utilizo bucles "for" que son más complejos porque utilizan
        * un contador propio de ellos que debe ser "declarado" dentro de sí mismos,
        * por lo que adoptamos el nombre de "contador" en este ejercicio para esa
        * variable.*/

        // Utilizo un bucle for para mostrar los números del 0 al 20.
        for (int contador = 0; contador <= 20; contador++) {
            System.out.printf("%d ", contador);// Imprimo directamente el contador.
            // Toda la lógica del bucle está arriba (variable; condición; acción)
        }
        System.out.println(" "); // Salto de linea.

        // utilizo un bucle for para mostrar los números del 20 al 0.
        for (int contador = 20; contador >= 0; contador--) {
            System.out.printf("%d ", contador); // Imprimo directamente el contador.
            // Toda la lógica del bucle está arriba (variable; condición; acción)
        }
        System.out.println(" "); // Salto de linea.

    }
}
