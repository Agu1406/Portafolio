package GradoSuperiorDAW.PrimeraEvaluacion.UT3EjerciciosConCondicionales;

import java.util.Scanner;

/**
 * <h1 style="text-align: center">Clase PositivoNegativoCero</h1><br>
 * <p style="text-align: justify">
 * Esta clase implementa dos métodos para determinar si un número introducido es positivo,
 * negativo o cero, y se analiza cuál de los dos métodos es más eficiente y por qué.
 * </p>
 * <br>
 * <h2 style="text-align: center">Métodos de Evaluación</h2>
 * <ul style="text-align: justify">
 *     <li>positivoNegativoCero: Utiliza una estructura if-else simple para evaluar el número.</li>
 *     <br>
 *     <li>positivoNegativoCeroOptimizado: Emplea una estructura anidada if para evaluar el número,
 *     lo que podría ser más eficiente en algunos contextos.</li>
 * </ul>
 * <br>
 * <p style="text-align: justify">
 * Tras la comparación, se determinará cuál es el método preferido basado en la eficiencia y la claridad del código.
 * </p>
 * <br>
 *
 * @author Agu1406 (Agustín)
 * @version 1.0
 * @since 19/10/24
 */
public class MPAAEjercicio1 {

    /**
     * Este método evalúa si el número introducido por el usuario es positivo, negativo o cero.
     * Utiliza una estructura if-else simple para realizar la evaluación.
     */
    public static void positivoNegativoCero() {
        Scanner teclado = new Scanner(System.in);
        System.out.print("Introduce un número entero: ");
        int numero = teclado.nextInt();

        if (numero == 0) {
            System.out.println("El número es cero");
        } else if (numero > 0) {
            System.out.println("El número es positivo");
        } else {
            System.out.println("El número es negativo");
        }
    }

    /**
     * Este método evalúa si el número introducido por el usuario es positivo, negativo o cero.
     * Utiliza una estructura anidada if para realizar la evaluación.
     */
    public static void positivoNegativoCeroOptimizado() {
        Scanner teclado = new Scanner(System.in);
        System.out.print("Introduce un número entero: ");
        int numero = teclado.nextInt();

        if (numero == 0) {
            System.out.println("El número es cero");
        } else {
            if (numero > 0) {
                System.out.println("El número es positivo");
            } else {
                System.out.println("El número es negativo");
            }
        }
    }

    /**
     * <h2 style="text-align: center">Análisis de eficiencia:</h2>
     * <p style="text-align: justify">
     * Ambos métodos son funcionalmente equivalentes, pero el método 'positivoNegativoCero'
     * es más eficiente en términos de claridad y legibilidad. Esto se debe a que utiliza
     * menos anidaciones y es más directo en su lógica, lo que hace que sea más fácil de
     * entender y mantener. La eficiencia de ejecución de ambos métodos es la misma ya que
     * el número de operaciones realizadas es igual en ambos casos.
     * </p><br><p style="text-align: justify">
     * Si a mi me lo preguntan, sin duda el mejor es el if anidado, la primera comprobación
     * descarta el número si es cero y la segunda solo necesita saber si es mayor a "0", es
     * por eso que con tan solo dos "preguntas / comprobaciones" ya sabe si es "0" o bien
     * si es positivo o negativo.
     * </p>
     */
    public static void main(String[] args) {
        // Ejecutar ambos métodos
        positivoNegativoCero();
        positivoNegativoCeroOptimizado();
    }
}
