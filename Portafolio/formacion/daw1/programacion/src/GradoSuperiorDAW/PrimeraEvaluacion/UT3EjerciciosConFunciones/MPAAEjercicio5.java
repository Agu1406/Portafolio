package GradoSuperiorDAW.PrimeraEvaluacion.UT3EjerciciosConFunciones;

import java.util.Scanner;

/**
 * <h1 style="text-align: center;">Clase ConvertidorDeTiempo</h1><br>
 * <p style="text-align: justify;">
 * Este programa incluye una función que recibe un número de segundos y calcula cuántos días,
 * horas, minutos y segundos corresponden a esa cantidad. Los resultados se muestran en formato
 * de tiempo legible.
 * </p><br>
 *
 * @version 1.0
 * @since 21/10/23
 * @author Agu1406 (Agustín)
 */
public class MPAAEjercicio5 {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        System.out.print("Introduce el número de segundos: ");
        int segundosTotales = teclado.nextInt();

        mostrarTiempoDesglosado(segundosTotales);
    }

    /**
     * Desglosa un total de segundos en días, horas, minutos y segundos.
     *
     * @param segundosTotales El número total de segundos a convertir.
     */
    public static void mostrarTiempoDesglosado(int segundosTotales) {
        /* Un minuto tiene 60 segundos y una hora tiene 60 minutos
        * por lo tanto 60 x 60 = 3600 segundos por hora, por eso,
        * un día tiene 24 horas, por eso la cantidad de segundos
        * entonces 3.600 x 24 es igual a la cantidad de segundos
        * que tiene un dia, eso es 86.400, si tomamos nuestros
        * segundos totales y los dividimos entre eso podemos
        * conseguir cuantos días hay en esa cantidad de
        * segundos. */
        int dias = segundosTotales / (24 * 3600);
        /* Los segundos que no se pudieron convertir a días
        * completos los guardamos aquí. */
        int segundosRestantes = segundosTotales % (24 * 3600);
        // Convertimos esos segundos en horas completas.
        int horas = segundosRestantes / 3600;
        /* Otra vez, los segundos que no se pudieron convertir
        * en horas completas, se guardan aquí.*/
        segundosRestantes %= 3600;
        // Convertimos esos segundos restantes en minutos.
        int minutos = segundosRestantes / 60;
        /* Otra vez, los segundos que no se pudieron convertir
         * en minutos completos, se guardan aquí.*/
        segundosRestantes %= 60;
        // Ya se quedan como segundos y nada más.

        System.out.println(segundosTotales + " segundos equivalen a: ");
        System.out.println(dias + " día(s), " + horas + " hora(s), " + minutos + " minuto(s) y " + segundosRestantes + " segundo(s).");    }
}
