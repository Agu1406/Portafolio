package PrimeraEvaluacion.UT3EjerciciosConBucles;

import java.util.Scanner;

/**
 * <h1 style="text-align: center;">Clase MPAAEjercicio10</h1><br>
 * <p style="text-align: justify;">
 * Este programa implementa una calculadora simple que permite al usuario realizar operaciones básicas
 * como sumar, restar, multiplicar y dividir. El usuario puede seleccionar la operación deseada a través de
 * un menú, y el programa seguirá mostrando el menú hasta que el usuario elija la opción de "Salir".
 * En la división, se valida que el divisor no sea cero, repitiendo la solicitud de entrada hasta que se
 * introduzca un valor válido.
 * </p><br>
 *
 * <h2 style="text-align: center;">Descripción del Ejercicio</h2><br>
 * <p style="text-align: justify;">
 * 10- Crea una calculadora con un menú que permita elegir entre “Sumar”, “Restar”,
 * “Multiplicar”, “Dividir” y “Salir”. Nota: En la división, si se introduce un cero como divisor,
 * se preguntará otra vez por este valor, hasta que se introduzca un número distinto de cero.
 * </p><br>
 *
 * @version 1.0
 * @since 10/10/2023
 * @author Agu1406 (Agustín)
 */
public class MPAAEjercicio10 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("Menú de la calculadora:");
            System.out.println("1. Sumar");
            System.out.println("2. Restar");
            System.out.println("3. Multiplicar");
            System.out.println("4. Dividir");
            System.out.println("5. Salir");
            System.out.print("Elige una opción (1-5): ");
            opcion = scanner.nextInt();

            if (opcion == 5) {
                System.out.println("Saliendo de la calculadora...");
                break;
            }

            System.out.print("Introduce el primer número: ");
            double num1 = scanner.nextDouble();
            System.out.print("Introduce el segundo número: ");
            double num2 = scanner.nextDouble();

            switch (opcion) {
                case 1: // Suma
                    System.out.println("Resultado: " + (num1 + num2));
                    break;
                case 2: // Resta
                    System.out.println("Resultado: " + (num1 - num2));
                    break;
                case 3: // Multiplicación
                    System.out.println("Resultado: " + (num1 * num2));
                    break;
                case 4: // División
                    while (num2 == 0) {
                        System.out.println("El divisor no puede ser cero. Introduce otro número:");
                        num2 = scanner.nextDouble();
                    }
                    System.out.println("Resultado: " + (num1 / num2));
                    break;
                default:
                    System.out.println("Opción no válida, por favor elige una opción entre 1 y 5.");
                    break;
            }
        } while (opcion != 5);

        scanner.close();
    }
}
