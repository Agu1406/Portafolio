package PrimeraEvaluacion.UT3EjerciciosRepasoDelUT3;

import java.util.Random;

/**
 * <h1 style="text-align: center;">Clase Suma y Producto de Números Aleatorios</h1><br>
 * <p style="text-align: justify;">
 * Este programa genera 10 números aleatorios entre 0 y 20. Calcula la suma de todos los números pares,
 * el producto de todos los números impares, y cuenta cuántos ceros se generaron.
 * </p><br>
 *
 * @version 1.0
 * @since 21/10/23
 * @author Agu1406 (Agustín)
 */
public class MPAAEjercicio9 {

    public static void main(String[] args) {
        Random generador = new Random();
        int sumaPares = 0;
        int productoImpares = 1;
        int conteoCeros = 0;
        int numero;

        System.out.println("Números generados:");

        for (int contador = 1; contador <= 10; contador++) {
            numero = generador.nextInt(21); // Genera un número aleatorio entre 0 y 20
            System.out.print(numero + " ");

            // Procesar si es par, impar o cero
            if (numero == 0) {
                conteoCeros++;
            } else if (numero % 2 == 0) {
                sumaPares += numero;
            } else {
                productoImpares *= numero;
            }
        }

        System.out.println("\n\nResultados:");
        System.out.println("Suma de los números pares: " + sumaPares);
        System.out.println("Producto de los números impares: " + (conteoCeros == 10 ? 0 : productoImpares)); // Si todos fueron ceros, el producto debe ser 0
        System.out.println("Cantidad de ceros: " + conteoCeros);
    }
}
