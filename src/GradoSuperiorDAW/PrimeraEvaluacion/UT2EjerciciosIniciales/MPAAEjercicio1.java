package GradoSuperiorDAW.PrimeraEvaluacion.UT2EjerciciosIniciales;

/**
 * Ejercicio N.º 1 - Crea un pequeño programa con distintos tipos de variables
 * donde se les asigne un valor y se muestre por pantalla.
 *
 * @author Agu1406 (Agustín)
 * @since 06/10/2023
 */
public class MPAAEjercicio1 {
    public static void main(String[] args) {
        /* En Java existen diferentes tipos de variables, en el UT2 se nos introdujo a las variables
         * de las que si bien, aunque no hicimos uso de todas, se nos dio una idea bastante clara de
         * cuáles eran y para qué sirven, esas variables son las detalladas a continuación: */

        /* Boolean: Representa dos valores: true o false. No tiene un tamaño específico definido por la
         * especificación de Java, pero en la práctica, ocupa 1 byte en la mayoría de las JVM, solo
         * admite los valores "true" de verdadero o "false" de falso, cualquier otro valor daria
         * un error. */
        boolean soyHumano = true; // ¿Soy humano? La respuesta es Si, verdadero, por eso su valor es "true".
        System.out.println("I'm human? the answer is: " + soyHumano);

        /* byte: Son números enteros de 8 bits con signo, su valor mínimo (negativo) es -128 y
         * su valor máximo (positivo) es 127, si se intentara asignar un valor a un "byte"
         * fuera de sus límites, ocasionaría un error, esto porque es como intentar llenar
         * un vaso de agua (con capacidad maxima de 250 ml) con 1 litro de agua, se
         * "desbordaría" y eso Java lo tolera poco o mejor dicho nada. */
        byte bytePositivo = 127; // Creación de un Byte en su máximo valor positivo posible.
        byte byteNegativo = -128; // Creación de un Byte en su valor máximo negativo posible.
        System.out.println("Byte positivo máximo: " + bytePositivo);
        System.out.println("Byte negativo máximo: " + byteNegativo);

        /* short: Son números enteros de 16 bits con signo (pueden ser positivos / negativos
         * su valor mínimo (negativo) puede ser de -32.768 y su valor máximo puede ser de
         * 32.767, no admite decimales, pasa lo mismo que con el byte, no puede soportar
         * desbordamientos. */
        short shortPositivo = 32767;
        short shortNegativo = -32768;
        System.out.println("Short positivo máximo: " + shortPositivo);
        System.out.println("Short negativo máximo: " + shortNegativo);

        /* int: Son números enteros de 32 bits con signo, su valor mínimo (negativo) es -2^31 y sabemos
         * (en Sistemas Informáticos vimos bases exponenciales y desplazamientos decimales), que eso es
         * -2.147.483.648, eso es porque -2 (2 negatívo) se multiplica consecutivamente 32 veces, es
         * decir 2 x 2 x 2 x 2... (otras 28 veces) = -2,147,483,648, lo mismo ocurre con su valor
         * máximo de 2^31-1. (2,147,483,647), el "-1" es por la perdida de espacio / memoria que
         * se reserva para el signo "+" o "-", eso es otra cosa que aprendimos en Sistemas, al
         * igual que con todas las variables de Java, no admite desbordamientos. */
        int intPositivo = 2147483647; // Aquí el "+" no es visible, pero está presente, por eso es 1 bit menos.
        int intNegativo = -2147483648; // Aquí si es visible ese "bit" perdido por el signo, porque se coloca el "-".
        System.out.println("Int positivo máximo: " + intPositivo);
        System.out.println("Int negativo máximo: " + intNegativo);

        /* long: son números enteros de 64 bits con signo. Valor mínimo de -2^63 y un valor máximo de 2^63-1,
         * aplicando el conocimiento anteriormente expuesto sabemos que sus valores máximos posibles son estos:
         * - long positivo: + 9.223.372.036.854.775.807
         * - long negativo: - 9.223.372.036.854.775.808
         * Además sabemos que se pierde un bit en la colocación del signo (si es positivo o negativo), por
         * eso el número positivo es 2^63-1, de ahí el "-1", por último, no admite desbordamientos. */
        long longPositivo = 9223372036854775807L; // Valor máximo que puede tener un long.
        long longNegativo = -9223372036854775808L; // Valor mínimo que puede tener un long.
        System.out.println("Long positivo máximo: " + longPositivo);
        System.out.println("Long negativo mínimo: " + longNegativo);

        /* float: Representa números con punto flotante (decimales) de precisión simple utilizando 32 bits,
         * Los números de tipos float son útiles para almacenar valores reales (con decimales), sus límites
         * y/o valores máximos se ven influenciados por la forma en la que se almacenan los valores dentro
         * de esos 32 bits, que es de la forma siguiente:
         *
         * - 1 bit se utiliza para el "signo", es decir, "negativo" o "positivo".
         * - 8 bits se utilizan para el valor entero del número, es decir, el valor no decimal.
         * - 23 bits, que son los restantes para los números decimales, los que están después de la coma.
         *
         * El valor máximo positivo de un float es aproximadamente 3.4028235e38, y el valor mínimo negativo,
         * que representa el negativo con la magnitud más grande posible, es aproximadamente -3.4028235e38,
         * siempre que se usan números del tipo flotante se debe indicar al final de estos la letra del
         * tipo de variable en eso, por eso los "float" terminan en la letra "f", además, Java reconoce
         * la letra "e", lo que le indica que 3.4028235e38f = 3.4028235 x 10^38 del tipo "f" */
        float floatPositivo = 3.4028235e38f; // Valor máximo positivo que puede tener un float.
        float floatNegativo = -3.4028235e38f; // Valor máximo negativo que puede tener un float.
        System.out.println("Float positivo máximo: " + floatPositivo);
        System.out.println("Float negativo máximo: " + floatNegativo);

        /* double: son número de punto flotante de precisión doble de 64 bits, a diferencia de sus los float
         * estos tienen prácticamente el doble de capacidad, curiosamente se llaman double, por lo que es
         * difícil olvidar la relación, sus 64 bits de información se organizan de la siguiente forma:
         *
         * - 1 bit se utiliza para el "signo", es decir, "negativo" o "positivo".
         * - 11 bits para el número entero, sin decimales
         * - 52 bits, es decir, los restantes, para los decimales.
         *
         * Sus valores máximos son enormes, tanto así que son una exponencia de 10 elevado a 308, siendo sus
         * valores máximos positivos y negativos los siguientes:
         *
         * - valor máximo positivo: + 1.7976931348623157e308
         * - valor máximo negativo: - 1.7976931348623157e308
         * */
        double doublePositivo = 1.7976931348623157e308; // Valor máximo positivo que puede tener un double.
        double doubleNegativo = -1.7976931348623157e308; // Valor máximo negativo que puede tener un double.
        System.out.println("Double positivo máximo: " + doublePositivo);
        System.out.println("Double negativo máximo: " + doubleNegativo);

        /* char: admite carácteres de 16 bits en el rango Unicode, se pueden asignar valores o bien usando
         * código UniCode o bien insertando el carácter directamente entre comillas simples, en Java las
         * comillas simples son caracteres y las comillas dobles cadenas de texto, solo pueden contener
         * uno y un solo carácter, no admite desbordamientos. */
        char miCaracter = 'A'; // Ejemplo de un carácter normal, utilizando la letra 'A'.
        char caracterUniCode = 'é'; // Un carácter escrito directo del código UniCode, es la letra "é".
        char caracterDirecto = 'é';
        System.out.println("Carácter normal: " + miCaracter);
        System.out.println("Carácter unicode: " + caracterUniCode);
        System.out.println("Carácter directo: " + caracterDirecto);

    }
}
