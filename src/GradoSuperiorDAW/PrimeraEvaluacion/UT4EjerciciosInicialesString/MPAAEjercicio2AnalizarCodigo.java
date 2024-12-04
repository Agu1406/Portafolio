package GradoSuperiorDAW.PrimeraEvaluacion.UT4EjerciciosInicialesString;

/**
 * <h1 style="text-align: center;">Clase MPAAEjercicio2AnalizarCodigo</h1><br>
 * <p style="text-align: justify;">
 * Esta clase contiene ejemplos para analizar y manipular cadenas de caracteres en Java.
 * Los ejemplos incluyen la obtención de la longitud de la cadena, la verificación de prefijos y sufijos,
 * la búsqueda de caracteres, la comparación de cadenas, el uso de subcadenas, la eliminación de espacios en blanco,
 * y la conversión entre cadenas y números.
 * </p><br>
 *
 * @author Agu1406 (Agustín)
 * @since 2023-09-09
 * @version 1.0
 */
public class MPAAEjercicio2AnalizarCodigo {
    public static void main(String[] args) {
        // Longitud del string, empieza a contar desde cero.
        String str = "El primer programa";
        System.out.println("longitud " + str.length());

        // Comienza y termina por... si es cierto, funciona con booleanos.
        str = "El primer programa";
        System.out.println("comienza por  \"El\" " + str.startsWith("El"));
        System.out.println("termina por \"programa\" " + str.endsWith("programa"));

        // Posición de un carácter, busca la posición de la primera letra "p" en el String usando indexof
        int pos = str.indexOf('p');
        System.out.println("posición de la letra p " + pos);

        // Segunda ocurrencia de 'p', como usamos un "+1", mostrara la segunda "p" que encuentre.
        pos = str.indexOf('p', pos + 1);

        // Comparación de orden lexicográfico (para ordenar grandes volumenes de datos)
        str = "Tomás";
        System.out.println("Orden alfabético " + str.compareTo("Alberto"));

        str = "Alberto";
        System.out.println("Orden alfabético " + str.compareTo("Tomás"));

        System.out.println("Orden alfabético " + str.compareTo("Alberto"));

        // Substring sirve para extraer un trozo de un String indicando posiciones.
        str = "El lenguaje Java";
        System.out.println("Substring " + str.substring(12));
        System.out.println("Substring " + str.substring(3, 11));

        // Eliminar los espacios en blanco al principio y al final de un String
        str = " 12 ";
        System.out.println("string original " + str);
        System.out.println("string sin espacios en blanco " + str.trim());

        // Convertir un número en string (sirve con casi cualquier variable)
        int valor = 24;
        str = String.valueOf(valor);
        System.out.println("número --> string " + str);

        // Convertir un string en número (sintaxis parecida para mayoría de variables)
        str = " 12 ";
        int numeroInt = Integer.parseInt(str.trim());
        System.out.println("string --> número " + numeroInt);
        str = "12.35 ";
        double numeroDouble = Double.valueOf(str).doubleValue();
        System.out.println("string --> número " + numeroDouble);
    }
}
