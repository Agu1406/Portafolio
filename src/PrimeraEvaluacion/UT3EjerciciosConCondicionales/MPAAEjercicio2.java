package PrimeraEvaluacion.UT3EjerciciosConCondicionales;

import java.util.Scanner;

/**
 * <h1 style="text-align: center;">Clase NumeroParImpar</h1><br>
 * <p style="text-align: justify;">
 * Esta clase permite al usuario ingresar un número entero y determina si es par o impar,
 * mostrando un mensaje descriptivo en la consola. Utiliza una estructura condicional simple if-else
 * para evaluar la paridad del número introducido.
 * </p>
 * <br>
 *
 * @version 1.0
 * @since 19/10/24
 * @author Agu1406 (Agustín)
 */
public class MPAAEjercicio2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduce un número entero: ");
        int numero = scanner.nextInt();

        // Estructura condicional para evaluar si el número es par o impar.
        if (numero % 2 == 0) {
            System.out.println("El número introducido es par.");
        } else {
            System.out.println("El número introducido es impar.");
        }
    }
}
