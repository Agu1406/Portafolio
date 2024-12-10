package GradoSuperiorDAW.PrimeraEvaluacion.UT3EjerciciosRepasoDelUT3;

import java.util.Scanner;

/**
 * <h1 style="text-align: center;">Clase Calificación Final en Programación</h1><br>
 * <p style="text-align: justify;">
 * Este programa calcula la calificación final de un alumno en la materia de Programación basándose en
 * el promedio de tres calificaciones parciales, la calificación del examen final y la calificación de
 * un trabajo final. Los porcentajes aplicados son 55% para el promedio de parciales, 30% para el examen
 * final y 15% para el trabajo final.
 * </p><br>
 *
 * @version 1.0
 * @since 21/10/23
 * @author Agu1406 (Agustín)
 */
public class MPAAEjercicio10 {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        System.out.print("Introduce la calificación del primer parcial: ");
        double parcial1 = teclado.nextDouble();
        System.out.print("Introduce la calificación del segundo parcial: ");
        double parcial2 = teclado.nextDouble();
        System.out.print("Introduce la calificación del tercer parcial: ");
        double parcial3 = teclado.nextDouble();
        System.out.print("Introduce la calificación del examen final: ");
        double examenFinal = teclado.nextDouble();
        System.out.print("Introduce la calificación del trabajo final: ");
        double trabajoFinal = teclado.nextDouble();

        double notaFinal = calcularNotaFinal(parcial1, parcial2, parcial3, examenFinal, trabajoFinal);
        System.out.println("La calificación final en la materia de Programación es: " + notaFinal);
    }

    /**
     * <h2 style="text-align: center;">Método calcularNotaFinal</h2><br>
     * <p style="text-align: justify;">
     * Calcula la nota final basada en las calificaciones de los parciales, el examen final y el trabajo final.
     * Utiliza los porcentajes establecidos para obtener la calificación final.
     * </p><br>
     *
     * @param parcial1 Calificación del primer parcial.
     * @param parcial2 Calificación del segundo parcial.
     * @param parcial3 Calificación del tercer parcial.
     * @param examenFinal Calificación del examen final.
     * @param trabajoFinal Calificación del trabajo final.
     * @return La calificación final calculada.
     */
    private static double calcularNotaFinal(double parcial1, double parcial2, double parcial3, double examenFinal, double trabajoFinal) {
        double promedioParciales = (parcial1 + parcial2 + parcial3) / 3;
        double notaFinal = promedioParciales * 0.55 + examenFinal * 0.30 + trabajoFinal * 0.15;
        return notaFinal;
    }
}
