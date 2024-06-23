package GradoSuperiorDAW.PrimeraEvaluacion.UT3EjerciciosRepasoDelUT3;

import java.util.Random;

/**
 * <h1 style="text-align: center;">Clase Generador de Números Aleatorios</h1><br>
 * <p style="text-align: justify;">
 * Este programa genera 20 números aleatorios entre 10 y 30 y los muestra por pantalla.
 * Al final, informa sobre la presencia de números pares, impares y la posible presencia de ceros.
 * </p><br>
 *
 * @version 1.0
 * @since 21/10/23
 * @author Agu1406 (Agustín)
 */
public class MPAAEjercicio6 {

    public static void main(String[] args) {
        Random generadorAleatorio = new Random();

        boolean hayPar = false;
        boolean hayImpar = false;
        boolean hayCero = false;

        System.out.println("Números aleatorios generados entre 10 y 30:");
        for (int contador = 1; contador <= 20; contador++) {
            int numeroAleatorio = 10 + generadorAleatorio.nextInt(21); // Números entre 10 y 30
            System.out.print(numeroAleatorio + " ");

            if (numeroAleatorio % 2 == 0) {
                hayPar = true;
            } else if (numeroAleatorio == 0) {
                hayCero = true;
            } else {
                hayImpar = true;
            }
        }
        System.out.println("\n\nResultados de la generación de números:");
        System.out.println("¿Había algún número par? " + (hayPar ? "Sí" : "No"));
        System.out.println("¿Había algún número impar? " + (hayImpar ? "Sí" : "No"));
        System.out.println("¿Había algún cero? " + (hayCero ? "Sí" : "No"));
    }
}
