package GradoSuperiorDAW.PrimeraEvaluacion.UT4EjerciciosInicialesString;

import java.util.Scanner;

/**
 * <h1 style="text-align: center;">Clase MPAAEjercicio10IdentificarPalindromo</h1><br>
 * <p style="text-align: justify;">
 * Esta clase permite al usuario introducir una palabra y verificar si es un palíndromo.
 * Un palíndromo es una palabra que se lee igual de adelante hacia atrás que de atrás hacia adelante.
 * </p><br>
 *
 * @author Agu1406 (Agustín)
 * @since 2023-09-24
 * @version 1.0
 */
public class MPAAEjercicio10IdentificarPalindromo {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        String cadena, cadenaInversa = "";

        /*
         * Para poder identificar si una palabra es un palíndromo
         * mi forma de abordar el problema fue crear un espejo de
         * la palabra, pero inverso e ir comparando las letras una
         * por una en sentidos diferentes, si al recorrer ambas
         * cadenas en sentidos contrarios cada letra en su una
         * posición resulta ser iguales, es un palíndromo.
         * */

        System.out.println("Introduce una palabra:");
        cadena = teclado.next();

        // Bucle para crear un espejo invertido de la cadena original.
        for (int posicion = cadena.length() - 1; posicion >= 0; posicion--) {
            // Crea una cadena invertida carácter por carácter a partir de la original.
            cadenaInversa += String.valueOf(cadena.charAt(posicion));
        }

        /*
         * Ahora que ya tenemos dos cadenas idénticas, una al derecho y la otra
         * al revés, podemos verificar si son iguales o no utilizando un simple
         * equals.
         * */

        boolean sonIguales = cadena.equals(cadenaInversa);

        System.out.println("¿Es un palíndromo? " + sonIguales);
    }
}
