package GradoSuperiorDAW.PrimeraEvaluacion.UT4EjerciciosInicialesString;

import java.util.Scanner;
/**
 * <h1 style="text-align: center;">Clase MPAAEjercicio5CuentaLetras</h1><br>
 * <p style="text-align: justify;">
 * Esta clase solicita una cadena de texto y un carácter (minúscula) por teclado,
 * y muestra cuántas veces aparece el carácter en la cadena. La comprobación de la
 * letra minúscula se realiza mediante una función que recibe un carácter y devuelve un booleano.
 * </p><br>
 * <h2 style="text-align: center;">Lista de métodos</h2>
 * <ul>
 *   <li>{@link #comprobarMinuscula(char)} - Comprueba si un carácter es una letra minúscula.</li>
 *   <li>{@link #contarCaracter(String, char)} - Cuenta cuantas veces aparece un carácter en una cadena</li>
 * </ul>
 *
 * @author Agu1406 (Agustín)
 * @since 2023-09-24
 * @version 1.0
 */
public class MPAAEjercicio5CuentaLetras {
    public static void main(String[] args) {
    /*
    * Pide una cadena y un carácter por teclado (comprueba que sea una letra minúscula)
    * y muestra cuantas veces aparece el carácter en la cadena. La comprobación de la
    * letra minúscula, debes hacerla con una función que recibirá un carácter y
    * devolverá un booleano
    * */

        // Primero debemos instanciar un "teclado" de la clase Scanner
        Scanner teclado = new Scanner(System.in);

        // Creamos un boolean para seguir solicitando la letra hasta que sea valida.
        boolean letraValida = false; // El bucle parará cuando sea true.

        // Creamos un String que el usuario introducirá más adelante.
        String cadena = " ";

        // Creamos el carácter que el usuario introducirá más adelante.
        char caracter = ' ';

        while (!letraValida) {
            // Creamos y solicitamos una cadena de texto por teclado.
            System.out.println("Introduce una cadena de texto. ");
            cadena = teclado.nextLine();

            // Creamos y solicitamos un carácter por teclado.
            System.out.println("Introduce un carácter para contar. ");
            caracter = teclado.nextLine().charAt(0);

            letraValida = comprobarMinuscula(caracter);
        }

        int conteoFinal = contarCaracter(cadena, caracter);

        System.out.println("La letra " + caracter + " aparece un total de " + conteoFinal + " veces.");
    }

    /**
     * <h2 style="text-align: left;">Método comprobarMinuscula</h2><br>
     * <p style="text-align: justify;">
     * Comprueba si un carácter es una letra minúscula.
     * </p>
     *
     * @param caracter Carácter a comprobar.
     * @return true si el carácter es una letra minúscula, false en caso contrario.
     */
    private static boolean comprobarMinuscula(char caracter) {
        if (Character.isLowerCase(caracter)) {
            System.out.println("La letra: \""+caracter+"\" es minúscula.");
        } else {
            System.out.println("La letra: \""+caracter+"\" es mayúscula.");
        }

        return Character.isLowerCase(caracter);
    }

    /**
     * <h2 style="text-align: left;">Método contarCaracter</h2><br>
     * <p style="text-align: justify;">
     * Cuenta cuántas veces aparece un carácter en una cadena de texto.
     * </p>
     *
     * @param cadena Cadena de texto en la que se va a buscar el carácter.
     * @param caracter Carácter a buscar en la cadena.
     * @return Número de veces que aparece el carácter en la cadena.
     */
    private static int contarCaracter(String cadena, char caracter) {
        int contador = 0;

        // Recorremos la cadena y contamos las apariciones del carácter.
        for (int posicion = 0; posicion < cadena.length(); posicion++) {
            if (cadena.charAt(posicion) == caracter) {
                contador++;
            }
        }
        return contador;
    }
}
