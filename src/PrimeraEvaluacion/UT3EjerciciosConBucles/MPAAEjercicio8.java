package PrimeraEvaluacion.UT3EjerciciosConBucles;

import java.util.Scanner;

/**
 * <h1 style="text-align: center;">Clase MPAAEjercicio8</h1><br>
 * <p style="text-align: justify;">
 * Este programa solicita al usuario que introduzca números repetidamente hasta que se introduzca un cero.
 * Calcula y muestra la suma de todos los números introducidos así como la media de estos. La entrada de
 * números termina cuando el usuario introduce '0', que actúa como condición de parada del bucle.
 * </p><br>
 *
 * <h2 style="text-align: center;">Descripción del Ejercicio</h2><br>
 * <p style="text-align: justify;">
 * 8- Algoritmo que pida números hasta que se introduzca un cero. Debe imprimir la suma y la
 * media de todos los números introducidos.
 * </p><br>
 *
 * @version 1.0
 * @since 10/10/2023
 * @author Agu1406 (Agustín)
 */
public class MPAAEjercicio8 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int suma = 0;  // Acumula la suma de todos los números introducidos.
        int contador = 0;  // Contabiliza el número de entradas para calcular la media.
        int usuario;  // Almacena el número introducido por el usuario.

        System.out.println("Introduce números (0 para terminar):");

        usuario = scanner.nextInt();  // Primera lectura antes de entrar al bucle.
        while (usuario != 0) {
            suma += usuario;  // Suma el número introducido al total acumulado.
            contador++;  // Incrementa el contador de números introducidos.
            usuario = scanner.nextInt();  // Lectura del siguiente número dentro del bucle.
        }

        if (contador > 0) {
            double media = (double) suma / contador;  // Calcula la media de los números introducidos.
            System.out.println("Suma total: " + suma);
            System.out.println("Media de los números introducidos: " + media);
        } else {
            System.out.println("No se introdujeron números aparte del cero inicial.");
        }
    }
}
