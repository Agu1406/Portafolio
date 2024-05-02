package UT3EjerciciosConBucles;

import java.util.Scanner;

/**
 * <h1 style="text-align: center;">Clase MPAAEjercicio2</h1><br>
 * <p style="text-align: justify;">
 * Esta clase proporciona una aplicación que pide al usuario un número y calcula su factorial.
 * El factorial de un número es el producto de todos los enteros desde 1 hasta el propio número.
 * Por ejemplo, 5! = 1x2x3x4x5=120. En este caso, se utiliza un bucle do-while para realizar el cálculo.
 * </p><br>
 *
 * @version 1.0
 * @since 10/10/2023
 */
public class MPAAEjercicio2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduce un número para calcular su factorial: ");
        int numero = scanner.nextInt();
        scanner.close();

        long factorial = 1; // Almacena el valor / resultado del cálculo factorial.
        int contador = 1; // Controla la cantidad de veces que algo ocurre.

        // Utilizando un bucle do-while para calcular el factorial
        do {
            // Se multiplica así mismo por el número actual del contador.
            factorial *= contador;
            // Incrementa la variable "contador".
            contador++;
        } while (contador <= numero);

        System.out.printf("El factorial de %d es: %d%n", numero, factorial);
    }
}
