package PrimeraEvaluacion.UT3EjerciciosRepasoDelUT3;

import java.util.Scanner;

/**
 * <h1 style="text-align: center;">Clase Conversor Fahrenheit a Celsius</h1><br>
 * <p style="text-align: justify;">
 * Este programa convierte una temperatura dada en grados Fahrenheit a grados Celsius
 * utilizando la fórmula: C = (F-32)*5/9.
 * </p><br>
 *
 * @version 1.0
 * @since 21/10/23
 * @author Agu1406 (Agustín)
 */
public class MPAAEjercicio3 {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in); // Creamos un objeto Scanner para leer la entrada del usuario.
        System.out.print("Introduce la temperatura en grados Fahrenheit: ");
        double fahrenheit = teclado.nextDouble(); // Leer el valor de temperatura en Fahrenheit.

        double celsius = convertirFahrenheitACelsius(fahrenheit); // Convertir Fahrenheit a Celsius.
        System.out.printf("%.2f grados Fahrenheit son %.2f grados Celsius.%n", fahrenheit, celsius);
    }

    /**
     * <h2 style="text-align: center;">Método convertirFahrenheitACelsius</h2><br>
     * <p style="text-align: justify;">
     * Convierte la temperatura de Fahrenheit a Celsius.
     * </p><br>
     *
     * @param fahrenheit La temperatura en grados Fahrenheit.
     * @return La temperatura en grados Celsius.
     */
    private static double convertirFahrenheitACelsius(double fahrenheit) {
        return (fahrenheit - 32) * 5 / 9;
    }
}
