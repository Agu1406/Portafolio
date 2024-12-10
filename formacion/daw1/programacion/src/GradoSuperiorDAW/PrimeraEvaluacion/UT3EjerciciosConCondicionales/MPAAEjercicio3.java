package GradoSuperiorDAW.PrimeraEvaluacion.UT3EjerciciosConCondicionales;

import java.util.Scanner;

/**
 * <h1 style="text-align: center;">Clase CompararNumeros</h1><br>
 * <p style="text-align: justify;">
 * Este programa solicita al usuario dos números y determina si el primero es mayor que el segundo,
 * informando sobre el resultado de la comparación a través de la consola.
 * </p><br>
 *
 * @version 1.0
 * @since 19/10/24
 * @author Agu1406 (Agustín)
 */
public class MPAAEjercicio3 {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        System.out.print("Introduce el primer número: ");
        int primerNumero = teclado.nextInt();

        System.out.print("Introduce el segundo número: ");
        int segundoNumero = teclado.nextInt();

        // Estructura condicional para comparar los dos números introducidos.
        if (primerNumero > segundoNumero) {
            System.out.println("El primer número (" + primerNumero + ") es mayor que el segundo (" + segundoNumero + ").");
        } else if (primerNumero < segundoNumero) {
            System.out.println("El primer número (" + primerNumero + ") es menor que el segundo (" + segundoNumero + ").");
        } else {
            System.out.println("Ambos números son iguales.");
        }
    }
}
