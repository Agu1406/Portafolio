package PrimeraEvaluacion.UT4EjerciciosInicialesString;

import java.util.Scanner;

/**
 * <h1 style="text-align: center;">Clase MPAAEjercicio3AnalizarCodigo</h1><br>
 * <p style="text-align: justify;">
 * Esta clase contiene un ejemplo que solicita al usuario que introduzca una cadena de caracteres utilizando la clase <code>Scanner</code>.
 * El propósito de este ejemplo es demostrar cómo leer datos de entrada desde la consola en Java.
 * </p><br>
 *
 * @author Agu1406 (Agustín)
 * @since 2023-09-09
 * @version 1.0
 */
public class MPAAEjercicio3AnalizarCodigo {

    public static void main(String[] args) {
        // Crear un objeto Scanner para leer la entrada del usuario desde la consola
        Scanner teclado = new Scanner(System.in);

        // Declarar una variable String para almacenar la cadena introducida por el usuario
        String cadena;

        // Mostrar un mensaje en la consola pidiendo al usuario que introduzca una cadena de caracteres
        System.out.println("Introduce una cadena de caracteres");

        // Leer la línea completa introducida por el usuario y almacenarla en la variable 'cadena'
        cadena = teclado.nextLine();
    }
}
