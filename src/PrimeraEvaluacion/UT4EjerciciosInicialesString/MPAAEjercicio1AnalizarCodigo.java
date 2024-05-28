package PrimeraEvaluacion.UT4EjerciciosInicialesString;

/**
 * <h1 style="text-align: center;">Clase MPAAEjercicio1AnalizarCodigo</h1><br>
 * <p style="text-align: justify;">
 * Esta clase contiene un ejemplo para analizar el comportamiento de las referencias y comparaciones de objetos <code>String</code> en Java.
 * Se demuestra cómo dos referencias pueden apuntar al mismo objeto y cómo comparar objetos <code>String</code> tanto por referencia como por valor.
 * </p><br>
 * @author Agu1406 (Agustín)
 * @since 2023-09-09
 * @version 1.0
 */
public class MPAAEjercicio1AnalizarCodigo {
    public static void main(String[] args) {

        /*
        * En este primer "trozo" de código ambos objetos, "str1" y "str2" apuntan
        * al mismo lugar en la memoria, por eso, si ambos tienen el mismo texto
        * alojado en su interior, para Java son el mismo objeto y tienen el
        * mismo valor.
        * */
        String str1 = "El lenguaje Java";
        String str2 = str1;
        System.out.println("String1 --> " + str1);
        System.out.println("String2 --> " + str2);
        System.out.println("¿Es el mismo objeto? --> " + (str2 == str1));
        System.out.println("¿El mismo valor? --> " + (str2.equals(str1)));
        System.out.println(" -- Separador de la primera prueba y la segunda prueba -- ");

        /*
        * En este segundo ejemplo, como usamos la palabra "new" estamos forzando a Java
        * a guardar en lugares diferentes de la memoria cada uno de ellos, haciendo que
        * aunque tengan el mismo valor (el mismo texto), al no estár en el mismo lugar
        * de la memoria sean objetos diferentes.
        * */
        str2 = new String(str1);
        System.out.println("String1 --> " + str1);
        System.out.println("String2 --> " + str2);
        System.out.println("¿Es el mismo objeto? --> " + (str2 == str1));
        System.out.println("¿El mismo valor? --> " + (str2.equals(str1)));
    }
}
