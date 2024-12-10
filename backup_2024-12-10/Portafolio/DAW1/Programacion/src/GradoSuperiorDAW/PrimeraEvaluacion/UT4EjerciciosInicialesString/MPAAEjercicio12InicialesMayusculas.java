package GradoSuperiorDAW.PrimeraEvaluacion.UT4EjerciciosInicialesString;

import java.util.Scanner;

/**
 * <h1 style="text-align: center;">Clase MPAAEjercicio12InicialesMayusculas</h1><br>
 * <p style="text-align: justify;">
 * Esta clase permite al usuario introducir un nombre y apellidos separados por espacios,
 * y genera un string con las iniciales de cada nombre y apellido en mayúsculas,
 * separadas por puntos.
 * </p><br>
 *
 * @author Agu1406 (Agustín)
 * @since 2023-09-24
 * @version 1.0
 */
public class MPAAEjercicio12InicialesMayusculas {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        String cadena, iniciales = "";
        char letra;

        // Solicitud por pantalla y por teclado de un nombre
        System.out.println("Introduce tu nombre y apellidos separado por espacios.");
        cadena = teclado.nextLine().trim();

        /*
         * Bucle que recorre toda la cadena, si encuentra un espacio en blanco toma
         * el siguiente carácter y lo guarda como mayúscula y lo "suma" al String
         * que contendrá las iniciales en mayúscula, en mi caso, por estética, las
         * iniciales se separaran por puntos. "."
         * */

        // Agregar la primera inicial del primer nombre.
        iniciales += Character.toUpperCase(cadena.charAt(0)) + ".";

        // Bucle que recorre todos los carácteres de una cadena, uno por uno.
        for (int pos = 1; pos < cadena.length(); pos++) {
            // Guardamos el carácter en la posición actual del bucle.
            letra = cadena.charAt(pos);

            // Si el carácter de esa posición es un espacio en blanco, ocurre el "if"
            if (letra == ' ' && pos + 1 < cadena.length()) {

                // Guardamos el carácter en la posición siguiente a la actual del bucle.
                letra = cadena.charAt(pos + 1);

                // con "Character" la convertimos en mayuscula.
                letra = Character.toUpperCase(letra);

                // Lo agregamos al String "iniciales" seguido de un punto.
                iniciales += letra + ".";
            }
        }

        // Imprimimos las iniciales
        System.out.println("Iniciales en mayúsculas: " + iniciales);
    }
}
