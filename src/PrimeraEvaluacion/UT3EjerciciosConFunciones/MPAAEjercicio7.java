package PrimeraEvaluacion.UT3EjerciciosConFunciones;

import java.util.Scanner;

/**
 * <h1 style="text-align: center;">Clase DivisionNumeros</h1><br>
 * <p style="text-align: justify;">
 * Este programa permite al usuario realizar la división de dos números.
 * Asegura que el divisor no sea cero y calcula el resultado de la división,
 * mostrando el resultado flotante.
 * </p><br>
 *
 * @version 1.0
 * @since 21/10/23
 * @author Agu1406 (Agustín)
 */
public class MPAAEjercicio7 {

    public static void main(String[] args) {
        Scanner teclado = new Scanner (System.in);
        System.out.print("Introduce el dividendo: ");
        float dividendo = teclado.nextFloat();

        float divisor;
        do {
            System.out.print("Introduce el divisor (diferente de cero): ");
            divisor = teclado.nextFloat();
            if (divisor == 0) {
                System.out.println("El divisor no puede ser cero. Por favor, intenta de nuevo.");
            }
        } while (divisor == 0);

        float resultado = dividirNumeros(dividendo, divisor);
        System.out.println("El resultado de la división es: " + resultado);
    }

    /**
     * <h2 style="text-align: center;">Método dividirNumeros</h2><br>
     * <p style="text-align: justify;">
     * Realiza la división de dos números flotantes proporcionados y devuelve el resultado.
     * Este método asegura que la división sea válida, es decir, que el divisor no sea cero,
     * y calcula el resultado de la división, retornando el valor flotante obtenido.
     * </p><br>
     *
     * @param dividendo El número a dividir.
     * @param divisor El número por el cual dividir, asegurándose de que no sea cero.
     * @return El resultado de la división como un float.
     */
    private static float dividirNumeros(float dividendo, float divisor) {
        float resultado;  // Calcula el resultado de la división
        resultado = dividendo / divisor;
        return resultado;  // Devuelve el resultado calculado
    }
}
