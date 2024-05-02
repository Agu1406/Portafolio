package PrimeraEvaluacion.UT3EjerciciosConBucles;

import java.util.Scanner;

/**
 * <h1 style="text-align: center;">Clase MPAAEjercicio4</h1><br>
 * <p style="text-align: justify;">
 * Este programa solicita un número al usuario y muestra la tabla de multiplicar de ese número.
 * Utiliza un bucle while para iterar a través de los multiplicadores del 1 al 10 y calcular
 * los productos correspondientes.
 * </p><br>
 *
 * <h2 style="text-align: center;">Descripción del Ejercicio</h2><br>
 * <p style="text-align: justify;">
 * 4- Realizar un programa que muestre la tabla de multiplicar de un número introducido por
 * teclado.
 * </p><br>
 *
 * @version 1.0
 * @since 10/10/2023
 * @author Agu1406 (Agustín)
 */
public class MPAAEjercicio4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduce un número para ver su tabla de multiplicar: ");
        int numero = scanner.nextInt();  // Número para el cual se calcula la tabla de multiplicar.

        int multiplicador = 1;  // Multiplicador utilizado en la tabla de multiplicar, inicia en 1.

        System.out.println("Tabla de multiplicar del " + numero + ":");
        while (multiplicador <= 10) {
            System.out.printf("%d x %d = %d%n", numero, multiplicador, numero * multiplicador);
            multiplicador++;  // Incrementa el multiplicador por 1 en cada iteración.
        }
    }
}

