package PrimeraEvaluacion.UT4EjerciciosInicialesString;

import java.util.Scanner;

/**
 * <h1 style="text-align: center;">Clase MPAAEjercicio6CadenaInvertida</h1><br>
 * <p style="text-align: justify;">
 * Esta clase invierte una cadena de texto introducida por el usuario.
 * </p><br>
 * <h2 style="text-align: center;">Descripción</h2>
 * <p style="text-align: justify;">
 * La clase solicita una cadena de texto al usuario y la invierte, mostrando la cadena original y la cadena invertida por pantalla.
 * </p><br>
 * <h2 style="text-align: center;">Métodos</h2>
 * <ul>
 *  <li>{@link #invertirCadena(String)} - Método que invierte una cadena. </li>
 * </ul>
 *
 * @author Agu1406 (Agustín)
 * @since 2023-09-24
 * @version 1.0
 */
public class MPAAEjercicio6CadenaInvertida {
    public static void main(String[] args) {
        // Creamos una instancia de la clase "Scanner" llamándola "teclado".
        Scanner teclado = new Scanner(System.in);

        // Solicitamos al usuario que introduzca una cadena de texto.
        System.out.println("Introduce una cadena de texto: ");
        String cadena = teclado.nextLine();

        /* Usando la longitud de una cadena podemos obtener la última posición
         * de la última letra de una cadena. Restamos 1 porque las posiciones
         * de los caracteres en una cadena empiezan desde 0.
         */
        String cadenaInvertida = invertirCadena(cadena);

        // Mostramos la cadena original.
        System.out.println("La cadena original era: ");
        System.out.println(cadena);

        // Mostramos la cadena invertida.
        System.out.println("La cadena invertida es: ");
        System.out.println(cadenaInvertida);
    }

    /**
     * <h2 style="text-align: left;">Método invertirCadena</h2><br>
     * <p style="text-align: justify;">
     * Toma una cadena de datos (String) y la devuelve con los mismos datos
     * pero con el ordén invertido, ejemplo "ABED" y devuelve "DEBA".
     * </p>
     *
     * @param cadena Cadena a invertir proporcionada por el usuario.
     * @return una cadena idéntica pero inversa.
     */
    private static String invertirCadena(String cadena) {
        int pos = cadena.length() - 1;

        // Creamos una variable para almacenar la cadena invertida.
        String cadenaInvertida = "";

        // Bucle que recorre la cadena desde el final hasta el principio.
        while (pos >= 0) {
            // Añadimos el carácter en la posición actual a la cadena invertida.
            cadenaInvertida = cadenaInvertida + cadena.charAt(pos);

            // Decrementamos la posición para movernos hacia el principio de la cadena.
            pos--;
        }
        return cadenaInvertida;
    }
}
