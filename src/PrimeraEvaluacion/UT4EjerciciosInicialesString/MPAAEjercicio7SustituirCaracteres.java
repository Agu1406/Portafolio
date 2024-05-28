package PrimeraEvaluacion.UT4EjerciciosInicialesString;

import java.util.Scanner;

/**
 * <h1 style="text-align: center;">Clase MPAAEjercicio7SustituirCaracteres</h1><br>
 * <p style="text-align: justify;">
 * Esta clase permite al usuario introducir una cadena de texto y dos caracteres.
 * El primer carácter se utilizará como el original que se desea reemplazar en la cadena,
 * y el segundo carácter será el que sustituya al original en todas sus apariciones.
 * </p><br>
 *
 * @autor Agu1406 (Agustín)
 * @since 2023-09-24
 * @version 1.0
 */
public class MPAAEjercicio7SustituirCaracteres {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        char original, remplazo;
        String cadena;

        // Solicitar al usuario que introduzca una cadena de texto
        System.out.println("Introduce una cadena:");
        cadena = teclado.nextLine();

        // Solicitar al usuario que introduzca el carácter original a reemplazar
        System.out.println("Introduce un carácter (es el original):");
        original = teclado.nextLine().trim().charAt(0);

        // Solicitar al usuario que introduzca el carácter de reemplazo
        System.out.println("Introduce otro carácter (será el reemplazo):");
        remplazo = teclado.nextLine().trim().charAt(0);

        /*
         * Conversión de los "char" en "String" para poder ser
         * utilizados en el método replaceAll de la clase String.
         */
        String originalCast = String.valueOf(original);
        String remplazoCast = String.valueOf(remplazo);

        /*
         * Método de la clase String que permite reemplazar por completo
         * una secuencia entera de datos "String" por otra. Para utilizar
         * esto con caracteres es necesario previamente convertirlos en
         * String.
         */
        cadena = cadena.replaceAll(originalCast, remplazoCast);

        /*
         * Otra forma de hacerlo es con un "replace" que no requiere
         * convertir los "char" en "String".
         */
        cadena = cadena.replace(original, remplazo);

        // Mostrar la cadena resultante después de los reemplazos
        System.out.println("Cadena modificada: " + cadena);
    }
}
