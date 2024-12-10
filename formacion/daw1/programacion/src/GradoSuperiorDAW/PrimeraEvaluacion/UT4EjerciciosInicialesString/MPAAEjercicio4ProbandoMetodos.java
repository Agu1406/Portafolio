package GradoSuperiorDAW.PrimeraEvaluacion.UT4EjerciciosInicialesString;
/**
 * <h1 style="text-align: center;">Clase MPAAEjercicio4ProbandoMetodos</h1><br>
 * <p style="text-align: justify;">
 * Esta clase contiene ejemplos de operaciones con la clase String en Java, incluyendo la muestra de caracteres,
 * comparación de cadenas, búsqueda de subcadenas, reemplazo de caracteres y subcadenas, y transformación de casos.
 * </p><br>
 *
 * @author Agu1406 (Agustín)
 * @since 2023-09-24
 * @version 1.0
 */
public class MPAAEjercicio4ProbandoMetodos {
    public static void main(String[] args) {
        /*
         * 4- Buscar en la API estándar de JAVA: http://java.sun.com/javase/8/docs/api/, la documentación de la
         *    clase String. Ayúdate de esta documentación para comprobar el correcto funcionamiento de la clase
         *    String, haciendo un código de prueba para realizar las siguientes operaciones: */

        //    a. Mostrar los caracteres de un String, uno a uno.
        int pos = 0; // Para controlar la posición del carácter que estoy mostrando.
        String cadena = "ABADENGO...123456"; // Texto del que mostraré uno por uno sus carácteres.

        // Bucle que mostrara todas las letras hasta que "pos" no sea menor a la longitud de la cadena.
        while (pos < cadena.length()) {

            // Imprimo por pantalla la posición actual y la letra en esa posición de la cadena.
            System.out.println("Letra en la posición " + pos + " es: " + cadena.charAt(pos));

            pos++; // Muestro la letra en la posición de "pos" y sumo "1" para pasar a la siguiente posición.
        }

        //    b. Comparar dos String y decir si son iguales o no.
        String ejemplo1 = "Agustín"; // String de ejemplo para probar métodos.
        String ejemplo2 = "Agustín"; // String de ejemplo para probar métodos.

        System.out.println("¿Ambos Strings tienen el mismo contenido? Respuesta: " + ejemplo1.equals(ejemplo2));

        //   c. Comparar dos String y decir si son el mismo objeto String.
        System.out.println("¿Ambos Strings son el mismo objeto? Respuesta: " + (ejemplo1 == ejemplo2));

        //    d. Comparar dos String y decir si son iguales o no, sin tener en cuenta las mayúsculas/minúsculas.
        String ejemplo3 = "agustíN"; // Ejemplo con la última letra mayúscula para probar métodos.

        /* Tenemos que convertir los Strings completamente a mayúsculas o minúsculas para que luego, al
        * compararlo sean iguales, porque si comparamos "Agustín" con "agustíN" por la diferencia de
        * mayúscula - minúscula saltara que no son iguales. */

        System.out.println("¿Ambos Strings tienen el mismo contenido? Respuesta: " + (ejemplo1.equals(ejemplo3)));

        String ejemplo1convertido = ejemplo1.toLowerCase();
        String ejemplo3convertido = ejemplo3.toLowerCase();

        System.out.println("¿Ambos Strings tienen el mismo contenido? Respuesta: " + (ejemplo1convertido.equals(ejemplo3convertido)));

        //    e. Comprobar si un String empieza con una cadena determinada.
        String cadenaStart = "Hola Mundo";

        System.out.println("¿El String empieza con la palabra \"hola\"? Respuesta: "+ cadenaStart.startsWith("Hola"));

        //    f. Comprobar si un String finaliza con una cadena determinada.

        System.out.println("¿El string termina con la palabra \"Mundo\"? Respuesta: "+ cadenaStart.endsWith("Mundo"));

        //    g. Extraer una parte de un String, indicando el inicio y el fin, de dicha parte.
        String extraer = "Extrae la palabra \"queso\" del String por favor";

        /* Encuentra la posición inicial de la palabra "queso" usando el método indexOf
        * para encontrar la posición donde empieza la palabra "queso" en la cadena. */
        int inicio = extraer.indexOf("queso");

        /* Encuentra la posición final de la palabra "queso", como ya conocemos su
        * posición inicial, su posición final será la inicial + la longitud de la
        * palabra. */
        int fin = inicio + "queso".length();

        /* Extrae la palabra "queso" usando el método substring ahora que ya conocemos
        * en que posición empieza la palabra y en que posición termina, usando esos
        * datos como indices para decirle a Java "extra la palabra que está entre
        * X posición y X posición" */
        String palabraExtraida = extraer.substring(inicio, fin);

        // Imprime la palabra extraída comprobando que fue correcto.
        System.out.println("La palabra extraída es: " + palabraExtraida);

        //    h. Indicar en qué posición de un String aparece por primera vez un carácter.
        String buscaLetra = "Busca la primera posición donde aparece la letra X, ¿Vale? esfuerzate";

        int posicionLetra = buscaLetra.indexOf("X");

        System.out.println("La primera vez que aparece la letra X es en la posición " + posicionLetra);

        //    i. Indicar en qué posición de un String aparece por primera vez otro String.

        String buscaPalabra = "Busca la primera vez que aparece la palabra 'que' ¿Vale?";

        int posicionPalabra = buscaPalabra.indexOf("que");

        System.out.println("La palabra 'que' aparece por primera vez en la posición: " + posicionPalabra);

        //    j. Generar un String que sea resultado de concatenar dos Strings.
        String nombre = "Agustín"; // Mi primer nombre.
        String apellido = "Marquez"; // Mi primer apellido.

        String nombreCompleto = nombre + " " + apellido; // Dejo un espacio entre el nombre y el apellido.

        System.out.println("Mi nombre completo es: " + nombreCompleto);

        //    k. Generar un String, reemplazando todas las apariciones de un carácter por otro carácter.
        String remplazaLetras = "Remplaza todas las letras 'a' de esta cadena ¡Tú puedes!";

        // Usamos el método replace para reemplazar todas las 'a' con '4'
        String resultado = remplazaLetras.replace('a', '4');

        // Imprime el resultado
        System.out.println("Cadena original: " + remplazaLetras);
        System.out.println("Cadena modificada: " + resultado);

        //    l. Generar un String, reemplazando todas las apariciones de un String por otro String.
        String remplazaPalabras = "Quiero que te esfuerces y que rempalces todas las palabras 'que' ¿vale?";

        String palabrasRemplazadas = remplazaPalabras.replace("que", "khe");

        // m. Generar un String, reemplazando la primera aparición de un String por otro String.
        String original = "Reemplaza la primera aparición de la palabra 'reemplazar' en esta cadena.";
        String reemplazada = original.replaceFirst("reemplazar", "sustituir");

        // Imprime el resultado
        System.out.println("Cadena original: " + original);
        System.out.println("Cadena con primera aparición reemplazada: " + reemplazada);

        // n. Generar un String con todos los caracteres de otro String, en mayúsculas.
        String minusculas = "este texto se convertirá a mayúsculas";
        String mayusculas = minusculas.toUpperCase();

        // Imprime el resultado
        System.out.println("Cadena en minúsculas: " + minusculas);
        System.out.println("Cadena en mayúsculas: " + mayusculas);

        // o. Generar un String con todos los caracteres de otro String, en minúsculas.
        String textomayusculas = "ESTE TEXTO ORIGINALMENTE ESTABA EN MAYÚSCULA";
        String textominusculas = textomayusculas.toLowerCase();

        // Imprime el resultado
        System.out.println("Cadena en mayúsculas: " + textomayusculas);
        System.out.println("Cadena en minúsculas: " + textominusculas);
    }

}
