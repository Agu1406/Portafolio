package GradoSuperiorDAW.PrimeraEvaluacion.UT3EjerciciosRepasoDelUT3;
/**
 * <h1 style="text-align: center;">Clase Sucesiones de Números</h1><br>
 * <p style="text-align: justify;">
 * Este programa muestra distintas sucesiones de números por columnas.
 * Incluye números del 0 al 10, números pares e impares desde el 0, entre otros.
 * </p><br>
 *
 * @version 1.0
 * @since 21/10/23
 * @author Agu1406 (Agustín)
 */
public class MPAAEjercicio2 {

    public static void main(String[] args) {
        // Se realizará con un bucle for ya que conocemos el número de iteraciones
        for(int i = 0; i <= 10; i++) {
            // Números del 0 al 10
            int normal = i;
            // Números pares desde el 0
            int pares = i * 2;
            // Números impares desde el 1
            int impares = i * 2 + 1;
            // Números del 10 al 0
            int inverso = 10 - i;
            // Decenas exactas (0,10,20,…,100)
            int decenas = i * 10;
            // Números desde el 30
            int desde30 = 30 + i;
            // Números 0 y 1 alternativamente
            int alterno = i % 2;
            // Los cuadrados de los primeros números
            int cuadrado = i * i;
            // Las potencias del número 2
            int potencia2 = (int)Math.pow(2, i);

            // Impresión de las columnas
            System.out.printf("%d %d %d %d %d %d %d %d %d%n",
                    normal, pares, impares, inverso, decenas, desde30, alterno, cuadrado, potencia2);
        }
    }
}
