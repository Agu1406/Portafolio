package PrimeraEvaluacion.UT4EjerciciosInicialesString;

import java.util.Scanner;

/**
 * <h1 style="text-align: center;">Clase MPAAEjercicio11CuentaPalabras</h1><br>
 * <p style="text-align: justify;">
 * Esta clase permite al usuario introducir una oración y cuenta el número de palabras en dicha oración.
 * El método utilizado para contar las palabras se basa en contar los espacios como separadores de palabras.
 * </p><br>
 *
 * @author Agu1406 (Agustín)
 * @since 2023-09-24
 * @version 1.0
 */
public class MPAAEjercicio11CuentaPalabras {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int totalPalabras = 0; //  Contador de cuantas palabras hay en una oración
        String cadena; // Oración introducida por el usuario con el Scanner.
        char letra; // Carácter temporal que utilizamos para comparaciones.

        // Solicitud por pantalla y por teclado de una oración.
        System.out.println("¡Hola! Introduce una oración.");
        // Utilizamos ".trim()" para eliminar espacios al principio y al final.
        cadena = teclado.nextLine().trim();

        /*
         * Para este ejercicio tomaremos en cuenta que la forma de contar
         * palabras será utilizando los espacios" " que se usan como
         * separadores, la cantidad total de separadores debería ser
         * el total de palabras en la oración.
         * */

        // Bucle que recorre todos los carácteres de una cadena, uno por uno.
        for (int pos = 0; pos < cadena.length() - 1; pos++) {
            // Guardamos el carácter en la posición actual del bucle.
            letra = cadena.charAt(pos);
            // Comparamos el carácter con un espacio en blanco, si son iguales, +1
            if (letra == ' ') {
                totalPalabras++;
            }
        }

        // Al total de palabras sumamos "1" para la palabra final que no tiene espacios.
        totalPalabras++;

        // Imprimimos por pantalla el resultado del programa.
        System.out.println("La oración introducida tiene un total de " + totalPalabras + " palabras.");
    }
}
