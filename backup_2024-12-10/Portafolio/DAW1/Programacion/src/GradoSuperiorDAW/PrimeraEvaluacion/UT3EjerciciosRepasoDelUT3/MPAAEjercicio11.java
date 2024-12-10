package GradoSuperiorDAW.PrimeraEvaluacion.UT3EjerciciosRepasoDelUT3;

import java.util.Scanner;

/**
 * <h1 style="text-align: center;">Clase DistanciaEntrePuntos</h1><br>
 * <p style="text-align: justify;">
 * Este programa calcula la distancia entre dos puntos en el plano cartesiano utilizando las coordenadas
 * proporcionadas por el usuario. La fórmula utilizada es la distancia euclidiana.
 * </p><br>
 *
 * @version 1.0
 * @since 21/10/23
 * @author Agu1406 (Agustín)
 */
public class MPAAEjercicio11 {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        double x1, y1, x2, y2, distancia;

        // Solicitar al usuario las coordenadas del primer punto
        System.out.println("Introduce las coordenadas del primer punto (x1, y1):");
        System.out.print("x1: ");
        x1 = teclado.nextDouble();
        System.out.print("y1: ");
        y1 = teclado.nextDouble();

        // Solicitar al usuario las coordenadas del segundo punto
        System.out.println("Introduce las coordenadas del segundo punto (x2, y2):");
        System.out.print("x2: ");
        x2 = teclado.nextDouble();
        System.out.print("y2: ");
        y2 = teclado.nextDouble();

        // Calcular la distancia
        distancia = calcularDistancia(x1, y1, x2, y2);

        // Mostrar la distancia
        System.out.printf("La distancia entre los puntos es: %.2f%n", distancia);
    }

    /**
     * <h2 style="text-align: center;">Método calcularDistancia</h2><br>
     * <p style="text-align: justify;">
     * Calcula la distancia euclidiana entre dos puntos dados en el plano cartesiano.
     * </p><br>
     *
     * @param x1 Coordenada x del primer punto.
     * @param y1 Coordenada y del primer punto.
     * @param x2 Coordenada x del segundo punto.
     * @param y2 Coordenada y del segundo punto.
     * @return La distancia calculada entre los dos puntos.
     */
    private static double calcularDistancia(double x1, double y1, double x2, double y2) {
        double distancia;
        distancia = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        return distancia;
    }
}
