package GradoSuperiorDAW.PrimeraEvaluacion.UT3EjerciciosConCondicionales;

import java.util.Scanner;

/**
 * <h1 style="text-align: center;">Clase DivisionNumeros</h1><br>
 * <p style="text-align: justify;">
 * Este programa solicita al usuario dos números y realiza la división del primero por el segundo.
 * Verifica que el segundo número, el divisor, no sea cero para evitar errores matemáticos y
 * proporciona el resultado de la división o un mensaje de advertencia en caso de que el divisor sea cero.
 * </p><br>
 *
 * @version 1.0
 * @since 19/10/24
 * @author Agu1406 (Agustín)
 */
public class MPAAEjercicio4 {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        System.out.print("Introduce el primer número (dividendo): ");
        double dividendo = teclado.nextDouble();

        System.out.print("Introduce el segundo número (divisor): ");
        double divisor = teclado.nextDouble();

        // Condicional para asegurarse de que el divisor no es cero
        if (divisor != 0) {
            double resultado = dividendo / divisor;
            System.out.println("El resultado de la división es: " + resultado);
        } else {
            System.out.println("No se puede dividir por cero. Introduce un divisor diferente de cero.");
        }
    }
}
