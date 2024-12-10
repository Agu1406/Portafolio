package GradoSuperiorDAW.PrimeraEvaluacion.UT2EjerciciosIniciales;

/**
 * Ejercicio N.º 9 - Conversiones de tipo.
 * Este programa realiza distintas conversiones de tipo para demostrar la conversión entre
 * distintos tipos de datos. Se realizarán conversiones implícitas y explícitas (casting)
 * entre tipos primitivos en Java. Se debe tener cuidado con la pérdida de precisión
 * durante la conversión de tipos con más capacidad a tipos con menos.
 *
 * @author Agu1406 (Agustín)
 * @since 06/10/2023
 */
public class MPAAEjercicio9 {

    public static void main(String[] args) {
        // Conversión de float a int (casting explícito requerido)
        float miFloat = 9.8f;
        int miInt = (int) miFloat;
        System.out.println("Float convertido a Int: " + miInt);

        // Conversión de int a float (casting implícito)
        miInt = 5;
        miFloat = miInt;
        System.out.println("Int convertido a Float: " + miFloat);

        // Conversión de int a long (casting implícito)
        long miLong = miInt;
        System.out.println("Int convertido a Long: " + miLong);

        // Conversión de long a int (casting explícito requerido)
        miLong = 12345678901234L;
        miInt = (int) miLong;
        System.out.println("Long convertido a Int: " + miInt);

        // Conversión de double a float (casting explícito requerido)
        double miDouble = 9.8;
        miFloat = (float) miDouble;
        System.out.println("Double convertido a Float: " + miFloat);

        // Conversión de float a double (casting implícito)
        miDouble = miFloat;
        System.out.println("Float convertido a Double: " + miDouble);
    }
}
