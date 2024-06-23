package GradoSuperiorDAW.PrimeraEvaluacion.UT4EjerciciosInicialesString;

import java.util.Scanner;

/**
 * <h1 style="text-align: center;">Clase MPAAEjercicio9BuscarCadenaEnCadena</h1><br>
 * <p style="text-align: justify;">
 * Esta clase permite al usuario introducir una cadena de texto y una subcadena,
 * y verifica si la subcadena se encuentra dentro de la cadena original.
 * La respuesta se personaliza para indicar si la subcadena se encuentra o no.
 * </p><br>
 *
 * @author Agu1406 (Agustín)
 * @since 2023-09-24
 * @version 1.0
 */
public class MPAAEjercicio9BuscarCadenaEnCadena {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in); // Instancia "Scanner" para poder insertar datos desde el teclado.
        String cadena, subcadena; // La cadena y la subcadena que busco dentro de ella.
        boolean respuesta; // "true" si efectivamente la subcadena existe dentro de la cadena, "false" si no.

        // Solicitud por pantalla y por teclado de una cadena / párrafo de texto.
        System.out.println("¡Hola! Introduce una cadena o párrafo.");
        cadena = teclado.nextLine();

        // Solicitud por pantalla y por teclado de la subcadena que buscaremos dentro de la cadena original.
        System.out.println("¿Qué palabra te gustaría buscar en la cadena anterior?");
        subcadena = teclado.nextLine();

        // Si la subcadena se encuentra dentro de la cadena en cualquier sitio, "true", si no, "false".
        respuesta = cadena.contains(subcadena);

        /*
         * Las respuestas "true" o "false" no me gustan, así que leyendo el manual de Java y las API me encontré
         * que se podían personalizar los mensajes y con ayuda de Jaime aprendí a hacerlo.
         */
        String trueFalsePersonalizado = respuesta ? "sí se encuentra" : "no se encuentra";

        // Imprimo mi respuesta del programa con mi "true-false" personalizado
        System.out.println("La subcadena \"" + subcadena + "\" " + trueFalsePersonalizado + " en la cadena original.");

        // Los \" se llaman barras de escape, me permiten utilizar caracteres como las comillas sin que Java los lea como código.
    }
}
