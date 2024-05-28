package PrimeraEvaluacion.UT4EjerciciosInicialesString;

import java.util.Scanner;

/**
 * <h1 style="text-align: center;">Clase MPAAEjercicio8InvertirMayusMinus</h1><br>
 * <p style="text-align: justify;">
 * Esta clase permite al usuario introducir una cadena de texto y luego invertir el tipo de letra
 * de cada carácter en la cadena, es decir, convierte las letras minúsculas a mayúsculas y las mayúsculas a minúsculas.
 * </p><br>
 *
 * @author Agu1406 (Agustín)
 * @since 2023-09-24
 * @version 1.0
 */
public class MPAAEjercicio8InvertirMayusMinus {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        boolean tipoLetra; // "false" es minúscula, "true" es mayúscula.
        String cadenaOriginal; // Es la que proporciona el usuario.
        String cadenaInvertida = ""; // Es idéntica pero con el tipo de letra invertido.

        System.out.println("¡Hola! Introduce una cadena de texto.");
        cadenaOriginal = teclado.nextLine();

        /*
         * Para poder invertir el orden de mayúsculas y minúsculas lo que
         * hice fue crear una nueva cadena "invertida" e irle agregando
         * una por una las letras hasta contener la misma información
         * que la cadena "original".
         */
        for (int posicion = 0; posicion < cadenaOriginal.length(); posicion++) {
            char letraInvertida; // guarda la letra original pero invertida (mayúsculas / minúsculas)

            // Llama a la función que "mira" una letra para saber si es mayúscula "true" o minúscula "false".
            tipoLetra = verificarMayusMinus(cadenaOriginal, posicion);

            // Si es "true" la convierte en minúsculas, si es "false" la convierte en mayúsculas.
            if (tipoLetra) {
                letraInvertida = Character.toLowerCase(cadenaOriginal.charAt(posicion));
            } else {
                letraInvertida = Character.toUpperCase(cadenaOriginal.charAt(posicion));
            }

            // Le suma a la cadena invertida su valor anterior + la nueva letra invertida.
            cadenaInvertida += letraInvertida;

            // Método alternativo que se podría haber hecho con StringBuilder (explicado en clase)
        }

        // Mostrar la cadena invertida
        System.out.println("Cadena con mayúsculas y minúsculas invertidas: " + cadenaInvertida);

        // Alternativa usando StringBuilder
        StringBuilder cadenaInvertidaBuilder = new StringBuilder();
        for (int posicion = 0; posicion < cadenaOriginal.length(); posicion++) {
            char letraInvertida;

            tipoLetra = verificarMayusMinus(cadenaOriginal, posicion);

            if (tipoLetra) {
                letraInvertida = Character.toLowerCase(cadenaOriginal.charAt(posicion));
            } else {
                letraInvertida = Character.toUpperCase(cadenaOriginal.charAt(posicion));
            }

            cadenaInvertidaBuilder.append(letraInvertida);
        }

        // Mostrar la cadena invertida usando StringBuilder
        System.out.println("Cadena con mayúsculas y minúsculas invertidas (StringBuilder): " + cadenaInvertidaBuilder);
    }

    /**
     * <h2 style="text-align: left;">Método verificarMayusMinus</h2><br>
     * <p style="text-align: justify;">
     * Este método verifica si el carácter en la posición especificada de la cadena es mayúscula o minúscula.
     * </p>
     *
     * @param cadenaOriginal Cadena de texto original.
     * @param posicion Posición del carácter en la cadena.
     * @return true si el carácter es mayúscula, false si es minúscula.
     * @since 2023-09-24
     */
    private static boolean verificarMayusMinus(String cadenaOriginal, int posicion) {
        char letraExtraida; // Extraigo la letra en la posición de "posición".
        boolean respuesta; // Devuelvo la respuesta de si es mayúscula o minúscula.

        // Extraigo la letra en la posición de la cadena en la que estoy actualmente.
        letraExtraida = cadenaOriginal.charAt(posicion);

        // Si es mayúscula será "true", en otro caso será "false".
        respuesta = Character.isUpperCase(letraExtraida);

        return respuesta; // Devuelvo la respuesta del método.
    }
}
